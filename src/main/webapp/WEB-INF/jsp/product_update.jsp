<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>     <%@include file="../include/header.jsp"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="product_update">
<input type = "hidden" name = "id" value = "${product.id }">
<input type = "hidden" name = "cid" value = "${product.cid }">
name:<input type = "text" name = "name" placeholder = "请输入名字">
subTitle:<input type = "text" name = "subTitle">
op:<input type = "text" name = "originalPrice">
pp:<input type = "text" name  = "promotePrice">
stock:<input type = "text" name = "stock">
<input type = "submit">
</form>
<a href=  "product?id=${product.cid }">返回</a>
</body>
</html>









