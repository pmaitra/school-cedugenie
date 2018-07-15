
$(document).ready(function(){
	$("#commodityName").blur(function(){		
			$.ajax({
				url: '/cedugenie/checkCommodityName.html',		   
				dataType: 'json',
				data: "commodityName=" + ($(this).val()),
				success: function(data) {
					if(data == "YES"){
						//<!-- added by ranita.sur on 21092017 -->

						document.getElementById("javascriptmsg").style.display = 'block';			
						document.getElementById("javascriptmsg").innerHTML = "Commodity Name already exists!";
						//alert("Commodity Name already exists!");	        	 
						$("#commodityName").val("");
						$("#commodityCode").val("");
						return false;
					}else{
						$("#commodityCode").val($("#commodityName").val());
					}
				}  
			});
		});
});

function checkCommodityName(box,codeId){
	var commodityName=box.value;
	commodityName=commodityName.replace(/\s{1,}/g,'');
	var commodityId=box.id;
	if(commodityName==""){
		box.value=document.getElementById(codeId).value;
	}
	$.ajax({
	    url: '/cedugenie/checkCommodityName.html',		   
	    dataType: 'json',
	    data: "commodityName=" + commodityName,
	    success: function(data) {		    	
	    	if(data == "YES"){
	    		//<!-- added by ranita.sur on 21092017 -->

	    		document.getElementById("javascriptmsg").style.display = 'block';			
				document.getElementById("javascriptmsg").innerHTML = "Commodity Name already exists!";
	    		//alert("Commodity Name already exists!");
	    		document.getElementById(commodityId).value=document.getElementById(codeId).value;
        	  	return false;
    		}
	    }  
	});
}

function createSubType(commodityTypeSel){
	var commoditySubTypeSelId=(commodityTypeSel.id).replace("commodityType","commoditySubType");
	
	var type=commodityTypeSel.value;
	var commoditySubType=document.getElementById(commoditySubTypeSelId);
	if(type=="ASSET"){
		commoditySubType.innerHTML='<option value="">Select</option><option value="IT">IT</option><option value="NONIT">NON IT</option>';
		/* added by sourav.bhadra on 27-07-2017
		 * to make commodity sub type enabled and required */
		commoditySubType.removeAttribute("disabled");
		commoditySubType.setAttribute("required","required");
	}
	if(type=="STOCK"){
		commoditySubType.innerHTML='<option value="">Select</option><option value="FOODING">Fooding</option><option value="LODGING">Lodging</option>';
		/* added by sourav.bhadra on 27-07-2017
		 * to make commodity sub type enabled and required */
		commoditySubType.removeAttribute("disabled");
		commoditySubType.setAttribute("required","required");
	}
}


function ValidateForm(){
	var regnum = '^[0-9]+$';
	var commodityName = $("#commodityName").val();
	var commodityDesc = $("#commodityDesc").val();
	var commodityType = $("#commodityType").val();
	var commoditySubType = $("#commoditySubType").val();
	/*var threshold = $("#threshold").val();*/
	
	if(commodityName == ""){
		alert("Please enter a commmodity name.");
		return false;
	}/* modified by sourav.bhadra on 17-08-2017 */
	/*if(commodityDesc == ""){
		alert("Please enter commodity description.");
		return false;
	}*/
	if(commodityType == ""){
		alert("Please select a commodity type.");
		return false;
	}
	if(commoditySubType == ""){
		alert("Please enter commodity subtype.");
		return false;
	}
	/*if(threshold == ""){
		alert("Please select a threshold value.");
		return false;
	}*/
	/*if(threshold == ""){
		if(!threshold.match(regnum)) {
			alert("The input is not valid. Please enter numeric value.");
			return false;
		}
	}
	else{
		return true;
	}*/
};

function createNewSubType(){
	var type=document.getElementById("commodityTypeOptions").value;
	if(type=="ASSET"){
		document.getElementById("commoditySubTypeOptions").innerHTML='<option value="">Select</option><option value="IT">It</option><option value="NONIT">Non It</option>';
	}
	if(type=="STOCK"){
		document.getElementById("commoditySubTypeOptions").innerHTML='<option value="">Select</option><option value="FOODING">Fooding</option><option value="LODGING">Lodging</option>';
	}
};

function updateCommodity(rowId){
	var commodityName = document.getElementById("newCommodityName"+rowId).value;
	var commodityDesc = document.getElementById("commodityDesc"+rowId).value;
	/*var inStock = document.getElementById("inStock"+rowId).value;*/
	/*var threshold = document.getElementById("threshold"+rowId).value;*/
	var commodityType = document.getElementById("commodityType"+rowId).value;
	var commoditySubType = document.getElementById("commoditySubType"+rowId).value;
	var commodityTypeOption = "";
	if(commodityType=="ASSET"){
		commodityTypeOption = "<option value='ASSET' selected>Asset</option><option value='STOCK'>Stock</option>";
	}else{
		commodityTypeOption = "<option value='ASSET'>Asset</option><option value='STOCK' selected>Stock</option>";
	}
	 
	var commoditySubTypeOption1 = "";
	var commoditySubTypeOption2 = "";
	if(commodityType=="ASSET"){
		if(commoditySubType=="IT"){
			commoditySubTypeOption1 = "<option value='IT' selected>IT</option><option value='NONIT'>NON IT</option>";
		}else{
			commoditySubTypeOption1 = "<option value='IT'>IT</option><option value='NONIT' selected>NON IT</option>";
		}
		
	}
	if(commodityType=="STOCK"){
		if(commoditySubType=="FOODING"){
			commoditySubTypeOption1 = "<option value='FOODING' selected>Fooding</option><option value='LODGING'>Lodging</option>'";
		}else{
			commoditySubTypeOption1 = "<option value='FOODING'>Fooding</option><option value='LODGING' selected>Lodging</option>'";
		}
		
	}
	
	$('#updateCommodityDetails > tbody').empty();
 	if((commodityName != null && commodityName!="") /*&& (commodityDesc != null && commodityDesc!="")*/){/* modified by sourav.bhadra on 18-08-2017 */
 		var row = "<tbody>";
 		row += "<tr><td><input type='text' id='commodityname' name='commodityname' class='form-control' value='"+commodityName+"' required></td>"
 		+"<td><input type='text' id='commoditydesc' name='commoditydesc' class='form-control' value='"+commodityDesc+"'></td>"	/* modified */
 		/*+"<td>"+inStock+"</td>"*/
 		/*+"<td><input type='text' id='thresholdVal' name='thresholdVal' class='form-control' value='"+threshold+"' required></td>"*/
 		+"<td><select id='commodityTypeOptions' name='commodityTypeOptions' onchange='createNewSubType();'>"+commodityTypeOption+"</select></td>"
 		+"<td><select id='commoditySubTypeOptions' name='commoditySubTypeOptions'>"+commoditySubTypeOption1+"</select></td></tr>";    				
 		
 		$("#updateCommodityDetails").append(row);
 	}  
	$('#modalInfo').fadeIn("fast");
    
 	var btn = document.getElementById("updateCommodity");
 	btn.setAttribute("onclick","saveCommodityDB('"+rowId+"');");
};

/* modified by sourav.bhadra on 23-10-2017 */
function saveCommodityDB(rowId){
	var reg_name = /^[A-Za-z]+$/;
	var reg_desc = /^[A-Za-z0-9\s]+$/;
	/*var reg_threshold = /^\d+(\.\d{1,2})?$/;*/
	
	var commoditynewname = document.getElementById("commodityname").value;
	var commoditynewdesc = document.getElementById("commoditydesc").value.trim();
	/*var newthreshold = document.getElementById("thresholdVal").value;*/
	var newcommoditytype = document.getElementById("commodityTypeOptions").value;
	var newcommoditysubtype = document.getElementById("commoditySubTypeOptions").value;
	
	var status = true; /* added by sourav.bhadra on 23-10-2017 */
	
	if(commoditynewname == null || commoditynewname == ""){
		document.getElementById("warningmsg").style.display = 'block';			
		document.getElementById("warningmsg").innerHTML = "Commodity Name can not be Empty.";
		status = false;
	}/* modified by sourav.bhadra on 17-08-2017 */
	/*else if(commoditynewdesc == null || commoditynewdesc == ""){
		document.getElementById("warningmsg").style.display = 'block';			
		document.getElementById("warningmsg").innerHTML = "Commodity Description can not be Empty.";
		return false;
	}else if(newthreshold == null || newthreshold == ""){
		document.getElementById("warningmsg").style.display = 'block';			
		document.getElementById("warningmsg").innerHTML = "Threshold can not be Empty.";
		status = false;
	}*/else if(newcommoditytype == null || newcommoditytype == ""){
		document.getElementById("warningmsg").style.display = 'block';			
		document.getElementById("warningmsg").innerHTML = "Please select Commodity Type.";
		status = false;
	}else if(newcommoditysubtype == null || newcommoditysubtype == ""){
		document.getElementById("warningmsg").style.display = 'block';			
		document.getElementById("warningmsg").innerHTML = "Please select Commodity Sub-Type.";
		status = false;
	}else{
		if(!commoditynewname.match(reg_name)){
			document.getElementById("warningmsg").style.display = 'block';			
			document.getElementById("warningmsg").innerHTML = "Commodity Name can contain characters and spaces between words.";
			status = false;
		}
		if(commoditynewdesc != '' && commoditynewdesc != null && commoditynewdesc.length != 0){ /* modified by sourav.bhadra on 18-08-2017 */
			if(!commoditynewdesc.match(reg_desc)){
				document.getElementById("warningmsg").style.display = 'block';			
				document.getElementById("warningmsg").innerHTML = "Commodity Description can contain characters, numbers and spaces between words.";
				status = false;
			}
		}
		/*if(!newthreshold.match(reg_threshold)){
			document.getElementById("warningmsg").style.display = 'block';			
			document.getElementById("warningmsg").innerHTML = "Threshold can contain numbers greater than Zero only.";
			status = false;
		}*/
		if(status == true){
			document.getElementById("saveId").value = rowId;
			document.getElementById("commodityNewName").value=commoditynewname;
			document.getElementById("commodityNewDesc").value=commoditynewdesc;
			//document.getElementById("newThreshold").value=newthreshold;
			document.getElementById("commodityNewType").value=newcommoditytype;
			document.getElementById("commodityNewSubType").value=newcommoditysubtype;
			
			document.getElementById("warningmsg").style.display = 'none';	
			document.editCommodity.submit();
		}
	}
};

function closeWarning(){
	document.getElementById("warningmsg").style.display = 'none';	
};