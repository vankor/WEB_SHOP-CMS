    <%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
         <div>
                      <c:forEach items="${comList}" var="comm">
					    <div style="clear: left; margin-top: 20px; width: 650px">
                            <div style="font-size: 14px; font-weight: bold; line-height: 16px; margin-bottom: 5px;">
                                <p><c:choose><c:when test="${!empty comm.author.name}">${comm.author.name}</c:when><c:otherwise><c:choose><c:when test="${!empty comm.authorname}">${comm.authorname}</c:when><c:otherwise>Гость</c:otherwise></c:choose></c:otherwise></c:choose> <span style="font-size: 11px; font-weight: normal; color: #6d6e71;">, <fmt:formatDate value = "${comm.datetime}" pattern = "yyyy-MM-dd hh:mm"></fmt:formatDate></span></p>
                                <div>
              <!--                       <div style="clear: right; float: right; font-size: 12px; color: #6c6c6c; font-weight: normal; padding-top: 5px;">9 голосов</div> --> 
                                    <span style="font-size: 12px; font-weight: normal; color: #404041;">Оценка товара: <img src="<c:url value = "/resources/images/good/img-rate${comm.note}.gif"/>" alt="${comm.note}" width="96" height="16" style="padding-left: 10px; padding-right: 10px;" /> Отлично</span>
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