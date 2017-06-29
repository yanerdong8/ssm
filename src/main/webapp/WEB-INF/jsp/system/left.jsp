<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>      
	<!-- BEGIN SIDEBAR LEFT -->
			<div class="sidebar-left sidebar-nicescroller">
				<ul class="sidebar-menu">
					  
					<c:forEach var="item" items="${menulist}">
					   <li>
						<c:if test="${empty item.murl}">
						<a href="#fakelink">
						<i class="fa fa-table icon-sidebar"></i>
							<i class="fa fa-angle-right chevron-icon-sidebar"></i>
							${item.name}
						</a> 
						</c:if>
						<c:if test="${!empty item.murl}">
						<a href="${pageContext.request.contextPath}${item.murl}?menuid=${item.id}&mpid=${item.pid}">
						<i class="fa fa-table icon-sidebar"></i>
							<i class="fa fa-angle-right chevron-icon-sidebar"></i>
							${item.name}
						</a> 
						</c:if>
						
							
						<ul class="submenu<c:if test="${mpid==item.id}"> visible</c:if>">
							<c:forEach var="sitem" items="${permissions}">
							   <c:if test="${item.id==sitem.pid}">
							     <li class="<c:if test="${menuid==sitem.id}">active selected</c:if>"><a href="${pageContext.request.contextPath}${sitem.murl}?menuid=${sitem.id}&mpid=${sitem.pid}">${sitem.name}</a></li> 
						       </c:if>
						    </c:forEach>
						</ul>
					  </li> 
					</c:forEach> 
				</ul>
			</div><!-- /.sidebar-left -->
			<!-- END SIDEBAR LEFT -->