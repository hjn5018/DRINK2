<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="ko">
<head>
    <%@ include file="/WEB-INF/jsp/itman/_inc/title.jsp" %>
    <title>가격 변경</title>
</head>
<body>
<div id="popup">
    <form method="post" action="<c:url value='/popup/asset/ppProcess/assetPriceInfoEditProc.do'/>" id="form">
        <input type="hidden" name="assIdx" value="${assetVO.assIdx}" />
        <div class="pop_tit">
            <p class="title">가격 변경</p>
        </div>
        <div class="pop_cont">
            <ul class="contEdit">
                <li>
                    <p class="tit">가격</p>
                    <p class="cont"><input type="text" id="price" name="price" value="${assetVO.price}"/></p>
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

document.getElementById('price').addEventListener('input', function (e) {
    let value = e.target.value;
    value = value.replace(/[^0-9]/g, '');
    value = value.replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    e.target.value = value;
});
</script>
</body>
</html>