<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf8">
	<link rel="stylesheet" type="text/css"  href="<c:url value="/resources/style.css" />" rel="stylesheet" >
	<title>BuckGoods</title>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

<script type="text/javascript">

function replace(id, el, url){
		var elemid = $(el).attr("id");
		
		$.ajax({
		       type: "POST",
		  	   url: url+"/all",
		       data: {"elemid": elemid, "goodid": id},
		       cache: false,                                
		       success: function(responce){
		    //	   alert(responce);
		    	   $('#main').html("");
		    	   var htmldata = "";
		           $("#main").html(responce);
		   		     $(".goodMenuNode").css("background", "blue" );
		           $(el).css("background", "gray" );
		     	//	location.href=url+'/#tab='+$(el).attr("id"); 
		       }
		     });
	}

function addtobuck(url, goodid){
	alert(url+"/bucket");
	alert(goodid);
	$.ajax({
	       type: "POST",
	  	   url: url+"/bucket",
	       data: {"goodid": goodid},
	       cache: false,                                
	       success: function(responce){
	    	   alert(responce);
	   			document.getElementById('bucket').style.display='block';
	    	   $("#bucket").html("");
	    	   $("#bucket").html(responce);
	   	//	     $(".goodMenuNode").css("background", "blue" );
	      //     $(el).css("background", "gray" );
	     	//	location.href=url+'/#tab='+$(el).attr("id"); 
	       }
	     });
		
	}

function addcomment(url, parentcom_id){ 
	var goodid = $("#goodid").val();
	var text = $("#text").val();
	alert(url+"/addcomment");
	alert(goodid +","+text+","+parentcom_id);
	
	$.ajax({
	       type: "POST",
	  	   url: url+"/addcomment",
	       data: {"goodid":goodid, "text":text, "parentcomm_id":parentcom_id},
	       cache: false,                                
	       success: function(responce){
	    	   alert(responce);
	   //			document.getElementById('commsection').style.display='block';
	    	   $("#commsection").html("");
	    	   $("#commsection").html(responce);
	   	//	     $(".goodMenuNode").css("background", "blue" );
	      //     $(el).css("background", "gray" );
	     	//	location.href=url+'/#tab='+$(el).attr("id"); 
	       }
	     });
		
	}

function callanswer(url, id){
	alert("comm"+id);
	
	var div;
	
	div = document.createElement("div");
	div.setAttribute("id", "answer"+id);
	div.innerHTML = "<label id = 'answer"+id+".name' name='answer["+id+"]'> Parameter "+id+" name: </label><input id='answer"+id+".name' name='answer["+id+"]' type='text'/><input type = 'button' onclick = 'addcomment("+url+","+id+")'></input>";
		
	document.getElementById("comm"+id).appendChild(div);
}

</script>
</head>
<body>
<h1> Все о ${good.name}</h1>
<ul>
<li><span style = "font:bold  110% Palatino serif;" >Аватар:</span><span style = "font:  110% bold;" >${good.thumb}</span></li>
<li><span style = "font:bold  110% Palatino serif;" >Название: </span><span style = "font:  110% bold;" >${good.name}</span> </li>
<li><span style = "font:bold  110%  Palatino serif;" >Цена: </span><span style = "font:  110% bold;" >${good.price}</span></li>
</ul>
<div id = "goodmenu" class = "goodMenu">
<div onclick = "replace(${good.id}, this, '${pageContext.request.contextPath}/good/${good.id}');" id = "all" class = "goodMenuNode">Все</div>
<div onclick = "replace(${good.id}, this, '${pageContext.request.contextPath}/good/${good.id}');" id = "params" class = "goodMenuNode" >Параметры</div>
<div onclick = "replace(${good.id}, this, '${pageContext.request.contextPath}/good/${good.id}');" id = "photos" class = "goodMenuNode" >Фото</div>
<div onclick = "replace(${good.id}, this, '${pageContext.request.contextPath}/good/${good.id}');" id = "voices" class = "goodMenuNode" >Отзывы</div>
</div>
<div id = "main" class = "goodinfo">	
<c:if test="${section == 'all' || section == 'params'}">
<c:if test="${!empty properties}">
<h3> Параметры товара ${good.name}:</h3>
	<ul>

	<c:forEach items = "${properties}" var = "entry">
	<li><span style = "font:bold  110% Palatino serif;" >${entry.key}: </span><span style = "font:  110% bold;" >${entry.value}</span> </li>
	</c:forEach>
	
</ul>
</c:if>
<h3> Описание товара ${good.name}:</h3>
<ul>
<li><span style = "font:bold  110% Palatino serif;" >Description: </span><br/><span style = "font:  110% bold;" >${good.description}</span></li>
</ul>
</c:if>



<c:if test="${section == 'all' || section == 'voices'}">
<c:if test="${!empty comList}">
<h3> Отзывы о товаре ${good.name}:</h3>
	<div id = "commsection">
	<table class="data">
	
		<tr>
			<th>Time</th>
			<th>TEXT</th>
			<th>&nbsp;</th>
		</tr>
		
		<c:forEach items="${comList}" var="com">
			<div id = "comm${com.id}">
			<tr>
				<td>${com.time}</td>
				<td>${com.text}</td>
				<td><a href="delete/${usr.id}">Delete</a></td>
				<td><a href="#"  onclick="callanswer('${pageContext.request.contextPath}/good/${good.id}',${com.id});return false">Answer</a></td>
			</tr>
			</div>
		</c:forEach>
		
	</table>
	</div>
	</c:if>
	<form method="post" action="addcomment" id = "comments">

	<table>
		<tr>
			<td><input id = "goodid"  name = "goodid" type = "hidden" path="goodid" value = "${good.id}"/></td>
			<td><label path="text"></label></td>
			<td><input id = "text" name = "text" path="text" /></td>
			
		</tr>
		
		<tr>
			<td colspan="2"><input type="button" onclick = "addcomment('${pageContext.request.contextPath}/good/${good.id}',-1)" id="addcomm"
				value="Отправить отзыв" /></td>
		</tr>
	</table>
</form>

</c:if>
<c:if test="${section == 'all' || section == 'photos'}">
<c:if test="${!empty photoList}">
<h3> Фото:</h3>
	<table class="data">
		<tr>
			<th>Time</th>
			<th>TEXT</th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach items="${comList}" var="com">
			<tr>
				<td>${com.time}</td>
				<td>${com.text}</td>
				<td><a href="delete/${usr.id}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
	
</c:if>
</c:if>
</div>
<h1> Приобрести ${good.name}:</h1>
<ul>
	<li><button onclick = "addtobuck('${pageContext.request.contextPath}/good/${good.id}',${good.id})">Add to bucket</button> </li>
</ul>

<div id = "res"></div>
<div id = "bucket"></div>

</body>
</html>