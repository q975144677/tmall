<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<div style = "width:500px;height:500px;border: 1px solid black;margin:0 auto;font-size:30px;">
支付宝 收款码
</div>
<span>需付款：￥<span>${price }</span></span>
<a href="end_pay?oid=${oid }">
<input type = "button" value = "支付" class = "btn btn-success btn-lg"></a>
<a href="forecart"><input type = "button" value = "返回" class = "btn btn-info btn-lg"></a>
</body>
</html>