	function change_delivery()
	{
		var city_id = $("#clienttown").val();
		var payment_id = $("#clientoplata_f").val();
		var fopcredit = $("#fopcredit").val();

		var tmp_array = Array();
		var tmp_arraywf = Array();
		var tmp_array_fid = Array();
		var tmp_array_name = Array();
		var tmp_array2 = Array();
		$.ajax({
			type: "POST",
			url: "/ajx/ajx_cart.php",
			data: 'cmd=uh_com_get_delivery_type&city_id='+city_id+'&payment_id='+payment_id+'&fc='+fopcredit+'&csi=c4vp2biauqd93btpr1mf2dlor3',
			dataType: "text",
			success: function(data)
			{
				var tmp = data.split("|");
				//tmp_array = tmp[1].split(";");

				tmp_arraywf = tmp[1].split(";");
				for(i=0; i<tmp_arraywf.length;i++)
				{
					var ttt = tmp_arraywf[i].split(",");
					tmp_array[tmp_array.length] = ttt[0];
					tmp_array_fid[tmp_array_fid.length] = ttt[1];
					tmp_array_name[tmp_array_name.length] = ttt[2];
				}

				if( $("#adress_fr").css("display") != "none" )
				{
					if( tmp[0] == 2 )
					{
						if(in_array("1", tmp_array))
						{
							tmp[0] = "1";
						}
					}
				}

				if(!in_array($('#clientdeliverytype_f').val(), tmp_array))
					$("#clientdeliverytype_f [value='"+tmp[0]+"']").attr("selected", "selected");

				$('#clientdeliverytype_f option').each(function()
				{
					if(!in_array(this.value, tmp_array))
						this.disabled = "disabled";
					else
						this.disabled = "";

					for(i=0; i<tmp_array.length;i++)
					{
						if(this.value == tmp_array[i])
						{
							this.setAttribute("fid", tmp_array_fid[i]);
							this.innerHTML = tmp_array_name[i];
						}
					}
				});
				$("#clientdeliverytype_f").prev().remove();
				$("#clientdeliverytype_f").selectbox();
				load_branch();
				delivery_type($("#clientdeliverytype_f").val());
				refresh_cart('c4vp2biauqd93btpr1mf2dlor3', '0', '');
			}
		});
	}
	
	
	function selcreditperiod(val)
	{
		if(val!="")
		{
		   var dt = new Date();
		   dt.setHours(dt.getHours()+24*180);
		   var pcval = makeCookie("srokcredit", val, dt.toUTCString(), "/", window.location.hostname);
		   setCookie( pcval );
		   refresh_cart('c4vp2biauqd93btpr1mf2dlor3','0', '');
		}
	}
	
	
	function setpromo(lnk, sesid )
	{
		var promo = $('#promo').val();
		if(ajxid==false)
		{
			ajxid = true;
			$.ajax({
				type: "POST",
				url: "/ajx/ajx_jq.php",
				data: 'cmd=uh_com_promo&promo='+promo+'&csi='+sesid,
				dataType: "text",
				success: function(data){
					if(data=="Промо-код " + promo + " применен!")
					{
						var cod = 'Введен промо-код: <b>'+promo+'</b>'+
						"<br />" +
						"<a href='javascript:void();' onclick='delpromo(" + '"' + sesid +'"'+", "+'"'+promo+'"'+")'>отменить использование промо-кода</a>";
						$("#promo_ok").html(cod);
						$("#promo_ok").css("display", "block");
						$("#promo_input").css("display", "none");
						$("#promo_no").css("display", "none");
					}
					else
					{
						$("#promo_no").html(data);
						$("#promo_no").css("display", "block");
						$("#promo_ok").css("display", "none");
						$("#promo_input").css("display", "block");
						ajxid = false;
					}
					refresh_cart('c4vp2biauqd93btpr1mf2dlor3', '0', '');
			   }
			});

		}
		return false;
	}

	function delpromo(sesid, promo )
	{
		$.ajax({
			type: "POST",
			url: "/ajx/ajx_jq.php",
			data: 'cmd=uh_com_promodel&promo='+promo+'&csi='+sesid,
			dataType: "text",
			success: function(data){
				$("#promo_input").css("display", "block");
				$("#promo_ok").css("display", "none");
				$("#promo_no").css("display", "none");
				ajxid = false;
				refresh_cart('c4vp2biauqd93btpr1mf2dlor3', '0', '');
			}
		});
		return false;
	}