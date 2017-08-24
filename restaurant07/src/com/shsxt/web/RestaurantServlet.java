package com.shsxt.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shsxt.constant.MenuConstant;
import com.shsxt.model.Restaurant;
import com.shsxt.model.vo.ResultInfo;
import com.shsxt.service.RestaurantService;
import com.shsxt.util.JsonUtil;
import com.shsxt.util.StringUtil;

/**
 * 餐厅模块
 */
@WebServlet("/restaurantServlet")
public class RestaurantServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private RestaurantService restaurantService = new RestaurantService();
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("menu_page", MenuConstant.RESTAURANT_PAGE);
		// 将餐厅ID存到作用域中
		String restaurantId = request.getParameter("id");
		String action = request.getParameter("action");
		if (StringUtil.isEmpty(action) || action.equals("info")) {
			// 得到餐厅对象
			Restaurant restaurant = restaurantService.queryRestaurantById(restaurantId);
			// 放到作用域中
			request.setAttribute("restaurant", restaurant);
			// 转发到指定页面
			request.getRequestDispatcher("restaurant.jsp").forward(request, response);
		} else if (action.equals("update")) {
			
			// 调用service的修改方法，获取ResultInfo对象
			ResultInfo resultInfo = restaurantService.updateRestaurantInfo (request);
			// 传给前台数据
			JsonUtil.toJson(resultInfo, response);
		}
		
	}

}
