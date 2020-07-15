<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>productImage.jsp</title>
<style type="text/css">
.image img{
	width: 400px;
	margin: 0 auto;
}
</style>
</head>
<body>
	<p class='image'>
		<img alt="${param.productName }" src="<c:url value='/pd_images/${param.imageURL }' />">
	</p>
	<button onclick="self.close()">닫기</button>
</body>
</html>