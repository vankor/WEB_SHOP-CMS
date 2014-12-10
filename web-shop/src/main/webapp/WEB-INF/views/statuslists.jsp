<div style="clear: left; width: 100%; margin-bottom: 10px; margin-top: 20px;">
                <div style="float: left; width: 1000px;" id="special">
                <div class="special_div activediv" id="div_2"><p id="p_2" onclick="slect_spec(2, 'Все лидеры продаж')" class="activep">Лидеры продаж</p></div>
    				<div class="special_div" id="div_3"><p id="p_3" onclick="slect_spec(3, 'Все новинки')" class="">Новинки</p></div><div class="special_div" id="div_20"><p id="p_20" onclick="slect_spec(20, 'Все акционные товары');" class="">Акции</p></div><div class="special_div" id="div_4"><p id="p_4" onclick="slect_spec(4, 'Все наши советы')" class="">Наш совет</p></div><div class="special_div" id="div_9"><p class="special_p" id="p_9" onclick="slect_spec(9, 'Все уцененные товары')">Уцененные товары</p></div>
                <div id="carousel" style="margin-top: 30px;">
                    <div style="position: relative;" id="car_2"><div><div class="gcaccesories">
                	  		 <div class="gcacontent" style="padding-top: 20px;">
                                <div class=" jcarousel-skin-gcatango5"><div class="jcarousel-container jcarousel-container-horizontal" style="position: relative; display: block;"><div class="jcarousel-clip jcarousel-clip-horizontal" style="overflow: hidden; position: relative;">
                                <ul id="gcacarousel_2" class="jcarousel-list jcarousel-list-horizontal" style="overflow: hidden; position: relative; top: 0px; margin: 0px; padding: 0px; left: 0px; width: 1950px;">
                                <li class="jcarousel-item jcarousel-item-horizontal jcarousel-item-1 jcarousel-item-1-horizontal" jcarouselindex="1" style="float: left; list-style: none;">
                			<div class="gcac_image" style="text-align: left; width: 150px; height: 135px; position: relative;">
                                <img src="http://img.mobilluck.com.ua/img/new_ico/cat/2.png" style="position:absolute;right:0;top:0;">
                				<table cellpadding="0" cellspacing="0" width="100%" border="0">
                				<tbody><tr>
                					<td align="left">
                						<a rel="nofollow" href="http://www.mobilluck.com.ua/katalog/iplanshet/Lenovo/Lenovo-IdeaTab_A7600_16GB_Black__59408879_-315646.html"><img src="http://pics.mobilluck.com.ua/thumb/iplanshet/Lenovo/Lenovo_IdeaTab_A7600_16GB_Black__59408879__315646_640716.jpg" width="130" height="87" alt="Планшет Lenovo IdeaTab A7600 16GB Black (59408879)"></a>
                					</td>
                				</tr>
                				</tbody></table>
                			</div>
                            <div style="height: 30px; overflow: hidden; text-align: left; font-size: 12px; margin-top: 5px;"><a rel="nofollow" href="http://www.mobilluck.com.ua/katalog/iplanshet/Lenovo/Lenovo-IdeaTab_A7600_16GB_Black__59408879_-315646.html">Планшет Lenovo IdeaTab A7600 16GB Black (59408879)</a></div>
                                <div style="text-align: left; font-size: 12px; margin-top: 0px; color: #939393;">
                                    <img src="http://www.mobilluck.com.ua/img/product/raiting5.png" style="position: relative; top: 4px;"> 10 оценок
                                </div>
                				<table width="100%" border="0">
                                <tbody><tr>
                                    <td width="50%" valign="middle" style="font-size: 16px; font-weight: bold; text-align: left;">
                                        
                                        3 799 грн
                                    </td>
                                    <td valign="top" style="font-size: 16px; font-weight: bold; text-align: center;">
                                        <div class="btn_buy_other_sm" onclick="return buy_popup(315646,'7t9rg62gbon4i3doht75olq7j0',this, '0')"></div>
                                    </td>
                                </tr>
                                </tbody></table>
                			</li>
                			
                			</ul>
                			</div>
                			<div class="jcarousel-prev jcarousel-prev-horizontal jcarousel-prev-disabled jcarousel-prev-disabled-horizontal" disabled="true" style="display: block;"></div><div class="jcarousel-next jcarousel-next-horizontal" disabled="false" style="display: block;"></div></div></div>
                			</div></div><div>
                    <div style="clear: left; font-size: 14px;"><a rel="nofollow" href="http://www.mobilluck.com.ua/katalog/iplanshet/spezprop9.html">Все уцененные товары</a></div></div></div></div>						<script style="display: none;">
				var load_array = new Array(20);
				function slect_spec(id, txt)
				{
				    if(load_array[id] != 1)
				        loaditems(293, id, txt);

				    $("#special").children(['.special_div']).removeClass('activediv');
				    $("#special").children(['.special_div']).children(['p']).removeClass('activep');

				    $("#p_"+id).addClass('activep');
				    $("#div_"+id).addClass('activediv');

				    $("#carousel").children().css('display', 'none');
				    //$("#carousel").children().fadeOut('slow');

				    $("#car_"+id).fadeIn("slow");//css('display', 'block');
				}

				function loaditems(sid, cmnt, txt)
				{
				    $("#car_"+cmnt).html('<table width="700" height="200" style="float: left;"><tr><td align="center" valign="middle"><img src="http://www.mobilluck.com.ua/img/load.gif" align="center"></td></tr></table>');
				    $.ajax({
				        type: "POST",
				        url: "/ajx/ajx_jq.php",
				        data: 'cmd=uh_com_loaditems&sid='+sid+'&cmnt='+cmnt+'&csi=7t9rg62gbon4i3doht75olq7j0',
				        dataType: "html",
				        success: function(data)
				        {
				            var nurl = "http://www.mobilluck.com.ua/katalog/iplanshet/spezprop"+cmnt+".html";
				            var nlink = '<div style="clear: left; font-size: 14px;"><a rel="nofollow" href="'+nurl+'">'+txt+'</a></div>';
				            $("#car_"+cmnt).html(data+nlink);
				        }
				    });
				    load_array[cmnt] = 1;
				}

				$(document).ready(function(){

				    loaditems(293, '2', 'Все лидеры продаж');

				    $("#car_2").css('display', 'block');
				    $("#p_2").addClass('activep');
				    $("#div_2").addClass('activediv');
				});
			</script>
				</div>

                </div>
                     </div>