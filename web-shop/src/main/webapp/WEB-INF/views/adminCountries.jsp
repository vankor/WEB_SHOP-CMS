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
<script>
var counter =0;
function appendState(){
	var div;
	
	div = document.createElement("div");
	div.setAttribute("id", "state"+counter);
	div.innerHTML = "<label id = 'countries"+counter+".name' name='countries["+counter+"]'> Название страны "+(counter+1)+": </label><input id='countries"+counter+".text' name='countries["+counter+"].name' type='text'/><a href = 'javascript:removeProperty("+counter+")'>Удалить страну</a>";
	counter++;
	
	document.getElementById("parentId").appendChild(div);
}

Element.prototype.remove = function() {
    this.parentElement.removeChild(this);
}
NodeList.prototype.remove = HTMLCollection.prototype.remove = function() {
    for(var i = 0, len = this.length; i < len; i++) {
        if(this[i] && this[i].parentElement) {
            this[i].parentElement.removeChild(this[i]);
        }
    }
}

function removeProperty(id){
	var strid = 'state'+id;
	document.getElementById(strid).remove();
	counter--;
}
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/adminheader.jsp" />

<div class="mainwrapper"> 
<h1>Вести страны:</h1>
<form:form method="post" action="addedCountry" modelAttribute="countrybean">
		<div id = "sector">
		<c:if test = "${!empty countrybean.countries}">
		<c:forEach items = "${countrybean.countries}" var = "country" varStatus = "i">
		<form:label id = 'country${i.index}.name' path = 'countries[${i.index}].name' name='countryname'> Название страны ${i.index+1}: </form:label><form:input id = 'country${i.index}.id' type="hidden" path = 'countries[${i.index}].id' name = 'countries'/><form:input id = 'country${i.index}.name' path = 'countries[${i.index}].name' name = 'countries'/><a href = "${pageContext.request.contextPath}/admin/admincountry/${country.id}">Редактировать страну</a><a href = "${pageContext.request.contextPath}/admin/setdeleted/Country/${country.id}">Удалить страну</a>
		<br>
		<script>counter = ${i.index + 1} </script>
		</c:forEach>
		</c:if>
		<div id = "parentId"></div>
		<a href = "#" onclick = "return appendState()">Добавить новую страну</a>
		<tr>

			<td colspan="2"><input type="submit"
				value="Сохранить" /></td>
		</tr>
		</div>
</form:form>
</div>
</body>
</html>