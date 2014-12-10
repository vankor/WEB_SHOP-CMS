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
	<link rel="stylesheet" type="text/css"  href="<c:url value="/resources/trackbar.css" />" rel="stylesheet" >
	<title>Goods</title>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="<c:url value="/resources/trackbar.js" />"></script>
<script src="<c:url value="/resources/adminscripts.js" />"></script>






<script type="text/javascript">
//	function setsearchval(el){
//		document.getElementById('search').value = '';
//		document.getElementById('search').value = el.innerHTML;
//		$('#resSearch').html("");
//	}

var url = "<c:out value="${pageContext.request.contextPath}"/>";
	$(document).ready(function(){

		$("#maincategtable td").bind("click", function(){
			location.href = $(this).parent("tr").find("#extend").attr("href");
		});
		
		
		$("#search").keyup(function(){
		     var search = $("#search").val();
		     $.ajax({
		       type: "POST",
		       url: "livesearch",
		       data: {"search": search},
		       cache: false,                                
		       success: function(responce){
		    	   $('#resSearch').html("");
		    	   var htmldata = "";
		        	/* what  i have to put here to updte my table <table id="table_grid"> */
			        $.each(responce,function(key, good) {
			        	
			            htmldata+="<div id = '1' style = 'cursor: pointer;'  onclick = 'setsearchval(this);' ><a href ='good/"+good.id+"'>" + good.name + "</a></div>";         
			        });
		          $("#resSearch").html(htmldata);
		       }
		     });
		     return false;
		   });
		
		
	$("#l1").click(function(){  
		var result = {};
		function isNumber(n) {
			  return !isNaN(parseInt(n)) && isFinite(n);
			}
		
		function getFormData(formid){
		    var unindexed_array = $(formid).serializeArray();
		    var indexed_array = {};
		    var all = {};
			
		    $.map(unindexed_array, function(n, i){
		    	var _;
		      	if(n.name.indexOf('{')!= -1){_ = n.name.indexOf('{'); _name = n.name.replace(/\}/gi, '').split('{');
		      	if(!indexed_array[_name[0]]){indexed_array[_name[0]] = all;}
		      	 for (var i=0, len=_name.length; i<len; i++) {
		    		 if (i == len-1) {
		      			 all[_name[i]] = n.value;
		    		 }
		    		 }
		      	}
		     	else{
		        indexed_array[n['name']] = n['value'];}
		    });

		    return indexed_array;
		}
		
		jQuery.fn.serializeObject=function() {
			  var json = {};
			  var all = [];
			  
			  var delim;
			  var map = {}
			  var j = 0;
			  jQuery.map(jQuery(this).serializeArray(), function(n, i) {
				  var _ ;
				 
				  if(n.name.indexOf('[')!= -1){_ = n.name.indexOf('[');_name = n.name.replace(/\]/gi, '').split('[');delim = "[";} else{_ = n.name.indexOf('{'); _name = n.name.replace(/\}/gi, '').split('{');delim = "{";}
			    
			    if (_ > -1) {
			    	var o = json;
			    	
			    	if(!result[_name[0]]){result[_name[0]] = all;}
			    	 for (var i=0, len=_name.length; i<len; i++) {
			    		 if (i == len-1) {
			    		          if (o[_name[i]] != null &&_name[i] == 'vals' ) {
			    			           	    		o[_name[i]].push(n.value);
			    						          }
			    						          else if (o[_name[i]] != null &&_name[i] != 'vals') {all[j] = o; j++; json = {}; json[_name[i]] = n.value || '';}
			    						          else{o[_name[i]] = (isNumber(n.value)) ?  parseInt(n.value) : o[_name[i]] = n.value || ''; 
			    						          o[_name[i]] = (typeof o[_name[i]] == 'string' &&_name[i]== 'vals') ? [o[_name[i]]]:o[_name[i]];
			    						          }
			    						        }
			    	 	 }
			    	 all[j] = o;
			
			    }

			  });
			 
			  return result;
			};
		
		$('#filter').serializeObject();
		var filters = $('#pricefilter').serializeObject();
	//	alert(JSON.stringify(postData));
		alert(JSON.stringify(filters));
//		alert(JSON.stringify(postData)+","+JSON.stringify(pricefilter));
//		var filters = JSON.stringify(postData)+","+JSON.stringify(pricefilter);
	//	alert(filters);
//		 alert(JSON.stringify(postData));	
//		 alert(JSON.stringify(postData1));
//	var data = $('#searchForm').serialize();
//	var producer = $('#value').val();
//	   var json = {"value" : producer};
//	   var json1 = $('#searchForm').serialize();
//	   var json2 = $('#searchForm').serializeArray();
//	   var json3 = $('#filter').serializeArray();
//	   var json4 = $('#filter').serialize();
//	   var json6 = {"params":[{"propid":22,"propname":"parameter1","vals":["ggg4","string12","VVVV1"]},{"propid":31,"propname":"p1","vals":["VAL1","ehe2","string18"]},{"propid":32,"propname":"p2","vals":["VAL2","fff42","string3"]},{"propid":33,"propname":"p3","vals":["VAL3","string2","fff23"]},{"propid":34,"propname":"p4","vals":["VAL4","string1","fff4"]}]};
	 
		   
	$.ajax({
		type: 'POST',
	    dataType : "json",
	    url : 'goodfilter',
	    async: false,
	    headers : {
	        'Accept' : 'application/json',
	        'Content-Type' : 'application/json'
	    },
	    data: JSON.stringify(filters),
	    success : function(responce) {   
	    	var table = document.getElementById('res');
        	for(var i = table.rows.length - 1; i > 0; i--)
	        {
	        table.deleteRow(i);
	        }
	        /* what  i have to put here to updte my table <table id="table_grid"> */
	        $.each(responce,function(key, good) {
	        	
	            var htmlrow ="<tr><td>" + good.name + "</td></tr>";         
	            $('#res').append(htmlrow);
	        });

	    },      
	    error : function(xhr, status, error){
            alert("Error!" + xhr.status);
	    }
	});
	});
	});
	


</script>
</head>
<body id = "accounts">

<script type="text/javascript">

    $(document).ready(function() {



    });

</script>

<script>
    $(document).ready(function() {



    });
</script>



<jsp:include page="/WEB-INF/views/adminheader.jsp" />



<br>


<div class="mainwrapper"> 
<c:if test = "${isroot == true }">
<div id = "sector">
<h2>Базовые параметры</h2>
<form:form method="post" action="${pageContext.request.contextPath}/admin/updatedBasicConfig" modelAttribute="basic" enctype="multipart/form-data">
<ul>
<li>
		<div id = "row">
			<div id = "col1">
				<form:label path="shopname">
					Название интернет-магазина
				</form:label>
			</div>
			<div id = "col2">
				<form:input path="shopname" /><form:errors path="shopname" cssClass="error" />
			</div>
		</div>
</li>

<li>
	<div id = "row">
			<div id = "col1">
				<form:label path="thumb">
					Логотип интренет-магазина
				</form:label>
			</div>
			<div id = "col2">
			<form:input type = "hidden" path = "thumb" id = "thumb"/>
			<form:input type = "hidden" value = "1" path = "id" id = "id"/>
				<c:choose><c:when test = "${!empty basic.thumb}"><img src="<c:url value="/resources/images${basic.thumb}" />" width="50" alt="Логотип"  /></c:when><c:otherwise>Нет логтипа   </c:otherwise></c:choose><form:input type = "file" id = "newthumb" text = "Загрузить новый логотип" path="newthumb"/>
			<form:errors path="thumb" cssClass="error" />
			</div>
		</div>
</li>
</ul>
<input value = "Сохранить" type = "submit"></input>
</form:form>
</div>
</c:if>
<c:choose>
<c:when test = "${isroot == true}">
<h2>Вести корневые категории</h2>
</c:when>
<c:otherwise>
<h2>Категория ${catname}</h2>
</c:otherwise>
</c:choose>
<c:if test = "${isroot != true}">
<div id = "goods">
<h3>Товары:</h3>
<c:if test = "${result == 'success'}">
<div id = "success"><p>${resulttext}</p></div>
</c:if>

<c:choose>
<c:when test = "${isfinal == false || isroot == true}">
<span>В категории нет товаров - категория промежуточная!</span>
</c:when>

<c:otherwise>
<c:if test="${!empty containers}">
<div id = "maincategtable">
<div id = "list" class = "list">
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
				<a <c:if test = "${entry.key == 'Развернуть'}"> id = "extend" </c:if> href="${pageContext.request.contextPath}${entry.value}">${entry.key}</a><br>
				</c:forEach>
				</td>
				
			
			</tr>
</c:forEach>
</table>
</div>
</div>
</c:if>
</c:otherwise>
</c:choose>
</div>
</c:if>
<div id = "categs">
<c:choose>
<c:when test = "${isroot == true}">
<h3>Список корневых категорий</h3>
<p>Примечание - корневая категория является пунктом меню.</p>

</c:when>
<c:otherwise>
<h3>Подкатегории:</h3>
</c:otherwise>
</c:choose>

<c:choose>
<c:when test = "${isfinal == true || isroot == false}">
<span>Категория конечная и содержит товары!</span>
</c:when>
<c:otherwise>
<div id = "maincategtable">
<div id = "list" class = "list">
<table id = "table" class="data">
<tr>
			<th id = "selector"></th>
			<c:forEach items="${containers[0].fields}" var="field">
			<th id = "${field.key.propname}">${field.key.colname}</th>
			</c:forEach>
			<th id = "functions">Функции</th>
			
</tr>

<c:forEach items="${containers}" var="container" varStatus = "i">
			<tr>
				<td id = "selector${i.index}" style = "background: #ededed;"></td>
				<c:forEach items="${container.fields}" var="field">
				<td>${field.value}</td>
				<c:if test = "${field.key.propname == 'isFinalCategory' && field.value == false}"><script>
				var i = ${i.index};
				$("#selector"+i).html("<img width = '40' height = '40' src = '"+url+"/resources/images/down-pointer.png'></img>");
				</script></c:if>
				</c:forEach>
				<td>
				<c:forEach items="${container.refs}" var="entry">
				<a <c:if test = "${entry.key == 'Развернуть'}"> id = "extend" </c:if> href="${pageContext.request.contextPath}${entry.value}">${entry.key}</a><br>
				</c:forEach>
				</td>
				
			
			</tr>
</c:forEach>
</table>
</div>
</div>
</c:otherwise>
</c:choose>
<div id = "functions">

<a href="<c:url value="/admin/add/addCateg" />">
	Добавить категорию
</a>


<c:if test = "${isfinal == false}">

<c:set var = "currid" value = "${catid}"/>

<c:if test = "${empty isroot || isroot == false}">
<c:set var = "currid" value = "0"/>
</c:if>

<a href="<c:url value="/admin/admincategory/${currid}/addChildCateg" />">
	Добавить cубкатегорию в категорию
</a>
</c:if>

<c:if test = "${empty isroot || isroot == false}">

<a href="<c:url value="/admin/admincategory/${catid}/updateCateg" />">
	Изменить текущую категорию
</a>
<br>
</c:if>
<c:if test = "${isfinal == true}">
<a href="<c:url value="/admin/admincategory/${catid}/addGoodtoCat" />">
	Добавить товар в категорию
</a>

<a href="${pageContext.request.contextPath}/admin/admincategory/${catid}/addProducer">
	Добавить производителя в категорию
</a>

</c:if>


</div>
</div>


<c:if test = "${isroot != true}">
<div id = "news">
<h3>Новости:</h3>
<c:choose>
<c:when test = "${empty newscontainers}">
<span>Категория не содержит новостей!</span>
</c:when>
<c:otherwise>
<div id = "maincategtable">
<div id = "list" class = "list">
<table id = "table" class="data">
<tr>
			<th id = "selector"></th>
			<c:forEach items="${newscontainers[0].fields}" var="field">
			<th id = "${field.key.propname}">${field.key.colname}</th>
			</c:forEach>
			<th id = "functions">Функции</th>
			
</tr>

<c:forEach items="${newscontainers}" var="container" varStatus = "i">
			<tr>
				<td id = "selector${i.index}" style = "background: #ededed;"></td>
				<c:forEach items="${container.fields}" var="field">
				<td>${field.value}</td>
				
				</c:forEach>
				<td>
				<c:forEach items="${container.refs}" var="entry">
				<a <c:if test = "${entry.key == 'Развернуть'}"> id = "extend" </c:if> href="${pageContext.request.contextPath}${entry.value}">${entry.key}</a><br>
				</c:forEach>
				</td>
				
			
			</tr>
</c:forEach>
</table>
</div>
</div>
</c:otherwise>
</c:choose>
<div id = "functions">

<a href="<c:url value="/admin/admincategory/${catid}/addNewstoCat" />">
	Добавить новость в категорию
</a>


</div>
</div>
</c:if>



<c:if test="${!empty currentCatList}">
	<table class="data">
		<tr>
			<th>Name</th>
			<th>&nbsp;</th>
			<th>&nbsp;</th>
			
		</tr>
		<c:forEach items="${currentCatList}" var="category">
			<tr>
				<td><a href="${pageContext.request.contextPath}/admin/admincategory/${category.id}">${category.name}</a></td>
				<td><a href="${pageContext.request.contextPath}/admin/admincategory/${category.id}/deleteCat">Удалить категорию</a></td>
			</tr>
		</c:forEach>
	</table>
</c:if>



<!--  
<a href="<c:url value="/login" />">
	Вход на сайт
</a>
<br/>

<a href="<c:url value="/reg" />">
	Регистрация
</a>
<br/>

<a href="<c:url value="/usrmenu" />">
	Меню пользователя
</a>
<br/>
-->

</div>
</body>
</html>