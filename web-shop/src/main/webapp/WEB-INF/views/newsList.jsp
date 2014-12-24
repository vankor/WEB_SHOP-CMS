
<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">


<head>

<title>Новости</title>
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
<!--  		<div class="sltoffer" style="display:none;">
			<img src="img/img-slt-5.gif" alt="5" width="26" height="20">
			<p class="sltc_title"><a href="http://www.mobilluck.com.ua/action.php" rel="nofollow">Акции</a> <span>68</span></p>
		</div>-->
	</div>
</div>


<div id="content">
	<div class="ccatalog">
		<div class="cbreadcrumbs">
			<a href="http://www.mobilluck.com.ua/">Главная</a> /
					</div>
		<h1 class="mgood_title">${currnewstype.title}</h1>
		<div class="ccnlist">
		<c:forEach items = "${newsList}" var = "news" varStatus = "i">
		<div class="ccnews">
					<p class="ccn_title"><a href="${pageContext.request.contextPath}/category/${news.category.id}/news/${news.id}">${news.title}</a></p>
					<div class="w100 float">
						<img src="<c:url value = "/resources/images${news.thumb}"/>" class="float" alt="" width="140" height="140">
						<div class="ccntext">
							<div class="ccnt_short">${news.promotext}</div>
							<p class="ccntmore"><a href="${pageContext.request.contextPath}/category/${news.category.id}/news/${news.id}">Читать полностью</a></p>
							<p class="ccntdate">${news.creation}</p>
						</div>
					</div>
				</div>
		</c:forEach>		
				</div>	
				</div>

<div class="ccpnav">
		<form id = "pages">
		<span class="arrow">&#8592;</span><a class="a-text a-prev" href="${entityurl}${pgnum-1}?type=${currnewstype.id}">Пред.</a>
		<c:forEach begin="1" end="${pagecount+1}" var="i">
		<c:choose>
		<c:when test = "${i==pgnum}">
		<span class="">${i}</span><input type = "hidden" value = "${i}" name = "pagenum" id = "pagenum"/>
		</c:when>
		<c:otherwise>
		<a class="a-text" href="${entityurl}${i}?type=${currnewstype.id}">${i}</a>
		</c:otherwise>
		</c:choose>
		</c:forEach>
		<a class="a-text a-next" href="${entityurl}${pgnum+1}?type=${currnewstype.id}">След.</a><span class="arrow">&#8594;</span>
		</form>
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
