<%-- /WEB-INF/jsp/itman/ingroup/emploView.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="ko">
<head>
    <%@ include file="/WEB-INF/jsp/itman/_inc/title.jsp" %>
    <title>ITMAN 직원 관리</title>
</head>
<body>
<%@ include file="/WEB-INF/jsp/itman/_inc/header.jsp" %>

<div id="contents">
    <div class="tit_search">
        <h2>직원 관리</h2>
    </div>

    <p class="delContent">
        <a onclick="window.open('<c:url value='/popup/employee/emploDel.do?empIdx=${emploVO.empIdx}'/>', '삭제팝업', 'width=500, height=300')" href="#none">
            <img src="<c:url value='../itman_static/_img/del_view.png'/>" alt="삭제">
        </a>
    </p>

    <ul class="adminView">
        <li>
            <p class="tit v02">사번</p>
            <p class="cont"><c:out value="${emploVO.empNum}"/></p>
            <p class="edit">
                <a onclick="window.open('<c:url value='/popup/employee/emploNumInfoEdit.do?empIdx=${emploVO.empIdx}'/>', '수정팝업', 'width=500, height=335')" href="#none" class="edit">수정</a>
            </p>
        </li>
        <li>
            <p class="tit v02">이름</p>
            <p class="cont"><c:out value="${emploVO.empName}"/></p>
            <p class="edit">
                <a onclick="window.open('<c:url value='/popup/employee/emploNameInfoEdit.do?empIdx=${emploVO.empIdx}'/>', '수정팝업', 'width=500, height=335')" href="#none" class="edit">수정</a>
            </p>
        </li>
        <li>
            <p class="tit v02">이메일</p>
            <p class="cont"><c:out value="${fn:length(emploVO.empMail) gt 0 ? emploVO.empMail : '-'}"/></p>
            <p class="edit">
                <a onclick="window.open('<c:url value='/popup/employee/emploMailInfoEdit.do?empIdx=${emploVO.empIdx}'/>', '수정팝업', 'width=500, height=335')" href="#none" class="edit">수정</a>
            </p>
        </li>
        <li>
            <p class="tit v02">휴대폰</p>
            <p class="cont"><c:out value="${fn:length(emploVO.empTel) gt 0 ? emploVO.empTel : '-'}"/></p>
            <p class="edit">
                <a onclick="window.open('<c:url value='/popup/employee/emploTelInfoEdit.do?empIdx=${emploVO.empIdx}'/>', '수정팝업', 'width=500, height=335')" href="#none" class="edit">수정</a>
            </p>
        </li>
        <li>
            <p class="tit v02">부서</p>
            <p class="cont"><c:out value="${emploVO.divName}"/></p>
            <p class="edit">
                <a onclick="window.open('<c:url value='/popup/employee/emploDivisionInfoEdit.do?empIdx=${emploVO.empIdx}'/>', '수정팝업', 'width=500, height=335')" href="#none" class="edit">수정</a>
            </p>
        </li>
        <li>
            <p class="tit v02">직위</p>
            <p class="cont"><c:out value="${emploVO.posName}"/></p>
            <p class="edit">
                <a onclick="window.open('<c:url value='/popup/employee/emploPosInfoEdit.do?empIdx=${emploVO.empIdx}'/>', '수정팝업', 'width=500, height=335')" href="#none" class="edit">수정</a>
            </p>
        </li>
        <li>
            <p class="tit v02">상태</p>
            <p class="cont"><c:out value="${emploVO.empStName}"/></p>
            <p class="edit">
                <a onclick="window.open('<c:url value='/popup/employee/emploStateInfoEdit.do?empIdx=${emploVO.empIdx}'/>', '수정팝업', 'width=500, height=335')" href="#none" class="edit">수정</a>
            </p>
        </li>
    </ul>

    <h3>소유 자산</h3>
    <div class="Basic">
        <ul class="adminList click img">
            <li class="tit">
                <p class="img">자산 이미지</p>
                <p class="num">일련번호</p>
                <p class="tit">자산명</p>
                <p class="cate">분류</p>
                <p class="date02">최초구매일</p>
                <p class="view"></p>
            </li>
            <c:choose>
                <c:when test="${fn:length(assetList) eq 0}">
                    <li class="nocont">검색 결과가 없습니다.</li>
                </c:when>
                <c:otherwise>
                    <c:forEach var="asset" items="${assetList}">
                        <li onclick="location.href='<c:url value="/ingroup/assetView.do?assIdx=${asset.assIdx}"/>'">
                            <p class="img">
                                <span><img src="<c:url value='${ctx}/upload/asset/${asset.image}'/>" onerror=this.src="<c:url value='/itman_static/_img/noimg.png'/>" alt="자산이미지 썸네일"/></span>
                            </p>
                            <p class="num"><c:out value="${asset.assUlid}"/></p>
                            <p class="tit"><c:out value="${asset.assName}"/></p>
                            <p class="cate"><c:out value="${asset.assCatName}"/></p>
                            <p class="date02"><c:out value="${asset.buyDate}"/></p>
                            <p class="view"></p>
                        </li>				
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/itman/_inc/footer.jsp" %>
</body>
</html>