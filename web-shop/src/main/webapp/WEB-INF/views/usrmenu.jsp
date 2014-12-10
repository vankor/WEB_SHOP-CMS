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
<h1 style = "color: red; font: bold;">Cabinet of <c:choose><c:when test="${!empty usr.login}">${usr.login}</c:when><c:otherwise>${usr.ip}</c:otherwise></c:choose></h1>

<c:if test="${!empty usr}">
	<table class="data">
		<tr>
			<th>Action:</th>
			
		</tr>
		
			<tr>
				<td><a href="${pageContext.request.contextPath}/usrcomm/${usr.id}">ShowComments</a></td>
			</tr>
			
			<tr>
				<td><a href="${pageContext.request.contextPath}/usrbuck/${usr.id}">ShowBuckets</a></td>
			</tr>
			
			<tr>
				<td><a href="${pageContext.request.contextPath}/usrorders/${usr.id}">ShowOrders</a></td>
			</tr>
		
	</table>
</c:if>



</body>
</html>