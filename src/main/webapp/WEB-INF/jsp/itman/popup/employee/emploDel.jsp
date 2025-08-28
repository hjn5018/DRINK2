<%-- /WEB-INF/jsp/itman/popup/employee/emploDel.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="ko">
<head>
    <c:set var="ctx" value="${pageContext.request.contextPath}" />
    <%@ include file="/WEB-INF/jsp/itman/_inc/title.jsp" %>
    <title>직원 삭제</title>
</head>
<body>

	<div id="popup">
		<div class="pop_tit">
			<p class="title">직원을 삭제하시겠습니까?</p>
		</div>
		<div class="pop_cont">
			<form:form id="form" method="post" action="${ctx}/popup/employee/emploDelProc.do" modelAttribute="emploVO">
			    <form:hidden path="empIdx" />
				<ul class="contEdit">
					<li>
						<p class="tit">비고</p>
						<p class="cont">
                            <%-- emploVO에 'remark' 필드가 있어야 합니다. --%>
                            <form:input path="remark" placeholder="삭제 사유를 입력하세요." />
                        </p>
					</li>
				</ul>
			</form:form>
			<p class="pop_btn">
                <a href="javascript:window.close();" class="del">취소</a>
                <a href="javascript:formSubmit();" class="comp">삭제</a>
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