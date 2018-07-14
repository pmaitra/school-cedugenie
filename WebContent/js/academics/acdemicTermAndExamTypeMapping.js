var y = 1;
function addExamRow(){     
    var table = document.getElementById("acdemicTermAndExamTypeMappingTab");
	var rowCount = table.rows.length;
	alert("rowCount=="+rowCount);
	var row = table.insertRow(rowCount);
	var cn = document.getElementById("className0");	
	var classNameList = new Array();
	for(var i = 0; i<cn.length;i++){			
		classNameList[i] = cn.options[i].value;
	}	
	var cell,element;
	cell = row.insertCell(0);
	element = document.createElement("select");
	element.className = "form-control";
	element.id = "className"+ y;
	element.name = "className";
	element.setAttribute("onchange","getCourse(this);");		
	element.add(new Option("Select...", ""));
	for(var i = 1; i<classNameList.length;i++){
		element.add(new Option(classNameList[i], classNameList[i]));
	}
	cell.appendChild(element);	
	
	cell = row.insertCell(1);
	element = document.createElement("select");	
	element.className = "form-control";
	element.name = "courseName";
	element.id = "courseNames"+ y;
	element.add(new Option("Select", ""));
	element.setAttribute("onchange","getExamType(this);");
	cell.appendChild(element);	
	
	cell = row.insertCell(2);
	element = document.createElement("select");	
	element.className = "form-control";
	element.name = "examType";
	element.id = "examType"+ y;
	element.add(new Option("Select", ""));
	element.setAttribute("onchange","getExam(this);");
	cell.appendChild(element);	
	
	
	cell = row.insertCell(3);
	//var Div=document.getElementById("Div0").cloneNode(true);
	   // var divid="Div"+y;
	   // Div.setAttribute("id",divid);
	   // Div.setAttribute("class","multipleSelectDiv");
	   // Div.setAttribute("onmouseout","hideDiv('"+divid+"');");
	   // Div.setAttribute("onmouseover","showDiv('"+divid+"');");
	   
	    element = document.createElement("input");
	    element.type = "text";
	    element.name= "examName";
		element.id= "examName"+y;
		/*element.setAttribute("readonly","readonly");
		element.setAttribute("class","textfield1");
	    element.setAttribute("onmouseout","hideDiv('"+divid+"');");
	    element.setAttribute("onmouseover","showDiv('"+divid+"');");
	    var checkBoxes=Div.getElementsByTagName("input");	       
	    for(var x=0;x<checkBoxes.length;x++){
	    	var fun="addText(this,'examName"+y+"')";
	    	checkBoxes[x].setAttribute("onclick",fun);	        	
	    	checkBoxes[x].checked=false;
	    }*/
	    cell.appendChild(element);
	   // cell.appendChild(Div);
		
		
	    cell = row.insertCell(4);
	 	element = document.createElement("select");	
	 	element.className = "form-control";
	 	element.name = "academicTerm";
	 	element.id = "academicTerm"+ y;	 
	 	element.add(new Option("Select", ""));
	 	cell.appendChild(element);
 	
	 	
 	
		cell= row.insertCell(5);
		element = document.createElement("img");
		var element = document.createElement('a');
		element.setAttribute("class","fa fa-minus-square");		
		element.setAttribute("onclick", "deleteRow(this);");
		element.setAttribute("href","#");
		cell.appendChild(element);		
		y++;
     
		var innerHeight2=document.body.scrollHeight;
	   	var frame=window.parent.document.getElementById("frame");	    	
	   	frame.style.height = innerHeight2+25+ 'px';
		
};



function deleteRow(obj){	
	var table = document.getElementById("acdemicTermAndExamTypeMappingTab");
	var rowCount = table.rows.length;
	
	if(rowCount>2){
		//document.getElementById("warningbox").style.visibility='collapse';
		var p = obj.parentNode.parentNode;
		p.parentNode.removeChild(p);
		//var innerHeight2=document.body.scrollHeight;
	  // 	var frame=window.parent.document.getElementById("frame");	    	
	   //	frame.style.height = innerHeight2+30+ 'px';
	}else{
		//document.getElementById("warningbox").style.visibility='visible';
		//document.getElementById("warningmsg").innerHTML="Atleast One Row Required";
	}
}


function getCourse(thisClassNode){   	

	var split= (thisClassNode.id).split("className");
	var index=split[1];
	alert("index=="+index)
	//document.getElementById("Div"+index).innerHTML="";	
	alert(thisClassNode.value);
	if((thisClassNode.value)!=''){
	$.ajax({
		    url: '/cedugenie/getCourseForStandard.html',
		    dataType: 'json',
		    data:"standard=" + (thisClassNode.value),
		    success: function(data) {	
		    	alert(data);
		    	if(data != ""){	        		
        			var courseArr1 = data.split("#*#");
        			var courseArr = courseArr1[0].split("~");
        			var options='<option value="">Select</option>';
        			for(var a=0; a<courseArr.length;a++){
        				if(courseArr[a] != null && courseArr[a] != ""){
        					var courseNameAndCode=courseArr[a].split("*");   
        					options=options+'<option value="'+courseNameAndCode[0]+'">'+courseNameAndCode[1]+'</option>';
    						document.getElementById("courseNames"+index).innerHTML=options;
        				}        				
        			}	        				
		    	}
		    	else{
		    		alert("No Course Found For Class ")+ (thisClassNode.value);
		    	}
		    }
		}); 			
	}
   
};


function getExamType(thisCourseNode){

		var split= (thisCourseNode.id).split("courseNames");
		var index=split[1];
    	var courseCode = document.getElementById("courseNames" + index);   	  	
    	var className = document.getElementById("className" + index);
    if((className.value)!='' && (courseCode)!=''){    
	$.ajax({
	    url: '/cedugenie/getExamTypeForClassCourse.html',
	    dataType: 'json',
	    data:"strClass=" + (className.value) + "&strCourse=" + (courseCode.value),
	    success: function(data) {
	    	if(data != null){
	    		alert(data);
	    		var options='<option value="">Select</option>';
	    		var arrTypeAndTerm = data.split("%");
	    		var arrExamType = arrTypeAndTerm[0].split(",");	    		
	    		var arrTerm = arrTypeAndTerm[1].split("@@");
	    		
	    		for(var examlist=1;examlist<=arrExamType.length-1;examlist++){
	    			if(arrExamType[examlist] != ""){		    			
	    				var examTypeNameAndCode = arrExamType[examlist].split("##");  
	    				options=options+'<option value="'+examTypeNameAndCode[1]+'">'+examTypeNameAndCode[0]+'</option>';
	    			}
				} 
	    		document.getElementById("examType"+index).innerHTML=options;	  
	    		//*********************************	    		
	    	   
	    		var options='<option value="">Select</option>';
	    		for(var term=1;term<=arrTerm.length-1;term++){	    			
	    			if(arrTerm[term] != ""){ 
	    				var arrTermCodeAndName = arrTerm[term].split("!~!");
	    				//academicTerm.add(new Option(arrTermCodeAndName[1], arrTermCodeAndName[0]));	
	    				options=options+'<option value="'+arrTermCodeAndName[0]+'">'+arrTermCodeAndName[1]+'</option>';
	    			}
				} 
	    		document.getElementById("academicTerm"+index).innerHTML=options;
	    	}
	    	
	    	//else 
	    	//document.getElementById("warningbox").style.visibility='visible';
			//document.getElementById("warningbox").innerHTML="No Exam Type Found";

	    }		
	}); 	
}
};

function getExam(thisExamTypeNode){
	
	var examTypeNode =thisExamTypeNode.value;    	
	var index=(thisExamTypeNode.id).substr((thisExamTypeNode.id).length - 1);    	  	
	var className = document.getElementById("className" + index);
	var courseCode = document.getElementById("courseNames"+index) ;	
	//document.getElementById("Div"+index).innerHTML="";
	document.getElementById("examName"+index).value="";
		$.ajax({
		    url: '/cedugenie/getExamForClassCourseExamType.html',
		    dataType: 'json',
		    data:"strClass=" + (className.value) + "&strCourse=" + (courseCode.value) + "&strExamType=" + (examTypeNode),
		    success: function(data) {
		    	if(data != ""){
		    		//document.getElementById("warningbox").style.visibility='collapse';
					//document.getElementById("warningbox").innerHTML="";
		    		var termName = new Array();
	        		var tName= new Array();
    				var tCode= new Array();
        			var examTerm = data.split("%");
	        		for(var i=0;i<examTerm.length-1;i++){			        			
	        			termName.push(examTerm[i]);			        				
	        		}	        					
        			for(var a=0; a<termName.length;a++){
        				var cname=termName[a].split("##");        				
        				tName.push(cname[0]);
        				tCode.push(cname[1]);		        				
        				var span=document.createElement("span");
        				var chkbx = document.createElement("input");
        				chkbx.type = "checkbox";
        				chkbx.value=tCode[a];
        				var onclick="addText(this,'examName"+index+"');";        				
        				chkbx.setAttribute("onclick",onclick);        				
        				var chkbxLabel = document.createTextNode(tName[a]);        				
        				span.appendChild(chkbx);
        				span.appendChild(chkbxLabel);			        				
        				//document.getElementById("Div"+index).appendChild(span);
		    		}        			
		    	}
		    	else{
		    		//document.getElementById("warningbox").style.visibility='visible';
					//document.getElementById("warningbox").innerHTML="No Exan Found";

		    	}		    	
		    }		
		}); 
	
};
var flag = 0;
function validateForm(){ 
	var concatinatedVal = "";
	var className1 = document.getElementsByName("className");
	for(var s=0;s<className1.length;s++){
		if(className1[s].value==''){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningbox").innerHTML="Please Select Class";
			return false;
			flag= 1;
		}
	}
	
	var courseName = document.getElementsByName("courseName");
	for(var s=0;s<courseName.length;s++){
		if(courseName[s].value==''){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningbox").innerHTML="Please Select Course Name";
			return false;
			flag= 1;
		}
	}
	
	var examType = document.getElementsByName("examType");
	for(var s=0;s<examType.length;s++){
		if(examType[s].value==''){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningbox").innerHTML="Please Select Exam Type Name";
			return false;
			flag= 1;
		}
	}
	
	var examName = document.getElementsByName("examName");
	for(var s=0;s<examName.length;s++){
		if(examName[s].value==''){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningbox").innerHTML="Please Select Exam Name";
			return false;
			flag= 1;
		}
	}
	
	var academicTerm = document.getElementsByName("academicTerm");
	for(var s=0;s<academicTerm.length;s++){
		if(academicTerm[s].value==''){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningbox").innerHTML="Please Select Academic Term Name";
			return false;
			flag= 1;
		}
	}	
	

if(flag == 0){
	$('#acdemicTermAndExamTypeMappingTab tr').not(':first').not(':last').each(function() {
	         var classValue = $(this).find("td:nth-child(1)").find("select").val();
	         var courseValue = $(this).find("td:nth-child(2)").find("select").val();
	         var examTypeValue = $(this).find("td:nth-child(3)").find("select").val();
	         var examPerTermValue = $(this).find("td:nth-child(4)").find("input").val();
	         var academicTermValue = $(this).find("td:nth-child(5)").find("select").val();
	        	 concatinatedVal = classValue+ "," +courseValue+ "," +examTypeValue+ "," +examPerTermValue+ "," +academicTermValue+ "/" +concatinatedVal ;
	});
	$.ajax({
	    url: '/sms/getTermExamMappingForValidation.html',
	    dataType: 'json',
	    data:"strParameter=" + (concatinatedVal),
	    success: function(data) {
	    	if(data != ""){	        
	    		if(data == "Previously Allocated"){
	    			document.getElementById("warningbox").style.visibility='visible';
	    			document.getElementById("warningbox").innerHTML="Previously  Mapped .Please Change the Combination.";
	    			return false;
	    		}
	    		if(data != "Previously Allocated"){
	    			 document.forms["termExamTypeMap"].submit();

	    		}
	    	}
	    			    	
	    }		
	}); 
}
}