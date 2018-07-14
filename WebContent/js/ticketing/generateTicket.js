var checkBoxAnswerValue = "";
function validate(){
	
	var affectedUser = document.getElementById("affectedUser").value;	
	var ticketServiceName = document.getElementById("ticketServiceName").value;	
	if(affectedUser == ""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Please select effected user!";
		return false;
	}
	if(ticketServiceName == ""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Please select service type!";
		return false;
	}else{
		document.getElementById("warningbox").style.visibility='collapse';
		return true;
	}	
	
}

$(document).ready(
        function() {
            //add more file components if Add is clicked
            $(".addFileClassName").each(function(){
            	$(this).click(function(){                      		
            		var tableNode = $(this).parent().parent().parent().parent();
            		 var row = $('<tr>'); 
	                 row.append($('<td><input type="file" name="uploadFile.ticketingRelatedFile" /></td><td><img  src="/icam/images/minus_icon.png" onclick="deleteThisRow(this);"></td>'));
	            $(tableNode).append(row); 
				});
            });
        });

function deleteThisRow(obj){	
	var parent = obj.parentNode.parentNode;
	parent.parentNode.removeChild(parent);
}

$(document).ready(function() {
	$("#ticketServiceName").change(function() {
		//alert("hiii");
		$.ajax({
			url: '/icam/getKeyDetailsForACategory.html',
			dataType: 'json',
			data: "category="+($("#ticketServiceName").val()),
			success: function(data){ 
				//alert(data);
				var divELement = "";
				var dataArr = data.split("#");
				var count = 0;
				divELement  = divELement + "<div class='row'>"
				for(var i=0; i < dataArr.length-1; i++){
					var keyArr = dataArr[i].split("*");
				//	alert("keyArr=="+keyArr);
					divELement  = divELement + "<div class='col-md-4'><label class='control-label'>"+keyArr[1]+"<span aria-required='true' class='required'>*</span></label><div class='form-group'><input type = 'hidden' class = 'form-control' id = 'key' name = 'key' value='"+keyArr[1]+"' required/><input type = 'text' class = 'form-control' id = '"+keyArr[1]+"key' name = '"+keyArr[1]+"key' value='' required/></div></div>"
					count = count + 1;
					if(count == 3){
						divELement  = divELement + "</div><div class='row'>"
					}
				}
				divELement  = divELement + "</div>";
				
				document.getElementById("divForKey").innerHTML = divELement;
				document.getElementById("divForKey").style.display = "block";
			}
		});
		
		$.ajax({
			url: '/icam/getSurveyDetailsForAJobType.html',
			dataType: 'json',
			data: "ticketServiceName="+($("#ticketServiceName").val()),
			success: function(data){  
				
				if(data != ""){
					var dataArr = data.split("##");
					//alert("dataArr[0]===="+dataArr[0]);
					var tableHead = document.getElementById("surveyhead");
					var tableBody = document.getElementById("surveyBody");
					var surveyArr = dataArr[0].split("*")
					var thead = "<tr><th colspan = '2'><input type = 'hidden' class = 'form-control' id = 'survey' name = 'survey' value = '"+surveyArr[1]+"' >"+surveyArr[0]+"</th></tr>";
					var tbody = "";
					tableHead.innerHTML = thead;
					var dataArrqA = dataArr[1].split(";");
					for(var i=0;i< dataArrqA.length -1;i++){
						var dattaAy = dataArrqA[i].split("*");
						tbody = tbody + "<tr><td>"+dattaAy[0]+"<input type = 'hidden' class = 'form-control' id = 'question' name = 'question' value = '"+dattaAy[2]+"' ></td>";
						//var dataAy = dattaAy[2].split("@");
						if(dattaAy[1]=='text'){
							tbody = tbody + "<td><input type = 'text' name = 'answer' class = 'form-control'></td></tr>";
						}else if(dattaAy[1]=='radio'){
							var dataradio = dattaAy[3].split("@");
							//tbody = tbody + "<td><input type = 'radio' name = 'answer'  id = '"+dataradio[0]+"' value = '"+dataradio[0]+"'>"+dataradio[0]+"<input type = 'radio' name = 'answer' id = '"+dataradio[1]+"' value = '"+dataradio[1]+"'>"+dataradio[1]+"</td></tr>";
							
							//tbody = tbody + "<td><input type = 'radio' name = 'radiobuttn'  id = '"+dataradio[0]+"' value = '"+dataradio[0]+"'>"+dataradio[0]+"<input type = 'radio' name = 'radiobuttn' id = '"+dataradio[1]+"' value = '"+dataradio[1]+"'>"+dataradio[1]+"</td></tr>";
							tbody = tbody + "<td>";
							for(var j = 0;j< dataradio.length-1; j++){
								tbody = tbody + "<input type = 'radio' name = 'answer'  id = '"+dataradio[j]+"' value = '"+dataradio[j]+"'>"+dataradio[j];
							}
							tbody = tbody + "</td></tr>";
						}else if(dattaAy[1]=='checkbox'){
							var datacheckbox = dattaAy[3].split("@");
							//tbody = tbody + "<td><input type = 'radio' name = 'answer'  id = '"+dataradio[0]+"' value = '"+dataradio[0]+"'>"+dataradio[0]+"<input type = 'radio' name = 'answer' id = '"+dataradio[1]+"' value = '"+dataradio[1]+"'>"+dataradio[1]+"</td></tr>";
							
							//tbody = tbody + "<td><input type = 'radio' name = 'radiobuttn'  id = '"+dataradio[0]+"' value = '"+dataradio[0]+"'>"+dataradio[0]+"<input type = 'radio' name = 'radiobuttn' id = '"+dataradio[1]+"' value = '"+dataradio[1]+"'>"+dataradio[1]+"</td></tr>";
							tbody = tbody + "<td><input type = 'hidden' name = 'answer' id = 'checkboxAnswer'>";
							for(var k = 0;k< datacheckbox.length-1; k++){
								tbody = tbody + "<input type = 'checkbox' name = 'checkboxValue'  id = '"+datacheckbox[k]+"' value = '"+datacheckbox[k]+"' onclick = 'return concatAnswers(this)'>"+datacheckbox[k];
							}
							tbody = tbody + "</td></tr>";
						}else if(dattaAy[1]=='textarea'){
							tbody = tbody + "<td><textarea id = 'answerTextarea' name = 'answer' class = 'form-control'></textarea></td></tr>";
						}
					}
					tableBody.innerHTML = tbody;
					
					document.getElementById("surveyDiv").style.display = "block";
					
				}else{
					
					document.getElementById("surveyDiv").style.display = "none";
				}
				
			}				
		}); 
	
	});
});

function concatAnswers(thisValue){
	//alert("hiii");
	var checkboxValue = thisValue.value;
	if ( document.getElementById(checkboxValue).checked == true ) {
		checkBoxAnswerValue = checkBoxAnswerValue + checkboxValue +",";
    }else if ( document.getElementById(checkboxValue).checked == false) {
    //	alert("within false");
    	var checkboxAnsArr = checkBoxAnswerValue.split(",");
    	//alert("checkboxAnsArr==="+checkboxAnsArr);
    	//alert("checkboxValue===="+checkboxValue);
    	checkBoxAnswerValue = "";
    	for(var i=0;i<checkboxAnsArr.length-1 ; i++){
    		if(checkboxValue != checkboxAnsArr[i]){
    			alert("not equal");
    			checkBoxAnswerValue = checkBoxAnswerValue +  checkboxAnsArr[i];
    		}
    	}
    }
	
	document.getElementById("checkboxAnswer").value = checkBoxAnswerValue;
	//alert("checkBoxAnswerValue=="+checkBoxAnswerValue);
}


$("#department").change(function() {
	var department = document.getElementById("ticketServiceName");
	removeOption(department);
	
	$.ajax({
		url: '/icam/getCategoryListForADepartment.html',
		dataType: 'json',
		data: "department="+($("#department").val()),
		success: function(data){ 
			
			var dataArr = data.split("#");
			for(var i=0;i<dataArr.length-1;i++){
				var dataDB = dataArr[i].split("*");
				$('#ticketServiceName')
   	         	.append($("<option></option>")
   	                    .attr("value",dataDB[0])
   	                    .text(dataDB[1])); 
   		
			}
		}
	});
});

function removeOption(x){
	for(var i=x.length;i>=0;i--){
		x.remove(i);
	}
	x.innerHTML="<option value=''>Select..</option>";
}


$("#standard").change(function(){
	document.getElementById("section").disabled = false;
	
	
	var sectionObject=document.getElementById("section");
	removeOption(sectionObject);

	
	$.ajax({
        url: '/icam/getSectionAgainstStandard.html',
        dataType: 'json',
        data: "standard=" + ($(this).val()),
        success: function(dataDB) {
        	var options="<option value=''>Select..</option>";
        	if(dataDB != "null" && dataDB !=""){
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

$("#section").change(function(){
	document.getElementById("rollNumber").disabled = false;
	
	
	var rollObject = document.getElementById("rollNumber");
	removeOption(rollObject);
	$.ajax({
		url: '/icam/getStudentsToAssignSection.html',
        dataType: 'json',
        data: "standard=" + ($("#standard").val())+ "&section="+($("#section").val()),
        success: function(dataDB) {
        	var options="<option value=''>Select..</option>";
        	//alert("dataDB=="+dataDB);
        	if(dataDB != "null" && dataDB !=""){
        		dataDB=dataDB.split(";");
            	
            	for(var i=0;i<dataDB.length;i++){
            		var rollName=dataDB[i].split("*");
            		options = options+"<option value='"+rollName[0]+"'>"+rollName[1]+"</option>";
            	}
            	rollObject.innerHTML = options;
        	}
        	
        }
	});
});


$("#ticketServiceName").change(function(){

	//alert("hii");
	var category = document.getElementById("ticketServiceName").value;
	//alert("category==="+category);
	if(category == "StudentLeave"){
		document.getElementById("standardDiv").style.display = "block";
		document.getElementById("sectionDiv").style.display = "block";
		document.getElementById("rollDiv").style.display = "block";
		document.getElementById("standard").setAttribute("required","required");
		document.getElementById("section").setAttribute("required","required");
		document.getElementById("rollNumber").setAttribute("required","required");
		document.getElementById("leaveStartDiv").style.display = "block";
		document.getElementById("fromDate").setAttribute("required","required");
		document.getElementById("leaveEndDiv").style.display = "block";
		document.getElementById("toDate").setAttribute("required","required");
	}else{
		document.getElementById("standardDiv").style.display = "none";
		document.getElementById("sectionDiv").style.display = "none";
		document.getElementById("rollDiv").style.display = "none";
		document.getElementById("standard").removeAttribute("required","required");
		document.getElementById("section").removeAttribute("required","required");
		document.getElementById("rollNumber").removeAttribute("required","required");
		document.getElementById("leaveStartDiv").style.display = "none";
		document.getElementById("fromDate").removeAttribute("required","required");
		document.getElementById("leaveEndDiv").style.display = "none";
		document.getElementById("toDate").removeAttribute("required","required");
	}
});