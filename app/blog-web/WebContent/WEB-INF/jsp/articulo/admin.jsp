<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Admin de Artículos</title>
</head>
<body>
	<h1>Artículos</h1>
	<form action="/blog-web/articulo/agregar" method="post">
		Título: <input type="text" name="titulo"><br>
		Contenido: <textarea rows="4" cols="4" name="contenido"></textarea><br>
		<input type="submit" value="Agregar">
	</form>
</body>
</html>