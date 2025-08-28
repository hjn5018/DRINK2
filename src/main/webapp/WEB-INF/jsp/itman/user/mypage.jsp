<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="/WEB-INF/jsp/itman/_inc/title.jsp" %>
    <c:if test="${not empty msg}">
  		<script>
   			alert('${msg}');
  		</script>
	</c:if>
</head>
<body id="mypage">
<%
    String tel = (String) request.getAttribute("memTel");
    String memTel = "";

    if (tel != null) {
        tel = tel.replaceAll("", ""); // 숫자만 추출
        if (tel.length() == 11) {
        	memTel = tel.replaceAll("(\\d{3})(\\d{4})(\\d{4})", "$1-$2-$3");
        } else if (tel.length() == 10) {
        	memTel = tel.replaceAll("(\\d{2,3})(\\d{3,4})(\\d{4})", "$1-$2-$3");
        } else {
        	memTel = tel;
        }
    }
    request.setAttribute("memTel", memTel);
%>
<input type="hidden" name="memIdx" value="${sessionScope.memIdx}">

<%@ include file="/WEB-INF/jsp/itman/_inc/header.jsp" %>
    <div id="contents">
        <div class="mypage_box">
            <h2>마이페이지</h2>
            <ul class="myinfo">
                <li>
                    <p>이름</p>
                    <div>${memName}</div>
                </li>
                <li>
                    <p>이메일</p>
                    <div>${memMail}</div>
                </li>
                <li>
                    <p>휴대폰 번호</p>
                    <div>${memTel} 
                        <a onclick="window.open('../popup/phoneEdit.do', '수정팝업', 'width=500, height=335')" href="#none">수정</a>
                    </div>
                </li>
                <li>
                    <p>비밀번호</p>
                    <div class="full"><a href="changePass.do">변경</a></div>
                </li>
            </ul>

            <h3>계정관리</h3>
            <ul class="boxlist">
                <li><a href="myGroup.do">그룹관리</a></li>
                <li><a href="privacy.do">서비스 이용약관</a></li>
                <li><a href="accDel.do">계정탈퇴</a></li>
            </ul>
        </div>
    </div>

    <%@ include file="/WEB-INF/jsp/itman/_inc/footer.jsp" %>
</body>
</html>