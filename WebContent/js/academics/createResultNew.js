$(document).ready(function(){
	var table = document.getElementById("studentResult");
	 $("#standard").change(function(){
		document.getElementById("section").disabled = false;
		document.getElementById("subject").disabled = false;
		document.getElementById("exam").disabled = false;
		document.getElementById("term").disabled = false;
		
		var sectionObject=document.getElementById("section");
		removeOption(sectionObject);
		var subjectObject=document.getElementById("subject");				
		removeOption(subjectObject);
		var examObject=document.getElementById("exam");				
		removeOption(examObject);
		//deleteRow(table);
		
		document.getElementById("insertUpdate").value="INSERT";
		document.getElementById("infomsgbox1").style.display='none';
		document.getElementById("infomsg1").innerHTML="";
		
		$.ajax({
	        url: '/cedugenie/getSectionAgainstStandard.html',
	        dataType: 'json',
	        data: "standard=" + ($(this).val()),
	        success: function(dataDB) {
	        	var options="<option value=''>Select..</option>";
	        	if(dataDB != "null" && dataDB !=""){
	        		var arr = dataDB.split(";");
					for (var i=0;i<arr.length;i++){   
						var section = arr[i].split("*");
						options=options+"<option value='"+section[0]+"'>"+section[1]+"</option>";
					}
				}
	        	sectionObject.innerHTML=options;
	       }
		});
		
		var standardValue = ($("#standard").val());
		if(standardValue=='VI'||standardValue == 'VII'||standardValue=='VIII'){
			document.getElementById("term").style.display = 'block';	
			document.getElementById("termhead").style.display = 'block';
			
			var termObject=document.getElementById("term");				
			removeOption(termObject);
			document.getElementById("warningbox").style.display = "none";
			$.ajax({
		        url: '/cedugenie/getTermForStandard.html',
		        dataType: 'json',
		        data: "standard=" + ($("#standard").val()),
		        success: function(dataDB) {		        
		        	var options="<option value=''>Select..</option>";
		        	if(dataDB != "null" && dataDB !=""){
		        		var arr = dataDB.split("*");
						for (var i=0;i<arr.length-1;i++){
							options=options+"<option value='"+arr[i]+"'>"+arr[i]+"</option>";
						}
					}
		        	termObject.innerHTML=options;
		       }
			});
		}
		if(standardValue=='IX'||standardValue == 'X' || standardValue=='XI'||standardValue == 'XII'){
			document.getElementById("term").style.display = 'none';	
			document.getElementById("termhead").style.display = 'none';	
		}
	});
	 
	 
	 $("#section").change(function(){
		//table.style.display = 'none';
		var subjectObject=document.getElementById("subject");
		removeOption(subjectObject);
		//var examObject=document.getElementById("exam");				
		//removeOption(examObject);
		//deleteRow(table);
		document.getElementById("insertUpdate").value="INSERT";
		document.getElementById("btnDiv").style.display = 'none';
		document.getElementById("infomsgbox1").style.display = 'none';
		document.getElementById("infomsg1").innerHTML="";
		
		$.ajax({
	        url: '/cedugenie/getSubjectGroupForStandard.html',
	        dataType: 'json',
	        data: "standard=" + ($("#standard").val()),
	        success: function(dataDB) {
	        	var options="<option value=''>Select..</option>";
	        	if(dataDB != "null" && dataDB !=""){
	        		var arr = dataDB.split("*~*");
					for (var i=0;i<arr.length-1;i++){
						options=options+"<option value='"+arr[i]+"'>"+arr[i]+"</option>";
					}
				}
	        	subjectObject.innerHTML=options;
	       }
		});
	});

	$("#subject").change(function(){
		//table.style.display = 'none';
		var examObject=document.getElementById("exam");				
		removeOption(examObject);
		//deleteRow(table);
		document.getElementById("insertUpdate").value="INSERT";
		document.getElementById("btnDiv").style.display = 'none';
		document.getElementById("infomsgbox1").style.display = 'none';
		document.getElementById("infomsg1").innerHTML="";
		

		var standardValue = ($("#standard").val());
		if(standardValue=='VI'||standardValue == 'VII'||standardValue=='VIII'){		
			$.ajax({
		        url: '/cedugenie/getExamForStandardAndTerm.html',
		        dataType: 'json',
		        data: "standard=" + ($("#standard").val())+ "&term="+($("#term").val()),
		        success: function(dataDB) {		        	
		        	var options="<option value=''>Select..</option>";
		        	if(dataDB != "null" && dataDB !=""){
		        		var arr = dataDB.split("*");
						for (var i=0;i<arr.length-1;i++){
							var codeName=arr[i].split("~");
							options=options+"<option value='"+codeName[0]+"'>"+codeName[1]+"</option>";
						}
					}
		        	examObject.innerHTML=options;
		       }
			});
		}
		if(standardValue=='IX'||standardValue == 'X' || standardValue=='XI'||standardValue == 'XII'){
			$.ajax({
		        url: '/cedugenie/getExamsForStandard.html',
		        dataType: 'json',
		        data: "standard=" + ($("#standard").val()),
		        success: function(dataDB) {
		        	var options="<option value=''>Select..</option>";
		        	if(dataDB != "null" && dataDB !=""){
		        		var arr = dataDB.split("*");
						for (var i=0;i<arr.length-1;i++){
							var codeName=arr[i].split("~");
							options=options+"<option value='"+codeName[0]+"'>"+codeName[1]+"</option>";
						}
					}
		        	examObject.innerHTML=options;
		       }
			});
			
		}
	});
	
	$("#exam").change(function(){
		/*table.style.display = 'none';
		deleteRow(table);*/
		document.getElementById("insertUpdate").value="INSERT";
		document.getElementById("btnDiv").style.display = 'none';
		document.getElementById("infomsgbox1").style.display = 'none';
		document.getElementById("infomsg1").innerHTML="";
				
		if(document.getElementById("standard").value==""){
			document.getElementById("warningbox").style.display = 'block';
			document.getElementById("warningmsg").innerHTML="Select Standard.";
			return false;
		}
		if(document.getElementById("section").value==""){
			document.getElementById("warningbox").style.display = 'block';
			document.getElementById("warningmsg").innerHTML="Select Section.";
			return false;
		}
		if(document.getElementById("subject").value==""){
			document.getElementById("warningbox").style.display = 'block';
			document.getElementById("warningmsg").innerHTML="Select Subject.";
			return false;
		}
		
		$.ajax({
	        url: '/cedugenie/getMarksForStudentsNew.html',
	        dataType: 'json',
	        data: "standard=" + ($("#standard").val())+ "&exam=" + ($(this).val())+ "&subject=" + ($("#subject").val())+ "&section=" + ($("#section").val())+ "&loggedInUser=" + ($("#loggedInUser").val()),
	        success: function(dataDB) {
	        	if(null!=dataDB && dataDB!=""){
	        		document.getElementById("warningbox").style.display = 'none';
	        		document.getElementById("warningmsg").innerHTML="";
	        		document.getElementById("uploadResultNewDiv").style.display = 'block';
		        			        	
		        	dataDB=dataDB.split("###");
		        	var status=dataDB[dataDB.length-1];
		        	document.getElementById("insertUpdate").value=status;
		        	if(status=="NA"){
		        		document.getElementById("btnDiv").style.display = 'none';
		        		document.getElementById("infomsgbox1").style.display = 'block';
		        		document.getElementById("infomsg1").innerHTML="You Are Not Authorised To Update These Marks";
		        	}else{
		        		document.getElementById("btnDiv").style.display = 'block';
		        		document.getElementById("infomsgbox1").style.display = 'none';
		        		document.getElementById("infomsg1").innerHTML="";
		        	}
		        	
		        	var check=0;
		        	var tester=dataDB[0].split("*~*");
		        	if(tester[2]!="0"){
		        		table.innerHTML="<tr><th>Sl. No.</th><th>Roll Number(Name)</th><th>Theory(Pass)</th><th>Practical(Pass)</th><th>Total(Pass)</th><th>Pass/Fail</th></tr>";
		        	}else{
		        		table.innerHTML="<tr><th>Sl. No.</th><th>Roll Number(Name)</th><th>Theory(Pass)</th><th>Total(Pass)</th><th>Pass/Fail</th></tr>";
		        		check=1;
		        	}
		        	for(var i=0;i<dataDB.length-1;i++){
		        		var data = dataDB[i].split("*~*");
		                var rowCount = table.rows.length;
		                var row = table.insertRow(rowCount);
		                
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
		                
		                cellNumber++;
		                cell1 = row.insertCell(cellNumber);
		                element = document.createElement("input");
		                element.type = "text";
		                element.name=data[0]+"theory";
		                element.id=data[0]+"_theory";
		                element.value=data[7];
		                element.className="textfield2 form-control";
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
		                element.className="textfield2 form-control";
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
		                
		                cellNumber++;
		            	cell = row.insertCell(cellNumber);
		                element = document.createElement("input");
		                element.type = "hidden";
		                element.name=data[0]+"obtained";
		                element.id=data[0]+"_obtained";
		                element.value=data[9];
		                element.className="textfield2 form-control";
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
		                
		                cellNumber++;
		            	cell = row.insertCell(cellNumber);
		                element = document.createElement("input");
		                element.type = "hidden";
		                element.name=data[0]+"passfail";
		                element.id=data[0]+"_passfail";
		                element.value=data[10];
		                element.className="textfield1 form-control";
		                cell.appendChild(element);
		                
		                element = document.createElement("span");
		                element.id=data[0]+"_passfailSpan";
		                element.innerHTML=data[10];
		                cell.appendChild(element);
		                
		            	
		                /*var innerHeight2=document.body.scrollHeight;
		            	var frame=window.parent.document.getElementById("frame");	    	
		            	frame.style.height = innerHeight2+250+ 'px';*/
		        	}
		       }else{
		    	   //table.style.display='none';
		    	   document.getElementById("warningbox").style.display = 'block';
		    	   document.getElementById("warningmsg").innerHTML="Students Not Found For The Selected Subject.";
		       }
			}
		});
	});
});

function removeOption(x){
	for(var i=x.length;i>=0;i--){
		x.remove(i);
	}
	x.innerHTML="<option value=''>Select..</option>";
}
function deleteRow(table){
    var rowCount = table.rows.length;
    for(var i=rowCount; i>1; i--){
   		table.deleteRow(i-1);
    }
}

function calculateMarks(box){
	var status=validateMarks(box);
	var boxid=box.id.split("_");
	var total=0,t=0,p=0;
	if(status=="Valid" || status=="Invalid"){
		var theoryMarks=document.getElementById(boxid[0]+"_theory").value;
		var theoryTotalMarks=document.getElementById(boxid[0]+"_theoryTotal").value;
		theoryTotalMarks = parseFloat(theoryTotalMarks);
		
		if(theoryMarks!="AB" && theoryMarks!="UM"){
			theoryMarks = parseFloat(theoryMarks);
			if(theoryMarks>theoryTotalMarks){
				document.getElementById(boxid[0]+"_theory").value="0";
				document.getElementById("warningbox").style.visibility='visible';
				document.getElementById("warningmsg").innerHTML="Marks Cannot Be Greater Than Full Marks.";
				theoryMarks=0;
			}
		}
		
		var practicalMarks=document.getElementById(boxid[0]+"_practical").value;		
		var practicalTotalMarks=document.getElementById(boxid[0]+"_practicalTotal").value;
		practicalTotalMarks = parseFloat(practicalTotalMarks);
		
		if(practicalMarks!="AB" && practicalMarks != "UM"){
			practicalMarks = parseFloat(practicalMarks);
			if(practicalMarks>practicalTotalMarks){
				document.getElementById(boxid[0]+"_practical").value="0";
				document.getElementById("warningbox").style.visibility='visible';
				document.getElementById("warningmsg").innerHTML="Marks Cannot Be Greater Than Full Marks.";
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
		}else if(theoryMarks=="UM" && practicalMarks=="UM"){
			totalObtainedMarks.value="UM";
			totalObtainedMarksspan.innerHTML="UM";
		}else if(theoryMarks=="UM"){
			total=practicalMarks;
			totalObtainedMarks.value=total;
			totalObtainedMarksspan.innerHTML=total;
			p=practicalMarks;
		}else if(practicalMarks=="UM"){
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
		if(boxid[1]=="theory"){
			var ptm=document.getElementById(boxid[0]+"_practicalTotal").value;
			ptm = parseFloat(ptm);
			if(ptm<=0){
				document.getElementById(boxid[0]+"_practical").value = "AB";
			}
			
			var practicalMarks=document.getElementById(boxid[0]+"_practical").value;
			if(practicalMarks!="AB"){
				practicalMarks = parseFloat(practicalMarks);
				total=practicalMarks;
				totalObtainedMarks.value=total;
				p=practicalMarks;
			}else{
				totalObtainedMarks.value="AB";
			}
		}else{
			var ttm=document.getElementById(boxid[0]+"__theoryTotal").value;
			ttm = parseFloat(ttm);
			if(ttm<=0){
				document.getElementById(boxid[0]+"_theory").value = "AB";
			}
			var theoryMarks=document.getElementById(boxid[0]+"_theory").value;
			if(theoryMarks!="AB"){
				theoryMarks = parseInt(theoryMarks);
				total=theoryMarks;
				totalObtainedMarks.value=total;
				t=theoryMarks;
			}else{
				totalObtainedMarks.value="AB";
			}
		}
	}else if(status=="UM"){
		var totalObtainedMarks=document.getElementById(boxid[0]+"_obtained");
		if(boxid[1]=="theory"){
			var ptm=document.getElementById(boxid[0]+"_practicalTotal").value;
			ptm = parseFloat(ptm);
			if(ptm<=0){
				document.getElementById(boxid[0]+"_practical").value = "UM";
			}
			
			var practicalMarks=document.getElementById(boxid[0]+"_practical").value;
			if(practicalMarks!="UM"){
				practicalMarks = parseFloat(practicalMarks);
				total=practicalMarks;
				totalObtainedMarks.value=total;
				p=practicalMarks;
			}else{
				totalObtainedMarks.value="UM";
			}
		}else{
			var ttm=document.getElementById(boxid[0]+"__theoryTotal").value;
			ttm = parseFloat(ttm);
			if(ttm<=0){
				document.getElementById(boxid[0]+"_theory").value = "UM";
			}
			
			var theoryMarks=document.getElementById(boxid[0]+"_theory").value;
			if(theoryMarks!="UM"){
				theoryMarks = parseInt(theoryMarks);
				total=theoryMarks;
				totalObtainedMarks.value=total;
				t=theoryMarks;
			}else{
				totalObtainedMarks.value="UM";
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
	var doubleRegx=/^[0-9]{1,}.[0-9]{1,}$/;
	var marks=box.value;
	marks=marks.toUpperCase();
	marks=marks.replace(/\s{1,}/g,'');
	box.value=marks;
	if(marks!="AB" && marks != "UM" ){
		if(!marks.match(doubleRegx) && !marks.match(intRegx)){
			document.getElementById("warningbox").style.display = 'block';
			document.getElementById("warningmsg").innerHTML="Invalid Marks. (Numeric. Atleat 1 character.) OR AB OR UM";
			box.value="0";
			return "Invalid";
		}else{
			marks = parseFloat(marks);
			if(marks<0){
				document.getElementById("warningbox").style.display = 'block';
				document.getElementById("warningmsg").innerHTML="Invalid Marks. (+ve Numbers Only.) OR AB OR UM";
				box.value="0";
				return "Invalid";
			}
		}
	}else{
		if(marks =="AB"){
			return "Absent";
		}else if(marks =="UM"){
			return "UM"
		}
	}
	return "Valid";
}
	
function saveStudentResult(){
	if(document.getElementById("standard").value==""){
		document.getElementById("warningbox").style.display = 'block';
		document.getElementById("warningmsg").innerHTML="Select Standard.";
		return false;
	}
	if(document.getElementById("section").value==""){
		document.getElementById("warningbox").style.display = 'block';
		document.getElementById("warningmsg").innerHTML="Select Section.";
		return false;
	}
	if(document.getElementById("rollNumber").value==""){
		document.getElementById("warningbox").style.display = 'block';
		document.getElementById("warningmsg").innerHTML="Select Roll Number.";
		return false;
	}
	if(document.getElementById("exam").value==""){
		document.getElementById("warningbox").style.display = 'block';
		document.getElementById("warningmsg").innerHTML="Select Exam.";
		return false;
	}
	var subjects=document.getElementsByName('subject');
	if(subjects.length==0){
		document.getElementById("warningbox").style.display = 'block';
		document.getElementById("warningmsg").innerHTML="No Subjects Found.";
		return false;
	}
	return true;
}