<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<div class="ArticleTopBtns">
	<div class="left_area"></div>

	<div class="right_area">
		<a href="/shop/product/productDetail.do?productNo=${prevNo }" class="btn btn-primary btn-lg active" role="button"
			aria-pressed="true">이전글</a> <a href="/shop/product/productDetail.do?productNo=${nextNo }"
			class="btn btn-primary btn-lg active" role="button"
			aria-pressed="true">다음글</a><a href="/index.do"
			class="btn btn-secondary btn-lg active" role="button"
			aria-pressed="true">목록</a>
	</div>
</div>