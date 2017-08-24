<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!--sidebar start-->
<aside>
    <div id="sidebar" class="nav-collapse ">
        <!-- sidebar menu start-->
       

<ul class="sidebar-menu" id="nav-accordion">
      <!-- 首页 -->
      <li>
          <a <c:if test="${menu_page == 'INDEX_PAGE'}"> class="active" </c:if> href="indexServlet?action=info&id=${restaurantInfo.restaurantId}">
              <i class="icon-bon-home font34"></i>
              <span>首页</span>
          </a>
      </li>
      
      <!-- 餐厅管理 -->
      <li class="sub-menu">
           <a href="javascript:(0);"  <c:if test="${menu_page == 'RESTAURANT_PAGE' || menu_page == 'REVIEW_PAGE'}"> class="active" </c:if>>
               <i class="icon-bon-venue font34"></i>
               <span>餐厅管理</span>
           </a>
           <ul class="sub">
                <li <c:if test="${menu_page == 'RESTAURANT_PAGE'}"> class="active" </c:if> ><a  href="restaurantServlet?action=info&id=${restaurantInfo.restaurantId }">餐厅详情</a></li>
                <li <c:if test="${menu_page == 'REVIEW_PAGE'}"> class="active" </c:if> ><a  href="reviewServlet?action=info&id=${restaurantInfo.restaurantId }">餐厅评论</a></li>
           </ul>
       </li>
       <!-- 促销 -->
       <li>
            <a <c:if test="${menu_page == 'PROMOTION_PAGE'}"> class="active" </c:if>  href="promotionServlet?action=info&id=1">
                <i class="icon-bon-promo font34"></i>
                <span>优惠促销</span>
            </a>
       </li>
       
       <!-- 设置 -->
       <li class="sub-menu">
             <a href="javascript:(0);" <c:if test="${menu_page == 'USER_PAGE'}"> class="active" </c:if>  >
                 <i class="icon-bon-settings font34"></i>
                 <span>设置</span>
             </a>
             <ul class="sub">
                <li <c:if test="${menu_page == 'USER_PAGE'}"> class="active" </c:if> >
               		 <a href="userServlet?action=toUpdate&id=${restaurantInfo.restaurantId}">我的账号</a>
                </li>
             </ul>
         </li>
    
</ul>
        <!-- sidebar menu end-->
    </div>
</aside>
<!--sidebar end-->