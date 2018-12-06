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
.fl div{
float:left;
position:relative;
left:300px}
</style>
<%@include file = "include/fore/header.jsp" %>
<%@include file = "include/fore/top.jsp" %>
<form method="post" action="createReview">
<table class="table">
<tr><td>
订单号：${o.orderCode}</td></tr>
<c:forEach items="${o.orderItems }" var = "o">
<tr><td>
商品名称：${o.product.name}</td><td><img src = "img/product/product${o.product.id }.jpg"></td></tr>
<tr><td colspan="2"><div class="fl"><div><textarea class="textarea" cols="200" rows="5" name = "content"></textarea></div></div><td></tr>
<input type = "hidden" name = "id" value = "${o.product.id }">
</c:forEach>
</table>
<input type = "hidden" name= "oid" value="${o.id }">
<div><input id = "sub" type ="submit" class="btn btn-success"></div>
</form>
<%@include file = "include/fore/footer.jsp" %>
</body>
<script>
$(function(){
	$("#sub").click(function(){
		$(".textarea").each(function(){
			var x=  $(this).val();
		
			if(x==""){
				$(this).val("该用户没有任何评价");
				
				
			}
		
		});
		return true;
	});
	
	
});
</script>
</html>