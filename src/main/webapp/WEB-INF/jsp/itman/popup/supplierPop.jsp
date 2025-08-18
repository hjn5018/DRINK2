<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<?php
	include "../_inc/dbconn.php";

    /**
     * 세션 체크
     */
    include "../_inc/loginTest.php";
    login_check();
    $group = $_SESSION['group'];

	$like = $_POST['like'];
	$page = $_GET['page'];

	$sql = ""; $sql_count = "";

	$first_limit = 0;
	$count_limit = 5;

	if(empty($page)){
		$first_limit = 0;
	}else{
		$first_limit = ($page-1) * $count_limit;
	}


	if(empty($like)){
		$sql = "SELECT * FROM ITM_SUPPLIER  WHERE GRO_IDX = $group AND DEL_YN = 'N' LIMIT $first_limit, $count_limit";
		$sql_count = "SELECT * FROM ITM_SUPPLIER  WHERE GRO_IDX = $group AND DEL_YN = 'N'";
	}else{
		$sql = "SELECT * FROM ITM_SUPPLIER WHERE GRO_IDX = $group AND DEL_YN = 'N' AND SUP_NAME LIKE '%{$like}%' LIMIT $first_limit, $count_limit";
		$sql_count = "SELECT * FROM ITM_SUPPLIER  WHERE GRO_IDX = $group AND DEL_YN = 'N' AND SUP_NAME LIKE '%{$like}%'";
	}
	
	$result = mysqli_query($dbconn, $sql);
	$result_count = mysqli_query($dbconn, $sql_count);
	$count = mysqli_num_rows($result_count);
	

	$total_page = ceil($count / $count_limit);

    function find_div_name($dbconn, $div_idx){
        $div_sql = "SELECT DIV_NAME FROM ITM_DIVISION WHERE DIV_IDX = {$div_idx} ";
        $div_query = mysqli_query($dbconn, $div_sql);
        $div_result = mysqli_fetch_array($div_query);
        return $div_result["DIV_NAME"];
    }

	function find_pos_name($dbconn, $pos_idx){
        $pos_sql = "SELECT POS_NAME FROM ITM_POSITION WHERE POS_IDX = $pos_idx";
        $pos_query = mysqli_query($dbconn, $pos_sql);
        $pos_result = mysqli_fetch_array($pos_query);
        return $pos_result["POS_NAME"];
    }

?>
<!doctype html>
<html lang="ko">
 <head>
 <%@ include file="/WEB-INF/jsp/itman/_inc/title.jsp" %>
 </head>
<body>

	<div id="popup">
		<div class="pop_tit">
			<p class="title">구매처 찾기</p>
		</div>
		<div class="pop_cont">
			<ul class="contEdit">
				<li>
				<form action="./supplierPop.do" id="frm" method="post" > 
					<p class="cont">
						<input type="text" name="like"  placeholder="구매처를 작성해주세요." class="search"><a href="javascript:form_submit();">검색</a>
					</p>
					</form>
				</li>
			</ul>
			<ul class="popList">
<!-- 				<li class="nocont">검색결과가 없습니다.</li> -->
				<c:forEach var="supplierVO" items="${supplierList }">
	<%-- 				<li><a href="javascript:onClick(<?=$row['SUP_IDX'];?>);"><span class="tit" id = <?="supName".$row['SUP_IDX']?>><?= $row['SUP_NAME']?></a></li> --%>
					<li><a href="javascript:onClick(${supplierVO.supIdx });"><span class="tit" id = supName${supplierVO.supIdx }>${supplierVO.supName }</a></li>
	<%-- 				<input type="hidden" id="<?=$row['SUP_IDX'];?>" value="<?=$row['SUP_IDX'];?>"/> --%>
					<input type="hidden" id="<?=$row['SUP_IDX'];?>" value="<?=$row['SUP_IDX'];?>"/>
				</c:forEach>
			</ul>
			<%-- 
			<p class="paging">
				<a href="supplierPop.do?page=1" class="first"><img src="../_img/first.png"></a>
				<a href="<?=(!$_GET['page']||$_GET['page']==1)?"supplierPop.do?page=1":"supplierPop.do?page=".$i?>" class="prev"><img src="../_img/prev.png"></a>
				<?php
					for($i = 1; $i <= $total_page ; $i++){
				?>
				<a href="supplierPop.do?page=<?=$i?>" class=<?=$i==$_GET['page']||empty($_GET['page'])&& $i ==1?"on":""?>><?=$i;?></a>
				<?php }; ?>
				<a href="<?=$_GET['page']>=$total_page?"supplierPop.do?page=".$total_page:"supplierPop.do?page=".($_GET['page']+1)?>" class="next"><img src="../_img/next.png"></a>
				<a href="<?="supplierPop.do?page=".$total_page?>" class="last"><img src="../_img/last.png"></a>
			</p>
			--%>

			<p class="pop_btn"><a href="javascript:window.close();" class="del">취소</a></p>
		</div>
	</div>

</body>
<script>
function form_submit(){
	document.getElementById("frm").submit();
}
function onClick(idx){
	// result = [];
	// str = document.getElementById(idx).innerHTML;
	// split_str = str.split('/');
	
	// for( const item of split_str){
	// 	result.push(item);
	// }


	opener.document.getElementById("supIdx").value = idx;
	opener.document.getElementById("supName").innerHTML = document.getElementById("supName"+idx).innerHTML;
	window.close();

}
</script>
</html>
