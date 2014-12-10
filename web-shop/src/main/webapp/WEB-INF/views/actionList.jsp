
<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">


<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1251" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>Интернет магазин МОБИЛЛАК - магазин бытовой техники, электроники, компьютерной техники, ноутбуков, телевизоров, компьютеров - Киев, Харьков, Днепропетровск, Львов, Донецк, Одесса</title>
<meta name="keywords" content="интернет магазин, mobilluck, телевизоры, ноутбуки, техника, электроника, Харьков, Киев, Днепропетровск, интернет-магазин" />
<meta name="description" content="MOBILLUCK - интернет-магазин техники с самыми лучшими ценами по всей Украине. Тел: 0-800-505-333. Наш интернет магазин выполняет доставку по территории Украины." />
<meta http-equiv="Content-Type" content="text/html; charset=windows-1251" />
<meta name="yandex-verification" content="657622478173aeb5" />
<meta http-equiv='Content-Language' content='ru-RU'/>
<meta property="fb:admins" content="100001023235388" />
<META name="ROBOTS" content="index,follow"/>
<jsp:include page="/WEB-INF/views/head.jsp" />
<script type="text/javascript">
//var url = "<c:out value = "${pageContext.request.contextPath}"/>";
$(document).ready(function(){
	$(".section_act_new").on("click", function(){
	//	alert("eeee");
		var thislist = $(this).parent().find(".sltclist_new");
		$(".section_act_new img").attr({src:url+"/resources/images/rigth.png"});
		if($(thislist).eq(0).is(":visible")==true)
		{
			$(thislist).slideUp("slow");
		}
		else
		{
			$(".sltclist_new").slideUp("slow");
			$(this).parent().find(".sltclist_new").slideDown("slow");
			$(this).children().eq(1).attr({src:url+"/resources/images/bot.png"})
		}

	});
});
		
	</script>
</head>
<body >
<div style="fixup" id="fixup" name="fixup"><div id="uplink">Наверх</div></div>
<div id="searchdrop" style="visibility: hidden; display: none; width: 462px;">
	<div class="searchdropcont">
		<div id="searchdrop_btn"><a href="javascript:hideTooltip('searchdrop')" class="closebut">X</a></div><div id="searchdrop_body"></div>
</div><!--[if lte IE 6.5]><iframe></iframe><![endif]--></div>
<div id="popuptooltip" style="visibility: hidden; display: none;">
	<div class="tooltipwndcont">
		<div id="popuptooltip_btn"><a href="javascript:hideTooltip('popuptooltip')" class="closebut">X</a></div><div id="popuptooltip_body"></div>
</div><!--[if lte IE 6.5]><iframe></iframe><![endif]--></div>
<!-- Yandex.Metrika counter 10.10.2011-->
<div style="display:none;"><script type="text/javascript">
(function(w, c){
    (w[c] = w[c] || []).push(function(){ try {w.yaCounter10254718 = new Ya.Metrika({id:10254718, enableAll: true});} catch(e) { } });
})(window, "yandex_metrika_callbacks");
</script></div>
<script src="//mc.yandex.ru/metrika/watch_visor.js" type="text/javascript" defer="defer"></script>
<noscript><div><img src="//mc.yandex.ru/watch/10254718" style="position:absolute; left:-9999px;" alt="" /></div></noscript>
<!-- /Yandex.Metrika counter -->
<div id="page">
<div id = "headerwrapper">
<jsp:include page="/WEB-INF/views/header.jsp" />
<div id="wrapper">
<jsp:include page="/WEB-INF/views/mainmenu.jsp" />
</div>
</div>
<div style="padding: 0px; width: 100%;">
<div id = "wrapper">
<div id = "main">
<div id="gsidebar" style="float:left; margin-top:60px">
        <br>
	<!--  -->	<div class="sltopic">
     <!--   	<div class="sltoffer3">
		<a href="http://www.mobilluck.com.ua/sale.php" rel="nofollow"><img src="http://img.mobilluck.com.ua/img/action/lm_raspr.png" alt="Распродажа"></a>
	</div>
		<div class="sltoffer3">
		<a href="http://www.mobilluck.com.ua/special_suggestions_of_day.php" rel="nofollow"><img src="http://img.mobilluck.com.ua/img/action/lm_dayitem.png" alt="Товар Дня"></a>
	</div>
	<div class="sltoffer3">
		<a href="http://www.mobilluck.com.ua/special_suggestions_of_week.php" rel="nofollow"><img src="http://img.mobilluck.com.ua/img/action/lm_weekprice.png" alt="Цена недели"></a>
	</div>
		<div class="sltoffer3">
		<a href="http://www.mobilluck.com.ua/marked_down.php" rel="nofollow"><img src="http://img.mobilluck.com.ua/img/action/lm_ucenka.png" alt="Уценка"></a>
	</div>
		<div class="sltoffer3">
		<a href="http://www.mobilluck.com.ua/onsalesoon.php" rel="nofollow"><img src="http://img.mobilluck.com.ua/img/action/lm_soon.png" alt="Скоро в продаже"></a>
	</div>
		<div class="sltoffer3">
		<a href="http://www.mobilluck.com.ua/presents.php"><img src="http://img.mobilluck.com.ua/img/action/lm_presents.png" alt="Идеи подарков"></a>
	</div>
		<div class="sltoffer3">
		<a href="http://www.mobilluck.com.ua/winners.php"><img src="http://img.mobilluck.com.ua/img/action/lm_winers.png" alt="Победители акций"></a>
	</div>-->
	                    <p class="sltc_title_new">Категории товаров</p>
                    <div class="sltcategory">
                        
                        <c:forEach items = "${catactions}" var = "entry">
                        <div>
                        <p class="section_act_new"><span>${entry.key}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="<c:url value = "/resources/images/rigth.png"/>">(${entry.value})</p>
                        <ul class="sltclist_new" style="display:none">
                        <c:forEach items = "${entry.key.actionschild}" var = "acentry">
                      	  <li><b><a href="${pageContext.request.contextPath}/category/${acentry.key.id}/actions/1">${acentry.key.name}</a></b> (${acentry.value})</li>
                      	</c:forEach>
                   
                        </ul>
                        </div>
                    	</c:forEach>
                           				
                     </div>
			</div>
</div>

<div id="gcontent">
		<div class="cbreadcrumbs"><a href="http://www.mobilluck.com.ua/">Главная</a> / <a href="http://www.mobilluck.com.ua/news.php">Новости, статьи, обзоры</a> / </div>
			<h1 class="mgood_title">Наши акции</h1>
    <!--                    <noindex>
	  	<table>
            <tbody><tr>
                <td class="cait_links">Мне нравится</td>
						<td class="cait_links">
						<noindex>


							<table width="500" border="0">
								<tbody><tr>

								<td style="display: inline-block; width: 150px; overflow: hidden;" width="150">
									<!-- VKONTAKTE -->
									<!-- Put this script tag to the <head> of your page -->
							<!--		<script type="text/javascript" src="http://userapi.com/js/api/openapi.js?32"></script>
									<script type="text/javascript">
  									VK.init({apiId: 2117138, onlyWidgets: true});
									</script>
									<!-- Put this div tag to the place, where the Like block will be -->
							<!--		<div id="vk_like" style="height: 22px; width: 180px; position: relative; clear: both; background: none;"><iframe name="fXD66418" frameborder="0" src="http://vk.com/widget_like.php?app=2117138&amp;width=100%&amp;_ver=1&amp;page=0&amp;url=http%3A%2F%2Fwww.mobilluck.com.ua%2Faction.php&amp;type=button&amp;verb=0&amp;color=&amp;title=%D0%90%D0%BA%D1%86%D0%B8%D0%B8%20%D0%B8%D0%BD%D1%82%D0%B5%D1%80%D0%BD%D0%B5%D1%82%20%D0%BC%D0%B0%D0%B3%D0%B0%D0%B7%D0%B8%D0%BD%D0%B0%20%D0%9C%D0%9E%D0%91%D0%98%D0%9B%D0%9B%D0%90%D0%9A&amp;description=%D0%90%D0%BA%D1%86%D0%B8%D0%B8%20%D0%B8%D0%BD%D1%82%D0%B5%D1%80%D0%BD%D0%B5%D1%82%20%D0%BC%D0%B0%D0%B3%D0%B0%D0%B7%D0%B8%D0%BD%D0%B0%20%D0%9C%D0%9E%D0%91%D0%98%D0%9B%D0%9B%D0%90%D0%9A&amp;image=&amp;text=&amp;h=22&amp;1496ec2457c" width="100%" height="22" scrolling="no" id="vkwidget1" style="overflow: hidden; height: 22px; width: 180px; z-index: 150;"></iframe></div>
									<script type="text/javascript">
									VK.Widgets.Like("vk_like", {type: "button"});
									</script>
								</td>


								<td style="display: inline-block; width: 72px;" width="72">
									<!-- GOOGLE +1 -->
                                    <!-- Place this tag where you want the +1 button to render -->
                        <!--            <div id="___plusone_0" style="text-indent: 0px; margin: 0px; padding: 0px; border-style: none; float: none; line-height: normal; font-size: 1px; vertical-align: baseline; display: inline-block; width: 90px; height: 20px; background: transparent;"><iframe frameborder="0" hspace="0" marginheight="0" marginwidth="0" scrolling="no" style="position: static; top: 0px; width: 90px; margin: 0px; border-style: none; left: 0px; visibility: visible; height: 20px;" tabindex="0" vspace="0" width="100%" id="I0_1414902465999" name="I0_1414902465999" src="https://apis.google.com/se/0/_/+1/fastbutton?usegapi=1&amp;size=medium&amp;hl=ru&amp;origin=http%3A%2F%2Fwww.mobilluck.com.ua&amp;url=http%3A%2F%2Fwww.mobilluck.com.ua%2Faction.php&amp;gsrc=3p&amp;ic=1&amp;jsh=m%3B%2F_%2Fscs%2Fapps-static%2F_%2Fjs%2Fk%3Doz.gapi.ru.eO4KVq19fQc.O%2Fm%3D__features__%2Fam%3DAQ%2Frt%3Dj%2Fd%3D1%2Ft%3Dzcms%2Frs%3DAGLTcCOgGIuyJJ09M7cwFnLtzTzYxU4IMA#_methods=onPlusOne%2C_ready%2C_close%2C_open%2C_resizeMe%2C_renderstart%2Concircled%2Cdrefresh%2Cerefresh&amp;id=I0_1414902465999&amp;parent=http%3A%2F%2Fwww.mobilluck.com.ua&amp;pfname=&amp;rpctoken=23805785" data-gapiattached="true" title="+1"></iframe></div>
                                    <!-- Place this tag after the last plusone tag -->
                          <!--          <script type="text/javascript">
                                    window.___gcfg = {lang: 'ru'};
                                    (function() {
                                    var po = document.createElement('script'); po.type = 'text/javascript'; po.async = true;
                                    po.src = 'https://apis.google.com/js/plusone.js';
                                    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(po, s);
                                    })();
                                    </script>
								</td>

								<td style="display: inline-block; width: 120px;" width="120">
									<iframe id="twitter-widget-0" scrolling="no" frameborder="0" allowtransparency="true" src="http://platform.twitter.com/widgets/tweet_button.d58098f8a7f0ff5a206e7f15442a6b30.ru.html#_=1414902466277&amp;count=horizontal&amp;id=twitter-widget-0&amp;lang=ru&amp;original_referer=http%3A%2F%2Fwww.mobilluck.com.ua%2Faction.php&amp;size=m&amp;text=%D0%90%D0%BA%D1%86%D0%B8%D0%B8%20%D0%B8%D0%BD%D1%82%D0%B5%D1%80%D0%BD%D0%B5%D1%82%20%D0%BC%D0%B0%D0%B3%D0%B0%D0%B7%D0%B8%D0%BD%D0%B0%20%D0%9C%D0%9E%D0%91%D0%98%D0%9B%D0%9B%D0%90%D0%9A&amp;url=http%3A%2F%2Fwww.mobilluck.com.ua%2Faction.php&amp;via=Mobilluck" class="twitter-share-button twitter-tweet-button twitter-share-button twitter-count-horizontal" title="Twitter Tweet Button" data-twttr-rendered="true" style="width: 209px; height: 20px;"></iframe><script type="text/javascript" src="http://platform.twitter.com/widgets.js"></script>
								</td>

								<td style="display: inline-block;  width: 135px;" width="135">
									<!-- FACEBOOK -->
						<!-- 			<div id="fb-root" class=" fb_reset"><div style="position: absolute; top: -10000px; height: 0px; width: 0px;"><div><iframe name="fb_xdm_frame_http" frameborder="0" allowtransparency="true" scrolling="no" id="fb_xdm_frame_http" aria-hidden="true" title="Facebook Cross Domain Communication Frame" tabindex="-1" src="http://static.ak.facebook.com/connect/xd_arbiter/ehazDpFPEnK.js?version=41#channel=f13ddfd6d4&amp;origin=http%3A%2F%2Fwww.mobilluck.com.ua" style="border: none;"></iframe><iframe name="fb_xdm_frame_https" frameborder="0" allowtransparency="true" scrolling="no" id="fb_xdm_frame_https" aria-hidden="true" title="Facebook Cross Domain Communication Frame" tabindex="-1" src="https://s-static.ak.facebook.com/connect/xd_arbiter/ehazDpFPEnK.js?version=41#channel=f13ddfd6d4&amp;origin=http%3A%2F%2Fwww.mobilluck.com.ua" style="border: none;"></iframe></div></div><div style="position: absolute; top: -10000px; height: 0px; width: 0px;"><div></div></div></div>
                            <script>(function(d, s, id) {
                              var js, fjs = d.getElementsByTagName(s)[0];
                              if (d.getElementById(id)) return;
                              js = d.createElement(s); js.id = id;
                              js.src = "//connect.facebook.net/ru_RU/all.js#xfbml=1";
                              fjs.parentNode.insertBefore(js, fjs);
                            }(document, 'script', 'facebook-jssdk'));</script>
                            <div class="fb-like fb_iframe_widget" data-href="" data-send="true" data-layout="button_count" data-width="130" data-show-faces="false" data-font="arial" fb-xfbml-state="rendered" fb-iframe-plugin-query="app_id=&amp;font=arial&amp;href=http%3A%2F%2Fwww.mobilluck.com.ua%2Faction.php&amp;layout=button_count&amp;locale=ru_RU&amp;sdk=joey&amp;send=true&amp;show_faces=false&amp;width=130"><span style="vertical-align: bottom; width: 189px; height: 20px;"><iframe name="fb753f498" width="130px" height="1000px" frameborder="0" allowtransparency="true" scrolling="no" title="fb:like Facebook Social Plugin" src="http://www.facebook.com/plugins/like.php?app_id=&amp;channel=http%3A%2F%2Fstatic.ak.facebook.com%2Fconnect%2Fxd_arbiter%2FehazDpFPEnK.js%3Fversion%3D41%23cb%3Df38583704c%26domain%3Dwww.mobilluck.com.ua%26origin%3Dhttp%253A%252F%252Fwww.mobilluck.com.ua%252Ff13ddfd6d4%26relation%3Dparent.parent&amp;font=arial&amp;href=http%3A%2F%2Fwww.mobilluck.com.ua%2Faction.php&amp;layout=button_count&amp;locale=ru_RU&amp;sdk=joey&amp;send=true&amp;show_faces=false&amp;width=130" style="border: none; visibility: visible; width: 189px; height: 20px;" class=""></iframe></span></div>
								</td>

								</tr>
							</tbody></table>

							</noindex>
						</td>
            </tr>
        </tbody></table>

		</noindex> -->

		

		<div class="p">
			<b>Действующие акции нашего интернет магазина:</b>
			<br><br>
	<c:forEach items = "${actions}" var = "action">
		<div class="index_action">
							<div class="index_action_inside">
								<a href="${pageContext.request.contextPath}/action/${action.id}" title="${action.name}"><img src="<c:url value="/resources/images${action.thumb}"/>" width="308" height="155" style="display: inline;"></a>
								<div class="index_action_text">
									<p class="index_action_title"><a href="${pageContext.request.contextPath}/action/${action.id}">${action.name}</a></p>
									<p class="index_action_left">
										До конца акции осталось <span class="days_left">${action.endtime}</span>
									</p>
								</div>
							</div>
						</div>
		</c:forEach>						<div id="loadaction"></div>
		<br>
		<div class="showmoreaction" onclick="loadactions('index_content', 6, 3)">
			<img src="http://img.mobilluck.com.ua/img/main_page/show_more.png" data-original="http://img.mobilluck.com.ua/img/main_page/show_more.png" style="display: inline;">
		</div><br>
		<div style="width:100%; text-align:center;font-weight: bold; clear:both;padding-top: 30px;">Не упустите возможность купить желаемый товар по акционным выгодным условиям!</div>
		

		<script>
	/*	var count_action = 12;
		function loadactions(cls, count_all, count_in_line)
        {
			var sid = '';
            $.ajax({
                type: "POST",
                url: "/ajx/ajx_jq.php",
                data: 'cmd=uh_loadaction&count_all='+count_all+'&count_in_line='+count_in_line+'&count='+count_action+'&sid='+sid,
                dataType: "html",
                success: function(data)
                {
					var tmp = data.split('|||');
					if(tmp[2])
					{
						var text = $("#loadaction").html() + tmp[2];
						$("#loadaction").html(text);
					}
					if(tmp[0] == 0)
					{
						$(".showmoreaction").hide();
					}
					count_action = 1*tmp[1];
                }
            });
        }*/
		</script>

		</div>
		<div class="ccpnav">
		<form id = "pages">
		<span class="arrow">&#8592;</span><a class="a-text a-prev" href="${entityurl}${pgnum-1}">Пред.</a>
		<c:forEach begin="1" end="${pagecount+1}" var="i">
		<c:choose>
		<c:when test = "${i==pgnum}">
		<span class="">${i}</span><input type = "hidden" value = "${i}" name = "pagenum" id = "pagenum"/>
		</c:when>
		<c:otherwise>
		<a class="a-text" href="${entityurl}${i}">${i}</a>
		</c:otherwise>
		</c:choose>
		</c:forEach>
		<a class="a-text a-next" href="${entityurl}${pgnum+1}">След.</a><span class="arrow">&#8594;</span>
		</form>
		</div>
		
		<!-- p -->
                <table width="480">
                    <tbody><tr>
                        <td colspan="4"><br></td>
                    </tr>
					<tr>
                        <td width="125" style="">
                            Добавить в заметки
                        </td>
						<td align="left" width="90">
                            <noindex>
                                <!-- Put this script tag to the <head> of your page -->
                                <script type="text/javascript" src="http://vkontakte.ru/js/api/share.js?11" charset="windows-1251"></script>
                                <!-- Put this script tag to the place, where the Share button will be -->
                                <script type="text/javascript"><!--
                                document.write(VK.Share.button({url: "http://www.mobilluck.com.ua/action.php"},{type: "link", text: "Сохранить"}));
                                --></script><table style="position: relative; cursor:pointer; width: auto; line-height: normal;" onmouseover="this.rows[0].cells[1].firstChild.firstChild.style.textDecoration='underline'" onmouseout="this.rows[0].cells[1].firstChild.firstChild.style.textDecoration='none'" cellspacing="0" cellpadding="0"><tbody><tr style="line-height: normal;"><td style="vertical-align: middle;"><a href="http://vk.com/share.php?url=http%3A%2F%2Fwww.mobilluck.com.ua%2Faction.php" onmouseup="this._btn=event.button;this.blur();" onclick="return VK.Share.click(0, this);" style="text-decoration:none;"><img src="//vk.com/images/icons/share_link.png" width="16" height="16" style="vertical-align: middle;border:0;"></a></td><td style="vertical-align: middle;"><a href="http://vk.com/share.php?url=http%3A%2F%2Fwww.mobilluck.com.ua%2Faction.php" onmouseup="this._btn=event.button;this.blur();" onclick="return VK.Share.click(0, this);" style="text-decoration:none;"><span style="padding: 0 0 0 5px; color: #2B587A; font-family: tahoma, arial; font-size: 11px;">Сохранить</span></a></td></tr></tbody></table>
                            </noindex>
                        </td>
                        <td align="left" width="90">
                            <noindex>
                                <a href="http://www.facebook.com/sharer.php?u=http://www.mobilluck.com.ua/action.php" target="_blank" class="share">
                                    <img src="http://www.mobilluck.com.ua/img/facebook16x16.gif" border="0">
                                </a>
                                <a href="http://www.facebook.com/sharer.php?u=http://www.mobilluck.com.ua/action.php" target="_blank" class="share">
                                    <span style="position: relative; top: -2px;">Сохранить</span>
                                </a>
                            </noindex>
                        </td>
                        <td align="left" width="90">
                            <noindex>
                                <a href="http://twitter.com/share" class="share" target="_blank">
                                    <img src="http://www.mobilluck.com.ua/img/twitter16.png" border="0">
                                </a>
                                <a href="http://twitter.com/share" class="share" target="_blank">
                                    <span style="position: relative; top: -2px;">Твитнуть</span>
                                </a>
                            </noindex>
                        </td>
                    </tr>
                    <style>
                    a.share{
                        text-decoration: none;
                        height: 16px;
                        vertical-align: middle;
                    }
                    a.share:hover{
                        text-decoration: underline;
                    }
                    </style>
				</tbody></table>
                <table width="570">
                    <tbody><tr>
                        <td colspan="8"><br></td>
                    </tr>
					<tr>
                        <td>
                            Вступайте в наши группы и получайте подарки
                        </td>
						<td align="left" width="30">
                            <noindex>
                                <a href="http://vkontakte.ru/club17491020" target="_blank"><img src="http://www.mobilluck.com.ua/img/vk20.png" border="0"></a>
                            </noindex>
                        </td>
                        <td align="left" width="30">
                            <noindex>
                                <a href="http://www.facebook.com/mobilluckua?sk=app_4949752878" target="_blank"><img src="http://www.mobilluck.com.ua/img/fb20.png" border="0"></a>
                            </noindex>
                        </td>
                        <td align="left" width="30">
                            <noindex>
                                <a href="http://www.odnoklassniki.ua/group/55614922031161" target="_blank"><img src="http://www.mobilluck.com.ua/img/odn20.png" border="0"></a>
                            </noindex>
                        </td>
                        <td align="left" width="30">
                            <noindex>
                                <a href="https://plus.google.com/108918605816953397967/posts" target="_blank"><img src="http://www.mobilluck.com.ua/img/gplus20.png" border="0"></a>
                            </noindex>
                        </td>
                        <td align="left" width="30">
                            <noindex>
                                <a href="http://twitter.com/#!/Mobilluck_spec" target="_blank"><img src="http://www.mobilluck.com.ua/img/tw20.png" border="0"></a>
                            </noindex>
                        </td>
                        <td align="left" width="30">
                            <noindex>
                                <a href="http://www.mobilluck.com.ua/rss/news.rss" target="_blank"><img src="http://www.mobilluck.com.ua/img/rss20.png" border="0"></a>
                            </noindex>
                        </td>
                        <td align="left" width="30">
                            <noindex>
                                <a href="http://www.youtube.com/user/mobilluckreview" target="_blank"><img src="http://www.mobilluck.com.ua/img/ytube20.png" border="0"></a>
                            </noindex>
                        </td>
                    </tr>
				</tbody></table>

</div>
</div>
<!--  
<div id="sidebarr">
<jsp:include page="/WEB-INF/views/sidebar.jsp" />
</div>
-->

<div class="both"></div>

</div></div></div>	

<jsp:include page="/WEB-INF/views/footer.jsp" />

    <script language="javascript">

/*	function showmsg(topic, lnk, ttop)
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
    }*/
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


	<!--	<div id="popup_wrapper">
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
		</div>-->

		
<div id="bucket" style="display: none;"></div>

</div>

</body>