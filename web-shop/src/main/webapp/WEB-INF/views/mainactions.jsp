<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<div style="margin: 0px auto; width: 1250px; ">
<div style="float: left; border-radius: 15px; clear: left; font-size: 18px; font-weight: bold; padding: 5px 10px; background-color: #ffc000; width: 150px; text-align: center;">Акции недели</div>
<div style="float: left; margin-left: 20px; margin-top: 6px; border-radius: 15px; background-color: #dbedff; padding: 2px 10px;">
<a href="${pageContext.request.contextPath}/actions/1" style="font-size: 12px; text-decoration: underline; border: 0px;">Все акции</a>
</div>

<div style="clear: left; padding-top: 10px;">
<c:forEach items = "${actions}" var = "action" varStatus = "i">
<div style="float: left; width: 300px; padding: 5px; font-size: 18px; line-height: 20px;">
<div style="height: 40px; overflow: hidden;">
<a href="${pageContext.request.contextPath}/action/${action.id}" title="${action.name}" style="color: black;">${action.name}</a>
</div>
<a href="${pageContext.request.contextPath}/action/${action.id}" title="${action.name}">
<img src="<c:url value="/resources/images${action.thumb}"/>" data-original="<c:url value="/resources/images${action.thumb}"/>" width="100%" border="0" alt="${action.name}"/>
</a>
</div>
</c:forEach>
</div>
<div style="clear: left;"></div>
</div>