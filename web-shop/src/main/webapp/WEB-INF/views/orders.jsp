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
<h1 style = "color: red; font: bold;">Orders of ${usr.login}:</h1>
	
<c:if test="${!empty ordList}">
	<table class="data">
		<tr>
			<th>DATE</th>
			<th>SUM</th>
			<th>&nbsp;</th>
			<th>&nbsp;</th>
			<th>&nbsp;</th>
		</tr>
		
			<tr>
			
			
			<c:forEach items="${ordList}" var="entry">
				<td><c:out value="${entry.key}"/></td>
				<td><c:out value="${entry.value}"/></td>
				<td><a href="ordgoods/${ord.id}">ShowGoods</a></td>
			</c:forEach>
			</tr>
		
	</table>
</c:if>



</body>
</html>