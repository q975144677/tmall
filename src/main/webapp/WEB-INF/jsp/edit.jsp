<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>     <%@include file="../include/header.jsp"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="css/bootstrap/jquery.min.js"></script>
<link href="css/bootstrap/3.3.6/bootstrap.min.css"
	rel="stylesheet">
<script src="css/bootstrap/3.3.6/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action = "change" method = "post" enctype="multipart/form-data">
分类名<input type = "text" name = "name">
<input type = "file" name = "image" accept= "image/*">
<input type = "hidden" name = "id" value = "${category.id}">
<input type = "submit" >
</form>
<a href = "admin">返回</a>
</body>
</html>