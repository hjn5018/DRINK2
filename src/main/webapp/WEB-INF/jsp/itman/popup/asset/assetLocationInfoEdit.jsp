<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
<head>
    <%@ include file="/WEB-INF/jsp/itman/_inc/title.jsp" %>
    <title>자산 위치 변경</title>
</head>
<body>
<div id="popup">
    <form method="post" action="<c:url value='/popup/asset/ppProcess/assetLocationInfoEditProc.do'/>" id="form">
        <input type="hidden" name="assIdx" value="${assetVO.assIdx}" />
        <div class="pop_tit">
            <p class="title">자산 위치 변경</p>
        </div>
        <div class="pop_cont">
            <ul class="contEdit">
                <li>
                    <p class="tit">자산 위치</p>
                    <p class="cont">
                        <select id="locIdx" name="locIdx">
                            <c:forEach var="location" items="${locationList}">
                                <option value="${location.locIdx}" ${assetVO.locIdx == location.locIdx ? 'selected' : ''}>${location.locName}</option>
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