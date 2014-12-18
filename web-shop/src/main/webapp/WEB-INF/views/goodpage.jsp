<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1251" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>${good.page.title}</title>

<jsp:include page="/WEB-INF/views/head.jsp" />
<link rel="stylesheet" type="text/css" href="http://css.mobilluck.com.ua/js/fancybox/jquery.fancybox-1.3.2.css" />

		<script type="text/javascript" src="http://js.mobilluck.com.ua/js/jquery-1.4.4.min.js"></script>


<script src="<c:url value="/resources/js/mascedinput.js" />"></script>
<script src="<c:url value="/resources/js/combobox.js" />"></script>
<script src="<c:url value="/resources/js/lazyload.js" />"></script>
<script src="<c:url value="/resources/js/popups.js" />"></script>
<script src="<c:url value="/resources/js/myajax.js" />"></script>
<script src="<c:url value="/resources/js/main.js" />"></script>
<script src="<c:url value="/resources/js/carousel.js" />"></script>
<script src="<c:url value="/resources/js/jcarousellite.js" />"></script>
<script src="<c:url value="/resources/js/base64.js" />"></script>
<script type="text/javascript" src="http://js.mobilluck.com.ua/js/cloud-zoom.1.0.2.js"></script>
<script type="text/javascript" src="http://js.mobilluck.com.ua/js/fancybox/jquery.fancybox-1.3.2.pack.js"></script>
<script src="<c:url value="/resources/js/myscrpits.js" />"></script>



<script type="text/javascript">
	

var dt = new Date();
dt.setHours(dt.getHours()+24*180);

var debug1 = '', mt, mStatus, mt2;
var answerinput;
var url = "<c:out value="${pageContext.request.contextPath}"/>" +"/good/"+ "<c:out value="${good.id}"/>"+"/"+"<c:out value="${section}"/>";
var baseurl = "<c:out value="${pageContext.request.contextPath}"/>"; 
var goodservurl = "<c:out value="${pageContext.request.contextPath}"/>"+"/goodservices"; 
function changeSect(section){ 
//	alert("#answertext"+parentcom_id);
	var text;
	if(parentcom_id>0){
	text = $("#answertext"+parentcom_id).val();
	$.ajax({
	       type: "POST",
	  	   url: url+"/addanswer",
	       data: {"goodid":goodid, "text":text, "parentcomm_id":parentcom_id, "section":"add"},
	       cache: false,                                
	       success: function(responce){
	    //	   alert(responce);
	   //			document.getElementById('commsection').style.display='block';
	    	   $("#mainpart").html("");
	    	   $("#mainpart").html(responce);
	   	//	     $(".goodMenuNode").css("background", "blue" );
	      //     $(el).css("background", "gray" );
	     	//	location.href=url+'/#tab='+$(el).attr("id"); 
	       }
	     });
	
	}
}	

function utf8_to_b64( str ) {
	  return window.btoa(unescape(encodeURIComponent( str )));
	}

function b64_to_utf8( str ) {
	  return decodeURIComponent(escape(window.atob( str )));
}

function addcomment(parentcom_id, goodid){ 
//	alert("ewgewgewg");
//	alert("#answertext"+parentcom_id);
	var url = "<c:out value="${pageContext.request.contextPath}"/>" +"/good/"+ "<c:out value="${good.id}"/>"+"/"+"<c:out value="${section}"/>";
	var text;
	if(parentcom_id>0){
	text = $("#answertext"+parentcom_id).val();
	$.ajax({
	       type: "POST",
	  	   url: url+"/addanswer",
	       data: {"goodid":goodid, "text":text, "parentcomm_id":parentcom_id, "section":"add"},
	       cache: false,                                
	       success: function(responce){
	    //	   alert(responce);
	   //			document.getElementById('commsection').style.display='block';
	    	   $("#commsection").html("");
	    	   $("#commsection").html(responce);
	    	   

	    	   
	    	   
	       }
	     });
	
	}
	else{
		
		name = $("#name").val();
		text = $("#text").val();
		plus = $("#plus").val();
		minus = $("#minus").val();
		note = $("#note").val();
	if(name=="" ) {$("#responce_block").append("<div style = 'float:left; clear:both;' id = 'msg'>Заполните имя!</div>");setTimeout('$("#msg").fadeOut("200")', 2000); return;}
	if(text=="" ) {$("#responce_block").append("<div style = 'float:left; clear:both;'  id = 'msg'>Заполните текст!</div>");setTimeout('$("#msg").fadeOut("200")', 2000); return;}
	if(note=="" ) {$("#responce_block").append("<div style = 'float:left; clear:both;'  id = 'msg'>Необходимо оценить товар!</div>");setTimeout('$("#msg").fadeOut("200")', 2000); return;}
	/*	alert(url+"/addcomment");
		alert("Добавление комментария а не ответа!"+goodid +","+text+","+parentcom_id);*/

		
		$.ajax({
		       type: "POST",
		  	   url: url+"/addcomment",
		       data: {"goodid":goodid,"note":note, "name":name, "text":text, "plus":plus, "minus":minus, "parentcomm_id":parentcom_id, "section":"add"},
		       cache: false,                                
		       success: function(responce){
		    //	   alert(responce);
		   //			document.getElementById('commsection').style.display='block';
		   			
		    	   $("#commsection").html("");
		    	   $("#commsection").html(responce);
		    	   $("#responce_block").html("Комментарий добавлен!");
		    	   setTimeout('$("#responce_block").fadeOut("200")', 2000);
		 //   	   $("#commsection").html("уцпуцпцуп");
		   	//	     $(".goodMenuNode").css("background", "blue" );
		      //     $(el).css("background", "gray" );
		     	//	location.href=url+'/#tab='+$(el).attr("id"); 
		       }
		     });
		
		
	}

		
	}
	


function callanswer(id, goodid){
//	alert("answertext"+id);
//	alert("addanswer"+id);
//	alert("addcomment("+url+","+id+")");
	var div;
	var buck = '"';
	div = document.createElement("div");
	div.setAttribute("id", "answer"+id);
	div.innerHTML = "<form><label id = 'answer"+id+"' name='answer["+id+"]'> Ваш ответ на комментарий: </label><input style='width: 200px; height: 25px;' id='answertext"+id+"' name='answer["+id+"]' type='text'/><input  value = 'Ответить' type = 'button' onclick = 'addcomment("+id+","+goodid+")'></input></form>";
//	alert("<label id = 'answer"+id+"' name='answer["+id+"]'> Ваш ответ "+id+" name: </label><input id='answer"+id+"' name='answer["+id+"]' type='text'/><input value = 'Ответить' type = 'button' onclick = 'addcomment("+buck+url+buck+","+id+","+goodid+")'></input>");	
	document.getElementById("addanswer"+id).appendChild(div);
	$("#answerlink"+id).hide();
	
}


function ultraservise_click(prodid, sesid, lnk, pcredit)
{
    //alert($("#chultra").attr("checked"));
    //$("#ultra_value").val();
    if(true)
    {
        var ww = $(window).width(), wh = $(window).height();
    	var left = this.offsetLeft, top = this.offsetTop;
    	if (navigator.userAgent.toLowerCase().indexOf("msie") != -1){left += (ww-978)/2;}

    	var coor = getOffset( lnk );
    	left = coor.left;
    	top = coor.top;

        $("#popup_cart_title").html("Корзина заказов");
    	$("#popup_cart_content").html("<br /><br />Идет подгрузка информации...<br /><br />");

        $("#popupbg").show();
        $("#popup_wrapper").show();

        $.ajax({
            type: "POST",
            url: "/ajx/ajx_jq.php",
            data: 'cmd=uh_com_cartitemadd2&prodid='+prodid+'&csi='+sesid,
            dataType: "text",
            success: function(data){
            	if(typeof $("#ys").val()!="undefined")
                {
                    var ys = $("#ys").val();
                }
                else
                {
                  var ys = 2;
                }
                //alert(sesid+' '+data+' '+ys);
                ui_cart_ultraset(sesid, data, ys, "popup_cart_content");
    		ui_cart_view( sesid, "popup_cart_content", true );

    	    }
    	});

     }
     else
     {
        $("#popup_cart_title").html("Корзина заказов");
    	$("#popup_cart_content").html("<br /><br />Идет подгрузка информации...<br /><br />");

        $("#popupbg").show();
        $("#popup_wrapper").show();

        $.ajax({
            type: "POST",
            url: "/ajx/ajx_jq.php",
            data: 'cmd=uh_com_cartitemadd3&prodid='+prodid+'&csi='+sesid,
            dataType: "text",
            success: function(data){
            	//alert("3 "+data);
                var ys = $("#ys").val();
                //alert(sesid+' '+data+' '+ys);
                ui_cart_ultraset(sesid, data, 0, "popup_cart_content");
                ui_cart_view( sesid, "popup_cart_content", true );

    	    }
    	});

     }
     $('body,html').animate( {scrollTop: 0}, 1000, 'swing');
}



$(document).ready(function(){
	var menu = $("ul.mainmenu2 li.mmd_item");
	var mmps = $(".hpslist .subm_item");
	for (var i=0; i<menu.length; i++){$(menu[i]).attr("id", "topm"+(i+1));};
	for (var i=0; i<mmps.length; i++){$(mmps[i]).attr("id", "subm"+(i+1));};

	menu.hover(
		function(){
			var idx = $(this).attr("id").replace("topm", "");
			clearTimeout(mt);
			mt = setTimeout(function(){
				if (mStatus == 'show'){
					menu.each(function(){$(this).removeClass("active");});
					mmps.each(function(){$(this).hide();});
					$("#topm"+idx).addClass("active");
					$("#subm"+idx).show();
				}
			}, 250);
			mStatus = 'show';
		},
		function(){
			var idx = $(this).attr("id").replace("topm", "");
			menu.each(function(){$(this).removeClass("active");});
			mmps.each(function(){$(this).hide();});
			mStatus = 'hide';
		}
	);
	mmps.hover(
		function(){
			var idx = $(this).attr("id").replace("subm", "");
			menu.each(function(){$(this).removeClass("active");});
			$("#topm"+idx).addClass("active");
			$(this).css("display", "block");
			mStatus = 'show';
		},
		function(){
			var idx = $(this).attr("id").replace("subm", "");
			menu.each(function(){$(this).removeClass("active");});
			mmps.each(function(){$(this).hide();});
			mStatus = 'hide';
		}
	);

	
	$("#tfvccarousel").jcarousel({scroll: 4});
	
	$("#hreff").bind("click", function(){
		var d = $(this).attr("data-href");
		var t = b64_to_utf8(d);
//		alert(t);
		window.location.href = "http://www."+t;
		
	});
	

	$(document).bind("click", function(){$(".ccsdropdown").css("display", "none");});
	$(".faddr_p a").bind("click", function(){
		var contpan = $(this).parent().parent().find(".ccsdropdown");
		contpan.css("display", "block");
		return false;
	});

	$("#rfiliallnk").bind("click", function(){
		var contpan = $(this).parent().parent().find(".ccsdropdown");
		contpan.css("display", "block");
		return false;
	});

	$("#lnkfilialinfo").bind("click", function(){
		var selobj = document.getElementById('s_thft_city');
		var selph = selobj.options[selobj.selectedIndex].value;
		var selph_p = selph.split(":");
		var selfil_id = selph_p[0];
		var lnk = document.getElementById('lnkfilialinfo');
		loadFilialInfo( lnk, selfil_id );
	});

	$("#ffavrss").attr("href", "http://www.mobilluck.com.ua/rss.php");
	$("#ffavtwit").bind("click", function(){ location.href="http://" + "twitter" + ".com/Mobilluck_spec"; });
	$("#ffavface").bind("click", function(){ location.href="http://" + "www" + ".facebook" + ".com/mobilluckua?sk=app_4949752878"; });
	$("#ffavvk").bind("click", function(){ location.href="http://" + "vkontakte" + ".ru/club17491020"; });
	$("#ffavodnk").bind("click", function(){ location.href="http://" + "www" + ".odnoklassniki" + ".ua/group/55614922031161" ; });
	$("#ffavgplus").bind("click", function(){ location.href="https://" + "plus" + ".google" + ".com/108918605816953397967/posts" ; });
	$("#ffavytube").bind("click", function(){ location.href="https://" + "www" + ".youtube" + ".com/user/mobilluckreview" ; });
	$("#ffavmailru").bind("click", function(){ location.href="http://" + "www" + ".my.mail" + ".ru/community/mobilluck/" ; });
			$("#gcacarousel2").jcarousel({
				scroll: 4,
				itemLoadCallback: function (carousel, state) {
					$("html, body").animate( { scrollTop: $(document).scrollTop()+1}, "1");
					$("html, body").animate( { scrollTop: $(document).scrollTop()-1}, "1");
				}
			});
				$("#mgicarousel").jcarousel({scroll: 1});
	$("#gcacarousel").jcarousel({
		scroll: 4,
		itemLoadCallback: function (carousel, state) {
			$("html, body").animate( { scrollTop: $(document).scrollTop()+1}, "1");
			$("html, body").animate( { scrollTop: $(document).scrollTop()-1}, "1");
		}
	});
	$("#gcacarousela").jcarousel({scroll: 1});
	$("#wscarousel").jcarousel({scroll: 1});

	$(".mgtl_add2buy").bind("click", function(){
		document.forms["addwishfrm"].submit();
	});

	$("a[rel=gcphoto_group]").fancybox({
		overlayOpacity: 0.8,
		overlayColor: '#000',
		transitionIn: 'none',
		transitionOut: 'none',
		titlePosition: 'over',
		titleFormat: function(title, currentArray, currentIndex, currentOpts){
			return '<'+'div id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</'+'div>';
		}
	});
	$("a[rel=gcphoto_group2]").fancybox({
		overlayOpacity: 0.8,
		overlayColor: '#000',
		transitionIn: 'none',
		transitionOut: 'none',
		titlePosition: 'over',
		titleFormat: function(title, currentArray, currentIndex, currentOpts){
			return '<'+'div id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</'+'div>';
		}
	});
//	$("#popup_close").bind("click", function(){
//		$("#popupbg").hide();
//		$("#popup_wrapper").hide();
//		$("#bucket").css("display", "none");
//		$("#bucket").html("");
//				return false;
//	});-->
	$('#uplink').bind('click',function(){
		$('body,html').animate( {scrollTop: 0}, 1000, 'swing');
		return false;
	});
});

$(window).scroll(function(){
	if($(document).scrollTop()>499)	$('#fixup').show(1000);
	else	$('#fixup').hide(1000);
});

function showmenu(sid, gid)
{
	$("#d_"+sid).children().css('display', 'none');
	$("#ul_"+sid).children().removeClass('limenu_active');
	$("#ul_"+sid).children().removeClass('lright_active');

	$("#t_"+sid+"_"+gid).css('display', 'block');
	$("#li_"+sid+"_"+gid).addClass('limenu_active');

	if(sid>5)	$("#li_"+sid+"_"+gid).addClass('lright_active');
}
</script>


<script type="text/javascript" charset="utf-8">
$(function() {
$("img").lazyload({ threshold : 100, effect : "fadeIn", failurelimit : 100 });
});
</script>

</head>
<body>
<c:if test = "${section == 'all'||empty section}">
        <div style="width: 750px; clear: left; margin-top: 50px; display: none;" id="description" xmlns:v="http://rdf.data-vocabulary.org/#" typeof="v:Review-aggregate"><div id="dh1"><h1 class="mgood_title"  property="v:itemreviewed"> ${good.name} </h1></div>
            <p style="font-family: sans-serif; font-size: 24px; color: #2d2d2d;">
            Описание
            </p>
            <div style="clear: left;" itemscope itemtype="http://schema.org/Offer">
                <div style="float: left; border: 0px solid red; display: block;  width: 300px; text-align: center;">
                    <img src="<c:url value="/resources/images${good.thumb}" />" width="290" alt="${good.name}" title="${good.name}" rel="v:photo" style="padding: 0px 15px 10px 0px;" itemprop="image"/>
                </div>
                <div style="float: left;">
                    <div style="width: 300px; font-family: sans-serif; font-size: 24px; color: #2d2d2d;">${good.name}</div>
                <!--     <div class="club_cost" style="margin-bottom: 5px;">
                        <div style="float: left; background-color: #E78000; padding: 2px 5px; border-radius: 5px; color: white; font-size: 14px;">
                            + 53 балла
                        </div>
                        <div class="btn_help" style="float: left; margin-left: 10px;" onclick="$('#popupbg').hide(); $('#dialog_wrapper').hide(); return load_text(this, 'plusballs', 53)"></div>
                    </div>
                 --> 
                 
                    <div class="old_cost">
                           
							
                        </div>
                    <div style="display: block;">
                        <div class="buy_block">
                            <div class="costline" property="v:pricerange">
                                <span class="cost" >${good.price}</span>
                                <span class="currency">грн</span>
                            </div>
                         <!--     <div class="btn_buy" onclick="$('#popupbg').hide(); $('#dialog_wrapper').hide(); return buy_popup(89477,'it986qr2arh979bde5unt5nfl2', this, '0')"></div> -->
                       
                       			<div class="btn_buy" onclick="$('#popupbg').hide(); $('#dialog_wrapper').hide(); return addtobuck(${good.id},${bucketsize})"></div>
                        </div>
                    </div>
                            <div class="presence" itemprop="availability" content="in_stock">
                                ${goodstate}
                            </div> 
                            <div class="srok">
                                <a href="" class="javalink" onclick="$('#popupbg').hide(); $('#dialog_wrapper').hide(); return load_text(this, 'uh_base', ${good.id})">Узнать срок и стоимость доставки</a>
                            </div>
                </div>
            </div>
            <div id="descr" class="prod_descr" style="clear: left; width: 650px;  overflow: hidden; display: block; padding-top: 10px;">
            <h2 style="font-family: sans-serif; font-size: 14px; color: #2d2d2d;">Описание ${good.name}</h2><br>${good.description}<br /><br />
                Желаете купить ${good.name}? Звоните!
            </div>
    <div style="display:none">
    <>
        <div rel="v:rating">
            <img src="<c:url value="/resources/images/good/raiting${averagenote}.png" />" typeof="v:Rating"/>
            <span property="v:value">4</span>
            <span property="v:best">5</span>
        </div>
        <span property="v:votes">${comCount}</span>
        <span property="v:count">${votes_count}</span>
        <span property="v:summary">30 игр/ ЖК-экран 2,5&quot;/ 16 Бит/ 64Мб</span>
        
    </div>
        </div>
</c:if>        
        
        <div style="fixup" id="fixup" name="fixup"><div id="uplink">Наверх</div></div>
<div id="searchdrop" style="visibility: hidden; display: none; width: 462px;">
	<div class="searchdropcont">
		<div id="searchdrop_btn"><a href="javascript:hideTooltip('searchdrop')" class="closebut">X</a></div><div id="searchdrop_body"></div>
</div><!--[if lte IE 6.5]><iframe></iframe><![endif]--></div>
<div id="popuptooltip" style="visibility: hidden; display: none;">
	<div class="tooltipwndcont">
		<div id="popuptooltip_btn"><a href="javascript:hideTooltip('popuptooltip')" class="closebut">X</a></div><div id="popuptooltip_body"></div>
</div>
</div>
<div id="page">
<jsp:include page="/WEB-INF/views/header.jsp" />
	<div id="wrapper">
		<div id="header">
			<div class="hmainmenu">
				<noindex>				
				<jsp:include page="/WEB-INF/views/mainmenu.jsp" />
				</noindex>		
				
			</div><!-- header -->

			</div>
					<div style="padding: 0px; width: 100%;">
					
						<div id="wrapper" style="margin:0 auto; padding-top: 0px;">
				
<div id="main" >

<div class="mgoods">
	<div class="cbreadcrumbs">
    <div itemscope itemtype="http://data-vocabulary.org/Breadcrumb" style="display: inline;">
					<a itemprop="url" href="${pageContext.request.contextPath}"><span itemprop="title">Главная</span></a> ›
				</div>
				
				<c:forEach items = "${pathtocategory}" var = "cat">
           		     <div itemscope itemtype="http://data-vocabulary.org/Breadcrumb" style="display: inline;">
          		  	<a itemprop="url" href="<c:url value = "/catalog/${cat.page.fullurl}/pagin/1"/>" title="${cat.name}">
          		  	<span itemprop="title"> ${cat.name}</span></a> › </div>
          		</c:forEach>
					<div itemscope itemtype="http://data-vocabulary.org/Breadcrumb" style="display: inline;">
					<span itemprop="title">${good.name}</span>
					</div>  
    </div>
	<div class="w100 float">
<div class="mgood_title" id="h1_1"></div>	
</div>
	<p class="mg_code" id="mg_code">Код товара: ${good.id}</p>
	
	
	
	    <script>
    var w = 0;
    var h = 0;
    var mv = 1;
    $(document).ready(function(){
        //Смена изображения при клике
        $(".smallphoto").click(function() {
            var w = 0;
            var wid = $(this).children().attr("wid");
            w = wid*350;
            w = "-"+w+"px";
            $("#photolist").css("left", w);
            //$("#photolist").animate({top: w}, 500);
            return false;
        });
    });

    </script>
	<script type="text/javascript" src="http://www.mobilluck.com.ua/js/jquery.scrollTo-min.js"></script>
	
	 <style>
                                #raiting {position:relative; height:14px; cursor:pointer; width:82px; float:left; overflow: hidden;} /* Блок рейтинга*/
                                #raiting_blank, #raiting_votes, #raiting_hover {height:14px; position:absolute;}
                                #raiting_blank { background:url(http://www.mobilluck.com.ua/img/product/raiting.png); width:82px;} /* "Чистые" звездочки */
                                #raiting_votes {background:url(http://www.mobilluck.com.ua/img/product/raiting.png) 0 -15px;} /*  Закрашенные звездочки */
                                #raiting_hover {background:url(http://www.mobilluck.com.ua/img/product/raiting.png) 0 -15px; display:none}  /*  звездочки при голосовании */
                                #raiting_info {float: left; width: 100%;}
                                #raiting_info img{vertical-align:middle; margin:0 5px; display:none}
                                #raiting_star {margin-top: 5px;}
                                </style>


                                <script type="text/javascript">
                                $(document).ready(function(){
                                total_reiting = parseInt("<c:out value="${total_rating}"/>"); // итоговый ретинг
                                good_id = parseInt("<c:out value="${good.id}"/>"); // id статьи
                                votes_count = parseInt("<c:out value="${votes_count}"/>"); // id статьи
                                var star_widht = total_reiting*16;
                                //$("#raiting_votes").width(star_widht);
                                //$("#raiting_info h5").append(total_reiting);

                                //if(he_voted == null){
                                	
                                	
                                    $("#raiting").hover(function() {
                                    	
                                          $("#raiting_votes, #raiting_hover").toggle();
                         	             },
                                    	  function() {
                                          $("#raiting_votes, #raiting_hover").toggle();
                                          $("#raiting_infot").html("Оцените товар");
                                    });
                                    
                                    
                                    var margin_doc = $("#raiting").offset();
                                    
                                    
                                    $("#raiting").mousemove(function(e){
                                    var widht_votes = e.pageX - margin_doc.left;
                                    if (widht_votes == 0) widht_votes =1 ;
                                    user_votes = Math.ceil(widht_votes/16);
                                    // обратите внимание переменная  user_votes должна задаваться без var, т.к. в этом случае она будет глобальной и мы сможем к ней обратиться из другой ф-ции (нужна будет при клике на оценке.
                                    $("#raiting_hover").width(user_votes*16);
                                    if(user_votes==1)
                                        $("#raiting_infot").html("Ужасно");
                                    else if(user_votes==2)
                                        $("#raiting_infot").html("Плохо");
                                    else if(user_votes==3)
                                        $("#raiting_infot").html("Нормально");
                                    else if(user_votes==4)
                                        $("#raiting_infot").html("Хорошо");
                                    else if(user_votes==5)
                                        $("#raiting_infot").html("Отлично");
                                    });
                                    
                                    
                                    // отправка
                                    $("#raiting").click(function(){
                                    $("#raiting_info span, #raiting_info img").toggle();
                                    $.get(
                                    url+"/estimate",
                                    {good_id: good_id, user_votes: user_votes , section: "item"},             // csi: "it986qr2arh979bde5unt5nfl2"//
                                    function(data){
                                    	$("#raiting_info").html(data);
                                    	votes_count++;
                              
                                        var t = total_reiting + user_votes
                             
                                        t = t/votes_count;
                               
                                        t = t*17;
                              //          alert(t);
                                    	$("#raiting_votes").width(t);
                           //         	$("#raiting_info span, #raiting_info img").toggle();
                                    	$("#raiting").unbind();
                                        //$("#raiting_hover").hide();

                                            }
                                    	   )
                                        });
                                //}
                                });
                                </script>
                            <style>
.blueborder
{
    border: 2px #c3dff0 solid;
    border-radius: 15px;
    width: 190px;
    padding: 8px 8px 8px 0px;
    margin-bottom: 5px;
}
.blueborder_p
{
    width: 100%;
    text-align: center;
    font-size: 16px;
    font-weight: bold;
    margin-bottom: 10px;
}
.blueborder_ul
{
    padding: 3px 0 0 0;
    margin: 0 0 0 23px;
    color:  #c3dff0;
    list-style-type: disc;
    font-size: 15px;
    line-height: 14px;
}
.blueborder_ul li
{
    margin-top: 4px;
}
.blueborder_txt
{
    margin-top: -3px;
    color: black;
    font-size: 13px;
    line-height: 14px;
}
</style>

      <script language="javascript">
			$("#rfiliallnk2").bind("click", function(){
				var contpan = $(this).parent().parent().find(".ccsdropdown");
				contpan.css("display", "block");
				return false;
			});
            function ultrasel(sel)
            {
                if(sel==2)
                {
                    $("#rfiliallnk2").html("на 2 года");
                    $("#ultracost").html("40");
                    $("#ys").val(2);
                }
                else
                {
                    $("#rfiliallnk2").html("на 3 года");
                    $("#ultracost").html("55");
                    $("#ys").val(3);
                }
                return false;
            }
        </script>

                <script type="text/javascript">
                $(document).ready(function(){
                    $(".jlresp").click(function(pos){
                        var rid = $(this).attr("rid");
                        $.get(
                            "http://www.mobilluck.com.ua/ajx/raiting.php",
                            {rid: rid, val: $(this).attr("val"), uid:0, ses_id: "it986qr2arh979bde5unt5nfl2", type: "response"},
                            function(data){
                                var t = data.split(";");
                                if(t.length>1)
                                {
                                    $("#pl"+rid).html(t[0]);
                                    $("#mn"+rid).html(t[1]);
                                }
                                else
                                {
                                    $("#txt").html(data);
                                    $("#txt").css("left", pos.pageX-150);
                                    $("#txt").css("top", pos.pageY-50);
                                    $("#txt").css("display", "block");
                                    $("#txt").animate({opacity: "100"}, 2000, "swing");
                                    setTimeout('$("#txt").animate({opacity: "0"}, 1000, "swing")', 3000);
                                    setTimeout('$("#txt").css("display", "none")', 3000);
                                }
                            }
                            )
                        return false;
                    });

                    $(".answerlink").click(function(pos){
                        var rid = $(this).attr("rid");
                        $.get(
                            "http://www.mobilluck.com.ua/ajx/raiting.php",
                            {rid: rid, uid: "it986qr2arh979bde5unt5nfl2", type: "answer"},
                            function(data){
                                //alert(data);
								$(".answer3").hide();
                                $("#answer"+rid).html(data);
                                $("#answer"+rid).show();
                            }
                            )
                        return false;
                    });

                    $(".responce").click(function(pos){
                        //var rid = $(this).attr("rid");
         //               alert(url+"/addresponce");
                        $.get(
                        		url+"/addresponce",
                            {section: "respblock", id:"<c:out value="${good.id}"/>"},
                            function(data){
          //                  	alert(data);
                                $("#responce_block").html(data);
                                $("body,html").animate( {scrollTop: $("#responce_block").position().top}, 1000, "swing");
                            }
                            )
                        return false;
                    });
                });
                </script>
	
	 <script>
            function changeContact3(regid, fc, fn, id1, id2)
            {
               	$("#"+id1).html(fc);
               	$("#"+id2).html(fn);
               	var dt = new Date();
               	dt.setHours(dt.getHours()+24*180);
               	var pcval = makeCookie("regioncity", regid, dt.toUTCString(), "/", window.location.hostname);
              	setCookie( pcval );
              	return false;
            }
            </script>
            <style>
                .ths_callback
                {
                    float: none;
                	background: none;
                	margin: 0px;
                	padding: 0px;
                	text-decoration:none;
                	cursor: pointer;
                    border-bottom: 1px dashed #007eff;
                    padding-top: 8px;
                }
            </style>
	 <script>
        function showalltext2()
        {
            if($("#descrseo").css("overflow")=="hidden")
            {
                $("#descrseo").css("overflow", "visible");
                $("#descrseo").css("height", "auto");
                $("#showdescrseo").css("display", "none");
                $("#hidedescrseo").css("display", "block");
            }
            else
            {
                $("#descrseo").css("overflow", "hidden");
                $("#descrseo").css("height", "20px");
                $("#hidedescrseo").css("display", "none");
                $("#showdescrseo").css("display", "block");
            }
            return false;
        }
        </script>
 <script type="text/javascript">
    $(window).load(function()
    {
                        //alert($("#h1").html());
                $("#h1_1").html($("#dh1").html());
                //alert($("#h1_1").html());
                $("#dh1").html("");
                	    var h1 = $("#mg_code").position();
		var h3 = $("#mg_code").height();
        var h2 = $("#tabline").height();
        $("#tabline").css("top", h1.top+h3+15+"px");
        $("#tabline").css("left", h1.left+"px");
        $("#tabline").css("display", "block");
        $("#mg_code").css("margin-bottom", h2+20+"px");
            h1 = $("#responce").position();
		h3 = $("#responce").height();
		h2 = $("#description").height();
		var h4 = $("#description").css("top");
 /*       $("#responce").append("<div style='clear:left; padding-top: 50px;'>"+$("#description").html()+"</div>");*/
        $("#description").html("");

        $("#showdescr").click(function()
        {
            $("#descr").css("overflow", "visible");
            $("#descr").css("height", "auto");
            $("#showdescr").css("display", "none");
            $("#hidedescr").css("display", "block");
        });
        $("#hidedescr").click(function()
        {
            $("#descr").css("overflow", "hidden");
            $("#descr").css("height", "300px");
            $("#hidedescr").css("display", "none");
            $("#showdescr").css("display", "block");
        });
            });


function strahovka_click(prodid, sesid, lnk, pcredit)
{
    if($("#chstrah").attr("checked")==true)
    {
        var ww = $(window).width(), wh = $(window).height();
    	var left = this.offsetLeft, top = this.offsetTop;
    	if (navigator.userAgent.toLowerCase().indexOf("msie") != -1){left += (ww-978)/2;}

    	var coor = getOffset( lnk );
    	left = coor.left;
    	top = coor.top;

        $("#popup_cart_title").html("Корзина заказов");
    	$("#popup_cart_content").html("<br /><br />Идет подгрузка информации...<br /><br />");

        $("#popupbg").show();
        $("#popup_wrapper").show();

        $.ajax({
            type: "POST",
            url: "/ajx/ajx_jq.php",
            data: 'cmd=uh_com_cartitemadd2&prodid='+prodid+'&csi='+sesid,
            dataType: "text",
            success: function(data){
                var stsum = $("#stsum").val();
                //alert(sesid+' '+data+' '+stsum);
                ui_cart_strahset(sesid, data, stsum, "popup_cart_content");
    			ui_cart_view( sesid, "popup_cart_content", true );

    	    }
    	});

     }
     else
     {
        $("#popup_cart_title").html("Корзина заказов");
    	$("#popup_cart_content").html("<br /><br />Идет подгрузка информации...<br /><br />");

        $("#popupbg").show();
        $("#popup_wrapper").show();

        $.ajax({
            type: "POST",
            url: "/ajx/ajx_jq.php",
            data: 'cmd=uh_com_cartitemadd3&prodid='+prodid+'&csi='+sesid,
            dataType: "text",
            success: function(data){
                ui_cart_strahset(sesid, data, 0, "popup_cart_content");
                ui_cart_view( sesid, "popup_cart_content", true );

    	    }
    	});

     }
     _gaq.push(['_trackPageview', 'http://www.mobilluck.com.ua/order_way/addtocart.html']);
     _gaq.push(['_trackPageview', 'http://www.mobilluck.com.ua/order_way/addtocart_strah.html']);
     $('body,html').animate( {scrollTop: 0}, 1000, 'swing');
}

var cCompareProds = new Array();
var cCompareType = 0;



function makeCmpButton(pid, tid, baseurl)
{
	//var tdo = $("#comprow"+pid);
    $("#cmprlnk").attr('href', baseurl+'/'+tid+'/comparegoods');
    $("#cmprlnk").html('Сравнить');
    //$("#cmprlnk").unbind('click');
	//tdo.html('<a href="/compare_all.php"><img src="/img/newbtn/btn_compare.gif" width="91" height="24" alt="Сравнить" /></a>');
}

function makeAddCmpLink(pid)
{
	var tdo = $("#comprow"+pid);

//	tdo.html('<a href="#" onclick="return addCompare('+pid+','+cCompareType+')" class="mgtl_add2cmp"><span>Добавить к сравнению</span></a>');
}

function remCompare(pid)
{
	remFromCompareList(pid);
	makeAddCmpLink(pid);

	updateCompareList();
}

function updateCompareList()
{
	//$("#progress"+select_id).show();
    $.ajax({
        type: "POST",
        url: "/ajx/ajx_jq.php",
        data: 'cmd=uh_com_listcomp',
        dataType: "text",
        success: function(data){
        	//$("#progress"+select_id).hide();
            $("#comppanp").html(data);
            $("#comppnum").html( $("#comppanp").children().length );

            if( $("#comppanp").children().length == 0 )
            	$("#compdiv1").css('display','none');
            else
            	$("#compdiv1").css('display','block');
        }
    });
}

function subscribe_product(prodid, lnk)
{
	var ww = $(window).width(), wh = $(window).height();
	var left = this.offsetLeft, top = this.offsetTop;
	if (navigator.userAgent.toLowerCase().indexOf("msie") != -1){left += (ww-978)/2;}

	var coor = getOffset( lnk );
	left = coor.left;
	top = coor.top;

	$("#dialog_title").html(dialogs[4][0]);
	$("#dialog_content").html(dialogs[4][1]);

	showPopupDlg(left, top);
	$(".dlgform").find("#frmprodid2").val(prodid);
    $(".dlgform").find("#dlgf_mail").val('');
	return false;
}


function show_mod(prodid, sectid, profid, cost_db, lnk, minus)
{

	var ww = $(window).width(), wh = $(window).height();
	var left = this.offsetLeft, top = this.offsetTop;
	if (navigator.userAgent.toLowerCase().indexOf("msie") != -1){left += (ww-978)/2;}

	var coor = getOffset( lnk );
	left = coor.left-minus;
	top = coor.top;

	$("#dialog_title").html("Модификации");
	$("#dialog_content").html("<br /><br />Идет подгрузка информации...<br /><br />");

	showPopupDlg(left, top, 750);
	//$("#popupbg").show();
	//$("#popup_wrapper").show();

	$.ajax({
        type: "POST",
        url: "/ajx/ajx_product.php",
        data: 'cmd=uh_getmod&prodid='+prodid+'&sectid='+sectid+'&profid='+profid+'&cost_db='+cost_db+'&csi=it986qr2arh979bde5unt5nfl2',
        dataType: "text",
        success: function(data){
        	//alert(data);
			$("#dialog_content").html(data);
	    }
	});
    //$('body,html').animate( {scrollTop: 0}, 1000, 'swing');
	return false;
}


function other_producer(lnk, pid)
{
    var ww = $(window).width(), wh = $(window).height();
	var left = this.offsetLeft, top = this.offsetTop;
	if (navigator.userAgent.toLowerCase().indexOf("msie") != -1){left += (ww-978)/2;}

	var coor = getOffset( lnk );
	left = coor.left;
	top = coor.top;

    var wd = 500;
    $("#dialog_title").html('Игровые приставки других брендов');
    $("#dialog_content").html("<br /><br />Идет подгрузка информации...<br /><br />");
    showPopupDlg(left, top, wd);
	$.ajax({
        type: "POST",
        url: "/ajx/ajx_product.php",
        data: 'cmd=uh_otherproducer&id='+pid,
        dataType: "text",
        success: function(data){
        	$("#dialog_content").html(data);
        }
    });
}

function add_wishlist(prodid, section, lnk)
{
	var ww = $(window).width(), wh = $(window).height();
	var left = this.offsetLeft, top = this.offsetTop;
	if (navigator.userAgent.toLowerCase().indexOf("msie") != -1){left += (ww-978)/2;}

	var coor = getOffset( lnk );
	left = coor.left;
	top = coor.top;
//	alert(goodservurl+"/addwishgood");
	$.ajax({
        type: "POST",
        url: goodservurl+"/addwishgood",
        data: {"section": section, "goodid":prodid},
//        dataType: "text",
        success: function(data){
 //       	alert(data);
        	$("#dialog_title").html('Желаемые покупки');
        	$("#dialog_content").html(data);
        	showPopupDlg(left, top, 350);
            $("#addwish").html("Все желаемые товары");
            $("#addwish").attr("href", "<c:out value="${pageContext.request.contextPath}"/>"+"/reg");
            $("#addwish").attr("onClick", "");
			$("#addwish").attr("target", "_blank");
        }
    });
}

    function show_big_photo(lnk, pid, i)
    {	
    	var name = "<c:out value = "${good.name}"/>";
        var ww = $(window).width(), wh = $(window).height();
    	var left = this.offsetLeft, top = this.offsetTop;
    	if (navigator.userAgent.toLowerCase().indexOf("msie") != -1){left += (ww-978)/2;}

        var wd = 720;
        var tw = $("body, html").width();
        left = (tw - wd)/2;
        top = 100;
        /*
    	var coor = getOffset( lnk );
    	left = coor.left;
    	top = coor.top;
        */

        $("#dialog_title").html(name);
        $("#dialog_content").html("<br /><br />Идет подгрузка информации...<br /><br />");
        showPopupDlg(left, top, wd);
    	$.ajax({
						type: "POST",
						url: "/ajx/ajx_jq2.php",
						data: 'cmd=uh_photovideo&id='+pid+'&csi=it986qr2arh979bde5unt5nfl2&i='+i,
						dataType: "html",
						success: function(data){
							$("#dialog_content").html(data);
						}
				});
		}
			</script>
		<script type="text/javascript" src="http://www.mobilluck.com.ua/onlinecredit/delta.js"></script>	
	
	
	
	
	<div id="mainpart" class="mainpart">
	
	<jsp:include page="/WEB-INF/views/brands.jsp" />
	<c:if test = "${section == 'all'||empty section}">
<jsp:include page="/WEB-INF/views/goodpart_all.jsp" />
</c:if>



<c:if test = "${section != 'all'}">

        <ul class="tabline" id="tabline" style="position: absolute;">

                     <c:choose><c:when test = "${section ==  'all'}"><li class="active"></c:when><c:otherwise><li class=""></c:otherwise></c:choose>
                <table cellpadding="0" cellspacing="0">
                    <tr>
                        <td class="td_l"></td>
                        <td class="td_c"><c:choose><c:when test = "${section ==  'all'}"><span>Обо всем</span></c:when><c:otherwise><a  href="${pageContext.request.contextPath}/catalog/${good.page.fullurl}/all">Обо всем</a></c:otherwise></c:choose></td>
                        <td class="td_r"></td>
                    </tr>
                </table>
            </li>
                       <c:choose><c:when test = "${section ==  'features'}"><li class="active"></c:when><c:otherwise><li class=""></c:otherwise></c:choose>
                <table cellpadding="0" cellspacing="0">
                    <tr>
                        <td class="td_l"></td>
                        <td class="td_c"><c:choose><c:when test = "${section ==  'features'}"><span>Характеристики</span></c:when><c:otherwise><a  href="${pageContext.request.contextPath}/catalog/${good.page.fullurl}/features">Характеристики</a></c:otherwise></c:choose></td>
                        <td class="td_r"></td>
                    </tr>
                </table>
            </li>
                        <c:choose><c:when test = "${section ==  'media'}"><li class="active"></c:when><c:otherwise><li class=""></c:otherwise></c:choose>
                <table cellpadding="0" cellspacing="0">
                    <tr>
                        <td class="td_l"></td>
                        <td class="td_c"><c:choose><c:when test = "${section ==  'media'}"><span>Фото, видео и 3D</span></c:when><c:otherwise><a  href="${pageContext.request.contextPath}/catalog/${good.page.fullurl}/media">Фото, видео и 3D</a></c:otherwise></c:choose></td>
                        <td class="td_r"></td>
                    </tr>
                </table>
            </li>
                         <c:choose><c:when test = "${section ==  'voices'}"><li class="active"></c:when><c:otherwise><li class=""></c:otherwise></c:choose>
                <table cellpadding="0" cellspacing="0">
                    <tr>
                        <td class="td_l"></td>
                        <td class="td_c"><c:choose><c:when test = "${section ==  'voices'}"><span>Отзывы</span></c:when><c:otherwise><a  href="${pageContext.request.contextPath}/catalog/${good.page.fullurl}/voices">Отзывы</a> <span>${comCount}</span></c:otherwise></c:choose></td>
                        <td class="td_r"></td>
                    </tr>
                </table>
            </li>
                         <c:choose><c:when test = "${section ==  'others'}"><li class="active"></c:when><c:otherwise><li class=""></c:otherwise></c:choose>
                <table cellpadding="0" cellspacing="0">
                    <tr>
                        <td class="td_l"></td>
                        <td class="td_c"><c:choose><c:when test = "${section ==  'others'}"><span>Похожие товары</span></c:when><c:otherwise><a  href="${pageContext.request.contextPath}/catalog/${good.page.fullurl}/others">Похожие товары</a></c:otherwise></c:choose></td>
                        <td class="td_r"></td>
                    </tr>
                </table>
            </li>
            

    </ul>
   			 <jsp:include page="/WEB-INF/views/goodpart_common.jsp" />
            
            <div id = "speccontent">
            <c:if test = "${section ==  'features'}">
    		   <jsp:include page="/WEB-INF/views/goodpart_features.jsp" />
            </c:if>
   
            <c:if test = "${section ==  'media'}">
	
				<jsp:include page="/WEB-INF/views/goodpart_media.jsp" />
			</c:if>
           
            
 			<c:if test = "${section ==  'voices'}">     
					<jsp:include page="/WEB-INF/views/goodpart_voices.jsp" />
 			</c:if>           
  
 			<c:if test = "${section ==  'others'}">           
      		     <jsp:include page="/WEB-INF/views/goodpart_others.jsp" />
          
   			 </c:if>        
        	</div>
            
	
  
  
 </c:if>  
 
 <jsp:include page="/WEB-INF/views/goodpart_common_bottom.jsp" />
 </div>
  
 <div id = "sidecontent">
 <jsp:include page="/WEB-INF/views/goodserviceinfo.jsp" />
         <div style="display:none; width: 256px; float: left; padding-top: 50px;">
         
			<jsp:include page="/WEB-INF/views/deliveryinfo.jsp" />
			<jsp:include page="/WEB-INF/views/subscribe.jsp" />
			<jsp:include page="/WEB-INF/views/ordercall.jsp" />
     
          </div>
  </div>
 
 
        
    <script type="text/javascript">
    $(window).load(function()
    {
                        //alert($("#h1").html());
                $("#h1_1").html($("#dh1").html());
                //alert($("#h1_1").html());
                $("#dh1").html("");
                	    var h1 = $("#mg_code").position();
		var h3 = $("#mg_code").height();
        var h2 = $("#tabline").height();
        $("#tabline").css("top", h1.top+h3+15+"px");
        $("#tabline").css("left", h1.left+"px");
        $("#tabline").css("display", "block");
        $("#mg_code").css("margin-bottom", h2+20+"px");
        });


var cCompareProds = new Array();
var cCompareType = 0;



function makeCmpButton(pid)
{
	//var tdo = $("#comprow"+pid);
    $("#cmprlnk").attr('href', 'http://www.mobilluck.com.ua/compare_all.php');
    $("#cmprlnk").html('Сравнить');
    //$("#cmprlnk").unbind('click');
	//tdo.html('<a href="/compare_all.php"><img src="/img/newbtn/btn_compare.gif" width="91" height="24" alt="Сравнить" /></a>');
}

function makeAddCmpLink(pid)
{
	var tdo = $("#comprow"+pid);

	tdo.html('<a href="#" onclick="return addCompare('+pid+','+cCompareType+')" class="mgtl_add2cmp"><span>Добавить к сравнению</span></a>');
}

function remCompare(pid)
{
	remFromCompareList(pid);
	makeAddCmpLink(pid);

	updateCompareList();
}

function updateCompareList()
{
	//$("#progress"+select_id).show();
    $.ajax({
        type: "POST",
        url: "/ajx/ajx_jq.php",
        data: 'cmd=uh_com_listcomp',
        dataType: "text",
        success: function(data){
        	//$("#progress"+select_id).hide();
            $("#comppanp").html(data);
            $("#comppnum").html( $("#comppanp").children().length );

            if( $("#comppanp").children().length == 0 )
            	$("#compdiv1").css('display','none');
            else
            	$("#compdiv1").css('display','block');
        }
    });
}





    function show_big_photo(lnk, pid, i)
    {
        var ww = $(window).width(), wh = $(window).height();
    	var left = this.offsetLeft, top = this.offsetTop;
    	if (navigator.userAgent.toLowerCase().indexOf("msie") != -1){left += (ww-978)/2;}

        var wd = 720;
        var tw = $("body, html").width();
        left = (tw - wd)/2;
        top = 100;
        /*
    	var coor = getOffset( lnk );
    	left = coor.left;
    	top = coor.top;
        */

        $("#dialog_title").html('Фото и видео Игровая приставка Genius Heeha 100X');
        $("#dialog_content").html("<br /><br />Идет подгрузка информации...<br /><br />");
        showPopupDlg(left, top, wd);
    	$.ajax({
						type: "POST",
						url: "/ajx/ajx_jq2.php",
						data: 'cmd=uh_photovideo&id='+pid+'&csi=n4o0hi0buepsoktemtuc0oclr6&i='+i,
						dataType: "html",
						success: function(data){
							$("#dialog_content").html(data);
						}
				});
		}
			</script>
</div>

</div>
<div class="both"></div></div></div></div>	</div></div></div>

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

<!--
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
-->
		<div id = "bucket"></div>


		<!-- End SiteHeart code -->
		</body>
</html>