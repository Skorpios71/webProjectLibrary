<%@page import="ua.library.db.DBManager"%>
<%@ page language="java" contentType="text/html; charset=cp1251"
    pageEncoding="cp1251"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="cp1251">
	<title>Librarian room</title>
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
   			.block-left{width:50%;height:320px;overflow:auto;float:left;}
			.block-right{width:50%;height:320px;overflow:auto;}
  		</style>
</head>
<body bgcolor=#E6E6FA>

		<form method="get" action="InputLogin">
    	<table>
        	<p align='right'> <%= session.getAttribute("login") + " " + DBManager.findFirstLastName(session.getAttribute("login").toString())%> (librarian) 
        	<form>
			<input type="button" value="logout" onClick='location.href="login.jsp"'>
			</form></p>
    	</table>
		</form>
		
		<table>
        	<form>
			<input type="button" value="SEARCH BOOK IN CATALOG" onClick='location.href="catalog.jsp"'> <%  %>
			<input type="button" value="SEARCH ALL USERS IN DATABASE" onClick='location.href="allUsers.jsp"'>
			</form>
    	</table><br>
		
		<div class="block-left">
		<form method="get" action="IssueBook">
    	<table>
    		<tr>
            	<td><h2>Issue book form:</h2></td>
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
            	<td>User login:</td>
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
    		<input style="width:250px;height:40px" type="submit" value="ISSUE AN ORDER">
		</form>
		</div>
		
		<div class="block-right">
		<form method="get" action="GetBook">
    	<table>
    		<tr>
            	<td><h2>Get book form:</h2></td>
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
            	<td>User login:</td>
            	<td><input style="width:160px;height:17px" id="myLogin" type="text" name="myLogin"></td>
       		</tr>
    	</table>
    		<input style="width:250px;height:40px" type="submit" value="GET BOOK">
		</form>
		</div>
		
		<h3>USER ORDERS</h3>
		<form method="get" action="DBManager">
			<table border="1" cellspacing="0" cellpadding="12"> 
				<%out.println(DBManager.findOrders());%> 
			</table> 
		</form>

</body>
</html>