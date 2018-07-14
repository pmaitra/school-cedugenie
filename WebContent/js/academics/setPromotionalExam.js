		$(document).ready( function(){
			
			
		/*	$("#standard").change(function (){
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
			

			
		});

function addPromotionalExamRow(){
	document.getElementById("warningbox").style.visibility='collapse';
	var lastListItem = $('#promotionalTab>tbody>tr:last');	  
	var table = document.getElementById("promotionalTab");	
    var row = table.rows[1];   
    var clnNode=row.cloneNode(true);  
    var courseSelect = clnNode.children[1].childNodes[1];
    for(var j=courseSelect.length;j>=1;j--){
			courseSelect.remove(j); 		    			
		}	
    $(lastListItem).before(clnNode); 
    var innerHeight2=document.body.scrollHeight;
   	var frame=window.parent.document.getElementById("frame");	    	
   	frame.style.height = innerHeight2+25+ 'px';
};


function makeEditable(rowId){
	rowId=rowId.replace("edit","");
	document.getElementById("examName"+rowId).removeAttribute("disabled");
	//document.getElementById("courseStartDate"+rowId).removeAttribute("readonly");	
	//document.getElementById("noOfOpenings"+rowId).removeAttribute("readonly");
	//document.getElementById("courseDuration"+rowId).removeAttribute("readonly");
	
	
};
function saveData(rowId){
	//alert("rowId ="+rowId);
	rowId=rowId.replace("save","");
	document.getElementById("saveId").value=rowId;
	
	//window.location="editHostel.html?saveId="+rowId;
	document.editPromotionalExam.submit();
};
/*function getCourse(thisClassNode){
    var courseSelect = thisClassNode.parentNode.parentNode.children[1].childNodes[1]; 	
	if((thisClassNode.value)!=''){
    $.ajax({
		    url: '/sms/getCourseForPromotionalExam.html',
		    dataType: 'json',
		    data:"strClass=" + (thisClassNode.value),
		    success: function(data) {
		    	if(data != ''){		    				    		
		    		var arrCourse = data.split(",");		    		
		    		for(var j=courseSelect.length;j>0;j--){
		    			courseSelect.remove(j); 		    			
		    		}		    		
		    		for(var courselist=1;courselist<=arrCourse.length-1;courselist++){
		    			if(arrCourse[courselist] != ""){		    			
		    				var courseNameAndCode = arrCourse[courselist].split("##");		    					
		    				courseSelect.add(new Option(courseNameAndCode[0], courseNameAndCode[1]));		    				
		    			}
					}		    		
		    	}
		    	else alert("No Course Name Found For Class");
		    } 
		}); 
	}
};

function getExam(thisExamTypeNode){
	var className = thisExamTypeNode.parentNode.parentNode.children[0].childNodes[1];
	var courseNode = thisExamTypeNode.parentNode.parentNode.children[1].childNodes[1];	
	var examNode = thisExamTypeNode.parentNode.parentNode.children[2].childNodes[1];		
	for(var j=examNode.length;j>0;j--){
		examNode.remove(j); 		    			
	}
	if((className.value)!='' && (courseNode.value)!=''){
	$.ajax({
	    url: '/sms/getAllExam.html',
	    dataType: 'json',
	    data:"strClass=" + (className.value) + "&strCourse=" + (courseNode.value),
	    success: function(data) {
	    	if(data != ""){		 
	    		var arrExamNode = data.split("%");
	    		for(var examlist=1;examlist<=arrExamNode.length-1;examlist++){
	    			if(arrExamNode[examlist] != ""){		
	    				var examNameAndCode = arrExamNode[examlist].split("##");
	    				examNode.add(new Option(examNameAndCode[0], examNameAndCode[1]));    				
	    			}
				} 	    		
	    	}
	    	else alert("No Exam Found For The Course");
	    }		
	}); 
}
};*/

function checkForPromotional(thisPromotionalExam){	
	var className = thisPromotionalExam.parentNode.parentNode.children[0].childNodes[1];
	var courseNode = thisPromotionalExam.parentNode.parentNode.children[1].childNodes[1];	
	var examName = thisPromotionalExam.value;
	if((className.value)!='' && (courseNode.value)!=''){
	$.ajax({
	    url: '/sms/checkForPromotionalExam.html',
	    dataType: 'json',
	    data:"class=" + (className.value) + "&courseNames=" + (courseNode.value),
	    success: function(data) {	    	
	    	if(data!= null ){	    		
	    		for(var i=thisPromotionalExam.length ;i>0;i--){    			
	    			thisPromotionalExam.remove(i);	    			
	    			}		    	
	    		}	    	
	    	}	
		}); 
	}
};

function deleteExamRow(thisDelNode){	
	var table = document.getElementById("promotionalTab"); 
    var rowCount = table.rows.length;   
    if(rowCount != 3){
		$(thisDelNode).parent().parent().remove();
		var innerHeight2=document.body.scrollHeight;
	   	var frame=window.parent.document.getElementById("frame");	    	
	   	frame.style.height = innerHeight2+25+ 'px';
    }else{
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Atleast One Row Required";
	}
};

function validateForm(){
	
	var elementName=document.getElementsByName("className");
	for(var x=0;x<elementName.length;x++){
		if(elementName[x].value==""){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Please Select Class ";
			return false;
		}
	}
	
	var elementName1=document.getElementsByName("courseNames");
	for(var x=0;x<elementName1.length;x++){
		if(elementName1[x].value==""){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Please Select Course Name ";
			return false;
		}
	}
	
	var elementName2=document.getElementsByName("promotionalExam");
	for(var x=0;x<elementName2.length;x++){
		if(elementName2[x].value==""){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Please Select Promotional Exam ";	
			return false;
		}
	}
	
}


