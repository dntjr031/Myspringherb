<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#startDay").datepicker({
			dateFormat:'yy-mm-dd',
			changYear:true,
			dayNamesMin:['일','월','화','수','목','금','토'],
			monthNames:['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']
		});
		$("#endDay").datepicker({
			dateFormat:'yy-mm-dd',
			changYear:true,
			dayNamesMin:['일','월','화','수','목','금','토'],
			monthNames:['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']
		});
		
		$(".btWeek").click(function() {
			$.setDate(7, 'd'); // 일주일 전
		});
		$(".btMonth1").click(function() {
			$.setDate(1, 'm'); // 1개월 전
		});
		$(".btMonth3").click(function() {
			$.setDate(3, 'm'); // 3개월 전
		});
	});
	
	$.setDate = function(term, type) {
		var arr = $("#endDay").val().split("-");
		var endDay = new Date(arr[0],arr[1]-1,arr[2]);
		if(type=='d'){
			endDay.setDate(endDay.getDate()-term);
		}else if(type=='m'){
			endDay.setMonth(endDay.getMonth()-term);
		}
		
		$("#startDay").val($.findDate(endDay));
	}
	
	$.findDate = function (date) {
		return date.getFullYear() + "-" + $.formatDate(date.getMonth()+1) + "-" + $.formatDate(date.getDate());
	}
	
	$.formatDate = function(d) {
		if(d<10){
			return "0"+d;
		}else{
			return d;
		}
	}
</script>
<!-- 조회기간 include -->
	조회기간
	<input type="button" value="1주일" class="btWeek">
	<input type="button" value="1개월" class="btMonth1">
	<input type="button" value="3개월" class="btMonth3">
		
	<input type="text" name="startDay" id="startDay" value="${dateSearchVO.startDay }"> 
	~ 
	<input type="text" name="endDay" id="endDay" value="${dateSearchVO.endDay }">