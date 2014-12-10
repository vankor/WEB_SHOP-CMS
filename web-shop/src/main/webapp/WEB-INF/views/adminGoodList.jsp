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

<!-- 2. Подключим jQuery UI 
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>-->
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>

<script src="<c:url value="/resources/jquery.dragtable.js" />"></script>
<script src="<c:url value="/resources/contextmenu.js" />"></script>
<link rel="stylesheet" href="<c:url value="/resources/dragtable-default.css"/>"/>

<script type="text/javascript">
$(document).ready(function(){
	
	

   $("#tabs").tabs();

});
</script>

<script>



var url = "<c:out value="${pageContext.request.contextPath}"/>";
var classname = "<c:out value="${classname}"/>";
adminclassname = "<c:out value="${adminclassname}"/>";

$(document).ready(function(){
		
	var url = "<c:out value="${pageContext.request.contextPath}"/>";
	$(document).on("click","th", function(e){
		$(this)[0].oncontextmenu = function() {
            return false;
        }

		if(!$("#moveactive").is(":checked")){  

	if(e.button == 0){
//	        alert('Вы кликнули левой колесиком');
	
		
	var isdate = ($( this ).attr('type')=="Date")?true:false;
	var objclass = $( this ).attr('type');
//	alert(adminclassname);
	var text = $(this).html();
	var propname = $(this).attr("id");
	var position = $(this).position();
	var offset = $(this).offset();
	var h = parseInt($(this).css("padding-bottom"))+parseInt($(this).css("padding-top"))+20;
	var pgnum = $("#pgnum").val();
	var coladmintype = adminclassname;
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
			$('table').dragtable();
			
				
				
			
		},      
	    error : function(xhr, status, error){
            alert("Error!" + xhr.status);
        }
		
	});
	}
	}
    }	
});





});

function deletefilter(propname, type){
	
	///	alert("egwg");

		if(propname==null){
			propname = $("#delref").attr("value");
		}

		var pgnum = parseInt($("#pgnum").val());
		
//		alert(propname+"   "+pgnum);

		$.ajax({
			type: "POST",
			url: url+"/admin/deletepropfilter",
			data: {"classname":classname, "adminclassname":adminclassname, "propname":propname, "pgnum":pgnum, "type":type},
			dataType: "text",
			success: function(data){
				$("#list").html(data);
				$('table').dragtable();
			}
		});
		
		
	}
	
function deletesortcriteria(propname){
	var pgnum = $("#pgnum").val();
//	alert(propname+"   "+pgnum);
	$.ajax({
		type: "POST",
		url: url+"/admin/deletesortcriteria",
		data: {"classname":"GoodItem", "propname":propname, "pgnum":pgnum},
		dataType: "text",
		success: function(data){
			$("#list").html(data);
			$('table').dragtable();
		}
	});
}	
</script>
<script>
$(document).ready(function(){
//	$('table').dragtable();
	$('#moveactive').change(function() {
        if($(this).is(":checked")) {
 //           var returnVal = confirm("Are you sure?");
//            $(this).attr("checked", returnVal);
        	$('table').dragtable();
        	$("th").off("click");
   //     	alert("reh");
        }
                
    });
	
	
	
    $('th').contextMenu('myMenu1', {
   
    	
    	
        bindings: {

          'filter': function(t) {
  //      	alert(url+"/admin/multichange");
            alert(t.id);

          },

          'hide': function(t) {
        	  var pgnum = $("#pgnum").val();
        	  var coladmintype = adminclassname;
            alert(t.id+ "  "+url+"/admin/hidecolumn");
            alert("propname " + t.id + " pgnum " + pgnum + "  adminclassname "+adminclassname+ " coladmintype "+coladmintype);
            $.ajax({
				type: 'POST',
//			    dataType : "json",
				url: url+"/admin/hidecolumn",
			    data: {"propname":t.id, "pgnum":pgnum, "adminclassname":adminclassname, "coladmintype":coladmintype},
		        success: function(data){
		      			$("#list").html(data);
		        },      
	    	    error : function(xhr, status, error){
	                alert("Error!" + xhr.status);
	    	    }
		    });

          },

        
        }

      });	
//$('table').dragtable();
});




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


<!--  
<table id="one">
    <thead class="test">
        <tr>
            <th data-header="id"><div class="dragtable-drag-handle"></div> ID</th>
            <th data-header="first_name">first name</th>
            <th data-header="last_name">last name</th>
            <th data-header="phone_number">phone number</th>
            <th data-header="color">team color</th>
            <th data-header="salary">salary made</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>10234</td>
            <td>John</td>
            <td>Smith</td>
            <td>555-555-5555</td>
            <td>orange</td>
            <td>$2.00</td>
        </tr>
        <tr>
            <td>102288</td>
            <td>Jane</td>
            <td>Smith</td>
            <td>555-445-5555</td>
            <td>purplue</td>
            <td>$1000.00</td>
        </tr>

        </tbody>
</table>
-->

<!--  <div id="tabs">
<ul>
<li><a href="#tabs-1">Медведь</a></li>
<li><a href="#tabs-2">Белка</a></li>
<li><a href="#tabs-3">Лисица</a></li>
</ul>
<div id="tabs-1">
<p><b>Медведи</b> (<i>лат. Ursus</i>) — род млекопитающих отряда хищных.</p>
<p>Согласно палеонтологическим сведениям, род медведей появился 5-6 миллионов лет назад. Первым его представителем в настоящее время считают медведя <i>Ursus minimus</i> — относительно небольшое животное, чьи ископаемые останки найдены на территории Франции. Все современные четыре вида рода, а также ряд вымерших (таких, например, как пещерный медведь <i>Ursus spelaeus</i>) происходят от этрусского медведя (<i>Ursus etruscus</i>), жившего 2-1 миллиона лет назад.</p>
<p>Самым молодым видом рода является белый медведь, который отделился от бурого медведя примерно 200 000 лет назад.</p>
</div>
<div id="tabs-2">
<p><b>Белки</b> (<i>лат. Sciurus</i>) — род грызунов семейства беличьих. Кроме собственно рода <i>Sciurus</i>, белками называют ещё целый ряд представителей семейства беличьих из родов бурундуковые белки (<i>Tamiasciurus</i>), пальмовые белки (<i>Funambulus</i>) и многих других. Что касается собственно рода <i>Sciurus</i>, то он объединяет в себя около 30 видов, распространённых в Европе, Северной и Южной Америке 
и в умеренном поясе Азии.</p>
<p>Имеет удлинённое тело с пушистым длинным хвостом, уши длинные, цвет темно-бурый с белым брюшком, иногда серый (особенно зимой). Водятся повсюду, кроме Австралии. Белка является источником ценного меха.</p>
</div>
<div id="tabs-3">
<p><b>Обыкновенная, или рыжая лисица</b> (<i>лат. Vulpes vulpes</i>) — хищное млекопитающее семейства псовых, наиболее распространённый и самый крупный вид рода лисиц. Длина тела 60—90 см, хвоста — 40—60 см, масса — 6—10 кг.</p>
<p>Окраска и размеры лисиц различны в разных местностях; всего насчитывают 40—50 подвидов, не учитывая более мелких форм. В общем, при продвижении на север лисицы становятся более крупными и светлыми, на юг — мелкими и более тускло окрашенными. В северных районах и в горах также чаще встречаются чёрно-бурые и другие меланистические формы окраски лисиц. Наиболее распространённый окрас: ярко-рыжая спина, белое брюхо, тёмные лапы. Внешне лисица представляет собой зверя среднего размера с изящным туловищем на невысоких лапах, с вытянутой мордой, острыми ушами и длинным пушистым хвостом.</p>
</div>-->





<table id = "table" class="data">
<thead class="test">
<tr>
			<th data-header="selector" id = "selector"></th>
			<c:forEach items="${containers[0].fields}" var="field">
			<th data-header="${field.key.propname}" type = "${field.key.propclassname}"<c:if test = "${field.key.isobject == true}">isobject = "true"</c:if> id = "${field.key.propname}">${field.key.colname}</th>
			</c:forEach>
			<th data-header="functions" id = "functions">Функции</th>
			
</tr>
</thead>
<c:forEach items="${containers}" var="container" varStatus = "i">
			<tr>
				<td id = "selector${i.index}" style = "background: #ededed;"></td>
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