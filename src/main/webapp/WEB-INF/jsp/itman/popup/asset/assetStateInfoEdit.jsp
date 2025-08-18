<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
<head>
    <%@ include file="/WEB-INF/jsp/itman/_inc/title.jsp" %>
    <title>자산 상태 변경</title>
</head>
<body>
<div id="popup">
    <form method="post" action="<c:url value='/popup/asset/ppProcess/assetStateInfoEditProc.do'/>" id="form">
        <input type="hidden" name="assIdx" value="${assetVO.assIdx}" />
        <div class="pop_tit">
            <p class="title">자산 상태 변경</p>
        </div>
        <div class="pop_cont">
            <ul class="contEdit">
                <li>
                    <p class="tit">자산 상태</p>
                    <p class="cont">
                        <select id="staIdx" name="staIdx">
                            <c:forEach var="state" items="${stateList}">
                                <option value="${state.staIdx}" ${assetVO.staIdx == state.staIdx ? 'selected' : ''}>${state.staName}</option>
                            </c:forEach>
                        </select>
                    </p>
                </li>
                <li>
                    <p class="tit">비고</p>
                    <p class="cont"><input name="alNote" type="text" /></p>
                </li>
            </ul>
            <p class="pop_btn"><a href="javascript:window.close();" class="del">취소</a><a href="javascript:formSubmit()" class="comp">수정</a></p>
        </div>
    </form>
</div>
<script>
function formSubmit(){
    document.getElementById('form').submit();
}
</script>
</body>
</html>