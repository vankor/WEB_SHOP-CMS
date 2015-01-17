<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1251" />

<title>Корзина заказов </title>
<jsp:include page="/WEB-INF/views/template/head.jsp" />
<link href="http://www.mobilluck.com.ua/rss/mobilluck.rss" rel="alternate" type="application/rss+xml" title="RSS" />

<link rel="stylesheet" type="text/css" href="http://css.mobilluck.com.ua/css/checkout.css">


		<script type="text/javascript" src="http://js.mobilluck.com.ua/js/jquery-1.4.4.min.js"></script>

	<script type="text/javascript" src="http://js.mobilluck.com.ua/js/cart.js"></script>


	<script src="<c:url value="/resources/js/mascedinput.js" />"></script>
	<script src="<c:url value="/resources/js/combobox.js" />"></script>
	<script src="<c:url value="/resources/js/lazyload.js" />"></script>
	<script src="<c:url value="/resources/js/popups.js" />"></script>
	<script src="<c:url value="/resources/js/myajax.js" />"></script>
	<script src="<c:url value="/resources/js/main.js" />"></script>
	<script src="<c:url value="/resources/js/carousel.js" />"></script>
	<script src="<c:url value="/resources/js/jcarousellite.js" />"></script>
	<script type="text/javascript" src="http://js.mobilluck.com.ua/js/cloud-zoom.1.0.2.js"></script>
	<script type="text/javascript" src="http://js.mobilluck.com.ua/js/fancybox/jquery.fancybox-1.3.2.pack.js"></script>
	<script src="<c:url value="/resources/js/myscrpits.js" />"></script>
	<script src="<c:url value="/resources/js/autocomplete.js" />"></script>
	<script src="<c:url value="/resources/js/datepicker.js" />"></script>
<script type="text/javascript">	

function setTownId(id, tt){
		
		$("#clienttown").attr("value", id);
		$("#clienttown_f").attr("value", tt);
		
		change_params();
}

$(document).ready(function() {
	//	change_city();
	var sessid ="<c:out value="${sessid}"/>";
			refresh_cart(sessid, '0', '');
});





$(function() {
    var cache = {};
    var url = "<c:out value="${pageContext.request.contextPath}"/>";
    $("#town").autocomplete({
      minLength: 1,
      source: function( request, response ) {
        var term = request.term;
        alert(term);
        if ( term in cache ) {
          response( cache[ term ] );
          return;
        }
 
        $.getJSON( url+"/citysearch", request, function( data, status, xhr ) {
          cache[ term ] = data;
          response( data );
        });
      }
    });
  });

$(document).ready(function(){
	var menu = $("ul.mainmenu3 li.mmd_item");
	var mmps = $(".hpslist .subm_item");
	for (var i=0; i<menu.length; i++){$(menu[i]).attr("id", "topm"+(i));};
	for (var i=0; i<mmps.length; i++){$(mmps[i]).attr("id", "subm"+(i+1));};

	menu.hover(
		function(){
			var idx = $(this).attr("id").replace("topm", "");
			clearTimeout(mt);
			mt = setTimeout(function(){
				if (mStatus == 'show'){
					menu.each(function(){$(this).removeClass("active");});
					mmps.each(function(){$(this).hide();});
					$("#subm"+idx).show();
				}
			}, 250);
			mStatus = 'show';
		},
		function(){
			var idx = $(this).attr("id").replace("topm", "");
			mmps.each(function(){$(this).hide();});
			mStatus = 'hide';
		}
	);
	mmps.hover(
		function(){
			var idx = $(this).attr("id").replace("subm", "");
			menu.each(function(){$(this).removeClass("active");});
			$("#topm"+idx).addClass("active");
			$(this).css("display", "block");
			mStatus = 'show';
		},
		function(){
			var idx = $(this).attr("id").replace("subm", "");
			menu.each(function(){$(this).removeClass("active");});
			mmps.each(function(){$(this).hide();});
			mStatus = 'hide';
		}
	);

	$("#tfvccarousel").jcarousel({scroll: 4});

	$(document).bind("click", function(){
		$(".ccsdropdown").css("display", "none");
		if(dnthidebasked==1)
		{
			dnthidebasked=0;
		}
		else
		{
			$(".basked_items_list").hide();
		}
		if($(".basked_items_list").is(":hidden"))
		{
			$(".baskedbox .basked .trigger").css("background-position", "-207px 0px");
		}
		if(dnthidemymob==1)
		{
			dnthidemymob=0;
		}
		else
		{
			$(".mymob_items_list").hide();

		}
		if($(".mymob_items_list").is(":hidden"))
		{
			$(".mymobbox .mymob .trigger").css("background-position", "-207px 0px");
		}
	});
	$(".faddr_p a").bind("click", function(){
		var contpan = $(this).parent().parent().find(".ccsdropdown");
		contpan.css("display", "block");
		return false;
	});

	$("#rfiliallnk").bind("click", function(){
		var contpan = $(this).parent().parent().find(".ccsdropdown");
		contpan.css("display", "block");
		return false;
	});

	$("#lnkfilialinfo").bind("click", function(){
		var selobj = document.getElementById('s_thft_city');
		var selph = selobj.options[selobj.selectedIndex].value;
		var selph_p = selph.split(":");
		var selfil_id = selph_p[0];
		var lnk = document.getElementById('lnkfilialinfo');
		loadFilialInfo( lnk, selfil_id );

	});



});

$(window).scroll(function(){
	if($(document).scrollTop()>499)	$('#fixup').show(1000);
	else	$('#fixup').hide(1000);
});



</script>



</head>
<body  class="p1280">
<div style="fixup" id="fixup" name="fixup"><div id="uplink">Наверх</div></div>

<div id="page">

	

	<jsp:include page="/WEB-INF/views/template/header.jsp" />
<div id="wrapper">
<jsp:include page="/WEB-INF/views/template/mainmenu.jsp" />
</div>
<div style="padding: 0px; width: 100%;">
<div id = "wrapper">
<div id = "main">

        	<div id="mccart">
    		<div class="checkout-middle-title">
					<h1>ОФОРМЛЕНИЕ ЗАКАЗА</h1>
</div>
${result}
<c:if test = "${result == 'success'}">
<div id = "success"><p>Заказ добавлен!</p></div>
</c:if>

<c:if test = "${result == 'nogoods'}">
<div id = "fail"><p>Корзина с товарами пуста!</p></div>
</c:if>
<div id='content_ch'>
	<div class='zakaz_of'>
		<form action='${pageContext.request.contextPath}/send' method="POST" id="form" modelAttribute = "order"  onsubmit="return testname();">
		<input type="hidden" name="sessid" value="${sessid}"/>
		<table class='item_f'>
			<input type="hidden" name="action" value="sendorder">
			<tr>
				<td class='first_f'>
					<h3>Контактная информация</h3>
					<div class='cont'>
						<table>
							<tr>
								<td class='left_f error_f'>
								</td>
								<td class='right_f error_f' >
									<span><b class="bred">!</b> Ваше имя *  </span>
								</td>
							</tr>
							<tr>
								<td class='left_f line_f'>
									<label for='clientname_f' class='checkout_lf'><p>Ваше имя <span class="required">*</span></p></label>
								</td>
								<td class='right_f line_f'>
								<c:choose>
									<c:when test="${!empty user }">
									<input type="text" id="clientname_f" name="clientname" value="${user.name}" />
									</c:when>
									<c:otherwise>
									<input type="text" id="clientname_f" name="clientname" value="" />
									</c:otherwise>
								</c:choose>
									<b class='bred' id="fiofailed"></b>
								</td>
							</tr>
							<tr>
								<td class='left_f error_f'>
								</td>
								<td class='right_f error_f' >
									<p><span><b class="bred">!</b> Ваш телефон *  </span> </p>
								</td>
							</tr>
							<tr class='mobile_f'>
								<td class='left_f'>
									<label for='clientphone_f' class='checkout_lf'><p>Мобильный телефон <span class="required">*</span></p></label>
								</td>
								<td id="tmpp">
									<div id="phones">
									<c:choose>
									<c:when test="${!empty user || !empty user.phonenumbers}">
										<c:forEach items = "${user.phonenumbers}" var = "number">
										<div>
											<select class="phone_f" name="clientphones[0].country.phonecode">
												<option value="+380" >UA</option>
												<option value="+7">RU</option>
												<option value="+375">BEL</option>
												<option value="+373">MOL</option>
											</select>
											<input id = "clienphone_f" type="text" class="clientphone_f" name="clientphones[0].numb" value="${number.numb}">
										</div>
										</c:forEach>
									</c:when>
									<c:otherwise>
									<div>
											<select class="phone_f" name="clientphones[0].country.phonecode">
												<option value="+380" >UA</option>
												<option value="+7">RU</option>
												<option value="+375">BEL</option>
												<option value="+373">MOL</option>
											</select>
											<input id = "clienphone_f" type="text" class="clientphone_f" name="clientphones[0].numb" value="">
										</div>
									</c:otherwise>
									</c:choose>
																			</div>
									<div style="clear:both;margin:2px 0 0 0"><a onclick="plus()" style="text-decoration:none; color:black; cursor:pointer; margin-top:3px;"><img src="<c:url value="/resources/images/plus.png"/>"><span style="font:normal 10px arial"> Добавить дополнительный номер</span></a></div>
									<script type="text/javascript">
									var phonenum = 1;
									function plus(){ //Заменить
										$("#phones").append('<div style="clear:both"><select class="phone_f" name="clientphones['+phonenum+'].country.phonecode"><option value="+380">UA</option><option value="+7">RU</option><option value="+375">BEL</option><option value="+373">MOL</option></select><input onload="maskit(this)"  type="text" style="float:left;margin: 0 0 0 7px; width: 150px;" name="clientphones['+phonenum+'].numb" value="" style="display: block; "><div><div style="float:right;margin:5px 0 0 0"><a onclick="minus(this)" style="text-decoration:none; color:black; cursor:pointer;"><img src="<c:url value="/resources/images/minus.png"/>"></a></div></div></div>');
										phonenum++;
										$(".phone_f").selectbox();
										$(".mobile_f input").last().mask('+38 (099) 999-99-99');
										flggs();
									}
									function minus(that){//Заменить
										//$(that).parents().eq(2).find("select").prev().remove();
										$(that).parents().eq(2).remove();
									}
									</script>
									<script type="text/javascript">
									$('.selectbox').load(function() {
										flggs();
									});
									</script>
								</td>
							</tr>
							<tr>
								<td class='left_f error_f'>
								</td>
								<td class='right_f error_f' >
									<span><b class="bred">!</b> e-mail введен не верно *  </span>
								</td>
							</tr>
							<tr>
								<td class='left_f line_f'>
									<label for='clientemail_f' class='checkout_lf'><p>Электронный адрес <span class="required">*</span></p></label>
								</td>
								<c:choose>
									<c:when test="${!empty user}">
								<td>
									<input type="text" id="clientemail_f" name="clientemail" value="${user.username}"  />
								</td>
								</c:when>
									<c:otherwise>
									<td>
									<input type="text" id="clientemail_f" name="clientemail" value="" />
									</td>
									</c:otherwise>
								</c:choose>
							</tr>

						<!--	<tr class="organization_f"  style="display:none">
								<td class='left_f error_f'>
								</td>
								<td class='right_f error_f' >
									<span><b class="bred">!</b> Название организации *  </span>
								</td>
							</tr>
							<tr class="organization_f"  style="display:none">
								<td class='left_f line_f'>
									<label for='client_company' class='checkout_lf'>Название организации <span class="required">*</span></label>
								</td>
								<td>
									<input type="text" id="client_company" name="client_company" value="" >
								</td>
							</tr>
							<tr class="organization_f"  style="display:none">
								<td class='left_f space_f' colspan='2'>
								</td>
							</tr>
							<tr class="organization_f"  style="display:none">
								<td class='left_f line_f'>
									<label for='client_okpo' class='checkout_lf'>Код ОКПО&nbsp;&nbsp;<span style="font-size: 12px; font-weight: normal"><br>(не обязательно)&nbsp;&nbsp;</span></label>
								</td>
								<td>
									<input type="text" id="client_okpo" name="client_okpo" value="" style="display: block; ">
								</td>
							</tr>

							<tr>
								<td class='left_f space_f' colspan='2'>
								</td>
							</tr>
							<tr>
							-->
								<td class='left_f'>
									<label for='clientcomment_f' class='checkout_lf'><p>Комментарий:</p></label>
								</td>
								<td>
									<textarea id='clientcomment_f' style='resize: none;' name="clientcomment" rows='3'></textarea>
								</td>
							</tr>
							<tr class='srochnost_f'>
								<td class='left_f'>
								</td>
								<td class='right_f'>
									<label id="data_f" onmouseup="check(this)" for='srochnost' >
										<p class='checkbox_f'>
										
											<input id='srochnost' type='checkbox' name='clientsrochnost' style='display:none'  />
										</p>
										<p style='margin-top:14px'>Срочность заказа</p>
									</label>
									<div id='btn_sr' class="btn_help" onclick="return load_text(this, 'urgency', '200')"></div>
									<script>
									function check(that){
										if(!$('#srochnost').attr("checked"))
										{
											$("#data_f").addClass('checked_s');
											$(".date_f").show();
										}
										if($('#srochnost').attr("checked"))
										{
											$("#data_f").removeClass('checked_s');
											$(".date_f").hide();
										}
									}
									</script>
							</tr>
							<tr class='date_f' style="display:none">
								<td class='left_f'>
									<label for="clientcomment_f" class="checkout_lf"><p>Дата доставки:</p></label>
								</td>
								<td class='right_f'>
									<div>
										<input type="text" name="client_delivdate" id="date" value="">
										<input type="text" name="client_delivtime" id="time" value="">
									</div>
									<p class='data_msg_f'>Выберите крайний срок доставки</p>
								</td>
															</tr>

						</table>
						
						
						
						
					</div>
				</td>
				<td class='second_f'>
					<h3>Доставка и оплата</h3>
					
					<div id  = "eee"></div>
					<div id  = "dost" class='cont'>
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
											<c:choose>
											<c:when test="${!empty user || !empty user.town}">
											<input type="text" id="clienttown_f" name="clienttown_f" value="${user.town.name}" placeholder="Введите название города" placeholder2="Введите название города"/>
											<input type="hidden" name="clienttown" id="clienttown" value="${user.town.id}"/>
											</c:when>
											<c:otherwise>
											<input type="text" id="clienttown_f" name="clienttown_f" value="" placeholder="Введите название города" placeholder2="Введите название города"/>
											<input type="hidden" name="clienttown" id="clienttown" value=""/>
											</c:otherwise>
											</c:choose>
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
									//	$("#clienttown_f").attr('value', name);
									//	$("#clienttown_f").attr('title', name);
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
										//	$("#clienttown_f").val("");
										//	$("#clienttown").val(0);
											//$("#dostavka_f").hide();
										//	change_city();
										}
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
									<select name="clientoplata" id="clientoplata_f" onchange="select_clientoplata(this);">
										<c:forEach items = "${paytypes}" var = "paytype">
                						<option value="${paytype.id}" >${paytype.name}</option>
                						</c:forEach>
                						</select>
                						
									<script type="text/javascript">
										$("#clientoplata_f").live('change', function () {
											change_params();
										});
									</script>
											<tr>
								<td class="left_f space_f" colspan="2">
									</td>
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
									<select name="clientdeliverytype" id="clientdeliverytype_f" onclick="showhelp(this);" onchange="select_clientdeliverytype(this);">
                						<c:forEach items = "${deliverytypes}" var = "deliverytype">
                						<option value="${deliverytype.id}" >${deliverytype.name}</option>
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
								<c:if test="${!empty user || !empty user.town}">
							
							<tr id="adress_fr" >
								<td class='left_f'>
									<label for='clientdeliverytype_f' class='checkout_lf'><p id="adress_title">Адрес доставки&nbsp;&nbsp;</p></label>
								</td>
								<td class='right_f' id="delivery_td">
									
									
									<!-- Если доставка по городу -->
									<div id="adress_pg">

										

										<div id="per" style="float: left;">
											<select class="fsel" name="type_street" id="adress_f" value = "${user.adress.street_type}">
												<option value="улица" >улица</option>
												<option value="проспект" >проспект</option>
												<option value="площадь" >площадь</option>
												<option value="бульвар" >бульвар</option>
												<option value="переулок" >переулок</option>
												<option value="проезд" >проезд</option>
												<option value="въезд" >въезд</option>
											</select>
										</div>
										<input  type='text' title="Название" placeholder="Название" placeholder2="Название" name="street" id="ulica" value="${user.adress.street_name}" style="float: left;"><br>
										<div style='text-align:left; padding-top:10px; clear: left;'>
											<input  type='text' title="№ Дома" placeholder="№ Дома" placeholder2="№ Дома" name="house" id="nhouse" value="${user.adress.house_num}">
											<input type='text' title="Этаж" placeholder="Этаж" placeholder2="Этаж" name="flor" id="floar" value="${user.adress.level}">
											<input type='text' title="№ Квартиры" placeholder="№ Квартиры" placeholder2="№ Квартиры" name="room" id="nroom" value="${user.adress.room_num}">
										</div>
										<div id="add_param">
											<div style="clear:both">
												<p style='clear:both; float: left'>Нужен занос?</p>
												<select name="nzanos" id="zanos">
													<option value="0" selected>Нет</option>
													<option value="1" >Да</option>
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
												Оплачивается только занос товара весом от 30 кг.<br/>
												Если товар габаритный, но помещается в лифт, оплата
												производится за первый и последний этажи.
											</div>
										</div>
									</div>
												</td>
							</tr>
							</c:if>
	
													</table>
					
					
					</div>
					<!--  									<input type="hidden" name="srok_cred"  value=""/>
                					<input type="hidden" id="client_company" name="client_company" value=""/>
                					<input type="hidden" id="client_okpo" name="client_okpo" value="" />		
                					<input name = "nzanos" type = "hidden" value = ""></input>
									<input name = "type_street" type = "hidden" value = ""></input>
									<input name = "street" type = "hidden" value = ""></input>
									<input name = "house" type = "hidden" value = ""></input>
									<input name = "flor" type = "hidden" value = ""></input>
									<input name = "room" type = "hidden" value = ""></input>
									<input name = "filial_adress" type = "hidden" value = ""></input>
									<input name = "nzanos" type = "hidden" value = ""></input>
									<input name = "netaj" type = "hidden" value = ""></input>
									<input name = "nlift" type = "hidden" value = ""></input>-->
				</td>
				
				

				
				
				
				<td class='third_f'>
					<div class='checkout_f'>
						<div style="margin: 20px auto; width: 150px; text-align: center;">
							Идет загрузка...<br/>
							<img src="img/loader.gif" align="center" >
						</div>
											</div>

					
			<!--  		<div class='discount_f' id="promoballsblock" style="height: 140px;">
						<div id='radio_f'>

							
							<div>
								<label class='' onclick="refresh_cart('c4vp2biauqd93btpr1mf2dlor3', '0', '');">
									<p class="chkdsk">
										<input type="radio" style="display:none" id="saleball" name="skidka"  value="ball">
									</p>
									<p class='text_f'>Использовать баллы (как получить карту)</p>
								</label>
							</div>
							<div>
								<label class='checked_f' onclick="refresh_cart('c4vp2biauqd93btpr1mf2dlor3', '0', '');">
									<p class="chkdsk">
										<input type="radio" style="display:none" name="skidka" checked='checked' value="diskount">
									</p>
									<p class='text_f'>У меня есть промо-код</p>
								</label>
							</div>

							
						</div>
				
						<div class='white_question' onclick="return load_text(this, 'ball_promo', '0')"></div>
						<div style="clear:both; display: none;" class="promo_f" id="ballsinput">
							Укажите номер дисконтной карты в личном кабинете						</div>
						<div style="clear:both; display: block;" class="promo_f" id="promoinput">
							<p style="color:white;font:normal 10px arial">Вы вводите промо-код</p>
							<div id="promo_input" style="display: block;">
								<input type="text" name="promo" id="promo" placeholder="Например, 1234-1234-1234-1234" placeholder2="Например, 1234-1234-1234-1234"><label><p class="recount"><label onclick="return setpromo(this, 'c4vp2biauqd93btpr1mf2dlor3')"><span>Пересчитать</span><input type="submit" style="display:none"></p></label>
							</div>
							<div id="promo_ok" style="padding: 0px; font-weight: normal; font-size: 12px; display: none;">
		                        Введен промо-код: <b></b>
                                <br />
                                <a href="javascript:void();" onclick="delpromo('c4vp2biauqd93btpr1mf2dlor3', '')">отменить использование промо-кода</a>
							</div>
							<div id="promo_no" style="padding: 0px; font-weight: normal; font-size: 12px; display: none;"></div>
						</div>



						<script type='text/javascript'>
						$("#radio_f label").live('click', function () {
							if(!$(".discount_f").last().hasClass("disabled_f"))
							{
								var refresh = false;
								if($("#pupuse"))
								{
									if($("#pupuse").parent().parent().hasClass("checked_f"))
									{
										refresh = true;
									}
								}
								$("#radio_f label").removeClass("checked_f");
								$(this).addClass("checked_f");
								if($(this).children().children().attr("value") == "ball")
								{
									$("#promoinput").hide();
									$("#ballsinput").show();
								}
								else if($(this).children().children().attr("value") == "diskount")
								{
									$("#ballsinput").hide();
									$("#promoinput").show();
								}
								else
								{
									$("#ballsinput").hide();
									$("#promoinput").hide();
									refresh = true;
								}

								if(refresh)
								{
									refresh_cart('c4vp2biauqd93btpr1mf2dlor3', '0', '');
								}
							}
						});
						</script>
-->
					</div>


					<input type="hidden" name="fopcredit" id="fopcredit" value="0"/>
					<input type="hidden" name="txtforcredit" id="txtforcredit"/>
					<input type="hidden" name="txtfordelivery" id="txtfordelivery"/>
                    <input type="hidden" name="txtfordelivery_nafilial" id="txtfordelivery_nafilial"/>
                    <input type="hidden" name="txtfordelivery_dodveri" id="txtfordelivery_dodveri"/>

				</td>
			</tr>
			<tr>
				<td style="padding-left: 181px; padding-top:30px">
					<label class='back_btn_f'><p onclick="history.back();"><span>Вернуться к покупкам</span></p></label>
				</td>
				<td  style="padding-left: 133px; padding-top:30px">
					<input type="submit" title="Оформить заказ" alt="Оформить заказ" class="btnorder" value="" style="border: none;">
				</td>
				<td  style=" padding-top:5px">
					<div class='diskount_bottom_f' style="margin: 20px 0 0">
						<p>
						<span>!</span>
						Оформление заказа не обязывает Вас к покупке. Наши менеджеры свяжутся с Вами для подтверждения и согласования деталей заказа.
						В ходе телефонного разговора можно внести изменения в заказ или отменить его.
						</p>
						<br/>
						<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Нажав кнопку «Оформить заказ», вы автоматически принимаете <a href="http://www.mobilluck.com.ua/info.php?page=using" target="_blank">пользовательское соглашение</a>.
						</p>
					</div>
				</td>
			</tr>
		</table>
		</form>
	</div>
</div>
<div id = "bucket"></div>



<script src="http://www.mobilluck.com.ua/js/jquery.selectbox.js"></script>
<link href="http://www.mobilluck.com.ua/css/selectbox.css" rel="stylesheet" />
<link type="text/css" rel="stylesheet" href="http://www.mobilluck.com.ua/css/date_style.css" />

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
flggs();
</script>
<script type='text/javascript'>
	var red_order = 0;
	var delivery_delivery = 0;

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

	$(document).ready(function() {
		
		$("#date").mask("9999-99-99");
		 // ---- Календарь -----
			$('#date').attachDatepicker({
				yearRange: '2014:2015',
				firstDay: 1,
				minDate: 0,
				defaultDate: +4,
				dateFormat:'yy-mm-dd',
				closeText:"<span id='dialog_close' href='#'></span>"
			});
		
		/* Placeholder for IE */
		if($.browser.msie) { // Условие для вызова только в IE
			$("#form").find("input[type='text'][placeholder2]").each(function() {
				var tp = $(this).attr("placeholder2");
				$(this).attr('value',tp).css('color','#ccc');
			}).focusin(function() {
				var val = $(this).attr('placeholder2');
				if($(this).val() == val) {
					$(this).attr('value','').css('color','#303030');
				}
			}).focusout(function() {
				var val = $(this).attr('placeholder2');
				if($(this).val() == "") {
					$(this).attr('value', val).css('color','#ccc');
				}
			});

			/* Protected send form */
			$("#form").submit(function() {
				$(this).find("input[type='text'][placeholder2]").each(function() {
					var val = $(this).attr('placeholder2');
					if($(this).val() == val) {
						$(this).attr('value','');
					}
				})
			});
		}

		
		
		
		// ---- Календарь -----

		$("#zanos").bind("change", function(){
			if($("#zanos").val() == 0)
			{
				$("#netajbl").hide();
				$("#nliftbl").hide();
			}
			else
			{
				$("#netajbl").show();
				$("#nliftbl").show();
			}
		});

		$("#etag").bind("change", function(){
			$("#zanos").trigger("change");
		});

		$("#lift").bind("change", function(){
			$("#zanos").trigger("change");
		});

		$("#zanos").trigger("change");
	});

	function change_adres(el)
	{
		var val = el.value;
		var val_array = val.split(";");
		if(val_array[0]!="")
		{
			if(val_array[0]!=$("#clienttown").val())
			{
				$("#clienttown").val(val_array[0]);
				$.ajax({
					type: "POST",
					url: "/ajx/ajx_cart.php",
					data: 'cmd=uh_com_get_cityname_by_code&city_id='+val_array[0],
					dataType: "text",
					success: function(data)
					{
						cityid = val_array[0];
						$("#clienttown_f").val(data);
						change_city();
					}
				});
			}

			$("#adress_f").val(val_array[1]);
			$("#adress_f").prev().remove();
			$("#adress_f").selectbox();
			$("#ulica").val(val_array[2]);
			$("#nhouse").val(val_array[3]);
			$("#floar").val(val_array[4]);
			$("#nroom").val(val_array[5]);
		}
		else
		{
			$("#adress_f").val(1);
			$("#ulica").val("");
			$("#nhouse").val("");
			$("#floar").val("");
			$("#nroom").val("");
		}
	}

	function change_city()
	{
		if(($("#clienttown").val()>0)&&($("#clienttown_f").val()==""))
		{
			$.ajax({
				type: "POST",
				url: "/ajx/ajx_cart.php",
				data: 'cmd=uh_com_get_cityname_by_code&city_id='+$("#clienttown").val(),
				dataType: "text",
				success: function(data)
				{
					if(data!="")
					{
						$("#clienttown_f").val(data);
						$("#clienttown_f").attr("title", data);
					}
				}
			});
		}
		change_payment();
	}

	function change_params(){
		var city_id = $("#clienttown").val();
		var payment_id = $("#clientoplata_f").val();
		var delivery_id = $("#clientdeliverytype_f").val();
		var url = "<c:out value="${pageContext.request.contextPath}"/>";
	//	alert("here");
		$.ajax({
			type: "POST",
			url: url+"/changeparams",
			data:{"city_id":city_id, "delivery_id":delivery_id, "payment_id":payment_id},
			dataType: "text",
			success: function(data)
			{
		//	alert(data);
				$("#dost").html(data);
				$("#clientoplata_f").selectbox();
				$("#clientdeliverytype_f").selectbox();	
			},
			error : function(xhr, status, error){
	            alert("Error!" + xhr.status);
	        }
		});
	}
	
	function change_payment()
	{

		var city_id = $("#clienttown").val();
		var url = "<c:out value="${pageContext.request.contextPath}"/>";
		var tmp_array = Array();
		$.ajax({
			type: "POST",
			url: url+"/changepayment",
			data: {"cmd":uh_com_get_payment_type, "city_id":city_id},
			dataType: "text",
			success: function(data)
			{
				var tmp = data.split("|");
				tmp_array = tmp[1].split(";");

				//alert($('#clientoplata_f').val()+" = "+data);
				if(!in_array($('#clientoplata_f').val(), tmp_array))
					$("#clientoplata_f [value='"+tmp[0]+"']").attr("selected", "selected");

				$('#clientoplata_f option').each(function()
				{
					if(!in_array(this.value, tmp_array))
						this.disabled = "disabled";
					else
						this.disabled = "";
				});
				$("#clientoplata_f").prev().remove();
				$("#clientoplata_f").selectbox();
				var payment_id = $("#clientoplata_f").val();
				show_hide_blocks(payment_id);
				change_delivery();
			}
		});

	}



	function load_branch()
	{
		var city_id = $("#clientdeliverytype_f option:selected").attr("fid");
		var delivery_id = $("#clientdeliverytype_f").val();
		var payment_id = $("#clientoplata_f").val();
		var url = "<c:out value="${pageContext.request.contextPath}"/>";
		$("#filial_adress_f").empty();
		$("#label_branch").html("Идет загрузка списка ... ");

		$.ajax({
			type: "POST",
			url: url+"/loadadresssect",
			data:{"city_id":city_id, "delivery_id":delivery_id, "payment_id":payment_id},
			dataType: "text",
			success: function(data)
			{
				//alert(data);
				var tmp = data.split(/\r?\n/);
				$("#label_branch").html(tmp[0]);
				$("#filial_adress_f").empty();
				for(i=1; i<tmp.length; i++)
				{
					var dis = "";
					var el = tmp[i].split(";");
					if(el[0] == 0)
						dis = 'disabled="disabled"';
					$("#filial_adress_f").append( $('<option value="'+el[1]+'" '+dis+'>'+el[2]+'</option>') );
				}
				$("#filial_adress_f").prev().remove();
				$("#filial_adress_f").selectbox();
			}
		});
	}

	function delivery_type(delivery)
	{
		var city_id = $("#clienttown").val();
		if(city_id > 0)
		{
			$("#adress_fr").show();
			var delivery_block_id = Array("", "adress_pg", "adress_filial", "adress_filial", "adress_pg", "adress_filial", "adress_pg", "adress_filial", "adress_pg");
			$("#delivery_td").children().hide();
			$("#"+delivery_block_id[delivery]).show();
			if($("#myadres"))
			{
				if(delivery == 1)
					$("#myadres").show();
				else
					$("#myadres").hide();
			}
		}
		else
			$("#adress_fr").hide();
	}

	function show_hide_blocks(payment)
	{
		switch(payment)
		{
			case "1":
				// Промо и баллы
				$(".discount_f").removeClass("disabled_f");
				$(".discount_f input").removeAttr("disabled");
				$("#hdnmsg").css("display","none");

				//Юр.лица
				$(".organization_f").css("display","none");

				//Кредит
				$("#kredit_f").css("display","none");
				var pcval = makeCookie("prcredit", "0", dt.toUTCString(), "/", window.location.hostname);
				setCookie( pcval );
				break;

			case "2":
				// Промо и баллы
				$(".discount_f").addClass("disabled_f");
				$(".discount_f input").attr("disabled","true");
				$("#hdnmsg").css("display","table-row");

				//Юр.лица
				$(".organization_f").css("display","table-row");

				//Кредит
				$("#kredit_f").css("display","none");
				var pcval = makeCookie("prcredit", "0", dt.toUTCString(), "/", window.location.hostname);
				setCookie( pcval );
				break;

			case "7":
				// Промо и баллы
				$(".discount_f").addClass("disabled_f");
				$(".discount_f input").attr("disabled","true");
				$("#hdnmsg").css("display","table-row");

				//Юр.лица
				$(".organization_f").css("display","none");

				//Кредит
				$("#kredit_f").css("display","none");
				var pcval = makeCookie("prcredit", "0", dt.toUTCString(), "/", window.location.hostname);
				setCookie( pcval );
				break;

			case "14":
				// Промо и баллы
				$(".discount_f").addClass("disabled_f");
				$(".discount_f input").attr("disabled","true");
				$("#hdnmsg").css("display","table-row");

				//Юр.лица
				$(".organization_f").css("display","none");

				//Кредит
				$("#kredit_f").css("display","table-row");
				var pcval = makeCookie("prcredit", "2", dt.toUTCString(), "/", window.location.hostname);
				setCookie( pcval );
				break;
		}
	}

	function in_array(what, where)
	{
		length_array = where.length;
		for(var i=0; i<length_array; i++)
		{
			//alert(what+" == "+where[i]);
			if(what == where[i])
	            return true;
		}
	    return false;
	}

	function refresh_cart(sesid, usrbal, usrdisc)
	{
		var htmltext =  '<div style="margin: 20px auto; width: 150px; text-align: center;">'+
					    'Идет загрузка...<br/>' +
						'<img src="img/loader.gif" align="center" >' +
						'</div>';
		$(".checkout_f").html(htmltext);

		var ds = $("#clientdeliverytype_f").val();
		var os = $("#clientoplata_f").val();
		var twn = $("#clienttown").val();
		var zanos = $("#zanos").val();
		var etag = $("#etag").val();
		var lift = $("#lift").val();

		if(ds != 1)
		{
			zanos = 0;
			etag = 0;
			lift = 0;
		}

		var tt = "";
		if($("#plus_snb") && $("#saleball").parent().parent().hasClass("checked_f"))
		{
			tt = '&snb='+$("#plus_snb").val();
		}

		if($("#pupuse"))
		{
			if($("#pupuse").parent().parent().hasClass("checked_f"))
			{
				tt += '&pupuse=1';
			}
		}

		var bl = 0;
		var rt = red_order;
		var url = "<c:out value="${pageContext.request.contextPath}"/>";
		if($('#balls'))
		{
			bl = $('#balls').val();
		}
		if(bl=="На Вашей карте "+usrbal+" баллов")
			bl = 0;
		if(bl*1>usrbal*1)
		{
			bl = usrbal;
			$('#balls').val(bl);
		}
	//	alert(url+"/ordergoods");
		$.ajax({
			type: "POST",
			url: url+"/ordergoods",
			data: {"section":"ordergoods"},
			//'cmd=uh_com_cartitems_sm&csi='+sesid+'&ds='+ds+'&os='+os+'&rt='+rt+'&bl='+bl+'&dct='+usrdisc+"&twn="+twn+tt+'&delivdeliv='+delivery_delivery+'&zanos='+zanos+'&etag='+etag+'&lift='+lift	,
			dataType: "text",
			success: function(data){
//				alert(data);
				if( data != "" )
				{
					$(".checkout_f").html(data);
				}
			}
		});
		return false;
	}



	function loadup(url, section)
	{
		$("#popup_cart_title").html("Корзина заказов");
		$("#popup_cart_content").html("<br /><br />Идет подгрузка информации...<br /><br />");

		$("#popupbg").show();
		$("#popup_wrapper").show();

		ui_cart_view(url, section, true, -1);
		return false;
	}

	var ajxid = false;





	function load_text(lnk, type, left_correct)
	{
		var ww = $(window).width(), wh = $(window).height();
		var left = this.offsetLeft, top = this.offsetTop;
		if (navigator.userAgent.toLowerCase().indexOf("msie") != -1){left += (ww-978)/2;}

		var coor = getOffset( lnk );
		left = coor.left;
		top = coor.top;

		left = left - left_correct;

		var wd = 500;
		$("#dialog_title").html("");
		$("#dialog_content").html("<br/><br/>Идет загрузка данных...<br/><br/>");
		showPopupDlg(left, top, wd);

		if(type == "ball_promo")
		{
			type = "ball";
			if($("#ballsinput").css("display")=="none")
				type = "promo";
		}

		$.ajax({
			type: "POST",
			url: "/ajx/ajx_cart.php",
			data: 'cmd=uh_com_text&type='+type,
			dataType: "text",
			success: function(data){
				var ar = data.split("|");
				wd = ar[0];
				$("#dialog_title").html(ar[1]);
				$("#dialog_content").html(ar[2]);
			}
		});

		return false;
	}

	function testname()
	{
		//alert("wegwg");
		var tmp = $("#clientname_f").css("background-color");
		if($("#clienttown_f").val()=="" || $("#clienttown").val()==""){
			alert("Заполнить город обязательно!");
			return false;
		}
		if($("#clientname_f").val()==""){
			alert("Заполнить имя обязательно!");
			return false;
		}
		
		if($("#clientemail_f").val()==""){
			alert("Заполнить емейл обязательно!");
			return false;
		}
		
		if(tmp!="rgb(255, 255, 0)")
		{
			$("#fiofailed").html("");
			var del = $("#clientdeliverytype_f").val();
			if(del>2)
			{
				var name = $("#clientname_f").val();
				rE1 = new RegExp("^[А-Яа-я\.\', іІїЇєЄ\-]{2,}[ ][А-Яа-я\.\', іІїЇєЄ\-]{2,}[ ][А-Яа-я\.\', іІїЇєЄ\-]{2,}$");
				if (!rE1.test(name))
				{
					$("#clientname_f").css("background-color", "yellow");
					document.getElementById('clientname_f').focus();
					$("#fiofailed").html("<br/>При отправке заказа через службу доставки необходимо указать полностью ФИО.");

					return false;
				}
			}
		}
		return true;
	}

</script>

			</div>
<div class="both"></div></div>
	<noindex>
		<div id="foot-items">
		<table>
		<tbody>
			<tr>
				<td>
					
				</td>
				<td>
					
					</td>
				</tr>
			</tbody>
			</table>
	</div>
		</noindex>
		</div></div></div>

	
		<jsp:include page="/WEB-INF/views/template/footer.jsp"/>

	<!--  <div id="popupbg"></div>-->
	<div id="dialog_wrapper">
		<div id="dialog_outer">
			<div id="dialog_inner">
				<a id="dialog_close" href="#">Закрыть</a>
				<div id="dialog_title">Войти в интернет-магазин</div>
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


<!--  		<div id="popup_wrapper">
			<div id="popup_outer" style="width: 680px;">
				<div id="popup_content">
					<a id="popup_close" href="#">Закрыть</a>
					<p id="popup_cart_title" class="mgood_title">Корзина покупок</p>
					<div style="clear: left; float: left; width: 100%;" id="popup_cart_content">
						g 3rgkelkrg lkwejrlkgwelkrglkwejrlkgjwelkr gjlwerlkgjwerglkwerlk
					</div>
					<div class="both"></div>
				</div>
			</div>
		</div>
-->
<div id="bucket"></div>

		</body>
</html>


