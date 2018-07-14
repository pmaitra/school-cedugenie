
$(document).ready(function(){
$("#employeeCode").change(
		function() {
			var employeeCode=document.getElementById("employeeCode").value;	
			if(employeeCode== "null" || employeeCode==''){
				return;
			}
			$.ajax({
		        url: '/icam/getStaffSalaryDetails.html',
		        data: "employeeCode=" +($(this).val()),
		        success: function(data) {
		        	if(data != ""){
			        	var sm=data.split("*");
			        	$("#salaryTemplate option").each(function(){
			        		if($(this).text() == sm[0])
			        			{
			        				$(this).prop("selected",true);
			        			}
			        	});
			        	var payBandSelect = document.getElementById("payBand");
		        		var gradePaySelect = document.getElementById("gradePay");
		        
		        		for(var i=payBandSelect.length;i>0;i--)
						{
		        			payBandSelect.remove(i);
						} 
		        		for(var j=gradePaySelect.length;j>0;j--)
						{
							gradePaySelect.remove(j);
						} 
			        	var defaultSelected = false;
			        	var nowSelected = true;
			        	var fetchedPayBand = sm[1];
			        	var fetchedPayBandCode = sm[14];
			        	var fetchedgradePay = sm[2];
			        	var fetchedgradePayCode = sm[3];
			        	$("#payBand").append(new Option(fetchedPayBand,fetchedPayBandCode,defaultSelected,nowSelected));
			        	$("#gradePay").append(new Option(fetchedgradePay,fetchedgradePayCode,defaultSelected,nowSelected));
			        	document.getElementById("basic").value=sm[4];
			        	document.getElementById("gpf").value=sm[5];
			        	document.getElementById("ed").value=sm[6];
			        	document.getElementById("wc").value=sm[7];		        	
			        	document.getElementById("freeElectricCharge").value=sm[8];
			        	document.getElementById("ip").value=sm[9];
			        	document.getElementById("gip").value=sm[10];
			        	document.getElementById("pt").value=sm[11];
			        	document.getElementById("nps").value=sm[12];
			        	document.getElementById("npsBoth").value=sm[13];
			        	document.getElementById("updateButton").style.visibility = "visible";
			        	document.getElementById("submitButton").style.visibility = "collapse";
			        }
		        	if(data == ""){		        
		        		document.getElementById("updateButton").style.visibility = "collapse";
			        	document.getElementById("submitButton").style.visibility = "visible";
		        		var payBandSelect = document.getElementById("payBand");
		        		var gradePaySelect = document.getElementById("gradePay");
		        		$("#salaryTemplate option").each(function(){
			        		$(this).removeAttr("selected");
			        	});
		        		for(var i=payBandSelect.length;i>0;i--)
						{
		        			payBandSelect.remove(i);
						} 
		        		for(var j=gradePaySelect.length;j>0;j--)
						{
							gradePaySelect.remove(j);
						} 
		        		document.getElementById("basic").value="0";
			        	document.getElementById("gpf").value="0";
			        	document.getElementById("ed").value="0";
			        	document.getElementById("wc").value="0";		        	
			        	document.getElementById("freeElectricCharge").value="0";
			        	document.getElementById("ip").value="0";
			        	document.getElementById("gip").value="0";
			        	document.getElementById("pt").value="0";
			        	document.getElementById("nps").value="0";
			        	document.getElementById("npsBoth").value="0";
		        	}
		        		        	
		        }
		}); 
	});
});

function getGradesForSalaryTemplate(pb){
	var value = pb.value;
	$.ajax({
        url: '/icam/getGradesForSalaryTemplate.html',
        dataType: 'json',
        data: "salaryTemplate=" + value,
        success: function(dataDB) {	        	
        	var selectNode = document.getElementById("payBand");        	
        	if(dataDB != null)
			{
        		for(var i=selectNode.length;i>0;i--){
        			selectNode.remove(i);
	        	}
        		var arr = dataDB.split("@");
        			
				for (var i=0;i<arr.length-1;i++){
					var codeAndValue=arr[i].split("~");	
					var option = document.createElement("option");
					option.text = codeAndValue[1];
					option.value = codeAndValue[0];
					selectNode.appendChild(option);
				}
			}
        	if(dataDB == null){
        		for(var j=selectNode.length;j>0;j--){
        			selectNode.remove(j);
	        	}
        	}
       }
	});	
}


function getAppointmentsToPostsWithGradePay(pb){
	var payBand = pb.value;
	var salTemp = document.getElementById("salaryTemplate").value;
	$.ajax({
        url: '/icam/getAppointmentsToPostsWithGradePay.html',
        dataType: 'json',        
        data: "payBand=" + payBand + "&salTemp=" + salTemp,
        success: function(dataDB) {	
        	var selectNode = document.getElementById("gradePay");        	
        	if(dataDB != null)
			{
        		for(var i=selectNode.length;i>0;i--){
        			selectNode.remove(i);
	        	}
        		var arr = dataDB.split("@");
        			
				for (var i=0;i<arr.length-1;i++){
					var codeAndValue=arr[i].split("~");	
					var option = document.createElement("option");
					option.text = codeAndValue[1];
					option.value = codeAndValue[0];
					selectNode.appendChild(option);
				}
			}
        	if(dataDB == null){
        		for(var j=selectNode.length;j>0;j--){
        			selectNode.remove(j);
	        	}
        	}
       }
	});	
}

function staffSalaryDetailsValidation(){	
	obj=document.getElementById("employeeCode");
	val=obj.value;
	if(val==""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Select Employee.";
		return false;
	}
	
	obj=document.getElementById("salaryTemplate");
	val=obj.value;
	if(val==""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Select Template.";
		return false;
	}
	
	obj=document.getElementById("payBand");
	val=obj.value;
	if(val==""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Select Pay.";
		return false;
	}
	
	
	obj=document.getElementById("gradePay");
	val=obj.value;
	if(val==""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Select Grade.";
		return false;
	}
	
	return true; 
}

function setZero(tBox){
	document.getElementById('warningbox').style.visibility = "collapse";
	if(tBox.value==""){
		tBox.value="0";
	}		
	if(isNaN(tBox.value)){
		document.getElementById('warningbox').style.visibility="visible";
		document.getElementById('warningmsg').innerHTML="Please Enter Proper Numeric Value";
		tBox.value="0";
	}else{
		var p=parseFloat(tBox.value);
		if(p<0){
			document.getElementById('warningbox').style.visibility="visible";
			document.getElementById('warningmsg').innerHTML="Please Enter a positive Number";
			tBox.value="0";
		}
	}		
}



