<%-- /WEB-INF/jsp/itman/popup/employee/emploDivisionInfoEdit.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="ko">
<head>
    <c:set var="ctx" value="${pageContext.request.contextPath}" />
    <%@ include file="/WEB-INF/jsp/itman/_inc/title.jsp" %>
    <title>부서 변경</title>
</head>
<body>

    <div id="popup">
        <div class="pop_tit">
            <p class="title">부서 변경</p>
        </div>
        <div class="pop_cont">
            <form:form id="form" method="post" action="${ctx}/popup/employee/emploDivisionInfoEditProc.do" modelAttribute="emploVO">
                <form:hidden path="empIdx" />
                <ul class="contEdit">
                    <li>
                        <p class="tit">부서 분류</p>
                        <p class="cont">
                            <form:select path="divIdx" cssClass="selectBox">
                                <c:forEach var="division" items="${divisionList}">
                                    <form:option value="${division.divIdx}" label="${division.divName}"/>
                                </c:forEach>
                            </form:select>
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
        document.getElementById('form').submit();
    }
</script>
</body>
</html>