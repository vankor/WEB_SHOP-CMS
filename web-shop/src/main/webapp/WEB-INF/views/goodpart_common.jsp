    <%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
    
        <div style="float: left; border: 0px solid red; display: block; text-align: center;">
                            <img src="<c:url value="/resources/images${good.thumb}" />" style="padding: 0px 25px 10px 25px;" width="250"/>
            

        </div>
     <!--    <div style="float: left;">
            
       <!--               <div class="club_cost" style="margin-bottom: 5px;">
                        <div style="float: left; background-color: #E78000; padding: 2px 5px; border-radius: 5px; color: white; font-size: 14px;">
                            + 56 баллов
                        </div>
                        <div class="btn_help" style="float: left; margin-left: 10px;" onclick="$('#popupbg').hide(); $('#dialog_wrapper').hide(); return load_text(this, 'plusballs', 56)"></div>
                    </div>
        -->
        <!--              <div style="display: block;">
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
                     </div> -->
                     
           
           
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
            <img id="buy" src="<c:url value = "/resources/images/good/buy1.png"/>" onclick="$('#popupbg').hide(); $('#dialog_wrapper').hide(); return addtobuck('${pageContext.request.contextPath}/good/${good.id}/${section}',${good.id},${bucketsize})">
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
                <td style="padding-left:20px;text-align: left; height: 30px; vertical-align:middle;">
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
           
           
           
                     
                     
        <div style="float: left; padding-left: 15px; width: 160px;">
        
    <div class="raiting"  rel="v:rating">
        <img src="<c:url value="/resources/images/good/raiting${good.round_rating}.png" />"/> ${good.votes_count} оценок
        <span style="display: none">4</span>
        <span style="display: none">5</span>
        <br />
        <img src="http://www.mobilluck.com.ua/img/product/response.png"/> <a rel="nofollow" href="http://www.mobilluck.com.ua/responce/game-stations/genius /genius -Heeha_100X-89477.html"><span property="v:votes">8</span> отзывов</a>
        
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

                                </div>        
                                
                         </div>
                                   <div class="links">
                <img src="http://www.mobilluck.com.ua/img/product/compare.png"/> <a href="#" id="cmprlnk${good.id}" onclick="return addCompare(${good.id},${good.category.id},'${pageContext.request.contextPath}')" class="javalink"><c:choose><c:when test = "${containswish == true}">Сравнить</c:when><c:otherwise>Добавить к сравнению</c:otherwise></c:choose></a>
                <br />
                <img src="http://www.mobilluck.com.ua/img/product/wishlist.png"/> <c:choose><c:when test="${empty user}"> <a  id="addwish" href="javascript:void();" onclick="return login(this,100)" class="javalink">В желаемые товары</a></c:when><c:otherwise><a  id="addwish" href="javascript:void();" onclick="return add_wishlist(${good.id},'wishreg',this)" class="javalink">В желаемые товары</a></c:otherwise></c:choose>
                <br />
           						 </div>