<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="ko">
<head>
    <c:set var="ctx" value="${pageContext.request.contextPath}" />
    <%@ include file="/WEB-INF/jsp/itman/_inc/title.jsp" %>
    <title>직원 상태 수정 팝업</title>
    <style>
        /* 간단한 스타일 예시 (실제 프로젝트의 CSS 파일을 따릅니다) */
        #popup .contEdit li { display: flex; align-items: center; margin-bottom: 10px; }
        #popup .contEdit .tit { width: 120px; font-weight: bold; }
        #popup .contEdit .tit span { color: #8A2BE2; } /* 보라색 별표 */
        #popup .contEdit .cont input[type="text"] { width: 250px; padding: 5px; }
        #popup .pop_btn { text-align: center; margin-top: 20px; }
        #popup .pop_btn a { display: inline-block; padding: 8px 15px; text-decoration: none; color: white; border-radius: 4px; margin: 0 5px;}
        #popup .pop_btn .del { background-color: #DC3545; } /* 취소 버튼 (빨간색) */
        #popup .pop_btn .comp { background-color: #343A40; } /* 수정 버튼 (어두운 회색) */
    </style>
</head>
<body>
    <div id="popup">
        <div class="pop_tit">
            <p class="title">직원 상태 수정 팝업</p>
        </div>
        <div class="pop_cont">
            <form:form id="itmStateForm" method="post" action="${ctx}/popup/state/updateStateProc.do" modelAttribute="stateVO">
                
                <form:hidden path="stIdx"/>

                <ul class="contEdit">
                    <li>
                        <p class="tit">직원 상태명<span>*</span></p>
                        <p class="cont">
                            <form:input path="stName" placeholder="직원 상태명을 입력해주세요." />
                        </p>
                    </li>
                    <li>
                        <p class="tit">상태코드번호<span>*</span></p>
                        <p class="cont">
                            <form:input path="stCode" placeholder="직원 상태 코드 번호를 입력해 주세요. ex)000" />
                        </p>
                    </li>
<!--                     <li> -->
<!--                         <p class="tit">사용 여부</p> -->
<!--                         <p class="cont"> -->
<%--                             <form:radiobutton path="stYn" value="Y" /> 사용 &nbsp;&nbsp; --%>
<%--                             <form:radiobutton path="stYn" value="N" /> 미사용 --%>
<!--                         </p> -->
<!--                     </li> -->
                    <li>
                        <p class="tit">비고</p>
                        <p class="cont">
                            <form:input path="slNote" />
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
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        function formSubmit() {
            var stName = $("#stName").val().trim();
            var stCode = $("#stCode").val().trim();
            if (stCode === '' || stName === '') {
                alert("필수 값을 입력해주세요!");
            } else {
                $("#itmStateForm").submit();
            }
        }
    </script>
</body>
</html>