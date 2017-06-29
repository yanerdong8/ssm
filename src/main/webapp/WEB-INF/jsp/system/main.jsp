<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
	content="Sentir, Responsive admin and dashboard UI kits template">
<meta name="keywords"
	content="admin,bootstrap,template,responsive admin,dashboard template,web apps template">
<meta name="author"
	content="Ari Rusmanto, Isoh Design Studio, Warung Themes">
<title>后台管理系统</title>
<jsp:include page="/style.jsp"></jsp:include>
 
<style type="text/css">
 .xline {
    border-bottom: solid 1px #dfe9ee;
    height: 5px;
}
.welinfo i {
    font-style: normal;
    padding-left: 8px;
}
.mainindex {
    padding: 20px;
    overflow: hidden;
    background-color: #FFFFFF;font-family: '微软雅黑';
}
.welinfo b {
    padding-left: 8px;
}
</style>
</head>

<body>
	<c:set var="menuCheck" scope="session" value="1"></c:set>
	<jsp:include page="top.jsp"></jsp:include>
	<jsp:include page="left.jsp"></jsp:include>
	<div class="wrapper">
		<!-- BEGIN PAGE CONTENT -->
		<div class="page-content">
			<div class="line-3" style="height: 20px"></div>
				<ol class="breadcrumb default square rsaquo sm">
					<li><a data-ajax="true" data-ajax-begin="$.BodyLoadBegin"
						data-ajax-failure="$.BodyLoadFailure" data-ajax-mode="replace"
						data-ajax-success="$.BodyLoadSuccess"
						data-ajax-update="#body-content"
						href="${pageContext.request.contextPath}/backgd/system/systemHome/home"><i class="fa fa-home"></i></a></li>
					<li class="active">首页</li>

				</ol>
			<div class="mainindex"> 
				<div class="welinfo">
					<span><img src="${pageContext.request.contextPath}/style/admin/assets/img/sun.png" alt=""></span>
				  
					<b>亲爱的用户,欢迎您使用${configVo.name},祝您工作愉快!</b>
				</div> 
				<div class="welinfo">
					<span><img src="${pageContext.request.contextPath}/style/admin/assets/img/time.png" alt="时间"></span> <i>您上次登录的时间：
						<fmt:formatDate value="${sessionScope.adminUser.lastLoginDate}" pattern="yyyy-MM-dd HH:mm"/> &nbsp;上次登录ip：${sessionScope.adminUser.lastLoginIp}</i>  
				</div> 
				<div class="xline"></div> 
			</div>
		</div>
	</div>


</body>
</html>