<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employee Manager Home</title>
</head>
<body>
	<div align="center">
		<h1>Employee List</h1>
		


		


			<c:forEach var="deparmentvalue" items="${listEmployee}" varStatus="status">
				
					<a href="aggregate?department=${deparmentvalue}">${deparmentvalue}</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
				
				
			</c:forEach>
		
	</div>
</body>
</html>
