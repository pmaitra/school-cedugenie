$(document).ready(function(){
	var tableBody = document.getElementById("houseMasterDataTable");
	$("#academicYear").change(function() {
		deleteRow(tableBody);
		document.getElementById("warningbox").style.display='none';
		$.ajax({
	        url: '/cedugenie/fetchCreatedHouseMaster.html',
	        data: "academicYear=" + ($(this).val()),
	        dataType: 'text',
	        success: function(data) {
	        	if(data!=null && data!=""){
	        		data=data.split("###");
	        		var text="";
	        		for(var i=0;i<data.length-1;i++){
	        			var subMar=data[i].split("*~*");
	        			var calculate="calculate(\""+i+"\");";
	        			var check="check(\""+i+"\");";
	        			text=text+"<tr>";
	        			text=text+"<td><input type='hidden' id='subject"+i+"' name='subject' value='"+subMar[0]+"'>"+subMar[1]+"</td>";
	        			text=text+"<td><input type='text' onfocus='makeZero(this);' onblur='"+calculate+"' id='theory"+i+"' class='form-control textfield1' name='"+subMar[0]+"theory' value='"+subMar[4]+"'></td>";
	        			text=text+"<td><input type='text' onfocus='makeZero(this);' onblur='"+calculate+"' id='theoryPass"+i+"' class='form-control textfield1' name='"+subMar[0]+"theoryPass' value='"+subMar[8]+"'></td>";
	        			text=text+"<td><input type='text' onfocus='makeZero(this);' onblur='"+calculate+"' id='practical"+i+"' class='form-control textfield1' name='"+subMar[0]+"practical' value='"+subMar[5]+"'></td>";
	        			text=text+"<td><input type='text' onfocus='makeZero(this);' onblur='"+calculate+"' id='practicalPass"+i+"' class='form-control textfield1' name='"+subMar[0]+"practicalPass' value='"+subMar[9]+"'></td>";
	        			text=text+"<td><input type='text' id='total"+i+"' readonly='readonly' class='form-control textfield1' name='"+subMar[0]+"total' value='"+subMar[6]+"'></td>";
	        			text=text+"<td><input type='text' readonly='readonly' onfocus='makeZero(this);' onblur='"+check+"' id='pass"+i+"' class='form-control textfield1' name='"+subMar[0]+"pass' value='"+subMar[7]+"'></td>";
	        			text=text+"</tr>";
	        		}
	        		if(text!=""){
		        		tableBody.innerHTML=text;
		        		/*table.style.display='block';
	        			text="";*/
	        		}
        			
        			/*var innerHeight2=document.body.scrollHeight;
        			var frame=window.parent.document.getElementById("frame");	    	
        			frame.style.height = innerHeight2+150+ 'px';*/
        				        			
	        	}else{
	        		document.getElementById("warningmsg").innerHTML="Subjects Not Found For Standard "+$("standard").val();
	        		document.getElementById("warningbox").style.visibility='collapse';
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
function removeOption(x){
	for(var i=x.length;i>=0;i--){
		x.remove(i);
	}
	x.innerHTML="<option value=''>Select</option>";
}
function makeZero(box){
	if(box.value=="0")
		box.value="";
}
function calculate(num){
	var intRegx=numeric=/^[0-9]{1,}$/;
	var t=0;
	var p=0;
	var tp = 0;
	var pp = 0;
	var theory=document.getElementById("theory"+num);
	var theoryMarks=theory.value;
	theoryMarks=theoryMarks.replace(/\s{1,}/g,'');
	theory.value=theoryMarks;
	
	var practical=document.getElementById("practical"+num);
	var practicalMarks=practical.value;
	practicalMarks=practicalMarks.replace(/\s{1,}/g,'');
	practical.value=practicalMarks;
	
	var theoryPass=document.getElementById("theoryPass"+num);
	var theoryPassMarks=theoryPass.value;
	theoryPassMarks=theoryPassMarks.replace(/\s{1,}/g,'');
	theoryPass.value=theoryPassMarks;
	
	var practicalPass=document.getElementById("practicalPass"+num);
	var practicalPassMarks=practicalPass.value;
	practicalPassMarks=practicalPassMarks.replace(/\s{1,}/g,'');
	practicalPass.value=practicalPassMarks;
	
	
	if(!theoryMarks.match(intRegx)){
		document.getElementById("warningbox").style.display='block';
		document.getElementById("warningmsg").innerHTML="Invalid Marks. (Numeric. Atleat 1 character.)";
		theory.value="0";
	}else{
		theoryMarks = parseInt(theoryMarks);
		if(theoryMarks<0){
			document.getElementById("warningbox").style.display='block';
			document.getElementById("warningmsg").innerHTML="Invalid Marks. (+ve Numbers Only.)";
			theory.value="0";
		}else{
			t=theoryMarks;
		}
	}
	
	if(!practicalMarks.match(intRegx)){
		document.getElementById("warningbox").style.display='block';
		document.getElementById("warningmsg").innerHTML="Invalid Marks. (Numeric. Atleat 1 character.)";
		practical.value="0";
	}else{
		practicalMarks = parseInt(practicalMarks);
		if(practicalMarks<0){
			document.getElementById("warningbox").style.display='block';
			document.getElementById("warningmsg").innerHTML="Invalid Marks. (+ve Numbers Only.)";
			practical.value="0";
		}else{
			p=practicalMarks;
		}
	}
	
	if(!theoryPassMarks.match(intRegx)){
		document.getElementById("warningbox").style.display='block';
		document.getElementById("warningmsg").innerHTML="Invalid Marks. (Numeric. Atleat 1 character.)";
		theoryPass.value="0";
	}else{
		theoryPassMarks = parseInt(theoryPassMarks);
		if(theoryPassMarks<0){
			document.getElementById("warningbox").style.display='block';
			document.getElementById("warningmsg").innerHTML="Invalid Marks. (+ve Numbers Only.)";
			theoryPass.value="0";
		}else{
			if(theoryPassMarks>t){
				document.getElementById("warningbox").style.display='block';
				document.getElementById("warningmsg").innerHTML="Theory Pass Marks Cannot Be Greater Than Theory Marks";
				theoryPass.value="0";
			}
			else{
				tp = theoryPassMarks;
			}
		}
	}
	
	if(!practicalPassMarks.match(intRegx)){
		document.getElementById("warningbox").style.display='block';
		document.getElementById("warningmsg").innerHTML="Invalid Marks. (Numeric. Atleat 1 character.)";
		practicalPass.value="0";
	}else{
		practicalPassMarks = parseInt(practicalPassMarks);
		if(practicalPassMarks<0){
			document.getElementById("warningbox").style.display='block';
			document.getElementById("warningmsg").innerHTML="Invalid Marks. (+ve Numbers Only.)";
			practicalPass.value="0";
		}else{
			if(practicalPassMarks>p){
				document.getElementById("warningbox").style.display='block';
				document.getElementById("warningmsg").innerHTML="Practical Pass Marks Cannot Be Greater Than Practical Marks";
				practicalPass.value="0";
			}
			else{
				pp = practicalPassMarks;
			}
		}
	}
	
	document.getElementById("total"+num).value=t+p;
	document.getElementById("pass"+num).value = tp+pp;
}
function check(num){
	var intRegx=numeric=/^[0-9]{1,}$/;
	var total=document.getElementById("total"+num).value;
	var pass=document.getElementById("pass"+num).value;
	total = parseInt(total);
	
	if(!pass.match(intRegx)){
		document.getElementById("warningbox").style.display='block';
		document.getElementById("warningmsg").innerHTML="Invalid Pass Marks. (Numeric. Atleat 1 character.)";
		document.getElementById("pass"+num).value="0";
	}else{
		pass = parseInt(pass);
		if(pass<0){
			document.getElementById("warningbox").style.display='block';
			document.getElementById("warningmsg").innerHTML="Invalid Pass Marks. (+ve Numbers Only.)";			
		}else{
			if(pass>total){
				document.getElementById("warningbox").style.display='block';
				document.getElementById("warningmsg").innerHTML="Pass Marks Cannot Be Greater Than Total Marks";
				document.getElementById("pass"+num).value="0";
			}
		}
	}
}
function saveClassSubjectMapping(){
	var standard=document.getElementById("standard").value;
	if(standard==""){
		document.getElementById("warningbox").style.display='block';
		document.getElementById("warningmsg").innerHTML="Select Standard.";
		return false;
	}
	
	
	if(standard != ""){
		if(standard=='VI'||standard == 'VII'||standard=='VIII'){
			var term=document.getElementById("term").value;
			if(term==""){
				document.getElementById("warningbox").style.display='block';
				document.getElementById("warningmsg").innerHTML="Select Term.";
				return false;
			}
		}
	}
	
	
	/*var exam=document.getElementById("exam").value;*/
	if(document.getElementById("exam").value ==""){
		document.getElementById("warningbox").style.display='block';
		document.getElementById("warningmsg").innerHTML="Select Exam.";
		return false;
	}
	
	var marks = document.getElementsByClassName("textfield1");
	if(marks.length==0){
		document.getElementById("warningbox").style.display='block';
		document.getElementById("warningmsg").innerHTML="No Marks";
		return false;
	}
	var intRegx=numeric=/^[0-9]{1,}$/;
	for(var i=0; i<marks.length;i++){
		var amount=marks[i].value;
		amount=amount.replace(/\s{1,}/g,'');
		marks[i].value=amount;
		
		if(!amount.match(intRegx)){
			document.getElementById("warningbox").style.display='block';
			document.getElementById("warningmsg").innerHTML="Invalid Marks. (Numeric. Atleat 1 character.)";
			return false;
		}else{
			amount = parseFloat(amount);
			if(amount<0){
				document.getElementById("warningbox").style.display='block';
				document.getElementById("warningmsg").innerHTML="Invalid Marks. (+ve Numbers Only.)";
				return false;
			}
		}
	}
	if(!checkTheroryMarksGreaterThanZero()){
		return false;
	}
	return true;
}

function checkTheroryMarksGreaterThanZero(){
	var totalRows = document.getElementsByClassName("textfield1");
	for(var i=0 ; i<totalRows.length ; i++){
		var theoryMarks = document.getElementById("theory"+i).value;
		var theoryPassMarks = document.getElementById("theoryPass"+i).value;
		if(theoryMarks<=0){
			document.getElementById("warningbox").style.display='block';
			document.getElementById("warningmsg").innerHTML="Therory Marks need to be greater than zero.";
			return false;
		}
		if(theoryPassMarks<=0){
			document.getElementById("warningbox").style.display='block';
			document.getElementById("warningmsg").innerHTML="Therory Pass Marks need to be greater than zero.";
			return false;
		}
	}
	return true;
}
