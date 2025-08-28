<%-- /WEB-INF/jsp/itman/popup/employee/emploNumInfoEdit.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="ko">
<head>
    <c:set var="ctx" value="${pageContext.request.contextPath}" />
    <%@ include file="/WEB-INF/jsp/itman/_inc/title.jsp" %>
    <title>직원 사번 변경</title>
</head>
<body>
    <div id="popup">
        <div class="pop_tit">
            <p class="title">직원 사번 변경</p>
        </div>
        <div class="pop_cont">
            <form:form id="form" method="post" action="${ctx}/popup/employee/emploNumInfoEditProc.do" modelAttribute="emploVO">
                <form:hidden path="empIdx" />
                <ul class="contEdit">
                    <li>
                        <p class="tit">직원 사번</p>
                        <p class="cont">
                            <form:input path="empNum" placeholder="변경할 사번을 입력하세요." />
                        </p>
                    </li>
                </ul>
            </form:form>
            <p class="pop_btn">
                <a href="javascript:window.close();" class="del">취소</a>
                <a href="javascript:formSubmit();" class="comp">수정</a>
            </p>
        </div>
    </div>
<script>
    function formSubmit(){
        // 간단한 유효성 검사 추가 가능
        document.getElementById('form').submit();
    }
</script>
</body>
</html>