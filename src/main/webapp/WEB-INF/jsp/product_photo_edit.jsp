<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>     <%@include file="../include/header.jsp"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method = "post" enctype = "multipart/form-data" action="product_changeImage">
<input type = "file" accept = "image/*" name = "image">
<input type=  "hidden" value = "${product.id}"  name = "id" >
<input type = "hidden" value = "${product.cid }" name = "cid">
<input type = "submit">
</form>
<a href="product?id=${product.cid }">返回</a>
</body>
</html>