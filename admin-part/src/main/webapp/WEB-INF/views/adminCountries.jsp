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
	div.innerHTML = "<label id = 'countries"+counter+".name' name='countries["+counter+"]'> Название страны "+(counter+1)+": </label><input id='countries"+counter+".text' style = 'min-width:80px; width:80px;' name='countries["+counter+"].name' type='text'/><label id = 'countries"+counter+".phonecode' name='countries["+counter+"].phonecode'> Код страны "+(counter+1)+": </label><input style = 'min-width:30px; width:30px;' id='countries"+counter+".phonecode' name='countries["+counter+"].phonecode' type='text'/><label> Флаг-иконка: <label><input id = 'countries[${i.index}].newthumb' style = 'min-width:200px; width:200px;' type = 'file' text = 'Загрузить новую иконку' name='countries["+counter+"].newthumb'/><a href = 'javascript:removeProperty("+counter+")'>Удалить страну</a>";
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
<c:if test = "${result == 'success'}">
<div id = "success"><p>Изменения сохранены!</p></div>
</c:if>
<form:form method="post" action="updatedCountries" modelAttribute="countrybean" enctype="multipart/form-data">
		<div id = "sector">
		<c:if test = "${!empty countrybean.countries}">
		<c:forEach items = "${countrybean.countries}" var = "country" varStatus = "i">
		<form:label id = 'country${i.index}.name' path = 'countries[${i.index}].name' name='countryname'> Название страны ${i.index+1}: </form:label>
		<form:input  id = 'country${i.index}.id' type="hidden" path = 'countries[${i.index}].id' name = 'countries'/>
		<form:input style = "min-width:80px; width:80px;" id = 'country${i.index}.name' path = 'countries[${i.index}].name' name = 'countries'/>
		<form:errors path="countries[${i.index}].name" cssClass="error" />
		
		<form:label id = 'country${i.index}.phonecode' path = 'countries[${i.index}].phonecode' name='phonecode'>Код страны ${i.index+1}: </form:label>
		<form:input style = "min-width:30px; width:30px;" id = 'country${i.index}.phonecode' path = 'countries[${i.index}].phonecode' name = 'phonecode'/>
		<form:errors path="countries[${i.index}].phonecode" cssClass="error" />
		
		<form:input type = "hidden" path = "countries[${i.index}].thumb" id = "countries[${i.index}].thumb"/>
		<c:choose>
		<c:when test = "${!empty countrybean.countries[i.index].thumb}"><img src="<c:url value="/resources/images${countrybean.countries[i.index].thumb}" />" width="30" alt="${countrybean.countries[i.index].name}"  /></c:when>
		<c:otherwise> Флаг-иконка: </c:otherwise></c:choose>
		<form:input style = "min-width:200px; width:200px;" id = "countries[${i.index}].newthumb" type = "file" text = "Загрузить новую иконку" path="countries[${i.index}].newthumb"/>
		
		
		
		
		
		<a href = "${pageContext.request.contextPath}/admin/admincountry/${country.id}">Редактировать</a>  <a href = "${pageContext.request.contextPath}/admin/realdelete/Country/${country.id}">Удалить</a>
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