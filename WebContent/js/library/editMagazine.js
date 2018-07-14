function activeForm(){
	document.getElementById("magazineName").removeAttribute('readonly');
	document.getElementById("magazinePeriod").removeAttribute('readonly');
	document.getElementById("magazineCost").removeAttribute('readonly');
	document.getElementById("magazineEntryDate").removeAttribute('readonly');
	document.getElementById("magazineReceiveDate").removeAttribute('readonly');	
	document.getElementById("submitForm").removeAttribute('disabled');
	document.getElementById("magazinePublisher.magazinePublisherName").removeAttribute('readonly');
	document.getElementById("magazineBillNo").removeAttribute('readonly');
	document.getElementById("magazineBillDate").removeAttribute('readonly');
	
	
	document.getElementById("infomsgbox").style.visibility="visible";
	document.getElementById("infomsg").innerHTML="Fields Are Now Editable";
}

function getPublisherName(thisMagazineName){	
	$(thisBookName).autocomplete({
		source: '/sms/getPublisherName.html'
	});		
};

function validateEditForm(){
	return true;
}