<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<div id = "instruments">
<div id = "list2">
<ul>
<c:if test = "${!empty filter.activefiltercols}">
<li>Активные фильтры: <c:forEach items = "${filter.activefiltercols}" var = "filterentry">

<div class="propval">
		<div name = "${filterentry.key}"><p>${filterentry.value}		
		<img style="cursor:pointer;" src="/web-shop/resources/images/ico-add2buydel.gif" onclick="deletefilter('${filterentry.key}','filter')"></p></div><br>

</div>
</c:forEach> 
</li>
</c:if>
<c:if test = "${!empty filter.sortprops}">
<li>Сортировка по: 
<c:forEach items = "${filter.sortprops}" var = "sortrentry"><span>

<div class="propval">
		<div name = "${sortrentry.key}"><p>${sortrentry.value}		
		<img style="cursor:pointer;" src="/web-shop/resources/images/ico-add2buydel.gif" onclick="deletefilter('${sortrentry.key}', 'sort')"></p></div><br>

</div>

</span>
</c:forEach> 
</li>
</c:if>
</ul>
<div><ul id = "selectcount"><ul></div>
</div>
	<input id = "pgnum" type = "hidden" value = "${pgnum}"/>
	<input id = "currurl" name = "currurl" type = "hidden" value = "${requestScope['javax.servlet.forward.servlet_path']}"/>
	
	<c:if test = "${adminclassname ==  'GoodItem'}">
	<div style = "padding-left:20%; width:80%;" id = "fixedbuttons"><ul ><li style = "display: inline;"><button id = 'create' onclick = 'load_goodchoise(this, "${pageContext.request.contextPath}/choiseCategory", "catfull", "choisecategory")'>Создать товар</button></li><li style = "display: inline;"><button id = 'create' onclick = "location.href = '${pageContext.request.contextPath}/admin/admingoodstates';">Вести состояния товара</button></li><li id = "menucontainer"></li></ul></div>
	</c:if>
	
	<c:if test = "${adminclassname ==  'Category'}">
	<div style = "padding-left:20%; width:80%;" id = "fixedbuttons"><ul ><li style = "display: inline;"><button id = 'create' onclick = "location.href = '${pageContext.request.contextPath}/admin/add/addCateg';">Создать категорию</button></li><li id = "menucontainer"></li></ul></div>
	</c:if>
	
	<c:if test = "${adminclassname ==  'User'}">
	<div style = "padding-left:20%; width:80%;" id = "fixedbuttons"><ul ><li style = "display: inline;"><button  onclick = "location.href = '${pageContext.request.contextPath}/admin/adminroles/pagin/1';">Вести данные ролей</a></li><li id = "menucontainer"></li></ul></div>
	</c:if>
	
	<c:if test = "${adminclassname ==  'News'}">
	<div style = "padding-left:20%; width:80%;" id = "fixedbuttons"><ul ><li style = "display: inline;"><button id = 'create' onclick = "location.href = '${pageContext.request.contextPath}/admin/admincontent/addnews';">Добавить новость</button></li><li id = "menucontainer"></li></ul></div>
	</c:if>
	
	<c:if test = "${adminclassname ==  'Action'}">
	<div style = "padding-left:20%; width:80%;" id = "fixedbuttons"><ul ><li style = "display: inline;"><button  onclick = "location.href = '${pageContext.request.contextPath}/admin/add/addaction';">Добавить акцию</a></li><li id = "menucontainer"></li></ul></div>
	</c:if>
	
	<c:if test = "${adminclassname ==  'Role'}">
	<div style = "padding-left:20%; width:80%;" id = "fixedbuttons"><ul ><li style = "display: inline;"><button  onclick = "location.href = '${pageContext.request.contextPath}/admin/add/createrole';">Добавить роль</a></li><li id = "menucontainer"></li></ul></div>
	</c:if>
	
	<c:if test = "${adminclassname ==  'DeliveryType'}">
	<div style = "padding-left:20%; width:80%;" id = "fixedbuttons"><ul ><li style = "display: inline;"><button  onclick = "location.href = '${pageContext.request.contextPath}/admin/add/adddeltype';">Добавить вид доставки</a></li><li id = "menucontainer"></li></ul></div>
	</c:if>
	
	<c:if test = "${adminclassname ==  'PayType'}">
	<div style = "padding-left:20%; width:80%;" id = "fixedbuttons"><ul ><li style = "display: inline;"><button  onclick = "location.href = '${pageContext.request.contextPath}/admin/add/addpaytype';">Добавить вид оплаты</a></li><li id = "menucontainer"></li></ul></div>
	</c:if>
	
	<c:if test = "${adminclassname ==  'Order'}">
	<div style = "padding-left:20%; width:80%;" id = "fixedbuttons"><ul ><li style = "display: inline;"><button  onclick = "location.href = '${pageContext.request.contextPath}/admin/adminorderrows/pagin/1';">Отдельные позиции заказов</a></li><li id = "menucontainer"></li></ul></div>
	</c:if>
	
	<c:if test = "${adminclassname ==  'Comment'}">
	<div style = "padding-left:20%; width:80%;" id = "fixedbuttons"><ul ><li id = "menucontainer"></li></ul></div>
	</c:if>
	
	
	<c:if test = "${adminclassname ==  'Producer'}">
	<div style = "padding-left:20%; width:80%;" id = "fixedbuttons"><ul ><li style = "display: inline;"><button  onclick = 'load_goodchoise(this, "${pageContext.request.contextPath}/choiseCategory", "catproducer", "choisecategory")'>Добавить производителя</a></li><li id = "menucontainer"></li></ul></div>
	</c:if>
	
	<c:if test = "${adminclassname ==  'PageGroup'}">
	<div style = "padding-left:20%; width:80%;" id = "fixedbuttons"><ul ><li style = "display: inline;"><button  onclick = "location.href = '${pageContext.request.contextPath}/admin/admincontent/addPagegroup';">Добавить группу страниц</a></li><li id = "menucontainer"></li></ul></div>
	</c:if>
	
	<c:if test = "${adminclassname ==  'Guarantie'}">
	<div style = "padding-left:20%; width:80%;" id = "fixedbuttons"><ul ><li style = "display: inline;"><button  onclick = "location.href = '${pageContext.request.contextPath}/admin/admincontent/addGuarantie';">Добавить вид гарантии</a></li><li id = "menucontainer"></li></ul></div>
	</c:if>
	
	<c:if test = "${adminclassname ==  'GoodStatus'}">
	<div style = "padding-left:20%; width:80%;" id = "fixedbuttons"><ul ><li style = "display: inline;"><button  onclick = "location.href = '${pageContext.request.contextPath}/admin/admincontent/addGoodstatus';">Добавить статус товара</a></li><li id = "menucontainer"></li></ul></div>
	</c:if>

	<c:if test = "${adminclassname ==  'Page'}">
	<div style = "padding-left:20%; width:80%;" id = "fixedbuttons"><ul ><li style = "display: inline;"><button  onclick = "location.href = '${pageContext.request.contextPath}/admin/admincontent/addInfopage';">Добавить инфо-страницу</a></li><li id = "menucontainer"></li></ul></div>
	</c:if>
	
	<c:if test = "${adminclassname ==  'Configuration'}">
	<div style = "padding-left:20%; width:80%;" id = "fixedbuttons"><ul ><li style = "display: inline;"><button  onclick = "location.href = '${pageContext.request.contextPath}/admin/admincontent/addConfiguration';">Добавить новый шаблон конфигурации</a></li><li id = "menucontainer"></li></ul></div>
	</c:if>
	
	<c:if test = "${adminclassname ==  'NewsType'}">
	<div style = "padding-left:20%; width:80%;" id = "fixedbuttons"><ul ><li style = "display: inline;"><button  onclick = "location.href = '${pageContext.request.contextPath}/admin/admincontent/addNewstype';">Добавить тип новостей</a></li><li id = "menucontainer"></li></ul></div>
	</c:if>

	<div class = "gridinstruments">
	<input type = "checkbox" id = "moveactive"/> Перемещение столбцов
	<button style = "display:none;" id = "saveroworder">Сохранить положение столбцов</button>
	<button value = "Перемещение столбцов" id = "managecols"> Управление отображением</button>
	<button  id = "saveformatwindow">Пользовательские форматы отображения</button>
	</div>
	
	<div class="ccpnav">
		<form id = "pages">
		<span class="arrow">&#8592;</span><a class="a-text a-prev" href="${pageContext.request.contextPath}${entityurl}${pgnum-1}">Пред.</a>
		<c:forEach begin="1" end="${pagecount+1}" var="i">
		<c:choose>
		<c:when test = "${i==pgnum}">
		<span class="">${i}</span><input type = "hidden" value = "${i}" name = "pagenum" id = "pagenum"/>
		</c:when>
		<c:otherwise>
		<a class="a-text" href="${pageContext.request.contextPath}${entityurl}${i}">${i}</a>
		</c:otherwise>
		</c:choose>
		</c:forEach>
		<a class="a-text a-next" href="${pageContext.request.contextPath}${entityurl}${pgnum+1}">След.</a><span class="arrow">&#8594;</span>
		</form>
</div>

</div>