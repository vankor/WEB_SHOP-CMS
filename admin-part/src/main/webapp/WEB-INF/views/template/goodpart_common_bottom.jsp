   <%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

    <div style="width: 1000px; margin-top: 50px; padding-top: 30px;">
            </div>
        <div style="width: 1000px; clear: left; margin-top: 50px;">
        <div class="gcaccesories" style="margin-top: 50px;">
    			     <div style="font-family: sans-serif; font-size: 24px; color: #2d2d2d;">
                    Похожие модели
                    </div>
    			<div class="gcacontent" style="padding-top: 20px;">
    				<ul id="gcacarousel2" class="jcarousel-skin-gcatango5">
    				<c:forEach items = "${othergoods}" var = "othergood">
    				<li style="border: 0px solid red;">
    				<div class="gcac_image" style="text-align: center; width: 100%; height: 125px;">
    					<table cellpadding="0" cellspacing="0" align="center" width="100%" border="0">
    						<tr>
    							<td align="center">
    								<a rel="nofollow" href="${pageContext.request.contextPath}/catalog/${othergood.page.fullurl}/all"><img src="<c:url value="/resources/images${othergood.thumb}" />" data-original="<c:url value="/resources/images${othergood.thumb}" />" alt="${othergood.name}"  style="max-width:130px; max-height:130px;" /></a>
    							</td>
    						</tr>
    					</table>
    				</div>
    				<div style="height: 32px; overflow: hidden; text-align: center; font-size: 12px; margin: 5px auto 0 auto; max-width: 130px;"><a rel="nofollow" href="${pageContext.request.contextPath}/catalog/${othergood.page.fullurl}/all">${othergood.name}</a></div>
    				<div style="text-align: center; font-size: 12px; margin-top: 0px; color: #939393">
                        <img src="<c:url value="/resources/images/good/raiting${othergood.round_rating}.png" />" style="position: relative; top: 4px;"> ${othergood.votes_count} оценок
                    </div>
                    <table width="100%" border="0">
                        <tr>
                            <td width="50%" valign="middle" style="font-size: 16px; font-weight: bold; text-align: left;">
								
                                ${othergood.price} грн 
                            </td>
                            <td valign="top" style="font-size: 16px; font-weight: bold; text-align: center;">
                                <div class="s_but" onclick="addtobuck(${othergood.id},${bucketsize})"></div>
                            </td>
                        </tr>
                    </table>
    			</li>
    			</c:forEach>
    			</ul>
    			</div></div>    </div>
    
<div style="width: 100%; float: left; margin-top: 50px;">
    <div style="font-family: sans-serif; font-size: 24px; color: #2d2d2d; margin-bottom: 20px;">
        Вы смотрите ${good.name}    </div>
    <div style="float: left; border: 0px solid red; display: block; width: 300px; text-align: center;">
                    <img src="<c:url value="/resources/images${good.thumb}" />" width="290" alt="${good.name}" title="${good.name}" style="padding: 0px 15px 10px 0px;"/>
            </div>
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
                    ${goodstate}, Сроки доставки: ${good.mindeliverydays}-${good.maxdeliverydays} дней
                  </span>
                  </c:if>
                  <c:if test = "${goodstate.id == 15}">
                 <span style="color:red">${goodstate}</span>
                  </c:if>
                </td>
                <td style="text-align: left; height: 30px; vertical-align:middle;">
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

    <div>
                <div id="descrseo" style="clear: left; width: 900px; height: 20px; overflow: hidden; display: block; padding-top: 10px;">
           <i>* Характеристики и комплектация ${good.name} могут изменяться производителем без уведомления.</i>
                   </div>
                            <div id="hidedescrseo" style="clear: left; width: 650px; text-align: left; display: none;"><a onclick="showalltext2()" href="javascript:void();" class="javalink">Свернуть текст</a></div>
       
    </div>
    </div>


<div style="margin-top: 30px; margin-bottom: 30px;">
                    <img src="http://www.mobilluck.com.ua/img/product/left_arrow.png"/> <a href="${pageContext.request.contextPath}/catalog/${good.page.fullurl}/all" style="font-size: 16px;">Вернуться к подробной информации о ${good.name}</a>
               </div>