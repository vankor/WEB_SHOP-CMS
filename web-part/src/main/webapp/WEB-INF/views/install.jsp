<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf8">
	<link rel="stylesheet" type="text/css"  href="<c:url value="/resources/style.css" />" rel="stylesheet" >
	    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>  
	<title>Установка</title>
	<link rel="stylesheet" href="<c:url value="/resources/style1.css"/>"/>
	
<link rel="stylesheet" href="<c:url value="/resources/style.css"/>"/>


<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.min.js" type="text/javascript"></script>

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
	
	
		<script type="text/javascript" src="<c:url value="/resources/js/tinymce/tinymce.min.js" />" ></script>
		<script>
		$(document).ready(function(){
		$("#settingsfolder").on('change',function (){
	    	  var filename = $(this).val();
	    	  alert(filename);
	   // 	  $('#thumb').val(filename);
	    	});
		});
		</script>

</head>
<body>
 <jsp:include page="/WEB-INF/views/adminheader.jsp" />

<div class="mainwrapper"> 
<div style = "width:800px; padding-left:4%;" id = "form">
<h2>Установка базовых параметров:</h2>
<c:if test = "${result == 'success'}">
<div id = "success"><p>Товар успешно сохранен!</p></div>
</c:if>
<form:form method="post"  modelAttribute="config" enctype="multipart/form-data">
	<ul>
	<div id = "sector">
	<h4>Параметры базы данных:</h4>

	
	
	<li>
	<div id = "row">
			<div id = "col1">
				<form:label path="dbprovider">База данных</form:label>
			</div>
			<div id = "col2">
				<form:select path="dbprovider">
    				<option value = "1">MySQL</option>
    				<option value = "2">Oracle</option>
				</form:select>
				<form:errors path="dbprovider" cssClass="error" />
			</div>
		</div>
	</li>
	

	<li>
	<div id = "row">
			<div id = "col1">
				<form:label path="dbname">
					Название базы данных
				</form:label>
			</div>
			<div id = "col2">
				<form:input path="dbname" /><form:errors path="dbname" cssClass="error" />
			</div>
		</div>
	</li>
	
	<li>
	<div id = "row">
			<div id = "col1">
				<form:label path="username">
					Имя пользователя базы данных
				</form:label>
			</div>
			<div id = "col2">
				<form:input path="username" /><form:errors path="username" cssClass="error" />
			</div>
		</div>
	</li>
	
	<li>
	<div id = "row">
			<div id = "col1">
				<form:label path="password">
					Пароль пользователя базы данных
				</form:label>
			</div>
			<div id = "col2">
				<form:input path="password" /><form:errors path="password" cssClass="error" />
			</div>
		</div>
	</li>
	
	<li>
	<div id = "row">
			<div id = "col1">
				<form:label path="dbhost">
					Хост базы данных
				</form:label>
			</div>
			<div id = "col2">
				<form:input path="dbhost" /><form:errors path="dbhost" cssClass="error" />
			</div>
		</div>
	</li>
	
	<li>
	<div id = "row">
			<div id = "col1">
				<form:label path="dbport">
					Номер порта базы данных
				</form:label>
			</div>
			<div id = "col2">
				<form:input path="dbport" /><form:errors path="dbport" cssClass="error" />
			</div>
		</div>
	</li>
	</div>
	<div id = "sector">
	<h4>Параметры администратора:</h4>
	<li>
	<div id = "row">
			<div id = "col1">
				<form:label path="admin_login">
					Логин администратора
				</form:label>
			</div>
			<div id = "col2">
				<form:input path="admin_login" /><form:errors path="admin_login" cssClass="error" />
			</div>
		</div>
	</li>
	
	<li>
	<div id = "row">
			<div id = "col1">
				<form:label path="admin_password">
					Пароль администратора
				</form:label>
			</div>
			<div id = "col2">
				<form:input path="admin_password" /><form:errors path="admin_password" cssClass="error" />
			</div>
		</div>
	</li>
	<!--  -->
	
	
	
	</div>
	<div id = "sector">
	<h4>Настройки кеша:</h4>
	<li>
	<div id = "row">
			<div id = "col1">
				<form:label path="use_second_level_cache">Использовать общий кеш обьектов</form:label>
			</div>
			<div id = "col2">
				<form:checkbox path="use_second_level_cache" />
				<form:errors path="use_second_level_cache" cssClass="error" />
			</div>
		</div>
	</li>
	
	<li>
	<div id = "row">
			<div id = "col1">
				<form:label path="use_query_cache">Использовать кеш запросов</form:label>
			</div>
			<div id = "col2">
				<form:checkbox path="use_query_cache" />
				<form:errors path="use_query_cache" cssClass="error" />
			</div>
		</div>
	</li>

		
	
	
	
	</div>
	
	
	
	<div id = "sector">
	<h4>Параметры магазина:</h4>
	<li>
	<div id = "row">
			<div id = "col1">
				<form:label path="shopname">
					Название магазина
				</form:label>
			</div>
			<div id = "col2">
				<form:input path="shopname" /><form:errors path="shopname" cssClass="error" />
			</div>
		</div>
	</li>
	

	<li>
			<div id = "col1">
			<input type="submit"
				value="Сохранить изменения" /></div>
				
		</li>

	</div>
	</ul>
	
</form:form>



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
				<!--<div class="dinner_t"></div>
				<div class="dinner_b"></div>
				<div class="dinner_l"></div>
				<div class="dinner_r"></div>
				<div class="dinner_tl"></div>
				<div class="dinner_tr"></div>
				<div class="dinner_bl"></div>
				<div class="dinner_br"></div>
				-->
			</div>
		</div>
	</div>

</body>
</html>