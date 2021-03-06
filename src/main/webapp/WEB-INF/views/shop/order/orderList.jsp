<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../inc/top.jsp" %>
<script type="text/javascript">
	
	function pageFunc(curPage) {
		$("input[name=currentPage]").val(curPage);
		$("form[name=frmPage]").submit();
	}
</script>
<!-- 페이징 처리를 위한 form 시작-->
<form name="frmPage" method="post" action="<c:url value='/shop/order/orderList.do' />">
	<input type="hidden" name="startDay" value="${dateSearchVO.startDay }">
	<input type="hidden" name="endDay" value="${dateSearchVO.endDay }">
	<input type="hidden" name="currentPage">	
</form>
<!-- 페이징 처리 form 끝 -->

<h2>주문 내역/ 배송현황</h2><br>
<p>${sessionScope.name }님의 주문내역입니다.</p>
<form name="frm1" method="post" 
	action="<c:url value='/shop/order/orderList.do' />" >
	<!-- 조회기간 include -->
	<%@include file="../../inc/dateTerm.jsp" %>
	<input type="submit" value="조회" >
</form>
<br>
<c:if test="${pagingInfo.totalRecord > 0 }">
	<p>
	${dateSearchVO.startDay } ~ ${dateSearchVO.endDay } 까지의 주문내역 총
	${pagingInfo.totalRecord } 건 입니다.
	</p>
</c:if>
<div class="divList">
<table class="box2"
	summary="주문 내역에 관한 표로써, 주문번호, 주문일자, 상품명/가격/수량,주문총금액,배송현황,취소/교환/반품에 대한 정보를 제공합니다.">
	<caption>주문 내역</caption>
	<colgroup>
		<col style="width:9%" />
		<col style="width:12%" />
		<col style="width:40%" />
		<col style="width:12%" />
		<col style="width:12%" />	
		<col style="width:14%" />
	</colgroup>
	<thead>
	  <tr>
	    <th scope="col">주문번호</th>
		<th scope="col">주문일자</th>
		<th scope="col">상품명/가격/수량</th>
		<th scope="col">주문총금액</th>
		<th scope="col">배송현황</th>
		<th scope="col">취소/교환/반품</th>
	  </tr>
	</thead> 
	<tbody>
		<c:if test="${empty list }">
			<tr class="align_center">
				<td colspan="6">주문 내역이 존재하지 않습니다.</td>
			</tr>
		</c:if>

		<!-- 반복 시작 -->
		<c:if test="${!empty list }">
			<c:forEach var="orderAllVo" items="${list }">
				<tr class="align_center">
					<td>${orderAllVo.orderVO.orderNo }</td>
					<td><fmt:formatDate value="${orderAllVo.orderVO.orderDate }" pattern="yyyy-MM-dd"/></td>
					<td class="align_left">
						<c:forEach var="map" items="${orderAllVo.orderDetailsList }">
							<p>
								<img alt="${map['PRODUCTNAME'] }" width="40" style="vertical-align: middle;"
								src="<c:url value='/pd_images/${map["IMAGEURL"] }' />" >
								${map['PRODUCTNAME'] }
								<b><fmt:formatNumber pattern="#,###" value="${map['SELLPRICE'] }"/> </b>원
								/ ${map['QUANTITY'] }개
							</p>
						</c:forEach>
					</td>
					<td class="align_right">
						<fmt:formatNumber value="${orderAllVo.orderVO.totalPrice }" pattern="#,###" />원
					</td>
					<td>${orderAllVo.orderVO.deliveryStatus }</td>
					<c:set value="${orderAllVo.orderVO.deliveryStatus }" var="status" />
					<td>
						<c:if test="${status == '입금확인중' || status == '결제완료'}">
							<a href="#">[취소]</a>
						</c:if>
						<c:if test="${status == '배송완료'}">
							<a href="#">[교환/반품]</a>
						</c:if>
						<c:if test="${status == '배송중'}">
							<a href="#">[배송현황]</a>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</c:if>
		<!-- 반복 끝 -->
</tbody>
</table>
</div>

<div class="divPage">		
	<!-- 페이지 번호 추가 -->		
	<c:if test="${pagingInfo.firstPage>1 }">
		<a href="#" onclick="pageFunc(${pagingInfo.firstPage-1})">			
		    <img src='<c:url value="/images/first.JPG" />'  border="0">	</a>
	</c:if>
					
	<!-- [1][2][3][4][5][6][7][8][9][10] -->
	<c:forEach var="i" begin="${pagingInfo.firstPage }" 
	end="${pagingInfo.lastPage }">
		<c:if test="${i==pagingInfo.currentPage }">
			<span style="color:blue;font-weight:bold">${i }</span>
		</c:if>
		<c:if test="${i!=pagingInfo.currentPage }">						
			<a href="#" onclick="pageFunc(${i})">
				[${i }]
			</a>
		</c:if>
	</c:forEach>
		
	<c:if test="${pagingInfo.lastPage<pagingInfo.totalPage }">
		<a href="#" onclick="pageFunc(${pagingInfo.lastPage+1})">			
			<img src="<c:url value="/images/last.JPG" />" border="0">
		</a>
	</c:if>
	<!--  페이지 번호 끝 -->
</div>




<%@ include file="../../inc/bottom.jsp" %>