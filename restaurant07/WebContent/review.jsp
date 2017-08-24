<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <%@ include file="header.jsp" %>
    <title>餐厅评论</title>
</head>

<body>

<section id="container">
    
<%@ include file="frame.jsp" %>


    <!--main content start-->
    <section id="main-content">
        <section class="wrapper">
            <div class="row">
                <div class="col-lg-12 clearfix">
                    <p class="font30 pull-left">评论</p>
                <!--<#include 'include/restaurantlist.ftl'>-->
                </div>
            </div>
            <!--state overview start-->
            <div class="row">
                <div class="col-lg-12 clearfix">
                    <ul class="review-list">
                        <li class="list-item">
	                        <c:if test="${page != null && page.datas != null }">
								<c:forEach var="item" items="${page.datas}" >
									
					          		<div class="clearfix pd-r-30 relative list-item-wrap">
		                                <a class="pull-left review-thumbnail" target="_blank">
		                                    <img src="statics/img/default-profile-photo.jpg">
		                                    ${item.userName}
		                                </a>
		                                <c:if test="${item.isLikeIt == 1 }">
		                               	    <p class="font24 c384351"><span class="mg-r-10 icon-bon-thumbup cfdbc13"></span>Liked</p>
		                                </c:if>
		                                <c:if test="${item.isLikeIt == 0 }">
		                                	<p class="font24 c384351"><span class="mg-r-10 icon-bon-thumbdown c384351"></span>DisLiked</p>
		                                </c:if>
		                                <div class="pd-t-5"></div>
		                                <p class="mg-t-10">${item.content}</p>
		                                <p class="clearfix"><span class="pull-right c75888c">${item.reviewDate}</span></p>
		                                <!-- <div class="clearfix mg-b-10" style="height: 36px">
		                                    <span class="review-flag cursor pull-right " onclick=""></span>
		                                </div>  -->
		                            </div>
					   				
					 			</c:forEach>
					 		</c:if>
                            
                        </li>
                    </ul>
                    
               <c:if test="${page != null}"> 
	                <ul class="pagination pagination-lg">
	                	<c:if test="${page.pageNum==page.totalPages}">
		                    <li>
		                    	<a href="reviewServlet?action=info&id=${restaurantInfo.restaurantId }&pageNum=1&pageSize=${page.pageSize}" class="active">首页</a>
		                    </li>
	                    </c:if>
	                    <c:if test="${page.pageNum != 1}">
		                    <li>
		                        <a href="reviewServlet?action=info&id=${restaurantInfo.restaurantId }&pageNum=${page.pageNum-1}&pageSize=${page.pageSize}">上一页</a>
		                    </li>
	                    </c:if>
	                    <c:forEach begin="${page.navStartPage}" end="${page.navEndPage}" var="p">
		                    <li <c:if test="${page.pageNum==p }">class="active"</c:if>>
		                    	<a href="reviewServlet?action=info&id=${restaurantInfo.restaurantId }&pageNum=${p}&pageSize=${page.pageSize}">${p}</a>
		                    </li>
	                    </c:forEach>
	                    <c:if test="${page.pageNum<page.totalPages}">
		                    <li>
		                    	<a href="reviewServlet?action=info&id=${restaurantInfo.restaurantId }&pageNum=${page.pageNum+1}&pageSize=${page.pageSize}">下一页</a>
		                    </li>
	                    </c:if>
	                    <c:if test="${page.pageNum==1}">
		                    <li>
		                        <a href="reviewServlet?action=info&id=${restaurantInfo.restaurantId }&pageNum=${page.totalPages}&pageSize=${page.pageSize}" class="active">末页</a>
		                    </li>
	                    </c:if>
	                </ul>
                </c:if>
                </div>
            </div>
            <!--state overview end-->

        </section>
    </section>
    <!--main content end-->
    <!--footer start-->
<%@ include file="copyright.jsp" %>
    <!--footer end-->
</section>
<%@ include file="footer.jsp" %>
<script>
    

</script>

</body>
</html>
