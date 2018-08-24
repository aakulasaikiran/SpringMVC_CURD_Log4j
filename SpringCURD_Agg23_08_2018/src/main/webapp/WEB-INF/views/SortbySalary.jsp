<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee Manager Home</title>
    </head>
    <body>
    	<div align="center">
	        <h1 style="color: red;">Employee List With Sort By Salary</h1>
	       <!--  <h3><a href="newEmployee">New Employee</a> | <a href="groupByDep">Groupbydep</a></h3>
	        --> 
	       <h3> <a href="" style="color: green;">Home</a></h3>
	        <table border="1">
	        	<th>No</th>
	        	<th>Name</th>
	        	<th>Email</th>
	        	<th>Address</th>
	        	<th>Telephone</th>
	        	<th>Salary</th>
	        	<th>Deportment</th>
	        	<th>Action</th>
	        	
				<c:forEach var="listEmployee" items="${listEmployee}" varStatus="status">
	        	<tr>
	        		<td>${status.index + 1}</td>
					<td>${listEmployee.name}</td>
					<td>${listEmployee.email}</td>
					<td>${listEmployee.address}</td>
					<td>${listEmployee.telephone}</td>
					<td>${listEmployee.salary}</td>
					<td>${listEmployee.deportment}</td>
					<td>
						<a href="editEmployee?email=${employee.email}">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="deleteEmployee?email=${employee.email}">Delete</a>
					</td>
							
	        	</tr>
				</c:forEach>	        	
			</table>
    	</div>
    </body>
</html>
