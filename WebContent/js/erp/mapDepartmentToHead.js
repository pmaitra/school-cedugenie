

function getUserDetails(rowId){
	
	
	$.ajax({
        url: '/cedugenie/getResourceUserId.html',
        dataType: 'json',
        data:"parent=" + $("#resourceTypeName"+rowId).val(),
        success: function(dataDB) {
        	var options="<option value=''>Select</option>";
        	if(dataDB != "null" && dataDB !=""){
        		var arr = dataDB.split("~");
        		
				for(var i=0;i<arr.length-1;i++){   
					var sub = arr[i].split("*");
					options=options+"<option value='"+sub[0]+"'>"+sub[1]+"</option>";
				}
			}
        	document.getElementById("resourceUserId"+rowId).innerHTML=options;
       }
	});
}


function validatingDetails(rowId)
{
	
	//alert(rowId);	
	document.getElementById("resourceTypeNames"+rowId).removeAttribute("disabled");	
	document.getElementById("departmentResource"+rowId).style.display='none';
	document.getElementById("departmentResourceType"+rowId).style.display='none';
	document.getElementById("resourceTypeNames"+rowId).style.display='block';	
	document.getElementById("resourceUserIds"+rowId).style.display='block';	
	document.getElementById("edit"+rowId).style.display='none';	
	document.getElementById("save"+rowId).style.display='block';	

}
function getUsersDetails(rowId){
	
	
	
	$.ajax({
        url: '/cedugenie/getResourceUserId.html',
        dataType: 'json',
        data:"parent=" + $("#resourceTypeNames"+rowId).val(),
        success: function(dataDB) {
        	
        	var options="<option value=''>Select</option>";
        	if(dataDB != "null" && dataDB !=""){
        		var arr = dataDB.split("~");
        		
				for(var i=0;i<arr.length-1;i++){   
					var sub = arr[i].split("*");
					options=options+"<option value='"+sub[0]+"'>"+sub[1]+"</option>";
				}
			}
        	document.getElementById("resourceUserIds"+rowId).innerHTML=options;
       }
	});
}

/*function setDepartmentResource(rowId)
{
	var a=document.getElementById("departmentResource"+rowId).value;
	var b=document.getElementById("resourceUserIds"+rowId).value;
	alert(a);
	alert(b);
	
	}*/

function getDepartmentResource(rowId)
{
	//alert("abr hbe");
	var departmentsCode=document.getElementById("departmentCode"+rowId).value;
	var resourcesId=document.getElementById("resourceUserIds"+rowId).value;
	//alert(departmentsCode);
	//alert(resourcesId);
	window.location='saveUpdateDetails.html?departmentCode='+departmentsCode+'&resourceId='+resourcesId+'';
	}