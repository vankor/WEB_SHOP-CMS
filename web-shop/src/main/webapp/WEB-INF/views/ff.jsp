<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Fuck the World</title>
</head>
<body>
<c:if test="${!empty goodList}">
			<c:forEach items="${goodList}" var="good">
						<div class="ico_zindex">
						<div class="ccitem2">
							<div class="ccitem2t"><img src="http://img.mobilluck.com.ua/img/new_ico/cat/4.png" style="position:absolute;right:0;top:0;"/>
								<table>
									<tr>
										<td>
											<a href="${pageContext.request.contextPath}/good/${good.id}" title="${good.name}" target="_blank"><img src="http://www.mobilluck.com.ua/img/spacer.gif" data-original="http://pics.mobilluck.com.ua/thumb/game-stations/genius%20/Genius_Heeha_100X_89477_140281.jpg" width="150" height="85.344827586207" alt="Фото Игровая приставка Genius Heeha 100X"  /></a>
										</td>
									</tr>
								</table>
							</div>
							<div class="cci2info">
								<p class="cci2_mdl"><a href="${pageContext.request.contextPath}/good/${good.id}" title="${good.name}" target="_blank">${good.name}</a></p>
								<table class="w100">
									<tr>
										<td colspan="2" style="height: 18px;">
											<img src="http://img.mobilluck.com.ua/img/product/raiting4.png" class="float" alt="4" />
											<p>&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/good/${good.id}" target="_blank">17 оценок</a></p>
										</td>
									</tr>
									<tr>
									<td>
										<p class="cci2_newprice" style="color: #bd9344">${good.price}</p>
										<p><font color='green'>на складе</font></p>
									</td>
									<td><div class="buy_btn_catalog_new" onclick="return buy_popup(89477, 'cihds58cg5492csdj8kmd8a672', this, '0')" title="Добавить в корзину - купить ${good.name}"></div></td>
                                </tr></table>
								<p class="cci2_add2" id="comprow89477"><a href="#" onclick="return addCompare(89477,15)" class="mgtl_add2cmp"><span>Добавить к сравнению</span></a></p>
								<div style="padding: 15px 0 15px 0; clear: left;">
									${good.description}
								</div>
								
							</div>
						</div>
						</div>
			</c:forEach>
			</c:if>	
</body>
</html>