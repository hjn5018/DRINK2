<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
 <head>
 <%@include file="../_inc/title.jsp"%>
 <c:if test="${not empty msg}">
	<script>
		alert('${msg}');
		
        if(window.opener && !window.opener.closed) {
        window.opener.location.reload();
        }
        window.close();
	</script>
</c:if>
 </head>
<body>
	<div id="popup">
		<div class="pop_tit">
			<p class="title">정보 변경</p>
		</div>
		<div class="pop_cont">
			<ul class="contEdit">

		    <div class="user_box join">
            <ul class="mem">
            <form action="./phoneEditProc.do" name="frm" id="frm" method="post">
                <li>
					<p>휴대폰</p>
					<div class="tel">
						<p class="full">
							<select id="userPhone1" name="userPhone1">
								<option>010</option>
								<option>011</option>
								<option>012</option>
							</select><span>-</span><input type="tel" id="userPhone2" name="userPhone2" value="${userPhone2}"><span>-</span><input type="tel" id="userPhone3" name="userPhone3" value="${userPhone3}">
						<!-- </p><a href="#">중복<br/>확인</a> -->
					</div>
				</li>
                </form>
			</ul></ul>
			<p class="pop_btn"><a href="javascript:self.close();" class="del">취소</a><a href="javascript:submit();" class="comp">수정</a></p>
		</div></div>
	</div>

</body>
<script>
    function submit(){

    // userphone2값이 비어있으면 실행.
    if($("#userPhone2").val().trim() == ""){
        alert('휴대폰 번호를 입력해주세요.');
        $("#userPhone2").focus();
        return false;
    }
	// userphone3값이 비어있으면 실행.
	if($("#userPhone3").val().trim() == ""){
		alert('휴대폰 번호를 입력해주세요.');
		$("#userPhone3").focus();
	return false;
    }
	// userphone2값이 4이상이면 실행.
	if($("#userPhone2").val().trim().length > 4){
		alert("휴대폰 형식을 확인해주세요.");
		$("#userPhone2").focus();
		return false;
	}
	// userphone3값이 4이상이면 실행.
	if($("#userPhone3").val().trim().length > 4){
		alert("휴대폰 형식을 확인해주세요.");
		$("#userPhone3").focus();
		return false;
	}

        frm.submit();
    }
</script>
</html>
