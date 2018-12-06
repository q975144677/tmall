<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>   
    
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<%@include file="/WEB-INF/jsp/include/fore/header.jsp"%>
<%@include file="include/fore/top.jsp"%>
<style>
.detail td{
padding: 10px;

}
.detail input{
position : relative;
left : -20px;
width:250px;
}
.detail textarea{
position : relative;
left : -20px;
width:400px;
}
.d2
{
 position:relative;
 left:-220px;
 }
 
</style>
<table style = "width:500px;margin:0px auto" class = "detail">
<form method = "post" action = "createOrder">
<tr><td colspan="2" style = "position:relative;left:-20px;width:200px;font-family:'黑体' ;font-size:18px;"><b>输入收货地址</b></td></tr>
<tr>
	<td>详细地址：</td>
	<td><textarea name = "address" cols="40" rows="3"></textarea></td>
</tr>
<tr>
	<td>邮政编码</td>
	<td><input name = "post" type = "text"></td>
</tr>
<tr>
	<td>收货人姓名</td>
	<td><input name = "receiver" type = "text"></td>
</tr>
<tr>
	<td>手机号码</td>
	<td><input name = "mobile" type = "text"></td>
</tr>


</table><table class = "table">
<tr>
	<td>产品图片</td>
	<td class= "d2">产品名称</td>

	<td>单件价格</td>
	<td>数量</td>
	<td>总价</td>
</tr>
	<c:forEach items = "${ois }" var = "o">
	<tr>
	<td><input type = "hidden" name = "oiid" value = "${o.id }"><img src = "img/product/product${o.pid }.jpg"></td>
	<td class = "d2">${o.product.name }</td>
	<td>￥${o.product.promotePrice }</td>
	<td>${o.number }</td>
	<td><span style = "color:red;font-size: 12px;"><b>￥${o.totalPrice }</b></span></td>
	</tr>
	</c:forEach>
	<tr><td colspan="5"><div style = "float:left;"><div style = "float:left;">给卖家留言</div><textarea name=  "userMessage" id="review" maxlength="200" cols="60" rows="5"></textarea><div id = "res"></div></div></td></tr>
	</table>
	
		<div style="position:relative;top:-90px;float:right;padding: 0px 100px 50px 0px"><span style="font-size: 18px;margin-right:30px;">实付款：￥</span><span style="font-size: 24px;color: red;"><b>${total }</b></span></div>
	<div style = "position:relative;left:220px;"><input class= "btn btn-danger" style = " float : right;width:300px;"type = "submit">
	<a href="forecart"><input class = "btn btn-info" style = "float:right;width:300px;" type="button" value = "返回购物车" /></a></div>
	</form><%@include file="include/fore/footer.jsp"%>
<script>
$(function(){
	var page = "review_check";

	$("#review").keyup(function(){
		var value = $(this).val();
		var x;
		$.post(
				page,
				{"value":value},
			function(result){
					x = result;
					$("#res").html("还可输入"+x+"字");
				}
				
		);
		
	});
	
	
});
	</script>
</body>
</html>