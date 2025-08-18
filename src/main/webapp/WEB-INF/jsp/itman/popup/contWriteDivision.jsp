<%-- /WEB-INF/jsp/itman/popup/division/contWriteDivision.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="ko">
<head>
    <c:set var="ctx" value="${pageContext.request.contextPath}" />
    <%@ include file="/WEB-INF/jsp/itman/_inc/title.jsp" %>
    <title>부서 수정 팝업</title>
    <link rel="stylesheet" href="${ctx}/css/common.css" />
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>

	<div id="popup">
		<div class="pop_tit">
			<p class="title">부서 수정 팝업</p>
		</div>
		<div class="pop_cont">
			<form:form id="itmDivisionForm" method="post" action="${ctx}/popup/division/updateDivisionProc.do" modelAttribute="divisionVO">
			    <form:hidden path="divIdx" />
			    <ul class="contEdit">
				    <li>
					    <p class="tit">부서명<span>*</span></p>
					    <p class="cont">
                            <form:input path="divName" id="div_name" placeholder="부서명을 입력해주세요." />
                        </p>
				    </li>
				    <li>
					    <p class="tit">부서코드번호<span>*</span></p>
					    <p class="cont">
                            <form:input path="divCode" id="div_code" placeholder="부서 코드 번호를 입력해 주세요. ex)000" />
                        </p>
				    </li>
				    <li>
					    <p class="tit">사용유무</p>
					    <p class="cont">
                            <form:radiobutton path="divYn" value="Y" id="yes" /><label for="yes">사용</label> 
                            <form:radiobutton path="divYn" value="N" id="no" /><label for="no">사용안함</label>
                        </p>
				    </li>
			    </ul>
                <p class="pop_btn">
                    <a href="javascript:window.close();" class="del">취소</a>
                    <a href="javascript:formSubmit();" class="comp">수정</a>
                </p>
		    </form:form>
        </div>
	</div>
<script>
    function formSubmit(){
        var divName = $("#div_name").val().trim();
        var divCode = $("#div_code").val().trim();
        
        if (!divName || !divCode) {
            alert("필수 값을 입력해주세요!");
        } else {
            $("#itmDivisionForm").submit();
        }
    }
</script>
</body>
</html>