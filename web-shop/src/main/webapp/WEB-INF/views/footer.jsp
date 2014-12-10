<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

	<div id="topfooter2">
				<div class="tf2inside">
				</div>
				
				<div class="tf2bottom">
				</div>
			</div>
		<div class="width1024">		
		
		<div id="footer-links">
			<table>
				<tbody>
					<tr>
						<c:forEach items = "${pagegroups}" var = "pagegroup">
						<c:if test = "${pagegroup.isdeleted !=true}">
						<td id="fl-fir">
							<p class="f-title">${pagegroup.name}</p>
							<ul>
								<c:forEach items = "${pagegroup.pages}" var = "page">
									<li><a href="${pageContext.request.contextPath}/info/${page.fullurl}">${page.name}</a></li>
								</c:forEach>
							
							</ul>
						</td>
						</c:if>
						</c:forEach>
							
						
						<td id="fl-fiv">
							<p class="f-title">График работы Call-центра</p>
							<ul class="timef">
								<li>Пн.-Пт.: с 8:00 до 21:00</li>
								<li>Суббота: с 9:00 до 20:00</li>
								<li>Воскресенье: с 9:00 до 19:00</li>
							</ul>
							<p class="f-title" style="margin-bottom:0; padding-bottom:0;">Мы в социальных сетях</p>									<a target="_blank" rel="nofollow" href="http://vkontakte.ru/club17491020"><img src="http://img.mobilluck.com.ua/img/social/vk.png" alt="" width="21" height="21" /></a>
									<a target="_blank" rel="nofollow" href="http://www.facebook.com/mobilluckua?sk=app_4949752878"><img src="http://img.mobilluck.com.ua/img/social/fb.png" alt="" width="21" height="21" /></a>
									<a target="_blank" rel="publisher" href="https://plus.google.com/108918605816953397967/posts"><img src="http://img.mobilluck.com.ua/img/social/gp.png" alt="" width="21" height="21" /></a>
									<a target="_blank" rel="nofollow" href="http://twitter.com/Mobilluck_spec"><img src="http://img.mobilluck.com.ua/img/social/tw.png" alt="" width="21" height="21" /></a>
									<a target="_blank" rel="nofollow" href="http://www.odnoklassniki.ua/group/55614922031161"><img src="http://img.mobilluck.com.ua/img/social/od.png" alt="" width="21" height="21" /></a>
									<a target="_blank" rel="nofollow" href="http://www.youtube.com/user/mobilluckreview"><img src="http://img.mobilluck.com.ua/img/social/yt.png" alt="" width="21" height="21" /></a>
									<a target="_blank" rel="nofollow" href="http://my.mail.ru/community/mobilluck/"><img src="http://img.mobilluck.com.ua/img/social/mail_ru.png" alt="" width="21" height="21" /></a>
							
							</td>
							</tr>
							<tr>
								<td id="fl-six" colspan="5" style="width: 100%">
																		<p class="underline">
										
										© 2004-2014 Интернет-магазин ${shopname}. Все права защищены
									</p>
									<br/>
								</td>
							</tr>
					</tbody>
			</table>
		</div>
	</div>
	
	
	
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
    
    
    
    
    
    
    <!-- 
    
    <table>
				<tbody>
					<tr>
						<c:forEach items = "${pagegroups}" var = "pagegroup">
						<td id="fl-fir">
							<p class="f-title">Условия</p>
							<ul>
								<li><a href="http://www.mobilluck.com.ua/info.php?page=about">О магазине</a></li>
								<li><a href="http://www.mobilluck.com.ua/mob_cont.php">Контакты</a></li>
								<li><a href="http://www.mobilluck.com.ua/deliver.php">Оплата и доставка</a></li>
								<li><a href="http://www.mobilluck.com.ua/cooperation.php">Сотрудничество с нами</a></li>
								<li><a href="http://www.mobilluck.com.ua/info.php?page=using">Гарантии и конфиденциальность</a></li>
							</ul>
						</td>
						<td id="fl-sec">
							<p class="f-title">Мой Мобиллак</p>
							<ul>	
							<li><c:choose><c:when test="${empty user}">
							<a href="#" class="popupc" onclick="return showmsg(1, this, 250)">Мой кабинет</a></c:when>	
							<c:otherwise><a href="${pageContext.request.contextPath}/cabinet?section=personal_data" class="popupc" >Мой кабинет</a>
							</c:otherwise>
							</c:choose>
							</li>
							<li><a href="#" class="popupc" onclick="return showmsg(2, this, 250)">Мои заказы</a></li>

							</ul>
						</td>
						<td id="fl-thir">
							<p class="f-title">Выгодные предложения</p>
							<ul>
								<li><a href="http://www.mobilluck.com.ua/action.php">Акции</a></li>
								<li><a href="http://www.mobilluck.com.ua/special_suggestions_of_week.php">Предложения недели</a></li>
								<li><a href="http://www.mobilluck.com.ua/" class="popupc" onclick="return showmsg(4, this, 250)">Клубные цены</a></li>
								<li><a href="http://www.mobilluck.com.ua/sale.php">Распродажа дня</a></li>
								<li><a href="http://www.mobilluck.com.ua/marked_down.php">Уцененные товары</a></li>
								<li><a href="http://www.mobilluck.com.ua/ultra-service.php">Расширенная гарантия</a></li>
							</ul>
						</td>
						<td id="fl-four">
							<p class="f-title">Помощь</p>
							<ul>
								<li><a href="http://www.mobilluck.com.ua/info.php?page=order_pay">Вопросы и ответы</a></li>
								<li><a class="popupc" onclick="feedback(12, this, 550)">Связь с руководством</a></li>
								<li><a class="popupc" onclick="feedback(5, this, 550)">Служба поддержки</a></li>
								<li><a href="http://www.mobilluck.com.ua/info.php?page=guarantee">Возврат товаров</a></li>
							</ul>
						</td>
						<td id="fl-fiv">
							<p class="f-title">График работы Call-центра</p>
							<ul class="timef">
								<li>Пн.-Пт.: с 8:00 до 21:00</li>
								<li>Суббота: с 9:00 до 20:00</li>
								<li>Воскресенье: с 9:00 до 19:00</li>
							</ul>
							<p class="f-title" style="margin-bottom:0; padding-bottom:0;">Мы в социальных сетях</p>									<a target="_blank" rel="nofollow" href="http://vkontakte.ru/club17491020"><img src="http://img.mobilluck.com.ua/img/social/vk.png" alt="" width="21" height="21" /></a>
									<a target="_blank" rel="nofollow" href="http://www.facebook.com/mobilluckua?sk=app_4949752878"><img src="http://img.mobilluck.com.ua/img/social/fb.png" alt="" width="21" height="21" /></a>
									<a target="_blank" rel="publisher" href="https://plus.google.com/108918605816953397967/posts"><img src="http://img.mobilluck.com.ua/img/social/gp.png" alt="" width="21" height="21" /></a>
									<a target="_blank" rel="nofollow" href="http://twitter.com/Mobilluck_spec"><img src="http://img.mobilluck.com.ua/img/social/tw.png" alt="" width="21" height="21" /></a>
									<a target="_blank" rel="nofollow" href="http://www.odnoklassniki.ua/group/55614922031161"><img src="http://img.mobilluck.com.ua/img/social/od.png" alt="" width="21" height="21" /></a>
									<a target="_blank" rel="nofollow" href="http://www.youtube.com/user/mobilluckreview"><img src="http://img.mobilluck.com.ua/img/social/yt.png" alt="" width="21" height="21" /></a>
									<a target="_blank" rel="nofollow" href="http://my.mail.ru/community/mobilluck/"><img src="http://img.mobilluck.com.ua/img/social/mail_ru.png" alt="" width="21" height="21" /></a>
							
															</td>
							</c:forEach>
							</tr>
							<tr>
								<td id="fl-six" colspan="5" style="width: 100%">
																		<p class="underline">
										<a href="http://www.mobilluck.com.ua/info.php?page=using" style="clear:both">Условия использования сайта</a> &nbsp;&nbsp;&nbsp;
										© 2004-2014 Интернет-магазин mobilluck.com.ua. Все права защищены
									</p>
									<br/>
								</td>
							</tr>
					</tbody>
			</table>
    
     -->