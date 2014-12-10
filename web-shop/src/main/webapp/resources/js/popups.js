function popupAssHandler(btn_ind)
{
	if( btn_ind == 1 )
	{
		$("#restorepassbtn").bind("click", function(){
			$("#dialog_title").html(dialogs[3][0]);
			$("#dialog_content").html(dialogs[3][1])

			popupAssHandler(2);
		});
	}
	else
	{
		$("#entersitebtn").bind("click", function(){
			/*
			$("#dialog_title").html(dialogs[2][0]);
			$("#dialog_content").html(dialogs[2][1])
			*/
			load_login();
			popupAssHandler(1);
		});
	}
}

$(document).ready(function(){
	//$(".ths_online, .ths_callback, .ths_login").bind("click", function(){
	$(".ths_callback, .ths_login, .ths_callback1, .ths_psw_restore, .registration").bind("click", function(){
		var o = $(this);
		var ww = $(window).width(), wh = $(window).height();
		var type = o.attr("class");
		var left = document.getElementById(type).offsetLeft, top = document.getElementById(type).offsetTop;
		if (navigator.userAgent.toLowerCase().indexOf("msie") != -1){left += (ww-978)/2;}
		var wd = 300;
		$("#dialog_title").css("font-size", "24px");
		switch(type){
			case 'ths_online':
				$("#dialog_title").html(dialogs[0][0]);
				$("#dialog_content").html(dialogs[0][1])
				break;
			case 'ths_callback1':
			case 'ths_callback':
				$("#dialog_title").html(dialogs[1][0]);
				$("#dialog_content").html(dialogs[1][1]);
				$("#dialog_title").css("font-size", "18px");
				wd = 325;
				break;
			case 'ths_login':
				/*
				$("#dialog_title").html(dialogs[2][0]);
				$("#dialog_content").html(dialogs[2][1]);
				*/
				load_login();
                left += 800;
				popupAssHandler(1);
				break;
            case 'ths_psw_restore':
				$("#dialog_title").html(dialogs[3][0]);
				$("#dialog_content").html(dialogs[3][1]);
                if($.browser.safari){
                    bodyelem = $("body")
                } else{
                    if($.browser.opera){
                        bodyelem = $("html")
                    } else{
                        bodyelem = $("html,body")
                    }
                }
                bodyelem.animate({scrollTop: 0});
				popupAssHandler(2);
				break;
            case 'registration':
				$("#dialog_title").html(dialogs[2][0]);
				$("#dialog_content").html(dialogs[2][1]);
                left += 800;
				popupAssHandler(1);
				break;
		}
		showPopupDlg(left, top, wd);
		return false;
	});
	
	$("#popup_close").bind("click", function(){
		alert("RWEEWRG");
		$("#bucket").css("display", "none");
		$("#bucket").html("");

	});
	
    $("#ths_loginnew").bind("click", function(){
		var o = $(this);
		var ww = $(window).width(), wh = $(window).height();
		//var type = o.attr("class");
		var left = $("#ths_loginnew").position().left, top = $("#ths_loginnew").position().top;
		if (navigator.userAgent.toLowerCase().indexOf("msie") != -1){left += (ww-978)/2;}
		/*
		$("#dialog_title").html(dialogs[2][0]);
		$("#dialog_content").html(dialogs[2][1]);
		*/
		load_login();
		showPopupDlg(left, top, 280);
        popupAssHandler(1);
		return false;
	});
	$("#dialog_close").bind("click", function(){
		$("#popupbg").hide();
		$("#dialog_wrapper").hide();
		return false;
	});

    $("#dialog_close2").bind("click", function(){
		$("#popupbg").hide();
		$("#dialog_wrapper2").hide();
		return false;
	});
    $("#restorepassbtn").bind("click", function(){
		$("#dialog_title").html(dialogs[3][0]);
		$("#dialog_content").html(dialogs[3][1]);
        popupAssHandler(2);
		return false;
	});

	 $("#ths_callback").bind("click", function(){
		var o = $(this);
		var ww = $(window).width(), wh = $(window).height();
		var left = $("#ths_callback").position().left, top = $("#ths_callback").position().top;
		if (navigator.userAgent.toLowerCase().indexOf("msie") != -1){left += (ww-978)/2;}
		$("#dialog_title").html(dialogs[1][0]);
		$("#dialog_title").css("font-size", "18px");
		$("#dialog_content").html(dialogs[1][1]);
		showPopupDlg(left, top, 325);
		return false;
	});
	 
		$(".close").click(function(){
			
			$(this).parent('div').fadeOut('200');
		});

});



function req_rephone(frm)
{
	var phone = frm.dlgf_phone.value;
	var iname = frm.dlgf_name.value;
	var topic = frm.dlgf_qtopic.value;

	$("#dlgf_phone_e").css('display', 'none');
	$("#dlgf_name_e").css('display', 'none');

	var err = false;

	var re_tel = /^0-[0-9]{2}-[0-9]{7}$/;
	if( !re_tel.test(phone) )
	{
		var re_tel = /^\+38 \(0[0-9]{2}\) [0-9]{3}-[0-9]{2}-[0-9]{2}$/;
		if( !re_tel.test(phone) )
		{
			$("#dlgf_phone_e").css('display', 'block');
			err = true;
		}
		//$("#dlgf_phone_e").css('display', 'block');
		//err = true;
	}

	if( iname == "" )
	{
		$("#dlgf_name_e").css('display', 'block');
		err = true;
	}

	if( !err )
	{
		$.ajax({
	        type: "POST",
	        url: "/ajx/ajx_jq.php",
	        data: 'cmd=uh_com_rephone&uphone='+phone+'&uname='+iname+'&utopic='+topic,
	        dataType: "text",
	        success: function(data){
	        	//$("#progress"+select_id).hide();
	        	$("#dialog_content").html(data);
	        }
	    });
	}

 	return false;
}

function req_restorepass(frm)
{
	var resmail = frm.resmail.value;
	var act = frm.action.value;
	var mod = frm.mode.value;
	var err = false;

	if( !err )
	{
		$.ajax({
	        type: "POST",
	        url: "/ajx/ajx_jq.php",
	        data: 'cmd=uh_com_restorepass&action='+act+'&mode='+mod+'&email='+resmail,
	        dataType: "text",
	        success: function(data){
	        	//$("#progress"+select_id).hide();
	        	$("#dialog_content").html(data);
	        }
	    });
	}

	return false
}

function send_sabscribe_prod(frm)
{
	var subsmail = frm.clientmail.value;
	//var act = frm.action.value;
	var prodid = frm.prodid.value;
	var err = false;

	if( prodid == "" )
	{
		err = true;
		$("#dialog_content").html("<p>Произошла ошибка при отправке<br />запроса. Попробуйте выполнить подписку<br />позже.</p>");
	}

	var re_mail = /^[\w\.\_\-]+@[\w\.]+[\w]{2,6}$/;
	if( !re_mail.test(subsmail) )
	{
		$("#dlgf_name_e").css('display', 'block');
		err = true;
	}

	if( !err )
	{
		$.ajax({
	        type: "POST",
	        url: "/ajx/ajx_jq.php",
	        data: 'cmd=uh_com_subscribeprod&prodid='+prodid+'&email='+subsmail,
	        dataType: "text",
	        success: function(data){
	        	//$("#progress"+select_id).hide();
	        	$("#dialog_content").html(data);
				$("#addsled").html("��� �����");
				$("#addsled").attr("href", "http://www.mobilluck.com.ua/user_room.php?mode=lookforprice");
				$("#addsled").attr("target", "_blank");
				$("#addsled").attr("onClick", "");
	        }
	    });
	}

	return false;
}

function send_availnotify_prod(frm)
{
	var subsmail = frm.clientmail.value;
	//var act = frm.action.value;
	var prodid = frm.prodid.value;
	var err = false;

	if( prodid == "" )
	{
		err = true;
		$("#dialog_content").html("<p>��������� ������ ��� ��������<br />�������. ���������� �����������<br />�����.</p>");
	}

	var re_mail = /^[\w\.\_\-]+@[\w\.]+[\w]{2,6}$/;
	if( !re_mail.test(subsmail) )
	{
		$("#dlgf_name_e").css('display', 'block');
		err = true;
	}

	if( !err )
	{
		$.ajax({
	        type: "POST",
	        url: "/ajx/ajx_jq.php",
	        data: 'cmd=uh_com_availnotifyprod&prodid='+prodid+'&email='+subsmail,
	        dataType: "text",
	        success: function(data){
	        	//$("#progress"+select_id).hide();
	        	$("#dialog_content").html(data);
	        }
	    });
	}

	return false
}

function send_proddescrerr(frm)
{
	var comment = frm.comment.value;
	//var act = frm.action.value;
	var prodid = frm.prodid.value;
	var err = false;

	if( prodid == "" )
	{
		err = true;
		$("#dialog_content").html("<p>��������� ������ ��� ��������<br />�������. ���������� ��������� ��������<br />�����.</p>");
	}

	if( comment == "" )
	{
		$("#dlgf_com_e").css('display', 'block');
		err = true;
	}

	if( !err )
	{
		$.ajax({
	        type: "POST",
	        url: "/ajx/ajx_jq.php",
	        data: 'cmd=uh_com_informperr&prodid='+prodid+'&comment='+comment,
	        dataType: "text",
	        success: function(data){
	        	//$("#progress"+select_id).hide();
	        	$("#dialog_content").html(data);
	        }
	    });
	}

	return false
}



function calc_delivery(frm)
{
	var region = frm.clientcity.value;
	var prodid = frm.prodid.value;

	err = false;

	$("#dlgf_w_e").css('display', 'none');
	$("#dlgf_h_e").css('display', 'none');
	$("#dlgf_l_e").css('display', 'none');
	$("#dlgf_weight_e").css('display', 'none');

	var weight = frm.delivweight.value;
	var w = frm.delivw.value;
	var h = frm.delivh.value;
	var l = frm.delivl.value;

	if( prodid == "" )
	{
		err = true;
		$("#dialog_content").html("<p>��������� ������ ��� ��������<br />�������. ���������� ��������� ��������<br />�����.</p>");
	}

	var re_num = /^[0-9]+$/;
	if( !re_num.test(w) )
	{
		$("#dlgf_w_e").css('display', 'block');
		err = true;
	}
	if( !re_num.test(h) )
	{
		$("#dlgf_h_e").css('display', 'block');
		err = true;
	}
	if( !re_num.test(l) )
	{
		$("#dlgf_l_e").css('display', 'block');
		err = true;
	}

	if( !re_num.test(weight) )
	{
		$("#dlgf_weight_e").css('display', 'block');
		err = true;
	}

	if( !err )
	{
		$.ajax({
	        type: "POST",
	        url: "/ajx/ajx_jq.php",
	        data: 'cmd=uh_com_delivcalc&pid='+prodid+'&reg='+region+'&weight='+weight+'&w='+w+'&h='+h+'&l='+l,
	        dataType: "text",
	        success: function(data){
	        	//$("#progress"+select_id).hide();
	        	$("#dialog_content").html(data);
	        }
	    });
	}

	return false
}

function calc_delivery2(frm)
{
	var region = frm.clientcity.value;
	var prodid = frm.prodid.value;

	err = false;

	$("#dlgf_w_e").css('display', 'none');
	$("#dlgf_h_e").css('display', 'none');
	$("#dlgf_l_e").css('display', 'none');
	$("#dlgf_weight_e").css('display', 'none');
    $("#dlgf_cost_e").css('display', 'none');
    $("#dlgf_city_e").css('display', 'none');


	var weight = frm.delivweight.value;
	var w = frm.delivw.value;
	var h = frm.delivh.value;
	var l = frm.delivl.value;
    var cost = frm.delivcost.value;

	if( prodid == "" )
	{
		err = true;
		$("#dialog_content").html("<p>��������� ������ ��� ��������<br />�������. ���������� ��������� ��������<br />�����.</p>");
	}


    if(region==0)
    {
        $("#dlgf_city_e").css('display', 'block');
		err = true;
    }

	var re_num = /^[0-9]+$/;
	if( !re_num.test(w) )
	{
		$("#dlgf_w_e").css('display', 'block');
		err = true;
	}
	if( !re_num.test(h) )
	{
		$("#dlgf_h_e").css('display', 'block');
		err = true;
	}
	if( !re_num.test(l) )
	{
		$("#dlgf_l_e").css('display', 'block');
		err = true;
	}

	if( !re_num.test(weight) )
	{
		$("#dlgf_weight_e").css('display', 'block');
		err = true;
	}

    if( !re_num.test(cost ) )
	{
		$("#dlgf_cost_e").css('display', 'block');
		err = true;
	}

	if( !err )
	{
		$.ajax({
	        type: "POST",
	        url: "/ajx/ajx_jq.php",
	        data: 'cmd=uh_com_delivcalc&pid='+prodid+'&reg='+region+'&weight='+weight+'&w='+w+'&h='+h+'&l='+l,
	        dataType: "text",
	        success: function(data){
	        	//$("#progress"+select_id).hide();
	        	$("#result").html(data);
	        }
	    });
	}

	return false
}

function podpiska2(frm)
{
    var subsmail = frm.clientmail.value;
    var name = frm.clientname.value;
    var err = false;

    if( name == "" )
    {
    	$("#dlgf_name_n").css('display', 'block');
    	err = true;
    }
   	var re_mail = /^[\w\.\_\-]+@[\w\.]+[\w]{2,6}$/;
   	if( !re_mail.test(subsmail) )
   	{
   		$("#dlgf_name_e").css('display', 'block');
   		err = true;
   	}

   	if( !err )
   	{
      	$.ajax({
            type: "POST",
            url: "/ajx/ajx_jq.php",
            data: 'cmd=uh_com_podpiska&clientmail='+subsmail+'&clientname='+name,
            dataType: "text",
            success: function(data){
              	$("#dialog_content").html(data);
            }
        });
    }
    return false;
}

function load_login()
{
	/*
	var ww = $(window).width(), wh = $(window).height();
	var left = this.offsetLeft, top = this.offsetTop;
	if (navigator.userAgent.toLowerCase().indexOf("msie") != -1){left += (ww-978)/2;}

	var coor = getOffset( lnk );
	left = coor.left-100;
	top = coor.top;
	*/
	var myurl = window.location;
	var wd = 280;
	$("#dialog_title").html('���� �� ����');
	$("#dialog_content").html("<br /><br />���� ��������� ����������...<br /><br />");
	//showPopupDlg(left, top, wd);
	//alert(url);
	$.ajax({
		type: "POST",
		url: "/ajx/ajx_login.php",
		data: 'cmd=uh_main&uri='+myurl,
		dataType: "text",
		success: function(data)
		{
			$("#dialog_content").html(data);
		}
	});
	return false;
}

var dialogs = [
['������-�����������','<p>test</p>'],
['�������� �������� ������','<p>������� ���� ������� � ���������������<br/> �������� ���������� ��� � �������<br/> �������� ��� call-������.</p>'+
	'<form action="/" class="dlgform" onsubmit="return req_rephone(this)">'+
	'<div class="dlgline">'+
		'<label for="dlgf_phone">����� ��������: <span class="required">*</span> <span id="dlgf_phone_e" class="error" style="display:none">������ �����������</span></label>'+
		'<input type="text" class="dlgftext" name="dlgf_phone" id="dlgf_phone" /><p class="dlghint">� ������� 0-XX-YYYYYYY</p>'+
	'</div>'+
	'<div class="dlgline"><label for="dlgf_name">���� ���: <span class="required">*</span> <span id="dlgf_name_e" class="error" style="display:none">��� �� �����</span></label>'+
		'<input type="text" class="dlgftext" name="dlgf_name" id="dlgf_name" /></div>'+
	'<div class="dlgline"><label for="dlgf_qtopic">���� �������:</label><input type="text" class="dlgftext" name="dlgf_qtopic" id="dlgf_qtopic" />'+
	'<p class="dlghint">��������, ����� ��������</p></div>'+
	'<div class="dlgline"><input type="submit" class="dlgfsubmit" name="dlgf_submit" value="" /></div>'+
	'</form>'],
['���� �� ����','<form action="/cart.php" class="dlgform" method="POST">' +
	'<input type="hidden" name="mode" value="postuser" />'+
	'<input type="hidden" name="action" value="orderlogin" />'+
	'<div class="dlgline"><label for="dlgf_mail">����������� �����: <span class="required">*</span></label><input type="text" class="dlgftext" name="clientlog" id="dlgf_mail" /></div>'+
	'<div class="dlgline"><label for="dlgf_mail">������: <span class="required">*</span></label><input type="password" class="dlgftext" name="clientpass" id="dlgf_psw" /></div>'+
	'<p><a class="dlglink" id="restorepassbtn" href="#"><span>��������� ������</span></a><br/><br/></p>'+
	'<div class="dlgline"><table class="dlgtreg"><tr><td><input type="submit" class="dlgflogin" name="dlgf_submit" value="" /></td><td><p>��� <a class="dlglink fw_bold" href="/input.php?a=makereg">������������������</a></p></td></tr></table></div></form>'+
    '�������������, �� ������������<br />� <a href="http://www.mobilluck.com.ua/info.php?page=using" rel="nofollow">���������������� �����������</a>.'],
['������������ ������','<form action="/cart.php" class="dlgform" method="POST" onsubmit="req_restorepass(this); return false">' +
	'<input type="hidden" name="action" value="zakaz" />' +
	'<input type="hidden" name="mode" value="go" />�� ������ ��� ������ �� �����������<br /> �����, ����� ����� �� ������� �����<br /> � ��������-�������.<br /><br />' +
	'<div class="dlgline"><label for="dlgf_mail">����������� �����: <span class="required">*</span></label><input type="text" class="dlgftext" name="resmail" id="dlgf_mail" /></div>'+
	'<p><a class="dlglink" id="entersitebtn" href="#"><span>����� �� ����</span></a><br/><br/></p>'+
	'<div class="dlgline"><table class="dlgtreg"><tr><td><input type="submit" class="dlgfsend" name="dlgf_submit" value="" /></td><td><p>��� <a class="dlglink fw_bold" href="/input.php?a=makereg">������������������</a></p></td></tr></table></div></form>'],
['������� �� �������','<p>������� ���� E-mail ����� � ����������� ��<br />��������� ������� �������� �� ������.<br />�� ������ �������� ���������� � ���,<br />��� ����� ��������� � ������ ���<br />����� ��� ����������.</p>' +
	'<form action="#" class="dlgform" method="POST" onsubmit="return send_sabscribe_prod(this);">' +
	'<input type="hidden" name="mode" value="" />'+
	'<input type="hidden" id="frmprodid2" name="prodid" value="" />'+
	'<input type="hidden" name="action" value="subscribeprod" />'+
	'<div class="dlgline"><label for="dlgf_mail">����������� �����: <span class="required">*</span> <span id="dlgf_name_e" class="error" style="display:none">E-mail ������ �����������</span></label>' +
		'<input type="text" class="dlgftext" name="clientmail" id="dlgf_mail" /></div>'+
	'<div class="dlgline"><table class="dlgtreg"><tr><td><input type="submit" class="dlgfsend" name="dlgf_submit" value="" /></td></tr></table></div></form>'],
['���������� ��������� ��������','<p>��������� ���������� ������.</p>' +
	'<form action="#" class="dlgform" method="POST" onsubmit="calc_delivery(this); return false">' +
	'<input type="hidden" name="mode" value="" />'+
	'<input type="hidden" id="frmprodid1" name="prodid" value="" />'+
	'<input type="hidden" name="action" value="calcdeliv" />'+
	'<div class="dlgline"><label for="dlgf_city">�����: <span class="required">*</span> <span id="dlgf_city_e" class="error" style="display:none">����� �� ������</span></label>' +
		'<select class="dlgftext" name="clientcity" id="dlgf_city"></select></div>'+
	'<div class="dlgline"><label for="dlgf_weight">��� (��): <span class="required">*</span> <span id="dlgf_weight_e" class="error" style="display:none">��� ������ �������</span></label>' +
		'<input type="text" class="dlgftext" name="delivweight" id="dlgf_weight" /></div>'+
	'<div class="dlgline"><label for="dlgf_w">������ (��): <span class="required">*</span> <span id="dlgf_w_e" class="error" style="display:none">������ ������� �������</span></label>' +
		'<input type="text" class="dlgftext" name="delivw" id="dlgf_w" /></div>'+
	'<div class="dlgline"><label for="dlgf_h">������ (��): <span class="required">*</span> <span id="dlgf_h_e" class="error" style="display:none">������ ������� �������</span></label>' +
		'<input type="text" class="dlgftext" name="delivh" id="dlgf_h" /></div>'+
	'<div class="dlgline"><label for="dlgf_l">����� (��): <span class="required">*</span> <span id="dlgf_l_e" class="error" style="display:none">����� ������� �������</span></label>' +
		'<input type="text" class="dlgftext" name="delivl" id="dlgf_l" /></div>'+
	'<div class="dlgline"><table class="dlgtreg"><tr><td><input type="submit" class="dlgfsend" name="dlgf_submit" value="" /></td></tr></table></div></form>'],
['������������� �� ������','<p>���� �� ����� ������ � ��������, <br />�� ������� ������, ��� ������ <br />����������� � �������� ������.</p>' +
	'<form action="#" class="dlgform" method="POST" onsubmit="return send_proddescrerr(this)">' +
	'<input type="hidden" name="mode" value="" />'+
	'<input type="hidden" id="frmprodid2" name="prodid" value="" />'+
	'<input type="hidden" name="action" value="informperr" />'+
	'<div class="dlgline"><label for="dlgf_com">������ �� ������: <span class="required">*</span> <span id="dlgf_com_e" class="error" style="display:none">�� �� ������� �����������</span></label>' +
		'<textarea class="dlgftext" name="comment" id="dlgf_com" /></div>'+
	'<div class="dlgline"><table class="dlgtreg"><tr><td><input type="submit" class="dlgfsend" name="dlgf_submit" value="" /></td></tr></table></div></form>'],
['�������� ��� � �������','<p>������� ���� E-mail ����� �<br />� ������ ��������� ������ � ������� ��<br /> �������� ��������������� ������ <br />�� ����.</p>' +
	'<form action="#" class="dlgform" method="POST" onsubmit="return send_availnotify_prod(this); return false;">' +
	'<input type="hidden" name="mode" value="" />'+
	'<input type="hidden" id="frmprodid2" name="prodid" value="" />'+
	'<input type="hidden" name="action" value="subscribeprod" />'+
	'<div class="dlgline"><label for="dlgf_mail">����������� �����: <span class="required">*</span> <span id="dlgf_name_e" class="error" style="display:none">E-mail ������ �����������</span></label>' +
		'<input type="text" class="dlgftext" name="clientmail" id="dlgf_mail" /></div>'+
	'<div class="dlgline"><table class="dlgtreg"><tr><td><input type="submit" class="dlgfsend" name="dlgf_submit" value="" /></td></tr></table></div></form>'],
['����������� �� ��������','<p>������� ���� ���������� ������<br />� �� ����������� ���������� ��������������<br />��� � ����� ����� ������ � �����������!<br /></p>' +
	'<form action="#" class="dlgform" method="POST" onsubmit="return podpiska2(this); return false;">' +
	'<input type="hidden" name="mode" value="" />'+
	'<input type="hidden" name="action" value="subscribeprod" />'+
	'<div class="dlgline"><label for="dlgf_mail">����������� �����: <span class="required">*</span> <span id="dlgf_name_e" class="error" style="display:none">E-mail ������ �����������</span></label>' +
		'<input type="text" class="dlgftext" name="clientmail" id="dlgf_mail" /></div>'+
    '<div class="dlgline"><label for="dlgf_name">���: <span class="required">*</span> <span id="dlgf_name_n" class="error" style="display:none">������� ���</span></label>' +
		'<input type="text" class="dlgftext" name="clientname" id="dlgf_name" /></div>'+
	'<div class="dlgline"><table class="dlgtreg"><tr><td><input type="submit" class="dlgfsend" name="dlgf_submit" value="" /></td></tr></table></div></form>'],
['����������� �� ��������','<p>������� ���� ���������� ������<br />� �� ����������� ���������� ��������������<br />��� � ����� ����� ������ � �����������!<br /></p>' +
	'<form action="#" class="dlgform" method="POST" onsubmit="return podpiska2(this); return false;">' +
	'<input type="hidden" name="mode" value="" />'+
	'<input type="hidden" name="action" value="subscribeprod" />'+
	'<div class="dlgline"><label for="dlgf_mail">����������� �����: <span class="required">*</span> <span id="dlgf_name_e" class="error" style="display:none">E-mail ������ �����������</span></label>' +
		'<input type="text" class="dlgftext" name="clientmail" id="dlgf_mail" /></div>'+
    '<div class="dlgline"><label for="dlgf_name">���: <span class="required">*</span> <span id="dlgf_name_n" class="error" style="display:none">������� ���</span></label>' +
		'<input type="text" class="dlgftext" name="clientname" id="dlgf_name" /></div>'+
	'<div class="dlgline"><table class="dlgtreg"><tr><td><input type="submit" class="submitbtn" name="dlgf_submit" value=" ��������� " /></td></tr></table></div></form>']
];


// 21.06.2013
function recall(lnk, lft)
{
	var ww = $(window).width(), wh = $(window).height();
	var left = this.offsetLeft, top = this.offsetTop;
	if (navigator.userAgent.toLowerCase().indexOf("msie") != -1){left += (ww-978)/2;}

	var coor = getOffset( lnk );
	left = coor.left + lft;
	top = coor.top;

	$("#dialog_title").html("");
	$("#dialog_content").html("<br /><br />���� ��������� ����������...<br /><br />");

	showPopupDlg(left, top, 490);

	$.ajax({
		type: "POST",
		url: "/ajx/ajx_pop.php",
		data: 'cmd=uh_call',
		dataType: "text",
		success: function(data){
			$("#dialog_content").html(data);
		}
	});

	return false;
}

/*function login(lnk, lft)
{
	var ww = $(window).width(), wh = $(window).height();
	var left = this.offsetLeft, top = this.offsetTop;
	if (navigator.userAgent.toLowerCase().indexOf("msie") != -1){left += (ww-978)/2;}

	var coor = getOffset( lnk );
	left = coor.left + lft;
	top = 20;//coor.top;

	$("#dialog_title").html("");
	$("#dialog_content").html("<br /><br />Загрузка контента...<br /><br />");

	showPopupDlg(left, top, 560);

	$.ajax({
		type: "POST",
		url: "/ajx/ajx_login2.php",
		data: 'cmd=uh_main',
		dataType: "text",
		success: function(data){
			$("#dialog_content").html(data);
		}
	});

	return false;
}*/


function req_feedback(frm)
{
	var email = frm.dlgf_mail.value;
	var phone = frm.dlgf_phone.value;
	var name = frm.dlgf_name.value;
	var topic = frm.dlgf_qtopic.value;
	var order = frm.dlgf_zakaz.value;
	var messg = frm.dlgf_mess.value;
	if(frm.qtopic) topic=frm.qtopic.value;
	$("#dlgf_phone_e").css('display', 'none');
	$("#dlgf_name_e").css('display', 'none');
	$("#dlgf_mail_e").css('display', 'none');
	$("#dlgf_mess_e").css('display', 'none');
	$("#dlgf_numz_e").css('display', 'none');

	var err = false;

	var re_tel = /^\+38 \(0[0-9]{2}\) [0-9]{3}-[0-9]{2}-[0-9]{2}$/;
	if( !re_tel.test(phone))
	{
		$("#dlgf_phone_e").css('display', 'block');
		err = true;
	}

	/*var re_tel = /^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$/;
	if( !re_tel.test(email) )
	{
		$("#dlgf_mail_e").css('display', 'block');
		err = true;
	}*/

	if(name=="")
	{
		$("#dlgf_name_e").css('display', 'block');
		err = true;
	}

	if(messg=="")
	{
		$("#dlgf_mess_e").css('display', 'block');
		err = true;
	}
	if( topic==0 )
	{
		$("#dlgf_topic_e").css('display', 'block');
		err = true;
	}
	if(topic==5||topic==11)
	{
		if(order=="")
		{
			$("#dlgf_numz_e").css('display', 'block');
			err = true;
		}
	}
	if( !err )
	{	$("#dialog_content").html("���� �������� �������...");
		$.ajax({
	        type: "POST",
	        url: "/ajx/ajx_pop.php",
	        data: 'cmd=uh_com_addfb&phone='+phone+'&name='+name+'&topic='+topic+'&email='+email+'&messg='+messg+'&order='+order,
	        dataType: "text",
	        success: function(data){
	        	$("#dialog_content").html(data+"<div style='display:none'>"+topic+"</div>");
	        }
	    });
	}

 	return false;
}
function getTheCity()
{
	$("#dialog_title").html("");
	$("#dialog_content").html(data);
	/*$.ajax({
	        type: "POST",
	        url: "/ajx/ajx_geo.php",
	        data: 'cmd=uh_get_the_city',
	        dataType: "text",
	        success: function(data){
	        	$("#dialog_content").html(data);
	        }
	    });*/
	showPopupDlg(left, top, 560);
}



function load_goodchoise(lnk,url, idfld, section)
{
//	alert(idfld +lnk);
	section = section.toLowerCase();
	var ww = $(window).width(), wh = $(window).height();
	var left = this.offsetLeft, top = this.offsetTop;
	var coor;
	if (navigator.userAgent.toLowerCase().indexOf("msie") != -1){left += (ww-978)/2;}
	
	if(!idfld == "catfull"){
	coor = getOffset(lnk);
	left = coor.left;
	top = coor.top;
	}
	else{
		
		left = 500;
		top = 200;
	}
	
	if(idfld == "filter"){
		idfld = idfld+$(lnk).attr("id");
		left = $(lnk).offset().left;
		top = $(lnk).offset().top+20;
	}
	
//	alert(left+ "   "+top);
/*	var wd = 450;
	$("#dialog_title").html("<p>Выбрать товар/категорию</p>");
	$("#dialog_content").html("<br /><br />Идет подгрузка информации...<br /><br />");*/
	
	

 /*   showPopupDlg(left, top, wd);*/
	
	$("#dialog_title").html("<p>Выбрать товар/категорию</p>");
	$("#dialog_content").html("<br /><br />Идет подгрузка информации...<br /><br />");
//    alert(idfld+"  "+section+"  "+url);
	$.ajax({
		type: "POST",
        url: url,
        data: {"idfld":idfld, "section":section},
        dataType: "text",
        success: function(data){
        	
     //   	alert(data);
        	//$("#progress"+select_id).hide();
        	$("#popupcontent").html(data);
        	
			$("#popup").fadeIn('200');
			/* 	$("#dialog_content").html(data);*/
        	
    //    	var mask='+38 (099) 999-99-99';
     //   	$("#ophone").mask(mask);
        },      
	    error : function(xhr, status, error){
            alert("Error!" + xhr.status);
	    }
    });

    return false;
}


function load_propvalchoise(lnk,url, section, propid, lval, lprop, lsegm){
	var ww = $(window).width(), wh = $(window).height();
	var left = this.offsetLeft, top = this.offsetTop;
	if (navigator.userAgent.toLowerCase().indexOf("msie") != -1){left += (ww-978)/2;}

	var coor = getOffset( lnk );
	left = coor.left;
	top = coor.top;

	var wd = 450;
	$("#dialog_title").html("<p>Выбрать доступное значение либо создайте новое</p>");
	$("#dialog_content").html("<br /><br />Идет подгрузка информации...<br /><br />");

    showPopupDlg(left, top, wd);
    
//   alert(url +"  "+ section +"  "+ propid);
    
    $.ajax({
		type: "POST",
        url: url,
        data: {"propid":propid, "section":section, "lval":lval, "lprop":lprop, "lsegm":lsegm},
        dataType: "text",
        success: function(data){
   //     	alert(data);
        	//$("#progress"+select_id).hide();
        	$("#dialog_content").html(data);
    //    	var mask='+38 (099) 999-99-99';
     //   	$("#ophone").mask(mask);
        }
    });

    return false;
    
}