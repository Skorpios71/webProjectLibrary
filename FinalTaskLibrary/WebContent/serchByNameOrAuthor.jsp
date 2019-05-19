<%@page import="ua.library.db.DBManager"%>
<%@ page language="java" contentType="text/html; charset=cp1251"
    pageEncoding="cp1251"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="cp1251">
<title>Catalog</title>
</head>
<body bgcolor=#E6E6FA>

	<form method="get" action="InputLogin">
		<table>
			<p align='right'> 
				<%=session.getAttribute("login")%>
				(user)
				<input type="button" value="logout"
					onClick='location.href="login.jsp"'>
			<br>
		</table>
	</form>

	<center>RESULTS</center>
	
	<form method="get" action="CreateOrder">
    	<table>
    		<tr>
            	<td>Book order form:</td>
       		</tr>
        	<tr>
            	<td>Name</td>
            	<td><input style="width:160px;height:17px" id="name" type="text" name="name"></td>
       		</tr>
        	<tr>
            	<td>Author</td>
            	<td><input style="width:160px;height:17px" id="author" type="text" name="author"></td>
        	</tr>
        	<tr>
            	<td>Edition</td>
            	<td><input style="width:160px;height:17px" id="edition" type="text" name="edition"></td>
       		</tr>
       		<tr>
            	<td>Data edition</td>
            	<td><input style="width:160px;height:17px" id="dataEdition" type="number" name="dataEdition"></td>
       		</tr>
       		<tr>
            	<td>My login:</td>
            	<td><input style="width:160px;height:17px" id="myLogin" type="text" name="myLogin"></td>
       		</tr>
       		<tr>
            	<td>(Give in) Hall / Card:</td>
            	<td>
            		<select style="width:164px;height:20px" name="giveIn">
     				<option value="card">card</option>
     				<option value="hall">hall</option>
   				</select></td>
       		</tr>
       		<tr>
            	<td>(If card) Data:</td>
            	<td><input style="width:160px;height:17px" id="data" type="date" name="data">(гггг.мм.дд)</td>
       		</tr>
    	</table>
    		<input style="width:250px;height:40px" type="submit" value="MAKE AN ORDER">
		</form>
            
		<form method="get" action="userRoom">
			<table border="1" cellspacing="0" cellpadding="12"> 
				<%String search = session.getAttribute("search").toString();
				out.println(DBManager.searchBooksSortNameOrAuthor(search.toString()));%> 
			</table> 
		</form>
</body>
</html>