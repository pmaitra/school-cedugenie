$(document).ready(function(){	
	$("#course").change(function(){
			
			if($(this).val()!=''){
				
				$.ajax({
			        url: '/cedugenie/getSectionAgainstCourse.html',
			        dataType: 'json',
			        data: {
			        		"course":$(this).val()
			        },
			        success: function(data) {
			        	if(data != ""){
			        		$('#section')
			        		    .find('option')
			        		    .remove()
			        		    .end()
			        		    .append('<option value="">Please Select</option>')
			        		    .val('');
			        		var arr = data.split(";");
			        		for (var i=0;i<arr.length;i++){
		        		  		if(arr[i]!=""){
		        		  			var arr2 = arr[i].split("*");
		        		  			$("#section").append($("<option></option>").val(arr2[0]).html(arr2[1]));
		        		  		}			        				
			        		}
			        	}else{				        					        		
			        		$('#section')
		        		    .find('option')
		        		    .remove()
		        		    .end()
		        		    .append('<option value="">Please Select</option>')
		        		    .val('');				        		
		        			alert("No Section Found For Selected Class");
				    	}
			        }
				});
							
			}
			
		});
	
	
	$("#teacherId").change(function(){
		
		if($(this).val()!=''){
			
			$.ajax({
		        url: '/cedugenie/getSubjectAgainstTeacher.html',
		        dataType: 'json',
		        data: {
		        		"teacher":$(this).val()
		        },
		        success: function(data) {
		        	if(data != ""){
		        		$('#subject')
		        		    .find('option')
		        		    .remove()
		        		    .end()
		        		    .append('<option value="">Please Select</option>')
		        		    .val('');
		        		var arr = data.split("*~*");
		        		for (var i=0;i<arr.length;i++){
	        		  		if(arr[i]!=""){
	        		  			var arr2 = arr[i].split("#@#");
	        		  			$("#subject").append($("<option></option>").val(arr2[0]).html(arr2[1]));
	        		  		}			        				
		        		}
		        	}else{				        					        		
		        		$('#subject')
	        		    .find('option')
	        		    .remove()
	        		    .end()
	        		    .append('<option value="">Please Select</option>')
	        		    .val('');				        		
	        			alert("No Subject Found For Selected Teacher");
			    	}
		        }
			});
						
		}
		
	});
});



function makeEditable(rowId){
	//alert(rowId);
	rowId=rowId.replace("edit","");
	document.getElementById("noOfClass"+rowId).removeAttribute("readonly");
	
};
function saveData(rowId){
	//alert(rowId);
	rowId = rowId.replace("save","");
	document.getElementById("saveId").value = rowId;
	document.getElementById("statusValue").value = "edit";
	document.editTeacherClassMapping.submit();
};
function deleteData(rowId){
	//alert(rowId);
	rowId = rowId.replace("delete","");
	document.getElementById("saveId").value = rowId;
	document.getElementById("statusValue").value = "delete";
	document.editTeacherClassMapping.submit();
}

