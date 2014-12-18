       <%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
   
        <div style="clear: both; float: left; width: 700px;">
                
        <div style="width: 700px; float: left; margin-top: 50px; margin-bottom: 20px; background-color: #fff;" id="responce">
            <div style="font-family: sans-serif; font-size: 24px; color: #2d2d2d;">
                Отзывы о ${good.name }
            </div>
            <div style="margin-bottom: 70px; margin-top: 15px;" id="responce_block">            
 <table width="500" cellspacing="5" border="0" align="left" style="font-size: 14px; margin-bottom: 20px;">
    <input id="id" type="hidden" value="271436" name="id"></input>
    <input type="hidden" value="chws9sg196" name="frsc"></input>
    <tbody>
           <tr>
        <c:if test="${empty comm.user}">
            <td>
                                        Ваше имя

                <br></br>
                <input id="name" type="text" name="name" style="width: 200px; height: 20px;"></input>
            </td>
         </c:if>   
            <td>
                                        
                                       <div id="raiting_star">
                                    <div id="raiting">
                                        <div id="raiting_blank"></div> <!--блок пустых звезд-->
                                        <div id="raiting_hover" style="display: none; width: 32px;"></div> <!--блок  звезд при наведении мышью-->
                                        <div id="raiting_votes" style="display: block;"></div> <!--блок с итогами голосов -->
                                    </div>
                                    <div id="raiting_info">
                                        <img src="http://www.mobilluck.com.ua/img/load.gif" width="15">
                                        <span id="raiting_infot">Ваша оценка </span>
                                    </div>
                                </div>

                <br></br>
                <input id="ocenka" type="hidden" name="ocenka" value="5"></input>
                <div id="raiting_star2">
                    <div id="raiting2"></div>
                </div>
                <style>
                                #raiting2 {position:relative; height:14px; cursor:pointer; width:82px; float:left; overflow: hidden;} /* Блок рейтинга*/
                                #raiting_blank2, #raiting_votes2, #raiting_hover2 {height:14px; position:absolute;}
                                #raiting_blank2 { background:url(http://www.mobilluck.com.ua/img/product/raiting.png); width:82px;} /* "Чистые" звездочки */
                                #raiting_votes2 {background:url(http://www.mobilluck.com.ua/img/product/raiting.png) 0 -15px;} /*  Закрашенные звездочки */
                                #raiting_hover2 {background:url(http://www.mobilluck.com.ua/img/product/raiting.png) 0 -15px; display:none}  /*  звездочки при голосовании */
                                #raiting_star2 {margin-top: 5px;}

                </style>
            </td>
        </tr>
  <!--        <tr>
            <td>
                                        Ваш Email

                <br></br>
                <input id="email" type="text" value="" name="email" style="width: 200px; height: 20px;"></input>
            </td>
            <td valign="bottom" style="font-size: 10px;">
                <input type="checkbox" checked="" value="1" name="podpiska"></input>

                 - получать уведомления о новых комментариях

            </td>
        </tr>
        -->
        <tr>
            <td colspan="2">
                                        Комментарий

                <br></br>
                <textarea id="text" style="width: 400px; height: 60px;" name="text"></textarea>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div class="resp_plus" style="padding-bottom: 0px;">

                    Плюсы

                </div>
                <textarea id="plus" style="width: 400px; height: 60px;" name="plus"></textarea>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div class="resp_minus">

                    Минусы

                </div>
                <textarea id="minus" style="width: 400px; height: 60px;" name="minus"></textarea>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div id="addresponce" class="responce" onclick = "addcomment(-1,${goodid});return false"></div>
                <a class="cancelanswer" style="margin-left: 20px; margin-top: 10px;" href="javascript:void()">

                    отменить

                </a>
            </td>
        </tr>
        <tr>
            <td colspan="2">
            </td>
        </tr>
    </tbody>

</table>

            
            </div>
            <div id="resp_error" style="clear: left; width: 650px;"></div>
            <div id="commsection" style="margin-bottom: 20px; border: 0px solid red;">
                <div>
                      <c:forEach items="${comList}" var="comm">
					    <div style="clear: left; margin-top: 20px; width: 650px">
                            <div style="font-size: 14px; font-weight: bold; line-height: 16px; margin-bottom: 5px;">
                                <p><c:choose><c:when test="${!empty comm.author.name}">${comm.author.name}</c:when><c:otherwise><c:choose><c:when test="${!empty comm.authorname}">${comm.authorname}</c:when><c:otherwise>Гость</c:otherwise></c:choose></c:otherwise></c:choose> <span style="font-size: 11px; font-weight: normal; color: #6d6e71;">, <fmt:formatDate value = "${comm.datetime}" pattern = "yyyy-MM-dd hh:mm"></fmt:formatDate></span></p>
                                <div>
              <!--                       <div style="clear: right; float: right; font-size: 12px; color: #6c6c6c; font-weight: normal; padding-top: 5px;">9 голосов</div> --> 
                                    <span style="font-size: 12px; font-weight: normal; color: #404041;">Оценка товара: <img src="<c:url value = "/resources/images/good/img-rate${comm.note}.gif"/>" alt="5" width="96" height="16" style="padding-left: 10px; padding-right: 10px;" /> Отлично</span>
                                </div>
    						</div>
	       					<div style="background-color: #ebf1f5; clear: left; padding: 15px;">
	       						<div>
                                    <div class="resp_all">
                                        <div id="ro283109s">
    		  	       					    ${comm.text}
                                        </div>
                                        <div style="display: none;" id="ro283109l">
                                            ${comm.text} 
                                        </div>
                                    </div>
                                    <c:if test = "${!empty comm.plus}">
                                            <div class="resp_plus">
                                                <div id='rp283109s'>
                                               ${comm.plus} 
                                                </div>
                                                <div style='display: none;' id='rp283109l'>
                                               ${comm.plus} 
                                                </div>
                                            </div>
                                     </c:if>
                                     <c:if test = "${!empty comm.minus}">
                                            <div class="resp_minus">
                                                <div id='rm283109s'>
                                                ${comm.minus}
                                                </div>
                                                <div style='display: none;' id='rm283109l'>
                                                ${comm.minus} 
                                                </div>
                                            </div> 
                                     </c:if>
                                            
                                </div>
                            </div>
                            <div style="clear: right; float: right; margin-top: 3px;">Отзыв полезен? <a title="Отзыв полезен" href="" id="respraiting" val="1" rid="283109" class="jlresp" style="color: green;">Да</a> (<span id="pl283109">5</span>) / <a  title="Отзыв не полезен" href="" class="jlresp" id="respraiting" val="2" rid="283109" style="color: red;">Нет</a> (<span id="mn283109">4</span>)</div>
                            <div style="clear: left; margin-top: 3px;"><a href="" id = "answerlink${comm.id}" onclick = "callanswer(${comm.id},${good.id});return false" rid="283109" class="answerlink">Ответить</a></div>
                            <div style="display: block;" id = "answers">
                            <div id = "addanswer${comm.id}"></div>
                                <c:forEach items="${comm.chieldcomms}" var="answer">
                                <div style="margin: 10px 0px 10px 104px;">
                                
                                    <p style="font-size: 14px; font-weight: bold; line-height: 16px; margin-bottom: 5px;"><c:choose><c:when test="${!empty answer.author.name}">${answer.author.name}</c:when><c:otherwise>Гость</c:otherwise></c:choose><span style="font-size: 11px; font-weight: normal; color: #6d6e71;">,<fmt:formatDate value = "${answer.datetime}" pattern = "yyyy-MM-dd hh:mm"></fmt:formatDate> </span></p>
	       		                    
	       		                    <div style="background-color: #ebf1f5; clear: left; padding: 15px;">
                                        ${answer.text}
                                    </div>
                                </div>
                                </c:forEach>
                                </div>
                            <div id="answers${comm.id}" style="display: none; padding: 20px 0px 10px 100px;"></div>
                            <div id="answer${comm.id}" class="answer3" style="display: none; padding: 20px 0px 10px 100px;"></div>

                        </div>
                        </c:forEach>
					  </div>
		</div>

                <div style="padding: 10px; display: none; opacity: 0; position: absolute; z-index: 10; border: 1px solid gray; background-color: #e8e8e8; color: black;" id="txt"></div>

        <div style="margin-top: 40px;">
                <div class="responce"></div>
        </div>
    </div>                                     
        <div style="margin-top: 30px; margin-bottom: 30px;">
                    <img src="http://www.mobilluck.com.ua/img/product/left_arrow.png"> <a href="${pageContext.request.contextPath}/catalog/${good.page.fullurl}/all" style="font-size: 16px;">Вернуться к подробной информации о ${good.name} Silver</a>
                </div>
                </div>