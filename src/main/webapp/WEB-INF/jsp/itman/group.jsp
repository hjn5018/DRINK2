<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!--     $sql = "SELECT * FROM ITM_GROUP WHERE GRO_OWNER_IDX = '".$_SESSION['userIDX']."' AND DEL_YN = 'N'"; -->

<!doctype html>
<html lang="ko">
<head>
<%@ include file="_inc/title.jsp"%>
<script>
	window.onload = function() {
		var width_size = window.innerWidth;

		if (width_size < 821) {
			$('.ham a').removeClass('on');
			$('.smn').removeClass('open');
		} else {
			$('.ham a').addClass('on');
			$('.smn').addClass('open');
			$(function() {
				$('.groupBox').masonry({
					itemSelector : '.groupItem',
					horizontalOrder : true
				});
			})
		}
	}
</script>

</head>
<body id="group">
	<%@ include file="_inc/header.jsp"%>
	<div id="contents">
		<!-- 새로생성될때 div class="Box"에 랜덤으로 [c01, c02, c03]클래스 중 하나가 붙게해주세요(컬러) / addBox 제외 -->
		<div class="groupBox">
			<div class="groupItem">
				<a
					onclick="window.open('popup/addGroup.do', '그룹생성팝업', 'width=500, height=400')"
					href="#" class="addBox">그룹 생성하기</a>
			</div>
			<%--
    <?php while($row = (mysqli_fetch_array($result))) { 
        $ASSET = "SELECT * FROM ITM_ASSET WHERE GRO_IDX={$row['GRO_IDX']} AND DEL_YN = 'N'";
        $EMPLO = "SELECT * FROM ITM_EMPLOYE WHERE GRO_IDX={$row['GRO_IDX']} AND DEL_YN = 'N'";
    ?>
     --%>
			<c:forEach var="vo" items="${groupList}">
				<div class="groupItem">
					<div class="Box c01">
						<p class="name">
							<a href="groupProc.do?id=0&groIdx=${vo.groIdx }">${vo.groName }</a>
						</p>
						<p class="going">
							<a href="groupProc.do?id=1&groIdx=${vo.groIdx }">자산<span>${vo.assetCount }</span></a>
							<a href="groupProc.do?id=2&groIdx=${vo.groIdx }">직원<span>${vo.employeeCount }</span></a>
						</p>
					</div>
				</div>
			</c:forEach>
			<%--<?php }; ?> --%>
		</div>
	</div>
	<%@ include file="_inc/footer.jsp"%>
</body>
</html>

