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
			<h2>구매처 관리</h2>
			<form action="buyList.do" method="get">
                <p class="list_search">
                    <select name="search">
                   		<option value="all" ${param.search == 'all' ? 'selected' : ''}>전체</option>
	                    <option value="name" ${param.search == 'name' ? 'selected' : ''}>구매처명</option>
                    	<option value="bnum" ${param.search == 'bnum' ? 'selected' : ''}>사업자등록번호</option>
                    	<option value="mail" ${param.search == 'mail' ? 'selected' : ''}>이메일</option>
                    	<option value="tel" ${param.search == 'tel' ? 'selected' : ''}>연락처</option>
                    </select>
                    <input type="text" name="like" value="${param.like}" placeholder="검색어를 입력해주세요." />
            	    <input type="submit" class="dark_btn" value="검색" style="height:42px; width:56px; background-color: black; color:white;">
				</p>
            </form>
		</div>
		<div class="num_list">
			<p class="total">총 <span>${count}</span>건의 결과가 있습니다.</p>
		</div>

		<c:out value="${test}" />
		<!-- 글쓰기 버튼-->
		<p class="addContent"><a onclick="window.open('../popup/contWriteItmSupplier.do', 'EditPopUp', 'width=500, height=500, status=no, toolbar=no, scrollbars=no')" class="edit">><span></span><span></span><span></span></a></p>
		<div class="Basic">
			<ul class="adminList">
                <li class="tit">
					<p class="num">No</p>
					<p class="tit">구매처명</p>
					<p class="bnum">사업자등록번호</p>
					<p class="mail">이메일</p>
					<p class="tel">연락처</p>
					<p class="editDel">관리</p>
				</li>
				<c:forEach var="row" items="${supplierList}" varStatus="status">
   	 				<li>
   	 					<p class="num">${(page - 1) * pageResult + status.index + 1}</p>
        				<p class="tit">${row.supName}</p>
        				<p class="bnum">${row.supBnum}</p>
			        	<p class="mail">${empty row.supMail ? '-' : row.supMail}</p>
        				<p class="tel">${empty row.supTel ? '-' : row.supTel}</p>
        				<p class="editDel">
            				<a onclick="window.open('../popup/contEditItmSupplier.do?idx=${row.supIdx}', 'EditPopUp', 'width=500, height=500, status=no, toolbar=no, scrollbars=no')" class="edit">수정</a><a onclick="window.open('../popup/listDelete.do?id=buyDel&idx=${row.supIdx}', 'EditPopUp', 'width=500, height=500, status=no, toolbar=no, scrollbars=no')" class="del">삭제</a>
        				</p>
    			</li>
				</c:forEach>
			</ul>
			<c:if test="${empty supplierList}">
              	<div style="text-align:center; margin-top:20px;">일치하는 자료가 없습니다.</div>
			</c:if>
		</div>
		<p class="paging">
			<a href="buyList.do?page=1&search=${param.search}&like=${param.like}" class="prev end">
            	<img src='../itman_static/_img/first.png' alt='맨처음'>
        	</a>
        	<a href="buyList.do?page=${page > 1 ? page - 1 : 1}&search=${param.search}&like=${param.like}" class="prev">
            	<img src='../itman_static/_img/prev.png' alt='이전으로'>
        	</a>
        	<c:forEach var="i" begin="1" end="${totalPage}">
	            <a href="buyList.do?page=${i}&search=${param.search}&like=${param.like}" class="${i == page ? 'on' : ''}">${i}</a>
        	</c:forEach>
	        <a href="buyList.do?page=${page < totalPage ? page + 1 : totalPage}&search=${param.search}&like=${param.like}" class="next">
	            <img src='../itman_static/_img/next.png' alt='다음으로'>
    	    </a>
	        <a href="buyList.do?page=${totalPage}&search=${param.search}&like=${param.like}" class="next end">
            	<img src='../itman_static/_img/last.png' alt='맨마지막'>
        	</a>
        </p>
	</div>
<%@ include file="/WEB-INF/jsp/itman/_inc/footer.jsp" %>
</body>
</html>