package cn.com.gene.comm;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/*
功能:		1xinxi.cn HTTP接口 发送短信

说明:		
http://sms.1xinxi.cn/asmx/smsservice.aspx?name=登录名&pwd=接口密码&mobile=手机号码&content=内容&sign=签名&st
ime=发送时间&type=pt&extno=自定义扩展码
 */
public class SendMessage {

	/**
	 * @param args
	 * @throws IOException
	 *             注册的验证码
	 */
	public static Map<String, String> getCodeByRegister(Long phone, HttpServletRequest request) throws IOException {
		int radomInt = new Random().nextInt(9999);
		String code = String.valueOf(radomInt);
		// 发送内容
		String content = "注册的手机验证码为:" + code + ",验证码五分种内有效。";
		String sign = "家谱表";

		// 创建StringBuffer对象用来操作字符串
		StringBuffer sb = new StringBuffer("http://sms.1xinxi.cn/asmx/smsservice.aspx?");

		// 向StringBuffer追加用户名
		sb.append("name=wylsz888@163.com");

		// 向StringBuffer追加密码（登陆网页版，在管理中心--基本资料--接口密码，是28位的）
		sb.append("&pwd=0899CED2A4D504A25A49D6712145");

		// 向StringBuffer追加手机号码
		sb.append("&mobile=" + phone);

		// 向StringBuffer追加消息内容转URL标准码
		content = java.net.URLEncoder.encode(content, "UTF-8");
		content = java.net.URLEncoder.encode(content, "UTF-8");
		content = java.net.URLDecoder.decode(content, "UTF-8");
		sb.append("&content=" + java.net.URLDecoder.decode(content, "UTF-8"));

		// 加签名
		sign = java.net.URLEncoder.encode(sign, "UTF-8");
		sign = java.net.URLEncoder.encode(sign, "UTF-8");
		sign = java.net.URLDecoder.decode(sign, "UTF-8");
		sb.append("&sign=" + java.net.URLDecoder.decode(sign, "UTF-8"));

		// 追加发送时间，可为空，为空为及时发送
		sb.append("&stime=");

		// type为固定值pt extno为扩展码，必须为数字 可为空
		sb.append("&type=pt&extno=");

		// 创建url对象
		// String temp = new String(sb.toString().getBytes("GBK"),"UTF-8");
		URL url = new URL(sb.toString());

		// 打开url连接
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		// 设置url请求方式 ‘get’ 或者 ‘post’
		connection.setRequestMethod("POST");

		// 发送
		InputStream is = url.openStream();

		// 转换返回值
		String returnStr = SendMessage.convertStreamToString(is);

		// 返回结果为‘0，20140009090990,1，提交成功’ 发送成功 具体见说明文档
		// 返回发送结果

		Map<String, String> ss = new HashMap<String, String>();
		ss.put("returnStr", returnStr);
		ss.put("code", code);
		// 尝试用session解决
		HttpSession session = request.getSession();
		// session存活时间
		session.setMaxInactiveInterval(300);
		// session.removeAttribute("code");
		session.setAttribute("code", code);
		return ss;

	}

	
	/**
	 * @param args
	 * @throws IOException
	 *             注册的验证码
	 */
	public static Map<String, String> passwordrelive(Long phone, HttpServletRequest request) throws IOException {
		int radomInt = new Random().nextInt(9999);
		String code = String.valueOf(radomInt);
		// 发送内容
		String content = "您好，您初次登录账户："+phone+",默认密码为：111111。";
		String sign = "家谱表";

		// 创建StringBuffer对象用来操作字符串
		StringBuffer sb = new StringBuffer("http://sms.1xinxi.cn/asmx/smsservice.aspx?");

		// 向StringBuffer追加用户名
		sb.append("name=wylsz888@163.com");

		// 向StringBuffer追加密码（登陆网页版，在管理中心--基本资料--接口密码，是28位的）
		sb.append("&pwd=0899CED2A4D504A25A49D6712145");

		// 向StringBuffer追加手机号码
		sb.append("&mobile=" + phone);

		// 向StringBuffer追加消息内容转URL标准码
		content = java.net.URLEncoder.encode(content, "UTF-8");
		content = java.net.URLEncoder.encode(content, "UTF-8");
		content = java.net.URLDecoder.decode(content, "UTF-8");
		sb.append("&content=" + java.net.URLDecoder.decode(content, "UTF-8"));

		// 加签名
		sign = java.net.URLEncoder.encode(sign, "UTF-8");
		sign = java.net.URLEncoder.encode(sign, "UTF-8");
		sign = java.net.URLDecoder.decode(sign, "UTF-8");
		sb.append("&sign=" + java.net.URLDecoder.decode(sign, "UTF-8"));

		// 追加发送时间，可为空，为空为及时发送
		sb.append("&stime=");

		// type为固定值pt extno为扩展码，必须为数字 可为空
		sb.append("&type=pt&extno=");

		// 创建url对象
		// String temp = new String(sb.toString().getBytes("GBK"),"UTF-8");
		URL url = new URL(sb.toString());

		// 打开url连接
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		// 设置url请求方式 ‘get’ 或者 ‘post’
		connection.setRequestMethod("POST");

		// 发送
		InputStream is = url.openStream();

		// 转换返回值
		String returnStr = SendMessage.convertStreamToString(is);

		// 返回结果为‘0，20140009090990,1，提交成功’ 发送成功 具体见说明文档
		// 返回发送结果

		Map<String, String> ss = new HashMap<String, String>();
		ss.put("returnStr", returnStr);
		ss.put("code", code);
		// 尝试用session解决
		HttpSession session = request.getSession();
		// session存活时间
		session.setMaxInactiveInterval(300);
		// session.removeAttribute("code");
		session.setAttribute("code", code);
		return ss;

	}
	/**
	 * 登录的验证码
	 * 
	 * @param phone
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static Map<String, String> getCodeByLogin(Long phone, HttpServletRequest request) throws IOException {
		int radomInt = new Random().nextInt(9999);
		String code = String.valueOf(radomInt);
		// 发送内容
		String content = "亲爱的用户您好,您的登录验证码为:" + code + ",验证码五分钟内有效。";
		String sign = "家谱表";
		// 发送内容
		//String content = "亲爱的七彩巢用户您好,您的登录验证码为:" + code + ",验证码五分钟内有效。";
		//String sign = "七彩巢";

		// 创建StringBuffer对象用来操作字符串
		StringBuffer sb = new StringBuffer("http://sms.1xinxi.cn/asmx/smsservice.aspx?");

		// 向StringBuffer追加用户名
		sb.append("name=wylsz888@163.com");

		// 向StringBuffer追加密码（登陆网页版，在管理中心--基本资料--接口密码，是28位的）
		sb.append("&pwd=0899CED2A4D504A25A49D6712145");

		// 向StringBuffer追加手机号码
		sb.append("&mobile=" + phone);

		// 向StringBuffer追加消息内容转URL标准码
		content = java.net.URLEncoder.encode(content, "UTF-8");
		content = java.net.URLEncoder.encode(content, "UTF-8");
		content = java.net.URLDecoder.decode(content, "UTF-8");
		sb.append("&content=" + java.net.URLDecoder.decode(content, "UTF-8"));

		// 加签名
		sign = java.net.URLEncoder.encode(sign, "UTF-8");
		sign = java.net.URLEncoder.encode(sign, "UTF-8");
		sign = java.net.URLDecoder.decode(sign, "UTF-8");
		sb.append("&sign=" + java.net.URLDecoder.decode(sign, "UTF-8"));

		// 追加发送时间，可为空，为空为及时发送
		sb.append("&stime=");

		// type为固定值pt extno为扩展码，必须为数字 可为空
		sb.append("&type=pt&extno=");

		// 创建url对象
		// String temp = new String(sb.toString().getBytes("GBK"),"UTF-8");
		URL url = new URL(sb.toString());

		// 打开url连接
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		// 设置url请求方式 ‘get’ 或者 ‘post’
		connection.setRequestMethod("POST");

		// 发送
		InputStream is = url.openStream();

		// 转换返回值
		String returnStr = SendMessage.convertStreamToString(is);

		// 返回结果为‘0，20140009090990,1，提交成功’ 发送成功 具体见说明文档
		// 返回发送结果

		Map<String, String> ss = new HashMap<String, String>();
		ss.put("returnStr", returnStr);
		// ss.put("code", code);
		// 尝试用session解决
		HttpSession session = request.getSession();
		// session存活时间
		session.setMaxInactiveInterval(300);
		// session.removeAttribute("code");
		session.setAttribute("code", code);
		return ss;

	}

	/**
	 * 找回密码
	 * 
	 * @param phone
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static Map<String, String> getCodeFindPwd(Long phone, HttpServletRequest request) throws IOException {
		int radomInt = new Random().nextInt(9999);
		String code = String.valueOf(radomInt);
		// 发送内容
		String content = "亲爱的户您好,您此次修改密码的验证码为:" + code + ",验证码五分钟内有效。";
		String sign = "家谱表";

		// 创建StringBuffer对象用来操作字符串
		StringBuffer sb = new StringBuffer("http://sms.1xinxi.cn/asmx/smsservice.aspx?");

		// 向StringBuffer追加用户名
		sb.append("name=wylsz888@163.com");

		// 向StringBuffer追加密码（登陆网页版，在管理中心--基本资料--接口密码，是28位的）
		sb.append("&pwd=0899CED2A4D504A25A49D6712145");

		// 向StringBuffer追加手机号码
		sb.append("&mobile=" + phone);

		// 向StringBuffer追加消息内容转URL标准码
		content = java.net.URLEncoder.encode(content, "UTF-8");
		content = java.net.URLEncoder.encode(content, "UTF-8");
		content = java.net.URLDecoder.decode(content, "UTF-8");
		sb.append("&content=" + java.net.URLDecoder.decode(content, "UTF-8"));

		// 加签名
		sign = java.net.URLEncoder.encode(sign, "UTF-8");
		sign = java.net.URLEncoder.encode(sign, "UTF-8");
		sign = java.net.URLDecoder.decode(sign, "UTF-8");
		sb.append("&sign=" + java.net.URLDecoder.decode(sign, "UTF-8"));

		// 追加发送时间，可为空，为空为及时发送
		sb.append("&stime=");

		// type为固定值pt extno为扩展码，必须为数字 可为空
		sb.append("&type=pt&extno=");

		// 创建url对象
		// String temp = new String(sb.toString().getBytes("GBK"),"UTF-8");
		URL url = new URL(sb.toString());

		// 打开url连接
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		// 设置url请求方式 ‘get’ 或者 ‘post’
		connection.setRequestMethod("POST");

		// 发送
		InputStream is = url.openStream();

		// 转换返回值
		String returnStr = SendMessage.convertStreamToString(is);

		// 返回结果为‘0，20140009090990,1，提交成功’ 发送成功 具体见说明文档
		// 返回发送结果

		Map<String, String> ss = new HashMap<String, String>();
		ss.put("returnStr", returnStr);
		// ss.put("code", code);
		// 尝试用session解决
		HttpSession session = request.getSession();
		// session存活时间
		session.setMaxInactiveInterval(300);
		// session.removeAttribute("code");
		session.setAttribute("code", code);
		return ss;

	}
	
	/**
	 * 找回密码
	 * 
	 * @param phone
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws MalformedURLException 
	 * @throws ProtocolException 
	 * @throws IOException
	 */
	public static Map<String, String> houseundercarriage(Long phone, String content,HttpServletRequest request)   {
		int radomInt = new Random().nextInt(9999);
		String code = String.valueOf(radomInt);
		
		String sign = "家谱";

		// 创建StringBuffer对象用来操作字符串
		StringBuffer sb = new StringBuffer("http://sms.1xinxi.cn/asmx/smsservice.aspx?");

		// 向StringBuffer追加用户名
		sb.append("name=wylsz888@163.com");

		// 向StringBuffer追加密码（登陆网页版，在管理中心--基本资料--接口密码，是28位的）
		sb.append("&pwd=0899CED2A4D504A25A49D6712145");

		// 向StringBuffer追加手机号码
		sb.append("&mobile=" + phone);
		Map<String, String> ss = new HashMap<String, String>();
		try {
			// 向StringBuffer追加消息内容转URL标准码
			content = java.net.URLEncoder.encode(content, "UTF-8");
			content = java.net.URLEncoder.encode(content, "UTF-8");
			content = java.net.URLDecoder.decode(content, "UTF-8");
			sb.append("&content=" + java.net.URLDecoder.decode(content, "UTF-8"));

			// 加签名
			sign = java.net.URLEncoder.encode(sign, "UTF-8");
			sign = java.net.URLEncoder.encode(sign, "UTF-8");
			sign = java.net.URLDecoder.decode(sign, "UTF-8");
			sb.append("&sign=" + java.net.URLDecoder.decode(sign, "UTF-8"));
			// 追加发送时间，可为空，为空为及时发送
			sb.append("&stime=");

			// type为固定值pt extno为扩展码，必须为数字 可为空
			sb.append("&type=pt&extno=");

			// 创建url对象
			// String temp = new String(sb.toString().getBytes("GBK"),"UTF-8");
			URL url = new URL(sb.toString());

			// 打开url连接
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			// 设置url请求方式 ‘get’ 或者 ‘post’
			connection.setRequestMethod("POST");

			// 发送
			InputStream is = url.openStream();
			// 转换返回值
			String returnStr = SendMessage.convertStreamToString(is);
			// 返回发送结果

			
			ss.put("returnStr", returnStr);
			// ss.put("code", code);
			// 尝试用session解决
			HttpSession session = request.getSession();
			// session存活时间
			session.setMaxInactiveInterval(300);
			// session.removeAttribute("code");
			session.setAttribute("code", code);
			return ss;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return ss;
		
		

		// 返回结果为‘0，20140009090990,1，提交成功’ 发送成功 具体见说明文档
		

	}
	
	
	/**
	 * 找回密码
	 * 
	 * @param phone
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws MalformedURLException 
	 * @throws ProtocolException 
	 * @throws IOException
	 */
	public static Map<String, String> qunfa(Long phone, String content,HttpServletRequest request) throws Exception  {
		int radomInt = new Random().nextInt(9999);
		String code = String.valueOf(radomInt);
		
		String sign = "家谱表";

		// 创建StringBuffer对象用来操作字符串
		StringBuffer sb = new StringBuffer("http://sms.1xinxi.cn/asmx/smsservice.aspx?");

		// 向StringBuffer追加用户名
		sb.append("name=wylsz888@163.com");

		// 向StringBuffer追加密码（登陆网页版，在管理中心--基本资料--接口密码，是28位的）
		sb.append("&pwd=0899CED2A4D504A25A49D6712145");

		// 向StringBuffer追加手机号码
		sb.append("&mobile=" + phone);

		// 向StringBuffer追加消息内容转URL标准码
		content = java.net.URLEncoder.encode(content, "UTF-8");
		content = java.net.URLEncoder.encode(content, "UTF-8");
		content = java.net.URLDecoder.decode(content, "UTF-8");
		sb.append("&content=" + java.net.URLDecoder.decode(content, "UTF-8"));

		// 加签名
		sign = java.net.URLEncoder.encode(sign, "UTF-8");
		sign = java.net.URLEncoder.encode(sign, "UTF-8");
		sign = java.net.URLDecoder.decode(sign, "UTF-8");
		sb.append("&sign=" + java.net.URLDecoder.decode(sign, "UTF-8"));

		// 追加发送时间，可为空，为空为及时发送
		sb.append("&stime=");

		// type为固定值pt extno为扩展码，必须为数字 可为空
		sb.append("&type=pt&extno=");

		// 创建url对象
		// String temp = new String(sb.toString().getBytes("GBK"),"UTF-8");
		URL url = new URL(sb.toString());

		// 打开url连接
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		// 设置url请求方式 ‘get’ 或者 ‘post’
		connection.setRequestMethod("POST");

		// 发送
		InputStream is = url.openStream();

		// 转换返回值
		String returnStr = SendMessage.convertStreamToString(is);

		// 返回结果为‘0，20140009090990,1，提交成功’ 发送成功 具体见说明文档
		// 返回发送结果

		Map<String, String> ss = new HashMap<String, String>();
		ss.put("returnStr", returnStr);
		// ss.put("code", code);
		// 尝试用session解决
		HttpSession session = request.getSession();
		// session存活时间
		session.setMaxInactiveInterval(300);
		// session.removeAttribute("code");
		session.setAttribute("code", code);
		return ss;

	}
	
	

	/**
	 * 换绑手机号
	 * 
	 * @param phone
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static Map<String, String> getCodeByUpdateTel(Long phone, HttpServletRequest request) throws IOException {
		int radomInt = new Random().nextInt(9999);
		String code = String.valueOf(radomInt);
		// 发送内容
		String content = "亲爱的用户您好,您此次换绑手机号的验证码为:" + code + ",验证码五分钟内有效。";
		String sign = "家谱表";

		// 创建StringBuffer对象用来操作字符串
		StringBuffer sb = new StringBuffer("http://sms.1xinxi.cn/asmx/smsservice.aspx?");

		// 向StringBuffer追加用户名
		sb.append("name=wylsz888@163.com");

		// 向StringBuffer追加密码（登陆网页版，在管理中心--基本资料--接口密码，是28位的）
		sb.append("&pwd=0899CED2A4D504A25A49D6712145");

		// 向StringBuffer追加手机号码
		sb.append("&mobile=" + phone);

		// 向StringBuffer追加消息内容转URL标准码
		content = java.net.URLEncoder.encode(content, "UTF-8");
		content = java.net.URLEncoder.encode(content, "UTF-8");
		content = java.net.URLDecoder.decode(content, "UTF-8");
		sb.append("&content=" + java.net.URLDecoder.decode(content, "UTF-8"));

		// 加签名
		sign = java.net.URLEncoder.encode(sign, "UTF-8");
		sign = java.net.URLEncoder.encode(sign, "UTF-8");
		sign = java.net.URLDecoder.decode(sign, "UTF-8");
		sb.append("&sign=" + java.net.URLDecoder.decode(sign, "UTF-8"));

		// 追加发送时间，可为空，为空为及时发送
		sb.append("&stime=");

		// type为固定值pt extno为扩展码，必须为数字 可为空
		sb.append("&type=pt&extno=");

		// 创建url对象
		// String temp = new String(sb.toString().getBytes("GBK"),"UTF-8");
		URL url = new URL(sb.toString());

		// 打开url连接
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		// 设置url请求方式 ‘get’ 或者 ‘post’
		connection.setRequestMethod("POST");

		// 发送
		InputStream is = url.openStream();

		// 转换返回值
		String returnStr = SendMessage.convertStreamToString(is);

		// 返回结果为‘0，20140009090990,1，提交成功’ 发送成功 具体见说明文档
		// 返回发送结果

		Map<String, String> ss = new HashMap<String, String>();
		ss.put("returnStr", returnStr);
		ss.put("code", code);
		// ss.put("code", code);
		// 尝试用session解决
		HttpSession session = request.getSession();
		// session存活时间
		session.setMaxInactiveInterval(300);
		// session.removeAttribute("code");
		session.setAttribute("code", code);
		return ss;

	}

	/**
	 * 转换返回值类型为UTF-8格式.
	 * 
	 * @param is
	 * @return
	 */
	public static String convertStreamToString(InputStream is) {
		StringBuilder sb1 = new StringBuilder();
		byte[] bytes = new byte[4096];
		int size = 0;

		try {
			while ((size = is.read(bytes)) > 0) {
				String str = new String(bytes, 0, size, "UTF-8");
				sb1.append(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb1.toString();
	}

}
