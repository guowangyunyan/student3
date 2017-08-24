package com.shsxt.model;

import java.sql.Date;

public class Review {

	private Integer reviewId;
	private Integer fkUserId; // user表中的userId
	private Integer fkRestaurantId;
	private String content;
	private Integer isLikeIt; // 是否喜欢这家餐厅 (0=不喜欢，1=喜欢)
	private Date createDate;
	private Date updateDate;
	private Integer isValid; // 评论是否有效 (0=无效，1=有效)
	
	public Integer getReviewId() {
		return reviewId;
	}
	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}
	public Integer getFkUserId() {
		return fkUserId;
	}
	public void setFkUserId(Integer fkUserId) {
		this.fkUserId = fkUserId;
	}
	public Integer getFkRestaurantId() {
		return fkRestaurantId;
	}
	public void setFkRestaurantId(Integer fkRestaurantId) {
		this.fkRestaurantId = fkRestaurantId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public Integer getIsLikeIt() {
		return isLikeIt;
	}
	public void setIsLikeIt(Integer isLikeIt) {
		this.isLikeIt = isLikeIt;
	}
	
}
