<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
    
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
<%@include file="../include/header3.jsp" %>
<style>
.noPoint{
list-style-type:none;
font-family:"微软雅黑";
}

.noPoint .li li{
padding:10px;
background-color:lightgrey;
width:145px;
z-index:4;
position:relative;
left:200px;
}
.noPoint .li li:hover {
background-color:white; 
color:threedhighlight;overflow:hidden	;
}
.nav{
background:red;
height:40px;
width:100%;
position:absolute;
top:146px;
z-index:3;
padding-left:500px;
}
.nav img{
padding-right:70px;
}
.nav span a{
color:white;
text-decoration: none;
padding-right:70px;


font-size: 18px;
}
body{
overflow:hidden;}
</style>
<div>
<div style = "width:100%">
<div class="nav">
<span style = "position:relative;left:-230px;color:white;font-family: '微软雅黑'">商品分类</span>
<a href=""><img src= "image/site/chaoshi.png" style = "height:100%;width:14%;"></a>

<a href=""><img src= "image/site/guoji.png" style = "height:100%;width:14%;" ></a>
<span>
<c:forEach items="${categories }" var = "c" varStatus = "s">
<c:if test ="${s.count>=5&&s.count<=8 }">
<a href = "#" >${c.name }</a>
</c:if>
</c:forEach>
</span>
</div>


<div id="d" style = "width:140px; position:absolute; padding:left:200px;top:145px;overflow:hidden;background-color: grey;width:100%;z-index:2;">

<c:forEach items = "${categories }" var = "c">
<div class="div" id="div${c.id }" style = "height:100%;width:880px;;z-index:5;overflow:hidden;background-color:white;position:absolute;left:384px;top:36px;">
<table>
<c:forEach items = "${c.productsByRows }" var = "pr">
<tr>
<c:forEach items = "${pr}" var = "p"><td><a href="" style = "padding:10px 60px 20px 0px;display:inline-block;">
${p.subTitle }</a></td>
</c:forEach>
</tr>

</c:forEach>
</table>
</div>
</c:forEach>
<ul id="father" class= "noPoint">
<div class= "li">

<c:forEach items = "${categories }" var = "c" >
<li class= "categoryP" id="${c.id }">
${c.name}
</li>
</c:forEach></div>
</ul></div>
</div>
</div>
<div>
<c:forEach items ="${categories}" var = "c"><div>
${c.name }</div>
<c:forEach items = "${c.products }" var = "p">

<img src = "img/product/product${p.id }.jpg">
</c:forEach>
</c:forEach>
</div>
<script>
$(function(){
	$(".div").hide();
	$(".categoryP").mouseenter(function(){
		$(".categoryP").each(function(){
			$("#div"+$(this).attr('id')).hide();
		});
	
	$("#div"+$(this).attr('id')).show();
	});
	$("#d").mouseleave(function(){
		$(".categoryP").each(function(){
			$("#div"+$(this).attr('id')).hide();
		});

	});
	
});
</script>


</body>
</html>