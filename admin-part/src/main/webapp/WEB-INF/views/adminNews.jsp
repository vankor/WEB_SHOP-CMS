<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value="/resources/style.css"/>"/>


<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.min.js" type="text/javascript"></script>
		<script type="text/javascript" src="http://js.mobilluck.com.ua/js/ur.js"></script>
		<script type="text/javascript">
(function (a, b){
	if (/android.+mobile|avantgo|bada\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\/|plucker|pocket|psp|symbian|treo|up\.(browser|link)|vodafone|wap|windows (ce|phone)|xda|xiino/i.test(a) || /1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\-(n|u)|c55\/|capi|ccwa|cdm\-|cell|chtm|cldc|cmd\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\-s|devi|dica|dmob|do(c|p)o|ds(12|\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\-|_)|g1 u|g560|gene|gf\-5|g\-mo|go(\.w|od)|gr(ad|un)|haie|hcit|hd\-(m|p|t)|hei\-|hi(pt|ta)|hp( i|ip)|hs\-c|ht(c(\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\-(20|go|ma)|i230|iac( |\-|\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\/)|klon|kpt |kwc\-|kyo(c|k)|le(no|xi)|lg( g|\/(k|l|u)|50|54|e\-|e\/|\-[a-w])|libw|lynx|m1\-w|m3ga|m50\/|ma(te|ui|xo)|mc(01|21|ca)|m\-cr|me(di|rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\-2|po(ck|rt|se)|prox|psio|pt\-g|qa\-a|qc(07|12|21|32|60|\-[2-7]|i\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\-|oo|p\-)|sdk\/|se(c(\-|0|1)|47|mc|nd|ri)|sgh\-|shar|sie(\-|m)|sk\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\-|v\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\-|tdg\-|tel(i|m)|tim\-|t\-mo|to(pl|sh)|ts(70|m\-|m3|m5)|tx\-9|up(\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|xda(\-|2|g)|yas\-|your|zeto|zte\-/i.test(a.substr(0, 4)))
		window.location = b
	})(navigator.userAgent || navigator.vendor || window.opera, 'http://m.mobilluck.com.ua/');
</script>


<link type="text/css" rel="stylesheet" href="<c:url value="/resources/date_style.css" />">


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
<script type="text/javascript">
tinyMCE.init({
	
mode : "textareas",

width : "640",
	height : 200



});
</script>
<script type="text/javascript">
var counter = 0;
var counter1 = 0;
var counter2 = 0;
cc=[];
cc[counter] = 0;
var buck = "'";
$(document).ready(function(){
	$('#creation_date').attachDatepicker({
		yearRange: '2014:2015',
		firstDay: 1,
		minDate:  new Date(2012, 1 - 1, 1),
		defaultDate: 0,
		dateFormat:'yy-mm-dd',
		closeText:"<span id='dialog_close' href='#'></span>"
	});
    
    $('#creation_date').mask("9999-99-99");
    $('#creation_time').mask("99:99");
    
   
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
<h2>Вести новость</h2><br>

<c:if test = "${type == 'update'}"><c:set var="action" value="updatedCateg" /></c:if>
<c:if test = "${type == 'create' || type == 'createchild'}"><c:set var="action" value="${pageContext.request.contextPath}/admin/add/addedCateg" /></c:if>


<div id = "form">
<form:form method="post" action = "${pageContext.request.contextPath}/admin/admincontent/updatednews"  modelAttribute="news" enctype="multipart/form-data">

<div id = "sector">
<h3>Основные данные</h3>
<c:if test = "${result == 'success'}">
<div id = "success"><p>Новость успешно сохранена!</p></div>
</c:if>
	<ul>
	<c:if test = "${type == 'update'}">
		<li>
		<div id = "row">
			<div id = "col1"><form:label type = "hidden" path="id">
				Id новости:
			</form:label>
			</div>
			<div id = "col2"><form:input type = "hidden" path="id" /><form:errors path="id" cssClass="error" /></div>
		</div>
		</li>
	</c:if>
		<li>
		<div id = "row">
			<div id = "col1"><form:label path="title">
				Заголовок новости:
			</form:label></div>
			<div id = "col2"><form:input path="title" /><form:errors path="title" cssClass="error" /></div>
		</div>
		</li>
		
		<li>
		<div id = "row">
		
			<div id = "col1"><form:label  path="creation_date">
				Дата создания:
			</form:label></div>
			<div id = "col2">
			<form:input path="creation_date" /><form:errors path="creation_date" cssClass="error" />
			</div>
			
		</div>
		</li>
		<li>
		<div id = "row">
		<div id = "col1">
		<form:label  path="creation_time">
				Время создания:
			</form:label></div>
			<div id = "col2">
			<form:input path="creation_time" /><form:errors path="creation_time" cssClass="error" />
			</div>
		</div>
		</li>
		<li>
		<div id = "row">
		
			<div id = "col1"><form:label  path="category.id">
				Категория:
			</form:label></div>
			<div id = "col2">
			<form:input type = "hidden" id = "cat_id" onclick = 'load_goodchoise(this, "${pageContext.request.contextPath}/choiseCategory", "cat", "choisecategory")' path="category.id" />
			<form:input  id = "cat_name" onclick = 'load_goodchoise(this, "${pageContext.request.contextPath}/choiseCategory", "cat", "choisecategory")' path="category.name" />
			<form:errors path="category" cssClass="error" />
			</div>
		</div>
		</li>
		
		<li>
		<div id = "row">
		
			<div id = "col1"><form:label  path="category.id">
				Тип новостей:
			</form:label></div>
			<div id = "col2">
			<form:input type = "hidden" id = "newstype_id" path="type.id" />
			<form:input  id = "newstype_text" onclick = "load_entitychoise('${pageContext.request.contextPath}','newstype','Model.NewsType', -1)" path="type.name" />
			<form:errors path="type" cssClass="error" />
			</div>
		</div>
		</li>
		
		<li>
		<div id = "row">
			<div id = "col2"><form:checkbox path="istop" value="Топ новость" />Топ новость (попадает на стартовую) </div> 
		</div>
		</li>
		
		<li>
		<div id = "row">
			<div id = "col2"><label>Промо текст (отображается в общем списке новостей)</label></div>
			<div id = "col2"><form:textarea cols='40' rows='2' path="promotext" value="Промотекст" />
			<form:errors path="promotext" cssClass="error" /> </div> 
		</div>
		</li>
		
		<li>
		<div id = "row">
		
			<div id = "col1"><form:label  path="thumb">
				Аватарка:
			</form:label></div>
				
			
			<div id = "col2">
			<form:input type = "hidden" path = "thumb" id = "thumb"/>
				<c:choose><c:when test = "${!empty news.thumb}"><img src="<c:url value="/resources/images${news.thumb}" />" width="50" alt="${news.title}"  /></c:when><c:otherwise>Нет аватара   </c:otherwise></c:choose><form:input type = "file" id = "newthumb" text = "Загрузить новый аватар" path="newthumb"/>
			<form:errors path="thumb" cssClass="error" />
			</div>
			
			
		</div>
		</li>
		
		</div>		
		<div id = "sector">
		<h3 id = 'textpart' path = 'textpart' name='textpart'>Текст новости </h3>
		<form:input id = 'textpart.id' type="hidden" path = 'textpart.id' name = 'textpart'/>
		<form:textarea cols='40' rows='3' id = 'textpart.name' path = 'textpart.text' name = 'textpart'/>
		<form:errors path="textpart.text" cssClass="error" />
		
		<div id = "videos${i.index}">
	
		<script>counter = ${i.index + 1} </script>
		</div>
		
		
		<c:if test = "${!empty news.images}">
		<c:forEach items = "${news.images}" var = "image" varStatus = "j">
		<form:label id = 'image${j.index}.name' path = 'images[${j.index}]' name='images'> Картинка  ${j.index+1}: </form:label>
		<form:input id = 'images${j.index}.name' type="hidden" path = 'images[${j.index}].id' name = 'images'/>
		<form:input id = 'images${j.index}.name' path = 'images[${j.index}].url' name = 'images'/>
		<a href = "${pageContext.request.contextPath}/admin/adminnews/${news.category.id}/deletenewsimage/${news.id}/${image.id}">Удалить картинку</a>
		<div id = "videos${j.index}">
	
		<script>counter1 = ${j.index + 1} </script>
		</div>
		
		</c:forEach>
		
		
		</c:if>
		
		<!--  <a href = 'javascript:appendImage()'>Добавить картинку</a><br>-->
		
		<c:if test = "${!empty news.videos}">
		<c:forEach items = "${news.videos}" var = "video" varStatus = "k">
		<form:label id = 'video${k.index}.name' path = 'videos[${k.index}]' name='videos'> Сегмент параметров  ${k.index+1}: </form:label>
		<form:input id = 'videos${k.index}.name' type="hidden" path = 'videos[${k.index}].id' name = 'videos'/>
		<form:input id = 'videos${k.index}.name' path = 'videos[${k.index}].url' name = 'videos'/>
		<a href = "${pageContext.request.contextPath}/admin/adminnews/${news.category.id}/deletenewsvideo/${news.id}/${video.id}">Удалить сегмент параметров</a>
		<div id = "videos${k.index}">
	
		<script>counter2 = ${k.index + 1} </script>
		</div>
		
		</c:forEach>
		
		
		</c:if>
		
		<!--<a href = 'javascript:appendVideos()'>Добавить видео</a><br>-->
		
		<script>
		
		function appendTextPart(){
			var div;
			var id = '"'+'textpart'+counter+'"';
		//	cc[counter]=0;
			div = document.createElement("div");
			div.setAttribute("id", "textpart"+counter);
//			alert("textpart"+counter);
//			var idfld = '"'+'textpart'+counter+'"';
//			var sect = '"'+'choisecategory'+'"';
			div.innerHTML = "<label id = 'textpart"+counter+".name' name='textparts["+counter+"].text'> Сегмент текста "+(counter+1)+": </label><textarea cols='40' rows='3' id='textparts"+counter+".name' name='textparts["+counter+"].text' type='text'></textarea><a href = 'javascript:removeProperty(0, "+counter+", "+id+")'>Удалить сегмент текста</a>";
			
			counter++;
			
			document.getElementById("textparts").appendChild(div);
		}
		
		function appendImage(){
			var div;
			var id = '"'+'image'+counter1+'"';
	//		cc[counter]=0;
			div = document.createElement("div");
	//		var idfld = '"'+'image'+counter1+'"';
	//		var sect = '"'+'choisecategory'+'"';
			div.setAttribute("id", "image"+counter1);
			div.innerHTML = "<label id = 'images"+counter1+".name' name='newimages["+counter1+"].name'> Картинка "+(counter1+1)+": </label><input type = 'file' id='images"+counter1+".name' name='newimages["+counter1+"]' /><a href = 'javascript:removeProperty(0, "+counter1+", "+id+" )'>Удалить картинку</a>";
			
			counter1++;
			
			document.getElementById("textparts").appendChild(div);
		}
		
		function appendVideos(){
			var div;
			var id = '"'+'video'+counter2+'"';
			cc[counter]=0;
			div = document.createElement("div");
	//		var idfld = '"'+'video'+counter2+'"';
	//		var sect = '"'+'choisecategory'+'"';
			div.setAttribute("id", "video"+counter2);
			div.innerHTML = "<label id = 'videos"+counter2+".name' name='videos["+counter2+"].name'> Видео "+(counter2+1)+": </label><input id='videos"+counter2+".url' name='videos["+counter2+"].url' type='text'/><a href = 'javascript:removeProperty(0, "+counter2+", "+id+")'>Удалить видео</a>";
			
			counter2++;
			
			document.getElementById("textparts").appendChild(div);
		}
		
		
		
		function appendPropertySegment(){
			var div;
			var id = "propsement"+counter;
			cc[counter]=0;
			div = document.createElement("div");
			div.setAttribute("id", "propsegment"+counter);
			div.innerHTML = "<label id = 'propsegments"+counter+".name' name='propsegments["+counter+"].name'> Сегмент параметров "+(counter+1)+": </label><input id='propsegments"+counter+".name' name='propsegments["+counter+"].name' type='text'/><a href = 'javascript:appendProperty("+counter+")'>Добавить параметр в сегмент</a><a href = 'javascript:removeProperty(0, "+counter+")'>Удалить сегмент параметров</a>";
			
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
		
		function removeProperty(h, id, pre){
			
			var strid;
			if(h==0){strid = 'propsegment'+id;	counter--;}
			
			else{strid = 'prop'+id; cc[id]--;}
	
			document.getElementById(pre).remove();
		}
		
		function appendProperty(parid){
//			alert("propsegment"+parid);
			propnum = cc[parid];
			
			var div;
			
			div = document.createElement("div");
			div.setAttribute("id", "prop"+propnum);
			div.innerHTML = "<label id = 'props"+propnum+".name' name='props["+propnum+"].name'> Параметр "+(propnum+1)+": </label><input id='props"+propnum+".name' name='propsegments["+parid+"].properties["+propnum+"].name' type='text'/><a href = 'javascript:removeProperty("+parid+", "+propnum+")'>Удалить параметр</a>";
			cc[parid]++;
//			alert(div);
			document.getElementById("propsegment"+parid).appendChild(div);
			
		}
		
		</script>
		
		<li>
		<div id = "row">

			<div id = "col2"><input type="submit"
				value="Сохранить" /></td>
		</div>
		</li>
		
	</ul>
	</div>
</form:form>
</div>
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
<div id = "popup" class = "popupwindow">
<div class="close"><img width="30" height="30" src="/web-shop/resources/images/x-button-gray.png"></div>
<div id = "popupcontent"></div>
</div>

<div id = "popup1" class = "popupwindow"></div>

</body>
</html>