<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administrador</title>
</head>
<body>
	<!-- NavBar -->
	<c:set var="Title" value="Administrador" scope="request" />
	<c:set var="Nombre" value="${usuarioActual.getNombre() }" scope="request" />
	<jsp:include page="../template/navbar.jsp"></jsp:include>
	<!-- Menu -->
	<jsp:include page="../administrador/menuAdmin.jsp"></jsp:include>
	
	PAGINA PRINCIPAL ADMINISTRADOR
	
</body>
</html>