$("#course").change(function (){
		document.getElementById("section").removeAttribute("disabled");
		//document.getElementById("subject").removeAttribute("disabled");
		//document.getElementById("exam").removeAttribute("disabled");
				//alert($("#course").val());
				$.ajax({
			        url: '/icam/getSectionAgainstCourse.html',
			        dataType: 'json',
			        data: "course=" + ($(this).val()),
			        success: function(dataDB) {
			        	var options="<option value=''>Select</option>";
			        	if(dataDB != "null" && dataDB !="")
						{
			        		var arr = dataDB.split(";");
							for (var i=0;i<arr.length;i++){   
								var section = arr[i].split("*");
								options=options+"<option value='"+section[0]+"'>"+section[1]+"</option>";
							}
						}
			        	document.getElementById("section").innerHTML=options;
			       }
				});

			
				$.ajax({
				    url: '/icam/getTermsForACourse.html',
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
					url: '/icam/getExamsForCourse.html',
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
			$("#section").change(function (){
				document.getElementById("term").removeAttribute("disabled");
			});
			$("#term").change(function (){
				document.getElementById("subject").removeAttribute("disabled");
						//alert("section=="+$("#section").val());
				/*		$.ajax({
						url: '/icam/getSubjectsForCourse.html',
						dataType: 'json',
						data: "course=" + $("#course").val()+"&section=" + ($("#section").val()),
						success: function(data) {
							//alert(data);
							var options="<option value=''>Select</option>";
				        	if(data != null && data !=""){
				        		data=data.split("#");
								for (var i=0;i<data.length-1;i++){ 
									
									var sub=data[i].split("*");
									options=options+"<option value='"+sub[1]+"'>"+sub[1]+"</option>";
								}
				        		data=data.split("*~*");
								for (var i=0;i<data.length-1;i++){ 
									
									var sub=data[i].split("#@#");
									options=options+"<option value='"+sub[1]+"'>"+sub[1]+"</option>";
								}
							}
				        	document.getElementById("subject").innerHTML=options;
							
							
							
						}
						});*/
				$.ajax({
					url: '/icam/getSubjectsForACourseAndTermAndTeacher.html',
					dataType: 'json',
					data: "course=" + $("#course").val()+"&term=" + ($("#term").val())+"&section=" + ($("#section").val()),
					success: function(data) {
						//alert("data===="+data);
						var options="<option value=''>Select</option>";
			        	if(data != null && data !=""){
			        		//data=data.split("#");
							/*for (var i=0;i<data.length-1;i++){ 
								
								var sub=data[i].split("*");
								options=options+"<option value='"+sub[1]+"'>"+sub[1]+"</option>";
							}*/
			        		data=data.split("*~*");
							for (var i=0;i<data.length-1;i++){ 
								
								var sub=data[i].split("#@#");
								options=options+"<option value='"+sub[1]+"'>"+sub[1]+"</option>";
							}
						}
			        	document.getElementById("subject").innerHTML=options;
						
						
						
					}
					});
				
				});
			$("#subject").change(function (){
				document.getElementById("exam").removeAttribute("disabled");
			});
			$("#exam").change(function (){
				document.getElementById("userDefinedExamMarksDiv").style.display = "block";
				$("#tableBody").empty();
				$.ajax({
				url: '/icam/getStudentsAndMarksForUserDefinedExams.html',
				dataType: 'json',
				data: "course=" + ($("#course").val())+ "&exam=" + ($("#exam").val())+ "&subject=" + ($("#subject").val())+ "&section=" + ($("#section").val()),
				success: function(allData) {
					var tableHead=document.getElementById("tableHead");
					var tableBody=document.getElementById("tableBody");
					allData=allData.split("###");
		        	var check=0;
		        	var tester=allData[0].split("*~*");
		        	if(tester[2]!="0"){
		        		tableHead.innerHTML="<tr><th>Sl. No.</th><th>Roll Number(Name)</th><th>Theory(Pass)</th><th>Practical(Pass)</th><th>Total(Pass)</th><th>Pass/Fail</th></tr>";
		        	}else{
		        		tableHead.innerHTML="<tr><th>Sl. No.</th><th>Roll Number(Name)</th><th>Theory(Pass)</th><th>Total(Pass)</th><th>Pass/Fail</th></tr>";
		        		check=1;
		        	}
		        	for(var i=0;i<allData.length-1;i++){
		        		var data = allData[i].split("*~*");
		                var rowCount = tableBody.rows.length;
		                var row = tableBody.insertRow(rowCount);
		                
		                var cell, element, cell1, cellNumber = -1;
		                
		                cellNumber++;
		                cell = row.insertCell(cellNumber);	
		                element = document.createTextNode(i+1);
		                cell.appendChild(element);
		                
		                cellNumber++;
		                cell = row.insertCell(cellNumber);
		                element = document.createTextNode(data[0]);
		                cell.appendChild(element);		
		                
		                data[0] = data[0].split("(")[0];
		                
		                element = document.createElement("input");
		                element.type = "hidden";
		                element.name="rollNumber";
		                element.id=data[0];
		                element.value=data[0];
		                cell.appendChild(element);
		                
		                               
		                //*************************************Roll
		                
		                cellNumber++;
		                cell1 = row.insertCell(cellNumber);
		                element = document.createElement("input");
		                element.type = "text";
		                element.name=data[0]+"theory";
		                element.id=data[0]+"_theory";
		                element.value=data[7];
		                element.className="textfield2";
		                element.setAttribute("onblur","calculateMarks(this);");
		                element.setAttribute("onfocus","clearMarks(this);");
		                cell1.appendChild(element);
		                
		                element = document.createElement("input");
		                element.type = "hidden";
		                element.name=data[0]+"theoryTotal";
		                element.id=data[0]+"_theoryTotal";
		                element.value=data[1];
		                cell1.appendChild(element);
		                
		                element = document.createElement("input");
		                element.type = "hidden";
		                element.name=data[0]+"theoryPass";
		                element.id=data[0]+"_theoryPass";
		                element.value=data[5];
		                cell1.appendChild(element);
		                
		                element = document.createTextNode(" / "+data[1]+"("+data[5]+")");
		                cell1.appendChild(element);
		                //*************************************Theory
		                
		                if(check==0){
		                	cellNumber++;
		                	cell = row.insertCell(cellNumber);
		                }else{
		                	cell=cell1;
		                }
		                
		                element = document.createElement("input");
		            	if(check==0)
		            		element.type = "text";
		            	else
		            		element.type = "hidden";
		                element.name=data[0]+"practical";
		                element.id=data[0]+"_practical";
		                element.value=data[8];
		                element.className="textfield2";
		                element.setAttribute("onblur","calculateMarks(this);");
		                element.setAttribute("onfocus","clearMarks(this);");
		                cell.appendChild(element);
		                
		                element = document.createElement("input");
		                element.type = "hidden";
		                element.name=data[0]+"practicalTotal";
		                element.id=data[0]+"_practicalTotal";
		                element.value=data[2];
		                cell.appendChild(element);
		                
		                element = document.createElement("input");
		                element.type = "hidden";
		                element.name=data[0]+"practicalPass";
		                element.id=data[0]+"_practicalPass";
		                element.value=data[6];
		                cell.appendChild(element);
		                if(check==0){
			                element = document.createTextNode(" / "+data[2]+"("+data[6]+")");
			                cell.appendChild(element);
		                }
		                //*************************************Practical
		                cellNumber++;
		            	cell = row.insertCell(cellNumber);
		                element = document.createElement("input");
		                element.type = "hidden";
		                element.name=data[0]+"obtained";
		                element.id=data[0]+"_obtained";
		                element.value=data[9];
		                element.className="textfield2";
		                element.setAttribute('readonly','readonly');
		                cell.appendChild(element);
		                
		                element = document.createElement("input");
		                element.type = "hidden";
		                element.name=data[0]+"total";
		                element.id=data[0]+"_total";
		                element.value=data[3];
		                cell.appendChild(element);		                
		                
		                element = document.createElement("input");
		                element.type = "hidden";
		                element.name=data[0]+"totalPass";
		                element.id=data[0]+"_totalPass";
		                element.value=data[4];
		                cell.appendChild(element);
		                
		                element = document.createElement("span");
		                element.id=data[0]+"_totalSpan";
		                element.innerHTML=data[9];
		                cell.appendChild(element);
		                
		                element = document.createTextNode(" / "+data[3]+"("+data[4]+")");
		                cell.appendChild(element);
		                //*************************************Total
		                cellNumber++;
		            	cell = row.insertCell(cellNumber);
		                element = document.createElement("input");
		                element.type = "hidden";
		                element.name=data[0]+"passfail";
		                element.id=data[0]+"_passfail";
		                element.value=data[10];
		                element.className="textfield1";
		                cell.appendChild(element);
		                
		                element = document.createElement("span");
		                element.id=data[0]+"_passfailSpan";
		                element.innerHTML=data[10];
		                cell.appendChild(element);
		                
		        	}
				}
				});
				
				
			});
			
		//});
		function calculateMarks(box){	
			var status=validateMarks(box);	
			var boxid=box.id.split("_");
			var total=0,t=0,p=0;
			if(status=="Valid" || status=="Invalid"){
				
				var theoryMarks=document.getElementById(boxid[0]+"_theory").value;
				var theoryTotalMarks=document.getElementById(boxid[0]+"_theoryTotal").value;
				theoryTotalMarks = parseInt(theoryTotalMarks);
				
				
				if(theoryMarks!="AB"){
					theoryMarks = parseInt(theoryMarks);
					if(theoryMarks>theoryTotalMarks){
						document.getElementById(boxid[0]+"_theory").value="0";
						alert("Marks Cannot Be Greater Than Full Marks.");
						theoryMarks=0;
					}
				}
				
				var practicalMarks=document.getElementById(boxid[0]+"_practical").value;		
				var practicalTotalMarks=document.getElementById(boxid[0]+"_practicalTotal").value;
				practicalTotalMarks = parseInt(practicalTotalMarks);
				
				if(practicalMarks!="AB"){
					practicalMarks = parseInt(practicalMarks);
					if(practicalMarks>practicalTotalMarks){
						document.getElementById(boxid[0]+"_practical").value="0";
						alert("Marks Cannot Be Greater Than Full Marks.");
						practicalMarks=0;
					}
				}
				
				
				var totalObtainedMarks=document.getElementById(boxid[0]+"_obtained");		
				var totalObtainedMarksspan=document.getElementById(boxid[0]+"_totalSpan");
				if(theoryMarks=="AB" && practicalMarks=="AB"){
					totalObtainedMarks.value="AB";
					totalObtainedMarksspan.innerHTML="AB";
				}else if(theoryMarks=="AB"){
					total=practicalMarks;
					totalObtainedMarks.value=total;
					totalObtainedMarksspan.innerHTML=total;
					p=practicalMarks;
				}else if(practicalMarks=="AB"){
					total=theoryMarks;
					totalObtainedMarks.value=total;
					totalObtainedMarksspan.innerHTML=total;
					t=theoryMarks;
				}else{
					total=theoryMarks+practicalMarks;
					totalObtainedMarks.value=total;
					totalObtainedMarksspan.innerHTML=total;
					p=practicalMarks;
					t=theoryMarks;
				}
			}else if(status=="Absent"){
				var totalObtainedMarks=document.getElementById(boxid[0]+"_obtained");
				var totalObtainedMarksspan=document.getElementById(boxid[0]+"_totalSpan");
				if(boxid[1]=="theory"){
					var practicalMarks=document.getElementById(boxid[0]+"_practical").value;
					//alert(practicalMarks);
					if(practicalMarks!="AB"){
						practicalMarks = parseInt(practicalMarks);
						total=practicalMarks;
						totalObtainedMarks.value=total;
						totalObtainedMarksspan.innerHTML=total;
						p=practicalMarks;
					}else{
						totalObtainedMarks.value="AB";
						totalObtainedMarksspan.innerHTML="AB";
					}
				}else{
					var theoryMarks=document.getElementById(boxid[0]+"_theory").value;
					if(theoryMarks!="AB"){
						theoryMarks = parseInt(theoryMarks);
						total=theoryMarks;
						totalObtainedMarks.value=total;
						totalObtainedMarksspan.innerHTML=total;
						t=theoryMarks;
					}else{
						totalObtainedMarks.value="AB";
						totalObtainedMarksspan.innerHTML="AB";
					}
				}
			}
			var s1,s2,s3;
			var passFail=document.getElementById(boxid[0]+"_passfail");
			var passFailSpan=document.getElementById(boxid[0]+"_passfailSpan");
			
			var passMarks=document.getElementById(boxid[0]+"_totalPass").value;
			passMarks = parseInt(passMarks);
			if(total>=passMarks){s1="PASS";}else{s1="FAIL";}
			
			passMarks=document.getElementById(boxid[0]+"_theoryPass").value;
			passMarks = parseInt(passMarks);
			if(t>=passMarks){s2="PASS";}else{s2="FAIL";}
			
			passMarks=document.getElementById(boxid[0]+"_practicalPass").value;
			passMarks = parseInt(passMarks);
			if(p>=passMarks){s3="PASS";}else{s3="FAIL";}
			
			if(s1=="FAIL" || s2=="FAIL" || s3=="FAIL"){
				passFail.value="FAIL";
				passFailSpan.innerHTML="FAIL";
			}else{
				passFail.value="PASS";
				passFailSpan.innerHTML="PASS";
			}
		}
		
		function clearMarks(box){
			if(box.value=="0"){
				box.value="";
			}
		}
		function validateMarks(box){
			var intRegx=numeric=/^[0-9]{1,}$/;
			var marks=box.value;
			marks=marks.toUpperCase();
			marks=marks.replace(/\s{1,}/g,'');
			box.value=marks;
			if(marks!="AB"){
				if(!marks.match(intRegx)){
					alert("Invalid Marks. (Numeric. Atleat 1 character.) OR AB");
					box.value="0";
					return "Invalid";
				}else{
					marks = parseInt(marks);
					if(marks<0){
						alert("Invalid Marks. (+ve Numbers Only.) OR AB");
						box.value="0";
						return "Invalid";
					}
				}
			}else{
				return "Absent";
			}
			return "Valid";
		}
		function deleteRow(table)
		{
		    var rowCount = table.rows.length;
		    for(var i=rowCount; i>1; i--)
			{
		   		table.deleteRow(i-1);
		           
		    }
		}
