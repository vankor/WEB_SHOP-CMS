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
<title>Вести категорию</title>
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
<c:if test = "${type == 'update'}"><c:set var="action" value="updatedCateg" /></c:if>
<c:if test = "${type == 'create' || type == 'createchild'}"><c:set var="action" value="${pageContext.request.contextPath}/admin/add/addedCateg" /></c:if>


<div style = "width:900px; padding-left:4%;" id = "form">
<h1>Вести категорию:</h1>
<c:if test = "${result == 'success'}">
<div id = "success"><p>Категория успешно сохранена!</p></div>
</c:if>
<form:form method="post" action = "${action}"  modelAttribute="category" enctype="multipart/form-data">
<ul style = "padding-left:0;">
	<div id = "sector">
	<h3>Основные данные</h3>
	<c:if test = "${type == 'update'}">
		<form:input type = "hidden" path="id" />
		
	</c:if>
		<li>
		<div id = "row">
			<div id = "col1"><form:label path="name">
				Название категории:
			</form:label></div>
		
			<div id = "col2"><form:input path="name" /><form:errors path="name" cssClass="error" /></div>
			
		
		
		</div>
		</li>
		
		<li>
		<c:if test = "${type != 'createchild'}">
		
			<div id = "col1"><form:label  path="parent_category">
				Родительская категория:
			</form:label></div>
			</c:if>
			<form:input id = "cat_id" type = "hidden" value = "${category.parent_category.id}" path="parent_category.id" />
			<c:choose><c:when test = "${type == 'createchild'}"><div id = "col2"><form:label  path="parent_category" > ${category.parent_category.name}</form:label></div></c:when><c:otherwise><div id = "col2"><form:input id = "cat_name"  onclick = "load_goodchoise(this, '${pageContext.request.contextPath}/choiseCategory', 'cat', 'choiseCategory')" path="parent_category.name" /></div></c:otherwise></c:choose>
		
			
		</li>	
		<li>
	<div id = "row">
			<div id = "col1">
				<form:label path="thumb">
					Аватарка категории
				</form:label>
			</div>
			<div id = "col2">
			<form:input type = "hidden" path = "thumb" id = "thumb"/>
				<c:choose><c:when test = "${!empty category.thumb}"><img src="<c:url value="/resources/images${category.thumb}" />" width="50" alt="${category.name}"  /></c:when><c:otherwise>Нет аватара   </c:otherwise></c:choose><form:input id = "newthumb" type = "file" text = "Загрузить новый аватар" path="newthumb"/>
			<form:errors path="thumb" cssClass="error" />
			</div>

			
		</div>
	</li>
		
		<li>
		<div id = "row">
			<div id = "col1"><form:label id = "istop" path="istop">
				Топ категория (отображать на главной):
			</form:label></div>
			<div id = "col2"><form:checkbox path="istop" /></div>
		</div>
		</li>
		
		<li>
		<div id = "row">
			<div id = "col1"><form:label id = "isfinal" path="isFinalCategory">
				Конечная категория (с товарами):
			</form:label></div>
			<div id = "col2"><form:checkbox path="isFinalCategory" /></div>
		</div>
		</li>
		</div>	
		<div id = "cat_properties">
		<h3>Параметры для товаров категории:</h3>
		<c:if test = "${!empty category.propsegments}">
		<c:forEach items = "${category.propsegments}" var = "parameter" varStatus = "i">
		<div style = "float:left;" class = 'addedphotosegment' id = "propsegment${i.index}">
		<form:label id = 'prop${i.index}.name' path = 'propsegments[${i.index}]' name='parametername'> Сегмент параметров  ${i.index+1}: </form:label>
		<form:input id = 'propsegments${i.index}.name' type="hidden" path = 'propsegments[${i.index}].id' name = 'propsegments'/>
		<form:input id = 'propsegments${i.index}.name' path = 'propsegments[${i.index}].name' name = 'propsegments'/>
		<form:errors path="propsegments[${i.index}].name" cssClass="error" />
		<form:label id = 'propsegments${i.index}.priority' path = 'propsegments[${i.index}].priority' name='propsegments'> Приоритет: </form:label>
		<form:input class = "numinput"  id = 'propsegments${i.index}.priority' path = 'propsegments[${i.index}].priority' name = 'propsegments'/>
		<form:errors path="propsegments[${i.index}].priority" cssClass="error" />
		<a href = 'javascript:appendProperty(${i.index})'>Добавить параметр в сегмент</a>
		<a href = 'javascript:removeProperty(0, ${i.index}, true)'>Удалить сегмент параметров</a>
		<div id = "propsegment${i.index}">
			<c:if test = "${!empty category.propsegments[i.index].properties }">
			<c:forEach items = "${category.propsegments[i.index].properties}" var = "prop" varStatus = "j">
			<div class = "addpropertysegment" id = "prop${j.index}">
			<form:label id = 'prop${i.index}.name' path = 'propsegments[${i.index}].properties[${j.index}].name' name='parametername'> Параметр  ${j.index+1}: </form:label>
			<form:input type = "hidden" id = 'prop${i.index}.name' path = 'propsegments[${i.index}].properties[${j.index}].id' name='parametername'/>
			<form:input id = 'prop${i.index}.name' path = 'propsegments[${i.index}].properties[${j.index}].name' name='parametername'/>
			<form:errors path="propsegments[${i.index}].properties[${j.index}].name" cssClass="error" />
			<form:label id = 'prop${i.index}.priority' path = 'propsegments[${i.index}].properties[${j.index}].priority' name='parameterpriority'> Приоритет: </form:label>
			<form:input id = 'prop${i.index}.priority' class = "numinput" path = 'propsegments[${i.index}].properties[${j.index}].priority' name='parameterpriority'/>
			<form:errors path="propsegments[${i.index}].properties[${j.index}].priority" cssClass="error" />
			<form:label id = 'prop${i.index}.mainvalscount' path = 'propsegments[${i.index}].properties[${j.index}].mainvalscount' name='parametermainvalscount'> Кол. основных значений: </form:label>
			<form:input id = 'prop${i.index}.mainvalscount' class = "numinput" path = 'propsegments[${i.index}].properties[${j.index}].mainvalscount' name='parametermainvalscount'/>
			<form:errors path="propsegments[${i.index}].properties[${j.index}].mainvalscount" cssClass="error" />
			
			<a href = 'javascript:removeProperty(${i.index}, ${j.index}, false)'>Удалить параметр</a>
			<br>
			<script>cc[counter] = ${j.index + 1} </script>
			</div>
			</c:forEach>
			</c:if>
		<script>counter = ${i.index + 1} </script>
		</div>
		</div>
		</c:forEach>
		</c:if>
		<div id = "parentId"></div>
		</div>
		<script>
		function appendPropertySegment(){
			var div;
			var id = "propsement"+counter;
			cc[counter]=0;
			div = document.createElement("div");
			div.setAttribute("id", "propsegment"+counter);
			div.setAttribute("class", "addedphotosegment");
			div.setAttribute("style", "float:left;");
			
			div.innerHTML = "<div id = 'row'><label id = 'propsegments"+counter+".name' name='propsegments["+counter+"].name'> Сегмент параметров "+(counter+1)+": </label><input id='propsegments"+counter+".name' name='propsegments["+counter+"].name' type='text'/><label>Приоритет</label><input class = 'numinput' id='propsegments"+counter+".priority' name='propsegments["+counter+"].priority' type='text'/><div id = 'refs'><a href = 'javascript:appendProperty("+counter+")'>Добавить параметр в сегмент</a><br><a href = 'javascript:removeProperty(0, "+counter+", true)'>Удалить сегмент параметров</a></div></div>";
			
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
		
		function removeProperty(h, id, segm){
			
			var strid;
			var elem;
			if(segm){
				$("#propsegment"+id).remove();
				counter--;
				}
			
			else{
				elem = $("#propsegment"+h).find('#prop'+id);
				cc[id]--;
			$(elem).remove();}
	
		}
		
		function appendProperty(parid){
//			alert("propsegment"+parid);
			propnum = cc[parid];
			
			var div;
			
			div = document.createElement("div");
			div.setAttribute("id", "prop"+propnum);
			div.setAttribute("class", "addpropertysegment");
			div.innerHTML = "<label id = 'props"+propnum+".name' name='props["+propnum+"].name'> Параметр "+(propnum+1)+": </label><input id='props"+propnum+".name' name='propsegments["+parid+"].properties["+propnum+"].name' type='text'/><label>Приоритет</label><input class = 'numinput' id='props"+propnum+".mainvalscount' name='propsegments["+parid+"].properties["+propnum+"].priority' type='text'/><label>Кол. основных значений</label><input class = 'numinput' id='props"+propnum+".mainvalscount' name='propsegments["+parid+"].properties["+propnum+"].mainvalscount' type='text'/><a href = 'javascript:removeProperty("+parid+", "+propnum+", false)'>Удалить параметр</a>";
			cc[parid]++;
//			alert(div);
			document.getElementById("propsegment"+parid).appendChild(div);
			
		}
		
		</script>
		
			<div id = "addref" <c:if test = "${category.isFinalCategory != 'true'}">style = "display:none;"</c:if>>
				<a role = "button" onclick = "return appendPropertySegment()">Добавить сегмент параметров</a>
			</div>
		
		
	
	
	
	<div id = sector>
	<h3>Параметры для веб-страницы:</h3>
	<c:if test ="${type == 'update'}">
	<form:input type = "hidden" path="page.id" />
	</c:if>
		
	<li>
	<div id = "row">
			<div id = "col1">
				<form:label path="page.url">
					URI страницы:
				</form:label>
			</div>
	
			<div id = "col2"><form:input path="page.url"/><form:errors path="page.url" cssClass="error" /></div>
			
		</div>
	</li>
	<li>	
		<div id = "row">
			<div id = "col1">
				<form:label path="page.title">
					Title страницы:
				</form:label>
			</div>
	
			<div id = "col2"><form:input path="page.title"/><form:errors path="page.title" cssClass="error" /></div>

		</div>
	</li>
	
		<li>
		<div id = "row">
			<div id = "col1"><form:label path="page.h1">
				Заголовок h1:
			</form:label></div>
			<div id = "col2"><form:input path="page.h1" /></div>

		</div>
		</li>
	<li>	
		<div id = "row">
			<div id = "col1">
				<form:label path="page.meta">
					Meta теги страницы:
				</form:label>
			</div>
	
			<div id = "col2"><form:textarea path="page.meta"/></div>
		</div>
	</li>
	
	<li>	
		<div id = "row">
			<div id = "col1">
				<form:label path="page.html">
					Общий html-код для всех товаров:
				</form:label>
			</div>
	
			<div id = "col2"><form:textarea path="page.html"/></div>
		</div>
	</li>
	
	<li>	
		<div id = "row">
			<div id = "col1">
				<input type="submit"
				value="Сохранить" />
			</div>
		</div>
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