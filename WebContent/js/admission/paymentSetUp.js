	window.onload=function (){
		if(window.parent.document.getElementById('scheduleInterview')!=null){
			window.parent.document.getElementById('scheduleInterview').style.background = 'cyan';
		}
		if(window.parent.document.getElementById('formSubmission')!=null){
			window.parent.document.getElementById('formSubmission').style.background = 'cyan';
		}
		if(window.parent.document.getElementById('interviewResult')!=null){
			window.parent.document.getElementById('interviewResult').style.background = 'cyan';
		}
		if(window.parent.document.getElementById('buildMeritList')!=null){
			window.parent.document.getElementById('buildMeritList').style.background = 'cyan';
		}
		if(window.parent.document.getElementById('viewMeritList')!=null){
			window.parent.document.getElementById('viewMeritList').style.background = 'green';
		}
		
		
		
		/*var checkbox = getElementsByClassName("listShowHide");
		for(var i=0;i<checkbox.length;i++){
			ShowHideField(checkbox[i].value, 'hscl', checkbox[i]);
		}*/
	};
	
	function ShowAll(cb){
		if(cb.checked){
			var checkbox=getElementsByClassName("listShowHide");
			for(var i=0;i<checkbox.length;i++){
				checkbox[i].checked = true;
				ShowHideField(checkbox[i].value, 'hscl', checkbox[i]);
			}
		}
		else{
			var checkbox=getElementsByClassName("listShowHide");
			for(var i=0;i<checkbox.length;i++){
				checkbox[i].checked = false;
				ShowHideField(checkbox[i].value, 'hscl', checkbox[i]);
			}
		}
		
	}

	$(document).ready(function() { 
		$("#phasedone").click(function() {
			var r=confirm("Are You Sure??") ;
			if (r==true){				
		     	 $.ajax({
		        	url: '/cedugenie/closeOnProcess.html',
		       		dataType: 'json',
		        	data: "admissionDrive=" + ($("#admissionDrive").val()),
		        	success: function(data) {		        		
		        		window.parent.close();
		        	}
				});
			}
		});
	});