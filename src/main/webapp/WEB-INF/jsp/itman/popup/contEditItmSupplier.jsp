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
			<p class="title">구매처 수정 팝업</p>
		</div>
		<div class="pop_cont">
			<form method="post" id="itmSupplierForm" action="./pp_process/updateSupplierProc.do">
			<ul class="contEdit">
			<input type="hidden" name="supIdx"  value="${row.supIdx}" />
			<input type="hidden" name="groIdx"  value="${row.groIdx}" />
				<li>
					<p class="tit">구매처 명<span>*</span></p>
					<p class="cont"><input type="text" id="supName" name="supName" placeholder="구매처 명을 입력해주세요." value="${row.supName}"></p>
				</li>
				<li>
					<p class="tit">사업자 </br>등록번호<span>*</span></p>
					<p class="cont"><input type="text" id="supBnum" name="supBnum" placeholder="사업자 등록번호를 입력해 주세요. ex)000" value="${row.supBnum}"></p>
				</li>
				<li>
					<p class="tit">e-mail</p>
					<p class="cont"><input type="text" name="supMail" placeholder="이메일를 입력해 주세요. ex)000" value="${row.supMail}"></p>
				</li>
                <li>
					<p class="tit">구매처연락처</p>
					<p class="cont"><input type="text" name="supTel" placeholder="구매처 연락처를 입력해 주세요." value="${row.supTel}"></p>
				</li>
			
				<!-- 비고란 -->
				<li>
					<p class="tit">비고</p>
					<p class="cont"><input type="text" name="supMemo" value="${row.supMemo}"/></p>
				</li>
			</ul>
			<p class="pop_btn"><a href="javascript:window.close();" class="del">취소</a><a href="javascript:formSubmit();" class="comp">수정</a></p>
		</div>
	</div>
<script>

    function formSubmit(){
		$sup_name = $("#supName").val().trim();
		$sup_bnum = $("#supBnum").val().trim();
		if(!$sup_name || !$sup_bnum){
			alert("필수 값을 입력해주세요!");
		}else{
			$("#itmSupplierForm").submit();
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
