

	function addRow(){
		var table = document.getElementById("dataTable"); 
        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount-1);

        var cell1 = row.insertCell(0);
        var element1 = document.createElement("input");
        element1.type = "text";
		element1.name="subject";
		element1.setAttribute("class","form-control");
        cell1.appendChild(element1);

        var cell2 = row.insertCell(1);
        var element2 = document.createElement("input");
        element2.type = "text";
		element2.name="marks";
		element2.setAttribute("class","form-control");
        cell2.appendChild(element2);
		
		var cell3 = row.insertCell(2);
		var element3 = document.createElement('a');
	    element3.setAttribute("onclick","return deleteRow(this);");
	    element3.setAttribute("class","fa fa-minus-square");
	    element3.setAttribute("href","#");
	    cell3.appendChild(element3);
		cell3.appendChild(element3);
   }
	
	function deleteRow(obj){
		var table = document.getElementById("dataTable");
		var rowCount = table.rows.length;
		
		if(rowCount>3){
			var p = obj.parentNode.parentNode;
			p.parentNode.removeChild(p);
		}/*else{
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Atleast One Row Required";
		}*/
	}

	function validate(){
		var sub=document.getElementsByName("subject");
		var mar=document.getElementsByName("marks");
		var len=sub.length;
		fi=document.getElementById("strFormID").value;
		c=document.getElementById("strComment").value;   	
   		v=document.aircontent.strStatus;   	
	   	//Form Id Validation
	   	if(fi=="NULL" || fi==""){
	   		//document.getElementById('warningbox').style.visibility="visible";
			//document.getElementById('warningmsg').innerHTML="Please Select Form Id";
	   		alert("Please Select Form Id");
	   		return false;
	   	}   	
	   
		for(var i=0;i<len;i++){
			subj=sub[i].value;
			mark=mar[i].value;			
			if (subj==""){
				//document.getElementById('warningbox').style.visibility="visible";
				//document.getElementById('warningmsg').innerHTML="Please Enter Subject";
				alert("Please Enter Subject");
				return false;
			}
			if(mark==""){
				//document.getElementById('warningbox').style.visibility="visible";
				//document.getElementById('warningmsg').innerHTML="Please Enter Marks";
				alert("Please Enter Marks");
				return false;
			}
			if(isNaN(mark)){
				alert("Please Enter Valid Marks");
				//document.getElementById('warningbox').style.visibility="visible";
				//document.getElementById('warningmsg').innerHTML="Please Enter Valid Marks";
				return false;
			}
		}
		//Comments Validation
	   	if(c==""){
	   		//document.getElementById('warningbox').style.visibility="visible";
		//	document.getElementById('warningmsg').innerHTML="Please Provide Comments";
	   		alert("Please Provide Comments");
	   		return false;
	   	}	
	   	//Decision Validation
	   	for (var i=0; i<v.length; i++){
	   		if(v[i].checked)
	   			break;
	   	}
	   	if (i==v.length){
	   		//document.getElementById('warningbox').style.visibility="visible";
		//	document.getElementById('warningmsg').innerHTML="Please Select Your Decision";
	   		alert("Please Select Your Decision");
	   		return false;
	   	}
		return true;
	}
	$(document).ready(function() { 
		$("#strFormID").change(
			function() {
				$.ajax({
			        url: '/cedugenie/getName.html',
			        dataType: 'json',
			        data: "strParam=" + ($(this).val() + ":" + $("#strCourseClass").val() + ":" + $("#strFormName").val() + ":" + $("#strAdmissionYear").val()),
			        success: function(data) {
			        	$('#name').val(data);
			        } 
			}); 
		});
	});

   
   