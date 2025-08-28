<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<? $page_num_depth_01 = 1; ?>

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
		<form action="./igProcess/assetWriteProc.do" method="post" id="frm" enctype="multipart/form-data">
		<input type="hidden" name="groIdx" value="${groIdx}" />
		<ul class="adminView Write">
			<li>
				<p class="tit">일련번호(ULID)</p>
				<input type="text" id="assUlid" name="assUlid" value="" placeholder="분류와 직원을 선택하면 자동으로 생성" readonly>
			</li>
			<li>
				<p class="tit">자산이미지</p>
				<div class="cont filebox">
					<label for="uploadFile">파일찾기</label> 
					<input type="file" name="uploadFile" id="uploadFile" class="upload-hidden">
					<input class="upload-name" id="showExFilename"  value="파일선택" disabled="disabled">
					<input type="hidden" name="fileName" id="fileName" />
				</div>
			</li>
			<li>
				<p class="tit">자산명 <span>*</span></p>
				<p class="cont"><input type="text" id="assName" name="assName" placeholder="자산명을 입력해주세요" onkeyup='saveValue(this);'></p>
			</li>
			<li>
				<p class="tit">분류 <span>*</span></p>
				<p class="cont">
					<select id="assCat" name = "assCatIdx">
						<option value="">분류 선택</option>
                        <c:forEach var="assetCategoryVO" items="${assetCategoryList }">
                        	<option name="${assetCategoryVO.assCatCode }" value="${assetCategoryVO.assCatIdx }">${assetCategoryVO.assCatName }</option>
                        </c:forEach>
					</select>
				</p>
				<p class="edit"><a onclick="window.open('../popup/contWriteAssetCategory.do', '자산분류등록팝업', 'width=500, height=335')" href="#none">분류 추가</a></p>
			</li>
			<li>
				<p class="tit">상태 <span>*</span></p>
				<p class="cont">
					<select id="staIdx" name="staIdx">
						<option value="">상태 선택</option>
						<c:forEach var="stateListVO" items="${stateList }">
                        	<option value="${stateListVO.staIdx }">${stateListVO.staName }</option>
                        </c:forEach>
					</select>
				</p>
				<p class="edit"><a onclick="window.open('../popup/contWriteItmState.do', '자산상태등록팝업', 'width=500, height=335')" href="#none">상태 추가</a></p>
			</li>
			<li>
				<p class="tit">위치 <span>*</span></p>
				<p class="cont">
				<input style="display:none" id="locIdx" name="locIdx" value="" />
				<a onclick="window.open('../popup/locationPop.do', '위치선택팝업', 'width=500, height=335')" href="#none" class="popbtn">위치 선택</a><span class="name" id="locName"></span></p>

				<p class="edit"><a onclick="window.open('../popup/contWriteItmLocation.do', '위치등록팝업', 'width=500, height=335')" href="#none">위치 추가</a></p>
			</li>
			<li>
				<p class="tit">사용직원 <span>*</span></p>
				<input type="hidden" id= "empIdx" name="empIdx" value="" />
				<p class="cont"><a onclick="window.open('../popup/searchPop.do', '직원등록팝업', 'width=700, height=435')" href="#none" class="popbtn">직원 선택</a><span class="name" id="empName" value=""></span></p>
			</li>
		</ul>
		
		<h3>구매 정보</h3>
		<ul class="adminView Write">
		<li>
				<p class="tit">구매처 </p>
				<p class="cont">
				<input style="display:none" id = "supIdx" name="supIdx" value="" />
				<a onclick="window.open('../popup/supplierPop.do', '구매처선택팝업', 'width=500, height=335')" href="#none" class="popbtn">구매처 선택</a><span class="name" id="supName"></span></p>
			
				<p class="edit"><a onclick="window.open('../popup/contWriteItmSupplier.do', '구매처추가팝업', 'width=700, height=435')" href="#none">구매처 추가</a></p>
			</li>
			<li>
				<p class="tit">최초 구매일</p>
				<p class="cont"><input name="buyDate" type="text" placeholder="최초 구매일을 입력해주세요" readonly class="datepicker"></p>
			</li>
			<li>
				<p class="tit">가격(원)</p>
				<p class="cont"><input id="price" name="price" type="text" placeholder="가격(원)을 입력해주세요" on></p>
			</li>
		</ul>

		<p class="pagebtn">
        <a href="assetList.do" class="del">취소</a>
         
        <a href="javascript:formSubmit();" class="comp">등록</a></p>

	</form>
	</div>
	<%@ include file="/WEB-INF/jsp/itman/_inc/footer.jsp" %>
	
	<c:if test="${!empty msg }">
		<script>
			alert("${msg }");
		</script>
	</c:if>
	
</body>
	<script>
		function formSubmit(){
			$assName_empty = $("#assName").val().trim()
			$locName_empty = $("#locName").html().trim();
			if(!$assName_empty){
				alert("자산명을 입력해 주세요!");
			}else if(!$assName_empty){
				alert("위치를 선택해 주세요!");
			}
			else{
				$assUlid = $("#assUlid").val().replace(/ /g, '');
				sessionStorage.clear();
				$("#assUlid").val($assUlid);
				$("#fileName").val($("#showExFilename").val());
				$("#frm").submit();
			}	
		}
		
        /* input에 입력된 값 로컬스토리지에 저장하기 */
        function saveValue(e){
            var id = e.id;
            var val = e.value;
            sessionStorage.setItem(id, val);
        }
        
		/* 로컬스토리지에 저장된 값으로 input 채우기 */ 
		function getSavedValue(v){
			if(!sessionStorage.getItem(v)){
				// 사용자가 입력하지 않았다면 defualt 값을 반환 
				return v.value;
			}
			return sessionStorage.getItem(v);
		}

		// JSP 값을 안전하게 JS 문자열로 보관
		var GRO_IDX = '<c:out value="${groIdx}"/>';

		function requestUlidIfPossible(){
			if(GRO_IDX !== '' && $("#assCat").val() !== '' && $("#empIdx").val() !== ''){
				$.ajax({
					url: "./assetAutoUlidProc.do",
					type: 'POST',
					data: {
						groIdx: GRO_IDX,
						assCatIdx: $("#assCat").val(),
						empIdx:  $("#empIdx").val()
					},
					dataType: 'json',
					success: function(res) {
						if (res.status === 'success') {
							$("#assUlid").val(res.assUlid);
						} else {
							alert('ULID 생성 실패: ' + res.message);
						}
					},
					error: function(xhr, status, error) {
						console.error('AJAX 오류:', status, error);
					}
				});	
			}
		}


		$(document).ready(function(){

			document.getElementById('price').addEventListener('input', function (e) {
				let value = e.target.value;

				// 숫자만 남기기
				value = value.replace(/[^0-9]/g, '');

				// 천 단위 콤마 추가
				value = value.replace(/\B(?=(\d{3})+(?!\d))/g, ',');

				e.target.value = value;
			});		
			
			$.datepicker.setDefaults({
				dateFormat: 'yy-mm-dd',
				prevText: '이전 달',
				nextText: '다음 달',
				monthNames: ['1월','2월','3월','4월','5월','6월',
							 '7월','8월','9월','10월','11월','12월'],
				monthNamesShort: ['1월','2월','3월','4월','5월','6월',
								  '7월','8월','9월','10월','11월','12월'],
				dayNames: ['일','월','화','수','목','금','토'],
				dayNamesShort: ['일','월','화','수','목','금','토'],
				dayNamesMin: ['일','월','화','수','목','금','토'],
				showMonthAfterYear: true,
				yearSuffix: '년'
			});
			
			$(".datepicker").datepicker();

			if(sessionStorage.getItem("showExFilename") != ""){
				$("#showExFilename").val(sessionStorage.getItem("showExFilename"));
			}

			if(sessionStorage.getItem("price") != ""){
				$("#price").val(sessionStorage.getItem("price"));
			}

			var assName = getSavedValue("assName");
			$("#assName").val(assName);
			
			// ==================================================
			// =======================uuid=======================
			// ==================================================
				
			$('#assCat').on('change', function () {
				requestUlidIfPossible();
        	})
			
			$('#empIdx').on('change', function () {
				requestUlidIfPossible();
        	})

			// 팝업에서 직원 선택 후 창이 닫히고 포커스가 돌아올 때도 시도
			$(window).on('focus', function(){
				requestUlidIfPossible();
			});


	})

		

	</script>
</html>
