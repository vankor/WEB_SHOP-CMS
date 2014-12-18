  <%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
  
    
    
    <div class="photobox">
    <table width="100%">
        <tr>
            <td valign="middle" align="center" height="350" style="width: 350px; height: 350px;">

                <div style="width: 350px; position: relative; overflow: hidden;">
                    <div id="photolist" style="border: 0px solid red; position: relative;">
                        <table border="0" width="700" cellpadding="0" cellspacing="0">
                        
					<c:forEach items = "${good.images}" var = "image" varStatus = "i">
                        <td align="center" valign="middle" style="width: 350px;">
                        <c:forEach items = "${good.statuses}" var = "status"><img src="<c:url value="/resources/images${status.thumb}" />" style="margin-bottom:20px;position:absolute;right:0;top:0;"/><br></c:forEach>
                            
                            <a href="#" onclick="show_big_photo(this, ${good.id}, ${i.index})">
                                <img src="<c:url value="/resources/images${image.url}" />" width="340" alt="${good.name}" id="main_view"/>
                            </a>
                        </td>
               		 </c:forEach>

                        </table>
			         </div>
                </div>
            </td>
        </tr>
        <tr>
            <td align="left"><table cellpadding="0" cellspacing="5" align="left"><tr>
            	<c:forEach items = "${good.images}" var = "image" varStatus = "i">
                <td valign="middle" align="center" class="smallphoto" style="cursor: pointer;">
                    <a href="<c:url value="/resources/images${image.url}" />" class="photosm" wid="${i.index}"  w="340" h="254"><img src="<c:url value="/resources/images${image.url}" />" width="50" alt="${good.name}"  /></a>
                </td>
                </c:forEach>
                </tr></table></td>
        </tr>
    </table>
    </div>
   	

    <div style="float: left; width: 350px;">
    <div class="prod_center" style="width: 335px;">
                    
                    <!-- 
                    <div class="club_cost" style="margin-bottom: 5px;">
                        <div style="float: left; background-color: #E78000; padding: 2px 5px; border-radius: 5px; color: white; font-size: 14px;">
                            + 53 балла
                        </div>
                        <div class="btn_help" style="float: left; margin-left: 10px;" onclick="$('#popupbg').hide(); $('#dialog_wrapper').hide(); return load_text(this, 'plusballs', 53)"></div>
                    </div>
                    --->
        <!--             <div class="old_cost">
                            							
                    </div>
                    <div style="display: block;">
                        <div class="buy_block">
                            <div class="costline" property="v:pricerange">
                                <span class="cost" >${good.price}</span>
                                <span class="currency">грн</span>
                            </div>
                            <div class="btn_buy" onclick="$('#popupbg').hide(); $('#dialog_wrapper').hide(); return addtobuck('${pageContext.request.contextPath}/good/${good.id}/${section}',${good.id},${bucketsize})"></div>
                        </div>
                    </div>
                            <div class="presence" itemprop="availability" content="in_stock">
                                ${goodstate}
                            </div> 
                            <div class="srok">
                                <a href="" class="javalink" onclick="$('#popupbg').hide(); $('#dialog_wrapper').hide(); return load_text(this, 'uh_base', 89477)">Узнать срок и стоимость доставки</a>
                            </div>
                            
                          --->   
                             
                             
                             
                             
                             	<div class="product_conf_buy" style="padding-bottom: 4px;">
  <table>
    <tbody>
      <tr class="frst">
        <td>
          <span class="bal_count">
            +${good.bonusscores} балла
          </span>
          <img src="<c:url value = "/resources/images/good/quest.png"/>" class="question" onclick="$('#popupbg').hide(); $('#dialog_wrapper').hide(); return load_text(this, 'plusballs', 112)">
        </td>
        <td rowspan="2">
          <a style="cursor:pointer">
            <img id="buy" src="<c:url value = "/resources/images/good/buy1.png"/>" onclick="$('#popupbg').hide(); $('#dialog_wrapper').hide(); return addtobuck(${good.id},${bucketsize})">
          </a>
        </td>
      </tr>
      <tr class="scnd">
        <td>
          <meta itemprop="currency" content="UAH">
          <span itemprop="price" class="price itemprice">
            ${good.price}
          </span>
          
          <span class="currency itemprice">
            грн
          </span>
          
        </td>
      </tr>
      <tr class="thrd" style="display:none;">
        <td>
        </td>
        <td>
        </td>
      </tr>
      <tr class="thrd">
        <td colspan="2">
          <table class="nal_new" style="height:auto">
            <tbody>
              <tr>
                <td style="height: 30px; vertical-align:middle;">
                  <img src="<c:url value = "/resources/images/good/instock.png"/>">
                 <c:if test = "${goodstate.id != 15}">
                  <span style="color:#008015">
                    <c:if test = "${!empty goodstate}">${goodstate}</c:if><c:if test = "${!empty good.mindeliverydays && !empty good.maxdeliverydays}">, ${mindeliverydays}-${maxdeliverydays} дней</c:if><c:if test = "${!empty good.mindeliverydays && empty good.maxdeliverydays}">, ${mindeliverydays} дней</c:if><c:if test = "${empty good.mindeliverydays && !empty good.maxdeliverydays}">, ${maxdeliverydays} дней</c:if>
                  </span>
                  </c:if>
                  <c:if test = "${goodstate.id == 15}">
                 <span style="color:red">${goodstate}</span>
                  </c:if>
                </td>
                <td style="padding-left:20px; text-align: left; height: 30px; vertical-align:middle;">
                  <a onclick="$('#popupbg').hide(); $('#dialog_wrapper').hide(); return load_text(this, 'uh_base', ${good.id})">
                    <img src="<c:url value = "/resources/images/good/clock.png"/>">
                    <span>
                      Сроки доставки
                    </span>
                    <img src="<c:url value = "/resources/images/good/quest.png"/>">
                  </a>
                </td>
              </tr>
            </tbody>
          </table>
        </td>
      </tr>
      
    </tbody>
  </table>
  <img src="http://img.mobilluck.com.ua/img/prdctcart/skidki.png" style="display:none" class="skidla_read">
</div>
                             
                             
                             
                             
                            
                            
                            
                            <div style="clear: left; width: 100%; margin-bottom: 10px;"><div style="float: left; width: 170px;">
    <div class="raiting"  rel="v:rating">
        <img src="<c:url value="/resources/images/good/raiting${averagenote}.png" />"/> ${votes_count} оценок
        <span style="display: none">4</span>
        <span style="display: none">5</span>
        <br />
        <img src="http://www.mobilluck.com.ua/img/product/response.png"/> <a rel="nofollow" href="${pageContext.request.contextPath}/catalog/${good.page.fullurl}/voices"><span property="v:votes">${comCount}</span> отзывов</a>
        
      	<c:if test="${isVoted == 0}">
                                <div id="raiting_star">
                                    <div id="raiting">
                                        <div id="raiting_blank"></div> <!--блок пустых звезд-->
                                        <div id="raiting_hover"></div> <!--блок  звезд при наведении мышью-->
                                        <div id="raiting_votes"></div> <!--блок с итогами голосов -->
                                    </div>
                                    <div id="raiting_info">
                                        <img src="http://www.mobilluck.com.ua/img/load.gif" width="15"/>
                                        <span id="raiting_infot">Оцените товар</span>
                                    </div>
                                </div>
			</c:if>
                               </div></div><div style="float: left;">           
                                <div class="links">
                <img src="http://www.mobilluck.com.ua/img/product/compare.png"/> <a href="#" id="cmprlnk${good.id}" onclick="return addCompare(${good.id},${good.category.id},'${pageContext.request.contextPath}')" class="javalink"><c:choose><c:when test = "${containswish == true}">Сравнить</c:when><c:otherwise>Добавить к сравнению</c:otherwise></c:choose></a>
                <br />
                <img src="http://www.mobilluck.com.ua/img/product/wishlist.png"/> <c:choose><c:when test="${empty user}"> <a  id="addwish" href="javascript:void();" onclick="return login(this,100)" class="javalink">В желаемые товары</a></c:when><c:otherwise><a  id="addwish" href="javascript:void();" onclick="return add_wishlist(${good.id},'wishreg',this)" class="javalink">В желаемые товары</a></c:otherwise></c:choose>
                <br />
           						 </div>
    </div><div style="clear: left;"></div></div>
  	<c:if test = "${!empty good.goodguaranties} ">
 	<table width="100%" border="0" style="clear:both;">
            <tbody><tr>
                <td valign="top" style="display:none;">
                    <input id="ultra_value" name="ultra_value" type="hidden" value="0">
                    <input type="checkbox" class="check" id="chultra" onclick="ultraservise_click(${good.id}, '7t9rg62gbon4i3doht75olq7j0', this, 0)">
                </td>
                <td style="width:240px;">
                    <div style="font-size: 12px; border: 1px solid #fff; position: relative; list-style: none; padding-left: 12px; line-height: 20px; margin-top: 0px;left: 3px; position: relative;top: 3px;">
                        <div style="float: left;  padding-left: 0px;">
                            <input type="hidden" name="ys" id="ys" value="2">
                            Расширенная гарантия <a id="rfiliallnk2" class="javalink" href="#">на ${good.goodguaranties[0].count} ${good.goodguaranties[0].periodtype}</a>&nbsp;<img style="cursor:pointer; position:relative; top:3px;" onclick="return load_text(this, 'ultra_service2', 342235)" src="http://img.mobilluck.com.ua/img/prdctcart/quest.png">
                        </div>
                        <div id="ccsdd4" class="ccsdropdown" style="width: 65px; left: 158px; top: 16px; display: none;">
                                                       
                                                        <div class="ccsdinside" style="padding-left: 0px; border: 1px solid gray; border-top: 0px solid gray;">
                                                                <table cellspacing="0" cellpadding="2">
                                        <tbody>
                                        <c:forEach items = "${good.goodguaranties}" var = "guar">
                                        <tr>
                                        
                                            <td>
                                                <a href="#" onclick="ultrasel(2); return false">${guar.count} ${guar.periodtype}</a>
                                            </td>
                                        </tr>
                                       </c:forEach>
                                                                </tbody></table>
                                                        </div>
                                        </div>
                    </div>
                </td>
                <td align="right" style="vertical-align:middle;">
                    <span style="font:bold 14px arial;position: relative;top: 2px; right: 5px;"><span id="ultracost" class="cost">300</span> грн</span><img style="cursor:pointer; margin-left:3px; vertical-align: middle;" onclick="ultraservise_click(${good.id}, '7t9rg62gbon4i3doht75olq7j0', this, 0)" src="<c:url value="/resources/images/good/bn.png" />">
                </td>
                 </tr>
                 </tbody>
                 </table>
  <div style="clear: left;"></div>
  </c:if>
   <!-- 
    <div class="ultra_service">
        Добавить к товару
        <br />
    
        <table width="100%" border="0">
            <tr>
                <td valign="top">
                    <input type="checkbox" class="check" id="chultra" onclick="ultraservise_click(89477, 'it986qr2arh979bde5unt5nfl2', this, 0)"/>
                </td>
                <td>
                    <div style="font-size: 12px; border: 1px solid #fff; position: relative; list-style: none; padding-left: 0px; line-height: 20px; margin-top: 0px;">

                        <div style="float: left;  padding-left: 0px;">
                            <input type="hidden" name="ys" id="ys" value="2">
                            Ультрасервис <a id="rfiliallnk2" class="javalink" href="#">на 2 года</a><img src="http://www.mobilluck.com.ua/img/product/select.png"/>
                        </div>
                        <div id="ccsdd4" class="ccsdropdown" style="width: 70px; left: 85px; top:12px; display:none;">
    							<div class="ccsdinside" style="padding-left: 0px; border: 1px solid gray; border-top: 0px solid gray;">
    								<table cellspacing="0" cellpadding="2">
                                        <tr>
                                            <td>
                                                <a href="#" onclick="ultrasel(2); return false">на 2 года</a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <a href="#" onclick="ultrasel(3); return false">на 3 года</a>
                                            </td>
                                        </tr>
    								</table>
    							</div>
    					</div>
                    </div>
                </td>
                <td align="right" width="58" valign="top">
                    <span><span id="ultracost" class="cost">40</span> грн</span>
                </td>
                <td><div class="btn_help" onclick="return load_text(this, 'ultra_service', 89477)"></div></td>
            </tr>
        </table>
    
    </div>
  -->

  	<div style="margin-top: 10px; "><div class="char_txt">
        <c:if test = "${!empty segments}">
		<h3> Параметры товара ${good.name}:</h3>
<!--		<a id = "hreff" data-href = "dmsuY29t" href = "#">ОНКОР</a>-->


<!--  <div class="product_all_info_specifications">
					<div id="product_all_preview">
														<table cellspacing="0" width="544" cellpadding="5">
							<tbody>
							
									<c:forEach items = "${segments}" var = "segment" varStatus = "i">
									<tr style="background-color: #fff">
										<td style="font-size: 14px; font-weight: bold;" colspan="2">${segment.segment_name}</td>
									</tr>
									<c:forEach items = "${segment.properties}" var = "propbean" varStatus = "j">
									<tr style="background-color: #fff">
												<td width="250" valign="top"><c:out value="${propbean.prop.name}:"/></td>
												<td>
												<c:forEach items = "${propbean.vals}" var = "value" varStatus = "i">
												${value.value}; 
												</c:forEach>
												</td>
											</tr>
									</c:forEach>
									
								</c:forEach>
														</tbody></table>
						<div class="item_fog"></div>
					</div>
					<p class="show_more_info" pid="0">Показать все характеристики</p>
					<script type="text/javascript">
						$(".show_more_info").click(function(){
							if($(this).attr("pid")==0)
							{
								$(this).attr("pid","1");
								$('#product_all_preview').css('max-height','none');
								$(".show_more_info").html("Скрыть характеристики");
								$("#product_all_preview .item_fog").hide();
							}
							else
							{
								$(this).attr("pid","0");
								$('#product_all_preview').css('max-height','334px');
								$(".show_more_info").html("Показать все характеристики");
								$("#product_all_preview .item_fog").show();
							}
						});
					</script>
					<div id="item_descr_info" style="display: block;">
						<p class="item_descr_info_title">Описание</p>
						<div id="item_descr_info_content">
							<div id="descr_new"><div style="clear:left;">
            <h2 style="font-family: sans-serif; font-size: 14px; color: #2d2d2d;">Описание ${good.name}</h2><br><br><div><b>${good.description}</div>

            </div></div>
							<div class="item_fog"></div>
						</div>
						<p class="show_more_descr" pid="0">Показать полный текст</p>
						
                                                <script type="text/javascript">
                                                   $(".item_error_founded").click(function()
                                                    {
                                                        var ww = $(window).width(), wh = $(window).height();
                                                        var left = this.offsetLeft, top = this.offsetTop;
                                                        if (navigator.userAgent.toLowerCase().indexOf("msie") != -1){left += (ww-978)/2;}

                                                        var coor = getOffset( this );
                                                        left = coor.left - 250;
                                                        top = coor.top - 10;

                                                        $.ajax({
                                                                type: "POST",
                                                                url: "/ajx/ajx_pop.php",
                                                                data: 'cmd=uh_feed_err&id=261479',
                                                                dataType: "text",
                                                                success: function(data){
                                                                        $("#dialog_title").html('');
                                                                        $("#dialog_content").html(data);
                                                                        showPopupDlg(left, top, 480);
                                                                }
                                                        });
                                                    });
                                                </script>
					</div>
				</div>

-->


		<ul>
			
			
			<c:forEach items = "${segments}" var = "segment" varStatus = "i">
			<tr><td><h4>${segment.segment_name}:</h4></td></tr>
			<c:forEach items = "${segment.properties}" var = "propbean" varStatus = "j">
			<li><c:out value="${propbean.prop.name}:"/>
			<c:forEach items = "${propbean.vals}" var = "value" varStatus = "i">
			<span style = "font:  110% bold;" >${value.value};</span> </li>
			</c:forEach>
			</c:forEach>
			</c:forEach>
			
			
		
	
		</ul>
		</c:if>
        </div>
        <div class="char_txt"><a rel="nofollow" href="${pageContext.request.contextPath}/catalog/${good.page.fullurl}/features" style="top: 0px; position: relative;">Все характеристики</a></div></div></div>
 

<jsp:include page="/WEB-INF/views/social.jsp" />
</div>    
        
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
        <div style="width: 1000px; clear: left;">
 
        <div style="width: 700px; float: left;">
        
        <div style="width: 700px; float: left; margin-top: 50px; margin-bottom: 20px; background-color: #fff;"  id="responce">
            <div style="font-family: sans-serif; font-size: 24px; color: #2d2d2d;">
                Полезные отзывы
            </div>
            <div style="margin-bottom: 70px; margin-top: 15px;" id="responce_block">
                <div class="responce" onclick = ""></div>
                <div style="float: left; margin-left: 15px; margin-top: 7px;">
                    <a rel="nofollow" href="http://www.mobilluck.com.ua/responce/game-stations/genius /genius -Heeha_100X-89477.html">Читать все отзывы</a>
                </div>
            </div>
            <div id="resp_error" style="clear: left; width: 650px;"></div>
            <div id="commsection" style="margin-bottom: 20px; border: 0px solid red;">
       		<jsp:include page="/WEB-INF/views/comments.jsp" />
		</div>


                <div style="padding: 10px; display: none; opacity: 0; position: absolute; z-index: 10; border: 1px solid gray; background-color: #e8e8e8; color: black;" id="txt"></div>

        <div style="margin-top: 40px;">
                <div class="responce"></div>
                <div style="float: left; margin-left: 15px; margin-top: 7px;">
                    <a rel="nofollow" href="http://www.mobilluck.com.ua/responce/game-stations/genius /genius -Heeha_100X-89477.html">Читать все отзывы</a>
                </div>
        </div>
    </div>        </div>
    

   
    </div>



