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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<style>
.image{
padding:10px;

}
.orderitem td{
padding:10px 0px 10px 0px;;
width:300px;
text-align: center
}
</style>
<table class = "table">
	<tr>
		<td>订单号</td>
		<td>状态</td>
		<td>总金额</td>
		<td>商品数量</td>
		<td>买家</td>
		<td>创建时间</td>
		<td>支付时间</td>
		<td>发货时间</td>
		<td>确认收货时间</td>
		<td>操作</td>
	</tr>
	<c:forEach items = "${orders }" var = "o" varStatus="a">
	<tr>
	<td>${o.id }</td>
	<td>${o.statusDesc }</td>
	<td>${o.total }</td>
	<td>${o.totalNumber }</td>
	<td>${o.user.name }</td>
	<td>${o.createDate }</td>
	<td>${o.payDate }</td>
	<td>${o.deliveryDate }</td>
	<td>${o.confirmDate }</td>
	<td><input class= "button" data-valuee = "${a.count }" type = "button" value = "查看详情">
	
	<c:if test="${o.status=='waitDelivery' }">
	<a style = "text-decoration:none;color:black;" href= "order_deliver?id=${o.id }"><input id="b" type = "button" value = "发货"></a>
	</c:if>
	</td>
	</tr><tr><td colspan="10">
<br>
<div style = "width:1200px;border:1px solid black;margin:0px auto;overflow:hidden;" class="attr"id="attr${a.count }">

	<c:forEach items= "${o.orderItems }" var = "oi" varStatus="s">	<table class= "orderitem">
	<c:if test = "${s.first }"><tr>
	<td>产品图</td>
	<td>产品名</td>
	<td>产品数</td>
	<td>单价</td>
	<td>总价</td></tr></c:if>
	<tr><td>
	<img class= "image" src= "img/product/product${oi.product.id }.jpg"><br>
	</td>
	<td>${oi.product.name }</td>
	<td>
	${oi.number }
	</td>
	<td>${oi.product.promotePrice }</td>
	<td>${oi.product.promotePrice*oi.number }</td>
	</tr></table>

	</c:forEach></div></td></tr>
	
	</c:forEach>
</table>
<script>
$(function(){
	var flag = 0
	$(".attr").hide();
	$(".button").click(function(){
		if(flag == 0){
			$(this).attr("value","返回缩略");
			flag = 1;
		}
		else{
			$(this).attr("value","查看详情");
			flag = 0;
			
		}	
		var x = $(this).data("valuee");
		var n = "#attr"+x;

		$(n).slideToggle(600);
	});
	 
});
</script>
</body>
</html>