<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="ko">
<head>
  <%@ include file="/WEB-INF/jsp/itman/_inc/title.jsp" %>
  <title>로그인</title>
</head>
<body>
<%-- 
로그인 진행 순서
login.do ->
loginProc.do ->
index.do
 --%>
  <div id="contents">
    <div class="user_box">
      <p class="tit">
        <a href="<c:url value='/index.do'/>">
          <img src="<c:url value='/itman_static/_img/itman_logo.png'/>" alt="아이티맨"/>
        </a>
      </p>
      
      <form action="<c:url value='/user/loginProc.do'/>" name="frm" id="frm" method="post">
        <ul class="mem">
          <li>
            <p>이메일</p>
            <div><input type="text" id="memMail" name="memMail"/></div>
          </li>
          <li>
            <p>비밀번호</p>
            <div><input type="password" id="memPw" name="memPw"/></div>
          </li>
        </ul>
        <p class="user_btn">
          <a href="#" onclick="frm.submit(); return false;">로그인</a>
        </p>
      </form>

      <p class="mam_btn">
        <a href="<c:url value='/user/join01.do'/>">회원가입</a>
        <a href="<c:url value='/user/findEmail.do'/>">이메일 찾기</a>
        <a href="<c:url value='/user/findPass.do'/>">비밀번호 찾기</a>
      </p>
    </div>
  </div>

<%@ include file="/WEB-INF/jsp/itman/_inc/footer.jsp" %>

</body>
</html>
