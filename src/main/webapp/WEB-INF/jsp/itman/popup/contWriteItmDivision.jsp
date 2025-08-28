<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>삭제 확인</title>
    <style>
        body {
            font-family: "Segoe UI", Arial, sans-serif;
            background: #f7fafb;
            margin: 0;
            min-width: 340px;
            min-height: 240px;
        }
        .popup-wrap {
            padding: 30px 24px;
            background: #f7fafb;
            min-width: 340px;
        }
        .popup-title {
            font-size: 18px;
            font-weight: 500;
            margin-bottom: 24px;
        }
        .form-row {
            margin-bottom: 36px;
        }
        .form-label {
            font-size: 15px;
            margin-bottom: 10px;
            display: block;
            color: #222;
        }
        .form-input {
            width: 100%;
            height: 38px;
            border: 1px solid #e0e0e0;
            padding: 0 8px;
            font-size: 15px;
            border-radius: 3px;
            box-sizing: border-box;
            background: #fff;
        }
        .popup-actions {
            display: flex;
            gap: 10px;
        }
        .btn {
            flex: 1 1 100px;
            padding: 10px 0 9px 0;
            font-size: 17px;
            border: none;
            border-radius: 4px;
            color: #fff;
            cursor: pointer;
            transition: background .13s;
        }
        .btn-cancel {
            background: #c5393a;
        }
        .btn-delete {
            background: #232325;
        }
        .btn-cancel:hover { background: #ab2f32; }
        .btn-delete:hover { background: #111; }
    </style>
</head>
<body>
    <div class="popup-wrap">
        <form id="deleteForm" method="post" action="${pageContext.request.contextPath}/popup/division/deleteDivisionProc.do">
            <input type="hidden" name="div_idx" value="${param.div_idx}" />
            <div class="popup-title">삭제하시겠습니까?</div>
            <div class="form-row">
                <label class="form-label" for="del_reason">비고</label>
                <input type="text" class="form-input" id="del_reason" name="del_reason" autocomplete="off" />
            </div>
            <div class="popup-actions">
                <button type="button" class="btn btn-cancel" onclick="window.close()">취소</button>
                <button type="submit" class="btn btn-delete">삭제</button>
            </div>
        </form>
    </div>

    <script>
        // 엔터로 submit 방지 (예시)
        document.getElementById('deleteForm').onsubmit = function() {
            return confirm('정말로 삭제하시겠습니까?');
        }
    </script>
</body>
</html>
