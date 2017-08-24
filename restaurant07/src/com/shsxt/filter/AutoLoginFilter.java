package com.shsxt.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shsxt.model.vo.ResultInfo;
import com.shsxt.util.StringUtil;

/**
 * 用户自动登录过滤类
 * @author Lisa Li  
 * @date 2017年7月28日
 */
@WebFilter(urlPatterns="/*")
public class AutoLoginFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request=(HttpServletRequest) arg0;
		HttpServletResponse response=(HttpServletResponse) arg1;
		/**
		 * 自动登录
		 *    1.静态资源 退出操作放行  
		 *    2.用户登录成功  放行
		 *    3.login.jsp   userServlet?action=autoLogin
		 *       用户名  密码 从cookie 获取用户名 密码  执行登录 
		 *       
		 */
		
		String url = request.getRequestURI();
		if(url.contains("/statics")){
			chain.doFilter(request, response);
			return;
		}
		
		
		String action = request.getParameter("action");
		if(!StringUtil.isEmpty(action)){
			if(action.equals("login")){
				chain.doFilter(request, response);
				return;
			}
		}
		
		// 判断session是否有值
		ResultInfo resultInfo = (ResultInfo) request.getSession().getAttribute("userInfo");
		if(null != resultInfo && resultInfo.getResultCode() == 1){
			chain.doFilter(request, response);
			return;
		}
		
		// 执行自动登录  获取cookie
		Cookie[] cookies = request.getCookies();
		if(null !=cookies && cookies.length > 0){
			for(Cookie cookie:cookies){
				String name = cookie.getName();// 获取cookie 名
				if(name.equals("userInfo")){
					String value = cookie.getValue();//  获取用户信息 userName-userPwd
					if (StringUtil.isEmpty(value)) {
						response.sendRedirect("login.jsp");
						return;
					}
					// 自动登录操作以后，依旧停留在当前页面
					// 得到当前页面的url
					String currentUrl = request.getRequestURL() + "";
					// 得到当前页面的参数
					String currentParams = request.getQueryString();
					if (StringUtil.isNotEmpty(currentParams)) {
						currentParams = currentParams.replaceAll("&", "-");
					}
					String email = value.split("-")[0];
					String password = value.split("-")[1];
					// 将当前页面的url和参数作为两个值转发给自动登录操作
					String dispatherUrl = "userServlet?action=autoLogin&email="+email+"&password="+password
							+ "&currentUrl=" +currentUrl + "&currentParams=" + currentParams;
					request.getRequestDispatcher(dispatherUrl).forward(request, response);
					return;
				}
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}


}
