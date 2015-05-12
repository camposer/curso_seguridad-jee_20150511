<%@page import="model.Articulo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Artículos</title>
	<style>
		h1 {
			text-align: center;
		}
		.articulo {
			width: 80%;
			margin-left: auto;
			margin-right: auto;
		}
		.articulo .titulo {
			background-color: gray;
		}
		.articulo .contenido {
			background-color: white;
		}
	</style>
</head>
<body>
	<h1>Artículos</h1>
	<%
		List<Articulo> articulos = (List<Articulo>) request.getAttribute("articulos");
		if (articulos != null)  for (Articulo a : articulos) { 
	%>
		<div class="articulo">
			<div class="titulo"><%= a.getTitulo() %></div>
			<div class="contenido"><%= a.getContenido() %></div>
		</div>
		<br>
	<% 
		}
	%>
</body>
</html>