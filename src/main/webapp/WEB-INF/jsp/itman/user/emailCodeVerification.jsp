<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="ko">
<head>

<%@ include file="/WEB-INF/jsp/itman/_inc/title.jsp"%>

<!-- 회원가입에서 접속한 경우 nextPage-->
<c:set var="nextPage" value="${pageContext.request.contextPath}/user/joinProc.do" />

<!-- 비밀번호찾기에서 접속한 경우 nextPage-->
<c:if test="${mode eq '비밀번호찾기'}">
	<c:set var="nextPage"
		value="${pageContext.request.contextPath}/user/changePassword.do" />
</c:if>

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
				이메일로<br/>
				전송된 인증 번호를<br/>
				입력해주세요.
			</p>

			<form action="${nextPage}" method="post" id="frm">
				<ul class="mem">
					<li>
						<div>
							<input type="text" id="verCode" name="verCode" placeholder="인증번호를 입력해 주세요.">
						</div>
					</li>
				</ul>
				
				<p class="user_btn">
					<a href="#" onclick="frm.submit(); return false;">다음</a>
				</p>

			</form>
		</div>
	</div>
	
	<%@ include file="/WEB-INF/jsp/itman/_inc/footer.jsp"%>


</body>
</html>