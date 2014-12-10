<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf8">
	<title>BuckGoods</title>
</head>
<body>
<c:choose>

<c:when test="${!empty usr}">
<h1> User is comming ${usr.login}</h1>
<h1> With password ${usr.pass}</h1>
</c:when>
<c:otherwise>
  <h1> Fail, there is no such user!</h1>
</c:otherwise>


</c:choose>