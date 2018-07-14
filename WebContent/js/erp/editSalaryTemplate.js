
function validateTemplate(){
	var salaryTemplateName=document.getElementById("salaryTemplateName").value.replace(/\s{1,}/g,'');
	var salaryBreakUpCode = document.getElementsByName("salaryBreakUpCode");
	if(salaryTemplateName=="" || salaryTemplateName.length==0){
		alert("Enter Template Name");
		 return false;
	 }
	 var counter=0;
	 for(var i=0;i<salaryBreakUpCode.length;i++){
		 if(salaryBreakUpCode[i].checked==true){			 
			 counter++;
		 }
	 }
	 if(counter<=0){
		 alert("Select Pay Heads");
		 return false;
	 }
	 return true;
}

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
		  var nextCell = $(this).parent().next().next().attr("id");
		  document.getElementById("hidVal").value = inputId;
		  document.getElementById("hidNextCell").value = nextCell;
		  $("#calculatorDiv").show();
	    //  document.getElementById("warningbox").style.visibility='collapse';
		//  document.getElementById("warningbox").innerHTML="";
		});
	});   
	
	/*$(".salaryBreakUpFormulaAnother").each(function(){
		var flag = 0;
		var formula = $(this).val();
		var nextCellId = $(this).parent().next().attr("id");		
		 var myVar=setInterval(function(){},5000);
		var values = [];
		var amounts = [];
		var operatorArray = [" + "," % "];
		var table = document.getElementById("formulaSetUpTable");
		$(table).find("tr").each(function() {
		    var payHeads = $(this).find("td:first").find("input").val();
		    var payHeadsAmount = $(this).find("td:last").text().trim();
		    amounts.push(payHeadsAmount);
		    if(payHeads != ""){
		    	values.push($(this).find("td:first").find("input").val()+"-"+payHeadsAmount);
		    }
		});
		var res = formula;
		for(var i = 0; i<values.length;i++){
		   var word_to_match = values[i].split("-");
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
	   alert(finalFormula);
		$.ajax({
	        url: '/sms/getCalculatedValueOfGivenFormula.html',
	        data: "finalFormula=" + finalFormula,
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
		
		$(this).focus(function(){
			$("#calculatorDiv").hide();
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningbox").innerHTML="Formula can not be changed.";
		});
	}); */  
	
	$("#closeButton").click(function(){
		 $("#calculatorDiv").hide();
	});
	
	
	/*$("#calButton").click(function(){
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
	        url: '/sms/getCalculatedValueOfGivenFormula.html',
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
		});*/
	
	$(".calbtn").each(function(){
		$(this).click(function(){
			var flag = 0;
			var formula = $(this).parent().prev().find("input").val();
			var prevText = $(this).parent().prev().find("input").attr('id');
			var nextCellId = $(this).parent().next().attr("id");
			//alert(formula+"-"+prevText+"-"+nextCellId);
			var values = [];
			var amounts = [];
			var operatorArray = [" + "," % "];
			var table = document.getElementById("formulaSetUpTable");
			$(table).find("tr").each(function() {
			    var payHeads = $(this).find("td:first").find("input").val();
			    var payHeadsAmount = $(this).find("td:last").text().trim();
			    amounts.push(payHeadsAmount);
			    if(payHeads != ""){
			    	values.push($(this).find("td:first").find("input").val()+"-"+payHeadsAmount);
			    }
			});
			
			var res = formula;
			for(var i = 0; i<values.length;i++){
			   var word_to_match = values[i].split("-");
			   var filter = new RegExp('\\b' + word_to_match[0] + '\\b', 'gi');
			   if(formula.match(filter)) {
				  flag = 1;
			      if(word_to_match[1] != ""){
			    	  res =  res.replace(word_to_match[0],word_to_match[1]);
			    	//  document.getElementById("warningbox").style.visibility='collapse';
					//  document.getElementById("warningbox").innerHTML="";
			    	  	//alert("RES:"+res);
					    document.getElementById("hidFormula").value=res;
			       }
			       else{
			    	   document.getElementById(nextCellId).innerHTML = "";
			    	   document.getElementById("hidFormula").value="";
			    	//   document.getElementById("warningbox").style.visibility='visible';
					//   document.getElementById("warningbox").innerHTML=word_to_match[0]+"is not set yet,so formula cant be solved.";
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
		 //  alert("finalFormula:"+finalFormula);
			$.ajax({
		        url: '/icam/getCalculatedValueOfGivenFormula.html',
		        data: "finalFormula=" + finalFormula,
		        dataType: 'json',
		        success: function(data) {
		        	//alert("DATA:"+data);
		        	if(data != null){
		     			document.getElementById(nextCellId).innerHTML = data;
		        	}//end if for not null
		        	if(data == null){
		 				document.getElementById(nextCellId).innerHTML = "";
		        	}//end if for null
		        }//end of success
			});// end of ajax
		});
		/*$(prevText).focus(function(){
			$("#calculatorDiv").hide();
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningbox").innerHTML="Formula can not be changed.";
		});*/
	});
	
});



