//window.onload = function(){	
//	document.getElementById('basicDetails').style.display = 'none';
//	document.getElementById('personalDetails').style.display = 'none';
//	document.getElementById('QualificationDetails').style.display = 'none';
//	document.getElementById('location').style.display = 'none';
//	document.getElementById('staffPreviousWorkDetails').style.display = 'none';	
//	document.getElementById('employeesDependents').style.display = 'none';	
//	document.getElementById('nominee').style.display = 'none';	
//	document.getElementById('employeesBankDetails').style.display = 'none';			
//	document.getElementById('workShopAndTrainingDetails').style.display = 'none';		
//	document.getElementById('awardsAndRecognization').style.display = 'none';
//	document.getElementById('publicationsDetailsDiv').style.display = 'none';		
//	document.getElementById('confidential').style.display = 'none';
//	document.getElementById('uploadImage').style.display = 'none';		
//	
//	$('#dateOfJoining').Zebra_DatePicker();    
//    $('#dateOfJoining').Zebra_DatePicker({
//    	  format: 'd/m/Y',    	  
//    	});
//    
//    $('#dateOfRetirement').Zebra_DatePicker();
//    $('#dateOfRetirement').Zebra_DatePicker({
//  	  format: 'd/m/Y'
//  	});  
//    
//    $('#dateOfBirth').Zebra_DatePicker(); 
//    $('#dateOfBirth').Zebra_DatePicker({
//    	  format: 'd/m/Y'
//    	});    
//    
//	$("#childDateOfBirth").Zebra_DatePicker();    
//    $("#childDateOfBirth").Zebra_DatePicker({
//    	  format: 'd/m/Y',    	  
//    	});
//    
//    $("#presentedOn").Zebra_DatePicker();    
//    $("#presentedOn").Zebra_DatePicker({
//    	  format: 'd/m/Y',    	  
//    	});
//};


//function unloadPopupBox() {    // TO Unload the Popupbox
//    $('#tempDetails').fadeOut("fast");
//    $("#personalDetails").css({ // this is just for style       
//        "opacity": "1" 
//    });
//} 

//function loadPopupBox() {    // To Load the Popupbox
//	$('#tempDetails').fadeIn("fast");
//	$("#personalDetails").css({ // this is just for style
//	    "opacity": "0.3"
//	});  
//} 

$(document).ready(function() {
    //add more file components if Add is clicked
    	$("#addFile").click(function(){                        		
    		var tableNode = $(this).parent().parent().parent().parent();
    		 var row = $('<tr>'); 
             row.append($('<td><input type="file" name="resource.uploadFile.qualificationRelatedFile" /><img src="/cedugenie/images/minus_icon.png"   onclick="deleteThisRow(this);"></td>'));
        $(tableNode).append(row); 
		});
    	
    	$("#addFile2").click(function(){                        		
    		var tableNode = $(this).parent().parent().parent().parent();
    		 var row = $('<tr>'); 
             row.append($('<td><input type="file" name="resource.uploadFile.experienceRelatedFile" /><a  class = "fa fa-minus-square" onclick="deleteThisRow(this);"></a></td>'));
        $(tableNode).append(row); 
		});
    	
    	$("#addFile3").click(function(){                        		
    		var tableNode = $(this).parent().parent().parent().parent();
    		 var row = $('<tr>'); 
             row.append($('<td><input type="file" name="resource.uploadFile.publicationRelatedFile" /><a  class = "fa fa-minus-square" onclick="deleteThisRow(this);"></a></td>'));
        $(tableNode).append(row); 
		});

    
    $(".permanentCountrys").change(
			function() {
				$.ajax({
			        url: '/cedugenie/getStateList.html',
			        dataType: 'json',
			        data: "permanentAddressCountry=" + ($(this).val()),
			        success: function(dataDB) {
			        	removePermanentStateOption();
			        	if(dataDB != "null" && dataDB !=""){
			        		var arr = dataDB.split(";");	
							for (var i=0;i<arr.length;i++)	{        									
								$(".permanentAddressState").append($("<option></option>").val(arr[i]).html(arr[i]));
							}							
						}
			       }
				});	      
	});
	
	$(".presentCountrys").change(
			function() {
				$.ajax({
			        url: '/cedugenie/getStateList.html',
			        dataType: 'json',
			        data: "presentAddressCountry=" + ($(this).val()),
			        success: function(dataDB) {
			        	removePresentStateOption();
			        	if(dataDB != "null" && dataDB !=""){			        		
			        		var arr = dataDB.split(";");	
							for (var i=0;i<arr.length;i++)	{        									
								$(".presentAddressState").append($("<option></option>").val(arr[i]).html(arr[i]));
							}							
						}
			       }
				});	      
			});
	
		$("#designationName").change(function(){
	    	$.ajax({
		        url: '/cedugenie/getDesignationLevelListForDesignation.html',
		        dataType: 'json',
		        data: "designation=" + ($(this).val()),
		        success: function(dataDB) {		        	
		        	var levelSelect =document.getElementById("designationLevel"); 
		        	if(dataDB != null)
					{
		        		for(var i=levelSelect.length;i>0;i--){
		        			levelSelect.remove(i);
			        	}
		        		
		        		var arrForLevel = dataDB.split("@");
						for (var j=0;j<arrForLevel.length-1;j++){   
							var levelList = arrForLevel[j].split("~");						
		                    $("#designationLevel").append($("<option></option>").val(levelList[0]).html(levelList[1]));
						}	
					}	        
		        }
			});	      
	    });
	$("#popupBoxYes").click(function(){
		unloadPopupBox();
	});
});

function deleteThisRow(obj){	
	var parent = obj.parentNode.parentNode;
	parent.parentNode.removeChild(parent);
}

//function checkBasicDetails(bvalue,divId,imgId){	
//	if(bvalue=="Collapse")	{
//	document.getElementById(divId).style.display = 'block';
//	document.getElementById(imgId).value = 'Expand';
//	document.getElementById(imgId).src = '/cedugenie/images/minus_icon.png';
//	var innerHeight2=document.body.scrollHeight;
// 	var frame=window.parent.document.getElementById("frame");	    	
// 	frame.style.height = innerHeight2+250+ 'px';
//	return true;
//	}
//	else{
//	document.getElementById(divId).style.display = 'none';
//	document.getElementById(imgId).value = 'Collapse';
//	document.getElementById(imgId).src = '/cedugenie/images/plus_icon.png';
//	var innerHeight2=document.body.scrollHeight;
//	var frame=window.parent.document.getElementById("frame");	    	
//	frame.style.height = innerHeight2+250+ 'px';
//	return true;
//	}
//}

//function check(bvalue,divId,imgId){
//	if(bvalue=="Expand")	{
//	document.getElementById(divId).style.display = 'none';
//	document.getElementById(imgId).value = 'Collapse';
//	document.getElementById(imgId).src = 'images/plus_icon.png';
//	var innerHeight2=document.body.scrollHeight;
// 	var frame=window.parent.document.getElementById("frame");	    	
// 	frame.style.height = innerHeight2+250+ 'px';
//	return true;
//	}
//	else{
//	document.getElementById(divId).style.display = 'block';
//	document.getElementById(imgId).value = 'Expand';
//	document.getElementById(imgId).src = 'images/minus_icon.png';
//	var innerHeight2=document.body.scrollHeight;
// 	var frame=window.parent.document.getElementById("frame");	    	
// 	frame.style.height = innerHeight2+250+ 'px';
//	return true;
//	}
//}

//function enableDisableWorkingExperience(valueForExpFrs) {
//	if(valueForExpFrs=="experienced"){
//	document.getElementById('totWorkExpYear').removeAttribute('disabled'); 
//	document.getElementById('totWorkExpMonth').removeAttribute('disabled');
//	}  
//   if(valueForExpFrs=="fresher"){
//	document.getElementById('totWorkExpYear').setAttribute('disabled','disabled'); 
//	document.getElementById('totWorkExpMonth').setAttribute('disabled','disabled');
//	}
//}

function validateFile(){
	if(!fileSelected()){
		document.getElementById("fileError").style.visibility='visible';
	}
}

function addOtherQualificationDetails(){
	var table = document.getElementById("OtherQualificationDetails"); 
    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);				
    					
    /*var cell1 = row.insertCell(0);
    var element1 = document.createElement("input");
    element1.type = "checkbox";
    element1.name="chkbox[]";
    cell1.appendChild(element1);    */
    
    var cell2 = row.insertCell(0);		
    var element2 = document.createElement("input");
	element2.type = "text";
    element2.name="othersExamName";
    element2.className ="form-control";
    cell2.appendChild(element2);         	

    var cell3 = row.insertCell(1);		
    var element3 = document.createElement("input");
    element3.type = "text";
    element3.name="othersSpecialization";
    element3.className ="form-control";
    cell3.appendChild(element3);    
    
    var cell4 = row.insertCell(2);		
    var element4 = document.createElement("input");
	 element4.type = "text";
    element4.name="othersSchoolCollege";
    element4.className ="form-control";
    cell4.appendChild(element4);
    
    var cell5 = row.insertCell(3);		
    var element5 = document.createElement("input");
    element5.type = "text";
    element5.name="othersBoardUniversity";
    element5.className ="form-control";
    cell5.appendChild(element5);					        
    
    var cell6 = row.insertCell(4);		
    var element6 = document.createElement("input");
    element6.type = "text";
    element6.name="othersMarks";
    element6.className ="form-control";
    cell6.appendChild(element6);
    
    var cell7 = row.insertCell(5);		
    var element7 = document.createElement("input");
    element7.type = "text";
    element7.name="othersPassingYear";
    element7.className ="form-control";
    cell7.appendChild(element7);
    
    var cell8 = row.insertCell(6);		
	var element8= document.createElement('a');	
	element8.setAttribute("class","fa fa-minus-square");
	element8.setAttribute("onclick", "deleteRow(this);");
	element8.setAttribute("href","#");
	cell8.appendChild(element8);	
}

function deleteRow(obj){	
	var table = document.getElementById("OtherQualificationDetails");
	var rowCount = table.rows.length;	
	if(rowCount>=2){
		var p = obj.parentNode.parentNode;
		p.parentNode.removeChild(p);
	}
}

function addChilOfEmployee(){
	var table = document.getElementById("childOfEmployee"); 
    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);				
    					
    /*var cell1 = row.insertCell(0);
    var element1 = document.createElement("input");
    element1.type = "checkbox";
    element1.name="chkbox[]";
    cell1.appendChild(element1);    */
    
    var cell2 = row.insertCell(0);		
    var element2 = document.createElement("input");
	element2.type = "text";
    element2.name="childName";
    element2.className ="form-control";
    cell2.appendChild(element2);         	

    var cell3 = row.insertCell(1);		
    var element3 = document.createElement("input");
    element3.type = "text";
    element3.name="childDateOfBirth";
    element3.className ="form-control";
    cell3.appendChild(element3);
    
    var cell4 = row.insertCell(2);		
	var element4= document.createElement('select');	
	element4.name="childGender";
	element4.className ="form-control";
	var opt2 = document.createElement('option');
	opt2.value = "";
	opt2.innerHTML = "Select";
	element4.appendChild(opt2)
	var opt1 = document.createElement('option');
	opt1.value = "M";
	opt1.innerHTML = "MALE";
	element4.appendChild(opt1);
	var opt = document.createElement('option');
	opt.value = "F";
	opt.innerHTML = "FEMALE";
	element4.appendChild(opt)
	cell4.appendChild(element4);	
    
    
    var cell5 = row.insertCell(3);		
	var element5= document.createElement('a');	
	element5.setAttribute("class","fa fa-minus-square");
	element5.setAttribute("onclick", "deleteRowOfEmployeeChild(this);");
	element5.setAttribute("href","#");
	cell5.appendChild(element5);	
}


function deleteRowOfEmployeeChild(obj){	
	var table = document.getElementById("childOfEmployee");
	var rowCount = table.rows.length;	
	if(rowCount>=2){
		var p = obj.parentNode.parentNode;
		p.parentNode.removeChild(p);
	}
}


function deleteTable(tableID){
	var table =tableID;	
    var rowCount = table.rows.length;
    for(var i=0; i<rowCount; i++) {
        var row = table.rows[i];
        var chkbox = row.cells[0].childNodes[0];
        if(null != chkbox && true == chkbox.checked) {
            table.deleteRow(i);
            rowCount--;
            i--;
        }else{
        	document.getElementById("warningbox").style.visibility='visible';
    		document.getElementById("warningmsg").innerHTML="Please Select Checkbox To Delete";
		}
    }   
}


function makeReadOnly(chvalue){	
	if(chvalue=="checked")
	{			
		var addr1 = document.getElementById('presentAddress1').value;
		var addr2 = document.getElementById('presentAddress2').value;		
		var lmark = document.getElementById('presentLandMark').value;
		var city = document.getElementById('presentCity').value;				
		var postOffice = document.getElementById('presentPostOffice').value;
		var police = document.getElementById('presentPoliceStation').value;		
		var pin = document.getElementById('presentPin').value;
		var dist = document.getElementById('presentDistrict').value;
		var state = document.getElementById('presentState').value;
		var country = document.getElementById('presentCountry').value;
		
		document.getElementById('permanentAddress1').value = addr1;
		document.getElementById('permanentAddress1').readOnly = true;
		
		document.getElementById('permanentAddress2').value = addr2;
		document.getElementById('permanentAddress2').readOnly = true;
		
		document.getElementById('permanentPostOffice').value = postOffice;
		document.getElementById('permanentPostOffice').readOnly = true;
		
		document.getElementById('permanentLandMark').value = lmark;
		document.getElementById('permanentLandMark').readOnly = true;
		
		document.getElementById('permanentDistrict').value = dist;
		document.getElementById('permanentDistrict').readOnly = true;
		
		document.getElementById('permanentPoliceStation').value = police;
		document.getElementById('permanentPoliceStation').readOnly = true;
		
		document.getElementById('permanentCity').value = city;
		document.getElementById('permanentCity').readOnly = true;
		
		document.getElementById('permanentPin').value = pin;
		document.getElementById('permanentPin').readOnly = true;
		
		var stateOption=document.getElementById('permanentState');		
		var len=stateOption.options.length;		
		for(var i=len;i>0;i--){
			stateOption.remove(i);
			}
		
		stateOption.options[stateOption.options.length]= new Option(state, state);
		document.getElementById('permanentState').value = state;
		document.getElementById('permanentState').setAttribute('selected', 'selected');
		document.getElementById('permanentState').disabled = true;		
		
		document.getElementById('permanentCountry').value = country;		
		document.getElementById('permanentCountry').setAttribute('selected', 'selected');
	  document.getElementById('permanentCountry').disabled = true;
		
		document.getElementById('checked').value = 'unchecked';
	}
	else
	{
		document.getElementById('permanentAddress1').readOnly = false;	
		document.getElementById('permanentAddress1').value = '';
		
		document.getElementById('permanentAddress2').readOnly = false;
		document.getElementById('permanentAddress2').value = '';

		document.getElementById('permanentPostOffice').readOnly = false;
		document.getElementById('permanentPostOffice').value = '';
		
		document.getElementById('permanentLandMark').readOnly = false;
		document.getElementById('permanentLandMark').value = '';
		
		document.getElementById('permanentDistrict').readOnly = false;
		document.getElementById('permanentDistrict').value = '';
		
		document.getElementById('permanentPoliceStation').readOnly = false;
		document.getElementById('permanentPoliceStation').value = '';
		
		document.getElementById('permanentCity').readOnly = false;
		document.getElementById('permanentCity').value = '';
		
		document.getElementById('permanentPin').readOnly = false;
		document.getElementById('permanentPin').value = '';			
		
		var stateOptionelse=document.getElementById('permanentState');		
		var len=stateOptionelse.options.length;		
		for(var i=len;i>0;i--){
			stateOptionelse.remove(i);
			}
		document.getElementById('permanentState').disabled = false;
		
		document.getElementById('permanentCountry').value = '';	
		document.getElementById('permanentCountry').disabled = false;
		
		document.getElementById('checked').value = 'checked';
	}
}

function removePresentStateOption(){
	var x=document.getElementById("presentState");
	x.innerHTML="";
	
	var option=document.createElement("option");
	option.text="Please Select";
	option.value="";
	
	try	  {
	  // for IE earlier than version 8
	  x.add(option,x.options[0]);
	  }
	catch (e)	  {
	  x.add(option,0);
	  }
}

function removePermanentStateOption(){
	var x=document.getElementById("permanentState");
	x.innerHTML="";
	var option=document.createElement("option");
	option.text="Please Select";
	option.value="";
	try	  {
	  // for IE earlier than version 8
	  x.add(option,x.options[0]);
	  }
	catch (e)	  {
	  x.add(option,0);
	  }
}

function removeSalTemplate(){
	var x = document.getElementById("salaryTemplate");
	x.innerHTML = "";
	var option = document.createElement("option");
	option.text = "Please Select";
	option.value = "";
	try	  {
	  // for IE earlier than version 8
	  x.add(option,x.options[0]);
	  }
	catch (e)	  {
	  x.add(option,0);
	  }
}

function deleteTableRow(){
	var table = document.getElementById("salBUp"); 
	var rowCount = table.rows.length;
	
	if(rowCount > 1){
		for(var i = rowCount-1; i>0; i--){
			table.deleteRow(i);
		}
	}
}

function getDesignationForResourceType(pb){
var res_type = pb.value;
$.ajax({
    url: '/cedugenie/getDesignationForResourceType.html',
    dataType: 'json',        
    data: "resourceType=" + res_type,
    success: function(dataDB) {	
    	var selectNode = document.getElementById("designationName");        	
    	if(dataDB != null)
		{
    		for(var i=selectNode.length;i>0;i--){
    			selectNode.remove(i);
        	}
    		var arr = dataDB.split("@");
    			
			for (var i=0;i<arr.length-1;i++){
				var codeAndValue=arr[i].split("~");	
				var option = document.createElement("option");
				option.text = codeAndValue[1];
				option.value = codeAndValue[0];
				selectNode.appendChild(option);
			}
		}
    	if(dataDB == null){
    		for(var j=selectNode.length;j>0;j--){
    			selectNode.remove(j);
        	}
    	}
   }
});	
}

function copyAddress(copyAddressBox){
	if(copyAddressBox.checked==true){
		document.getElementById("permanentAddressCountry").value=document.getElementById("presentAddressCountry").value;
		document.getElementById("permanentAddressLine").value=document.getElementById("presentAddressLine").value;
		document.getElementById("permanentAddressLandmark").value=document.getElementById("presentAddressLandmark").value;
		document.getElementById("permanentAddressCityVillage").value=document.getElementById("presentAddressCityVillage").value;
		//document.getElementById("permanentAddressRailwayStation").value=document.getElementById("presentAddressRailwayStation").value;
		document.getElementById("permanentAddressPostOffice").value=document.getElementById("presentAddressPostOffice").value;
		document.getElementById("permanentAddressPoliceStation").value=document.getElementById("presentAddressPoliceStation").value;
		document.getElementById("permanentAddressPinCode").value=document.getElementById("presentAddressPinCode").value;
		document.getElementById("permanentAddressDistrict").value=document.getElementById("presentAddressDistrict").value;
		//document.getElementById("permanentAddressPhone").value=document.getElementById("presentAddressPhone").value;
		document.getElementById("permanentAddressState").value=document.getElementById("presentAddressState").value;
		
		document.getElementById("permanentAddressCountry").setAttribute("readonly","readonly");
		document.getElementById("permanentAddressLine").setAttribute("readonly","readonly");
		document.getElementById("permanentAddressLandmark").setAttribute("readonly","readonly");
		document.getElementById("permanentAddressCityVillage").setAttribute("readonly","readonly");
		//document.getElementById("permanentAddressRailwayStation").setAttribute("readonly","readonly");
		document.getElementById("permanentAddressPostOffice").setAttribute("readonly","readonly");
		document.getElementById("permanentAddressPoliceStation").setAttribute("readonly","readonly");
		document.getElementById("permanentAddressPinCode").setAttribute("readonly","readonly");
		document.getElementById("permanentAddressDistrict").setAttribute("readonly","readonly");
		//document.getElementById("permanentAddressPhone").setAttribute("readonly","readonly");
		document.getElementById("permanentAddressState").setAttribute("readonly","readonly");
	}else{
		document.getElementById("permanentAddressCountry").value="IND";
		document.getElementById("permanentAddressLine").value="";
		document.getElementById("permanentAddressLandmark").value="";
		document.getElementById("permanentAddressCityVillage").value="";
		//document.getElementById("permanentAddressRailwayStation").value="";
		document.getElementById("permanentAddressPostOffice").value="";
		document.getElementById("permanentAddressPoliceStation").value="";
		document.getElementById("permanentAddressPinCode").value="";
		document.getElementById("permanentAddressDistrict").value="";
		document.getElementById("permanentAddressPhone").value="";
		//document.getElementById("permanentAddressState").value="AN";
	}
}


