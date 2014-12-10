       <%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test = "${!empty text}">
	${text}
</c:if>
<c:if test = "${empty text}">
<div class="product_dop_info">
  <div class="garanty_info">
    <ul class="rblock_menu">
      <li class="active" sid="0">
        <span>
          Доставка
        </span>
      </li>
      <li sid="1" class="">
        <span>
          Оплата
        </span>
      </li>
      <li sid="2" class="">
        <span>
          Гарантия
        </span>
      </li>
    </ul>
    <ul class="rblock_content">
      <li style="display: block;">
        <div class="delivery_item">
          <span style="font: bold 14px arial;">
            Ваш город: 
          </span>
          <span class="choise_the_city">
            ${currenttown.name}
          </span>
          <div class="content_info">
         	<c:forEach items="${currenttown.towndeliveries}" var="tdl">
            <br>
             <p>
              <b>
                ${tdl.deliveryType.name}
              </b>
            </p>
            <p>
            	<c:choose>
            	<c:when test = " ${tdl.price == 0 || tdl.iscostless}">
            	бесплатно
            	</c:when>
            	<c:otherwise>
            	 ${tdl.price}
            	</c:otherwise>
             
              </c:choose>
              <c:if test = "${tdl.deliveryType.shortname == 'samovyvoz'}">
              <a onclick="skladNalichie(2, this, 0)">
                Проверить наличие
              </a>
             </c:if>
            </p>
           </c:forEach>
            <script type="text/javascript">
              var townid = "<c:out value="${currenttown.id}"/>";
              
              function skladNalichie(city, that, st)
              {
                
            	var url = "<c:out value="${pageContext.request.contextPath}"/>" +"/catalog/"+ "<c:out value="${good.category.page.fullurl}"/>"+"/all";
              $("#dialog_title").html("");
              $("#dialog_content").html("Идет подгрузка информации...");
              var left = parseInt($(that).offset().left);
              var top = parseInt($(that).offset().top);
              showPopupDlg(left-100, top+20, 450);
              $.ajax({
                type: "POST",
                url: url+"/checkgoodhaving",
                data: {"townid":townid},
                dataType: "text",
                success: function(data){
                  //alert(data);
                  $("#dialog_content").html(data);
                }
              });
            }
                                                               </script>
                                                           </div>
                                                          </div>
                                                  </li>
                                                  <li style="display: none;">
                                                    <div class="pay_item">
                                                    
                                                      <p class="title_info">
                                                        Оплата товара удобным для Вас способом
                                                      </p>
                                                      <div class="content_info">
                                                      <c:forEach items="${paytypes}" var="paytype">
                                                       <c:if test = "${!empty paytype.info}">
                                                        <p>
                                                          <a onclick="return load_text(this, 'paytype', ${paytype.id})">
                                                            ${paytype.name}
                                                          </a>
                                                        </p>
                                                        </c:if>
                                                      </c:forEach>
                                                      </div>
                                                    </div>
                                                  </li>
                                                  <li style="display: none;">
                                                    <div class="garanty_item">
                                                      <div class="content_info">
                                                      	<c:if test = "${!empty good.prodguarantie}">
                                                        <p>
                                                          <a onclick="return load_text(this, 'garanty', ${good.prodguarantie.id})">
                                                            Гарантия
                                                          </a>
                                                          ${good.prodguarantie.count} ${good.prodguarantie.periodtype} от производителя
                                                        </p>
                                                        </c:if>
                                                        <c:if test = "${!empty good.exchangeguarantie}">
                                                         <p>
                                                          <a onclick="return load_text(this, 'garanty', ${good.exchangeguarantie.id})">
                                                            Обмен товара в течении  ${good.exchangeguarantie.count} ${good.exchangeguarantie.periodtype}
                                                          </a>
                                                         
                                                        </p>
                                                        </c:if>
                                                        <c:if test = "${!empty good.prodguarantie}">
                                                       <p>
                                                        
                                                            Расширенные гарантии:
                                                          
                                                         
                                                        </p>
                                                        
                                                      <c:forEach items="${good.goodguaranties}" var="guar">
                                                      <p><a onclick="return load_text(this, 'garanty', ${guar.id})">${guar.guarantie.count} ${guar.guarantie.periodtype} - ${guar.price} </a></p>
                                                      </c:forEach>    
                                                      </c:if>                                             
                                                      </div>
                                                    </div>
                                                  </li>
                                                  </ul>
  </div>
  <script type="text/javascript">
    $(".rblock_menu li").click(function(){
      $(".rblock_menu li").removeClass("active");
      $(".rblock_content li").hide();
      $(this).addClass("active");
      $(".rblock_content li").eq($(this).attr("sid")).show();
    }
                              );
    (function ($) {
      $.each(['show', 'hide'], function (i, ev) {
        var el = $.fn[ev];
        $.fn[ev] = function () {
          this.trigger(ev);
          return el.apply(this, arguments);
        };
      });
    }
    )(jQuery);
    once=0;
    $(".choise_the_city").on("click", function(){
      var url = "<c:out value="${pageContext.request.contextPath}"/>" +"/catalog/"+ "<c:out value="${good.page.fullurl}"/>"+"/all";
      
      
      var once = 0;
      alert(url+"    ");
      $('#dialog_wrapper').on('hide', function () {
        if(once==0)
        {
          $.ajax({
            type: "POST",
            url: url+"/choisetown",
            data: {"townid":townid},
            dataType: "text",
            success: function(data){
              $(".delivery_item .content_info").html(data);
              $(".delivery_item .choise_the_city").html($("#my_city").html());
            }
          });
        }
        once = 1;
        
        $('#dialog_wrapper').unbind('hide');
      });
    });
  </script>
</div>
</c:if>