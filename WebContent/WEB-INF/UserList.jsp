<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista korisnika</title>
</head>
<body>
<center>
<h1>Lista korisnika</h1>
	<table id="box" border = "1" cellpadding="5" style="background-color: #ffffcc;">
		<tr>
			<td>Id:</td>
			
			<td>Name:</td>

			<td>User:</td>

			<td>Password:</td>
			
			<td></td>

		</tr>

		<c:forEach items="${userList}" var="user">

			<tr>
				<td>${user.id}</td>
				
				<td>${user.name}</td>

				<td>${user.userid}</td>

				<td>${user.pwd}</td>
				
				<td>
				 	<a href="edit?id=${user.id}">Edit</a>
                </td>
                <td>
				 	<a href="delete?id=${user.id}">Delete</a>
                </td>
			</tr>



		</c:forEach>
		<tr><td colspan="7"><a href="add">Add</a></td></tr> 
		<tr><td colspan="7"><a href="login">LogOut</a></td></tr> 
	</table>
	</center>
</body>
</html>