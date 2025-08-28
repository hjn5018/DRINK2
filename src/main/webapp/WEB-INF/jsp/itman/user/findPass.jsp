<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="ko">
<head>
<%@include file="../_inc/title.jsp"%>
</head>
<body>
<%--
findPass.do: 회원정보 입력 : (form) memName, memMail, userPhone123, mode ->
findPassProc.do: 기존 회원 여부 확인 : findMemIdx(), memName, memMail, memTel, mode ->
sendEmailProc.do: 이메일 전송 (session에 회원 정보 set): sendEmailCode(), (session) memName, memMail, memTel, mode, ecNum, regDate ->
verifyEmailCode.do: 인증번호 검증: verCode ->
changePassword.do: 변경할 비밀번호 입력: (form) memPw, memPwCh ->
changePasswordProc.do: 비밀번호 변경 (session의 회원 정보 remove)
 --%>
	<div id="contents">
		<div class="user_box join">
			<p class="tit">
				<a href="<c:url value='/index.do'/>">
					<img src="<c:url value='/itman_static/_img/itman_logo.png'/>" alt="아이티맨" />
				</a>
			</p>
			<p class="find_tit">
				비밀번호를<br />
				잊으셨나요?
				<span>회원가입시 입력하셨던 정보를 하단에 입력해주세요.</span>
			</p>

			<form action="findPassProc.do" name="frm" id="frm" method="post">
				<ul class="mem">
					<li>
						<p>사용자 이름</p>
						<div>
							<input type="text" id="memName" name="memName">
						</div>
					</li>
					<li>
						<p>이메일</p>
						<div>
							<input type="text" id="memMail" name="memMail">
						</div>
					</li>
					<li>
						<p>휴대폰 번호</p>
						<div class="tel">
							<p class="full">
								<select id="userPhone1" name="userPhone1">
									<option>010</option>
									<option>011</option>
									<option>012</option>
								</select><span>-</span><input type="tel" id="userPhone2" name="userPhone2"><span>-</span><input type="tel" id="userPhone3" name="userPhone3">
							</p>
						</div>
					</li>
				</ul>
					<input type="hidden" name="mode" value="비밀번호찾기">
				<p class="user_btn">
					<a href="#" onclick="fn_submit(); return false;">비밀번호 찾기</a>
				</p>
			</form>
		</div>
	</div>
	<%@ include file="/WEB-INF/jsp/itman/_inc/footer.jsp"%>
</body>
<script language="javascript">
	
	// 제출 전 유효성 검증
	function fn_submit() {
		
		//이름
		if ($("#memName").val().trim() == "") {
			alert("이름을 입력해주세요.");
			$("#memName").focus();
			return false;
		}
		
		// 이메일
		if ($("#memMail").val().trim() == "") {
			alert("이메일을 입력해주세요.");
			$("#memMail").focus();
			return false;
		}
		
		// 휴대폰 번호
		if ($("#userPhone2").val().trim() == "") {
			alert('휴대폰 번호를 입력해주세요.');
			$("#userPhone2").focus();
			return false;
		}
		
		if ($("#userPhone3").val().trim() == "") {
			alert('휴대폰 번호를 입력해주세요.');
			$("#userPhone3").focus();
			return false;
		}
		
		if ($("#userPhone2").val().trim().length !== 4) {
			alert("휴대폰 형식을 확인해주세요.");
			$("#userPhone2").focus();
			return false;
		}
		
		if ($("#userPhone3").val().trim().length !== 4) {
			alert("휴대폰 형식을 확인해주세요.");
			$("#userPhone3").focus();
			return false;
		}
		
		frm.submit();
	}
</script>

</html>
