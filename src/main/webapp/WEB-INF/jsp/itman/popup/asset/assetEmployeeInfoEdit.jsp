<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<!doctype html>
<html lang="ko">
<head>
    <%@ include file="/WEB-INF/jsp/itman/_inc/title.jsp" %>
    <title>직원 찾기</title>
</head>
<body>
<div id="popup">
    <div class="pop_tit">
        <p class="title">직원 찾기</p>
    </div>
    <div class="pop_cont">
        <ul class="contEdit">
            <li>
                <form action="<c:url value='/popup/asset/assetEmployeeInfoEdit.do'/>" id="frm" method="post">
                    <input type="hidden" name="assIdx" value="${assetVO.assIdx}" />
                    <input type="hidden" id="pageIndex" name="pageIndex" value="${searchVO.pageIndex}"/>
                    <p class="cont">
                        <input type="text" name="searchKeyword" value="${searchVO.searchKeyword}" placeholder="직원 이름을 작성해주세요." class="search">
                        <a href="javascript:form_submit();">직원 검색</a>
                    </p>
                </form>
            </li>
        </ul>
        <ul class="popList">
            <c:if test="${empty employeeList}">
                <li class="nocont">검색결과가 없습니다.</li>
            </c:if>
            <c:forEach var="employee" items="${employeeList}">
                <li>
                    <a href="javascript:onClick('${employee.empIdx}', '${employee.empName}');">
                        <span class="tit">${employee.empName}</span>
                        <span class="sub">${employee.divName} / ${employee.posName} / ${employee.empMail}</span>
                    </a>
                </li>
            </c:forEach>
        </ul>
        
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
        <p class="pop_btn"><a href="javascript:window.close();" class="del">취소</a></p>
    </div>
</div>
<form action="<c:url value='/popup/asset/ppProcess/assetEmployeeInfoEditProc.do'/>" id="form_proc" method="post">
    <input type="hidden" name="assIdx" value="${assetVO.assIdx}" />
    <input type="hidden" id="empIdx" name="empIdx" value="" />
    <input type="hidden" id="empName" name="empName" value="" />
</form>
<script>
    function form_submit() {
        document.getElementById("frm").submit();
    }

	function linkPage(pageIndex){
    var totalPageCount = ${paginationInfo.totalPageCount};

    if (pageIndex < 1 || pageIndex > totalPageCount) {
        return;
    }
	document.getElementById('pageIndex').value = pageIndex;
	document.getElementById('frm').submit();
}

	$('#paging a').on('click', function(e) {
		e.preventDefault(); 
		var page = $(this).data('page');
		if (page) {
			linkPage(page);
		}
	});

    function onClick(idx, name) {
        document.getElementById("empIdx").value = idx;
        document.getElementById("empName").value = name;
        document.getElementById("form_proc").submit();
    }
</script>
</body>
</html>