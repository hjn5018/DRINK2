<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="ko">
 <head>
  <%@ include file="/WEB-INF/jsp/itman/_inc/title.jsp" %>
 </head>
<body>
<%@ include file="/WEB-INF/jsp/itman/_inc/header.jsp" %>
	<div id="contents">
		<div class="tit_search">
			<h2>자산 분류 관리</h2>
			<form action="assetCategory.do">
                <p class="list_search">
                    <select name="search">
						<option value="all" ${param.search == 'all' ? 'selected' : ''}>전체</option>
						<option value="code" ${param.search == 'code' ? 'selected' : ''}>코드</option>
						<option value="name" ${param.search == 'name' ? 'selected' : ''}>자산 분류명</option>
                    </select>
                    <input type="text" name="like"  value="${param.like }" placeholder="검색어를 입력해주세요.">
                    <input type="hidden" name="page" value="${page }">
					<input type="submit" class="dark_btn" value="검색" style="height:42px; width:56px; background-color: black; color:white;"></input>
				</p>
            </form>
		</div>

		<div class="num_list">
			<p class="total">총 <span>${count }</span>건의 결과가 있습니다.</p>
			<a href="assetCategoryExcel.do?search=${search }&like=${like}"
			   style="float: right; background-color: #28a745; color: white; padding: 5px 10px; border-radius: 2px; text-decoration: none; font-weight: bold;">
			   엑셀 다운로드
			</a>
		</div>

        <?=$test?>
		<!-- 글쓰기 버튼-->
		<p class="addContent"><a onclick="window.open('../popup/contWriteAssetCategory.do', 'EditPopUp', 'width=500, height=500, status=no,toolbar=no,scrollbars=no')" class="edit">><span></span><span></span><span></span></a></p>
		<div class="Basic">
			<ul class="adminList">
                <li class="tit">
					<p class="num">코드</p>
					<p class="tit">자산 분류명</p>
					<p class="tit">비고</p>
					<p class="editDel">관리</p>
				</li>
                <c:forEach var="assetCategoryVO" items="${assetCategoryList }">
                    <li>
                        <p class="num">${assetCategoryVO.assCatCode }</p>
                        <p class="tit">${assetCategoryVO.assCatName }</p>
                        <p class="tit">${empty assetCategoryVO.slNote ? '-' : assetCategoryVO.slNote}</p>
                        <p class="editDel">
							<a onclick="window.open('../popup/contEditAssetCategory.do?assCatIdx=${assetCategoryVO.assCatIdx}', 'EditPopUp', 'width=500, height=500, status=no,toolbar=no,scrollbars=no')" class="edit">수정</a><a onclick="window.open('../popup/listDelete.do?id=assetCateDel&assCatIdx=${assetCategoryVO.assCatIdx}', 'EditPopUp', 'width=500, height=500, status=no,toolbar=no,scrollbars=no')" class="del">삭제</a>
						</p>
                    </li>
                </c:forEach>
                <c:if test="${empty assetCategoryList}">
	              	<div style="text-align:center; margin-top:20px;">일치하는 자료가 없습니다.</div>
				</c:if>    
			</ul>
		</div>
		<p class="paging">
                <a href="./assetCategory.do?search=${param.search }&like=${param.like }&page=1" class="prev end">
                	<img src='../itman_static/_img/first.png' alt='맨처음'>
                </a>
                <a href="./assetCategory.do?search=${param.search }&like=${param.like }&page=${page > 1 ? page - 1 : 1}" class="prev">
               	 <img src='../itman_static/_img/prev.png' alt='이전으로'>
                </a>
                <c:forEach var="i" begin="1" end="${totalPage }" >
                	<a href="./assetCategory.do?search=${param.search }&like=${param.like }&page=${i}" class="${i == page ? 'on' : ''}">${i }</a>
                </c:forEach>
                <a href="./assetCategory.do?search=${param.search }&like=${param.like }&page=${page < totalPage ? page + 1 : totalPage}" class="next">
              	  <img src='../itman_static/_img/next.png' alt='다음으로'></a>
                <a href="./assetCategory.do?search=${param.search }&like=${param.like }&page=${totalPage}" class="next end">
              	  <img src='../itman_static/_img/last.png' alt='맨마지막'>
                </a>
            </p>
	</div>
		
	<%@ include file="/WEB-INF/jsp/itman/_inc/footer.jsp" %>

</body>
</html>