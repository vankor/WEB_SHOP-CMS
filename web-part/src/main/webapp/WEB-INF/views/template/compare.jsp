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
	
	<link rel="stylesheet" type="text/css"  href="<c:url value="/resources/trackbar.css" />" rel="stylesheet" >
<title>Сравнение товаров категории ${category.name}</title>

<jsp:include page="/WEB-INF/views/template/head.jsp" />
<script type="text/javascript">

var url = "<c:out value="${pageContext.request.contextPath}"/>";

</script>




<script type="text/javascript" charset="utf-8">
$(function() {
$("img").lazyload({ threshold : 100, effect : "fadeIn", failurelimit : 100 });
});
</script>

</head>
<body >

<div style="fixup" id="fixup" name="fixup"><div id="uplink">Наверх</div></div>

<div id="page">
<jsp:include page="/WEB-INF/views/template/header.jsp" />
<div id="wrapper">
<jsp:include page="/WEB-INF/views/template/mainmenu.jsp" />
</div>
<div style="padding: 0px; width: 100%;">
<div id = "wrapper">
<div id = "main"><script language="javascript">
var cCompareProds = new Array(271436,79365);


function remCompareGood(goodid, catid, url, section){ 

//	alert(goodid +","+catid+", "+url+"/fromcompare"+", "+section);
	
	$.ajax({
	       type: "POST",
	  	   url: url+"/fromcompare",
	       data: {"goodid":goodid, "catid":catid, "section": section},
	       cache: false,                                
	       success: function(responce){
	    	   $("#tocompare").html("");
	    	   $("#tocompare").html(responce);
	       }
	     });
		
	}

function remCompare(pid)
{
	remFromCompareList(pid);

	document.forms["frmcmpupdt"].submit();
}
</script>
<div class="mgoods">
    <div class="chelps">
	   <div class="cbreadcrumbs">

	<div itemscope itemtype="http://data-vocabulary.org/Breadcrumb" style="display: inline;">
		<a itemprop="url" href="http://www.mobilluck.com.ua/"><span itemprop="title">Главная</span></a> › </div>
		<div itemscope itemtype="http://data-vocabulary.org/Breadcrumb" style="display: inline;">
			<a itemprop="url" href="http://www.mobilluck.com.ua/katalog/mobile_bluetooth/" title=" Телефоны и плееры"><span itemprop="title"> Телефоны и плееры</span></a> › </div>
		<div itemscope itemtype="http://data-vocabulary.org/Breadcrumb" style="display: inline;">
			<a itemprop="url" href="http://www.mobilluck.com.ua/katalog/game-stations/" title="Игровые приставки"><span itemprop="title">Игровые приставки</span></a>        </div>
	   </div>
	<div>
		<h1 class="mgood_title">Товары для сравнения</h1>
		<span class="mgood_span"> 2</span>
	</div>
	<form action="/compare_all.php" name="frmcmpupdt" method="POST" style="margin:0; padding:0;">
	<input type="hidden" name="action" id="action" value="" />
	<input type="hidden" name="delpid" name="delpid" value="0" />
	</form>
	<div class="mgcompare">
		<div class="mgcsort">
			<p>Показать:</p>
			<ul class="mgcslist">
				<li class="active">все характеристики</li>
				<!--<li><a href="#">только отличия</a></li>-->
			</ul>
		<a href="${pageContext.request.contextPath}/catalog/${category.page.fullurl}/pagin/1" class="mgtl_add2cmp"><span>Добавить к сравнению</span></a>		</div>
		<div id = "tocompare" class="w100 float">
<ul id="mgccarousel" class="jcarousel-skin-mgcctango"><li>
				<table class="mgctable" cellpadding="0" cellspacing="0">
				
			<tr>
			<td class="mgctleft"></td>
			<c:forEach items = "${goods}" var = "good">
			<td class="mgctinside">
					<p class="mgcti_delete"><a onclick = "remCompareGood(${good.id},${category.id},'${pageContext.request.contextPath}/${category.id}/comparegoods','remcompare')" href="#"><span>Удалить</span></a></p>
					<div class="ccitem2" style="width: 200px;">
						<div class="cci2image">
							<table>
								<tr>
									<td><a href="${pageContext.request.contextPath}/catalog/${good.page.fullurl}/all" title="${good.name}" target="_blank"><img src="<c:url value="/resources/images${good.thumb}" />" width="95" height="56" alt="${good.thumb}"  /></a></td>
								</tr>
							</table>
						</div>
						<div class="cci2info">
							<p class="cci2_mdl" style="text-align: center;"><a href="${pageContext.request.contextPath}/catalog/${good.page.fullurl}/all">${good.name}</a></p>
							<table class="w100">
								<tr>
									<td>
										
										<p class="mgcti_price" style="font-size: 16px;"> ${good.price} </p>
									</td><td>
											 <div class="buy_btn_catalog_new" onclick="addtobuck(${good.id},${bucketsize})" title="Добавить в корзину - купить ${good.name}"></div>
										</td></tr>
							</table>
						</div>
					</div>
				</td>
				
					
				
				
				
				</c:forEach>
				</tr>
				<c:forEach items = "${propscompared}" var = "propsegment">
				<tr><td class="mgctleft" colspan = "${goodcount}">${propsegment.sgm.name}</td></tr>
				<c:forEach items = "${propsegment.propbeans}" var = "propcompare">
				<tr><td class="mgctleft">${propcompare.prop.name}</td><c:forEach items = "${propcompare.comparegoods}" var = "goodcomp"><td class="mgctinside">${goodcomp.val.value}</td></c:forEach></tr>
				</c:forEach>
				</c:forEach>
</table>
				</li></ul>		</div>
	</div>
	<div class="both"></div>
</div>
<div class="both"></div></div></div></div>
	<noindex>
	
	<jsp:include page="/WEB-INF/views/template/lastviews.jsp" />
		</noindex>
		</div></div>

	
	<jsp:include page="/WEB-INF/views/template/footer.jsp"/>

    

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


		
<div id="bucket" style="display: none;"></div>
		</body>
</html>