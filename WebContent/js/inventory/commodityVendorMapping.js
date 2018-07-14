$(document).ready(function(){
	/*$('#popupBoxNo').click( function() {  
		unloadPopupBox();
	}); 		

	function loadPopupBox() {    // To Load the Popupbox
		$('#dialog').fadeIn("fast");
		$("#wrap").css({ // this is just for style
		    "opacity": "0.3"
		});
	}
	
	function unloadPopupBox() {    // TO Unload the Popupbox
	    $('#dialog').fadeOut("fast");
	    $("#wrap").css({ // this is just for style       
	        "opacity": "1" 
	    });
	}*/
	
	$("#vendorName").change(function() {
		document.getElementById("vendorCode").value=$(this).val();
		var financialYear = $("#financialYear").val();
		//alert("financialYear=="+financialYear);
		$.ajax({
			url:' /icam/vendorCommodityList.html',
			data:'vendorCode='+($(this).val())+"&financialYear=" +financialYear,
			dataType: 'json',
			success: function(data)
			{
				var checkLength = $('input:checkbox').length;
				for (var i = 0; i < checkLength; i++) {
					var chckid = "check"+i;
					var txtid = "txt"+i;
					document.getElementById(chckid).checked=false;
					document.getElementById(txtid).value='0.00';
					document.getElementById(txtid).readOnly = false;
					txtid = "old"+i;
					document.getElementById(txtid).value='0.00';
				}
				
				if(data != ""){
					var inputs = document.getElementsByTagName("input"); 
					data=data.split("+-+");
					
					for (var i = 0; i < inputs.length; i++) {
						if (inputs[i].type == "checkbox"){
							for(var j=0;j<data.length;j++)
							{
								var s=data[j].split("##");
								var s0=s[1];
								var s1=s[0];;
								var s2=inputs[i].value;
								if(s1!=s2)
								{
									continue;
								}
								else{
									inputs[i].checked=true;
									var tbid=(inputs[i].id).replace("check","txt");
									document.getElementById(tbid).value=s0;
									 document.getElementById(tbid).readOnly = true;
									tbid=(inputs[i].id).replace("check","old");
									document.getElementById(tbid).value=s0;
									break;
								}
							}
						}  
					}
				}
			}
		}); 
	});	
	
	/*$(".clearbtn").each(function(){		
		$(this).click(function(){
			var commodityCode=this.id;
			var vendorCode=document.getElementById("vendorCode").value;
			if(vendorCode!=""){
				$.ajax({
					url:' /icam/vendorCommodityPriceHistory.html',
					data:"vendorCode=" + vendorCode+ "&commodityCode=" +commodityCode,
					dataType: 'json',
					success: function(data)
					{
						var allData="";
						if(data=="")
							allData=allData+"<tr><th>"+commodityCode+" Was Never Supplied By "+vendorCode+"</th></tr>";
						else{
							allData=allData+"<tr><th>Date</th><th>Price</th></tr>";
							data=data.split("~*~");
							for(var i=0;i<data.length-1;i++){
								var temp=data[i].split("*~*");
								allData=allData+"<tr><td>"+temp[0]+"</td><td>"+temp[1]+"</td></tr>";
							}
						}
						loadPopupBox();
						document.getElementById("ShowData").innerHTML=allData;
					}
				});
			}else{
				alert("Please Select Vendor First.");
			}
		});
	});*/
	
});



function validate(){
	
	if(document.getElementById('vendorName').value==""){
//		document.getElementById('warningbox').style.visibility="visible";
//		document.getElementById('warningmsg').innerHTML="Select Vendor Name";
		alert("Select Vendor Name");
		return false;
	}
	
	var count=0;
	var checkboxes = document.getElementsByName("commodity");
	for (var i=0; i<checkboxes.length; i++) {
		if (checkboxes[i].checked) {
			count++;
			var chkbxid=checkboxes[i].id;
			var chkbxval=document.getElementById(chkbxid).value;
			
			var selid=chkbxid.replace("check", "txt");
			var txtval=document.getElementById(selid).value;
			if(txtval==""){
				alert("Enter Price For "+chkbxval);
				return false;
			}else{
				if(isNaN(txtval)){
					alert("Please Enter Numeric Rate For "+chkbxval);
					return false;		
				}
				txtval=parseFloat(txtval);
				if(txtval<=0.0){
					alert("Please Enter Positive Rate For "+chkbxval);
					return false;	
				}
			}
		}
	}
	if(count<=0){
		alert("Please Select Atleast One Commodity To Allot");
		return false;
	}
}