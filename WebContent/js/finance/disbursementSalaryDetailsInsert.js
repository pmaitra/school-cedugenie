
$(document).ready(function(){
$("#employee").change(
		function() {
			var employeeCode=document.getElementById("employee").value;	
			if(employeeCode== "null" || employeeCode==''){
				return;
			}
			$.ajax({
		        url: '/icam/getStaffSalaryDetails.html',
		        data: "employeeCode=" +($(this).val()),
		        success: function(data) {
		        	if(data != ""){
			        	var sm=data.split("*");
			        	document.getElementById("pay").value=parseFloat(sm[4])+parseFloat(sm[2]);
			        	document.getElementById("basic").value=sm[4];
			        	document.getElementById("gradePay").value=sm[2];
			        	document.getElementById("gpf").value=sm[5];
			        	document.getElementById("ed").value=sm[6];
			        	document.getElementById("wc").value=sm[7];		        	
			        	document.getElementById("freeElectricCharge").value=sm[8];
			        	document.getElementById("ip").value=sm[9];
			        	document.getElementById("gip").value=sm[10];
			        	document.getElementById("pt").value=sm[11];
			        	document.getElementById("nps").value=sm[12];
			        	document.getElementById("npsBoth").value=sm[13];
			        	
			        }
		        }	
		 });
			
		$.ajax({
	        url: '/icam/getDesignationSalaryDetails.html',
	        data: "designation=" +($(this).val())+ "&parameter=EMPLOYEE",
	        success: function(data) {
	        	if(data != ""){
		        	var sm=data.split("*");
		        	document.getElementById("da").value=sm[0];
		        	document.getElementById("tptl").value=sm[1];
		        	document.getElementById("smaHma").value=sm[2];
		        	document.getElementById("ma").value=sm[3];
		        	document.getElementById("sa").value=sm[4];
		        	document.getElementById("gpf").value=sm[5];
		        	document.getElementById("cpf").value=sm[6];
		        	document.getElementById("meterCharge").value=sm[7];
		        	document.getElementById("upto100ECRate").value=sm[8];
		        	document.getElementById("above100ECRate").value=sm[9];
		        	document.getElementById("status").value=sm[10];
		        	
	        	}else{
	        		document.getElementById("da").value="0.0";
		        	document.getElementById("tptl").value="0.0";
		        	document.getElementById("smaHma").value="0.0";
		        	document.getElementById("ma").value="0.0";
		        	document.getElementById("sa").value="0.0";
		        	document.getElementById("gpf").value="0.0";
		        	document.getElementById("cpf").value="0.0";
		        	document.getElementById("meterCharge").value="0.0";
		        	document.getElementById("upto100ECRate").value="0.0";
		        	document.getElementById("above100ECRate").value="0.0";
		        	document.getElementById("status").value="INSERT";
	        	}
	        		
	        		        	
	        }
	}); 

		
			
	});
});

function setZero(tBox){
	
}

function validateBox(box){
	var intRegx=numeric=/^[0-9]{1,}$/;
	var doubleRegx=/^[0-9]{1,}.[0-9]{1,}$/;
	var val=box.value;
	val=val.replace(/\s{1,}/g,'');
	box.value=val;
	if((!val.match(intRegx)) && (!val.match(doubleRegx))){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Invalid Amount. Only Numeric Allowed";
		box.value="0.0";
	}else{
		val = parseFloat(val);
		if(val<0){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Invalid Amount. (+ve Numbers Only.)";
			box.value="0.0";
		}
	}
}
function clearBox(box){
	if(box.value=="0.0"){
		box.value="";
	}
}

function makeChequeReadOnly(select, chequeId){
	if(select.value=="CASH A/C"){
		document.getElementById(chequeId).value="";
		document.getElementById(chequeId).setAttribute("readonly","readonly");
	}else{
		document.getElementById(chequeId).removeAttribute("readonly","readonly");
	}
}

function calculateECharge(meter){
	validateBox(meter);
	var val=parseFloat(meter.value);	
	var freeElectricCharge=parseFloat(document.getElementById("freeElectricCharge").value);
	val=val-freeElectricCharge;
	var upto100ECRate=parseFloat(document.getElementById("upto100ECRate").value);
	var above100ECRate=parseFloat(document.getElementById("above100ECRate").value);
	var amount=parseFloat(document.getElementById("meterCharge").value);
	if(val<=100){
		amount=amount+(val*upto100ECRate);
	}else{
		amount=amount+(100*upto100ECRate);
		amount=amount+((val-100)*above100ECRate);
	}
	document.getElementById("electricCharge").value=amount;
}