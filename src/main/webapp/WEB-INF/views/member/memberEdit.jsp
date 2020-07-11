<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/top.jsp" %>
<%@ include file="../login/loginCheck.jsp" %>
<script type="text/javascript" src="<c:url value='/resources/js/member.js'/>"></script>

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
<form name="frm1" method="post" action="<c:url value='/member/memberEdit.do'/>">
<fieldset>
	<legend>회원 정보 수정</legend>
    <div>        
        <label for="name">성명</label>
        <span>${memVo.name}</span>
    </div>
    <div>
        <label for="userid">회원ID</label>
        <span>${memVo.userid}</span>
    </div>
    <div>
        <label for="pwd">비밀번호</label>
        <input type="Password" name="pwd" id="pwd" class="necessary">
    </div>
    <div>
        <label for="pwd2">비밀번호 확인</label>
        <input type="Password" name="pwd2" id="pwd2">
    </div>
    <div>
        <label for="zipcode">주소</label>
        <input type="text" name="zipcode" id="zipcode" ReadOnly  
        	title="우편번호" class="width_80" value="${memVo.zipcode}">
        <input type="Button" value="우편번호 찾기" id="btnZipcode" title="새창열림"><br />
        <span class="sp1">&nbsp;</span>
        <input type="text" name="address" ReadOnly title="주소"  class="width_350"  
        	value="${memVo.address}"><br />
        <span class="sp1">&nbsp;</span>
        <input type="text" name="addressDetail" title="상세주소"  class="width_350"  
        	value="${memVo.addressDetail}">
    </div>
    <div>
        <label for="hp1">핸드폰</label>&nbsp;<select name="hp1" id="hp1" title="휴대폰 앞자리">
            <option value="010" <c:if test="${memVo.hp1 == '010' }">selected="selected"</c:if>>010</option>
            <option value="011" <c:if test="${memVo.hp1 == '011' }">selected="selected"</c:if>>011</option>
            <option value="016" <c:if test="${memVo.hp1 == '016' }">selected="selected"</c:if>>016</option>
            <option value="017" <c:if test="${memVo.hp1 == '017' }">selected="selected"</c:if>>017</option>
            <option value="018" <c:if test="${memVo.hp1 == '018' }">selected="selected"</c:if>>018</option>
            <option value="019" <c:if test="${memVo.hp1 == '019' }">selected="selected"</c:if>>019</option>
       	</select>
        -
        <input type="text" name="hp2" id="hp2" maxlength="4" title="휴대폰 가운데자리"
        	class="width_80" value="${memVo.hp2}">-
        <input type="text" name="hp3" id="hp3" maxlength="4" title="휴대폰 뒷자리"
        	class="width_80" value="${memVo.hp3}">
    </div>
    <div>
        <label for="email1">이메일 주소</label>
        <input type="text" name="email1"  id="email1" title="이메일주소 앞자리" value="${memVo.email1}">@
        <select name="email2" id="email2"  title="이메일주소 뒷자리">
            <%-- <option value="naver.com"<%if(email2.equals("naver.com")){%> selected="selected" <%} %>>naver.com</option>
            <option value="hanmail.net"<%if(email2.equals("hanmail.net")){%> selected="selected" <%} %>>hanmail.net</option>
            <option value="nate.com"<%if(email2.equals("nate.com")){%> selected="selected" <%} %>>nate.com</option>
            <option value="gmail.com"<%if(email2.equals("gmail.com")){%> selected="selected" <%} %>>gmail.com</option> --%>
           <%--  <%for(int i = 0; i < emailList.length; i++){ %>
	            <option value="<%=emailList[i] %>"
	            <%if(email2.equals(emailList[i])){%> 
	            selected="selected" <%} %>
	            ><%=emailList[i] %></option>
            <%} %> --%>
            <c:forEach var="list" items="${emailList }">
            	<option value="${list }" 
            		<c:if test="${list == memVo.email2 }">
            			selected="selected"
            		</c:if>
            	>${list }</option>
            </c:forEach>
            
            <option value="etc"
            		<c:if test="${isEtc == true}">
            			selected="selected"
            		</c:if>
            >직접입력</option>
        </select>
        <input type="text" name="email3" id="email3" title="직접입력인 경우 이메일주소 뒷자리"
        	
           		<c:if test="${isEtc == true}">
           			style="visibility:visible;" value="${memVo.email2 }" 
           		</c:if>
           		<c:if test="${isEtc == false}">
           			style="visibility:hidden;" 
           		</c:if>
           
        	>
    </div>
    <div class="center">
         <input type="submit" id="wr_submit" value="정보수정">
    </div>
</fieldset>

    <input type ="hidden" name="chkId" id="chkId" value="Y">
        
</form>
</div>
</article>

<%@ include file="../inc/bottom.jsp"%>