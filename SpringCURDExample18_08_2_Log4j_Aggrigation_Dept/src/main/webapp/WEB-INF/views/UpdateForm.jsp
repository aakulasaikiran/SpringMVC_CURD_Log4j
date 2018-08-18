<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New/Edit Contact</title>
</head>
<body>
	<div align="center">
		<h1 style="color: red;" align="center">Update Employee</h1>
		<form:form action="updateEmployee" method="post"
			modelAttribute="employee1">
			<table>

				<tr>
					<td><form:hidden path="email" /></td>
				</tr>
				<tr>
					<td>Name:</td>
					<td><form:input path="name" /></td>
				</tr>

				<tr>
					<td>Address:</td>
					<td><form:input path="address" /></td>
				</tr>
				<tr>
					<td>Telephone:</td>
					<td><form:input path="telephone" /></td>
				</tr>
				<tr>
					<td>Salary:</td>
					<td><form:input path="salary" /></td>
				</tr>
				<tr>
					<td>Deportment:</td>
					<td><form:select path="deportment" itemValue="Software">
							<option>Software</option>
							<option>Tester</option>
							<option>Manager</option>
							<option>Director</option>
						</form:select></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Save"></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>