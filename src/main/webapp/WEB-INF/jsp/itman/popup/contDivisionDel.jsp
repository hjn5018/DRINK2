<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
<head>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<jsp:include page="../_inc/title.jsp" /> 
<link rel="stylesheet" href="${ctx}/css/common.css" />
</head>
<body>
<div id="popup">
    <div class="pop_tit">
        <p class="title">삭제하시겠습니까?</p>
    </div>
    <div class="pop_cont">
        <form id="deleteForm" method="post" action='<c:url value="/popup/division/deleteDivisionProc.do"/>'>
            <!-- 컨트롤러에서 div_idx를 받는 경우 -->
            <input type="hidden" name="div_idx" value="${param.div_idx}" />

            <ul class="contEdit">
                <li>
                    <p class="tit">비고</p>
                    <p class="cont">
                        <textarea name="del_reason" style="width:100%;height:80px;"></textarea>
                    </p>
                </li>
            </ul>

            <p class="pop_btn">
                <a href="javascript:window.close();" class="del">취소</a>
                <a href="javascript:submitDeleteForm();" class="comp">삭제</a>
            </p>
        </form>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
function submitDeleteForm() {
    // 필요한 경우 사유 필수 체크
    // if (!$('textarea[name=del_reason]').val().trim()) {
    //     alert('삭제 사유를 입력해주세요.');
    //     return;
    // }
    $('#deleteForm').submit();
}
</script>
</body>
</html>
