
$(document).ready(function(){
$("#teacherId").change(
			function() {
				var teacherId=document.getElementById("teacherId").value;	
				if(teacherId== "null" || teacherId==''){
					return;
				}
				$.ajax({
			        url: '/icam/getStaffDetailsForTeacherSubjectMapping.html',
			        data: "staffUserId=" +($(this).val()),
			        success: function(data) {
			        	//alert("data==="+data);
			        	var sm=data.split("**");
			        	//alert("sm==="+sm);
			        	var sm1 = sm[0].split("*");
			        	//alert("sm1========="+sm1);
			        	document.getElementById("teacherName").value=sm1[1];
			        	document.getElementById("designation").value=sm1[3];
			        	document.getElementById("jobType").value=sm1[4];
			        	//document.getElementById("teachingLevel").value=sm1[4];
			        	/* $(".midsec1").each(function(){
		        			 (this).setAttribute("style","visibility: visible;");
		        		 });*/
		        		/* var bt =sm[4];*/
		        		 /*if(bt=="")	var test = "break";*/
		        		/* if(bt!="") 		{	        				 
		        		 var tab=bt.split("#");
		        		 var length=tab.length-1;
		        		 var qualificationTable = document.getElementById("qualificationTable"); 
		        		 deleteRow(qualificationTable);
		        		 var qualificationDiv = document.getElementById("qualificationDiv");
		        		 qualificationDiv.removeAttribute("style");
		        		 qualificationDiv.setAttribute("style", "visibility:visible");
		        		 var rowCount = qualificationTable.rows.length;
		        		 var row = qualificationTable.insertRow(rowCount);
		        		 var cols = document.getElementById("cols");
		        		 cols.setAttribute("colspan", length-1);*/
		        		/* for(var i=0;i<length;i++)
		        			{
			        		var cell = row.insertCell(i);
			        		var element= document.createElement("input");
			        		element.type = "text";
			        		element.value=tab[i];
			        		element.setAttribute("readonly","readonly");
			        		cell.appendChild(element);
		        			}		*/        		 
			        	//}
			        	document.getElementById("oldSubjects").innerHTML="";
						var oldSubjects="";
						if(sm[1] != "" || sm[1] !=''){
							var sm2 = sm[1].split("#*#");
							//alert("data 0==="+data[0]);
							//alert("data1=="+data[1]);
							if(sm2[0]!="" ||sm2[0] !=''){
								data1 = sm2[0].split("*~*");
								for(var i=0;i<data1.length-1;i++){
									//alert("within");
									var sub=data1[i].split("#@#");
									document.getElementById(sub[1]).checked=true;
									oldSubjects=oldSubjects+"<input type='hidden' name='oldSubjects' value='"+sub[1]+"'/>";
								}
								alert("oldSubjects==="+oldSubjects);
								document.getElementById("oldSubjects").innerHTML=oldSubjects;
							}else{
									data2 = sm2[1].split("###");
									for(var i=1;i<data2.length;i++){
										//alert("data2 value ====="+data2[i]);
										document.getElementById(data2[i]).checked = false;
									}
								}
							
					}
			        }
			}); 
	});
});

function teacherSubjectMappingValidation(){	
	
	if(document.getElementById("teacherId").value.replace(/\s{1,}/g,'').length ==0){
		document.getElementById("warningbox").style.visibility="visible";
		document.getElementById("warningbox").innerHTML="Please Select Teacher Id";		
		 return false;
	}
	
	return true;
}


function deleteRow(tableID)
{	
	var rowCount = tableID.rows.length-1;
	 for(var i=rowCount; i>0; i--)
		{	
			tableID.deleteRow(i);
        }
} 

function getLoggedData(){
	var teacherId= document.getElementById("teacherId").value;
	 $.ajax({
		    url: '/sms/getUpdatedTeacherSubjectMappingLogDetails.html',
		    dataType: 'json',
		    data: "teacherId="+teacherId,
		    success: function(data) {
		    	if(data!=''){
		    		createTable(data);
			    }
		    }
	 });
}

function validate()
{
	var name = document.getElementsByName("subjects");
	for (var i=0;i<name.length;i++)
		{
		if(name[i].checked)
			{
			
			return true;
			}
		
		}
	alert("Course Mapping Not done yet");
	return false;
}