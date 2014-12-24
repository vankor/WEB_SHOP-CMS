<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>Insert title here</title>
	<link rel="stylesheet" href="<c:url value="/resources/popupwindow.css"/>"/>
	<link rel="stylesheet" href="<c:url value="/resources/style.css"/>"/>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
var counter = 0;
var countryid = "<c:out value="${country.id}"/>";
cc=[];
cc[counter] = 0;
var buck = "'";
var url = "<c:out value="${pageContext.request.contextPath}"/>";

$(document).ready(function(){
	$("#region").attr("value", "ewg");
	
    $('#add').click(function() {
        document.getElementById('openModal').style.display = 'block';
        location.href='#openModal';

        $('#openModal').fadeIn(30000);
    });
    
 
    
  
    
    $( "#myregion" ).change(function() {
    //	var regid = $(this).val();
    	var regname = $(this).children(":selected").html();
    	var regid = $(this).children(":selected").val();
    	$("#regionname").attr("value", regname);
    	var regnum = $(this).attr("num");
    	   	
   	 $("#townaddref").attr("href",url+"/admin/admincountry/"+countryid+"/region/"+regid+"/addtown");
   	 $("#towndelref").attr("href",url+"/admin/admincountry/"+countryid+"/deleteRegion/"+regid);
   	    
    	$.ajax({
    			type:"post",	
    		 	url: url+"/admin/getregiontowns",
    	        dataType: 'text',
    	        data: {"regid":regid, "regnum":regnum},
    	        success: function(data){
    	    //    	alert(data);
    	        	$("#regioninfo").html(data);
    	        	
    	        }
    	});
    	
 
    });
});

function addField(){
	alert(counter);
	var div;
	div = document.createElement("div");
	div.innerHTML = "<label path = 'category.parameters' name='parametername'> Parameter "+counter+" name: </label><input path = 'category.parameters["+counter+"]' value='parameter"+counter+"' name = 'parameters'/>";
	counter++;
	document.getElementById("parentId").appendChild(div);
}

function changename(){
//	alert("rege");
	$("#regionname").attr("type", "text");
	
}


</script>

</head>

<body>
 <jsp:include page="/WEB-INF/views/adminheader.jsp" />

<div class="mainwrapper"> 
<h2>Просмотреть/редактировать страну ${country.name}</h2>


<c:set var="action" value="${pageContext.request.contextPath}/admin/admincountry/${country.id}/updatedRegion" />
<c:set var="add" value="${pageContext.request.contextPath}/admin/admincountry/${country.id}/addRegions" />


	<div id = "form">


	<ul>
<form:form method="post" action = "${action}"  modelAttribute="region">
		
		<li>
		<div id = "col1">
		
		<label id = 'region.num'  name='regionnum'> Регион: </label>
		</div>
		<div id = "col2">
		<select id = "myregion" num = "0" name = "id" value = "name" autocomplete = "off">
					<c:forEach items = "${region.country.regions}" var = "reg" varStatus = "i">
					<c:choose>
					<c:when test = "${i.index ==0}">
						<option selected num = "${i.index}"  name = "regions[${i.index}].id" value = "${reg.id}">${reg.name}</option>
					</c:when>
					<c:otherwise>
						<option num = "${i.index}" name = "regions[${i.index}].id" value = "${reg.id}" >${reg.name}</option>
					</c:otherwise>
					</c:choose>
					
						
					</c:forEach>
    				
		</select>
		
		
		<form:input id = 'regions.id' type="hidden" path = 'id' name = 'regionid'/>
		<form:input id = 'regions0.country' type="hidden" path = 'country.id' name = 'cntrud'/>
		<form:input id = 'regions.country' type="hidden" path = 'country.name' name = 'cntrname'/>
		<form:input path = 'name' id = 'regionname' value = "${region.name}" type = "hidden"/><br><a onclick = "changename()" role = "button">Изменить имя региона</a><br>
		<a id = "townaddref" href = "${pageContext.request.contextPath}/admin/admincountry/${region.country.id}/region/${region.id}/addtown">Добавить город в регион</a><br>
		
		<a id = "towndelref" href = "${pageContext.request.contextPath}/admin/admincountry/${region.country.id}/deleteRegion/${region.id}">Удалить регион страны</a>
		</div>
		</li>
		
		<li>

			<div id = "col2"><input type="submit"
				value="Сохранить изменения" /></div>
		</li>
		
		</form:form>
		
		<form:form method="post" action = "${add}"  modelAttribute="newregs">
		<li>
		<div style = "overflow:auto; float:left; clear:both;" id = "parentId"></div>
		</li>
		<div id = "col2"><input type="submit"
				value="Сохранить добавленные регионы" /></div>
				<li><div id = "col2"><a role = "button" onclick = "return appendPropertySegment()">Добавить регион в страну</a></div></li>
		</form:form>
		<li>
			<div id = "col1"><label>
				Список городов региона:
			</label></div>
		</li>
		</ul>
		
		
		<div id = "sector" style = "width:750px;">
		<div id = "regioninfo">
			<table style = "">
				<th><td>Название</td><td>Код</td></th>
				<div id = "reg"></div>
			
			<c:if test = "${!empty country.regions[0].towns }">
			<c:forEach items = "${country.regions[0].towns}" var = "town" varStatus = "j">
			<c:if test = "${town.isdeleted != true}">
			<tr>
			<td><label id = 'town${j.index}.name'  > Город  ${j.index+1}: </label></td>
			
			<td><label id = 'town${j.index}.name' >${town.name}</label></td>
			
			<td><label id = 'town${j.index}.phonecode'  >${town.phonecode}</label></td>
			
			<td><a href = "${pageContext.request.contextPath}/admin/admincountry/${town.region.country.id}/adminTown/${town.id}">Вести данные для города</a></td>
			<td><a href = "${pageContext.request.contextPath}/admin/realdelete/Town/${town.id}">Удалить город</a></td>
			</tr>
			</c:if>
			<script>cc[counter] = ${j.index + 1} </script>
			</c:forEach>
			</c:if>
				
			</table>
		
		</div>
		</div>
				
		
		<script>
		function appendPropertySegment(){
			var div;
			var id = "region"+counter;
			var ctrid = "<c:out value="${country.id}"/>";
			cc[counter]=0;
			div = document.createElement("div");
			div.setAttribute("id", "reg"+counter);
			div.innerHTML = "<label id = 'regions"+counter+".name' name='regs["+counter+"].name'> Регион "+(counter+1)+": </label><input id='regions"+counter+".name' name='regs["+counter+"].name' type='text'/><input id='regions"+counter+".country.id' value = '"+ctrid+"' name='regs["+counter+"].country.id' type='hidden'/><a href = 'javascript:appendProperty("+counter+")'>Добавить город в регион</a><a href = 'javascript:removeProperty(-1, "+counter+")'>Удалить регион</a>";
			
			counter++;
			
			document.getElementById("parentId").appendChild(div);
		}
		
		Element.prototype.remove = function() {
		    this.parentElement.removeChild(this);
		}
		NodeList.prototype.remove = HTMLCollection.prototype.remove = function() {
		    for(var i = 0, len = this.length; i < len; i++) {
		        if(this[i] && this[i].parentElement) {
		            this[i].parentElement.removeChild(this[i]);
		        }
		    }
		}
		
		function removeProperty(h, id){
			
			var strid;
			if(h==-1){strid = 'reg'+id;	counter--;}
			
			else{strid = 'town'+id; cc[id]--;}
	
			document.getElementById(strid).remove();
		}
		
		function appendProperty(parid){
			alert("propsegment"+parid);
			propnum = cc[parid];
			
			var div;
			
			div = document.createElement("div");
			div.setAttribute("id", "town"+propnum);
			div.innerHTML = "<label id = 'regions["+parid+"].towns["+propnum+"].name' name='regions["+parid+"].towns["+propnum+"].name'> Город "+(propnum+1)+": </label><input id='regions["+parid+"].towns["+propnum+"].name' name='regions["+parid+"].towns["+propnum+"].name' type='text'/><input id='regions["+parid+"].towns["+propnum+"].phonecode' name='regions["+parid+"].towns["+propnum+"].phonecode' type='text'/><a href = 'javascript:removeProperty("+parid+", "+propnum+")'>Удалить город</a>";
			cc[parid]++;
//			alert(div);
			document.getElementById("reg"+parid).appendChild(div);
			
		}
		
		</script>
	
		
		
	
	</div>


</div>

</body>
</html>