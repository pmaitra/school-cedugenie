function validate(){
	var alphaNumeric = /^[A-Za-z0-9 ]{1,50}$/;
	var jobTypeName = document.getElementById("jobTypeName").value;
	jobTypeName = jobTypeName.replace(/\s{1,}/g,' ');
	jobTypeName = jobTypeName.trim();
	if(jobTypeName == ""){
		alert("Please Enter Task Name");
		/* document.getElementById("warningbox").style.visibility="visible";
		document.getElementById("warningmsg").innerHTML="Please Enter Access Type Name"; */
		return false;
	}
	if (jobTypeName != '') {
		if (!jobTypeName.match(alphaNumeric)) {
			alert("Invalid Job Name");
			return false;
		}
	}
	var jobTypeDesc = document.getElementsByName("jobTypeDesc");
	
		if(jobTypeDesc.value==""){
			alert("Please write some description. ");
			return false;
		}
	
	return true;
}

function checkTaskAssignee(){
	var assignee = $("#taskAssignee").val();
	//alert("assignee="+assignee);
	if(assignee == 'designation'){
		//alert("hii");
		document.getElementById("departmentDiv").style.display = "block";
		document.getElementById("designationDiv").style.display = "block";
		document.getElementById("designationLevelDiv").style.display = "block";
	}else{
		document.getElementById("departmentDiv").style.display = "none";
		document.getElementById("designationDiv").style.display = "none";
		document.getElementById("designationLevelDiv").style.display = "none";
	}
}

$("#department").change(function(){
	$.ajax({
        url: '/icam/getDesignationListForDepartment.html',
        dataType: 'json',
        data: "department=" + ($(this).val()),
        success: function(dataDB) {	
        	var designationSelect =document.getElementById("designation"); 
        	if(dataDB != null)
			{
        		for(var i = designationSelect.length;i>0;i--){
        			designationSelect.remove(i);
	        	}
        		
        		var arrForDesignation = dataDB.split("@");
				for (var j=0;j<arrForDesignation.length-1;j++){   
					var designationList = arrForDesignation[j].split("~");						
                    $("#designation").append($("<option></option>").val(designationList[0]).html(designationList[1]));
				}	
			}	  
        }
	});
});

$("#designation").change(function(){
	$.ajax({
        url: '/icam/getDesignationLevelListForDesignation.html',
        dataType: 'json',
        data: "designation=" + ($(this).val()),
        success: function(dataDB) {		        	
        	var levelSelect =document.getElementById("designationLevel"); 
        	if(dataDB != null)
			{
        		for(var i=levelSelect.length;i>0;i--){
        			levelSelect.remove(i);
	        	}
        		
        		var arrForLevel = dataDB.split("@");
				for (var j=0;j<arrForLevel.length-1;j++){   
					var levelList = arrForLevel[j].split("~");						
                    $("#designationLevel").append($("<option></option>").val(levelList[0]).html(levelList[1]));
				}	
			}	        
        }
	});	      
});

function visibleLink(){
	
	var checkBox = document.getElementById("isLinked");
	 if (checkBox.checked == true){
		 document.getElementById("linkDiv").style.display = "block";
		 document.getElementById("noteDiv").style.display = "block";
		 document.getElementById("actionDiv").style.display = "block";
	 }else{
		 document.getElementById("linkDiv").style.display = "none";
		 document.getElementById("noteDiv").style.display = "none";
		 document.getElementById("actionDiv").style.display = "none";
	 }
	
}