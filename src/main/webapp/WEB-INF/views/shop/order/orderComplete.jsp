<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ include file="../../inc/top.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<style type="text/css">
	.cartListTbl{
		width:650px;		
	}
	.cartListTbl td{
		padding:10px;
	}
	.cartListTbl caption{
		visibility:hidden;
	}
	.cartListDiv1{
		width:650px;
		text-align:center;		
		margin:10px;
	}
	
	.divForm {
		width: 650px;
	}
	.divForm span{
		font-size:0.9em; 
	}		
	.divForm .sp1	{
		text-align: left;
	}
	.divForm .title{
		font-size:1.1em;
		font-weight:bold;	
	}
	.divForm .titleP, .titleP{
		margin:10px 0;	
		border: none;
	}
	
	.divForm p{
		overflow: hidden;
		border-bottom: 1px solid #c4d54987;
		margin: 5px 0;
		padding: 0 0 5px 0;
	}
</style>

<h2>주문 완료</h2>
<p style="font-size:13pt;font-weight:bold">
	주문과 결제가 정상적으로 완료되었습니다
	[주문번호 : ${order['ORDERNO'] }]
</p>
<br><br>
<p class="titleP">
	<img src='<c:url value="/resources/images/dot7.JPG"/>' align="absmiddle" />
	<span style="font-size:12pt;font-weight:bold">주문하신 상품</span>
</p>

<div>
<table  class="cartListTbl box2"
summary="주문 상품 목록에 관한 표로써, 상품명,가격, 수량, 금액 등의 정보를 제공합니다.">
	<caption>주문 상품 목록</caption>
	<colgroup>
		<col width="49%" />
		<col width="17%" />
		<col width="17%" />
		<col width="*" />		
	</colgroup>
	<thead>
		<tr>
			<th scope="col">상품명</th>
			<th scope="col">판매가격</th>
			<th scope="col">수량</th>
			<th scope="col">합계</th>			
		</tr>
	</thead>
	<tbody>
	<!-- 데이터가 있는 경우 -->
	<!-- 반복문 시작 -->				
	<c:if test="${!empty list }">
		<c:forEach var="map" items="${list }">
			<tr class="align_center">
				<td class="align_left">
					<img alt="${map['PRODUCTNAME'] }" src="<c:url value='/pd_images/${map["IMAGEURL"] }'/>"
						width="40px" align="absmiddle" >
					${map["PRODUCTNAME"] }
				</td>
				<td class="align_right"><fmt:formatNumber value="${map['SELLPRICE'] }"/>원</td>
				<td>${map["QUANTITY"] }</td>
				<td class="align_right">
					<fmt:formatNumber value="${map['SELLPRICE'] * map['QUANTITY']}"/>원
				</td>
			</tr>
		</c:forEach>
	</c:if>
	<!-- 반복문 끝 -->
	</tbody>
</table>
</div>
<br>
 
<div class="divForm">   
  	<p class="titleP">
		<img src='<c:url value="/resources/images/dot7.JPG"/>' align="absmiddle" />
    	<span class="title">배송 정보</span>
	</p>
	<p>
        <span class="sp1">보내시는 분 </span>
        <span >${order['ORDERNAME'] }</span>        
    </p>
    <p>
        <span class="sp1">받으시는 분 </span>
        <span>${order['CUSTOMERNAME'] }</span>
    </p>
    
    <p>
        <span class="sp1">배송지 주소</span>
        <span>${order['ADDRESS'] } ${order['ADDRESSDETAIL'] }</span>
    </p>
    <p>
        <span class="sp1">휴대폰 번호</span>
        <span>${order['HP'] }</span>
    </p>        
   
	<br>
	<p class="titleP">
		<img src='<c:url value="/resources/images/dot7.JPG"/>' align="absmiddle" />
		<span class="title">결제 정보</span>
	</p>	
    <p>
        <span class="sp1">결제금액</span>
        <span><fmt:formatNumber value="${order['TOTALPRICE'] }" pattern="#,###"/>원     </span>
    </p>
</div>

<%@ include file="../../inc/bottom.jsp"%>    
