<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<div style="margin: 0px auto; width: 1200px; ">
<div style="border-radius: 15px; clear: left; float: left; font-size: 18px; font-weight: bold; padding: 5px 10px; background-color: #ffc000; width: 250px; text-align: center;">Новости, статьи, обзоры</div>
<div style="float: left; margin-left: 20px; margin-top: 6px; border-radius: 15px; background-color: #dbedff; padding: 2px 10px;">
<a href="${pageContext.request.contextPath}/newslist/1?type=1" style="font-size: 12px; text-decoration: underline; border: 0px;">Все новости</a>
</div>
<table style="padding-top: 10px; clear: left;">
<tr>
<c:forEach items = "${mainnews}" var = "news" varStatus = "i">
<td width="20%" valign="top">
<table>
<tr>
<td width="50" valign="top">
<img src="<c:url value = "/resources/images${news.thumb}"/>" data-original="<c:url value = "/category/${news.category.id}/news/${news.id}"/>" width="50"/>
</td>
<td>
<a href="<c:url value = "/category/${news.category.id}/news/${news.id}"/>">${news.title}</a>
<br />
<p>${news.promotext}</p>
<span style="color: gray;">${news.creation}</span>
</td>
</tr>
</table>
</td>
<c:if test = "${(i.index+1) % 5==0 && i.index!=0}">
<tr><tr>
</c:if>

</c:forEach>

</tr>
</table>
<div style="clear: left;"></div>
</div> 