<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">


<c:if test = "${config.slider.isonmain}">
<div id="slides">
		<div style="margin: 0 auto; width: 990px; position: relative;">
		<div class="prev">
		<img src="http://img.mobilluck.com.ua/nd/img/big_slider_prev.png" width="25" height="275" alt="Предыдущий">
		</div>
		<div class="slides_container">
		<c:forEach items = "${config.slider.images}" var = "image">
		<a href="${image.href}" title="${image.text}" target="_blank">
		<img src="<c:url value="/resources/images${image.url}" />" width="990" height="275" alt="${image.text}">
		</a>
		</c:forEach>
		</div>
		<div class="next">
		<img src="http://img.mobilluck.com.ua/nd/img/big_slider_next.png" width="25" height="275" alt="Следующий">
		</div>
</div>
<div style="clear: left;"></div>
</div>
</c:if>


<!-- 
<a href="http://www.mobilluck.com.ua/action.php?action=3947" title="При покупке мобильного телефона Sony — сертификат «Сильпо» в подарок!" target="_blank">
		<img src="http://www.mobilluck.com.ua/files/index_slides/2014/03/Sony.jpg" width="990" height="275" alt="При покупке мобильного телефона Sony — сертификат «Сильпо» в подарок!">
		</a>
		
		<a href="http://www.mobilluck.com.ua/katalog/sport/portal_tourism.html" title="Отличный выбор товаров для туризма и пикника" target="_blank">
		<img src="http://www.mobilluck.com.ua/files/index_slides/2014/03/rast_tovar-dlia-turizma4.jpg" width="990" height="275" alt="Отличный выбор товаров для туризма и пикника">
		</a>
		
		<a href="http://www.mobilluck.com.ua/katalog/auto/" title="Подготовьте авто к весне!" target="_blank">
		<img src="http://www.mobilluck.com.ua/files/index_slides/2014/03/Avtovesna.jpg" width="990" height="275" alt="Подготовьте авто к весне!">
		</a>
		
		<a href="http://www.mobilluck.com.ua/action.php?action=3716" title="Лови момент! Суперцена на фотоаппарат Samsung WB2100 + карта памяти В ПОДАРОК!" target="_blank">
		<img src="http://www.mobilluck.com.ua/files/index_slides/2014/03/samsung_foto.jpg" width="990" height="275" alt="Лови момент! Суперцена на фотоаппарат Samsung WB2100 + карта памяти В ПОДАРОК!">
		</a>
		
		<a href="http://www.mobilluck.com.ua/action.php?action=4146" title="Жесткий диск в подарок!" target="_blank">
		<img src="http://www.mobilluck.com.ua/files/index_slides/2014/04/action_Apple.jpg" width="990" height="275" alt="Жесткий диск в подарок!">
		</a>
 -->