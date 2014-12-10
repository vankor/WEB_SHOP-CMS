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
		
		<c:if test = "${!empty actions}">
		<c:forEach items = "${actions}" var = "action" varStatus = "i">
		<label id = 'actname' name='parametername'> Имя акции: </label><input value = "${action.id}" id = 'actid' type="hidden" path = 'id' name = 'actid'/><input id = 'actionname' value = "${action.name}" path = 'namet' name = 'name'/><label id = 'endtime' path = 'endtime' name='endtime'> Действительна с даты: </label><input value = "${action.endtime}" id = 'endtime' path = 'endtime' name = 'endtime'/><a href = "${pageContext.request.contextPath}/admin/adminactions/updateaction/${action.id}">Редактировать акцию</a><a href = "${pageContext.request.contextPath}/admin/adminactions/deleteaction/${action.id}">Удалить акцию</a>
		<br>
		<script>counter = ${i.index + 1} </script>
		</c:forEach>
		</c:if>
		<div id = "parentId"></div>
		<a href = "${pageContext.request.contextPath}/admin/adminactions/addaction" >Добавить новую акцию</a>


</body>
</html>