<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    
    <meta charset="UTF-8">
    <title>그룹 생성하기</title>
    
</head>
<body>
    <div id="popup">
        <div class="pop_tit">
            <p class="title">그룹생성하기</p>
        </div>
        <div class="pop_cont">
            <%-- 2. form 태그의 action을 컨트롤러의 주소로 변경 --%>
            <form action="<c:url value='/addGroup.do'/>" name="frm" id="frm" method="post" enctype="multipart/form-data">
                <ul class="contEdit">
                    <li>
                        <p class="tit">그룹 이름</p>
                        <%-- 3. VO의 필드명과 input의 name을 일치시킴 --%>
                        <p class="cont"><input type="text" id="groupName" name="groupName" placeholder="그룹명을 작성해주세요."></p>
                    </li>
                    <li>
                        <p class="tit">그룹 이미지</p>
                        <div class="cont filebox">
                            <label for="ex_filename">파일찾기</label> 
                            <input type="file" id="ex_filename" name="image" class="upload-hidden">
                            <input class="upload-name" value="파일선택" disabled="disabled">
                        </div>
                    </li>
                    <li>
                        <p class="tit">그룹 설명</p>
                        <p class="cont"><textarea id="groupNote" name="groupNote" placeholder="그룹 설명을 작성해주세요."></textarea></p>
                    </li>
                </ul>
                <p class="pop_btn"><a href="javascript:self.close();" class="del">취소</a><a href="javascript:fn_submit();" class="comp">생성</a></p>
            </form>
        </div>
    </div>
</body>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
    var doubleSubmitFlag = false;
    function fn_submit() {
        // 그룹 이름
        if ($("#groupName").val().trim() == "") {
            alert("그룹 이름은 필수입니다.");
            $("#groupName").focus();
            return false;
        } else if (!doubleSubmitFlag) {
            doubleSubmitFlag = true;
            document.frm.submit();
        }
    }
</script>
</html>