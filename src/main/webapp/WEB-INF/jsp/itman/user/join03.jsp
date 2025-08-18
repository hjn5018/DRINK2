<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			<ul class="step">
				<li class="comp"><span>1</span></li>
				<li class="comp"><span>2</span></li>
				<li class="on"><span>3</span>가입 완료</li>

			</ul>
			<div class="comp_page">
				<span>${memName}</span>님 가입되었습니다!
			</div>
			<p class="user_btn">
				<a href="login.do">로그인하기</a>
			</p>
		</div>
	</div>
	<%@ include file="/WEB-INF/jsp/itman/_inc/footer.jsp"%>
</body>
</html>
