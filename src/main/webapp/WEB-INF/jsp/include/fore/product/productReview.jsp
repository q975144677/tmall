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
.detail label{
width:140px;
}
</style>
<script>
$(function(){

});
</script><div class="productReviewDiv" >
    <div class="productReviewTopPart">
        <a  href="#nowhere" class="productReviewTopPartSelectedLink">商品详情</a>
        <a  href="#nowhere" class="selected">累计评价 <span class="productReviewTopReviewLinkNumber">${p.reviewCount}</span> </a>
    </div>
     
    <div class="productReviewContentPart">
        <c:forEach items="${p.reviews}" var="r">
        <div class="productReviewItem">
         
            <div class="productReviewItemDesc">
                <div class="productReviewItemContent">
                    ${r.content }
                </div>
                <div class="productReviewItemDate"><fmt:formatDate value="${r.createDate}" pattern="yyyy-MM-dd"/></div>
            </div>
            <div class="productReviewItemUserInfo">
             	
                ${r.user.anonymousName}<span class="userInfoGrayPart">（匿名）</span>
            </div>
             
            <div style="clear:both"></div>
         
        </div>
        </c:forEach>
    </div>
 
</div>

</div>


</body>
</html>