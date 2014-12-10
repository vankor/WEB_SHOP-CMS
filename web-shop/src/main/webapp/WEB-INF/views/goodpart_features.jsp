    <%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<div style="clear: both; float: left; width: 700px;">
            			<c:if test = "${!empty segments}">
                        <div style="float: left; width: 700px;">
                        
                <div style="width: 700px; float: left; margin-top: 50px; margin-bottom: 20px;"  id="responce">
                    <div style="font-family: sans-serif; font-size: 24px; color: #2d2d2d;">
                    Самое важное
                    </div>
                                        <table cellspacing="0" width="650" cellpadding="5" style="margin-bottom: 10px;">
                                        <c:forEach items = "${segments}" var = "segment" varStatus = "i">
                    
                            <tr style="background-color: #ecf1f5">
                                <td style="font-size: 16px; font-weight: bold;" colspan="2" >${segment.segment_name}</td>
                            </tr>
                            <c:forEach items = "${segment.properties}" var = "propbean" varStatus = "j">
                            <c:forEach items = "${propbean.vals}" var = "value" varStatus = "i">
                            <tr style="background-color: #ecf1f5">
                            
                            <td width="250" valign="top"><c:out value="${propbean.prop.name}:"/></td>
                	  			<td>${value.value}</td>
                					</tr>
                					</c:forEach>
                			</c:forEach>
                                          
                					</c:forEach>	
                					</table>
                                    </div>
                                    


                <div style="width: 700px; float: left; margin-top: 50px; margin-bottom: 20px;"  id="responce">
                    <div style="font-family: sans-serif; font-size: 24px; color: #2d2d2d;">
                    Все характеристики
                    </div>
                                        <table cellspacing="0" width="650" cellpadding="5">
                       <c:forEach items = "${segments}" var = "segment" varStatus = "i">                 
                    
                            <tr style="background-color: #ecf1f5">
                                <td style="font-size: 16px; font-weight: bold;" colspan="2" >${segment.segment_name}</td>
                            </tr>
                          <c:forEach items = "${segment.properties}" var = "propbean" varStatus = "j">
                            <c:forEach items = "${propbean.vals}" var = "value" varStatus = "i">
                            <tr style="background-color: #ecf1f5">
                            
                            <td width="250" valign="top"><c:out value="${propbean.prop.name}:"/></td>
                	  			<td>${value.value}</td>
                					</tr>
                					</c:forEach>
                			</c:forEach>		
                                           
                    	
                    	</c:forEach>
                    	</table>
                </div>
                <div class="prod_help" style="padding-top: 30px; width: 650px;">
                    	           </div>
               
            </div>
            </c:if>
    </div>