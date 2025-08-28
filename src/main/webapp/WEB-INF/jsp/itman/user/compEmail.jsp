<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
 <head>
<%@ include file="/WEB-INF/jsp/itman/_inc/title.jsp" %>
 </head>
<body>
	<div id="contents">
		<div class="user_box join">
			<p class="tit">
				<a href="${pageContext.request.contextPath}/index.do">
          		<img src="${pageContext.request.contextPath}/itman_static/_img/itman_logo.png" alt="아이티맨"/>
        		</a></p>
			<p class="find_tit">
				고객님의<br/>
				정보와 일치하는<br/>
				이메일입니다.
			</p>
			<p class="find_comp">${memMail}</p>

			<p class="user_btn"><a href="login.do">로그인 하기</a></p>
			<p class="mam_btn"><a href="findPass.do">비밀번호 찾기</a></p>
		</div>
	</div>
	<%@ include file="/WEB-INF/jsp/itman/_inc/footer.jsp" %>
</body>
</html>
