<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>     <%@include file="../include/header.jsp"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action = "property_update" method = "post">
<input type="text" name = "name" placeholder = "请输入要修改的名字"/>
<input type = "hidden" name = "id" value ="${property.id}">
<input type="hidden" name = "cid" value = "${property.cid}"/>
<input type  = "submit">
</form>
<a href = "property?id=${property.cid}">返回</a>
</body>
</html>