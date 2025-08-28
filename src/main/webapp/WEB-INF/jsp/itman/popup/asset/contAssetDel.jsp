<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<?php
	 include "../../_inc/dbconn.php";

	 /**
	  * 세션 체크
	  */
	 include "../../_inc/loginTest.php";
	 login_check();
 
	 $ASS_IDX= $_GET['ass_idx'];
?>
<!doctype html>
<html lang="ko">
 <head>
  <%@ include file="/WEB-INF/jsp/itman/_inc/title.jsp" %>
 </head>
<body>

	<div id="popup">
		<div class="pop_tit">
			<p class="title">자산을 삭제하시겠습니까?</p>
		</div>
		<div class="pop_cont">
			<p class="pop_btn"><a href="javascript:;window.close();" class="del">취소</a><a href="<c:url value='/ppProcess/contAssetDelProc.do?assIdx=${assetVO.assIdx }'/>" class="comp">삭제</a></p>
		</div>
	</div>

</body>
</html>
