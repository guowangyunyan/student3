package com.shsxt.service;

import java.util.List;

import com.shsxt.dao.ReviewDao;
import com.shsxt.model.vo.ReviewVo;
import com.shsxt.util.Page;
import com.shsxt.util.StringUtil;

public class ReviewService {
	
	private ReviewDao reviewDao = new ReviewDao();

	/**
	 * 评论列表分页
	 * @param restaurantId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page<ReviewVo> findReviewListByPage(String restaurantIdStr, String pageNumStr, String pageSizeStr) {

		// 参数非空判断
		if (StringUtil.isEmpty(restaurantIdStr)) {
			return null;
		}
		// 设置pageNum
		Integer pageNum = 1; 
		if (StringUtil.isNotEmpty(pageNumStr)) {
			pageNum = Integer.parseInt(pageNumStr);
		}
		// 设置pageSize
		Integer pageSize = 10; 
		if (StringUtil.isNotEmpty(pageSizeStr)) {
			pageSize = Integer.parseInt(pageSizeStr);
		}
		// 查询评论的总数量
		Integer totalCount = reviewDao.queryReviewTotalCount (Integer.parseInt(restaurantIdStr));
		// 查询餐厅评论的集合
		List<ReviewVo> reviewVos = reviewDao.queryReviewByPage (Integer.parseInt(restaurantIdStr), pageNum, pageSize);
		// 创建page对象
		Page<ReviewVo> page = new Page<>(pageNum, pageSize, totalCount);
		// 将集合放到page中
		page.setDatas(reviewVos);
		
		return page;
	}

}
