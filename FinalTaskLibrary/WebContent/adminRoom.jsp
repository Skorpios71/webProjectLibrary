<%@page import="ua.library.db.DBManager"%>
<%@ page language="java" contentType="text/html; charset=cp1251"
    pageEncoding="cp1251"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="cp1251">
	<title>Admin room</title>
		<style>
   			hr {
    		border: none;
    		background-color: #A569BD;
    		color: #A569BD;
    		height: 2px;
  			}
  			
   			o {
    		border: 3px solid red;
    		padding: 8px;
   			}
  		</style>
</head>

<body bgcolor=#E6E6FA>

        <form method="get" action="InputLogin">
    	<table>
        	<p align='right'> <%= session.getAttribute("login") + " " + DBManager.findFirstLastName(session.getAttribute("login").toString())%> (administrator)
        	<form>
			<input type="button" value="logout" onClick='location.href="login.jsp"'>
			</form></p>
    	</table>
		</form>
		
		<table>
        	<form>
			<input type="button" value="SEARCH ALL USERS IN DATABASE" onClick='location.href="allUsers.jsp"'>
			</form>
    	</table><br>
		<hr>
		
		<form method="get" action="AddBook">
    	<table>
        	<tr>
        		<h3>Add book in library</h3>
            	<td><label for="nameBook">Name:</label></td>
            	<td><input  style="width:160px;height:17px" id="nameBook" type="text" name="nameBook"></td>
            	<td><label for="authorBook">Author:</label></td>
            	<td><input  style="width:160px;height:17px" id="authorBook" type="text" name="authorBook"></td>
            	<td><label for="edition">Edition:</label></td>
            	<td><input  style="width:160px;height:17px" id="edition" type="text" name="edition"></td>
            	<td><label for="dataEdition">Data edition:</label></td>
            	<td><input  style="width:160px;height:17px" id="dataEdition" type="number" name="dataEdition"></td>
            	<td><label for="count">Count:</label></td>
            	<td><input  style="width:160px;height:17px" id="count" type="number" name="count"></td>
            	<td><input style="width:80px;height:23px" type="submit" value="submit"></td>
       		</tr>
    	</table><br>
		</form>
		<hr>
		
		<form method="get" action="RemoveBook">
    	<table>
        	<tr>
        		<h3>Remove book from library</h3>
            	<td><label for="nameBook">Name:</label></td>
            	<td><input  style="width:160px;height:17px" id="nameBook" type="text" name="nameBook"></td>
            	<td><label for="authorBook">Author:</label></td>
            	<td><input  style="width:160px;height:17px" id="authorBook" type="text" name="authorBook"></td>
            	<td><label for="edition">Edition:</label></td>
            	<td><input  style="width:160px;height:17px" id="edition" type="text" name="edition"></td>
            	<td><label for="dataEdition">Data edition:</label></td>
            	<td><input  style="width:160px;height:17px" id="dataEdition" type="number" name="dataEdition"></td>
            	<td><label for="count">Count:</label></td>
            	<td><input  style="width:160px;height:17px" id="count" type="number" name="count"></td>
            	<td><input style="width:80px;height:23px" type="submit" value="submit"></td>
       		</tr>
    	</table><br>
		</form>
		<hr>
		
		<form method="get" action="CreateLibrarian">
    	<table>
        	<tr>
        		<h3>Create librarian</h3>
            	<td><label for="login">Login:</label></td>
            	<td><input  style="width:160px;height:17px" id="login" type="text" name="login"></td>
            	<td><label for="password">Password:</label></td>
            	<td><input  style="width:160px;height:17px" id="password" type="text" name="password"></td>
            	<td><label for="firstName">First Name:</label></td>
            	<td><input  style="width:160px;height:17px" id="firstName" type="text" name="firstName"></td>
            	<td><label for="lastName">Last Name:</label></td>
            	<td><input  style="width:160px;height:17px" id="lastName" type="text" name="lastName"></td>
            	<td><input style="width:80px;height:23px" type="submit" value="submit"></td>
       		</tr>
    	</table><br>
		</form>
		<hr>
		
		<form method="get" action="RemoveLibrarian">
    	<table>
        	<tr>
        		<h3>Remove librarian</h3>
        		<td><label for="login">Login:</label></td>
            	<td><input  style="width:160px;height:17px" id="login" type="text" name="login"></td>
            	<td><input style="width:80px;height:23px" type="submit" value="submit"></td>
       		</tr>
    	</table><br>
		</form>
		<hr>
		
		<form method="get" action="UsersBlock">
    	<table>
        	<tr>
        		<h3>Block user</h3>
            	<td><label for="login">Login:</label></td>
            	<td><input  style="width:160px;height:17px" id="login" type="text" name="login"></td>
            	<td><input style="width:80px;height:23px" type="submit" value="submit"></td>
       		</tr>
    	</table><br>
		</form>
		<hr>
		
		<form method="get" action="UsersUnblock">
    	<table>
        	<tr>
        		<h3>Unblock user</h3>
            	<td><label for="login">Login:</label></td>
            	<td><input  style="width:160px;height:17px" id="login" type="text" name="login"></td>
            	<td><input style="width:80px;height:23px" type="submit" value="submit"></td>
       		</tr>
    	</table><br>
		</form>
	
</body>
</html>