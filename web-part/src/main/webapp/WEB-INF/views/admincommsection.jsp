<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="myfn" uri="http://samplefn"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="<c:url value="/resources/js/popups.js" />"></script>
<script src="<c:url value="/resources/js/mascedinput.js" />"></script>

	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.min.js" type="text/javascript"></script>
<c:if test="${section != 'propvalues'}">
<script charset="UTF-8"  src="<c:url value="/resources/js/adminscripts.js" />"></script>
</c:if>
<link rel="stylesheet" href="<c:url value="/resources/datepicker.css"/>"/>
<script charset="UTF-8"  src="<c:url value="/resources/js/myscripts.js" />"></script>
<script src="<c:url value="/resources/js/mascedinput.js" />"></script>
<script src="<c:url value="/resources/js/datepicker.js" />"></script>

</head>
<body>

<c:if test="${section != 'goodchoise' && section != 'categorychoise' && section != 'entitychoise'}">
<script>

var url = "<c:out value="${pageContext.request.contextPath}"/>";
var classname = "<c:out value="${classname}"/>";
var adminclassname = "<c:out value="${adminclassname}"/>";
var propclassname = "<c:out value="${node.propclassname}"/>";
$(document).ready(function(){
	
	
	var url = "<c:out value="${pageContext.request.contextPath}"/>";


	if(propclassname == 'Date'){
$('#min').mask("9999-99-99 99:99");
$('#max').mask("9999-99-99 99:99");	

	$('#min').attachDatepicker({
		yearRange: '2014:2015',
		firstDay: 1,
		minDate:  new Date(2012, 1 - 1, 1),
		defaultDate: 0,
		dateFormat:'yy-mm-dd',
		closeText:"<span id='dialog_close' href='#'></span>"
	});
	
	$('#max').attachDatepicker({
		yearRange: '2014:2015',
		firstDay: 1,
		minDate:  new Date(2012, 1 - 1, 1),
		defaultDate: 0,
		dateFormat:'yy-mm-dd',
		closeText:"<span id='dialog_close' href='#'></span>"
	});
	
	}

$("#l1").on("click", function(){ 

var result = {};
function isNumber(n) {
return !isNaN(parseInt(n)) && isFinite(n);
}




jQuery.fn.serializeObject=function() {
var json = {};
var all = [];
var val={};
var vals = [];
var delim;
var map = {};
var j = 0;
var k =0;
	 
jQuery.map(jQuery(this).serializeArray(), function(n, i) {

var _ ;

if(n.name.indexOf('[')!= -1){_ = n.name.indexOf('[');_name = n.name.replace(/\]/gi, '').split('[');delim = "[";} else{_ = n.name.indexOf('{'); _name = n.name.replace(/\}/gi, '').split('{');delim = "{";}

if (_ > -1) {
var o = json;

if(!result[_name[0]]){result[_name[0]] = vals;}
 for (var i=0, len=_name.length; i<len; i++) {
	 if (i == len-1) {
//			 alert("["+(i-1)+"]"+ _name[i-1]+"   "+n.value);

		 if (_name[i-1] == 'vals' ) {
			 o['vals']=vals;
		  if (_name[i] == 'id' ||  _name[i] == 'value') {
	
			 
		 	 if (val['id'] != null && val['value']!=null){
		 		 vals[k] = val;
		 		 k++;
	
		 		o[_name[i-1]] = vals;
   				
		 	 	 val={};
		 	 	
		 	 }
				val[_name[i]] = (isNumber(n.value)) ?  parseInt(n.value) : n.value || '';
	//			alert("В массиве: "+val[_name[i]]);

		 }

		 }
		 else{
		     
		          if (o[_name[i-1]] != null) { vals[k] = val;  all[j] = o; j++; json = {};  vals = []; val={}; k=0; json[_name[i-1]] = n.value || '';}
		          else{ o[_name[i-1]] = (isNumber(n.value)) ?  parseInt(n.value) : o[_name[i-1]] = n.value || ''; 
		          }
		    				 
		 }
      }
 	 }
 vals[k] = val;
}
else{
result[n.name] = (isNumber(n.value)) ?  parseInt(n.value) : n.value;
}

});

return result;
};



var filters = $('#filter').serializeObject();
//var filters = $('#pricefilter').serializeObject();
//filters = $('#pages').serializeObject();
var hasvaluefilter = !$("#toogle").prop('checked');
var hasmin = $("#min").val()!='';
var hasmax = $("#max").val()!='';
var hasrangefilter = hasmax || hasmin;
filters["hasvaluefilter"]=hasvaluefilter;
filters["hasrangefilter"]=hasrangefilter;
//alert(hasvaluefilter);
var caturl = "<c:out value="${pageContext.request.contextPath}"/>" +"/category/"+ "<c:out value="${category.id}"/>"+"/pagin/1";
//	alert(JSON.stringify(postData));
//	alert(JSON.stringify(filters));
//	alert(url+"/dofilter");
	$.ajax({
		type: "POST",
		url: url+"/admin/dofilter",
		cache: false,  
	    headers : {
	        'Accept' : 'application/json',
	        'Content-Type' : 'application/json'
	    },
	    data: JSON.stringify(filters),
	    success : function(response) {  
//	    	alert(response);
	    
	    	var g = $("#pagenum").attr("value");
	    //	 $('table').dragtable();
	        $("#list").html(response);
	       
	        
	    },      
	    error : function(xhr, status, error){
            alert("Error!" + xhr.status);
        }
	});

});



$(document).mouseup(function (e)
		{
	var container = $("#filters");
		    var excl = [];
		     excl[0] = $("#dialog_wrapper");
		     excl[1] = $(".catlist");
		     excl[2] = $(".datepicker_control");
		     excl[3] = $(".datepicker_close");
		     excl[4] = $(".datepicker_links");
		     excl[5] = $(".datepicker_header");
		     excl[6] = $(".datepicker");
		     excl[7] = $(".datepicker_prev");
		     excl[8] = $(".datepicker_next");
		     excl[9] = $(".datepicker_current");
		     excl[10] = $(".datepicker_control");
		     excl[11] = $(".datepicker_links");
		    var i =0;
		    var toclose = true;
		    for(i=0;i<excl.length;i++){
		    	if(excl[i].has(e.target).length == 1){
		    		toclose = false;
		    		break;
		    	}
		    }
		    
	//			    alert(toclose);
				    if(toclose == true){
				    if (!container.is(e.target)// if the target of the click isn't the container...
				        && container.has(e.target).length === 0) // ... nor a descendant of the container
				    {	//alert(e.currentTarget.id);
				    	
				    	
				 	  	container.detach();
				 //       container.hide();
				    }
				    }
		    
		    $("#propvals").children('div').each(function(index) {
				
				if($( this ).find("#propvalue").attr('type')!='hidden'){
					$( this ).find("#propvalue").val('Введите значение');
				}
				});
		/*    $("#propvalue").each(function(index) {
				$(this).val('Введите значение');

			});*/
		});




});
checkedall = true;


$(".propcheck").on("change", function(){
	var checkboxes = $("#filter").find(':checkbox').not("#sort, #toogle");
//	alert($(this).prop('checked'));
    if($("#toogle").prop('checked')) {
    	$("#toogle").prop('checked', false);$("#toogle").html("Выделить все");
    } 
    else if($(this).prop('checked')){

    if(checkboxes.size()==$("#filter").find( "input:checked" ).not("#sort").length){$("#toogle").prop('checked', true);}
    }
	

	
});



$("#toogle").on("change", function(){
	var checkboxes = $("#filter").find(':checkbox').not("#sort");
    if($(this).prop('checked')) {
      checkboxes.prop('checked', true);$("#toogle").html("Убрать все выделения");
    } else {
      checkboxes.prop('checked', false);$("#toogle").html("Выделить все");
    }
	

	
});
</script>
</c:if>
<c:if test="${section == 'propvalues'}">

<c:if test = "${node.isobject == true && node.propclassname!='Date' && node.propclassname!='String'}">
<c:set var = "isobject" value = "true"/>
</c:if>
<form class="contact_form" id = "filter" method="post">

<input type = "hidden" value = "${node.propname}" name = "propname" id = "propname"/>
<input type = "hidden" value = "${node.classname}" name = "classname" id = "classname"/>
<input type = "hidden" value = "${node.adminclassname}" name = "adminclassname" id = "adminclassname"/>
<input type = "hidden" value = "${node.coladmintype}" name = "coladmintype" id = "coladmintype"/>
<input type = "hidden" value = "${node.pgnum}" name = "pgnum" id = "pgnum"/>
<input id = "currurl" name = "currurl" type = "hidden" value = "${currurl}"/>

<input type = "hidden" value = "${node.colname}" name = "colname" id = "colname"/>
<br>
<c:choose>
<c:when test = "${node.issortcriteria == true}">
<input type = "checkbox" value = "true" name = "issortcriteria" id = "sort" checked/> 
</c:when>
<c:otherwise>
<input type = "checkbox" value = "true" name = "issortcriteria" id = "sort"/> 
</c:otherwise>
</c:choose>
Сортировать по столбцу<br><br>
<a value = "${node.propname}" id = "delref" role="button" onclick = "deletefilter('${node.propname}')" checked>Снять фильтр со свойства</a>
<h4>Дипазон значений</h4>
<label>От</label>

<input <c:if test = "${isobject==true}"> onclick = 'load_goodchoise(this, "${pageContext.request.contextPath}/choiseCategory", "filter", "choise${node.propclassname}")'</c:if>  type = "text" name = "min" id = "min"/><br>
<label>До</label>

<input <c:if test = "${isobject==true}"> onclick = 'load_goodchoise(this, "${pageContext.request.contextPath}/choiseCategory", "filter", "choise${node.propclassname}")'</c:if>  type = "text" name = "max" id = "max"/>
<h4>Выбор конкретных значений</h4>
<input type = "checkbox" id = "toogle" checked/> Выделить/убрать все
<ul>
<c:forEach items="${node.vals}" var="val" varStatus = "j">

<c:if test = "${j.index == 0}"><div id = "visibleprops"></c:if>

<li>
							<input type ="hidden" name = "vals[id]" id="param${j.index}" path="params[${i.index}].vals[${j.index}].id" value="${val.id}"/>
							<c:choose>
							
							<c:when test = "${val.ischecked == true}">
							
							<input class = "propcheck" type ="checkbox" name = "vals[value]" id="param${j.index}" onclick="sh_flt('param${j.index}')" path="params[${i.index}].vals[${j.index}].value" value="${val.value}" checked/>
							</c:when>
							<c:otherwise>
							<input class = "propcheck" type ="checkbox" name = "vals[value]" id="param${j.index}" onclick="sh_flt('param${j.index}')" path="params[${i.index}].vals[${j.index}].value" value="${val.value}" />
							</c:otherwise>
							</c:choose>
							${val.value}
</li>

<c:if test = "${j.index == visiblecount}"></div><a id = "showall" role = "button" onclick = "$('#unvisibleprops').show(); $(this).hide();">Показать все значения</a><div id =  "unvisibleprops"></c:if>

<c:if test="${(fn:length(node.vals)-1) == j.index}">
   <a style = "margin: 10px 5px;" role = "button" onclick = "$('#unvisibleprops').hide(); $('#showall').show();">Скрыть все значения</a>
   </div>
   
</c:if>

</c:forEach>






</ul>





</form>
<input id = "l1" value = "Фильтровать" type = "button"/>
</c:if>

<c:if test="${section == 'filterres'}">
<jsp:include page="/WEB-INF/views/admininstrumentbar.jsp" />


<c:if test = "${table == 'singleclasstable' || empty table}">

	<table id = "table" class="data">
<tr>
			<th id = "selector"></th>
			<c:forEach items="${containers[0].fields}" var="field">
			<th id = "${field.key.propname}">${field.key.colname}</th>
			</c:forEach>
			<th id = "functions">Функции</th>
			
</tr>

<c:forEach items="${containers}" var="container">
			<tr>
				<td id = "selector" style = "background: #ededed;"></td>
				<c:forEach items="${container.fields}" var="field">
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
</c:if>

<c:if test = "${table == 'multiclasstable'}">
<p>MULTTTTTTTTTTIIIIIIIIIII</p>
<table id = "table" class="data">
<tr>
			<th id = "selector"></th>
			<c:forEach items="${multicontainers[0].fieldcontainers}" var="containers">
			<c:forEach items="${containers.value.fields}" var="field">
			
			<th admintype = "${containers.key}" type = "${field.key.propclassname}"<c:if test = "${field.key.isobject == true}">isobject = "true"</c:if> id = "${field.key.propname}">${field.key.colname}</th>
			</c:forEach>
			</c:forEach>
			<th id = "functions">Функции</th>
			
</tr>

<c:forEach items="${multicontainers}" var="multicontainer" varStatus = "i">
			<tr>
				<td id = "selector${i.index}" style = "background: #ededed;"></td>
				<c:forEach items="${multicontainer.fieldcontainers}" var="containers">
				<c:forEach items="${containers.value.fields}" var="field">
				<td>${field.value}</td>
				
				</c:forEach>
				</c:forEach>
				<td>
				<c:forEach items="${multicontainer.refs}" var="entry">
				<a href="${pageContext.request.contextPath}${entry.value}">${entry.key}</a><br>
				</c:forEach>
				</td>
				
			
			</tr>
</c:forEach>
</table>
</c:if>

<div class="ccpnav">
		<form id = "pages">
		<span class="arrow">&#8592;</span><a class="a-text a-prev" href="${entityurl}${pgnum-1}">Пред.</a>
		<c:forEach begin="1" end="${pagecount+1}" var="i">
		<c:choose>
		<c:when test = "${i==pgnum}">
		<span class="">${i}</span><input type = "hidden" value = "${i}" name = "pagenum" id = "pagenum"/>
		</c:when>
		<c:otherwise>
		<a class="a-text" href="${entityurl}${i}">${i}</a>
		</c:otherwise>
		</c:choose>
		</c:forEach>
		<a class="a-text a-next" href="${entityurl}${pgnum+1}">След.</a><span class="arrow">&#8594;</span>
		</form>
</div>
<div id = "popup" class = "popupwindow">
<div class="close"><img width="30" height="30" src="/web-shop/resources/images/x-button-gray.png"></div>
<div id = "popupcontent"></div>
</div>
	<div id="popupbg"></div>
	<div id="dialog_wrapper">
		<div id="dialog_outer">
			<div id="dialog_inner">
				<a id="dialog_close" role="button"></a>
				<div id="dialog_title"></div>
				<div id="dialog_content"></div>
			</div>
		</div>
	</div>
</c:if>

<c:if test="${section == 'formmultichange'}">
<script src="<c:url value="/resources/js/popups.js" />"></script>
	<div class = "close"><img width="30" height="30"  src = "<c:url value ="/resources/images/x-button-gray.png"/>"/></div>
	<h4 style = "text-align:center;" class = "white">Доступные для массового редактирования поля</h4>
	<div id = "propvals">
	<form class="contact_form">
	<input  id = "objid" type = "hidden" value = "${id}"></input>
	<ul id = "rr">
	<c:forEach items="${propvals}" var="pval" varStatus = "i">
	<li><div class = "changerow" id = "propval${i.index}"><label>${pval.colname}</label><input id = "entityid" type = "hidden"/><span id = "propvalspan"  style = "display:none;"><c:choose><c:when test = "${pval.classname == 'Boolean'}"><select id = "propvalue${i.index}" type = "${pval.classname}" style = "margin-right:40px;"  class="propvalue search rounded"><option>true</option> <option>false</option></select></c:when><c:otherwise><input type = "${pval.classname}"  style = "margin-right:40px;"  class="propvalue search rounded" placeholder="Введите значение" onclick = "load_entitychoise('${pageContext.request.contextPath}','propvalue${i.index}','${pval.type.name}', ${i.index})"  id = "propvalue${i.index}" type = "text"></input></c:otherwise></c:choose></span><a id = "propvalhref" onclick = "showinput(${i.index},'${pval.propname}')" role = "button" >Ввести значение</a><input  id = "propname" type = "hidden" value = "${pval.propname}"></input></div></li>
	</c:forEach>
	<li class = "buttonli"><input type = "button" value = "Сохранить значения" id = "multisetsubmit" class= "multisetsubmit"/></li>
	</ul>
	</form>
	</div>
	<div id = "popup1" class = "popupwindow"></div>
</c:if>

<c:if test="${section == 'otherentitychoise'}">
	<div class = "close"><img width="30" height="30"  src = "<c:url value ="/resources/images/x-button-gray.png"/>"/></div>
	<h3 style = "text-align:center;" class = "white">Выберите значение:</h3>
	<c:forEach items="${entities}" var="entity" varStatus = "k">
		<p>${entity}     <a role="button" onclick = "return chsEntity(${entity.id}, '${idfld}','${pageContext.request.contextPath}', '${section}', '${classname}','${entity}')">Выбрать</a></p><br>
	</c:forEach>
</c:if>

<c:if test="${section == 'choisecategory'}">
<script>
function openLevel(catid,level, isfinal){
	
	if($("#level"+level+catid).css("display")=="none"){
		$("#level"+level+catid).css("display","block");
	}
	else{
		$("#level"+level+catid).css("display","none");
	}
	
	
}



</script>

<div class = "close"><img width="20" height="20"  src = "<c:url value ="/resources/images/x-button-gray.png"/>"/></div>
<h4 style = "margin-top: 5px; text-align:center;" class = "white">Выберите значение:</h4>
<div class="catlist">
<div id = "root">
<c:set var = "level" value = "1"/>
<c:forEach items="${catList}" var="category" varStatus = "k">
<a role = "button" onclick = "return openLevel(${category.id},${level})">${category.name}</a>&nbsp<a role = "button" onclick = "return setVal(${category.id}, '${idfld}','${pageContext.request.contextPath}', '${section}')">Выбрать</a><br>

<div class = "sublevels" id = "level${level}${category.id}" style = "padding-left:5%; display:none;">
<c:forEach items="${category.subcategs}" var="subcat1" varStatus = "i">
<c:set var="level" value="${level+1}"/>
<a role = "button" onclick = "return openLevel(${subcat1.id},${level}, ${subcat1.isFinalCategory})">${subcat1.name}</a>&nbsp<a role = "button" onclick = "return setVal(${subcat1.id}, '${idfld}','${pageContext.request.contextPath}', '${section}')">Выбрать</a><br>

<div class = "sublevels" id = "level${level}${subcat1.id}" style = "padding-left:5%; display:none;">
<c:forEach items="${subcat1.subcategs}" var="subcat2" varStatus = "j">
<c:set var="level" value="${level+1}"/>
<a role = "button" onclick = "return openLevel(${subcat2.id},${level}, ${subcat2.isFinalCategory})">${subcat2.name}</a>&nbsp<a role = "button" onclick = "return setVal(${subcat2.id}, '${idfld}','${pageContext.request.contextPath}', '${section}')">Выбрать</a><br>

<div class = "sublevels" id = "level${level}${subcat2.id}" style = "padding-left:5%; display:none;">
<c:forEach items="${subcat2.subcategs}" var="subcat3" varStatus = "l">
<c:set var="level" value="${level+1}"/>
<a role = "button" onclick = "return openLevel(${subcat3.id},${level}, ${subcat3.isFinalCategory})">${subcat3.name}</a>&nbsp<a role = "button" onclick = "return setVal(${subcat3.id}, '${idfld}','${pageContext.request.contextPath}', '${section}')">Выбрать</a><br>
<div class = "sublevels" id = "level${level}${subcat3.id}" style = "padding-left:5%; display:none;">
<c:forEach items="${subcat3.subcategs}" var="subcat4" varStatus = "i">
<a role = "button" onclick = "return setVal(${subcat4.id}, '${idfld}','${pageContext.request.contextPath}', '${section}')">${subcat4.name}</a><br>
</c:forEach>
</div>
</c:forEach>
</div>

</c:forEach>
</div>

</c:forEach>
</div>
</c:forEach>
</div>
</div>

</c:if>

<c:if test="${section == 'goodchoise'}">
<script>
function openLevel(catid,level, isfinal){
	
	if($("#level"+level+catid).css("display")=="none"){
		$("#level"+level+catid).css("display","block");
	}
	else{
		$("#level"+level+catid).css("display","none");
	}
	
	
}
</script>
<div class = "close"><img width="20" height="20"  src = "<c:url value ="/resources/images/x-button-gray.png"/>"/></div>
<h4 style = "margin-top: 5px; text-align:center;" class = "white">Выберите значение:</h4>
<div class="catlist">
<div id = "root">
<c:set var = "level" value = "1"/>
<c:forEach items="${catList}" var="category" varStatus = "k">
<a role = "button" onclick = "return openLevel(${category.id},${level})">${category.name}</a><br>

<div class = "sublevels" id = "level${level}${category.id}" style = "padding-left:5%; display:none;">
<c:forEach items="${category.subcategs}" var="subcat1" varStatus = "i">
<c:set var="level" value="${level+1}"/>
<a role = "button" onclick = "return openLevel(${subcat1.id},${level}, ${subcat1.isFinalCategory})">${subcat1.name}</a><br>
<c:choose>
<c:when test = "${subcat1.isFinalCategory=='true'}">
<div class = "goods" id = "level${level}${subcat1.id}" style = "padding-left:5%; display:none;">
<c:forEach items="${subcat1.goods}" var="good" varStatus = "i">
<a role = "button" onclick = "return chsEntity(${good.id},'${idfld}','${pageContext.request.contextPath}', '${section}', 'GoodItem', '${good.name}')">${good.name}</a><br>
</c:forEach>
</div>
</c:when>
<c:otherwise>
<div class = "sublevels" id = "level${level}${subcat1.id}" style = "padding-left:5%; display:none;">
<c:forEach items="${subcat1.subcategs}" var="subcat2" varStatus = "j">
<c:set var="level" value="${level+1}"/>
<a role = "button" onclick = "return openLevel(${subcat2.id},${level}, ${subcat2.isFinalCategory})">${subcat2.name}</a><br>
<c:choose>
<c:when test = "${subcat2.isFinalCategory=='true'}">
<div class = "sublevels" id = "level${level}${subcat2.id}" style = "padding-left:5%; display:none;">
<c:forEach items="${subcat2.goods}" var="good" varStatus = "i">
<a role = "button" onclick = "return chsGood(${good.id}, '${idfld}','${pageContext.request.contextPath}', '${section}')">${good.name}</a><br>
</c:forEach>
</div>
</c:when>
<c:otherwise>
<div class = "sublevels" id = "level${level}${subcat2.id}" style = "padding-left:5%; display:none;">
<c:forEach items="${subcat2.subcategs}" var="subcat3" varStatus = "l">
<c:set var="level" value="${level+1}"/>
<a role = "button" onclick = "return openLevel(${subcat3.id},${level}, ${subcat3.isFinalCategory})">${subcat3.name}</a><br>
<div class = "sublevels" id = "level${level}${subcat3.id}" style = "padding-left:5%; display:none;">
<c:forEach items="${subcat3.goods}" var="good" varStatus = "i">
<a role = "button" onclick = "return chsGood(${good.id}, '${idfld}','${pageContext.request.contextPath}', '${section}')">${good.name}</a><br>
</c:forEach>
</div>
</c:forEach>
</div>
</c:otherwise>
</c:choose>
</c:forEach>
</div>
</c:otherwise>
</c:choose>
</c:forEach>
</div>
</c:forEach>
</div>
</div>
</c:if>



<c:if test="${section == 'regiontowns'}">
	<table>
				<th><td>Название</td><td>Код</td></th>
				<div id = "reg${i.index}"></div>
			
			<c:if test = "${!empty region.towns }">
			<c:forEach items = "${region.towns}" var = "town" varStatus = "j">
			<tr>
			<td><label id = 'town${j.index}.name'  name='regions[${regnum}].towns[${j.index}].name'> Город  ${j.index+1}: </label></td>
			<input type = "hidden" value = '${town.id}' id = 'town${j.index}.name' name='regions[${regnum}].towns[${j.index}].id'/>
			<td><label id = 'town${j.index}.name'  name='regions[${regnum}].towns[${j.index}].name'>${town.name}</label></td>
			<input type = "hidden" value = '${town.name}' name='tregions[${regnum}].towns[${j.index}].name'/>
			<td><label id = 'town${j.index}.phonecode'  name='regions[${regnum}].towns[${j.index}].phonecode'>${town.phonecode}</label></td>
			<input type = "hidden" value = '${town.phonecode}' id = 'town${j.index}.name' name='regions[${regnum}].towns[${j.index}].phonecode'/>
			<td><a href = "${pageContext.request.contextPath}/admin/admincountry/${town.region.country.id}/adminTown/${town.id}">Вести данных для города</a></td>
			<td><a href = "${pageContext.request.contextPath}/admin/admincountry/${town.region.country.id}/deleteTown/${town.id}">Удалить город</a></td>
			</tr>
			
			<script>cc[counter] = ${j.index + 1} </script>
			</c:forEach>
			</c:if>
				
			</table>

</c:if>


<c:if test="${section == 'managecols'}">
<script>
$(document).ready(function(){

	$("#listcols li").on("mousedown", function(){
		$(this).toggleClass("highlight");
	});
	
	$("#savestruct").on("click", function(){
		
		var i = 0;
		var col = [];
		if($("#listhidden ul").html()!=""){
		$("#listhidden li").each(function(){
			
			
			col[i] = $(this).attr("id");
			i++;		
			
		
		
		});
		}
//		alert(JSON.stringify(col));
//		alert(adminclassname);
		$.ajax({
			type: "POST",
			url: url+"/admin/savecolumnstructure",
			data: {"hiddencols":JSON.stringify(col), "adminclassname":adminclassname},
			dataType: "text",
			success: function(data){
			$("#list").html(data);
			$(".popupwindow").hide();
				
			},      
		    error : function(xhr, status, error){
	            alert("Error!" + xhr.status);
	        }
		});
	});
	
	
});

function moveToHidden(){
	$("#listcols li ").each(function(){
		if($(this).hasClass("highlight")){
	//		alert($(this).attr("id"));
			$("#listhidden").find("#listcols").append(this);
			$(this).removeClass("highlight");
		}
	
	
	});
	}
	
function moveToVisible(){
	$("#listcols li ").each(function(){
		if($(this).hasClass("highlight")){
	//		alert($(this).attr("id"));
			$("#listvisible").find("#listcols").append(this);
			$(this).removeClass("highlight");
		}
	
	
	});
	}	


</script>
<div class = "close"><img width="30" height="30"  src = "<c:url value ="/resources/images/x-button-gray.png"/>"/></div>
<ul>

	<li>
		<div id = "listcolrow">
			<div id = "listvisible">
				<label>
					Отображаемые столбцы
				</label>
				<ul id = "listcols">
				<c:forEach items = "${activecols}" var = "activecol" varStatus = "i">
				<li id = "${activecol.key}">${activecol.value}</li>
				</c:forEach>
			</ul>
			</div>
			<div id = "colsmenubar">
			<ul id = "changevisible">
			<li><button onclick = "moveToHidden()">>></button></li>
			<li><button onclick = "moveToVisible()"><<</button></li>
			</ul>
			</div>
			<div id = "listhidden">
			<label>
			Скрытые столбцы
			</label>
			<ul id = "listcols">
			<c:forEach items = "${hiddencols}" var = "hiddencol" varStatus = "i">
				<li id = "${hiddencol.key}">${hiddencol.value}</li>
				</c:forEach>
			</ul>
			</div>
		</div>
	</li>

	</ul>
	
	<button id = "savestruct">Сохранить</button>
</c:if>

<c:if test="${section == 'rolechoise'}">
	<table>
				
				<div id = "reg${i.index}"></div>
			
			<h3>Добавить роль пользователю</h3>
			<c:if test = "${!empty roles}">
			<c:forEach items = "${roles}" var = "role" varStatus = "j">
			<ul>
			<li><a role = "button" href = "${pageContext.request.contextPath}/admin/adminusers/${userid}/addrole/${role.authority}">${role.authority}</a></li>
			
			</ul>
			
			</c:forEach>
			</c:if>
				
			</table>

</c:if>



<c:if test="${section == 'saveformatwindow'}">
<script>
$(document).ready(function(){

	$("#listcols li").on("mousedown", function(){
		$("#listcols li").each(function(){
			if($(this).hasClass("highlight")){
				$(this).removeClass("highlight");
				
			}
		});
		$(this).toggleClass("highlight");
	});
	
	$("#listformats li").on("click", function(){
		$("#currentformat").html('<input fid = "'+$(this).attr("id")+'" value = "'+$(this).html()+'" id = "formatname" type = "text"/>');
		$("#saveformat").attr("sect","update");
		$("#saveformat").html("Перезаписать текущий формат");
	});
	
	$("#formatname").keyup(function(){
		var exist = false;
		var val = $(this).val();
		
		$("#listformats li").each(function(){
			if($(this).html() == val){
				exist = true; 
				
			}
		});
	//	alert(exist);
		if(exist){
			$("#saveformat").attr("sect","update");
			$("#saveformat").html("Перезаписать текущий формат");
		}
		else{
			$("#saveformat").attr("sect","create");
			$("#saveformat").html("Сохранить новый формат");
		}	
	});
});
</script>
<div class = "close"><img width="30" height="30"  src = "<c:url value ="/resources/images/x-button-gray.png"/>"/></div>
<h3>Форматы отображения</h3>

<div id = "listformats">
			
				<c:if test="${!empty formats}">
					<label>
					Созданные форматы
				</label>
				<ul id = "listcols">
				<c:forEach items = "${formats}" var = "format" varStatus = "i">
				<li  id = "${format.id}">${format.name}</li>
				</c:forEach>
			</ul>
			</c:if>
			</div>
			<c:choose>
			<c:when test="${!empty currentformat}">
			<label>Выбранный формат:</label><div id = "currentformat"><input fid = "${currentformat.id}" value = "${currentformat.name}" id = "formatname" type = "text"/></div>
			</c:when>
			<c:otherwise>
			<label>Выбранный формат:</label><div id = "currentformat"><input fid = "" id = "formatname" type = "text"/></div>
			</c:otherwise>
			</c:choose>
			
			

<button id = "applyformat">Применить формат</button>
<c:choose>
			<c:when test="${!empty currentformat}">
			<button sect = "${type}" id = "saveformat">Перезаписать текущий формат</button>
			</c:when>
			<c:otherwise>
			<button sect = "${type}" id = "saveformat">Сохранить новый формат</button>
			</c:otherwise>
			</c:choose>

</c:if>


<c:if test="${section == 'addresident'}">

<div style = "float:left;" class = 'addedphotosegment' id = "propsegment${counter}">
		
		<div class = "adress">
		<h4>Адресс <c:if test = "${type == 'RESIDENT'}">представительства магазина</c:if><c:if test = "${type == 'DELIVERYSERVICE'}">пункта службы доставки</c:if> (№ ${innercounter+1}):</h4>
		
<div style='text-align:left; overflow:auto; width:900px; padding-top:10px; clear: left;'>
<div id='per' style='float: left;'>
<select class='fsel' name='residents[${counter}].street_type' id='adress_f'>
<option value='улица' >улица</option>
<option value='проспект' >проспект</option>
<option value='площадь'>площадь</option>
<option value='бульвар' >бульвар</option>
<option value='переулок' >переулок</option>
<option value='проезд' >проезд</option>
<option value='въезд' >въезд</option>
</select>
</div>
<input  type='text' title='Название' placeholder='Название' placeholder2='Название' name='residents[${counter}].street_name' id='ulica' value='' style='float: left;'>
<input  type='text' style='min-width:30px; width:30px; float: left;' title='№ Дома' placeholder='Дом' placeholder2='№ Дома' name='residents[${counter}].house_num' id='nhouse' value=''>
<input type='text' style='min-width:30px; width:30px; float: left;' title='Этаж' placeholder='Этаж' placeholder2='Этаж' name='residents[${counter}].level' id='level' value=''>
<input type='text' style='min-width:30px; width:30px; float: left;' title='№ Квартиры' placeholder='Кв.' placeholder2='№ Квартиры' name='residents[${counter}].room_num' id='nroom' value=''/>
<div id = "numb" style='float: left;'>



</div>
<input type = "hidden" name = "residents[${counter}].adresstype" value = "${type}"/>
</div>
		</div>
		
		<div class = "dtpprices" id = "deltype_prices${counter}">
			
			
		<h4>Способы доставки:</h4>
		
		
				<c:if test = "${!empty deltypes}">
		<c:forEach items = "${deltypes}" var = "deltype" varStatus = "k">
				<c:if test = "${deltype.type==type}">
				<div id = "delprice_${counter}_${k.index}" style = "margin-left:40px;">
				<c:choose>
				<c:when test = "${myfn:containsdt(resident.delivtypes,deltype)}">	
				<input type="checkbox" name="residents[${counter}].deltypes[${k.index}].deltype.id" value="${deltype.id}" onchange = "showAddforAddr(${counter},${k.index});" checked>${deltype.name}		
				<input type="hidden" name="residents[${counter}].deltypes[${k.index}].deltype.name" value="${deltype.name}"/>
				<input type="hidden" name="residents[${counter}].deltype.shortname" value="${deltype.name}"/>
				<div style = "padding-left:20px;" id = "add_${counter}_${k.index}">
				<c:if test = "${resident.deltypes[k.index].iscostless == false}"><label>Цена доставки: </label> От <input type="text" id = "price${k.index}" name="residents[${counter}].deltypes[${k.index}].minprice" value="${resident.deltypes[k.index].minprice}"/> до <input type="text" id = "maxprice${k.index}" name="residents[${counter}].deltypes[${k.index}].maxprice" value="${resident.deltypes[k.index].maxprice}"/></c:if>
				
				<c:choose>
				<c:when test = "${resident.deltypes[k.index].iscostless == true}">
				<input type="checkbox" name="residents[${counter}].deltypes[${k.index}].iscostless" checked/>Бесплатная<br>	
				</c:when>
				<c:otherwise>
				<input type="checkbox" name="residents[${counter}].deltypes[${k.index}].iscostless" onchange = "costless(${k.index});"/>Бесплатная<br>
				</c:otherwise>
				</c:choose>
				<label>Время доставки: </label> От <input type="text" style = "min-width:30px; width:30px;" id = "mindays${k.index}" name="residents[${counter}].deltypes[${k.index}].mindays" value="${resident.deltypes[k.index].mindays}"/> до <input style = "min-width:30px; width:30px;" type="text" id = "maxdays${k.index}" name="residents[${counter}].deltypes[${k.index}].maxdays" value="${resident.deltypes[k.index].maxdays}"/>
				</div>
				</c:when>
				<c:otherwise>
				<input type="checkbox" name="residents[${counter}].deltypes[${k.index}].deltype.id" value="${deltype.id}"  onchange = "showAddforAddr(${counter},${k.index});">${deltype.name}
				<input type="hidden" name="residents[${counter}].deltypes[${k.index}].deltype.name" value="${deltype.name}"/>
				<input type="hidden" name="residents[${counter}].deltypes[${k.index}].deltype.shortname" value="${deltype.shortname}"/>
				
				&nbsp&nbsp&nbsp&nbsp&nbsp<div style = "padding-left:20px;" class = "deladdr"  id = "add_${counter}_${k.index}"></div>	
				
				</c:otherwise>
				</c:choose>
				
				</div>
				</c:if>
		</c:forEach>
		</c:if>
		
		</div>
		
			<c:if test = "${!empty resident.phnnumbers}">
		<div class = "phnnumbers">	
			<h4>Номера телефона для адреса:</h4>
		
		<c:forEach items = "${resident.phnnumbers}" var = "phonenumber" varStatus = "j">
		<div style = "margin-left:30px;" id = "numb${j.index}">
		<input type='hidden'  name="residents[${counter}].phnnumbers[${j.index}].id" value="${resident.phnnumbers[0].id}">
		<input type='hidden'  name="residents[${counter}].phnnumbers[${j.index}].phtype" value="${resident.phnnumbers[0].phtype}">
		<label id = 'numb${j.index}.name' name='residents[${counter}].phnnumbers[${j.index}].numb'> ${j.index+1}) </label>
		<input type='text' title="Номер телефона" placeholder="Номер телефона" placeholder2="Номер телефона" name="residents[${counter}].phnnumbers[${j.index}].numb" value="${resident.phnnumbers[0].numb}">
		<form:errors path="residents[${counter}].phnnumbers[${j.index}].numb" cssClass="error" />
		<label>Основной номер:</label>
		<c:choose>
				<c:when test = "${phonenumber.isMainResidentNumber == true}">
				<input type="checkbox" name="residents[${counter}].phnnumbers[${j.index}].isMainResidentNumber" checked/>
				</c:when>
				<c:otherwise>
				<input type="checkbox" name="residents[${counter}].phnnumbers[${j.index}].isMainResidentNumber"/>
				</c:otherwise>
				</c:choose>
				
		<span style = 'margin-left:100px;'><a href = 'javascript:removePhone(${counter}, ${j.index}, false)'>Удалить номер</a></span>
		</div>
		
		</c:forEach>
		</div>
		</c:if>
		<script></script>
		<a href = 'javascript:appendPhone(${counter})'>Добавить номер телефона к адресу</a>
		
		
		<a href = 'javascript:removePhone(0, ${counter}, true)'>Удалить адрес</a>
		
		</div>




</c:if>



</body>
</html>