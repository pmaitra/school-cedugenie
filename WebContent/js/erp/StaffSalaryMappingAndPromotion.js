$(document).ready(function() {	
		 $("#userId").autocomplete({
		 	source: '/icam/getStaffUserIdList.html'
		 });
		 
		 
		 
		 $("#designationType").change(function(){
		    	$.ajax({
			        url: '/icam/getDesignationBasedOnDesignationType.html',
			        dataType: 'json',
			        data: "designationTypeCode=" + ($(this).val()),
			        success: function(dataDB) {	
			        	var x=document.getElementById("designation");
			        	if(dataDB != null)
						{
			        		for(var i=x.length;i>0;i--){
				        		x.remove(i);
				        	}
			        		var arr = dataDB.split("@");
			        			
							for (var i=0;i<arr.length;i++){   
								var designationDetails = arr[i].split(",");
			                    $("#designation").append($("<option></option>").val(designationDetails[0]).html(designationDetails[1]));
							}
						}
			        	if(dataDB == null){
			        		for(var j=x.length;j>0;j--){
				        		x.remove(j);
				        	}
			        	}
			       }
				});	      
		    });
		    
		    
		    $("#designation").change(function(){
		    	$.ajax({
			        url: '/icam/getReportingPerson.html',
			        dataType: 'json',
			        data: "designation=" + ($(this).val()),
			        success: function(dataDB) {	
			        	var levelSelect =document.getElementById("level"); 
			        	if(dataDB != null)
						{
			        		
			        		
			        		
			        		var arr = dataDB.split("$$");
			        		
			        			
							
							if(arr[1] != null){
								for(var select=levelSelect.length;select>0;select--){
									if(levelSelect.options[select-1].value != ""){
										levelSelect.remove(select-1);
									}
					        	}
								var arrForLevel = arr[1].split("@");
								for (var j=0;j<arrForLevel.length;j++){   
									var levelList = arrForLevel[j].split(",");
				                    $("#level").append($("<option></option>").val(levelList[0]).html(levelList[1]));
								}
							}
							if(arr[1] == "null"){
								for(var selectNo=levelSelect.length;selectNo>0;selectNo--){
									if(levelSelect.options[selectNo-1].value != ""){
										levelSelect.remove(selectNo-1);
									}
					        	}
							}
							
						}
			        	if(dataDB == null){
			        		
			        		for(var selectNo=levelSelect.length;selectNo>0;selectNo--){
			        			if(levelSelect.options[selectNo-1].value != ""){
			        				levelSelect.remove(selectNo-1);
			        			}
				        	}
			        	}
			       }
				});	      
		    });
		    
		    $("#level").change(
				function() {
					$.ajax({
				        url: '/icam/getSalTemplate.html',
				        dataType: 'json',
				        data: "strlevel=" + ($(this).val()) + "&strDesignationCode=" + ($("#designation")).val(),
				        success: function(dataDB) {
				        	
				        	document.getElementById("salaryTemplateName").innerHTML="<option value=''>Select...</option>";
				        	if(dataDB != null && dataDB !=""){
				        		var arr = dataDB.split("@@");	
								for (var i=0;i<arr.length-1;i++){
									var arr2 = arr[i].split("~");
									$("#salaryTemplateName").append($("<option></option>").val(arr2[1]).html(arr2[0]));
								}							
							}
				        }
					});	      
				}
		    );
		        
		    $("#salaryTemplateName").change(
				function() {
					$.ajax({
				        url: '/icam/getSalBreakUp.html',
				        dataType: 'json',
				        data:{
				        	strSalaryTemp:$(this).val(),
				        	strDesignationCode:($("#designation")).val(),
				        	strLevel:($("#level")).val()
				        },
				        success: function(dataDB) {
				        	     	
				        	if(dataDB != null && dataDB !=""){
				        		var table = document.getElementById("salaryStructureForPromote");
				        		//table.deleteRow(2);
				        		//row = table.insertRow(1);
				        		table.innerHTML="<tr><th colspan='2'>New Salary Breakup</th></tr>";
			                    var earTable=document.createElement("table");
			                    var dedTable=document.createElement("table");
			                    earTable.innerHTML="<tr><th>EARNING</th><th>AMOUNT(Rs.)</th><th>FORMULA(Given)</th></tr>";
			                    earTable.setAttribute("cellspacing","0");
			                    earTable.setAttribute("cellpadding","0");
			                    earTable.setAttribute("class","table table-bordered table-striped mb-none dataTable");
			                    earTable.id="salaryStructureFotr";
			                    
			                    dedTable.innerHTML="<tr><th>DEDUCTION</th><th>AMOUNT(Rs.)</th><th>FORMULA(Given)</th></tr>";
			                    dedTable.setAttribute("cellspacing","0");
			                    dedTable.setAttribute("cellspacing","0");
			                    dedTable.setAttribute("cellpadding","0");
			                    dedTable.setAttribute("class","table table-bordered table-striped mb-none dataTable");
			                    dedTable.id="salaryStructureForded";
			                    
				        		var arr = dataDB.split("@@");
				        		var y = 0;
				        		var x = 0;
				        		for(var i = 0; i < arr.length-1; i++){
				        			arr2 = arr[i].split("~");
					                
					                if(arr2[0]=="EARNING"){
					                	var rowCount = earTable.rows.length;
					                	
					                    var row = earTable.insertRow(rowCount);
	
					            		var cell = row.insertCell(0);		
					                    var element = document.createTextNode(arr2[1]);
					                    var hidText = document.createElement("input");
					                    hidText.type = "hidden";
					                    hidText.name="salBreakUpHead";
					                    hidText.id="salBreakUpHead"+y;
					                    hidText.value=arr2[1];
					            		cell.appendChild(element);
					            		cell.appendChild(hidText);
					            		
					            		var cell = row.insertCell(1);		
					                    var element = document.createElement("input");
					                    element.type = "text";
					                    element.className="form-control";
					                    element.name="amount";
					                    element.id="ea"+y;
					                    element.value="0";
					                    element.setAttribute("onblur","calculateAmount('"+arr2[1]+"',this);");
					                    if(arr2[2] != "null"){
					                    	 element.setAttribute("readonly","readonly");
					                    }
					            		cell.appendChild(element);
					            		
					            		
					            		var cell = row.insertCell(2);		
					            		if(arr2[2] != "null"){
						                    var element = document.createTextNode(arr2[2]);
						                   
						                    cell.appendChild(element);
					            		}
					        		}else{
					        			if(arr2[3] == "false"){
						        			var rowCount = dedTable.rows.length;
						                    var row = dedTable.insertRow(rowCount);
		
						            		var cell = row.insertCell(0);		
						                    var element = document.createTextNode(arr2[1]);
						                    var hidText = document.createElement("input");
						                    hidText.type = "hidden";
						                    hidText.name="salBreakUpHead";
						                    hidText.id="salBreakUpHead"+x;
						                    hidText.value=arr2[1];
						            		cell.appendChild(element);
						            		cell.appendChild(hidText);
						            		
						            		var cell = row.insertCell(1);		
						                    var element = document.createElement("input");
						                    element.type = "text";
						                    element.className="form-control";
						                    element.name="amount";
						                    element.id="de"+y;
						                    element.value="0";
						                    element.setAttribute("onblur","calculateAmount('"+arr2[1]+"',this);");
						                    if(arr2[2] != "null"){
						                    	element.setAttribute("readonly","readonly");
						                    }
						            		cell.appendChild(element);
						            		
						            		var cell = row.insertCell(2);		
						            		if(arr2[2] != "null"){
							                    var element = document.createTextNode(arr2[2]);
							                    cell.appendChild(element);
						            		}
					        			}
					        		}
					                y++;
					                x++;
				        		}
				        		
				        		var rowCount = dedTable.rows.length;
			                    var row = dedTable.insertRow(rowCount);
			            		var cell = row.insertCell(0);		
			                    var element = document.createTextNode("Total Deduction");
			            		cell.appendChild(element);			            		
			            		var cell = row.insertCell(1);		
			                    var element = document.createElement("input");
			                    element.type = "text";
			                    element.className="form-control";
			                    element.name="totalDeductionAmount";
			                    element.id="totalDeductionAmount";
			                    element.setAttribute("readonly","readonly");
			                    element.value="0";
			            		cell.appendChild(element);
			            		var cell = row.insertCell(2);
			            		
			            		var rowCount = earTable.rows.length;
			                    var row = earTable.insertRow(rowCount);
			            		var cell = row.insertCell(0);		
			                    var element = document.createTextNode("Gross Amount(Rs.)");
			            		cell.appendChild(element);			            		
			            		var cell = row.insertCell(1);		
			                    var element = document.createElement("input");
			                    element.type = "text";
			                    element.className="form-control";
			                    element.name="grossAmount";
			                    element.id="grossAmount";
			                    element.setAttribute("readonly","readonly");
			                    element.value="0";
			            		cell.appendChild(element);
			            		var cell = row.insertCell(2);
				        		
				        		var amountTab=document.createElement("table");
				        		amountTab.innerHTML="<tr><th>Net Amount(Rs.)</th></tr>";
				        		amountTab.setAttribute("cellspacing","0");
				        		amountTab.setAttribute("cellpadding","0");
				        		amountTab.setAttribute("class","table table-bordered table-striped mb-none");
				        		var row = amountTab.insertRow(1);				        		
			            			            		
			            		var cell = row.insertCell(0);		
			                    var element = document.createElement("input");
			                    element.type = "text";
			                    element.className="form-control";
			                    element.id="netAmount";
			                    element.name="netAmount";
			                    element.setAttribute("readonly","readonly");
			                    element.value="0";
			            		cell.appendChild(element);
				        		
			            		var rowCount1 = table.rows.length;
				        		row = table.insertRow(rowCount1);
			            		var cell = row.insertCell(0);
			            		cell.appendChild(earTable);
			            		var cell = row.insertCell(1);
			            		cell.appendChild(dedTable);
			            		cell.appendChild(amountTab);
							}
				        }
					});	      
				}
		    );
	});

function userIdValidation(){
	ran = /^[A-Za-z0-9_.-]{1,}$/;
	var userId=document.getElementById("userId").value.replace(/\s{1,}/g,'');
	if(userId.length==0){
		alert("Please Enter User Id");
		return false;
	}
	if (userId.length != 0) {
		if (!userId.match(ran)) {
			alert("Invalid User Id");
			return false;
		}
	}	
	return true;
}	

var flag = 0;
var flagAnother = 0;
function calculateAmount(payHead,thisObj){
	
	
	 var table = document.getElementById("salaryStructureFotr"); 
	 var rowCount = table.rows.length-1;
	
	 var tableForDed = document.getElementById("salaryStructureForded");
	 var rowCountForDed = tableForDed.rows.length-1;
		
	 var operatorArray = [" + "," % "," / "," + "," - "," * "];
	 var values = [];
	 var replacedFormula = [];
	 var replacedFormulaAnother = [];
	 
	 var replacedFormulaForDed = [];
	 var replacedFormulaAnotherForDed = [];
	 var BasicPayHead = payHead;
	 var amount=document.getElementsByName("amount");
	
	 
	 
	 if(BasicPayHead == 'BASIC'){
		for(var i=1;i<rowCount;i++){
			var payHeads = document.getElementById("salaryStructureFotr").rows[i].cells[0].children[0].value;
			var outputId = amount[i-1].id;
			
			var payHeadsAmount = document.getElementById(outputId).value;
			
			var valOfTextBox=document.getElementById(outputId).value.replace(/\s{1,}/g,'');
			if (isNaN(valOfTextBox)){
				alert("Please Enter Numeric Value");
				return false;
			}
			if(valOfTextBox == ""){
				document.getElementById(outputId).value=0;
			}
			if (valOfTextBox<0){
				alert("Please Enter Positive Value");
				return false;
			}			
			values.push(payHeads+"-"+payHeadsAmount);
			var formulaForEarning = document.getElementById("salaryStructureFotr").rows[i].cells[2].innerHTML;
			
			
			if(formulaForEarning != ""){				
				for(var k=0; k<operatorArray.length; k++){
				   if (formulaForEarning.indexOf(operatorArray[k]) != -1){
					   if(operatorArray[k] == " + "){
						   formulaForEarning = formulaForEarning.replace(operatorArray[k]," %2B ");
			            }
			            if(operatorArray[k] == " % "){
			            	formulaForEarning = formulaForEarning.replace(operatorArray[k]," %25 ");
			            }
			        }
				}
				var res = formulaForEarning;
				for(var l = 0; l<values.length;l++){
				   var word_to_match = values[l].split("-");
				   //use \b to match on word boundaries
				   var filter = new RegExp('\\b' + word_to_match[0] + '\\b', 'gi');
				   if(formulaForEarning.match(filter)) {
					   flag = 1;
				       if(word_to_match[1] != ""){
				    	   res =  res.replace(word_to_match[0],word_to_match[1]);
				    	   replacedFormula.push(res+"_"+outputId);
				       }
				   } 
			   }
				if(flag != 1){
					replacedFormulaAnother.push(formulaForEarning+"_"+outputId);
				 }
				flag = 0;
			}
			if(formulaForEarning == ""){
				calculateGrossAndNetSalary();
			}
			
		}
		
		
		for(var arrayLength = 0; arrayLength<replacedFormula.length;arrayLength++){
			var splitedValue = replacedFormula[arrayLength].split("_");
			
			for(var headArrayLen = 0; headArrayLen<values.length;headArrayLen++){
				   var word_to_match = values[headArrayLen].split("-");
				   var filter = new RegExp('\\b' + word_to_match[0] + '\\b', 'gi');
				   if(splitedValue[0].match(filter)) {
					   replacedFormula.splice(1,replacedFormula.indexOf(splitedValue[0]+"_"+splitedValue[1]));
				   }
			}
		}
		
		for(var arrayLengths = 0; arrayLengths<replacedFormula.length;arrayLengths++){
			
			
			$.ajax({
		        url: '/icam/getCalculatedExactValueOfGivenFormula.html', //for HRA
		        data: "finalFormula=" + (replacedFormula[arrayLengths]),
		        dataType: 'json',
		        success: function(data) {
		        	
		        	if(data!=null){
		        	var individualData = data.split(";");
		        	/* modified by sourav.bhadra on 31-08-2017 */
	        		var amt = parseFloat(individualData[0]);
		        	document.getElementById(individualData[1]).value = (amt).toFixed(2);
		        	calculateGrossAndNetSalary();
		        	}
		        }//end of success
				});// end of ajax 
		}
		for(var arrayLengthAno = 0; arrayLengthAno<replacedFormulaAnother.length;arrayLengthAno++){
			$.ajax({
		        url: '/icam/getCalculatedExactValueOfGivenFormula.html', 
		        data: "finalFormula=" + (replacedFormulaAnother[arrayLengthAno]),
		        dataType: 'json',
		        success: function(data) {
		        	
		        	if(data!=null){
		        	var individualData = data.split(";");
		        	/* modified by sourav.bhadra on 31-08-2017 */
	        		var amt = parseFloat(individualData[0]);
		        	document.getElementById(individualData[1]).value = (amt).toFixed(2);
		        	calculateGrossAndNetSalary();
		        	}
		        }//end of success
				});// end of ajax 
		}
		for(var j=1;j<rowCountForDed;j++){
			
			/*var outputId = document.getElementById("salaryStructureForded").rows[j].cells[1].firstChild.id;*/
			var outputId = amount[i-1].id;
			var formulaForDed = document.getElementById("salaryStructureForded").rows[j].cells[2].innerHTML;
			
			if(formulaForDed != ""){
				for(var k=0; k<operatorArray.length; k++){
				   if (formulaForDed.indexOf(operatorArray[k]) != -1){
					   if(operatorArray[k] == " + "){
						   formulaForDed = formulaForDed.replace(operatorArray[k]," %2B ");
			            }
			            if(operatorArray[k] == " % "){
			            	formulaForDed = formulaForDed.replace(operatorArray[k]," %25 ");
			            	
			            //	calculateGrossAndNetSalary();
			            }
			        }
				}
				
				var res = formulaForDed;
				for(var l = 0; l<values.length;l++){
				   var word_to_match = values[l].split("-");
				   //use \b to match on word boundaries
				   var filter = new RegExp('\\b' + word_to_match[0] + '\\b', 'gi');
				   if(formulaForDed.match(filter)) {
					   flagAnother = 1;
				       if(word_to_match[1] != ""){
				    	   res =  res.replace(word_to_match[0],word_to_match[1]);
				    	   replacedFormulaForDed.push(res+"_"+outputId);
				       }
				   } 
			   }
				if(flagAnother != 1){
					replacedFormulaAnotherForDed.push(formulaForDed+"_"+outputId);
				 }
				flagAnother = 0;
			}
		}
		for(var arrayLengthForDedAno = 0; arrayLengthForDedAno<replacedFormulaForDed.length;arrayLengthForDedAno++){
			var splitedValue = replacedFormulaForDed[arrayLengthForDedAno].split("_");
			for(var headArrayLen = 0; headArrayLen<values.length;headArrayLen++){
				   var word_to_match = values[headArrayLen].split("-");
				   var filter = new RegExp('\\b' + word_to_match[0] + '\\b', 'gi');
				   if(splitedValue[0].match(filter)) {
					   replacedFormulaForDed.splice(1,replacedFormulaForDed.indexOf(splitedValue[0]+"_"+splitedValue[1]));
				   }
			}
		}
		for(var arrayLengthForDed = 0; arrayLengthForDed<replacedFormulaForDed.length;arrayLengthForDed++){
			$.ajax({
		        url: '/icam/getCalculatedExactValueOfGivenFormula.html', 
		        data: "finalFormula=" + (replacedFormulaForDed[arrayLengthForDed]),
		        dataType: 'json',
		        success: function(data) {
		        	if(data != null){
			        	var individualData = data.split(";");
			        	/* modified by sourav.bhadra on 31-08-2017 */
		        		var amt = parseFloat(individualData[0]);
			        	document.getElementById(individualData[1]).value = (amt).toFixed(2);
			        	calculateGrossAndNetSalary();
		        	}
		        }//end of success
				});// end of ajax 
		}
		for(var arrayLens = 0; arrayLens<replacedFormulaAnotherForDed.length;arrayLens++){
			$.ajax({
		        url: '/icam/getCalculatedExactValueOfGivenFormula.html', 
		        data: "finalFormula=" + (replacedFormulaAnotherForDed[arrayLen]),
		        dataType: 'json',
		        success: function(data) {
		        	if(data != null){
		        		var individualData = data.split(";");
		        		/* modified by sourav.bhadra on 31-08-2017 */
		        		var amt = parseFloat(individualData[0]);
			        	document.getElementById(individualData[1]).value = (amt).toFixed(2);
			        	calculateGrossAndNetSalary();
		        	}
		        }//end of success
				});// end of ajax 
		}
	 }
	 if(BasicPayHead != 'BASIC'){
			calculateGrossAndNetSalary();
	 }
	 /////////////////////////////////////////////////////////////////////////////////////////////////////
}

function calculateGrossAndNetSalary(){
	var earn=0;
	var ded=0;
	var amount=document.getElementsByName("amount");
		 
	for(var i=0;i<amount.length;i++){
		var id=amount[i].id;
		var val=amount[i].value;
		val=parseFloat(val);
		id=id.substring(0,2);
		
		var valOfTextBox=amount[i].value.replace(/\s{1,}/g,'');
		if (isNaN(valOfTextBox)){
		//	document.getElementById("warningbox").style.visibility='visible';
		//	document.getElementById("warningbox").innerHTML="Please Enter Numeric Value";
			return false;
		}
		if(valOfTextBox == ""){
			document.getElementById(outputId).value=0;
		}
		if (valOfTextBox<0){
		//	document.getElementById("warningbox").style.visibility='visible';
		//	document.getElementById("warningbox").innerHTML="Please Enter Positive Value";
			return false;
		}
		
		
		if(id=="ea"){
			earn=(earn+val);
		}
		if(id=="de"){
			ded=(ded+val);
		}
	}
	/* modified by sourav.bhadra on 31-08-2017 */
	document.getElementById("grossAmount").value=(earn).toFixed(2);
	document.getElementById("totalDeductionAmount").value=(ded).toFixed(2);
	document.getElementById("netAmount").value=(earn-ded).toFixed(2);
}

function updateSalaryBreakUp(){
	var designationType = document.getElementById("designationType").value;
	var designation = document.getElementById("designation").value;
	var level = document.getElementById("level").value;
	var salaryTemplateName = document.getElementById("salaryTemplateName").value;
	document.getElementById("designationTypeHid").value = designationType;
	document.getElementById("designationHid").value = designation;
	document.getElementById("levelHid").value = level;
	document.getElementById("templateHid").value = salaryTemplateName;
	 return true;
}