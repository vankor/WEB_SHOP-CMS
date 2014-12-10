<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>${category.page.title}</title>
<jsp:include page="/WEB-INF/views/head.jsp" />


<link rel="stylesheet" type="text/css" href="http://css.mobilluck.com.ua/css/portal2014.css"/>



<script type="text/javascript" src="http://js.mobilluck.com.ua/js/jquery.autocomplete.js"></script>
<script type="text/javascript" src="http://js.mobilluck.com.ua/nd/js/slides.min.jquery.js">
</script>
<script type="text/javascript">

url = "<c:out value="${pageContext.request.contextPath}"/>" +"/category/"+ "<c:out value="${category.id}"/>";

	
</script>



<script type="text/javascript" charset="utf-8">
$(function() {
$("img").lazyload({ threshold : 100, effect : "fadeIn", failurelimit : 100 });
});
</script>

</head>
<body  class="p1280">
<div style="fixup" id="fixup" name="fixup"><div id="uplink">Наверх</div></div>

<div id="page">
	<link rel="stylesheet" type="text/css" href="http://www.mobilluck.com.ua/css/all20132.css" />
	
<div id ="headerwrapper">
<jsp:include page="/WEB-INF/views/header.jsp" />
<div id="wrapper">
<jsp:include page="/WEB-INF/views/mainmenu.jsp" />
</div>
</div>

					<div style="padding: 0px; width: 100%;">
						<div style="width: 100%; height: 39px; display:none">
							<div style="background-color: #E9BB3A; width: 100%; height: 37px;">
								<div style="width: 990px; margin: 0 auto;">
																		<a href="http://www.mobilluck.com.ua/sale.php" rel="nofollow"><img src="http://www.mobilluck.com.ua/files/megasale1.png"/></a>
								</div>
							</div>
						</div>
						<div id="wrapper" style="margin:0 auto; padding-top: 0px;">
					</noindex>
<div id="main" >				<div id="content1">
					<div class="cbreadcrumbs" id="breadcrumbs" style="font-size: 12px;">

				<div id="compdiv1" style="display: none;">
                <form action="http://www.mobilluck.com.ua/compare_all.php" name="cmpfrm" method="POST">
               	 <div id="comppan1p" style="padding:0">
             		   	<p><a href="/compare_all.php">Сравнить <span id="comppnum">0</span> товары</a></p>
             		   	<div id="comppanp"></div>
            		</div>
            	</form>
            	</div>				
            	
           	 	<div itemscope itemtype="http://data-vocabulary.org/Breadcrumb" style="display: inline;">
					<a itemprop="url" href="${pageContext.request.contextPath}"><span itemprop="title">Главная</span></a> ›
				</div>
				
				<c:forEach items = "${pathtocategory}" var = "cat">
           		     <div itemscope itemtype="http://data-vocabulary.org/Breadcrumb" style="display: inline;">
          		  	<a itemprop="url" href="<c:url value = "/catalog/${cat.page.fullurl}/pagin/1"/>" title=" Телефоны и плееры">
          		  	<span itemprop="title"> ${cat.name}</span></a> › </div>
          		</c:forEach>
					<div itemscope itemtype="http://data-vocabulary.org/Breadcrumb" style="display: inline;">
					<span itemprop="title">${category.name}</span>
					</div>            
			</div>
										<h1 class="mgood_title">${category.name}</h1>

					
											<div class="subnavigation">
							<div class="showall" id="fltr_0"><p><a href="#" onclick="return loadsect(${category.id}, 0)">Все каталоги</a></p></div>
							
															<c:forEach items = "${subcategs}" var = "cat" varStatus = "j">
															<div id="fltr_${cat.id}"><p><a role = "button" <c:choose><c:when test = "${cat.isFinalCategory == true && empty cat.subcategs[0]}">href="${pageContext.request.contextPath}/catalog/${cat.page.fullurl}/pagin/1" </c:when><c:otherwise>onclick="return loadsect(${category.id}, ${cat.id})"</c:otherwise></c:choose>> ${cat.name} </a></p></div>
															
															</c:forEach>
														</div>
					<!--  					<c:forEach items = "allsubcats">-->	
											<div id="catalog2"></div>
										<!-- 	</c:forEach>
					-->	
					<script>
					var datasect = Array(parseInt("<c:out value="${subcategscount}"/>")+1);
					
					datasect[0] = '<table><tbody><tr><c:forEach items = "${allsubcategs}" var = "cat"><td><a href="${pageContext.request.contextPath}/category/'+parseInt("<c:out value="${cat.id}"/>")+'"><p>${cat.name}</p><div class="cat_photo"><img src="<c:url value="/resources/images/${cat.thumb}" />" data-original="<c:url value="/resources/images/${cat.thumb}" />"></div></a></td></c:forEach></tr></tbody></table>';
	
					function loadsect(gid, dgid)
					{
						url = "<c:out value="${pageContext.request.contextPath}"/>" +"/category/"+ "<c:out value="${category.id}"/>"+"/pagin/1";
						//alert(url+"/listsubcategsection"+"  "+ gid+ "   "+  dgid);
						if(datasect[dgid] === undefined)
						{
							
							$.ajax({
								type: "POST",
								url: url+"/listsubcategsection",
								data: {"section":"subcatlist", "par_id":gid, "cat_id":dgid,},
								success: function(data){
									
									$("#catalog2").html(data);
									datasect[dgid] = data;
								},
								 error : function(xhr, status, error){
							            alert("Error!" + xhr.status);
								 }
							});
							
						}
						else
						{
							$("#catalog2").html(datasect[dgid]);
							$("img").lazyload({ threshold : 100, effect : "fadeIn", failurelimit : 100 });
							$("html, body").animate( { scrollTop: $(document).scrollTop()+1}, "1");
							$("html, body").animate( { scrollTop: $(document).scrollTop()-1}, "1");
						}
						$(".subnavigation").children().removeClass("showall");
						$("#fltr_"+dgid).addClass("showall");
						return false;
					}
					loadsect(11, 0);
					</script>



											<div class="midd-sep">
							<div class="mid-sep-tittle"><p>ВЫБОР ПО БРЕНДУ</p></div>
							<div class="all-tipe-button"><p><a href="http://www.mobilluck.com.ua/brands/" rel="nofollow">Все бренды</a></p></div>
						</div>
						<ul class="logos-list">
							<c:forEach items = "${category.subcategsproducers}" var = "producer">
							<li><a href="" title="${producer.name}"><img src="<c:url value="/resources/images${producer.thumb}" />" data-original="<c:url value="/resources/images${producer.thumb}" />" alt="${producer.name}"></a></li>
							</c:forEach>
						</ul>
										</div>

				<noindex>

				
		<!-- 		<div class="midd-question">
					<div class="mid-sep-tittle"><p>Остались вопросы?</p></div>
				</div>
			  	<div id="question-block">
					<table>
						<tr>
							<td class="search-q">
								<p>Не удалось найти желаемый товар?</p>
								<div class="search-bq">
									<p class="sertit">Воспользуйтесь поиском по сайту</p>
									<div class="search-place">
										<form class="search-q" action="http://www.mobilluck.com.ua/search.php" method="get" id="srch2">
											<input class="searchq" name="sw" id="searchq" type="text" placeholder="Введите название товара">
											<label class="find-q"><input type="submit"><p class="but_sub">Найти</p></label>
										</form>
										<script>
										jQuery('#srch2').submit(function(){
											if(($("#searchq").val()=="")||($("#searchq").val()=="Введите название товара"))
												return false;
										});
										</script>
									</div>


									<div><p class="sertit">Быстрый переход в категорию</p></div>
									<div id="fast-travel-block">
										<form id="go-to" onSubmit="return false">
											<input id="fast-travel" class="fast-travel" type="text" placeholder="Введите название категории товаров"><label class="arrow-fast">&nbsp;<input type="submit" style="display:none"></label>
										</form>
									</div>
									<script type="text/javascript">
									$(document).ready(function()
									{
										$("#fast-travel").autocomplete( "/ajx/fast_category.php",
												{
													matchSubset:1,
													cacheLength:10,
													maxItemsToShow:10,
													height: 400,
													width: 305,
													open: function( event, ui ){
														$(".ac_results").parent().css("width", "305px");
													},
													onItemSelect: function(data){
														$("#go-to").attr('action', data.extra);
														if(lastKeyPressCode==13) window.location.href=$("#go-to").attr('action');
													}
												});
										$("#go-to").bind("submit", function(obj){
											if($(this).attr('action')) window.location.href=$(this).attr('action');
										});
									});
									$(".ac_results ul li").live("mousedown", function(){ this.click(); });

									</script>
								</div>
							</td>
							<td class="callus-q">
								<p style="font:bold 18px arial;padding-bottom:11px">Нужна помощь в выборе?</p>
								<p style="font:14px arial;padding-bottom:7px;">Бесплатно по Украине</p>
								<p style="font:14px arial;color:rgb(0,125,183);padding-bottom:7px">0 800 505-333</p>
								<p style="font:14px arial;padding-bottom:9px">Многоканальная линия</p>
								<p style="font:14px arial;color:rgb(0,125,183);padding-bottom:6px">067 62-62-399</p>
								<p style="font:14px arial;color:rgb(0,125,183);padding-bottom:6px">067 21-9999-6</p>
								<p style="font:14px arial;color:rgb(0,125,183);padding-bottom:6px">044 323-03-34</p>
								<p style="font:bold 14px arial;color:rgb(0,125,183)"><a style="text-decoration: none; border-bottom: 1px dashed rgb(0,125,183)" href="#" onclick="return recall(this, -10)">Заказать обратный звонок</a></p>
							</td>
							<td class="qa-q">
								<p style="font:bold 18px arial;padding-bottom:12px;">Вопросы и пожелания</p>
								<p style="font:bold 14px arial;color:rgb(0,125,183);padding-bottom:5px;"><a href="http://www.mobilluck.com.ua/info.php?page=order_pay" target="_blank" rel="nofollow">Вопросы и ответы</a></p>
								<p style="font:12px arial">Если возникли трудности в процессе использования сайта или вопросы о процессе покупки.</p>
								<div id="t-buttons">
									<div id="say-thanks" onclick="feedback(1, this, 150)"><p>Поблагодарить</p></div><div id="say-complaint"  onclick="feedback(7, this, 150)"><p>Пожаловаться</p></div>
								</div>
								<p style="font:12px arial">Будем благодарны за положительные отзывы и конструктивные пожелания</p>
							</td>
						</tr>
					</table>
				</div>-->
				</noindex>

				    <script language="javascript">
    function show_hide_filter(id)
    {
        var vis = $('#f_'+id).css('display');
        //alert(vis);
        if(vis=='block')
        {
            $('#f_'+id).hide(500);
            $('#img_'+id).attr('src', 'http://www.mobilluck.com.ua/img/filter_down.png');
        }
        else
        {
            $('#f_'+id).show(500);
            $('#img_'+id).attr('src', 'http://www.mobilluck.com.ua/img/filter_up.png');
        }
    }

    function subscribe_product(prodid, lnk)
    {
    	var ww = $(window).width(), wh = $(window).height();
    	var left = this.offsetLeft, top = this.offsetTop;
    	if (navigator.userAgent.toLowerCase().indexOf("msie") != -1){left += (ww-978)/2;}

        var coor = getOffset( lnk );
        left = coor.left;
        top = coor.top;

        $("#dialog_title").html(dialogs[4][0]);
        $("#dialog_content").html(dialogs[4][1]);

        showPopupDlg(left, top);
        $(".dlgform").find("#frmprodid2").val(prodid);
        return false;
    }

        </script>
    <div class="both"></div></div>	</div></div></div>

	
		<jsp:include page="/WEB-INF/views/footer.jsp" />
	
    <script language="javascript">

	function showmsg(topic, lnk, ttop)
	{
		var ww = $(window).width(), wh = $(window).height();
		var left = this.offsetLeft, top = this.offsetTop;
		if (navigator.userAgent.toLowerCase().indexOf("msie") != -1){left += (ww-978)/2;}

		var coor = getOffset( lnk );
		left = coor.left - 150;
		top = coor.top - ttop;

		var title = "";
		var content = "";
		switch(topic)
		{
			case 1:
				title = "Мой кабинет";
				content = "<p style='text-align: justify;'>Для входа в Личный кабинет необходимо <a href='http://www.mobilluck.com.ua/input.php?a=makereg' onclick=\"$('body,html').animate( {scrollTop: 0}, 1000, 'swing'); return login($('#nlogin'), 200);\" class='popupc'>зарегистрироваться на сайте</a>. Это займет не более минуты, особенно если использовать логин через социальные сети.<br/>Если у вас уже есть личный кабинет, то вы можете <a href='http://www.mobilluck.com.ua/input.php?a=makereg' onclick=\"$('body,html').animate( {scrollTop: 0}, 1000, 'swing'); return login($('#nlogin'), 200);\" class='popupc'>войти в него сейчас</a>.<br/><br/>В Личном кабинете в удобной форме доступна информация о бонусных балах, ваших заказах, отзывах на товары, а также вы можете следить за ценами на желаемые товары.<br/><br/>Также, зарегистрированные пользователи получают возможность покупать товары по Клубным ценам.</p>";
				break;
			case 2:
				title = "Мои заказы";
				content = "<p style='text-align: justify;'>Для входа в Личный кабинет необходимо <a href='http://www.mobilluck.com.ua/input.php?a=makereg' onclick=\"$('body,html').animate( {scrollTop: 0}, 1000, 'swing'); return login($('#nlogin'), 200);\" class='popupc'>зарегистрироваться на сайте</a>. Это займет не более минуты, особенно если использовать логин через социальные сети.<br/>Если у вас уже есть личный кабинет, то вы можете <a href='http://www.mobilluck.com.ua/input.php?a=makereg' onclick=\"$('body,html').animate( {scrollTop: 0}, 1000, 'swing'); return login($('#nlogin'), 200);\" class='popupc'>войти в него сейчас</a>.<br/><br/>Сервис Мои заказы позволяет отслеживать статус нового заказа и просматривать историю совершенных покупок.</p>";
				break;
			case 3:
				title = "Мои товары";
				content = "<p style='text-align: justify;'>Для входа в Личный кабинет необходимо <a href='http://www.mobilluck.com.ua/input.php?a=makereg' onclick=\"$('body,html').animate( {scrollTop: 0}, 1000, 'swing'); return login($('#nlogin'), 200);\" class='popupc'>зарегистрироваться на сайте</a>. Это займет не более минуты, особенно если использовать логин через социальные сети.<br/>Если у вас уже есть личный кабинет, то вы можете <a href='http://www.mobilluck.com.ua/input.php?a=makereg' onclick=\"$('body,html').animate( {scrollTop: 0}, 1000, 'swing'); return login($('#nlogin'), 200);\" class='popupc'>войти в него сейчас</a>.<br/><br/>Сервис Мои товары позволяет следить за ценами и наличием желаемых товаров, поделится своими желаемыми товарами в соц.сетях, оставить отзывы на приобретенные товары, чтобы помочь другим посетителям в выборе товара.</p>";
				break;
			case 4:
				title = "Клубные цены";
				content = "<p style='text-align: justify;'>Для того, чтобы приобретать товары по Клубным ценам необходимо <a href='http://www.mobilluck.com.ua/input.php?a=makereg' onclick=\"$('body,html').animate( {scrollTop: 0}, 1000, 'swing'); return login($('#nlogin'), 200);\" class='popupc'>зарегистрироваться на сайте</a>. Это займет не более минуты, особенно если использовать логин через социальные сети.<br/>Если у вас уже есть личный кабинет, то вы можете <a href='http://www.mobilluck.com.ua/input.php?a=makereg' onclick=\"$('body,html').animate( {scrollTop: 0}, 1000, 'swing'); return login($('#nlogin'), 200);\" class='popupc'>войти в него сейчас</a>.<br/><br/>Клубные цены – это самый выгодный бонус, который Вы получаете за регистрацию. Возможность купить товар со скидкой появляется сразу после регистрации.</p>";
				break;
		}

		$("#dialog_title").html(title);
		$("#dialog_content").html(content);
		showPopupDlg(left, top, 480);
		return false;
	}

    function podpiska(lnk, inp, mintop)
    {
        var ww = $(window).width(), wh = $(window).height();
        var left = this.offsetLeft, top = this.offsetTop;
        if (navigator.userAgent.toLowerCase().indexOf("msie") != -1){left += (ww-978)/2;}

        var coor = getOffset( lnk );
        left = coor.left;
        if(mintop)
		{
			top = coor.top-mintop;
		}
		else
		{
			top = coor.top;
		}

        $("#dialog_title").html(dialogs[9][0]);
        $("#dialog_content").html(dialogs[9][1]);

        var vle = document.getElementById(inp).value;
        if(vle=='Ваша электронная почта')
            vle = '';
        showPopupDlg(left, top);

        $(".dlgform").find("#dlgf_mail").val(vle);
        return false;
    }
    </script>

	<div id="popupbg"></div>
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


		<div id="popup_wrapper">
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

		
<div id="bucket" style="display: none;"></div>


<!-- Google Code for &#1041;&#1099;&#1083;&#1080; Remarketing List -->
<script type="text/javascript">
/* <![CDATA[ */
var google_conversion_id = 1005680689;
var google_conversion_language = "en";
var google_conversion_format = "3";
var google_conversion_color = "666666";
var google_conversion_label = "RJuzCKepmQIQsfDF3wM";
var google_conversion_value = 0;
/* ]]> */
</script>
<script type="text/javascript" src="http://www.googleadservices.com/pagead/conversion.js">
</script>
<noscript>
<div style="display:inline;">
<img height="1" width="1" style="border-style:none;" alt="" src="http://www.googleadservices.com/pagead/conversion/1005680689/?label=RJuzCKepmQIQsfDF3wM&amp;guid=ON&amp;script=0"/>
</div>
</noscript>

<!-- Google Code for All Visitors Remarketing List -->
<script type="text/javascript">
/* <![CDATA[ */
var google_conversion_id = 971723204;
var google_conversion_language = "en";
var google_conversion_format = "3";
var google_conversion_color = "666666";
var google_conversion_label = "b5NCCKyO7QIQxKOtzwM";
var google_conversion_value = 0;
/* ]]> */
</script>
<script type="text/javascript" src="http://www.googleadservices.com/pagead/conversion.js">
</script>
<noscript>
<div style="display:inline;">
<img height="1" width="1" style="border-style:none;" alt="" src="http://www.googleadservices.com/pagead/conversion/971723204/?label=b5NCCKyO7QIQxKOtzwM&amp;guid=ON&amp;script=0"/>
</div>
</noscript>


	<script>
function feedback(topic, lnk, ttop)
{
	var ww = $(window).width(), wh = $(window).height();
	var left = this.offsetLeft, top = this.offsetTop;
	if (navigator.userAgent.toLowerCase().indexOf("msie") != -1){left += (ww-978)/2;}

	var coor = getOffset( lnk );
	left = coor.left - 250;
	top = coor.top - ttop;

	$.ajax({
		type: "POST",
		url: "/ajx/ajx_pop.php",
		data: 'cmd=uh_feed&topic='+topic+'',
		dataType: "text",
		success: function(data){
			$("#dialog_title").html('');
			$("#dialog_content").html(data);
			showPopupDlg(left, top, 480);
		}
	});
}
</script>
			<script id="xcntmyAsync" type="text/javascript">
			(function(){
				var xscr = document.createElement( 'script' );
				var xcntr = escape(document.referrer); xscr.async = true;
				xscr.src = ( document.location.protocol === 'https:' ? 'https:' : 'http:' )
				+ '//x.cnt.my/async/track/?r=' + Math.random();
				var x = document.getElementById( 'xcntmyAsync' );
				x.parentNode.insertBefore( xscr, x );
			}());
			</script>
			<script src="http://rt.actionpay.ru/code/mobilluck/" defer></script><script>window.APRT_DATA = { pageType: 3, currentCategory: { id: 1, name: ' Телефоны и плееры' } }; </script>
				<script type="text/javascript">
					window.ad_category = "1";// required
				    window._retag = window._retag || [];
					window._retag.push({code: "9ce88867c3", level: 1});
					(function () {
				        var s=document.createElement("script");
						s.async=true;
						s.src=(document.location.protocol == "https:" ? "https:" : "http:") + "//cdn.admitad.com/static/js/retag.js";
						var a=document.getElementsByTagName("script")[0]
						a.parentNode.insertBefore(s, a);
					})()
				</script>
						<!-- Start SiteHeart code -->
		<script>
		(function(){
		// your widget ID
		var widget_id = 662652;
		_shcp =[{widget_id : widget_id}];
		// set default language
		var lang =(navigator.language || navigator.systemLanguage
		|| navigator.userLanguage ||"en")
		.substr(0,2).toLowerCase();
		// script url
		var url ="widget.siteheart.com/widget/sh/"+ widget_id +"/"+ lang +"/widget.js";
		var hcc = document.createElement("script");
		hcc.type ="text/javascript";
		hcc.async =true;
		hcc.src =("https:"== document.location.protocol ?"https":"http")
		+"://"+ url;
		var s = document.getElementsByTagName("script")[0];
		s.parentNode.insertBefore(hcc, s.nextSibling);
		})();
		</script>
		<!-- End SiteHeart code -->
		</body>
</html>