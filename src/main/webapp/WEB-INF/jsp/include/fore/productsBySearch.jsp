<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="searchProducts">
     
    <c:forEach items="${ps}" var="p">
        <div class="productUnit" price="${p.promotePrice}">
            <a href="foreproduct?id=${p.id}">
                <img class="productImage" src="img/productImage/${p.firstProductImage.id}.jpg">
            </a>
            <span class="productPrice">¥<fmt:formatNumber type="number" value="${p.promotePrice}" minFractionDigits="2"/></span>
            <a class="productLink" href="foreproduct?id=${p.id}">
             ${p.name}
            </a>
             
            <a class="tmallLink" href="foreproduct?id=${p.id}">天猫专卖</a>
 
            <div class="productInfo">
                <span class="monthDeal ">月成交 <span class="productDealNumber">${p.saleCount}笔</span></span>
                <span class="productReview">评价<span class="productReviewNumber">${p.reviewCount}</span></span>
                <span class="wangwang"><img src="img/site/wangwang.png"></span>
            </div>
             
        </div>
    </c:forEach>
    <c:if test="${empty ps}">
        <div class="noMatch">没有满足条件的产品<div>
    </c:if>
     
        <div style="clear:both"></div>
</div>

</body>
</html>