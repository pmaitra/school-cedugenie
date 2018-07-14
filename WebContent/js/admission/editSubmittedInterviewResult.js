window.onload=function()
	{
		if(window.parent.document.getElementById('scheduleInterview')!=null){
			window.parent.document.getElementById('scheduleInterview').style.background = 'cyan';
		}
		if(window.parent.document.getElementById('formSubmission')!=null){
			window.parent.document.getElementById('formSubmission').style.background = 'cyan';
		}
		if(window.parent.document.getElementById('interviewResult')!=null){
			window.parent.document.getElementById('interviewResult').style.background = 'green';
		}
	};

	function editable()
	{
		document.getElementById('AddRow').removeAttribute('disabled');	
		document.getElementById('strComment').removeAttribute('readonly');
		
		var deleteImage=document.getElementsByName('deleteImage');
		for(var i=0;i<deleteImage.length;i++)
			deleteImage[i].setAttribute("onclick","deleteRow(this);");
		
		var subject=document.getElementsByName('subject');
		for(var i=0;i<subject.length;i++)
			subject[i].removeAttribute('readonly');
		
		var marks=document.getElementsByName('marks');
		for(var i=0;i<marks.length;i++){
			marks[i].removeAttribute('readonly');
		}
		
		for(var i=0;i<3;i++){
			document.getElementById("strStatus"+i).removeAttribute('disabled');
		}
		
		document.getElementById('submitbutton').removeAttribute('disabled');
		
		//document.getElementById('infomsgbox').style.visibility="visible";
		//document.getElementById('infomsg').innerHTML="Fields Are Now Editable";
		
	}

	function validate()
	{
		document.getElementById('infomsgbox').style.visibility="collapse";
		
		var sub=document.getElementsByName("subject");
		var mar=document.getElementsByName("marks");
		var len=sub.length;
		for(var i=0;i<len;i++)
		{
			subj=sub[i].value;
			mark=mar[i].value;
		 
		if (subj=="")
		{
			document.getElementById('warningbox').style.visibility="visible";
			document.getElementById('warningmsg').innerHTML="Please Enter Subject";
			return false;
		}
		if(mark=="")
		{
			document.getElementById('warningbox').style.visibility="visible";
			document.getElementById('warningmsg').innerHTML="Please Enter Marks";
			return false;
		}
		if(isNaN(mark))
		{
			document.getElementById('warningbox').style.visibility="visible";
			document.getElementById('warningmsg').innerHTML="Please Enter Valid Marks";
			return false;
		}	
		}
		return true;
	}


/*	function addRow()
	{
		document.getElementById("warningbox").style.visibility='collapse';
		document.getElementById('infomsgbox').style.visibility="collapse";
		
		var table = document.getElementById("dataTable"); 
		var rowCount = table.rows.length;
		var row = table.insertRow(rowCount-1);
		
		var cell1 = row.insertCell(0);
		var element1 = document.createElement("input");
		element1.type = "text";
		element1.name="subject";
		element1.setAttribute("class","textfield");
		cell1.appendChild(element1);
		
		var cell2 = row.insertCell(1);
		var element2 = document.createElement("input");
		element2.type = "text";
		element2.name="marks";
		element2.setAttribute("class","textfield");
		cell2.appendChild(element2);
			
		var cell3 = row.insertCell(2);
		element3 = document.createElement("img");
		element3.setAttribute("src","/sms/images/minus_icon.png");
		element3.setAttribute("onclick","deleteRow(this);");
		cell3.appendChild(element3);
	}
*/
	
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
	function deleteRow(obj)
	{
		
		var table = document.getElementById("dataTable");
		var rowCount = table.rows.length;
		
		if(rowCount>3){
			var p = obj.parentNode.parentNode;
			p.parentNode.removeChild(p);
		}else{
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Atleast One Row Required";
		}
	}