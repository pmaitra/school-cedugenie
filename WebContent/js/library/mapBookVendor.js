window.onload = function(){
	/*var checkbox = getElementsByClassName("listShowHide");
	for(var i=0;i<checkbox.length;i++){
		ShowHideField(checkbox[i].value, 'table', checkbox[i]);
	}*/
};

$(document).ready(function(){
//$('#popupBoxNo').click( function() {  
//	unloadPopupBox();
//}); 
//	
//
//function loadPopupBox() {    // To Load the Popupbox
//	$('#dialog').fadeIn("fast");
//	 $("#table").css({ // this is just for style
//	    "opacity": "0.3"
//	}); 
//} 
//function unloadPopupBox() {    // TO Unload the Popupbox
//    $('#dialog').fadeOut("fast");
//     $("#table").css({ // this is just for style       
//        "opacity": "1" 
//    }); 
//} 
$("#vendorName").change(
	function() {
		var vCode = $(this).val();
		document.getElementById("vendorCode").value=$(this).val();
		$.ajax({
			url:' /cedugenie/getVendorBooks.html',
			data:'vendorCode='+($(this).val()),
			dataType: 'json',
			success: function(data){
				if(data == ""){
					$("#infomsgbox").css('visibility','visible');
					$("#infomsg").text('This vendor is not mapped yet.');
					var inputs = document.getElementsByTagName("input"); 
					for (var i = 0; i < inputs.length; i++) {  
						document.getElementById("vendorCode").value=vCode;
						if (inputs[i].type == "checkbox"){
							inputs[i].checked=false;
						}
						if (inputs[i].type == "text"){
							inputs[i].value='0.0';
						}
					}
				}
				if(data != ""){
					$("#infomsgbox").css('visibility','collapse');
					$("#infomsg").text('');
					var inputs = document.getElementsByTagName("input"); 
					for (var i = 0; i < inputs.length; i++) {  
						document.getElementById("vendorCode").value=vCode;
						if (inputs[i].type == "checkbox"){
							inputs[i].checked=false;
						}
						if (inputs[i].type == "text"){
							inputs[i].value='0.0';
						}
					}
					data=data.split("+-+");
					var p=0;
					for (var i = 0; i < inputs.length; i++) {  
						if (inputs[i].type == "checkbox"){
							
							for(var j=0;j<data.length;j++)
							{
								var s=data[j].split("##");
								var s0=s[1];
								var s1=s[0];
								var s2=inputs[i].value;
								if(s1!=s2)
								{
									continue;
								}
								else{
									inputs[i].checked=true;
									var tbid=inputs[i].id.replace("check","txt");
									document.getElementById(tbid).value=s0;
									break;
								}
							}
							p++;
						}  
					}
				 
				  }
			}
	});
});

$(".clearbtn").each(function(){
	$(this).click(function(){
		var itemId=this.id;
		var vendorId=document.getElementById("vendorCode").value;
		$.ajax({
			url:' /cedugenie/getVendorBookPriceHistory.html',
			data:"strVendorCode=" + vendorId+ "&strItemId=" +itemId,
			dataType: 'json',
			success: function(data)
			{
				var allData="";
				if(data=="")
					allData=allData+"<tr><th>"+itemId+" Was Never Supplied By "+vendorId+"</th></tr>";
				else{
					allData=allData+"<tr><th>Date</th><th>Price</th></tr>";
					data=data.split("~*~");
					for(var i=0;i<data.length-1;i++)
					{
						var temp=data[i].split("*~*");
						allData=allData+"<tr><td>"+temp[0]+"</td><td>"+temp[1]+"</td></tr>";
					}
				}
				loadPopupBox();
				document.getElementById("ShowData").innerHTML=allData;
			}
		});
	});
});


$("#submitButton").click(function(){
	var retVal = true;
	var selectVal = document.getElementById("vendorName").value.replace(/\s{1,}/g,'').length;
	var regPositiveNum = /^[+]?([0-9]+(?:[\.][0-9]*)?|\.[0-9]+)$/;
	var itemCodeRadio = $("input:checked" ).length;
	if(selectVal == 0){
//		$("#warningbox").css('visibility','visible');
//		$("#warningmsg").text('Select a vendor name.');
		alert("Please select a vendor name.");
		return false;
		retVal = false;
	}
	if(itemCodeRadio == 0){
		alert("Please check at least one Asset.");	
		return false;
	}
	if(itemCodeRadio != 0){		
		$('input:checkbox').each(function(){	
			if ($(this).is(':checked'))
			{	
				//document.getElementById("warningbox").style.visibility="collapse";
				var allottTo = $(this).parent().next().next().next().find('input').val();

				if(allottTo == "0.0"){
//					$("#warningbox").css('visibility','visible');
//					$("#warningmsg").text('Enter a proper price rate.');
					alert("Please enter a proper price rate.");
					retVal = false;
					return false;
				}
				if(allottTo != ""){
					if(!allottTo.match(regPositiveNum)){
//						$("#warningbox").css('visibility','visible');
//						$("#warningmsg").text('Enter a proper numeric price rate.');
						alert("Please enter a proper numeric rate.");
						retVal = false;
						return false;
					}					
				}
			}	
		});
	} 	
	return retVal;
});


/*$("#submitButton").click(function(){
	var retVal = true;
	var regPositiveNum = /^[+]?([0-9]+(?:[\.][0-9]*)?|\.[0-9]+)$/;
	var itemCodeRadio = $("input:checked" ).length;
	if(itemCodeRadio == 0){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Please check at least one Asset.";	
		return false;
	}
	if(itemCodeRadio != 0){		
		$('input:checkbox').each(function(){	
			if ($(this).is(':checked'))
			{	
				document.getElementById("warningbox").style.visibility="collapse";
				var allottTo = $(this).parent().next().next().next().find('input').val();

				if(allottTo == "0.0"){
					$("#warningbox").css('visibility','visible');
					$("#warningmsg").text('Enter a proper price rate.');
					retVal = false;
					return false;
				}
				if(allottTo != ""){
					if(!allottTo.match(regPositiveNum)){
						$("#warningbox").css('visibility','visible');
						$("#warningmsg").text('Enter a proper numeric price rate.');
						retVal = false;
						return false;
					}					
				}
			}	
		});
	} 	
	return retVal;*/
}); 
