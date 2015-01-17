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
	<title>Configurations</title>
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
	var counter = 0;
	var countervid = 0;
	var counter1 = [];

	cc=[];
//	controllers[counter1] = 0;
//	cc1[controllers[counter1]] =0;
	function appendImage(){
		var div;
		
		div = document.createElement("div");
		div.setAttribute("id", "img"+counter);
		var imgid = '"img'+counter+'"';
		div.innerHTML = "<li><div class = 'addedphotosegment' id = 'row'>Поряд. №: <input style = 'min-width:30px !important; width:30px;' type = 'text' name = 'slider.images["+counter+"].priority' id = 'imgpriority"+counter+"'/><label id = 'images"+counter+".url' name='slider.images["+counter+"].newimage'> Выбрать: </label><input type = 'hidden' name = 'slider.images["+counter+"].url' id = 'imgurl"+counter+"'/><input onchange = 'setimgurl("+counter+")' type = 'file' id='images"+counter+".file' name='slider.images["+counter+"].newimage' type='text'/> Ссылка: <input type = 'text' name = 'slider.images["+counter+"].href' id = 'imghref"+counter+"'/> <br>Текст: <textarea name = 'slider.images["+counter+"].text' id = 'imgtext"+counter+"'></textarea>  <a href = 'javascript:removeProperty("+imgid+")'>Удалить картинку</a></div></li><br>";
		counter++;
		
		document.getElementById("parentimg").appendChild(div);
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
		
		$("#hasNews").on('change', function(){
			 //   	alert("weg");
			    	if($(this).is(":checked")){
			    		$(".newsnumb").show();
			    	}
			    	else{
			    		$(".newsnumb").hide();
			    	}
			    });
		
		$("#hasActions").on('change', function(){
			 //   	alert("weg");
			    	if($(this).is(":checked")){
			    		$(".actionsnumb").show();
			    	}
			    	else{
			    		$(".actionsnumb").hide();
			    	}
			    });

	});

	
	 
		
		
    </script>
</head>
<body>
 <jsp:include page="/WEB-INF/views/adminheader.jsp" />

<div class="mainwrapper"> 
<c:set var="action" value="${pageContext.request.contextPath}/admin/admincontent/updatedConfiguration" />

<div style = "overflow:hidden; width:900px; padding-left:4%;" id = "form">
<h2>Вести шаблон конфигурации магазина:</h2>
<c:if test = "${result == 'success'}">
<div id = "success"><p>Конфигурация успешно сохранена!</p></div>
</c:if>
<form:form method="post" action="${action}" modelAttribute="configuration" enctype="multipart/form-data">
	<ul>
	<div id = "sector">
	<h4>Основная информация:</h4>
	<c:if test = "${type == 'update'}">
	<form:input type = "hidden" path="id" />
	</c:if>
	
	
	<li>
		<div id = "row">
			<div id = "col1">
				<form:label path="name">
					Имя конфигурации
				</form:label>
			</div>
			<div id = "col2">
				<form:input path="name" /><form:errors path="name" cssClass="error" />
			</div>
		</div>
	</li>
	</div>
	<div id = "sector">
	<h4>Параметры главной страницы:</h4>
	

	<li>	
		<div id = "row">
			<div id = "col1">
				<form:label path="startpagetitle">
					Title главной страницы:
				</form:label>
			</div>
	
			<div id = "col2"><form:input path="startpagetitle"/><form:errors path="startpagetitle" cssClass="error" /></div>
		</div>
	</li>
	
	<li>
	<div id = "row">
			<div id = "col1">
				<form:label path="startpagemeta">
					Мета-теги для главной страницы:
				</form:label>
			</div>
	
			<div id = "col2"><form:textarea path="startpagemeta"/><form:errors path="startpagemeta" cssClass="error" /></div>
		</div>
	</li>
	
	<li>
	<div id = "row">
			<div id = "col1">
				<form:label style = "margin-right:15px;" path="hasNewsOnMain">
					Показывать новости 
				</form:label>
				<form:checkbox id = "hasNews" path="hasNewsOnMain"/>
				<form:errors path="hasNewsOnMain" cssClass="error" />
			</div>
			<div class = "newsnumb" <c:if test = "${configuration.hasNewsOnMain != true}"> style = "display:none;"</c:if> id = "col2">
				<form:label path="newscount">
					Количество новостей на главной
				</form:label>
				<form:input path="newscount" />
				<form:errors path="newscount" cssClass="error" />
			</div>
		</div>
	</li>
	
	<li>
	<div id = "row">
			<div id = "col1">
				<form:label style = "margin-right:15px;" path="hasActionsOnMain">
					Показывать акции 
				</form:label>
				<form:checkbox id = "hasActions" path="hasActionsOnMain"/>
				<form:errors path="hasActionsOnMain" cssClass="error" />
			</div>
			<div class = "actionsnumb" <c:if test = "${configuration.hasActionsOnMain != true}"> style = "display:none;"</c:if> id = "col2">
				<form:label path="actionscount">
					Количество акций на главной
				</form:label>
				<form:input path="actionscount" />
				<form:errors path="actionscount" cssClass="error" />
			</div>
		</div>
	</li>
	
		<li>
		<div id = "row">
			<div id = "col1">Показывать топ-товары </div> 
			<div id = "col2"><form:checkbox path="hasTopGoodsListOnMain" /><form:errors path="hasTopGoodsListOnMain" cssClass="error" /></div>
		</div> 	
		</li>
		
		<li>
		<div id = "row">
			<div id = "col1">Показывать товары дня и товары недели </div> 
			<div id = "col2"><form:checkbox path="hasDayAndWeekTopOnMain" /><form:errors path="hasDayAndWeekTopOnMain" cssClass="error" /></div>
		</div> 	
		</li>
		
	</div>
	
	<div id = "sector">
	<h4>Настройка слайдера:</h4>	
		<li>
			<div id = "col1"><form:checkbox path="hasSlider" />Показывать слайдер на главной <form:errors path="hasSlider" cssClass="error" /></div> 
		</li>
	
	<li>
	<div style = "margin-top:15px; margin-bottom:15px;" id = "row">
	
			<a role = "button" onclick = "load_entitychoise('${pageContext.request.contextPath}','producer','Model.Slider', -1)">Выбрать другой слайдер из списка созданных</a>
	</div>
		
		
	
	</li>
	
	
	<li>
	<div id = "row">
			
			
			<form:input type = "hidden" path = "slider.id"  id = "slider_id"/>
				Имя слайдера: <form:input id = "slider_text" path="slider.name" /> <form:errors path="slider.name" cssClass="error" />
				Путь к папке с фото (необяз.): <form:input id = "slider_folderpath" path="slider.folderpath" /> <form:errors path="slider.folderpath" cssClass="error" />
			
		</div>
	
	</li>
	
	


	
	<c:if test = "${!empty configuration.slider.images}">
	<c:forEach items = "${configuration.slider.images}" var ="image" varStatus = "i">
	<script>counter = "<c:out value = "${i.index}"/>";counter++;</script>
	
	
	<li>
	<div class = 'addedphotosegment' id = 'row'>
	Поряд. №: <form:input style = 'min-width:30px !important; width:30px;' type = 'text' path = 'slider.images[${i.index}].priority' id = 'imgpriority${i.index}'/><form:errors path="slider.images[${i.index}].priority" cssClass="error" />
	<img style = "margin-left:15px;"  src="<c:url value="/resources/images${image.url}" />" width="50" alt="${image.alt}"  />
	
	<form:input type = "hidden" path="slider.images[${i.index}].id" />
			<form:input type = "hidden" path="slider.images[${i.index}].url"/>
			<form:input type = "hidden" path="slider.images[${i.index}].alt"/>
	<form:input style = "margin-left:10px;" onchange = 'setimgurl(${i.index})' type = 'file' id='images${i.index}.file' path='slider.images[${i.index}].newimage' /> 
	Ссылка: <form:input type = "text" path="slider.images[${i.index}].href"/><form:errors path="slider.images[${i.index}].href" cssClass="error" /> <br>
	Текст: <form:textarea path = 'slider.images[${i.index}].text' id = 'imgtext${i.index}'></form:textarea>  
	<img style = "cursor:pointer;"  src = "<c:url value = "/resources/images/ico-add2buydel.gif"/>"  onclick = "javascript:location.href = '<c:url value='/admin/admincontent/deleteImageFromSlider'/>';"/></div>
	<form:errors path="slider.images[${i.index}]" cssClass="error" />
	</li>
	<br>
	
	
	
	
	
	</c:forEach>
	</c:if>
	
	<div id = "parentimg"></div>
		
		<li>
		<div id = "col1"><a href = "javascript:appendImage()">Добавить фото в слайдер</a></div>
		
		</li>
	
	</div>
	
	<div id = sector>
	<h4>Прочие настройки:</h4>
	
		<li>
		<div id = "row">
			<div id = "col1">Режим модерации комментариев </div> 
			<div id = "col2"><form:checkbox path="commentsModeration" /><form:errors path="commentsModeration" cssClass="error" /></div>
		</div> 	
		</li>
		
		<li>
		<div id = "row">
			<div id = "col1">Конфигурация активна </div> 
			<div id = "col2"><form:checkbox path="isactive" /><form:errors path="isactive" cssClass="error" /></div>
		</div> 	
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