<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>Вести акцию</title>
<link rel="stylesheet" href="<c:url value="/resources/style1.css"/>"/>


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
	<link rel="stylesheet" href="<c:url value="/resources/style.css"/>"/>
	
	<script type="text/javascript" src="<c:url value="/resources/js/tinymce/tinymce.min.js" />" ></script>
<script type="text/javascript">
tinyMCE.init({
	
mode : "textareas",

width : "640",
	height : 400



});
</script>
	
<script type="text/javascript">
var counter = 0;
var counter1 =0;
cc=[];
cc[counter] = 0;
var buck = "'";
$(document).ready(function(){
    $('#add').click(function() {
        document.getElementById('openModal').style.display = 'block';
        location.href='#openModal';

        $('#openModal').fadeIn(30000);
    });
    
    
    
    $('#enddate').mask("9999-99-99");
    
    $('#endtime').mask("99:99");
    
    $("#enddate").attachDatepicker({
		yearRange: '2014:2015',
		firstDay: 1,
		minDate:  1,
		defaultDate: 0,
		dateFormat:'yy-mm-dd',
		closeText:"<span id='dialog_close' href='#'></span>"
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

<c:if test = "${type == 'update'}"><c:set var="act" value="${pageContext.request.contextPath}/admin/adminactions/updatedaction" /></c:if>
<c:if test = "${type == 'create'}"><c:set var="act" value="${pageContext.request.contextPath}/admin/adminactions/updatedaction" /></c:if>


<div id = "form" class = "actionform" style = "width:900px; padding-left:4%;">
<form:form method="post" action = "${act}"  modelAttribute="action" enctype="multipart/form-data">
	<h3>Вести акцию</h3>
	<c:if test = "${result == 'success'}">
		<div id = "success"><p>Акция успешно сохранена!</p></div>
	</c:if>
	<div id = "sector">
	<h3>Основные данные</h3>
	<ul>
	<c:if test = "${type == 'update'}">
				
			<form:input type = "hidden" path="id" />
		
	</c:if>
		<li>
			<div id = "col1"><form:label path="name">
				Название акции:
			</form:label></div>
			<div id = "col2"><form:input path="name" /><form:errors path="name" cssClass="error" /></div>
		</li>
		
		<li>
			<div id = "col1"><form:label path="text">
				Текст акции:
			</form:label></div>
			<div id = "col2"><form:textarea path="text" cols = "18" rows = "12"/><form:errors path="text" cssClass="error" /></div>
		</li>
		
		<li>
			<div id = "col1"><form:label path="enddate">
				Дата окончания акции:
			</form:label></div>
			<div id = "col2"><form:input path="enddate" /><form:errors path="enddate" cssClass="error" /></div>
		</li>	
		<li>
			<div id = "col1"><form:label path="endtime">
				Время окончания акции:
			</form:label></div>
			<div id = "col2"><form:input path="endtime" /><form:errors path="endtime" cssClass="error" /></div>
		</li>	
			
		<li>
		
			<div id = "col1"><form:label  path="thumb">
				Промо-картинка:
			</form:label></div>
			<div id = "col2">
			<form:input type = "hidden" path = "thumb" id = "thumb"/>
				<c:choose><c:when test = "${!empty action.thumb}"><img src="<c:url value="/resources/images${action.thumb}" />" width="50" alt="${action.name}"  /></c:when><c:otherwise>Нет аватара   </c:otherwise></c:choose><form:input type = "file" id = "newthumb" text = "Загрузить новый аватар" path="newthumb"/>
			<form:errors path="thumb" cssClass="error" />
			</div>
		</li>
		
		<li>
			<div id = "col1"><form:label path="isactive">
				Акция активна:
			</form:label></div>
			<div id = "col2"><form:checkbox path="isactive" /></div>
		</li>	
		
		<li>
			<div id = "col1"><form:label path="istop">
				Топ акция (попадает на стартувую страницу):
			</form:label></div>
			<div id = "col2"><form:checkbox path="istop" /></div>
		</li>	
				
		<c:if test = "${!empty action.giftgoods}">
		<c:forEach items = "${action.giftgoods}" var = "gift" varStatus = "i">
		<li>
		<div id = "col1">
		<form:label id = 'gift${i.index}.name' path = 'giftgoods[${i.index}]' name='giftname'> Товар-подарок  ${i.index+1}: </form:label>
		</div>
		<form:input id='ngift${i.index}_id' onclick = "load_goodchoise(this, '${pageContext.request.contextPath}/choiseCategory', 'ngift${i.index}','choisegood')" type="hidden" path = 'giftgoods[${i.index}].id' name = 'giftid'/>
		<div id = "col2">
		<form:input id='ngift${i.index}_name' onclick = 'load_goodchoise(this, "${pageContext.request.contextPath}/choiseCategory", "ngift${i.index}", "choisegood")' path = 'giftgoods[${i.index}].name' name = 'propsegments'/>
		<form:errors path="giftgoods[${i.index}]" cssClass="error" />
		</div>
		<div id = "col3">
		<a href = "${pageContext.request.contextPath}/admin/adminactions/${action.id}/deletegift/${gift.id}">Удалить товар-подарок</a>
		</div>
		</li>
		<div id = "gift${i.index}">
	
		<script>counter = ${i.index + 1} </script>
		</div>
		
		</c:forEach>
		</c:if>
		<li>
		<div id = "col1">
		<div id = "newgifts"></div>
		<a href = 'javascript:appendGift(${i.index})'>Добавить товар-подарок</a><br>
		</div>
		</li>
		<c:if test = "${!empty action.actiongoods}">
		<c:forEach items = "${action.actiongoods}" var = "actiongood" varStatus = "i">
		<li>
		<div id = "col1">
		<form:label id = 'actiongood${i.index}.name' path = 'actiongoods[${i.index}]' name='actiongoodname'> Подакционный товар  ${i.index+1}: </form:label>
		</div>
		
		<form:input id='actiongood${i.index}_id' onclick = "load_goodchoise(this, '${pageContext.request.contextPath}/choiseCategory', 'actiongood${i.index}', 'choisegood')" type="hidden" path = 'actiongoods[${i.index}].id' name = 'actiongoodid'/>
		<div id = "col2">
		<form:input id='actiongood${i.index}_name' onclick = 'load_goodchoise(this, "${pageContext.request.contextPath}/choiseCategory", "actiongood${i.index}", "choisegood")' path = 'actiongoods[${i.index}].name' name = 'propsegments'/>
		<form:errors path="actiongood[${i.index}]" cssClass="error" />
		</div>
		<div id = "col3">
		<a href = "${pageContext.request.contextPath}/admin/adminactions/${action.id}/deleteactiongood/${actiongood.id}">Удалить подакционный товар</a>
		</div>
		</li>
		<div id = "actiongood${i.index}">
	
		<script>counter1 = ${i.index + 1} </script>
		</div>
		
		</c:forEach>
		</c:if>
		<li>
		<div id = "col1">
		<div id = "newactiongood"></div>
		<a href = 'javascript:appendActionGood(${i.index})'>Добавить подакционный товар</a>
		</div>
		</li>
		<script>
		function appendGift(){
			var div;
			var url = '"'+"<c:out value="${pageContext.request.contextPath}"/>" +"/choiseCategory"+'"';
			var id = "ngift"+counter;
		//	cc[counter]=0;
			div = document.createElement("div");
			var idfld = '"'+'ngift'+counter+'"';
			var sect = '"'+'choisegood'+'"';
			div.setAttribute("id", idfld);
			div.innerHTML = "<label id = 'ngift"+counter+".name' name='giftgoods["+counter+"].name'> Товар-подарок "+(counter+1)+": </label><input id='ngift"+counter+"_id' value = '' name='giftgoods["+counter+"].id' type='hidden'/><input id='ngift"+counter+"_name' onclick = 'load_goodchoise(this, "+url+", "+idfld+", "+sect+")' name='giftgoods["+counter+"].name' type='text'/><a href = 'javascript:removeGift(0, "+counter+")'>Удалить товар-подарок</a>";
			
			counter++;
			
			document.getElementById("newgifts").appendChild(div);
		}
		
		function appendActionGood(){
			var div;
			var url = '"'+'<c:out value="${pageContext.request.contextPath}"/>' +'/choiseCategory'+'"';
			var id = "newactiongood"+counter1;
		//	cc[counter]=0;
			div = document.createElement("div");
			var idfld = '"'+'actiongood'+counter1+'"';
			var sect = '"'+'choisegood'+'"';
			div.setAttribute("id", idfld);
			
			div.innerHTML = "<label id = 'actiongoodl"+counter1+".name' name='actiongoods["+counter1+"].name'> Подакционный товар "+(counter1+1)+": </label><input id='actiongood"+counter1+"_id' onclick = 'load_goodchoise(this, "+url+", "+idfld+", "+sect+")' value = '' name='actiongoods["+counter1+"].id' type='hidden'/><input id='actiongood"+counter1+"_name' onclick = 'load_goodchoise(this, "+url+", "+idfld+", "+sect+")' value = '' name='actiongoods["+counter1+"].name' type='text'/><a href = 'javascript:removeActionGood(0, "+counter1+")'>Удалить подакционный товар</a>";
			
			counter1++;
			
			document.getElementById("newactiongood").appendChild(div);
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
		
		function removeGift(h, id){
			
			var strid;
			if(h==0){strid = 'actiongood'+id;	counter--;}
			
			else{strid = 'prop'+id; cc[id]--;}
	
			document.getElementById(strid).remove();
		}
		
		function removeActionGood(h, id){
			
			var strid;
			if(h==0){strid = 'ngift'+id;	counter1--;}
			
			else{strid = 'prop'+id; cc[id]--;}
	
			document.getElementById(strid).remove();
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

			<div id = "col1" colspan="2"><input type="submit"
				value="Сохранить акцию" /></div>
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
	
	<div id = "popup" class = "popupwindow">
<div class="close"><img width="30" height="30" src="/web-shop/resources/images/x-button-gray.png"></div>
<div id = "popupcontent"></div>
</div>

</body>
</html>