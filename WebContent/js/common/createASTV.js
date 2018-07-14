function calculateStock(thisObj){
	var digits =/^[0-9]{1,}$/;
	var marks=thisObj.value;
	marks=marks.replace(/\s{1,}/g,'');
	thisObj.value=marks;
	
	if(thisObj.value==""){
		thisObj.value=0;
	}
	
	document.getElementById("warningbox").style.visibility = 'collapse';
	
	var objId = thisObj.id;
	var obj = objId.substring(0,4);
	var onlyID = objId.slice(-1);
//	alert("Obj => "+obj+"  || ID => "+onlyID);
	
	if(obj == "gBal"){
		var groundBal = document.getElementById("gBal"+onlyID).value;
		if(groundBal != ""){
			if (!groundBal.match(digits)) {
				document.getElementById("warningbox").style.visibility='visible';
				document.getElementById("warningmsg").innerHTML = "Enter numeric Ground Balance";
				document.getElementById("gBal"+onlyID).value = 0;
				return false;
			}
		}
	}
	if(obj == "serv"){
		var serviceBal = document.getElementById("serv"+onlyID).value;
		if(serviceBal != ""){
			if (!serviceBal.match(digits)) {
				document.getElementById("warningbox").style.visibility='visible';
				document.getElementById("warningmsg").innerHTML = "Enter numeric Serviceable quantity";
				document.getElementById("serv"+onlyID).value = 0;
				return false;
			}
		}
	}
	if(obj == "repa"){
		var repairBal = document.getElementById("repa"+onlyID).value;
		if(repairBal != ""){
			if (!repairBal.match(digits)) {
				document.getElementById("warningbox").style.visibility='visible';
				document.getElementById("warningmsg").innerHTML = "Enter numeric Repairable quantity";
				document.getElementById("repa"+onlyID).value = 0;
				return false;
			}
		}
	}
//	if(obj == "gBal"){
//		document.getElementById("defi"+onlyID).value = 0;
//		document.getElementById("surp"+onlyID).value = 0;
//		
//		var ledgerBal = document.getElementById("lBal"+onlyID).value;
//		ledgerBal = parseInt(ledgerBal);
//		
//		var groundBal = document.getElementById("gBal"+onlyID).value;
//		groundBal = parseInt(groundBal);
//		
//		var difference = ledgerBal-groundBal;
//		difference = parseInt(difference);
//		alert(difference);
//		if(difference > 0){
//			document.getElementById("defi"+onlyID).value = difference;
//		}
//		if(difference < 0){
//			document.getElementById("surp"+onlyID).value = Math.abs(difference);
//		}
//	}
}
