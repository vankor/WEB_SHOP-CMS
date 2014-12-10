<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1251" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>${currpage.title}</title>

<jsp:include page="/WEB-INF/views/head.jsp" />
<script type="text/javascript">

var url = "<c:out value="${pageContext.request.contextPath}"/>";

</script>




<script type="text/javascript" charset="utf-8">
$(function() {
$("img").lazyload({ threshold : 100, effect : "fadeIn", failurelimit : 100 });
});
</script>

</head>
<body >

<div style="fixup" id="fixup" name="fixup"><div id="uplink">Наверх</div></div>

<div id="page">
<jsp:include page="/WEB-INF/views/header.jsp" />
<div id="wrapper">
<jsp:include page="/WEB-INF/views/mainmenu.jsp" />
</div>
<div style="padding: 0px; width: 100%;">
<div id = "wrapper">
<div id = "main">

<div id="sidebarl">
	<br>
	<c:forEach items = "${allpagegroups}" var = "pggroup" varStatus = "i">
	<div class="sltcategory2">
		<p class="sltc2title">${pggroup.name}</p>
		<ul class="sltc2list">
			<c:forEach items = "${pggroup.pages}" var = "page" varStatus = "i">
            <li><a href="${pageContext.request.contextPath}/info/${page.fullurl}">${page.name}</a></li>			
			</c:forEach>     
		</ul>
	</div>
	</c:forEach>

</div>

<div id="content2">
	<div class="ccatalog">
		<div class="cbreadcrumbs">
			<a href="${pageContext.request.contextPath}">Главная</a> / <a href="${pageContext.request.contextPath}${currpage.fullurl}">${currpage.name}</a>
				
		</div>
        
        <table>
            <tbody><tr>
                <td class="cait_links">
                    <br>
                    Мне нравится
                </td>
                <td class="cait_links">
				    <noindex>
      
					</noindex>
				</td>
            </tr>
        </tbody>
        </table>
        
		<div class="carticle">
			<h1 class="mgood_title">${currpage.h1}</h1>
			<div class="cacontent">
			<div align="justify">
			${currpage.html}
</div>
</div>
		

		</div>
	</div>
</div>


</div>

<div id="sidebarr">
<jsp:include page="/WEB-INF/views/sidebar.jsp" />
</div>


<div class="both"></div>

</div></div></div>	

<jsp:include page="/WEB-INF/views/footer.jsp" />

    

	<div id="popupbg"></div>
	<div id="dialog_wrapper">
		<div id="dialog_outer">
			<div id="dialog_inner">
				<a id="dialog_close" href="#">Закрыть</a>
				<div id="dialog_title">Войти в интернет-магазин</div>
				<div id="dialog_content"></div>
				<!--<div class="dinner_t"></div>
				<div class="dinner_b"></div>
				<div class="dinner_l"></div>
				<div class="dinner_r"></div>
				<div class="dinner_tl"></div>
				<div class="dinner_tr"></div>
				<div class="dinner_bl"></div>
				<div class="dinner_br"></div>
				-->
			</div>
		</div>
	</div>


		
<div id="bucket" style="display: none;"></div>

</div>

</body>






