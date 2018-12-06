<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
  <%@include file="../include/header.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="css/bootstrap/jquery.min.js"></script>
<link href="css/bootstrap/3.3.6/bootstrap.min.css"
	rel="stylesheet">
<script src="css/bootstrap/3.3.6/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<style>
#formAdd input{
float:left;
margin:10px;
 }
</style>
	<table class = "table"><tr>
	<td>ID</td>
	<td>图片</td>
	<td>类名</td>
	<td>属性管理</td>
	<td>产品管理</td>
	<td>编辑</td>
	<td>删除</td></tr>
		<c:forEach items="${cs}" var="c">

<tr>

<td>
${c.id}</td>
<td><img src = "img/category/category${c.id}.jpg"></td>
<td>${c.name}</td>
<td><a class =  "glyphicon glyphicon-pencil" href = "property?id=${c.id}"></a></td>
<td><a class = "glyphicon glyphicon-th-list" href= "product?id=${c.id }"></a></td>
<td><a class =  "glyphicon glyphicon-cog"href="edit?id=${c.id}"></a></td>
<td><a class = "glyphicon glyphicon-trash" href="delete?id=${c.id}&page=${page.page}"></a></td>

</tr>


</c:forEach>
</table>
<div align = "center">	
	<input type = "button" value = "首页" class=  "btn" onclick = "window.location.href='admin?page=1'">
	<input type= "button" value = "上一页" class= "btn" onclick = "window.location.href='admin?page=${page.previous}'">
	
	<input type="button" value = "下一页"  class = "btn" onclick = "window.location.href='admin?page=${page.next}'">
	<input class = "btn" type = "button" onclick = "window.location.href='admin?page=${page.endPage}'" value = "尾页">
</div>
<div align="center" style = "width:500px;margin: 0px auto;">
<form id = "formAdd" action="insert" enctype="multipart/form-data" method="post"><div style="width: 300px;float:left;">
<input type=  "text" name = "name" class = "form-control"><br>
<input  accept="image/*" type="file" name="image" />
<input type = "submit" class = "btn btn-primary"></div>
</form>
</div>

</body>
</html>