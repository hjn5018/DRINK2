<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/user/login.do" var="loginUrl"/>

<script type="text/javascript">

	<c:if test="${!empty msg}">
	    alert("${msg}");
	</c:if>
	
	window.opener.location.href = '${loginUrl}';
	window.close();
	
</script>
