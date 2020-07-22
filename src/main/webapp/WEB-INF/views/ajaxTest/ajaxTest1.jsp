<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"
	integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
	crossorigin="anonymous"></script>
<title>ajaxTest/ajaxTest1.jsp</title>
<script type="text/javascript">
	$(function() {
		$.ajax({
			url: '<c:url value="/ajaxTest/search.do" />',
			type: 'get',
			/* data: {
				keyword:"s",
				id:"hong"
			}, */
			data : "keyword=s&id=kim",
			success: function(res) {
				$("#result").html(res);
			},
			error: function(xhr, status, error) {
				alert(status+", " + error);
			}
		});
	});
</script>
</head>
<body>
	<h1>ajax 연습1</h1>
	<div id="result"></div>
</body>
</html>