package com.shsxt.model.vo;

public class ReviewVo {
	
	private Integer reviewId;
	private String content;
	private Integer isLikeIt; // 是否喜欢这家餐厅 (0=不喜欢，1=喜欢)
	private String userName;
	private String reviewDate;
	
	public Integer getReviewId() {
		return reviewId;
	}
	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}
	
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getIsLikeIt() {
		return isLikeIt;
	}
	public void setIsLikeIt(Integer isLikeIt) {
		this.isLikeIt = isLikeIt;
	}
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
	
}
