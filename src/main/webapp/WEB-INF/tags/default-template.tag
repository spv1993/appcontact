<!DOCTYPE html>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@attribute name="title" fragment="true" %>
<%@attribute name="header" fragment="true" %>
<html ng-app="app">
<head>
	<title><jsp:invoke fragment="title"/></title>
	
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link href="<c:url value='/styles/vendors.css' />" rel="stylesheet"></link>	
</head>
<body>
	<jsp:doBody/>
	
	<script src="<c:url value='/scripts/vendors.js' />"></script>
	<script src="<c:url value='/scripts/app.js' />"></script>
	<script src="<c:url value='/scripts/controller/contact.controller.js' />"></script>
	<script src="<c:url value='/scripts/service/contact.service.js' />"></script>
</body>
</html>