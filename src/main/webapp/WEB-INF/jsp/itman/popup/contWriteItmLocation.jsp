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
			<p class="title">자산 위치 작성 팝업</p>
		</div>
		<div class="pop_cont">
			<form method="post" id="itmLocationForm" action="./ppProcess/writeItmLocationProc.do?groIdx=${groIdx }">
			<ul class="contEdit">
				<li>
					<p class="tit">자산 위치명<span>*</span></p>
					<p class="cont"><input type="text" id="locName" name="locName" placeholder="위치명을 입력해주세요." value=""></p>
				</li>
				<li>
					<p class="tit">자산 위치 코드번호<span>*</span></p>
					<p class="cont"><input type="text" id="locCode" name="locCode" placeholder="위치 코드 번호를 입력해 주세요. ex)000"></p>
				</li>
			
				<!-- 비고란 -->
				<li>
					<p class="tit">비고</p>
					<p class="cont"><input type="text" name="slNote"/></p>
				</li>
			</ul>
			<p class="pop_btn"><a href="javascript:window.close();" class="del">취소</a><a href="javascript:formSubmit();" class="comp">작성</a></p>
			</form>
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
