<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="ko">
 <head>
<%@include file="../_inc/title.jsp"%>
<c:if test="${not empty msg}">
	<script>
   		alert('${msg}');
  	</script>
 </c:if>
<script>
  document.addEventListener('DOMContentLoaded', function(){
    var btn = document.getElementById('check');
    btn.addEventListener('click', function(e){
      e.preventDefault();
      document.getElementById('delForm').submit();
    });
  });
</script>

</head>
<body>
	<div id="popup">
		<div class="pop_tit">
			<p class="title">삭제하시겠습니까?</p>
		</div>
		<div class="pop_cont">
			<form method="POST" id="delForm" action="${pageContext.request.contextPath}/pp_process/listDeleteProc.do">
				<input type="hidden" id="idx" name="idx" value="${idx}">
				<input type="hidden" id="id" name="id" value="${id}">
				<ul class="contEdit">
					<li>
						<p class="tit">비고</p>
						<p class="cont"><input type="text" name="memo" id="memo"/></p>
					</li>
				</ul>		
				<!--<p class="pop_btn"><a href="javascript:;window.close();" class="del">취소</a><a href="./pp_process/listDeleteProc.do?id=<?=$method?>&target=<?=$target?>" class="comp">삭제</a></p> -->
				<p class="pop_btn"><a href="javascript:;window.close();" class="del">취소</a><a href="javascript:formSubmit();" id="check" class="comp">삭제</a></p>
			</form>
		</div>
	</div>
</body>
</html>

<!-- >>>>>>> 3037e4d20b7a85cb5465a63b58f8c2c9da2fe6d7 -->
