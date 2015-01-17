<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<c:if test = "${section=='prodgoods'}">
	<c:if test = "${!empty prodgoods}">
	
		<c:forEach items = "${prodgoods}" var = "pagegood">
						
						<li><p><a href="${pageContext.request.contextPath}/catalog/${pagegood.page.fullurl}/all">${pagegood.name}</a></p></li>
						</c:forEach>
	</c:if>
</c:if>
<c:if test = "${section!='prodgoods'}">
<script type="text/javascript">
			$(document).ready(function(){
			$(".cat_list_title, .cat_list img").bind("click", function(){
				
					var thislist = $(this).parent().find(".cat_list_list");
					var url = "<c:out value="${pageContext.request.contextPath}"/>" +"/catalog/"+ "<c:out value="${good.category.page.fullurl}"/>"+"/all";
					$(".cat_list img").attr({src:"http://img.mobilluck.com.ua/img/prdctcart/rght.png"});
					var that = $(this).parent();
					if($(thislist).eq(0).is(":visible")==true)
					{
						$(thislist).slideUp("cat_list_list");
					}
					else
					{
						$(".cat_list_list").slideUp("slow");
						var make_id=$(that).attr("sid");
						
						if(make_id!=0)
						{
							$(that).attr("sid", "0");
							$(that).find(".cat_list_list").html("<li><img style=\"padding: 0 0 0 14px;\" src=\"http://img.mobilluck.com.ua/img/load.gif\"></li>")
						
							$.ajax({
								
								type: "POST",
								url: url+"/pagin/1/producergoods",
								data: {"prodid":make_id},
								dataType: "text",
								success: function(data){
								
									$(that).find(".cat_list_list").html(data);
								},      
							    error : function(xhr, status, error){
						            alert("Error!" + xhr.status);
						        }
						   });
						}
						$(this).parent().find(".cat_list_list").slideDown("slow");
						$(this).parent().children().eq(0).attr({src:"http://img.mobilluck.com.ua/img/prdctcart/dwn.png"})
					}

				});
			});
			</script>
<div class="left_sb_new" style="width: 183px;">
		<div class="cat_title">
			${good.category.name}		
		</div>
			<ul class="cat_list" style="width: 145px;">
						<c:forEach items = "${prodlist}" var = "entry">
										<li sid="${entry.key.id}">
								<img src="http://img.mobilluck.com.ua/img/prdctcart/rght.png"><span class="cat_list_title">${entry.key.name} (${entry.value})</span>
								<ul class="cat_list_list" style="display:none">
								</ul>
							</li>
						</c:forEach>
			</ul>
			
		</div>
</c:if>