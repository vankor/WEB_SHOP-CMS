      <%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
  
    <div style="clear: both;float: left; width: 700px;">
                    <div style="width: 700px; float: left; margin-top: 50px; margin-bottom: 20px;" id="responce">
                        <div style="font-family: sans-serif; font-size: 24px; color: #2d2d2d; margin-bottom: 20px;">
                        Похожие товары
                        </div>
                <div style="width: 100%;">
                <c:forEach items = "${othergoods}" var = "othergood">
                    <div style="height: 260px; text-align: center; width: 180px; float: left; margin-bottom: 40px; margin-right: 40px;">
        				<div class="gcac_image" style="text-align: center;">
        					<table cellpadding="0" cellspacing="0" align="center" width="100%" border="0">
        						<tbody><tr>
        							<td align="center">
        								<a href="${pageContext.request.contextPath}/good/${othergood.id}/all"><img src="<c:url value="/resources/images${othergood.thumb}" />" width="80" alt="${othergood.name}"></a>
        							</td>
        						</tr>
        					</tbody></table>
        				</div>
                        <div style="height: 47px; overflow: hidden; text-align: left; font-size: 12px; margin-top: 5px;"><a href="${pageContext.request.contextPath}/good/${othergood.id}/all">${othergood.name}</a></div>
                        <div style="text-align: left; font-size: 12px; margin-top: 0px; color: #939393;">
                            <img src="<c:url value = "/resources/images/good/raiting${othergood.round_rating}.png"/>" style="position: relative; top: 4px;"></img> ${othergood.votes_count} оценок
                        </div>
        				<table border="0">
                            <tbody><tr>
                                <td width="50%" valign="middle" style="font-size: 16px; font-weight: bold; text-align: left;">
                                    ${othergood.price}
                                </td>
                                <td valign="top" style="font-size: 16px; font-weight: bold; text-align: center;">
                                    <div class="btn_buy_other_sm" onclick="addtobuck('${pageContext.request.contextPath}/good/${othergood.id}/all',${othergood.id},${bucketsize})"></div>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" class="comprow" id="comprow${othergood.id}" align="left">
                                    <a id="cmprlnk${othergood.id}" href="#" onclick="return addCompare(${othergood.id},${good.category.id},'${pageContext.request.contextPath}')" class="mgtl_add2cmp"><span>Добавить к сравнению</span></a>
                                </td>
                            </tr>
                        </tbody></table>
        			</div>
                </c:forEach>
                </div>                    </div>
                <div style="margin-top: 30px; margin-bottom: 30px;">
                    <img src="http://www.mobilluck.com.ua/img/product/left_arrow.png"> <a href="http://www.mobilluck.com.ua/katalog/noutbuk/acer/acer-Aspire_V5-123-12104G50nss__NX_MFREU_003__Silver-260940.html" style="font-size: 16px;">Вернуться к подробной информации о Ноутбук Acer Aspire V5-123-12104G50nss (NX.MFREU.003) Silver</a>
                </div>
                </div>