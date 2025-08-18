<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
 <head>
<%@ include file="/WEB-INF/jsp/itman/_inc/title.jsp" %> 
 </head>
<body>
	<div id="contents">
		<div class="user_box join">
			<p class="tit">
				<a href="${pageContext.request.contextPath}/index.do">
          			<img src="${pageContext.request.contextPath}/itman_static/_img/itman_logo.png" alt="아이티맨"/>
        		</a></p>
			<p class="find_tit">
				이메일을<br/>잊으셨나요?
				<span>회원가입시 입력하셨던 정보를 하단에 입력해주세요.</span>
			</p>

            <form action="findEmailProc.do" name="frm" id="frm" method="post">
			<ul class="mem">
				<li>
					<p>사용자 이름</p>
                    <div><input type="text" id="memName" name="memName"></div>
				</li>
				<li>
					<p>휴대폰 번호</p>
					<div class="tel">
						<p class="full">
                            <select id="userPhone1" name="userPhone1">
								<option>010</option>
								<option>011</option>
								<option>012</option>
                                </select><span>-</span><input type="tel" id="userPhone2" name="userPhone2"><span>-</span><input type="tel" id="userPhone3" name="userPhone3">
						</p>
					</div>
				</li>
			</ul>
			<p class="user_btn"><a href="javascript:fn_submit();">이메일 찾기</a></p>
            </form>
		</div>
	</div>
	<%@ include file="/WEB-INF/jsp/itman/_inc/footer.jsp" %>
</body>
<script>
    function fn_submit() {
        document.getElementById("frm").submit();  // ← frm 변수를 명확히 지정
    }
</script>

<c:if test="${param.error eq 'Y'}">
    <script>
        alert('입력한 정보의 회원이 존재하지 않습니다.');
    </script>
</c:if>
</html>
