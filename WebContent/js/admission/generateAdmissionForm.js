function fprint()
{
	var num=document.getElementById("numberOfprint").value;
	num = num.replace(/\s{1,}/g,'');
	var regPositiveNum = '[-+]?([0-9]*\.[0-9]+|[0-9]+)$';
	
	if(num==""){
		//document.getElementById("warningbox").style.visibility="visible";
	//	document.getElementById("warningmsg").innerHTML="Please Enter a Number";
		alert()
		return false;
	}
	else if(!num.match(regPositiveNum)){
		document.getElementById("warningbox").style.visibility="visible";
		document.getElementById("warningmsg").innerHTML="Please Enter a valid Number";
		return false;
	}	
	else{		
		document.getElementById("warningbox").style.visibility="collapse";
		printForm.submit();
	}
		
	//window.print();
}
