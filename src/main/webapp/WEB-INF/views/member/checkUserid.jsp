<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>중복확인</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/mainstyle.css'/>">
<script type="text/javascript" src="<c:url value='/resources/js/jquery-3.5.1.min.js'/>"></script>
<script type="text/javascript">
	$(function() {
		$("input[type=button]").click(function() {
			$(opener.document).find("#userid").val("${param.userid }");
			$(opener.document).find("#chkId").val("Y");
			$(opener.document).find("#userid").attr("readonly","readonly");
			self.close();
		});
		
		$("form[name=frmDup]").submit(function() {
			var id = $("#userid").val();
			if(id == ''){
				alert("아이디를 입력하세요");
				return false;
			}
		});
	});
</script>
</head>
<body>
	<h1>아이디 중복 검사</h1>
	<form action="<c:url value='/member/checkUserid.do'/>" method="post" name="frmDup">
		<label for="userid">회원ID</label>
		<input type="text" id="userid" value="${param.userid }" name="userid">
		<input type="submit" value="아이디 확인">
		<c:if test="${result == EXIST_ID }">
			<p>"${param.userid }"은 이미 등록된 아이디입니다. 다른 아이디를 입력하세요</p>
		</c:if>
		<c:if test="${result == NON_EXIST_ID }">
			<input type="button" value="사용하기">
			<p>"${param.userid }"은 사용 가능한 아이디입니다. [사용하기]버튼을 클릭하세요</p>
		</c:if>
	</form>
	
</body>
</html>