<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>This is a test page for JSP!</title>
</head>
<body>
	<p>jsp using javabean example</p>
	
	<jsp:useBean id="user" class="com.yed.common.bean.User"></jsp:useBean>
	<jsp:setProperty property="name" name="user" value="yed"/>
	
	<jsp:getProperty property="name" name="user"/>
	
	<jsp:forward page="list.jsp" />
	
</body>
</html>