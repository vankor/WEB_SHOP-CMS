<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf8">
	<title>Goods</title>
</head>
<body>
<h1 style = "color: red; font: bold;">Comments of ${usr.login} : </h1>
	
<c:if test="${!empty comList}">
	<table class="data">
		<tr>
			<th>ID</th>
			<th>Time</th>
			<th>Good</th>
			<th>TEXT</th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach items="${comList}" var="com">
			<tr>
				<td>${com.id}</td>
				<td>${com.Time}</td>
				<td>${com.good.name}</td>
				<td>${com.text}</td>
				<td><a href="delete/${usr.id}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</c:if>



</body>
</html>