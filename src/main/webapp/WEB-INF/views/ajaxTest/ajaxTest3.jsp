<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"
	integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
	crossorigin="anonymous"></script>
<title>ajaxTest/ajaxTest1.jsp</title>
<script type="text/javascript">
	$.ajaxSetup({
		error : function(jqXHR, exception) {
			if (jqXHR.status === 0) {
				alert('Not connect.\n Verify Network.');
			} else if (jqXHR.status == 400) {
				alert('Server understood the request, but request content was invalid. [400]');
			} else if (jqXHR.status == 401) {
				alert('Unauthorized access. [401]');
			} else if (jqXHR.status == 403) {
				alert('Forbidden resource can not be accessed. [403]');
			} else if (jqXHR.status == 404) {
				alert('Requested page not found. [404]');
			} else if (jqXHR.status == 500) {
				alert('Internal server error. [500]');
			} else if (jqXHR.status == 503) {
				alert('Service unavailable. [503]');
			} else if (exception === 'parsererror') {
				alert('Requested JSON parse failed. [Failed]');
			} else if (exception === 'timeout') {
				alert('Time out error. [Timeout]');
			} else if (exception === 'abort') {
				alert('Ajax request aborted. [Aborted]');
			} else {
				alert('Uncaught Error.n' + jqXHR.responseText);
			}
		}
	});

	$(function() {
		//{"no":102,"name":"김동율","content":"재미없어요"}
		$("#query").click(function() {
			$.ajax({
				url : '<c:url value="/ajaxTest/ajaxView.do" />',
				type : 'get',
				data : {no: $("#no").val()},
				dataType: "json",
				success : function(res) {
					var str = "번호 : " + res.no + "<br>";
					str += "이름 : " + res.name + "<br>";
					str += "내용 : " + res.content + "<br><br>";
					$("#result").html(str);
				}
			});
		});
	});
</script>
</head>
<body>
	<form id="frm1">
		번호 : <input type="text" id="no" size="7" /> <input type="button"
			id="query" value="조회"><br>
		<h2>메모를 남기세요</h2>
		이름 : <input type="text" id="name" name="name" /><br> 메모 : <input
			type="text" id="content" name="content" size="50" /><br> <input
			type="submit" value="입력">

		<h2>결과</h2>
		<div id="result" style="background: #eeeeee; width: 500px"></div>
	</form>
</body>
</html>