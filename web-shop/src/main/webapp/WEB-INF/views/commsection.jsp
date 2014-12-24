<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<c:if test="${section != 'bucket' && section != 'choisecategory'}">
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
	</c:if>
<script>
var url = "<c:out value="${pageContext.request.contextPath}"/>";
</script>
</head>
<body>

<c:if test="${section == 'add'}">


<div>
                      <c:forEach items="${comList}" var="comm">
					    <div style="clear: left; margin-top: 20px; width: 650px">
                            <div style="font-size: 14px; font-weight: bold; line-height: 16px; margin-bottom: 5px;">
    <p><c:choose><c:when test="${!empty comm.author.name}">${comm.author.name}</c:when><c:otherwise><c:choose><c:when test="${!empty comm.authorname}">${comm.authorname}</c:when><c:otherwise>Гость</c:otherwise></c:choose></c:otherwise></c:choose> <span style="font-size: 11px; font-weight: normal; color: #6d6e71;">, <fmt:formatDate value = "${comm.datetime}" pattern = "yyyy-MM-dd hh:mm"></fmt:formatDate></span></p>
                                <div>
              <!--                       <div style="clear: right; float: right; font-size: 12px; color: #6c6c6c; font-weight: normal; padding-top: 5px;">9 голосов</div> --> 
                                    <span style="font-size: 12px; font-weight: normal; color: #404041;">Оценка товара: <img src="<c:url value = "/resources/images/good/img-rate${comm.note}.gif"/>" alt="5" width="96" height="16" style="padding-left: 10px; padding-right: 10px;" /> Отлично</span>
                                </div>
    						</div>
	       					<div style="background-color: #ebf1f5; clear: left; padding: 15px;">
	       						<div>
                                    <div class="resp_all">
                                        <div id="ro283109s">
    		  	       					    ${comm.text}
                                        </div>
                                        <div style="display: none;" id="ro283109l">
                                            ${comm.text} 
                                        </div>
                                    </div>
                                    <c:if test = "${!empty comm.plus}">
                                            <div class="resp_plus">
                                                <div id='rp283109s'>
                                               ${comm.plus} 
                                                </div>
                                                <div style='display: none;' id='rp283109l'>
                                               ${comm.plus} 
                                                </div>
                                            </div>
                                     </c:if>
                                     <c:if test = "${!empty comm.minus}">
                                            <div class="resp_minus">
                                                <div id='rm283109s'>
                                                ${comm.minus}
                                                </div>
                                                <div style='display: none;' id='rm283109l'>
                                                ${comm.minus} 
                                                </div>
                                            </div> 
                                     </c:if>
                                            
                                </div>
                            </div>
                            <div style="clear: right; float: right; margin-top: 3px;">Отзыв полезен? <a title="Отзыв полезен" href="" id="respraiting" val="1" rid="283109" class="jlresp" style="color: green;">Да</a> (<span id="pl283109">5</span>) / <a  title="Отзыв не полезен" href="" class="jlresp" id="respraiting" val="2" rid="283109" style="color: red;">Нет</a> (<span id="mn283109">4</span>)</div>
                            <div style="clear: left; margin-top: 3px;"><a href="" id = "answerlink${comm.id}" onclick = "callanswer(${comm.id},${good.id});return false" rid="283109" class="answerlink">Ответить</a></div>
                            <div style="display: block;" id = "answers">
                            <div id = "addanswer${comm.id}"></div>
                                <c:forEach items="${comm.chieldcomms}" var="answer">
                                <div style="margin: 10px 0px 10px 104px;">
                                
                                    <p style="font-size: 14px; font-weight: bold; line-height: 16px; margin-bottom: 5px;"><c:choose><c:when test="${!empty answer.author.name}">${answer.author.name}</c:when><c:otherwise>Гость</c:otherwise></c:choose><span style="font-size: 11px; font-weight: normal; color: #6d6e71;">, <fmt:formatDate value = "${answer.datetime}" pattern = "yyyy-MM-dd hh:mm"></fmt:formatDate></span></p>
	       		                    
	       		                    <div style="background-color: #ebf1f5; clear: left; padding: 15px;">
                                        ${answer.text}
                                    </div>
                                </div>
                                </c:forEach>
                                </div>
                            <div id="answers${comm.id}" style="display: none; padding: 20px 0px 10px 100px;"></div>
                            <div id="answer283109" class="answer3" style="display: none; padding: 20px 0px 10px 100px;"></div>

                        </div>
                        </c:forEach>
					  </div>


</c:if>




<c:if test="${section == 'uinfo'}">
<div style="color: ${color};"><strong>${text}</strong></div>
</c:if>

<c:if test="${section == 'upass'}">
<div id = "passresult" style="color: ${color};"><strong>${text}</strong></div>
</c:if>



<c:if test="${section == 'loadnumberform'}">
<script>

function add_to_base(mode, ses, numbid)
{
	var url = "<c:out value="${pageContext.request.contextPath}"/>" +"/cabinet/updateNumber";
    var numb   = $("#clienphone_f").attr('value');
    var numbtype = $("#ophone_type").attr('value');
  
//    alert(mode +"  "+ ses+"  "+  numbid);
//      alert(cmd='+mode+'&SesID='+ses+'&name='+n+'&type='+t+'&town='+tu+'&type_s='+ts+'&street='+s+'&house='+h+'&flor='+f+'&room='+r+'&code='+code+'&maine='+maine);
	$.ajax({
        type: "POST",
        url: url,
        data:{"cmd":mode, "sessid":ses, "numb":numb,"numb_id":numbid, "numb_type":numbtype}, 
        dataType: "text",
        success: function(data){
      //  	alert(data);
        	$("#popupbg").hide();
    		$("#dialog_wrapper").hide();
        	$("#telefonUR").html(data);
        }
    });

}


flggs();
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
	



$(".mobile_f .selectbox li").live('click', function () {
		for (var i in flags)
		{
			temp=$(this).html().replace(i,flags[i]);
			$(this).html(temp);
		}
		flggs();
	});

	$.mask.definitions['2'] = "[0-2]";
	$.mask.definitions['6'] = "[0-5]";
    $("#time").mask("29:69");

	$(".clientphone_f").mask("+38 (099) 999-99-99");
	$(".mobile_f select").live('change', function () {
		var mask='+38 (099) 999-99-99';
		var sel=$(this).val();
		if($(this).val()=='+380'){mask='+38 (099) 999-99-99';}
		if($(this).val()=='+7'){mask='+7 (999) 999-99-99';}
		if($(this).val()=='+375'){mask='+375 (99) 999-99-99';}
		if($(this).val()=='+373'){mask='+373 (99) 999-99-99';}
		$(this).parent().find('input').eq(0).mask(mask);
	});
	
	


</script>
			<c:if test="${!empty number}">
			<c:set var="numb" value="${number.numb}"></c:set>
			</c:if>
            <div style="margin-bottom: 15px;"><strong>Добавить номер телефона</strong></div>
            <table cellpadding="0" cellspacing="0">
            <tbody><tr class = "mobile_f">
				
                <td>
						<div id="phones">
									
									
									<div>
											<select class="phone_f" name="clientphone_code[]">
												<option value="+380" >UA</option>
												<option value="+7">RU</option>
												<option value="+375">BEL</option>
												<option value="+373">MOL</option>
											</select>
											<c:choose>
									<c:when test="${!empty number}">
                   						 <input id = "clienphone_f" type="text" class="clientphone_f" name="clientphone_number[]" value="${number.numb}">
                    				</c:when>
										<c:otherwise>
											<input id = "clienphone_f" type="text" class="clientphone_f" name="clientphone_number[]" value="">		
                    					</c:otherwise>
											</c:choose>	
									</div>
									
				</div>
			</td>
			</tr>
			
			<tr>
			<td>
			<div id="type_phone">
					 <select name="type_phone" id="ophone_type" style="font-size: 14px; height: 20px; position: absolute; top: -9999px;">
                        <option value="1">Мобильный</option>
                        <option value="2">Стационарный</option>
                    </select>
					</div>	
			</td>
			</tr>	
			
           
			<tr><td colspan="3" style="padding-top:10px"><input id="maine" name="maine" value="1" type="checkbox"> главный телефон</td></tr>
            </tbody></table>
			<c:choose>
			<c:when test="${!empty number}">
			
            <div id="gotovoUR" onclick="add_to_base('update_number','${sessid}', ${number.id});"></div>
            
            </c:when>
            <c:otherwise>
            <div id="gotovoUR" onclick="add_to_base('add_number','${sessid}', -1);"></div>
            </c:otherwise>
			</c:choose>
	<script>
	
	$("#ophone_type").selectbox();
	$(".phone_f").selectbox();
	</script>
</c:if>

<c:if test="${section == 'choisecategory'}">
<script src="<c:url value="/resources/js/myscrpits.js" />"></script>
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
<h4>Выберите категорию:</h4>
<div class="catlist">
<div id = "root">
<c:set var = "level" value = "1"/>
<c:forEach items="${catList}" var="category" varStatus = "k">
<a role="button" onclick = "return openLevel(${category.id},${level}, ${category.isFinalCategory})">${category.name}</a>&nbsp<a role="button" onclick = "return chsGood(${category.id}, '${idfld}','${pageContext.request.contextPath}', '${section}')">Выбрать</a><br>

<div class = "sublevels" id = "level${level}${category.id}" style = "padding-left:5%; display:none;">
<c:forEach items="${category.subcategs}" var="subcat1" varStatus = "i">
<c:set var="level" value="${level+1}"/>
<a role="button" onclick = "return openLevel(${subcat1.id},${level}, ${subcat1.isFinalCategory})">${subcat1.name}</a>&nbsp<a role="button" onclick = "return chsGood(${subcat1.id}, '${idfld}','${pageContext.request.contextPath}', '${section}')">Выбрать</a><br>

<div class = "sublevels" id = "level${level}${subcat1.id}" style = "padding-left:5%; display:none;">
<c:forEach items="${subcat1.subcategs}" var="subcat2" varStatus = "j">
<c:set var="level" value="${level+1}"/>
<a role="button" onclick = "return openLevel(${subcat2.id},${level}, ${subcat2.isFinalCategory})">${subcat2.name}</a>&nbsp<a role="button" onclick = "return chsGood(${subcat2.id}, '${idfld}','${pageContext.request.contextPath}', '${section}')">Выбрать</a><br>

<div class = "sublevels" id = "level${level}${subcat2.id}" style = "padding-left:5%; display:none;">
<c:forEach items="${subcat2.subcategs}" var="subcat3" varStatus = "l">
<c:set var="level" value="${level+1}"/>
<a role="button" onclick = "return openLevel(${subcat3.id},${level}, ${subcat3.isFinalCategory})">${subcat3.name}</a>&nbsp<a role="button" onclick = "return chsGood(${subcat3.id}, '${idfld}','${pageContext.request.contextPath}', '${section}')">Выбрать</a><br>
<div class = "sublevels" id = "level${level}${subcat3.id}" style = "padding-left:5%; display:none;">
<c:forEach items="${subcat3.subcategs}" var="subcat4" varStatus = "i">
<a role="button" onclick = "return chsGood(${subcat4.id}, '${idfld}','${pageContext.request.contextPath}', '${section}')">${subcat4.name}</a><br>
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



<c:if test="${section == 'propvals'}">
<script>
count = 1;
var lsegm = parseInt("<c:out value="${lsegm}"/>");
var lprop = parseInt("<c:out value="${lprop}"/>");
//alert(lsegm+"   "+lprop);
//alert(counter1[lsegm][lprop]);
var nmb = counter1[lsegm][lprop];
var lval = parseInt("<c:out value="${lval}"/>")+nmb;


function saveValues(id, type){
	
	
	var url = "<c:out value="${pageContext.request.contextPath}"/>";
	var i=1;
	var parid = 'nval_'+lsegm+'_'+lprop;
	$("#popupbg").hide();
	$("#dialog_wrapper").hide();
	
	if(type == 1 &&  id >=0){
		

		div = document.createElement("div");
		
		div.setAttribute("id", 'newval_'+lsegm+'_'+lprop+'_'+nmb+'.value');
		div.setAttribute("class", "propval");
		var val = $("#vall"+i).attr("value");
		
		$.ajax({
	        type: "POST",
	        url: url + "/choisePropValue",
	        dataType: 'json',
	        data: {"id":id},
	        success: function(vv){
	     //   	alert(good);
	        		$("#popupbg").hide();
	    		$("#dialog_wrapper").hide();
	    		var id = '"'+'newval_'+lsegm+'_'+lprop+'_'+nmb+'.value'+'"';
	    //		alert('newval_'+lsegm+'_'+lprop+'_'+counter1[lsegm][lprop]+'.value');
	    //		alert(lval);
	    		div.innerHTML = "<input type = 'hidden' value = '"+vv.id+"' id='newval"+i+".value' name='segments["+lsegm+"].properties["+lprop+"].vals["+lval+"].id' type='text'/><p>"+vv.value+"<img style = 'cursor:pointer;' src = '"+url+"/resources/images/ico-add2buydel.gif' onclick = 'javascript:removeProperty("+id+")'/></p><br>";
	    		document.getElementById('nvals_'+lsegm+'_'+lprop).appendChild(div);
	    		counter1[lsegm][lprop]++;
	    		lval++;
	    		},      
	    	    error : function(xhr, status, error){
	                alert("Error!" + xhr.status);
	    	    }
	    });
		
		
	}
	
	else{
	
	for(i = 1; i<count; i++){
		div = document.createElement("div");
		var id = '"'+'newval_'+lsegm+'_'+lprop+'_'+nmb+'.value'+'"';
		div.setAttribute("id", 'newval_'+lsegm+'_'+lprop+'_'+nmb+'.value');
		div.setAttribute("class", "propval");
		var val = $("#vall"+i).attr("value");
		div.innerHTML = "<input type = 'hidden' value = '"+val+"' id='newval"+i+".value' name='segments["+lsegm+"].properties["+lprop+"].vals["+lval+"].value' type='text'/><p>"+val+"<img style = 'cursor:pointer;' src = '"+url+"/resources/images/ico-add2buydel.gif' onclick = 'javascript:removeProperty("+id+")'/></p><br>";
//		alert("<input type = 'hidden' value = '"+val+"' id='newval"+i+".value' name='segments["+lsegm+"].properties["+lprop+"].vals["+lval+"].value' type='text'/><p>"+val+"<img style = 'cursor:pointer;' src = '"+url+"/resources/images/ico-add2buydel.gif' onclick = 'javascript:removeProperty("+id+")'/></p><br>");
		
			document.getElementById('nvals_'+lsegm+'_'+lprop).appendChild(div);
		
		
			counter1[lsegm][lprop]++;
		lval++;
	}
	}
	
}

function addValue(){
//	$("#").html();

	
	if(count == 1){
		 $("#button").html("<button id = 'button' value = 'Добавить значения' onclick = 'saveValues(-1,0)'>Добавить значения</button>");
	}

	var div;
	var id = '"'+'val'+count+'"';
//	cc[counter]=0;
	div = document.createElement("div");
//		var idfld = '"'+'video'+counter2+'"';
//		var sect = '"'+'choisecategory'+'"';
	div.setAttribute("id", "val"+count);
	div.innerHTML = "<label id = 'val"+count+".name' name='vals["+count+"].value'> Новое значение для свойства: </label><input id='vall"+count+"' name='val"+count+".value' value='' type='text'></input><a href = 'javascript:removeProperty(0, "+count+", "+id+")'>Удалить</a>";
	
	count++;
	
	document.getElementById("newvals").appendChild(div);
	
	
}
</script>
<div class="catlist">
<div id = "root">
<c:forEach items="${propvals}" var="value" varStatus = "k">
<a role = "button"" onclick = "return saveValues(${value.id},1)">${value.value}</a><br>
</c:forEach>
<br>
<a role = "button" onclick = "return addValue()">Добавить новое значение свойства</a>
</div>
</div>
<div id = "newvals"></div>
<div id = "button"></div>
</c:if>
<c:if test="${section == 'choisegood' || section == 'choisegooditem'}">

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
<h4>Выберите товар (выбирать категорию нельзя):</h4>
<div class="catlist">
<div id = "root">
<c:set var = "level" value = "1"/>
<c:forEach items="${catList}" var="category" varStatus = "k">
<a role="button" onclick = "return openLevel(${category.id},${level})">${category.name}</a><br>

<div class = "sublevels" id = "level${level}${category.id}" style = "padding-left:5%; display:none;">
<c:forEach items="${category.subcategs}" var="subcat1" varStatus = "i">
<c:set var="level" value="${level+1}"/>
<a role="button" onclick = "return openLevel(${subcat1.id},${level}, ${subcat1.isFinalCategory})">${subcat1.name}</a><br>
<c:choose>
<c:when test = "${subcat1.isFinalCategory=='true'}">
<div class = "goods" id = "level${level}${subcat1.id}" style = "padding-left:5%; display:none;">
<c:forEach items="${subcat1.goods}" var="good" varStatus = "i">
<a role="button" onclick = "return chsGood(${good.id},'${idfld}','${pageContext.request.contextPath}', '${section}')">${good.name}</a><br>
</c:forEach>
</div>
</c:when>
<c:otherwise>
<div class = "sublevels" id = "level${level}${subcat1.id}" style = "padding-left:5%; display:none;">
<c:forEach items="${subcat1.subcategs}" var="subcat2" varStatus = "j">
<c:set var="level" value="${level+1}"/>
<a role="button" onclick = "return openLevel(${subcat2.id},${level}, ${subcat2.isFinalCategory})">${subcat2.name}</a><br>
<c:choose>
<c:when test = "${subcat2.isFinalCategory=='true'}">
<div class = "sublevels" id = "level${level}${subcat2.id}" style = "padding-left:5%; display:none;">
<c:forEach items="${subcat2.goods}" var="good" varStatus = "i">
<a role="button" onclick = "return chsGood(${good.id}, '${idfld}','${pageContext.request.contextPath}', '${section}')">${good.name}</a><br>
</c:forEach>
</div>
</c:when>
<c:otherwise>
<div class = "sublevels" id = "level${level}${subcat2.id}" style = "padding-left:5%; display:none;">
<c:forEach items="${subcat2.subcategs}" var="subcat3" varStatus = "l">
<c:set var="level" value="${level+1}"/>
<a role="button" onclick = "return openLevel(${subcat3.id},${level}, ${subcat3.isFinalCategory})">${subcat3.name}</a><br>
<div class = "sublevels" id = "level${level}${subcat3.id}" style = "padding-left:5%; display:none;">
<c:forEach items="${subcat3.goods}" var="good" varStatus = "i">
<a role="button" onclick = "return chsGood(${good.id}, '${idfld}','${pageContext.request.contextPath}', '${section}')">${good.name}</a><br>
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



<c:if test="${section == 'remcompare'}">
<ul id="mgccarousel" class="jcarousel-skin-mgcctango"><li>
				<table class="mgctable" cellpadding="0" cellspacing="0">
				
			<tr>
			<td class="mgctleft"></td>
			<c:forEach items = "${goods}" var = "good">
			<td class="mgctinside">
					<p class="mgcti_delete"><a onclick = "remCompareGood(${good.id},${catid},'${pageContext.request.contextPath}/${catid}/comparegoods','remcompare')" role="button"><span>Удалить</span></a></p>
					<div class="ccitem2" style="width: 200px;">
						<div class="cci2image">
							<table>
								<tr>
									<td><a href="${pageContext.request.contextPath}/good/${good.id}"><img src="<c:url value="/resources/images${good.thumb}" />" width="95" height="56" alt="${good.thumb}"  /></a></td>
								</tr>
							</table>
						</div>
						<div class="cci2info">
							<p class="cci2_mdl" style="text-align: center;"><a href="${pageContext.request.contextPath}/good/${good.id}">${good.name}</a></p>
							<table class="w100">
								<tr>
									<td>
										
										<p class="mgcti_price" style="font-size: 16px;"> ${good.price} </p>
									</td><td>
											  <div class="buy_btn_catalog_new" onclick="return buy_popup(271436, '7kfpt0hglhp3dhs0cjp5beiql5', this, '0')" title="Добавить в корзину - купить Игровая консоль Globex PGP-200 Blue"></div>
										</td></tr>
							</table>
						</div>
					</div>
				</td>
				</c:forEach>
				</tr>
				<c:forEach items = "${propscompared}" var = "propcompare">
				<tr><td class="mgctleft">${propcompare.prop.name}</td><c:forEach items = "${propcompare.comparegoods}" var = "goodcomp"><td class="mgctinside">${goodcomp.val.value}</td></c:forEach></tr>
				</c:forEach>
</table>
				</li></ul>		

</c:if>

<c:if test="${section == 'respblock'}">
<script>
$(document).ready(function(){
$("#user_raiting").hover(function() {
//	alert("ee");
    $("#user_raiting_votes, #user_raiting_hover").toggle();
    },
	  function() {
    $("#user_raiting_votes, #user_raiting_hover").toggle();
//   $("#user_raiting_infot").html("Оцените товар");
});


var margin_doc = $("#user_raiting").offset();


$("#user_raiting").mousemove(function(e){
var widht_votes = e.pageX - margin_doc.left;
if (widht_votes == 0) widht_votes =1 ;
user_votes = Math.ceil(widht_votes/16);
// обратите внимание переменная  user_votes должна задаваться без var, т.к. в этом случае она будет глобальной и мы сможем к ней обратиться из другой ф-ции (нужна будет при клике на оценке.
$("#user_raiting_hover").width(user_votes*16);
if(user_votes==1)
  $("#user_raiting_infot").html("Ужасно");
else if(user_votes==2)
  $("#user_raiting_infot").html("Плохо");
else if(user_votes==3)
  $("#user_raiting_infot").html("Нормально");
else if(user_votes==4)
  $("#user_raiting_infot").html("Хорошо");
else if(user_votes==5)
  $("#user_raiting_infot").html("Отлично");
});

$("#user_raiting").click(function(){
//	alert(user_votes);
    $("#note").attr("value", user_votes);
   
        });

});
</script>
<span style = "font-size: 14px;">Ваша оценка товара:</span>
         <div id="user_raiting_star">
                                    <div id="user_raiting">
                                        <div id="user_raiting_blank"></div> <!--блок пустых звезд-->
                                        <div id="user_raiting_hover" style="display: none; width: 32px;"></div> <!--блок  звезд при наведении мышью-->
                                        <div id="user_raiting_votes" style="display: block;"></div> <!--блок с итогами голосов -->
                                    </div>
                                    <div id="user_raiting_info">
                                        <img src="http://www.mobilluck.com.ua/img/load.gif" width="15">
                                        <span id="user_raiting_infot">Ваша оценка</span>
                                    </div>
                                </div>
    <input id="note" type="hidden" name="note"></input>                             
<table width="500" cellspacing="5" border="0" align="left" style="font-size: 14px; margin-bottom: 20px;">

    <input id="id" type="hidden" value="271436" name="id"></input>
    <input type="hidden" value="chws9sg196" name="frsc"></input>
    <tbody>
    
        <tr>
        <c:if test="${empty comm.user}">
            <td>


                                        Ваше имя

                <br></br>
                <input id="name" type="text" name="name" style="width: 200px; height: 20px;"></input>
            </td>
         </c:if>   
            <td>


                              

                <br></br>
                <input id="ocenka" type="hidden" name="ocenka" value="5"></input>
                <div id="raiting_star2">
                    <div id="raiting2"></div>
                </div>
                <style>


                                #raiting2 {position:relative; height:14px; cursor:pointer; width:82px; float:left; overflow: hidden;} /* Блок рейтинга*/
                                #raiting_blank2, #raiting_votes2, #raiting_hover2 {height:14px; position:absolute;}
                                #raiting_blank2 { background:url(http://www.mobilluck.com.ua/img/product/raiting.png); width:82px;} /* "Чистые" звездочки */
                                #raiting_votes2 {background:url(http://www.mobilluck.com.ua/img/product/raiting.png) 0 -15px;} /*  Закрашенные звездочки */
                                #raiting_hover2 {background:url(http://www.mobilluck.com.ua/img/product/raiting.png) 0 -15px; display:none}  /*  звездочки при голосовании */
                                #raiting_star2 {margin-top: 5px;}

                </style>
            </td>
        </tr>
  <!--        <tr>
            <td>


                                        Ваш Email

                <br></br>
                <input id="email" type="text" value="" name="email" style="width: 200px; height: 20px;"></input>
            </td>
            <td valign="bottom" style="font-size: 10px;">
                <input type="checkbox" checked="" value="1" name="podpiska"></input>

                 - получать уведомления о новых комментариях

            </td>
        </tr>
        -->
        <tr>
            <td colspan="2">


                                        Комментарий

                <br></br>
                <textarea id="text" style="width: 400px; height: 60px;" name="text"></textarea>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div class="resp_plus" style="padding-bottom: 0px;">

                    Плюсы

                </div>
                <textarea id="plus" style="width: 400px; height: 60px;" name="plus"></textarea>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div class="resp_minus">

                    Минусы

                </div>
                <textarea id="minus" style="width: 400px; height: 60px;" name="minus"></textarea>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div id="addresponce" class="responce" onclick = "addcomment(-1,${goodid});return false"></div>
                <a class="cancelanswer" style="margin-left: 20px; margin-top: 10px;" href="javascript:void()">

                    отменить

                </a>
            </td>
        </tr>
        <tr>
            <td colspan="2">
            </td>
        </tr>
    </tbody>

</table>
          
                                
</c:if>

<c:if test="${section == 'orderdatefilter'}">

<div style="font-size: 12px; padding: 20px;">

    <table>
    
    <tr>
			<th>Номер заказа</th>
			<th>Время заказа</th>
			<th>Крайний срок доставки</th>
			<th>Адресс доставки</th>
			<th>Тип доставки</th>
			<th>Способ оплаты</th>
			<th>Срок кредита</th>
			<th>Компания</th>
			<th>ОКПО</th>
			<th>Сумма заказа</th>
			<th>Позиции заказа</th>
	</tr>
    <c:forEach items = "${orders}" var = "order">
    <tr>
    <td>${order.id}</td>
    <td>${order.time}</td>
    <td>${order.delivtime}</td>
	<td>${order.adress}</td>
	<td>${order.deliverytype.name}</td>
	<td>${order.paytype.name}</td>
	<td>${order.cred_term}</td>
	<td>${order.client_company}</td>
	<td>${order.client_okpo}</td>
    <td>${order.amount}</td>
	<td><a role = "button" onclick = "return loadup('${pageContext.request.contextPath}','orderpositions',${order.id});">Позиции заказа</a></td>
	</tr>
    </c:forEach>
    </table>

</div>

</c:if>

<c:if test="${section == 'orderpositions'}">
<script>

function clo(){
//	alert("www");
		$("#bucket").css("display", "none");
		$("#bucket").html("");
		$("#popupbg").hide();
		$("#popup_wrapper").hide();
}
</script>
<div id="popup_wrapper" style="display: block;">

    <div id="popup_outer" style="width: 680px;">
        <div id="popup_content">
            <div id="popup_close" onclick = "clo()">

                Закрыть

            </div>
            <p id="popup_cart_title" class="mgood_title">

                Позиции заказа
           </p>
            <div id="popup_cart_content" style="clear: left; float: left; width: 100%;">
                <div style="border: 0px solid red; background-color: white; width: 600px; margin: 0 auto; padding: 0px;">
                    <form id="cartform" name="cartform" method="POST" action="http://www.mobilluck.com.ua/cart.php">
                        <input type="hidden" value="fillorder" name="mode"></input>
                        <div class="w100 float">
                            <table class="mcctable" cellspacing="0" cellpadding="0" border="0" align="center" style="width: 570px; padding: 0px;">
                                <tbody>
                                <c:forEach items="${orderrows}" var="row">
                                    <tr>
                                    <!--      <td width="25" valign="top" align="center" style="padding: 5px;" rowspan="2">
                                            <a onclick="return deletefrombuck('${pageContext.request.contextPath}/good/${row.good.id}/all',${row.good.id},${bucketsize})" href="#">
                                                <img src="http://www.mobilluck.com.ua/img/product/close.png"></img>
                                            </a>
                                        </td>-->
                                        <td width="130" align="center" style="padding: 0px;" rowspan="2">
                                            <a href="${pageContext.request.contextPath}/good/${row.good.id}">
                                                <img width="95" height="84" alt="${row.good.name}" src="<c:url value="/resources/images${row.good.thumb}" />"></img>
                                            </a>
                                        </td>
                                        <td width="200" style="padding: 0px;">
                                            <input type="hidden" value="43317" name="cartit[]"></input>
                                            <p>
                                                <a href="${pageContext.request.contextPath}/good/${row.good.id}">

                                                    ${row.good.name}

                                                </a>
                                            </p>
                                        </td>
                                        <td style="padding: 0px 10px;">
                                        <br><br>
                                            <p id="goodcount_${row.good.id}">${row.goodcount} шт.</p>

                                            

                                            <br></br>
                                          <!--    <div id="cit_recalc${row.good.id}" class="order3_hlink" style="display: none;">
                                                <a onclick="return changecount('${pageContext.request.contextPath}/good/${row.good.id}/all',${row.good.id},${bucketsize})" href="#">

                                                    пересчитать

                                                </a>
                                            </div>
                                            -->
                                        </td>
                                        <td style="padding: 0px; text-align: right;">
                                            <p style="font-size: 18px;">
                                                <span style="font-size: 24px; font-weight: bold;">

                                                    ${row.good.price}

                                                </span>

                                                 грн

                                            </p>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td bgcolor="white" style="padding: 0px 10px; background-color: white" colspan="3">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="5"></td>
                                    </tr>
                             </c:forEach>
                                    <tr>
                                        <td class="w100" colspan="6">
                                            <table class="order3table">
                                                <tbody>
                                                    <tr>
                                                        <td id="linkback" align="left" style="padding-left: 10px;">
                                                            <img src="http://www.mobilluck.com.ua/img/product/left_arrow.png"></img>
                                                            <a href="javascript:history.back();">

                                                                Вернуться к выбору товаров

                                                            </a>
                                                        </td>
                                                        <td class="ta_right" style="font-size: 18px;">


                                                            						
                                                            							Всего за заказ
                                                            							

                                                            <span style="font-size: 24px; font-weight: bold;">

                                                                ${sum}

                                                            </span>

                                                             грн*
                                                            						

                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
 <!--                         <div class="w100 float">

   						 <table cellspacing="0" cellpadding="0" align="center" style="width: 570px; padding: 0px;">
       						 <tbody>
       						     <tr>
          				      <td align="right" style="padding: 0px;">
               			     <div id="btn_order" class="btnorder" onclick = "location.href = '${pageContext.request.contextPath}/order'"></div>
              			  </td>
            		</tr>
        </tbody>
    </table>

</div>-->
                    </form>
                    <table align="center" style="width: 600px; padding: 0px 0px;"></table>
                    <div id="procredit" style="width: 100%;"></div>
                    <div style="clear: left; height: 10px;"></div>
                </div>
            </div>
            <div class="both"></div>
        </div>
    </div>

</div>

</c:if>




<c:if test="${section == 'citybound'}">


<div class="blueborder">
<p class="blueborder_p">Доставка</p>
<ul class="blueborder_ul">
<li><span class="blueborder_txt">самовывоз в Киеве <b>бесплатно</b></span></li>
<li><span class="blueborder_txt">адресная доставка по Киеву <b>35 грн</b></span></li>
<li><span class="blueborder_txt">доставка в пункт самовывоза <b>25 грн</b></span></li>
<li><div class="btn_help" onclick="return load_text(this, 'uh_base', 89477)"></div><span class="blueborder_txt">адресная доставка до двери <b>35 грн</b></span></li>
<li><span class="blueborder_txt">оплата при получении</span></li>

</ul>
</div>
<div style="padding-bottom: 10px; border-bottom: 1px solid silver;"><p style="line-height: 20px;"><b>Гарантия</b> <a href="javascript:void();" class="javalink" onclick="return load_text(this, 'guarantee', 89477)">12 мес. или <b>Возврат</b></a></p><p style="line-height: 20px;"><b>Оплата</b> <a href="javascript:void();" class="javalink" onclick="return load_text(this, 'oplata', 89477)">при получении</a></p></div>
</c:if>

<c:if test="${section == 'item'}">
 <!--     <img width="15" src="http://www.mobilluck.com.ua/img/load.gif" style="display: none;"></img>-->
    <span id="raiting_infot" style="display: inline;">

        Спасибо. 

    </span>
 </c:if>   
    

<c:if test="${section == 'subcatlist'}">
<table><tbody><tr><c:forEach items = "${subcats}" var = "cat">
<td><a href="${pageContext.request.contextPath}/catalog/${cat.page.fullurl}/pagin/1"><p>${cat.name}</p><div class="cat_photo"><img src="<c:url value="/resources/images/${cat.thumb}" />" data-original="<c:url value="/resources/images/${cat.thumb}" />"></div></a></td>
</c:forEach></tr></tbody></table>

</c:if> 


<c:if test="${section == 'wishnotreg'}">
       Желаемые покупки доступны только для зарегистрированных пользователей!

                <br></br>
                <a target="_blank" href="http://www.mobilluck.com.ua/input.php">

                    Авторизируйтесь

                </a>

                 либо пройдите
                                

                <a target="_blank" href="${pageContext.request.contextPath}/reg">

                    регистрацию

                </a>
</c:if> 


<c:if test="${section == 'wishreg'}">
  Товар добавлен в желаемые!
</c:if>

<c:if test="${section == 'watchnotreg'}">

<p>

    Укажите свой E-mail адрес и подпишитесь на

    <br></br>

    изменения ценовой политики по товару.

    <br></br>

    Вы будете получать информацию о том,

    <br></br>

    что товар участвует в акциях или

    <br></br>

    попал под распродажу.

</p>
<form class="dlgform" onsubmit="return send_sabscribe_prod(this);" method="POST" action="#">

    <input type="hidden" value="" name="mode"></input>
    <input id="frmprodid2" type="hidden" value="197675" name="prodid"></input>
    <input type="hidden" value="subscribeprod" name="action"></input>
    <div class="dlgline">
        <label for="dlgf_mail">

            Электронная почта: 

            <span class="required">

                *

            </span>
            <span id="dlgf_name_e" class="error" style="display:none">

                E-mail введен неправильно

            </span>
        </label>
        <input id="dlgf_mail" class="dlgftext" type="text" name="clientmail"></input>
    </div>
    <div class="dlgline">
        <table class="dlgtreg">
            <tbody>
                <tr>
                    <td>
                        <input class="dlgfsend" type="submit" value="" name="dlgf_submit"></input>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>

</form>

</c:if>



<c:if test="${section == 'watchreg'}">

<p>

    Укажите свой E-mail адрес и подпишитесь на

    <br></br>

    изменения ценовой политики по товару.

    <br></br>

    Вы будете получать информацию о том,

    <br></br>

    что товар участвует в акциях или

    <br></br>

    попал под распродажу.

</p>
<form class="dlgform" onsubmit="return send_sabscribe_prod(this);" method="POST" action="#">

    <input type="hidden" value="" name="mode"></input>
    <input id="frmprodid2" type="hidden" value="197675" name="prodid"></input>
    <input type="hidden" value="subscribeprod" name="action"></input>
    <div class="dlgline">
        <label for="dlgf_mail">

            Электронная почта: 

            <span class="required">

                *

            </span>
            <span id="dlgf_name_e" class="error" style="display:none">

                E-mail введен неправильно

            </span>
        </label>
        <input id="dlgf_mail" class="dlgftext" type="text" name="clientmail"></input>
    </div>
    <div class="dlgline">
        <table class="dlgtreg">
            <tbody>
                <tr>
                    <td>
                        <input class="dlgfsend" type="submit" value="" name="dlgf_submit"></input>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>

</form>

</c:if>



<c:if test="${section == 'login'}">
 <!-- -    <div id="dialog_outer">
        <div id="dialog_inner">
            <a id="dialog_close" href="#"></a>
            <div id="dialog_title"></div>
 -->
          
                <form id="login-form" method="POST" action="http://www.mobilluck.com.ua/cart.php">
                    <table>
                        <tbody>
                            <tr>
                                <td class="left-side">
                                    <p class="titleSec">
                                        <span>

                                            Войти быстро, используя

                                        </span>
                                    </p>
                                    <div id="vkontakte_login" onclick="fbAuth('http://oauth.vkontakte.ru/authorize?client_id=211713…=notify%2Cfriends%2Cphotos%2Caudio%2Cvideo', 'Вконтакте', 2)" style="cursor: pointer;">
                                        <img src="http://www.mobilluck.com.ua/img/popup/soc_vk.png"></img>
                                    </div>
                                    <div id="facebook_login" onclick="fbAuth('https://www.facebook.com/dialog/oauth?client_id=3663…y,user_hometown,user_location&display=popup', 'Facebook', 1)" style="cursor: pointer;">
                                        <img src="http://www.mobilluck.com.ua/img/popup/soc_fb.png"></img>
                                    </div>
                                </td>
                                <td class="right-side">
                                    <input type="hidden" value="orderlogin" name="action"></input>
                                    <input type="hidden" value="" name="refurlhndl"></input>
                                    <input type="hidden" value="postuser" name="mode"></input>
                                    <div>
                                        <label class="titleSec" for="dlgf_mail">

                                            Электронная почта: 

                                            <span class="necessary">

                                                *

                                            </span>
                                        </label>
                                        <p>
                                            <input id="dlgf_mail" class="dlgftext" type="text" name="clientlog"></input>
                                        </p>
                                    </div>
                                    <div>
                                        <label class="titleSec" for="dlgf_mail">

                                            Пароль: 

                                            <span class="necessary">

                                                *

                                            </span>
                                        </label>
                                        <p>
                                            <input id="dlgf_psw" class="dlgftext" type="password" name="clientpass"></input>
                                        </p>
                                        <p class="restore_pass">
                                            <a id="restorepassbtn2" class="dlglink" role="button">
                                                <span>

                                                    Напомнить пароль

                                                </span>
                                            </a>
                                        </p>
                                    </div>
                                    <label id="login_button" for="login">
                                        <div>
                                            <span>

                                                Войти

                                            </span>
                                            <input id="login" type="submit" name="dlgf_submit"></input>
                                        </div>
                                    </label>
                                    <p class="or-registrate">

                                        или 

                                        <a href="${pageContext.request.contextPath}/reg">

                                            зарегистрироваться

                                        </a>
                                    </p>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <p class="license_agreement">

                                        Регистрируясь, вы принимаете условия 

                                        <a rel="nofollow" href="http://www.mobilluck.com.ua/info.php?page=using">

                                            соглашения.

                                        </a>
                                    </p>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            
            <!--

            <div class="dinner_t"></div>
            				<div class="dinne…

            -->
  <!-- -        </div>
    </div>

 -->
</c:if> 

<c:if test="${section == 'succesreg'}">
<p><span class="letter" style = "padding-left:30%;">Поздравляем с успешной регистрацией!</span></p>
</c:if> 



<c:if test="${section == 'ordergoods'}">

<div class="checkout_f">
				<table>
					<tbody><tr>
						<td class="checkout_top_f checkout_left_top_f"><h3>Ваша корзина</h3></td>
						<td colspan="2" class="checkout_top_f">
							<p class="redact_f">
								<a role="button" onclick="loadup('${pageContext.request.contextPath}', 'bucket');"><img src="img/checkout/redact.png"><span class="red_mid_f">Редактировать</span></a>
							</p>
						</td>
					</tr>
					
					<c:forEach items="${buckrows}" var="row">
				<tr class="item_mf">
					<td class="checkout_left_f">
        				<a href="${pageContext.request.contextPath}/good/${row.good.id}" target="_blank">${row.good.name}</a>
    				</td>
					<td class="count_f">
						${row.goodcount} шт.
					</td>
                    <td class="price_f">${row.good.price} грн
					</td>
				</tr>
                 	</c:forEach>       
                    <tr class="summ_f">
                        <td colspan="3">
							<p>Всего к оплате: <span id="cstall">${sum}</span> грн</p>
						</td>
					</tr></tbody></table>
<!--  
<form method="POST" action="https://merchant.webmoney.ru/lmi/payment.asp">
<input name="LMI_PAYMENT_NO" value="1">
<input name="LMI_PAYMENT_AMOUNT" value="${sum}">
<input type = "hidden" name="LMI_PAYMENT_DESC_BASE64" value="${text}">
<input type = "hidden" name="LMI_PAYEE_PURSE" value="U252091915788">
<!--  <input type="hidden" name="id" value="345">
Укажите email для отправки товара: <input type="text" name="email" size="15"> 
<input type="submit" value="Перейти к оплате через вебмани">
</form>				
	-->				
					</div>

</c:if> 

<c:if test="${section == 'towns'}">
<c:forEach items="${towns}" var="town">
${town}
</c:forEach> 
</c:if> 

<c:if test="${section == 'fulladress'}">
							
										<div id="per" style="float: left;">
											<span class="selectbox" style="display: inline-block; position: relative; z-index: 1;"><div class="select" style="float:left;position:relative;z-index:99"><div class="text">улица</div><b class="trigger"><i class="arrow"></i></b></div><div class="dropdown" style="position: absolute; z-index: 9999; overflow-y: auto; overflow-x: hidden; list-style: none outside none; left: 0px; display: none;"><ul><li style="" class="selected sel" title="улица">улица</li><li title="проспект">проспект</li><li title="площадь">площадь</li><li title="бульвар">бульвар</li><li title="переулок">переулок</li><li title="проезд">проезд</li><li title="въезд">въезд</li></ul></div></span><select style="position: absolute; top: -9999px;" class="fsel" name="type_street" id="adress_f">
												<option value="1">улица</option>
												<option value="2">проспект</option>
												<option value="3">площадь</option>
												<option value="4">бульвар</option>
												<option value="5">переулок</option>
												<option value="6">проезд</option>
												<option value="7">въезд</option>
											</select>
										</div>
										<input title="Название" placeholder="Название" placeholder2="Название" name="street" id="ulica" value="" style="float: left;" type="text"><br>
										<div style="text-align:left; padding-top:10px; clear: left;">
											<input title="№ Дома" placeholder="№ Дома" placeholder2="№ Дома" name="house" id="nhouse" value="" type="text">
											<input title="Этаж" placeholder="Этаж" placeholder2="Этаж" name="flor" id="floar" value="" type="text">
											<input title="№ Квартиры" placeholder="№ Квартиры" placeholder2="№ Квартиры" name="room" id="nroom" value="" type="text">
										</div>
										<div id="add_param">
											<div style="clear:both">
												<p style="clear:both; float: left">Нужен занос?</p>
												<span class="selectbox" style="display: inline-block; position: relative; z-index: 1;"><div class="select" style="float:left;position:relative;z-index:99"><div class="text">Нет</div><b class="trigger"><i class="arrow"></i></b></div><div class="dropdown" style="position: absolute; z-index: 9999; overflow-y: auto; overflow-x: hidden; list-style: none outside none; left: 0px; display: none;"><ul><li style="" class="selected sel" title="Нет">Нет</li><li title="Да">Да</li></ul></div></span><select style="position: absolute; top: -9999px;" name="nzanos" id="zanos">
													<option value="0" selected="">Нет</option>
													<option value="1">Да</option>
												</select>
											</div>
											<div style="clear: both; display: none;" id="netajbl">
												<p style="clear:both; float: left">Какой этаж?</p>
												<span class="selectbox" style="display: inline-block; position: relative; z-index: 1;"><div class="select" style="float:left;position:relative;z-index:99"><div class="text">1 этаж</div><b class="trigger"><i class="arrow"></i></b></div><div class="dropdown" style="position: absolute; z-index: 9999; overflow-y: auto; overflow-x: hidden; list-style: none outside none; left: 0px; display: none;"><ul><li style="" class="selected sel" title="1 этаж">1 этаж</li><li title="2 этаж">2 этаж</li><li title="3 этаж">3 этаж</li><li title="4 этаж">4 этаж</li><li title="5 этаж">5 этаж</li><li title="6 этаж">6 этаж</li><li title="7 этаж">7 этаж</li><li title="8 этаж">8 этаж</li><li title="9 этаж">9 этаж</li><li title="10 этаж">10 этаж</li><li title="11 этаж">11 этаж</li><li title="12 этаж">12 этаж</li><li title="13 этаж">13 этаж</li><li title="14 этаж">14 этаж</li><li title="15 этаж">15 этаж</li><li title="16 этаж">16 этаж</li><li title="17 этаж">17 этаж</li><li title="18 этаж">18 этаж</li><li title="19 этаж">19 этаж</li><li title="20 этаж">20 этаж</li><li title="21 этаж">21 этаж</li><li title="22 этаж">22 этаж</li><li title="23 этаж">23 этаж</li><li title="24 этаж">24 этаж</li><li title="25 этаж">25 этаж</li><li title="26 этаж">26 этаж</li><li title="27 этаж">27 этаж</li><li title="28 этаж">28 этаж</li><li title="29 этаж">29 этаж</li><li title="30 этаж">30 этаж</li><li title="31 этаж">31 этаж</li><li title="32 этаж">32 этаж</li><li title="33 этаж">33 этаж</li><li title="34 этаж">34 этаж</li></ul></div></span><select style="position: absolute; top: -9999px;" name="netaj" id="etag">
													<option value="1">1 этаж</option><option value="2">2 этаж</option><option value="3">3 этаж</option><option value="4">4 этаж</option><option value="5">5 этаж</option><option value="6">6 этаж</option><option value="7">7 этаж</option><option value="8">8 этаж</option><option value="9">9 этаж</option><option value="10">10 этаж</option><option value="11">11 этаж</option><option value="12">12 этаж</option><option value="13">13 этаж</option><option value="14">14 этаж</option><option value="15">15 этаж</option><option value="16">16 этаж</option><option value="17">17 этаж</option><option value="18">18 этаж</option><option value="19">19 этаж</option><option value="20">20 этаж</option><option value="21">21 этаж</option><option value="22">22 этаж</option><option value="23">23 этаж</option><option value="24">24 этаж</option><option value="25">25 этаж</option><option value="26">26 этаж</option><option value="27">27 этаж</option><option value="28">28 этаж</option><option value="29">29 этаж</option><option value="30">30 этаж</option><option value="31">31 этаж</option><option value="32">32 этаж</option><option value="33">33 этаж</option><option value="34">34 этаж</option>												</select>
											</div>
											<div style="clear: both; display: none;" id="nliftbl">
												<p style="clear:both; float: left">Есть лифт?</p>
												<span class="selectbox" style="display: inline-block; position: relative; z-index: 1;"><div class="select" style="float:left;position:relative;z-index:99"><div class="text">Нет</div><b class="trigger"><i class="arrow"></i></b></div><div class="dropdown" style="position: absolute; z-index: 9999; overflow-y: auto; overflow-x: hidden; list-style: none outside none; left: 0px; display: none;"><ul><li style="" class="selected sel" title="Нет">Нет</li><li title="Есть">Есть</li></ul></div></span><select style="position: absolute; top: -9999px;" name="nlift" id="lift">
													<option value="0">Нет</option>
													<option value="1">Есть</option>
												</select>
											</div>
											<div style="clear: left;font-suze:10px; color:gray;padding-top:20px;text-align: justify;">
												Оплачивается только занос товара весом от 30 кг.<br>
												Если товар габаритный, но помещается в лифт, оплата
												производится за первый и последний этажи.
											</div>
										</div>
									

</c:if> 









<c:if test="${section == 'orderparams'}">
			<script type='text/javascript'>
$(".phone_f").selectbox();
$("#chislo select").selectbox();
$("#mount_f select").selectbox();
$("#clientoplata_f").selectbox();
$("#clientdeliverytype_f").selectbox();
$("#adress_fr select").selectbox();
$("#zanos").selectbox();
$("#lift").selectbox();
$("#etag").selectbox();
$("#filial_adress_f").selectbox();
$("#delivery_adress_f").selectbox();
</script>
					
							<table>
							<tr>
								<td class='error_f'>
								</td>
								<td class='error_f' >
									<span><b class="bred">!</b> Ваш Город *  </span>
								</td>
							</tr>
							<tr id='city_f'>
								<td  class='left_f'>
									<label for='clienttown_f' class='checkout_lf'><p>Город <span class="required">*</span></p></label>
								</td>
								<td class='right_f'>
								<link rel="stylesheet" type="text/css" href="http://www.mobilluck.com.ua/css/jquery.autocomplete.css"/>
								<div id="cities">
																		<div class="citiess">
										<div class="citiess_inp">
											<input type="text" id="clienttown_f" name="clienttown_f" value="${town.name}" placeholder="Введите название города" placeholder2="Введите название города">
											<input type="hidden" name="clienttown" id="clienttown" value="${town.id}">
										</div>
										<b class="trigger" id="clienttownbtn"></b>
									</div>
								</div>
								</td>
								<script type="text/javascript">
								function hide() {
									if($("#clienttown_another").hasFocus()){
										$("#city_f .selectbox .dropdown").css("visibility", "hidden");
									}
								 }
								function shown()  {
									if($(".ac_results").last().css("display")=="none"){
										$("#city_f .selectbox .dropdown").css("visibility", "visible");}
									}
								</script>
								<script type="text/javascript">
								
									var cityid = 0;
									var editinput = false;
									var showcity = true;
									var url = "<c:out value="${pageContext.request.contextPath}"/>";
								//	alert(url+"/citysearch");
									var keyCodeArray = new Array("19",35,"36",37,38,39,40,45,112,113,114,115,116,117,118,119,120,121,122,123);
									$("#clienttown_f").autocomplete(url+"/citysearch",
									{
										matchSubset:0,
										cacheLength:0,
										minChars:0,
										height: 400,
										width: 400,
										resultsClass: "selectbox2",
										focusshow: true,
										selectOnly: false,
										additionalText: "Если в списке нет нужного Вам города, проверьте правильность ввода названия.",
										open: function( event, ui ){},
										onItemSelect: function(item){
										$("#clienttown_f").attr('value', name);
										$("#clienttown_f").attr('title', name);
										},
								
									});
									$("#clienttownbtn").bind('click', function () {
										
										showcity = true;
										$("#clienttown_f").trigger("focus");
									});
									$("#clienttown_f").live("keypress", function(eventObject){
										showcity = true;
										var t = in_array(eventObject.keyCode, keyCodeArray);
										if(t==false)
										{
											editinput = true;
										}
									});
									$("#clienttown_f").live("blur", function(){
										showcity = true;
										if(($("#clienttown").val()==cityid)&&(editinput))
										{
											$("#clienttown_f").val("");
											$("#clienttown").val(0);
											//$("#dostavka_f").hide();
											change_city();
										}
									});
									
									$("#zanos").change(function(){
										var zanos = $("#zanos").val();
										if(zanos == 1){
											$("#netajbl").css("display","block");
											$("#nliftbl").css("display","block");
										}
										else{
												$("#netajbl").css("display","none");
												$("#nliftbl").css("display","none");
											
										}
									//	refresh_cart('c4vp2biauqd93btpr1mf2dlor3', '0', '');
									});
									</script>
								<td class='help_f'>
									<div class="btn_help" style="margin: 6px 0 0 12px;" onclick="return load_text(this, 'city', '300')"></div>
								</td>
							</tr>
								<tr>
							<td class='left_f space_f' colspan='2'>
								</td>
							</tr>
							<tr id="oplata_f">
								<td  class='left_f'>
									<label for='clientoplata_f' class='checkout_lf'><p>Способ оплаты&nbsp;&nbsp;</p></label>
									
								</td>
								<td class='right_f'>
									 <select id="clientoplata_f" onclick="showhelp(this);" name="clientoplata" style="position: absolute; top: -9999px;">
										<c:forEach items = "${paytypes}" var = "paytype">
										<c:choose>
										<c:when test="${paytype.id==ptype.id}">
                						<option selected="selected" value="${paytype.id}" >${paytype.name}</option>
                						</c:when>
                						<c:otherwise>
                						<option value="${paytype.id}" >${paytype.name}</option>
                						</c:otherwise>
                						</c:choose>
                						</c:forEach>
                						</select>
									<script type="text/javascript">
										$("#clientoplata_f").live('change', function () {
											change_params();
										});
									</script>
								<c:choose>	
								<c:when test="${ptype.shortname == 'onlinecred'}">
									<p id="hdnmsg" style="display: table-row;">При выбранном способе оплаты использование баллов и промо кода невозможно</p>
									<tr id="kredit_f" style="display: table-row;">
										<td class="left_f">
									<label for="kredit_f" class="checkout_lf"><p>Срок кредита(мес.) <span class="required">*</span></p></label>
								</td>
								<td class="right_f">
									<input type="text" name="srok_cred" id="srok" onblur="selcreditperiod(this.value);" value="3">
								</td>
							
								</c:when>
								
								<c:when test="${ptype.shortname == 'nocash'}">
									<tr>
								<td class="left_f space_f" colspan="2">
									</td>
								</tr>
									<tr class="organization_f" style="display: table-row;">
								<td class="left_f line_f">
									<label for="client_company" class="checkout_lf">Название организации <span class="required">*</span></label>
								</td>
								<td>
									<input type="text" id="client_company" name="client_company" value="">
								</td>
								</tr>

							<tr class="organization_f" style="display: table-row;">
								<td class="left_f space_f" colspan="2">
								</td>
							</tr>

							<tr class="organization_f" style="display: table-row;">
								<td class="left_f line_f">
									<label for="client_okpo" class="checkout_lf">Код ОКПО&nbsp;&nbsp;<span style="font-size: 12px; font-weight: normal"><br>(не обязательно)&nbsp;&nbsp;</span></label>
								</td>
								<td>
									<input type="text" id="client_okpo" name="client_okpo" value="" style="display: block; ">
								</td>
								</tr>



						
							</c:when>
							<c:otherwise>
                		
                			</c:otherwise>
							</c:choose>
								
							
								<tr>
								<td class="left_f space_f" colspan="2">
									</td>
								</tr>
								</tr>
								
					<!--				<p id="hdnmsg" style="display:none;">При выбранном способе оплаты использование баллов и промо кода невозможно</p>
								</td>
								<td class='help_f'>
									<div class="btn_help" style="margin: 6px 0 0 12px;" onclick="return load_text(this, 'payment', '300')"></div>
								</td>
							</tr>
							<tr id="kredit_f"  style="display:none;">
								<td  class='left_f'>
									<label for='kredit_f' class='checkout_lf'><p>Срок кредита(мес.) <span class="required">*</span></p></label>
								</td>
								<td class='right_f'>
									<input type="text" name="srok" id="srok" onblur="selcreditperiod(this.value);" valeu="3">
								</td>
								<td>
								</td>
							</tr>
								<tr>
							<td class='left_f space_f' colspan='2'>
								</td>
							</tr>
							-->
							<tr  id="dostavka_f">
								<td class='left_f'>
									<label for='clientdeliverytype_f' class='checkout_lf'><p>Способ доставки&nbsp;&nbsp;</p></label>
								</td>
								<td class='right_f'>
									      <select id="clientdeliverytype_f" name="clientdeliverytype" style="position: absolute; top: -9999px;">
               						 <c:forEach items = "${deliverytypes}" var = "delivery">
										<c:choose>
										<c:when test="${delivery.id==deliverytype.id}">
                						<option selected="selected" value="${delivery.id}" >${delivery.name}</option>
                						</c:when>
                						<c:otherwise>
                						<option value="${delivery.id}" >${delivery.name}</option>
                						</c:otherwise>
                						</c:choose>
                						</c:forEach>
               						 </select>
                						
								</td>
								<script type="text/javascript">
									$("#clientdeliverytype_f").live('change', function () {
										change_params();
									});
								</script>
								<td class='help_f'>
									<div class="btn_help" style="margin: 6px 0 0 12px;" onclick="return load_text(this, 'delivery', '300')"></div>
								</td>
							</tr>
							<tr>
							<td class='left_f space_f' colspan='2'></td>
							</tr>
							
							<c:choose>	
								<c:when test="${deliverytype.shortname=='adress'}">
							
							<tr id="adress_fr" >
								<td class='left_f'>
									<label for='clientdeliverytype_f' class='checkout_lf'><p id="adress_title">Адрес доставки&nbsp;&nbsp;</p></label>
								</td>
								<td class='right_f' id="delivery_td">
									
									
									<!-- Если доставка по городу -->
									<div id="adress_pg">

										

										<div id="per" style="float: left;">
											<select class="fsel" name="type_street" id="adress_f">
												<option value="улица" >улица</option>
												<option value="проспект" >проспект</option>
												<option value="площадь" >площадь</option>
												<option value="бульвар" >бульвар</option>
												<option value="переулок" >переулок</option>
												<option value="проезд" >проезд</option>
												<option value="въезд" >въезд</option>
											</select>
										</div>
										<input  type='text' title="Название" placeholder="Название" placeholder2="Название" name="street" id="ulica" value="" style="float: left;"><br>
										<div style='text-align:left; padding-top:10px; clear: left;'>
											<input  type='text' title="№ Дома" placeholder="№ Дома" placeholder2="№ Дома" name="house" id="nhouse" value="">
											<input type='text' title="Этаж" placeholder="Этаж" placeholder2="Этаж" name="flor" id="floar" value="">
											<input type='text' title="№ Квартиры" placeholder="№ Квартиры" placeholder2="№ Квартиры" name="room" id="nroom" value="">
										</div>
										<div id="add_param">
											<div style="clear:both">
												<p style='clear:both; float: left'>Нужен занос?</p>
												<select name="nzanos" id="zanos">
													<option value="0" selected>Нет</option>
													<option value="1" >Да</option>
												</select>
											</div>
											<div style="clear:both; display: none;" id="netajbl" >
												<p style='clear:both; float: left'>Какой этаж?</p>
												<select name="netaj" id="etag">
													<option value="1" >1 этаж</option><option value="2" >2 этаж</option><option value="3" >3 этаж</option><option value="4" >4 этаж</option><option value="5" >5 этаж</option><option value="6" >6 этаж</option><option value="7" >7 этаж</option><option value="8" >8 этаж</option><option value="9" >9 этаж</option><option value="10" >10 этаж</option><option value="11" >11 этаж</option><option value="12" >12 этаж</option><option value="13" >13 этаж</option><option value="14" >14 этаж</option><option value="15" >15 этаж</option><option value="16" >16 этаж</option><option value="17" >17 этаж</option><option value="18" >18 этаж</option><option value="19" >19 этаж</option><option value="20" >20 этаж</option><option value="21" >21 этаж</option><option value="22" >22 этаж</option><option value="23" >23 этаж</option><option value="24" >24 этаж</option><option value="25" >25 этаж</option><option value="26" >26 этаж</option><option value="27" >27 этаж</option><option value="28" >28 этаж</option><option value="29" >29 этаж</option><option value="30" >30 этаж</option><option value="31" >31 этаж</option><option value="32" >32 этаж</option><option value="33" >33 этаж</option><option value="34" >34 этаж</option>												</select>
											</div>
											<div style="clear:both; display: none;" id="nliftbl">
												<p style='clear:both; float: left'>Есть лифт?</p>
												<select name="nlift" id="lift">
													<option value="0" >Нет</option>
													<option value="1" >Есть</option>
												</select>
											</div>
											
											<div style="clear: left;font-suze:10px; color:gray;padding-top:20px;text-align: justify;">
												Оплачивается только занос товара весом от 30 кг.<br/>
												Если товар габаритный, но помещается в лифт, оплата
												производится за первый и последний этажи.
											</div>
										</div>
									</div>
												</td>
							</tr>
									
								</c:when>
									<c:when test="${deliverytype.shortname == 'samovyvoz'}">
									<tr id="adress_fr" >
								<td class='left_f'>
									<label for='clientdeliverytype_f' class='checkout_lf'><p id="adress_title">Адрес доставки&nbsp;&nbsp;</p></label>
								</td>
								<td class='right_f' id="delivery_td">
										<div id="adress_filial" style="">
										<label id="label_branch">Представительства в городе</label>
										<select name="filial_adress" id="filial_adress_f" onclick="showhelp(this);" onchange="select_clientdeliverytype(this);" style="position: absolute; top: -9999px;">
										<c:forEach items = "${residents}" var = "resident">
										<option value="${resident.id}">${resident.town.name}, ${resident.street_type} ${resident.street_name}, ${resident.house_num}</option>
										</c:forEach>
										</select>
									</div>
										</td>
							</tr>
									</c:when>
									<c:otherwise>
					
									
									
									</c:otherwise>
						</c:choose>	
																
						
				</table>
		
</c:if>




<c:if test="${section == 'usernumbers'}">
<c:forEach items = "${user.phonenumbers}" var = "number"> 
                            <div>

  								  
  								 ${number.numb};
								
    						<span onclick="load_text(this, 'update_number', '${user.username}', '${sessid}', 'Редактировать номер телефона', ${number.id});" style="cursor:pointer;">
     					   <img width="12" height="12" border="0" alt="Редактировать номер" src="http://www.mobilluck.com.ua/img/edit.gif"></img>
   							 </span>
  						  <span onclick="delPhone_Adres('del_phone', '${sessid}', ${number.id})" style="cursor:pointer;">
   					     <img width="12" height="12" border="0" alt="Удалить телефон" src="http://www.mobilluck.com.ua/img/ico-add2buydel.gif"></img>
 						   </span>

						</div>
						</c:forEach>
                            <div style="font-size: 14px; color: #828379; padding: 2px;">
								<c:if test = "${empty user.phonenumbers}">
								
  								  <i>
	
     							   Нет номера телефона

    								</i>
								</c:if>
											</div>
									<div style="padding: 2px;" onclick="load_text(this, 'add_number', '${user.username}', '${sessid}', 'Добавить номер телефона', -1);">

    								<span id="" style="border-bottom: 1px dashed #477dfb; cursor: pointer; font-size: 12px; color: #477dfb;">

     								   Добавить номер

   									 </span>

									</div>
                            
                                <script>
                                    view_number('rfsafboakco231ui5bgupeqp60', 'telefonUR', 'phone_number');
                                </script>

</c:if>


 


</body>
</html>