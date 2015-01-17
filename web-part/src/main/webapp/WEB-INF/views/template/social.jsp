   <%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div style="clear: left; width: 53px; padding-top: 10px;">            <div style="clear: left; width: 482px; padding-top: 10px;">
                <noindex>
                    <div class="social" style="float: left; width: 155px;">

						<!-- VKONTAKTE -->
                        <!-- Put this script tag to the <head> of your page -->
                        <script type="text/javascript" src="http://userapi.com/js/api/openapi.js?32"></script>
                        <script type="text/javascript">
                            VK.init({apiId: 2117138, onlyWidgets: true});
                        </script>
                        <!-- Put this div tag to the place, where the Like block will be -->
                        <div id="vk_like" style="width: 100px;"></div>
               <!--         <script type="text/javascript">
                            VK.Widgets.Like("vk_like", {type: "button"});
                        </script>-->
                    </div>

                    <div class="social" style="float: left; width: 75px;">
                        <!-- GOOGLE +1 -->
                        <!-- Place this tag where you want the +1 button to render -->
                        <g:plusone size="medium"></g:plusone>
                        <!-- Place this tag after the last plusone tag -->
                        <script type="text/javascript">
                            window.___gcfg = {lang: 'ru'};
                            (function() {
                                var po = document.createElement('script'); po.type = 'text/javascript'; po.async = true;
                                po.src = 'https://apis.google.com/js/plusone.js';
                                var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(po, s);
                            })();
                        </script>
                    </div>

                    <div class="social" style="float: left; width: 110px;">
                        <a href="http://twitter.com/share" class="twitter-share-button" data-count="horizontal" data-via="Mobilluck" data-lang="ru">Tweet</a>
                        <script type="text/javascript" src="http://platform.twitter.com/widgets.js"></script>
                    </div>


                    <div class="social" style="float: left; width: 125px;">
                        <!-- FACEBOOK -->
                            <div id="fb-root"></div>
                            <script>(function(d, s, id) {
                              var js, fjs = d.getElementsByTagName(s)[0];
                              if (d.getElementById(id)) return;
                              js = d.createElement(s); js.id = id;
                              js.src = "//connect.facebook.net/ru_RU/all.js#xfbml=1";
                              fjs.parentNode.insertBefore(js, fjs);
                            }(document, 'script', 'facebook-jssdk'));</script>
                            <div class="fb-like" data-href="" data-send="true" data-layout="button_count" data-width="130" data-show-faces="false" data-font="arial"></div>
					</div>

                </noindex>
            </div>
        </div>