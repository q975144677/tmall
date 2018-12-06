<%@page import="yellow.pojo.PropertyValue"%>
<%@page import="java.util.List"%>
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
<script src="css/bootstrap/jquery.min.js"></script>
<link href="css/bootstrap/3.3.6/bootstrap.min.css"
	rel="stylesheet">
<script src="css/bootstrap/3.3.6/bootstrap.min.js"></script><script>


$(function() {
	$("input.pv").keyup(function(){
    var value = $(this).val();
    var page = "property_value_update";
    var pvid = $(this).attr("pvid");
    var parentSpan = $(this).parent("span");
    parentSpan.css("border","1px solid yellow");
    $.post(
        page,
        {"value":value,"id":pvid},
        function(result){
            if("success"==result)
                parentSpan.css("border","1px solid green");
            else
                parentSpan.css("border","1px solid red");
        }
    );
});
});
</script>
<body>
<style>
.t td{
padding:0px 10px;

}

</style>



<div style = "width:500px;margin:0px auto;;">
<table class = "t"><tr>

<c:forEach items="${propertyValues}" var= "p" varStatus="vs">

<td>
${map[p.ptid]}</td>
<td>
：<span><input class="pv" pvid = "${p.id}" type = "text" value = "${p.value}"></span></td>
<c:if test = "${vs.index%2==1 }"></tr><tr></c:if>
</c:forEach>
</tr>
</table>

<a href= "product?id=${cid}">返回产品</a>
<a href = "admin">返回主页</a></div>
</body>

</html>