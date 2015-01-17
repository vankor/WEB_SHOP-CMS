<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<script>
$(document).ready(function(){
	var menu = $("ul.mainmenu2 li.mmd_item");
	var mmps = $(".hpslist .subm_item");
	for (var i=0; i<menu.length; i++){$(menu[i]).attr("id", "topm"+(i+1));};
	for (var i=0; i<mmps.length; i++){$(mmps[i]).attr("id", "subm"+(i+1));};

	menu.hover(
		function(){
			
			var idx = $(this).attr("id").replace("topm", "");
			clearTimeout(mt);
			mt = setTimeout(function(){
				if (mStatus == 'show'){
					menu.each(function(){$(this).removeClass("active");});
					mmps.each(function(){$(this).hide();});
					$("#topm"+idx).addClass("active");
					$("#subm"+idx).fadeIn(500);
				}
			}, 250);
			mStatus = 'show';
		},
		function(){
			var idx = $(this).attr("id").replace("topm", "");
			menu.each(function(){$(this).removeClass("active");});
			mmps.each(function(){$(this).hide();});
			mStatus = 'hide';
		}
	);
	mmps.hover(
		function(){
			var idx = $(this).attr("id").replace("subm", "");
			menu.each(function(){$(this).removeClass("active");});
			$("#topm"+idx).addClass("active");
			$(this).css("display", "block");
			mStatus = 'show';
		},
		function(){
			var idx = $(this).attr("id").replace("subm", "");
			menu.each(function(){$(this).removeClass("active");});
			mmps.each(function(){$(this).hide();});
			mStatus = 'hide';
		}
	);
});
</script>
		<div id="header">
			<div class="hmainmenu">
								<ul class="mainmenu2">
								<c:forEach items="${currentCatList}" var="category">
								<c:if test = "${category.isdeleted !=true && !empty category.page}">
								<c:choose>
								<c:when test="${i.index == 0}">
							<li class="mmd_item first"><table><tr><td><a href="${pageContext.request.contextPath}/catalog/${category.page.fullurl}/pagin/1">${category.name}</a></td></tr></table></li>
								</c:when>
								<c:otherwise>
							<li class="mmd_item"><table><tr><td><a href="${pageContext.request.contextPath}/catalog/${category.page.fullurl}/pagin/1">${category.name}</a></td></tr></table></li>
								</c:otherwise>
								</c:choose>
								</c:if>
								</c:forEach>
				</ul>

			</div>
<div class="hpslist">
	<c:forEach items="${currentCatList}" var="category" varStatus = "k">
	<div class="subm_item submi${k.index} bgleft">
	
	<div class="blk2" style="float: left">
	<ul class="ulmenu" id="ul_${k.index+1}">
	<c:forEach items="${category.subcategs}" var="subcat1" varStatus = "i">
	<c:choose>

	<c:when test="${i.index == 0}">
	<li class="limenu l_left limenu_active " id="li_${k.index+1}_${subcat1.id}" onmouseover="showmenu(${k.index+1}, ${subcat1.id})"><a href="${pageContext.request.contextPath}/catalog/${subcat1.page.fullurl}/pagin/1" class="black_link">${subcat1.name}</a></li>
	</c:when>
	<c:otherwise>
 	<li class="limenu l_left " id="li_${k.index+1}_${subcat1.id}" onmouseover="showmenu(${k.index+1}, ${subcat1.id})"><a href="${pageContext.request.contextPath}/catalog/${subcat1.page.fullurl}/pagin/1" class="black_link">${subcat1.name}</a></li>
	</c:otherwise>
	</c:choose>
	</c:forEach>
	</ul>
	<div style="clear: left;"></div>
	</div>
	
	<div class="blk1" style="float: left" id="d_${k.index+1}">
	<c:forEach items="${category.subcategs}" var="subcat1" varStatus = "i">
	<c:choose>
	<c:when test="${i.index == 0}">
	<div class="popmtbl blk3" id="t_${k.index+1}_${subcat1.id}" style="display: block;"><c:forEach items="${subcat1.subcategs}" var="subcat2" varStatus = "j"><div class="divleft220">${subcat2.name} <br/><c:forEach items="${subcat2.subcategs}" var="subcat3" varStatus = "l"><a href="${pageContext.request.contextPath}/catalog/${category.page.fullurl}/pagin/1">${subcat3.name}</a><br/></c:forEach></div>
	</c:forEach></div>
	</c:when>
	<c:otherwise>
	<div class="popmtbl blk3" id="t_${k.index+1}_${subcat1.id}" style="display: none;"><c:forEach items="${subcat1.subcategs}" var="subcat2" varStatus = "j"><div class="divleft220">${subcat2.name} <br/><c:forEach items="${subcat2.subcategs}" var="subcat3" varStatus = "l"><a href="${pageContext.request.contextPath}/catalog/${category.page.fullurl}/pagin/1">${subcat3.name}</a><br/></c:forEach></div>
	</c:forEach></div>
	</c:otherwise>
	</c:choose>
	</c:forEach>
	</div>
	</div>
	</c:forEach>


<div class="subm_item submi2 bgleft">

	</div>		</div><!-- header -->

					</div>