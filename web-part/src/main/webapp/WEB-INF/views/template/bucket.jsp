<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<div style = "display:none;" id = "bucket_count">${bucketsize}</div>

<script>
$(document).ready(function(){
$("#buck_close").click(function(){
	$("#bucket").css("display", "none");
	$("#bucket").html("");
	$("#popupbg").hide();
	$("#popup_wrapper").hide();

});
});
</script>
<div id="popup_wrapper" style="display: block;">

    <div id="popup_outer" style="width: 680px;">
        <div id="popup_content">
            <div id="buck_close" >

                Закрыть

            </div>
            <p id="popup_cart_title" class="mgood_title">

                Корзина заказов

            </p>
            <div id="popup_cart_content" style="clear: left; float: left; width: 100%;">
                <div style="border: 0px solid red; background-color: white; width: 600px; margin: 0 auto; padding: 0px;">
                    <form id="cartform" name="cartform" method="POST" action="http://www.mobilluck.com.ua/cart.php">
                        <input type="hidden" value="fillorder" name="mode"></input>
                        <div class="w100 float">
                            <table class="mcctable" cellspacing="0" cellpadding="0" border="0" align="center" style="width: 570px; padding: 0px;">
                                <tbody>
                                <c:forEach items="${buckrows}" var="row">
                                    <tr>
                                        <td width="25" valign="top" align="center" style="padding: 5px;" rowspan="2">
                                            <a onclick="return deletefrombuck(${row.good.id},${bucketsize})" role="button">
                                                <img src="http://www.mobilluck.com.ua/img/product/close.png"></img>
                                            </a>
                                        </td>
                                        <td width="130" align="center" style="padding: 0px;" rowspan="2">
                                            <a href="${pageContext.request.contextPath}/good/${row.good.id}">
                                                <img width="95" height="84" alt="${row.good.name}" src="<c:url value="/resources/images${row.good.thumb}" />"></img>
                                            </a>
                                        </td>
                                        <td width="200" style="padding: 0px;">
                                            <input type="hidden" value="43317" name="cartit[]"></input>
                                            <p>
                                                <a href="${pageContext.request.contextPath}/good/${row.good.id}">

                                                    ${row.good.name}

                                                </a>
                                            </p>
                                        </td>
                                        <td style="padding: 0px 10px;">
                                            <input id="goodcount_${row.good.id}" class="i_mcct_price" type="text" onkeypress="document.getElementById('cit_recalc${row.good.id}').style.display='block';" value="${row.goodcount}" name="cit_num[]"></input>

                                             шт.

                                            <br></br>
                                            <div id="cit_recalc${row.good.id}" class="order3_hlink" style="display: none;">
                                                <a onclick="return changecount(${row.good.id},${bucketsize})" role="button">

                                                    пересчитать

                                                </a>
                                            </div>
                                        </td>
                                        <td style="padding: 0px; text-align: right;">
                                            <p style="font-size: 18px;">
                                                <span style="font-size: 24px; font-weight: bold;">

                                                    ${row.good.price}

                                                </span>

                                                 грн

                                            </p>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td bgcolor="white" style="padding: 0px 10px; background-color: white" colspan="3">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="5"></td>
                                    </tr>
                             </c:forEach>
                                    <tr>
                                        <td class="w100" colspan="6">
                                            <table class="order3table">
                                                <tbody>
                                                    <tr>
                                                        <td id="linkback" align="left" style="padding-left: 10px;">
                                                            <img src="http://www.mobilluck.com.ua/img/product/left_arrow.png"></img>
                                                            <a href="javascript:history.back();">

                                                                Вернуться к выбору товаров

                                                            </a>
                                                        </td>
                                                        <td class="ta_right" style="font-size: 18px;">


                                                            						
                                                            							Всего за заказ
                                                            							

                                                            <span style="font-size: 24px; font-weight: bold;">

                                                                ${sum}

                                                            </span>

                                                             грн*
                                                            						

                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="w100 float">

   						 <table cellspacing="0" cellpadding="0" align="center" style="width: 570px; padding: 0px;">
       						 <tbody>
       						     <tr>
          				      <td align="right" style="padding: 0px;">
               			     <div id="btn_order" class="btnorder" onclick = "location.href = '${pageContext.request.contextPath}/order'"></div>
              			  </td>
            		</tr>
        </tbody>
    </table>

</div>
                    </form>
                    <table align="center" style="width: 600px; padding: 0px 0px;"></table>
                    <div id="procredit" style="width: 100%;"></div>
                    <div style="clear: left; height: 10px;"></div>
                </div>
            </div>
            <div class="both"></div>
        </div>
    </div>

</div>

<script>
function clo(){
	$("#bucket").css("display", "none");
	$("#bucket").html("");
	$("#popupbg").hide();
	$("#popup_wrapper").hide();
}

</script>
