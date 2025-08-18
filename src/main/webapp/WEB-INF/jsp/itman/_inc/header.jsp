<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- 컨텍스트 경로 설정 -->
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<header>
    <div class="h_left">
        <p class="ham"><a href="#none"><span></span><span></span><span></span></a></p>
        <h1><a href="${ctx}/groupProc.do?groIdx=${groIdx}&id=0"><img src="${ctx}/upload/group/${groupVO.groImg}" onerror="this.src='${ctx}/itman_static/_img/groupImage/no_img.png'" alt="그룹 로고" /></a></h1>
        <div class="tmn">
            <p class="m_tit"></p>
            <ul class="list">
                <li class="dash"><a href="${ctx}/groupProc.do?groIdx=${groIdx}&id=0" class="${pageNumDepth01 == 0 ? 'on' : ''}">대시보드</a></li>
                <li><a href="${ctx}/groupProc.do?groIdx=${groIdx}&id=1" class="${pageNumDepth01 == 1 ? 'on' : ''}">자산 관리<span>${groupVO.assetCount}</span></a></li>
                <li><a href="${ctx}/groupProc.do?groIdx=${groIdx}&id=2" class="${pageNumDepth01 == 2 ? 'on' : ''}">직원 관리<span>${groupVO.employeeCount}</span></a></li>
                <li><a href="${ctx}/groupProc.do?groIdx=${groIdx}&id=3" class="${pageNumDepth01 == 3 ? 'on' : ''}">부서 관리<span>${groupVO.divisionCount}</span></a></li>
                <li><a href="${ctx}/groupProc.do?groIdx=${groIdx}&id=4" class="${pageNumDepth01 == 4 ? 'on' : ''}">직위 관리<span>${groupVO.positionCount}</span></a></li>
                <li><a href="${ctx}/groupProc.do?groIdx=${groIdx}&id=5" class="${pageNumDepth01 == 5 ? 'on' : ''}">구매처 관리<span>${groupVO.supplierCount}</span></a></li>
                <li><a href="${ctx}/groupProc.do?groIdx=${groIdx}&id=6" class="${pageNumDepth01 == 6 ? 'on' : ''}">위치 관리<span>${groupVO.locationCount}</span></a></li>
                <li><a href="${ctx}/groupProc.do?groIdx=${groIdx}&id=7" class="${pageNumDepth01 == 7 ? 'on' : ''}">자산 분류 관리<span>${groupVO.assetCategoryCount}</span></a></li>
                <li><a href="${ctx}/groupProc.do?groIdx=${groIdx}&id=8" class="${pageNumDepth01 == 8 ? 'on' : ''}">자산 상태 관리<span>${groupVO.stateCount}</span></a></li>
                <li><a href="${ctx}/groupProc.do?groIdx=${groIdx}&id=9" class="${pageNumDepth01 == 9 ? 'on' : ''}">직원 상태 관리<span>${groupVO.empStateCount}</span></a></li>
                <li><a href="${ctx}/groupProc.do?groIdx=${groIdx}&id=10" class="${pageNumDepth01 == 10 ? 'on' : ''}">자산 히스토리</a></li>
            </ul>
        </div>
    </div>
    <div class="h_right">
        <a href="#" class="hr_btn"><span></span><span></span><span></span></a>
        <p class="hr_box">
            <span>${sessionScope.memName}님</span>
            <a href="${ctx}/user/mypage.do" class="mypage">마이페이지</a>
            <a href="${ctx}/user/logoutProc.do" class="logout">로그아웃</a>
            <a href="${ctx}/group.do" class="group">전체그룹메인</a>
        </p>
    </div>
</header>

<!-- 사이드메뉴 -->
<div class="smn">
    <ul class="smn_list">
        <c:forEach var="groupVO" items="${groupList}">
            <li>
                <p><a href="#">${groupVO.groName}</a></p>
                <ul>
                    <li><a href="${ctx}/groupProc.do?groIdx=${groupVO.groIdx}&id=0">대시보드</a></li>
                    <li><a href="${ctx}/groupProc.do?groIdx=${groupVO.groIdx}&id=1">자산 관리</a></li>
                    <li><a href="${ctx}/groupProc.do?groIdx=${groupVO.groIdx}&id=2">직원 관리</a></li>
                    <li><a href="${ctx}/groupProc.do?groIdx=${groupVO.groIdx}&id=3">부서 관리</a></li>
                    <li><a href="${ctx}/groupProc.do?groIdx=${groupVO.groIdx}&id=4">직위 관리</a></li>
                    <li><a href="${ctx}/groupProc.do?groIdx=${groupVO.groIdx}&id=5">구매처 관리</a></li>
                    <li><a href="${ctx}/groupProc.do?groIdx=${groupVO.groIdx}&id=6">위치 관리</a></li>
                </ul>
            </li>
        </c:forEach>
    </ul>
    <p class="go_itman"><a href="${ctx}/index.do"><span><img src="${ctx}/itman_static/_img/_favicon/apple-icon-180x180.png" alt="아이티맨 메인 아이콘" /></span>아이티맨 메인으로가기</a></p>
</div>

<script>
    function movePage(groupIDX, pageIDX) {
        location.href='${ctx}/groupProc.do?group=' + groupIDX + '&id=' + pageIDX;
    }
</script>

<!-- 비고팝업 -->
<div class="more_layer">
    <div class="bg"></div>
    <div class="pop-layer">
        <div class="pop_tit">
            <p class="title">비고</p>
            <p class="close"><a class="cbtn" href="#none"><img src="${ctx}/itman_static/_img/close.png" alt="닫기" /></a></p>
        </div>
        <div class="pop_cont">
        </div>
    </div>
</div>
<!-- 비고팝업 끝 -->
