<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url value="/ingroup/emploList.do?groIdx=${groIdx}" var="emploList"/>

<script type="text/javascript">

    <c:if test="${!empty msg}">
	    alert("${msg}");
	</c:if>
        
    // 이전 페이지로 돌아갑니다.
    window.opener.location.href="${emploList}";
   	window.close();
        
</script>
