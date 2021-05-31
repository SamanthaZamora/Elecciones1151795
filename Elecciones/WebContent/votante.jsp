<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!-- JSTL PREFIJO C -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PROCESO DE ELECCIÓN</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark" style="background-color: red">
			<div>
				<a href="#" class="navbar-brand"> Gestión de Votantes </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list" class="nav-link"> Votantes </a></li>
			</ul>
		</nav>
	</header>

	<br>

	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${Votante != null}">
					<form action="update" method="post">
				</c:if>

				<c:if test="${Votante == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${Votante != null}"> Editar Votante </c:if>

						<c:if test="${Votante == null}"> Insertar Votante  </c:if>
					</h2>
				</caption>

				<c:if test="${Votante != null}">
					<input type="hidden" name="id" value="<c:out value='${Votante.id}' />" />
				</c:if>

				<br>
				<fieldset class="form-group">
					<label>Estamento</label> 
					<br> 
					<select	name="estamento" class="rounded form-control">
						<c:forEach var="estamento" items="${estamento}">
							<option value="${estamento.id}">${estamento.descripcion }</option>
						</c:forEach>
					</select>
				</fieldset>
				
				<fieldset class="form-group">
					<label>Tipo de Documento</label> 
					<br> 
					<select	name="tipodocumento" class="rounded form-control">
						<c:forEach var="tipodocumento" items="${tipodocumento}">
							<option value="${tipodocumento.id}">${tipodocumento.descripcion}</option>
						</c:forEach>
					</select>
				</fieldset>
				
				<fieldset class="form-group">
					<label>Documento</label> <input type="text"
						value="<c:out value='${Votante.documento}' />" class="form-control"
						name="documento" required="required">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Nombre del Votante</label> <input type="text"
						value="<c:out value='${Votante.nombre}' />" class="form-control"
						name="name" required="required">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Email</label> <input type="email"
						value="<c:out value='${Votante.email}' />" class="form-control"
						name="email" required="required">
				</fieldset>


				<fieldset class="form-group">
					<label>Proceso</label> 
					<br> 
					<select	name="eleccion" class="rounded form-control">
						<c:forEach var="eleccion" items="${eleccion}">
							<option value="${eleccion.id}">${eleccion.nombre}</option>
						</c:forEach>
					</select>
				</fieldset>

				<br>
				<button type="submit" class="btn" style="background-color: red">Guardar</button>
				</form>
			</div>
		</div>
	</div>

</body>
</html>