window.onload = function(){
	document.getElementById('personalDetails').style.display = 'none';
	document.getElementById('location').style.display = 'none';
	document.getElementById('studentPreviousEducationalDetails').style.display = 'none';
	document.getElementById('uploadImage').style.display = 'none';
	document.getElementById('examinationDetails').style.display = 'none';
	document.getElementById('bankDetails').style.display = 'none';
};

function check(bvalue,divId,imgId){
	if(bvalue=="Expand")	{
	document.getElementById(divId).style.display = 'none';
	document.getElementById(imgId).value = 'Collapse';
	document.getElementById(imgId).src = 'images/plus_icon.png';
	var innerHeight2=document.body.scrollHeight;
	var frame=window.parent.document.getElementById("frame");	    	
	frame.style.height = innerHeight2+250+ 'px';
	return true;
	}
	else{
	document.getElementById(divId).style.display = 'block';
	document.getElementById(imgId).value = 'Expand';
	document.getElementById(imgId).src = 'images/minus_icon.png';
	var innerHeight2=document.body.scrollHeight;
	var frame=window.parent.document.getElementById("frame");	    	
	frame.style.height = innerHeight2+250+ 'px';
	return true;
	}
}

function makeReadOnly(chvalue)
{	
if(chvalue=="checked"){
	var addr1 = document.getElementById('presentAddress1').value;
	var lmark = document.getElementById('presentAddressLandmark').value;
	var city = document.getElementById('presentAddressCityVillage').value;				
	var postOffice = document.getElementById('presentAddressPostOffice').value;
	var police = document.getElementById('presentAddressPoliceStation').value;		
	var pin = document.getElementById('presentAddressPinCode').value;
	var dist = document.getElementById('presentAddressDistrict').value;
	var state = document.getElementById('presentAddressState').value;
	var country = document.getElementById('presentAddressCountry').value;
				
		document.getElementById('permanentAddress1').value = addr1;
		document.getElementById('permanentAddress1').readOnly = true;
		
		document.getElementById('permanentAddressPostOffice').value = postOffice;
		document.getElementById('permanentAddressPostOffice').readOnly = true;
		
		document.getElementById('permanentAddressLandmark').value = lmark;
		document.getElementById('permanentAddressLandmark').readOnly = true;
		
		document.getElementById('permanentAddressDistrict').value = dist;
		document.getElementById('permanentAddressDistrict').readOnly = true;
		
		document.getElementById('permanentAddressPoliceStation').value = police;
		document.getElementById('permanentAddressPoliceStation').readOnly = true;
		
		document.getElementById('permanentAddressCityVillage').value = city;
		document.getElementById('permanentAddressCityVillage').readOnly = true;
		
		document.getElementById('permanentAddressPinCode').value = pin;
		document.getElementById('permanentAddressPinCode').readOnly = true;
		
		var stateOption=document.getElementById('permanentAddressState');
		var len=stateOption.options.length;
		for(var i=len;i>0;i--){
			stateOption.remove(i);
			}
		stateOption.options[stateOption.options.length]= new Option(state, state);
		document.getElementById('permanentAddressState').value = state;
		document.getElementById('permanentAddressState').disabled = true;		
		
		document.getElementById('permanentAddressCountry').value = country;	
		document.getElementById('permanentAddressCountry').disabled = true;
		
		document.getElementById('checked').value = 'unchecked';
	}
	else
	{
		document.getElementById('permanentAddress1').readOnly = false;	
		document.getElementById('permanentAddress1').value = '';
		
		document.getElementById('permanentAddressPostOffice').readOnly = false;
		document.getElementById('permanentAddressPostOffice').value = '';
		
		document.getElementById('permanentAddressLandmark').readOnly = false;
		document.getElementById('permanentAddressLandmark').value = '';
		
		document.getElementById('permanentAddressDistrict').readOnly = false;
		document.getElementById('permanentAddressDistrict').value = '';
		
		document.getElementById('permanentAddressPoliceStation').readOnly = false;
		document.getElementById('permanentAddressPoliceStation').value = '';
		
		document.getElementById('permanentAddressCityVillage').readOnly = false;
		document.getElementById('permanentAddressCityVillage').value = '';
		
		document.getElementById('permanentAddressPinCode').readOnly = false;
		document.getElementById('permanentAddressPinCode').value = '';		
		
		var stateOptionelse=document.getElementById('permanentAddressState');		
		var len=stateOptionelse.options.length;		
		for(var i=len;i>0;i--){
			stateOptionelse.remove(i);
			}
		document.getElementById('permanentAddressState').disabled = false;
		
		document.getElementById('permanentAddressCountry').value = '';	
		document.getElementById('permanentAddressCountry').disabled = false;
		
		document.getElementById('checked').value = 'checked';
	}
}

var ct = 0; 
function new_link(){  
	
	var newlink=document.getElementById("newlinktpl");
	var newfileData=document.getElementsByName("uploadFile.fileData");	
	var flag=0;
	for(var i= 0;i<newfileData.length;i++){
		if(newfileData[i].value !="" && newfileData[i].value !=null)
			{
			flag++;
			}				
	}
	if(newfileData.length == flag){
		ct++;
		
		var fileID="fileData"+ct;
		
		var rowText="<input type='file' name='uploadFile.fileData' id='"+fileID+"' >" +
		"<img src='/sms/images/minus_icon.png' id='"+ct+"' name='Delete' onclick='del(this.id);'>";
		
		newlink.innerHTML=rowText;
		
		}
	else{
		alert("Please select One Document");
		
	}
} 
function del(val){ 
	var newlink=document.getElementById("newlinktpl");
	var str="fileData"+val;
	var str1=val;
	var n=document.getElementById(str);
	var p=document.getElementById(str1);
	newlink.removeChild(n);
	newlink.removeChild(p);
}

$(document).ready(function() { 	
	$('#dateOfBirth').Zebra_DatePicker();
	 $('#endDate').Zebra_DatePicker();
	 
	 $('#dateOfBirth').Zebra_DatePicker({
	 	  format: 'd/m/Y',
	 	  direction: false
	 	});
	 $('#bankDate').Zebra_DatePicker({
	  format: 'd/m/Y',
	  direction: false
	});
	 $('#dateOfEnrolment').Zebra_DatePicker({
		  format: 'd/m/Y',
		  direction: false
		});

		 $('#dateOfDischarge').Zebra_DatePicker({
			  format: 'd/m/Y',
			  direction: false
			});dateOfDischarge
	
	
	
	

	
	$(".presentAddressCountry").change(
			function() {
				removePresentStateOption();
				$.ajax({
			        url: '/cedugenie/getStateList.html',
			        dataType: 'json',
			        data: "country=" + ($(this).val()),
			        success: function(dataDB) {
			        	if(dataDB != "null" && dataDB !="")
						{
			        		var arr = dataDB.split(";");	
							for (var i=0;i<arr.length;i++){   
								var state = arr[i].split("*");
								$(".presentAddressState").append($("<option></option>").val(state[1]).html(state[1]));
							}							
						}
			       }
				});	      
	});
	
	$(".permanentAddressCountry").change(
			function() {
	        	removePermanentStateOption();

				$.ajax({
			        url: '/cedugenie/getStateList.html',
			        dataType: 'json',
			        data: "country=" + ($(this).val()),
			        success: function(dataDB) {
			        	if(dataDB != "null" && dataDB !=""){
			        		var arr = dataDB.split(";");	
							for (var i=0;i<arr.length;i++)	{ 
								var state = arr[i].split("*");
								$(".permanentAddressState").append($("<option></option>").val(state[1]).html(state[1]));
							}							
						}
			       }
				});	      
	});
	
});


function removeOption(x)
{
	for(var i=x.length;i>0;i--)
	{
		x.remove(i);
	}
}

function removePresentStateOption()
{
	var x=document.getElementById("presentAddressState");
	x.innerHTML="";
	
	var option=document.createElement("option");
	option.text="Please Select";
	option.value="";
	
	try
	  {
	  // for IE earlier than version 8
	  x.add(option,x.options[0]);
	  }
	catch (e)
	  {
	  x.add(option,0);
	  }
}

function removePermanentStateOption()
{
	var x=document.getElementById("permanentAddressState");
	x.innerHTML="";
	var option=document.createElement("option");
	option.text="Please Select";
	option.value="";
	try
	  {
	  // for IE earlier than version 8
	  x.add(option,x.options[0]);
	  }
	catch (e)
	  {
	  x.add(option,0);
	  }
}






