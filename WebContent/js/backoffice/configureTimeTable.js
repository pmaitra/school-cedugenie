
var p=1;

function deleteRow(tableID){
    var table = document.getElementById(tableID);
    var rowCount = table.rows.length;
	var count=0;
	for(var i=3; i<rowCount; i++)
	{
		var row = table.rows[i];
		var chkbox = row.cells[0].childNodes[0];
		if(true == chkbox.checked)
		{
			count=count+1;
		}
	}

	if(rowCount<=count+2)
	{
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningbox").innerHTML ="Sorry, Atleast one Book with Number Of Copies Required";
		return false;
	}
	
    for(var i=0; i<rowCount; i++)
	{
        var row = table.rows[i];
        var chkbox = row.cells[0].childNodes[0];
        if(null != chkbox && true == chkbox.checked)
		{
            table.deleteRow(i);
            rowCount--;
            i--;
        }
    }
};

function deleteRow1(obj){
	var table = document.getElementById("timetable");
	var rowCount = table.rows.length;

	if(rowCount>1){
		var p = obj.parentNode.parentNode;
		p.parentNode.removeChild(p);
	}else{
		alert("Atleast One Row Required");
	}
};

 

function validateTimeTable(){
	var regnum = '^[0-9]+$';
	var rname = /^[A-Za-z. ]{2,}$/;
	var semesterName = document.getElementById("semsterName").value;
	var teacherName = document.getElementsByName("teacherName");
	var classSectionName = document.getElementsByName("classSectionName");
	var subjectName = document.getElementsByName("subjectName");
	var noOfClasses = document.getElementsByName("noOfClasses");
	
	if(semesterName == "")
		{
			alert("Please select a semester!!");
			return false;
		}
	
	for(var i = 0; i<teacherName.length; i++){
		var tname = teacherName[i].value;
		if(tname == ""){
			alert("Please select a teacher for entry no."+(i+1)+".");
			return false;
		}
	}
	
	for(var i = 0; i<classSectionName.length; i++){
		var clSec = classSectionName[i].value;
		if(clSec == ""){
			alert("Please select a program for entry no."+(i+1)+".");
			return false;
		}
	}
	
	for(var i = 0; i<subjectName.length; i++){
		var subj = subjectName[i].value;
		if(subj == ""){
			alert("Please select a course for entry no."+(i+1)+".");
			return false;
		}
	}
	
	for(var i = 0; i<noOfClasses.length; i++){
		var noCls = noOfClasses[i].value;
		if(noCls == ""){
			alert("Please enter no of classes for entry no."+(i+1)+".");
			return false;
		}
		if(noCls != ""){
			if (!noCls.match(regnum)) {
				alert("This input is not valid.Please enter numeric value for entry no."+(i+1)+".");
				return false;
			}
		}
	}

	var rowId= document.getElementById("teacherID").value;
	var teacherName= document.getElementById("teacherName"+rowId).value
	var classSectionName = document.getElementById("classSectionName"+rowId).value;
	var subjectName=  document.getElementById("subjectName"+rowId).value;
	
	var oldTeacherNames = document.getElementsByName("OldTeacherName");
	var OldClassSectionName= document.getElementsByName("OldClassSectionName");
	var OldSubjectName= document.getElementsByName("OldSubjectName");

	for(var i=0; i<oldTeacherNames.length;i++)
	{
		if(oldTeacherNames[i].value==teacherName)
		{
			if(OldClassSectionName[i].value==classSectionName)
			{
				if(OldSubjectName[i].value==subjectName)
					{
						document.getElementById("addbtn").disabled = true;
						alert("Teacher :: "+ teacherName +"\n With Same Program :: "+ classSectionName + "\n And Course :: " + subjectName +" Already Exixts");
						return false;
						
					}
			}
		}
	}
	
} 



$(document).ready(function() {
	
	var teacherData = null;
	var classSectionData = null;
	var subjectData = null;
	var i = 1;
	
	$('#addbtn').click(function(){
		
		var table = document.getElementById("timetable");
		var rowCount = table.rows.length;
	    var row = table.insertRow(rowCount);
	   
	    var cell2 = row.insertCell(0);
	    var element2 = document.createElement("select");
		element2.name="teacherName";
		element2.id="teacherName"+i;
		var teacherId = "teacherName"+i;
		element2.setAttribute("class","form-control");
		cell2.appendChild(element2);
		//alert(teacherData);
		var teacherOptions = '<select name= \"teacherName\" class=\"form-control teacher\" id=\"'+ teacherId + '\"><option value=\"\">Select...</option>' + teacherData + '</select>';
		document.getElementById(teacherId).outerHTML = teacherOptions;
	    
		var cell3 = row.insertCell(1);
	    var element3 = document.createElement("select");
		element3.name="classSectionName";
		element3.id="classSectionName"+i;
		var classSectionId = "classSectionName"+i;
		element3.setAttribute("class","form-control sectionName");
		cell3.appendChild(element3);
		//alert(classSectionData);
		var classSectionOptions = '<option value="">Select...</option>';
		document.getElementById(classSectionId).innerHTML = classSectionOptions;
		
		var cell4 = row.insertCell(2);
	    var element4 = document.createElement("select");
	    element4.name = "subjectName";
	    element4.id="subjectName"+i;
	    var subjectId = "subjectName"+i;
	    element4.setAttribute("class","form-control subject");
	    cell4.appendChild(element4);
	    //alert(subjectData);
	    var subjectOptions = '<option value="">Select...</option>';
		document.getElementById(subjectId).innerHTML = subjectOptions;
	    
		var cell5 = row.insertCell(3);
	    var element5 = document.createElement("input");
	    element5.type = "text";
	    element5.name="noOfClasses";
	    element5.id="noOfClasses"+i;
	    element5.setAttribute("class","form-control");
		cell5.appendChild(element5);
		
		
		var cell5 = row.insertCell(4);
	 	var element5 = document.createElement("button");	
	 	element5.setAttribute("class","mb-xs mt-xs mr-xs btn btn-danger");
	 	element5.setAttribute("onclick", "deleteRow1(this);");
	 	element5.innerHTML = "Cancel";
	 	cell5.appendChild(element5);
			 	
		i++;
		callalert();
    });
	
	
	
	
		
   $.ajax({
		url:"http://localhost:8080/cedugenie/getTeacherListForTimeTable.html", 
        dataType:"text",
        success:function(data){
        	teacherData = data;
        	//alert("teacherData : " + teacherData);
        },
        error:function(data){
        	alert("error : " + data);
        }
    });
   
   /*$.ajax({
		url:"http://localhost:8080/cedugenie/getClassSectionListForTimeTable.html", 
       dataType:"text",
       success:function(data){
       		classSectionData = data;
       		//alert("classSectionData : " + classSectionData);
       },
       error:function(data){
       	alert("error : " + data);
       }
   });*/
   
   /*$.ajax({
		url:"http://localhost:8080/cedugenie/getSubjectListForTimeTable.html", 
      dataType:"text",
      success:function(data){
    	  subjectData = data;
      	  //alert("subjectData : " + subjectData);
      },
      error:function(data){
      	alert("error : " + data);
      }
  });*/
});

/**added by Saif*
 * To get the program name for the teachers
 * Date- 28/08/2017*/
$(document).ready( function(){
	callalert();
	
});
function callalert(){
	$("select").on("change", function() {
		  var id = $(this).attr("id");
		  var rowId = id.substr(11);
		  document.getElementById("teacherID").value= rowId;
		});
	$(".teacher").change(function (){
		var id = $(this).attr("id");
		var rowId = id.substr(11);
		var teacherName = $(this).val();
		
		var sem = $("#semsterName").val();
		$.ajax({
		    url: '/cedugenie/getProgramNameAgainstSemesterAndTeacher.html',
		    dataType: 'json',
		    data:"teacherID=" + teacherName + "&semsterName=" + sem,
		    success: function(data) {
		    	var options='<option value="">Select...</option>';
		    	if(data!=null && data != ""){	        		
	    			var termArr1 = data.split("#");
	    			for(var a=0; a<termArr1.length-1;a++){
	    				if(termArr1[a] != null && termArr1[a] != ""){
	    					options=options+'<option value="'+termArr1[a]+'">'+termArr1[a]+'</option>';
						}        				
	    			}        				
		    	}
		    	document.getElementById("classSectionName"+rowId).innerHTML=options;
		    }
		}); 
	});
	
	$(".sectionName").change(function (){
		var id = $(this).attr("id");
		var rowId = id.substr(16);
		var teacherName =document.getElementById("teacherName"+rowId).value;
		var sem = $("#semsterName").val();
		var sectionName = $(this).val();
		
		$.ajax({
		    url: '/cedugenie/getCourseNameAgainstProgramTeacherAndSemester.html',
		    dataType: 'json',
		    data:"teacherID=" + teacherName + "&semsterName=" + sem + "&sectionName=" + sectionName,
		    success: function(data) {
		    	var options='<option value="">Select...</option>';
		    	if(data!=null && data != ""){	        		
	    			var termArr1 = data.split("#");
	    			for(var a=0; a<termArr1.length-1;a++){
	    				if(termArr1[a] != null && termArr1[a] != ""){
	    					options=options+'<option value="'+termArr1[a]+'">'+termArr1[a]+'</option>';
						}        				
	    			}        				
		    	}
		    	document.getElementById("subjectName"+rowId).innerHTML=options;
		    }
		}); 
	});
	
	$(".subject").change(function (){
		var id = $(this).attr("id");
		var rowId = id.substr(11);
		var teacherName= document.getElementById("teacherName"+rowId).value;	
		var classSectionName = document.getElementById("classSectionName"+rowId).value;
		var subjectName=  document.getElementById("subjectName"+rowId).value;
		
		var oldTeacherNames = document.getElementsByName("OldTeacherName");
		var OldClassSectionName= document.getElementsByName("OldClassSectionName");
		var OldSubjectName= document.getElementsByName("OldSubjectName");

		//checking for duplicate at the time of selecting items for saving
		//if duplicate entry exists for saving then it will restrict him
		
		
		for(var i=0; i<oldTeacherNames.length;i++)
		{
			if(oldTeacherNames[i].value==teacherName)
			{
				if(OldClassSectionName[i].value==classSectionName)
				{
					if(OldSubjectName[i].value==subjectName)
						{
							document.getElementById("addbtn").disabled = true;
							document.getElementById("submitButton").disabled = true;
							alert("Teacher :: "+ teacherName +"\n With Same Program :: "+ classSectionName + "\n And Course :: " + subjectName +" Already Exixts");
							alert("Please provide the unique Teacher, Program and Course mapping!!");
							return false;
						}
					else
						{
							document.getElementById("addbtn").removeAttribute("disabled");
							document.getElementById("submitButton").removeAttribute("disabled");
						}
				}
				/*alert("Teacher Already Exixts");
				return false;*/
			}
		}


	});
	
}

function enableAllFieldsToChooseValue()
{
	var semesterName= document.getElementById("semsterName").value;
	document.getElementById("addbtn").removeAttribute("disabled");
	document.getElementById("teacherName0").removeAttribute("disabled");
	document.getElementById("classSectionName0").removeAttribute("disabled");
	document.getElementById("subjectName0").removeAttribute("disabled");
	document.getElementById("noOfClasses0").removeAttribute("disabled");
	document.getElementById("cancelButton").removeAttribute("disabled");
	document.getElementById("submitButton").removeAttribute("disabled");
	if(semesterName== "")
	{
		document.getElementById("addbtn").disabled = true;
		document.getElementById("teacherName0").disabled = true;
		document.getElementById("classSectionName0").disabled = true;
		document.getElementById("subjectName0").disabled = true;
		document.getElementById("noOfClasses0").disabled = true;
		document.getElementById("cancelButton").disabled = true;
		document.getElementById("submitButton").disabled = true;
	}
}

