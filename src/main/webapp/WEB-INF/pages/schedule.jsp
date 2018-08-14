<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
</head>
<body>
	<h1>Schedule:</h1>
	<table border="1">
		<thead>
			<tr>
				<th>Shift 1</th>
				<th>Shift 2</th>
				<th>Date</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${schedule.items}">
			<tr>
				<td>${item.engineer1.id}</td>
				<td>${item.engineer2.id}</td>
				<td>${item.date}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>