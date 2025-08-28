<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html lang="ko">
 <head>
  <%@ include file="/WEB-INF/jsp/itman/_inc/title.jsp" %>
 </head>
<body>

	<div id="popup">
		<div class="pop_tit">
			<p class="title">자산 상태 추가 팝업</p>
		</div>
		<div class="pop_cont">
			<form method="post" id="stateCateForm" action="./ppProcess/writeItmStateProc.do?groIdx=${groIdx }">
			<ul class="contEdit">
				<li>
					<p class="tit">자산 상태명<span>*</span></p>
					<p class="cont"><input type="text" id="staName" name="staName" placeholder="자산 상태명을 입력해주세요." value=""></p>
				</li>
				<li>
					<p class="tit">상태코드번호<span>*</span></p>
					<p class="cont"><input type="text" id="staCode" name="staCode" placeholder="자산 상태 코드 번호를 입력해 주세요. ex)000"></p>
				</li>
			
				<!-- 비고란 -->
				<li>
					<p class="tit">비고</p>
					<p class="cont"><input type="text" name="staNote"/></p>
				</li>
				
			</ul>
			<p class="pop_btn"><a href="javascript:window.close();" class="del">취소</a><a href="javascript:formSubmit();" class="comp">등록</a></p>
			</form>
		</div>
	</div>
<script>

    function formSubmit(){
			$sta_name_empty = $("#staName").val().trim();
			$sta_code_empty = $("#staCode").val().trim();

			if(!$sta_name_empty || !$sta_code_empty){
				alert("필수 값을 입력해주세요!");
			}else{
				$("#stateCateForm").submit();
			}
		}
		
</script>
</body>
</html>