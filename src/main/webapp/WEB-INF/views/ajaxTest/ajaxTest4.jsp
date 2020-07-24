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
<title>ajaxTest/ajaxTest4.jsp</title>
<script type="text/javascript">
	$(function() {
		$("#frm1").submit(function() {
			var ser1 = $(this).serialize();
			//=> 입력 양식의 내용을 쿼리문자열로 만든다
			var ser2 = $(this).serializeArray();
			//=> 입력 양식의 내용을 객체로 만든다
			
			$('<p></p>').html(ser1).appendTo("#result");
			$('<p></p>').text(ser2).appendTo("#result");
			
			var str = $.param(ser2);
			$('<p></p>').text(str).appendTo("#result");
			return false;
		});
	});
</script>
</head>
<body>
	<h1>serialize, serializeArray 연습</h1>
	<form id="frm1">
		이름 : <input type="text" id="name" name="name"/><br>
		메모 : <input type="text" id="content" name="content" size="50" /><br>
		<input type="submit" value="입력"><br>
		<h2>결과</h2>
		<div id="result"></div>
	</form> 
</body>
</html>