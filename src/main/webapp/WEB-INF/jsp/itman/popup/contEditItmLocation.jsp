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
			<p class="title">자산 위치 수정 팝업</p>
		</div>
		<div class="pop_cont">
			<form method="post" id="itmLocationForm" action="./pp_process/updateItmLocationProc.do">
			<input type="hidden" name="locIdx" value="${row.locIdx}" >
			<ul class="contEdit">
				<li>
					<p class="tit">자산 위치명<span>*</span></p>
					<p class="cont"><input type="text" id="locName" name="locName" placeholder="자산 위치 명을 입력해주세요." value="${row.locName}"></p>
				</li>
				<li>
					<p class="tit">자산 위치 코드번호<span>*</span></p>
					<p class="cont"><input type="text" id="locCode" name="locCode" placeholder="자산 위치 코드 번호를 입력해 주세요. ex)000" value="${row.locCode}"></p>
				</li>
				
				<!-- 비고란 -->
				<li>
					<p class="tit">비고</p>
					<p class="cont"><input type="text" name="slNote" value="${row.slNote}"/></p>
				</li>
			</ul>
			<p class="pop_btn"><a href="javascript:window.close();" class="del">취소</a><a href="javascript:formSubmit();" class="comp">수정</a></p>
		</div>
	</div>
<script>
	
     function formSubmit(){
			$loc_name_empty = $("#locName").val().trim();
			$loc_code_empty = $("#locCode").val().trim();

			if(!$loc_name_empty || !$loc_code_empty){
				alert("필수 값을 입력해주세요!");
			}else{
				$("#itmLocationForm").submit();
			}
		}

</script>
</body>
</html>
