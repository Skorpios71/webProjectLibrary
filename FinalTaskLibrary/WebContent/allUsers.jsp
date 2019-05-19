<%@page import="ua.library.db.DBManager"%>
<%@ page language="java" contentType="text/html; charset=cp1251"
    pageEncoding="cp1251"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="cp1251">
<title>All users</title>
</head>
<body bgcolor=#E6E6FA>

		<form method="get" action="InputLogin">
    	<table>
        	<p align='right'> <%= session.getAttribute("login")%>
        	<form>
			<input type="button" value="logout" onClick='location.href="login.jsp"'>
			</form></p>
    	</table>
		</form>
		
		<form method="get" action="InputLogin">
    	<table>
        	<form>
			<p align='left'><input type="button" value="BACK" onClick='location.href="adminRoom.jsp"'>
			</form></p>
    	</table>
		</form>

		<center>
		<h2><o>ALL USERS</o></h2> 
		<form method="get" action="DBManager">
			<table border="1" cellspacing="0" cellpadding="12"> 
				<%out.println(DBManager.findUsers());%> 
			</table> 
		</form>
		</center>
		
		<center>
		<h2><o>ALL BLOCK USERS</o></h2> 
		<form method="get" action="DBManager">
			<table border="1" cellspacing="0" cellpadding="12"> 
				<%out.println(DBManager.findBlockUsers());%> 
			</table> 
		</form>
		</center>

</body>
</html>