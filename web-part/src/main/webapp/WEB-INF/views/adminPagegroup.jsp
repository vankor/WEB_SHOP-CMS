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
<c:set var="action" value="${pageContext.request.contextPath}/admin/admincontent/updatedPagegroup" />


<div style = "width:800px; padding-left:4%;" id = "form">
<h1>Вести группу страниц:</h1>
<c:if test = "${result == 'success'}">
<div id = "success"><p>Группа страниц успешно сохранена!</p></div>
</c:if>
<form:form method="post" action = "${action}"  modelAttribute="pagegroup" enctype="multipart/form-data">
<ul>

	<div id = "sector">
	<c:if test = "${type == 'update'}">
				
			<div id = "col2"><form:input type = "hidden" path="id" /></div>
	
	</c:if>
		<li>
		<div id = "row">
			<div id = "col1"><form:label path="name">
				Название группы:
			</form:label></div>
		
			<div id = "col2"><form:input path="name" /><form:errors path="name" cssClass="error" /></div>
			
		
		
		</div>
		</li>
		
		<li>
		<div id = "row">
			<div id = "col1"><form:label id = "isinfooter" path="isinfooter">
				Выводить группу в футере:
			</form:label></div>
			<div id = "col2"><form:checkbox path="isinfooter" /></div>
		</div>
		</li>
			
	<li>
		<div id = "row">
			<div id = "col1">
				<form:label path="thumb">
					Аватарка группы
				</form:label>
			</div>
			<div id = "col2">
			<form:input type = "hidden" path = "thumb" id = "thumb"/>
				<c:choose><c:when test = "${!empty pagegroup.thumb}"><img src="<c:url value="/resources/images${pagegroup.thumb}" />" width="50" alt="${pagegroup.name}"  /></c:when><c:otherwise>Нет аватара   </c:otherwise></c:choose><form:input id = "newthumb" type = "file" text = "Загрузить новый аватар" path="newthumb"/>
			<form:errors path="thumb" cssClass="error" />
			</div>

			
		</div>
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
		