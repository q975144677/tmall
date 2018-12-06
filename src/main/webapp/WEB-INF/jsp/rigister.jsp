<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<style>
.re td{
padding:20px 10px;

}
.re input{
position:relative;
left:-10px;
}
.re span{
font:bold 14px "黑体";
}
</style>
<form action = "registSuccess" method = "post" style = "margin-bottom:100px;">
<table class="re" style = "margin:0px auto;position:relative;left:-50px;"><tr><td><span>
设置会员名</span></td></tr>	
<tr><td></td><td>登录名</td><td><input type = "text" name = "name" id = "user"></td><td style = "position:relative;top:0px;left:0px">
<div style = "position:absolute;top:25px;left: 0px;width:200px;" id = "isExist"></div></td></tr>
<tr><td><span>设置登录密码</span></td></tr>
<tr><td></td><td>登陆密码</td><td><input id="p1" class = "p" type = "password" name= "password"></td><td style = "position:relative;top:0px;left:0px">
<div style = "position:absolute;top:25px;left: 0px;width:200px;" id = "div"></div></td>></tr>
<tr><td></td><td>密码确认</td><td><input id="p2" class = "p" type = "password"></td></tr>
</table><div align = "center"><input id="sub" type = "submit" value = "提交" style = "width:200px;height:30px;" class="btn"></div>
</form>
</body>
<script>
$(function(){	
	var flag = 0;
	var flag2 = 0;
	var p1;
	var p2;
	$("#sub").attr("disabled","true");
	$("#sub").prop("class","btn disabled");
	$(".p").keyup(function(){
	
		p1=$("#p1").val();
		p2=$("#p2").val();	
		if(len(p1)>4){
		if(p1==p2){
			flag = 1;
			$("#div").html("密码相同");
			if(flag2 == 1){
				$("#sub").attr("disabled",false);
				$("#sub").prop("class","btn btn-danger");	
			}
		}
		else{
			flag = 0;
			$("#div").html("密码不相同");
			$("#sub").attr("class","btn disabled");
			$("#sub").prop("disabled","true");
		}
		}
		else{
	
			$("#div").html("密码长度过短");
		}
	});
	$("#user").keyup(function(){
		
		var page = "isExist";
		var name = $(this).val();		
		var x = "isExist";
		$.post(
		page,
		{"name":name},
		function(result){
			
			if(result == "true"){
				flag2=1;
			$("#isExist").html("可以使用").css("color","green");	
			if(flag==1){
			$("#sub").attr("disabled",false);
			$("#sub").prop("class","btn btn-danger");
			}
			}
			else{
				flag2=0;
				$("#isExist").html("不可以使用");
			$("#isExist").css("color","red");
			$("#sub").attr("class","btn disabled");
			$("#sub").prop("disabled","true");
		}
		}
		);
	});
	function len(s) {
		var l = 0;
		var a = s.split("");
		for (var i=0;i<a.length;i++) {
		if (a[i].charCodeAt(0)<299) {
		l++;
		} else {
		l+=2;
		}
		}
		return l;
		}
	
});
</script>
</html>