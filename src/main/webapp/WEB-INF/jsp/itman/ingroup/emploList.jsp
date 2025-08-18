<%-- /WEB-INF/jsp/itman/ingroup/emploList.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!doctype html>
<html lang="ko">
<head>
    <%-- 공통 CSS, 파비콘 등을 포함하는 title.jsp. 모든 경로는 <c:url>을 사용해야 합니다. --%>
    <%@ include file="/WEB-INF/jsp/itman/_inc/title.jsp" %>
    <title>ITMAN 직원 관리</title>
</head>
<body>
<%-- 공통 상단 메뉴를 포함하는 header.jsp. 모든 경로는 <c:url>을 사용해야 합니다. --%>
<%@ include file="/WEB-INF/jsp/itman/_inc/header.jsp" %>
<div id="contents">
    <div class="tit_search">
        <h2>직원 관리</h2>
        
        <form id="searchForm" name="searchForm" method="get" action="<c:url value='/ingroup/emploList.do'/>">
            <%-- groIdx는 검색 조건이 바뀌어도 계속 유지되어야 하므로 hidden input으로 값을 넘겨줍니다. --%>
            <input type="hidden" name="groIdx" value="<c:out value='${searchVO.groIdx}'/>" />
            
            <p class="list_search">
                <select name="searchCondition">
                    <option value="all" <c:if test="${searchVO.searchCondition == 'all'}">selected</c:if>>전체</option>
                    <option value="name" <c:if test="${searchVO.searchCondition == 'name'}">selected</c:if>>이름</option>
                    <option value="emp" <c:if test="${searchVO.searchCondition == 'emp'}">selected</c:if>>사번</option>
                    <option value="div" <c:if test="${searchVO.searchCondition == 'div'}">selected</c:if>>부서</option>
                </select>
                <input name="searchKeyword" type="text" value="<c:out value='${searchVO.searchKeyword}'/>" placeholder="검색어를 입력해주세요.">
                <a href="javascript:fn_search();" class="dark_btn">검색</a>
            </p>
        </form>
    </div>
    
    <div class="num_list">
        <p class="total">총 <span><c:out value="${paginationInfo.totalRecordCount}"/></span>건의 결과가 있습니다.</p>
    </div>
    
    <%-- 직원 등록 페이지로 이동하는 버튼 --%>
    <p class="addContent"><a href="<c:url value='/ingroup/emploWrite.do?groIdx=${searchVO.groIdx}'/>"><span></span><span></span><span></span></a></p>
    
    <div class="Basic">
        <ul class="adminList click">
            <li class="tit">
                <p class="num">사번</p>
                <p class="tit">이름</p>
                <p class="depart">부서</p>
                <p class="spot">직위</p>
                <p class="mail">이메일</p>
                <p class="tel">휴대폰</p>
                <p class="stat">상태</p>
            </li>
            
            <%-- 결과가 없을 경우 메시지를 출력합니다. --%>
            <c:if test="${empty resultList}">
                <li>
                    <div style="text-align:center; width:100%; padding: 20px 0;">
                        일치하는 자료가 없습니다.
                    </div>
                </li>
            </c:if>
            
            <%-- Controller에서 전달받은 직원 목록(resultList)을 반복하여 출력합니다. --%>
            <c:forEach var="item" items="${resultList}" varStatus="status">
                <li onclick="location.href='<c:url value='/ingroup/emploView.do?empIdx=${item.empIdx}&groIdx=${searchVO.groIdx}'/>'">
                    <p class="num"><c:out value="${item.empNum}"/></p>
                    <p class="tit"><c:out value="${item.empName}"/></p>
                    <div class="depSpot">
                        <p class="depart"><c:out value="${item.divName}"/></p>
                        <p class="spot"><c:out value="${item.posName}"/></p>
                    </div>
                    <div class="mailTel">
                        <p class="mail"><c:out value="${empty item.empMail ? '-' : item.empMail}"/></p>
                        <p class="tel"><c:out value="${empty item.empTel ? '-' : item.empTel}"/></p>
                    </div>
                    <p class="stat"><c:out value="${item.empStName}"/></p>
                </li>
            </c:forEach>
        </ul>
    </div>
    
    <p class="paging">
        <a href="<c:url value='/ingroup/emploList.do?pageIndex=1'/>" class="prev end"><img src="<c:url value='/itman_static/_img/first.png'/>" alt='맨처음'></a>
        <a href="<c:url value='/ingroup/emploList.do?pageIndex=${page > 1 ? page - 1 : 1}'/>" class="prev"><img src="<c:url value='/itman_static/_img/prev.png'/>" alt='이전으로'></a>
        
        <c:forEach var="i" begin="1" end="${paginationInfo.totalPageCount}">
            <a href="<c:url value='/ingroup/emploList.do?pageIndex=${i}'/>" class="${i == paginationInfo.currentPageNo ? 'on' : ''}">${i}</a>
        </c:forEach>

        <a href="<c:url value='/ingroup/emploList.do?pageIndex=${page < paginationInfo.totalPageCount ? page + 1 : paginationInfo.totalPageCount}'/>" class="next"><img src="<c:url value='/itman_static/_img/next.png'/>" alt="다음으로"></a>
        <a href="<c:url value='/ingroup/emploList.do?pageIndex=${paginationInfo.totalPageCount}'/>" class="next end"><img src="<c:url value='/itman_static/_img/last.png'/>" alt="맨마지막"></a>
    </p>
    
    <%-- 페이지 이동을 위한 hidden input --%>
    <input type="hidden" name="pageIndex" id="pageIndex" value="${searchVO.pageIndex}" form="searchForm"/>
</div>
<%-- 공통 하단 푸터를 포함합니다. --%>
<%@ include file="/WEB-INF/jsp/itman/_inc/footer.jsp" %>
<script type="text/javascript">
    // 페이지 이동 함수
    function fn_link_page(pageNo) {
        document.searchForm.pageIndex.value = pageNo;
        document.searchForm.submit();
    }
    // 검색 함수
    function fn_search() {
        // 검색 시에는 1페이지부터 조회
        document.searchForm.pageIndex.value = 1;
        document.searchForm.submit();
    }
</script>
</body>
</html>