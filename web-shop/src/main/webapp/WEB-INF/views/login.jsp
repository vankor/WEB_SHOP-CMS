<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Авторизация</title>

<script>
var url = "<c:out value="${pageContext.request.contextPath}"/>";
</script>
</head>
<body>

<c:if test="${section == 'login'}">

                    <form id="login-form" method="POST" action="${pageContext.request.contextPath}/j_spring_security_check">
                    <table>
                        <tbody>
                            <tr>
                                <td class="left-side">
                                    <p class="titleSec">
                                        <span>

                                            Войти быстро, используя

                                        </span>
                                    </p>
                                    <div id="vkontakte_login" onclick="" style="cursor: pointer;">
                                        <img src="http://www.mobilluck.com.ua/img/popup/soc_vk.png"></img>
                                    </div>
                                    <div id="facebook_login" onclick="" style="cursor: pointer;">
                                        <img src="http://www.mobilluck.com.ua/img/popup/soc_fb.png"></img>
                                    </div>
                                </td>
                                <td class="right-side">
                                    <input type="hidden" value="orderlogin" name="action"></input>
                                    <input type="hidden" value="" name="refurlhndl"></input>
                                    <input type="hidden" value="postuser" name="mode"></input>
                                    <div>
                                        <label class="titleSec" for="dlgf_mail">

                                            Электронная почта: 

                                            <span class="necessary">

                                                *

                                            </span>
                                        </label>
                                        <p>
                                            <input id="dlgf_mail" class="dlgftext" type="text" name="j_username"></input>
                                        </p>
                                    </div>
                                    <div>
                                        <label class="titleSec" for="dlgf_mail">

                                            Пароль: 

                                            <span class="necessary">

                                                *

                                            </span>
                                        </label>
                                        <p>
                                            <input id="dlgf_psw" class="dlgftext" type="password" name="j_password"></input>
                                        </p>
                                        <p class="restore_pass">
                                            <a id="restorepassbtn2" class="dlglink" href="#">
                                                <span>

                                                    Напомнить пароль

                                                </span>
                                            </a>
                                        </p>
                                    </div>
     <!--                              <label id="login_button" for="login">  -->  
                                        <div>
                                                   <input value = "Войти" id="login" type="submit" name="dlgf_submit"></input>
                                        </div>
               <!--                     </label>   --> 
                                    <p class="or-registrate">

                                        или 

                                        <a href="${pageContext.request.contextPath}/reg">

                                            зарегистрироваться

                                        </a>
                                    </p>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <p class="license_agreement">

                                        Регистрируясь, вы принимаете условия 

                                        <a rel="nofollow" href="http://www.mobilluck.com.ua/info.php?page=using">

                                            соглашения.

                                        </a>
                                    </p>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
  </c:if> 
  
  
  <c:if test="${section == 'adminlogin'}">
<link rel="stylesheet" href="<c:url value="/resources/loginstyles.css"/>"/>
                    
                    <div id="container">
		<form id="login-form" method="POST" action="${pageContext.request.contextPath}/j_spring_security_check">
			<label for="name">Логин:</label>
			<input id="dlgf_mail" class="dlgftext" type="name" name="j_username"></input>
			<label for="username">Пароль:</label>
			<p><a href="#">Забыли пароль?</a></p>
			<input id="dlgf_psw" class="dlgftext" type="password" name="j_password"></input>
			<div id="lower">
				<input type="checkbox"><label class="check" for="checkbox">Запомнить меня</label>
				<input type="submit" value="Войти">
			</div>
		</form>
	</div>
                
  </c:if> 
  
  
  
  <c:if test="${section == 'adminerror'}">
  <link rel="stylesheet" href="<c:url value="/resources/loginstyles.css"/>"/>
                    
                    <div id="container">
                    <div style = "color:red; margin:10px 10px;">Неверный логин или пароль!</div>
		<form id="login-form" method="POST" action="${pageContext.request.contextPath}/j_spring_security_check">
			<label for="name">Логин:</label>
			<input id="dlgf_mail" class="dlgftext" type="name" name="j_username"></input>
			<label for="username">Пароль:</label>
			<p><a href="#">Забыли пароль?</a></p>
			<input id="dlgf_psw" class="dlgftext" type="password" name="j_password"></input>
			<div id="lower">
				<input type="checkbox"><label class="check" for="checkbox">Запомнить меня</label>
				<input type="submit" value="Войти">
			</div>
		</form>
	</div>
  </c:if>
  


  
  
  
  
  
  