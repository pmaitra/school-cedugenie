function deleteRow(table){
    var rowCount = table.rows.length;
    for(var i=rowCount; i>=1; i--){
   		table.deleteRow(i-1);
    }
}

$(document).ready(function(){
	var retVal =  null;
	$("#submit").click(function(){
		var textVal = $("#rollNumber").val();
		$(".defaultselect").each(function(){
			var selectVal = $(this).val();			
			if(selectVal == ""){
	        	retVal = false;
	        	return false;
			}
			if(selectVal != ""){
	        	retVal = true;
	        	return true;
			}
		});
		
		if(textVal != ""){
			/*$('input:checkbox').each(function(){		
				if($(this).not(':checked')){
					document.getElementById("warningbox").style.display='block';		        		
	        		document.getElementById("warningmsg").innerHTML="Please check the checkbox.";
	        		retVal = false;
					return false;
				}
			});*/
			if(($("#studentFeeDetailsBody input:checked").length) == 0){
				document.getElementById("warningbox").style.display='block';		        		
        		document.getElementById("warningmsg").innerHTML="Please check at least one checkbox.";
        		retVal = false;
				return false;
			}
			$('input:checkbox').each(function(){
				if($(this).is(':checked')){
					var payingVal = $(this).parent().next().next().next().next().next().next().find('input:text').val();
					var totalVal = $(this).parent().next().next().next().find('input:hidden').val();
					var feesDuration = $(this).parent().next().next().find('input:hidden').val();
					var paidVal = $(this).parent().next().next().next().next().find('input:hidden').val();
					var checkVal = "";
		  			if(payingVal==""){
		  				document.getElementById("warningbox").style.display='block';		        		
		        		document.getElementById("warningmsg").innerHTML="Please Enter paying amount for selected categoy.";
		  				retVal = false;
		  				return false;
		  			}
		 
		  			payingVal = parseFloat(payingVal);
		  			totalVal = parseFloat(totalVal);
		  			paidVal = parseFloat(paidVal);
		  			checkVal = paidVal + payingVal;
		  			if(feesDuration=='QUARTERLY'){
		  				totalVal=totalVal*4;
		  			}
		  			if(isNaN(payingVal)){
		  				document.getElementById("warningbox").style.display='block';		        		
		        		document.getElementById("warningmsg").innerHTML="Please Enter paying amount in numerical format.";
		  				retVal = false;
		  				return false;
		  			}
		  			if(checkVal>totalVal){
		  				document.getElementById("warningbox").style.display='block';		        		
		        		document.getElementById("warningmsg").innerHTML="Paying amount is exceeding the total amount.";
		  				retVal = false;
		  				return false;
		  			}
		  			else{
		  				retVal = true;
		  				return true;
		  			}
	  			}
			});
		}
		return retVal;
	});
	
	/*$("#standardName").change(function(){
		$.ajax({
	        url: '/cedugenie/getCourseForClass.html',
	        data: "standard=" + ($(this).val()),
	        dataType: 'json',
	        success: function(data) {
	        	if(data == ""){
		        	alert("Course is not created for particular class.");
	        	}
	        	if(data != ""){
		        	var courses = data.split("#");
		        	var s4 = document.getElementById("courseName");
		        	for(var i=s4.length;i>0;i--){
						s4.remove(i);
					}
		        	for(var i=0;i<courses.length;i++){
		        		var course=courses[i].split("*");
		        		s4.add(new Option(course[1], course[0]),null);
		        	}
	        	}
        	}
		}); 
	});*/
	

	
	
	$("#standardName").change(function(){
		$.ajax({
	        url: '/cedugenie/getSectionForStandard.html',
	        data:"standard=" + ($(this).val()),
	        dataType: 'json',
	        success: function(data) {
	        	var i;
	        	if(data == ""){
	        		document.getElementById("warningbox").style.display='block';		        		
	        		document.getElementById("warningmsg").innerHTML="Section is not created for this standard.";
	        	}
	        	if(data != ""){
		        	var sec=data.split("#");
		        	
		        	var s4 = document.getElementById("sectionName");
		        	for(i=s4.length;i>0;i--){
		        		s4.remove(i);
		        	}
		        	for(i=0;i<sec.length;i++){
		        		var s=sec[i].split("*");
						s4.add(new Option(s[1], s[0]),null);
		        	}
		        }
	        }
		}); 
	});
	
	$("#sectionName").change(function(){
		$.ajax({
	        url: '/cedugenie/getStudentAgainstSection.html',
	        data:"standard=" +($("#standardName").val())+"&section=" + ($(this).val()),
	        dataType: 'json',
	        success: function(data) {
		        var s4 = document.getElementById("studentName");
	        	for(var i=s4.length;i>0;i--){
	        		s4.remove(i);
	        	}
	        	if(data == ""){
	        		document.getElementById("warningbox").style.display='block';		        		
	        		document.getElementById("warningmsg").innerHTML="No student.";
	        	}
	        	if(data != ""){
		        	var sec=data.split("#");
		        	for(var i=0;i<sec.length;i++){
		        		var s=sec[i].split("*");
						s4.add(new Option(s[1], s[0]),null);
		        	}
	        	}
	        }
		}); 
	});
	
	
	$("#studentName").change(function(){
		$('#rollNumber').val($(this).val());
		$('#netTotAmount').val("0.00");
		$.ajax({
	        url: '/cedugenie/getFeeStructureAgainstStudent.html',
	        data: "standard=" +($("#standardName").val())+"&academicSession=" +($("#academicSsession").val())+"&rollNumber=" +($("#rollNumber").val()),
	        dataType: 'json',
	        success: function(data) {
	        	if(data != null && data!=""){
	        		document.getElementById("feesDetailsTableDiv").style.display = 'block';
	        		var table = document.getElementById("studentFeeDetailsBody");
		            var rowCount = table.rows.length;
		            /*Added By Naimisha*/
		            for(var i=rowCount-1; i>=0; i--){
		          		table.deleteRow(i);
		           }
					var netTotAmount=0.00;
			        var d=data.split("^^");
			        
			        for(var i=0;i<d.length-1;i++){
		        		var ss=d[i].split("~");
		        		var s = ss[0].split("*");
	        			rowCount = table.rows.length;
			            var row = table.insertRow(rowCount);
			           
			            var cell,element;
			            var valueToPay;
			             
			            cell = row.insertCell(0);
			            element = document.createElement("input");
			            element.name="feeName";
			            element.id = "feeId"+i;
			            element.value=s[0];
			            element.type = "checkbox";
			            cell.appendChild(element);
			             
			            cell = row.insertCell(1);
			            element = document.createTextNode(s[1]);
			            cell.appendChild(element);
			            element = document.createElement("input");
			            element.type = "hidden";
			            element.name="feeCategory"+s[0];
			            element.id = "feeCategory"+s[0];
			            element.value = s[1];
			            cell.appendChild(element);
			            
			            cell = row.insertCell(2);
			            element = document.createTextNode(s[3]);
			            cell.appendChild(element);					             
			            element = document.createElement("input");
			            element.name = "duration"+s[0];
			            element.id = "duration"+s[0];
			            element.type = "hidden";
			            element.value = s[3];
			            cell.appendChild(element);
			             
			            cell = row.insertCell(3);
			            if(s[3]=="YEARLY"){
			            	element = document.createTextNode("("+s[2]+" x 1)="+s[2]);
			            	valueToPay = s[2];
			            }
			            else if(s[3]=="HALF YEARLY"){
			            	element = document.createTextNode("("+s[2]+" x 2)="+(parseFloat(s[2])*2));
			            	valueToPay = (parseFloat(s[2])*2);
			            }
			            else if(s[3]=="QUARTERLY"){
			            	element = document.createTextNode("("+s[2]+" x 4)="+(parseFloat(s[2])*4));
			            	valueToPay = (parseFloat(s[2])*4);
			            } 
			            else if(s[3]=="ONE TIME"){
			            	element = document.createTextNode("("+s[2]+" x 1)="+(parseFloat(s[2])*1));
			            	valueToPay = (parseFloat(s[2])*1);
			            }
			            else if(s[3]=="MONTHLY"){
			            	element = document.createTextNode("("+s[2]+" x 12)="+(parseFloat(s[2])*12));
			            	valueToPay = (parseFloat(s[2])*12);
			            }
			            else{
			            	element = document.createTextNode(s[2]);
			            	valueToPay = s[2];
			            }
			            cell.appendChild(element);
			            element = document.createElement("input");
			            element.type = "hidden";
			            element.name= "valueToPay"+s[0];
			            element.id= "valueToPay"+s[0];
			            element.value=valueToPay;
			            cell.appendChild(element);
			            //netTotAmount=netTotAmount+parseFloat(s[2]);
			            netTotAmount=netTotAmount+valueToPay;
			            
			            cell = row.insertCell(4);
			            element = document.createTextNode(s[4]);
			            cell.appendChild(element);
			            element = document.createElement("input");
			            element.type = "hidden";
			            element.name = "paidAmount"+s[0];
			            element.id = "paidAmount"+s[0];
			            element.value = s[4];
			            cell.appendChild(element);
			            
			            var dc = ss[1].split("#");
			            var comment = "";
			            for(var k = 0; k<dc.length; k++){
			            	//comment = comment + dc[k] + "<br>";
			            	comment += dc[k] + "<br>";
			            }
			            cell = row.insertCell(5);
			            //element = document.createTextNode();
			            cell.innerHTML = comment;
			            
			            if(valueToPay == s[4]){
			            	cell = row.insertCell(6);
				            /*element = document.createElement("input");
				            element.name="pay"+s[0];
				            element.type = "text";
				            element.setAttribute("class","form-control");
				            element.setAttribute("readonly", "readonly");
				            element.value = "PAID";
				            cell.appendChild(element);*/
				            cell.innerHTML = "PAID";
			            	
				            cell = row.insertCell(7);
				            /*element = document.createElement("textarea");
				            element.className='txtarea';
				            element.name="comment"+s[0];
				            element.setAttribute("class","form-control");
				            element.setAttribute("readonly", "readonly");
				            element.value = "N/A";
				            cell.appendChild(element);*/
				            cell.innerHTML = "";
				            
				            document.getElementById("feeId"+i).setAttribute("disabled" , "disabled");
				            document.getElementById("feeId"+i).setAttribute("class" , "feeNameClass");
			            }
			            else{
			            	cell = row.insertCell(6);
				            element = document.createElement("input");
				            element.name="pay"+s[0];
				            element.type = "text";
				            element.setAttribute("class","textfield1 form-control");
				            cell.appendChild(element);
				             
				            cell = row.insertCell(7);
				            element = document.createElement("textarea");
				            element.className='txtarea';
				            element.name="comment"+s[0];
				            element.setAttribute("class","textarea form-control");
				            cell.appendChild(element);
				             
				            //document.getElementById("feeName").setAttribute("checked" , "checked");
			            }
		        	 }
			         $('#netTotAmount').val(netTotAmount);
			         checkAllCheckbox();
			         //document.getElementById("studentFeeDetails").style.display='block';
		        	}
		        	else{
		        		document.getElementById("warningbox").style.display='block';		        		
		        		document.getElementById("warningmsg").innerHTML="Fees Category Not Found";
		        	}
	        	}
			}); 
		});
	});
function checkAllCheckbox(){
	var diasbledBox = $('.feeNameClass').length;
	var rowCount = $('#studentFeeDetailsBody tr').length;
	if(diasbledBox == rowCount){
		$("#footerId").css('display','none');
	}else{
		$("#footerId").css('display','block');
	}
}