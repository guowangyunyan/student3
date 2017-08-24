package com.shsxt.service;

import java.util.List;
import org.apache.log4j.Logger;
import com.shsxt.dao.PromotionDao;
import com.shsxt.model.Promotion;
import com.shsxt.model.vo.ResultInfo;
import com.shsxt.util.Page;
import com.shsxt.util.StringUtil;

public class PromotionService {
	
	private PromotionDao promotionDao = new PromotionDao();
	
	private Logger logger = Logger.getLogger(PromotionService.class);

	/**
	 * 分页查询餐厅促销列表
	 * @param id
	 * @param pageNumStr
	 * @param pageSizeStr
	 * @return
	 */
	public Page<Promotion> findPromotionByPage(Integer id, String pageNumStr, String pageSizeStr) {
		// 参数的非空判断
		if (id == null || id == 0) {
			return null;
		}
		// 设置pageNum，pageSize
		Integer pageNum = 1;
		if (StringUtil.isNotEmpty(pageNumStr)) {
			pageNum = Integer.parseInt(pageNumStr);
		}
		Integer pageSize = 10;
		if (StringUtil.isNotEmpty(pageSizeStr)) {
			pageSize = Integer.parseInt(pageSizeStr);
		}
		// 查询餐厅的promotion的总数量
		Integer totalCount = promotionDao.queryPromotionTotalCount (id);
		// 查询餐厅的promotion的集合
		List<Promotion> promotions = promotionDao.queryPromotionByPage (id, pageNum, pageSize);
		// 得到pag对象
		Page<Promotion> page = new Page<>(pageNum, pageSize, totalCount);
		// 将集合放到page对象中
		page.setDatas(promotions);
		
		return page;
	}

	public ResultInfo saveOrUpdate(String promotionName, String promotionType, String startDate, String endDate,
			String content, String isActive, String restaurantId, String promotionId) {
		ResultInfo messageModel = new ResultInfo();
		logger.info("restaurantId:"+ restaurantId);
		if (StringUtil.isEmpty(restaurantId)) {
			messageModel.setResultCode(0);
			messageModel.setResultMessage("参数错误！");
			return messageModel;
		}
		logger.info("promotionName:"+ promotionName);
		if (StringUtil.isEmpty(promotionName)) {
			messageModel.setResultCode(0);
			messageModel.setResultMessage("促销名称不能为空！");
			return messageModel;
		}
		logger.info("promotionType:"+ promotionType);
		if (StringUtil.isEmpty(promotionType)) {
			messageModel.setResultCode(0);
			messageModel.setResultMessage("促销类型不能为空！");
			return messageModel;
		}
		logger.info("startDate:"+ startDate);
		if (StringUtil.isEmpty(startDate)) {
			messageModel.setResultCode(0);
			messageModel.setResultMessage("促销开始时间不能为空！");
			return messageModel;
		}
		logger.info("endDate:"+ endDate);
		if (StringUtil.isEmpty(endDate)) {
			messageModel.setResultCode(0);
			messageModel.setResultMessage("促销结束时间不能为空！");
			return messageModel;
		}
		logger.info("content:"+ content);
		if (StringUtil.isEmpty(content)) {
			messageModel.setResultCode(0);
			messageModel.setResultMessage("促销活动内容不能为空！");
			return messageModel;
		}
		logger.info("isActive:"+ isActive);
		if (StringUtil.isEmpty(isActive)) {
			isActive = "0";
		}
		// 调用dao层方法
		Integer code = promotionDao.saveOrUpdate (promotionName,
				promotionType,startDate,endDate,content,isActive,restaurantId, promotionId);
		if (code != 1) {
			messageModel.setResultCode(0);
			messageModel.setResultMessage("创建失败！");
			return messageModel;
		}
		
		messageModel.setResultCode(1);
		
		return messageModel;
	}

	/**
	 * 通过promotionId查找促销对象
	 * @param promotionId
	 * @return
	 */
	public Promotion queryPromotionById(String promotionId) {
		// 参数非空判断
		if (StringUtil.isEmpty(promotionId)) {
			return null;
		}
		Promotion promotion = promotionDao.queryPromotionById(promotionId);
		return promotion;
	}

	
	/**
	 * 删除促销
	 * @param restaurantId
	 * @param promotionId
	 * @return
	 */
	public ResultInfo deletePromotion(String restaurantId, String promotionId) {
		ResultInfo resultInfo = new ResultInfo();
		// 非空判断
		if (StringUtil.isEmpty(restaurantId)) {
			resultInfo.setResultCode(0);
			resultInfo.setResultMessage("餐厅ID不能为空！");
			return resultInfo;
		}
		if (StringUtil.isEmpty(promotionId)) {
			resultInfo.setResultCode(0);
			resultInfo.setResultMessage("PromotionId不能为空！");
			return resultInfo;
		}
		// 查找promotion是否存在
		Promotion promotion = promotionDao.queryPromotionById(promotionId);
		if (promotion == null) {
			resultInfo.setResultCode(0);
			resultInfo.setResultMessage("数据不存在！");
			return resultInfo;
		}
		// 调用dao的方法，删除
		Integer code  = promotionDao.deletePromotion(restaurantId, promotionId);
		if (code != 1) {
			resultInfo.setResultCode(0);
			resultInfo.setResultMessage("删除失败！");
			return resultInfo;
		}
		
		resultInfo.setResultCode(1);
		return resultInfo;
	}

	/**
	 * 打开或关闭促销活动
	 * @param restaurantId
	 * @param promotionId
	 * @param isActive
	 * @return
	 */
	public ResultInfo isActive(String restaurantId, String promotionId, String isActive) {
		// 参数非空判断
		ResultInfo messageModel = new ResultInfo();
		if(StringUtil.isEmpty(restaurantId) || StringUtil.isEmpty(promotionId) || StringUtil.isEmpty(isActive)) {
			messageModel.setResultCode(0);
			messageModel.setResultMessage("参数异常！");
			return messageModel;
		}
		Integer code  = promotionDao.isActive(Integer.parseInt(restaurantId),Integer.parseInt(promotionId),Integer.parseInt(isActive));
		if (code == null || code != 1) {
			messageModel.setResultCode(0);
			messageModel.setResultMessage("系统异常！");
			return messageModel;
		}
		messageModel.setResultCode(1);
		messageModel.setResultMessage("Success!");
		return messageModel;
	}

}
