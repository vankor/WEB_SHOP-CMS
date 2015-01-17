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

<script>
var counter =0;
var counterres =0;
var counterdel =0;
var town_id = parseInt("<c:out value="${town.id}"/>");
info_item={};
dltp_info=[];
dltp_info1=[];
var i =0;
var price=[];
var costl=[];
cc=[];
cc[counter] = 0;

function appendState(){
	var div;
	var url = "<c:out value="${pageContext.request.contextPath}"/>" +"/admin/admincountry/"+ "<c:out value="${town.region.country.id}"/>";
	
	div = document.createElement("div");
	div.setAttribute("id", "adress"+counter);
	div.setAttribute("class", "newadress");
	
	$.ajax({
		type: "POST",
	url: url+"/addDelTypeToGood",
		data: {"counter":counter,"innercounter":counterres, "ids":dltp_info, "type":"RESIDENT"},
		dataType: "text",
		success: function(data){
			$("#parentId").append(data+"<br>");
			cc[counter] = 0;
			counter++;
			counterres++;
			document.getElementById("parentId").appendChild(div);
		}
	});
//	div.innerHTML = "";
		
//	alert("cc["+counter+"]=0");
	
	

	
	
}



function appendDelservAdress(){
	var div;
	var url = "<c:out value="${pageContext.request.contextPath}"/>" +"/admin/admincountry/"+ "<c:out value="${town.region.country.id}"/>";
	
	div = document.createElement("div");
	div.setAttribute("id", "adress"+counter);
	div.setAttribute("class", "newadress");
	
	$.ajax({
		type: "POST",
		url: url+"/addDelTypeToGood",
		data: {"counter":counter, "innercounter":counterdel, "ids":dltp_info, "type":"DELIVERYSERVICE"},
		dataType: "text",
		success: function(data){
			$("#parentDelId").append(data+"<br>");
			cc[counter] = 0;
			counter++;
			counterdel++;
			document.getElementById("parentDelId").appendChild(div);
		}
	});
//	div.innerHTML = "";
	
	
	
	
}


function removePhone(h, id, segm){
//	alert("#propsegment"+id);
	var strid;
	var elem;
	if(segm){
		$("#propsegment"+id).remove();
		counter--;
		}
	
	else{
		elem = $("#propsegment"+h).find('#numb'+id);
		cc[id]--;
	$(elem).remove();}

}


var flags = {
		'UA': '<img src="<c:url value="/resources/images/Flags-00.png"/>"><p style="display:none">UA</p>',
		'RU': '<img src="<c:url value="/resources/images/Flags-01.png"/>"><p style="display:none">RU</p>',
		'BEL': '<img src="<c:url value="/resources/images/Flags-02.png"/>"><p style="display:none">BEL</p>',
		'MOL': '<img src="<c:url value="/resources/images/Flags-03.png"/>"><p style="display:none">MOL</p>'};

	function flggs(){
		for (var i in flags)
		{
			$(".mobile_f .selectbox .select .text").each( function()
			{
				temp=$(this).html().replace(i,flags[i]);
				$(this).html(temp);
			});
			$(".mobile_f .selectbox li").each( function()
			{
				temp=$(this).html().replace(i,flags[i]);
				$(this).html(temp);
			});
		}
	}

$(document).ready(function(){
	$(".phone_f .selectbox li").live('click', function () {
		for (var i in flags)
		{
			temp=$(this).html().replace(i,flags[i]);
			$(this).html(temp);
		}
		flggs();
	});
	$.mask.definitions['2'] = "[0-2]";
	$.mask.definitions['6'] = "[0-5]";
//	$("#time").mask("29:69");
	
	$(".phone_f").live('change', function () {
		var mask='+38 (099) 999-99-99';
		var sel=$(this).val();
		if($(this).val()=='+380'){mask='+38 (099) 999-99-99';}
		if($(this).val()=='+7'){mask='+7 (999) 999-99-99';}
		if($(this).val()=='+375'){mask='+375 (99) 999-99-99';}
		if($(this).val()=='+373'){mask='+373 (99) 999-99-99';}
		$(this).parent().find('input').eq(0).mask(mask);
	});
});	
	
function appendPhone(parid){
//	alert("propsegment"+parid);
	propnum = cc[parid];
	
	var div;
	
	div = document.createElement("div");
	div.setAttribute("id", "numb"+propnum);
	div.setAttribute("class", "addpropertysegment");
	div.innerHTML = "<label id = 'props"+propnum+".name' name='props["+propnum+"].name'>"+(propnum+1)+") </label><select style='min-width:60px; width:80px;' class='phone_f'><option value='+380' >UA</option><option value='+7'>RU</option><option value='+375'>BEL</option><option value='+373'>MOL</option></select><input class='clientphone_f"+parid+propnum+"' id = 'clienphone_f' name='residents["+parid+"].phnnumbers["+propnum+"].numb' type='text'/><label>Основной номер: </label><input type='checkbox' name='residents["+parid+"].phnnumbers["+propnum+"].isMainResidentNumber'/><span style = 'margin-left:100px;'><a href = 'javascript:removePhone("+parid+", "+propnum+", false)'>Удалить номер</a></span>";
//	alert("ee");
	
	

	
			flggs();
			/*	$(".clientphone_f"+parid+propnum).mask("+38 (099) 999-99-99");
			

			

				*/
	
	
	
	
	cc[parid]++;
//	alert(div);
	document.getElementById("propsegment"+parid).appendChild(div);
	
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


function showAddforAddr(ind, num){
	var bool = ($("#add_"+ind+"_"+num).html()!="")?true:false;
	if(bool==false){
	//	<label>Время доставки: </label> От <input type="text" style = "min-width:30px; width:30px;" id = "mindays${k.index}" name="residents[${i.index}].deltypes[${k.index}].mindays" value="${resident.deltypes[k.index].mindays}"/> до <input style = "min-width:30px; width:30px;" type="text" id = "maxdays${k.index}" name="residents[${i.index}].deltypes[${k.index}].maxdays" value="${resident.deltypes[k.index].maxdays}"/>
		$("#add_"+ind+"_"+num).html('<div class = "addterms">Время доставки: от <input type="text" value = "0" name="residents['+ind+'].deltypes['+num+'].mindays" id = "mindays'+num+'"/> до <input type="text" value = "0" name="residents['+ind+'].deltypes['+num+'].maxdays" id = "maxdays'+num+'"/> дней</div><div class = "addrprice" id = "pricediap_'+ind+'_'+num+'" style = "display:none;">Цена: от <input type="text" value = "0" name="residents['+ind+'].deltypes['+num+'].minprice" id = "minprice'+num+'"/> до <input type="text" value = "0" name="residents['+ind+'].deltypes['+num+'].maxprice" id = "maxprice'+num+'"/> грн</div><input id = "addrdel_costless_'+ind+'_'+num+'" type="checkbox" onchange = "pricecostaddr('+ind+','+num+')" name="residents['+ind+'].deltypes['+num+'].iscostless" id = "iscostless" checked/>Бесплатная ');
		return;
	}
	else{
//		price[num] = ($("#delprice"+num).val()==0)?price[num]:$("#delprice"+num).val();
		$("#add_"+ind+"_"+num).html("");
		bool = false;
		return;
	}
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

function pricecostaddr(ind, num){
	
	$('#pricediap_'+ind+'_'+num).show();
	$('#addrdel_costless_'+ind+'_'+num).attr("onchange","costlessaddr("+ind+", "+num+")");
/*	$("#add"+num).html('<label>Цена доставки: </label><input type="text" value = "'+price[num]+'" name="deltypes['+num+'].price" id = "price'+num+'"/><input type="checkbox" onchange = "costless('+num+')" name="deltypes['+num+'].iscostless" id = "iscostless"/>Бесплатная<br>');
	costl[num]=false;*/
}

function costlessaddr(ind, num){
	$('#pricediap_'+ind+'_'+num).hide();
	$('#addrdel_costless_'+ind+'_'+num).attr("onchange","pricecostaddr("+ind+", "+num+")");
}




</script>
</head>
<body>

 <jsp:include page="/WEB-INF/views/adminheader.jsp" />

<div  class="mainwrapper"> 

<h2>Данные для города ${town.name}</h2>
<c:if test = "${result == 'success'}">
<div id = "success"><p>Город успешно сохранен!</p></div>
</c:if>
<c:forEach items = "${idbean.ids}" var ="id">
<script>
dltp_info[i] = "<c:out value="${id}"/>";
i++;	

</script>
</c:forEach>
<script>
info_item["ids"] = dltp_info;

</script>
<c:set var="action" value="${pageContext.request.contextPath}/admin/admincountry/${town.region.country.id}/updatedTown" />

<form:form method="post" action="${action}" modelAttribute="town">
<div style = "padding-left: 5%; float:left; width:880px;" id = "form">
<div id = "sector">	
<h3>Основные данные:</h3>
		<form:input type = "hidden" path="id" />
		<ul>
		<li>
		<div id = "col1">
		<form:label path="phonecode" >Название города: </form:label>
		</div>
		<div id = "col2">
		<form:input path="name" />
		</div>
		</li>
		
		<li>
		<div id = "col1">
		<form:label path="phonecode" >Телефонный код: </form:label>
		</div>
		<div id = "col2">
		<form:input path="phonecode" />
		</div>
		</li>
		<br>
		<li>
		<div id = "col1">
		<form:label path="phonecode" >Страна: </form:label>
		</div>
		<div id = "col2">
		<select name="region.country.id" id="country" >
			 <c:forEach items = "${countries}" var = "country">
										<c:choose>
										<c:when test="${country.id==town.region.country.id}">
                						<option selected="selected" value="${country.id}" >${country.name}</option>
                						</c:when>
                						<c:otherwise>
                						<option value="${country.id}" >${country.name}</option>
                						</c:otherwise>
                						</c:choose>
              </c:forEach>
           
        </select>
		</div>
		</li>
		<br>
		<li>
		<div id = "col1">
		<form:input type = "hidden" path="region.country.name" />
		<form:label path="phonecode" >Регион: </form:label>
		</div>
		<div id = "col2">
		<select name="region.id" id="region" >
			 <c:forEach items = "${regions}" var = "region">
										<c:choose>
										<c:when test="${region.id==town.region.id}">
                						<option selected="selected" value="${region.id}" >${region.name}</option>
                						</c:when>
                						<c:otherwise>
                						<option value="${region.id}" >${region.name}</option>
                						</c:otherwise>
                						</c:choose>
              </c:forEach>
           
        </select>
         <form:input type = "hidden" path="region.name" />
		</div>
		</li>
        <li>
		<div id = "col1">
		<form:label path="isdefault" >Город по умолчанию (если не задан пользователем): </form:label>
		</div>
		<div id = "col2">
		<form:checkbox path="isdefault" />
		</div>
		</li>
			
		
      </div>	
       <div id = "sector">
		<h3>Способы доставки:</h3>
		<c:if test = "${!empty deltypes}">
		<c:forEach items = "${deltypes}" var = "deltype" varStatus = "k">
				<div style = "margin-left:40px;">
				<c:choose>
				<c:when test = "${myfn:containsdt(town.deltypes,deltype)}">	
				<input type="checkbox" name="deltypes[${k.index}].deltype.id" value="${deltype.id}" onchange = "showAdd(${k.index});" checked>${deltype.name}		
				<input type="hidden" name="deltypes[${k.index}].deltype.name" value="${deltype.name}"/>
				<input type="hidden" name="deltypes[${k.index}].deltype.shortname" value="${deltype.name}"/>
				<div style = "padding-left:20px;" id = "add${k.index}">
				<c:if test = "${town.deltypes[k.index].iscostless == false}"><label>Цена доставки: </label><input type="text" id = "price${k.index}" name="deltypes[${k.index}].price" value="${town.deltypes[k.index].price}"/></c:if>
				<c:choose>
				<c:when test = "${town.deltypes[k.index].iscostless == true}">
				<input type="checkbox" name="deltypes[${k.index}].iscostless" checked/>Бесплатная<br>	
				</c:when>
				<c:otherwise>
				<input type="checkbox" name="deltypes[${k.index}].iscostless" onchange = "costless(${k.index});"/>Бесплатная<br>
				</c:otherwise>
				</c:choose>
				</div>
				</c:when>
				<c:otherwise>
				<input type="checkbox" name="deltypes[${k.index}].deltype.id" value="${deltype.id}"  onchange = "showAdd(${k.index});">${deltype.name}
				<input type="hidden" name="deltypes[${k.index}].deltype.name" value="${deltype.name}"/>
				<input type="hidden" name="deltypes[${k.index}].deltype.shortname" value="${deltype.shortname}"/>
				
				&nbsp&nbsp&nbsp&nbsp&nbsp<div style = "padding-left:20px;"  id = "add${k.index}"></div>	
				
				</c:otherwise>
				</c:choose>
				</div>
				<br>
		</c:forEach>
		</c:if>
		</div>
		</ul>
			
	
</div>

<!--  
<div id ="spots">		
		
		<h3>Представительства магазина:</h3>
		<div id = "parentId" class = "newadress"></div>
		<table>
		<c:if test = "${!empty town.residents}">
		<th><td>Тип улицы</td><td>Название улицы</td><td>Дом</td><td>Этаж</td><td>Квартира</td>
		</th>
		<c:forEach items = "${town.shopresidents}" var = "resident" varStatus = "i">
		<tr><td></td>
		<td>

						
											<form:input type = "hidden" path = "residents[${i.index}].id"/>	
											<select class="fsel" name="residents[${i.index}].street_type" id="adress_f">
												
												<option value="улица" <c:if test = "${resident.street_type == 'улица'}">selected</c:if>>улица</option>
												
												<option  value="проспект" <c:if test = "${resident.street_type == 'проспект'}">selected</c:if>>проспект</option>
												
												<option  value="площадь" <c:if test = "${resident.street_type == 'площадь'}">selected</c:if>>площадь</option>
				
												<option value="бульвар" <c:if test = "${resident.street_type == 'бульвар'}">selected</c:if>>бульвар</option>
											
												<option value="переулок" <c:if test = "${resident.street_type == 'переулок'}">selected</c:if>>переулок</option>
												
												<option value="проезд" <c:if test = "${resident.street_type == 'проезд'}">selected</c:if>>проезд</option>
												
												<option value="въезд" <c:if test = "${resident.street_type == 'въезд'}">selected</c:if>>въезд</option>
											</select>
		</td>
										
		<td>								<input  type='text' title="Название" placeholder="Название" placeholder2="Название" name="residents[${i.index}].street_name" id="ulica" value="${resident.street_name}" ></td>
		
		<td>									<input  style = "min-width:30px; width:30px;" type='text' title="№ Дома" placeholder="№ Дома" placeholder2="№ Дома" name="residents[${i.index}].house_num" id="nhouse" value="${resident.house_num}"></td>
		<td>									<input style = "min-width:30px; width:30px;" type='text' title="Этаж" placeholder="Этаж" placeholder2="Этаж" name="residents[${i.index}].level" id="floar" value="${resident.level}"></td>
		<td>									<input style = "min-width:30px; width:30px;" type='text' title="№ Квартиры" placeholder="№ Квартиры" placeholder2="№ Квартиры" name="residents[${i.index}].room_num" id="nroom" value="${resident.room_num}"></td>
		<td>									<input type='text' title="Номер телефона" placeholder="Номер телефона" placeholder2="Номер телефона" name="residents[${i.index}].phnnumbers[0].numb" value="${resident.phnnumbers[0].numb}"></td>				
												<input type='hidden'  name="residents[${i.index}].phnnumbers[0].id" value="${resident.phnnumbers[0].id}">
												<input type='hidden'  name="residents[${i.index}].phnnumbers[0].isMainResidentNumber" value="${resident.phnnumbers[0].isMainResidentNumber}">
												<input type='hidden'  name="residents[${i.index}].phnnumbers[0].phtype" value="${resident.phnnumbers[0].phtype}">
												<input type = "hidden" name = "residents[${i.index}].adresstype" value = "${resident.adresstype}">
												
		<td><a href = "${pageContext.request.contextPath}/admin/admincountry/${town.region.country.id}/adminTown/${town.id}/deleteAdress/${resident.id}">Удалить адрес</a></td>

		
		</tr>
		<br>
		<script>counter = ${i.index + 1} </script>
		</c:forEach>
												
		
		</c:if>
		</table>
		<a role = "button" onclick = "return appendState()">Добавить новый адрес представительства</a>
</div>	
-->	

<div id = "residents" style = " clear:both;">
		<h3>Представительства магазина:</h3>
		<c:set var = "rescounter" value="0"/>
		<c:if test = "${!empty town.residents}">
		<c:forEach items = "${town.residents}" var = "resident" varStatus = "i">
		<c:if test = "${resident.adresstype == 'RESIDENT'}">
		<div style = "float:left;" class = 'addedphotosegment' id = "propsegment${i.index}">
		<form:input type = "hidden" path = "residents[${i.index}].id"/>	
		
		
		<div class = "adress">
		<h4>Адресс представительства магазина (№ ${rescounter + 1}):</h4>
		<select class="fsel" name="residents[${i.index}].street_type" id="adress_f">
												
												<option value="улица" <c:if test = "${resident.street_type == 'улица'}">selected</c:if>>улица</option>
												
												<option  value="проспект" <c:if test = "${resident.street_type == 'проспект'}">selected</c:if>>проспект</option>
												
												<option  value="площадь" <c:if test = "${resident.street_type == 'площадь'}">selected</c:if>>площадь</option>
				
												<option value="бульвар" <c:if test = "${resident.street_type == 'бульвар'}">selected</c:if>>бульвар</option>
											
												<option value="переулок" <c:if test = "${resident.street_type == 'переулок'}">selected</c:if>>переулок</option>
												
												<option value="проезд" <c:if test = "${resident.street_type == 'проезд'}">selected</c:if>>проезд</option>
												
												<option value="въезд" <c:if test = "${resident.street_type == 'въезд'}">selected</c:if>>въезд</option>
		</select>
		<form:errors path="residents[${i.index}].street_type" cssClass="error" />
		<input  type='text' title="Название" placeholder="Название" placeholder2="Название" name="residents[${i.index}].street_name" id="ulica" value="${resident.street_name}" />
		<form:errors path="residents[${i.index}].street_name" cssClass="error" />
		<input  style = "min-width:30px; width:30px;" type='text' title="№ Дома" placeholder="№ Дома" placeholder2="№ Дома" name="residents[${i.index}].house_num" id="nhouse" value="${resident.house_num}"/>
		<form:errors path="residents[${i.index}].house_num" cssClass="error" />
		
		
		<input type = "hidden" name = "residents[${i.index}].adresstype" value = "${resident.adresstype}">
		<input style = "min-width:30px; width:30px;" type='text' title="Этаж" placeholder="Этаж" placeholder2="Этаж" name="residents[${i.index}].level" id="floar" value="${resident.level}">
		<input style = "min-width:30px; width:30px;" type='text' title="№ Квартиры" placeholder="№ Квартиры" placeholder2="№ Квартиры" name="residents[${i.index}].room_num" id="nroom" value="${resident.room_num}">
		</div>
		
		<div class = "dtpprices" id = "deltype_prices${i.index}">
			
			
		<h4>Способы доставки:</h4>
		
		
				<c:if test = "${!empty deltypes}">
		<c:forEach items = "${deltypes}" var = "deltype" varStatus = "k">
				<c:if test = "${deltype.type=='RESIDENT'}">
				<div id = "delprice_${i.index}_${k.index}" style = "float:left; clear:both; margin-left:40px;">
				<c:set var = "pos" value = "${myfn:getdelposition(resident.deltypes,deltype)}"/>
				
				<c:choose>
				
				<c:when test = "${pos>=0}">	
				
				<input type="checkbox" name="residents[${i.index}].deltypes[${k.index}].deltype.id" value="${deltype.id}" onchange = "showAddforAddr(${i.index},${k.index});" checked>${deltype.name}		
				<input type="hidden" name="residents[${i.index}].deltypes[${k.index}].deltype.name" value="${deltype.name}"/>
				<input type="hidden" name="residents[${i.index}].deltype.shortname" value="${deltype.name}"/>
				<div style = "padding-left:20px;" id = "add_${i.index}_${k.index}">
				<c:if test = "${resident.deltypes[pos].iscostless == false}"><label>Цена доставки: </label> От <input style = "min-width:30px; width:30px;" type="text" id = "price${k.index}" name="residents[${i.index}].deltypes[${k.index}].minprice" value="${resident.deltypes[pos].minprice}"/> до <input style = "min-width:30px; width:30px;" type="text" id = "maxprice${k.index}" name="residents[${i.index}].deltypes[${k.index}].maxprice" value="${resident.deltypes[0].maxprice}"/></c:if>
				
				<c:choose>
				<c:when test = "${resident.deltypes[pos].iscostless == true}">
				<input type="checkbox" name="residents[${i.index}].deltypes[${k.index}].iscostless" checked/>Бесплатная<br>	
				</c:when>
				<c:otherwise>
				<input type="checkbox" name="residents[${i.index}].deltypes[${k.index}].iscostless" onchange = "costless(${k.index});"/>Бесплатная<br>
				</c:otherwise>
				</c:choose>
				<label>Время доставки: </label> От <input type="text" style = "min-width:30px; width:30px;" id = "mindays${k.index}" name="residents[${i.index}].deltypes[${k.index}].mindays" value="${resident.deltypes[pos].mindays}"/> до <input style = "min-width:30px; width:30px;" type="text" id = "maxdays${k.index}" name="residents[${i.index}].deltypes[${k.index}].maxdays" value="${resident.deltypes[pos].maxdays}"/>
				</div>
				</c:when>
				<c:otherwise>
				<input type="checkbox" name="residents[${i.index}].deltypes[${k.index}].deltype.id" value="${deltype.id}"  onchange = "showAddforAddr(${i.index},${k.index});">${deltype.name}
				<input type="hidden" name="residents[${i.index}].deltypes[${k.index}].deltype.name" value="${deltype.name}"/>
				<input type="hidden" name="residents[${i.index}].deltypes[${k.index}].deltype.shortname" value="${deltype.shortname}"/>
				
				&nbsp&nbsp&nbsp&nbsp&nbsp<div style = "padding-left:20px;" class = "deladdr"  id = "add_${i.index}_${k.index}"></div>	
				
				</c:otherwise>
				</c:choose>
				
				</div>
				</c:if>
		</c:forEach>
		</c:if>
		
		</div>
		<script>cc[counter] = 0;</script>
			<c:if test = "${!empty resident.phnnumbers}">
		<div class = "phnnumbers">	
			<h4>Номера телефона для адреса:</h4>
		
		<c:forEach items = "${resident.phnnumbers}" var = "phonenumber" varStatus = "j">
		<div style = "margin-left:30px;" id = "numb${j.index}">
		<input type='hidden'  name="residents[${i.index}].phnnumbers[${j.index}].id" value="${resident.phnnumbers[j.index].id}">
		<input type='hidden'  name="residents[${i.index}].phnnumbers[${j.index}].phtype" value="${resident.phnnumbers[j.index].phtype}">
		<label id = 'numb${j.index}.name' name='residents[${i.index}].phnnumbers[${j.index}].numb'> ${j.index+1}) </label>
		<input type='text' title="Номер телефона" placeholder="Номер телефона" placeholder2="Номер телефона" name="residents[${i.index}].phnnumbers[${j.index}].numb" value="${resident.phnnumbers[j.index].numb}">
		<form:errors path="residents[${i.index}].phnnumbers[${j.index}].numb" cssClass="error" />
		<label>Основной номер:</label>
		<c:choose>
				<c:when test = "${phonenumber.isMainResidentNumber == true}">
				<input type="checkbox" name="residents[${i.index}].phnnumbers[${j.index}].isMainResidentNumber" checked/>
				</c:when>
				<c:otherwise>
				<input type="checkbox" name="residents[${i.index}].phnnumbers[${j.index}].isMainResidentNumber"/>
				</c:otherwise>
				</c:choose>
				
		<span style = 'margin-left:100px;'><a href = 'javascript:removePhone(${i.index}, ${j.index}, false)'>Удалить номер</a></span>
		</div>
		<script>cc[counter] = ${j.index + 1} </script>
		</c:forEach>
		</div>
		</c:if>
		
		
		<script>counter = ${i.index + 1}; counterres = ${rescounter+1}; </script>
		<c:set var="rescounter" value="${rescounter + 1}" />
		<a href = 'javascript:appendPhone(${i.index})'>Добавить номер телефона к адресу</a>
		
		
		<a href = 'javascript:removePhone(0, ${i.index}, true)'>Удалить адрес</a>
		
		</div>
		</c:if>
		</c:forEach>
		
		</c:if>
		<div id = "parentId" class = "newadress"></div>
		</div>
		<a href = 'javascript:appendState()'>Добавить представительство</a>
		







<div id = "delservices" style = " clear:both;">
		<h3>Адреса пунктов служб доставки:</h3>
		<c:set var = "delcounter" value="0"/>
		<c:if test = "${!empty town.residents}">
		<c:forEach items = "${town.residents}" var = "resident" varStatus = "i">
		<c:if test = "${resident.adresstype == 'DELIVERYSERVICE'}">
		<div style = "float:left;" class = 'addedphotosegment' id = "propsegment${i.index}">
		<form:input type = "hidden" path = "residents[${i.index}].id"/>	
		
		
		<div class = "adress">
		<h4>Адресс пункта службы дотсавки (№ ${delcounter + 1}):</h4>
		<select class="fsel" name="residents[${i.index}].street_type" id="adress_f">
												
												<option value="улица" <c:if test = "${resident.street_type == 'улица'}">selected</c:if>>улица</option>
												
												<option  value="проспект" <c:if test = "${resident.street_type == 'проспект'}">selected</c:if>>проспект</option>
												
												<option  value="площадь" <c:if test = "${resident.street_type == 'площадь'}">selected</c:if>>площадь</option>
				
												<option value="бульвар" <c:if test = "${resident.street_type == 'бульвар'}">selected</c:if>>бульвар</option>
											
												<option value="переулок" <c:if test = "${resident.street_type == 'переулок'}">selected</c:if>>переулок</option>
												
												<option value="проезд" <c:if test = "${resident.street_type == 'проезд'}">selected</c:if>>проезд</option>
												
												<option value="въезд" <c:if test = "${resident.street_type == 'въезд'}">selected</c:if>>въезд</option>
		</select>
		<form:errors path="residents[${i.index}].street_type" cssClass="error" />
		<input  type='text' title="Название" placeholder="Название" placeholder2="Название" name="residents[${i.index}].street_name" id="ulica" value="${resident.street_name}" />
		<form:errors path="residents[${i.index}].street_name" cssClass="error" />
		<input  style = "min-width:30px; width:30px;" type='text' title="№ Дома" placeholder="№ Дома" placeholder2="№ Дома" name="residents[${i.index}].house_num" id="nhouse" value="${resident.house_num}"/>
		<form:errors path="residents[${i.index}].house_num" cssClass="error" />
		
		
		<input type = "hidden" name = "residents[${i.index}].adresstype" value = "${resident.adresstype}">
		<input style = "min-width:30px; width:30px;" type='text' title="Этаж" placeholder="Этаж" placeholder2="Этаж" name="residents[${i.index}].level" id="floar" value="${resident.level}">
		<input style = "min-width:30px; width:30px;" type='text' title="№ Квартиры" placeholder="№ Квартиры" placeholder2="№ Квартиры" name="residents[${i.index}].room_num" id="nroom" value="${resident.room_num}">
		</div>
		
		<div class = "dtpprices" id = "deltype_prices${i.index}">
			
			
		<h4>Способы доставки:</h4>
		
		
				<c:if test = "${!empty deltypes}">
				
		<c:forEach items = "${deltypes}" var = "deltype" varStatus = "k">
		<c:if test = "${deltype.type=='DELIVERYSERVICE'}">
				<div id = "delprice_${i.index}_${k.index}" style = "float:left; clear:both; margin-left:40px;">
				<c:set var = "pos" value = "${myfn:getdelposition(resident.deltypes,deltype)}"/>
				
				<c:choose>
				
				<c:when test = "${pos>=0}">	
				
				<input type="checkbox" name="residents[${i.index}].deltypes[${k.index}].deltype.id" value="${deltype.id}" onchange = "showAddforAddr(${i.index},${k.index});" checked>${deltype.name}		
				<input type="hidden" name="residents[${i.index}].deltypes[${k.index}].deltype.name" value="${deltype.name}"/>
				<input type="hidden" name="residents[${i.index}].deltype.shortname" value="${deltype.name}"/>
				<div style = "padding-left:20px;" id = "add_${i.index}_${k.index}">
				<c:if test = "${resident.deltypes[pos].iscostless == false}"><label>Цена доставки: </label> От <input style = "min-width:30px; width:30px;" type="text" id = "price${k.index}" name="residents[${i.index}].deltypes[${k.index}].minprice" value="${resident.deltypes[pos].minprice}"/> до <input style = "min-width:30px; width:30px;" type="text" id = "maxprice${k.index}" name="residents[${i.index}].deltypes[${k.index}].maxprice" value="${resident.deltypes[0].maxprice}"/></c:if>
				
				<c:choose>
				<c:when test = "${resident.deltypes[pos].iscostless == true}">
				<input type="checkbox" name="residents[${i.index}].deltypes[${k.index}].iscostless" checked/>Бесплатная<br>	
				</c:when>
				<c:otherwise>
				<input type="checkbox" name="residents[${i.index}].deltypes[${k.index}].iscostless" onchange = "costless(${k.index});"/>Бесплатная<br>
				</c:otherwise>
				</c:choose>
				<label>Время доставки: </label> От <input type="text" style = "min-width:30px; width:30px;" id = "mindays${k.index}" name="residents[${i.index}].deltypes[${k.index}].mindays" value="${resident.deltypes[pos].mindays}"/> до <input style = "min-width:30px; width:30px;" type="text" id = "maxdays${k.index}" name="residents[${i.index}].deltypes[${k.index}].maxdays" value="${resident.deltypes[pos].maxdays}"/>
				</div>
				</c:when>
				<c:otherwise>
				<input type="checkbox" name="residents[${i.index}].deltypes[${k.index}].deltype.id" value="${deltype.id}"  onchange = "showAddforAddr(${i.index},${k.index});">${deltype.name}
				<input type="hidden" name="residents[${i.index}].deltypes[${k.index}].deltype.name" value="${deltype.name}"/>
				<input type="hidden" name="residents[${i.index}].deltypes[${k.index}].deltype.shortname" value="${deltype.shortname}"/>
				
				&nbsp&nbsp&nbsp&nbsp&nbsp<div style = "padding-left:20px;" class = "deladdr"  id = "add_${i.index}_${k.index}"></div>	
				
				</c:otherwise>
				</c:choose>
				
				</div>
			</c:if>
		</c:forEach>
		</c:if>
		
		</div>
		<script>cc[counter] = 0;</script>
			<c:if test = "${!empty resident.phnnumbers}">
		<div class = "phnnumbers">	
			<h4>Номера телефона для адреса:</h4>
		
		<c:forEach items = "${resident.phnnumbers}" var = "phonenumber" varStatus = "j">
		<div style = "margin-left:30px;" id = "numb${j.index}">
		<input type='hidden'  name="residents[${i.index}].phnnumbers[${j.index}].id" value="${resident.phnnumbers[j.index].id}">
		<input type='hidden'  name="residents[${i.index}].phnnumbers[${j.index}].phtype" value="${resident.phnnumbers[j.index].phtype}">
		<label id = 'numb${j.index}.name' name='residents[${i.index}].phnnumbers[${j.index}].numb'> ${j.index+1}) </label>
		<input type='text' title="Номер телефона" placeholder="Номер телефона" placeholder2="Номер телефона" name="residents[${i.index}].phnnumbers[${j.index}].numb" value="${resident.phnnumbers[j.index].numb}">
		<form:errors path="residents[${i.index}].phnnumbers[${j.index}].numb" cssClass="error" />
		<label>Основной номер:</label>
		<c:choose>
				<c:when test = "${phonenumber.isMainResidentNumber == true}">
				<input type="checkbox" name="residents[${i.index}].phnnumbers[${j.index}].isMainResidentNumber" checked/>
				</c:when>
				<c:otherwise>
				<input type="checkbox" name="residents[${i.index}].phnnumbers[${j.index}].isMainResidentNumber"/>
				</c:otherwise>
				</c:choose>
				
		<span style = 'margin-left:100px;'><a href = 'javascript:removePhone(${i.index}, ${j.index}, false)'>Удалить номер</a></span>
		</div>
		<script>cc[counter] = ${j.index + 1} </script>
		</c:forEach>
		</div>
		</c:if>
		
		
		<script>counter = ${i.index + 1}; counterdel = ${delcounter + 1}; </script>
		<c:set var="delcounter" value="${delcounter + 1}" />
		<a href = 'javascript:appendPhone(${i.index})'>Добавить номер телефона к адресу</a>
		
		
		<a href = 'javascript:removePhone(0, ${i.index}, true)'>Удалить адрес</a>
		
		</div>
		</c:if>
		</c:forEach>
		
		</c:if>
		<div id = "parentDelId" class = "newadress"></div>
		</div>
		<a href = 'javascript:appendDelservAdress()'>Добавить адрес службу доставки</a>



<!-- 
	

<div id ="spots">		
		
		<h3>Адреса служб доставки:</h3>
		<div id = "parentDelId" class = "newdeliveryadress"></div>
		<table>
		<c:if test = "${!empty town.residents}">
		<th><td>Тип улицы</td><td>Название улицы</td><td>Дом</td><td>Этаж</td><td>Квартира</td>
		</th>
		<c:forEach items = "${town.shopdelresidents}" var = "resident" varStatus = "i">
		<tr><td></td>
		<td>

						
											<form:input type = "hidden" path = "residents[${i.index}].id"/>	
											<select class="fsel" name="residents[${i.index}].street_type" id="adress_f">
												
												<option value="улица" <c:if test = "${resident.street_type == 'улица'}">selected</c:if>>улица</option>
												
												<option  value="проспект" <c:if test = "${resident.street_type == 'проспект'}">selected</c:if>>проспект</option>
												
												<option  value="площадь" <c:if test = "${resident.street_type == 'площадь'}">selected</c:if>>площадь</option>
				
												<option value="бульвар" <c:if test = "${resident.street_type == 'бульвар'}">selected</c:if>>бульвар</option>
											
												<option value="переулок" <c:if test = "${resident.street_type == 'переулок'}">selected</c:if>>переулок</option>
												
												<option value="проезд" <c:if test = "${resident.street_type == 'проезд'}">selected</c:if>>проезд</option>
												
												<option value="въезд" <c:if test = "${resident.street_type == 'въезд'}">selected</c:if>>въезд</option>
											</select>
		</td>
										
		<td>								<input  type='text' title="Название" placeholder="Название" placeholder2="Название" name="residents[${i.index}].street_name" id="ulica" value="${resident.street_name}" ></td>
		
		<td>									<input  style = "width:40px;" type='text' title="№ Дома" placeholder="№ Дома" placeholder2="№ Дома" name="residents[${i.index}].house_num" id="nhouse" value="${resident.house_num}"></td>
		<td>									<input style = "width:40px;" type='text' title="Этаж" placeholder="Этаж" placeholder2="Этаж" name="residents[${i.index}].level" id="floar" value="${resident.level}"></td>
		<td>									<input style = "width:40px;" type='text' title="№ Квартиры" placeholder="№ Квартиры" placeholder2="№ Квартиры" name="residents[${i.index}].room_num" id="nroom" value="${resident.room_num}"></td>
		<td>									<input type='text' title="Номер телефона" placeholder="Номер телефона" placeholder2="Номер телефона" name="residents[${i.index}].phnnumbers[0].numb" value="${resident.phnnumbers[0].numb}"></td>				
												<input type='hidden'  name="residents[${i.index}].phnnumbers[0].id" value="${resident.phnnumbers[0].id}">
												<input type='hidden'  name="residents[${i.index}].phnnumbers[0].isMainResidentNumber" value="${resident.phnnumbers[0].isMainResidentNumber}">
												<input type='hidden'  name="residents[${i.index}].phnnumbers[0].phtype" value="${resident.phnnumbers[0].phtype}">
												<input type = "hidden" name = "residents[${i.index}].adresstype" value = "${resident.adresstype}">
												
		<td><a href = "${pageContext.request.contextPath}/admin/admincountry/${town.region.country.id}/adminTown/${town.id}/deleteAdress/${resident.id}">Удалить адрес</a></td>

		
		</tr>
		<br>
		<script>counter = ${i.index + 1} </script>
		</c:forEach>
												
		
		</c:if>
		</table>
		<a role = "button" onclick = "return appendDelService()">Добавить новый адрес службы доставки</a>
</div>	
-->			
		
	<div id ="spots">	
		

			<input type="submit"
				value="Сохранить" />
	</div>
</form:form>

</div>

</body>
</html>