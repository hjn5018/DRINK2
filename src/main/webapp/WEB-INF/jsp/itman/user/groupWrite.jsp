<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="ko">
<head>
<%@include file="../_inc/title.jsp"%> 
</head>
<body>
	<div id="popup">
		<div class="pop_tit">
			<p class="title">그룹수정하기</p>
		</div>
		<div class="pop_cont">
            <form method="post" id="itmGroupForm" action="./updateGroupProc.do" enctype="multipart/form-data">
			<input type="hidden" name="groIdx" value="${valueRow.groIdx}" >
			<ul class="contEdit">
				<li>
					<p class="tit">그룹 이름</p>
					<p class="cont"><input type="text" name="groName" placeholder="그룹명을 작성해주세요." value="${valueRow.groName}"></p>
				</li>
				<li>
					<p class="tit">그룹 이미지</p>
					<div class="cont filebox">
					  <label for="ex_filename" onclick="openImg()">파일찾기</label> 
					  <!-- <form id="FILE_FORM" method="post" enctype="multipart/form-data" action=""> -->
					    <input type="file" id="filename" name="image" class="upload-hidden" >
						<input class="upload-name" value="${valueRow.groImg}" disabled="disabled">
				      <!-- </form> -->
					</div>
				</li>
				<li>
					<p class="tit">그룹 설명</p>
					<p class="cont"><textarea id="groNote" name="groNote" placeholder="그룹 설명을 작성해주세요.">${valueRow.groNote}</textarea></p>
				</li>
			</ul>
            <p class="pop_btn"><a href="javascript:self.close();" class="del">취소</a><a href="javascript:formSubmit();" class="comp">수정</a></p>
        	</form>
		</div>
	</div>

</body>
<script>
	function openImg() {
		$("#filename").click();
	  }
     function formSubmit(){
        $("#itmGroupForm").submit();
    }
        
</script>
</html>

