<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
$(document).ready(function() {
	$("img").lazyload({ threshold : 100, effect : "fadeIn", failurelimit : 100 });
	});
</script>

<html>

   <div id = "catcount">
				<h2 class="mgood_title">${category.name}</h2>
				<span class="mgood_span"> ${catgoodscount}</span>
	</div>

<div id = "activeuserfilters">
                 
                 <c:if test = "${!empty paramfilter.params}">
					<c:forEach items="${paramfilter.params}" var="prop" varStatus = "i">
						<li>
						<p>${prop.propname}:</p>
						<c:forEach items="${prop.vals}" var="val" varStatus = "i">
						<c:if test = "${val.ischecked == true}">
						<div class="propval">
						<div name = "${val.id}"><p>${val.value}		
							<img width = "12" style="cursor:pointer;" src="/web-shop/resources/images/ico-add2buydel.gif" onclick="removepropval(${val.id})"></p></div><br>

						</div>
						</c:if>
						</c:forEach> 
						</li>
					</c:forEach> 
					
					</c:if>
                 </div>

<div id = "valfilters">
			<form:form id="filter" action="${pageContext.request.contextPath}/goodfilter" modelAttribute="paramfilter" method="post">
						<c:forEach items="${paramfilter.params}" var="prop" varStatus = "i">
					
						<c:if test = "${prop.valcount>0}">
						<div class="slfbitem">
						<div class="slt_title_new" onclick="show_hide_filter(${prop.propid})"><span class="nt"><p><form:label path = "params[${i.index}].propname">${prop.propname}</form:label></span> <img src="http://www.mobilluck.com.ua/nd/img/filter_up.png" id="img_${prop.propid}"></div>
						<div class="properyblock" id="${prop.propid}">
							<input type = "hidden" name = "params[propname][e]" value =  "${prop.propname}" path = "params[${i.index}].propname"></input>
							<input type = "hidden" name = "params[propid][r]" value =  "${prop.propid}" path = "params[${i.index}].propid"></input>
							
							<c:forEach items="${prop.vals}" var="val" varStatus = "j"><br>
							<input type ="hidden" name = "params[vals][id]" id="${prop.propid}_param${j.index}" path="params[${i.index}].vals[${j.index}].id" value="${val.id}"/>
							<c:choose>
							<c:when test = "${val.ischecked == true}">
							<input type ="checkbox" vid = "${val.id}" name = "params[vals][value]" id="${prop.propid}_param${j.index}" onclick="sh_flt('${prop.propid}_param${j.index}')" path="params[${i.index}].vals[${j.index}].value" value="${val.value}" checked/>
							</c:when>
							<c:otherwise>
							<input type ="checkbox" vid ="${val.id}" name = "params[vals][value]" id="${prop.propid}_param${j.index}" onclick="sh_flt('${prop.propid}_param${j.index}')" path="params[${i.index}].vals[${j.index}].value" value="${val.value}" />
							</c:otherwise>
							</c:choose>
							<p><a role = "button" onclick = "toogleCheckbox()">${val.value} </a> <c:if test = "${val.ischecked == false}"><span>(${val.count})</span></c:if></p>
							</c:forEach>
						</div></div>
									
						<a onclick='return showhidebrand()' id="shbtn" class="showhidebtn"><b>Показать все (${prop.valcount})</b></a>
						</c:if>		
					</c:forEach>
				<!--  	<input id = "l2" type = "button" value="Use Filter" ></input> -->
					</form:form>
</div>	

<div id = "goodlist">
			<div class="cclist">

								<style>
					.ico_zindex
					{
						position: relative;
						float:left; height: 280px;
						margin-bottom: 35px;
					}
					.ico_zindex:hover
					{
						z-index: 99;
					}

					.ccitem2
					{
						height: 280px;
						overflow: hidden;
						position: relative;
					}

					.ccitem2:hover
					{
						margin: -2px;
						height: auto;
						overflow: visible;
						z-index: 99;
						border: 2px solid #FFAE20;
						border-radius: 5px;
						box-shadow: 5px 5px 13px 0px rgba(54, 69, 102, 0.5);
						background-color: white;
					}
				</style>
			
			<div id = "goods">
			<c:if test="${!empty goodList}">
			<c:forEach items="${goodList}" var="good">
						<div class="ico_zindex">
						<div class="ccitem2">
							<div class="ccitem2t"><img src="http://img.mobilluck.com.ua/img/new_ico/cat/4.png" style="position:absolute;right:0;top:0;"/>
								<table>
									<tr>
										<td>
											<a href="${pageContext.request.contextPath}/catalog/${good.page.fullurl}/all" title="${good.name}" target="_blank"><img src="<c:url value="/resources/images${good.thumb}" />"  width="150" height="85.344827586207" alt="Фото ${good.name}"  /></a>
										</td>
									</tr>
								</table>
							</div>
							<div class="cci2info">
								<p class="cci2_mdl"><a href="${pageContext.request.contextPath}/catalog/${good.page.fullurl}/all" title="${good.name}" target="_blank">${good.name}</a></p>
								<table class="w100">
									<tr>
										<td colspan="2" style="height: 18px;">
											<img src="<c:url value="/resources/images/good/raiting${good.round_rating}.png" />" class="float" alt="${average_rating}" />
											<p>&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/catalog/${good.page.fullurl}/all" target="_blank">${good.votes_count} оценок</a></p>
										</td>
									</tr>
									<tr>
									<td>
										<p class="cci2_newprice" style="color: #bd9344">${good.price}</p>
										<p><font color='green'>на складе</font></p>
									</td>
									<td><div class="buy_btn_catalog_new" onclick="addtobuck('${pageContext.request.contextPath}/good/${good.id}/all',${good.id},${bucketsize})" title="Добавить в корзину - купить ${good.name}"></div></td>
                                </tr></table>
								<p class="cci2_add2" id="comprow${good.id}"><a href="#" onclick="addToCompare(${good.id},${good.category.id},'${pageContext.request.contextPath}');" class="mgtl_add2cmp">Добавить к сравнению</a></p>
								<div style="padding: 15px 0 15px 0; clear: left;">
									${good.description}
								</div>
								
							</div>
						</div>
						</div>
			</c:forEach>
			</c:if>	
			</div>		
		</div>
		
		<div class="ccpnav">
		<form id = "pages">
		<span class="arrow">&#8592;</span><a class="a-text a-prev" href="${pageContext.request.contextPath}/category/${category.id}/pagin/${currentpage-1}">Пред.</a>
		<c:forEach begin="1" end="${pagecount+1}" var="i">
		<c:choose>
		<c:when test = "${i==currentpage}">
		<span class="">${i}</span><input type = "hidden" value = "${i}" name = "pagenum" id = "pagenum"/>
		</c:when>
		<c:otherwise>
		<a class="a-text" href="${pageContext.request.contextPath}/category/${category.id}/pagin/${i}">${i}</a>
		</c:otherwise>
		</c:choose>
		</c:forEach>
		<a class="a-text a-next" href="${pageContext.request.contextPath}/category/${category.id}/pagin/${currentpage+1}">След.</a><span class="arrow">&#8594;</span>
		</form>
		</div>
		</div>
</html>




