    <%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<div style="clear: both;float: left; width: 700px;">
                    <div style = "float:left; clear:both;">
                        <div  style="font-family: sans-serif; font-size: 24px; color: #2d2d2d; margin-bottom: 20px;">
                        Фото
                        </div>
                     
                        	<c:forEach items = "${good.images}" var = "image" varStatus = "i">
                        	<div style = "float:left; ">
                    			<table cellspacing="0" cellpadding="0" border="0" width="134" height="134">
                    			<tbody><tr>
                    			                   			
                    			<td align="center"><a href="<c:url value="/resources/images${image.url}" />" rel="gcphoto_group2" title=""><img src="<c:url value="/resources/images${image.url}" />" width="95" height="68" alt="Фотография ${good.name}"></a></td></tr>
                    			</tbody></table>
                    		</div>
                    		</c:forEach>
                    		 	
                    		</div>   
             <div style = "float:left; clear:both;">               
                <div style="font-family: sans-serif; font-size: 24px; color: #2d2d2d;">
                Видеообзоры
                </div>
				<script src="https://apis.google.com/js/platform.js" gapi_processed="true"></script>
              
                <c:forEach items = "${good.videos}" var = "video" varStatus = "i">
            <div style="text-align: left;">Видеообзор ${good.name}</div><div style="width: 650px;"><iframe width="640" height="360" src="${video.url}" frameborder="0" allowfullscreen=""></iframe></div><br>
           		 </c:forEach>
            <br>
	<!--  			<div>
					<span style="position: relative;top: -5px;">Подписаться на Youtube канал интернет-магазина</span> <div id="___ytsubscribe_0" style="text-indent: 0px; margin: 0px; padding: 0px; border-style: none; float: none; line-height: normal; font-size: 1px; vertical-align: baseline; display: inline-block; width: 131px; height: 24px; background: transparent;"><iframe frameborder="0" hspace="0" marginheight="0" marginwidth="0" scrolling="no" style="position: static; top: 0px; width: 131px; margin: 0px; border-style: none; left: 0px; visibility: visible; height: 24px;" tabindex="0" vspace="0" width="100%" id="I0_1404087917257" name="I0_1404087917257" src="https://www.youtube.com/subscribe_embed?usegapi=1&amp;channel=mobilluckreview&amp;layout=default&amp;theme=dark&amp;count=default&amp;origin=http%3A%2F%2Fwww.mobilluck.com.ua&amp;gsrc=3p&amp;ic=1&amp;jsh=m%3B%2F_%2Fscs%2Fapps-static%2F_%2Fjs%2Fk%3Doz.gapi.ru.TH371UThQCM.O%2Fm%3D__features__%2Fam%3DAQ%2Frt%3Dj%2Fd%3D1%2Fz%3Dzcms%2Frs%3DAItRSTNu3PqSiDq__-43pYwUa5r7xz1srA#_methods=onPlusOne%2C_ready%2C_close%2C_open%2C_resizeMe%2C_renderstart%2Concircled%2Cdrefresh%2Cerefresh%2Conload&amp;id=I0_1404087917257&amp;parent=http%3A%2F%2Fwww.mobilluck.com.ua&amp;pfname=&amp;rpctoken=34487291" data-gapiattached="true"></iframe></div>
				</div>
-->
             
          </div>
            
                </div>