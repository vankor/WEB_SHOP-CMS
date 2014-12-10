<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<!-- 
<td class="head_title elem"><a href="http://www.mobilluck.com.ua/deliver.php">Оплата и доставка</a></td>
			<td class="head_div"><p></p></td>
			<td class="head_title elem"><a href="http://www.mobilluck.com.ua/mob_cont.php">Контакты</a></td>
			<td class="head_div"><p></p></td>
			<td class="head_title elem"><a href="http://www.mobilluck.com.ua/info.php?page=order_pay">Помощь</a></td>
			<td class="head_div"><p></p></td>
			<td class="head_title elem"><a href="http://www.mobilluck.com.ua/info.php?page=order_pay">Заказть обратный звонок</a></td>
 -->

<script>var baseurl = "<c:out value="${pageContext.request.contextPath}"/>"; 
var goodservurl = "<c:out value="${pageContext.request.contextPath}"/>"+"/goodservices"; 
</script>

<script>
function load_text(lnk, type, pid)
{
	var ww = $(window).width(), wh = $(window).height();
	var left = this.offsetLeft, top = this.offsetTop;
	if (navigator.userAgent.toLowerCase().indexOf("msie") != -1){left += (ww-978)/2;}

	var coor = getOffset( lnk );
	left = coor.left;
	top = coor.top;

	var wd = 500;
	if(type=="paytype")
	{
		$("#dialog_title").html('Способы оплаты');
	}
	else if(type=="deliver")
	{
		$("#dialog_title").html('Доставка');
	}
	else if(type=="np")
	{
		$("#dialog_title").html('Доставка "Новой Почтой"');
	}
	else if(type=="garanty")
	{
		$("#dialog_title").html('Гарантия');
	}
	else if(type=="ultra_service")
	{
		$("#dialog_title").html('Ультра сервис');
		left = left-300;
	}
	else if(type=="insurence")
	{
		$("#dialog_title").html('Страховка');
		left = left-300;
	}
	else if(type=="club_cost")
	{
		$("#dialog_title").html('Клубная цена');
	}
	else if(type=="plusballs")
	{
		$("#dialog_title").html('Получайте баллы!');
		left = left - 200;
		width = 400;
		pid = pid + '&model=Игровая приставка Genius Heeha 100X'
	}
	else if(type=="creditinfo")
	{
		$("#dialog_title").html('Онлайн-кредит под 0,01%');
		$("#dialog_content").html("<br /><br />Внимание! Кредит оформляется на безналичную стоимость товара.<br /><br />");
		showPopupDlg(left, top, wd);
		return false;
	}
	else if((type=="delivnew")||(type=="delivnew2"))
	{
		$("#dialog_title").html('Срок и стоимость доставки');
		wd = 640;
	}
	else if(type=="uh_base")
	{
		wd = 850;
	}
	else
	{
	    $("#dialog_title").html('Дополнительная информация');
	}
	$("#dialog_content").html("<br /><br />Идет подгрузка информации...<br /><br />");
    if(left<100)
    {
        var tw = $("body, html").width();
        left = (tw - wd)/2;
        top = 100;
    }
    if(top<100)
    {
        top = 150;
    }
    showPopupDlg(left, top, wd);
   
	if(type!="uh_base")
	{
		$.ajax({
			type: "POST",
			url: goodservurl+"/gettext",
			data: {"type":type, "pid":pid},
			dataType: "text",
			success: function(data){
				//$("#progress"+select_id).hide();
				$("#dialog_content").html(data);
			},
			error : function(xhr, status, error){
	            alert("Error!" + xhr.status);
	        }
		});
	}
	else
	{

			$("#dialog_content").html("<div style='padding: 40px; text-align:center'><img src='http://img.mobilluck.com.ua/img/loader.gif' width='62' height='13'></div>");
			$("#dialog_title").html('Срок и стоимость доставки');
			$.ajax({
	        type: "POST",
	        url: goodservurl+"/deliveryprices",
	        data: {"pid":pid},
	        dataType: "text",
	        success: function(data){
	        	$("#dialog_content").html(data);
				},
		error : function(xhr, status, error){
            alert("Error!" + xhr.status);
        }
			});
	}
    return false;
}
</script>

<div id = "topmenu">
<table class="men_top_table">
		<tbody>
		<tr>
			<c:forEach items = "${headerpages}" var = "page">
			<td class="head_title elem"><a href="${pageContext.request.contextPath}/info/${page.fullurl}">${page.name}</a></td>
			<td class="head_div"><p></p></td>
			</c:forEach>
			<td style="width:30%;">
				
		<c:choose><c:when test="${isAuthorized == false || empty user}">
		<a rel="nofollow" id="nlogin" href="#" onclick="return login(this,100)">
		<span>Войти</span></a>
		</c:when>
		<c:otherwise><p>Добрый день, уважаемый ${user.name}! </p><a rel="nofollow" id="nlogin" href="<c:url value="j_spring_security_logout" />">
		<span>Выйти</span></a>
		</c:otherwise>
		</c:choose>	
		
			</td>
		
			<td class="head_basked">
				<div class="baskedbox">
					<div onclick = "showbuck()" class="basked">
						<p class="basked_items_count"><span style="position:relative; top:2px; right:0">0</span></p><div class="basked_img"></div><span class="text">Корзина</span>
						<div class="trigger" style="background-position: -207px 0px;">&nbsp;</div>
					</div>
						<div class="basked_items_list" style="display: none;">
									<table>
										<tbody><tr><td colspan="3" style="color:black;">Вы пока еще ничего<br>сюда не положили</td></tr>									</tbody></table>
						</div>
				</div>
			</td>
			<td class="head_div"><p></p></td>
			<td class="head_mymob">
				<div class="mymobbox">
					<div <c:choose><c:when test="${empty user}"> onclick="return login(this,100)" </c:when> <c:otherwise> onclick = "location.href = '${pageContext.request.contextPath}/cabinet?section=personal_data';"</c:otherwise>
							</c:choose> class="mymob">
						
						<span class="text">Мой Кабинет</span>
						<div class="trigger" style="background-position: -207px 0px;"><i class="arrow"></i></div>
					</div>
						
				</div>
			</td>
			<td class="head_div"><p></p></td>
			<td class="head_enterout">
			<a rel="nofollow" id="nlogin" href="http://www.mobilluck.com.ua/input.php" onclick="return login(this, 200)"><div class="h_login"></div></a>			</td>
		</tr>
		</tbody>
		</table>
</div>
	
	
	
	
	
	
	

<table id="tophead">
	<tr>
		<td rowspan="2" align="center">
			<a href="<c:out value="${pageContext.request.contextPath}"/>" title="Интернет-магазин">
																<img src="<c:url value="/resources/images${basic.logo}" />" border="0" alt="Интернет-магазин ${basic.shopname}" />

			</a>
	<h1 style='font-size: 12px; font-weight: normal;'>${basic.shopname}</h1>		</td>
		<td  width="500">
 				<div id="ttelp" style="width:650px;">
			
			<div class="geoposition">
			Нажмите, чтобы выбрать город:
				<div  onclick = "return load_text(this, 'uh_base', -1)" class = "map" >
					<a style="text-decoration:none; color:black;">
						
						<div style="font:bold 14px 'Segoe UI'; cursor:pointer;" id="your_city">
							${currenttown.name}							
							<input type="hidden" name="cityid" value="${currenttown.id}">
						</div>
					</a>
				</div>
				
			</div>
	<!--  		<div class="closest" pid="1">
				<p><a href="http://www.mobilluck.com.ua/geolocation" style="text-decoration:none">Ближайший пункт выдачи</a></p>
				<div class="punkt_fadein">
						<div class="triangle-up">
						</div>
						<div id="punkti"><span><b>1. Отделение Мист Экспресс в Ковель</b></span><br><span>ул. Ватутина 9</span><br><br><span><b>2. Отделение Новой почты №1</b></span><br><span>Отделение №1: ул. Возрождения 1 ж (р-н «КовельСельМаш»,конечная остановка)</span><br><br></div>
					</div>
			</div>
			<script type="text/javascript">
			$(document).ready(function() {
				$(".closest").mouseover(function(){
				if($(this).attr("pid")=="0")
				{
					$.ajax({
					type: "POST",
					url: "/ajx/ajx_geo.php",
					data: 'cmd=uh_closest&sid='+ 86,
					dataType: "text",
					success: function(data){
							if(data=="")
							{
								$("#punkti").html("<span>Доставка Мист Экспресс в любой город Украины</span>");
							}
							else
							{
								$("#punkti").empty();
								var fill = data.split("|");
								for(i=0; i<fill.length;i++)
								{
									var temp = fill[i].split(";");
									var num = i+1;
									$("#punkti").append("<span><b>"+num+". "+temp[0]+"</b></span><br>");
									$("#punkti").append("<span>"+temp[1]+"</span><br><br>");
								}
							}
						}
					});
				}
				$(this).attr("pid", "1");
				});
			});
			</script>
		-->
				
				
			</div>
		<td rowspan="2" width="580" style="font-size: 12px; color: #636466;">
		<div id="thf_phones"><p id="cont_ph">044 323-03-34</p><p>&nbsp;&nbsp;&nbsp; 0 800 505-333</p></div>
		<div class="ttadr"><p><a id="lnkfilialinfo" onclick ="return load_text(this, 'uh_base', 0)" role = "button">Адреса представительств</a></p></div>
		
		</td>		
		</td>
	</tr>
	<tr>
		<td  width="500">
			<form action="http://www.mobilluck.com.ua/search.php" method="GET" name="srch" id="srch">
			<table>
			<tr>
				<td>
								<input autocomplete="off" type="text" name="sw" id="searchsw" value="" placeholder="Что вы хотите найти?" x-webkit-speech="x-webkit-speech"/>
				<div id="searchsw2"></div>
				</td>
				<td><input id="searchbtn" type="submit" src="http://img.mobilluck.com.ua/img/product/search.png" value="Искать" /></td>
			</tr>
						</table>
			</form>

			<script>
			jQuery(function (){
				jQuery('#searchsw').combobox([]);

				jQuery('#srch').submit(function(){
					if((jQuery.combobox.instances[0].selector.enter == true) && (jQuery.combobox.instances[0].selector.url!=""))
						return false;
					if(($("#searchsw").val()=="")||($("#searchsw").val()=="Что вы хотите найти?"))
						return false;
				});

				jQuery('#searchsw').keyup(function(event){
					if (event.keyCode != 40 && event.keyCode != 38 && event.keyCode != 27 && event.keyCode != 13)
					{
						var txt = $(this).val();
						if(txt.length>2){
							alert(txt);
							fillSearchListNS(txt, 'searchdrop', 'searchsw');
							
						}
					}
				});
			});
			$(window).load(function(){
				$(".combobox_selector").css("top", ($("#searchsw2").position().top-5)+"px");
			});
			</script>
		</td>
		</tr>
	</table>

	
