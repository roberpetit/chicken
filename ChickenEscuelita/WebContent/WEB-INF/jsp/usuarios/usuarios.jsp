<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title><spring:message code="usuarios"/></title>

</head>
<body>

<h1 class="page-header"><spring:message code="usuarios"/></h1>

	<div class="panel-group">
		<div class="panel panel-primary">
			<div class="panel-body">
				<table id="tablita" class="display order-column" cellspacing="0"
					width="100%">
					<thead>
						<tr>
							<th><spring:message code="nombreUsuario" /></th>
							<th><spring:message code="nombre" /></th>
							<th><spring:message code="apellido" /></th>
							<th><spring:message code="perfiles"/></th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	
	<form:form id="formModificar" action="usuariosModificar" method="post" commandName="usuarioNM">
		<form:input id="id" path="id" type="hidden"/>
		<form:input id="nombreUsuario" path="nombreUsuario" type="hidden"/>
		<form:input id="nombre" path="nombre" type="hidden"/>
		<form:input id="apellido" path="apellido" type="hidden"/>
		<form:input id="contrasenia" path="contrasenia" type="hidden"/>
		<form:input id="borrado" path="borrado" type="hidden"/>
	</form:form>
	
	<c:set var="value">
		<spring:message code="mensajeBorrar" />
	</c:set>
	<input id="mensajeBorrar" type="hidden" value="${value}" />
	
	<c:set var="borrar">
		<spring:message code="borrar" />
	</c:set>

	<c:set var="modificar">
		<spring:message code="modificar" />
	</c:set>

<div class="wait"></div>

<script>

$(document).ready(function(){

	var table = $('#tablita').DataTable( {
		language: i18n(),
		ajax: "usuariosJson",
	    columns: [
	        {data: "nombreUsuario" },
	        {data: "nombre" },
	        {data: "apellido" },
	        {data: "listaPerfiles[, ].nombre" },
	        {defaultContent:'<button class="btn btn-danger" id="borrar">${borrar}</button>'},
	        {defaultContent:'<button class="btn btn-warning" id="modificar">${modificar}</button>'}
	        ],
	    select:true,
	    paging:true,
	    pageLength:50,
	    ordering:true,
	    dom: 'Bfrtip',
	    buttons: [
	              {
	                  text: '<button class="btn btn-success pull-left" id="nuevo"><spring:message code="nuevo"/></button>',
	                  action: function ( e, dt, node, config ) {
	                      window.location = "usuariosNuevo";
	                  }
	              }
	          ]
	});
	
	$(document).on({
	    ajaxStart: function() {$("body").addClass("loading");},
	    ajaxStop: function() {$("body").removeClass("loading");}
	});
	
	$('#tablita tbody').on('click', '#borrar', function (e) {
		var data = table.row(this.closest("tr")).data();
		var json = {
			"id" : data["id"],
			"nombreUsuario" : data["nombreUsuario"],
			"nombre" : data["nombre"],
			"apellido" : data["apellido"],
			"contrasenia": data["contrasenia"],
			"borrado" : data["borrado"]
		};
		var mensaje = document.getElementById("mensajeBorrar").value;
		e.preventDefault();
		bootbox.confirm(mensaje, function (response) {
			if (response) {
				$.ajax({
					url : "usuariosBorrarJson",
					type : "DELETE",
					data : JSON.stringify(json),
					dataType : "json",
					contentType : "application/json",
					processData : false,
					complete : function () {
						table.ajax.reload();
					}
				});
			}
		});
	});
	
	
	$('#tablita tbody').on('click', '#modificar', function (e) {
		var data = table.row(this.closest("tr")).data();
		e.preventDefault();
		document.getElementById("id").value = data["id"];
		document.getElementById("nombreUsuario").value = data["nombreUsuario"];
		document.getElementById("nombre").value = data["nombre"];
		document.getElementById("apellido").value = data["apellido"];
		document.getElementById("contrasenia").value = data["contrasenia"];
		document.getElementById("borrado").value = data["borrado"];
		document.getElementById("formModificar").submit();
	});
});

</script>

</body>
</html>