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
		<script type="text/javascript" src="<c:url value="/resources/js/tinymce/tinymce.min.js" />" ></script>
<script type="text/javascript">
tinyMCE.init({
mode : "textareas",
width : "640",
height : 300
});
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/adminheader.jsp" />

<div class="mainwrapper"> 
<h1>Вести вид доставки:</h1>


<form:form method="post" action="${pageContext.request.contextPath}/admin/updateddeltype" modelAttribute="deltype">
		<div id = "sector">
		<h3>Основные данные:</h3>
		<ul>
		<c:if test = "${section == 'update' }">
		<li>
		
		<div id = "col1">
		<form:label id = 'deltype.id' path = 'id' name='id'> ИД: </form:label>
		</div>
		<div id = "col2">
		
		<form:input type = "hidden" id = 'deltype.id' value = '${deltype.id}' path = 'id' name='id'></form:input>
		<form:label id = 'deltype.authority' path = 'id' name='id'> ${deltype.id} </form:label>
		
		
		</div>
		
		</li>
		</c:if>
		<li>
		<div id = "col1">
		<form:label id = 'deltype.name' path = 'name' name='name'> Имя способа доставки: </form:label>
		</div>
		<div id = "col2">
		<form:input id = 'deltype.name' path = 'name' name='name'/>
		</div>
		</li>
	
		<li>
		<div id = "col1">
		<form:label id = 'deltype.shortname' path = 'shortname' name='shortname'> Код способа доставки: </form:label>
		</div>
		<div id = "col2">
		<form:input id = 'deltype.shortname' path = 'shortname' name='shortname'/>
		</div>
		</li>
		
		<li>
		<div id = "col1">
		<form:label id = 'deltype.type' path = 'type' name='type'> Тип способа доставки: </form:label>
		</div>
		<div id = "col2">
		<form:select path="type">
    				<form:options items="${types}" />
		</form:select>
		</div>
		</li>
		
		<li>	
		<div id = "row">
			<div id = "col1">
				<form:label path="info">
					Информация про вид доставки:
				</form:label>
			</div>
	
			<div id = "col2"><form:textarea path="info"/></div>
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