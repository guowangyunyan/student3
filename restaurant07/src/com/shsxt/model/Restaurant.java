package com.shsxt.model;

import java.sql.Date;

public class Restaurant {

	private Integer restaurantId; // 餐厅ID
	private String restaurantName; // 餐厅名称
	private String address; // 餐厅地址
	private String telephone; // 餐厅电话
	private String cuisine; // 菜系
	private String avgPrice; // 人均价格
	private String website; // 网址
	private Integer isHasWiFi; // 是否有wifi 1=有 0=没有
	private Integer isHasSeat; // 是否有座 1=有 0=没有
	private Date createDate;
	private Date updateDate;
	private Integer isValid;
	
	public Integer getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getCuisine() {
		return cuisine;
	}
	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}
	public String getAvgPrice() {
		return avgPrice;
	}
	public void setAvgPrice(String avgPrice) {
		this.avgPrice = avgPrice;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public Integer getIsHasWiFi() {
		return isHasWiFi;
	}
	public void setIsHasWiFi(Integer isHasWiFi) {
		this.isHasWiFi = isHasWiFi;
	}
	public Integer getIsHasSeat() {
		return isHasSeat;
	}
	public void setIsHasSeat(Integer isHasSeat) {
		this.isHasSeat = isHasSeat;
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
