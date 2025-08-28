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
			<p class="title">자산 분류 추가 팝업</p>
		</div>
		<div class="pop_cont">
			<form method="post" id="assetCategoryForm" action="./ppProcess/writeAssetCategoryProc.do?groIdx=${groIdx}">
			<ul class="contEdit">
				<li>
					<p class="tit">자산 분류 코드<span>*</span></p>
					<p class="cont"><input type="text" id="assCatCode" name="assCatCode" placeholder="자산 분류 코드를 입력해주세요." value=""></p>
				</li>
				<li>
					<p class="tit">자산 분류명<span>*</span></p>
					<p class="cont"><input type="text" id="assCatName" name="assCatName" placeholder="자산 분류명을 입력해주세요." value=""></p>
				</li>
				<!-- 비고란 -->
				<li>
					<p class="tit">비고</p>
					<p class="cont"><input type="text" name="slNote"/></p>
				</li>
			</ul>
			<p class="pop_btn"><a href="javascript:window.close();" class="del">취소</a><a href="javascript:formSubmit();" class="comp">추가</a></p>
			</form>
		</div>
	</div>
<script>
    function formSubmit(){
		$assCatCode = $("#assCatCode").val().trim();
		$assCatName = $("#assCatName").val().trim();
		if(!$assCatCode || !$assCatName){
			alert("필수 값을 입력해주세요!");
		}else{
			$("#assetCategoryForm").submit();
		}
    }
</script>
</body>
</html>