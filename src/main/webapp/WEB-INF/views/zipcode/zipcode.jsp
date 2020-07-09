<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>우편번호 찾기</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/mainstyle.css'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/layout.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/mystyle.css'/>"/>
<script type="text/javascript" src="<c:url value='/resources/js/jquery-3.5.1.min.js'/>"></script>
<script type="text/javascript">
	$(function() {
		$("#dong").focus();
		
		$("form[name=frmZip]").submit(function() {
			if($("#dong").val() == ""){
				alert("검색할 지역명을 입력하세요");
				$("#dong").focus();
				return false;
			}
		});
		
	});
	
	function setZipcode(zipcode, address) {
		$(opener.document).find("#zipcode").val(zipcode);
		$(opener.document).find("input[name=address]").val(address);
		self.close()
	}
</script>
<style type="text/css">
.box2{
	width: 490px;
}

#divZip{
	margin: 10px 0;
}

label {
	font-size: 0.9em;
}
</style>
</head>
<body>
	<h1>우편번호 검색</h1>
	<form action="<c:url value='/zipcode/zipcode.do'/>" name="frmZip" method="post">
		<label for="dong">지역명</label>
		<input type="text" id="dong" name="dong" value="${param.dong }">
		<input type="submit" value="찾기">
	</form>
<c:if test="${!empty param.dong }">
	<div id="divZip">
		<table class="box2" summary="우편번호 검색 결과에 관한 표로써 우편번호, 주소에 대한 정보를 제공합니다.">
		<colgroup>
			<col style="width: 20%">
			<col style="width: *">
		</colgroup>
			<thead>
				<tr>
					<th scope="col">우편번호</th>
					<th scope="col">주소</th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="vo">
				<tr>
					<td>${vo.zipcode }</td>
					<td>
						<c:set value="${vo.sido} ${vo.gugun} ${vo.dong }" var="address" />
						<a href="#" onclick="setZipcode('${vo.zipcode }','${address}')">
							${address} 
							<c:if test="${empty vo.endbunji }">
								${vo.startbunji}
							</c:if>
							<c:if test="${!empty vo.endbunji }">
								( ${vo.startbunji} ~ ${vo.endbunji} )
							</c:if>
						</a>
					</td>
				</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty list }">
				<tr>
					<td colspan="2">검색결과가 없습니다.</td>
				</tr>
			</c:if>
			</tbody>
		</table>
	</div>
</c:if>
	<div style="text-align: center;">
	<%-- <%
	if(pagingVo != null){
		if(pagingVo.getFirstPage()>1){ %>
		<a href="zipcode.jsp?currentPage=<%=pagingVo.getFirstPage()-1%>&dong=<%=dong%>">
			<img src="../images/first.JPG" alt="이전 블럭으로 이동">
		</a>
	<%} %>
	
	<%for(int i=pagingVo.getFirstPage();i<=pagingVo.getLastPage();i++){ 
		if(i > pagingVo.getTotalPage()) break;
	%>
		<%if(i!=currentPage){%>
			<a href="zipcode.jsp?currentPage=<%=i%>&dong=<%=dong%>">[<%=i %>]</a>
		<%}else{ %>
			<span style="color:blue;font-weight:bold"><%=i %></span>
		<%}//if %>					
	<%}//for %>	
	
	<%if(pagingVo.getLastPage() < pagingVo.getTotalPage()){ %>
		<a href="zipcode.jsp?currentPage=<%=pagingVo.getLastPage()+1%>&dong=<%=dong%>">
			<img src="../images/last.JPG" alt="다음 블럭으로 이동">
		</a>
	<%} 
	}%> --%>
	
	</div>
</body>
</html>