<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf8">
	<title>AnonimGoods</title>
</head>
<body>
<h1 style = "color: green; font: bold;">Goods of ${usr.login} : </h1>
	
<c:if test="${!empty currgoods}">
	<table class="data">
		<tr>
			<th>Name</th>
			<th>Description</th>
			<th>Price</th>
			<th>&nbsp;</th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach items="${currgoods}" var="good">
			<tr>
				<td>${good.name}</td>
				<td>${good.description}</td>
				<td>${good.price}</td>
				<td><a href="${pageContext.request.contextPath}/good/${good.id}">Show Good</a></td>
				<td><a href="deletefrombuck/${good.id}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</c:if>
</body>
</html>