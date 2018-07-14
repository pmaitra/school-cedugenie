		$(document).ready( function(){
			
			
			/*$("#standard").change(function (){
				$.ajax({
				url: '/cedugenie/getCourseForStandard.html',
				dataType: 'json',
				data: "standard=" + $("#standard").val(),
				success: function(data) {
					var options='<option value="">Select</option>';
					if(data!=""){
						data=data.split("~");						
						for(var i=0;i<data.length-1;i++){
							var course=data[i].split("*");
							options=options+'<option value="'+course[0]+'">'+course[1]+'</option>';
						}
					}
					document.getElementById("course").innerHTML=options;
				}
				});
				
				
			});*/
			
			$("#course").change(function (){
				
				$.ajax({
				    url: '/cedugenie/getTermsForACourse.html',
				    dataType: 'json',
				    data:"course=" +  $("#course").val(),
				    success: function(data) {	
				    	//alert("data ====="+data);
				    	var options='<option value="">Select</option>';
				    	if(data!=null && data != ""){	        		
			    			var termArr1 = data.split("*~*");
			    			//var courseArr = courseArr1[0].split("#@#");
			    		
			    			for(var a=0; a<termArr1.length;a++){
			    				if(termArr1[a] != null && termArr1[a] != ""){
			    					var termNameAndCode=termArr1[a].split("#@#");  
											options=options+'<option value="'+termNameAndCode[0]+'">'+termNameAndCode[1]+'</option>';
									
			    				}        				
			    			}	        				
				    	}
				    	document.getElementById("term").innerHTML=options;
				    	/*else{
				    		alert("No Course Found For Class ")+ (thisClassNode.value);
				    	}*/
				    }
				});
				
				
				
				
				
				$.ajax({
					url: '/cedugenie/getExamsForCourse.html',
					dataType: 'json',
					data: "course=" + $("#course").val(),
					success: function(data) {
						var options='<option value="">Select</option>';
						if(data!=""){
							data=data.split("~");						
							for(var i=0;i<data.length-1;i++){
								options=options+'<option value="'+data[i]+'">'+data[i]+'</option>';
							}
						}
						document.getElementById("exam").innerHTML=options;
					}
				});
				
			});
			
			$("#term").change(function (){
				document.getElementById("userDefnedExamMarksDiv").style.display = "block";
				$("#tableBody").empty();
				var tableBody=document.getElementById("tableBody");
				$.ajax({
				/*url: '/cedugenie/getSubjectsForACourse.html',
				dataType: 'json',
				data: "course=" + $("#course").val(),*/
				url: '/cedugenie/getSubjectsForTermAndCourse.html',
				dataType: 'json',
				data: "course=" + ($("#course").val())+ "&term=" + ($("#term").val()),
				success: function(data) {
		        	if(data!=null && data!=""){
		        		//data=data.split("*~*");
		        		data=data.split("#*#");
		        		var text="";
		        		for(var i=0;i<data.length-1;i++){
		        			var sub=data[i].split("#~#");
		        			var y =data.length;
		        			document.getElementById("length").value=y;
		        			var calculate="calculate(\""+i+"\");";
		        			var check="check(\""+i+"\");";
		        			
		        			text=text+"<tr class='gradeX'>";
		        			text=text+"<td><input style='width:80px;' type='hidden' id='subject"+i+"' name='subject' value='"+sub[1]+"'>"+sub[1]+"</td>";
		        			text=text+"<td><input style='width:80px;' type='text' class='form-control' onfocus='makeZero(this);' onblur='"+calculate+"' id='theory"+i+"' name='"+sub[1]+"theory' value='0'></td>";
		        			text=text+"<td><input style='width:80px;' type='text' class='form-control' onfocus='makeZero(this);' onblur='"+calculate+"' id='theoryPass"+i+"' name='"+sub[1]+"theoryPass' value='0'></td>";
		        			text=text+"<td><input style='width:80px;' type='text' class='form-control' onfocus='makeZero(this);' onblur='"+calculate+"' id='practical"+i+"' name='"+sub[1]+"practical' value='0'></td>";
		        			text=text+"<td><input style='width:80px;' type='text' class='form-control' onfocus='makeZero(this);' onblur='"+calculate+"' id='practicalPass"+i+"' name='"+sub[1]+"practicalPass' value='0'></td>";
		        			text=text+"<td><input style='width:80px;' type='text' class='form-control' id='total"+i+"' readonly='readonly' name='"+sub[1]+"total' value='0'></td>";
		        			text=text+"<td><input style='width:80px;' type='text' class='form-control' onfocus='makeZero(this);' onblur='"+check+"' id='pass"+i+"' name='"+sub[1]+"pass' value='0'></td>";
		        			text=text+"</tr>";
		        			
		        		}				        		
		        		tableBody.innerHTML=text;
	        				        			
		        	}else{
		        		alert("Courses Not Found For Course");
		        	}
				}
				});
			});
			
			$("#exam").change(function (){
				$.ajax({
				url: '/cedugenie/getSubjectAndMarksForUserDefinedExams.html',
				dataType: 'json',
				data: "course=" + ($("#course").val())+ "&exam=" + ($("#exam").val()),
				success: function(data) {
					if(data!=""){
						data=data.split("###");
						for(var i=0;i<data.length-1;i++){
							var arr=data[i].split("*~*");
							document.getElementsByName(arr[0]+"theory")[0].value=arr[1];
							document.getElementsByName(arr[0]+"practical")[0].value=arr[2];
							document.getElementsByName(arr[0]+"total")[0].value=arr[3];
							document.getElementsByName(arr[0]+"pass")[0].value=arr[4];
							document.getElementsByName(arr[0]+"theoryPass")[0].value=arr[5];
							document.getElementsByName(arr[0]+"practicalPass")[0].value=arr[6];
						}
					}else{
						var x = document.getElementsByTagName("input");
						for (var i = 0; i < x.length; i++) {
						    if (x[i].type == "text"){
						    	x[i].value=0;
						    }
						}
					}
				}
				});
				
				
			});
			
		});
		function makeZero(box){
			if(box.value=="0")
				box.value="";
		}
		function calculate(num){
			var intRegx=numeric=/^[0-9]{1,}$/;
			var t=0;
			var p=0;
			
			var theory=document.getElementById("theory"+num);
			var theoryMarks=theory.value;
			theoryMarks=theoryMarks.replace(/\s{1,}/g,'');
			theory.value=theoryMarks;
			
			var practical=document.getElementById("practical"+num);
			var practicalMarks=practical.value;
			practicalMarks=practicalMarks.replace(/\s{1,}/g,'');
			practical.value=practicalMarks;
			
			var theoryPass=document.getElementById("theoryPass"+num);
			var theoryPassMarks=theoryPass.value;
			theoryPassMarks=theoryPassMarks.replace(/\s{1,}/g,'');
			theoryPass.value=theoryPassMarks;
			
			var practicalPass=document.getElementById("practicalPass"+num);
			var practicalPassMarks=practicalPass.value;
			practicalPassMarks=practicalPassMarks.replace(/\s{1,}/g,'');
			practicalPass.value=practicalPassMarks;
			
			
			if(!theoryMarks.match(intRegx)){
				alert("Invalid Marks. (Numeric. Atleat 1 character.)");
				theory.value="0";
			}else{
				theoryMarks = parseInt(theoryMarks);
				if(theoryMarks<0){
					alert("Invalid Marks. (+ve Numbers Only.)");
					theory.value="0";
				}else{
					t=theoryMarks;
				}
			}
			
			if(!practicalMarks.match(intRegx)){
				alert("Invalid Marks. (Numeric. Atleat 1 character.)");
				practical.value="0";
			}else{
				practicalMarks = parseInt(practicalMarks);
				if(practicalMarks<0){
					alert("Invalid Marks. (+ve Numbers Only.)");
					practical.value="0";
				}else{
					p=practicalMarks;
				}
			}
			
			if(!theoryPassMarks.match(intRegx)){
				alert("Invalid Marks. (Numeric. Atleat 1 character.)");
				theoryPass.value="0";
			}else{
				theoryPassMarks = parseInt(theoryPassMarks);
				if(theoryPassMarks<0){
					alert("Invalid Marks. (+ve Numbers Only.)");
					theoryPass.value="0";
				}else{
					if(theoryPassMarks>t){
						alert("Theory Pass Marks Cannot Be Greater Than Theory Marks");
						theoryPass.value="0";
					}
				}
			}
			
			if(!practicalPassMarks.match(intRegx)){
				alert("Invalid Marks. (Numeric. Atleat 1 character.)");
				practicalPass.value="0";
			}else{
				practicalPassMarks = parseInt(practicalPassMarks);
				if(practicalPassMarks<0){
					alert("Invalid Marks. (+ve Numbers Only.)");
					practicalPass.value="0";
				}else{
					if(practicalPassMarks>p){
						alert("Practical Pass Marks Cannot Be Greater Than Practical Marks");
						practicalPass.value="0";
					}
				}
			}
			
			document.getElementById("total"+num).value=t+p;
			
		}
		function check(num){
			var intRegx=numeric=/^[0-9]{1,}$/;
			var total=document.getElementById("total"+num).value;
			var pass=document.getElementById("pass"+num).value;
			total = parseInt(total);
			
			if(!pass.match(intRegx)){
				alert("Invalid Pass Marks. (Numeric. Atleat 1 character.)");
				document.getElementById("pass"+num).value="0";
			}else{
				pass = parseInt(pass);
				if(pass<0){
					alert("Invalid Pass Marks. (+ve Numbers Only.)");			
				}else{
					if(pass>total){
						alert("Pass Marls Cannot Be Greater Than Total Marks");
						document.getElementById("pass"+num).value="0";
					}
				}
			}
		}
	
	function validate(){
		var standard = document.getElementById("standard").value;
		if(standard=="" ||standard =='null' ){
			//document.getElementById("warningbox").style.visibility='visible';
			//document.getElementById("warningmsg").innerHTML="Select Standard.";
			alert("Select Standard.");
			return false;
		}
		var course = document.getElementById("course").value;
		if(course=="" ||course =='null' ){
			//document.getElementById("warningbox").style.visibility='visible';
			//document.getElementById("warningmsg").innerHTML="Select Standard.";
			alert("Select course.");
			return false;
		}
		
		var exam = document.getElementById("exam").value;
		if(exam=="" || exam =='null'){
			//document.getElementById("warningbox").style.visibility='visible';
			//document.getElementById("warningmsg").innerHTML="Select Exam.";
			alert("Select Exam.");
			return false;
		}
		/* var marks=getElementsByClassName("textfield1");
		if(marks.length==0){
			//document.getElementById("warningbox").style.visibility='visible';
			//document.getElementById("warningmsg").innerHTML="Subjects Not Found";
			return false;
		} */
		return true;
	}
	
	function detailsSubmit()
	{
		
		var length= document.getElementById("length").value;
		
		for(var i=0;i<length-1;i++)
			{
			
			var practical=document.getElementById("practical"+i).value;
			var practicalPass=document.getElementById("practicalPass"+i).value;
			var theory=document.getElementById("theory"+i).value;
			var theoryPass=document.getElementById("theoryPass"+i).value;
			var pass=document.getElementById("pass"+i).value;
			if(theoryPass==0||theory==0||pass==0)
				{
				document.getElementById("javascriptmsg2").style.display = 'block';			
				document.getElementById("javascriptmsg2").innerHTML = "Please  enter  all  marks";	
				return false;
				}
			}
		return true;
			
	}