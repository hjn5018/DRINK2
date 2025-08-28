<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="ko">
 <head>
<%@include file="../_inc/title.jsp"%> 
<!--   session_start(); -->
<!--   $group = $_SESSION['group']; -->
 </head>
<body id="mypage">
<%@ include file="/WEB-INF/jsp/itman/_inc/header.jsp" %>
	<div id="contents">
		<div class="user_box join">
			<h2><a href="mypage.do">계정 탈퇴</a></h2>
			<p class="find_tit mt40">
                ${memName}님의<br/>
				계정을 삭제<br/>
				하시겠습니까?
			</p>
			<ul class="mem">
            <form action="accDelProc.do" name="frm" id="frm" method="post">
				<li>
					<p>비밀번호</p>
					<div><input type="password" id="memPw" name="memPw"></div>
				</li>
			</ul>
            <br>
			<p class="red_txt">계정 탈퇴 시 복구가 불가능합니다!</p>
			<p class="user_btn"><a href="javascript:fn_submit();">회원탈퇴</a></p>
</form>
		</div>
	</div>
<%@ include file="/WEB-INF/jsp/itman/_inc/footer.jsp" %>
</body>
<script language="javascript">
function fn_submit(){
    frm.submit();
}
</script>
<c:if test="${not empty msg}">
	<script type="text/javascript">
		alert('${msg}');
	</script>
</c:if>
</html>

