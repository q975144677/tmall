<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<style>
.sear span{
padding:0px 30px 0px 0px;
color:lightgrey;
}
.sear a{
text-decoration:none;
color:lightgrey;
}
.sear a:hover{
color:red;
}
</style><div>
<img src = "image/site/logo.gif" style = "float:left">
<div style = "margin:0px auto 0px; width:420px; position:relative;top:40px;"  >
<div style = "float:left">
<div align = "center" style = "width:420px; height:42px;background-color:red; ">
<form action="">
<input type = "text" style = "width:70%;height:36px;padding:1px;position:relative;top:0px;left:-7px;;" name = "search">
<input type = "submit" value = "搜索" style = "font-family:'黑体';color:white;background-color:red;width:24%;height:36px;;border:1px solid red;position:relative;top:3px;font-size:130%">
</form>
<div class= "sear" style= "float:left;margin-top:5px;">
<c:forEach items = "${categories }" var = "c" varStatus="s">
<c:if test ="${s.count<=4 }">
<c:if test = "${!s.first }"><span>|</span></c:if><span><a href="">${c.name }</a></span>
</c:if>
</c:forEach>
</div>
</div>
</div>
</div>
</div>
</body>
</html>