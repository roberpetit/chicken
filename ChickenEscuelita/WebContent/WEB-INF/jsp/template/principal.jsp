<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title><spring:message code="nombreApp"/></title>
<jsp:include page="../template/importacion.jsp"></jsp:include>

</head>
<body>


	<!-- NavBar -->
	<c:set var="Title" scope="request" value="${usuarioActual.getListaPerfiles().get(0).getNombre()}"/>
	<c:set var="Nombre" value="${usuarioActual.getNombre() }"	scope="request" />
	<jsp:include page="../template/navbar.jsp"></jsp:include>

	<div class="alineacion">
		<!-- Menu -->
		<jsp:include page="../template/menu.jsp"></jsp:include>
	</div>
	<div class="pantalla">
		<jsp:include page="../${pageToLoad}.jsp"></jsp:include>
	</div>


<script type="text/javascript">
$(document).ready(function() {
    $('#tablita').DataTable();
} );
</script>
</body>
</html>