<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:if test="${section == 'all' || section == 'params'}">
<c:if test="${!empty properties}">
<h3> Параметры товара ${good.name}:</h3>
	<ul>

	<c:forEach items = "${properties}" var = "entry">
	<li><span style = "font:bold  110% Palatino serif;" >${entry.key}: </span><span style = "font:  110% bold;" >${entry.value}</span> </li>
	</c:forEach>
	
</ul>
</c:if>
<h3> Описание товара ${good.name}:</h3>
<ul>
<li><span style = "font:bold  110% Palatino serif;" >Description: </span><br/><span style = "font:  110% bold;" >${good.description}</span></li>
</ul>
</c:if>



<c:if test="${section == 'all' || section == 'voices'}">
<c:if test="${!empty comList}">
<h3> Отзывы о товаре ${good.name}:</h3>
	<table class="data">
		<tr>
			<th>Time</th>
			<th>TEXT</th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach items="${comList}" var="com">
			<tr>
				<td>${com.time}</td>
				<td>${com.text}</td>
				<td><a href="delete/${usr.id}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
	</c:if>
	<form method="post" action="addcomment/?goodid=${good.id}">

	<table>
		<tr>
			<td><label path="text"></label></td>
			<td><input path="text" /></td>
			
		</tr>
		
		<tr>
			<td colspan="2"><input type="button"
				value="ADD Comment" /></td>
		</tr>
	</table>
</form>

</c:if>
<c:if test="${section == 'all' || section == 'photos'}">
<c:if test="${!empty photoList}">
<h3> Фото:</h3>
	<table class="data">
		<tr>
			<th>Time</th>
			<th>TEXT</th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach items="${comList}" var="com">
			<tr>
				<td>${com.time}</td>
				<td>${com.text}</td>
				<td><a href="delete/${usr.id}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
	
</c:if>
</c:if>