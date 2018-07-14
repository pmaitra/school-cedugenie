	$(document).ready(function() { 	
		$(".presentAddressCountry").change(
				function() {
					removePresentStateOption();
					$.ajax({
						url: '/icam/getStateList.html',
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
		
		function removePresentStateOption(){
			var x=document.getElementById("presentAddressState");
			x.innerHTML="";
			var option=document.createElement("option");
			option.text="Please Select";
			option.value="";
			try{
			  // for IE earlier than version 8
			  x.add(option,x.options[0]);
			}catch (e){
			  x.add(option,0);
			}
		}
		
		$("#venue").bind('blur',function(){
			document.getElementById("warningbox").style.visibility='collapse';
			document.getElementById("warningmsg").innerHTML="";
			var venue=$("#venue").val();	
			 $.ajax({
		     url: '/icam/existingVenueChecking.html',
		     dataType: 'json',
		     data: "venue=" +venue,
		     success: function(data) {
		    	if(data != null && data!= ""){
		    		document.getElementById("warningbox").style.visibility='visible';
		    		document.getElementById("warningmsg").innerHTML="Venue already exists";
		 			document.getElementById("venue").value='';
		    	  	return false;
		    	}else{	    		
		    		document.getElementById("warningbox").style.visibility='collapse';
		    	}
		    }  
			});			
		});
		
		
		
		
		
	});
	
	
	function validateCreateExamVenueForm()
	{
		document.getElementById("warningbox").style.visibility="collapse";
		document.getElementById("warningmsg").innerHTML="";

	ran=/^[A-Za-z ]{1,}$/;
	ran1=/^[A-Za-z0-9_.-\/]{1,50}$/;
	ran2=/^[A-Za-z0-9_.-]{1,50}$/;
	numeric=/^[0-9]{1,}$/;
	phoneno = /^\d{11}$/;  
	em1=/^([a-z0-9_.-])+@([a-z0-9_.-])+\.+([a-z0-9_-]{2,3})+\.+([a-z0-9_-]{2,3})$/;
	em2=/^([a-z0-9_.-])+@([a-z0-9_.-])+\.+([a-z0-9_-]{3})$/;
	pin = /^\d{6}$/; 
	
	//Validate Venue Name
	venue = document.getElementById("venue").value;
	venue = venue.replace(/\s{1,}/g,'');
	if(venue == ""){	
		document.getElementById("warningbox").style.visibility="visible";
		document.getElementById("warningmsg").innerHTML="Please Enter Venue";
		return false;
	}
	if(venue!=''){
		if(!venue.match(ran2)){
			document.getElementById("warningbox").style.visibility="visible";
			document.getElementById("warningmsg").innerHTML="Invalid Venue. Should use a-z,0-9,_,.,-";
			return false;
		}
	}
	
	//Validate Contact Number
	contactNo = document.getElementById("contactNo").value;
	contactNo = contactNo.replace(/\s{1,}/g,'');
	if(contactNo == ""){	
		document.getElementById("warningbox").style.visibility="visible";
		document.getElementById("warningmsg").innerHTML="Please Enter Contact No";
		return false;
	}
	if(contactNo!=''){
		if(!contactNo.match(numeric)){
			document.getElementById("warningbox").style.visibility="visible";
			document.getElementById("warningmsg").innerHTML="Invalid Contact No. Should be Numeric";
			return false;
		}
	}
	
	//Validate Capacity
	capacity = document.getElementById("capacity").value;
	capacity = capacity.replace(/\s{1,}/g,'');
	if(capacity == ""){	
		document.getElementById("warningbox").style.visibility="visible";
		document.getElementById("warningmsg").innerHTML="Please Enter Capacity";
		return false;
	}
	if(capacity!=''){
		if(!capacity.match(numeric)){
			document.getElementById("warningbox").style.visibility="visible";
			document.getElementById("warningmsg").innerHTML="Invalid Capacity. Should be Numeric";
			return false;
		}else{
			document.getElementById("capacity").value=capacity;
		}
	}
	 
	
	
	
	//Validate mobile Number
	mobileNo = document.getElementById("mobileNo").value;
	mobileNo = mobileNo.replace(/\s{1,}/g,'');
	if(mobileNo != ''){	
		if(!mobileNo.match(phoneno)){
			document.getElementById("warningbox").style.visibility="visible";
			document.getElementById("warningmsg").innerHTML="Invalid Mobile No. Should be 11 Digit Mobile Number with STD code";
			return false;
		}
	}
	
	//Validate Email Address
	email = document.getElementById("email").value;
	email = email.replace(/\s{1,}/g,'');
	if(email != ''){	
		if(!(em1.test(email) || em2.test(email))){
			document.getElementById("warningbox").style.visibility="visible";
			document.getElementById("warningmsg").innerHTML="Please Enter Valid E-mail Id";
			return false;
		}
	}
	
	//Validate Address 
	presentAddress1 = document.getElementById("presentAddress1").value;
	if(presentAddress1 == ""){	
		document.getElementById("warningbox").style.visibility="visible";
		document.getElementById("warningmsg").innerHTML="Please Enter Address";
		return false;
	}
	
	//Validate Present Address City-Village
	presentAddressCityVillage = document.getElementById("presentAddressCityVillage").value;
	if(presentAddressCityVillage == ""){	
		document.getElementById("warningbox").style.visibility="visible";
		document.getElementById("warningmsg").innerHTML="Please Enter City/Village";
		return false;
	}
	

	//Validate present PostOffice
	presentAddressPostOffice = document.getElementById("presentAddressPostOffice").value;
	if(presentAddressPostOffice == ""){	
		document.getElementById("warningbox").style.visibility="visible";
		document.getElementById("warningmsg").innerHTML="Please Enter PostOffice";
		return false;
	}

	//Validate Present PoliceStation
	presentAddressPoliceStation = document.getElementById("presentAddressPoliceStation").value;
	if(presentAddressPoliceStation == ""){	
		document.getElementById("warningbox").style.visibility="visible";
		document.getElementById("warningmsg").innerHTML="Please Enter Police Station";
		return false;
	}

	//Validate Present Pin
	var presentPin = document.getElementById("presentAddressPinCode").value;
	presentPin.replace(/^\s+|\s+$/g,'');
	if(presentPin == ""){	
		document.getElementById("warningbox").style.visibility="visible";
		document.getElementById("warningmsg").innerHTML="Please Enter Pin Code";
		return false;
	}
	if(presentPin!=""){
		if(! presentPin.match(pin)){
			document.getElementById("warningbox").style.visibility="visible";
			document.getElementById("warningmsg").innerHTML="Please Enter Valid Pin Code";
			return false;
		}	
	}
	
	//Validate District
	presentAddressDistrict = document.getElementById("presentAddressDistrict").value;
	presentAddressDistrict = presentAddressDistrict.replace(/\s{1,}/g,'');
	if(presentAddressDistrict == ""){	
		document.getElementById("warningbox").style.visibility="visible";
		document.getElementById("warningmsg").innerHTML="Please Enter District";
		return false;
	}

	//Validate Permanent Country
	presentAddressCountry = document.getElementById("presentAddressCountry").value;
	presentAddressCountry = presentAddressCountry.replace(/\s{1,}/g,'');
	if(presentAddressCountry == ""){	
		document.getElementById("warningbox").style.visibility="visible";
		document.getElementById("warningmsg").innerHTML="Please Enter Country";
		return false;
	}

	//Validate State
	presentAddressState = document.getElementById("presentAddressState").value;
	presentAddressState = presentAddressState.replace(/\s{1,}/g,'');
	if(presentAddressState == ""){	
		document.getElementById("warningbox").style.visibility="visible";
		document.getElementById("warningmsg").innerHTML="Please Enter State";
		return false;
	}

	
	
	
	}
