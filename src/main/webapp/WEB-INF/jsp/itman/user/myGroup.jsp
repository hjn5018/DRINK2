<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="ko">
<head>
<%@include file="../_inc/title.jsp"%> 
</head>
<body id="mypage">
<%@ include file="/WEB-INF/jsp/itman/_inc/header.jsp" %> 
	<div id="contents">
		<div class="mypage_box">
			<h2><a href="mypage.do">그룹관리</a></h2>
			<ul class="groupEdit">
				<li><a onclick="window.open('../popup/addGroup.do', '그룹생성팝업', 'width=500, height=345')" href="#none" class="addBox">그룹생성하기</a></li>
 			<c:forEach var="row" items="${groupList}">
				<li>
					<p class="name">${row.groName}</p>
					<p class="btn">
                        <a onclick="window.open('../user/groupWrite.do?idx=${row.groIdx}', 'EditPopUp', 'width=500, height=500', 'status=no,toolbar=no,scrollbars=no')" class="edit">수정</a>
						<a onclick="window.open('../user/groupDel.do?idx=${row.groIdx}', 'EditPopUp', 'width=500, height=500', 'status=no,toolbar=no,scrollbars=no')" class="del">삭제</a>
                        
                    </p>
				</li>
            </c:forEach>
			</ul>
		</div>
	</div>
<%@ include file="/WEB-INF/jsp/itman/_inc/footer.jsp" %>
</body>
<script language="javascript">

//document.frm.submit();
</script>
</html>


