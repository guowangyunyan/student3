package com.shsxt.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.shsxt.model.vo.ResultInfo;
import com.shsxt.util.StringUtil;


/**
 * 用户非法请求过滤
 * @author Lisa Li  
 * @date 2017年7月27日
 */
@WebFilter(urlPatterns="/*")
public class NoAccessFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		/**
		 * 1.合法请求
		 *    登录,注册,退出   放行
		 * 2.合法资源（静态资源）
		 *   图片，css js 放心   login.jsp,register.jsp  放行
		 * 3.非法请求过滤
		 *    判断用户是否登录
		 *       登录  用户操作合法  放行（执行servlet 相关方法）
		 *       未登录  用户操作非法 跳转到登录页面执行登录操作
		 */
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		String url = request.getRequestURI();// 用户请求的资源
		
		// 把转发前的url放到作用域中
		request.setAttribute("currentPageUrl", url);// restaurant01/inserServlet
		// 静态资源  login register 执行放行
		if(url.contains("/statics") || url.contains("login.jsp")){
			chain.doFilter(request, response);
			return;
		}
		
		// 合法请求执行放行处理
		String action = request.getParameter("action");
		if(!StringUtil.isEmpty(action)){
			if(action.equals("login") || action.equals("loginOut") || action.equals("autoLogin")){
				chain.doFilter(request, response);
				return;
			}
		}
		
		  /**
		   * 获取session 中userInfo	
		   *   userInfo 非空  用户已登录
		   *   userInfo 空  用户未登录
		   */
			HttpSession session=request.getSession();
			ResultInfo messageModel=(ResultInfo) session.getAttribute("userInfo");
			if(null==messageModel){
				response.sendRedirect(request.getContextPath()+"/login.jsp");
				return;
			}
			chain.doFilter(request, response);
		}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
