	var p = 2;
	var i = 1;
	function addrows(){
        var table = document.getElementById("payBandDetails");        
        var rowCount = table.rows.length;       
        var row = table.insertRow(rowCount-1);        
        var cell = row.insertCell(0);		
        var element = document.createElement("input");
        element.type = "text";		        	        	
        element.value = "0";
        element.setAttribute("style","text-align: right;");
        element.setAttribute("onfocus","this.value='';");
        element.setAttribute("onblur","setZero(this);");
//        element.setAttribute("onblur","calculateTotal(this);");	
        element.className = "textfield1";
        element.name = "appointmentToPostsWithGradePay";
        element.id = "appointmentToPostsWithGradePay"+i;
        cell.appendChild(element);
        
        var cell = row.insertCell(1);		
        var element = document.createElement("input");
        element.type = "text";		        	        	
        element.value = "0";
        element.setAttribute("style","text-align: right;");
        element.setAttribute("onfocus","this.value='';");
        element.setAttribute("onblur","setZero(this);");	
//        element.setAttribute("onblur","calculateTotal(this);");	
        element.className = "textfield1";
        element.name = "payInThePayBand";
        element.id = "payInThePayBand"+i;
        cell.appendChild(element);
        
        var cell = row.insertCell(2);		
        var element = document.createElement("input");
        element.type = "text";		        	        	
        element.value = "0";
        element.setAttribute("style","text-align: right;");
        element.setAttribute("onfocus","this.value='';");
        element.setAttribute("onblur","setZero(this);");       
        element.className = "textfield1";
        element.name = "totalAmount";
        element.id = "totalAmount"+i;
        cell.appendChild(element);
        
        var cell = row.insertCell(3);
        element = document.createElement("img");
        element.setAttribute("src","/icam/images/minus_icon.png");
        element.setAttribute("onclick","deleteRow(this);");
        cell.appendChild(element);
        
        i++;     
	};
	
	
	function setZero(tBox){
		document.getElementById('warningbox').style.visibility = "collapse";
		if(tBox.value==""){
			tBox.value="0";
		}		
		if(isNaN(tBox.value)){
			document.getElementById('warningbox').style.visibility="visible";
			document.getElementById('warningmsg').innerHTML="Please Enter Proper Numeric Value";
			tBox.value="0";
		}else{
			var p=parseFloat(tBox.value);
			if(p<0){
				document.getElementById('warningbox').style.visibility="visible";
				document.getElementById('warningmsg').innerHTML="Please Enter a positive Number";
				tBox.value="0";
			}
		}		
	}
	
	
	function deleteRow(obj){	
		var table = document.getElementById("payBandDetails");
		var rowCount = table.rows.length;
		
		if(rowCount>3){
			var p = obj.parentNode.parentNode;
			p.parentNode.removeChild(p);			
		}else{
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Atleast One Row Required";
		}
	} 
	
	function validateFixationOfPay(){
		var intRegx=/^[0-9]{1,}$/;
		var doubleRegx=/^[0-9]{1,}.[0-9]{1,}$/;
		var allowDot = /^[A-Za-z0-9_.-]{1,}$/;
		
		obj=document.getElementById("salaryTemplateCode");
		val=obj.value;
		if(val==""){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Select Template Name";
			return false;
		}		
		
		obj=document.getElementById("fixationOfPayName");
		val=obj.value;		
		val=val.replace(/\s{1,}/g,' ');
		val=val.trim();
		obj.value=val;
		if(!val.match(allowDot)){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Invalid Pay Band Name";
			return false;
		}	
		
		obj=document.getElementById("fixationOfPayStartRange");
		val=obj.value;
		val=val.replace(/\s{1,}/g,'');
		obj.value=val;
		if((!val.match(intRegx)) && (!val.match(doubleRegx))){
	    	document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Invalid Start Range";
			return false;
	    }
		
		obj=document.getElementById("fixationOfPayEndRange");
		val=obj.value;
		val=val.replace(/\s{1,}/g,'');
		obj.value=val;
		if((!val.match(intRegx)) && (!val.match(doubleRegx))){
	    	document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Invalid End Range";
			return false;
	    }		
	}
	
	function calculateTotal(tBox){		
		var firstTBoxValue = tBox.id;
		alert(firstTBoxValue);
//		var secondTBoxValue = $(tBox).parent().next().find('input:text').val();   	
//		var add = firstTBoxValue + secondTBoxValue;		
//		var thirdTBoxValue = $(tBox).parent().next().next().find('input:text').val();
//		alert(':thirdTBoxValue:'+thirdTBoxValue);
//		$(tBox).parent().next().next().find('input:text').val(add);

				
	}
		
