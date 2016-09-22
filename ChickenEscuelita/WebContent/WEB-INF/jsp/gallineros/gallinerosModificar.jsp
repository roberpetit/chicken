<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="gallinerosModificar"/></title>
</head>
<body>

<h1 class="page-header"><spring:message code="gallinerosModificar"/></h1>

<form:form class="form-horizontal maxwid" id="formModificar" action="gallinerosProcesarModificar" method="post" commandName="gallinero">
	<form:input id="id" path="id" type="hidden" value="${gallinero.getId()}"/>
	<div class="form-group">
		<form:label class="control-label col-sm-2" path="nombre"><spring:message code="nombre"/>:</form:label>
		<div class="col-sm-10">
			<form:input class="form-control" id="nombre" path="nombre" value="${gallinero.getNombre()}" />
		</div>
	</div>
	<div class="form-group">
		<form:label class="control-label col-sm-2" path="usuarioId"><spring:message code="usuario"/>:</form:label>
		<div class="col-sm-10">
			<form:select class="form-control" style="width:auto;" id="usuarioId" path="usuarioId" required="required">
				<form:option value=""><spring:message code="seleccionar" /></form:option>
					<c:forEach items="${listaUsuarios}" var="usuario">
						<form:option value="${usuario.getId()}">
							<c:out value="${usuario.getNombre()}"></c:out>
						</form:option>
					</c:forEach>
			</form:select>
		</div>
	</div>
	<div class="form-group">
		<form:label class="control-label col-sm-2" path="stockGallinas"><spring:message code="stock"/>:</form:label>
		<div class="col-sm-10">
			<form:input class="form-control" id="stockGallinas" path="stockGallinas"  value="${gallinero.getStockGallinas()}" required="required"/>
		</div>
	</div>
	<div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
			<input class="btn btn-default" id="botonGuardar" type="button" value=<spring:message code="guardar"/> />
		</div>
	</div>
</form:form>
	
<c:set var="value">
	<spring:message code="mensajeModificar" />
</c:set>
<input id="mensajeModificar" type="hidden" value="${value}" />
	
<p id="errores"></p>

<c:set var="mensajeErrorNombreVacio">
	<spring:message code="mensajeErrorNombreVacio" />
</c:set>

<c:set var="mensajeErrorNombreUnico">
	<spring:message code="mensajeErrorNombreUnico" />
</c:set>

<c:set var="mensajeErrorStockMinimo">
	<spring:message code="mensajeErrorStockMinimo" />
</c:set>

<c:set var="mensajeErrorUsuarioInvalido">
	<spring:message code="mensajeErrorUsuarioInvalido" />
</c:set>
	
<form:form id="formAtras" action="atras" method="post">
	<input id="url" type="hidden" name="url" />
	<input id="botonAtras" type="button" value=<spring:message code="atras"/> />
</form:form>

<div class="wait"></div>
	
<script>

$(document).on({
    ajaxStart: function() {$("body").addClass("loading");},
    ajaxStop: function() {$("body").removeClass("loading");}
});

var mensajesError = {
		mensajeErrorNombreVacio: "${mensajeErrorNombreVacio}",
		mensajeErrorNombreUnico: "${mensajeErrorNombreUnico}",
		mensajeErrorStockMinimo: "${mensajeErrorStockMinimo}",
		mensajeErrorUsuarioInvalido: "${mensajeErrorUsuarioInvalido}",
};

$('#botonGuardar').on('click', function (e) {
	var mensaje = document.getElementById("mensajeModificar").value;
    e.preventDefault();
    bootbox.confirm(mensaje, function (response) {        
        if(response) {
        	var json = {
        			"id" : document.getElementById("id").value,
        			"nombre" : document.getElementById("nombre").value,
        			"usuarioId" : document.getElementById("usuarioId").value,
        			"stockGallinas" : document.getElementById("stockGallinas").value
        		};
        	$.ajax({
        		url : "gallinerosModificarJson",
        		type : "POST",
        		data : JSON.stringify(json),
        		dataType : "json",
        		contentType : "application/json",
        		processData : false,
        		success: function(errores){
        			var mensaje = "";
        			for(var i = 0; i < errores.length; i++) {
        				mensaje += mensajesError[errores[i].code] + "<br>";
        			}
        			document.getElementById("errores").innerHTML = mensaje;
        		},
        		error: function(){
        			window.location = "gallineros";
        		}
        	});
        }
    });
});

$('#botonAtras').on('click', function(e) {
	e.preventDefault();
	var url = document.URL;
	document.getElementById("url").value = url;
	document.getElementById("formAtras").submit();
});

</script>

</body>
</html>