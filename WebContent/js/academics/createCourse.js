/*$(function(){
   var formIssuanceDate = document.getElementById("formIssuanceDate").value;
   //alert("formIssuanceDate===="+formIssuanceDate);
    
    $("#formIssuanceDate").datepicker({
        minDate: 0,
        maxDate: '+1Y+6M',
		 dateFormat: 'dd/mm/yy',
        onSelect: function (dateStr) {
            var min = $(this).datepicker('getDate'); // Get selected date
            $("#lastFormSubmissionDate").datepicker('option', 'minDate', min || '0'); // Set other min, default to today
            $("#lastFormSubmissionDate").removeAttr('disabled','disabled');
        }
    });
    
    $("#lastFormSubmissionDate").datepicker({
        minDate: 0,
        maxDate: '+1Y+6M',
		 dateFormat: 'dd/mm/yy',
        onSelect: function (dateStr) {
            var min = $(this).datepicker('getDate'); // Get selected date
            $("#scrutinyDate").datepicker('option', 'minDate', min || '0'); // Set other min, default to today
            $("#scrutinyDate").removeAttr('disabled','disabled');
        }
    });
    $("#scrutinyDate").datepicker({
        minDate: 0,
        maxDate: '+1Y+6M',
		 dateFormat: 'dd/mm/yy',
        onSelect: function (dateStr) {
            var min = $(this).datepicker('getDate'); // Get selected date
            $("#interviewDate").datepicker('option', 'minDate', min || '0'); // Set other min, default to today
            $("#interviewDate").removeAttr('disabled','disabled');
        }
    });
    
    $("#interviewDate").datepicker({
        minDate: 0,
        maxDate: '+1Y+6M',
		 dateFormat: 'dd/mm/yy',
        onSelect: function (dateStr) {
            var min = $(this).datepicker('getDate'); // Get selected date
            $("#marksSubmissionDate").datepicker('option', 'minDate', min || '0'); // Set other min, default to today
            $("#marksSubmissionDate").removeAttr('disabled','disabled');
        }
    });
    $("#marksSubmissionDate").datepicker({
        minDate: 0,
        maxDate: '+1Y+6M',
		 dateFormat: 'dd/mm/yy',
        onSelect: function (dateStr) {
            var min = $(this).datepicker('getDate'); // Get selected date
            $("#admissionDriveStartDate").datepicker('option', 'minDate', min || '0'); // Set other min, default to today
            $("#admissionDriveStartDate").removeAttr('disabled','disabled');
        }
    });
    
    $("#admissionDriveStartDate").datepicker({
        minDate: 0,
        maxDate: '+1Y+6M',
		 dateFormat: 'dd/mm/yy',
        onSelect: function (dateStr) {
            var min = $(this).datepicker('getDate'); // Get selected date
            $("#admissionDriveExpectedEndDate").datepicker('option', 'minDate', min || '0'); // Set other min, default to today
            $("#admissionDriveExpectedEndDate").removeAttr('disabled','disabled');
        }
    });

    
    $("#admissionDriveExpectedEndDate").datepicker({
        minDate: '0',
        maxDate: '+1Y+6M',
		dateFormat: 'dd/mm/yy',
        onSelect: function (dateStr) {
            var min = $(this).datepicker('getDate'); // Get selected date
            $('#courseStartDate').datepicker('option', 'minDate', min || '0'); // Set other max, default to +18 months
            $("#courseStartDate").removeAttr('disabled','disabled');
        }
    });
    
    $("#courseStartDate").datepicker({
        minDate: '0',
        maxDate: '',
		dateFormat: 'dd/mm/yy',
        onSelect: function (dateStr) {
            var max = $(this).datepicker('getDate'); // Get selected date
            $('#datepicker').datepicker('option', 'maxDate', max || '+1Y+6M'); // Set other max, default to +18 months
           // $("#scrutinyDate").removeAttr('disabled','disabled');
        }
    });
    

});*/

$("#courseName").change(function(){
	//alert("hii");
	var table = document.getElementById("sectionbody");
	//alert($("#courseName").val());
    $.ajax({
    	url: '/cedugenie/getSectionAgainstStandard.html',
        dataType: 'json',
        data: "standard=" + ($(this).val()),
        success: function(dataDB) {
        	//alert("dataDB==="+dataDB);
        	deleteRow(table);
        	//var options="<option value=''>Select</option>";
        	if(dataDB != "null" && dataDB !="")
			{
        		var arr = dataDB.split(";");
				for (var i=0;i<arr.length;i++){   
					var section = arr[i].split("*");
					//options=options+"<option value='"+section[0]+"'>"+section[1]+"</option>";
					var rowCount = table.rows.length;
	                var row = table.insertRow(rowCount);
	                
	                var cell,element;
	                
	                cell = row.insertCell(0);		
	                element = document.createElement("input");
	                element.type = "hidden";
	                element.name="section";
	                element.value=section[0];
	                var element1 = document.createTextNode(section[0]);
	                cell.appendChild(element1);
	                cell.appendChild(element);
	                
	                cell = row.insertCell(1);		
	                element = document.createElement("input");
	                element.name="sectionCapacity";
	                element.id=section[0]+"_capacity";
	                element.className = "form-control";
	                element.value = 0;
	                element.setAttribute('required','required');
	                element.setAttribute("onblur","calculateTotalCapacity(this);");
	                cell.appendChild(element);
				}
			}
        	
       }
	});
    document.getElementById("sectionDiv").style.display = "block";
});
function deleteRow(table)
{
    var rowCount = table.rows.length;
    for(var i=rowCount; i>1; i--)
	{
   		table.deleteRow(i-1);
           
    }
/*    var innerHeight2=document.body.scrollHeight;
	var frame=window.parent.document.getElementById("frame");	    	
	frame.style.height = innerHeight2+50+ 'px';*/
    
}
var total=0;
function calculateTotalCapacity(box){	
	//alert(box);
	var boxValue = parseInt(box.value);
	//alert("bokValue=="+boxValue);
	var boxValueSatus = isNaN(boxValue);
	//alert(boxValueSatus);
	if(boxValueSatus==true){
		//total = 0;
		boxValue = 0;
	}
	total = parseInt( total )+ boxValue;
	document.getElementById("noOfOpenings").value = total;
}

function setNoOfOpenings(){
	var noOfOpening = document.getElementById("totalSeats").value;
	document.getElementById("noOfOpenings").value = noOfOpening;
};

function setStandardName(){
	var courseName = document.getElementById("courseName").value;
	document.getElementById("courseClass").value = courseName;
};


function updateAdmission(index, programName,  openings, duration){
	//alert("Index :: "+index+"\tProgram Name :: "+programName+"\tAdmission End Date :: "+admissionEndDate+"\tCourse Start Date :: "+courseStartDate+"\tOpenings :: "+openings+"\tDuration :: "+duration);
	//(admissionEndDate != null && admissionEndDate!="") && (courseStartDate != null && courseStartDate!="")
		//&& (openings != null && openings!="") && 
	
	$('#updateAdmissionDetails > tbody').empty();
 	if((duration != null && duration!="")){
 		var row = "<tbody>";
 		row += "<tr><td>"+"<input type='hidden' id='index' name='index' value='"+index+"'>"
 		+programName+"</td>"
 		/*+"<td><div class='form-group'><div class='input-group'><span class='input-group-addon'><i class='fa fa-calendar'></i></span><input type='text' id='admissionEndDate' name='admissionEndDate' class='form-control' value='"+admissionEndDate+"' required></div></div></td>"
 		+"<td><div class='form-group'><div class='input-group'><span class='input-group-addon'><i class='fa fa-calendar'></i></span><input type='text' id='courseNewStartDate' name='courseNewStartDate' class='form-control' value='"+courseStartDate+"' required></div></div></td>"
 		+"<td><input type='text' id='openings' name='openings' class='form-control' value='"+openings+"' pattern='^[1-9]\d*$' required readonly></td>"*/
 		+"<td>"+openings+"</td>"
 		+"<td><input type='text' id='duration' name='duration' class='form-control' value='"+duration+"' pattern='^[1-9]\d*$' required></td></tr>";    				
 		
 		$("#updateAdmissionDetails").append(row);
 	}
 	
	
 	/*$("#admissionEndDate").datepicker({
 		 minDate: 0,
         maxDate: '+1Y+6M',
 		 dateFormat: 'dd/mm/yy',
         onSelect: function (dateStr) {
             var min = $(this).datepicker('getDate'); // Get selected date
             $("#courseNewStartDate").datepicker('option', 'minDate', min || '0'); // Set other min, default to today
         }
    });
    
    $("#courseNewStartDate").datepicker({
        minDate: '0',
        maxDate: '',
		dateFormat: 'dd/mm/yy',
        onSelect: function (dateStr) {
            var max = $(this).datepicker('getDate'); // Get selected date
            $('#datepicker').datepicker('option', 'maxDate', max || '+1Y+6M'); // Set other max, default to +18 months
        }
    });*/
    $('#modalInfo').fadeIn("fast");
    
 	var btn = document.getElementById("updateAdmission");
 	btn.setAttribute("onclick","saveAdmission('"+index+"');");
};

function saveAdmission(rowid){
//	var admissionEndDate = document.getElementById("admissionEndDate").value;
	//var courseStartDate = document.getElementById("courseNewStartDate").value;
	//var openings = document.getElementById("openings").value;
	var duration = document.getElementById("duration").value;
	
	
/*	if(admissionEndDate == null || admissionEndDate == ""){
		document.getElementById("warningmsg").style.display = 'block';			
		document.getElementById("warningmsg").innerHTML = "Admission End Date can not be Empty.";
		return false;
	}else if(courseStartDate == null || courseStartDate == ""){
		document.getElementById("warningmsg").style.display = 'block';			
		document.getElementById("warningmsg").innerHTML = "Course Start Date can not be Empty.";
		return false;
	}else if(openings == null || openings == ""){
		document.getElementById("warningmsg").style.display = 'block';			
		document.getElementById("warningmsg").innerHTML = "Number of openings can not be Empty.";
		return false;
	}else*/ if(duration == null || duration == ""){
		document.getElementById("warningmsg").style.display = 'block';			
		document.getElementById("warningmsg").innerHTML = "Duration can not be Empty.";
		return false;
	}else{
		document.getElementById("saveId").value=rowid;
		//document.getElementById("newAdmissionEndDate").value=admissionEndDate;
		//document.getElementById("newCourseStartDate").value=courseStartDate;
	//	document.getElementById("newOpenings").value=openings;
		document.getElementById("newDuration").value=duration;
				
		document.getElementById("warningmsg").style.display = 'none';	
		document.editCourse.submit();
	}
	
};

function closeWarning(){
	document.getElementById("warningmsg").style.display = 'none';	
}






function validate(){
	
	var reg1 = /^[A-Za-z0-9 ]{1,50}$/;
	
	var courseName = document.getElementById("courseName").value;	
	courseName = courseName.replace(/\s{1,}/g,' ');
	courseName = courseName.trim();
	//courseTypeName = courseTypeName.toUpperCase();
	document.getElementById("courseName").value = courseName;
	
	if(courseName == "" || courseName == 'null'){
		alert("Please Enter Course Name");
		return false;
	}
	
	if(!courseName.match(reg1)){
		//document.getElementById("warningbox").style.visibility='visible';
		//document.getElementById("warningmsg").innerHTML="Invalid Subject Name. (Alpha-Numeric. Atleat 1 character.)";
		alert("Invalid Course Name. (Alpha-Numeric. Atleat 1 character.)");
		return false;
	}
	var oldCourseNames = document.getElementsByName('oldCourseNames');
	for(var i=0; i<oldCourseNames.length;i++){
		if(oldCourseNames[i].value == courseName){
			//document.getElementById("warningbox").style.visibility='visible';
			//document.getElementById("warningmsg").innerHTML="Subject Name Already Exixts";
			alert("Course Name Already Exixts");
			return false;
		}
	}
	
	var courseType = document.getElementById("courseType").value;
	if(courseType == "" || courseType == 'null'){
		alert("Please Enter Course Type");
		return false;
	}
	
	var kclass = document.getElementById("class").value;
	if(kclass == "" || kclass == 'null'){
		alert("Please Enter Standard");
		return false;
	}
	
	var totalSeats = document.getElementById("totalSeats").value;
	if(totalSeats == "" || totalSeats == 'null'){
		alert("Please Enter Total Seats");
		return false;
	}
	var noOfOpenings = document.getElementById("noOfOpenings").value;
	if(noOfOpenings == "" || noOfOpenings == 'null'){
		alert("Please Enter No Of Openings");
		return false;
	}
	
	var admissionAvailableStartDate = document.getElementById("admissionDriveStartDate").value;
	if(admissionAvailableStartDate == "" || admissionAvailableStartDate == 'null'){
		alert("Please Enter Admission Start Date");
		return false;
	}
	
	var admissionDriveExpectedEndDate = document.getElementById("admissionDriveExpectedEndDate").value;
	if(admissionDriveExpectedEndDate == "" || admissionDriveExpectedEndDate == 'null'){
		alert("Please Enter Admission End Date");
		return false;
	}
	if(admissionDriveExpectedEndDate < admissionAvailableStartDate){
		alert("Admission Start Date Can not be after Admission End Date");
		return false;
	}
	
	var courseStartDate = document.getElementById("courseStartDate").value;
	if(courseStartDate == "" || courseStartDate == 'null'){
		alert("Please Enter Course Start Date");
		return false;
	}
	
	if( admissionDriveExpectedEndDate < courseStartDate){
		alert("Course Start Date Can not be before Admission End Date");
		return false;
	}
	
	var courseDuration = document.getElementById("courseDuration").value;
	if(courseDuration == "" || courseDuration == 'null'){
		alert("Please Enter Course Duration");
		return false;
	}

	var courseStartTime = document.getElementById("courseStartTime").value;
	if(courseStartTime == "" || courseStartTime == 'null'){
		alert("Please Enter Course Start Time");
		return false;
	}

	var courseEndTime = document.getElementById("courseEndTime").value;
	if(courseEndTime == "" || courseEndTime == 'null'){
		alert("Please Enter Course End Time");
		return false;
	}

	
	return true;
}