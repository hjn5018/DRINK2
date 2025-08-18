<%-- /WEB-INF/jsp/itman/popup/employee/emploDivisionWrite.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="ko">
<head>
    <%@ include file="/WEB-INF/jsp/itman/_inc/title.jsp" %>
    <title>부서 등록</title>
    <link rel="stylesheet" href="<c:url value='/css/common.css'/>" />
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
<div id="popup">
    <div class="pop_tit">
        <p class="title">정보를 입력해주세요</p>
    </div>
    <div class="pop_cont">
        <form:form id="frm" method="post" modelAttribute="divisionVO" action="${pageContext.request.contextPath}/popup/employee/emploDivisionWriteProc.do">
            <form:hidden path="groIdx" />
            <ul class="contEdit">
                <li>
                    <p class="tit">부서명 <span>*</span></p>
                    <p class="cont">
                        <form:input path="divName" id="divName" placeholder="추가할 부서명을 입력해 주세요." cssClass="inputText"/>
                    </p>
                </li>
                <li>
                    <p class="tit">코드번호 <span>*</span></p>
                    <p class="cont">
                        <form:input path="divCode" id="divCode" placeholder="추가할 부서 코드번호를 입력해 주세요." cssClass="inputText"/>
                    </p>
                </li>
                <li>
                    <p class="tit">사용여부</p>
                    <p class="cont">
                        <form:radiobutton path="divYn" value="Y" id="yes" /><label for="yes">사용</label>
                        <form:radiobutton path="divYn" value="N" id="no" /><label for="no">사용안함</label>
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

<c:if test="${not empty msg}">
    <script type="text/javascript">
        alert("${msg}")
    </script>
</c:if>

<script type="text/javascript">
    function formSubmit() {
        if (document.getElementById('divName').value.trim() === '') {
            alert('부서명을 입력해주세요.');
            return;
        }
        if (document.getElementById('divCode').value.trim() === '') {
            alert('코드번호를 입력해주세요.');
            return;
        }
        document.getElementById('frm').submit();
    }
</script>
</body>
</html>