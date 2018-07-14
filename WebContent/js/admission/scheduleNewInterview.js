
	
	//Validate The Data Of "Schedule Interview  Form"
	function validate(){ 
		fid=document.getElementById("formId").value;
		//len=fid.length;
		
		doi=document.getElementById("interviewDate").value;
		doi=doi.replace(/\s{1,}/g,'');
		toi=document.getElementById("interviewTime").value;
		toi=toi.replace(/\s{1,}/g,'');
		tn=document.getElementById("examinerName").value;
		tn = tn.replace(/\s{2,}/g,' ');
		rvn=document.getElementById("reviewerName").value;
		rvn = rvn.replace(/\s{2,}/g,' ');
		rn=document.getElementById("roomNo").value;
		rn = rn.replace(/\s{2,}/g,' ');
		v=document.getElementById("venue").value;
		v = v.replace(/\s{2,}/g,' ');
		
		//Regular Expression
		redt=/^(\d{1,2})\/(\d{1,2})\/(\d{4})$/;
		rt=/^(\d{1,2}):(\d{2})([AP]M)?$/;
		cr =/^[A-Za-z0-9\s]*$/;
		r=/^[A-Za-z\s]{1,}$/;
		var regPositiveNum = '[-+]?([0-9]*\.[0-9]+|[0-9]+)$';
		
		if(fid=="NULL" || fid==""){
			alert("Please Select Form Id");
			/*document.getElementById('warningbox').style.visibility="visible";
			document.getElementById('warningmsg').innerHTML="Please Enter Date Of Interview";*/
			return false;
		}
		//Date Of Interview Validation
		if(doi=="NULL" || doi==""){
			alert("Please Enter Date Of Interview");
			/*document.getElementById('warningbox').style.visibility="visible";
			document.getElementById('warningmsg').innerHTML="Please Enter Date Of Interview";*/
			return false;
		}
		
		//Time Validation
		if(toi=="NULL" || toi==""){
			alert("Please Enter Time Of Interview");
			/*document.getElementById('warningbox').style.visibility="visible";
			document.getElementById('warningmsg').innerHTML="Please Enter Time Of Interview";*/
			return false;
		}
		
		
		//Examiner's Name Validation
		if(tn=="" || tn=="NULL"){
			alert("Please Select Examiner's Name");
			/*document.getElementById('warningbox').style.visibility="visible";
			document.getElementById('warningmsg').innerHTML="Please Select Examiner's Name";*/
			return false;
		}

		//Reviewer's Name Validation
		if(rvn=="" || rvn=="NULL"){
			alert("Please Select Reviewer's Name");
			/*
			document.getElementById('warningbox').style.visibility="visible";
			document.getElementById('warningmsg').innerHTML="Please Select Reviewer's Name";*/
			return false;
		}
		
		/*
		//Room Number Validation
		if(rn=="" || rn==" "){
			alert("Please Enter Room Number");
			document.getElementById('warningbox').style.visibility="visible";
			document.getElementById('warningmsg').innerHTML="Please Enter Room Number";
			return false;
		}*/
		if(!rn.match(cr)){
			alert("Invalid Room Number Provided");
			/*document.getElementById('warningbox').style.visibility="visible";
			document.getElementById('warningmsg').innerHTML="Invalid Room Number Provided";*/
			return false;
		}

		//Venue Validation
		if(v==""){
			alert("Please Enter Venue");
			/*document.getElementById('warningbox').style.visibility="visible";
			document.getElementById('warningmsg').innerHTML="Please Enter Venue";*/
			return false;
		}
		if(!v.match(cr)){
			alert("Please Enter Valid Venue");
			/*document.getElementById('warningbox').style.visibility="visible";
			document.getElementById('warningmsg').innerHTML="Please Enter Valid Venue";*/
			return false;
		}
		
	}	