	$(document).ready(function() { 	
		$("#klass").change(
			function() {
				/*document.getElementById("infomsgbox").style.visibility="collapse";*/
				$.ajax({
			        url: '/icam/getDriveList.html',
			        dataType: 'json',
			        data: "klass=" + ($(this).val())+"&year=" + ($("#year").val()),
			        success: function(dataDB) {	
			        	var x=document.getElementById("driveName");
			        	removeOption(x);
			        	document.getElementById("noOfOpenings").value="";
		        		document.getElementById("formFees").value="";
		        		document.getElementById("noOfFormSold").value="";
			        	if(dataDB != "null" && dataDB !=""){
			        		var driveArr = dataDB.split("*");
							for (var i=0;i<driveArr.length;i++){        	
			                    $("#driveName").append($("<option></option>").val(driveArr[i]).html(driveArr[i]));
							}
							x=document.getElementById("driveName");
							x.remove(i);
						}/*else{
			        		document.getElementById("infomsgbox").style.visibility="visible";
							document.getElementById("infomsg").innerHTML="Drive Name Not Found.";
			        	}*/
			       }
				});
			});	 
		
		$("#driveName").change(
				
				function() {
					/*document.getElementById("infomsgbox").style.visibility="collapse";*/
					$.ajax({
				        url: '/icam/getFormDetails.html',
				        dataType: 'json',
				        data: "driveName=" + ($(this).val())+"&year=" + ($("#year").val())+"&klass=" + ($("#klass").val()),
				        success: function(dataDB) {	
				        	
				        	document.getElementById("noOfOpenings").value="";
			        		document.getElementById("formFees").value="";
			        		document.getElementById("noOfFormSold").value="";
			        		document.getElementById("noOfFormSold").removeAttribute('readonly');
				        	if(dataDB != null && dataDB !=""){
				        		var formDetailsArr = dataDB.split("*");
				        		document.getElementById("noOfOpenings").value=formDetailsArr[0];
				        		document.getElementById("formFees").value=formDetailsArr[1];
				        		document.getElementById("noOfFormSold").value=formDetailsArr[2];
				        		if(formDetailsArr[2]!=""){
				        			document.getElementById("noOfFormSold").setAttribute('readonly','readonly');
				        		}
							}/*else{
								document.getElementById("infomsgbox").style.visibility="visible";
								document.getElementById("infomsg").innerHTML="Form details not found.";
				        	}*/
				       }
					});
				});	      
		});
	function removeOption(x){
		for(var i=x.length;i>0;i--){
			x.remove(i);
		}
	}
	
	function validate(){
		cr =/^[0-9]*$/;
		
		/*var klass = document.getElementById("klass").value;
		var driveName = document.getElementById("driveName").value;
		if(klass == ""){
			document.getElementById("warningbox").style.visibility="visible";
			document.getElementById("warningmsg").innerHTML="Please select class.";
			return false;
		}
		if(driveName == ""){
			document.getElementById("warningbox").style.visibility="visible";
			document.getElementById("warningmsg").innerHTML="Please select drive name.";			
			return false;
		}*/
		
		var noOfOpenings=document.getElementById("noOfOpenings").value;
		if(noOfOpenings!=null){
			noOfOpenings = noOfOpenings.replace(/\s{2,}/g,' ');
			var noOfFormSold=document.getElementById("noOfFormSold").value;
			if(noOfFormSold!=null && noOfFormSold.match(cr)){
				noOfFormSold = noOfFormSold.replace(/\s{2,}/g,' ');
				var intNoOfFormSold = parseInt(noOfFormSold);
				var intNoOfOpenings = parseInt(noOfOpenings);
				
				if(intNoOfOpenings < intNoOfFormSold){
					//document.getElementById("warningbox").style.visibility="visible";
					//document.getElementById("warningbox").innerHTML="No of Form Sold is more then Total No Of Form .";	
					alert("No of Form Sold is more then Total No Of Form .");
					return false;
				}
			}/*else{
				//document.getElementById("warningbox").style.visibility="visible";
				//document.getElementById("warningbox").innerHTML="Plese enter numeric and positive value .";			
				return false;
			}*/
		}
	}
	
	
	function getLoggedData(){
		 $.ajax({
			    url: '/sms/getSaleFormLogDetails.html',
			    dataType: 'json',
			    success: function(data) {
			    	if(data!=''){
			    		createTable(data);
				    }
			    }
		 });
	}
	
	
	