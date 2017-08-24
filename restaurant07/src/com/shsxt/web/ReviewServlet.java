package com.shsxt.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shsxt.constant.MenuConstant;
import com.shsxt.model.vo.ReviewVo;
import com.shsxt.service.ReviewService;
import com.shsxt.util.Page;

/**
 * 餐厅评论
 */
@WebServlet("/reviewServlet")
public class ReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReviewService reviewService = new ReviewService();

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("menu_page", MenuConstant.REVIEW_PAGE);
		// 得到餐厅ID，当前页码，每页的数量
		String restaurantId = request.getParameter("id");
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		// 调用service的方法得到page对象
		Page<ReviewVo> page = reviewService.findReviewListByPage(restaurantId, pageNum, pageSize);
		// 存作用域
		request.setAttribute("page", page);
		// 转发到review.jsp页面
		request.getRequestDispatcher("review.jsp").forward(request, response);
	}

}
