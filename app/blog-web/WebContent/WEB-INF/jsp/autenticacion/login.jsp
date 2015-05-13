<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Login</title>
	<script src="/blog-web/js/sha256.js"></script>
	<script>
		var login = function() {
			var username = document.getElementById("username");
			var password = document.getElementById("password");
			var jPassword = document.getElementById("j_password");
			
			if (username && password) {
				var hash = CryptoJS.SHA256(password.value);
				jPassword.value = hash;
				
				document.forms.formLogin.submit();
			}
		};
	</script>
</head>
<body>
	<form name="formLogin" action="j_security_check" method="post">
		Usuario: <input type="text" id="username" name="j_username"><br>
		Contraseña: 
		<input type="hidden" name="j_password" id="j_password">
		<input type="text" id="password"><br> 
		<input type="button" value="Login" onclick="login()">
	</form>
</body>
</html>