<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
 <head>
<%@ include file="/WEB-INF/jsp/itman/_inc/title.jsp" %>
</head>
<body>
	<div id="popup">
		<div class="pop_tit">
			<p class="title">자산 상태 수정 팝업</p>
		</div>
		<div class="pop_cont">
			<form method="post" id="itmStatForm" action="./pp_process/updateStatProc.do">
			<input type="hidden" name="staIdx" value="${row.staIdx}">
			<ul class="contEdit">
				<li>
					<p class="tit">자산 상태명<span>*</span></p>
					<p class="cont"><input type="text" id="staName" name="staName" placeholder="자산 상태명을 입력해주세요." value="${row.staName}"></p>
				</li>
				<li>
					<p class="tit">상태코드번호<span>*</span></p>
					<p class="cont"><input type="text" id="staCode" name="staCode" placeholder="자산 상태 코드 번호를 입력해 주세요. ex)000" value="${row.staCode}"></p>
				</li>
			
				<!-- 비고란 -->
				<li>
					<p class="tit">비고</p>
					<p class="cont"><input type="text" name="staNote" value="${row.staNote}"/></p>
				</li>
			</ul>
			<p class="pop_btn"><a href="javascript:;window.close();" class="del">취소</a><a href="javascript:formSubmit();" class="comp">수정</a></p>
		</div>
	</div>
<script>
    
	function formSubmit(){
			$staName_empty = $("#staName").val().trim();
			$staCode_empty = $("#staCode").val().trim();

			if(!$staName_empty || !$staCode_empty){
				alert("필수 값을 입력해주세요!");
			}else{
				$("#itmStatForm").submit();
			}
		}
</script>
</body>
</html>