<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
 <head>
<%@include file="../_inc/title.jsp"%>
 </head>
<body id="group">
<%@ include file="/WEB-INF/jsp/itman/_inc/header.jsp" %>
	<div id="contents">
		<div class="mypage_box">
			<h2><a href="mypage.do">비밀번호 변경</a></h2>
			<ul class="myinfo pass">
			<form action="mypage/changePasswordProc.do" name="frm" id="frm" method="post">
				<li>
					<p>현재 비밀번호</p>
					<div><input id="beforePw" name="beforePw" type="password"/></div>
				</li>
				<li>
					<p>변경 비밀번호</p>
					<div><input id="memPw" name="memPw" type="password"/></div>
				</li>
				<li>
					<p>비밀번호 확인</p>
					<div><input id="memPwCh" name="memPwCh "type="password"/></div>
				</li>
			</ul>

			<p class="pagebtn"><a href="javascript:form_submit();" class="comp">변경</a></p>
		</div>
	</div>
<%@ include file="/WEB-INF/jsp/itman/_inc/footer.jsp" %>
</body>
<script>
	function form_submit(){
			if($("#beforePw").val().trim() == ""){
			alert("현재 비밀번호를 입력해주세요.");
			$("#beforePw").focus();
			return false;
			}
		    // userpw_ch값이 비어있으면 실행.
			if($("#memPw").val().trim() == ""){
				alert("변경 비밀번호를 입력해주세요.");
				$("#memPw").focus();
				return false;
			}
			// userpw_ch값이 비어있으면 실행.
			if($("#memPwCh").val().trim() == ""){
				alert("비밀번호 확인을 입력해주세요.");
				$("#memPwCh").focus();
				return false;
			}
			
			
		    // userpw값이 6자 이상 20자 이하를 벗어나면 실행.
			if($("#memPw").val().trim().length < 6 || $("#memPw").val().trim().length > 20){
				alert("비밀번호는 6자 이상 20자 이하로 입력해주세요.");
				$("#memPw").focus();
				return false;
			}
		    
			if($("#memPw").val().trim() != $("#memPwCh").val().trim()) {
		        alert('비밀번호가 다릅니다. 다시 입력해주세요.');
		        $("#memPw").focus();
		        return false;
		    }
		    document.frm.submit();
			
		
	}
	</script>
	<c:if test="${!empty msg}">
	<script>
		alert("${msg}");
	</script>
	</c:if>
</html>

