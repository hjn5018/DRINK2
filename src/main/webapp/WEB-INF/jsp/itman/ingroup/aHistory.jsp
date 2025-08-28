<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>

<!doctype html>
<html lang="ko">
<head>
    <%@ include file="/WEB-INF/jsp/itman/_inc/title.jsp" %>
    <title>자산 히스토리</title>
</head>
<body>
<%@ include file="/WEB-INF/jsp/itman/_inc/header.jsp" %>

<div id="contents">
    <div class="tit_search">
        <h2>자산 히스토리</h2>
        <form id="searchForm" name="searchForm" method="get" action="<c:url value='/ingroup/aHistory.do'/>">
            <p class="list_search">
                <select name="searchCondition">
                    <option value="all" <c:if test="${egovItmanAssetLogVO.searchCondition == 'all'}">selected</c:if>>전체</option>
                    <option value="ulid" <c:if test="${egovItmanAssetLogVO.searchCondition == 'ulid'}">selected</c:if>>일련번호</option>
                    <option value="name" <c:if test="${egovItmanAssetLogVO.searchCondition == 'name'}">selected</c:if>>자산명</option>
                    <option value="content" <c:if test="${egovItmanAssetLogVO.searchCondition == 'content'}">selected</c:if>>내용</option>
                    <option value="note" <c:if test="${egovItmanAssetLogVO.searchCondition == 'note'}">selected</c:if>>비고</option>
                </select>
                <input type="text" name="searchKeyword" value="<c:out value='${egovItmanAssetLogVO.searchKeyword}'/>" placeholder="검색어를 입력해주세요.">
                <a href="javascript:fn_search();" class="dark_btn">검색</a>
            </p>
            <input type="hidden" name="pageIndex" id="pageIndex" value="${egovItmanAssetLogVO.pageIndex}" />
            <input type="hidden" name="orderBy" id="orderByHidden" value="${egovItmanAssetLogVO.orderBy}" />
        </form>
    </div>

    <div class="num_list">
        <p class="total">총 <span><c:out value='${paginationInfo.totalRecordCount}'/></span> 건의 결과가 있습니다.</p>
        <p class="view">
            <select id="orderBy" name="orderBy" onchange="fn_search();">
                <option value="regDate_desc" <c:if test="${egovItmanAssetLogVO.orderBy == 'regDate_desc'}">selected</c:if>>최신순</option>
                <option value="regDate_asc" <c:if test="${egovItmanAssetLogVO.orderBy == 'regDate_asc'}">selected</c:if>>오래된순</option>
            </select>
        </p>
    </div>

    <div class="Basic">
        <ul class="adminList history">
            <li class="tit">
                <p class="admin">처리자</p>
                <p class="date">일시</p>
                <p class="num">자산 일련번호</p>
                <p class="tit">자산명</p>
                <p class="stat02">활동 구분</p>
                <p class="type">분류</p>
                <p class="change">내용</p>
                <p class="etc">비고</p>
            </li>

            <c:if test="${empty assetLogList}">
                <li>
                    <div style="text-align:center; width:100%; padding: 20px 0;">일치하는 자료가 없습니다.</div>
                </li>
            </c:if>

            <c:forEach var="log" items="${assetLogList}">
                <li>
                    <p class="admin"><c:out value='${log.egovItmanMemberVO.memName}'/></p>
                    <p class="date"><c:out value='${log.regDate}'/></p>
                    <p class="num"><c:out value='${log.egovItmanAssetVO.assUlid}'/></p>
                    <p class="tit"><c:out value='${log.egovItmanAssetVO.assName}'/></p>
                    <p class="stat02"><c:out value='${log.alType}'/></p>
                    <p class="type"><c:out value='${log.alCat}'/></p>
                    <p class="change"><c:out value='${log.alCont}'/></p>
                    <p class="etc"><c:out value='${log.alNote}'/></p>
                </li>
            </c:forEach>
        </ul>
    </div>

    <div id="paging">
        <p class="paging">
            <a href="#" class="prev end" data-page="1"><img src="<c:url value='/itman_static/_img/first.png'/>" alt="맨처음"></a>
            <a href="#" class="prev" data-page="${paginationInfo.currentPageNo - 1}"><img src="<c:url value='/itman_static/_img/prev.png'/>" alt="이전으로"></a>
            <c:forEach var="i" begin="${paginationInfo.firstPageNo}" end="${paginationInfo.lastPageNo}">
                <a class="<c:if test='${i == paginationInfo.currentPageNo}'>on</c:if>" href="#" data-page="${i}">${i}</a>
            </c:forEach>
            <a href="#" class="next" data-page="${paginationInfo.currentPageNo + 1}"><img src="<c:url value='/itman_static/_img/next.png'/>" alt="다음으로"></a>
            <a href="#" class="next end" data-page="${paginationInfo.totalPageCount}"><img src="<c:url value='/itman_static/_img/last.png'/>" alt="맨마지막"></a>
        </p>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/itman/_inc/footer.jsp" %>

<script type="text/javascript">
    function fn_search() {
        var form = document.getElementById('searchForm');
        var kw = form.querySelector('input[name="searchKeyword"]');
        if (kw && typeof kw.value === 'string') {
            kw.value = kw.value.trim();
        }
        var orderBySelect = document.getElementById('orderBy');
        var orderByHidden = document.getElementById('orderByHidden');
        if (orderBySelect && orderByHidden) {
            orderByHidden.value = orderBySelect.value;
        }
        document.getElementById('pageIndex').value = 1;
        form.submit();
    }
    // assetList와 동일한 페이지 이동 처리
    document.addEventListener('DOMContentLoaded', function () {
        var paging = document.getElementById('paging');
        var totalPageCount = ${paginationInfo.totalPageCount};
        var currentPageNo = ${paginationInfo.currentPageNo};

        // Prev/Next 유효성에 따라 비활성화 클래스 부여
        try {
            var prev = paging.querySelector('a.prev');
            var next = paging.querySelector('a.next');
            if (prev && currentPageNo <= 1) {
                prev.classList.add('disabled');
            }
            if (next && currentPageNo >= totalPageCount) {
                next.classList.add('disabled');
            }
        } catch (e) {}
        
        if (paging) {
            paging.addEventListener('click', function (e) {
                var target = e.target;
                // img 클릭 시 상위 a로 올리기
                if (target.tagName === 'IMG') {
                    target = target.parentElement;
                }
                if (target.tagName === 'A' && target.dataset.page) {
                    e.preventDefault();
                    var page = parseInt(target.dataset.page, 10);
                    if (isNaN(page)) { return; }
                    // 페이지 유효성 검사: 1..totalPageCount & 현재 페이지 클릭 무시
                    if (page < 1 || page > totalPageCount || page === currentPageNo) { return; }
                    document.getElementById('pageIndex').value = page;
                    document.getElementById('searchForm').submit();
                }
            });
        }
    });
</script>
</body>
</html>
