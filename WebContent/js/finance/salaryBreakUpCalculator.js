


function c(val)
{
	if(val == ""){
		var nextCellId = document.getElementById("hidNextCell").value;
		document.getElementById(nextCellId).innerHTML = "";
	}
		var outputId = document.getElementById("hidVal").value;
		document.getElementById(outputId).value = val;
}
function v(val)
{
	var outputId = document.getElementById("hidVal").value;
	document.getElementById(outputId).value+=val;
}
function doBackSpace(){
	var outputId = document.getElementById("hidVal").value;
	var fulltext = document.getElementById(outputId).value;
	var result = fulltext.substring(0, fulltext.length-1);
	document.getElementById(outputId).value = result;
}
$(document).ready(function(){
	$(".salaryBreakUpFormula").each(function(){
		$(this).focus(function() {
		  var inputId = $(this).attr("id");
		  var nextCell = $(this).parent().next().attr("id");
		  document.getElementById("hidVal").value = inputId;
		  document.getElementById("hidNextCell").value = nextCell;
		  $("#calculatorDiv").show();
	      document.getElementById("warningbox").style.visibility='collapse';
		  document.getElementById("warningbox").innerHTML="";
		});
	});   
	
	$("#closeButton").click(function(){
		 $("#calculatorDiv").hide();
	});
	
	
	$("#calButton").click(function(){
		var flag = 0;
		var outputId = document.getElementById("hidVal").value;
		var formula = document.getElementById(outputId).value;
		var nextCellId = document.getElementById("hidNextCell").value;
		var values = [];
		var amounts = [];
		var operatorArray = [" + "," % "];
		var table = document.getElementById("formulaSetUpTable");
		$(table).find("tr").each(function() {
			var payHeads = $(this).find("td:first").find("input").val();
		    var payHeadsAmount = $(this).find("td:last").text().trim();
		    amounts.push(payHeadsAmount);
			if(payHeads != null){
				values.push($(this).find("td:first").find("input").val()+"-"+payHeadsAmount);
			}
		});
		var res = formula;
		for(var i = 0; i<values.length;i++){
		   var word_to_match = values[i].split("-");
		   //use \b to match on word boundaries
		   var filter = new RegExp('\\b' + word_to_match[0] + '\\b', 'gi');
		   if(formula.match(filter)) {
			   flag = 1;
		       if(word_to_match[1] != ""){
		    	   res =  res.replace(word_to_match[0],word_to_match[1]);
		    	   document.getElementById("warningbox").style.visibility='collapse';
				   document.getElementById("warningbox").innerHTML="";
				   document.getElementById("hidFormula").value=res;
		       }
		       else{
		    	   document.getElementById(nextCellId).innerHTML = "";
		    	   document.getElementById("hidFormula").value="";
		    	   document.getElementById("warningbox").style.visibility='visible';
				   document.getElementById("warningbox").innerHTML=word_to_match[0]+"is not set yet,so formula cant be solved.";
		       }
		   } 
	   }
	   if(flag != 1){
		   document.getElementById("hidFormula").value=formula;
	   }
	   var finalFormula = document.getElementById("hidFormula").value;
	   var cleanFormula = finalFormula;
	   for(var i=0; i<operatorArray.length; i++){
		   if (cleanFormula.indexOf(operatorArray[i]) != -1){
	            if(operatorArray[i] == " + "){
	            	finalFormula = finalFormula.replace(operatorArray[i]," %2B ");
	            }
	            if(operatorArray[i] == " % "){
	            	finalFormula = finalFormula.replace(operatorArray[i]," %25 ");
	            }
	        }
	    }
	   $.ajax({
	        url: '/cedugenie/getCalculatedValueOfGivenFormula.html',
	        data: "finalFormula=" + (finalFormula),
	        dataType: 'json',
	        success: function(data) {
	        	if(data != null){
	     			document.getElementById(nextCellId).innerHTML = data;
	        	}//end if for not null
	        	if(data == null){
	 				document.getElementById(nextCellId).innerHTML = "";
	        	}//end if for null
	        }//end of success
			});// end of ajax 
		});
	
	
	
	$("#employeeCode").change(function() {
		var employeeCode=document.getElementById("employeeCode").value;	
		if(employeeCode == "null" || employeeCode ==''){
    		document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningbox").innerHTML= "Please Select any Employee Code.";
		}
		if(employeeCode != "null" && employeeCode !=''){
			document.getElementById("warningbox").style.visibility='collapse';
			document.getElementById("warningbox").innerHTML= "";
			$.ajax({
		        url: '/cedugenie/getStaffSalaryDetails.html',
		        data: "employeeCode=" +($(this).val()),
		        success: function(data) {
		        	if(data != ""){
		        		$(".salaryBreakUpFormula").each(function(){
		        			$(this).val("");
		        		});
		        		$(".formulaValueClass").each(function(){
		        			$(this).html("");
		        		});
		        		var sm=data.split("*");
			        	var basicSalary = sm[3];
			        	document.getElementById("basicAmountPerStaff").innerHTML = basicSalary;
			        	document.getElementById("warningbox").style.visibility='collapse';
						document.getElementById("warningbox").innerHTML= "";
		        	}
		        	if(data == ""){
		        		$(".salaryBreakUpFormula").each(function(){
		        			$(this).val("");
		        		});
		        		$(".formulaValueClass").each(function(){
		        			$(this).html("");
		        		});
		        		document.getElementById("basicAmountPerStaff").innerHTML = "";
		        		document.getElementById("warningbox").style.visibility='visible';
						document.getElementById("warningbox").innerHTML= "Basic Salary not found for " + employeeCode;
		        	}
		        }
			}); 
		}
	});

});



