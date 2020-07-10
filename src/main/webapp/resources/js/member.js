/**
 * member.js
 */

var contextPath = "/herb";

$(function(){
	
	$("form[name=frm1]").submit(function() {
		
		var isNecessary = false;
		
		$(".necessary").each(function() {
			if($(this).val() == ''){
				alert($(this).prev().html() + "를 입력하셔야 합니다.");
				$(this).focus();
				isNecessary = true;
				event.preventDefault();
				return false;
			}
		});
		
		if(isNecessary){
			event.preventDefault();
			return false;
		}
		
		if($("#pwd").val() != $("#pwd2").val()){
			alert("비밀번호가 일치하지 않습니다.");
			$("#pwd2").focus();
			event.preventDefault();
			return false;
		}
		
		if($("#chkId").val() != 'Y'){
			alert("중복확인을 해야합니다.");
			$("#userid").focus();
			event.preventDefault();
			return false;
		}
		
	});
	
	$("#email2").change(function(){
		if($(this).val() == "etc"){
			$("#email3").css("visibility", "visible");
			$("#email3").val("");
			$("#email3").focus();
		}else{
			$("#email3").css("visibility","hidden");
		}
	});
	$('#btnZipcode').click(function(){
		window.open(contextPath + "/zipcode/zipcode.do?currentPage=" + "1", "zip", "height = 500, width = 600, resizable = yes, scrollbar= yes");
	});
	$('#btnChkId').click(function(){
		var userid = $('#userid').val();
		window.open(contextPath + "/member/checkUserid.do?userid=" + userid, "chk", "height = 300, width = 450, resizable = yes, scrollbar= yes")
	});


});

function validate_userid(id){
	var pattern = new RegExp(/^[a-zA-Z0-9_]+$/g);
	return pattern.test(id);
	/*
	정규식 /^[a-zA-z0-9_]+$/g
	a에서 z 사이의 문자, A~Z사이의 문자, 0에서 9사이의 숫자나 _로 시작하거나 끝나야 한다는 의미
	닫기 대괄호(])뒤의 + 기호는 이 패턴이 한번 또는 그 이상 반복된다는 의미
	*/
}

function validate_phone(tel){
	var pattern = new RegExp(/^[0-9]*$/g);
	return pattern.test(tel);
	/*
	정규식 /^[0-9]*$/g
	0에서 9사이의 숫자로 시작하거나 끝나야 한다는 의미(^는시작, $는 끝을 의미)
	닫기 대괄호(])뒤의 * 기호는 0번 이상 반복된다는 의미
	*/
}