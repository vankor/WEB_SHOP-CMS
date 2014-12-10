function addtobuck(goodurl, goodid, bucksize){
				//	alert(goodurl+"/bucket");
				//		alert(goodid);
	 
						$.ajax({
						       type: "POST",
						  	   url: goodurl+"/bucket",
						       data: {"goodid": goodid},
						       cache: false,                                
						       success: function(responce){
						//    	   alert(responce);
						   			document.getElementById('bucket').style.display='block';
						    	   $("#bucket").html("");
						    	   $("#bucket").html(responce);
						    	   goodsinbucket=++bucksize;
						    	   $("#bucketval").html("");
						    	   $("#bucketval").html("("+goodsinbucket+")");
						    	  
						   	//	     $(".goodMenuNode").css("background", "blue" );
						      //     $(el).css("background", "gray" );
						     	//	location.href=url+'/#tab='+$(el).attr("id"); 
						       }
						     });
							
	}

function showbuck(){
	//	alert(baseurl+"/showbucket");
	//		alert(goodid);

			$.ajax({
			       type: "POST",
			  	   url: baseurl+"/showbucket",
			       cache: false,                                
			       success: function(responce){
			//    	   alert(responce);
			   			document.getElementById('bucket').style.display='block';
			    	   $("#bucket").html("");
			    	   $("#bucket").html(responce);
			    	   			    	  
			   	//	     $(".goodMenuNode").css("background", "blue" );
			      //     $(el).css("background", "gray" );
			     	//	location.href=url+'/#tab='+$(el).attr("id"); 
			       }
			     });
				
}



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


function showmenu(sid, gid)
{
	$("#d_"+sid).children().css('display', 'none');
	$("#ul_"+sid).children().removeClass('limenu_active');
	$("#ul_"+sid).children().removeClass('lright_active');

	$("#t_"+sid+"_"+gid).css('display', 'block');
	$("#li_"+sid+"_"+gid).addClass('limenu_active');

	if(sid>5)	$("#li_"+sid+"_"+gid).addClass('lright_active');
}

function deletefrombuck(goodurl, goodid, bucksize){
//	alert(goodurl+"/deletefrombuck");
//	alert(goodid);
	$.ajax({
	       type: "POST",
	  	   url: goodurl+"/deletefrombuck",
	       data: {"goodid": goodid},
	       cache: false,                                
	       success: function(responce){
	//    	   alert(responce);
	//   			document.getElementById('bucket').style.display='block';
	    	   $("#bucket").html("");
	    	   $("#bucket").html(responce);
	    	   goodsinbucket=--bucksize;
	    	   $("#bucketval").html("");
	    	   $("#bucketval").html("("+goodsinbucket+")");
	   	//	     $(".goodMenuNode").css("background", "blue" );
	      //     $(el).css("background", "gray" );
	     	//	location.href=url+'/#tab='+$(el).attr("id"); 
	       }
	     });
		
}

function changecount(goodurl, goodid,bucksize){
//	alert(goodurl+"/deletefrombuck");
//	alert(goodid);
	var count = $("#goodcount_"+goodid).val();
//	alert(count);
	$.ajax({
	       type: "POST",
	  	   url: goodurl+"/changecount",
	       data: {"goodid": goodid, "count": count},
	       cache: false,                                
	       success: function(responce){
	//    	   alert(responce);
	//   			document.getElementById('bucket').style.display='block';
	    	   $("#bucket").html("");
	    	   $("#bucket").html(responce);
	    	   goodsinbucket=--bucksize;
	    	   $("#bucketval").html("");
	    	   $("#bucketval").html("("+goodsinbucket+")");
	   	//	     $(".goodMenuNode").css("background", "blue" );
	      //     $(el).css("background", "gray" );
	     	//	location.href=url+'/#tab='+$(el).attr("id"); 
	       }
	     });
		
}


function addCompare(pid, tid, baseurl){
//	alert(pid + "     "+tid+"     "+baseurl);
	
    if($("#cmprlnk"+pid).html()!='Сравнить')
    {
    	url = baseurl+'/good/'+pid+'/all';
    	
        addtocompare(pid, tid, url);
        $("#cmprlnk"+pid).attr('href', baseurl+'/'+tid+'/comparegoods');
        $("#cmprlnk"+pid).html('Сравнить');
        makeCmpButton(pid, tid, baseurl);
//        updateCompareList();
    	return false;
    }
    else
    {
 //       location.hre = '${pageContext.request.contextPath}/'+tid+'/comparegoods';
    }
}


function addtocompare(goodid, catid, url){ 
//	alert("#answertext"+parentcom_id);
//	var text = $("#answertext"+parentcom_id).val();
//    alert(url+"/tocompare");
//	alert(goodid +","+catid);
//	alert(url);
	$.ajax({
	       type: "POST",
	  	   url: url+"/tocompare",
	       data: {"goodid":goodid, "catid":catid},
	       dataType : "json",
	       cache: false,                                
	       success: function(responce){
//	    	   alert(responce);
	   //			document.getElementById('commsection').style.display='block';
	    	   $("#comparedsets").html("");
	    	   $("#comparedsets").html(responce);
	   	//	     $(".goodMenuNode").css("background", "blue" );
	      //     $(el).css("background", "gray" );
	     	//	location.href=url+'/#tab='+$(el).attr("id"); 
	       }
	     });
		
	}

$(document).ready(function(){

$("#search").keyup(function(){
		     var search = $("#search").val();
		     $.ajax({
		       type: "POST",
		       url: "livesearch",
		       data: {"search": search},
		       cache: false,                                
		       success: function(responce){
		    	   $('#resSearch').html("");
		    	   var htmldata = "";
		        	/* what  i have to put here to updte my table <table id="table_grid"> */
			        $.each(responce,function(key, good) {
			        	
			        	
			            htmldata+="<div id = '1' style = 'cursor: pointer;'  onclick = 'setsearchval(this);' ><a href ='good/"+good.id+"'>" + good.name + "</a></div>";         
			        });
		          $("#resSearch").html(htmldata);
		       }
		     });
		     return false;
});



$("#multidelete").bind("click", function(){
	alert("multidelete");
});

$("#multichange").bind("click", function(){
	alert("multichange");
});




});


//Cart functions
function ui_cart_view(url,view_pan_id,popup_mode,col_id)
{
//	alert(url+"/popup");
//	alert(view_pan_id);
	$.ajax({
        type: "POST",
        url: url+"/popup",
        data: {"section":view_pan_id, "colid":col_id},
        dataType: "text",
        success: function(data){
  //      	alert(data);
        	document.getElementById('bucket').style.display='block';
        	if( data != "" )
        	{
				$("#bucket").html(data);
				//document.getElementById(view_pan_id).innerHTML = data;
        	}

			if(popup_mode)
			{
            	ui_cart_set_handlers(sesid,view_pan_id);
   			}
        }
    });
}

function setTownId(id, tt){
	$("#clienttown").attr("value", id);
	$("#clienttown_f").attr("value", tt);

}

function chsGood(id,idfld, urlparam, section){

	var	url;

	if(section == 'choisecategory'){
	url = urlparam+"/choiseCat";
	}
	
	if(section == 'choisegood'){
	url = urlparam+"/choiseGood";
	}
	
	if(idfld == 'goodcopy'){
		location.href = urlparam+"/admin/admingood/"+id+"/copyGood";
	}
	
	if(idfld == 'catfull'){
		
		location.href = urlparam+"/admin/admincategory/"+id+"/addGoodtoCat";
	}
	
	if(idfld == 'catproducer'){
		
		location.href = urlparam+"/admin/admincategory/"+id+"/addProducer";
	}
//	alert("fbdb");
	$.ajax({
        type: "POST",
        url: url,
        dataType: 'json',
        data: {"id":id},
        success: function(good){
  //      	alert(good);
        	if(idfld.indexOf('filter') > -1){
        		
        		$("#popupbg").hide();
        		$("#dialog_wrapper").hide();
        		idfld = idfld.split('filter')[1];
        		$("#"+idfld).val(good.name);
        		return;
        	}
        	if(idfld.indexOf('propvalue') > -1){
        		
        		$("#popupbg").hide();
        		$("#dialog_wrapper").hide();
        		$("#"+idfld).val(good.name);
        		$("#entityid").val(good.id);
        		return;
        	}
        	else{
        	
        	$("#popupbg").hide();
    		$("#dialog_wrapper").hide();
    		
    		$("#"+idfld+"_id").val(good.id);
        	$("#"+idfld+"_name").val(good.name);
        	}
    		},      
    	    error : function(xhr, status, error){
                alert("Error!" + xhr.status);
    	    }
    });
	
	
	
}


function fillValue(id, url){
		alert(url+"/choisePropValue");
		alert(id);
		$.ajax({
	        type: "POST",
	        url: url+"/choisePropValue",
	        dataType: 'json',
	        data: {"id":id},
	        success: function(val){
	        	alert(val);
	        	return val;
	        	$("#popupbg").hide();
	    		$("#dialog_wrapper").hide();
	    		response = "<input type = 'hidden' value = '"+val.id+"' id='newval"+i+".value' name='segments["+lsegm+"].properties["+lprop+"].vals["+lval+"].value' type='text'/><p>"+val.value+"<img style = 'cursor:pointer;' src = '"+url+"/resources/images/ico-add2buydel.gif' onclick = 'javascript:removeProperty("+id+")'/></p><br>";
	    		alert(response);
	    		div.innerHTML = response;
	    		
	    		
	    		},      
	    	    error : function(xhr, status, error){
	                alert("Error!" + xhr.status);
	    	    }
	        
	    });
		
//		div.innerHTML = "<input type = 'hidden' value = '"+val+"' id='newval"+i+".value' name='segments["+lsegm+"].properties["+lprop+"].vals["+lval+"].value' type='text'/><p>"+val+"<img style = 'cursor:pointer;' src = '"+url+"/resources/images/ico-add2buydel.gif' onclick = 'javascript:removeProperty("+id+")'/></p><br>";
//		alert("<input type = 'hidden' value = '"+val+"' id='newval"+i+".value' name='segments["+lsegm+"].properties["+lprop+"].vals["+lval+"].value' type='text'/><p>"+val+"<img style = 'cursor:pointer;' src = '"+url+"/resources/images/ico-add2buydel.gif' onclick = 'javascript:removeProperty("+id+")'/></p><br>");
		
			document.getElementById('nvals_'+lsegm+'_'+lprop).appendChild(div);
		
		
			counter1++;
		lval++;
	
	
	
	
	
	
	
	
}

function login(lnk, lft)
{
	var ww = $(window).width(), wh = $(window).height();
	var left = this.offsetLeft, top = this.offsetTop;
	if (navigator.userAgent.toLowerCase().indexOf("msie") != -1){left += (ww-978)/2;}
	
	var coor = getOffset( lnk );
	top = 20;//coor.top;

	$("#dialog_title").html("");
	$("#dialog_content").html("<br /><br />Идет подгрузка информации...<br /><br />");

	showPopupDlg(left, top, 560, true);
//	popup_position($("#dialog_wrapper"));
//	alert(baseurl +"/logindialog");
	$.ajax({
	       type: "POST",
	  	   url: baseurl +"/logindialog",
	       data: {"section":"login"},
	       cache: false,                                
	       success: function(responce){
	
	    	   $("#dialog_content").html(responce);
	    	   popup_position( $("#dialog_wrapper"));

	       }
	     });
	


	return false;
}


function showPopupDlg(l, t, dwset, center){
	var dwidth = $("#dialog_wrapper").width(), dheight = $("#dialog_wrapper").height();
	if( dwset )
	{
		 $("#dialog_wrapper").css('width',dwset+'px');
		 dwidth = dwset;
		 dheight = $("#dialog_wrapper").height();
	}
	if(center){popup_position($("#dialog_wrapper"));}
	else{
	var ww = $(window).width(), wh = $(window).height();
	//$(".dinner_t, .dinner_b").css("width", (dwidth-16)+"px");
	//$(".dinner_l, .dinner_r").css("height", (dheight-16)+"px");
	
	
	if (l+10+dwidth <= ww){
			$("#dialog_wrapper").css({
				'right': 'auto',
				'left': (l+10)+"px",
				'top': (t+20)+"px",
				'bottom':'auto'
				
			});		
	}
	else{
		
			$("#dialog_wrapper").css({
				'left': 'auto',
				'right': "10px",
				'top': (t+20)+"px",
				'bottom':'auto'
			});
		
	}
	if(t+20+dheight>$(document).height()){
		$("#dialog_wrapper").css({
				'top': "auto",
				'bottom':'20px'
			});
	}
	/*���� ������� ���� ������ ������� �������(�� ������� ���������)*/
	var workwidth=1024;
	if($("body").hasClass("p1280")) workwidth=1280;
	if(l+10+dwidth>workwidth+(ww-workwidth)/2) {
		l=(ww-workwidth)/2 + 10;
		$("#dialog_wrapper").css({
				'left': 'auto',
				'right': l,
			});
	}

	$("#popupbg").css({
		'width': ($(window).width())+"px"
	});
	}
	$("#popupbg").show();
	$("#dialog_wrapper").show();
	var dwidth = $("#dialog_wrapper").width(), dheight = $("#dialog_wrapper").height();
	//$(".dinner_t, .dinner_b").css("width", (dwidth-16)+"px");
	//$(".dinner_l, .dinner_r").css("height", (dheight-16)+"px");
}


function uh_get_object(obj_id)
{
	var obj_var = null;

	if( document.all )
		obj_var = document.all(obj_id);
    else if (document.getElementById)
		obj_var = document.getElementById(obj_id);
	else if(document.layers)
		obj_var = document.layers[obj_id];

    if( obj_var )
    	return obj_var;

    alert('Error retrieve object handle by id: ' + obj_id);
}


function fillSearchListNS(str_val, obj_id, inp_id)
{
   	var ctrl_droplist = uh_get_object( obj_id );
  	var ctrl_text = uh_get_object( inp_id );

   	if( search_is_running )
   		return;

    search_is_running = true;
    alert(ctrl_droplist+"   "+ctrl_text+"    "+str_val);
    if( str_val.length > 1 )
    {
   
    	$.ajax({
 	       type: "POST",
 	  	   url: baseurl +"/livesearch",
 	       data: {"searchstring":str_val},
 	       cache: false,                                
 	       success: function(responce){
 	
 	    	   $("#searchres").html(responce);
 	    	   $("#searchres").show();

 	       }
 	     });
    	
    	
    	/*    if( AJAX != null )
        {
            var process_fn = function(res)
            {
                eval( 'var responce_data = ' + res );
                var tmp = "";
                var arr_slov = new Array();
                var tmpstr = "";
                var tstr = "";

                try
                {
					//alert(responce_data.words.length);
                    for(i=0; i<responce_data.words.length; i++)
                    {
                        if( i>0 )
                            tmp += ", \r";

                        tmp += "'"+ responce_data.words[i].word +"'";
                        tsrt = '<table width="100%" style="cursor: pointer;" >';
                        tsrt += '<tr>';
                        tsrt += '<td rowspan="2" width="70" align="center">';
                        tsrt += '<img src="' +responce_data.words[i].img+ '" width="' + responce_data.words[i].width + '">';
                        tsrt += '</td>';
                        tsrt += '<td><a href="javascript:location.href=\'' + responce_data.words[i].url + '\';">' + responce_data.words[i].word + '</a></td></tr>';
                        if(responce_data.words[i].cost!="0 грн")
                           tsrt += '<tr><td><font color="orange" size="3"><b>' +responce_data.words[i].cost+ '</b></font></td></tr></table>';
                        else
                           tsrt += '<tr><td><font color="black" size="2">нет в наличии</fon></td></tr></table>';
                        tsrt += '<input type="hidden" name="cbb'+i+'" id="cbb'+i+'" value="' +responce_data.words[i].url+ '">';
                        arr_slov[i] = tsrt;
                    }
                    if( tmp == "" )
                    {
						//alert("1");
                        jQuery.combobox.instances[0].setSelectOptions([tmp]);
                    }
                    else
                    {
						//alert(arr_slov);
                        arr_slov[responce_data.words.length] = '<div style="background-color: #4a4a4a; width: 100%;"><div style="float: right; padding: 2px 10px;" onclick="$(\'.combobox_selector\').hide(); return: false;"><font style="color: #fff;">Закрыть</font></div><div style="padding: 2px 20px;"><a href="http://www.mobilluck.com.ua/search.php?sw='+str_val+'" style="color: #fff;">Все результаты поиска</a></div></div>';
                        jQuery.combobox.instances[0].setSelectOptions(arr_slov);
                        jQuery.combobox.instances[0].selector.buildSelectOptionList('');
                        jQuery.combobox.instances[0].selector.show();
                    }
                }
                catch(e1)
                {
                    //alert("Error!");
                    $('.combobox_selector').hide();
  					//hideTooltip(obj_id);
   				}
   				search_is_running = false;
            };

            var post_req_str = "rcom=uh_com_srchlist3&swstart=" + unescape(escapeEx(str_val));
            AJAX.SendRequest('POST', '', process_fn, encode64(post_req_str));
       }*/
    }
    else
    {
        $('.combobox_selector').hide();
        //hideTooltip(obj_id);
        search_is_running = false;
    }
}



function popup_position(el){
	$(el).css("position", "fixed");
    var my_popup = $(el), // наш попап
            my_popup_w = my_popup.width(), // ширина попапа
            my_popup_h = my_popup.height(), // высота попапа
            popup_half_w = my_popup_w/2, // половина ширины попапа
            popup_half_h = my_popup_h/2, // половина высоты попапа
            win_w = $(window).width(), // ширина окна
            win_h = $(window).height(); // высота окна

    if ( win_w > my_popup_w ) { // если ширина окна больше ширины попапа
            my_popup.css({'margin-left':-popup_half_w, 'left': '50%'});
    }
    if ( win_w < my_popup_w ) { // если ширина окна меньше ширины попапа                  
            my_popup.css({'margin-left': 5, 'left': '0'});
    }
    if ( win_h > my_popup_h ) { // если высота окна больше ширины попапа
            my_popup.css({'margin-top': -popup_half_h, 'top':'50%'});
    }
    if ( win_h < my_popup_h ) { // если высота окна меньше ширины попапа
            my_popup.css({'margin-top': 5, 'top': '0'});
    }
}




		


		