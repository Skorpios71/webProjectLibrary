<%@page import="ua.library.db.DBManager"%>
<%@ page language="java" contentType="text/html; charset=cp1251"
    pageEncoding="cp1251"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="cp1251">
	<title>User room</title>
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
   			td.col1 { background: red; color: #A52A2A; }
   			#left { text-align: justify ; }
  		</style>
</head>
<body bgcolor=#E6E6FA>

		<form method="get" action="InputLogin">
    	<table>
        	<p align='right'> <%= session.getAttribute("login") + " " + DBManager.findFirstLastName(session.getAttribute("login").toString())%> (user) 
        	<form>
			<input type="button" value="logout" onClick='location.href="login.jsp"'>
			</form></p>
    	</table>
		</form><br>
		
		<form method="get" action="SearchByNameOrAuthor">
		SEARCH:
		<input style="width:130px;height:18px" type="text" name="search" value="name/author">
		<input style="width:80px;height:24px" type="submit" value="submit"><br>
		</form><p>
    	
    	<table>
        	<form>
			<input type="button" value="SEARCH BOOK IN CATALOG" onClick='location.href="catalog.jsp"'>
			</form>
    	</table><br>
		
		<center>
		<div id="left"><h2>My books</h2></div>
		</center>
		
		<form method="get" action="DBManager">
			<table border="1" cellspacing="0" cellpadding="12"> 
				<%String login = session.getAttribute("login").toString();
				out.println(DBManager.findUserBooks(login.toString()));%> 
			</table> 
		</form>

</body>
</html>