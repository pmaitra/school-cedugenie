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
	
	if(obj == "cbPr"){
		var cbVal = document.getElementById("cbPr"+onlyID).value;
		if(cbVal != ""){
			if (!cbVal.match(digits)) {
				document.getElementById("warningbox").style.visibility='visible';
				document.getElementById("warningmsg").innerHTML = "Enter numeric CB value";
				document.getElementById("cbPr"+onlyID).value = 0;
				return false;
			}
		}
	}
	if(obj == "quns"){
		var unsVal = document.getElementById("quns"+onlyID).value;
		if(unsVal != ""){
			if (!unsVal.match(digits)) {
				document.getElementById("warningbox").style.visibility='visible';
				document.getElementById("warningmsg").innerHTML = "Enter numeric UNS quantity";
				document.getElementById("quns"+onlyID).value = 0;
				return false;
			}
		}
	}
	if(obj == "cbPr"){
		document.getElementById("cost"+onlyID).value = 0.0;
		
		var ledgerBal = document.getElementById("lBal"+onlyID).value;
		ledgerBal = parseInt(ledgerBal);
		
		var cbBal = document.getElementById("cbPr"+onlyID).value;
		cbBal = parseInt(cbBal);
		
		var prevCondemn = document.getElementById("prCo"+onlyID).value;
		prevCondemn = parseInt(prevCondemn);
		
		var difference = ledgerBal-cbBal;
		difference = parseInt(difference);
//		alert(difference);
		if(difference > 0){
			var rateBal = document.getElementById("rate"+onlyID).value;
			rateBal = parseInt(rateBal);
			
			document.getElementById("cost"+onlyID).value = (cbBal + prevCondemn) * rateBal;
		}
		if(difference < 0){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML = "CB quantity can not be more than Total Stock.";
			document.getElementById("cbPr"+onlyID).value = 0;
			return false;
//			document.getElementById("surp"+onlyID).value = Math.abs(difference);
		}
	}
}
