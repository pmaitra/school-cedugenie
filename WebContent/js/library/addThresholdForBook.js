window.onload = function(){
	var checkbox = getElementsByClassName("listShowHide");
	for(var i=0;i<checkbox.length;i++){
		ShowHideField(checkbox[i].value, 'thres', checkbox[i]);
	}
};
function ShowAll(cb){
	if(cb.checked){
		var checkbox=getElementsByClassName("listShowHide");
		for(var i=0;i<checkbox.length;i++){
			checkbox[i].checked=true;
			ShowHideField(checkbox[i].value, 'thres', checkbox[i]);
		}
	}
	else{
		var checkbox=getElementsByClassName("listShowHide");
		for(var i=0;i<checkbox.length;i++){
			checkbox[i].checked=false;
			ShowHideField(checkbox[i].value, 'thres', checkbox[i]);
		}
	}
	
}

function onSearchingProducts(){
	if(validateSearch('query','data','warningbox','warningmsg')){
		document.addThresholdDetails.method="POST";
		document.addThresholdDetails.action="searchBookThreshold.html";
		document.addThresholdDetails.submit();             // Submit the page
		return true;
	}
	else{
		document.getElementById("infomsgbox").style.visibility='collapse';
		return false;
	}

}

function onCheckBoxSubmit(){
	//document.getElementById("infomsgbox").style.visibility="collapse";
	if(!ValidateThreshold())
		return false;
		document.addThresholdDetails.method="POST";
		document.addThresholdDetails.action="updateThresholdForBook.html";
		document.addThresholdDetails.submit();            // Submit the page
	return true;
}

function edit(){	
	var edit = document.getElementsByTagName("input");
	for(var i=0;i<edit.length;i++){
		if(edit[i].type=="text")
		edit[i].removeAttribute("readonly");
	}
	document.getElementById("submitButton").removeAttribute("disabled");
	var check = document.getElementsByName("itemThresholdValue");
	for(var i=0;i<check.length;i++)
		check[i].removeAttribute("disabled");
	document.getElementById("infomsgbox").style.visibility="visible";
	document.getElementById("infomsg").innerHTML="Fields Are Now Editable";
}
var retVal = null;
function ValidateThreshold()
{
	var itemId=document.getElementsByName("itemThresholdValue");
	
	var count=0;
	for(var i=0;i<itemId.length;i++){		
		if (itemId[i].checked){
			count=1;
		}
	}
	if(count==0){
//		document.getElementById("warningbox").style.visibility="visible";
//		document.getElementById("warningmsg").innerHTML="Please Select Atleast One Book";
		alert("Please Select Atleast One Book");
		return false;
	}else{
		for(var i=0;i<itemId.length;i++){		
			if (itemId[i].checked){
				var textid=itemId[i].id.replace("ch", "tx"); 
				var textVal =document.getElementById(textid).value;
				if(textVal == ""){
//					document.getElementById("warningbox").style.visibility="visible";
//					document.getElementById("warningmsg").innerHTML="Please Enter Threshold Value";
					alert("Please Enter Threshold Value.");
					return false;
				}
				
				if(isNaN(textVal)){
//					document.getElementById("warningbox").style.visibility="visible";
//					document.getElementById("warningmsg").innerHTML="Please Enter Numeric Value";
					alert("Please Enter Numeric Value.");
					return false;
				}
				textVal = parseInt(textVal);
				if(textVal<0){
//					document.getElementById("warningbox").style.visibility="visible";
//					document.getElementById("warningmsg").innerHTML="Please Enter Positive Value";
					alert("Please Enter Positive Value.");
					return false;
				}
			}
		}
	}
	return true;
}