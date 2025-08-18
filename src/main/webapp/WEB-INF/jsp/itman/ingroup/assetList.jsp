<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
    
<!doctype html>
<html lang="ko">
 <head>
 <%@ include file="/WEB-INF/jsp/itman/_inc/title.jsp" %>
 </head>
<body>
<%@ include file="/WEB-INF/jsp/itman/_inc/header.jsp" %>
	<div id="contents">
		<form id="frmSubmit" method="get" action="assetList.do" >
			<div class="tit_search">
				<h2>자산 관리</h2>
					<p class="list_search">
						<select name="searchCondition">
							<option value="all" <c:if test="${egovItmanAssetVO.searchCondition == 'all'}">selected</c:if>>전체</option>
							<option value="ASS_ULID" <c:if test="${egovItmanAssetVO.searchCondition == 'ASS_ULID'}">selected</c:if>>일련번호</option>
							<option value="ASS_NAME" <c:if test="${egovItmanAssetVO.searchCondition == 'ASS_NAME'}">selected</c:if>>자산명</option>
							<option value="ASS_CAT_NAME" <c:if test="${egovItmanAssetVO.searchCondition == 'ASS_CAT_NAME'}">selected</c:if>>분류</option>
						</select>
						<input type="text" name="searchKeyword"  value="${egovItmanAssetVO.searchKeyword}" placeholder="검색어를 입력해주세요.">
						<input type="hidden" name="pageIndex" value="${egovItmanAssetVO.pageIndex}"/>
						<input type="submit" class="dark_btn" value="검색" style="height:42px; width:56px; background-color: black; color:white;"></input>
					</p>
			</div>
			<div class="num_list">
				<p class="total">총 <span>${paginationInfo.totalRecordCount}</span>건의 결과가 있습니다.</p>
				<p class="view">
					<select id="recordCountPerPage" name="recordCountPerPage" >
						<option value="10" <c:if test="${egovItmanAssetVO.recordCountPerPage == 10}">selected</c:if>>10개씩보기</option>
						<option value="20" <c:if test="${egovItmanAssetVO.recordCountPerPage == 20}">selected</c:if>>20개씩보기</option>
						<option value="40" <c:if test="${egovItmanAssetVO.recordCountPerPage == 40}">selected</c:if>>40개씩보기</option>
					</select>
				</p>
			</div>
		</form>
		
		<!-- 글쓰기 버튼-->
		<p class="addContent"><a href="assetWrite.do"><span></span><span></span><span></span></a></p>

		<div class="Basic">
			<c:if test="${empty assetList}">
				<ul class="adminList">
					<li class="nocont">
						검색 결과가 없습니다.
					</li>
				</ul>
			</c:if>
			<c:if test="${not empty assetList}">
				<ul class="adminList click img">
					<li class="tit">
						<p class="num">No</p>
						<p class="img">자산 이미지</p>
						<p class="num">일련번호</p>
						<p class="tit">자산명</p>
						<p class="cate">분류</p>
						<p class="stat">상태</p>
						<p class="name">사용 직원</p>
						<p class="loc">위치</p>
						<p class="p_info">구매처</p>
						<p class="p_date">최초구매일</p>
						<p class="p_pay">가격(원)</p>
					</li>

	                <c:forEach var="assetVO" items="${assetList }" varStatus="status">
						<li onclick="location.href='assetView.do?assIdx=${assetVO.assIdx}'">
							<p class="num">${status.count }</p>
							<p class="img"><span><img src="<c:url value='/upload/asset/${assetVO.image }'/>" onerror = "this.src='../itman_static/_img/noimg.png'" alt="자산이미지 썸네일"/></span></p>
							<p class="num">${assetVO.assUlid }</p>
							<p class="tit">${assetVO.assName }</p>
							<p class="cate">${assetVO.assCatName }</p>
							<p class="stat">${assetVO.staName }</p>
							<div class="nameLoc">
								<p class="name">${assetVO.empName }</p>
								<p class="loc">${assetVO.locName }</p>
							</div>
							<div class="purInfo">
								<p class="p_info">
									<c:choose>
										<c:when test="${not empty assetVO.supName }">
											${assetVO.supName }
										</c:when>
										<c:otherwise>
											-
										</c:otherwise>
									</c:choose>
								</p>
								<p class="p_date">
									<c:choose>
										<c:when test="${not empty assetVO.buyDate }">
											${assetVO.buyDate }
										</c:when>
										<c:otherwise>
											-
										</c:otherwise>
									</c:choose>
								</p>
								<p class="p_pay">
									<c:choose>
										<c:when test="${not empty assetVO.price }">
											${assetVO.price }
										</c:when>
										<c:otherwise>
											-
										</c:otherwise>
									</c:choose>
								</p>
							</div>
						</li>
	                </c:forEach>
				</ul>
			</c:if>
		</div>

		<div id="paging">
			<p class="paging">
					<a href="#" class="prev end" data-page="1"><img src="<c:url value='/itman_static/_img/first.png'/>" alt="맨처음"></a>
					<a href="#" class="prev" data-page="${paginationInfo.currentPageNo - 1}"><img src="<c:url value='/itman_static/_img/prev.png'/>" alt="이전으로"></a>
				<c:forEach var="i" begin="${paginationInfo.firstPageNo}" end="${paginationInfo.lastPageNo}">
					<a class="<c:if test="${i == paginationInfo.currentPageNo}">on</c:if>" href="#" data-page="${i}">${i}</a>
				</c:forEach>
					<a href="#" class="next" data-page="${paginationInfo.currentPageNo + 1}"><img src="<c:url value='/itman_static/_img/next.png'/>" alt="다음으로"></a>
					<a href="#" class="next end" data-page="${paginationInfo.totalPageCount}"><img src="<c:url value='/itman_static/_img/last.png'/>" alt="맨마지막"></a>
			</p>
		</div>
	</div>
	<%@ include file="/WEB-INF/jsp/itman/_inc/footer.jsp" %>
	
</body>
<script>
	function linkPage(pageIndex){
    var totalPageCount = ${paginationInfo.totalPageCount};

    if (pageIndex < 1 || pageIndex > totalPageCount) {
        return;
    }
	document.getElementById('frmSubmit').pageIndex.value = pageIndex;
	document.getElementById('frmSubmit').submit();
}

	$('#paging a').on('click', function(e) {
		e.preventDefault(); 
		var page = $(this).data('page');
		if (page) {
			linkPage(page);
		}
	});

	$('#recordCountPerPage').on('change', function () {
		document.getElementById('frmSubmit').pageIndex.value = 1; 
		document.getElementById('frmSubmit').submit();
	})
	</script>
</html>