<%-- /WEB-INF/jsp/itman/ingroup/emploWrite.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!doctype html>
<html lang="ko">
<head>
    <%@ include file="/WEB-INF/jsp/itman/_inc/title.jsp" %>
    <title>ITMAN 직원 등록</title>
</head>
<body>
<%@ include file="/WEB-INF/jsp/itman/_inc/header.jsp" %>
<div id="contents">
    <div class="tit_search">
        <h2>직원 관리</h2>
    </div>
    
    <%-- modelAttribute="emploVO"와 VO의 필드명을 매칭합니다 --%>
    <form:form id="writeForm" name="writeForm" method="post" action="${ctx }/ingroup/emploWriteProc.do" modelAttribute="emploVO">
        <form:hidden path="groIdx" />
        <ul class="adminView Write">
            <li>
                <p class="tit">사번 <span>*</span></p>
                <%-- VO의 empNum 필드에 바인딩 --%>
                <p class="cont"><form:input path="empNum" placeholder="사번을 입력해주세요" /></p>
            </li>
            <li>
                <p class="tit">이름 <span>*</span></p>
                <%-- VO의 empName 필드에 바인딩 --%>
                <p class="cont"><form:input path="empName" placeholder="이름을 입력해주세요" /></p>
            </li>
            <li>
                <p class="tit">이메일</p>
                <%-- VO의 empMail 필드에 바인딩 --%>
                <p class="cont"><form:input path="empMail" placeholder="이메일을 입력해주세요" /></p>
            </li>
            <li>
                <p class="tit">휴대폰</p>
                <%-- VO의 empTel 필드에 바인딩 --%>
                <p class="cont"><form:input path="empTel" placeholder="휴대폰번호를 입력해주세요" /></p>
            </li>
            <li>
                <p class="tit">부서 <span>*</span></p>
                <p class="cont">
                    <%-- VO의 divIdx 필드에 바인딩 --%>
                    <form:select path="divIdx" cssClass="selectBox">
                        <form:option value="" label="부서를 선택해주세요." />
                        <c:forEach var="division" items="${divisionList}">
                            <form:option value="${division.divIdx}" label="${division.divName}" />
                        </c:forEach>
                    </form:select>
                </p>
                <p class="edit"><a href="javascript:openPopup('<c:url value='/popup/emploDivisionWrite.do?groIdx=${emploVO.groIdx}'/>');">부서 추가</a></p>
            </li>
            <li>
                <p class="tit">직위 <span>*</span></p>
                <p class="cont">
                    <%-- VO의 posIdx 필드에 바인딩 --%>
                    <form:select path="posIdx" cssClass="selectBox">
                        <form:option value="" label="직위를 선택해주세요." />
                        <c:forEach var="position" items="${positionList}">
                            <form:option value="${position.posIdx}" label="${position.posName}" />
                        </c:forEach>
                    </form:select>
                </p>
                <p class="edit"><a href="javascript:openPopup('<c:url value='/popup/emploPositionWrite.do?groIdx=${emploVO.groIdx}'/>');">직위 추가</a></p>
            </li>
            <li>
                <p class="tit">상태 <span>*</span></p>
                <p class="cont">
                    <%-- VO의 empStIdx 필드에 바인딩 --%>
                    <form:select path="empStIdx" cssClass="selectBox">
                        <form:option value="" label="상태를 선택해주세요." />
                        <c:forEach var="state" items="${stateList}">
                            <form:option value="${state.empStIdx}" label="${state.empStName}" />
                        </c:forEach>
                    </form:select>
                </p>
                <p class="edit"><a href="javascript:openPopup('<c:url value='/popup/emploStateWrite.do?groIdx=${emploVO.groIdx}'/>');">상태 추가</a></p>
            </li>
        </ul>
    </form:form>
    
    <p class="pagebtn">
        <a href="<c:url value='/ingroup/emploList.do?groIdx=${emploVO.groIdx}'/>" class="del">취소</a>
        <a href="javascript:fn_submit();" class="comp">등록</a>
    </p>
</div>
<%@ include file="/WEB-INF/jsp/itman/_inc/footer.jsp" %>
<script type="text/javascript">
// 폼 제출 유효성 검사 함수
function fn_submit() {
    if ($("#empNum").val().trim() === "") {
        alert("사번을 입력해주세요.");
        $("#empNum").focus();
        return;
    }
    if ($("#empName").val().trim() === "") {
        alert("이름을 입력해주세요.");
        $("#empName").focus();
        return;
    }
    if ($("#divIdx").val() === "") {
        alert("부서를 선택해주세요.");
        return;
    }
    if ($("#posIdx").val() === "") {
        alert("직위를 선택해주세요.");
        return;
    }
    if ($("#empStIdx").val() === "") {
        alert("상태를 선택해주세요.");
        return;
    }
    // 모든 유효성 검사 통과 시 폼 제출
    $("#writeForm").submit();
}

// 팝업창 열기 함수
function openPopup(url) {
    window.open(url, 'popupWindow', 'width=500, height=335, status=no, toolbar=no, scrollbars=no');
}
</script>
</body>
</html>