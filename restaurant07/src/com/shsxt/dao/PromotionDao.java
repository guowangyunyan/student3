package com.shsxt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.shsxt.model.Promotion;
import com.shsxt.util.DBUtil;
import com.shsxt.util.StringUtil;

public class PromotionDao {
	
	private Logger logger = Logger.getLogger(PromotionDao.class);

	/**
	 * 查询餐厅的促销活动的总数量
	 * @param id
	 * @return
	 */
	public Integer queryPromotionTotalCount(Integer restaurantId) {
		Integer totalCount = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DBUtil.getConnection();
			String sql = "select count(promotionId) as totalCount from promotion where isValid=1 and fkRestaurantId = ?";
			logger.info("sql:" + sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, restaurantId);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				totalCount = resultSet.getInt("totalCount");
				logger.info("得到餐厅促销的总数量totalCount:" + totalCount);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(resultSet, preparedStatement, connection);
		}
		return totalCount;
	}

	/**
	 * 分页查询餐厅的促销集合
	 * @param id
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<Promotion> queryPromotionByPage(Integer id, Integer pageNum, Integer pageSize) {
		List<Promotion> promotions = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DBUtil.getConnection();
			String sql = "select * from promotion where isValid = 1 and fkRestaurantId = ? limit ?,?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			Integer limit = (pageNum - 1) * pageSize;
			preparedStatement.setInt(2, limit);
			preparedStatement.setInt(3, pageSize);
			// 执行查询
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Promotion promotion = new Promotion();
				promotion.setContent(resultSet.getString("content"));
				promotion.setCreateDate(resultSet.getDate("createDate"));
				promotion.setEndDate(resultSet.getDate("endDate"));
				promotion.setFkRestaurantId(resultSet.getInt("fkRestaurantId"));
				promotion.setIsValid(resultSet.getInt("isValid"));
				promotion.setPromotionId(resultSet.getInt("promotionId"));
				promotion.setPromotionName(resultSet.getString("promotionName"));
				promotion.setPromotionType(resultSet.getInt("promotionType"));
				promotion.setStartDate(resultSet.getDate("startDate"));
				promotion.setUpdateDate(resultSet.getDate("updateDate"));
				promotion.setIsActive(resultSet.getInt("isActive"));
				promotions.add(promotion);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(resultSet, preparedStatement, connection);
		}
		return promotions;
	}

	/**
	 * 创建促销
	 * @param promotionName
	 * @param promotionType
	 * @param startDate
	 * @param endDate
	 * @param content
	 * @param isActive
	 * @param restaurantId
	 * @return
	 */
	public Integer saveOrUpdate(String promotionName, String promotionType, String startDate, String endDate,
			String content, String isActive, String restaurantId, String promotionId) {
		Integer code  = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DBUtil.getConnection();
			String sql = "";
			if (StringUtil.isEmpty(promotionId)) {
				sql = "insert into promotion (promotionName,promotionType,startDate,endDate,content,isActive,fkRestaurantId) VALUES (?,?,?,?,?,?,?)";
			} else {
				sql = "update promotion set promotionName = ?, promotionType= ?, startDate = ?, endDate = ?, content =?, isActive = ?, updateDate=now() where promotionId = ?";
			}
			preparedStatement =connection.prepareStatement(sql);
			if (StringUtil.isEmpty(promotionId)) {
				preparedStatement.setString(1, promotionName);
				preparedStatement.setInt(2, Integer.parseInt(promotionType));
				preparedStatement.setString(3, startDate);
				preparedStatement.setString(4, endDate);
				preparedStatement.setString(5, content);
				preparedStatement.setInt(6, Integer.parseInt(isActive));
				preparedStatement.setInt(7, Integer.parseInt(restaurantId));
			} else {
				preparedStatement.setString(1, promotionName);
				preparedStatement.setInt(2, Integer.parseInt(promotionType));
				preparedStatement.setString(3, startDate);
				preparedStatement.setString(4, endDate);
				preparedStatement.setString(5, content);
				preparedStatement.setInt(6, Integer.parseInt(isActive));
				preparedStatement.setInt(7, Integer.parseInt(promotionId));
			}
			
			// 执行修改
			int row = preparedStatement.executeUpdate();
			if (row > 0) {
				code =1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, preparedStatement, connection);
		}
		return code;
	}

	/**
	 * 通过promotionId查找促销对象
	 * @param promotionId
	 * @return
	 */
	public Promotion queryPromotionById(String promotionId) {
		Promotion promotion = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DBUtil.getConnection();
			String sql = "select * from promotion where isValid= 1 and promotionId = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(promotionId));
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				promotion = new Promotion();
				promotion.setPromotionId(resultSet.getInt("promotionId"));
				promotion.setContent(resultSet.getString("content"));
				promotion.setEndDate(resultSet.getDate("endDate"));
				promotion.setFkRestaurantId(resultSet.getInt("fkRestaurantId"));
				promotion.setIsActive(resultSet.getInt("isActive"));
				promotion.setIsValid(resultSet.getInt("isValid"));
				promotion.setPromotionName(resultSet.getString("promotionName"));
				promotion.setPromotionType(resultSet.getInt("promotionType"));
				promotion.setStartDate(resultSet.getDate("startDate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(resultSet, preparedStatement, connection);
		}
		return promotion;
	}

	
	/**
	 * 通过餐厅ID和促销ID删除promotion
	 * @param restaurantId
	 * @param promotionId
	 * @return
	 */
	public Integer deletePromotion(String restaurantId, String promotionId) {
		Integer code = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DBUtil.getConnection();
			String sql = "update promotion set isValid =0 ,updateDate=now() where fkRestaurantId = ? and promotionId = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(restaurantId));
			preparedStatement.setInt(2, Integer.parseInt(promotionId));
			int row = preparedStatement.executeUpdate();
			if (row > 0) {
				code =1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, preparedStatement, connection);
		}
		return code;
	}

	/**
	 * 打开或关闭促销
	 * @param parseInt
	 * @param parseInt2
	 * @param parseInt3
	 * @return
	 */
	public Integer isActive(int restaurantId, int promotionId, int isActive) {
		Integer code = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DBUtil.getConnection();
			String sql = "update promotion set isActive = ? ,updateDate =now() where fkrestaurantId=? and promotionId = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, isActive);
			preparedStatement.setInt(2, restaurantId);
			preparedStatement.setInt(3, promotionId);
			int row = preparedStatement.executeUpdate();
			if (row >0) {
				code =1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, preparedStatement, connection);
		}
		return code;
	}

}
