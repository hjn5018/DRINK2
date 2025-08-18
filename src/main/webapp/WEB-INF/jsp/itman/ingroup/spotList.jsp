<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<!doctype html>
<html lang="ko">
<head>
    <title>직위 관리</title>
    <c:set var="ctx" value="${pageContext.request.contextPath}" />
    <%-- 공통 CSS/JS 등을 포함하는 title.jsp --%>
    <%@ include file="/WEB-INF/jsp/itman/_inc/title.jsp" %>
</head>
<body>
<%@ include file="/WEB-INF/jsp/itman/_inc/header.jsp" %>
<div id="contents">
    <%-- 검색 폼 --%>
    <form id="searchForm" method="get" action="${ctx}/ingroup/spotList.do" onsubmit="return handleSearch();">
        <%-- 페이지 이동을 위해 pageIndex 값을 유지 --%>
        <input type="hidden" name="pageIndex" value="${searchVO.pageIndex}" />
        <input type="hidden" name="orderby" value="${searchVO.orderby}" />
        <div class="tit_search">
            <h2>직위 관리</h2>
            <p class="list_search">
                <select name="searchCondition">
                    <option value="all" ${searchVO.searchCondition eq 'all' ? 'selected' : ''}>전체</option>
                    <option value="code" ${searchVO.searchCondition eq 'code' ? 'selected' : ''}>코드번호</option>
                    <option value="name" ${searchVO.searchCondition eq 'name' ? 'selected' : ''}>직위명</option>
                </select>
                <input type="text" name="searchKeyword" value="<c:out value='${searchVO.searchKeyword}'/>" placeholder="검색어를 입력해주세요.">
                <input type="submit" class="dark_btn" value="검색" style="height:42px; width:56px; background-color: black; color:white;" />
            </p>
        </div>
    </form> <%-- 검색 폼 끝 --%>
    <div class="num_list">
        <p class="total">총 <span><c:out value="${paginationInfo.totalRecordCount}"/></span>건의 결과가 있습니다.</p>
        <select name="orderby" style="float: right;" onchange="fn_egov_sort(this.value);">
            <option value="pos_code asc" ${searchVO.orderby eq 'pos_code asc' || empty searchVO.orderby ? 'selected' : ''}>코드번호 오름차순</option>
            <option value="pos_code desc" ${searchVO.orderby eq 'pos_code desc' ? 'selected' : ''}>코드번호 내림차순</option>
        </select>
    </div>
    <p class="addContent">
        <a href="javascript:void(0);" onclick="openPopup('${ctx}/popup/position/contWriteItmPosition.do?groIdx=${searchVO.groIdx}', 'addPopup');" class="edit">
            <span></span><span></span><span></span>
        </a>
    </p>
    <div class="Basic">
        <ul class="adminList">
            <li class="tit">
                <p class="num">No</p>
                <p class="cod">코드번호</p>
                <p class="tit">직위명</p>
                <p class="editDel">관리</p>
            </li>
            
            <%-- 데이터가 없을 경우 --%>
            <c:if test="${empty resultList}">
                <li class="nocont">일치하는 자료가 없습니다.</li>
            </c:if>
            <%-- 데이터가 있을 경우 (c:forEach 사용) --%>
            <c:forEach var="item" items="${resultList}" varStatus="status">
            <li>
                <%-- No. 계산 로직: 전체 개수 - (페이지 인덱스 - 1) * 페이지당 개수 - 현재 인덱스 --%>
                <p class="num">
                    <c:out value="${status.index + 1}"/>
<%--                     <c:out value="${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage) - status.index}"/> --%>
                </p>
                <p class="cod"><c:out value="${item.posCode}"/></p>
                <p class="tit"><c:out value="${item.posName}"/></p>
                <p class="editDel">
                    <a href="javascript:void(0);" onclick="openPopup('${ctx}/popup/position/contUpdateItmPosition.do?posIdx=${item.posIdx}', 'editPopup');" class="edit">수정</a>
<%--                     <a href="javascript:void(0);" onclick="openDeletePopup('${item.posIdx}');" class="del">삭제</a> --%>
                    <a onclick="window.open('../popup/listDelete.do?id=posDel&posIdx=${item.posIdx}', 'EditPopUp', 'width=500, height=500, status=no,toolbar=no,scrollbars=no')" class="del">삭제</a>
                </p>
            </li>
            </c:forEach>
        </ul>
    </div>
    
    <%-- JSTL 기반의 페이지네이션 --%>
    <p class="paging">
        <a href="javascript:fn_egov_link_page(1);" class="prev end"><img src='${ctx}/itman_static/_img/first.png' alt='맨처음'></a>
        <a href="javascript:fn_egov_link_page(${paginationInfo.currentPageNo > 1 ? paginationInfo.currentPageNo - 1 : 1});" class="prev"><img src='${ctx}/itman_static/_img/prev.png' alt='이전으로'></a>
        
        <c:forEach var="i" begin="${paginationInfo.firstPageNoOnPageList}" end="${paginationInfo.lastPageNoOnPageList}">
            <a href="javascript:fn_egov_link_page(${i});" class="${i == paginationInfo.currentPageNo ? 'on' : ''}">${i}</a>
        </c:forEach>
        <a href="javascript:fn_egov_link_page(${paginationInfo.currentPageNo < paginationInfo.totalPageCount ? paginationInfo.currentPageNo + 1 : paginationInfo.totalPageCount});" class="next"><img src='${ctx}/itman_static/_img/next.png' alt='다음으로'></a>
        <a href="javascript:fn_egov_link_page(${paginationInfo.totalPageCount});" class="next end"><img src='${ctx}/itman_static/_img/last.png' alt='맨마지막'></a>
    </p>
    
</div>
<%@ include file="/WEB-INF/jsp/itman/_inc/footer.jsp" %>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
    // 페이지 이동 함수
    function fn_egov_link_page(pageNo) {
        var form = document.getElementById('searchForm');
        form.pageIndex.value = pageNo;
        form.submit();
    }
    // 정렬 함수
    function fn_egov_sort(orderbyValue) {
        var form = document.getElementById('searchForm');
        form.querySelector('[name="orderby"]').value = orderbyValue;
        form.pageIndex.value = 1; // 정렬 시에는 첫 페이지로 이동
        form.submit();
    }
    // 팝업 열기 함수
    function openPopup(url, name) {
        window.open(url, name, 'width=500, height=500, status=no, toolbar=no, scrollbars=no');
    }
    // 삭제 팝업 열기 함수
    function openDeletePopup(posIdx) {
        var url = "${ctx}/popup/position/contDeleteItmPosition.do?posIdx=" + posIdx;
        window.open(url, "deletePopup", "width=400, height=300, status=no, toolbar=no, scrollbars=no");
    }
    // 검색 버튼 클릭 시 페이지를 1로 초기화하는 함수
    function handleSearch() {
        var form = document.getElementById('searchForm');
        form.pageIndex.value = 1;
        return true; // 폼 제출 허용
    }
</script>
</body>
</html>