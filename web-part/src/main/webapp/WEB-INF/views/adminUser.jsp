<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="myfn" uri="http://samplefn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.min.js" type="text/javascript"></script>


	<script type="text/javascript" src="http://js.mobilluck.com.ua/js/slides.jquery.js"></script>
<script type="text/javascript" src="http://js.mobilluck.com.ua/js/jquery.maskedinput.js"></script>
<script type="text/javascript" src="http://js.mobilluck.com.ua/js/combobox/jquery.combobox.js"></script>
<script type="text/javascript" src="http://js.mobilluck.com.ua/js/jquery.lazyload.js?v=1.8.3"></script>
<script type="text/javascript" src="http://js.mobilluck.com.ua/js/popups2014v3.js"></script>
<script type="text/javascript" src="http://js.mobilluck.com.ua/js/myajax.js"></script>
<script type="text/javascript" src="http://js.mobilluck.com.ua/js/main.js"></script>
<script type="text/javascript" src="http://js.mobilluck.com.ua/js/jquery.jcarousel.min.js"></script>
<script type="text/javascript" src="http://js.mobilluck.com.ua/js/jcarousellite.js"></script>

<link type="text/css" rel="stylesheet" href="http://www.mobilluck.com.ua/css/date_style.css" />
<link rel="stylesheet" href="<c:url value="/resources/popupwindow.css"/>"/>
	<link rel="stylesheet" href="<c:url value="/resources/style.css"/>"/>
<script type="text/javascript" src="http://www.mobilluck.com.ua/js/ui.datepicker.js"></script>

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

<script>
var counter =0;
var town_id = parseInt("<c:out value="${town.id}"/>");
info_item={};
dltp_info=[];
var i =0;
var price=[];
var costl=[];
adminclassname = "User";
$(document).ready(function(){
$("#regdate").mask("9999-99-99");
$("#regtime").mask("99:99");
});
function appendState(){
	var div;
	var url = "<c:out value="${pageContext.request.contextPath}"/>" +"/admin/admincountry/"+ "<c:out value="${town.region.country.id}"/>";
	
	div = document.createElement("div");
	div.setAttribute("id", "adress"+counter);
	div.setAttribute("class", "newadress");
	
	$.ajax({
		type: "POST",
	url: url+"/addDelTypeToGood",
		data: {"counter":counter, "ids":dltp_info},
		dataType: "text",
		success: function(data){
			$("#parentId").html(data+"<br>");

		}
	});
//	div.innerHTML = "";
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


function showAdd(num){
//	alert("ewg");
	
	var bool = ($("#add"+num).html()!="")?true:false;
	if(costl[num] && bool==false){
		$("#add"+num).html('<input type="hidden" value = "0.0" name="deltypes['+num+'].price" id = "price'+num+'"/><input type="checkbox" onchange = "pricecost('+num+')" name="deltypes['+num+'].iscostless" id = "iscostless" checked/>Бесплатная<br>');
		return;
	}
	if(bool){
		price[num] = ($("#price"+num).val()==0)?price[num]:$("#price"+num).val();
		$("#add"+num).html("");
		bool = false;
		return;
	}
	
	price[num]= (price[num] == null)?0.0:price[num];
	$("#add"+num).html('<label>Цена доставки: </label><input type="text" value = "'+price[num]+'" name="deltypes['+num+'].price" id = "price'+num+'"/><input type="checkbox" onchange = "costless('+num+')" name="deltypes['+num+'].iscostless" id = "iscostless"/>Бесплатная<br>');
	
	bool=true;
}

function costless(num){
	price[num] = $("#price"+num).val();
	$("#add"+num).html("");
	$("#add"+num).html('<input type="hidden" value = "0.0" name="deltypes['+num+'].price" id = "price'+num+'"/><input type="checkbox" onchange = "pricecost('+num+')" name="deltypes['+num+'].iscostless" id = "iscostless" checked/>Бесплатная<br>');
	costl[num]=true;
}

function pricecost(num){
	
	$("#add"+num).html("");
	$("#add"+num).html('<label>Цена доставки: </label><input type="text" value = "'+price[num]+'" name="deltypes['+num+'].price" id = "price'+num+'"/><input type="checkbox" onchange = "costless('+num+')" name="deltypes['+num+'].iscostless" id = "iscostless"/>Бесплатная<br>');
	costl[num]=false;
}


</script>
</head>
<body>

 <jsp:include page="/WEB-INF/views/adminheader.jsp" />

<div class="mainwrapper"> 

<h2>Вести пользователя ${user.name}</h2>



<c:set var="action" value="${pageContext.request.contextPath}/admin/adminusers/${user.id}/updateduser" />

<form:form method="post" action="${action}" modelAttribute="user">
<c:if test = "${section == 'commonuserinfo'}">	
<div id = "form">

<div id = "sector">	
<h3>Основные данные:</h3>
		<form:input type = "hidden" path="id" />
		<ul>
		<li>
		<div id = "col1">
		<form:label path="name" >Имя: </form:label>
		</div>
		<div id = "col2">
		<form:input path="name" />
		</div>
		</li>
		
		<li>
		<div id = "col1">
		<form:label path="username" >Емейл-логин: </form:label>
		</div>
		<div id = "col2">
		<form:input path="username" />
		</div>
		</li>
		
		<li>
		<div id = "col1">
		<form:label path="originpassword" >Пароль: </form:label>
		</div>
		<div id = "col2">
		<form:input path="originpassword" />
		</div>
		</li>
		
		<li>
		<div id = "col1">
		<form:label path="town.name" >Название города: </form:label>
		</div>
		<div id = "col2">
		<form:input type = "hidden" path="town.id" />
		<form:input path="town.name" />
		</div>
		</li>
		
		
		
		<li>
		<div id = "col1">
		<form:label path="proved" >Подтверждена регистрация: </form:label>
		</div>
		<div id = "col2">
		<form:input path="proved" />
		</div>
		</li>
		
		
		</div>
		
	
		<div id = "sector">
		
		<li>
		<div id = "col1">
		<h4>Адрес: </h4>
		</div>
		<div id = "col1">
		<table>
		<c:if test = "${!empty user.useradress}">
		<tr><td>Тип улицы</td><td>Название улицы</td><td>Дом</td><td>Этаж</td><td>Квартира</td>
		</tr>
		
		<tr>
		<td>

						
											<form:input type = "hidden" path = "useradress.id"/>	
											<select class="fsel" name="useradress.street_type" id="adress_f">
												
												<option value="улица" <c:if test = "${user.useradress.street_type == 'улица'}">selected</c:if>>улица</option>
												
												<option  value="проспект" <c:if test = "${user.useradress.street_type == 'проспект'}">selected</c:if>>проспект</option>
												
												<option  value="площадь" <c:if test = "${user.useradress.street_type == 'площадь'}">selected</c:if>>площадь</option>
				
												<option value="бульвар" <c:if test = "${user.useradress.street_type == 'бульвар'}">selected</c:if>>бульвар</option>
											
												<option value="переулок" <c:if test = "${user.useradress.street_type == 'переулок'}">selected</c:if>>переулок</option>
												
												<option value="проезд" <c:if test = "${user.useradress.street_type == 'проезд'}">selected</c:if>>проезд</option>
												
												<option value="въезд" <c:if test = "${user.useradress.street_type == 'въезд'}">selected</c:if>>въезд</option>
											</select>
		</td>
										
		<td>								<input  type='text' style = "width:70px;" title="Название" placeholder="Название" placeholder2="Название" name="useradress.street_name" id="ulica" value="${user.useradress.street_name}" ></td>
		
		<td>									<input  style = "width:20px;" type='text' title="№ Дома" placeholder="№ Дома" placeholder2="№ Дома" name="useradress.house_num" id="nhouse" value="${user.useradress.house_num}"></td>
		<td>									<input style = "width:20px;" type='text' title="Этаж" placeholder="Этаж" placeholder2="Этаж" name="useradress.level" id="floar" value="${user.useradress.level}"></td>
		<td>									<input style = "width:20px;" type='text' title="№ Квартиры" placeholder="№ Квартиры" placeholder2="№ Квартиры" name="useradress.room_num" id="nroom" value="${user.useradress.room_num}"></td>
		<td>									<input style = "width:70px;" type='text' title="Номер телефона" placeholder="Номер телефона" placeholder2="Номер телефона" name="useradress.phnnumbers[0].numb" value="${user.useradress.phnnumbers[0].numb}"></td>				
												<input type='hidden'  name="useradress.phnnumbers[0].id" value="${user.useradress.phnnumbers[0].id}">
												<input type='hidden'  name="useradress.phnnumbers[0].isMainResidentNumber" value="${user.useradress.phnnumbers[0].isMainResidentNumber}">
												<input type='hidden'  name="useradress.phnnumbers[0].phtype" value="${user.useradress.phnnumbers[0].phtype}">
												<input type = "hidden" name = "useradress.adresstype" value = "${user.useradress.adresstype}">
												
	
		
		</tr>
		
												
		
		</c:if>
		</table>
		</div>
		</li>
		
		
		</div>
		<div id = "sector">
		
		<li>
		<div id = "col1">
		<form:label path="regdate" >Дата регистрации: </form:label>
		</div>
		<div id = "col2">
		<form:input path="regdate" />
		</div>
		</li>
		
		<li>
		<div id = "col1">
		<form:label path="regtime" >Время регистрации: </form:label>
		</div>
		<div id = "col2">
		<form:input path="regtime" />
		</div>
		</li>
		
		<li>
		<div id = "col1">
		<form:label path="birthdate" >Дата рождения: </form:label>
		</div>
		<div id = "col2">
		<form:input path="birthdate" />
		</div>
		</li>
		
		<li>
		<div id = "col1">
		<form:label path="nick" >Ник: </form:label>
		</div>
		<div id = "col2">
		<form:input path="nick" />
		</div>
		</li>
		
		<li>
		<div id = "col1">
		<form:label path="isbanned" >Бан: </form:label>
		</div>
		<div id = "col2">
		<form:input path="isbanned" />
		</div>
		</li>
		
   	
 
		
		   </div>
	   
		   </ul>
			
	
</div>
</c:if>	
<c:if test = "${section == 'commonuserinfo'}">		
<div id = "userorders">		
		
		<h3>Заказы ползователя:</h3>
		<div id = "parentId" class = "newadress"></div>
<div id="list" class = "list">
		<table id = "table" class="data" style = "">
<tr>
			<th id = "selector"></th>
			<c:forEach items="${ordercontainers[0].fields}" var="field">
			<th type = "${field.key.propclassname}"<c:if test = "${field.key.isobject == true}">isobject = "true"</c:if> id = "${field.key.propname}">${field.key.colname}</th>
			</c:forEach>
			<th id = "functions">Функции</th>
			
</tr>

<c:forEach items="${ordercontainers}" var="container" varStatus = "i">
			<tr>
				<td id = "selector${i.index}" style = "background: #ededed;"></td>
				<c:forEach items="${container.fields}" var="field" varStatus = "i">
				<c:if test = "${field.key.propname == 'id'}"><input name = "orders[${i.index}].id" type = "hidden"/></c:if>
				<td>${field.value}</td>
				
				</c:forEach>
				<td>
				<c:forEach items="${container.refs}" var="entry">
				<a href="${pageContext.request.contextPath}${entry.value}">${entry.key}</a><br>
				</c:forEach>
				</td>
				
			
			</tr>
</c:forEach>
</table>
</div>
<div class="ccpnav">
		<div id = "pages">
		<span class="arrow">&#8592;</span><a class="a-text a-prev" href="${pageContext.request.contextPath}${entityurl}${orderspgnum-1}">Пред.</a>
		<c:forEach begin="1" end="${orderspagecount+1}" var="i">
		<c:choose>
		<c:when test = "${i==orderspgnum}">
		<span class="">${i}</span><input type = "hidden" value = "${i}" name = "orderspgnum" id = "orderspgnum"/>
		</c:when>
		<c:otherwise>
		<a class="a-text" href="${pageContext.request.contextPath}${entityurl}${i}">${i}</a>
		</c:otherwise>
		</c:choose>
		</c:forEach>
		<a class="a-text a-next" href="${pageContext.request.contextPath}${entityurl}${orderspgnum+1}">След.</a><span class="arrow">&#8594;</span>
		</div>
</div>
</div>

<div id = "userhistory">	

		<h3>История просмотра ползователя:</h3>
		<div id = "parentId" class = "newadress"></div>
<div id="list" class = "list">
		<table id = "table" class="data">
<tr>
			<th id = "selector"></th>
			<c:forEach items="${historygoodcontainers[0].fields}" var="field">
			<th type = "${field.key.propclassname}"<c:if test = "${field.key.isobject == true}">isobject = "true"</c:if> id = "${field.key.propname}">${field.key.colname}</th>
			</c:forEach>
			<th id = "functions">Функции</th>
			
</tr>

<c:forEach items="${historygoodcontainers}" var="container" varStatus = "i">
			<tr>
				<td id = "selector${i.index}" style = "background: #ededed;"></td>
				<c:forEach items="${container.fields}" var="field" varStatus = "i">
				<c:if test = "${field.key.propname == 'id'}"><input name = "historygoods[${i.index}].id" type = "hidden"/></c:if>
				<td>${field.value}</td>
				
				</c:forEach>
				<td>
				<c:forEach items="${container.refs}" var="entry">
				<a href="${pageContext.request.contextPath}${entry.value}">${entry.key}</a><br>
				</c:forEach>
				</td>
				
			
			</tr>
</c:forEach>
</table>
</div>
<div class="ccpnav">
		<div id = "pages">
		<span class="arrow">&#8592;</span><a class="a-text a-prev" href="${pageContext.request.contextPath}${entityurl}${historygoodpgnum-1}">Пред.</a>
		<c:forEach begin="1" end="${historygoodpagecount+1}" var="i">
		<c:choose>
		<c:when test = "${i==historygoodpgnum}">
		<span class="">${i}</span><input type = "hidden" value = "${i}" name = "historygoodpgnum" id = "historygoodpgnum"/>
		</c:when>
		<c:otherwise>
		<a class="a-text" href="${pageContext.request.contextPath}${entityurl}${i}">${i}</a>
		</c:otherwise>
		</c:choose>
		</c:forEach>
		<a class="a-text a-next" href="${pageContext.request.contextPath}${entityurl}${historygoodpgnum+1}">След.</a><span class="arrow">&#8594;</span>
		</div>
</div>
</div>

<div id = "userwishes">
		<h3>Желаемые товары ползователя:</h3>
	<div id="list" class = "list">
		<div id = "parentId" class = "newadress"></div>
		<table id = "table" class="data">
<tr>
			<th id = "selector"></th>
			<c:forEach items="${wishgoodcontainers[0].fields}" var="field">
			<th type = "${field.key.propclassname}"<c:if test = "${field.key.isobject == true}">isobject = "true"</c:if> id = "${field.key.propname}">${field.key.colname}</th>
			</c:forEach>
			<th id = "functions">Функции</th>
			
</tr>

<c:forEach items="${wishgoodcontainers}" var="container" varStatus = "i">
			<tr>
				<td id = "selector${i.index}" style = "background: #ededed;"></td>
				<c:forEach items="${container.fields}" var="field" varStatus = "i">
				<c:if test = "${field.key.propname == 'id'}"><input name = "wishgoods[${i.index}].id" type = "hidden"/></c:if>
				<td>${field.value}</td>
				
				</c:forEach>
				<td>
				<c:forEach items="${container.refs}" var="entry">
				<a href="${pageContext.request.contextPath}${entry.value}">${entry.key}</a><br>
				</c:forEach>
				</td>
				
			
			</tr>
</c:forEach>
</table>
</div>		
<div class="ccpnav">
		<div id = "pages">
		<span class="arrow">&#8592;</span><a class="a-text a-prev" href="${pageContext.request.contextPath}${entityurl}${wishgoodpgnum-1}">Пред.</a>
		<c:forEach begin="1" end="${wishgoodpagecount+1}" var="i">
		<c:choose>
		<c:when test = "${i==wishgoodpgnum}">
		<span class="">${i}</span><input type = "hidden" value = "${i}" name = "wishgoodpgnum" id = "wishgoodpgnum"/>
		</c:when>
		<c:otherwise>
		<a class="a-text" href="${pageContext.request.contextPath}${entityurl}${i}">${i}</a>
		</c:otherwise>
		</c:choose>
		</c:forEach>
		<a class="a-text a-next" href="${pageContext.request.contextPath}${entityurl}${wishgoodpgnum+1}">След.</a><span class="arrow">&#8594;</span>
		</div>
</div>
</div>


<div id = "userwatches">
		
				<h3>Отслеживаемые товары ползователя:</h3>

		<div id = "parentId" class = "newadress"></div>
<div id="list" class = "list">
		<table id = "table" class="data">
<tr>
			<th id = "selector"></th>
			<c:forEach items="${watchgoodcontainers[0].fields}" var="field">
			<th type = "${field.key.propclassname}"<c:if test = "${field.key.isobject == true}">isobject = "true"</c:if> id = "${field.key.propname}">${field.key.colname}</th>
			</c:forEach>
			<th id = "functions">Функции</th>
			
</tr>

<c:forEach items="${watchgoodcontainers}" var="container" varStatus = "i">
			<tr>
				<td id = "selector${i.index}" style = "background: #ededed;"></td>
				<c:forEach items="${container.fields}" var="field" varStatus = "i">
				<c:if test = "${field.key.propname == 'id'}"><input name = "watchgoods[${i.index}].id" type = "hidden"/></c:if>
				<td>${field.value}</td>
				
				</c:forEach>
				<td>
				<c:forEach items="${container.refs}" var="entry">
				<a href="${pageContext.request.contextPath}${entry.value}">${entry.key}</a><br>
				</c:forEach>
				</td>
				
			
			</tr>
</c:forEach>
</table>
</div>
		
<div class="ccpnav">
		<div id = "pages">
		<span class="arrow">&#8592;</span><a class="a-text a-prev" href="${pageContext.request.contextPath}${entityurl}${watchgoodpgnum-1}">Пред.</a>
		<c:forEach begin="1" end="${watchgoodpagecount+1}" var="i">
		<c:choose>
		<c:when test = "${i==watchgoodpgnum}">
		<span class="">${i}</span><input type = "hidden" value = "${i}" name = "watchgoodpgnum" id = "watchgoodpgnum"/>
		</c:when>
		<c:otherwise>
		<a class="a-text" href="${pageContext.request.contextPath}${entityurl}${i}">${i}</a>
		</c:otherwise>
		</c:choose>
		</c:forEach>
		<a class="a-text a-next" href="${pageContext.request.contextPath}${entityurl}${watchgoodpgnum+1}">След.</a><span class="arrow">&#8594;</span>
		</div>
</div>
</div>
		
<div id = "phonenumbers">
		
				<h3>Номера телефонов:</h3>

		<div id = "parentId" class = "newadress"></div>
<div id="list" class = "list">
		<table id = "table" class="data">
<tr>
			<th id = "selector"></th>
			<c:forEach items="${phonecontainers[0].fields}" var="field">
			<th type = "${field.key.propclassname}"<c:if test = "${field.key.isobject == true}">isobject = "true"</c:if> id = "${field.key.propname}">${field.key.colname}</th>
			</c:forEach>
			<th id = "functions">Функции</th>
			
</tr>

<c:forEach items="${phonecontainers}" var="container" varStatus = "i">
			<tr>
				<td id = "selector${i.index}" style = "background: #ededed;"></td>
				<c:forEach items="${container.fields}" var="field" varStatus = "i">
				<c:if test = "${field.key.propname == 'id'}"><input name = "phonenumbers[${i.index}].id" type = "hidden"/></c:if>
				<td>${field.value}</td>
				
				</c:forEach>
				<td>
				<c:forEach items="${container.refs}" var="entry">
				<a href="${pageContext.request.contextPath}${entry.value}">${entry.key}</a><br>
				</c:forEach>
				</td>
				
			
			</tr>
</c:forEach>
</table>
</div>
		
<div class="ccpnav">
		<div id = "pages">
		<span class="arrow">&#8592;</span><a class="a-text a-prev" href="${pageContext.request.contextPath}${entityurl}${phonespgnum-1}">Пред.</a>
		<c:forEach begin="1" end="${phonespagecount+1}" var="i">
		<c:choose>
		<c:when test = "${i==phonespgnum}">
		<span class="">${i}</span><input type = "hidden" value = "${i}" name = "watchgoodpgnum" id = "watchgoodpgnum"/>
		</c:when>
		<c:otherwise>
		<a class="a-text" href="${pageContext.request.contextPath}${entityurl}${i}">${i}</a>
		</c:otherwise>
		</c:choose>
		</c:forEach>
		<a class="a-text a-next" href="${pageContext.request.contextPath}${entityurl}${phonespgnum+1}">След.</a><span class="arrow">&#8594;</span>
		</div>
</div>
</div>		
</c:if>	

<c:if test = "${section == 'userroles'}">		
<div id = "userroles">
		
				<h3>Роли пользователя:</h3>

		<div id = "parentId" class = "newadress"></div>
<div id="list" class = "list">
		<table id = "table" class="data">
<tr>
			<th id = "selector"></th>
			<c:forEach items="${rolecontainers[0].fields}" var="field">
			<th type = "${field.key.propclassname}"<c:if test = "${field.key.isobject == true}">isobject = "true"</c:if> id = "${field.key.propname}">${field.key.colname}</th>
			</c:forEach>
			<th id = "functions">Функции</th>
			
</tr>

<c:forEach items="${rolecontainers}" var="container" varStatus = "i">
			<tr>
				<td id = "selector${i.index}" style = "background: #ededed;"></td>
				<c:forEach items="${container.fields}" var="field" varStatus = "i">
				<c:if test = "${field.key.propname == 'authority'}"><input name = "roles[${i.index}].authority" type = "hidden"/></c:if>
				<td>${field.value}</td>
				
				</c:forEach>
				<td>
				<c:forEach items="${container.refs}" var="entry">
				<a href="${pageContext.request.contextPath}${entry.value}">${entry.key}</a><br>
				</c:forEach>
				</td>
				
			
			</tr>
</c:forEach>
</table>
</div>
		
<div class="ccpnav">
		<div id = "pages">
		<span class="arrow">&#8592;</span><a class="a-text a-prev" href="${pageContext.request.contextPath}${entityurl}${rolespgnum-1}">Пред.</a>
		<c:forEach begin="1" end="${rolespagecount+1}" var="i">
		<c:choose>
		<c:when test = "${i==rolespgnum}">
		<span class="">${i}</span><input type = "hidden" value = "${i}" name = "rolespgnum" id = "rolespgnum"/>
		</c:when>
		<c:otherwise>
		<a class="a-text" href="${pageContext.request.contextPath}${entityurl}${i}">${i}</a>
		</c:otherwise>
		</c:choose>
		</c:forEach>
		<a class="a-text a-next" href="${pageContext.request.contextPath}${entityurl}${rolespgnum+1}">След.</a><span class="arrow">&#8594;</span>
		</div>
</div>
</div>
<div id = "propval0"><div class = "propvalue" id = "propvalue0"><a role = "button" id="add" onclick="load_entitychoise('/web-shop', ${user.id}, 'Model.Role', 0)">Добавить роль</a></div></div>
<a id="create" href = "<c:url value ="/admin/createrole"/>">Создать роль</a>
	
</c:if>	
	<input type="submit"
				value="Обновить состояния!" />

</form:form>

</div>
<div id = "popup1" class = "popupwindow"></div>
</body>
</html>