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
<title>ajaxTest/ajaxTest2.jsp</title>
<script type="text/javascript">
	$.ajaxSetup({
		error: function(jqXHR, exception) {
	        if (jqXHR.status === 0) {
	            alert('Not connect.\n Verify Network.');
	        } 
	        else if (jqXHR.status == 400) {
	            alert('Server understood the request, but request content was invalid. [400]');
	        } 
	        else if (jqXHR.status == 401) {
	            alert('Unauthorized access. [401]');
	        } 
	        else if (jqXHR.status == 403) {
	            alert('Forbidden resource can not be accessed. [403]');
	        } 
	        else if (jqXHR.status == 404) {
	            alert('Requested page not found. [404]');
	        } 
	        else if (jqXHR.status == 500) {
	            alert('Internal server error. [500]');
	        } 
	        else if (jqXHR.status == 503) {
	            alert('Service unavailable. [503]');
	        } 
	        else if (exception === 'parsererror') {
	            alert('Requested JSON parse failed. [Failed]');
	        } 
	        else if (exception === 'timeout') {
	            alert('Time out error. [Timeout]');
	        } 
	        else if (exception === 'abort') {
	            alert('Ajax request aborted. [Aborted]');
	        } 
	        else {
	            alert('Uncaught Error.n' + jqXHR.responseText);
	        }
		}
	});
	$(function() {
		//[{"no":1,"name":"홍길동","content":"내용1"},{"no":2,"name":"김길동","content":"내용2"},{"no":3,"name":"이길동","content":"내용3"}]
		$("#bt1").click(function() {
			$.ajax({
				url: '<c:url value="/ajaxTest/ajaxList.do" />',
				type: 'get',
				dataType: "json",
				success: function(res) {
					//alert(res.length);
					if(res.length > 0){
						var str = "";
						$.each(res, function(idx, item) {
							str += "번호 : " + item.no + "<br>";
							str += "이름 : " + item.name + "<br>";
							str += "내용 : " + item.content + "<br><br>";
						});					
						
						$("#info").html(str);
					}
				}
			});
		});
		
		$("#bt2").click(function() {
			//{"no":5,"name":"김동율","content":"재미없어요"}
			$.ajax({
				url: "<c:url value='/ajaxTest/ajaxDetail.do' />",
				dataType: "json",
				type: "get",
				data: "no=5",
				success: function(res) {
					var str = "번호 - " + res.no + "<br>";
					str += "이름 - " + res.name + "<br>";
					str += "내용 - " + res.content + "<br><br>";
 					$("#info").append(str);
				}
			});
		});
	});
</script>
</head>
<body>
	<h1>ajax 연습2</h1>
	<button id="bt1" type="button">List</button>
	<button id="bt2" type="button">VO</button>
	<hr>
	<div id="info"></div>
</body>
</html>