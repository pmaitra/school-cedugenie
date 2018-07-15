
$(document).ready(function(){
$("#designation").change(
		function() {
			var designation=document.getElementById("designation").value;	
			if(designation== "null" || designation==''){
				return;
			}
			$.ajax({
		        url: '/cedugenie/getDesignationSalaryDetails.html',
		        data: "designation=" +($(this).val())+ "&parameter=DESIGNATION",
		        success: function(data) {
//		        	alert(data);
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
		val = parseInt(val);
		if(val<0){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Invalid Amount. (+ve Numbers Only.)";
			box.value="0.0";
		}
	}
}
function clearMarks(box){
	if(box.value=="0.0"){
		box.value="";
	}
}