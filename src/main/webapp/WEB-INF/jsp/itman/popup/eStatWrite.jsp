<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="ko">
<head>
    <%-- PHP include를 jsp:include로 변경 --%>
    <%@ include file="/WEB-INF/jsp/itman/_inc/title.jsp" %>
</head>
<body>
    <div id="popup">
        <div class="pop_tit">
            <%-- PHP echo를 EL(Expression Language)로 변경 --%>
            <p class="title"><c:out value="${groName}"/>&nbsp; 직원 상태 추가 팝업</p>
        </div>
        <div class="pop_cont">
            <%-- form의 action을 Controller의 URL로 변경 --%>
            <form method="post" id="empstateCateForm" action="<c:url value='/popup/addEStat.do'/>">
                
                <%-- Controller로 groIdx를 전달하기 위한 hidden input 추가 --%>
                <input type="hidden" name="groIdx" value="<c:out value='${groIdx}'/>" />

                <ul class="contEdit">
                    <li>
                        <p class="tit">직원 상태명<span>*</span></p>
                        <%-- input 필드명은 VO의 필드명과 일치시키는 것이 좋습니다 --%>
                        <p class="cont"><input type="text" id="stName" name="stName" placeholder="직원 상태명을 입력해주세요." value=""></p>
                    </li>
                    <li>
                        <p class="tit">상태코드번호<span>*</span></p>
                        <p class="cont"><input type="text" id="stCode" name="stCode" placeholder="직원 상태 코드 번호를 입력해 주세요. ex)000"></p>
                    </li>
                    
                    <li>
                        <p class="tit">비고</p>
                        <p class="cont"><input type="text" name="slNote"/></p>
                    </li>
                </ul>
                <p class="pop_btn">
                    <a href="javascript:window.close();" class="del">취소</a>
                    <a href="javascript:formSubmit();" class="comp">등록</a>
                </p>
            </form>
        </div>
    </div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    function formSubmit() {
        // id를 수정한 필드명에 맞게 변경
        var empNameEmpty = $("#stName").val().trim();
        var empCodeEmpty = $("#stCode").val().trim();

        if (!empNameEmpty || !empCodeEmpty) {
            alert("필수 값을 입력해주세요!");
        } else {
            $("#empstateCateForm").submit();
        }
    }
</script>
</body>
</html>