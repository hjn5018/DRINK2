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
      id="frm" 
      method="post" 
      modelAttribute="positionVO" 
      action="${pageContext.request.contextPath}/popup/employee/emploPositionWriteProc.do">

      <form:hidden path="groIdx" />

      <ul class="contEdit">
        <li>
          <p class="tit">직위이름 <span>*</span></p>
          <p class="cont">
            <form:input path="posName" id="posName" cssClass="inputText" placeholder="직위명을 입력해 주세요." />
          </p>
        </li>
        <li>
          <p class="tit">코드번호 <span>*</span></p>
          <p class="cont">
            <form:input path="posCode" id="posCode" cssClass="inputText" placeholder="직위 코드번호를 입력해 주세요." />
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
  if ($('#posName').val().trim() === '') {
    alert('직위이름을 입력해주세요.');
    return;
  }
  if ($('#posCode').val().trim() === '') {
    alert('코드번호를 입력해주세요.');
    return;
  }
  $('#frm').submit();
}
</script>

</body>
</html>
