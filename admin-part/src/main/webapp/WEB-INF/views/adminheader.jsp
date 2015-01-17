<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<body>

<script>
$(function () {                                      // Когда страница загрузится
    $('nav li a').each(function () {             // получаем все нужные нам ссылки
        var location = window.location.href; // получаем адрес страницы
        var link = this.href;   
        if(location == link) {               // при совпадении адреса ссылки и адреса окна
            $(this).parent("li").addClass('active');  //добавляем класс
        }
    });
});

$(document).ready(function(){
$("#SubmenusInDash span").click(function() {
    $("#SubmenusInDash ul").slideToggle("fast");
});
$("#SubmenusInDash ul").mouseleave(function() {
    $(this).slideUp("fast");
});


$("#Dashboardnavigation .DashSubMenu").parent().addClass('subway');



$('nav#PrimaryNavigation ul').before("<span id='listnavigation'>Menu</span>");

$("nav#PrimaryNavigation #listnavigation").click(function() {
    $('nav#PrimaryNavigation ul ').slideToggle();
});

$('#footerTop .container').find('aside').wrapAll("<div id='footerMenuList'></div>");

$('#footerTop .container  #footerMenuList').before("<span id='Footer-listnavigation'>Footer Menu</span>");

$("#Footer-listnavigation").click(function() {
    $('#footerMenuList ').slideToggle();
    $(this).toggleClass("active");
});


var ServicesName = $("#ProjectBrowsePanel-links a.active").attr('title');


$('#ProjectBrowsePanel').after("<span id='BrowseByServices'>Browse By: <b>" + ServicesName + "</b></span>");

if (ServicesName === undefined) {
    $('#BrowseByServices').hide();
}
;

$('#ProjectBrowsePanel-links').before("<span id='BrowseServices'>Browse Services</span>");
$("#BrowseServices").click(function() {
    $('#ProjectBrowsePanel-links').slideToggle();
    $(this).toggleClass("active");
});
});
</script>
<div id="header">

    <div class="container">
        <a href="/" title="Zillion Designs" id="logo">
            <img src="<c:url value="/resources/images/logo.png" />" />
        </a>

        <!-- Primary Navigation  *** Start -->
        <nav id="PrimaryNavigation" class="panelPrimaryNavigation">
            <ul>
            <li id="menuStartContest"><a href="<c:url value="/admin/adminconfigurations/pagin/1" />" class="redLink">Настройки магазина</a></li>
		<li id="menuStartContest"><a href="<c:url value="/admin/adminorders/pagin/1" />" class="redLink">Статистика заказов</a></li>
		<li id="menuBrowseContest"><a href="<c:url value="/admin/admincomments/pagin/1" />">Статистика комментариев </a></li>
		<li id="menuPortfolio"><a href="${pageContext.request.contextPath}">Перейти на сайт</a></li>		 
		
		<!--<li><a href="#">My Projects</a></li>-->
            </ul>
        </nav>
        <!-- Primary Navigation  *** End -->
    </div>
</div>
<!-- Header  *** End-->


<div id="Dashboardnavigation">
    <div class="container">
    <nav>
	<ul>
	    <li id="navmain" ><a href="<c:url value="/admin" />">Стартовая</a></li>
	    

		
		<li id="navcategories" ><a href="<c:url value="/admin/admincategory/pagin/1" />">Категории</a>
		
			    </li>
		
		
	        	    <li id="navgoods" ><a href="<c:url value="/admin/admingoods/pagin/1" />">Товары</a></li>
	    	    	    <li id="navMessages" >
		<a role = "button">Новости</a>
		<ul class="DashSubMenu"  class="active">
		    <li><a href="<c:url value="/admin/adminnewstypes/pagin/1" />">Типы новостей</a></li>
		    <li><a href="<c:url value="/admin/adminnews/pagin/1" />">Новости</a></li>
		</ul>
	    </li>

	        	    <li id="navspots" ><a href="<c:url value="/admin/adminCountry" />">Представительства</a></li>
    	    <li id="navContactus" ><a href="<c:url value="/admin/adminusers/pagin/1" />">Пользователи</a></li>
    	    	    <li id="navothers" class = "subway">
		<a role = "button">Другие функции</a>
		<ul class="DashSubMenu"  class="active">
		    <li><a href="<c:url value="/admin/adminactions/pagin/1" />">Акции</a></li>
		    <li><a href="<c:url value="/admin/adminproducers/pagin/1" />">Производители</a></li>
		    
		    <li><a href="<c:url value="/admin/admindeltypes/pagin/1" />">Виды доставки</a></li>
		    <li><a href="<c:url value="/admin/adminpaytypes/pagin/1" />">Виды оплаты</a></li>
		    <li><a href="<c:url value="/admin/adminguaranties/pagin/1" />">Виды гарантий</a></li>
		     <li><a href="<c:url value="/admin/admingoodstatuses/pagin/1" />">Статусы товаров</a></li>
		    <li><a href="<c:url value="/admin/adminpagegroups/pagin/1" />">Группы инфо-страниц</a></li>
		    <li><a href="<c:url value="/admin/adminpages/pagin/1" />">Инфо-страницы</a></li>
		    
		<!--     <li><a href="<c:url value="/admin/adminforwards/pagin/1" />">Рассылки</a></li>--> 



		    		</ul>
	    </li>
	    	    <!--<li id="navPayment"><a target="_blank" rel="nofollow" href="http://support.mycroburst.com">Contact Us</a></li>-->
	</ul>
    </nav>
    </div>
</div>
</body>