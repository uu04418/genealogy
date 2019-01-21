package cn.com.gene.inter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.com.gene.comm.Config;
import cn.com.gene.comm.ResourcesUtil;
import cn.com.gene.comm.ResultMap;
import cn.com.gene.service.UserService;
import net.sf.json.JSONObject;
/*
 * 登录拦截器，注明哪些方法需要登录以后才可以进行的操作要 [此拦截器主要作用用后台用户登录作拦截]
 * 
 */
public class LoginInterceptor implements HandlerInterceptor {
	
	
	@Autowired UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 先获得需要放行的url
		List<String> commonurl = ResourcesUtil.gekeyList(Config.common_url);
		// 用户访问的url
		String url = request.getRequestURI();
		String accesstoken = "";
		Long userid = -1L;
		
		// 判断是否能够获取到头部token
		accesstoken = (String)request.getHeader("Authorization");
		userid  = userService.getuseridbytoken(accesstoken);
		request.setAttribute("checkuserid", userid+"");
		// 说明这是要放行的url
		if (commonurl.contains(url) ) {
			return true;
		};
	
		if (userid < 0 ) {
			JSONObject json = JSONObject.fromObject(ResultMap.build(1000, "请检查登录授权token是否正确！")); // 转换为json
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			response.getWriter().write(json.toString());
			response.getWriter().flush();
			response.getWriter().close();
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	

	

}
