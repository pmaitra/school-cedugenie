$(document).ready(function(){	

	$("#commodityCode").change(function(){
		var commodityCode=$(this).val();
		if(commodityCode != ""){
			$.ajax({
			    url: '/icam/getIndividualNotAllotedCommodity.html',		   
			    dataType: 'json',
			    data: "commodityCode=" + ($(this).val()),
			    success: function(data) {
			    	data=data.split("@@@");
			    	var commodity=data[0];
			    	var resource=data[1];
			    	
			    	if(commodity==""){
			    		alert("Individual Commodity Not Found.");
			    		document.getElementById("commodityDetailsTable").style.display="none";
			    		table.innerHTML="";
						return false;
			    	}
			    	if(resource==""){
			    		alert("Resource Not Found To Allocate.");
			    		document.getElementById("commodityDetailsTable").style.display="none";
			    		table.innerHTML="";
						return false;
			    	}
			    	if(commodity!="" && resource!=""){
			    		document.getElementById("commodityDetailsTable").style.display="block";
			    		var p=1;
			    		var table=document.getElementById("tableBody");
			    		table.innerHTML="";
			    		commodity=commodity.split("~*~");
			    		
			    		
			    		for(var i=0;i<commodity.length-1;i++)
			    		{
			    			var commodityDetails=commodity[i].split("*-*");
			    			
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
			    	        if(commodityDetails[1] == "null"){
			    	        	element = document.createTextNode("N/A");
			    	        }
			    	        else{
			    	        	element = document.createTextNode(commodityDetails[1]);
			    	        }
			    	        cell.appendChild(element);
			    	        
			    	        cell = row.insertCell(3);
			    	        element = document.createTextNode(commodityDetails[2]);
			    	        cell.appendChild(element);
			    	        
			    	        cell = row.insertCell(4);
			    	        element = document.createElement("select");
			    	        element.name=commodityDetails[0]+"allotTo";
			    	        element.id="allotTo"+p;
			    	        element.className="form-control";
			    	        var op = new Option();
		    	        	op.value = "";
		    	        	op.text =  "Select...";
		    	        	element.options.add(op);
		    	        	var R=resource.split("~*~");
			    	        for(var j=0;j<R.length-1;j++)
			    	        {
			    	        	var res=R[j].split("*-*");
			    	        	op = new Option();
			    	        	op.value = res[0];
			    	        	op.text =  res[1];
			    	        	element.options.add(op);  
			    	        }
			    	        cell.appendChild(element);
			    	        
			    	        p++;
			    		}	
			    		document.getElementById("commodityDetailsTable").style.visibility = "visible";
		    		}
		    					    	
			    }  
			});
		}else{
			table.innerHTML="";
			document.getElementById("commodityDetailsTable").style.display="none";
		}
	});

});



function validate(){
	
	if(document.getElementById('allotedBy').value==""){
		alert("Alloted By Is Not Provided");
		return false;		
	}
	if(document.getElementById('commodityCode').value==""){
		alert("Please Select Commodity");
		return false;		
	}
	
	var count=0;
	var checkboxes = document.getElementsByName("commodityCode");
	for (var i=0; i<checkboxes.length; i++) {
		if (checkboxes[i].checked) {
			count++;
			var chkbxid=checkboxes[i].id;
			var chkbxval=document.getElementById(chkbxid).value;
			
			var selid=chkbxid.replace("commodityCode", "allotTo");
			var selval=document.getElementById(selid).value;
			if(selval==""){
				alert("Please Select Allot to Field For Commodity Code "+chkbxval);
				return false;
			}
		}
	}
	if(count<=0){
		alert("Please Select Atleast One Commodity To Allot");
		return false;
	}
}