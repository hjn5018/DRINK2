<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko">
<head>

<%@ include file="/WEB-INF/jsp/itman/_inc/title.jsp"%>
<title>가입 정보 입력 및 인증</title>

</head>
<body>
	<div id="contents">
		<div class="user_box join">
			<p class="tit">
				<a href="<c:url value='/index.do'/>"> <img src="<c:url value='/itman_static/_img/itman_logo.png'/>" alt="아이티맨" />
				</a>
			</p>
			<ul class="step">
				<li class="comp"><span>1</span></li>
				<li class="on"><span>2</span>가입 정보 입력 및 인증</li>
				<li><span>3</span></li>
			</ul>
			<form action="<c:url value='/user/sendEmailProc.do'/>" name="frm" id="frm" method="post">
				<ul class="mem">
					<li>
						<p>사용자 이름</p>
						<div>
							<input type="text" id="memName" name="memName" placeholder="홍길동" />
						</div>
					</li>
					<li>
						<p>이메일</p>
						<div>
							<input type="text" class="fadeInfirst" name="memMail" id="email" placeholder="example@example.com" required />
							<div id="idCheck"></div>
						</div>
					</li>
					<li>
						<p>비밀번호</p>
						<div>
							<input type="password" id="memPw" name="memPw" />
						</div>
					</li>
					<li>
						<p>비밀번호 확인</p>
						<div>
							<input type="password" id="userpw_ch" name="userpw_ch" />
						</div>
					</li>
					<li>
						<p>휴대폰</p>
						<div class="tel">
							<p class="full">
								<select id="userPhone1" name="userPhone1">
									<option value="010">010</option>
									<option value="011">011</option>
									<option value="012">012</option>
								</select><span>&#8209;</span><input type="tel" id="userPhone2" name="userPhone2" /><span>&#8209;</span><input type="tel" id="userPhone3" name="userPhone3" />
							</p>
						</div>
					</li>
				</ul>
				<input type="hidden" name="mode" value="회원가입" />
				<p class="user_btn">
					<a href="#" onclick="fn_submit(); return false;">다음</a>
				</p>
			</form>
		</div>
	</div>
	
	<%@ include file="/WEB-INF/jsp/itman/_inc/footer.jsp"%>
	
	<script type="text/javascript">
	
		// 이메일 유효성 검증
		$(document).ready(function(){
	    	$(".fadeInfirst").on("keyup", function(){
	    	    var email = $(this).val();
	    	    $.post("${pageContext.request.contextPath}/user/idCheck.do", { email: email }, function(data){
	    	        if(data == "0"){
	    	            $("#idCheck").html("<span class='true'>사용가능한 이메일입니다</span>");
	    	        } else if(data == "1"){
	    	            $("#idCheck").html("<span class='false'>이미 존재하는 이메일입니다.</span>");
	    	        } else if(data == "2"){
	    	            $("#idCheck").html("<span class='false'>이메일 형식에 맞게 입력해주세요.</span>");
	    	        }
	    	    });
	    	});
	    });
		
		// 제출 전 유효성 검증(이름, 이메일, 비밀번호, 휴대폰 번호)
		function fn_submit() {
			
			// 이름
			if ($("#memName").val().trim() == "") {
				alert("이름을 입력해주세요.");
				$("#memName").focus();
				return false;
			}
			
			var expNameText = /[가-힣]+$/;
			if (!expNameText.test($("#memName").val())) {
				alert("이름을 한글로 입력해주세요.");
				$("#memName").focus();
				return false;
			}
		
			// 이메일
			if ($("#email").val().trim() == "") {
				alert("이메일을 입력해주세요.");
				$("#email").focus();
				return false;
			}
			
			var expEmailText = /^[A-Za-z0-9\.\-]+@[A-Za-z0-9\.\-]+\.[A-Za-z0-9\.\-]+$/;
			if (!expEmailText.test($("#email").val())) {
				alert("이메일 형식에 맞게 입력해주세요.");
				$("#email").focus();
				return false;
			}
			
			// 비밀번호
			if ($("#memPw").val().trim() == "") {
				alert("비밀번호를 입력해주세요.");
				$("#memPw").focus();
				return false;
			}
			
			if ($("#userpw_ch").val().trim() == "") {
				alert("비밀번호 확인을 입력해주세요.");
				$("#userpw_ch").focus();
				return false;
			}
			
			if ($("#memPw").val().trim().length < 6
					|| $("#memPw").val().trim().length > 20) {
				alert("비밀번호는 6자 이상 20자 이하로 입력해주세요.");
				$("#memPw").focus();
				return false;
			}
			
			if ($("#memPw").val().trim() != $("#userpw_ch").val().trim()) {
				alert("비밀번호가 다릅니다. 다시 입력해주세요.");
				$("#userpw_ch").focus();
				return false;
			}
			
			// 휴대폰 번호
			if ($("#userPhone2").val().trim() == "") {
				alert("휴대폰 번호를 입력해주세요.");
				$("#userPhone2").focus();
				return false;
			}
			
			if ($("#userPhone3").val().trim() == "") {
				alert("휴대폰 번호를 입력해주세요.");
				$("#userPhone3").focus();
				return false;
			}
			
			if ($("#userPhone2").val().trim().length > 4) {
				alert("휴대폰 형식을 확인해주세요.");
				$("#userPhone2").focus();
				return false;
			}
			
			if ($("#userPhone3").val().trim().length > 4) {
				alert("휴대폰 형식을 확인해주세요.");
				$("#userPhone3").focus();
				return false;
			}

			// 제출
			$("#frm").submit();
		}
	</script>
	
</body>
</html>
