/*!
 * Combobox Plugin for jQuery, version 0.5.0
 *
 * Copyright 2012, Dell Sala
 * http://dellsala.com/
 * https://github.com/dellsala/Combo-Box-jQuery-Plugin
 * Dual licensed under the MIT or GPL Version 2 licenses.
 * http://jquery.org/license
 *
 * Date: 2012-01-15
 */
(function () {

    jQuery.fn.combobox = function (selectOptions) {

        return this.each(function () {
            var newCombobox = new Combobox(this, selectOptions);
            jQuery.combobox.instances.push(newCombobox);
        });

    };

    jQuery.combobox = {
        instances : []
    };


    var Combobox = function (textInputElement, selectOptions) {
        this.textInputElement = jQuery(textInputElement);
        var container = this.textInputElement.wrap(
            '<span class="combobox" style="position:relative; '+
            'display:-moz-inline-box; display:inline-block;"/>'
        );
        this.selector = new ComboboxSelector(this);
        this.setSelectOptions(selectOptions);
        var inputHeight = this.textInputElement.outerHeight();
        var buttonLeftPosition = this.textInputElement.outerWidth() + 0;
        var showSelectorButton = jQuery(
            '<a href="#" class="combobox_button" '+
            'style="position:absolute; height:1px; width: 1px;'+
            'top:0; left:'+buttonLeftPosition+'px;"><div class="combobox_arrow"></div></a>'
        ).insertAfter(this.textInputElement);
        //this.textInputElement.css('margin', '0 '+showSelectorButton.outerWidth()+'px 0 0');
        var thisSelector = this.selector;
        var thisCombobox = this;
        showSelectorButton.click(function (e) {
            jQuery('html').trigger('click');
            thisSelector.buildSelectOptionList();
            thisSelector.show();
            thisCombobox.focus();
            return false;
        })
        this.bindKeypress();
    };

    Combobox.prototype = {

        setSelectOptions : function (selectOptions) {
            this.selector.setSelectOptions(selectOptions);
            this.selector.buildSelectOptionList(this.getValue());
        },

        bindKeypress : function () {
            var thisCombobox = this;
            this.textInputElement.keyup(function (event) {
                if (event.keyCode == Combobox.keys.TAB
                    || event.keyCode == Combobox.keys.SHIFT)
                {
                    return;
                }
                if (event.keyCode != Combobox.keys.DOWNARROW
                    && event.keyCode != Combobox.keys.UPARROW
                    && event.keyCode != Combobox.keys.ESCAPE
                    && event.keyCode != Combobox.keys.ENTER)
                {
                    thisCombobox.selector.buildSelectOptionList(thisCombobox.getValue());
                }
                thisCombobox.selector.show()
            });
        },

        setValue : function (value) {
            //alert("setvalue");
            //var oldValue = this.textInputElement.val();
            //this.textInputElement.val(value);
            //if (oldValue != value) {
            //    this.textInputElement.trigger('change');
            //}
        },

        getValue : function () {
            return this.textInputElement.val();
        },

        focus : function () {
            //alert("1");
            this.textInputElement.trigger('focus');
        }

    };

    Combobox.keys = {
        UPARROW : 38,
        DOWNARROW : 40,
        ENTER : 13,
        ESCAPE : 27,
        TAB : 9,
        SHIFT : 16
    };



    var ComboboxSelector = function (combobox) {
        this.combobox = combobox;
        this.url = "";
        this.enter = false;
        this.optionCount = 0;
        this.selectedIndex = -1;
        this.allSelectOptions = [];
        var selectorTop = combobox.textInputElement.outerHeight();
        var selectorWidth = combobox.textInputElement.outerWidth();

        //var pos = $("#searchsw").position();
        //alert($("#searchsw2").position().top);
        //alert($("#searchsw2").position().left);

        this.selectorElement = jQuery(
            '<div class="combobox_selector" '+
            'style="display: none; width:'+selectorWidth+
            'px; position: absolute; left: '+$("#searchsw2").position().left+'px; top: '+($("#searchsw2").position().top-5)+'px;"'+
            //'px; position: absolute; z-index: 999;"'+
            '></div>'
        //).insertAfter(this.combobox.textInputElement);
        ).insertAfter($("#foot-items"));
        //'px; position: absolute; left: '+$("#searchsw2").position().left+'px; top: '+($("#searchsw2").position().top-5)+'px;"'+
        //this.combobox.textInputElement

        /*
        this.selectorElement = jQuery(
            '<div style="position: relative; z-index: 1001;" id="ns"></div>'
        ).appendTo(this.selectorElement2);
        */

        var thisSelector = this;
        this.keypressHandler = function (e) {
            thisSelector.enter = false;
            if (e.keyCode == Combobox.keys.DOWNARROW) {
                /*
                if($('body,html').css('position')!="relative")
                {
                    $('body,html').css('position', 'relative');
                    $('body,html').css('overflow', 'hidden');
                    $('body,html').css('height', '100%');

                    var l = $(".combobox_selector").css("left");
                    l = l.substr(0, l.length-2);
                    l = (l*1)+9;
                    $(".combobox_selector").css('left', l+'px');
                }
                */
                /*
                $('body,html').scroll(function () {
                    return false;
                });
                */
                thisSelector.selectNext();
            } else if (e.keyCode == Combobox.keys.UPARROW) {
                /*
                if($('body,html').css('position')!="relative")
                {
                    $('body,html').css('position', 'relative');
                    $('body,html').css('overflow', 'hidden');
                    $('body,html').css('height', '100%');

                    var l = $(".combobox_selector").css("left");
                    l = l.substr(0, l.length-2);
                    l = (l*1)+9;
                    $(".combobox_selector").css('left', l+'px');
                }
                */
                /*
                $('body,html').scroll(function () {
                    return false;
                });
                */
                thisSelector.selectPrevious();
            } else if (e.keyCode == Combobox.keys.ESCAPE) {
                thisSelector.hide();
                thisSelector.combobox.focus();
            } else if (e.keyCode == Combobox.keys.ENTER) {
                thisSelector.enter = true;
                //alert("1");
                //alert("А тут мф доделаем переход по урлу!");
                //alert(thisSelector.getSelectedIndex());
                //alert($("#cbb"+thisSelector.getSelectedIndex()).val());
                //location.href = $("#cbb"+thisSelector.getSelectedIndex()).val();
                //alert("по энтеру "+thisSelector.url);
                if(thisSelector.url!="")
                    location.href = thisSelector.url;
                return false;
                /*
                thisSelector.combobox.setValue(thisSelector.getSelectedValue());
                thisSelector.combobox.focus();
                thisSelector.hide();
                */
            }
            return false;
        }

    }


    ComboboxSelector.prototype = {

        setSelectOptions : function (selectOptions) {
            this.allSelectOptions = selectOptions;
        },

        buildSelectOptionList : function (startingLetters) {
            if (! startingLetters) {
                startingLetters = "";
            }
            this.unselect();
            this.selectorElement.empty();
            var selectOptions = [];
            this.selectedIndex = -1;
            for (var i=0; i < this.allSelectOptions.length; i++) {
                if (! startingLetters.length
                    || this.allSelectOptions[i].toLowerCase().indexOf(startingLetters.toLowerCase()) === 0)
                {
                    selectOptions.push(this.allSelectOptions[i]);
                }
            }
            this.optionCount = selectOptions.length;
            var ulElement = jQuery('<ul class="combo"></ul>').appendTo(this.selectorElement);

            for (var i = 0; i < selectOptions.length; i++) {
                ulElement.append('<li lid="'+i+'">'+selectOptions[i]+'</li>');
            }
            var thisSelector = this;
            this.selectorElement.find('li').click(function (e) {
                //alert("keydown");
                //var si = thisSelector.getSelectedIndex();
                //var url = $("#cbb"+si).val();
                //alert(thisSelector.url);
                //alert("по клику "+thisSelector.url);
                location.href = thisSelector.url;
                //alert("keydown="+si);
                return false;
                //alert("А тут мф доделаем переход по урлу!");
                //thisSelector.hide();
                //thisSelector.combobox.setValue(this.innerHTML);
                //thisSelector.combobox.focus();
            });
            this.selectorElement.find('li:last').unbind('click');
            this.selectorElement.find('li').mouseover(function (e) {
                //this.selectorElement.find('li').removeClass('selected');
                //alert($(this).attr('lid'));
                var si = $(this).attr('lid');
                //alert("mouseover="+si);
                thisSelector.url = $("#cbb"+si).val();
                //thisSelector.url();
            });
            this.selectorElement.mouseover(function (e) {
                thisSelector.unselect();
            });
            this.htmlClickHandler = function () {
                thisSelector.hide();
            };
            this.selectorElement.find('li').keydown(function (e) {
                //alert("keydown");
                //var si = thisSelector.getSelectedIndex();
                //alert("keydown="+si);
            });
        },

        show : function () {
            if (this.selectorElement.find('li').length < 1
                || this.selectorElement.is(':visible'))
            {
                return false;
            }
            jQuery('html').keyup(this.keypressHandler);
            this.selectorElement.slideDown('fast');
            //this.selectorElement.css('z-index', 100);
            //alert(this.selectorElement.css('z-index'))
            jQuery('html').click(this.htmlClickHandler);

            return true;
        },

        hide : function () {
            jQuery('html').unbind('keyup', this.keypressHandler);
            jQuery('html').unbind('click', this.htmlClickHandler);
            this.selectorElement.unbind('click');
            this.unselect();
            this.selectorElement.hide();
            /*
            if($('body,html').css('position')=="relative")
            {
                $('body,html').css('position', 'static');
                $('body,html').css('overflow', 'auto');
                $('body,html').css('height', 'auto');
                var l = $(".combobox_selector").css("left");
                l = l.substr(0, l.length-2);
                l = (l*1)-9;
                $(".combobox_selector").css('left', l+'px');
            }
            */
        },

        selectNext : function () {
            var newSelectedIndex = this.selectedIndex + 1;
            if (newSelectedIndex > this.optionCount - 1) {
                newSelectedIndex = 0;
            }
            this.select(newSelectedIndex);
            this.url = $("#cbb"+newSelectedIndex).val();
            //$("#searchsw").blur();
            //focus(this);
        },

        selectPrevious : function () {
            var newSelectedIndex = this.selectedIndex - 1;
            if (newSelectedIndex < 0) {
                newSelectedIndex = this.optionCount - 1;
            }
            this.select(newSelectedIndex);
            this.url = $("#cbb"+newSelectedIndex).val();
            //$("#searchsw").blur();
            //focus(this);
        },

        select : function (index) {
            this.unselect();
        	this.selectorElement.find('li:eq('+index+')').addClass('selected');
        	this.selectedIndex = index;
        },

        unselect : function () {
        	this.selectorElement.find('li').removeClass('selected');
        	this.selectedIndex = -1;
        },

        getSelectedValue : function () {
        	return this.selectorElement.find('li').get(this.selectedIndex).innerHTML;
        },

        getSelectedIndex : function () {
        	return this.selectedIndex;
        }

    };


})();