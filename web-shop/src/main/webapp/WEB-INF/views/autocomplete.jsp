<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${section == 'towns'}">
<c:forEach items="${towns}" var="town">
<div id="town_${town.id}" onclick = "$('#clienttown').attr('value', ${town.id}); $('#clienttown_f').attr('value', '${town.name}');change_params();">${town.name}</div>


		
		
</c:forEach> 
</c:if> 
