package cn.com.gene.controller;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jpay.ext.kit.HttpKit;
import com.jpay.ext.kit.PaymentKit;
import com.jpay.weixin.api.WxPayApiConfig;
import com.jpay.weixin.api.WxPayApiConfigKit;
import com.thoughtworks.xstream.XStream;
import cn.com.gene.comm.IDUtils;
import cn.com.gene.comm.PayCommonConfig;
import cn.com.gene.comm.ResultMap;
import cn.com.gene.pojo.Consume;
import cn.com.gene.pojo.Integral;
import cn.com.gene.pojo.Vipcount;
import cn.com.gene.service.InteService;
import cn.com.gene.service.VipService;
import cn.com.gene.weixin.HttpRequest;
import cn.com.gene.weixin.OrderInfo;
import cn.com.gene.weixin.OrderReturnInfo;
import cn.com.gene.weixin.RandomStringGenerator;
import cn.com.gene.weixin.SignInfo;
import cn.com.gene.weixin.Signature;
import net.sf.json.JSONObject;



@Controller
@SuppressWarnings("deprecation")
public class WeiXinPayController {
	
	@Autowired HttpServletRequest request;
	@Autowired VipService vipService;
	@Autowired InteService inteService;
	
	public static WxPayApiConfig getApiConfig() {
		return WxPayApiConfig.New().setAppId(PayCommonConfig.gene_appid).
				setMchId(PayCommonConfig.qcc_gzh_mchid).setPaternerKey(PayCommonConfig.qcc_gzh_partnerKey)
				.setPayModel(WxPayApiConfig.PayModel.BUSINESSMODEL);
	}
	
	/**小程序第一步获取OPENID
	 * NO 1 , 获取用户的openID
	 * **/ 
	@RequestMapping("/weixinpay/searchopenid")
	@ResponseBody
	public ResultMap getopenid(String opencode ) {
		String openid = "-1";
		List<Object> list = null;
		try {
			list = get_gzfzz_weixinuseridbyxiaochengxu (opencode);
			return ResultMap.IS_200(list);
		} catch (IOException e) {
			e.printStackTrace();
			return ResultMap.build(400, "获取OPENID失败", openid);
		}
	}
	
	
	
	/**小程序 第二步 创建订单
	 * NO2 ,创建订单
	 * 
	 * **/ 
	@RequestMapping("/weixinpay/createorder")
	@ResponseBody
	public ResultMap createorder(String openid, int total_free, String descname)
			throws IllegalAccessException, UnrecoverableKeyException, KeyManagementException, ClientProtocolException,
			KeyStoreException, NoSuchAlgorithmException, IOException {
		Long userid = IDUtils.searchuseridbyrequest(request);
		if (userid < 0 ) {return ResultMap.build(404,"未知用户");}
		OrderInfo order = new OrderInfo();
		String conId = IDUtils.genItemId();
		String out_trade_no = conId + "cz" + userid;
		order.setAppid(PayCommonConfig.gene_appid);
		order.setMch_id(PayCommonConfig.qcc_gzh_mchid);
		order.setNonce_str(RandomStringGenerator.getRandomStringByLength(32));
		order.setBody("金币充值");
		order.setTotal_fee(total_free);
		order.setSpbill_create_ip("120.24.43.56");
		// 充值回调
		String notify_url = "";
		if ("jbcz".equals(descname)) {
			notify_url = PayCommonConfig.jinbireturn;
		}
		if ("yecz".equals(descname)) {
			notify_url = PayCommonConfig.yuereturn;
		}		
		order.setOut_trade_no(out_trade_no);
		order.setNotify_url(notify_url);
		order.setTrade_type("JSAPI");
		order.setOpenid(openid);
		order.setSign_type("MD5");
		// 生成签名
		String sign = Signature.getSign(order);
		order.setSign(sign);

		String result = HttpRequest.sendPost(PayCommonConfig.weixin_tongyixiadan, order);
		XStream xStream = new XStream();
		xStream.alias("xml", OrderReturnInfo.class);
		OrderReturnInfo returnInfo = (OrderReturnInfo) xStream.fromXML(result);
		JSONObject json = new JSONObject();
		json.put("prepay_id", returnInfo.getPrepay_id());
		return ResultMap.IS_200(json);
	}
	
	
	
	/**再次生成签名
	 * NO3 ,再次签名
	 * 
	 * **/ 
	@ResponseBody
	@RequestMapping("/weixinpay/signafter")
	public ResultMap signafter(String repay_id ) throws IllegalAccessException {
		SignInfo signInfo = new SignInfo();
		signInfo.setAppId(PayCommonConfig.gene_appid);
		long time = System.currentTimeMillis() / 1000;
		signInfo.setTimeStamp(String.valueOf(time));
		signInfo.setNonceStr(RandomStringGenerator.getRandomStringByLength(32));
		signInfo.setRepay_id("prepay_id=" + repay_id);
		signInfo.setSignType("MD5");
		// 生成签名
		String sign = Signature.getSign(signInfo);
		JSONObject json = new JSONObject();
		json.put("timeStamp", signInfo.getTimeStamp());
		json.put("nonceStr", signInfo.getNonceStr());
		json.put("package", signInfo.getRepay_id());
		json.put("signType", signInfo.getSignType());
		json.put("paySign", sign);
		return ResultMap.IS_200(json);
	}
	
	
	@ResponseBody // 金币充值回调
	@RequestMapping(value = "/weixinpay/jinbi/return", method = { RequestMethod.POST, RequestMethod.GET })
	public String jinbireturn(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String xmlMsg = HttpKit.readData(request);
		String returnstr = "";
		Map<String, String> params = PaymentKit.xmlToMap(xmlMsg);
		String result_code = params.get("result_code");
		// 总金额
		String total_amount = params.get("total_fee");
		String out_trade_no = params.get("out_trade_no");
		WxPayApiConfigKit.setThreadLocalWxPayApiConfig(getApiConfig());
		if (PaymentKit.verifyNotify(params, WxPayApiConfigKit.getWxPayApiConfig().getPaternerKey())) {
			if (("SUCCESS").equals(result_code)) {
				String[] str = out_trade_no.split("cz");
				String descname = "金币充值";
				String userid = str[1];
				// 这里需要需要置顶更新和消费记录
				returnstr = updateConsumeAndIntegral(descname, total_amount, userid);
			}
		}
		return returnstr;
	}
	
	
	
	// 更新充值记录表和金币表信息
	private String updateConsumeAndIntegral(String descname, String total_amount,  String userid) {
		// 账单插入一条记录
		Consume consume = new Consume();
		Long ordernum = vipService.ordernum();
		consume.setConsumeid(ordernum);
		consume.setType(1); // 1-消费 ，2-充值
		// 记录充值的时间
		consume.setCreatetime(new Date());
		consume.setUpdatetime(new Date());
		if ("jbcz".equals(descname)) {descname = "金币充值";}
		consume.setDescname(descname);
		consume.setMonetary((Double.valueOf(total_amount) / 100.0));
		consume.setUserid(Long.valueOf(userid));
		vipService.addConsume(consume);

		Integral integral = new Integral();
		Long count = (Long.valueOf(total_amount) / 10);
		integral.setEvent(descname);
		integral.setUpdatetime(new Date());
		integral.setUserid(Long.valueOf(userid));
		integral.setType(12); // 金币事件类型
		integral.setState(1); // 金币状态        1-收入         2-支出
		integral.setCount(count);
		String str = inteService.chongzhirenturn(integral);
		
		// 这里作收益操作
		vipService.addinvitebalance(Long.valueOf(userid), Double.valueOf(total_amount) / 100.0);
		
		
		return str;
	}
	
	
	
	
	// 根据code获得小程序的openid
	@SuppressWarnings({ "resource", "unchecked"})
	public List<Object> get_gzfzz_weixinuseridbyxiaochengxu(String code) throws IOException {
		List<Object> list = new ArrayList<Object>();
		String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + 
				PayCommonConfig.gene_appid + "&secret=" + 
				PayCommonConfig.gene_Secret+ "&js_code="
				+ code + "&grant_type=authorization_code";
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		HttpResponse res = client.execute(post);
		if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			HttpEntity entity = res.getEntity();
			String str = EntityUtils.toString(entity, "utf-8");
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> jsonOb = mapper.readValue(str, Map.class);
			list.add(jsonOb.get("session_key"));
			list.add(jsonOb.get("openid"));
			list.add(jsonOb.get("unionid"));
		}
		return list;
	}
	
	
	
	
	// 充值
	@RequestMapping(value = "/weixinpay/yue/return", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public String pay_notify(HttpServletRequest request) {
		String xmlMsg = HttpKit.readData(request);
		Map<String, String> params = PaymentKit.xmlToMap(xmlMsg);
		String result_code = params.get("result_code");
		String total_amount = params.get("total_fee");  // 总金额
		String out_trade_no = params.get("out_trade_no"); // 订单号
		WxPayApiConfigKit.setThreadLocalWxPayApiConfig(getApiConfig());
		if (PaymentKit.verifyNotify(params, WxPayApiConfigKit.getWxPayApiConfig().getPaternerKey())) {
			if (("SUCCESS").equals(result_code)) {
				// 更新订单信息
				// log.warn("更新订单信息:" + attach);
				String[] str = null;
				String descname = "";
				if (out_trade_no.indexOf("cz") != -1) {
					str = out_trade_no.split("cz");
					descname = "余额充值";
				}
				String userid = str[1];
				// 更新充值记录表记录
				updateConsumeandVip(descname, total_amount, userid);
				Map<String, String> xml = new HashMap<String, String>();
				xml.put("return_code", "SUCCESS");
				xml.put("return_msg", "OK");
				return PaymentKit.toXml(xml);
			}
		}
		return null;
	}
	
	
	
	public String updateConsumeandVip(String descname, String total_amount, String user_id) {
		// 先插入充值记录
		Consume consume = new Consume();
		Long ordernum = vipService.ordernum();
		consume.setConsumeid(ordernum);
		consume.setType(2); // 1 ,消费 2.充值
		// 记录充值的时间
		consume.setCreatetime(new Date());
		consume.setUpdatetime(new Date());
		consume.setDescname(descname);
		consume.setMonetary((Double.valueOf(total_amount) / 100.0));
		consume.setUserid(Long.valueOf(user_id));
		// 修改订单状态为充值成功
		vipService.addConsume(consume);

		// 修改用户余额表记录
		Vipcount vipcount = vipService.searchmybure(Long.valueOf(user_id));
		vipcount.setBalance(vipcount.getBalance() + (Double.valueOf(total_amount) / 100.0));
		vipcount.setCreatetime(new Date());
		vipcount.setUserid(Long.valueOf(user_id));
		vipService.updateVipSelective(vipcount);
		return "success";
	}

}
