function resourceDetails(val){

	if(val=="BOOK_CATEGORY_1"){
		$("#bookDiv").css ('display','block');
		$("#footerDiv").css ("display","block");
		$("#magazineDiv").css ('display','none');
	}
	if(val=="BOOK_CATEGORY_2"){
		$("#bookDiv").css ('display','none');
		$("#magazineDiv").css ('display','block');
		$("#footerDiv").css ("display","block");
	}
}


function getPublisherName(){
	$("#bookPublisherName").autocomplete({
		minLength: 0,
		source: '/cedugenie/getPublisherName.html',
	});
	$("#magazinePublisherName").autocomplete({
		minLength: 0,
		source: '/cedugenie/getPublisherName.html',
	});
};

function validateForm(){
	/**Modified by Saif.Ali
	 * Date- 07/02/2018
	 */
	var categoryName= $("#bookCategoryCode").val();
	
	if(categoryName=="BOOK_CATEGORY_1")
		{
			var decimal=  /^[+]?[0-9]+(\.[0-9]+)?$/; 
			var checkInteger= /^[0-9]+$/;
			var checkCharar= /^[a-zA-Z]*$/;
			var checkCracterSpace= /^[a-zA-Z\s]*$/;
			var checkYear=  /^\d{4}$/;
			var checkBillNumber = /^[a-zA-Z0-9/\-]*$/;
			
			var bookEntryDate= $("#bookEntryDate").val();
			var year = $("#publishingYear").val();
			var cost= $("#cost").val();
			var pages= $("#pages").val();
			var classNumber = $("#classNo").val();
			var author= $("#author").val();
			var bookNumber= $("#bookNo").val();
			var bookTitle= $("#bookName").val();
			var source= $("#source").val();
			var withdrawalNumber= $("#withdrawalNo").val();
			var place = $("#bookPlace").val();
			var billNumber= $("#billNo").val();
			var withdrawalDate = $("#withdrawalDate").val();
			var publisher = $("#bookPublisherName").val();
			var billDate = $("#billDate").val();
			var remarks= $("#remarks").val();
			var current_year= new Date().getFullYear();
			
			if(author!="") {
				if(!(author.match(checkCracterSpace))) { 
					$("#alertDiv").css ("display","block");
					var message= "Please provide only character value in Author";
					document.getElementById("alertDiv").innerHTML = message;
					return false;
				}
				else {
					$("#alertDiv").css ("display","none");
				}
			}
			
			if(place!="") {
				if(!(place.match(checkCracterSpace))) { 
					$("#alertDiv").css ("display","block");
					var message= "Please provide only character value in place";
					document.getElementById("alertDiv").innerHTML = message;
					return false;
				}
				else {
					$("#alertDiv").css ("display","none");
					//return true;
				}
			}
			
			if(publisher!="") {
				if(!(publisher.match(checkCracterSpace))) { 
					$("#alertDiv").css ("display","block");
					var message= "Please provide only character value in publisher";
					document.getElementById("alertDiv").innerHTML = message;
					return false;
				}
				else {
					$("#alertDiv").css ("display","none");
				}
			}
			
			if(year.toString()!="") {
				if(!(year.match(checkYear))) {
					$("#alertDiv").css ("display","block");
					var message= "Please give a proper 4 digit year!!";
					document.getElementById("alertDiv").innerHTML = message;
					return false;
				}
				else {
					$("#alertDiv").css ("display","none");
				}
				if(year>current_year) {	
					$("#alertDiv").css ("display","block");
					var message= "year cannot be greater than the current year!!";
					document.getElementById("alertDiv").innerHTML = message;
					return false;
				}
				else {
					$("#alertDiv").css ("display","none");
				}
			}
			
			if(pages.toString()!= "") {
				if(!(pages.match(checkInteger))) { 
					$("#alertDiv").css ("display","block");
					var message= "Please provide only integer value in the page";
					document.getElementById("alertDiv").innerHTML = message;
					return false;
				}
				else {
					$("#alertDiv").css ("display","none");
				}
			}
		
			if(source != "") {
				if(!(source.match(checkCracterSpace))) { 
					$("#alertDiv").css ("display","block");
					var message= "Please provide only character value in source";
					document.getElementById("alertDiv").innerHTML = message;
					return false;
				}
				else {
					$("#alertDiv").css ("display","none");
				}
			}
			if(classNumber.toString()!=""){
				if(!(classNumber.match(checkInteger))) { 
					$("#alertDiv").css ("display","block");
					var message= "Please provide only integer value in the Class Number";
					document.getElementById("alertDiv").innerHTML = message;
					return false;
				}
				else{
					$("#alertDiv").css ("display","none");
				}
			}

			if(bookNumber.toString()!=""){
				if(!(bookNumber.match(checkInteger))) { 
					$("#alertDiv").css ("display","block");
					var message= "Please provide only integer value in the Book Number";
					document.getElementById("alertDiv").innerHTML = message;
					return false;
				}
				else { 
					$("#alertDiv").css ("display","none");
				}
			}

			
			if(remarks!=""){
				if(!(remarks.match(checkCracterSpace))) { 
					$("#alertDiv").css ("display","block");
					var message= "Please provide only character value in remarks";
					document.getElementById("alertDiv").innerHTML = message;
					return false;
				}
				else{
					$("#alertDiv").css ("display","none");
				}
			}
			
		}
	
	if(categoryName=="BOOK_CATEGORY_2"){
			var decimal=  /^[+]?[0-9]+(\.[0-9]+)?$/;  
			var checkInteger= /^[0-9]+$/;
			var checkCharar= /^[a-zA-Z]*$/;
			var checkCracterSpace= /^[a-zA-Z\s]*$/;
			
			var magDateOfEntry = $("#magazineEntryDate").val();
			var magazinePeriod= $("#magazinePeriod").val();
			var magazinetitle= $("#magazineName").val();
			var magazinePublisher = $("#magazinePublisherName").val();
			
			if(magazinetitle!=""){
				if(!(magazinetitle.match(checkCracterSpace))) { 
					$("#alertDiv").css ("display","block");
					var message= "Please provide only character value in Magazine Title";
					document.getElementById("alertDiv").innerHTML = message;
					return false;
				}
				else{
					$("#alertDiv").css ("display","none");
				}
			}
			
			if(magazinePublisher!=""){
				if(!(magazinePublisher.match(checkCracterSpace))) { 
					$("#alertDiv").css ("display","block");
					var message= "Please provide only character value in Magazine Publisher";
					document.getElementById("alertDiv").innerHTML = message;
					return false;
				}
				else{
					$("#alertDiv").css ("display","none");
				}
			}
			
			if(magazinePeriod!=""){
				if(!(magazinePeriod.match(checkInteger))) { 
					$("#alertDiv").css ("display","block");
					var message= "Please provide only integer value in Magazine Period";
					document.getElementById("alertDiv").innerHTML = message;
					return false;
				}
				else{
					$("#alertDiv").css ("display","none");
				}
			}
			
		}
};	