﻿<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
<title>登录</title>
<jsp:include page="/style.jsp"></jsp:include>
<script type="text/javascript">

        function loginFun() {
        	
        	sendAjax("${pageContext.request.contextPath}/login", $("#myForm").serializeArray(), function(data){
        			window.location.href = "${pageContext.request.contextPath}/main";
        	
        	});
        }
    </script>
    
</head>

<body class="login tooltips">

	<div class="login-header text-center" align="center">
		<img
			src="${pageContext.request.contextPath}/style/admin/assets/img/logo-login.png"
			class="logo" alt="Logo">

	</div>
	<div class="login-wrapper">
		<div style="height: 20px"></div>	
		<form role="form" id="myForm" style="margin: 20px"> 
			<div class="form-group has-feedback lg left-feedback no-label">
				<input id="loginName" name="loginName" type="text" value=""
					class="form-control no-border input-lg rounded"
					placeholder="输入您的账号" autofocus> <span
					class="fa fa-user form-control-feedback"></span>
			</div>
			<div class="form-group has-feedback lg left-feedback no-label">
				<input id="password" name="password" type="password"
					class="form-control no-border input-lg rounded"
					placeholder="输入您的密码" value=""> <span
					class="fa fa-unlock-alt form-control-feedback"></span>
			</div>
			<div>
			    <div class="form-group" style="float: left;width: 60%">
				 <input id="userCode" name="userCode" type="text"
					class="form-control no-border input-lg rounded"
					placeholder="输入验证码">  
			    </div>
			    <img style="margin-left: 10px;margin-top:8px;cursor: pointer;" id="codeImg" title="看不清换一张" alt="验证码" src="${pageContext.request.contextPath}/system/code/code" onclick="changeImg()"/>	
			</div>
			
			<div class="form-group">
				<button  onclick="loginFun()" type="button"
					class="btn btn-warning btn-lg btn-perspective btn-block">登录</button>
			</div>
		</form>
	</div>

</body>
</html>