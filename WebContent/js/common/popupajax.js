var status="";
var div_id="";
	 $(document).ready(function() {
		  $('#dialog_window_1').hide();
			 function expireWarning() { /*ajax call to retrieve the data from database thrugh controller using spring*/
				 document.getElementById("dialog_form").innerHTML="";
				 //var p='';
				 var msg='';
				 $.ajax({
		    	url:'/icam/getNotifyForItemThresold.html',/*the function name of controller by which the data is retrieved*/
		    	dataType: 'json',
		    	success: function(data) {
		    		
		        	if(data!=null && data.length!=0){
		        		status="loaded";
		        		
		    			msg=msg+'<tr><th style="border-bottom: double 1px; font-size: 14px; color: red; "><b><i><center><u>:: The following Items are below their threshold values  ::</u></center></i></b></th></tr>'+
		    			'<tr><td><div style="font-weight: bold;font-family:bahamas, sans-serif;">'+
						'<span style="background-color:black;font-size: 14px;" >';
		    			
		        		//var datastr = data.substring(1,data.length -1);
		        		var arr = data.split("|");
		        		
		        		msg =msg+ "<h3>";
		        		for (var i=0;i<arr.length-1;i++){
		        			var j = i + 1;
		        			msg = msg + j + " : " + arr[i]+"<br><br>";
		        		}
		        		msg = msg + "</h3></span></div></td></tr>";
		        	  //$('#new_window_content').append(msg);
		        		
		        	}
		        	  //--------------------UPDATING FROM--------------------------
		        	var insuranceTaxData="";
		        	  $.ajax({
		        	  url:'/icam/getNotifyForInsuranceAndTaxDate.html',/*the function name of controller by which the data is retrieved*/
				    	dataType: 'json',
				    	success: function(data) {
				    		
				    		if(data!=null && data.length!=0){
				    			
				    			//document.getElementById("dialog_form").innerHTML="";
				    			insuranceTaxData=insuranceTaxData+'<tr><th style="border-bottom: double 1px; font-size: 14px; color: red; "><b><i><center><u>:: The following Car Details under insurence expiry date or tax payable date  ::</u></center></i></b></th></tr>'+
				    			'<tr><td><div style="font-weight: bold;font-family:bahamas, sans-serif;">'+
								'<span style="background-color:black;font-size: 14px;" >';
				    			 
								
				    			//document.getElementById("dialog_form").innerHTML=p;
				        		
				    			var insuranceAndTaxPayableCarDetails=data.split("#");
				    			if(insuranceAndTaxPayableCarDetails[0]!="none"){
				    				status="loaded";
				    				var carInsuranceDetails = insuranceAndTaxPayableCarDetails[0].split("|");
				    				insuranceTaxData =insuranceTaxData.concat("<h3>");
					        		for (var i=0;i<carInsuranceDetails.length-1;i++){
					        			var j = i + 1;
					        			var carDetails = carInsuranceDetails[i].split("-");
					        			insuranceTaxData = insuranceTaxData + j + ": Car Details : " + carDetails[0]+" - "+carDetails[1]+"</br> Insurence Company: "+carDetails[2]+" </br> Expiry Date: "+carDetails[3]+"<br><br>";
					        		}
					        		insuranceTaxData = insuranceTaxData + "</h3>";
					        		//$('#new_window_content').append(msg);
				    			}
				    			if(insuranceAndTaxPayableCarDetails[1]!="none"){
				    				status="loaded";
				    				var carTaxPayableDetails = insuranceAndTaxPayableCarDetails[1].split("|");
				    				insuranceTaxData =insuranceTaxData+ "<h3>";
					        		for (var i=0;i<carTaxPayableDetails.length-1;i++){
					        			var j = i + 1;
					        			var carDetails = carTaxPayableDetails[i].split("-");
					        			insuranceTaxData = insuranceTaxData + j + ": Car Details : " + carDetails[0]+" - "+carDetails[1]+"</br> Last Date Of Tax: "+carDetails[3]+"<br><br>";
					        		}
					        		insuranceTaxData = insuranceTaxData + "</h3>";
					        		//$('#new_window_content').append(msg);
				    			}
				    			insuranceTaxData=insuranceTaxData+'</span></div></td></tr>';
				    		}document.getElementById("dialog_form").innerHTML=msg+insuranceTaxData;
				    	}
		        	  });
		        	  //alert(insuranceTaxData);
		        	  
		        	  //document.getElementById("new_window_content").innerHTML=msg;
		        	  //---------------------UPDATING TO---------------------------
		        	  //alert(status);
		        	  if(status=="loaded"){
	        		  var d =	 $('#dialog_window_1').dialog({             /*dialog box*/
	  			  		width: 'auto',
						height: 'auto',
						autoOpen : false,
						position:['right bottom']
						});
	        		  $('#dialog_window_1').dialog("open");
	        		  //d.dialog("open");
	  		 
	        		  var div_count = $('.dialog_window').length + 1;
	        		  div_id = 'dialog_window_'+div_count;
						/*call the function to open the dialog box when the data is coming from database*/		
										                               
										
						/*activities will be done when the  pop up box will be minimized state*/						
										
						var _init = $.ui.dialog.prototype._init;
						$.ui.dialog.prototype._init = function() {
						_init.apply(this, arguments);
						
						var dialog_element = this;
						var dialog_id = this.uiDialogTitlebar.next().attr('id');
						
						this.uiDialogTitlebar.append('<a href="#" id="' + dialog_id + 
						'-minbutton" class="ui-dialog-titlebar-minimize ui-corner-all">'+
						'<span class="ui-icon ui-icon-minusthick" onclick="minFunction();"></span></a>');
						
						$('#dialog_window_minimized_container').append(
						'<div class="dialog_window_minimized ui-widget ui-state-default ui-corner-all" id="' + 
						dialog_id + '_minimized">' + this.uiDialogTitlebar.find('.ui-dialog-title').text() + 
						'<span class="ui-icon ui-icon-newwin"></div>');
						
						if($('#' + dialog_id + '-minbutton').hover(function() {
						$(this).addClass('ui-state-hover');
						}, function() {
						$(this).removeClass('ui-state-hover');
						}).click()) {
						dialog_element.close();      /*if pop up box is minimized then next time it will not be visible*/
						
						$('#dialog_window_minimized_container').css("text-decoration","blink"); /*when the data will come again the minimized pop up box's title bar will be blinked*/
						if(null!=document.getElementById(div_id))						
							document.getElementById(div_id).style.display="none";
						$('#' + dialog_id + '_minimized').show(); 
						}else{
						d.dialog("open"); /* call the dialog function to open the pop up box*/
						$('#' + dialog_id + '_minimized').click(function() {
						
						$(this).hide();
						dialog_element.open();

						});
						}
						};

					var create_dialog = $('#dialog_window_1');
					var create_button = $(this); 	 
					 if( create_dialog.dialog('isOpen') ) {
						 	create_button.button('option', 'label', 'Create a new Window');
							create_dialog.dialog('close');
						} else {
							create_button.button('option', 'label', 'Close Window');
							create_dialog.dialog('open');
						} 
					 
					 
					 }        	
		        	
		        }
				 
				// --------------
				 
		        });
				 
				 
	        	

			      setTimeout(expireWarning,100000); /*calling the ajax function after 5 sec time interval */

		 }
		 setTimeout(expireWarning,100000);
		

				
			
 	}); 