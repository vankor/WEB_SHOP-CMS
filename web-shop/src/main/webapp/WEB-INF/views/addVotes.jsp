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
function appendvote(){
	var div;
	
	div = document.createElement("div");
	div.setAttribute("id", "vote"+counter);
	div.innerHTML = "<label id = 'votes"+counter+".id' name='votes["+counter+"]'> Идентификатор оценки "+(counter+1)+": </label><input id='votes"+counter+".id' name='votes["+counter+"].id' type='text'/><label id = 'votes"+counter+".vote_name' name='votes["+counter+"]'> Имя оценки "+(counter+1)+": </label><input id='votes"+counter+".vote_name' name='votes["+counter+"].vote_name' type='text'/><a href = 'javascript:removeProperty("+counter+")'>Удалить состояние</a>";
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
	var strid = 'vote'+id;
	document.getElementById(strid).remove();
	counter--;
}
</script>
</head>
<body>
 <jsp:include page="/WEB-INF/views/adminheader.jsp" />

<div class="mainwrapper">
<div id = "form"> 
<form:form method="post" action="addedVote" modelAttribute="votes">
		
		<c:if test = "${!empty votes.votes}">
		<c:forEach items = "${votes.votes}" var = "vote" varStatus = "i">
		<form:label id = 'vote${i.index}.id' path = 'votes[${i.index}].id' name='votename'> Идентификатор оценки ${i.index+1}: </form:label><form:input id = 'vote${i.index}.id' path = 'votes[${i.index}].id' name = 'votes'/><form:label id = 'vote${i.index}.vote_name' path = 'votes[${i.index}].vote_name' name='votename'> Имя оценки ${i.index+1}: </form:label><form:input id = 'vote${i.index}.vote_name' path = 'votes[${i.index}].vote_name' name = 'votes'/><a href = "${pageContext.request.contextPath}/deletevote/${vote.id}">Удалить состояние</a>
		<br>
		<script>counter = ${i.index + 1} </script>
		</c:forEach>
		</c:if>
		<div id = "parentId"></div>
		<a href = "#" onclick = "return appendvote()">Добавить новое состояние</a>
		<tr>

			<td colspan="2"><input type="submit"
				value="Обновить состояния!" /></td>
		</tr>
</form:form>
</div>
</div>
</body>
</html>