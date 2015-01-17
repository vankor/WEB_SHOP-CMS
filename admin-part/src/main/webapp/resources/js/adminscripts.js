var pushed = false;
var select = true;
var hasselected = false;
var tt = 0;



function setVal(id,idfld, urlparam, section){
	var	url;
//	alert("fbdb");
//	alert(section);
//	alert(adminclassname);
	if(section == 'choisecategory'){
	url = urlparam+"/choiseCat";
	}
	
	if(section == 'otherentitychoise'){
		url = urlparam+"/choiseEntity";
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
//	alert(url);
	$.ajax({
        type: "POST",
        url: url,
        dataType: 'json',
        data: {"id":id},
        success: function(good){
  //     	alert(good.name);
       	$("#popup1").hide();
       	
//		alert('ee');
		$("#"+idfld).val(good.name);
		$("#"+idfld).parent('span').parent('div').find('#entityid').val(good.id);
//		alert(adminclassname);
		return;
        	if(idfld.contains('filter')){
        		$("#popupbg").hide();
        		$("#dialog_wrapper").hide();
        		idfld = idfld.split('filter')[1];
        		$("#"+idfld).val(good.name);
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


function chsEntity(id,idfld, urlparam, section, classname, text){
	var	url;
//	alert("fbdb");
//	alert(section);
//	alert(adminclassname);

	
//	if(section == 'otherentitychoise'){
		url = urlparam+"/admin/choiseEntity";
//	}
	

		
	if(idfld == 'catfull'){
		
		location.href = urlparam+"/admin/admincategory/"+id+"/addGoodtoCat";
	}
//	alert(url+"  "+classname);
/*	alert("ewg");
	alert("Номер "+statnum);*/
	$.ajax({
        type: "POST",
        url: url,
        dataType: 'json',
        data: {"id":id, "classname":classname},
        success: function(good){
  //     	alert(good.name);
       	$("#popup1").hide();
       	if(idfld == 'producer' || idfld == 'parentpage' || idfld == 'pagegroup' || idfld == 'slider' || idfld == 'newstype'){
       		$("#"+idfld+"_id").val(good.id);
       		$("#"+idfld+"_text").val(text);
     //  		alert("#"+idfld+"_id");
       		$("#popup").hide();
       		return;
       	}
       	
       	if(idfld == 'exchangeguarantie' || idfld == 'prodguarantie'){
       		$("#"+idfld+"_id").val(good.id);
       		$("#"+idfld+"_text").html(text);
       		$("#del"+idfld).show();
     //  		alert("#"+idfld+"_id");
       		return;
       	}
       	
       	if(idfld == 'guarantie'){
       		var cid = "'"+"guar"+guarnum+"'";
       		$("#guaranties").append('<div id = "guar'+guarnum+'"><li>'+
       				'<div id = "col2">' +
       				'<label><b>Гарантия на ' + 
       				text +
       				'</b></label>'+
       				'<input type = "hidden" value = "'+good.id+'" name="goodguaranties['+guarnum+'].pk.guarantie.id" />'+
       				'<label style = "margin-left:20px;">Цена гарантии: </label>'+
       				'<input style = "margin-left:5px; min-width:40px !important;width:40px;" type = "text" name="goodguaranties['+guarnum+'].price"/>' +
       				'<label style = "margin-left:20px;">Бесплатная: </label>'+
       				'<input style = "margin-left:5px;" type = "checkbox" name="goodguaranties['+guarnum+'].iscostless"/>' +
       				'</div>' +
       				'<div id = "col3">' +
       				'<img style = "cursor:pointer;"  src = "'+urlparam+'/resources/images/ico-add2buydel.gif"  onclick = "javascript:removeGuarantie('+cid+')"/>' +
       				'</div>'+
       				'</li>');
       		
       		guarnum++;
       		return;

       	}
       	if(idfld == 'status'){
  //  	alert(statnum);
    	var path = urlparam+"/resources/images"+good.thumb;
    	var cid = "'"+"status_"+statnum+"'";
		$("#statuses").append('<div id = "status_'+statnum+'" class = "propval" style = "background: transparent;">'+
				'<input type = "hidden" value = "'+good.id+'" name = "statuses['+statnum+'].id"></input>'+
				'<img style = "cursor:pointer;"  src = "'+path+'"/>'+
				'<img style = "cursor:pointer;"  src = "'+urlparam+'/resources/images/ico-add2buydel.gif"  onclick = "javascript:removeStatus('+cid+')"/>'+
				'</div> ');
		statnum++;
		return;

       	}
		$("#"+idfld).val(text);
		$("#"+idfld).parent('span').parent('div').find('#entityid').val(good.id);

		return;

    		},      
    	    error : function(xhr, status, error){
                alert("Error!" + xhr.status);
    	    }
    });
	
	
	
}



function load_entitychoise(url, idfld, classname, num)
{
//	alert("eee");
//	alert(guarnum);
/*	if(classname != "java.util.Date" && !classname.contains("Model.")){
		
	}
	
	if(classname == "java.util.Date"){
		elem.attachDatepicker({
			yearRange: '2014:2015',
			firstDay: 1,
			minDate:  new Date(2012, 1 - 1, 1),
			defaultDate: 0,
			dateFormat:'yy-mm-dd',
			closeText:"<span id='dialog_close' href='#'></span>"
		});
	}*/
	
	if(classname.split(".")[0] == "Model"){
//		alert("#propvalue"+num);
//		alert(classname+"     "+url+"/admin/getentitychoise");	
		if(num>0){
			alert("ee");
		var coor =  $("#propval"+num).find("#propvalue"+num).offset();
/*		if(coor==null){
		coor =  $("#choiseentity").offset();
		}*/

//		alert(coor);
		var left = coor.left;
		var top = coor.top;
//		alert(left+"   "+top);
		var wd = 400;
		var tempadminclassname = adminclassname;
		}
//		showPopupDlg(left, top, wd);
/*		$("#dialog_title").html("<p>Выбрать товар/категорию</p>");*/
		$("#popup1").html("<br/><br />Идет подгрузка информации...<br /><br />");
		
//		alert(url+"   "+ idfld+"   "+ classname+"   "+ num);
		$.ajax({
			type: "POST",
	        url: url+"/admin/getentitychoise",
	        data: {"classname":classname, "idfld":idfld},
	        dataType: "text",
	        success: function(data){
	 //       	alert(data);
	        	$("#popup1").fadeIn('200');
	        	$("#popup1").html(data);
				
				
//	        	$("#dialog_content").html(data);
				adminclassname = tempadminclassname;
//				alert(adminclassname);
//				alert(guarnum);
	        },      
		    error : function(xhr, status, error){
	            alert("Error!" + xhr.status);
		    }
	    });
	}
	

}



function showPopupDlg(l, t, dwset){
	var dwidth = $("#popup1").width(), dheight = $("#popup1").height();
	if( dwset )
	{
		 $("#popup1").css('width',dwset+'px');
		 dwidth = dwset;
		 dheight = $("#popup1").height();
	}

	var ww = $(window).width(), wh = $(window).height();
	//$(".dinner_t, .dinner_b").css("width", (dwidth-16)+"px");
	//$(".dinner_l, .dinner_r").css("height", (dheight-16)+"px");
	
	
	if (l+10+dwidth <= ww){
			$("#popup1").css({
				'right': 'auto',
				'left': (l+10)+"px",
				'top': (t+20)+"px",
				'bottom':'auto'
				
			});		
	}
	else{
		
			$("#popup1").css({
				'left': 'auto',
				'right': "10px",
				'top': (t+20)+"px",
				'bottom':'auto'
			});
		
	}

	//$(".dinner_t, .dinner_b").css("width", (dwidth-16)+"px");
	//$(".dinner_l, .dinner_r").css("height", (dheight-16)+"px");
}


function showinput(num, propname){
//	alert("ewgwegewg");
	var b = $("#objid").val();
//	alert(b);
	var value = '';
	if(b!=null){
//		alert(url+"/admin/getpropertyvalue       "+b+"     "+propname+"    "+adminclassname);
//		alert(adminclassname);
		$.ajax({
	        type: "POST",
	        url: url+"/admin/getpropertystringvalue",
	        dataType: 'json',
	        data: {"adminclassname":adminclassname, "propname":propname, "id":b},
	      
	        success: function(data){
	    //    	alert(data);
	        	value = data;
	        	var type = $("#propval"+num).find("#propvalue"+num).attr('type');
	
	        	if(type == "Boolean"){
	              		$("#propval"+num).find("select option").each(function(){
	   	        			if ($(this).text() == value)
	        			    $(this).attr("selected","selected");
	        		});
	        	}
	        	$("#propval"+num).find("#propvalspan").show();
				$("#propval"+num).find("#propvalue"+num).attr('value',value);
				
		//		alert($("#propval"+num).find("#propvalue").attr('type'));
				
			},      
		    error : function(xhr, status, error){
	            
	            if(xhr.status==200){
	    //        	alert(xhr.responseText);
	      			value = xhr.responseText;
	      			
		        	$("#propval"+num).find("#propvalspan").show();
					$("#propval"+num).find("#propvalue"+num).attr('value',value);	
					
		//			alert($("#propval"+num).find("#propvalue").attr('type'));
					
	            }
	            else{
	            	alert("Error!" + xhr.status);
	            }
	        
	        }
	        
	    });
		
			
	}
		
		$("#propval"+num).find("#propvalue").attr('type','text');
		

}


function getOffset(elem) {
    if (elem.getBoundingClientRect) {
        // "правильный" вариант
        return getOffsetRect(elem)
    } else {
        // пусть работает хоть как-то
        return getOffsetSum(elem)
    }
}

function multidelete(url, classname, pgnum){
	var ids = [];
	
//	alert(url+"   "+classname);
	$(".myclass").each(function(index) {
		ids[index] = $( this ).find("td:eq(1)").html();
//		alert("g");
	});
	var bean = {};
	bean["classname"] = adminclassname;
	bean["ids"] = ids;
	bean["pgnum"] = pgnum;
	alert(ids);
	alert(JSON.stringify(bean));
	$.ajax({
		type: 'POST',
//	    dataType : "json",
		url: url+"/admin/multidelete",
	    cache: false,  
	    headers : {
	        'Accept' : 'application/json',
	        'Content-Type' : 'application/json'
	    },

        data: JSON.stringify(bean),
        success: function(data){
     //   	alert(good);

        	$("#list").html(data);
    		},      
    	    error : function(xhr, status, error){
                alert("Error!" + xhr.status);
    	    }
    });

}

function formmultichange(url){
	
//		alert(url+"/popup");
//		alert(view_pan_id);
	
	var obj = {};
	var mas = [];
	var idmas = [];
	var i = 0;
	tt++;
	var ttt = 0;
	if(tt<=2){
	formmultichange(url);
	$("#table tr:first th").each(function(index) {
		
	if($( this ).attr('id')!="selector" && $( this ).attr('id')!="functions"){
	kk = {};
	kk["propname"] =	$( this ).attr('id');
	kk["colname"] =	$( this ).html();
	kk["classname"] =	classname;
	kk["adminclassname"] =	adminclassname;
	$(".myclass").each(function(index) {
		if(index==0){
		kk["objid"] = $( this ).find("td:eq(1)").html();}
		else{kk["objid"] =null;}
	
	});
	
	mas[i]=kk;
	i++;
	}
	
	});
	
	
//	alert(JSON.stringify(mas));
		$.ajax({
			type: 'POST',
//		    dataType : "json",
			url: url+"/admin/formmultichange",
		    cache: false,  
		    headers : {
		        'Accept' : 'application/json',
		        'Content-Type' : 'application/json'
		    },

	        data: JSON.stringify(mas),
	        success: function(data){
	  //      	alert(data);
	    //    	document.getElementById('popup').style.display='block';
	        	if( data != "" )
	        	{
					$("#popup").html(data);
					$("#popup").fadeIn('200');
					//document.getElementById(view_pan_id).innerHTML = data;
	        	}

		
	        },      
    	    error : function(xhr, status, error){
             //   alert("Error!" + xhr.status);
    	    }
	    });
	}
}

function multichange(){
	alert("ewgewg");
}

function popup_position(el){
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

$(document).ready(function(){
	
	
	$("#propvals input").click(function(){
		/*		$("#propvalue").each(function(index) {
					$(this).val('Введите значение');

				});*/
				$(this).val('');
			});
		
		$("#multisetsubmit").click(function(){
		//	alert(adminclassname);
			var kk = {};
			var obj = {};
			var mas = [];
			var ids = [];
			var i = 0;
//			alert($("#propvals").html());
			
			$("#propvals").find("div").each(function(index) {
	//		alert($( this ).find('span').css('display'));
			if($( this ).find('span').css('display')!='none'){
			kk = {};
			kk["propname"] =	$( this ).find("#propname").val();
	//		alert($( this ).find("#entityid").val());
			if($( this ).find("#entityid").val()!=null && $( this ).find("#entityid").val()!=""){
				kk["propvalue"] =	$( this ).find("#entityid").val();
			}
			else{
			kk["propvalue"] =	$( this ).find(".propvalue").val();
			}
//			alert(kk["propvalue"]);
//			alert(JSON.stringify(kk));
			mas[i]=kk;
			i++;
			}
			});
//			alert(JSON.stringify(mas));

			$(".myclass").each(function(index) {
				ids[index] = $( this ).find("td:eq(1)").html();
//				alert("g");
			});
			
			obj["propvals"]=mas;
			obj["classname"]=adminclassname;
			obj["ids"]=ids;
			obj["pgnum"]=parseInt($("#pgnum").val());
//			alert(JSON.stringify(obj));
			$.ajax({
				type: 'POST',
//			    dataType : "json",
				url: url+"/admin/multichange",
			    cache: false,  
			    headers : {
			        'Accept' : 'application/json',
			        'Content-Type' : 'application/json'
			    },

		        data: JSON.stringify(obj),
		        success: function(data){
		        	$("#popupbg").hide();
	        		$("#dialog_wrapper").hide();
		        		$("#popup").detach();
						$("#list").html(data);
		        },      
	    	    error : function(xhr, status, error){
	                alert("Error!" + xhr.status);
	    	    }
		    });
		});	
		
		
		$("#saveroworder").on("click", function(e){
			var i = 0;
			var col = [];
			
			$("th").not("#selector, #functions").each(function(){
								
				col[i] = $(this).attr("id");
				i++;		
				
			
			
			});
			
/*			alert(JSON.stringify(col));
		alert(adminclassname);
		alert(url+"/admin/savecolumnorder");*/
			$.ajax({
				type: "POST",
				url: url+"/admin/savecolumnorder",
				data: {"cols":JSON.stringify(col), "adminclassname":adminclassname},
				dataType: "text",
				success: function(data){
					if(data == 1){
						$("#popup").html("Порядок столбцов сохранен!");
						popup_position($("#popup"));
						$("#popup").fadeIn('200');
						setTimeout('$("#popup").fadeOut("200")', 3000);

					}
				
					
				},      
			    error : function(xhr, status, error){
		            alert("Error!" + xhr.status);
		        }
			});
			
		});
		
		$("#saveformat").on("click", function(e){
			var formatname = $("#formatname").val();
			var type = $(this).attr("sect");
			var format_id = $("#currentformat").find("input").attr("fid");
	/*		alert(formatname);
			alert(url+"/admin/savefilter");
			alert(adminclassname);
			alert(type);*/
			alert(format_id);
			if(format_id==null)format_id = -1;
			alert(format_id);
			/*		alert(adminclassname);
		alert(url+"/admin/savecolumnorder");*/
			$.ajax({
				type: "POST",
				url: url+"/admin/savefilter",
				data: {"formatname":formatname, "adminclassname":adminclassname, "format_id":format_id, "type":type},
				dataType: "text",
				success: function(data){
				//	alert(data);
					if(data == 1){
						$("#popup").html("Формат "+formatname+"  сохранен!");
						popup_position($("#popup"));
						$("#popup").fadeIn('200');
						setTimeout('$("#popup").fadeOut("200")', 2000);

					}
					if(data == 0){
		/*				alert("без фильтра");*/
					}
				
					
				},      
			    error : function(xhr, status, error){
		            alert("Error!" + xhr.status);
		        }
			});
			
		});
		
		
		$("#saveformatwindow").on("click", function(e){
	
	//				alert(adminclassname);
					/*alert(url+"/admin/savecolumnorder");*/
			$.ajax({
				type: "POST",
				url: url+"/admin/saveformatwindow",
				data: {"adminclassname":adminclassname},
				dataType: "text",
				success: function(data){
						clname = adminclassname;
						
						$("#popup").html(data);
						
						popup_position($("#popup"));
						$("#popup").fadeIn('200');
			
						adminclassname = clname;


				},      
			    error : function(xhr, status, error){
		            alert("Error!" + xhr.status);
		        }
			});
			
		});
		

		$("#applyformat").on("click", function(e){
	/*		alert(url+"/admin/loadformat");*/
			
			var format_id = $("#currentformat").find("input").attr("fid");
	/*		alert(format_id);
			alert(adminclassname);*/
			var pgnum = $("#pgnum").val();
/*			alert(pgnum);*/
			$.ajax({
				type: "POST",
				url: url+"/admin/loadformat",
				data: {"adminclassname":adminclassname, "pgnum":pgnum, "format_id":format_id},
				dataType: "text",
				success: function(data){
						clname = adminclassname;
						$("#popup").hide();
						$("#list").html(data);
						
				/*		popup_position($("#popup"));
						$("#popup").fadeIn('200');*/
						
						adminclassname = clname;


				},      
			    error : function(xhr, status, error){
		            alert("Error!" + xhr.status);
		        }
			});
			
		});




		$("#moveactive").on("change", function(){
			if(!$("#moveactive").is(":checked")){
				
				$("#saveroworder").hide();
			} 
			
			else{
				$("#saveroworder").show();
			} 
		});

		
		
		$("#managecols").on("click", function(){
	//		alert(url+"/admin/multichange");
			


			$.ajax({
				type: 'POST',
//			    dataType : "json",
				url: url+"/admin/managecols",
			    data: {"adminclassname":adminclassname},
		        success: function(data){
		        	$("#popup").html(data);
					$("#popup").fadeIn('200');
		        },      
	    	    error : function(xhr, status, error){
	                alert("Error!" + xhr.status);
	    	    }
		    });
			
		});
		
		
$('#table').on('mousedown mouseup mouseover', function mouseState(e) {
	
	var currenttr=$(e.target).closest('tr');
//	var currentsel=$(e.target).closest('tr').children('td').find("#selector");
//	alert(currentsel.attr('id'));
	//if(currentsel.is(':hover)')){
	
	if (e.target.tagName === 'TD'){
    if (e.type == "mousedown" && !pushed) {
 //   	alert("down "+isdown);
    	select = (currenttr.hasClass("myclass"))?false:true;
    	pushed = (currenttr.find("th")[0]==null)?true:false;
    		if(pushed && select){
    		currenttr.addClass('myclass');
    		currenttr.children('td').css('background','rgb(96,108,136)');
    		}
    		if(pushed && !select){
				$(e.target).closest('tr').removeClass('myclass');
			    $(e.target).closest('tr').children('td').css('background','');	
			}
    		pushed = true;	
    		
    	
    }
    if (e.type == "mouseup") {
    	pushed = false;
    	currurl = '"'+url+'"';
    	currclass = '"GoodItem"';
    	pushed = false;
    	var t = 0;
    	var count = 0;
    	hasselected = false;
  //  	alert($(".myclass")[0]);
    	if($(".myclass")[0]!=null){
    		hasselected = true;
    	}
    	$(".myclass").each(function(){
    		count++;
    	});
    	if(count>0){
    	$("#selectcount").html("<li>Выбранно записей: "+count+"</li>");}
    	else{$("#selectcount").html("");}
    	
    	if(!hasselected){
    		$("#multimenu").detach();
    	}
    	else{
   // 		alert("fff");
    		$("#menucontainer").html("<div id = 'multimenu'><button onclick = 'multidelete("+currurl+","+currclass+","+$("#pgnum").val()+")' id ='multidelete'>Удалить записи</button><button id = 'formmultichange' onclick ='formmultichange("+currurl+")'>Изменить значения полей для записей</button></div>");
    	}
//    	currenttr.removeClass('myclass');
//    	currenttr.closest('tr').children('td').css('background','');
    }
    
    if (e.type == "mouseover") {
    	var ind = false;
    	select = (currenttr.hasClass("myclass"))?false:true;
    	if(pushed == true)
 //   	alert(select);
    	if(pushed == true && select) {
    //		alert(pushed);
    //		currenttr=current.next();
    //		alert("addmyclass");
    		currenttr.addClass('myclass');
    		currenttr.children('td').css('background','rgb(96,108,136)');
    		ind = true;
    	}
    	if(pushed == true && !select){
  //  		alert("delmyclass");
    		$(e.target).closest('tr').removeClass('myclass');
		    $(e.target).closest('tr').children('td').css('background','');
		    
    	}
//    	currenttr.addClass('myclass');
// 		currenttr.children('td').css('background','#849693');
/*	    	$(e.target).closest('tr').removeClass('myclass');
	    $(e.target).closest('tr').children('td').css('background','');*/
    }
    }
});




});