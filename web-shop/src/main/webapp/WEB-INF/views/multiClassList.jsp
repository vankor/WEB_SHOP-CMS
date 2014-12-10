<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="myfn" uri="http://samplefn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf8">
	<title> Управление товарами</title>
		<link rel="stylesheet" type="text/css"  href="<c:url value="/resources/style.css" />" rel="stylesheet" >
		


	<link rel="stylesheet" type="text/css"  href="<c:url value="/resources/trackbar.css" />" rel="stylesheet" >
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.min.js" type="text/javascript"></script>
<script charset="UTF-8"  src="<c:url value="/resources/js/adminscripts.js" />"></script>
<script charset="UTF-8"  src="<c:url value="/resources/js/myscripts.js" />"></script>
<script charset="UTF-8"  src="<c:url value="/resources/js/popups.js" />"></script>
<script src="<c:url value="/resources/js/popups.js" />"></script>
<link rel="stylesheet" href="<c:url value="/resources/popupwindow.css"/>"/>
<link rel="stylesheet" href="<c:url value="/resources/datepicker.css"/>"/>

<script src="<c:url value="/resources/js/mascedinput.js" />"></script>
<script src="<c:url value="/resources/js/datepicker.js" />"></script>


<script>



var url = "<c:out value="${pageContext.request.contextPath}"/>";
var classname = "<c:out value="${classname}"/>";
var adminclassname = "<c:out value="${adminclassname}"/>";

$(document).ready(function(){
		
	var url = "<c:out value="${pageContext.request.contextPath}"/>";
	$(document).on("click","th", function(e){
	
	var isdate = ($( this ).attr('type')=="Date")?true:false;
	var objclass = $( this ).attr('type');
//	alert(adminclassname);
	var text = $(this).html();
	var propname = $(this).attr("id");
	var position = $(this).position();
	var offset = $(this).offset();
	var h = parseInt($(this).css("padding-bottom"))+parseInt($(this).css("padding-top"))+20;
	var pgnum = $("#pgnum").val();
	var coladmintype = $( this ).attr("admintype");
//	alert(coladmintype+"     "+adminclassname);
	var currurl = $("#currurl").val();
	var colname = $(this).html();
//	alert(h);
//	alert("left: " + position.left + ", top: " + position.top);
//	alert("left: " + offset.left + ", top: " + offset.top);
//	alert(url+"/admin/getpropvals     Поле:"+propname);
	
	if(propname!=null && propname!=""){
	$.ajax({
		type: "POST",
		url: url+"/admin/getpropvals",
		data: {"classname":classname,"adminclassname":adminclassname, "propname":propname, "pgnum":pgnum, "colname":colname, "currurl":currurl, "coladmintype":coladmintype},
		dataType: "text",
		success: function(data){
//			alert(data);
			$("#list").append("<div class = 'filters' id = 'filters'></div>");
			$("#filters").offset({ top: (offset.top+h), left: offset.left});
			$("#filters").html(data);
			
			
				
				
			
		},      
	    error : function(xhr, status, error){
            alert("Error!" + xhr.status);
        }
		
	});
	}
});

/*$("#filter").bind("click", function(){
	alert(url+"/admin/getpropvalues");
	
});*/
});

function deletefilter(propname, type){
	
	///	alert("egwg");

		if(propname==null){
			propname = $("#delref").attr("value");
		}

		var pgnum = $("#pgnum").val();
//		alert(propname+"   "+pgnum);

		$.ajax({
			type: "POST",
			url: url+"/admin/deletepropfilter",
			data: {"classname":classname, "adminclassname":adminclassname, "propname":propname, "pgnum":pgnum, "type":type},
			dataType: "text",
			success: function(data){
				$("#list").html(data);
				
			}
		});
		
		
	}
</script>
</head>
<body>

<jsp:include page="/WEB-INF/views/adminheader.jsp" />
<!--  <input id = "test" type = "text"/>-->
<br>
<div class = "widemainwrapper">
<h2>${title}</h2>
<div id="list" class = "list">


<jsp:include page="/WEB-INF/views/admininstrumentbar.jsp" />



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

<div class="ccpnav">
		<form id = "pages">
		<span class="arrow">&#8592;</span><a class="a-text a-prev" href="${pageContext.request.contextPath}${entityurl}${pgnum-1}">Пред.</a>
		<c:forEach begin="1" end="${pagecount+1}" var="i">
		<c:choose>
		<c:when test = "${i==pgnum}">
		<span class="">${i}</span><input type = "hidden" value = "${i}" name = "pagenum" id = "pagenum"/>
		</c:when>
		<c:otherwise>
		<a class="a-text" href="${pageContext.request.contextPath}${entityurl}${i}">${i}</a>
		</c:otherwise>
		</c:choose>
		</c:forEach>
		<a class="a-text a-next" href="${pageContext.request.contextPath}${entityurl}${pgnum+1}">След.</a><span class="arrow">&#8594;</span>
		</form>
</div>
</div>
</div>
<div id = "popup" class = "popupwindow"></div>
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