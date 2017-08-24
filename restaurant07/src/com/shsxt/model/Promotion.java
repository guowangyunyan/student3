package com.shsxt.model;

import java.sql.Date;

public class Promotion {

	private Integer promotionId; // 活动ID
	private Integer fkRestaurantId; // 餐厅ID
	private String promotionName; // 活动名称
	private Integer promotionType; // 促销类型
	private Date startDate; // 促销开始时间
	private Date endDate; // 促销结束时间
	private Integer isActive; // 活动是否上线 (0=未上线,1=已上线)
	private String content; // 促销活动内容
	private Date createDate;
	private Date updateDate;
	private Integer isValid;
	
	
	public Integer getPromotionId() {
		return promotionId;
	}
	public void setPromotionId(Integer promotionId) {
		this.promotionId = promotionId;
	}
	public String getPromotionName() {
		return promotionName;
	}
	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}
	public Integer getPromotionType() {
		return promotionType;
	}
	public void setPromotionType(Integer promotionType) {
		this.promotionType = promotionType;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public Integer getIsActive() {
		return isActive;
	}
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getFkRestaurantId() {
		return fkRestaurantId;
	}
	public void setFkRestaurantId(Integer fkRestaurantId) {
		this.fkRestaurantId = fkRestaurantId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Integer getIsValid() {
		return isValid;
	}
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}
	
	
}
