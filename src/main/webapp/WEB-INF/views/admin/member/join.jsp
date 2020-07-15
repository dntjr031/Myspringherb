<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../inc/adminTop.jsp" %>

<script type="text/javascript" src='<c:url value="/resources/js/member.js"/>'></script>
<script type="text/javascript" >
$(function() {
	$('#btChk').click(function(){
		var userid = $('#userid').val();
		window.open("<c:url value='/admin/member/checkUserid.do?userid=" + userid + "' />" , 
				"chk", "height = 300, width = 450, location = yes, scrollbar= yes");
	});
});
</script>
<style type="text/css">
	.width_80{
		width:80px;
	}
	.width_350{
		width:350px;
	}	
</style>
<article>
<div class="divForm">
<form name="frm1" method="post" action="<c:url value='/admin/member/join.do'/>">
<fieldset>
	<legend>관리자 등록</legend>
    <div>        
        <label for="name">관리자명</label>
        <input type="text" name="name" id="name" style="ime-mode:active" class="necessary">
    </div>
    <div>
        <label for="userid">관리자ID</label>
        <input type="text" name="userid" id="userid" class="necessary"
        		style="ime-mode:inactive">&nbsp;
        <input type="button" value="중복확인" id="btChk" title="새창열림">
    </div>
    <div>
        <label for="pwd">비밀번호</label>
        <input type="Password" name="pwd" id="pwd" class="necessary">
    </div>
    <div>
        <label for="pwd2">비밀번호 확인</label>
        <input type="Password" name="pwd2" id="pwd2" >
    </div>
    <div>
        <label for="level">레벨</label>&nbsp;
        <select name="authCode" id="level" class="necessary">
            <option value="">선택하세요</option>
            <c:forEach var="auth" items="${authList }">
            	<option value="${auth.authCode }">${auth.authName }</option>
            </c:forEach>
       	</select>
    </div>
    <div class="center">
         <input type="submit" id="wr_submit" value="등록">
    </div>
</fieldset>

    <input type ="text" name="chkId" id="chkId">
        
</form>
</div>
</article>

<%@ include file="../../inc/adminBottom.jsp"%>