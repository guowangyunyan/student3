package com.shsxt.model.vo;

import java.util.List;

import com.shsxt.model.Restaurant;

/**
 * 餐厅的封装类对象类
 * @author Lisa Li  
 * @date 2017年8月21日
 */
public class RestaurantInfo {

	private Integer restaurantId; // 当前餐厅ID
	private Restaurant resultObject; // 当前餐厅对象
	private List<Restaurant> resultList; // 餐厅集合
	public Integer getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}
	public Restaurant getResultObject() {
		return resultObject;
	}
	public void setResultObject(Restaurant resultObject) {
		this.resultObject = resultObject;
	}
	public List<Restaurant> getResultList() {
		return resultList;
	}
	public void setResultList(List<Restaurant> resultList) {
		this.resultList = resultList;
	}
	
}
