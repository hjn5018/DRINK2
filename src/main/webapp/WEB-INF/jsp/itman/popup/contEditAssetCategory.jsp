<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
<head>
	<%@include file="../_inc/title.jsp"%>
</head>
<body>
	<div id="popup">
		<div class="pop_tit">
			<p class="title">자산 분류 수정 팝업</p>
		</div>
		<div class="pop_cont">
			<form method="post" id="assetCategoryForm" action="./ppProcess/updateAssetCategoryProc.do">
			<ul class="contEdit">
			<input type="hidden" name="assCatIdx"  value="${assetCategoryVO.assCatIdx}" />
			<input type="hidden" name="groIdx"  value="${assetCategoryVO.groIdx}" />
				<li>
					<p class="tit">자산 분류 코드<span>*</span></p>
					<p class="cont"><input type="text" id="assCatCode" name="assCatCode" placeholder="자산 분류 코드를 입력해주세요." value="${assetCategoryVO.assCatCode}"></p>
				</li>
				<li>
					<p class="tit">자산 분류명<span>*</span></p>
					<p class="cont"><input type="text" id="assCatName" name="assCatName" placeholder="자산 분류명을 입력해주세요." value="${assetCategoryVO.assCatName}"></p>
				</li>
				<!-- 비고란 -->
				<li>
					<p class="tit">비고</p>
					<p class="cont"><input type="text" name="slNote" value="${assetCategoryVO.slNote}"/></p>
				</li>
			</ul>
			<p class="pop_btn"><a href="javascript:window.close();" class="del">취소</a><a href="javascript:formSubmit();" class="comp">수정</a></p>
		</div>
	</div>
<script>

    function formSubmit(){
		$ass_cat_code = $("#assCatCode").val().trim();
		$ass_cat_name = $("#assCatName").val().trim();
		if(!$ass_cat_code || !$ass_cat_name){
			alert("필수 값을 입력해주세요!");
		}else{
			$("#assetCategoryForm").submit();
		}
    }

</script>
<c:if test="${not empty msg}">
	<script type="text/javascript">
		alert("${msg}")
	</script>
</c:if>
</body>
</html>