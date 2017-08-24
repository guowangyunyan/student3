package com.shsxt.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shsxt.constant.MenuConstant;
import com.shsxt.model.Promotion;
import com.shsxt.model.vo.RestaurantInfo;
import com.shsxt.model.vo.ResultInfo;
import com.shsxt.service.PromotionService;
import com.shsxt.util.JsonUtil;
import com.shsxt.util.Page;
import com.shsxt.util.StringUtil;

/**
 * 优惠促销
 */
@WebServlet("/promotionServlet")
public class PromotionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PromotionService promotionService = new PromotionService();

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 将页面值存到作用域
		request.setAttribute("menu_page", MenuConstant.PROMOTION_PAGE);
		// 得到用户行为
		String action = request.getParameter("action");
		
		if (StringUtil.isEmpty(action) || action.equals("info")) {
			// 促销列表页
			promotionInfo (request, response);
		} else if (action.equals("toCreate")) {
			request.getRequestDispatcher("promotion-create.jsp").forward(request, response);
		} else if (action.equals("saveOrUpdate")) {
			// 创建或修改促销
			saveOrUpdate (request, response);
		} else if (action.equals("toUpdate")) {
			// 进入修改页面
			toUpdate(request, response);
		} else if (action.equals("delete")) {
			deletePromotion (request, response);
		} else if (action.equals("isActive")) {
			// 打开或关闭促销活动
			isActive (request, response);
		}
		
	}


	/**
	 * 打开或关闭促销活动
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void isActive(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 获取参数
		String restaurantId = request.getParameter("id");
		String promotionId = request.getParameter("promotionId");
		String isActive = request.getParameter("isActive");
		// 调用service层，返回ResultInfo对象
		ResultInfo resultInfo = promotionService.isActive(restaurantId, promotionId, isActive);
		// 响应
		JsonUtil.toJson(resultInfo, response);
		
	}


	/**
	 * 删除促销
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void deletePromotion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 获得参数
		String restaurantId = request.getParameter("id");
		String promotionId = request.getParameter("promotionId");
		// 调用service的方法，返回resultInfo对象
		ResultInfo resultInfo = promotionService.deletePromotion(restaurantId, promotionId);
		// 将数据返回给前台
		JsonUtil.toJson(resultInfo, response);
		
	}


	/**
	 * 进入修改页面
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void toUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 得到promotionID
		String promotionId = request.getParameter("pId");
		// 调用service方法，查询promotion对象
		Promotion promotion = promotionService.queryPromotionById (promotionId);
		// 存作用域
		request.setAttribute("promotion", promotion);
		// 转发
		request.getRequestDispatcher("promotion-update.jsp").forward(request, response);
		
	}


	/**
	 * 创建或修改促销活动
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void saveOrUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 获取参数
		String promotionType = request.getParameter("promotionType");
		String promotionName = request.getParameter("promotionName");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String content = request.getParameter("content");
		String isActive = request.getParameter("isActive");
		String restaurantId = request.getParameter("id");
		String promotionId = request.getParameter("promotionId");
		// 调用service的方法
		ResultInfo resultInfo = promotionService.saveOrUpdate (promotionName,
				promotionType,startDate,endDate,content,isActive,restaurantId,promotionId);
		// 传给前台
		JsonUtil.toJson(resultInfo, response);
	}


	/**
	 * 促销列表页
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void promotionInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RestaurantInfo restaurantInfo = (RestaurantInfo) request.getSession().getAttribute("restaurantInfo");
		if (restaurantInfo == null) {
			return;
		}
		// 得到餐厅ID
		Integer id = restaurantInfo.getRestaurantId();
		// 得到pageNum，pageSize
		String pageNum = request.getParameter("pageNum");
		String pageSize= request.getParameter("pageSize");
		// 调用service的方法，返回page对象
		Page<Promotion> page = promotionService.findPromotionByPage (id, pageNum, pageSize);
		// 存作用域
		request.setAttribute("page", page);
		// 转发页面
		request.getRequestDispatcher("promotion.jsp").forward(request, response);
		
	}

}
