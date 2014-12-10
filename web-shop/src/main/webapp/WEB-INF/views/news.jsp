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
<title>${news.page.title}</title>

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
<jsp:include page="/WEB-INF/views/header.jsp" />
<div id="wrapper">
<jsp:include page="/WEB-INF/views/mainmenu.jsp" />
</div>
<div style="padding: 0px; width: 100%;">
<div id = "wrapper">
<div id = "main">
<div id="sidebarl">
	<br>
	<div class="sltopic">
		<c:forEach items = "${newsTypes}" var = "ntpentry" varStatus = "i">
		<div class="sltcategory">
			<p class="sltc_title"><a href="${pageContext.request.contextPath}/newslist/1?type=${ntpentry.key.id}" rel="nofollow">${ntpentry.key.name}</a> <span>(${ntpentry.value})</span></p>
			<ul class="sltclist">
				<c:forEach items = "${ntpentry.key.categnews}" var = "ctnentry" varStatus = "j">
				<li><a href="${pageContext.request.contextPath}/category/${ctnentry.key.id}/newslist/1?type=${ntpentry.key.id}">${ctnentry.key.name}</a><span> (${ctnentry.value})</span></li>
				</c:forEach>			
			</ul>
		</div>
		
		</c:forEach>

	</div>
</div>








<div id="content2">
	<div class="ccatalog">
		<div class="cbreadcrumbs">
			<a href="http://www.mobilluck.com.ua/">Главная</a> / <a href="http://www.mobilluck.com.ua/news.php">Новости, статьи, обзоры</a> /
				<a href="http://www.mobilluck.com.ua/news.php?group=1">Новости</a>
		</div>
        
        <table>
            <tbody><tr>
                <td class="cait_links">
                    <br>
                    Мне нравится
                </td>
                <td class="cait_links">
				    <noindex>
      
					</noindex>
				</td>
            </tr>
        </tbody></table>
        
		<div class="carticle">
			<h1 class="mgood_title">${news.title}</h1>
			<div class="cacontent">
			<div align="justify">
			${news.textpart.text}
</div>
</div>
			<div class="cainfo">
				<table class="caitable">
					<tbody><tr>
						<td class="cait_date">${news.creation}</td>
                    </tr>
				</tbody></table>
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
                          <!--       <script type="text/javascript" src="http://vkontakte.ru/js/api/share.js?11" charset="windows-1251"></script>
                                <!-- Put this script tag to the place, where the Share button will be -->
                                <script type="text/javascript"><!--
                                document.write(VK.Share.button({url: ""},{type: "link", text: "Сохранить"}));
                                --></script><table style="position: relative; cursor:pointer; width: auto; line-height: normal;" onmouseover="this.rows[0].cells[1].firstChild.firstChild.style.textDecoration='underline'" onmouseout="this.rows[0].cells[1].firstChild.firstChild.style.textDecoration='none'" cellspacing="0" cellpadding="0"><tbody><tr style="line-height: normal;"><td style="vertical-align: middle;">
                        <!--         <a href="http://vk.com/share.php?url=http%3A%2F%2Fwww.mobilluck.com.ua%2Fnews.php%3Fnewsid%3D8828" onmouseup="this._btn=event.button;this.blur();" onclick="return VK.Share.click(0, this);" style="text-decoration:none;">
                                <img src="//vk.com/images/icons/share_link.png" width="16" height="16" style="vertical-align: middle;border:0;">
                                </a>
                                </td> --> 
                                <td style="vertical-align: middle;"><a href="" onmouseup="this._btn=event.button;this.blur();" onclick="return VK.Share.click(0, this);" style="text-decoration:none;"><span style="padding: 0 0 0 5px; color: #2B587A; font-family: tahoma, arial; font-size: 11px;">Сохранить</span></a></td></tr></tbody></table>                               
                            </noindex>
                        </td>
                        <td align="left" width="90">
                            <noindex>                                
                                <a href="" target="_blank" class="share">
                                    <img src="http://www.mobilluck.com.ua/img/facebook16x16.gif" border="0"> 
                                </a>
                                <a href="" target="_blank" class="share">
                                    <span style="position: relative; top: -2px;">Сохранить</span>
                                </a>                               
                            </noindex>
                        </td>
                        <td align="left" width="90">
                            <noindex>                                
                                <a href="http://twitter.com/share" class="share" target="_blank">
                                    <img src="" border="0">
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
                            Вступайте в наши группы и следите за обновлениями
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
                                <a href="http://twitter.com/#!/Mobilluck_news" target="_blank"><img src="http://www.mobilluck.com.ua/img/tw20.png" border="0"></a>                              
                            </noindex>
                        </td>
                        <td align="left" width="30">
                            <noindex>                                
                                <a href="http://www.mobilluck.com.ua/rss/news.rss" target="_blank"><img src="http://www.mobilluck.com.ua/img/rss20.png" border="0"></a>                              
                            </noindex>
                        </td>
                        <td align="left" width="30">
                            <noindex>                                
                                <a href="https://www.youtube.com/user/mobilluckreview" target="_blank"><img src="http://www.mobilluck.com.ua/img/ytube20.png" border="0"></a>                              
                            </noindex>
                        </td>                                                
                    </tr>                    
				</tbody></table>
			</div>
			<div class="caread">
				<p class="gst_title">Другие новости по теме</p>
				<div class="w100 float">
				<c:forEach items = "${othernews}" var = "news">
				<div class="srnitem">
							<p class="srni_link"><a href="${pageContext.request.contextPath}/category/${news.category.id}/news/${news.id}">${news.title}</a></p>
							<div class="srni_text">
								${news.promotext}</div>
							<p class="srni_date">${news.creation}</p>
						</div>
						
					</c:forEach>	
						</div>
				<div class="both"></div>
			</div>
		</div>
	</div>
</div>










</div>

<div id="sidebarr">
<jsp:include page="/WEB-INF/views/sidebar.jsp" />
</div>


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






