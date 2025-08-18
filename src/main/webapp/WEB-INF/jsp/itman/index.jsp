<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url value="/itman_static/_img/itman_logo.png" var="logoPath"/>

<!doctype html>
<html lang="ko">
<head>
    <%@ include file="/WEB-INF/jsp/itman/_inc/title.jsp" %>
    <link href="https://webfontworld.github.io/gmarket/GmarketSans.css" rel="stylesheet">
    <title>ITMAN 아이티맨</title>
</head>
<body>
    <header class="h_index">
        <div class="h_left">
            <h1 class="logo">
	            <a href="#">
		            <img src="${logoPath}" alt="아이티맨"/>
	            </a>
            </h1>
        </div>
        <c:choose>
            <c:when test="${!isLogin}">
                <p class="h_right_index">
                    <a href="<c:url value='/user/login.do'/>">로그인</a>
                    <a href="<c:url value='/user/join01.do'/>" class="join">회원가입</a>
                </p>
            </c:when>
            <c:otherwise>
                <div class="h_right">
                    <a href="#" class="hr_btn"><span></span><span></span><span></span></a>
                    <p class="hr_box">
                        <span>${memName}님</span>
                        <a href="<c:url value='/user/mypage.do'/>" class="btn mypage">마이페이지</a>
                        <a href="<c:url value='/user/logoutProc.do'/>" class="btn logout">로그아웃</a>
                        <a href="<c:url value='/group.do'/>" class="btn group">전체그룹메인</a>
                    </p>
                </div>
            </c:otherwise>
        </c:choose>
    </header>

    <div id="contents" class="index">
        <p class="logo">
        	<img src="${logoPath}" alt="아이티맨"/>
        </p>
        <p class="index_tt">
	        개인과 기업이 할 수 있는 자산관리 시스템
	        <span>자산 데이터와 코드 관리 전반에 활용할 수 있는 제품과 솔루션</span>
        </p>
        <ul class="index_service">
            <li><a href="<c:url value='/group.do'/>"><span>Service 01</span>자산 관리</a></li>
            <li><a href="<c:url value='/group.do'/>"><span>Service 02</span>직원 관리</a></li>
            <li><a href="<c:url value='/group.do'/>"><span>Service 03</span>부서 관리</a></li>
            <li><a href="<c:url value='/group.do'/>"><span>Service 04</span>직위 관리</a></li>
            <li><a href="<c:url value='/group.do'/>"><span>Service 05</span>구매처 관리</a></li>
            <li><a href="<c:url value='/group.do'/>"><span>Service 06</span>위치 관리</a></li>
        </ul>
        <p class="index_st">무료로 이용해보기</p>
        <p class="index_btn">
            <c:choose>
                <c:when test="${!isLogin}">
                    <a href="<c:url value='/group.do'/>">그룹 생성하기</a>
                </c:when>
                <c:otherwise>
                    <a onclick="window.open('popup/addGroup.do', '그룹생성팝업','width=500,height=350')" href="#" class="addBox">그룹 생성하기</a>
                </c:otherwise>
            </c:choose>
            <a href="#" onclick="unready(); return false;">유로로 더 많은 기능 사용하기</a>
        </p>
    </div>

    <%@ include file="/WEB-INF/jsp/itman/_inc/footer.jsp" %>
    <script>
	    function unready() {
	        alert("아직 준비중입니다.");
	    }
    </script>
</body>
</html>
