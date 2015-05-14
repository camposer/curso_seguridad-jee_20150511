<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Inicio</title>
</head>
<body>
	<h1>Curso de Seguridad JEE</h1>
	<a href="/blog-web/articulo/listar">Listar artículos</a> (protegido con seguridad declarativa, usuario: user, rol: USER)<br>
	<a href="/blog-web/articulo/admin">Administrar artículos</a> (protegido con seguridad declarativa, usuario: admin, rol: ADMIN)<br>
	<a href="/blog-web/sesion/cerrar">Cerrar sesión</a>
</body>
</html>