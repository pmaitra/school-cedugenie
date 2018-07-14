$(document).ready(function(){
	 $("#standard, #toStandard").change(function(){
		var sectionObject;
		var id=$(this).attr("id");
		var options="<option value=''>Select..</option>";
		if(id=="standard"){
			sectionObject=document.getElementById("section");
			sectionObject.innerHTML=options;
		}else{
			sectionObject=document.getElementById("toSection");			
			sectionObject.innerHTML=options;
		}
		$.ajax({
	        url: '/cedugenie/getSectionAgainstStandard.html',
	        dataType: 'json',
	        data: "standard=" + ($(this).val()),
	        success: function(dataDB) {
	        	if(dataDB != "null" && dataDB !="")
				{
	        		var arr = dataDB.split(";");
					for (var i=0;i<arr.length;i++){   
						var section = arr[i].split("*");
						options=options+"<option value='"+section[0]+"'>"+section[1]+"</option>";
					}
				}
	        	sectionObject.innerHTML=options;
	       }
		});
	});
	
	 $("#type").change(function(){
		var type = $(this).val();
		if(type == 'PROMOTE'){
			document.getElementById("toStandardDiv").style.display = 'block';
			document.getElementById("toSectionDiv").style.display = 'block';
		}if(type == 'PASSOUT'){
			document.getElementById("toStandardDiv").style.display = 'none';
			document.getElementById("toSectionDiv").style.display = 'none';
		}
	 });
	 
	 $("#section").change(function(){
		 $.ajax({
	        url: '/cedugenie/getStudentsResultForPromotion.html',
	        dataType: 'json',
	        data: "standard=" + ($("#standard").val())+ "&section=" + ($(this).val()),
	        success: function(dataDB) {
	        	if(null!=dataDB && dataDB!=""){
	        		document.getElementById("studentPromotionDiv").style.display = "block";	        		
		        	dataDB=dataDB.split("###");
		        	var table = document.getElementById("studentList");
		        	for(var i=0;i<dataDB.length-1;i++){
		        		var data=dataDB[i].split("***");
		                var rowCount = table.rows.length;
		                var row = table.insertRow(rowCount);
		                
		                var cell,element;
		                
		                cell = row.insertCell(0);		
		                element = document.createElement("input");
		                element.type = "checkbox";
		                element.name="rollNumber";		                
		                element.value=data[0];
		                if(data[2]!='PASS'){
		                	element.disabled=true;
		                }
		                if(data[3]!="PAID"){
		                	element.disabled=true;
		                }else{
	                	element.className="rollNumber";
		                }
		                cell.appendChild(element);
		                
		                cell = row.insertCell(1);		
		                element = document.createTextNode(data[0]);
		                cell.appendChild(element);
		                
		                cell = row.insertCell(2);		
		                element = document.createTextNode(data[1]);
		                cell.appendChild(element);
		                
		                cell = row.insertCell(3);		
		                element = document.createTextNode(data[2]);
		                cell.appendChild(element);
		                
		                cell = row.insertCell(4);		
		                element = document.createTextNode(data[3]);
		                cell.appendChild(element);
		        	}
	        	}
        	}
		 });
	 });
});

function deleteRow(table){
    var rowCount = table.rows.length;
    for(var i=rowCount; i>1; i--){
   		table.deleteRow(i-1);           
    }
}
function saveStudentPromotion(){
	if(document.getElementById("standard").value==""){
		document.getElementById("warningbox").style.display='block';
		document.getElementById("warningmsg").innerHTML="Select Current Standard.";
		return false;
	}
	if(document.getElementById("section").value==""){
		document.getElementById("warningbox").style.display='block';
		document.getElementById("warningmsg").innerHTML="Select Current Section.";
		return false;
	}
	if(document.getElementById("type").value==""){
		document.getElementById("warningbox").style.display='block';
		document.getElementById("warningmsg").innerHTML="Select Promoting Type.";
		return false;
	}
	if(document.getElementById("type").value=="PROMOTE"){
		if(document.getElementById("toStandard").value==""){
			document.getElementById("warningbox").style.display='block';
			document.getElementById("warningmsg").innerHTML="Select Standard Where to promote.";
			return false;
		}
		if(document.getElementById("toSection").value==""){
			document.getElementById("warningbox").style.display='block';
			document.getElementById("warningmsg").innerHTML="Select Section Where to promote.";
			return false;
		}
	}
	var subjects=document.getElementsByName('rollNumber');
	var counter=0;
	for(var i=0; i<subjects.length;i++){
		if(subjects[i].checked)
			counter=counter+1;
	}
	if(counter<=0){
		document.getElementById("warningbox").style.display='block';
		document.getElementById("warningmsg").innerHTML="Select Atleast One Student To Promote";
		return false;
	}
	return true;
}