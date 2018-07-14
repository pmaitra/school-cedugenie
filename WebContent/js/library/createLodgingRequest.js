window.onload = function(){
	//var checkbox = getElementsByClassName("listShowHide");
	for(var i=0;i<checkbox.length;i++){
		ShowHideField(checkbox[i].value, 'clr', checkbox[i]);
	}
};


	function onSearchLodgingRequest(){
		if(validateSearch('query','data','warningbox','warningmsg')){
			document.createLodgingRequest.action = "searchLodgingRequest.html";
	   		document.createLodgingRequest.submit();             // Submit the page
	        return true;
		}
		else{
			return false;
		}
     }
	function onSubmitLodgingRequest(){	
		document.createLodgingRequest.method="POST";
		document.createLodgingRequest.action = "submitLodgingRequest.html";
		document.createLodgingRequest.submit();             // back from the page
		return true;
	}
	
	function go(box){
		if(box.checked){
			if(document.getElementById("maxNoOfBooksPerRequest") == 0){
				$("#errorMsgDiv").css ("display","block");
				var message= "Currently you are not allowed to request for a book. Please contact librarian for futher details.";
				document.getElementById("errorMsgDiv").innerHTML = message;
				return false;
			}
			else if(checkMaxBooksRequest()){
				$("#footerDiv").css ("display","block");
				var x=document.getElementById("names");
				var newLI = document.createElement("li");
				newLI.setAttribute("id", box.value);
				newLI.setAttribute("title", "Click To Dis-select");
				newLI.setAttribute("onclick", "come(this.id);");
				var newText = document.createTextNode(box.value);
				newLI.appendChild(newText);
				x.appendChild(newLI);
			}else{
				box.checked=false;
			}
		}else{
			var x=document.getElementById("names");
			li = document.getElementById(box.value),
			x.removeChild(li);
		}
		
	}
	
	function checkMaxBooksRequest(){
		var countCheckd=0;
		$(".checkClass").each(function(){
			if ($(this).is(':checked')){
				countCheckd=countCheckd+1;
			}
    	});
		if(countCheckd  > parseInt(document.getElementById("maxNoOfBooksPerRequest").value)){
			$("#errorMsgDiv").css ("display","block");
			var message= "Not more than "+document.getElementById("maxNoOfBooksPerRequest").value+" books are allowed per request.";
			document.getElementById("errorMsgDiv").innerHTML = message;
			return false;
		}else{
			return true;
		}
	}
	
	function come(d){
		var x=document.getElementById("names");
		li = document.getElementById(d),
		x.removeChild(li);
		
		var inputElems = document.getElementsByTagName("input");
		
		for (var i=0; i<inputElems.length; i++){
			if (inputElems[i].type === "checkbox" && inputElems[i].value === d){
			inputElems[i].checked = false;
			}
		}
	}
	
	var retVal = null;
	$(document).ready(function(){
		$("#submitForm").click(function(){
			var userTextVal = $("#userId").val();
			var countCheckd=0;
			$(".checkClass").each(function(){	
				if ($(this).is(':checked')){			
					countCheckd=1;
				}
	    	});
			if(countCheckd  == 0){
				$("#errorMsgDiv").css ("display","block");
				var message= "Please check one book.";
				document.getElementById("errorMsgDiv").innerHTML = message;
				return false;
			}
			if(userTextVal == ""){
				$("#errorMsgDiv").css ("display","block");
				var message= "Enter the User Id.";
				document.getElementById("errorMsgDiv").innerHTML = message;
				retVal = false;
			}
			if(userTextVal != ""){
				onSubmitLodgingRequest();
			}
			return retVal;
		});
	});