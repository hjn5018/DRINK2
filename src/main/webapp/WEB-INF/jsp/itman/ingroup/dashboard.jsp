<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<?php $page_num_depth_01 = 0; 
	$result = mysqli_fetch_array($state_count_query);
	return $result[0];
}
?>

<!doctype html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/itman/_inc/title.jsp" %>
</head>
<body>
	<%@ include file="../_inc/header.jsp"%>

	<div id="contents" class="dashboard">
		<div class="overflow">
			<div class="Basic f_r">
				<div class="base_tit">
					<p class="tit">자산 상태표</p>
					<p class="more">
						<a href="aStatList.do"><img src="<c:url value='/itman_static/_img/more.png'/>" alt="더보기" /></a>
					</p>
				</div>
				<ul class="adminList history">
					<li class="tit">
						<p class="admin">상태값</p>
						<p class="date">수량</p>
					</li>

					<c:forEach var="assetState" items="${assetStateList }">
						<li>
							<p class="admin">${assetState.staName }</p>
							<p class="date">${assetState.staCnt }</p>
						</li>
					</c:forEach>
				</ul>
			</div>

			<div class="Basic f_l">
				<div class="base_tit mt">
					<p class="tit">자산 히스토리</p>
					<p class="more">
						<a href="aHistory.do"><img src="<c:url value='/itman_static/_img/more.png'/>" alt="더보기" /></a>
					</p>
				</div>
				<ul class="adminList history">
					<li class="tit">
						<p class="admin">처리자</p>
						<p class="date">일시</p>
						<p class="tit">자산명</p>
						<p class="stat02">활동 구분</p>
						<p class="type">분류</p>
						<p class="change">내용</p>
					</li>

					<?php 
						while($row = (mysqli_fetch_array($asset_query))){
					?>
					<c:forEach var="egovItmanAssetLog" items="${egovItmanAssetLogList }">
					<li>
						<p class="admin">${egovItmanAssetLog.egovItmanMemberVO.memName}<?=$row['MEM_NAME']?></p>
						<p class="date">${egovItmanAssetLog.regDate}<?=$row['REG_DATE']?></p>
						<p class="tit">${egovItmanAssetLog.egovItmanAssetVO.assName}<?=$row['ASS_NAME']?></p>
						<p class="stat02">${egovItmanAssetLog.alType}<?=$row['AL_TYPE']?></p>
						<div class="typeChange">
							<p class="type">${egovItmanAssetLog.alCat}<?=$row['AL_CAT']?></p>
							<p class="change">${egovItmanAssetLog.alCont}<?=$row['AL_CONT']?></p>
						</div>
					</li>
					</c:forEach>
					<?php }; ?>
				</ul>
			</div>
		</div>
		<!-- <div class="overflow mt">
			<div class="Basic f_l">
				<div class="base_tit">
					<p class="tit">시스템 히스토리</p>
					<p class="more"><a href="shistory.php"><img src="../_img/more.png" alt="더보기" /></a></p>
				</div>
				<ul class="adminList history">
					<li class="tit">
						<p class="admin">처리자</p>
						<p class="date">일시</p>
						<p class="tit">분류</p>
						<p class="stat02">활동 구분</p>
						<p class="change">내용</p>
					</li>
					<?php 
						while($row = (mysqli_fetch_array($sys_query))){
					?>
					<li>
						<p class="admin"><?=$row['MEM_NAME']?></p>
						<p class="date"><?= $row['REG_DATE']?></p>
						<p class="tit"><?=$row['SL_CAT']?></p>
						<p class="stat02"><?=$row['SL_TYPE']?></p>
						<p class="change"><?= $row['SL_CONT']?></p>
					</li>
					<?php }; ?>
				</ul>
			</div>
		</div> -->
	</div>

	<%@ include file="../_inc/footer.jsp"%>

	<!-- 자산 상태표 차트 -->
	<script type="text/javascript"
		src="https://www.gstatic.com/charts/loader.js"></script>
	<script type="text/javascript">
		google.charts.load('current', {
			'packages' : [ 'corechart' ]
		});
		google.charts.setOnLoadCallback(drawChart);
	</script>
</body>
</html>
