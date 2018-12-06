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
<style>
.table input{
width:20px;
height:20px;
}
</style>
<%@include file = "include/fore/top.jsp"%>
<form action = "forbuy" method = "post">
<table class = "table">
<tr>
<td></td>
<td>商品图片</td>
<td>商品名称</td>
<td>商品数量</td>
<td>商品单价</td>
<td><a href= "#" id = "deleteAll">全部删除</a></td>
</tr>

<c:forEach items = "${ois }" var = "o">
<tr class ="tr">
	<td><input type = "checkbox" id="pre"><input id = "input"style ="display:none;" type = "checkbox" value = "${o.id }" data-num="${o.number }" data-price = "${o.product.promotePrice}" name = "oiid"></td><td><a href = "#"><img src = "img/product/product${o.pid }.jpg"></a></td>
	<td>${o.product.name}</td>
	<td><a  class = "glyphicon glyphicon-arrow-up up" ></a><span  id = "number" data-value ="${o.id }">${o.number }</span><a class="glyphicon glyphicon-arrow-down down"></a></td>
	<td>${o.product.promotePrice }</td>
	<td><a href = "delete_orderItem?id=${o.id }"><span class = "glyphicon glyphicon-trash"></span></a></td>
</tr>
</c:forEach>

</table>
<h3><span>总共<span id="num">0</span>件，总共<span id = "price">0</span>元</span>
</h3>
<input id="submit" disabled="disabled" class= "btn btn-danger btn-block" type = "submit" value = "去支付" style = "height:50px;">
</form>
<%@include file = "include/fore/footer.jsp"%>
<%@include file = "include/fore/modal.jsp"%>
<%@include file = "include/fore/header.jsp"%>
</body><script>
$(function(){
	var price=0 ; 
	var num=0;
	  function sleep(n) { //n表示的毫秒数
		            var start = new Date().getTime();
		            while (true) if (new Date().getTime() - start > n) break;
		        }  
	
	$(".up").click(function(){
		
		var n =$(this).parent().find("span").html();		
		var page = "change_orderItem";
		var id = $(this).parent().find("span").data("value");
		n++;
	var span =$(this).parent().find("#number");	
	var nu = $(this).parent().parent().find("#input");
	
	var y = parseInt($(this).parent().parent().find("#input").data("price"));

		$.post(
				page,
				{"id":id,"number":n},
				function(result){	
					if(result!="false"){	
					n=result;	
					span.html(n);		
					nu.data("num",n);
					var x = nu.data("num");	
					if(nu.prop("checked")){			
						
						num++;			
						price += y;			
						$("#price").html(price);			
						$("#num").html(num);			
					}
					}
				
				}
		);
	
		
		if(!$(this).parent().parent().find("#input").prop("checked")){
			$(this).parent().parent().find("#input").prop("checked","checked");
			$(this).parent().parent().find("#pre").prop("checked","checked");
		$(this).css("background-color","#F5F5DC");

		var temp = parseInt($(this).parent().parent().find("#input").data("price")) ;
		var temp2 = parseInt($(this).parent().parent().find("#input").data("num"));
		
		price+=temp*temp2;
		num+=temp2;
		$("#price").html(price);
		$("#num").html(num);	
			}
			else{
				$(this).parent().parent().css("background-color","");
				$(this).parent().parent().find("#input").prop("checked",false);
				$(this).parent().parent().find("#pre").prop("checked",false);
				var temp = parseInt($(this).parent().parent().find("#input").data("price")) ;
				var temp2 = parseInt($(this).parent().parent().find("#input").data("num"));
				price-=temp*temp2;
				num-=temp2;
				$("#price").html(price);
				$("#num").html(num);
			}
			flag=parseInt(num);
			if(flag<=0){
				$("#submit").prop("disabled",true);
				
			}
			else{
				$("#submit").prop("disabled",false);
			}
	});
	
	$(".down").click(function(){

		var n =$(this).parent().find("span").html();		
		var page = "change_orderItem";
		var id = $(this).parent().find("span").data("value");
		n--;
		var span =$(this).parent().find("#number");	
		var nu = $(this).parent().parent().find("#input");
		
		var y = parseInt($(this).parent().parent().find("#input").data("price"));		
			$.post(
					page,
					{"id":id,"number":n},
					function(result){	
						if(result!="false"){	
						n=result;	
						span.html(n);		
						nu.data("num",n);
						var x = nu.data("num");	
						if(nu.prop("checked")){			
							
							num--;			
							price -= y;			
							$("#price").html(price);			
							$("#num").html(num);			
						}
						}
					
					}
			);
		if(!$(this).parent().parent().find("#input").prop("checked")){
			$(this).parent().parent().find("#input").prop("checked","checked");
			$(this).parent().parent().find("#pre").prop("checked","checked");
		$(this).css("background-color","#F5F5DC");

		var temp = parseInt($(this).parent().parent().find("#input").data("price")) ;
		var temp2 = parseInt($(this).parent().parent().find("#input").data("num"));
		
		price+=temp*temp2;
		num+=temp2;
		$("#price").html(price);
		$("#num").html(num);	
			}
			else{
				$(this).parent().parent().css("background-color","");
				$(this).parent().parent().find("#input").prop("checked",false);
				$(this).parent().parent().find("#pre").prop("checked",false);
				var temp = parseInt($(this).parent().parent().find("#input").data("price")) ;
				var temp2 = parseInt($(this).parent().parent().find("#input").data("num"));
				price-=temp*temp2;
				num-=temp2;
				$("#price").html(price);
				$("#num").html(num);
			}
			flag=parseInt(num);
			if(flag<=0){
				$("#submit").prop("disabled",true);
				
			}
			else{
				$("#submit").prop("disabled",false);
			}
	});
	$(".tr").click(function(){
	
		if(!$(this).find("#input").prop("checked")){
		$(this).find("#input").prop("checked","checked");
		$(this).find("#pre").prop("checked","checked");
	$(this).css("background-color","#F5F5DC");

	var temp = parseInt($(this).find("#input").data("price")) ;
	var temp2 = parseInt($(this).find("#input").data("num"));
	
	price+=temp*temp2;
	num+=temp2;
	$("#price").html(price);
	$("#num").html(num);	
		}
		else{
			$(this).css("background-color","");
			$(this).find("#input").prop("checked",false);
			$(this).find("#pre").prop("checked",false);
			var temp = parseInt($(this).find("#input").data("price")) ;
			var temp2 = parseInt($(this).find("#input").data("num"));
			price-=temp*temp2;
			num-=temp2;
			$("#price").html(price);
			$("#num").html(num);
		}
		flag=parseInt(num);
		if(flag<=0){
			$("#submit").prop("disabled",true);
			
		}
		else{
			$("#submit").prop("disabled",false);
		}
	});
	$("#deleteAll").click(function(){
		var flag = confirm("是否要全部删除");
		if(flag){
			location.href="delete_orderItem?id=0&uid=${user.id }";
		}
	});
});

</script>
</html>