<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<?php
    include("../_inc/dbconn.php");
    // group 불러오기
    include "../_inc/loginTest.php";
    login_check();
    $group = $_SESSION['group'];
	$REG_IDX = $_SESSION['userIDX'];

	$group_sql = "SELECT * FROM ITM_GROUP WHERE REG_IDX = $REG_IDX";
	$group_query = mysqli_query($dbconn, $group_sql);

?>
<!doctype html>
<html lang="ko">
 <head>
  <%@ include file="/WEB-INF/jsp/itman/_inc/title.jsp" %>
 </head>
<body>

	<div id="popup">
		<div class="pop_tit">
			<p class="title">구매처 추가 팝업</p>
		</div>
		<div class="pop_cont">
			<form method="post" id="itmSupplierForm" action="./ppProcess/writeItmSupplierProc.do?groIdx=${groIdx }">
			<ul class="contEdit">
				<li>
					<p class="tit">구매처 명<span>*</span></p>
					<p class="cont"><input type="text" id="supName" name="supName" placeholder="구매처 명을 입력해주세요." value=""></p>
				</li>
				<li>
					<p class="tit">사업자 </br>등록번호<span>*</span></p>
					<p class="cont"><input type="text" id="supBnum" name="supBnum" placeholder="사업자 등록번호를 입력해 주세요."></p>
				</li>
				<li>
					<p class="tit">email</p>
					<p class="cont"><input type="text" name="supMail" placeholder="이메일를 입력해 주세요."></p>
				</li>
                <li>
					<p class="tit">구매처연락처</p>
					<p class="cont"><input type="text" name="supTel" placeholder="구매처 연락처를 입력해 주세요."></p>
				</li>
			
				<!-- 비고란 -->
				<li>
					<p class="tit">비고</p>
					<p class="cont"><input type="text" name="supMemo"/></p>
				</li>
			</ul>
			<p class="pop_btn"><a href="javascript:window.close();" class="del">취소</a><a href="javascript:formSubmit();" class="comp">추가</a></p>
			</form>
		</div>
	</div>
<script>
    function formSubmit(){
		$supName = $("#supName").val().trim();
		$supBnum = $("#supBnum").val().trim();
		if(!$supName || !$supBnum){
			alert("필수 값을 입력해주세요!");
		}else{
			$("#itmSupplierForm").submit();
		}


    }
</script>
</body>
</html>
