<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="ko">
<head>
    <c:set var="ctx" value="${pageContext.request.contextPath}" />
    <%@ include file="/WEB-INF/jsp/itman/_inc/title.jsp" %>
    <title>직원 상태 추가 팝업</title>
</head>
<body>
    <div id="popup">
        <div class="pop_tit">
            <%-- Controller에서 직접 전달한 그룹 이름(groName)을 사용 --%>
            <p class="title"><c:out value="${groName}"/>&nbsp; 직원 상태 추가 팝업</p>
        </div>
        <div class="pop_cont">
            <%-- modelAttribute를 통해 VO와 폼을 연결 --%>
            <form:form id="empstateCateForm" method="post" action="${ctx}/popup/state/writeStateProc.do" modelAttribute="stateVO">
                
                <%-- 수정 1: groIdx를 hidden 필드로 추가하여 form 전송 시 함께 보내도록 함 --%>
                <form:hidden path="groIdx" />

                <ul class="contEdit">
                    <li>
                        <p class="tit">직원 상태명<span>*</span></p>
                        <p class="cont">
                            <form:input path="stName" placeholder="직원 상태명을 입력해주세요." cssErrorClass="error" />
                        </p>
                    </li>
                    <li>
                        <p class="tit">상태 코드번호<span>*</span></p>
                        <p class="cont">
                            <form:input path="stCode" placeholder="직원 상태 코드 번호를 입력해 주세요. ex)000" cssErrorClass="error" />
                        </p>
                    </li>
                    <li>
                        <p class="tit">비고</p>
                        <p class="cont">
                            <form:input path="slNote" />
                        </p>
                    </li>
                </ul>
                <p class="pop_btn">
                    <a href="javascript:window.close();" class="del">취소</a>
                    <a href="javascript:formSubmit();" class="comp">등록</a>
                </p>
            </form:form>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        function formSubmit() {
            var stName = $("#stName").val().trim();
            var stCode = $("#stCode").val().trim();
            if (stName === '' || stCode === '') {
                alert("필수 값을 입력해주세요!");
            } else {
                $("#empstateCateForm").submit();
            }
        }
    </script>
</body>
</html>