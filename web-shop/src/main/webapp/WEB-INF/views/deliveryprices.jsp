    <%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<c:if test = "${section == 'regions'}">
<script>
function selectregion(rid, gid, el){
	
	$.ajax({
		type: "POST",
		url: goodservurl+ "/selectregion",
		data:{"rid":rid, "gid":gid},
//		dataType: "text",
		success: function(response){
			
			$("#citydelivery").html(response);
			$("#choiseobl").find(".checkad").attr("class", "");
			$(el).parent("li").addClass("checkad");
			
		},
		error : function(xhr, status, error){
            alert("Error!" + xhr.status);
        }
	
		
	});
	
	
}
function selectcity(cid, gid, el){
	
	$.ajax({
		type: "POST",
		url: goodservurl+ "/selectcity",
		data:{"cid":cid, "gid":gid},
//		dataType: "text",
		success: function(response){
		//	alert(response);
		
			$("#srokiadressbody").html(response);
			$(".base_c").attr("class", "");
			$(".base_c").addClass("addit_c");
			$(el).parent("li").removeClass("addit_c");
			$(el).parent("li").addClass("base_c checkad");
		
			if(gid<0){
				$("#choisesubmit").html(response);
			}
		},
		error : function(xhr, status, error){
            alert("Error!" + xhr.status);
        }
	
		
	});
	
	
}

function choisecurrentcity(cid){
	$.ajax({
		type: "POST",
		url: goodservurl+"/choisecity",
		data:{"cid":cid},
//		dataType: "text",
		success: function(response){
			if(response==1){
			$("#dialog_wrapper").hide();
			$("#popupbg").hide();}
			
			
		},
		error : function(xhr, status, error){
            alert("Error!" + xhr.status);
        }
	
		
	});
}
</script>
</c:if>
<c:if test = "${section == 'regions'}">

  <link rel="stylesheet" href="<c:url value="/resources/popupdelivery.css"/>"/>
  <div id="sroki_content">
    <div id="choiseobl">
      <p class="choiseobl_title_info">
        Выберите область
      </p>
      <div id="choiseoblbody">
        <table>
          <tbody>
            <tr>
            <c:set var = "newcol" value  = "1"/>
              <c:forEach items = "${regions}" var="region" varStatus ="i">
                 <c:if test = "${i.index == 0}" >
               <td>
                	<ul>     
                </c:if>
                
                    <li sid="${region.id}" <c:choose><c:when test = "${selregion.id == region.id}">class="checkad"</c:when><c:otherwise>class=""</c:otherwise></c:choose>>
                      <a role = "button" onclick = "selectregion(${region.id}, ${goodid}, this)">
                        ${region.name}
                      </a>
                    </li>
               <c:if test = "${(i.index+1) % 6 == 0 && newcol == 0}" >
                    </ul>
                	</td>
                	<td>
                	<ul>
                </c:if>
                <c:if test = "${i.index == 0}"><c:set var = "newcol" value  = "0"/></c:if>
               
                </c:forEach>
              </tr>
            </tbody>
        </table>
      </div>
    </div>
 
 <div id = "citydelivery">
    <div id="choisecity">
      <p class="choisecity_title_info">
        Выберите город
      </p>
      <div id="choisecitybody">
        
        <table>
          <tbody>
            <tr>
            <c:forEach items = "${selregion.deliverytowns}" var="town" varStatus ="i">
              <td>
                <ul>
                  <li <c:choose><c:when test = "${seltown.id == town.id}">class="base_c checkad"</c:when><c:otherwise>class="addit_c"</c:otherwise></c:choose> sid="${town.id}">
                    <a role = "button" onclick = "selectcity(${town.id}, ${goodid}, this)">
                      ${town.name}
                    </a>
                  </li>
                </ul>
              </td>
             </c:forEach>
             </tr>
          </tbody>
        </table>
        
        
      </div>
    </div>
    <c:if test = "${type == 'citychoise'}">
    <input type = "button" onclick = "location.href = '${pageContext.request.contextPath}/settown?cid=${seltown.id}';" value = "Выбрать город"/>
    </c:if>
    <c:if test = "${type != 'citychoise'}">
    <div id="srokiadress">
      <p class="srokiadresstitle">
        Адреса пунктов выдачи и служб доставки в ${seltown.name}							
      </p>
      
      <div id="srokiadressbody">
      <c:forEach items = "${seltown.shopresidents}" var = "resident">
        <div id="srokiadressbody_left" style="width:420px; float:left">
          <div class="adr_body">
            <img src="http://img.mobilluck.com.ua/img/filials.png">
          </div>
          <div class="right_fl" style="padding-left:10px; width:360px">
            <div>
              <p>
                <b>
                  Адрес представительства магазина
                </b>
              </p>
              <p>
                ${resident}										
              </p>
            </div>
          </div>
        </div>
        <div id="srokiadressinfo" style="width:350px; float:left;">
          <br>
          <table>
            <tbody>
              <tr>
                <td>
                  <b>
                    Ориентировочный срок отгрузки.
                  </b>
                  <c:forEach items = "${resident.addrdel}" var = "addl">
                  <br>
                  ${addl.deliveryType.name}: ${addl.mindelterm}-${addl.maxdelterm} дней
                 
                  </c:forEach>
                </td>
              </tr>
              <tr>
                <td style="padding-top:15px;">
                  <b>
                    Стоимость доставки.
                  </b>
                  <c:forEach items = "${resident.addrdel}" var = "addl">
                  <br>
                  ${addl.deliveryType.name}: ${addl.mindelprice}-${addl.maxdelprice} грн
                 
                  </c:forEach>
                </td>
              </tr>
            </tbody>
          </table>
          
        </div>
        <div style="clear:both">
        </div>
        </c:forEach>
        
        
        <c:forEach items = "${seltown.deliveryservices}" var = "resident">
        <div id="srokiadressbody_left" style="width:420px; float:left">
          <div class="adr_body">
            <img src="http://img.mobilluck.com.ua/img/filials.png">
          </div>
          <div class="right_fl" style="padding-left:10px; width:360px">
            <div>
              <p>
                <b>
                  Адрес службы дотсавки
                </b>
              </p>
              <p>
                ${resident}										
              </p>
            </div>
          </div>
        </div>
        <div id="srokiadressinfo" style="width:350px; float:left;">
          <br>
          <table>
            <tbody>
              <tr>
                <td>
                  <b>
                    Ориентировочный срок доставки.
                  </b>
                  <c:forEach items = "${resident.addrdel}" var = "addl">
                  <br>
                  ${addl.deliveryType.name}: ${addl.mindelterm}-${addl.maxdelterm} дней
                 
                  </c:forEach>
                </td>
              </tr>
              <tr>
                <td style="padding-top:15px;">
                  <b>
                    Стоимость доставки.
                  </b>
                  <c:forEach items = "${resident.addrdel}" var = "addl">
                  <br>
                  ${addl.deliveryType.name}: ${addl.mindelprice}-${addl.maxdelprice} грн
                 
                  </c:forEach>
                </td>
              </tr>
            </tbody>
          </table>
          
        </div>
        <div style="clear:both">
        </div>
        </c:forEach>
        
        
        <div class="adr_prim">
          * - указанные выше даты являются ориентировочными, более подробную информацию вы можете получить у менеджеров.
        </div>
      </div>
   </c:if>   
      
    </div>
 </div>   
  </div>
 </c:if> 
 <c:if test = "${section == 'deliverytowns'}">
 	   <div id="choisecity">
      <p class="choisecity_title_info">
        Выберите город
      </p>
      <div id="choisecitybody">
        
        <table>
          <tbody>
            <tr>
            <c:forEach items = "${selregion.deliverytowns}" var="town" varStatus ="i">
              <td>
                <ul>
                  <li <c:choose><c:when test = "${seltown.id == town.id}">class="base_c checkad"</c:when><c:otherwise>class="addit_c"</c:otherwise></c:choose> sid="${town.id}">
                    <a onclick = "selectcity(${town.id}, ${goodid}, this)">
                      ${town.name}
                    </a>
                  </li>
                </ul>
              </td>
             </c:forEach>
             </tr>
          </tbody>
        </table>
        
        
      </div>
    </div>
     <c:if test = "${type == 'citychoise'}">
     <div id = "choisesubmit">
    <input type = "button" onclick = "location.href = '${pageContext.request.contextPath}/settown?cid=${seltown.id}';" value = "Выбрать город"/>
    </div>
    </c:if>
    <c:if test = "${type != 'citychoise'}">
    <div id="srokiadress">
      <p class="srokiadresstitle">
        Адреса пунктов выдачи и служб доставки в ${seltown.name}							
      </p>
      
      <div id="srokiadressbody">
      <c:forEach items = "${seltown.shopresidents}" var = "resident">
        <div id="srokiadressbody_left" style="width:420px; float:left">
          <div class="adr_body">
            <img src="http://img.mobilluck.com.ua/img/filials.png">
          </div>
          <div class="right_fl" style="padding-left:10px; width:360px">
            <div>
              <p>
                <b>
                  Адрес представительства магазина
                </b>
              </p>
              <p>
                ${resident}										
              </p>
            </div>
          </div>
        </div>
        <div id="srokiadressinfo" style="width:350px; float:left;">
          <br>
          <table>
            <tbody>
              <tr>
                <td>
                  <b>
                    Ориентировочный срок отгрузки.
                  </b>
                  <c:forEach items = "${resident.addrdel}" var = "addl">
                  <br>
                  ${addl.deliveryType.name}: ${addl.mindelterm}-${addl.maxdelterm} дней
                 
                  </c:forEach>
                </td>
              </tr>
              <tr>
                <td style="padding-top:15px;">
                  <b>
                    Стоимость доставки.
                  </b>
                  <c:forEach items = "${resident.addrdel}" var = "addl">
                  <br>
                  ${addl.deliveryType.name}: ${addl.mindelprice}-${addl.maxdelprice} грн
                 
                  </c:forEach>
                </td>
              </tr>
            </tbody>
          </table>
          
        </div>
        <div style="clear:both">
        </div>
        </c:forEach>
        
        
        <c:forEach items = "${seltown.deliveryservices}" var = "resident">
        <div id="srokiadressbody_left" style="width:420px; float:left">
          <div class="adr_body">
            <img src="http://img.mobilluck.com.ua/img/filials.png">
          </div>
          <div class="right_fl" style="padding-left:10px; width:360px">
            <div>
              <p>
                <b>
                  Адрес службы дотсавки
                </b>
              </p>
              <p>
                ${resident}										
              </p>
            </div>
          </div>
        </div>
        <div id="srokiadressinfo" style="width:350px; float:left;">
          <br>
          <table>
            <tbody>
              <tr>
                <td>
                  <b>
                    Ориентировочный срок доставки.
                  </b>
                  <c:forEach items = "${resident.addrdel}" var = "addl">
                  <br>
                  ${addl.deliveryType.name}: ${addl.mindelterm}-${addl.maxdelterm} дней
                 
                  </c:forEach>
                </td>
              </tr>
              <tr>
                <td style="padding-top:15px;">
                  <b>
                    Стоимость доставки.
                  </b>
                  <c:forEach items = "${resident.addrdel}" var = "addl">
                  <br>
                  ${addl.deliveryType.name}: ${addl.mindelprice}-${addl.maxdelprice} грн
                 
                  </c:forEach>
                </td>
              </tr>
            </tbody>
          </table>
          
        </div>
        <div style="clear:both">
        </div>
        </c:forEach>
        
        
        <div class="adr_prim">
          * - указанные выше даты являются ориентировочными, более подробную информацию вы можете получить у менеджеров.
        </div>
      </div>
      
      
    </div>
 	</c:if>
 </c:if>
 
 <c:if test = "${section == 'adressdeliveries'}">
  <c:if test = "${type == 'citychoise'}">
  <div id = "choisesubmit">
    <input type = "button" onclick = "location.href = '${pageContext.request.contextPath}/settown?cid=${seltown.id}';" value = "Выбрать город"/>
    </div>
    </c:if>
    <c:if test = "${type != 'citychoise'}">
 	   <c:forEach items = "${seltown.shopresidents}" var = "resident">
        <div id="srokiadressbody_left" style="width:420px; float:left">
          <div class="adr_body">
            <img src="http://img.mobilluck.com.ua/img/filials.png">
          </div>
          <div class="right_fl" style="padding-left:10px; width:360px">
            <div>
              <p>
                <b>
                  Адрес представительства магазина
                </b>
              </p>
              <p>
                ${resident}										
              </p>
            </div>
          </div>
        </div>
        <div id="srokiadressinfo" style="width:350px; float:left;">
          <br>
          <table>
            <tbody>
              <tr>
                <td>
                  <b>
                    Ориентировочный срок отгрузки.
                  </b>
                  <c:forEach items = "${resident.addrdel}" var = "addl">
                  <br>
                  ${addl.deliveryType.name}: ${addl.mindelterm}-${addl.maxdelterm} дней
                 
                  </c:forEach>
                </td>
              </tr>
              <tr>
                <td style="padding-top:15px;">
                  <b>
                    Стоимость доставки.
                  </b>
                  <c:forEach items = "${resident.addrdel}" var = "addl">
                  <br>
                  ${addl.deliveryType.name}: ${addl.mindelprice}-${addl.maxdelprice} грн
                 
                  </c:forEach>
                </td>
              </tr>
            </tbody>
          </table>
          
        </div>
        <div style="clear:both">
        </div>
        </c:forEach>
        
        
        <c:forEach items = "${seltown.deliveryservices}" var = "resident">
        <div id="srokiadressbody_left" style="width:420px; float:left">
          <div class="adr_body">
            <img src="http://img.mobilluck.com.ua/img/filials.png">
          </div>
          <div class="right_fl" style="padding-left:10px; width:360px">
            <div>
              <p>
                <b>
                  Адрес службы дотсавки
                </b>
              </p>
              <p>
                ${resident}										
              </p>
            </div>
          </div>
        </div>
        <div id="srokiadressinfo" style="width:350px; float:left;">
          <br>
          <table>
            <tbody>
              <tr>
                <td>
                  <b>
                    Ориентировочный срок доставки.
                  </b>
                  <c:forEach items = "${resident.addrdel}" var = "addl">
                  <br>
                  ${addl.deliveryType.name}: ${addl.mindelterm}-${addl.maxdelterm} дней
                 
                  </c:forEach>
                </td>
              </tr>
              <tr>
                <td style="padding-top:15px;">
                  <b>
                    Стоимость доставки.
                  </b>
                  <c:forEach items = "${resident.addrdel}" var = "addl">
                  <br>
                  ${addl.deliveryType.name}: ${addl.mindelprice}-${addl.maxdelprice} грн
                 
                  </c:forEach>
                </td>
              </tr>
            </tbody>
          </table>
          
        </div>
        <div style="clear:both">
        </div>
        </c:forEach>
        
        
        <div class="adr_prim">
          * - указанные выше даты являются ориентировочными, более подробную информацию вы можете получить у менеджеров.
        </div>
       </c:if>
  </c:if>
