<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!doctype html>
<html lang="ko">
 <head>
 <%@ include file="/WEB-INF/jsp/itman/_inc/title.jsp" %>
  <? 
	 include "../_inc/loginTest.php";
	 login_check();
	 $group = $_SESSION['group'];
  ?>
 </head>
<body>

	<div id="popup">
		<div class="pop_tit">
			<p class="title">그룹생성하기</p>
		</div>
		<div class="pop_cont">
        <form action="./ppProcess/addGroupProc.do" name="frm" id="frm" method="post" enctype= "multipart/form-data">
			<ul class="contEdit">
				<li>
					<p class="tit">그룹 이름</p>
					<p class="cont"><input type="text" id="groName" name="groName" placeholder="그룹명을 작성해주세요."></p>
				</li>
				<li>
					<p class="tit">그룹 이미지</p>
					<div class="cont filebox">
					  <label for="uploadFile">파일찾기</label> 
					  <input type="file" id="uploadFile" name=uploadFile class="upload-hidden">
					  <input class="upload-name" value="파일선택" disabled="disabled">
					</div>
				</li>
				<li>
					<p class="tit">그룹 설명</p>
					<p class="cont"><textarea id="groNote" name="groNote" placeholder="그룹 설명을 작성해주세요."></textarea></p>
				</li>
			</ul>
            <p class="pop_btn"><a href="javascript:self.close();" class="del">취소</a><a href="javascript:fn_submit();" class="comp">생성</a></p>
        </form>
		</div>
	</div>


<c:if test="${!empty result }">
	<c:choose>
		<c:when test="${result == 1 }">
			<script>
				alert('그룹이 생성되었습니다.');
				self.close();
				opener.parent.location.reload();
			</script>
		</c:when>
		<c:when test="${result == 0 }">
			<script>
				alert('생성에 실패했습니다. 잠시 후 다시 시도해주세요.');
				history.back();
			</script>
		</c:when>
	</c:choose>
</c:if>

</body>
<script language="javascript">
		$doubleSubmitFlag = false;
function fn_submit(){
    //그룹 이름
	if($("#groName").val().trim() == ""){
		alert("그룹 이름은 필수입니다.");
		$("#groName").focus();
		return false;
	}else if(!$doubleSubmitFlag){
		$doubleSubmitFlag = true;
		frm.submit();
	}

}
</script>
</html>

