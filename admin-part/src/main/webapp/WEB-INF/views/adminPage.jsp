<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
    prefix="springForm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>Insert title here</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

	<script src="<c:url value="/resources/js/mascedinput.js" />"></script>
	<script src="<c:url value="/resources/js/combobox.js" />"></script>
	<script src="<c:url value="/resources/js/lazyload.js" />"></script>
	<script src="<c:url value="/resources/js/popups.js" />"></script>
	<script src="<c:url value="/resources/js/myajax.js" />"></script>
	<script src="<c:url value="/resources/js/main.js" />"></script>
	<script src="<c:url value="/resources/js/carousel.js" />"></script>
	<script src="<c:url value="/resources/js/jcarousellite.js" />"></script>
	<script type="text/javascript" src="http://js.mobilluck.com.ua/js/cloud-zoom.1.0.2.js"></script><script type="text/javascript" src="http://js.mobilluck.com.ua/js/fancybox/jquery.fancybox-1.3.2.pack.js"></script>
	<script src="<c:url value="/resources/js/myscrpits.js" />"></script>
	<script src="<c:url value="/resources/js/autocomplete.js" />"></script>
	<script src="<c:url value="/resources/js/datepicker.js" />"></script>
	<script src="<c:url value="/resources/js/adminscripts.js" />"></script>
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
<script type="text/javascript">
var counter = 0;
cc=[];
cc[counter] = 0;
var buck = "'";
$(document).ready(function(){
    $('#add').click(function() {
        document.getElementById('openModal').style.display = 'block';
        location.href='#openModal';

        $('#openModal').fadeIn(30000);
    });
    
    $("#isFinalCategory1").on('change', function(){
 //   	alert("weg");
    	if($(this).is(":checked")){
    		$("#addref").show();
    	}
    	else{
    		$("#addref").hide();
    	}
    });
    
    $("#newthumb").on('change',function (){
    	  var filename = $(this).val();
    	  $('#thumb').val(filename);
    	});
});

function addField(){
	alert(counter);
	var div;
	div = document.createElement("div");
	div.innerHTML = "<label path = 'category.parameters' name='parametername'> Parameter "+counter+" name: </label><input path = 'category.parameters["+counter+"]' value='parameter"+counter+"' name = 'parameters'/>";
	counter++;
	document.getElementById("parentId").appendChild(div);
}
</script>

</head>
<body>
<jsp:include page="/WEB-INF/views/adminheader.jsp" />

<div class="mainwrapper"> 
<c:set var="action" value="${pageContext.request.contextPath}/admin/admincontent/updatedInfopage" />


<div style = "width:800px; padding-left:4%;" id = "form">
<h1>Вести инфо-страницу:</h1>
<c:if test = "${result == 'success'}">
<div id = "success"><p>Инфо-страница успешно сохранена!</p></div>
</c:if>
<form:form method="post" action = "${action}"  modelAttribute="page" enctype="multipart/form-data">
<ul>

	<div id = "sector">
	<c:if test = "${type == 'update'}">
		<li>
		<div id = "row">
			<div id = "col1"><form:label type = "hidden" path="id">
				Id страницы:
			</form:label></div>
			<div id = "col2"><form:input type = "hidden" path="id" /></div>
			</div>
		</li>
	</c:if>
		<li>
		<div id = "row">
			<div id = "col1"><form:label path="name">
				Название страницы:
			</form:label></div>
		
			<div id = "col2"><form:input path="name" /><form:errors path="name" cssClass="error" /></div>

		</div>
		</li>
		
		<li>
		<div id = "row">
			<div id = "col1"><form:label path="url">
				URL страницы:
			</form:label></div>
		
			<div id = "col2"><form:input path="url" /><form:errors path="url" cssClass="error" /></div>

		</div>
		</li>
		
		<li>
		<div id = "row">
			<div id = "col1"><form:label path="title">
				Title страницы:
			</form:label></div>
		
			<div id = "col2"><form:input path="title" /><form:errors path="title" cssClass="error" /></div>

		</div>
		</li>
		
		<li>
		<div id = "row">
			<div id = "col1"><form:label path="html">
				Html страницы:
			</form:label></div>
		
			<div id = "col2"><form:textarea path="html" /><form:errors path="html" cssClass="error" /></div>

		</div>
		</li>
		
		<li>
		<div id = "row">
			<div id = "col1"><form:label path="meta">
				Meta-теги страницы:
			</form:label></div>
		
			<div id = "col2"><form:textarea path="meta" /><form:errors path="meta" cssClass="error" /></div>

		</div>
		</li>
		
		<li>
		<div id = "row">
			<div id = "col1"><form:label path="h1">
				H1 страницы:
			</form:label></div>
		
			<div id = "col2"><form:input path="h1" /><form:errors path="h1" cssClass="error" /></div>

		</div>
		</li>
		
		<li>
		<div id = "row">
			<div id = "col1"><form:label path="parent_page.id">
				Родительская страница:
			</form:label></div>
			<form:input type = "hidden" id = "parentpage_id" path="parent_page.id"/>
			<div id = "col2"><form:input id = "parentpage_text" path="parent_page.name" onclick = "load_entitychoise('${pageContext.request.contextPath}','parentpage','Model.Page', -1)"/><form:errors path="parent_page" cssClass="error" /></div>

		</div>
		</li>
			
		
		
		<li>
		<div id = "row">
			<div id = "col1"><form:label path="pagegroup">
				Группа страниц:
			</form:label></div>
			<form:input type = "hidden" id = "pagegroup_id" path="pagegroup.id"/>
			<div id = "col2"><form:input id = "pagegroup_text" path="pagegroup.name" onclick = "load_entitychoise('${pageContext.request.contextPath}','pagegroup','Model.PageGroup', -1)"/><form:errors path="pagegroup" cssClass="error" /></div>

		</div>
		</li>
		
		<li>
			<div id = "col1"><form:checkbox path="isinfooter" />Показывать в футере</div> 
		</li>
	
		<li>
			<div id = "col1"><form:checkbox path="isinheader" />Показывать в хедере</div> 
		</li>
		
		<li>
			<div id = "col1"><form:checkbox path="isinmainmenu" />Показывать в главном меню</div> 
		</li>
		
	<li>

			<div id = "col1"><input type="submit" value="Сохранить" /></div>
		
		</li>	
		
	</div>
</ul>

</form:form>
</div>
</div>
 


<div id = "popup" class = "popupwindow">
<div class="close"><img width="30" height="30" src="/web-shop/resources/images/x-button-gray.png"></div>
<div id = "popupcontent"></div>
</div>
<div id = "popup1" class = "popupwindow"></div>

<div id="popupbg"></div>
	<div id="dialog_wrapper">
		<div id="dialog_outer">
			<div id="dialog_inner">
				<a id="dialog_close" href="#"></a>
				<div id="dialog_title"></div>
				<div id="dialog_content"></div>
			</div>
		</div>
	</div>

</body>
</html>		
		