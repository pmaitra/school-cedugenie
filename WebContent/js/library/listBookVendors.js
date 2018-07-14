window.onload = function(){
	var checkbox = getElementsByClassName("listShowHide");
	for(var i=0;i<checkbox.length;i++){
		ShowHideField(checkbox[i].value, 'lbv', checkbox[i]);
	}
};


function onSearchingProducts(){
	if(validateSearch('query','data','warningbox','warningmsg')){
		document.viewVendorListDetails.method="POST";
		document.viewVendorListDetails.action="searchBookVendorList.html";
		document.viewVendorListDetails.submit();             // Submit the page
	  	return true;
	}
	else{
		return false;
	}
}  

function editAction(){
	if(valradio("vendorCode","warningbox","warningmsg")){
		document.viewVendorListDetails.method="POST";
		document.viewVendorListDetails.action="editBookVendor.html";
		document.viewVendorListDetails.submit();             // Submit the page
	  	return true;
	}
	else
		return false;
}
