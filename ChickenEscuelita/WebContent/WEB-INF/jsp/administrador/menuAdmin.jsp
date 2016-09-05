<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/menu.css" />
</head>
<body>
	<!-- Menu Admin -->
	<!-- usuarios link -->
	<form:form action="usuarios" method="post" >
		<input class="menu" type="submit" value=<spring:message code="usuarios"/> />
	</form:form>

	<!-- parametros link -->
	<form:form action="parametros" method="post">
		<input class="menu" type="submit" value=<spring:message code="parametros"/> />
	</form:form>


</body>
</html>
