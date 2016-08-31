<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<title>Parametro</title>

</head>
<body>
	<h1>Parametro</h1>

	<form:form method="POST" action="parametrosModificarNuevo"	commandName="parametro">
		<form:input path="id" type="hidden" value="${parametro.getId()}"/>
	
		<table>
			<tr>
				<td><spring:message code="descripcion" /></td>
				<td><form:input path="descripcion" /></td>
			</tr>
			<tr>
				<td><spring:message code="valor" /></td>
				<td><form:input path="valor" /></td>
			</tr>
			<tr>
				<td colspan="4" style="text-align: center;">
				<input type="hidden" name="flagNuevoModificar" value="${flagNuevoModificar}"/>
				<input type="submit" value="<spring:message code="guardar"/>" /> </td>
			</tr>
		</table>
	</form:form>
</body>
</html>