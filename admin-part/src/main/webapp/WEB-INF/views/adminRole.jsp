<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
	<link rel="stylesheet" href="<c:url value="/resources/popupwindow.css"/>"/>
	<link rel="stylesheet" href="<c:url value="/resources/style.css"/>"/>

</head>
<body>
<jsp:include page="/WEB-INF/views/adminheader.jsp" />

<div class="mainwrapper"> 
<h1>Вести роль:</h1>

<form:form method="post" action="${pageContext.request.contextPath}/admin/updatedrole" modelAttribute="role">
		<div id = "sector">
		<ul>
		
		<li>
		<div id = "col1">
		<form:label id = 'role.authority' path = 'authority' name='authority'> ИД роли: </form:label>
		</div>
		<div id = "col2">
		<c:if test = "${section == 'update' }">
		<form:input type = "hidden" id = 'role.authority' value = '${role.authority}' path = 'authority' name='authority'></form:input>
		<form:label id = 'role.authority' path = 'authority' name='authority'> ${role.authority} </form:label>
		</c:if>
		<c:if test = "${section == 'create'}">
		<form:input id = 'role.authority' path = 'authority' name='authority'></form:input>
		</c:if>
		</div>
		</li>
		
		<li>
		<div id = "col1">
		<form:label id = 'role.description' path = 'description' name='description'> Описание роли: </form:label>
		</div>
		<div id = "col2">
		<form:input id = 'role.authority' path = 'description' name='description'/>
		</div>
		</li>
	
		
		<li>

			<div id = "col1"><input type="submit" value="Сохранить" /></div>
		
		</li>
		
		</ul>
		</div>
</form:form>
</div>
</body>
</html>