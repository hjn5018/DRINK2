<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/itman/_inc/title.jsp" %>
</head>
<body>
	<div id="popup">
		<div class="pop_tit">
			<p class="title">삭제하시겠습니까?</p>
		</div>
		<div class="pop_cont">
			<ul class="contEdit">
				<li>
					<form action="./groupDelProc.do" name="frm" id="frm" method="post">
					<input type="hidden" name="groIdx" value="${groIdx}"/>
						<p class="tit">비밀번호</p>
						<p class="cont"><input id="memPw" name="memPw" type="password"/></p>
					</form>
				</li>
			</ul>
		<p class="pop_btn"><a href="javascript:window.close();" class="del">취소</a><a href="javascript:form_submit();" class="comp">삭제</a></p>
		</div>
	</div>

</body>
<script>
	function form_submit() {
		$("#frm").submit();
	  }
	</script>
<c:if test="${not empty msg}">
	<script type="text/javascript">
		alert("${msg}")
	</script>
</c:if>
</html>
