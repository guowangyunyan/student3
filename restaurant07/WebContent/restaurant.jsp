<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
      <link rel="stylesheet" href="statics/chosen/docsupport/style.css">
      <link rel="stylesheet" href="statics/chosen/docsupport/prism.css">
      <link rel="stylesheet" href="statics/chosen/chosen.css">
    <%@ include file="header.jsp" %>
    <title>餐厅详情</title>
    <style type="text/css" media="all">
        /* fix rtl for demo */
       .chosen-rtl .chosen-drop { left: -9000px; }
    </style>
  </head>

  <body>

<%@ include file="frame.jsp" %>

      <!--main content start-->
      <section id="main-content">
          <section class="wrapper">
             <div class="row">
                <div class="col-lg-12 clearfix">
                 <!-- <#include 'include/restaurantlist.ftl'>-->
                </div>
              </div>
              <!-- page start-->

              <div class="row">
                  <div class="col-lg-6">
                      <section class="panel">
                          <header class="panel-heading">
                              	基本信息
                          </header>
                          <div class="panel-body"> 
                          <input type="hidden" id="restaurantId"  value="<c:if test='${!empty restaurant && !empty restaurant.restaurantId}'>${restaurant.restaurantId}</c:if>">
                              <div class="form-group">
                                  <label for="">名称</label>
                                  <input type="text" class="form-control" id="restaurantName" placeholder="餐厅名称"  maxlength="50" 
                                  value="<c:if test='${!empty restaurant && !empty restaurant.restaurantName}'>${restaurant.restaurantName}</c:if>">
                              </div>
                                 
                              <div class="form-group mylocation" >
                                  <label for="">地址</label>
                                  <input type="text" class="form-control" id="address" placeholder="餐厅地址"  maxlength="100"  
                                  value="<c:if test="${!empty restaurant && !empty restaurant.address}">${restaurant.address}</c:if>">
                              </div>
                              <div class="form-group">
                                      <label for="">菜系</label>
                                      <select data-placeholder="选择菜系..." class="chosen-select-cuisine form-control m-bot15 "  >
                                            <c:choose>
											    <c:when test="${!empty restaurant &&  !empty restaurant.cuisine}">
											        <option value="湘菜" <c:if test='${"湘菜" == restaurant.cuisine}'>selected</c:if> >湘菜</option>
		                                            <option value="川菜" <c:if test='${"川菜" == restaurant.cuisine}'>selected</c:if> >川菜</option>
		                                            <option value="本帮菜" <c:if test='${"本帮菜" == restaurant.cuisine}'>selected</c:if> >本帮菜</option>
		                                            <option value="徽菜" <c:if test='${"徽菜" == restaurant.cuisine}'>selected</c:if> >徽菜</option>
											    </c:when>
											    <c:otherwise>
											        <option value="湘菜" >湘菜</option>
		                                            <option value="川菜" >川菜</option>
		                                            <option value="本帮菜" >本帮菜</option>
		                                            <option value="徽菜" >徽菜</option>
											    </c:otherwise>
											</c:choose>
                                      </select>
                              </div>

                              <div class="form-group">
                                  <label for="">人均价格</label>
                                  <select data-placeholder="选择人均价格..." class="chosen-select-avgPrice form-control m-bot15 ">
                                      <c:choose>
										    <c:when test="${!empty restaurant && !empty restaurant.avgPrice}">
										        <option value="¥" <c:if test='${"¥" == restaurant.avgPrice}'>selected</c:if> >0 ~ ¥100</option>
	                                            <option value="¥¥" <c:if test='${"¥¥" == restaurant.avgPrice}'>selected</c:if> >¥100 ~ ¥200</option>
	                                            <option value="¥¥¥" <c:if test='${"¥¥¥" == restaurant.avgPrice}'>selected</c:if> >¥200 ~ ¥300</option>
	                                            <option value="¥¥¥¥" <c:if test='${"¥¥¥¥" == restaurant.avgPrice}'>selected</c:if> >¥300以上</option>
										    </c:when>
										    <c:otherwise>
										        <option value="¥" >0 ~ ¥100</option>
	                                            <option value="¥¥" >¥100 ~ ¥200</option>
	                                            <option value="¥¥¥" >¥200 ~ ¥300</option>
	                                            <option value="¥¥¥¥" >¥300以上</option>
										    </c:otherwise>
										</c:choose>
                                  </select>
                              </div>
                                  <div class="form-group">
                                      <label for="">电话</label>
                                      <input type="tel" class="form-control" id="telephone" placeholder="" maxlength="20" value="<c:if test='${!empty restaurant && !empty restaurant.telephone}'>${restaurant.telephone }</c:if>">
                                  </div>
                                  <div class="form-group">
                                      <label for="">网站</label>
                                      <input type="text" class="form-control" id="website" placeholder="" maxlength="80" value="<c:if test='${!empty restaurant && !empty restaurant.website}'>${restaurant.website }</c:if>">
                                  </div>
                              <div class="form-group">
                                  <label for="" class="block" >便利设施</label>
                                  <c:choose>
									    <c:when test="${!empty restaurant &&  !empty restaurant.isHasWiFi && restaurant.isHasWiFi == 1}">
									    	<input type="checkbox" id="isHasWiFi" checked>&nbsp;&nbsp;WiFi
									    </c:when>
									    <c:otherwise>
									    	<input type="checkbox" id="isHasWiFi" >&nbsp;&nbsp;WiFi
									    </c:otherwise>
								  </c:choose>
								  <c:choose>
									    <c:when test="${!empty restaurant &&  !empty restaurant.isHasSeat && restaurant.isHasSeat == 1}">
									    	<input type="checkbox" id="isHasSeat" checked>&nbsp;&nbsp;室外有座
									    </c:when>
									    <c:otherwise>
									    	<input type="checkbox" id="isHasSeat" >&nbsp;&nbsp;室外有座
									    </c:otherwise>
								  </c:choose>
                              </div>
                              
                              <button id="submitBtn" type="button" class="btn btn-success" >保存</button>
                      </section>
                  </div>
                  
                  <div class="col-lg-6" id="mapmap">
                      <section class="panel">
                          <header class="panel-heading">
                           		地图
                          </header>
                          <div class="panel-body">
                             <!-- <div class="bg-ccc" id="mapdiv" style="height:300px;display: none"></div> -->
                             <%@include file="map.jsp" %>
                             <br/>
                          <p class="text-center pd-t-10 pd-b-10">如果标注不正确，请直接拖动地图修改</p>
						</div>
                      </section>

                  </div>
                  
              </div>

              <!-- page end-->
          </section>
      </section>
      <!--main content end-->
      <!--footer start-->
      <footer class="site-footer">
          <div class="text-center">
               <%@ include file="copyright.jsp" %>
              <a href="#" class="go-top">
                  <i class="icon-angle-up"></i>
              </a>
          </div>
      </footer>
      <!--footer end-->
  </section>

   <!-- js placed at the end of the document so the pages load faster -->
  <%@ include file="footer.jsp" %>
  <script src="statics/chosen/chosen.jquery.js" type="text/javascript"></script>
  <script src="statics/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
  <script src="statics/js/restaurant/restaurant.js" type="text/javascript" charset="utf-8"></script>
  
  <script type="text/javascript">

  


  
  </script>
  
  </body>
</html>
