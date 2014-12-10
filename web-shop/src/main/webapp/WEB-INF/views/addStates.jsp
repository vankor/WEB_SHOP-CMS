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
<link rel="stylesheet" href="<c:url value="/resources/style.css"/>"/>
<script>
var counter =0;
function appendState(){
	var div;
	
	div = document.createElement("div");
	div.setAttribute("id", "state"+counter);
	div.innerHTML = "<label id = 'states"+counter+".name' name='states["+counter+"]'> Имя состояния "+(counter+1)+": </label><input id='states"+counter+".text' name='states["+counter+"].text' type='text'/><a href = 'javascript:removeProperty("+counter+")'>Удалить состояние</a>";
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
<div id = "form">
<h3>Вести состояния товара:</h3>
<form:form method="post" action="addedGoodState" modelAttribute="goodstates">
		
		<c:if test = "${!empty goodstates.states}">
		<c:forEach items = "${goodstates.states}" var = "state" varStatus = "i">
		<form:label id = 'state${i.index}.name' path = 'states[${i.index}].text' name='parametername'> Имя состояния ${i.index+1}: </form:label><form:input id = 'state${i.index}.id' type="hidden" path = 'states[${i.index}].id' name = 'parameters'/><form:input id = 'state${i.index}.text' path = 'states[${i.index}].text' name = 'states'/><a href = "${pageContext.request.contextPath}/admin/setdeleted/GoodState/${state.id}">Удалить состояние</a>
		<br>
		<script>counter = ${i.index + 1} </script>
		</c:forEach>
		</c:if>
		<div id = "parentId"></div>
		<a href = "#" onclick = "return appendState()">Добавить новое состояние</a>
		<tr>

			<td colspan="2"><input type="submit"
				value="Обновить состояния!" /></td>
		</tr>
</form:form>
</div>
</div>
</body>
</html>