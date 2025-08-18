<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/itman/_inc/title.jsp"%>
</head>

<body>
	<div id="contents">
		<div class="user_box join">
			<p class="tit">
				<a href="<c:url value='/index.do'/>">
					<img src="<c:url value='/itman_static/_img/itman_logo.png'/>" alt="아이티맨" />
				</a>
			</p>
			<p class="find_tit">
				재설정할<br />
				비밀번호를<br />
				입력해주세요.
			</p>
			<form action="<c:url value='/user/changePasswordProc.do'/>" name="frm" id="frm" method="post">
				<ul class="mem">
					<li>
						<p>비밀번호</p>
						<div>
							<input type="password" id="memPw" name="memPw">
						</div>
					</li>
					<li>
						<p>비밀번호 확인</p>
						<div>
							<input type="password" id="memPwCh" name="memPwCh">
						</div>
					</li>
				</ul>

				<p class="user_btn">
					<a href="#" onclick="fn_submit(); return false;">비밀번호 변경</a>
				</p>
			</form>
		</div>
	</div>
	
	<%@ include file="/WEB-INF/jsp/itman/_inc/footer.jsp"%>
	
	<script>
	
	// 제출 전 유효성 검사 
	function fn_submit() {
		
		if ($("#memPw").val().trim() == "") {
			alert("비밀번호를 입력해주세요.");
			$("#memPw").focus();
			return false;
		}
		
		if ($("#memPwCh").val().trim() == "") {
			alert("비밀번호를 입력해주세요.");
			$("#memPwCh").focus();
			return false;
		}
		
		if ($("#memPw").val().trim().length < 6
				|| $("#memPw").val().trim().length > 20) {
			alert("비밀번호는 6자 이상 20자 이하로 입력해주세요.");
			$("#memPw").focus();
			return false;
		}
		
		if ($("#memPw").val().trim() != $("#memPwCh").val().trim()) {
			alert('비밀번호가 다릅니다. 다시 입력해주세요.');
			$("#memPw").focus();
			return false;
		}
		
		document.frm.submit();
	}
	</script>
</body>
</html>
