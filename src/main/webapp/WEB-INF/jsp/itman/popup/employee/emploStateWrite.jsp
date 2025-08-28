<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!doctype html>
<html lang="ko">
<head>
    <%@ include file="/WEB-INF/jsp/itman/_inc/title.jsp" %>
    <title>직위 등록</title>
    <link rel="stylesheet" href="<c:url value='/css/common.css'/>" />
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
<div id="popup">
  <div class="pop_tit">
    <p class="title">정보를 입력해주세요</p>
  </div>
  <div class="pop_cont">
    <form:form 
      modelAttribute="stateVO" 
      id="frm" 
      method="post" 
      action="${pageContext.request.contextPath}/popup/employee/emploStateWriteProc.do">
      
      <form:hidden path="groIdx" />
      
      <ul class="contEdit">
        <li>
          <p class="tit">상태이름 <span>*</span></p>
          <p class="cont">
            <form:input path="empStName" id="empStName" cssClass="inputText" placeholder="직원 상태명을 입력해 주세요." />
          </p>
        </li>
        <li>
          <p class="tit">코드번호 <span>*</span></p>
          <p class="cont">
            <form:input path="empStCode" id="empStCode" cssClass="inputText" placeholder="직원 상태 코드번호를 입력해 주세요." />
          </p>
        </li>
      </ul>
      
      <p class="pop_btn">
        <a href="javascript:window.close();" class="del">취소</a>
        <a href="javascript:formSubmit();" class="comp">저장</a>
      </p>
    </form:form>
  </div>
</div>

<script>
function formSubmit() {
  if (document.getElementById('empStName').value.trim() === '') {
    alert('상태이름을 입력해주세요.');
    return;
  }
  if (document.getElementById('empStCode').value.trim() === '') {
    alert('코드번호를 입력해주세요.');
    return;
  }
  document.getElementById('frm').submit();
}
</script>
</body>
</html>
