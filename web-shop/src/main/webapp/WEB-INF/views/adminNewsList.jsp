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
<h2>Список новостей категории ${category.name}</h2>
		
		<c:if test = "${!empty news}">
		<c:forEach items = "${news}" var = "news" varStatus = "i">
		<label id = 'actname' name='parametername'> Заголовок новости: </label><input value = "${news.id}" id = 'newsid' type="hidden" path = 'id' name = 'newsid'/><input id = 'newstitle' value = "${news.title}" path = 'title' name = 'name'/><label id = 'endtime' path = 'endtime' name='endtime'> Создана: </label><input value = "${news.creation}" id = 'crtime' path = 'creation' name = 'crtime'/><a href = "${pageContext.request.contextPath}/admin/adminnews/${category.id}/updatenews/${news.id}">Редактировать новость</a><a href = "${pageContext.request.contextPath}/admin/adminnews/${category.id}/deletenews/${news.id}">Удалить новость</a>
		<br>
		<script>counter = ${i.index + 1} </script>
		</c:forEach>
		</c:if>
		<div id = "parentId"></div>
		<a href = "${pageContext.request.contextPath}/admin/adminnews/${category.id}/addnews" >Добавить новость</a>


</body>
</html>