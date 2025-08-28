<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>


<!doctype html>
<html lang="ko">
 <head>
   <%@include file="../_inc/title.jsp"%>
 </head>
<body>

	<div id="popup">
		<div class="pop_tit">
			<p class="title">직원 찾기</p>
		</div>
		<div class="pop_cont">
			<ul class="contEdit">
				<li>
					<form action="./searchPop.do" id="frm" method="post" >
						<input type="hidden" name="pageIndex" value="1"/>
						<p class="cont">
							<input type="text" name="searchKeyword"  placeholder="직원 이름을 작성해주세요." class="search"><a href="javascript:form_submit();">직원 검색</a>
						</p>
					</form>
				</li>
			</ul>
			<ul class="popList">
				<c:if test="${empty resultList}">
					<li class="nocont">검색결과가 없습니다.</li>
				</c:if>
				<c:forEach var="employeeVO" items="${resultList }">
					<li>
						<a href="javascript:onClick(${employeeVO.empIdx });">
						<span class="tit" id="empName${employeeVO.empIdx }">
						${employeeVO.empName }
						</span>
						<span class="sub">
						${employeeVO.divName } / ${employeeVO.posName} / ${employeeVO.empMail }
						</span>
						</a>
						<input type="hidden" id="${employeeVO.empIdx }" value="${employeeVO.empIdx }"/>
					</li>
				</c:forEach>
			</ul>

			<p class="paging">
				<a href="javascript:linkPage(1);" class="first"><img src="../itman_static/_img/first.png"></a>
				<a href="javascript:linkPage(${paginationInfo.currentPageNo - 1});" class="prev"><img src="../itman_static/_img/prev.png"></a>
				<c:forEach var="i" begin="${paginationInfo.firstPageNoOnPageList}" end="${paginationInfo.lastPageNoOnPageList}">
					<a href="javascript:linkPage(${i});">${i}</a>
				</c:forEach>
				<a href="javascript:linkPage(${paginationInfo.currentPageNo + 1});" class="next"><img src="../itman_static/_img/next.png"></a>
				<a href="javascript:linkPage(${paginationInfo.totalPageCount});" class="last"><img src="../itman_static/_img/last.png"></a>
			</p>

			<p class="pop_btn"><a href="javascript:window.close();" class="del">취소</a></p>
		</div>
	</div>

</body>
<script>
function form_submit(){
	document.getElementById("frm").pageIndex.value = 1;
	document.getElementById("frm").submit();
}
function linkPage(pageNo){
    var totalPageCount = ${paginationInfo.totalPageCount};

    if (pageNo < 1 || pageNo > totalPageCount) {
        return;
    }
    document.getElementById("frm").pageIndex.value = pageNo;
    document.getElementById("frm").submit();
}
function onClick(idx){
	opener.document.getElementById("empIdx").value = idx;
	window.opener.empIdx = idx;
	opener.document.getElementById("empName").innerHTML = document.getElementById("empName"+idx).innerHTML;
	window.close();

}
</script>
</html>