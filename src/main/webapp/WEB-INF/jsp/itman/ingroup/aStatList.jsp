<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/itman/_inc/title.jsp" %>
</head>
<body>
<%@ include file="/WEB-INF/jsp/itman/_inc/header.jsp" %>
	<div id="contents">
		<div class="tit_search">
			<h2>자산 상태 관리</h2>
			<form id="search" method="get" action="aStatList.do">
				<p class="list_search">
					<select name="search">
						<option value="all" ${param.search == 'all' ? 'selected' : ''}>전체</option>
						<option value="code" ${param.search == 'code' ? 'selected' : ''}>코드</option>
						<option value="name" ${param.search == 'name' ? 'selected' : ''}>상태이름</option>
						<option value="note" ${param.search == 'note' ? 'selected' : ''}>비고</option>
					</select>
                    <input type="text" name="like" value="${param.like}" placeholder="검색어를 입력해주세요." />
					<input type="submit" class="dark_btn" value="검색" style="height:42px; width:56px; background-color: black; color:white;"></input>
				</p>
			</form>		
		</div>
		<div class="num_list">
			<p class="total">총 <span>${count}</span>건의 결과가 있습니다.</p>
		</div>
		
		<c:out value="${test}" />	
		<!-- 글쓰기 버튼-->
		<p class="addContent"><a onclick="window.open('../popup/contWriteItmState.do', 'EditPopUp', 'width=500, height=500, status=no,toolbar=no,scrollbars=no')" class="edit">><span></span><span></span><span></span></a></p>
		<div class="Basic">
			<ul class="adminList">
				<li class="tit">
					<p class="num">코드</p>
					<p class="tit">상태 이름</p>
					<p class="num">수량</p>
					<p class="tit">비고</p>
					<p class="editDel">관리</p>
				</li>

				<c:forEach var="row" items="${stateList}" varStatus="status">
					<li>
						<p class="num">${row.staCode}</p>
                    	<p class="tit">${row.staName}</p>
                    	<p class="num">${row.staCnt}</p>
                    	<p class="tit">${row.staNote}</p>
						<p class="editDel">
							<a onclick="window.open('../popup/contWrite.do?idx=${row.staIdx}', 'EditPopUp', 'width=500, height=500, status=no,toolbar=no,scrollbars=no')" class="edit">수정</a><a onclick="window.open('../popup/contDel.do?id=staDel&idx=${row.staIdx}', 'EditPopUp', 'width=500, height=500, status=no,toolbar=no,scrollbars=no')" class="del">삭제</a>
						</p>
					</li>				
				</c:forEach>
				<c:if test="${empty stateList}">
              		<div style="text-align:center; margin-top:20px;">일치하는 자료가 없습니다.</div>
				</c:if>
			</ul>
		</div>
		<p class="paging">
			<a href="aStatList.do?page=1&search=${param.search}&like=${param.like}" class="prev end">
            	<img src='../itman_static/_img/first.png' alt='맨처음'>
        	</a>
        	<a href="aStatList.do?page=${page > 1 ? page - 1 : 1}&search=${param.search}&like=${param.like}" class="prev">
            	<img src='../itman_static/_img/prev.png' alt='이전으로'>
        	</a>
        	<c:forEach var="i" begin="1" end="${totalPage}">
	            <a href="aStatList.do?page=${i}&search=${param.search}&like=${param.like}" class="${i == page ? 'on' : ''}">${i}</a>
        	</c:forEach>
	        <a href="aStatList.do?page=${page < totalPage ? page + 1 : totalPage}&search=${param.search}&like=${param.like}" class="next">
	            <img src='../itman_static/_img/next.png' alt='다음으로'>
    	    </a>
	        <a href="aStatList.do?page=${totalPage}&search=${param.search}&like=${param.like}" class="next end">
            	<img src='../itman_static/_img/last.png' alt='맨마지막'>
        	</a>
		</div>
	<%@ include file="/WEB-INF/jsp/itman/_inc/footer.jsp" %>
</body>
</html>