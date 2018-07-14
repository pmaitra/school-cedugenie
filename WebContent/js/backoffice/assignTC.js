$(document).ready(function(){
	
	
	$("#class").change(
			function() {
				document.getElementById("submit").setAttribute("disabled","disabled");
				document.getElementById("warningbox").style.visibility="collapse";
				$.ajax({
			        url: '/sms/getStreamAgainstClass.html',
			        data: "class=" + ($(this).val()),
			        dataType: 'json',
			        success: function(data) {
			        	if(data == ""){
			        		$("#infomsgbox").css('visibility','visible');
				        	$("#infomsg").text("Class is not created.");
			        	}
			        	if(data != ""){
			        		$("#infomsgbox").css('visibility','collapse');
				        	$("#infomsg").text("");
				        	var sec=data.split("~");
			        		var s4 = document.getElementById("stream");
							for(var i=s4.length;i>0;i--)
							{
								s4.remove(i);
							}
							for(var i=0;i<sec.length-1;i++)
							{
								var s=sec[i].split("*");
								s4.add(new Option(s[1], s[0]),null);
							}
			        	}
			        } 
			}); 
	});
	
	
	$("#stream").change(
			function() {
				document.getElementById("submit").setAttribute("disabled","disabled");
				document.getElementById("warningbox").style.visibility="collapse";
				$.ajax({
			        url: '/cedugenie/getSectionAgainstClassAndStream.html',
			        data:"class=" + ($("#class").val()) + "&stream=" + ($(this).val()),
			        dataType: 'json',
			        success: function(data) {
			        	var i;
			        	var s6=document.getElementById("student");
			        	for(i=s6.length;i>0;i--)
			        	{
			        		s6.remove(i);
			        	}
			        	
			        	document.getElementById("student").value="";
			        	
			        	var s4 = document.getElementById("section");
			        	for(i=s4.length;i>0;i--)
			        	{
			        		s4.remove(i);
			        	}
			        	if(data == ""){
			        		$("#infomsgbox").css('visibility','visible');
				        	$("#infomsg").text("Section is not created for particular class and stream.");
			        	}
			        	if(data != ""){
			        		$("#infomsgbox").css('visibility','collapse');
				        	$("#infomsg").text("");
				        	var sec=data.split("~");
				        	for(i=0;i<sec.length;i++)
				        	{
				        		var s=sec[i].split("*");
								s4.add(new Option(s[1], s[0]),null);
				        	}
				        	s4.remove(i);// remove last null element
			        	}
			        	}
				
			}); 
	});
	
	$("#section").change(
			function() {
				document.getElementById("submit").setAttribute("disabled","disabled");
				document.getElementById("warningbox").style.visibility="collapse";
				$.ajax({
			        url: '/cedugenie/getResourceAgainstSection.html',
			        data:"sectionCode=" + ($("#section").val()),
			        dataType: 'json',
			        success: function(data) {
			        	
				        var s4 = document.getElementById("student");
			        	for(var i=s4.length;i>0;i--)
			        	{
			        		s4.remove(i);
			        	}
			        	if(data == ""){
			        		$("#infomsgbox").css('visibility','visible');
				        	$("#infomsg").text("Resource is not assigned to particular section.");
			        	}
			        	if(data != ""){
			        		$("#infomsgbox").css('visibility','collapse');
				        	$("#infomsg").text("");
				        	var sec=data.split("~");
				        	for(var i=0;i<sec.length;i++)
				        	{
				        		var s=sec[i].split("*");
								s4.add(new Option(s[1], s[0]),null);
				        	}
				        	s4.remove(i);// remove last null element
			        	}
			        }
				
			}); 
	});
	
	
	$("#student").change(
			function() {
				document.getElementById("warningbox").style.visibility="collapse";
				$('#stuID').val($(this).val());
				$.ajax({
			        url: '/cedugenie/checkWheatherFeesPaid.html',
			        data: "student=" +($(this).val())+"&session=" +($("#session").val()),
			        dataType: 'json',
			        success: function(data) {
			        	
		        		document.getElementById("warningbox").style.visibility="visible";
		        		document.getElementById("warningmsg1").innerHTML=data;
		        		
		        		if(document.getElementById("tcCause").value=="diciplinary")
		        		{
		        			document.getElementById("warningmsg2").innerHTML="TC Can Be Granted";
		        			document.getElementById("submit").removeAttribute("disabled");
		        		}else{
		        			if(data=="Fees Clear"){
		        				document.getElementById("warningmsg2").innerHTML="TC Can Be Granted";
		        				document.getElementById("submit").removeAttribute("disabled");
		        			}else{
		        				document.getElementById("warningmsg2").innerHTML="TC Can Not Be Granted";
		        				document.getElementById("submit").setAttribute("disabled","disabled");
		        			}
		        		}
			        }
			}); 
	});

});