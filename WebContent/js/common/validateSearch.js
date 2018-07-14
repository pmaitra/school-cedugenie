function validateSearch(query,data,warningbox,warningmsg){
	
	if(document.getElementById(query).value.replace(/\s{1,}/g,'').length ==0){
		document.getElementById(warningbox).style.visibility='visible';
		document.getElementById(warningmsg).innerHTML="Please Provide Search Criteria";
		 return false;
	}
	
	if(document.getElementById(data).value=="" || document.getElementById(data).value=="Search"){
		document.getElementById(warningbox).style.visibility='visible';
		document.getElementById(warningmsg).innerHTML="Please Provide Search Content";
		return false;
	}
	return true;
}