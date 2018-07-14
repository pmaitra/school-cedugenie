
/*	window.onload=function(){
		if(window.parent.document.getElementById('formSubmission')!=null){
			window.parent.document.getElementById('formSubmission').style.background = 'green';		
		}
		
		$('#dateOfSubmission').Zebra_DatePicker();		
		$('#dateOfSubmission').Zebra_DatePicker({
	 	  format: 'd/m/Y',
	 	  direction:[$("#admissionDriveStartDate").val(),$("#admissionDriveExpectedEndDate").val()]
		});	
		$('#dateOfBirth').Zebra_DatePicker();		
		$('#dateOfBirth').Zebra_DatePicker({
	 	  format: 'd/m/Y',
		});	
		
	};*/
	
    function new_link(){  
    	document.getElementById("warningbox").style.visibility='collapse';
    	
    	 var table = document.getElementById("attachmentTable"); 
         var rowCount = table.rows.length;
         var row = table.insertRow(rowCount-1);
         
         var cell,element;
         
         cell = row.insertCell(0);
         element = document.createElement("input");
         element.type = "file"; 
         element.name="file";
         element.setAttribute("class","textfield");
         cell.appendChild(element);
         
         cell = row.insertCell(1);
         element = document.createElement("img");
         element.setAttribute("src","/sms/images/minus_icon.png");
         element.setAttribute("onclick","delIt(this);");
         cell.appendChild(element);
             	
        
    }  
   
    // function to delete the newly added set of elements  
    function delIt(eleId)  
    {
    	var table = document.getElementById("attachmentTable");
    	var rowCount = table.rows.length;
    	
    	if(rowCount>2){
    		var p = eleId.parentNode.parentNode;
    		p.parentNode.removeChild(p);
    	}else{
    		document.getElementById("warningbox").style.visibility='visible';
    		document.getElementById("warningmsg").innerHTML="Atleast One Attachment Required";
    	}
    }  


//Validating The Data of "submitAdmissionForm"
function validate()
{	
//	alert("hiiiiii");
	var msg="You Have Selected    ";
	fid=document.getElementById("formID").value;
	fdos=document.getElementById("formSubmissionDate").value;
	fdos=fdos.replace(/\s{1,}/g,'');
	fsn1=document.getElementById("candidateFirstName").value;
	fsn1=fsn1.replace(/\s{1,}/g, '');
	fsn2=document.getElementById("candidateMiddleName").value;
	fsn2=fsn2.replace(/\s{1,}/g, '');
	fsn3=document.getElementById("candidateLastName").value;
	fsn3=fsn3.replace(/\s{1,}/g, '');
	fdob=document.getElementById("dateOfBirth").value;
	fdob=fdob.replace(/\s{1,}/g,'');
	femail=document.getElementById("candidateEmail").value;
	fcn=document.getElementById("candidateContactNo").value;
	fcn=fcn.replace(/\s{1,}/g, '');
	att=document.getElementsByName("file");
	//Regular Expression
	redt=/^(\d{1,2})\/(\d{1,2})\/(\d{4})$/;
	ran=/^[A-Za-z ]{3,20}$/;
	cr=/^[\w\s]/;
	em1=/^([a-z0-9_.-])+@([a-z0-9_.-])+\.+([a-z0-9_-]{2,3})+\.+([a-z0-9_-]{2,3})$/;
	em2=/^([a-z0-9_.-])+@([a-z0-9_.-])+\.+([a-z0-9_-]{3})$/;
	var regPositiveNum = '[-+]?([0-9]*\.[0-9]+|[0-9]+)$';

	//Form ID validation
	if(fid=="" || fid=="NULL"){
		//document.getElementById("warningbox").style.visibility="visible";
		//document.getElementById("warningmsg").innerHTML="Please Select FORMID";
		alert("Please Select FORMID");
		return false;
	}
	//Date Validation
	if(fdos=="" || fid=="NULL"){
		//document.getElementById("warningbox").style.visibility="visible";
		//document.getElementById("warningmsg").innerHTML="Please Enter Date Of Submission";
		alert("Please Enter Date Of Submission");
		return false;
	}

	//Student Name Validation
	if(fsn1=='First Name' || fsn1=="")
	{
	//	document.getElementById("warningbox").style.visibility="visible";
	//	document.getElementById("warningmsg").innerHTML="Please Enter First Name of Student";
		alert("Please Enter First Name of Student");
		return false;
	}
	if(fsn1!=''){
		if(!fsn1.match(ran)){
		//	document.getElementById("warningbox").style.visibility="visible";
			//document.getElementById("warningmsg").innerHTML="Invalid First Name of Student";
			alert("Invalid First Name of Student");
			return false;
		}
	}
	
	if(fsn2=='MiddleName' || fsn2==""){
		document.getElementById("candidateMiddleName").value="";
	}
		
	if(fsn3=='Last Name' || fsn3==""){
		//document.getElementById("warningbox").style.visibility="visible";
	//	document.getElementById("warningmsg").innerHTML="Please Enter Last Name of Student";
		alert("Please Enter Last Name of Student");
		return false;
	}
	if(fsn3!=''){
		if(!fsn3.match(ran)){
		//	document.getElementById("warningbox").style.visibility="visible";
		//	document.getElementById("warningmsg").innerHTML="Invalid Last Name of Student";
			alert("Invalid Last Name of Student");
			return false;
		}
	}
	
	//Validate Gender
	var gndr = document.getElementsByName("gender");
	if ( ( gndr[0].checked == false ) && ( gndr[1].checked == false ))
	{
		//document.getElementById("warningbox").style.visibility="visible";
		//document.getElementById("warningmsg").innerHTML="Please select Gender: Male or Female";
		alert("Please select Gender: Male or Female");
		return false;
	}
	
	//Date of Birth Validation
	if( fdob=="" || fid=="NULL"){
		//document.getElementById("warningbox").style.visibility="visible";
		//document.getElementById("warningmsg").innerHTML="Please Enter Date Of Birth";
		alert("Please Enter Date Of Birth");
		return false;
	}
	
	//Validate category
	ct = document.getElementById("category").value;
	ct = ct.replace(/\s{1,}/g,'');
	if(ct == "")
	{	alert("Please Select Category");
		//document.getElementById("warningbox").style.visibility="visible";
	//	document.getElementById("warningmsg").innerHTML="Please Select Category";
		return false;
	}
	
	//Email Validation
	if(femail==""){
		//document.getElementById("warningbox").style.visibility="visible"; 
		//document.getElementById("warningmsg").innerHTML="Please Enter E-mail Id";
		alert("Please Enter E-mail Id");
		return false;
    }	
    if(!(em1.test(femail) || em2.test(femail))){
    //	document.getElementById("warningbox").style.visibility="visible";
	//	document.getElementById("warningmsg").innerHTML="Please Enter Valid E-mail Id";
    	alert("Please Enter Valid E-mail Id");
		return false;
    }
    
	//Contact Number Validation
	if(fcn==""){
		//document.getElementById("warningbox").style.visibility="visible";
	//	document.getElementById("warningmsg").innerHTML="Please Enter Contact Number";
		alert("Please Enter Contact Number");
		return false;
	}
	else
	{
		if(!fcn.match(regPositiveNum)){
			//document.getElementById("warningbox").style.visibility="visible";
		//	document.getElementById("warningmsg").innerHTML="Please Enter Numeric contact Number";
			alert("Please Enter Numeric contact Number");
			return false;
		}
		if (fcn<7000000000 || fcn>9999999999){
		//	document.getElementById("warningbox").style.visibility="visible";
		//	document.getElementById("warningmsg").innerHTML="Please Enter Valid Phone Number";
			alert("Please Enter Valid Phone Number");
			return false;
		}		
		/*else{		
			//document.getElementById("warningbox").style.visibility="collapse";
			chk=document.getElementsByName("file");
			for(var i=0;i<chk.length-1;i++){
				var value = document.getElementsByName('file')[i].value;
				msg=msg+value+"    ";			
			}
			if(msg=="You Have Selected "){
				if(!confirm("You Have Not Uploaded Any Attachment.\n  \t Do You Want To Submit ???"))
					return false;
			}
			else{
				if(!confirm(msg))
					return false;
			}
		}*/
		return true;
	}
}

