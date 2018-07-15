	var p = 2;
	var i = 1;
	function addrows(){	
        var table = document.getElementById("pTaxSalarySlab"); 
        var rowCount = table.rows.length;        
        var row = table.insertRow(rowCount-1);
    	
        var cell = row.insertCell(0);		
        var element = document.createElement("input");
        element.type = "text";		        	        	
        element.value = "0.00";
        element.setAttribute("style","text-align: right;");
        element.setAttribute("onfocus","this.value='';");
        element.setAttribute("onblur","setZero(this);");
        element.className = "form-control";
        element.name = "startSlabIndex";
        element.id = "startSlabIndex"+i;
        cell.appendChild(element);
        
        var cell = row.insertCell(1);		
        var element = document.createElement("input");
        element.type = "text";		        	        	
        element.value = "0.00";
        element.setAttribute("style","text-align: right;");
        element.setAttribute("onfocus","this.value='';");
        element.setAttribute("onblur","setZero(this);");	       
        element.className = "form-control";
        element.name = "endSlabIndex";
        element.id = "endSlabIndex"+i;
        cell.appendChild(element);
        
        var cell = row.insertCell(2);		
        var element = document.createElement("input");
        element.type = "text";		        	        	
        element.value = "0.00";
        element.setAttribute("style","text-align: right;");
        element.setAttribute("onfocus","this.value='';");
        element.setAttribute("onblur","setZero(this);");       
        element.className = "form-control";
        element.name = "taxAmount";
        element.id = "taxAmount"+i;
        cell.appendChild(element);
        
        i++;     
	};
	
	
	function setZero(tBox){
		document.getElementById('warningbox').style.visibility = "collapse";
		if(tBox.value==""){
			tBox.value="0.00";
		}		
		if(isNaN(tBox.value)){
//			document.getElementById('warningbox').style.visibility="visible";
//			document.getElementById('warningmsg').innerHTML="Please Enter Proper Numeric Value";
			alert("Please Enter Proper Numeric Value");
			tBox.value="0.00";
		}else{
			var p=parseFloat(tBox.value);
			if(p<0.0){
//				document.getElementById('warningbox').style.visibility="visible";
//				document.getElementById('warningmsg').innerHTML="Please Enter a positive Number";
				alert("Please Enter a positive Number");
				tBox.value="0.00";
			}
		}		
	}
	
	
$(document).ready(function() {
	$("#miscTaxTypeName").change(function(){
		if(($(this).val()) != ''){
			if(($("#miscTaxTypeName option:selected").text() == 'INCOME TAX')){
				//window.location = "editIncomeTaxSalarySlab.html";
				var calculationFor = $("#miscTaxTypeName").val();
				window.location='editIncomeTaxSalarySlab.html?calculationFor='+calculationFor+'';
			}
			$("#taxBasedOn").val("Select...");
			$("#figureType").val("Select...");
		}  	
	});	
	
	$("#taxBasedOn").change(function(){
		//document.getElementById("warningbox").style.visibility = 'collapse';		
	});	
	
	$("#figureType").change(function(){
		//document.getElementById("infomsgbox").style.visibility = 'collapse';				
		document.getElementById("salarySlab").style.visibility = 'collapse';
		if(($("#miscTaxTypeName").val()) == ''){
//			document.getElementById('warningbox').style.visibility = "visible";
//			document.getElementById('warningmsg').innerHTML = "Please Select a Tax Type";
			alert("Please Select a Tax Type");
			return false;
		}
		if(($("#taxBasedOn").val()) == ''){
//			document.getElementById('warningbox').style.visibility = "visible";
//			document.getElementById('warningmsg').innerHTML = "Please Select a Tax Type Based On";
			alert("Please Select a Tax Type Based On");
			return false;
		}
		if(($(this).val()) != ''){			
			$.ajax({
		        url: '/cedugenie/getSubmittedSlabTypeForMiscTax.html',
		        dataType: 'json',
		        data: "miscTaxTypeName=" + ($("#miscTaxTypeName").val()),
		        success: function(dataDB) {
		        	if(dataDB != null)
					{
		        		if(dataDB == 0){
		        			var vals = ($("#figureType").val());
		        			var cell = document.getElementById("pTaxSalarySlab").rows[0].cells;
		        			cell[2].innerHTML = "TAX "+vals;
			        		document.getElementById("salarySlab").style.visibility = 'visible';	        			
		        		}else{
		        			document.getElementById("infomsgbox").style.visibility = 'visible';
		        			document.getElementById("infomsg").innerHTML = "Slab for this Tax Type is already set for the Current Academic Year. You can Edit it from the \"View Slab For Miscellaneous Tax\" link.";
		        		}
					}	        	
		        }
			});
		}    	
	});
	
	
	
});