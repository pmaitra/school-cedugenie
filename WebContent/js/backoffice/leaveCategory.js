var rownum = 0;
function deleteRows()
{	
	 var chkbox1=document.getElementsByName("leaveCode");
	 var table=document.getElementById("previousCategoryDetail");
	 for(var checkboxnum=chkbox1.length-1;checkboxnum>-1;checkboxnum--)
	{
		if(chkbox1[checkboxnum].checked == true)
		{
			table.deleteRow(checkboxnum+1);
			rownum++;
		}
	}
}    

		

function deleteAction(){
	 document.leaveCategoryForm.method="post";
	 document.leaveCategoryForm.action="deleteLeave.html";
	 document.leaveCategoryForm.submit();             // back from the page
	 return true;
}

function editAction(){
	 document.leaveCategoryForm.method="post";
	 document.leaveCategoryForm.action="editLeave.html";
	 document.leaveCategoryForm.submit();             // back from the page
	 return true;
}
var chk=0;
function showenable(){
	var textboxname = document.getElementsByName('leaveType1');
	var checkbox = document.getElementsByName('leaveCode');
	  for(var i=0;i<textboxname.length;i++)
	 		{
		  if(checkbox[i].type=="checkbox" && checkbox[i].checked){
			  chk=1;	
			  textboxname[i].removeAttribute("readonly");	
			  document.getElementById('submitPre').removeAttribute("disabled");
			  document.getElementById('deletePre').removeAttribute("disabled");
			  document.getElementById("warningbox").style.visibility = "collapse";
			  document.getElementById("warningmsg").innerHTML= "";
		  }
		  
	 	  }
	  if (chk==0){
		  	document.getElementById("warningbox").style.visibility = "visible";
		  	document.getElementById("warningmsg").innerHTML= "Must check some option!";
	  }
}
function validText(text_id){
	var leaveTypeName=document.getElementById(text_id).value;		
	$.ajax({
	    url: '/icam/getLeaveCategoryTypeNameForValidation.html',
	    dataType: 'json',
	    data: "leaveTypeName=" +leaveTypeName,		   
	    success: function(data) {		
	    	if(data == "YES"){ 	
	    		$("#warningbox").css('visibility', 'visible');
	    		$('#warningmsg').text("Leave Type Name already exists.");	 
        	  	$('#submit').attr("disabled",true);
        	  	return false;
        	}	
	    	else{		    		
	    		$("#warningbox").css('visibility', 'collapse');
	    		$('#warningmsg').text("");
	    		$('#submit').attr("disabled",false);   			    		
	    	}
	    }  
	});	
}
function validTextAnother(text_id){
	var leaveTypeName=document.getElementById(text_id).value;	
	$.ajax({
	    url: '/icam/getLeaveCategoryTypeNameForValidation.html',
	    dataType: 'json',
	    data: "leaveTypeName=" +leaveTypeName,		   
	    success: function(data) {	
	    	if(data == "YES"){ 	
			  	$("#warningbox").css('visibility', 'visible');
				$('#warningmsg').text("Leave Type Name already exists.");	 
        	  	$('#submitPre').attr("disabled",true);
				if(leaveTypeName.toUpperCase() == text_id){
					$("#warningbox").css('visibility', 'collapse');
					$('#warningmsg').text("");	 
		    		$('#submitPre').attr("disabled",false);   
		    	} 
        	  	return false;
        		}	
	    	else{		    		
	    		$("#warningbox").css('visibility', 'collapse');
				$('#warningmsg').text("");	 
	    		$('#submitPre').attr("disabled",false);   			    		
	    	}
	    }  
	});	
}


$(document).ready(function() {

	/*Get Previous leave type*/
		$.ajax({
	    url: '/icam/getPreviousLeaveType.html',
	    	dataType: 'json',
	    	success: function(data) {
	    	if(data != null){
	    		$('#submitPre').css('visibility','visible');
	    		$('#deletePre').css('visibility','visible');
	    		$('#editPre').css('visibility','visible');
	    		$("#infomsgbox").css('visibility', 'collapse');
				$('#infomsg').text("");	
				 var row = "";
			 	 var arr = data.split("/");	
			 	 var len = arr.length;
				 for(var clist=0;clist<arr.length;clist++){								    				
	   				var arr1 = arr[clist].split(",");
	   				for(var cfees=0;cfees<arr1.length;cfees++){
		   				if(arr1[cfees] != "" || arr1[cfees] != ""){
							$('#previousCategoryDetail thead').show();
		                 	row = $('<tr>');
		                 	//row.append($('<td><input type="checkbox" id="leaveCode" name="leaveCode" value="'+arr1[0]+'"/></td>'));
		                 	row.append($('<td><input class="form-control" type="text" id="'+arr1[1]+'" name="leaveType1" readonly="readonly" onkeyup="validTextAnother(this.id);" value="'+arr1[1]+'" /></td>'));
		                 	row.append($('<td><a class="on-default edit-row" href="#"><i class="fa fa-pencil"></i></a><a class="hidden on-editing save-row" href="#"><i class="fa fa-save"></i></a></td>'));
		   				}
	   				}
			    $('#previousCategoryDetail').append(row);
				    }
		    }
	    	
	    	if(data == null){
	    		$('#previousCategoryDetail thead').hide();
	    		$('#submitPre').css('visibility','collapse');
	    		$('#deletePre').css('visibility','collapse');
	    		$('#editPre').css('visibility','collapse');
	    		$("#infomsgbox").css('visibility', 'visible');
				$('#infomsg').text("Leave Type is not assigned yet.");	
	    	}
		 } 
		}); 
			
		/*submit action*/
			var codeandtype = "";
			var code="";
			var leavename="";
			$('#submitPre').click(function(){
				$('input:checkbox').each(function()
						{	
							if ($(this).is(':checked'))
							{					
				    			code = $(this).val();
				    			leavename = $(this).parent().next().find('input:text').val();
							}
							codeandtype = code + "," + leavename + "/" + codeandtype;
			    		});
				$('#hiddenleaveType').val(codeandtype);
    			editAction();
						});
			/*chacking same name*/
			
	 		
			$("#leaveType").bind('keyup blur',function(){
				var leaveTypeName=$("#leaveType").val();			
				$.ajax({
				    url: '/icam/getLeaveCategoryTypeNameForValidation.html',
				    dataType: 'json',
				    data: "leaveTypeName=" +leaveTypeName,		   
				    success: function(data) {		    
				    	if(data == "YES"){ 	
				    		$("#warningbox").css('visibility', 'visible');
				    		$('#warningmsg').text("Leave Type Name already exists.");
				    		$('#submit').attr("disabled",true);
			        	  	return false;
			        		}	
				    	else{		    		
//				    		$$("#warningbox").css('visibility', 'collapse');
//				    		$('#warningmsg').text("");
				    		$('#submit').attr("disabled",false);   			    		
				    		}
				    	}  
					});
				}); 
	 	 
});