$(document).ready( function(){
	/*$("#standard").change(function (){
		$.ajax({
		    url: '/icam/getTermsForACourse.html',
		    dataType: 'json',
		    data:"course=" +  $("#standard").val(),
		    success: function(data) {
		    	var options='<option value="">Select</option>';
		    	if(data!=null && data != ""){
	    			var termArr1 = data.split("*~*");
	    			for(var a=0; a<termArr1.length;a++){
	    				if(termArr1[a] != null && termArr1[a] != ""){
	    					var termNameAndCode=termArr1[a].split("#@#");  
							options=options+'<option value="'+termNameAndCode[0]+'">'+termNameAndCode[1]+'</option>';
						}
	    			}
		    	}
		    	document.getElementById("term").innerHTML=options;
		    }
		});
	});*/
	
	$("#standard").change(function (){
		document.getElementById("setExamMarksDiv").style.display = "block";
			$.ajax({
				url: '/icam/getExamsForTermAndCourse.html',
				dataType: 'json',
				data: "course=" + $("#standard").val(),
				success: function(data) {
					var options='<option value="">Select..</option>';
					if(data!=""){
						data=data.split("*~*");						
						for(var i=0;i<data.length-1;i++){
							var examcodeName = data[i].split("#@#")
							options=options+'<option value="'+examcodeName[0]+'">'+examcodeName[1]+'</option>';
						}
					}
					document.getElementById("exam").innerHTML=options;
				}
			});
			var tableBody=document.getElementById("tableBody");
			$.ajax({
			url: '/icam/getSubjectsForTermAndCourse.html',
			dataType: 'json',
			data: "course=" + $("#standard").val(),
			success: function(data) {
	        	if(data!=null && data!=""){
	        		data=data.split("#*#");
	        		var text="";
	        		for(var i=0;i<data.length-1;i++){
	        			var sub=data[i].split("#~#");
	        			var y =data.length;
	        		    document.getElementById("length").value=y;
	        			var calculate="calculate(\""+i+"\");";
	        			var check="check(\""+i+"\");";
	        			
	        			text=text+"<tr class='gradeX'>";
	        			text=text+"<td><input style='width:80px;' type='hidden' id='subject"+i+"' name='subject' value='"+sub[1]+"'>"+sub[1]+"</td>";
	        			text=text+"<td><input style='width:80px;' type='text' class='form-control' onfocus='makeZero(this);' onblur='"+calculate+"' id='theory"+i+"' name='"+sub[1]+"theory' value='0' required></td>";
	        			text=text+"<td><input style='width:80px;' type='text' class='form-control' onfocus='makeZero(this);' onblur='"+calculate+"' id='theoryPass"+i+"' name='"+sub[1]+"theoryPass' value='0' required></td>";
	        			text=text+"<td><input style='width:80px;' type='text' class='form-control' onfocus='makeZero(this);' onblur='"+calculate+"' id='practical"+i+"' name='"+sub[1]+"practical' value='0'></td>";
	        			text=text+"<td><input style='width:80px;' type='text' class='form-control' onfocus='makeZero(this);' onblur='"+calculate+"' id='practicalPass"+i+"' name='"+sub[1]+"practicalPass' value='0'></td>";
	        			text=text+"<td><input style='width:80px;' type='text' class='form-control' id='total"+i+"' readonly='readonly' name='"+sub[1]+"total' value='0'></td>";
	        			text=text+"<td><input style='width:80px;' type='text' class='form-control' onfocus='makeZero(this);' onblur='"+check+"' id='pass"+i+"' name='"+sub[1]+"pass' value='0'></td>";
	        			text=text+"</tr>";
	        		}
	        		tableBody.innerHTML=text;
	        	}else{
	        		alert("Subjects not found for standard");
	        	}
			}
		});
	});
	$("#exam").change(function (){
		$.ajax({
	        url: '/icam/getSubjectsAndMarksForCourseExamTerm.html',
	        data: "course=" + ($("#standard").val())+ "&exam=" + ($("#exam").val())+ "&term=" + ($("#term").val()),
	        dataType: 'json',
	        success: function(data) {
	    		if(data!=null && data!=""){
					data=data.split("###");
					for(var i=0;i<data.length-1;i++){
						var arr=data[i].split("*~*");
						document.getElementsByName(arr[0]+"theory")[0].value=arr[4];
						document.getElementsByName(arr[0]+"practical")[0].value=arr[5];
						document.getElementsByName(arr[0]+"total")[0].value=arr[6];
						document.getElementsByName(arr[0]+"pass")[0].value=arr[7];
						document.getElementsByName(arr[0]+"theoryPass")[0].value=arr[8];
						document.getElementsByName(arr[0]+"practicalPass")[0].value=arr[9];
					}
				}else{
					var x = document.getElementsByTagName("input");
					for (var i = 0; i < x.length; i++) {
					    if (x[i].type == "text"){
					    	x[i].value=0;
					    }
					}
				}
	        }
	    });
	});
});
function makeZero(box){
	if(box.value=="0")
		box.value="";
}
function calculate(num){
	var intRegx=numeric=/^[0-9]{1,}$/;
	var t = 0;
	var p = 0;
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
	
	if(theoryPassMarks==null || theoryPassMarks==0){
	}
	if(!theoryMarks.match(intRegx)){
		document.getElementById("javascriptmsg2").style.display = 'block';
		document.getElementById("javascriptmsg2").innerHTML = "Please enter any marks";
		//alert("Invalid Marks. (Numeric. Atleat 1 character.)");
		theory.value="0";
		document.getElementById("javascriptmsg2").style.display = 'none';
	}else{
		theoryMarks = parseInt(theoryMarks);
		if(theoryMarks<0){
			document.getElementById("javascriptmsg2").style.display = 'block';			
			document.getElementById("javascriptmsg2").innerHTML = "The marks you've entered is invalid.";
			//alert("Invalid Marks. (+ve Numbers Only.)");
			theory.value="0";
			document.getElementById("javascriptmsg2").style.display = 'none';
		}else{
			t=theoryMarks;
		}
	}
	if(!practicalMarks.match(intRegx)){
		document.getElementById("javascriptmsg2").style.display = 'block';			
		document.getElementById("javascriptmsg2").innerHTML = "Please enter any marks";
		practical.value="0";
		document.getElementById("javascriptmsg2").style.display = 'none';
	}else{
		practicalMarks = parseInt(practicalMarks);
		if(practicalMarks<0){
			document.getElementById("javascriptmsg2").style.display = 'block';			
			document.getElementById("javascriptmsg2").innerHTML = "The marks you've entered is invalid.";
			practical.value="0";
			document.getElementById("javascriptmsg2").style.display = 'none';
		}else{
			p=practicalMarks;
		}
	}
	if(!theoryPassMarks.match(intRegx)){
		document.getElementById("javascriptmsg2").style.display = 'block';			
		document.getElementById("javascriptmsg2").innerHTML = "Please enter any marks";
		theoryPass.value="0";
		document.getElementById("javascriptmsg2").style.display = 'none';
	}else{
		theoryPassMarks = parseInt(theoryPassMarks);
		if(theoryPassMarks<0){
			document.getElementById("javascriptmsg2").style.display = 'block';			
			document.getElementById("javascriptmsg2").innerHTML = "The marks you've entered is invalid.";
			theoryPass.value="0";
			document.getElementById("javascriptmsg2").style.display = 'none';
		}else{
			if(theoryPassMarks>t){
				document.getElementById("javascriptmsg2").style.display = 'block';			
				document.getElementById("javascriptmsg2").innerHTML = "Theory Pass Marks Cannot Be Greater Than Theory Marks.";
				//alert("Theory Pass Marks Cannot Be Greater Than Theory Marks");
				theoryPass.value="0";
				document.getElementById("javascriptmsg2").style.display = 'none';
			}
			else{
				tp = theoryPassMarks;
			}
		}
	}
	if(!practicalPassMarks.match(intRegx)){
		document.getElementById("javascriptmsg2").style.display = 'block';			
		document.getElementById("javascriptmsg2").innerHTML = "Please enter any marks";
		practicalPass.value="0";
		document.getElementById("javascriptmsg2").style.display = 'none';
	}else{
		practicalPassMarks = parseInt(practicalPassMarks);
		if(practicalPassMarks<0){
			document.getElementById("javascriptmsg2").style.display = 'block';			
			document.getElementById("javascriptmsg2").innerHTML = "The marks you've entered is invalid.";
			practicalPass.value="0";
			document.getElementById("javascriptmsg2").style.display = 'none';
		}else{
			if(practicalPassMarks>p){
				document.getElementById("javascriptmsg2").style.display = 'block';			
				document.getElementById("javascriptmsg2").innerHTML = "Practical Pass Marks Cannot Be Greater Than Practical Marks";
				//alert("Practical Pass Marks Cannot Be Greater Than Practical Marks");
				practicalPass.value="0";
				document.getElementById("javascriptmsg2").style.display = 'none';
			}
			else{
				pp = practicalPassMarks;
			}
		}
	}
	document.getElementById("total"+num).value = t+p;
	document.getElementById("pass"+num).value = tp+pp;
}
function check(num){
	var intRegx=numeric=/^[0-9]{1,}$/;
	var total=document.getElementById("total"+num).value;
	var pass=document.getElementById("pass"+num).value;
	total = parseInt(total);
	
	if(!pass.match(intRegx)){
		document.getElementById("javascriptmsg2").style.display = 'block';			
		document.getElementById("javascriptmsg2").innerHTML = "Please enter any marks";
		document.getElementById("pass"+num).value="0";
		document.getElementById("javascriptmsg2").style.display = 'none';
	}else{
		pass = parseInt(pass);
		if(pass<0){
			document.getElementById("javascriptmsg2").style.display = 'block';			
			document.getElementById("javascriptmsg2").innerHTML = "The marks you've entered is invalid.";
			document.getElementById("pass"+num).value="0";
			document.getElementById("javascriptmsg2").style.display = 'none';
		}else{
			if(pass>total){
				document.getElementById("javascriptmsg2").style.display = 'block';			
				document.getElementById("javascriptmsg2").innerHTML = "Pass Marks Cannot Be Greater Than Total Marks";
				document.getElementById("pass"+num).value="0";
				document.getElementById("javascriptmsg2").style.display = 'none';
			}
		}
	}
}
	
function validate(){
	var standard = document.getElementById("standard").value;
	if(standard=="" ||standard =='null' ){
		alert("Select Standard.");
		return false;
	}
	var course = document.getElementById("course").value;
	if(course=="" ||course =='null' ){
		alert("Select course.");
		return false;
	}
	
	var exam = document.getElementById("exam").value;
	if(exam=="" || exam =='null'){
		alert("Select Exam.");
		return false;
	}
	return true;
}
	
	
function detailsSubmit(){
	var length= document.getElementById("length").value;
	for(var i=0;i<length-1;i++){
		var practical=document.getElementById("practical"+i).value;
		var practicalPass=document.getElementById("practicalPass"+i).value;
		var theory=document.getElementById("theory"+i).value;
		var theoryPass=document.getElementById("theoryPass"+i).value;
		var pass=document.getElementById("pass"+i).value;
		/*if(practical==0||practicalPass==0||theoryPass==0||theory==0||pass==0){
			document.getElementById("javascriptmsg2").style.display = 'block';			
			document.getElementById("javascriptmsg2").innerHTML = "Please enter all Marks";	
			return false;
		}*/
	}
	return true;
}