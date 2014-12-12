<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1251" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>Регистрация постоянного клиента интернет-магазина </title>

<jsp:include page="/WEB-INF/views/head.jsp" />
<script type="text/javascript">

var url = "<c:out value="${pageContext.request.contextPath}"/>";

</script>




<script type="text/javascript" charset="utf-8">
$(function() {
$("img").lazyload({ threshold : 100, effect : "fadeIn", failurelimit : 100 });
});
</script>

<script type="text/javascript">
var dt = new Date();
dt.setHours(dt.getHours()+24*180);
var url = "<c:out value="${pageContext.request.contextPath}"/>";

var debug1 = '', mt, mStatus, mt2;

function adduser(){
	
	
	
    var name = $("#clientname").val();
    var email = $("#clientemail").val();
    var psw = $("#clientp1").val();
    var dis = $("#clientdscount").val();
    var city = $("#clienttown").val();
    var cityname = $("#clienttown_f").val();
    
    var mailer = false;
    var answer = $("#clientanswer").val();
    
    if (answer != $("#answer").val())
    {
    	alert("Контрольный пример неверный(");
    	return;
    }
    
    if ($('#clientmailer').is(":checked"))
    {
    	mailer = true;
    }
//    alert(name+ "    " + email + "    " +psw+ "    " +city+"      "+mailer+"      "+answer);
//    alert(checkInName() == true && checkLog() == true );
	if(checkInName() == true && checkLog() == true ){
		
	$.ajax({
	       type: "POST",
	  	   url: url+"/adduser",
	       data: {"name":name, "cityname":cityname, "email":email, "pass":psw, "city":city, "mailer":mailer, "section":"add"},
	       cache: false,                                
	       success: function(responce){
	 //   	   alert(responce);

	    	   $("#regresult").html(responce);
	    	   $("#regresult").fadeIn(2000);
	   	//	     $(".goodMenuNode").css("background", "blue" );
	      //     $(el).css("background", "gray" );
	     	//	location.href=url+'/#tab='+$(el).attr("id"); 
	       }
	     });
	}
	
}
</script>


</head>

<body  class="p1280">
<div style="fixup" id="fixup" name="fixup"><div id="uplink">Наверх</div></div>
<div id="searchdrop" style="visibility: hidden; display: none; width: 462px;">
	<div class="searchdropcont">
		<div id="searchdrop_btn"><a href="javascript:hideTooltip('searchdrop')" class="closebut">X</a></div><div id="searchdrop_body"></div>
</div><!--[if lte IE 6.5]><iframe></iframe><![endif]--></div>
<div id="popuptooltip" style="visibility: hidden; display: none;">
	<div class="tooltipwndcont">
		<div id="popuptooltip_btn"><a href="javascript:hideTooltip('popuptooltip')" class="closebut">X</a></div><div id="popuptooltip_body"></div>
</div></div>

<div id="page">
<div id ="headerwrapper">
<jsp:include page="/WEB-INF/views/header.jsp" />
<div id="wrapper">
<jsp:include page="/WEB-INF/views/mainmenu.jsp" />
</div>
</div>



	<link rel="stylesheet" type="text/css" href="http://www.mobilluck.com.ua/css/all20132.css" />
	

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
<div id="main" >





		<div class="ccatalog">
		<div class="cbreadcrumbs">
			<a href="http://www.mobilluck.com.ua/">Главная</a> /
		</div>
		<div>
			<h1 class="mgood_title">Поиск <i>«${req}»</i></h1>
						<span class="mgood_span"> </span>
		</div>
<div class="cclist">
<c:forEach items = "${goods}" var = "good" varStatus = "i">
	<div class="ccitem">
				<table class="ccitable" cellpadding="0" cellspacing="0">
					<tbody><tr>
						<td class="ccit_image" style="position:relative;display: block;">
							<a rel="nofollow" href="${pageContext.request.contextPath}/good/${good.id}/all"><img src="<c:url value="/resources/images${good.thumb}" />" data-original="<c:url value="/resources/images${good.thumb}" />" width="90" alt="${good.name}"  /></a>
						</td>
						<td class="ccit_descr">
							<p class="ccitd_title"><a href="${pageContext.request.contextPath}/good/${good.id}/all">${good.name}</a></p>
							<p class="ccitd_code">Код товара: ${good.id}</p>
							<div class="ccitd_resp">
								<img src="<c:url value="/resources/images/good/raiting${good.round_rating}.png" />" style="position: relative; top: 4px;"> ${good.votes_count} оценок
								
							</div>
							<div class="ccitd_info">${good.description}</div>
							
							<table class="ccitdtable" cellpadding="0" cellspacing="0">
								<tbody><tr>
									<td class="comprow" id="comprow144021"><a href="#" onclick="return addCompare(144021,164)" class="mgtl_add2cmp"><span>Добавить к сравнению</span></a></td>
									<td>
										<c:if test = "${!empty good.state.text}">${good.state.text}</c:if><c:if test = "${!empty good.mindeliverydays && !empty good.maxdeliverydays}">, ${mindeliverydays}-${maxdeliverydays} дней</c:if>
									</td>
								</tr>
								<tr><td class="ccitdt_sep"></td></tr>
								<tr>
									<td class="pricerow">
										
										<p class="ccitd_price">${good.price} грн</p>
									</td>
									<td>
                                    <a rel="nofollow" onclick="addtobuck('${pageContext.request.contextPath}/good/${good.id}/all',${good.id},${bucketsize})"><img src="http://img.mobilluck.com.ua/img/btn_buy_new.gif" alt="Добавить в корзину - купить Духовой шкаф электрический ElectroLux EEB 4231 POX (12 мес.)" height="37" width="107"></a>
									</td>
								</tr>
								<tr><td class="ccitdt_sep"></td></tr>
							</tbody></table>
						</td>
					</tr>
				</tbody>
				</table>
			</div>
			</c:forEach>
			</div>
			<div class="ccpnav">
		<form id = "pages">
		<span class="arrow">&#8592;</span><a class="a-text a-prev" href="${pageContext.request.contextPath}/category/${category.id}/pagin/${currentpage-1}">Пред.</a>
		<c:forEach begin="1" end="${pagecount+1}" var="i">
		<c:choose>
		<c:when test = "${i==currentpage}">
		<span class="">${i}</span><input type = "hidden" value = "${i}" name = "pagenum" id = "pagenum"/>
		</c:when>
		<c:otherwise>
		<a class="a-text" href="${pageContext.request.contextPath}/category/${category.id}/pagin/${i}">${i}</a>
		</c:otherwise>
		</c:choose>
		</c:forEach>
		<a class="a-text a-next" href="${pageContext.request.contextPath}/category/${category.id}/pagin/${currentpage+1}">След.</a><span class="arrow">&#8594;</span>
		</form>
		</div>	
</div>
</div>


<div class="both"></div></div>	</div></div></div>

	<jsp:include page="/WEB-INF/views/footer.jsp" />


	<div id="popupbg"></div>
	<div id="dialog_wrapper">
		<div id="dialog_outer">
			<div id="dialog_inner">
				<a id="dialog_close" href="#">Закрыть</a>
				<div id="dialog_title">Войти в интернет-магазин</div>
				<div id="dialog_content"></div>
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

	</body>
</html>

