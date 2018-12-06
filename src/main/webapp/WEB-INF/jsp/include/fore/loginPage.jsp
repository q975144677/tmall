<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="height:100%">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body style="height: 100%">
	<style>
.login div {
	padding: 14px
}

.login input {
	width: 90%;
	height: 40px
}
</style>
	<div
		style="position: relative; top: -100px; left: 0px; width: 100%; height: 100%; background-image: url(image/1f3355974fc4bc7525a4615178cbed29.jpg); background-repeat: no-repeat; background-size: 100% 100%;">
		<div class="login"
			style="position: relative; top: 200px; left: 0px; margin: 100px auto 0px 1000px;; background-color: white; width: 300px; height: 340px;">
			<form action="login_check" method="post">
				<div style="width: 200px; height: 30px;">${msg}</div>
				<div style="font-size: 15px; font-family: '黑体'">账户登陆</div>
				<div>
					 <span
						class=" glyphicon glyphicon-user"></span>
					<input type="text" name="name" placeholder="手机/会员名/邮箱">
				</div>
				<div>
				<span
						class=" glyphicon glyphicon-lock"></span>
					<input type="password" name="password" placeholder="密码">
				</div>
				<div>
					<a style="float: left;" href="#">忘记登陆密码</a><a style="float: right;"
						href="registPage">免费注册</a>
				</div>
				<div>
					<input type="submit" class="btn-danger" style = "width:100%" value="登录">
				</div>
			</form>
		</div>
	</div>
</body>
</html>