package com.shsxt.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shsxt.constant.MenuConstant;
import com.shsxt.model.User;
import com.shsxt.model.vo.RestaurantInfo;
import com.shsxt.model.vo.ResultInfo;
import com.shsxt.service.RestaurantService;


@WebServlet("/indexServlet")
public class IndexServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private RestaurantService restaurantService = new RestaurantService();
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String restaurantId = request.getParameter("id");
		ResultInfo resultInfo = (ResultInfo)request.getSession().getAttribute("userInfo");
		User user = (User)resultInfo.getResultObject();
		RestaurantInfo restaurantInfo = restaurantService.queryRestaurantByUserId(user.getUserId(), restaurantId);
		request.getSession().setAttribute("restaurantInfo", restaurantInfo);
		request.setAttribute("menu_page", MenuConstant.INDEX_PAGE);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
