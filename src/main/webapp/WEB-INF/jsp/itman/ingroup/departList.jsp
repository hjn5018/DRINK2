<%-- /WEB-INF/jsp/itman/ingroup/departList.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<!doctype html>
<html lang="ko">
<head>
    <c:set var="ctx" value="${pageContext.request.contextPath}" />
    <%@ include file="/WEB-INF/jsp/itman/_inc/title.jsp" %>
    <title>부서 관리</title>
</head>
<body>
<%@ include file="/WEB-INF/jsp/itman/_inc/header.jsp" %>
<div id="contents">
    <div class="tit_search">
        <h2>부서 관리</h2>
        <form id="search" method="get" action="${ctx}/ingroup/departList.do">
            <input type="hidden" name="pageIndex" value="1"/>
            <p class="list_search">
                <select name="searchCondition">
                    <option value="all" <c:if test="${searchVO.searchCondition eq 'all'}">selected</c:if>>전체</option>
                    <option value="code" <c:if test="${searchVO.searchCondition eq 'code'}">selected</c:if>>코드번호</option>
                    <option value="name" <c:if test="${searchVO.searchCondition eq 'name'}">selected</c:if>>부서명</option>
                </select>
                <input type="text" name="searchKeyword" value="${searchVO.searchKeyword}" placeholder="검색어를 입력해주세요.">
                <input type="submit" class="dark_btn" value="검색" style="height:42px; width:56px; background-color: black; color:white;" />
            </p>
        </form>
    </div>
    
    <div class="num_list">
        <p class="total">총 <span>${paginationInfo.totalRecordCount}</span>건의 결과가 있습니다.</p>
    </div>
    
    <p class="addContent">
        <a onclick="openPopup('<c:url value="/popup/emploDivisionWrite.do?groIdx=${searchVO.groIdx}"/>')" class="edit">
            <span></span><span></span><span></span>
        </a>
    </p>
    <div class="Basic">
        <ul class="adminList">
            <li class="tit">
                <p class="num">No</p>
                <p class="cod">코드번호</p>
                <p class="tit">부서명</p>
                <p class="pos">사용유무</p>
                <p class="editDel">관리</p>
            </li>
            <c:choose>
                <c:when test="${fn:length(resultList) eq 0}">
                    <li class="nocont">일치하는 자료가 없습니다.</li>
                </c:when>
                <c:otherwise>
                    <c:forEach var="division" items="${resultList}" varStatus="status">
                        <li>
                            <p class="num"><c:out value="${status.index + 1}"/></p>
                            <p class="cod"><c:out value="${division.divCode}"/></p>
                            <p class="tit"><c:out value="${division.divName}"/></p>
                            <p class="pos"><c:out value="${division.divYn eq 'Y' ? '사용' : '사용안함'}"/></p>
                            <p class="editDel">
                                <a onclick="openPopup('<c:url value="/popup/division/contWriteDivision.do?idx=${division.divIdx}"/>')" class="edit">수정</a>
<%--                                 <a onclick="openPopup('<c:url value="/popup/contDivisionDel.do?div_idx=${division.divIdx}"/>')" class="del">삭제</a> --%>
<%--                                 <a onclick="window.open('../popup/listDelete.do?id=assetCateDel&assCatIdx=${assetCategoryVO.assCatIdx}', 'EditPopUp', 'width=500, height=500, status=no,toolbar=no,scrollbars=no')" class="del">삭제</a> --%>
                                <a onclick="window.open('../popup/listDelete.do?id=divDel&divIdx=${division.divIdx}', 'EditPopUp', 'width=500, height=500, status=no,toolbar=no,scrollbars=no')" class="del">삭제</a>
                            </p>
                        </li>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
    
    <p class="paging">
        <a href="<c:url value='/ingroup/departList.do?pageIndex=1'/>" class="prev end"><img src="<c:url value='/itman_static/_img/first.png'/>" alt="맨처음"></a>
        <a href="<c:url value='/ingroup/departList.do?pageIndex=${paginationInfo.currentPageNo > 1 ? paginationInfo.currentPageNo - 1 : 1}'/>" class="prev"><img src="<c:url value='/itman_static/_img/prev.png'/>" alt="이전으로"></a>
        
        <c:forEach var="i" begin="${paginationInfo.firstPageNoOnPageList}" end="${paginationInfo.lastPageNoOnPageList}">
            <a href="<c:url value='/ingroup/departList.do?pageIndex=${i}'/>" class="${i == paginationInfo.currentPageNo ? 'on' : ''}">${i}</a>
        </c:forEach>
        <a href="<c:url value='/ingroup/departList.do?pageIndex=${paginationInfo.currentPageNo < paginationInfo.totalPageCount ? paginationInfo.currentPageNo + 1 : paginationInfo.totalPageCount}'/>" class="next"><img src="<c:url value='/itman_static/_img/next.png'/>" alt="다음으로"></a>
        <a href="<c:url value='/ingroup/departList.do?pageIndex=${paginationInfo.totalPageCount}'/>" class="next end"><img src="<c:url value='/itman_static/_img/last.png'/>" alt="맨마지막"></a>
    </p>
    
</div>
<%@ include file="/WEB-INF/jsp/itman/_inc/footer.jsp" %>
<script>
    function fn_egov_selectList(pageNo) {
        var form = document.getElementById('search');
        var pageIndexInput = form.querySelector('input[name="pageIndex"]');
        if (!pageIndexInput) {
            pageIndexInput = document.createElement('input');
            pageIndexInput.type = 'hidden';
            pageIndexInput.name = 'pageIndex';
            form.appendChild(pageIndexInput);
        }
        pageIndexInput.value = pageNo;
        form.submit();
    }
    function openPopup(url) {
        window.open(url, 'popupWindow', 'width=500, height=500, status=no, toolbar=no, scrollbars=no');
    }
</script>
</body>
</html>