//$(document).ready(function(){
	
	/*var table = document.getElementById("datatable-editable");*/
	//table.style.visibility='collapse';
	 /*$("#rollNumber").click(function(){
		 alert("hiii");
	        //$('form').submit();
	   });*/
//});
function getAminitiesUsage(){
	//alert("hiiii");
	//alert($(this).attr('id'));
	var rollNumber = $(this).attr('id');
	//document.getElementById("saveId").value=rollNumber;
	//document.editExam.submit();
	window.location="getAminitiesUsageDetaulsByStudent.html?rollNumber="+rollNumber;
}

$("#standard").change(function(){
	//alert($(standard).val());
	 var table = document.getElementById("datatable-editable");
	 var sectionObject=document.getElementById("section");
	 var options="<option value=''>Select</option>";
	 if(($(standard).val()) == ''){
		 deleteRow(table);
	 }
	 if(($(standard).val())=="All"){
		deleteRow(table);
		$.ajax({
	        url: '/icam/getStudentsForAllCourse.html',
	        dataType: 'json',
	        /*data: "standard=" + ($("#standard").val())+ "&section=NA",*/
	        success: function(dataDB) {
	        	//alert(dataDB);
	        	if(null!=dataDB && dataDB!=""){
	        		
		        //	table.style.visibility='visible';
		        	dataDB=dataDB.split(";");
		        	
		        	for(var i=1;i<dataDB.length;i++){
		        		var data=dataDB[i].split("*");
		                var rowCount = table.rows.length;
		                var row = table.insertRow(rowCount);
		                
		                var cell,element;
		                
		                cell = row.insertCell(0);		
		                element = document.createElement("input");
		                element.type = "radio";
		                element.id=data[0];
		                element.name="rollNumber";
		                element.className="form-control";
		                element.value=data[0];
		                //element.onclick = function(){alert("hii");};
		                element.addEventListener("click", getAminitiesUsage, false);
		                cell.appendChild(element);
		                
		                cell = row.insertCell(1);		
		                element = document.createTextNode(data[0]);
		                cell.appendChild(element);
		                
		                cell = row.insertCell(2);		
		                element = document.createTextNode(data[1]);
		                cell.appendChild(element);
		                
		              /*  cell = row.insertCell(3);
		                element = document.createElement("BUTTON");
		                var t = document.createTextNode("CLICK ME");
		                btn.appendChild(t);
		                document.body.appendChild(btn);*/
		               
		        	}
	        	}else{
	        		//document.getElementById("warningbox1").style.visibility='visible';
	        		//document.getElementById("warningmsg1").innerHTML="Section is defined for all students in this standard.";
	        		alert("No Students.");
	        	}
	       }
		});
	}else{
		deleteRow(table);
		$.ajax({
	        url: '/icam/getSectionAgainstCourse.html',
	        dataType: 'json',
	        data: "course=" + ($(this).val()),
	        success: function(dataDB) {
	        	if(dataDB != "null" && dataDB !="")
				{
	        		var arr = dataDB.split(";");
	        		options = options+"<option value='All'>All</option>";
					for (var i=0;i<arr.length;i++){   
						var section = arr[i].split("*");
						options=options+"<option value='"+section[0]+"'>"+section[1]+"</option>";
					}
					sectionObject.innerHTML=options;
				}
	        }
	    });
	}

});
$("#section").change(function(){
	 var table = document.getElementById("datatable-editable");
	 var sectionObject=document.getElementById("section");
	 var options="<option value=''>Select</option>";
	 if(($(section).val()) == ''){
		 deleteRow(table);
	 }
	// if(($(section).val())=="All"){
		deleteRow(table);
		$.ajax({
	        url: '/icam/getStudentsForprogrammeAndBatch.html',
	        dataType: 'json',
	        data: "course=" + ($("#standard").val())+ "&section="+($("#section").val()),
	        success: function(dataDB) {
	        	//alert(dataDB);
	        	if(null!=dataDB && dataDB!=""){
	        		
		        //	table.style.visibility='visible';
		        	dataDB=dataDB.split(";");
		        	
		        	for(var i=1;i<dataDB.length;i++){
		        		var data=dataDB[i].split("*");
		                var rowCount = table.rows.length;
		                var row = table.insertRow(rowCount);
		                
		                var cell,element;
		                
		                cell = row.insertCell(0);		
		                element = document.createElement("input");
		                element.type = "radio";
		                element.id=data[0];
		                element.name="rollNumber";
		                element.className="form-control";
		                element.value=data[0];
		                //element.onclick = function(){alert("hii");};
		                element.addEventListener("click", getAminitiesUsage, false);
		                cell.appendChild(element);
		                
		                cell = row.insertCell(1);		
		                element = document.createTextNode(data[0]);
		                cell.appendChild(element);
		                
		                cell = row.insertCell(2);		
		                element = document.createTextNode(data[1]);
		                cell.appendChild(element);
		                
		              /*  cell = row.insertCell(3);
		                element = document.createElement("BUTTON");
		                var t = document.createTextNode("CLICK ME");
		                btn.appendChild(t);
		                document.body.appendChild(btn);*/
		               
		        	}
	        	}else{
	        		//document.getElementById("warningbox1").style.visibility='visible';
	        		//document.getElementById("warningmsg1").innerHTML="Section is defined for all students in this standard.";
	        		alert("No Student For This Section");
	        	}
	       }
		});
	// }
});
function deleteRow(table)
{
    var rowCount = table.rows.length;
    for(var i=rowCount; i>1; i--)
	{
   		table.deleteRow(i-1);
           
    }
    var innerHeight2=document.body.scrollHeight;
	var frame=window.parent.document.getElementById("frame");	    	
	frame.style.height = innerHeight2+50+ 'px';
    
}
function validate(){
	if(document.getElementById("standard").value==""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Select Standars.";
		return false;
	}
	if(document.getElementById("section").value==""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Select Promoting Section.";
		return false;
	}
	var subjects=document.getElementsByName('rollNumber');
	var counter=0;
	for(var i=0; i<subjects.length;i++){
		if(subjects[i].checked)
			counter=counter+1;
	}
	if(counter<=0){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Select Atleast One Student To Assign";
		return false;
	}
	return true;
}