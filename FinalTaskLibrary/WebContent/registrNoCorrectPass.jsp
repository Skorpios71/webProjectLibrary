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
            	<td><input style="width:130px;height:17px" id="login" type="text" name="login"></td>
       		</tr>
        	<tr>
            	<td><label for="password">Password</label></td>
            	<td><input style="width:130px;height:17px" id="password" type="password" name="password"></td>
        	</tr>
        	<tr>
        		<font size="3" color="red">Passwords are not the same!</font>
            	<td><label for="password2">Repeat password</label></td>
            	<td><input style="width:130px;height:17px" id="password2" type="password" name="password2"></td>
        	</tr>
        	<tr>
            	<td><label for="text">firstName</label></td>
            	<td><input style="width:130px;height:17px" id="text" type="text" name="firstName"></td>
       		</tr>
       		<tr>
            	<td><label for="text">lastName</label></td>
            	<td><input style="width:130px;height:17px" id="text" type="text" name="lastName"></td>
       		</tr>
    	</table><br>
    	<form>
			<input type="button" value="SAVE" onClick='location.href="login.jsp"'>
		</form>
	</form>
	</center>
	
</body>
</html>