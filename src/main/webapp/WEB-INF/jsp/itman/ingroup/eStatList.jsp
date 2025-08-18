<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<!doctype html>
<html lang="ko">
<head>
    <c:set var="ctx" value="${pageContext.request.contextPath}" />
    <%@ include file="/WEB-INF/jsp/itman/_inc/title.jsp" %>
    <title>직원 상태 관리</title>
</head>
<body>
<%@ include file="/WEB-INF/jsp/itman/_inc/header.jsp" %>
<div id="contents">
    <div class="tit_search">
        <h2>직원 상태 관리</h2>
        <form id="searchForm" method="get" action="${ctx}/ingroup/eStatList.do">
            <input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>" />
            <p class="list_search">
                <select name="searchCondition">
                    <option value="all" <c:if test="${searchVO.searchCondition eq 'all'}">selected</c:if>>전체</option>
                    <option value="name" <c:if test="${searchVO.searchCondition eq 'name'}">selected</c:if>>상태이름</option>
                    <option value="note" <c:if test="${searchVO.searchCondition eq 'note'}">selected</c:if>>비고</option>
                </select>
                <input type="text" name="searchKeyword" value="<c:out value='${searchVO.searchKeyword}'/>" placeholder="검색어를 입력해주세요.">
                <input type="submit" class="dark_btn" value="검색" style="height:42px; width:56px; background-color: black; color:white;" />
            </p>
        </form>
    </div>
    <div class="num_list">
        <p class="total">총 <span><c:out value="${paginationInfo.totalRecordCount}"/></span>건의 결과가 있습니다.</p>
    </div>
    <p class="addContent">
        <a onclick="openPopup('<c:url value="/popup/state/contWriteItmState.do?groIdx=${searchVO.groIdx}"/>', 'addPopup');" class="edit">
            <span></span><span></span><span></span>
        </a>
    </p>
    <div class="Basic">
        <ul class="adminList">
            <li class="tit">
                <p class="num">No</p>
                <p class="tit">상태 이름</p>
                <p class="num">직원수</p>
                <p class="tit">비고</p>
                <p class="editDel">관리</p>
            </li>
            
            <c:choose>
                <c:when test="${fn:length(resultList) eq 0}">
                    <li class="nocont">일치하는 자료가 없습니다.</li>
                </c:when>
                <c:otherwise>
                    <c:forEach var="item" items="${resultList}" varStatus="status">
                        <li>
                            <p class="num">
                                <c:out value="${status.index + 1}"/>
                            </p>
                            <p class="tit"><c:out value="${item.stName}"/></p>
                            <p class="num"><c:out value="${item.empCnt}"/></p>
                            <p class="tit"><c:out value="${item.slNote}"/></p>
                            <p class="editDel">
                                <a onclick="openPopup('<c:url value="/popup/state/contUpdateItmState.do?stIdx=${item.stIdx}"/>', 'editPopup');" class="edit">수정</a>
                                <a onclick="openPopup('<c:url value="/popup/listDelete.do?id=eStateDel&idx=${item.stIdx}"/>', 'delPopup');" class="del">삭제</a>
                            </p>
                        </li>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
    
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
<script>
    function fn_egov_link_page(pageNo) {
        var form = document.getElementById('searchForm');
        form.pageIndex.value = pageNo;
        form.submit();
    }
    
    function openPopup(url, name) {
        window.open(url, name, 'width=500, height=500, status=no, toolbar=no, scrollbars=no');
    }
</script>
</body>
</html>