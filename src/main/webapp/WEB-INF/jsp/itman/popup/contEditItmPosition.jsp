<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="ko">
<head>
    <c:set var="ctx" value="${pageContext.request.contextPath}" />
    <%@ include file="/WEB-INF/jsp/itman/_inc/title.jsp" %>
    <title>직위 수정 팝업</title>
</head>
<body>
    <div id="popup">
        <div class="pop_tit">
            <p class="title">직위 수정 팝업</p>
        </div>
        <div class="pop_cont">
            <form:form id="itmPositionForm" method="post" action="${ctx}/popup/position/updatePositionProc.do" modelAttribute="positionVO">
                <form:hidden path="posIdx"/>
                <ul class="contEdit">
                    <li>
                        <p class="tit">직위명<span>*</span></p>
                        <p class="cont">
                            <form:input path="posName" placeholder="직위명을 입력해주세요." />
                        </p>
                    </li>
                    <li>
                        <p class="tit">직위 코드<span>*</span></p>
                        <p class="cont">
                            <form:input path="posCode" placeholder="직위 코드 번호를 입력해 주세요. ex)000" />
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
                    <a href="javascript:formSubmit();" class="comp">수정</a>
                </p>
            </form:form>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script>
        function formSubmit() {
            var posCode = $("#posCode").val().trim();
            var posName = $("#posName").val().trim();
            if (posCode === '' || posName === '') {
                alert("필수 값을 입력해주세요!");
            } else {
                $("#itmPositionForm").submit();
            }
        }
    </script>
</body>
</html>