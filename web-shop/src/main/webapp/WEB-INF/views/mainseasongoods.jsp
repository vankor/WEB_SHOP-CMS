<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<div style="margin: 0px auto; width: 960px;">
<div style="border-radius: 15px; clear: left; float: left; font-size: 18px; font-weight: bold; padding: 5px 10px; background-color: #ffc000; width: 266px; text-align: center;">Товары сезона</div>
<div style="float: left; margin-left: 20px; margin-top: 6px; border-radius: 15px; background-color: #dbedff; padding: 2px 10px;">
<a href="${pageContext.request.contextPath}/seasonsgoods" style="font-size: 12px; text-decoration: underline; border: 0px;">Все товары</a>
</div><div style="clear: left; margin-top: 10px;">
    <div style="width: 1000px; margin-top: 0px; padding-top: 10px;"><div class="gcaccesories" style="margin-top: 0px;">
    			<div class="gcacontent" style="padding-top: 0px;">
    				<ul id="gcacarousel" class="jcarousel-skin-gcatango5">
    				<c:forEach items = "${seasongoods}" var = "good" varStatus = "i">
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
    			</div></div>
    </div></div>
<div style="clear: left;"></div>
</div>