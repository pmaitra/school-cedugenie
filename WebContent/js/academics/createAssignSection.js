$("#standard").change(function(){
	var table = document.getElementById("sectionbody");
	var sectionObject=document.getElementById("section");
	var options=" <select class='form-control' name='className' id = 'className0' onclick='valuedetails()'><option value='0'>Select...</option>";
	$.ajax({
        url: '/cedugenie/getSectionsAgainstCourse.html',
        dataType: 'json',
        data: "standard=" + ($(this).val()),
        success: function(dataDB) {
        	if(dataDB != "null" && dataDB !=""){
        		var arr = dataDB.split(";");
				for (var i=0;i<arr.length;i++){
					var section = arr[i].split("*");
					options=options+"<option value='"+section[0]+"'>"+section[1]+"</option>";
				}
				options=options+"</select>";
				document.getElementById("section").innerHTML=options;
				deleteRow(table);
					$.ajax({
				        url: '/cedugenie/getStudentsToAssignSection.html',
				        dataType: 'json',
				        data: "standard=" + ($("#standard").val())+ "&section=NA",
				        success: function(dataDB) {
				        	
				        	if(null!=dataDB && dataDB!=""){
					        	dataDB=dataDB.split(";");
					        	
					        	for(var i=0;i<dataDB.length;i++){
					        		var data=dataDB[i].split("*");
					                var rowCount = table.rows.length;
					                var row = table.insertRow(rowCount);
					                
					                var cell,element;
					                
					                cell = row.insertCell(0);		
					                element = document.createElement("input");
					                element.type = "checkbox";
					                element.name="rollNumber";
					                element.value=data[0];
					                cell.appendChild(element);
					                
					                cell = row.insertCell(1);		
					                element = document.createTextNode(data[0]);
					                cell.appendChild(element);
					                
					                cell = row.insertCell(2);		
					                element = document.createTextNode(data[1]);
					                cell.appendChild(element);
									/*naimisha.ghosh 07.08.2017 start
									var secondLanguage;
						            if(data[2] == 'null'){
						                secondLanguage = 'Not Available';
						            }else{
						                secondLanguage = data[2];
						            }
									cell=row.insertCell(3);
									element = document.createTextNode(secondLanguage);
									cell.appendChild(element);
									naimisha.ghosh 07.08.2017 end*/
					        	}
				        	}else{
				        		
				        		document.getElementById("javascriptmsg2").style.display='block';
				        		document.getElementById("javascriptmsg2").innerHTML="Section is defined for all students in this Course.";
				        	}
				       }
					});
			}else{
				document.getElementById("javascriptmsg2").style.display='block';
        		document.getElementById("javascriptmsg2").innerHTML="Section not found for this Course";
			}
        	
       }
	});	
});

function deleteRow(table){
    var rowCount = table.rows.length;
    for(var i=rowCount; i>1; i--){
   		table.deleteRow(i-1);
    }
    var innerHeight2=document.body.scrollHeight;
	var frame=window.parent.document.getElementById("frame");	    	
	frame.style.height = innerHeight2+50+ 'px';
}
function validate(){
	if(document.getElementById("standard").value==""){
		document.getElementById("javascriptmsg2").style.display='block';
		document.getElementById("javascriptmsg2").innerHTML="Select Course.";
		return false;
	}
	if(document.getElementById("section").value==""){
		document.getElementById("javascriptmsg2").style.display='block';
		document.getElementById("javascriptmsg2").innerHTML="Select Promoting Section.";
		return false;
	}
	var subjects=document.getElementsByName('rollNumber');
	var counter=0;
	for(var i=0; i<subjects.length;i++){
		if(subjects[i].checked)
			counter=counter+1;
	}
	if(counter<=0){
		document.getElementById("javascriptmsg2").style.display='block';
		return false;
	}
	return true;
}
function addSubject(){
	var table = document.getElementsByName("rollNumber");
	if(table.length==0){
		document.getElementById("javascriptmsg2").style.display = 'block';;
		document.getElementById("javascriptmsg2").innerHTML="No student selected";
		return false;
	}
	else{
		for (var i=0;i<table.length;i++){
			if(table[i].checked){
				return true;
			}
			else{
				document.getElementById("javascriptmsg2").style.display = 'block';;
				document.getElementById("javascriptmsg2").innerHTML="No student selected";
			}
			return false;
		}
	}

}
//Added By ranita.sur on 03082017 for getting the strength of Student
$("#section").change(function() {
	$.ajax({
		url:' /cedugenie/getStrengthOfStudents.html',
		data:"standard="+($("#standard").val())+ "&section=" + ($(this).val()),
		dataType: 'json',
		success: function(data){	
			if(data != ""){
			document.getElementById("strength").value = data;
			}	
	}
		
}); 
});