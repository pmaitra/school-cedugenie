
 function onUpdate()
{
	
	if(document.getElementById('commodityCode').value==""){
		alert("Select Commodity");
		return false;		
	}
	
	var count=0;
	var checkboxes = document.getElementsByName("commodityCode");
	for (var i=0; i<checkboxes.length; i++) {
		if (checkboxes[i].checked) {
			count++;
			var chkbxid=checkboxes[i].id;
			var chkbxval=document.getElementById(chkbxid).value;
			 
			
			var tstid1=chkbxid.replace("commodityCode", "model");
			var txtval1=document.getElementById(tstid1).value;
			if(txtval1==""){
				alert("Enter Model No. For "+chkbxval);
				return false;
			}
			
			
			var selid=chkbxid.replace("commodityCode", "warranty");
			var txtval=document.getElementById(selid).value;
			if(txtval==""){
				alert("Enter Warranty For "+chkbxval);
				return false;
			}else{
				if(isNaN(txtval))
				{
					alert("Please Enter Numeric Warranty For "+chkbxval);
					return false;		
				}
				txtval=parseFloat(txtval);
				if(txtval<=0.0){
					alert("Please Enter Positive Warranty For "+chkbxval);
					return false;	
				}
			}
			
			var selid=chkbxid.replace("commodityCode", "depreciation");
			var txtval=document.getElementById(selid).value;
			if(txtval==""){
				alert("Enter Depreciation For "+chkbxval);
				return false;
			}else{
				if(isNaN(txtval))
				{
					alert("Please Enter Numeric Depreciation For "+chkbxval);
					return false;		
				}
				txtval=parseFloat(txtval);
				if(txtval<=0.0){
					alert("Please Enter Positive Depreciation For "+chkbxval);
					return false;	
				}
			}
			
			
		}
	}
	if(count<=0){
		alert("Please Select Atleast One Commodity To Update");
		return false;
	}	
	
	document.individualAssetDetails.action="updateIndividualCommodityDetails.html";
	document.individualAssetDetails.submit();
	return true;
} 	

function onRetire()
{	
	if(document.getElementById('commodityCode').value==""){
		alert("Select Commodity");
		return false;		
	}
	
	var count=0;
	var checkboxes = document.getElementsByName("commodityCode");
	for (var i=0; i<checkboxes.length; i++) {
		if (checkboxes[i].checked)
			count++;
	}
	if(count<=0){
		alert("Please Select Atleast One Commodity To Retire");
		return false;
	}	
	
	document.individualAssetDetails.action="retireCommodity.html";
  	document.individualAssetDetails.submit();
  	return true;
}
function onEdit()
{
	var text = document.getElementsByTagName('input');
	for(var i=0; i< text.length; i++){
		if(text[i].type=="text"){
			if(text[i].readOnly){
				text[i].removeAttribute("readonly");
			}
		}
	}
}
$(document).ready(function(){
	
	$("#commodityCode").change(function(){
		document.getElementById("commodityDetailsTable").style.display="none";
		var table=document.getElementById("tableBody");
		table.innerHTML="";
		var commodityCode=$(this).val();
		if(commodityCode != ""){
			$.ajax({
			    url: '/cedugenie/getIndividualCommodity.html',		   
			    dataType: 'json',
			    data: "commodityCode=" + ($(this).val()),
			    success: function(data) {
			    	if(data==""){
			    		alert("Individual Commodity Not Found.");
						return false;
			    	}else{
			    		var p=1;			    		
			    		commodity=data.split("~*~");
			    		
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
			    	        element = document.createTextNode(commodityDetails[1]);
			    	        cell.appendChild(element);
			    	        
			    	        
			    	        
			    	        cell = row.insertCell(3);
			    	        element = document.createElement("input");
			    	        element.type = "text";
			    	        element.name=commodityDetails[0]+"model";
			    	        element.id="model"+p;
			    	        element.className="form-control";
			    	        if(commodityDetails[2]=='null' || commodityDetails[2]==null)
			    	        	commodityDetails[2]="";
			    	        else			    	        	
			    	        	element.value=commodityDetails[2];
			    	        element.setAttribute("readonly","readonly");
			    	        cell.appendChild(element);
			    	        
			    	        cell = row.insertCell(4);
			    	        element = document.createElement("input");
			    	        element.type = "text";
			    	        element.name=commodityDetails[0]+"warranty";
			    	        element.id="warranty"+p;
			    	        element.className="form-control";
			    	        if(commodityDetails[3]=='null' || commodityDetails[3]==null)
			    	        	commodityDetails[3]="";
			    	        else
			    	        	element.setAttribute("readonly","readonly");
			    	        element.value=commodityDetails[3];
			    	        cell.appendChild(element);
			    	        
			    	        cell = row.insertCell(5);
			    	        element = document.createElement("input");
			    	        element.type = "text";
			    	        element.name=commodityDetails[0]+"depreciation";
			    	        element.id="depreciation"+p;
			    	        element.className="form-control";
			    	        if(commodityDetails[4]=='null' || commodityDetails[4]==null)
			    	        	commodityDetails[4]="";
			    	        else
			    	        	element.setAttribute("readonly","readonly");
			    	        element.value=commodityDetails[4];
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