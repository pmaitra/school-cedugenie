function validate(){
	
	var year= document.getElementById("year"); 
	if(year.value == "select"){
		
		document.getElementById("warningbox").style.visibility = "visible";
	  	document.getElementById("warningmsg").innerHTML= "Select the Academic year.";
		return false;
	}
	return true;
}
function removeZero(box){
	if(box.value=="0"){
		box.value="";
	}
}
function setZero(tBox){
	//document.getElementById('warningbox').style.visibility = "collapse";
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

$(document).ready(function(){
	$("#jobTypeName").change(function(){
		
		var tb= document.getElementsByClassName("textfield2");
		for(var i=0;i<tb.length;i++){
			tb[i].value="0";
		}
		if($(this).val()== "null" || $(this).val()==''){
			return;
		}
		
		$.ajax({
	        url: '/cedugenie/getEmployeeCompleteLeaveDetails.html',
	        data: "jobTypeName=" +($(this).val())+ "&employeeType=" + ($("#employeeTypeName").val())+ "&academicYear=" + ($("#year").val()),
	        success: function(data) {
	        	if(null!=data && data!=""){
		        	data=data.split("#");
		        	for(var i=0;i<data.length-1;i++){
		        		var p= data[i].split("*");			        		
		        		document.getElementById(p[0]+"leaveDuration").value=p[1];
		        		//document.getElementById(p[0]+"leaveLimit").value=p[2];
		        		document.getElementById(p[0]+"leaveEncashment").value=p[3];
		        		document.getElementById(p[0]+"leaveCarryForward").value=p[4];
		        		document.getElementById("oldLeaveType").value=p[0];		//added by saif 20-03-2018
		        		document.getElementById("oldLeaveDuration").value=p[1];	//added by saif 20-03-2018
		        		document.getElementById("oldLeaveEncashment").value=p[3]; //added by saif 20-03-2018
		        		document.getElementById("oldLeaveCarryForward").value=p[4]; //added by saif 20-03-2018
		        	}
		        	document.getElementById("status").value="UPDATE";
	        	}else{
	        		document.getElementById("status").value="INSERT";
	        	}
	        }
		});
		document.getElementById("details").style.visibility="visible";
	});
	
	$("#employeeTypeName").change(function(){
		document.getElementById("details").style.visibility="collapse";
		document.getElementById("jobTypeName").value="";
		var tb=getElementsByClassName("textfield2");
		for(var i=0;i<tb.length;i++){
			tb[i].value="0";
		}
		var sel=getElementsByClassName("defaultselect1");
		for(var i=0;i<sel.length;i++){
			sel[i].value="false";
		}
	});
	$("#year").change(function(){
		document.getElementById("details").style.visibility="collapse";
		document.getElementById("employeeTypeName").value="";
		document.getElementById("jobTypeName").value="";
		var tb=getElementsByClassName("textfield2");
		for(var i=0;i<tb.length;i++){
			tb[i].value="0";
		}
		var sel=getElementsByClassName("defaultselect1");
		for(var i=0;i<sel.length;i++){
			sel[i].value="false";
		}
	});
});





