<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!-- JSTL PREFIJO C -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
				<!--request.getContextPath() llama a la acción del servlet a ejecutar /action  -->
				<li><a href="<%=request.getContextPath()%>/list" class="nav-link">Votantes </a></li>
			</ul>
		</nav>
	</header>
	
	<br>
	
	<div class="row">
		<div class="container">
			<h3 class="text-center">Listado de Votantes </h3>
			<hr>
			
			<div class="container text-left">
				<a href="<%=request.getContextPath()%>/new" class="btn" style="background-color: red">Nuevo Votante</a>
			</div>
			
			<br>
			
			<table class="table table-bordered">
				<thead>
					<tr>				
						<th>Id</th>											
						<th>Nombre del Votante</th>
						<th>Email </th>
						<th>Documento </th>
						<th>Tipo de Documento </th>
						<th>Elección </th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="votante" items="${listV}">
						<tr>
							<td><c:out value="${votante.id}" /></td>
							<td><c:out value="${votante.nombre}" /></td>
							<td><c:out value="${votante.email}" /></td>
							<td><c:out value="${votante.documento}" /></td>
							<td><c:out value="${votante.tipodocumento}" /></td>
							<td><c:out value="${votante.eleccion}" /></td>
							<td>
								<a href="edit?id=<c:out value='${votante.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; 
								<a href="delete?id=<c:out value='${votante.id}' />">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	
</body>
</html>