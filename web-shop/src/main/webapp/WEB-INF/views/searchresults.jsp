<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">





		<div class="ccatalog">
		<div class="cbreadcrumbs">
			<a href="http://www.mobilluck.com.ua/">Главная</a> /
		</div>
		<div>
			<h1 class="mgood_title">Поиск <i>«шкаф»</i></h1>
						<span class="mgood_span"> </span>
		</div>
<div class="cclist">
<c:forEach items = "${goods}" var = "good" varStatus = "i">
	<div class="ccitem">
				<table class="ccitable" cellpadding="0" cellspacing="0">
					<tbody><tr>
						<td class="ccit_image" style="position:relative;display: block;">
							<a rel="nofollow" href="${pageContext.request.contextPath}/good/${good.id}/all"><img src="<c:url value="/resources/images${good.thumb}" />" data-original="<c:url value="/resources/images${good.thumb}" />" width="90" alt="${good.name}"  /></a>
						</td>
						<td class="ccit_descr">
							<p class="ccitd_title"><a href="${pageContext.request.contextPath}/good/${good.id}/all">${good.name}</a></p>
							<p class="ccitd_code">Код товара: ${good.id}</p>
							<div class="ccitd_resp">
								<img src="<c:url value="/resources/images/good/raiting${good.round_rating}.png" />" style="position: relative; top: 4px;"> ${good.votes_count} оценок
								
							</div>
							<div class="ccitd_info">${good.description}</div>
							
							<table class="ccitdtable" cellpadding="0" cellspacing="0">
								<tbody><tr>
									<td class="comprow" id="comprow144021"><a href="#" onclick="return addCompare(144021,164)" class="mgtl_add2cmp"><span>Добавить к сравнению</span></a></td>
									<td>
										<c:if test = "${!empty good.state.text}">${good.state.text}</c:if><c:if test = "${!empty good.mindeliverydays && !empty good.maxdeliverydays}">, ${mindeliverydays}-${maxdeliverydays} дней</c:if>
									</td>
								</tr>
								<tr><td class="ccitdt_sep"></td></tr>
								<tr>
									<td class="pricerow">
										
										<p class="ccitd_price">${good.price} грн</p>
									</td>
									<td>
                                    <a rel="nofollow" onclick="addtobuck('${pageContext.request.contextPath}/good/${good.id}/all',${good.id},${bucketsize})"><img src="http://img.mobilluck.com.ua/img/btn_buy_new.gif" alt="Добавить в корзину - купить Духовой шкаф электрический ElectroLux EEB 4231 POX (12 мес.)" height="37" width="107"></a>
									</td>
								</tr>
								<tr><td class="ccitdt_sep"></td></tr>
							</tbody></table>
						</td>
					</tr>
				</tbody>
				</table>
			</div>
			</c:forEach>
			</div>
			<div class="ccpnav" style="width: 100%;"><span class="a-button">1</span><a class="a-text" href="/search.php?pi=1&amp;pn=30&amp;sw=шкаф">2</a><a class="a-text" href="/search.php?pi=2&amp;pn=30&amp;sw=шкаф">3</a><a class="a-text" href="/search.php?pi=3&amp;pn=30&amp;sw=шкаф">...</a><a class="a-text" href="/search.php?pi=84&amp;pn=30&amp;sw=шкаф">85</a><a class="a-text a-next" href="/search.php?pi=1&amp;pn=30&amp;sw=шкаф">След.</a><span class="arrow">→</span>
			</div>	
</div>













  				<ul id="srchresults">
    				<c:forEach items = "${goods}" var = "good" varStatus = "i">
    				<li style="border: 0px solid red;">
    				<div class="gcac_image" style="text-align: center; width: 100%;">
    					<table cellpadding="0" cellspacing="0" align="center" width="100%" border="0">
    						<tr>
    							<td align="center">
    								<a rel="nofollow" href="${pageContext.request.contextPath}/good/${good.id}/all"><img src="<c:url value="/resources/images${good.thumb}" />" data-original="<c:url value="/resources/images${good.thumb}" />" width="90" alt="${good.name}"  /></a>
    							</td>
    						</tr>
    					</table>
    				</div>
                    <div style="height: 47px; overflow: hidden; text-align: left; font-size: 12px; margin-top: 5px;"><a rel="nofollow" href="${pageContext.request.contextPath}/good/${good.id}/all">${good.name}</a></div>
                    <div style="text-align: left; font-size: 12px; margin-top: 0px; color: #939393;">
                        <img src="<c:url value="/resources/images/good/raiting${good.round_rating}.png" />" style="position: relative; top: 4px;"> ${good.votes_count} оценок
                    </div>
    				<table width="100%" border="0">
                        <tr>
                            <td width="50%" valign="middle" style="font-size: 16px; font-weight: bold; text-align: left;">
                                ${good.price} грн.
                            </td>
                            <td valign="top" style="font-size: 16px; font-weight: bold; text-align: center;">
                                <div class="btn_buy_other_sm" onclick="addtobuck('${pageContext.request.contextPath}/good/${good.id}/all',${good.id},${bucketsize})"></div>
                            </td>
                        </tr>
                    </table>
    			</li>
    			</c:forEach>
    			</ul>
