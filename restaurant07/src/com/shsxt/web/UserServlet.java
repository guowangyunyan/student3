package com.shsxt.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.shsxt.constant.MenuConstant;
import com.shsxt.model.User;
import com.shsxt.model.vo.ResultInfo;
import com.shsxt.service.UserService;
import com.shsxt.util.JsonUtil;
import com.shsxt.util.StringUtil;

/**
 * 用户模块
 */
@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private UserService userService = new UserService();

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 用户行为
		String action = request.getParameter("action");
		// 判断action是否为空
		if (StringUtil.isEmpty(action)) {
			
			response.sendRedirect("login.jsp");
			
		} else if (action.equals("login")) {
			// 用户登录
			ResultInfo resultInfo = userLogin(request, response);
			// 返回数据给前台
			JsonUtil.toJson(resultInfo, response);
		} else if (action.equals("loginOut")) {
			// 退出登录
			userLoginOut(request, response);
		} else if (action.equals("toUpdate")) {
			// 进入用户信息修改页面
			toUpdate(request, response);
		} else if (action.equals("updateUser")) {
			// 修改用户信息
			updateUser(request, response);
		} else if (action.equals("autoLogin")) {
			aotuLogin (request, response);
		}
	}


	/**
	 * 自动登录
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void aotuLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ResultInfo resultInfo = userLogin(request, response);
		if (resultInfo != null && resultInfo.getResultCode() == 1) {
			// 从自动登录过滤器中得到需要跳转的页面
			String currentUrl = request.getParameter("currentUrl");
			// 从自动登录过滤器中得到需要跳转的页面 （过滤器中将这个参数中的&替换成-传过来,我们这里需要替换回来）
			String currentParams = request.getParameter("currentParams");
			if (StringUtil.isNotEmpty(currentParams) ) {	
				currentParams = currentParams.replaceAll("-", "&");
			}
			response.sendRedirect(currentUrl + "?" + currentParams); 
		} else {
			response.sendRedirect("login.jsp");
		}
		
		
	}


	/**
	 * 修改用户信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 得到参数，用户名，密码，邮箱
		String email = request.getParameter("email");
		String userName = request.getParameter("userName");
		String newPassword = request.getParameter("newPassword");
		// 调用service层的修改方法，会返回resultInfo
		ResultInfo resultInfo = userService.updateUser(email, userName, newPassword);
		// 存session
		request.getSession().setAttribute("userInfo", resultInfo);
		// 传数据给前台
		JsonUtil.toJson(resultInfo, response);
	}


	/**
	 * 进入修改用户信息页面
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void toUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("menu_page", MenuConstant.USER_PAGE);
		// 将餐厅存到作用域中
		String restauarntId = request.getParameter("id");
		request.setAttribute("restaurantId", restauarntId);
		// 从session获取ResultInfo对象
		ResultInfo resultInfo = (ResultInfo) request.getSession().getAttribute("userInfo");
		// 判断是否为空
		if (resultInfo != null && resultInfo.getResultObject() != null) {
			// 获取用户对象
			User user = (User) resultInfo.getResultObject();
			// 存到作用域中
			request.setAttribute("user", user);
			request.getRequestDispatcher("editself.jsp").forward(request, response);
		}
		
	}



	/**
	 * 退出登录
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void userLoginOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 清空session
		request.getSession().setAttribute("userInfo", null);
		request.getSession().invalidate(); // 销毁会话
		// 清空cookie
		Cookie[] cookie = request.getCookies();
		if (cookie != null && cookie.length >0) {
			for (Cookie cookie2 : cookie) {
				if (cookie2.getName().equals("userInfo")) {
					cookie2.setMaxAge(0);
					response.addCookie(cookie2);
				}
			}
		}
		response.sendRedirect("login.jsp");
	}


	/**
	 * 用户登录
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public ResultInfo userLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 用户邮箱
		String email = request.getParameter("email");
		// 用户密码
		String password = request.getParameter("password");
		// 调用service的方法
		ResultInfo resultInfo = userService.userLogin(email, password);
		// 如果成功
		if (resultInfo.getResultCode() == 1) {
			// 得到是否记住密码
			String isRememberPwd = request.getParameter("isRememberPwd");
			if (StringUtil.isNotEmpty(isRememberPwd) && isRememberPwd.equals("1")) {
				// 存Cookie
				String value = email + "-" + password;
				Cookie cookie = new Cookie("userInfo", value);
				cookie.setPath(request.getContextPath());
				cookie.setMaxAge(60*60*24*3); // 设置为3天失败，单位秒
				response.addCookie(cookie);
			}
			
		}
		// 存session
		request.getSession().setAttribute("userInfo", resultInfo);
		return resultInfo;
	}

}
