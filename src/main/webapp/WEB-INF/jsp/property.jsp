<%@page import="yellow.util.Page"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
      <%@include file="../include/header.jsp"%>	
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script src="css/bootstrap/jquery.min.js"></script>
<link href="css/bootstrap/3.3.6/bootstrap.min.css"
	rel="stylesheet">
<script src="css/bootstrap/3.3.6/bootstrap.min.js"></script>
<body>
<style>

</style>
<div align = "center">
<div>
<table class = "table">
	<tr>
		<td>ID</td>
		<td>属性名称</td>
		<td>编辑</td>
		<td>删除</td>
	</tr>
	<c:forEach items = "${propertys}" var = "property">
	<tr>
		<td>${property.id}</td>
		<td>${property.name}</td>
		<td><a class = "glyphicon glyphicon-cog" href="property_edit?id=${property.id}&cid=${property.cid}"></a></td>
		<td><a class = "glyphicon glyphicon-trash" href="property_delete?id=${property.id}&cid=${property.cid}"></a></td>
	</tr>
	</c:forEach>
	
</table>

<form action = "property_add" method = "post" style = "display:inline">
<input type = "hidden" name = "cid" value = "${cid}">
<input type = "text" name = "name" placeholder = "请输入名称">
<input type = "submit">

</form><button onclick = "window.location.href='admin'">返回</button></div>

<%
Page p = (Page)request.getAttribute("page");

if(p.getPrevious()>=1){ %>
<input type = "button" onclick = "window.location.href = 'property?page=1&id=${category.id}'" value = "首页">
<input type = "button" onclick = "window.location.href='property?page=${page.previous}&id=${category.id}'" value = "上一页">

<%}
if(p.getNext()<=p.getEndPage()){ %>
<input type = "button" onclick = "window.location.href='property?page=${page.next}&id=${category.id}'" value = "下一页">
<input type = "button" onclick = "window.location.href='property?page=${page.endPage}&id=${category.id}'" value = "尾页">
<%} %>
<!-- 

<%=p.getEndPage() %>
<%=p.getPrevious() %>
<%=p.getNext() %> -->
</div>
</body>
</html>