<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1251" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>${config.startpagetitle}</title>

<jsp:include page="/WEB-INF/views/template/head.jsp" />
<script type="text/javascript">

var url = "<c:out value="${pageContext.request.contextPath}"/>";




function signin(){
	
	    $.get(
	    		"/login",
	        {section: "login"},
	        function(data){
	                  	alert(data);
				 $("#dialog_wrapper").html("");
	            $("#dialog_wrapper").html(data);
//	            $("body,html").animate( {scrollTop: $("#responce_block").position().top}, 1000, "swing");
	        }
	        )
	    return false;

	
	}






</script>




<script type="text/javascript" charset="utf-8">
$(function() {
$("img").lazyload({ threshold : 100, effect : "fadeIn", failurelimit : 100 });
});
</script>

</head>
<body >

<div id="page">
<c:if test = "${!empty user}">
${user.id} rgrg
</c:if>
<div id ="headerwrapper">
<jsp:include page="/WEB-INF/views/template/header.jsp" />
<div id="wrapper">
<jsp:include page="/WEB-INF/views/template/mainmenu.jsp" />
</div>
</div>
	<div id="wrapper">

					<div style="padding: 0px; width: 100%;">
						
						<div id="wrapper" style="margin:0 auto; padding-top: 0px;">
					
<div class="w100 float" id="index999">
<c:if test = "${!empty config && config.hasSlider == true}">
<jsp:include page="/WEB-INF/views/template/slider.jsp" />
</c:if>
<div class="probel"></div>

<c:if test = "${!empty config && config.hasMainInfoOnMain == true}">
<jsp:include page="/WEB-INF/views/template/maininfo.jsp" />
</c:if>

<div class="probel"></div>
<script type="text/javascript" src="http://js.mobilluck.com.ua/js/jquery-ui.js" ></script>

<c:if test = "${!empty config && config.hasTopGoodsListOnMain == true}">
<jsp:include page="/WEB-INF/views/template/topgoods.jsp" />
</c:if>

<div class="probel"></div>
<div style="border-radius: 5px; margin: 0px auto; width: 1000px; background-color: #dbedff; padding: 10px 20px; font-size: 18px; text-align: center;">
<span style="float: left; margin: 7px 0px 0px 0px;">Хотите узнавать про лучшие скидки?</span>
<input type="text" name="i_fu_email" id="i_fu_email" style="float: left; height: 28px; width: 350px; margin: 0px 0px 0px 10px;" onclick="(this.value == 'Ваша электронная почта' ? this.value='' : this.value)" onfocus="(this.value == 'Ваша электронная почта' ? this.value='' : this.value)" onblur="(this.value == '' ? this.value='Ваша электронная почта' : this.value)" value="Ваша электронная почта" />
<div class="registration" onclick="return podpiska(this, 'i_fu_email', 0)"></div>
<div style="clear: left;"></div>
</div>
<div class="probel"></div>



<c:if test = "${!empty config && config.hasActionsOnMain == true}">
<jsp:include page="/WEB-INF/views/template/mainactions.jsp" />
</c:if>

<div class="probel"></div>

<c:if test = "${!empty config && config.hasNewsOnMain == true}">
<jsp:include page="/WEB-INF/views/template/mainnews.jsp" />
</c:if>

   <script>
        var load_array = new Array(12);
        var tekid = 0;
		$(document).ready(function()
        {
            $('#slides').slides({
				preload: false,
				preloadImage: 'nd/img/loading.gif',
				play: 5000,
				pause: 2500,
				hoverPause: true,
				effect: 'fade',
				animationComplete: function(){
					/*
					$("html, body").animate( { scrollTop: $(document).scrollTop()+1}, "1");
					$("html, body").animate( { scrollTop: $(document).scrollTop()-1}, "1");
					*/
				}
			});
            //$("#featured > ul").tabs({fx:{opacity: "toggle"}}).tabs("rotate", 10000, true);
            var nxt = setTimeout('slect_spec(tekid);', 1);
            $("#gcacarousel").jcarousel({
				scroll: 5,
				itemLoadCallback: function (carousel, state) {
					$("html, body").animate( { scrollTop: $(document).scrollTop()+1}, "1");
					$("html, body").animate( { scrollTop: $(document).scrollTop()-1}, "1");
				}
			});
        });

   
	</script>
    </div><div class="both"></div></div></div></div>	</div></div></div>

<jsp:include page="/WEB-INF/views/template/footer.jsp" />



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

		
<div id="bucket" style="display: none;"></div>

		</body>
</html>