<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>   
    
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
.table td{
border:1px solid lightgrey;
}
.table tr{
padding:10px;
}
.nav div{
float:left;
}
.nav div{
width:20%;

}

.nav button{
width:100%;
border:1px solid black; 
}

.btn-info{
width:100px;
}
</style>
<%@include file = "include/fore/top.jsp" %>
<%@include file = "include/fore/header.jsp" %>
<div class= "nav">
<div><a><button id="b1" class= "btn btn-danger active">所有订单</button></a></div>
<div><a><button id="b2" class= "btn btn-danger">待付款</button></a></div>
<div><a><button id="b3" class= "btn btn-danger">待发货</button></a></div>
<div><a><button id="b4" class= "btn btn-danger">待收货</button></a></div>
<div><a><button id="b5" class= "btn btn-danger">待评价</button></a></div>
</div>
<table class = "table t1"><tr>
<td style = "width:50%">宝贝</td>
<td>单价</td>
<td>数量</td>
<td>实付款</td>
<td rowspan="2">交易操作</td>
</tr>
<c:forEach items="${orders }" var = "o">
<tr class = "c${o.id }">
<td>${o.createDate }订单号：${o.orderCode }</td>
<td>天猫商场</td>
<td>和我联系</td>
<td><a href= "order_delete?id=${o.id }">取消订单</a></td>
</tr>
<tr>
<c:forEach items="${o.orderItems }" var = "oi" varStatus="os">
<tr><td>
<img src = "img/product/product${oi.pid }.jpg">
${oi.product.name}
</td>
<c:if test="${os.first }">
<td class= "td" >￥${oi.product.promotePrice }</td>
<td class= "td" >${o.totalNumber }</td>
<td class= "td" >${o.total }</td>
<td class= "td" ><c:if test="${o.status!='waitPay' }"><c:if test="${o.status!='waitConfirm' }"><c:if test="${o.status!='waitReview' }">${o.statusDesc }</c:if></c:if></c:if>
<c:if test = "${o.status=='waitPay' }"><a href="forepay?oid=${o.id }&price=${o.total}"><button class="btn btn-info">去付款</button></a></c:if>
<c:if test = "${o.status=='waitConfirm' }"><a data-value="${o.id }" class="recive"><button class="btn btn-info">收货</button></a></c:if>
<c:if test = "${o.status=='waitReview' }"><a href="review?oid=${o.id }"><button class="btn btn-info">评价</button></a></c:if>

</td>
</c:if>
</tr>
</c:forEach>
</tr>
</c:forEach>

</table>
<table class = "table t2"><tr>
<td style = "width:50%">宝贝</td>
<td>单价</td>
<td>数量</td>
<td>实付款</td>
<td rowspan="2">交易操作</td>
</tr>
<c:forEach items="${waitpayOrders }" var = "o">
<tr>
<td>${o.createDate }订单号：${o.orderCode }</td>
<td>天猫商场</td>
<td>和我联系</td>
<td><a href= "order_delete?id=${o.id }">取消订单</a></td>
</tr>
<tr>
<c:forEach items="${o.orderItems }" var = "oi" varStatus="os">
<tr><td>
<img src = "img/product/product${oi.pid }.jpg">
${oi.product.name}
</td>
<c:if test="${os.first }">
<td class= "td" >￥${oi.product.promotePrice }</td>
<td class= "td" >${o.totalNumber }</td>
<td class= "td" >${o.total }</td>
<td class= "td" ><c:if test="${o.status!='waitPay' }">${o.statusDesc }</c:if><c:if test = "${o.status=='waitPay' }"><a href="forepay?oid=${o.id }&price=${o.total}"><button class="btn btn-info">去付款</button></a></c:if></td>
</c:if>
</tr>
</c:forEach>
</tr>
</c:forEach>

</table>

<table class = "table t3"><tr>
<td style = "width:50%">宝贝</td>
<td>单价</td>
<td>数量</td>
<td>实付款</td>
<td rowspan="2">交易操作</td>
</tr>
<c:forEach items="${waitDeliveryOrders }" var = "o">
<tr>
<td>${o.createDate }订单号：${o.orderCode }</td>
<td>天猫商场</td>
<td>和我联系</td>
<td><a href= "order_delete?id=${o.id }">取消订单</a></td>
</tr>
<tr>
<c:forEach items="${o.orderItems }" var = "oi" varStatus="os">
<tr><td>
<img src = "img/product/product${oi.pid }.jpg">
${oi.product.name}
</td>
<c:if test="${os.first }">
<td class= "td" >￥${oi.product.promotePrice }</td>
<td class= "td" >${o.totalNumber }</td>
<td class= "td" >${o.total }</td>
<td class= "td" >${o.statusDesc }</td>
</c:if>
</tr>
</c:forEach>
</tr>
</c:forEach>

</table>


<table class = "table t4"><tr>
<td style = "width:50%">宝贝</td>
<td>单价</td>
<td>数量</td>
<td>实付款</td>
<td rowspan="2">交易操作</td>
</tr>
<c:forEach items="${waitConfirmOrders }" var = "o">
<tr class = "c${o.id }">
<td>${o.createDate }订单号：${o.orderCode }</td>
<td>天猫商场</td>
<td>和我联系</td>
<td><a href= "order_delete?id=${o.id }">取消订单</a></td>
</tr>
<tr>
<c:forEach items="${o.orderItems }" var = "oi" varStatus="os">
<tr><td>
<img src = "img/product/product${oi.pid }.jpg">
${oi.product.name}
</td>
<c:if test="${os.first }">
<td class= "td" >￥${oi.product.promotePrice }</td>
<td class= "td" >${o.totalNumber }</td>
<td class= "td" >${o.total }</td>
<td class= "td" ><c:if test = "${o.status=='waitConfirm' }"><a data-value="${o.id }" class="recive"><button class="btn btn-info">收货</button></a></c:if></td>
</c:if>
</tr>
</c:forEach>
</tr>
</c:forEach>

</table>


<table class = "table t5"><tr>
<td style = "width:50%">宝贝</td>
<td>单价</td>
<td>数量</td>
<td>实付款</td>
<td rowspan="2">交易操作</td>
</tr>
<c:forEach items="${waitReviewOrders }" var = "o">
<tr>
<td>${o.createDate }订单号：${o.orderCode }</td>
<td>天猫商场</td>
<td>和我联系</td>
<td><a href= "order_delete?id=${o.id }">取消订单</a></td>
</tr>
<tr>
<c:forEach items="${o.orderItems }" var = "oi" varStatus="os">
<tr><td>
<img src = "img/product/product${oi.pid }.jpg">
${oi.product.name}
</td>
<c:if test="${os.first }">
<td class= "td" >￥${oi.product.promotePrice }</td>
<td class= "td" >${o.totalNumber }</td>
<td class= "td" >${o.total }</td>
<td class= "td" ><c:if test = "${o.status=='waitReview' }"><a data-value="${o.id }" class="review" href="review?oid=${o.id }"><button class="btn btn-info">评价</button></a></c:if></td>
</c:if>
</tr>
</c:forEach>
</tr>
</c:forEach>

</table>
<%@include file = "include/fore/footer.jsp" %>
</body>
<script>
$(function(){
	$(".table").not(".t1").hide();
	$("#b1").click(function(){
		$("#b1").prop("class","btn btn-danger active");
		$(".table").show();
		$(".table").not(".t1").hide();
	});
	$("#b2").click(function(){
		$(".btn-danger").prop("class","btn btn-danger");
		$("#b2").prop("class","btn btn-danger active");
		$(".table").show();
		$(".table").not(".t2").hide();
	});
	$("#b3").click(function(){
		$(".btn-danger").prop("class","btn btn-danger")
		$("#b3").prop("class","btn btn-danger active");
		$(".table").show();
		$(".table").not(".t3").hide();
	});
	$("#b4").click(function(){
		$(".btn-danger").prop("class","btn btn-danger")
		$("#b4").prop("class","btn btn-danger active");
		$(".table").show();
		$(".table").not(".t4").hide();
	});
	$("#b5").click(function(){
		$(".btn-danger").prop("class","btn btn-danger")
		$("#b5").prop("class","btn btn-danger active");
		$(".table").show();
		$(".table").not(".t5").hide();
	});
	$(".recive").click(function(){
		var x = parseInt($(this).data("value"));
		var page = "recive";
		var parent = $(this).parent().parent();
		var y =".c"+x;
	
		$.post(
				page,
				{"oid":x},
				function(result){
					if(result=="success"){	
						location.reload();
					}
				}
		);
		
	
	});
	
});

</script>
</html>