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
<title>Регистрация постоянного клиента интернет-магазина </title>

<jsp:include page="/WEB-INF/views/template/head.jsp" />
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
<jsp:include page="/WEB-INF/views/template/header.jsp" />
<div id="wrapper">
<jsp:include page="/WEB-INF/views/template/mainmenu.jsp" />
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
<div id="main" ><style>

</style>
<script>
function  inputSwitchTab( tab )
{
	$( '.inputTab1' ).removeClass( 'active' );
	$( '.inputTab2' ).removeClass( 'active' );

	$( '.inputTab'+tab ).addClass( 'active' );
}
</script>
<c:if test = "${logresult == 'fail'}">
<div id = "loginfail">Вы ввели неверный логин или пароль, зарегистрируйтесь либо повторите попытку!</div>
</c:if>
<div class="inputHeader">Регистрация</div>

<div class="inputBody">
<table cellspacing=0 cellpadding=0>
	<tr>
		<td  class="inputSwitch" align=left>
			<input type=radio name="inputRadLogin" id="inputRadLogin" onchange="inputSwitchTab(1)" ><label for="inputRadLogin">Я зарегистрированный пользователь</label>
			<br />
			<input type=radio name="inputRadLogin" id="inputRadRegister" onchange="inputSwitchTab(2)" checked><label for="inputRadRegister">Я новый покупатель и хочу зарегистрироваться</label>
		</td>
	</tr>
	<tr class="inputTab1"><td>
		<table cellspacing=0 cellpadding=0 class="inputBodyTable">
			<tr>
				<td class="inputLeft" valign=top>
					<table cellspacing=0 cellpadding=0>
						<tr>
							<th>Войти быстро используя</th>
						</tr>
						<tr>
							<td>
								<img src="http://www.mobilluck.com.ua/img/popup/soc_vk.png" onclick="fbAuth('http://oauth.vkontakte.ru/authorize?client_id=2117138&scope=notify&redirect_uri=http://www.mobilluck.com.ua/vk.php&response_type=code&display=popup&scope=notify%2Cfriends%2Cphotos%2Caudio%2Cvideo', 'Вконтакте', 2)"/>
							</td>
						</tr>
						<tr>
							<td>
								<img src="http://www.mobilluck.com.ua/img/popup/soc_fb.png" onclick="fbAuth('https://www.facebook.com/dialog/oauth?client_id=366394396719366&redirect_uri=http://www.mobilluck.com.ua/fb.php&scope=email,user_likes,user_location,user_birthday,user_hometown,user_location&display=popup', 'Facebook', 1)"/>
							</td>
						</tr>
					</table>
				</td>
				<td class="inputRight" valign=top>
				<form id="login-form" method="POST" action="${pageContext.request.contextPath}/j_spring_security_check">
                    <table>
                        <tbody>
                            <tr>
                                
                                <td class="right-side">
                                    <input type="hidden" value="orderlogin" name="action"></input>
                                    <input type="hidden" value="" name="refurlhndl"></input>
                                    <input type="hidden" value="postuser" name="mode"></input>
                                    <div>
                                        <label class="titleSec" for="dlgf_mail">

                                            Электронная почта: 

                                            <span class="necessary">

                                                *

                                            </span>
                                        </label>
                                        <p>
                                            <input id="dlgf_mail" class="dlgftext" type="text" name="j_username"></input>
                                        </p>
                                    </div>
                                    <div>
                                        <label class="titleSec" for="dlgf_mail">

                                            Пароль: 

                                            <span class="necessary">

                                                *

                                            </span>
                                        </label>
                                        <p>
                                            <input id="dlgf_psw" class="dlgftext" type="password" name="j_password"></input>
                                        </p>
                                        <p class="restore_pass">
                                            <a id="restorepassbtn2" class="dlglink" href="#">
                                                <span>

                                                    Напомнить пароль

                                                </span>
                                            </a>
                                        </p>
                                    </div>
     <!--                              <label id="login_button" for="login">  -->  
                                        <div>
                                                   <input value = "Войти" id="login" type="submit" name="dlgf_submit"></input>
                                        </div>
               <!--                     </label>   --> 
                                    <p class="or-registrate">

                                        или 

                                        <a href="${pageContext.request.contextPath}/reg">

                                            зарегистрироваться

                                        </a>
                                    </p>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <p class="license_agreement">

                                        Регистрируясь, вы принимаете условия 

                                        <a rel="nofollow" href="http://www.mobilluck.com.ua/info.php?page=using">

                                            соглашения.

                                        </a>
                                    </p>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
			</tr>
		</table>
	</td></tr>
	<tr class="inputTab2 active"><td colspan=2>
		<form id="clientfrm" name="clientfrm" action="" method="POST" onsubmit="return testinfo(this);">
		<input type="hidden" name="action" value="makereg">
				<table cellspacing=0 cellpadding=0>
			<tr>
				<td class="inputRegLeft" align=right>Имя: <span style="color: red">*</span></td>
				<td class="inputRegRight" align=left>
					<span class="nameErrorList"></span>
					<input type="text" name="clientname" id="clientname" value="" onblur="checkInName()">
				</td>
				<td class="inputRegSpacer">&nbsp;</td>
			</tr>
			<tr>
				<td class="inputRegLeft" align=right>E-mail: <span style="color: red">*</span></td>
				<td class="inputRegRight" align=left>
					<span class="emailErrorList"></span>
					<input type="text" name="clientemail" id="clientemail" value="" onblur="checkLog()"/>
				</td>
				<td class="inputRegSpacer">&nbsp;</td>
			</tr>
			<tr>
				<td class="inputRegLeft" align=right>Пароль: <span style="color: red">*</span></td>
				<td class="inputRegRight" align=left>
					<input type="password" name="clientp1"  id="clientp1"/>
				</td>
				<td class="inputRegSpacer">&nbsp;</td>
			</tr>
	<!--  		<tr>
				<td class="inputRegLeft" align=right valign=middle><img onclick="load_help( this, 0 );" src="http://www.mobilluck.com.ua/img/product/help.png" style=" cursor:pointer;float:right;"/><span style="display:block; float:right; height:18px;line-height:18px;">Номер Дисконтной карты:&nbsp;&nbsp;</span></td>
				<td class="inputRegRight" align=left>
					<span class="discErrorList"></span>
					<input type="text" name="clientdscount" id="clientdscount" value="" onblur="checkDics()"/>
				</td>
				<td class="inputRegSpacer">&nbsp;</td>
			</tr>
	-->
			<tr>
				<td class="inputRegLeft" align=right>Город:</td>
				<td class="inputRegRight" align=left>
					<script src="http://www.mobilluck.com.ua/js/jquery.selectbox.js"></script>
					<link href="http://www.mobilluck.com.ua/css/selectbox.css" rel="stylesheet" />
						<script src="<c:url value="/resources/js/autocomplete.js" />"></script>
					<link rel="stylesheet" type="text/css" href="http://www.mobilluck.com.ua/css/jquery.autocomplete.css">
					<div id="cities">
						<div class="citiess">
							<div class="citiess_inp">
								<input type="text" id="clienttown_f" name="clienttown_f" value="" placeholder="Введите название города" placeholder2="Введите название города">
								<input type="hidden" name="discont_town" id="clienttown" value="">
							</div>
							<b class="trigger" id="clienttownbtn"></b>
						</div>
					</div>
					<script type="text/javascript">
					function hide() {
						if($("#clienttown_another").hasFocus()){
							$(".selectbox .dropdown").css("visibility", "hidden");
						}
					 }
					function shown()  {
						if($(".ac_results").last().css("display")=="none"){
							$(".selectbox .dropdown").css("visibility", "visible");}
						}
					</script>
					<script type="text/javascript">
					var cityid = 0;
					var editinput = false;
					var showcity = true;
					var url = "<c:out value="${pageContext.request.contextPath}"/>";
				//	alert(url+"/citysearch");
					var keyCodeArray = new Array("19",35,"36",37,38,39,40,45,112,113,114,115,116,117,118,119,120,121,122,123);
					$("#clienttown_f").autocomplete(url+"/citysearch",
					{
						matchSubset:0,
						cacheLength:0,
						minChars:0,
						height: 400,
						width: 400,
						resultsClass: "selectbox2",
						focusshow: true,
						selectOnly: false,
						additionalText: "Если в списке нет нужного Вам города, проверьте правильность ввода названия.",
						open: function( event, ui ){},
						onItemSelect: function(item){
					//	$("#clienttown_f").attr('value', name);
					//	$("#clienttown_f").attr('title', name);
						},
				
					});
					$("#clienttownbtn").bind('click', function () {
						
						showcity = true;
						$("#clienttown_f").trigger("focus");
					});
					$("#clienttown_f").live("keypress", function(eventObject){
						showcity = true;
						var t = in_array(eventObject.keyCode, keyCodeArray);
						if(t==false)
						{
							editinput = true;
						}
					});
					$("#clienttown_f").live("blur", function(){
						showcity = true;
						if(($("#clienttown").val()==cityid)&&(editinput))
						{
						//	$("#clienttown_f").val("");
						//	$("#clienttown").val(0);
							//$("#dostavka_f").hide();
						//	change_city();
						}
					});
				
					</script>
				</td>
				<td class="inputRegSpacer">&nbsp;</td>
			</tr>
			<tr>
				<td class="inputRegLeft" align=right>Рассылки:</td>
				<td class="inputRegRight" align=left>
					<input type="checkbox" name="clientmailer" id="clientmailer" style="height: 25px; width: 25px;float:left;" checked="checked" /><label for="clientmailer" style="float: left;display: block;line-height: 25px;height: 25px;margin: 10px 0px;"> - получать информацию об Акциях и Спецпредложениях</label>
				</td>
				<td class="inputRegSpacer">&nbsp;</td>
			</tr>
			<tr>
				<td class="inputRegLeft" align=right>Контрольный вопрос:</td>
				<td class="inputRegRight" align=left>
										<b style="margin-left:10px; line-height: 25px; font-size: 13px;">${rand1} + ${rand2} = </b><input type="text" name="clientanswer" id="clientanswer" value="" style="width:80px;"/>
					<input id="answer" type="hidden" name="answer" value="${rand1+rand2}"/>
				</td>
				<td class="inputRegSpacer">&nbsp;</td>
			</tr>
			
			
			</div>
			<tr>
				<td class="inputRegLeft" align=right></td>
				<td class="inputRegRight" align=left><input class="inputRegSubmit" onclick = "return adduser()" name="orderbut" value="Зарегистрироваться"/></td>
				<td class="inputRegSpacer">&nbsp;</td>
			
				<div id="regresult" style = "padding-top:7px; font-size:14px; border-radius:2px; display:none; height: 20px; background: gray;">
  				</div>
			</tr>
			<tr>
				<td class="inputRegLeft" align=right></td>
				<td class="inputRegRight" align=left>
					Регистрируясь, вы соглашаетесь с <a href="javascript:void();" onclick="$('#reginfo').slideToggle(333);">пользовательским соглашением</a>
				</td>
				<td class="inputRegSpacer">&nbsp;</td>
			</tr>
		</table>
		
		</form>
	</td></tr>
</table>
</div>
<div class="inputFooter" style="margin-top: 10px;">

<p id="reginfo" style="color:#888; text-align:left; display:none;">
При регистрации на сайте www.mobilluck.ua и оформлении заказов, Вы даете согласие предоставить достоверную и точную информацию
о себе и своих контактных данных.
<br />
Администрация сайта с уважением и ответственностью относится к конфиденциальной информации любого лица, ставшего посетителем этого сайта.
Обработка предоставленных пользователями личных данных полностью соответствует действующему законодательству Украины о защите персональных
данных. Принимая это соглашение Вы даете согласие на сбор и использование определенной информации о Вас, а также подтверждаете,
что Вы ознакомлены со своими правами , закрепленными в ст. 8 ЗУ «О защите персональных данных», реализация которых гарантируется
администрацией сайта.
<br />
Личные данные пользователей не предоставляются третьим лицам. Администрация сайта обязуется осуществлять сбор только той персональной
информации, которую Вы предоставляете добровольно в случае, когда информация нужна для предоставления (улучшения) услуг Потребителю.
<br />
Если вы не хотите получать информативные смс по нашим акциям, просто позвоните в центр приема звонков и откажитесь или напишите отказ
от рассылки на почту info@mobilluck.ua. При отказе мы обязуемся исключить ваш адрес из смс рассылки.
<br />
В процессе регистрации на сайте, вы получаете логин и пароль за безопасность, которых вы несете ответственность.
</p>
</div>

<script language="JavaScript">

$("#clientdscount").mask("000999999777");
$("#restorepassbtnmy").on("click", function(){
		var ww = $(window).width(), wh = $(window).height();
		var left = document.getElementById("nlogin").offsetLeft, top = document.getElementById("nlogin").offsetTop;
		if (navigator.userAgent.toLowerCase().indexOf("msie") != -1){left += (ww-978)/2;}
		var wd = 350;
		left += 800;

		$("#dialog_title").html("");
		$("#dialog_content").html("<br /><br />Идет подгрузка информации...<br /><br />");
		$("#dialog_wrapper").css("width", "350px");
		showPopupDlg(left, top, wd);
		$('body,html').animate( {scrollTop: 0}, 500, 'swing');
		$.ajax({
			type: "POST",
			url: "/ajx/ajx_login2.php",
			data: "cmd=uh_restpas",
			dataType: "text",
			success: function(data){
				$("#dialog_content").html(data);
			}
		});
		return false;
	});

var fbwindow = null;
function fbAuth(url, title, type)
{
	fbwindow = openWindow(url, title);
	checkClosed(fbwindow, type);
}

function openWindow(url, title)
{
	return window.open(url, title, 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width=800, height=400, top=300, left=200')
}

function checkClosed(window, type)
{
	if (window && typeof window != 'number')
	{
		fbwindow = window
	}
	if (fbwindow)
	{
		if (fbwindow.closed)
		{
			fbwindow = null;
			if(type==1)
				location.href = "";
			else if(type==2)
			{
				$.ajax({
					type: "POST",
					url: "/ajx/ajx_login.php",
					data: "cmd=uh_vk&uri=",
					dataType: "text",
					success: function(data)
					{
						if(data=="autorizeok")
							location.href = ""
						else
						{
							$("#dialog_content").html(data);
							var ww = $(window).width(), wh = $(window).height();
							var left = document.getElementById("nlogin").offsetLeft, top = document.getElementById("nlogin").offsetTop;
							if (navigator.userAgent.toLowerCase().indexOf("msie") != -1){left += (ww-978)/2;}
							var wd = 350;
							left += 800;
							showPopupDlg(left, top, wd);
							$('body,html').animate( {scrollTop: 0}, 500, 'swing');
						}
					}
				});
			}
		}
		else
		{
			setTimeout(function() { checkClosed(window, type)}, 100);
		}
	}
}


function show_div(i)
{
	//alert(i);
	var name1 = "";
	var name2 = "";
    var txt = "";
	if(i==1)
	{
		name1 = "new";
		name2 = "in";
        txt = "Я зарегистрированый покупатель!";
	}
	else
	{
		name1 = "in";
		name2 = "new";
        txt = "Я новый покупатель, хочу зарегистрироваться!";
	}
	//alert(name1+" "+name2);
	document.getElementById(name1).style.visibility = "hidden";
	document.getElementById(name1).style.display = "none";
	document.getElementById(name2).style.visibility = "visible";
	document.getElementById(name2).style.display = "block";

    $('#mgood_title').html(txt);
}

function nav_xmlhttp ()
{
	var xmlhttp = false;

	/*@cc_on @*/
	/*@if (@_jscript_version >= 5)
	try
	{
		xmlhttp = new ActiveXObject('Msxml2.XMLHTTP');
	}
	catch (e)
	{
		try
		{
			xmlhttp = new ActiveXObject('Microsoft.XMLHTTP');
		}
		catch (e)
		{
			xmlhttp = false;
		}
	}
	@end @*/

	if (!xmlhttp && typeof XMLHttpRequest != 'undefined')
	{
		try
		{
			xmlhttp = new XMLHttpRequest();
		}
		catch (e)
		{
			xmlhttp = false;
		}
	}

	if (!xmlhttp && window.createRequest)
	{
		try
		{
			xmlhttp = window.createRequest();
		}
		catch (e)
		{
			xmlhttp = false;
		}
	}

	return xmlhttp;
}



function proverka (l,i)
{
	var l="";
	//alert('111');
	//alert ('1');
	var wnode=document.getElementById('clientlogin');
 	wnode.innerHTML= "<img src=\"img/load.gif\"  width=\"20\">";
	//alert ('2');
	var xmlhttp = nav_xmlhttp();
	if (xmlhttp)
	{
		//alert ('3');
		var a = document.getElementById('clientemail');
		//alert (a.value);
		//xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=win-1251');
		xmlhttp.open('GET', 'http://v2.mobilluck.ua/navigator/2_.php?a=' + a.value, false);
		//alert ('3');
		xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=win-1251');
		//alert ('3');
		//header("Content-type: text/html; charset=windows-1251");
		xmlhttp.send(null);
		//alert ('3');
		//xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=win-1251');
		if (xmlhttp.status == 200)
		{
			//alert ('4');
			var text = "";
			var rows = xmlhttp.responseText.split(/\r?\n/);
			var cols = rows[0].split(',');
			var pr = true;

			//var re = /^[\w*\s*(*\-\.**)*\w]*$/g;
			var re = /((\w)*(\.)*(\w)*)+@(\w)+\.(\w)+/g;
			//r re = /[\w*@\w*\.\w+/g;

			if(!re.test(a.value))
			{
				pr = false;
				//alert('Ошибка');
			}
			else
			{
				//alert(a.value);
				pr = true;
			}
			if ((cols[0]=='good')&&(pr==true))
			{
				text = "Email введен правильно!";
				wnode.innerHTML = "<a href=\"#\" title=\""+text+"\"><img src=\"img/good.gif\"  width=\"20\" border=\"0\"></a>";
				var eb = document.getElementById('errorbox');
					eb.innerHTML = "";
					eb.style.width = "435px";
					eb.style.visibility = "hidden";
					eb.style.display = "none";
			}
			else
			{
				//alert('0');
				if(i==1)
				{
					//alert('1');
					proverka (l,0);
				}
				else
				{
					if(cols[0]!='good')
					{
						text1 = "Данный Email уже зарегистрирован у нас на сайте. Если Вы не помните свой пароль, воспользуйтесь функцией восстановения пароля.";
						text = "Данный Email уже зарегистрирован у нас на сайте. Если Вы не помните свой пароль, воспользуйтесь <a href=\"javascript:void();\" onClick=\"popupWin = window.open('http://www.mobilluck.com.ua/restore.php', 'contacts', 'menubar=0,directories=0,location=0,toolbar=0,status=0,resizable=0,scrollbars=0,top=100,left=100,right=0,border=0,right=0,width=350,height=200'); popupWin.focus(); returnfalse;\"> функцией восстановения пароля</a>.";
					}
					else
					{
						text1 = "Email введен неправильно!";
						text = "Email введен неправильно!";
					}
					wnode.innerHTML = "<a href=\"#\" title=\""+text1+"\"><img src=\"img/bad.gif\" width=\"20\" border=\"0\"></a>";
					var eb = document.getElementById('errorbox');
					eb.innerHTML = text;
					eb.style.width = "435px";
					eb.style.visibility = "visible";
					eb.style.display = "block";
				}
			}

		}
	}
}

/*
span.nameErrorList,
span.emailErrorList,
span.discErrorList
*/
function checkDics()
{
	var dis = $("#clientdscount").val();

    if(dis=="" || dis=="000______777")
    {
		$("span.discErrorList").html("");
		$("span.discErrorList").hide();
		$("#clientdscount").removeClass('inputErrorClass');
		return true;
    }

    if(((dis.length!=6)&&(dis.length!=12))&&(dis.length>0))
    {
		$("span.discErrorList").html("Вы ввели неправильный номер дисконта");
		$("span.discErrorList").show();
        $("#clientdscount").addClass('inputErrorClass');
        return false;
    }


    var re = /^(\d){6}$/g;
 	if( !re.test(dis) )
 	{
		var re2 = /000([0-9]{6})777/;
		if( !re2.test(dis) )
		{
			$("span.discErrorList").html("Вы ввели неправильный номер дисконта");
			$("span.discErrorList").show();
			$("#clientdscount").addClass('inputErrorClass');
			return false;
		}
		else
		{
			if((dis>'000722304777')||(dis<'000001001777'))
			{
				$("span.discErrorList").html("Вы ввели неправильный номер дисконта");
				$("span.discErrorList").show();
				$("#clientdscount").addClass('inputErrorClass');
				return false;
			}
			else
			{
				$("span.discErrorList").html("");
				$("span.discErrorList").hide();
				$("#clientdscount").removeClass('inputErrorClass');
				return true;
			}
		}
 	}
    else
    {
        if(dis>'722304')
        {
			$("span.discErrorList").html("Вы ввели неправильный номер дисконта");
			$("span.discErrorList").show();
            $("#clientdscount").addClass('inputErrorClass');
            return false;
        }
		else
		{
			$("span.discErrorList").html("");
			$("span.discErrorList").hide();
			$("#clientdscount").removeClass('inputErrorClass');
			return true;
		}
    }
}

function checkInName()
{
	var name = $("#clientname").val();
	if( name == '' )
	{
		$("span.nameErrorList").html("Вы не ввели имя");
		$("span.nameErrorList").show();
		$("#clientname").addClass('inputErrorClass');
		return false;
	}
	$("span.nameErrorList").html("");
	$("span.nameErrorList").hide();
	$("#clientname").removeClass('inputErrorClass');
	return true;
}


function checkLog()
{
	var log = $("#clientemail").val();
	var value=0;

	if( log == "" )
	{
		$("span.emailErrorList").html("Вы не ввели E-mail");
		$("span.emailErrorList").show();
		$("#clientemail").addClass('inputErrorClass');
		return false;
	}

	var re = /((\w)*(\.)*(\-)*(\w)*)+@((\w)*(\-)*(\w)*)+\.(\w)+/g;
 	if( !re.test(log) )
 	{
 		$("span.emailErrorList").html("Вы ввели неправильный E-mail");
		$("span.emailErrorList").show();
		$("#clientemail").addClass('inputErrorClass');
 		return false;
 	}
	
 	
	$.ajax({
        type: "POST",
        url: url+"/emailchecker",
        data: {"log":log},
        async: false,
        dataType: "text",
        success: function(data){
        	//$("#progress"+select_id).hide();
        	        	
        	if( data == "yes" )
        	{
        		$("span.emailErrorList").html("Такой E-mail уже зарегистрирован");
				$("span.emailErrorList").show();
				$("#clientemail").addClass('inputErrorClass');
				value = false;
        	}
        	else
        	{
        		
				$("span.emailErrorList").html("");
				$("span.emailErrorList").hide();
        		$("#clientemail").removeClass('inputErrorClass');
        		
        		value = true;
        	}
        }
    });
	return value;
}

function testinfo()
{
    var name = $("#clientname").val();
    var email = $("#clientemail").val();
    var psw = $("#clientp1").val();
    var dis = $("#clientdscount").val();
    var city = $("#discont_town").val();

    if(name=="")
    {
        alert('Вы не ввели имя');
        return false;
    }
    if(psw=="")
    {
        alert('Вы не ввели пароль');
        return false;
    }

    if(email=="")
    {
        alert('Вы не ввели E-mail');
        return false;
    }
    /*
    else
    {
        var tmp = checkLog();
        if(tmp==false)
        {
            alert('Проверьте правильность ввода E-mail');
            return false;
        }
    }
     */
    if(dis!="")
    {
        if(city==0)
        {
            alert('Вы не ввели город');
            return false;
        }
        /*
        var tmp2 = checkDics();
        if(tmp2==false)
        {
            alert('Проверьте правильность ввода Дисконтной карты');
            return false;
        }
        */
    }

    return true;
}

function load_help(lnk, left_correct)
{
	var ww = $(window).width(), wh = $(window).height();
	var left = this.offsetLeft, top = this.offsetTop;
	if (navigator.userAgent.toLowerCase().indexOf("msie") != -1){left += (ww-978)/2;}

	var coor = getOffset( lnk );
	left = coor.left;
	top = coor.top;

	left = left - left_correct;

	var wd = 450;
	$("#dialog_title").html("Номер дисконтной карты");
	$("#dialog_content").html("Внимание! Введен новый формат номера дисконтной карты 000XXXXXX777. Если номер Вашей дисконтной карты состоит из 6 цифр, например, 522000, то ввести нужно 000522000777<br/><br/>");
	showPopupDlg(left, top, wd);

	return false;
}

$(document).ready(function()
{
			});
</script>

<div class="both"></div></div>	</div></div></div>

	<jsp:include page="/WEB-INF/views/template/footer.jsp"/>


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