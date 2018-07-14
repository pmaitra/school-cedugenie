function showDiv(classDiv){
	document.getElementById(classDiv).style.display="block";
}

function hideDiv(classDiv){
	document.getElementById(classDiv).style.display="none";
}

function addText(chkbx,w){
	var ww=document.getElementById(w).value;	
	if(chkbx.checked){
		if(ww=="" || ww=='null')
			ww=ww+chkbx.value;
		else
			ww=ww+';'+chkbx.value;
		
		document.getElementById(w).value=ww;
	}
	else{
		var wInLower=ww.toLowerCase();
		var chkbxInLower=chkbx.value.toLowerCase();			
		var i=wInLower.search(chkbxInLower);
		//i=-1 Not Possible since the value will always be present in textbox as check box is checked			
		if(i==0){
			wInLower = wInLower.replace(chkbxInLower,"");
			document.getElementById(w).value=wInLower;			
			var j=wInLower.search(';');
			if(j==0){
			wInLower = wInLower.replace(';',"");
			document.getElementById(w).value=wInLower;
			}
		}
		if(i>0){
			chkbxInLower=";"+chkbxInLower;
			wInLower = wInLower.replace(chkbxInLower,"");
			document.getElementById(w).value=wInLower;
		}
	}
};