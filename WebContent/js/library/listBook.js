
window.onload = function(){
	var checkbox = getElementsByClassName("listShowHide");
	for(var i=0;i<checkbox.length;i++){
		ShowHideField(checkbox[i].value, 'subMar', checkbox[i]);
	}
};

/*function ShowAll(cb){
	if(cb.checked){
		var checkbox=getElementsByClassName("listShowHide");
		for(var i=0;i<checkbox.length;i++){
			checkbox[i].checked=true;
			ShowHideField(checkbox[i].value, 'subMar', checkbox[i]);
		}
	}
	else{
		var checkbox=getElementsByClassName("listShowHide");
		for(var i=0;i<checkbox.length;i++){
			checkbox[i].checked=false;
			ShowHideField(checkbox[i].value, 'subMar', checkbox[i]);
		}
	}
	
}*/


function valradio() {
	if($('input[name=bookCode]:checked').length<=0){
	 alert("Please Select An Option");
	 return false;
	}
}

/* added by sourav.bhadra on 12-02-2018 */
function categoryWiseList(val){
	if(val=="BOOK_CATEGORY_1"){
		$("#bookPanel").css ("display","block");
		$("#magazinePanel").css ("display","none");
		
		
	}
	if(val=="BOOK_CATEGORY_2"){
		$("#magazinePanel").css ("display","block");
		$("#bookPanel").css ("display","none");
	}
}

/* added by sourav.bhadra on 15-02-2018 */
function submitMagazineEditForm(code,category,name,period,price,publisher,entryDate,receiveDate,billNo,billDate){
	//alert("LB.js :: LN49 :: \n"+code+"\n"+category+"\n"+name+"\n"+period+"\n"+price+"\n"+publisher+"\n"+entryDate+"\n"+receiveDate+"\n"+billNo+"\n"+billDate);
	document.getElementById("magazineCategory").value = category;
	document.getElementById("magazineName").value = name;
	document.getElementById("magazinePeriod").value = period;
	document.getElementById("magazineCost").value = price;
	document.getElementById("magazinePublisher.magazinePublisherName").value = publisher;
	document.getElementById("magazineEntryDate").value = entryDate;
	document.getElementById("magazineReceiveDate").value = receiveDate;
	document.getElementById("magazineBillNo").value = billNo;
	document.getElementById("magazineBillDate").value = billDate;
	
	$('#editMagazineForm').submit();
}

/* added by sourav.bhadra on 16-02-2018 */
function submitBookEditForm(category, code, name, accessionNo, place, year, pages, volume, source, billNo, billDate, cost, classNo, bookNo, dateofEntry, publisher, withdrawalNo, withdrawalDate, remarks){
	//alert("LB.js :: LN67 :: \n"+category+"\n"+code+"\n"+name+"\n"+accessionNo+"\n"+place+"\n"+year+"\n"+pages+"\n"+volume+"\n"+source+"\n"+billNo+"\n"+billDate+"\n"+cost+"\n"+classNo+"\n"+bookNo+"\n"+dateofEntry+"\n"+publisher+"\n"+withdrawalNo+"\n"+withdrawalDate+"\n"+remarks);
	document.getElementById("bookType").value = category;
	document.getElementById("bookCode").value = code;
	document.getElementById("bookName").value = name;
	document.getElementById("accessionNumber").value = accessionNo;
	document.getElementById("bookPlace").value = place;
	document.getElementById("publishingYear").value = year;
	document.getElementById("pages").value = pages;
	document.getElementById("volume").value = volume;
	document.getElementById("source").value = source;
	document.getElementById("billNo").value = billNo;
	document.getElementById("billDate").value = billDate;
	document.getElementById("cost").value = cost;
	document.getElementById("classNo").value = classNo;
	document.getElementById("bookNo").value = bookNo;
	document.getElementById("bookEntryDate").value = dateofEntry;
	document.getElementById("bookPublisher.bookPublisherName").value = publisher;
	document.getElementById("withdrawalNo").value = withdrawalNo;
	document.getElementById("withdrawalDate").value = withdrawalDate;
	document.getElementById("remarks").value = remarks;
	
	$('#editBookForm').submit();
}