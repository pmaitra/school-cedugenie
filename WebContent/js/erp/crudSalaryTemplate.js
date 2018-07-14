	function removeDisabled(rd,erb,er){	
		document.getElementById('warningbox').style.visibility = "collapse";
		valradio(rd,erb,er);
		
		document.getElementById("submitt").removeAttribute('disabled');
		var radios = document.getElementsByName("salaryTemplateCode");
		for(var i=0; i<=radios.length; i++){
			if(document.getElementById("radioSalaryTemplate"+i).checked == true){
				document.getElementById("textSalaryTemplateName"+i).removeAttribute('disabled');
				document.getElementById("textSalaryTemplateDesc"+i).removeAttribute('disabled');
				break;
			}
		}
	}
	
	function validateSalaryTemplateForm(){	
		var alphaNumeric=/^[A-Za-z0-9 ]{1,50}$/;
		
				
		obj=document.getElementById("salaryTemplateName");
		val=obj.value;		
		val=val.replace(/\s{1,}/g,' ');
		val=val.trim();
		obj.value=val;
		if(!val.match(alphaNumeric)){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Invalid Salary Template Name";
			return false;
		}			
		
		obj=document.getElementById("salaryTemplateDesc");
		val=obj.value;		
		val=val.replace(/\s{1,}/g,' ');
		val=val.trim();
		obj.value=val;
		if(val!=""){
			if(!val.match(alphaNumeric)){
				document.getElementById("warningbox").style.visibility='visible';
				document.getElementById("warningmsg").innerHTML="Invalid Salary Template Description";
				return false;
			}
		}
		
		
	}
	
	
	
	function validateEditSalaryTemplateForm(){	
	
		var alphaNumeric=/^[A-Za-z0-9 ]{1,50}$/;			
		
		var radios = document.getElementsByName("salaryTemplateCode");
		for(var i=0; i<radios.length; i++){
			if(document.getElementById("radioSalaryTemplate"+i).checked == true){
				obj=document.getElementById("textSalaryTemplateName"+i);
				val=obj.value;		
				val=val.replace(/\s{1,}/g,' ');
				val=val.trim();
				obj.value=val;
				if(!val.match(alphaNumeric)){
					document.getElementById("warningbox").style.visibility='visible';
					document.getElementById("warningmsg").innerHTML="Invalid Salary Template Name";
					return false;
				}
				else{
					document.getElementById("warningbox").style.visibility='collapse';
					document.getElementById("warningmsg").innerHTML="";
				}
				
				obj=document.getElementById("textSalaryTemplateDesc"+i);
				val=obj.value;		
				val=val.replace(/\s{1,}/g,' ');
				val=val.trim();
				obj.value=val;
				if(!val.match(alphaNumeric)){
					document.getElementById("warningbox").style.visibility='visible';
					document.getElementById("warningmsg").innerHTML="Invalid Salary Template Description";
					return false;
				}	
				else{
					document.getElementById("warningbox").style.visibility='collapse';
					document.getElementById("warningmsg").innerHTML="";
				}
			}			
		}
				
	}