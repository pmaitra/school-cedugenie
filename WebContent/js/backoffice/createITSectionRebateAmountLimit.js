$(document).ready(function() {
	$("#incomeAge").change(function(){	
		removeSelectData();			
		if(($(this).val()) != ''){
			$.ajax({
		        url: '/icam/getITSectionGroupsForIncomeAge.html',
		        dataType: 'json',
		        data: {	'incomeAge.incomeAgeCode': $("#incomeAge").val(),	'financialYear.financialYearCode': $("#finYear").val() },
		        success: function(dataDB) {		        	
		        	if(dataDB != null && dataDB != "")
					{
		        		var arr = dataDB.split("^^");
						for(var i=0; i<arr.length-1; i++){
							var arr2 = arr[i].split("~");
							$("#itSecGroup").append($("<option></option>").val(arr2[0]).html(arr2[1]));
						}		        		
					}		        	
		        }
			});
		}    	
	});
	
	$("#itSecGroup").change(function(){	
		$('#itSecDetailAmnt').empty();
		if(($(this).val()) != ''){			
			
			$.ajax({
		        url: '/icam/checkITSecDetailAmount.html',
		        dataType: 'json',
		        data: {	'status': $("#itSecGroup").val(), 
		        		'incomeAge.incomeAgeCode': $("#incomeAge").val(),	
		        		'financialYear.financialYearCode': $("#finYear").val() },
		        success: function(dataDB) {		        	
		        	if(dataDB != null && dataDB != ""){
		        		if(dataDB == 0){
        		        	document.getElementById('infomsgbox').style.visibility = 'collapse';
		        			$.ajax({
		        		        url: '/icam/getITSectionForITGroups.html',
		        		        dataType: 'json',
		        		        data: {	'status': $("#itSecGroup").val(), 
		        		        		'incomeAge.incomeAgeCode': $("#incomeAge").val(),	
		        		        		'financialYear.financialYearCode': $("#finYear").val() },
		        		        success: function(dataDB) {		        	
		        		        	if(dataDB != null && dataDB != "")
		        					{
//		        		        		alert(dataDB);		        		
		        		        		var arr = dataDB.split("###");
		        		        		
		        	        			var sm = "";						
		        	        			sm = sm + "<tr><th colspan='2'>Amount Details of IT Section Group : "+($("#itSecGroup option:selected").text())+"</th></tr>" +
		        	        					"<tr><th>IT Section Group</th><th>Maximum Amount</th></tr>" +
		        	        					"<tr><th>"+($("#itSecGroup option:selected").text())+"</th>" +
		        	        						"<th><input type='text' class='textfield1' id='groupAmount' name='groupAmount' readonly='readonly' value='"+arr[0]+"'></th></tr>";
		        		        				        		        		
		        		        		var arr2 = arr[1].split("##");
		        		        		for(var i=0; i<arr2.length-1; i++){							
		        							var arr3 = arr2[i].split("^^");
		        							
		        							//split arr3[0]
		        							var secSplit = arr3[0].split("~");
		        							sm = sm + "<tr><th colspan='2'>Enter Maximum Amount For Sub Section of "+secSplit[1]+"</th></tr>" +
		        										"<tr><th>IT Section</th><th>Maximum Amount</th></tr>" +
		        										"<input type='hidden' name='itSecName' value='"+secSplit[0]+"' >" ;
		        							if(secSplit[2] == 0){								
		        								sm = sm + "<tr><th>"+secSplit[1]+"</th><th>NA</th></tr>";
		        							}else{								
		        								sm = sm + "<tr><th>"+secSplit[1]+"</th><th><input type='text' class='textfield1' id='secAmount' name='secAmount' readonly='readonly' value='"+secSplit[2]+"'></th></tr>";
		        							}							
//		        							alert("sec --> "+arr3[0]+"  ||  sec Details --> "+arr3[1]);
		        							
		        							if(arr3[1] != ""){
		        								var arr4 = arr3[1].split("::");
		        								sm = sm + "<tr><th>Rebate</th><th>Maximum Amount</th></tr>";
		        								for(var j=0; j<arr4.length-1; j++){
//		        									alert("@@ secDetailName --> "+arr4[j]);
		        									var rebateSplit = arr4[j].split(":");									
		        									sm = sm + "<input type='hidden' name='"+secSplit[0]+"' value='"+rebateSplit[0]+"' >" +
		        											"<tr><td>"+rebateSplit[1]+"</td><td><input type='text' class='textfield1' id='secDetailAmount"+j+"' name='"+rebateSplit[0]+"'></td></tr>";		        									
		        								}
		        							}
		        							
		        						}
		        		        		$('#itSecDetailAmnt').append(sm);
		        		        		document.getElementById("itSecDetailAmntDiv").style.visibility = 'visible';		        		        		
		        					}
		        		        	document.getElementById("successbox").style.visibility = 'collapse';
		        		        }
		        			});
		        		}else{
		        			document.getElementById("itSecDetailAmntDiv").style.visibility = 'collapse';	
		        			document.getElementById('infomsgbox').style.visibility="visible";
		        			document.getElementById('infomsg').innerHTML="Maximum Amount for Rebates under this IT Section Group is already Created.";
		        		}
		        		
		        	}
		        	
		        }
			});
			
		}    	
	});
	
});


function removeSelectData()
{
	var x = document.getElementById("itSecGroup");
	x.innerHTML = "";
	var option = document.createElement("option");
	option.text = "Select...";
	option.value = "";
	try  {	  
	  x.add(option,x.options[0]);
	}catch (e)  {
	  x.add(option,0);
	}
}



