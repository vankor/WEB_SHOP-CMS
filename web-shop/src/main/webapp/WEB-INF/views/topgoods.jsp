<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
 <c:if test = "${section != 'topgoods'}">
 <script>
 var tekid = 0;

 var tccount = "<c:out value="${topcatcount}"/>";
var topgoodcount;
 function loaditems(sid, cmnt)
 {
     
	 $("#central_div_"+cmnt).html('<table width="700" height="550"><tr><td align="center" valign="middle"><img src="http://www.mobilluck.com.ua/img/load.gif" align="center"></td></tr></table>');
//     alert(url+"/loadtopgoods");
//     alert(sid);
     $.ajax({
         type: "POST",
         url: url+"/loadtopgoods",
         data: {"cat_id":sid},
         dataType: "text",
         success: function(data)
         {
  //       	alert(data);
             $("#central_div_"+cmnt).html(data);
             topgoodcount = topgoodcount;
         }
     });
 //    alert(topgoodcount)
     load_array[cmnt] = 1;
 }
 $(document).ready(function(){
            var nxt = setTimeout('slect(tekid);', 200);
        });
 function slect(id)
 {	
//	 alert(id);
     if(typeof(nxt)!="undefined")
     {
         clearTimeout(nxt);
     }
     if(load_array[id] != 1)
     {
         var sid = $("#nav-fragment-"+id).attr("sid");
         loaditems(sid, id);
     }
     $("#sat_cat_list").children(['ul']).children(['li']).removeClass('cursat');
		$("#nav-fragment-"+id).addClass('cursat');
     $("#central_div_"+tekid).hide();
     $("#central_div_"+id).fadeIn(800);
     tekid = id;
     ntekid = tekid;
     if(tekid==(tccount-1))
         ntekid = 0;
     else
         ntekid++;
     nxt = setTimeout('slect(ntekid);', 15000);
		return false;
 }
 
</script>
<div class="sat_sidebar">
			<div class="sat_top_title">
				<span>Каталог скидок</span>
			</div>
			<div id="sat_cat_list">
				<ul>
					<c:forEach items = "${topcategories}" var = "category" varStatus = "i">
						<li class="sat_cat" sid="${category.id}" onclick="slect(${i.index})" id="nav-fragment-${i.index}"><a>${category.name}</a></li>
					<li class="sat_cat_div"></li>
					</c:forEach>
					
				</ul>
			</div>
		</div>

<div id = "topcenter" style="float: left;width: 990px;height: 670px; overflow:hidden; overflow-x: hidden; ">
		
			<c:forEach items = "${topcategories}" var = "category" varStatus = "i">
			<div class="rightlist" id="central_div_${i.index}" style="display: none;">

					</div>
					</c:forEach>


		</div>
</c:if>
<c:if test = "${section == 'topgoods'}">
	<script>
	var topgoodcount = "<c:out value = "${goodcount}"/>";
	if(topgoodcount>8){
		$("#topcenter").css("overflow-y","scroll");
	}
	else{
		$("#topcenter").css("overflow-y","hidden");
	}
	</script>
	<ul>
			<c:forEach items = "${category.topgoods}" var = "good" varStatus = "j">
			<li>
						<div class="s_item">
							<div class="topskid">
								<div class="toppric">
									<span>-${good.discount} грн</span>
								</div>
							</div>
							<div class="sat_img">
								<a href="${pageContext.request.contextPath}/good/${good.id}/all"><img src="<c:url value="/resources/images${good.thumb}" />" width="89" style="padding-top: 2px"></a>
							</div>
							<div class="s_title" style="height: 37px; margin-bottom: 5px;"><a href="${pageContext.request.contextPath}/good/${good.id}/all">${good.name}</a></div>
							<div class="s_stars">
								<div id="raiting_hover2" style="height: 16px;width: 83px;background: url(<c:url value="/resources/images/good/raiting${good.round_rating}.png" />) 0 -18px; float:left;"></div>
								<p style="padding:0; margin:0;float:left">&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/good/${good.id}/voices" target="_blank">${good.votes_count} оценок</a></p>
							</div>
							<div class="s_bottom">
								<table>
									<tbody>
									<tr>
										<td class="s_price">
											${good.price} грн
										</td>
										<td rowspan="2" class="s_button">
											<div class="s_but" onclick=" $(window).scrollTop(); addtobuck('${pageContext.request.contextPath}/good/${good.id}/all', ${good.id}, ${bucketsize})"></div>
										</td>
									</tr>
									<tr>
										<td class="s_old_price">
											<span>${good.oldprice} грн</span>
										</td>
									</tr>
								</tbody>
								</table>
							</div>
						</div>
					</li>
					</c:forEach>
					</ul>
</c:if>