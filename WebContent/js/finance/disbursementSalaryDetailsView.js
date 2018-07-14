
$(document).ready(function(){
$("#employeeCode").change(
		function() {
			var employeeCode=document.getElementById("employeeCode").value;	
			if(employeeCode== "null" || employeeCode==''){
				return;
			}
			$.ajax({
		        url: '/icam/viewStaffDisbursedSalaryDetails.html',
		        data: "employeeCode=" +($(this).val())+ "&month=" + ($("#month").val()),
		        success: function(data) {
		        	if(data != ""){
			        	var sm=data.split("*");			        	
			        	document.getElementById("gradePay").innerHTML=sm[1];
			        	document.getElementById("basic").innerHTML=sm[2];
			        	document.getElementById("da").innerHTML=sm[3];
			        	document.getElementById("smaHma").innerHTML=sm[4];
			        	document.getElementById("tptl").innerHTML=sm[5];
			        	document.getElementById("ma").innerHTML=sm[6];
			        	document.getElementById("sa").innerHTML=sm[7];
			        	document.getElementById("arrear").innerHTML=sm[8];
			        	document.getElementById("miscInc").innerHTML=sm[9];
			        	document.getElementById("ed").innerHTML=sm[10];
			        	document.getElementById("nps").innerHTML=sm[11];
			        	document.getElementById("gpf").innerHTML=sm[12];
			        	document.getElementById("cpf").innerHTML=sm[13];
			        	document.getElementById("npsBoth").innerHTML=sm[14];
			        	document.getElementById("wc").innerHTML=sm[15];
			        	document.getElementById("electricCharge").innerHTML=sm[16];
			        	document.getElementById("ip").innerHTML=sm[17];
			        	document.getElementById("pfl").innerHTML=sm[18];
			        	document.getElementById("fa").innerHTML=sm[19];
			        	document.getElementById("gip").innerHTML=sm[20];
			        	document.getElementById("pt").innerHTML=sm[21];
			        	document.getElementById("it").innerHTML=sm[22];
			        	document.getElementById("miscExpenses").innerHTML=sm[23];
			        	document.getElementById("gross").innerHTML=sm[24];
			        	document.getElementById("total").innerHTML=sm[25];
			        	document.getElementById("netSalary").innerHTML=sm[26];
			        }
		        }	
		 });	
	});
});
