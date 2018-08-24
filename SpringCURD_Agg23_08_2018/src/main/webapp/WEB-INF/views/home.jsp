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
	        <h1 style="color: red;">Employee List</h1>
	        <h3><a href="newEmployee">New Employee</a> | <a href="groupByDep">Groupbydep</a>| <a href="sortbySalary">SortbySalary</a>|<a href="aggregateContact" class="button">Aggregation</a></h3>
	       <style>
	        #new_contact{
	        	font-size:24px;
	        	margin:0 20px 0 0;
	        }
	        .button {
    background-color:#008CBA; 
    border: none;
    color: white;
    padding: 15px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
}
</style>
	        <table border="1">
	        	<th>No</th>
	        	<th>Name</th>
	        	<th>Email</th>
	        	<th>Address</th>
	        	<th>Telephone</th>
	        	<th>Salary</th>
	        	<th>Deportment</th>
	        	<th>Action</th>
	        	
				<c:forEach var="employee" items="${listEmployee}" varStatus="status">
	        	<tr>
	        		<td>${status.index + 1}</td>
					<td>${employee.name}</td>
					<td>${employee.email}</td>
					<td>${employee.address}</td>
					<td>${employee.telephone}</td>
					<td>${employee.salary}</td>
					<td>${employee.deportment}</td>
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
