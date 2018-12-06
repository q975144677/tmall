<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>   
    
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@include file="../include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<script src="css/bootstrap/jquery.min.js"></script>
<link href="css/bootstrap/3.3.6/bootstrap.min.css"
	rel="stylesheet">
<script src="css/bootstrap/3.3.6/bootstrap.min.js"></script>
<style>
#float div{
float:left;;
}
#float span{
font-size:16px;
font-family: "黑体";
}
#float input{

width:200px;
height:40px;
}
.divd img{
width:100px;
height:100px;
}
</style>
<div class="divd" style = "height:300px;">
<div id = "float" style = "position:relative;left:100px">
<div>
<span>单个图片</span>
<form action="productImage_insert" enctype="multipart/form-data" method = "post">
<input type = "hidden" name = "pid" value = "${product.id }">
<input type = "hidden" name = "cid" value = "${cid }">
<input type = "hidden" name = "type" value = "Single">
<input class="btn" type = "file" name = "image" accept="image/*">
<input class="btn-danger"type = "submit">
</form>
<div style="width:500px;">
<table class="table">
<c:forEach items = "${singleImages }" var = "p"><tr><td>${p.id }</td><td>
<img src = "img/productImage/${p.id }.jpg"></td><td><a href="productImage_delete?id=${p.id }&cid=${cid}&pid=${product.id}">删除</a></td></tr>
</c:forEach>
</table>
</div>
</div>
<div>
<span style = "position:relative;left:200px">详细图片</span>
<form style = "position:relative;left:200px" action="productImage_insert" enctype="multipart/form-data" method = "post">
<input type = "hidden" name = "pid" value = "${product.id }">
<input type = "hidden" name = "cid" value = "${cid }">
<input type = "hidden" name = "type" value = "Detail">
<input class="btn" type = "file" name = "image" accept="image/*">
<input class="btn-danger"type = "submit">
</form>
<div style="width:500px;position:relative;left:200px">
<table class="table">
<c:forEach items = "${detailImages }" var = "p"><tr><td>${p.id }</td><td>
<img src = "img/productImage/${p.id }.jpg"></td><td><a href="productImage_delete?id=${p.id }&ci	d=${cid}&pid=${product.id}">删除</a></td></tr>
</c:forEach>
</table>
</div>
</div>
</div>
</div><div>
<a href= "product?id=${cid }" style ="position:relative;left:600px"><button class= "btn">返回</button></a>
</div>
</body>
</html>