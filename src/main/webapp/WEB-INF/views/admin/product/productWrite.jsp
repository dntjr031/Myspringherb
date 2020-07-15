<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../inc/adminTop.jsp"%>
<script type="text/javascript">
	$(function() {
		
		$("form[name=frm1]").submit(function() {
			
			var isNecessary = false;
	
			$(".necessary").each(function() {
				if ($(this).val() == '') {
					alert($(this).prev().html() + "를 입력하셔야 합니다.");
					$(this).focus();
					isNecessary = true;
					event.preventDefault();
					return false;
				}
			});
	
			if (isNecessary) {
				event.preventDefault();
				return false;
			}
			
			if($("#sellPrice").val().length < 1){
				$("#sellPrice").val(0)
			}
			
			if($("#mileage").val().length < 1){
				$("#mileage").val(0)
			}
		});
		
	});
</script>

<div class="divForm">
	<form name="frm1" method="post" action="" enctype="multipart/form-data"
		onsubmit="return validate(this)">
		<fieldset>
			<legend>상품 등록</legend>
			<div>
				<label for="productName">상품명</label> <input type="text"
					name="productName" id="productName" size="50" class="necessary">
			</div>
			<div>
				<label for="categoryNo">카테고리</label> <select name="categoryNo"
					id="categoryNo" title="카테고리" class="necessary">
					<option value="">선택하세요</option>
					<!-- 반복문 시작 -->
					<c:forEach var="cat" items="${catList}">
						<option value="${cat.categoryNo }">${cat.categoryName }</option>
					</c:forEach>
					<!-- 반복문 끝 -->
				</select>
			</div>
			<div>
				<label for="imageUpload">상품이미지</label> <input type="file"
					name="imageUpload" id="imageUpload" class="necessary">
			</div>
			<div>
				<label for="sellPrice">가격</label> <input type="text"
					name="sellPrice" id="sellPrice">
			</div>
			<div>
				<label for="explains">요약설명</label>
				<textarea cols="84" rows="5" name="explains" id="explains"></textarea>
			</div>
			<div>
				<label for="description">상세설명</label>
				<textarea cols="84" rows="5" name="description" id="description"></textarea>
			</div>
			<div>
				<label for="mileage">적립금</label> <input type="text" name="mileage"
					id="mileage">
			</div>
			<div>
				<label for="company">제조회사</label> <input type="text" name="company"
					id="company">
			</div>
			<div>
				<p class="center">
					<input type="submit" value="등록"> <input type="reset"
						value="취소">
			</div>
		</fieldset>
	</form>
</div>

<%@ include file="../../inc/adminBottom.jsp"%>
