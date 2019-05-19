<%@ page language="java" contentType="text/html; charset=cp1251"
    pageEncoding="cp1251"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="cp1251">
	<title>Sign up</title>
</head>

<body bgcolor=#E6E6FA>

	<center>
	<h2>Sign in library</h2><p>
		<form method="get" action="RegistrIn">
    	<table>
        	<tr>
            	<td><label for="login">login</label></td>
            	<td><input  style="width:160px;height:17px" id="login" type="text" name="login"></td>
       		</tr>
        	<tr>
            	<td><label for="password">Password</label></td>
            	<td><input  style="width:160px;height:17px" id="password" type="password" name="password"></td>
        	</tr>
        	<tr>
            	<td><label for="text">firstName</label></td>
            	<td><input  style="width:160px;height:17px" id="text" type="text" name="firstName"></td>
       		</tr>
       		<tr>
            	<td><label for="text">lastName</label></td>
            	<td><input  style="width:160px;height:17px" id="text" type="text" name="lastName"></td>
       		</tr>
    	</table><br>
    	<input style="width:80px;height:35px" type="submit" value="submit"><br>
	</form>
	</center>
	
</body>
</html>