<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <%@include file="../include/header.jsp"%>
 
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
<style>

</style>
<table class = "table">
<tr>
	<td>ID</td>
	<td>图片</td>
	<td>产品名称</td>
	<td>产品小标题</td>
	<td>原价</td>
	<td>优惠价格</td>
	<td>库存数量</td>
	<td>图片管理</td>
	<td>设置属性</td>
	<td>编辑</td>
	<td>删除</td>
</tr>

<c:forEach items = "${products}" var = "product">
<tr>
<td>${product.id }</td>
<td><img src = "img/product/product${product.id }.jpg"></td>
<td>${product.name }</td>
<td>${product.subTitle }</td>
<td>${product.originalPrice }</td>
<td>${product.promotePrice }</td>
<td>${product.stock }</td>

<td><a class = "glyphicon glyphicon-picture" href="productImage?id=${product.id}&cid=${product.cid}"></a></td>
<td><a  class = "glyphicon glyphicon-asterisk" href="property_value?id=${product.id}&cid=${product.cid}"></a></td>
<td><a class =  "glyphicon glyphicon-pencil" href="product_edit?id=${product.id }&cid=${product.cid}"></a></td>
<td><a href="product_delete?id=${product.id }&cid=${product.cid}&page=${page.page}" class = "glyphicon glyphicon-trash"></a></td>
<tr>
</c:forEach>
</table><div align  = "center">
<input value = "next" type = "button" onclick = "window.location.href='product?page=${page.next}&id=${cid}'"
<c:if test = "${page.next>page.endPage}">style = "display:none"</c:if>
>



${page.page }

<input value = "privious" type = "button" onclick = "window.location.href='product?page=${page.previous}&id=${cid}'"
<c:if test = "${page.previous<1}"> style = "display:none"</c:if>
>
<a href= "admin">返回</a>
</div>
<div align = "center" style = "width:100%;margin-top:20px">
<form action = "product_insert" method="post" enctype="multipart/form-data">
name:<input type="text" name = "name"/><br>
subTitle:<input type="text" name = "subTitle"/><br>
op:<input type="text" name = "originalPrice"/><br>
pp:<input type="text" name = "promotePrice"/><br>
stock:<input type="text" name = "stock"/><br>
<input type = "hidden" name = "cid" value = "${cid }">
<input type = "file" name = "image" accept="image/*">
<input type="submit" />
</form>
</div>



</body>
</html>