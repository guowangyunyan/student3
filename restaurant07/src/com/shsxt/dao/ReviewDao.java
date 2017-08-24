package com.shsxt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import com.shsxt.model.vo.ReviewVo;
import com.shsxt.util.DBUtil;

public class ReviewDao {

	/**
	 * 查询评论的总数量
	 * @param parseInt
	 * @return
	 */
	public Integer queryReviewTotalCount(int restaurantId) {
		Integer totalCount = 0; // 某餐厅评论的总数量
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = DBUtil.getConnection();
			String sql = "select count(reviewId) as totalCount from review where isValid =1 and fkRestaurantId = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, restaurantId);
			rs = ps.executeQuery();
			while (rs.next()) {
				totalCount = rs.getInt("totalCount");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, ps, connection);
		}
		return totalCount;
	}

	/**
	 * 查询餐厅评论的集合
	 * @param parseInt
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<ReviewVo> queryReviewByPage(int restaurantId, Integer pageNum, Integer pageSize) {
		List<ReviewVo> reviewVos = new ArrayList<>();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = DBUtil.getConnection();
			String sql = "select reviewId,userName,content,isLikeIt,r.createDate as reviewDate from review r LEFT JOIN user u on r.fkUserId = u.userId where r.isValid = 1 and r.fkRestaurantId = ? limit ?,?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, restaurantId);
			Integer limit = (pageNum - 1) * pageSize;
			ps.setInt(2, limit);
			ps.setInt(3, pageSize);
			// 查询
			rs = ps.executeQuery();
			while(rs.next()){
				ReviewVo review = new ReviewVo();
				review.setContent(rs.getString("content"));
				review.setReviewDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(rs.getTimestamp("reviewDate")));
				review.setReviewId(rs.getInt("reviewId"));
				review.setUserName(rs.getString("userName"));
				review.setIsLikeIt(rs.getInt("isLikeIt"));
				reviewVos.add(review);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, ps, connection);
		}
		
		return reviewVos;
	}

}
