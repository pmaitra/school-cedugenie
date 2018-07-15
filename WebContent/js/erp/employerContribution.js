$(document).ready(function() {
	$("#figureType").change(function(){
		var row = "";
	//	document.getElementById("infomsgbox").style.visibility = 'collapse';		
		document.getElementById("salarySlab").style.visibility = 'collapse';
		if(($("#miscTaxTypeName").val()) == ''){
	//		document.getElementById('warningbox').style.visibility = "visible";
	//		document.getElementById('warningmsg').innerHTML = "Please Select a Tax Type";
			return false;
		}
		if(($("#taxBasedOn").val()) == ''){
	//		document.getElementById('warningbox').style.visibility = "visible";
	//		document.getElementById('warningmsg').innerHTML = "Please Select a Tax Type Based On";
			return false;
		}
		$('#contributionSlab tbody').remove();
		$('#contributionSlabForUpdate tbody').remove();
		if(($(this).val()) != ''){	
			var vals = ($("#figureType").val());
			var cell = document.getElementById("contributionSlab").rows[0].cells;
			cell[2].innerHTML = "TAX "+vals;
    		document.getElementById("salarySlab").style.visibility = 'visible';
    		
			 row = $('<tr>'); 
             row.append($('<td><input type="text" class="form-control" name = "startSlabIndex" id="startSlabIndex0" value="0.0" style="text-align: right;" onblur="setZero(this);" ></td>'));
             row.append($('<td><input type="text" class="form-control" name = "endSlabIndex" id="endSlabIndex0" value="0.0" style="text-align: right;" onblur="setZero(this);"></td>'));
             row.append($('<input type="text" class="form-control" name = "taxAmount" id="taxAmount0" value="0.0" style="text-align: right;" onblur="setZero(this);"></td>'));
             row.append($('<td><img alt="" src="/cedugenie/images/minus_icon.png" onclick="deleteRow(this);"></td>'));
             $('#contributionSlab').append(row);	
             
             var lastRow = $('<tr>'); 
     		lastRow.append($('<td colspan="7"><input type="button" value = "ADD" class="addbtn" onclick="addrows();"></td>'));
     		$('#contributionSlab').append(lastRow);
             
			
//    		$("#submitButton").css('visibility','visible');
    		$("#clearButton").css('visibility','visible');
    		$("#contributionSlab").css('visibility','visible');
		}    	
});
	
	
	$("#miscTaxTypeName").change(function(){
		var row = "";
		if(($(this).val()) == ''){
		//	document.getElementById('warningbox').style.visibility = "visible";
		//	document.getElementById('warningmsg').innerHTML = "Please Select a Tax Type";
			return false;
		}
		if(($(this).val()) != ''){	
			$.ajax({
		        url: '/cedugenie/getSubmittedEmployerContribution.html',
		        dataType: 'json',
		        data: "miscTaxTypeName=" + ($("#miscTaxTypeName").val()),
		        success: function(dataDB) {
		        	if(dataDB != "")
					{
		        		$("#salarySlab").css('visibility','visible');
		        		$("#contributionSlab").css('visibility','collapse');		        		
		        		$("#submitButton").css('visibility','visible');
		        		$("#submitButton").attr("disabled",true);
		        		$("#editButton").css('visibility','visible');
		        		$("#clearButton").css('visibility','visible');
		        		$('#contributionSlabForUpdate').css('visibility','visible');
		        		$('#contributionSlabForUpdate thead').css('visibility','visible');
		        		$('#contributionSlabForUpdate tbody').remove();
		        		$('#contributionSlab tbody').remove();
		        		
		        		var arraySplited = dataDB.split("@@");
		        		for(var len=0;len<arraySplited.length-1;len++){
		        			
		        			var individualData = arraySplited[len].split(",");
		        			var cell = document.getElementById("contributionSlabForUpdate").rows[0].cells;
			    			cell[2].innerHTML = "TAX "+individualData[3];
		        			$("#taxBasedOn option").each(function(){
		        				var optionVal = $(this).attr("value");
		        				if(individualData[7] == optionVal){
		        					$(this).attr("selected","selected");
		        					$("#taxBasedOn").attr("disabled",true);
		        				}
		        			});
		        			$("#figureType option").each(function(){
		        				var optionValAnother = $(this).attr("value");
		        				if(individualData[3] == optionValAnother){
		        					$(this).attr("selected","selected");
		        					$("#figureType").attr("disabled",true);
		        				}
		        			});
		        			
		        			 row = $('<tr>'); 
	    	                 row.append($('<td><input type="text" class="form-control" name = "startSlabIndex" id="startSlabIndex0" value="'+individualData[0]+'" readonly="readonly" style="text-align: right;" onblur="setZero(this);" ></td>'));
	    	                 row.append($('<td><input type="text" class="form-control" name = "endSlabIndex" id="endSlabIndex0" value="'+individualData[1]+'" readonly="readonly" style="text-align: right;" onblur="setZero(this);"></td>'));
	    	                 row.append($('<input type="text" class="form-control" name = "taxAmount" id="taxAmount0" value="'+individualData[2]+'" readonly="readonly" style="text-align: right;" onblur="setZero(this);"></td>'));
	    	                 row.append($('<td><img alt="" src="/cedugenie/images/minus_icon.png" onclick="deleteRowForUpdate(this);"></td>'));
	    	                 $('#contributionSlabForUpdate').append(row);	
		        		}
		        		var lastRow = $('<tr>'); 
		        		lastRow.append($('<td colspan="7"><input type="button" disabled id="addRowButton" class="addbtn btn btn-primary" value="ADD" onclick="addrowsForUpdate();"></td>'));
		        		$('#contributionSlabForUpdate').append(lastRow);
					}	  
		        	if(dataDB == "")
					{
		        		$("#salarySlab").css('visibility','collapse');
		        		$("#contributionSlab").css('visibility','collapse');
		        		$("#submitButton").css('visibility','collapse');
		        		$("#clearButton").css('visibility','collapse');
		        		$("#editButton").css('visibility','collapse');
		        		$("#contributionSlabForUpdate").css('visibility','collapse');
		        		$("#taxBasedOn").attr("disabled",false);
		        		$("#figureType").attr("disabled",false);
		        		$("#taxBasedOn").find('option:selected').removeAttr("selected");
		        		$("#figureType").find('option:selected').removeAttr("selected");
					}
		        }
			});
		}
	});
	
	
	$("#editButton").click(function(){
		$("#taxBasedOn").attr("disabled",false);
		$("#figureType").attr("disabled",false);
		$("#submitButton").attr("disabled",false);
		$("#addRowButton").attr("disabled",false);
		$(".form-control").each(function(){
			$(this).attr("readonly",false);
		});
	
	});
});


var i = 1;
function addrows(){	
	//document.getElementById("warningbox").style.visibility='collapse';
	//document.getElementById("warningmsg").innerHTML="";
	
    var table = document.getElementById("contributionSlab"); 
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
    
    
    var cell = row.insertCell(3);
    var element = document.createElement("img");
    element.setAttribute("src","/cedugenie/images/minus_icon.png");
    element.setAttribute("onclick","deleteRow(this);");
    cell.appendChild(element); 
    
    i++;     
};

var j = 1;
function addrowsForUpdate(){	
//	document.getElementById("warningbox").style.visibility='collapse';
//	document.getElementById("warningmsg").innerHTML="";
	
    var table = document.getElementById("contributionSlabForUpdate"); 
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
    element.id = "startSlabIndex"+j;
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
    element.id = "endSlabIndex"+j;
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
    element.id = "taxAmount"+j;
    cell.appendChild(element);
    
    
    var cell = row.insertCell(3);
    var element = document.createElement("img");
    element.setAttribute("src","/cedugenie/images/minus_icon.png");
    element.setAttribute("onclick","deleteRowForUpdate(this);");
    cell.appendChild(element); 
    
    i++;     
};


function deleteRow(obj){	
		var table = document.getElementById("contributionSlab");
		var rowCount = table.rows.length;
		if(rowCount>3){
			var p = obj.parentNode.parentNode;
			p.parentNode.removeChild(p);			
		}else{
	//		document.getElementById("warningbox").style.visibility='visible';
	//		document.getElementById("warningmsg").innerHTML="Atleast One Row Required";
		}
	}   

function deleteRowForUpdate(obj){	
	var table = document.getElementById("contributionSlabForUpdate");
	var rowCount = table.rows.length;
	if(rowCount>3){
		var p = obj.parentNode.parentNode;
		p.parentNode.removeChild(p);			
	}else{
	//	document.getElementById("warningbox").style.visibility='visible';
	//	document.getElementById("warningmsg").innerHTML="Atleast One Row Required";
	}
}   

function setZero(tBox){
//	document.getElementById('warningbox').style.visibility = "collapse";
	if(tBox.value==""){
		tBox.value="0.00";
	}		
	if(isNaN(tBox.value)){
	//	document.getElementById('warningbox').style.visibility="visible";
	//	document.getElementById('warningmsg').innerHTML="Please Enter Proper Numeric Value";
		tBox.value="0.00";
	}else{
		var p=parseFloat(tBox.value);
		if(p<0.0){
	//		document.getElementById('warningbox').style.visibility="visible";
	//		document.getElementById('warningmsg').innerHTML="Please Enter a positive Number";
			tBox.value="0.00";
		}
	}		
}