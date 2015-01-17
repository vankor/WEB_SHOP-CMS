         <%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
     
     
     <div style="float:left; width: 256px; margin-bottom: 15px;">
        <div style="background-image: url('http://www.mobilluck.com.ua/img/product/rb_top.png'); height: 10px;"></div>
        <div style="border-left: 2px #d68b03 solid; border-right: 2px #d68b03 solid; padding: 0px 15px 0px 15px;">
            <p style="font-size: 16px; font-weight: bold; margin-bottom: 15px;">
            <img src="http://www.mobilluck.com.ua/img/product/rss.png" align="left" style="padding-right: 5px; margin-top: 5px;"/>
            Узнавайте о новых<br/>акциях и распродажах
            </p>
            Электронная почта
            <br />
            <input type="text" id="i_fu_email" name="i_fu_email" style="height: 28px; width: 200px; margin: 3px 0px 3px 0px;"/>
            <p style="color: #6c6c6c; margin-bottom: 3px;"></p>
            <div class="subscribe" style="clear: left;" onclick="return podpiska(this,'i_fu_email')"></div>
        </div>
        <div style="background-image: url('http://www.mobilluck.com.ua/img/product/rb_bottom.png'); height: 10px;"></div>
    </div>