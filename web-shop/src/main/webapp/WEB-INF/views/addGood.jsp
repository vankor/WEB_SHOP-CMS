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
	<title>Goods</title>
	<link rel="stylesheet" href="<c:url value="/resources/style1.css"/>"/>
	
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


<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.min.js" type="text/javascript"></script>
		<script type="text/javascript" src="http://js.mobilluck.com.ua/js/ur.js"></script>
		<script type="text/javascript">
(function (a, b){
	if (/android.+mobile|avantgo|bada\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\/|plucker|pocket|psp|symbian|treo|up\.(browser|link)|vodafone|wap|windows (ce|phone)|xda|xiino/i.test(a) || /1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\-(n|u)|c55\/|capi|ccwa|cdm\-|cell|chtm|cldc|cmd\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\-s|devi|dica|dmob|do(c|p)o|ds(12|\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\-|_)|g1 u|g560|gene|gf\-5|g\-mo|go(\.w|od)|gr(ad|un)|haie|hcit|hd\-(m|p|t)|hei\-|hi(pt|ta)|hp( i|ip)|hs\-c|ht(c(\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\-(20|go|ma)|i230|iac( |\-|\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\/)|klon|kpt |kwc\-|kyo(c|k)|le(no|xi)|lg( g|\/(k|l|u)|50|54|e\-|e\/|\-[a-w])|libw|lynx|m1\-w|m3ga|m50\/|ma(te|ui|xo)|mc(01|21|ca)|m\-cr|me(di|rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\-2|po(ck|rt|se)|prox|psio|pt\-g|qa\-a|qc(07|12|21|32|60|\-[2-7]|i\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\-|oo|p\-)|sdk\/|se(c(\-|0|1)|47|mc|nd|ri)|sgh\-|shar|sie(\-|m)|sk\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\-|v\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\-|tdg\-|tel(i|m)|tim\-|t\-mo|to(pl|sh)|ts(70|m\-|m3|m5)|tx\-9|up(\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|xda(\-|2|g)|yas\-|your|zeto|zte\-/i.test(a.substr(0, 4)))
		window.location = b
	})(navigator.userAgent || navigator.vendor || window.opera, 'http://m.mobilluck.com.ua/');
</script>
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
	var countervid = 0;
	var counter1 = [];

	cc=[];
//	cc[counter1] = 0;
//	cc1[cc[counter1]] =0;
	function appendImage(){
		var div;
		
		div = document.createElement("div");
		div.setAttribute("id", "img"+counter);
		var imgid = '"img'+counter+'"';
		div.innerHTML = "<label id = 'images"+counter+".url' name='newimages["+counter+"]'> Путь к картинке "+(counter+1)+": </label><input type = 'hidden' name = 'images["+counter+"].url' id = 'imgurl"+counter+"'/><input onchange = 'setimgurl("+counter+")' type = 'file' id='images"+counter+".file' name='newimages["+counter+"]' type='text'/><a href = 'javascript:removeProperty("+imgid+")'>Удалить картинку</a>";
		counter++;
		
		document.getElementById("parentimg").appendChild(div);
	}
	
	
	function appendVideo(){
		var div;
		
		div = document.createElement("div");
		div.setAttribute("id", "vid"+countervid);
		var vidid = '"vid'+countervid+'"';
		div.innerHTML = "<label id = 'videos"+countervid+".name' name='newimages["+countervid+"]'> Путь видео "+(countervid+1)+": </label><input type = 'hidden' name = 'videos["+countervid+"].url' id = 'vidurl"+countervid+"'/><input id='videos"+countervid+".url' name='newvideos["+countervid+"].url' type='text'/><input onchange = 'setvidurl("+countervid+")' type = 'file' id='videos"+countervid+".file' name='newvideos["+countervid+"]' /><a href = 'javascript:removeProperty("+vidid+")'>Удалить видео</a>";
		countervid++;
		
		document.getElementById("parentvid").appendChild(div);
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
	//	var strid = "prop"+id;
	//	alert(id);
		document.getElementById(id).remove();
		counter--;
	}
	
	function removeGuarantie(id){
		//	var strid = "prop"+id;
		//	alert(id);
			document.getElementById(id).remove();
			guarnum--;
		}
	
	function removeStatus(id){
		//	var strid = "prop"+id;
		//	alert(id);
			document.getElementById(id).remove();
			statnum--;
		}
	
	function setimgurl(id){
	
  	 	 alert( $('#imgurl'+id).val("setted"));
	}
	
	function setvidurl(id){
		
	  	  $('#vidurl'+id).val("setted");
	}
	
	
	$(document).ready(function(){
		$("#newthumb").on('change',function (){
	    	  var filename = $(this).val();
	    	  $('#thumb').val(filename);
	    	});

	});

	
	 
		
		
    </script>
</head>
<body>
 <jsp:include page="/WEB-INF/views/adminheader.jsp" />

<div class="mainwrapper"> 
 <c:if test = "${section == 'updategood'}"><c:set var="action" value="${pageContext.request.contextPath}/admin/admingood/${good.id}/updatedGood" /></c:if>
<c:if test = "${section == 'addgood'}"><c:set var="action" value="${pageContext.request.contextPath}/admin/admingood/-1/updatedGood" /></c:if>
<div style = "width:800px; padding-left:4%;" id = "form">
<h2>Вести товар:</h2>
<c:if test = "${result == 'success'}">
<div id = "success"><p>Товар успешно сохранен!</p></div>
</c:if>
<form:form method="post" action="${action}" modelAttribute="good" enctype="multipart/form-data">
	
	<div id = "sector">
	<h4>Основные данные:</h4>
	<c:if test = "${section == 'updategood'}">
	<form:input type = "hidden" path="id" />
	</c:if>
	<ul>
	<li>
	<div id = "row"><div id = "col1"><b>Категория товаров: </b></div><div id = "col2"><form:input type = "hidden"  path="category_id" /><b>${category.name}</b><form:errors path="category_id" cssClass="error" /></div></div>
	</li>
	<li>
		<div id = "row">
			<div id = "col1">
				<form:label path="name">
					Имя
				</form:label>
			</div>
			<div id = "col2">
				<form:input path="name" /><form:errors path="name" cssClass="error" />
			</div>
		</div>
	</li>
	<li>
	<div id = "row">
			<div id = "col1">
				<form:label path="description">
				Описание
			</form:label>
			</div>
			<div id = "col2">
				<form:textarea path="description" />
			</div>
		</div>
	
	</li>
	
	
	
	<li>
	<div id = "row">
			<div id = "col1">
			<form:label path="producer">
				Производитель
			</form:label>
			</div>
			<div id = "col2">
			<form:input type = "hidden" path = "producer.id"  id = "producer_id"/>
				<form:input id = "producer_text" path="producer.name" onclick = "load_entitychoise('${pageContext.request.contextPath}','producer','Model.Producer', -1)"/>
			</div>
		</div>
	
	</li>
	<li>
	<div id = "row">
			<div id = "col1">
				<form:label path="price">
					Цена
				</form:label>
			</div>
			<div id = "col2">
				<form:input path="price" /><form:errors path="price" cssClass="error" />
			</div>
		</div>
	</li>
	
		<li>
	<div id = "row">
			<div id = "col1">
				<form:label path="discount">Скидка:</form:label>
			</div>
			<div id = "col2">
				<form:input path="discount" />
				<form:errors path="discount" cssClass="error" />
			</div>
		</div>
	</li>
	
	<li>
	<div id = "row">
			<div id = "col1">
				<form:label path="oldprice">Старая цена:</form:label>
			</div>
			<div id = "col2">
				<form:input path="oldprice" />
				<form:errors path="oldprice" cssClass="error" />
			</div>
		</div>
	</li>
	
	<li>
	<div id = "row">
			<div id = "col1">
				<form:label path="bonusscores">Бонусных баллов за покупку:</form:label>
			</div>
			<div id = "col2">
				<form:input path="bonusscores" />
				<form:errors path="bonusscores" cssClass="error" />
			</div>
		</div>
	</li>
	
	<li>
	<div id = "row">
			<div id = "col1">
				<form:label path="thumb">
					Аватарка
				</form:label>
			</div>
			<div id = "col2">
			<form:input type = "hidden" path = "thumb" id = "thumb"/>
				<c:choose><c:when test = "${!empty good.thumb}"><img src="<c:url value="/resources/images${good.thumb}" />" width="50" alt="${good.name}"  /></c:when><c:otherwise>Нет аватара   </c:otherwise></c:choose><form:input type = "file" id = "newthumb" text = "Загрузить новый аватар" path="newthumb"/>
			<form:errors path="thumb" cssClass="error" />
			</div>
		</div>
	</li>
	
	<li>
	<div id = "row">
			<div id = "col1">
				<form:label path="state_name">Состояние товара</form:label>
			</div>
			<div id = "col2">
				<form:select path="state_name">
    				<form:options items="${states}" />
				</form:select>
				<form:errors path="state_name" cssClass="error" />
			</div>
		</div>
	</li>
	
	<li>
	<div id = "row">
			<div id = "col1">
				<form:label path="mindeliverydays">Минимальный срок доставки (дней):</form:label>
			</div>
			<div id = "col2">
				<form:input path="mindeliverydays" />
				<form:errors path="mindeliverydays" cssClass="error" />
			</div>
		</div>
	</li>
	
	<li>
	<div id = "row">
			<div id = "col1">
				<form:label path="maxdeliverydays">Максимальный срок доставки (дней):</form:label>
			</div>
			<div id = "col2">
				<form:input path="maxdeliverydays" />
				<form:errors path="maxdeliverydays" cssClass="error" />
			</div>
		</div>
	</li>
	
	<c:if test = "${!empty good.images}">
	<c:forEach items = "${good.images}" var ="image" varStatus = "i">
	
	<li>
	<div id = "row">
			<div id = "col1">
				<form:label path="name">
					Путь к картинке:
				</form:label>
			</div>
			<form:input type = "hidden" path="images[${i.index}].id" />
			<form:input type = "hidden" path="images[${i.index}].url"/>
			<div id = "col2">
				<img src="<c:url value="/resources/images${image.url}" />" width="50" alt="${good.name}"  />
			</div>
			<div id = "col3">
					<c:choose>
						<c:when test = "${section == 'updategood'}">
							<img style = "cursor:pointer;"  src = "<c:url value = "/resources/images/ico-add2buydel.gif"/>"  onclick = "javascript:location.href = '<c:url value='/admin/admingood/${good.id}/deleteImage/${image.id}'/>';"/></p><br>
						</c:when>
						<c:otherwise>
							<img style = "cursor:pointer;"  src = "<c:url value = "/resources/images/ico-add2buydel.gif"/>"  onclick = "$('#img${image.id}').remove();"/></p><br>
		
						</c:otherwise>
					</c:choose>
			<form:errors path="images[${i.index}].url" cssClass="error" />
			</div>
		</div>
	</li>
	
	
	
	</c:forEach>
	</c:if>
	
	
			<div id = "parentimg"></div>
		
		<c:if test = "${!empty good.videos}">
		<c:forEach items = "${good.videos}" var ="video" varStatus = "i">
		
		<li id = "vid${video.id}">
			<div id = "col1">
			<form:label path="videos[${i.index}].url">
				Путь к паке с фото:
			</form:label>
			</div>
			<form:input type = "hidden" path="videos[${i.index}].id" />
			<div id = "col2"><form:input path="videos[${i.index}].url"/></div>
			<div id = "col3">
			
			
			<c:choose>
		<c:when test = "${section == 'updategood'}">
		<img style = "cursor:pointer;"  src = "<c:url value = "/resources/images/ico-add2buydel.gif"/>"  onclick = "javascript:location.href = '<c:url value='/admin/admingood/${good.id}/deleteVideo/${video.id}'/>';"/></p><br>
		</c:when>
		<c:otherwise>
		<img style = "cursor:pointer;"  src = "<c:url value = "/resources/images/ico-add2buydel.gif"/>"  onclick = "$('#vid${video.id}').remove();"/></p><br>
		
		</c:otherwise>
		</c:choose>
			
			
	<!-- 		<a href = "${pageContext.request.contextPath}/admin/admingood/${good.id}/deleteImage/${image.id}">Удалить фото</a>  -->
			<form:errors path="videos[${i.index}].url" cssClass="error" />
			</div>
		</li>
	
		</c:forEach>
		
		</c:if>
		
		
		
		
		<li>
		<div id = "col1"><a href = "javascript:appendImage()">Добавить фото</a></div>
		<div><a href = "javascript:appendVideo()">Добавить видео</a></div>
		</li>
	
	</div>
	<div id = sector>
	<li>
	<div id = "row">
	<div id = "col2"><h3>Гарантии для товара</h3></div>
	</div>
	</li>
	
	
	<li>
	<div style = "margin-bottom:0;" id = "row">
			<div id = "col1">
				<form:label path="prodguarantie">Гарантия для обмена товара:</form:label>
		</div>
			
			<div id = "col2">
				<form:label style = "font-weight:700;" path="prodguarantie" id = "prodguarantie_text">
	
				${good.prodguarantie}
				</form:label>
				<c:if test = '${empty good.prodguarantie}'><c:set var = "displayprod" value = "display:none;"/></c:if>
				<form:input type = "hidden" id = "prodguarantie_id" path="prodguarantie.id" />
				<img id = "delprodguarantie" style = "${displayprod} cursor:pointer;"  src = "<c:url value = "/resources/images/ico-add2buydel.gif"/>"  onclick = "javascript:location.href = '<c:url value='/admin/admingood/${good.id}/deleteGuarantieValue/${prodguarantie.id}'/>';"/>
				</div>
			
			
				<form:errors path="prodguarantie" cssClass="error" />
		</div>
	</li>
	<li>
	<div id = "col2"><a role="button" onclick = "load_entitychoise('${pageContext.request.contextPath}','prodguarantie','Model.Guarantie', -1)">Выбрать гарантию от производителя</a></div>
	</li>
	
	<li>
	<div style = "margin-bottom:0;" id = "row">
			
				<div id = "col1">
				<form:label path="exchangeguarantie">Гарантия для обмена товара:</form:label>
				</div>
				<div id = "col2">
				<form:label style = "font-weight:700;" path="exchangeguarantie" id = "exchangeguarantie_text">
	
				${good.exchangeguarantie}
				</form:label>
				<c:if test = '${empty good.exchangeguarantie}'><c:set var = "displayex" value = "display:none;"/></c:if>
				<form:input type = "hidden" id = "exchangeguarantie_id" path="exchangeguarantie.id" />
				<img id = "delexchangeguarantie" style = "${displayex} cursor:pointer;"  src = "<c:url value = "/resources/images/ico-add2buydel.gif"/>"  onclick = "javascript:location.href = '<c:url value='/admin/admingood/${good.id}/deleteGuarantieValue/${guar.pk.guarantie.id}'/>';"/>
				</div>
				
				
				
				<form:errors path="exchangeguarantie" cssClass="error" />
			
		</div>
	</li>
	<li>
	<div id = "col2"><a role="button" onclick = "load_entitychoise('${pageContext.request.contextPath}','exchangeguarantie','Model.Guarantie', -1)">Выбрать гарантию для обмена</a></div>
	</li>
	

	<li>
	<div id = "col1">
				<label>Дополнительные гарантии от интернет-магазина:</label>
	</div>
	</li>
	
	<div id = "guaranties">
	<script>guarnum = 0;</script>
	
	
	<c:if test = "${!empty good.goodguaranties}">
	<c:forEach items = "${good.goodguaranties}" var ="guar" varStatus = "i">
	<script>guarnum = "<c:out value = "${i.index}"/>";</script>
	<li>
	
	<div id = "col2">
	<form:label path="goodguaranties[${i.index}].pk.guarantie">
	
				<b>${guar.pk.guarantie}</b>
	</form:label>
	<form:input type = "hidden" path="goodguaranties[${i.index}].pk.guarantie.id" />
	<form:label  style = "margin-left:20px;" path="goodguaranties[${i.index}].price">Цена гарантии:</form:label>
	<form:input style = "margin-left:5px; min-width:40px !important;width:40px;" type = "text" path="goodguaranties[${i.index}].price" />
	<form:label  style = "margin-left:20px;" path="goodguaranties[${i.index}].iscostless">Бесплатно:</form:label>
	<form:checkbox style = "margin-left:5px;" path="goodguaranties[${i.index}].iscostless" value="Бесплатно" />
	</div>
	<div id = "col3">
	<img style = "cursor:pointer;"  src = "<c:url value = "/resources/images/ico-add2buydel.gif"/>"  onclick = "javascript:location.href = '<c:url value='/admin/admingood/${good.id}/deleteGuarantieValue/${guar.pk.guarantie.id}'/>';"/>
	</div>
	</li>
	</c:forEach>
	</c:if>
	</div>
	<li>
	<div id = "col1"><a role="button" onclick = "load_entitychoise('${pageContext.request.contextPath}','guarantie','Model.Guarantie', -1)">Добавить гарантию товару</a></div>
	</li>
	<br>
	
	
	<script>statnum = 0;</script>
	<li>
	<div id = "row">
	<div id = "col2"><h3>Статусы для товара</h3></div>
	</div>
	</li>
	
	<li>
	<div id = "col2">
		<div id = "statuses">
		<c:if test = "${!empty good.statuses}">
		<c:forEach items = "${good.statuses}" var = "status" varStatus = "k">
		<script>statnum = "<c:out value = "${k.index}"/>";</script>
		<div id = "status_${k.index}" class = "propval" style = "background: transparent;">
		<form:input type = "hidden" name = "param${k.index}" path = "statuses[${k.index}].id"></form:input>
		<img style = "cursor:pointer;"  src = "${pageContext.request.contextPath}/resources/images${status.thumb}"/>
		<img style = "cursor:pointer;"  src = "<c:url value = "/resources/images/ico-add2buydel.gif"/>"  onclick = "javascript:location.href = '<c:url value='/admin/admingood/${good.id}/deleteStatusValue/${status.id}'/>';"/></p><br>
		
		
		</div>
		</c:forEach>
		</c:if>
		</div>
		</div>
	</li>
	
	<li>
	<div id = "col1"><a role="button" onclick = "load_entitychoise('${pageContext.request.contextPath}','status','Model.GoodStatus', -1)">Добавить статус товару</a></div>
	</li>
	
	

	</div>

	<div id = sector>
	<c:if test ="${section == 'updategood'}">
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
			<div id = "col1">
				<form:label path="page.meta">
					Заголовок h1:
				</form:label>
			</div>
	
			<div id = "col2"><form:input path="page.h1"/></div>
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
	
	</div>
	
	
	<div id = "parentimg"></div>
	
		<c:set var = "lval" value = "0"></c:set>
		<c:set var = "lprop" value = "0"></c:set>
		<c:set var = "lsegm" value = "0"></c:set>
		
		<c:if test = "${!empty good.segments}">
		
		<c:forEach items = "${good.segments}" var = "segment" varStatus = "i">
		
		<c:set var = "lsegm" value = "${i.index}"></c:set>
		<c:set var = "lval" value = "0"></c:set>
		<c:set var = "lprop" value = "0"></c:set>
		<script>counter1[${lsegm}]=[];</script>
		
		<div id = "sector">
		
		<h4>${segment.segment_name}:</h4><form:input type = "hidden" path = "segments[${i.index}].id"></form:input>
		
		<c:forEach items = "${segment.properties}" var = "propbean" varStatus = "j">
		<c:set var = "lprop" value = "${j.index}"></c:set>
		<c:set var = "lval" value = "0"></c:set>
		<script> counter1[${lsegm}][${lprop}]=0</script>
		<li>
		<form:input type = "hidden" name = "param${i.index}" path = "segments[${i.index}].properties[${j.index}].prop.id"></form:input>
		<div id = "col1">
		<label type = "" name = "param${i}">
		<c:out value="${propbean.prop.name}"/></label></div>
		
		<div id = "col2"><div class = "propvalue" id = "nvals_${lsegm}_${lprop}">
		<c:if test = "${!empty propbean.vals}">
		<c:forEach items = "${propbean.vals}" var = "value" varStatus = "k">
		<c:set var = "lval" value = "${k.index+1}"></c:set>
		<div id = "propval_${lsegm}_${lprop}_${lval}" class = "propval">
		<form:input type = "hidden" name = "param${i}" path = "segments[${i.index}].properties[${j.index}].vals[${k.index}].id"></form:input><p>${value.value}
		
		<c:choose>
		<c:when test = "${section == 'updategood'}">
		<img style = "cursor:pointer;"  src = "<c:url value = "/resources/images/ico-add2buydel.gif"/>"  onclick = "javascript:location.href = '<c:url value='/admin/admingood/${good.id}/deletePropValue/${value.id}'/>';"/></p><br>
		</c:when>
		<c:otherwise>
		<img style = "cursor:pointer;"  src = "<c:url value = "/resources/images/ico-add2buydel.gif"/>"  onclick = "javascript:removeProperty('propval_${lsegm}_${lprop}_${lval}')"/></p><br>
		
		</c:otherwise>
		</c:choose>
		
		</div>
		</c:forEach>
		</c:if>
		</div></div>
		<div id = "col3">
				<a role="button" onclick = 'load_propvalchoise(this, "${pageContext.request.contextPath}/addPropValue", "propvals", ${propbean.prop.id}, ${lval}, ${lprop}, ${lsegm})'>Добавить значение</a>
			</div>
			
		
		
		</li>
		
		</c:forEach>
	
	
		</div>
		
		
		
		
		</c:forEach>
		</c:if>
		
		<div id = "sector">
		<li>
			<div id = "col1"><form:checkbox path="isTopGood" value="Топ товар" />Топ товар </div> 
		</li>
		
		<li>
			<div id = "col1"><form:checkbox path="isSeasonGood" value="Товар сезона" />Товар сезона </div> 
		</li>
		</div>
		
		<div id = "sector">
		<li>
			<div id = "col1">
			<input type="submit"
				value="Сохранить изменения" /></div>
				
		</li>
		<li>
		<div id = "col3"><a role="button" onclick = "load_goodchoise(this, '${pageContext.request.contextPath}/choiseCategory', 'goodcopy','choisegood')">Копировать значения из товара</a></div>
		</li>
		</div>
		
	</ul>
	
	
	
	
	
	</div>
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