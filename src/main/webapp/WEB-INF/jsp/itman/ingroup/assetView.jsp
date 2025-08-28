<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<h2>자산 관리</h2>
		</div>
		<!-- 글삭제 -->
<%-- 		<p class="delContent"><a onclick="window.open('<?='../popup/asset/contAssetDel.php?ass_idx='.$row['ASS_IDX']?>', '삭제팝업', 'width=500, height=300')" href="#none"><img src="../itman_static/_img/del_view.png"></a></p> --%>
		<p class="delContent"><a href="<c:url value='/popup/asset/contAssetDel.do?assIdx=${assetVO.assIdx}'/>" onclick="window.open(this.href,'삭제팝업','width=500, height=300'); return false;"><img src="<c:url value='/itman_static/_img/del_view.png'/>" alt="삭제"></a></p>

		<div class="viewTop">
			<div class="img">
				<span><img id="img" src="<c:url value='/upload/asset/${assetVO.image }'/>" onerror = "this.src='../itman_static/_img/noimg.png'" alt="자산이미지 썸네일"/></span>
				  <form id="FILE_FORM" method="post" enctype="multipart/form-data" action="<c:url value='/popup/asset/ppProcess/assetImageEditProc.do'/>">
				  <input type="hidden" name="assIdx" id="assIdxHidden" value="${assetVO.assIdx}" />
					<p class="filebox">
        			<label for="uploadFile">파일찾기</label> 
					<input type="file" id="uploadFile" name="uploadFile" class="upload-hidden" style="display:none">
				  </form>
				</p>
			</div>
			<ul class="adminView">
				<li>
					<p class="tit">일련번호(ULID)</p>
					<p class="cont">${assetVO.assUlid }</p>
				</li> 
				<li>
					<p class="tit">자산명</p>
					<p class="cont">${assetVO.assName }</p>
					<p class="edit"><a href="<c:url value='/popup/asset/assetNameInfoEdit.do?assIdx=${assetVO.assIdx}'/>" onclick="window.open(this.href,'수정팝업','width=500, height=300'); return false;" class="edit">수정</a></p>
				</li> 
				<li>
					<p class="tit">분류</p>
					<p class="cont">${assetVO.assCatName }</p>
					<p class="edit"><a href="<c:url value='/popup/asset/assetCategoryInfoEdit.do?assIdx=${assetVO.assIdx}'/>" onclick="window.open(this.href,'수정팝업','width=500, height=300'); return false;" class="edit">수정</a></p>
				</li> 
				<li>
					<p class="tit">최초 등록 일시</p>
					<p class="cont">${assetVO.regDate }</p>
				</li>
				<li>
					<p class="tit">상태</p>
					<p class="cont">${assetVO.staName }</p>
					<p class="edit"><a href="<c:url value='/popup/asset/assetStateInfoEdit.do?assIdx=${assetVO.assIdx}'/>" onclick="window.open(this.href,'수정팝업','width=500, height=300'); return false;" class="edit">수정</a></p>
				</li> 
				<li>
					<p class="tit">위치</p>
					<p class="cont">${assetVO.locName }</p>
					<p class="edit"><a href="<c:url value='/popup/asset/assetLocationInfoEdit.do?assIdx=${assetVO.assIdx}'/>" onclick="window.open(this.href,'수정팝업','width=500, height=300'); return false;" class="edit">수정</a></p>
				</li> 
				<li>
					<p class="tit">사용직원</p>
					<p class="cont">${assetVO.empName }</p>
					<p class="edit"><a href="<c:url value='/popup/asset/assetEmployeeInfoEdit.do?assIdx=${assetVO.assIdx}'/>" onclick="window.open(this.href,'수정팝업','width=500, height=300'); return false;" class="edit">수정</a></p>
				</li>
				<li>
					<p class="tit">최종 수정 일시</p>
					<p class="cont">${assetVO.modDate }</p>
				</li> 
			</ul>
		</div>

		<h3>구매 정보</h3>
		<ul class="adminView v02">
			<li>
				<p class="tit">구매처</p>
				<p class="cont">${assetVO.supName }</p>
				<p class="edit"><a href="<c:url value='/popup/asset/assetSupplyInfoEdit.do?assIdx=${assetVO.assIdx}'/>" onclick="window.open(this.href,'수정팝업','width=500, height=300'); return false;" class="edit">수정</a></p>
			</li> <br/>
			<li>
				<p class="tit">최초 구매일</p>
				<p class="cont">
				<c:choose>
					<c:when test="${!empty assetVO.buyDate }">
						${assetVO.buyDate }
					</c:when>
					<c:otherwise>
						-
					</c:otherwise>
				</c:choose>
				</p>
				<p class="edit"><a href="<c:url value='/popup/asset/assetBuyDateInfoEdit.do?assIdx=${assetVO.assIdx}'/>" onclick="window.open(this.href,'수정팝업','width=500, height=300'); return false;" class="edit">수정</a></p>
			</li> <br/>
			<li>
				<p class="tit">가격(원)</p>
				<p class="cont">${assetVO.price }</p>
				<p class="edit"><a href="<c:url value='/popup/asset/assetPriceInfoEdit.do?assIdx=${assetVO.assIdx}'/>" onclick="window.open(this.href,'수정팝업','width=500, height=300'); return false;" class="edit">수정</a></p>
			</li> <br/>
		</ul>

		<h3>부속품 정보</h3>
		<div class="table_box">
			<table>
				<colgroup><col width="100px"><col width="auto"></colgroup>
				<tbody>
					<tr>
						<th>OS</th>
						<td>-</td>
					</tr>
					<tr>
						<th>CPU</th>
						<td>-</td>
					</tr>
					<tr>
						<th>MEMORY</th>
						<td>-</td>
					</tr>
				</tbody>
			</table>
		</div>

		<h3>소프트웨어 정보</h3>
		<div class="table_box">
			<table>
				<colgroup><col width="*"></colgroup>
				<thead>
					<tr>
						<th>Name</th>
						<th>Vendor</th>
						<th>Version</th>
					</tr>
				</thead>
				<tbody>
					<tr><td colspan="3">-</td></tr>
				</tbody>
			</table>
		</div>

		<h3>히스토리</h3>
		<div class="Basic">
			<ul class="adminList history">
				<li class="tit">
					<p class="admin">처리자</p>
					<p class="date">일시</p>
					<p class="tit">분류</p>
					<p class="stat02">활동 구분</p>
					<p class="change v02">내용</p>
					<p class="etc v02">비고</p>
				</li> 
				<c:forEach var="log" items="${assetLogList}">
					<li>
						<p class="admin">${log.egovItmanMemberVO.memName}</p>
						<p class="date">${log.regDate}</p>
						<p class="tit">${log.alCat}</p>
						<p class="stat02">${log.alType}</p>
						<p class="change v02">${log.alCont}</p>
						<p class="etc v02">${log.alNote}</p>
					</li>
				</c:forEach>
				<c:if test="${empty assetLogList}">
					<li class="nocont">히스토리가 없습니다.</li>
				</c:if>
			</ul>
		</div>
	</div>
	<%@ include file="/WEB-INF/jsp/itman/_inc/footer.jsp" %>
	<%-- Removed legacy JS that posted to PHP endpoints --%>
	<script>
  (function () {
    const imgEl = document.getElementById('img');
    const fileInput = document.getElementById('uploadFile');
    const form = document.getElementById('FILE_FORM');

    // 이미지(썸네일) 클릭 → 파일 선택 열기
    imgEl.addEventListener('click', function () {
      fileInput.click();
    });

    // 파일 선택 시: 유효성 검사 → 미리보기 → 자동 제출
    fileInput.addEventListener('change', function (e) {
      const file = e.target.files && e.target.files[0];
      if (!file) return;

      // 타입/용량 간단 검사 (필요시 용량 제한 조정)
      if (!file.type.startsWith('image/')) {
        alert('이미지 파일만 업로드할 수 있어요.');
        fileInput.value = '';
        return;
      }
      const maxMB = 10;
      if (file.size > maxMB * 1024 * 1024) {
        alert('파일 크기가 너무 큽니다. (최대 ' + maxMB + 'MB)');
        fileInput.value = '';
        return;
      }

      // 미리보기
      const blobUrl = URL.createObjectURL(file);
      imgEl.src = blobUrl;

      // 자동 제출
      form.submit();

      // 메모리 누수 방지 (폼 전송으로 페이지 리로드가 되면 사실 불필요하지만 안전하게)
      setTimeout(() => URL.revokeObjectURL(blobUrl), 1000);
    });
  })();
</script>
	
</body>
</html>