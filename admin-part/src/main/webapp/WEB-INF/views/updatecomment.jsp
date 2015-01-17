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
<h1>Вести комментарий:</h1>

<form:form method="post" action="${pageContext.request.contextPath}/admin/updatedcomment" modelAttribute="comment">
		<div id = "sector">
		<ul>
		
		<li>
		<div id = "col1">
		<form:label id = 'deltype.id' path = 'id' name='id'> ИД: </form:label>
		</div>
		<div id = "col2">
		<c:if test = "${section == 'update' }">
		<form:input type = "hidden" id = 'comment.id' value = '${comment.id}' path = 'id' name='id'></form:input>
		<form:label id = 'comment.id' path = 'id' name='id'> ${comment.id} </form:label>
		</c:if>
		<c:if test = "${section == 'create'}">
		<form:input id = 'comment.id' path = 'id' name='id'></form:input>
		</c:if>
		</div>
		</li>
		
		<li>
		<div id = "col1">
		<form:label id = 'author' path = 'author' name='author'> Автор комментария: </form:label>
		</div>
		<div id = "col2">
		<form:label id = 'author' path = 'author' name='author'> ${comment.author} </form:label>
		</div>
		</li>
	
		<li>
		<div id = "col1">
		<form:label id = 'good' path = 'good' name='good'> Комментарий к товару: </form:label>
		</div>
		<div id = "col2">
		<form:label id = 'good' path = 'good' name='good'> ${comment.good} </form:label>
		</div>
		</li>
		
		<li>
		<div id = "col1">
		<form:label id = 'text' path = 'text' name='text'> Текст  комментария: </form:label>
		</div>
		<div id = "col2">
		<form:textarea cols = '20' rows = '40' id = 'text' path = 'text' name='text'></form:textarea>
		</div>
		</li>
		
		<li>
		<div id = "col1">
		<form:label id = 'date' path = 'date' name='date'> Дата комментария: </form:label>
		</div>
		<div id = "col2">
		<form:input id = 'date' path = 'date' name='date'></form:input>
		</div>
		</li>
		
		<li>
		<div id = "col1">
		<form:label id = 'time' path = 'time' name='time'> Время комментария: </form:label>
		</div>
		<div id = "col2">
		<form:input id = 'time' path = 'time' name='time'></form:input>
		</div>
		</li>
		
		<li>
		<div id = "col1">
		<form:label id = 'authorname' path = 'authorname' name='authorname'> Имя автора при вводе комментария: </form:label>
		</div>
		<div id = "col2">
		<form:input id = 'authornamee' path = 'authorname' name='authorname'></form:input>
		</div>
		</li>
		
		<li>
		<div id = "col1">
		<form:label id = 'authoremail' path = 'authoremail' name='authoremail'> Почта автора: </form:label>
		</div>
		<div id = "col2">
		<c:if test = "${empty comment.author}">
		<form:input id = 'authoremail' path = 'authoremail' name='authoremail'></form:input>
		</c:if>
		<c:if test = "${!empty comment.author}">
		<form:input id = 'authoremail' path = 'author.username' name='authoremail'></form:input>
		</c:if>
		</div>
		</li>
		
		<li>
		<div id = "col1">
		<form:label id = 'plus' path = 'plus' name='plus'> Плюсы товара: </form:label>
		</div>
		<div id = "col2">
		<form:textarea cols = '20' rows = '40' id = 'plus' path = 'plus' name='plus'></form:textarea>
		</div>
		</li>
		
		<li>
		<div id = "col1">
		<form:label id = 'minus' path = 'minus' name='minus'> Минусы товара: </form:label>
		</div>
		<div id = "col2">
		<form:textarea id = 'minus' path = 'minus' name='minus'></form:textarea>
		</div>
		</li>
		
		<li>
		<div id = "col1">
		<form:label id = 'note' path = 'note' name='note'> Оценка товара: </form:label>
		</div>
		<div id = "col2">
		<form:input id = 'note' path = 'note' name='note'></form:input>
		</div>
		</li>
		
		<li>
			<div id = "col1"><form:label path="isproved">
				Комментарий опубликован:
			</form:label></div>
			<div id = "col2"><form:checkbox path="isproved" /></div>
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