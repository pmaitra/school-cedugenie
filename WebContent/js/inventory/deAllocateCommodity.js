$(document).ready(function(){	
	
	$("#commodityCode").change(function(){
		var commodityCode=$(this).val();
		if(commodityCode != ""){
			$.ajax({
			    url: '/cedugenie/getIndividualAllotedCommodity.html',		   
			    dataType: 'json',
			    data: "commodityCode=" + ($(this).val()),
			    success: function(data) {
			    	var table = document.getElementById('tableBody');
			    	table.innerHTML="";
			    	if(data==""){
			    		alert("No Commodities Alloted Yet");
			    		document.getElementById("commodityDetailsTable").style.display="none";
						return false;
			    	}else{
			    		
			    		data=data.split("~*~");
			    		
			    		var p=0;
			    		for(var i=0;i<data.length-1;i++)
			    		{
			    			var commodityDetails=data[i].split("*-*");
			    			
			    			var rowCount = table.rows.length;
			    	        var row = table.insertRow(rowCount);
			    	        var cell,element;
			    	        
			    	        cell = row.insertCell(0);
			    	        element = document.createElement("input");
			    	        element.type = "checkbox";
			    	        element.name="commodityCode";
			    	        element.id="commodityCode"+p;
			    	        element.value=commodityDetails[0];
			    	        cell.appendChild(element);
			    	        
			    	        cell = row.insertCell(1);
			    	        element = document.createTextNode(commodityDetails[0]);
			    	        cell.appendChild(element);
			    	        
			    	        cell = row.insertCell(2);
			    	        element = document.createTextNode(commodityDetails[1]);
			    	        cell.appendChild(element);
			    	        
			    	        cell = row.insertCell(3);
			    	        element = document.createTextNode(commodityDetails[2]);
			    	        cell.appendChild(element);
			    	        
			    	        cell = row.insertCell(4);
			    	        element = document.createTextNode(commodityDetails[3]);
			    	        cell.appendChild(element);
			    	        
			    	        cell = row.insertCell(5);
			    	        element = document.createTextNode(commodityDetails[4]);
			    	        cell.appendChild(element);
			    	        
			    	        cell = row.insertCell(6);
			    	        element = document.createTextNode(commodityDetails[5]);
			    	        cell.appendChild(element);
			    	        
			    	        cell = row.insertCell(7);
			    	        element = document.createElement("textarea");
			    	        element.name=commodityDetails[0]+"comment";
			    	        element.className="txtarea";
			    	        cell.appendChild(element);
			    	        
			    	        p++;
			    		}	
			    		document.getElementById("commodityDetailsTable").style.display="block";
		    		}
		    					    	
			    }  
			});
		}
	});

});



function validate(){
	
	if(document.getElementById('returnedTo').value==""){
		alert("Returned To By Is Not Provided");
		return false;		
	}
	if(document.getElementById('commodityCode').value==""){
		alert("Please Select Commodity");
		return false;		
	}
	
	var count=0;
	var checkboxes = document.getElementsByName("commodityCode");
	for (var i=0; i<checkboxes.length; i++){
		if (checkboxes[i].checked)
			count++;
	}
	if(count<=0){
		alert("Please Select Atleast One Commodity To De-Allot");
		return false;
	}
}