<!DOCTYPE html>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@attribute name="title" fragment="true" %>
<%@attribute name="header" fragment="true" %>
<html>
<head>
	<title><jsp:invoke fragment="title"/></title>
	
	<spring:url value="/styles/common.css" var="commoncss"/>
    <link href="${commoncss}" rel="stylesheet" />
    
    <spring:url value="/scripts/common.js" var="commonjs"/>
    <script src="${commonjs}"></script>
	
</head>
<body>
	<jsp:doBody/>
</body>
</html>