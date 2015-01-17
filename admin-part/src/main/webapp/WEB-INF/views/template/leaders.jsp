<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script>

</script>
</head>
<body>


	<div class="gstopic srliders">
		<p class="gst_title">Лидеры продаж</p>
		<div class="gstlist">
		<c:forEach items = "${leaders}" var="good" varStatus = "j">
		<div class="gstlitem">
				<div class="gstli_image">
					<table cellpadding="0" cellspacing="0" class="w100">
						<tr>
							<td>
								<a href="${pageContext.request.contextPath}/catalog/${good.page.fullurl}/all" rel="nofollow"><img src="<c:url value="/resources/images${good.thumb}" />" width="60" height="100" alt="${good.name}" /></a>
							</td>
						</tr>
					</table>
				</div>
				<div class="gstlitext">
					<p class="gstli_model"><a href="${pageContext.request.contextPath}/catalog/${good.page.fullurl}/all" rel="nofollow">${good.name}</a></p>
					<p class="gstli_price">${good.price} грн.</p>
				</div>
			</div>
		</c:forEach>	
			</div>
		<div class="w100 float">
					</div>
		<div class="both"></div>
	</div>

</body>