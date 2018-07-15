function resourceDetails(val){
	if(val=="BOOK_CATEGORY_1"){
		$.ajax({
			url:'/cedugenie/getListOfItemsFromCatalogue.html',
			data:"category="+val,
			success:function(data){
				$("#bookName").html(data);
			},error:function () {
	            alert("No data found");
	        }
		});
		$("#bookDiv").css ("display","block");
		$("#footerDiv").css ("display","block");
		$("#magazineDiv").css ("display","none");
	}
	if(val=="BOOK_CATEGORY_2"){
		$.ajax({
			url:'/cedugenie/getListOfItemsFromCatalogue.html',
			data:"category="+val,
			success:function(data){
				$("#magazineName").html(data);
			},error:function () {
	            alert("No data found");
	        }
		});
		$("#bookDiv").css ("display","none");
		$("#magazineDiv").css ("display","block");
		$("#footerDiv").css ("display","block");
	}
}

/*function getBookName(){
	$("#bookName").autocomplete({
		source: '/cedugenie/getBooksInCatalogue.html',
	});
	$("#magazineName").autocomplete({
		minLength:0,
		source: '/cedugenie/getMagazinesInCatalogue.html',
	});
};*/

$("#magazineName").change(function(){
	var magCode = $(this).val();
	$.ajax({
		url:'/cedugenie/getAllDetailsOfItemsFromCatalogue.html',
		data:"magCode=" + magCode+ "&category=" + $("#category").val(),
		success:function(data){
			var magDataSplit = data.split("#");
			$("#magazineEntryDate").attr("readonly", false);
			$("#magazinePublisherName").val(magDataSplit[1]);
			$("#magazinePeriod").val(magDataSplit[2]);
			$("#magazineBillNo").attr("readonly", false);
			$("#magazineBillDate").attr("readonly", false);
			$("#magazineReceiveDate").attr("readonly", false);
			$("#magazineCost").attr("readonly", false);
			$("#withdrawalDate").attr("readonly", false);
			$("#remarks").attr("readonly", false);
		},error:function () {
            alert("No data found");
        }
	});
});

$("#bookName").change(function(){
	var bookCode = $(this).val();
	$.ajax({
		url:'/cedugenie/getAllDetailsOfItemsFromCatalogue.html',
		data:"bookCode=" + bookCode+ "&category=" + $("#category").val(),
		success:function(data){
			var fullDataSplit = data.split("^^");
			var bookDataSplit = fullDataSplit[0].split("#");
			var authorDataSplit = fullDataSplit[1].split("$");
			$("#bookEntryDate").attr("readonly", false);
			var str="";
			for(var i=0; i<authorDataSplit.length; i++){
				str = str + authorDataSplit[i]+",";
			}
			$("#author").val(str.slice(0,-1));
			$("#bookPlace").val(bookDataSplit[1]);
			$("#bookPublisherName").val(bookDataSplit[2]);
			$("#publishingYear").val(bookDataSplit[3]);
			$("#pages").val(bookDataSplit[4]);
			$("#volume").val(bookDataSplit[5]);
			$("#source").val(bookDataSplit[6]);
			$("#billNo").attr("readonly", false);
			$("#billDate").attr("readonly", false);
			$("#cost").attr("readonly", false);
			$("#classNo").val(bookDataSplit[7]);
			$("#bookNo").val(bookDataSplit[7]);
			$("#withdrawalNo").attr("readonly", false);
			$("#withdrawalDate").attr("readonly", false);
			$("#remarks").attr("readonly", false);
		},error:function () {
            alert("No data found");
        }
	});
});

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

function ValidateForm(){
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
			
			if(author!="")
			{
				if(!(author.match(checkCracterSpace))) 
				{ 
					$("#alertDiv").css ("display","block");
					var message= "Please provide only character value in Author";
					document.getElementById("alertDiv").innerHTML = message;
					return false;
				}
				else
				{
					$("#alertDiv").css ("display","none");
				//	return true;
				}
			}
			
			if(place!="")
			{
				if(!(place.match(checkCracterSpace))) 
				{ 
					$("#alertDiv").css ("display","block");
					var message= "Please provide only character value in place";
					document.getElementById("alertDiv").innerHTML = message;
					return false;
				}
				else
				{
					$("#alertDiv").css ("display","none");
					//return true;
				}
			}
			
			if(publisher!="")
			{
				if(!(publisher.match(checkCracterSpace))) 
				{ 
					$("#alertDiv").css ("display","block");
					var message= "Please provide only character value in publisher";
					document.getElementById("alertDiv").innerHTML = message;
					return false;
				}
				else
				{
					$("#alertDiv").css ("display","none");
					//return true;
				}
			}
			
			if(year.toString()!="")
			{
				if(!(year.match(checkYear)))
				{
					$("#alertDiv").css ("display","block");
					var message= "Please give a proper 4 digit year!!";
					document.getElementById("alertDiv").innerHTML = message;
					return false;
				}
				else
				{
					$("#alertDiv").css ("display","none");
					//return false;
				}
				if(year>current_year)
				{	
					$("#alertDiv").css ("display","block");
					var message= "year cannot be greater than the current year!!";
					document.getElementById("alertDiv").innerHTML = message;
					return false;
				}
				else
				{
					$("#alertDiv").css ("display","none");
				}
			}
			
			if(pages.toString()!= "")
			{
				if(!(pages.match(checkInteger))) 
				{ 
					$("#alertDiv").css ("display","block");
					var message= "Please provide only integer value in the page";
					document.getElementById("alertDiv").innerHTML = message;
					return false;
				}
				else
				{
					$("#alertDiv").css ("display","none");
					//return true;
				}
			}
		
			if(source != "")
			{
				if(!(source.match(checkCracterSpace))) 
				{ 
					$("#alertDiv").css ("display","block");
					var message= "Please provide only character value in source";
					document.getElementById("alertDiv").innerHTML = message;
					return false;
				}
				else
				{
					$("#alertDiv").css ("display","none");
					//return true;
				}
			}
			
			if(billNumber.toString()!="")
			{
				if(!(billNumber.match(checkBillNumber))) 
				{ 
					$("#alertDiv").css ("display","block");
					var message= "You can give alphabet number and this /\- only";
					document.getElementById("alertDiv").innerHTML = message;
					return false;
				}
				else
				{
					$("#alertDiv").css ("display","none");
					//return true;
				}
			}
			
			if(cost.toString()!="")
			{
				if(!(cost.match(decimal))) 
				{ 
					$("#alertDiv").css ("display","block");
					var message= "Please provide correct value in cost";
					document.getElementById("alertDiv").innerHTML = message;
					return false;
				}
				else
				{
					$("#alertDiv").css ("display","none");
					//return true;
				}
			}
			
			if(classNumber.toString()!="")
			{
				if(!(classNumber.match(checkInteger))) 
				{ 
					$("#alertDiv").css ("display","block");
					var message= "Please provide only integer value in the Class Number";
					document.getElementById("alertDiv").innerHTML = message;
					return false;
				}
				else
				{
					$("#alertDiv").css ("display","none");
					//return true;
				}
			}

			if(bookNumber.toString()!="")
			{
				if(!(bookNumber.match(checkInteger))) 
				{ 
					$("#alertDiv").css ("display","block");
					var message= "Please provide only integer value in the Book Number";
					document.getElementById("alertDiv").innerHTML = message;
					return false;
				}
				else 
				{ 
					$("#alertDiv").css ("display","none");
					//return true;
				}
			}

			if(withdrawalNumber.toString()!="")
			{
				if(!(withdrawalNumber.match(checkInteger))) 
				{ 
					$("#alertDiv").css ("display","block");
					var message= "Please provide only integer value in the Withdrawal Number";
					document.getElementById("alertDiv").innerHTML = message;
					return false;
				}
				else
				{
					$("#alertDiv").css ("display","none");
				//	return true;
				}
			}
			
			if(remarks!="")
			{
				if(!(remarks.match(checkCracterSpace))) 
				{ 
					$("#alertDiv").css ("display","block");
					var message= "Please provide only character value in remarks";
					document.getElementById("alertDiv").innerHTML = message;
					return false;
				}
				else
				{
					$("#alertDiv").css ("display","none");
				//	return true;
				}
			}
			
		}
	
	if(categoryName=="BOOK_CATEGORY_2")
		{
			var decimal=  /^[+]?[0-9]+(\.[0-9]+)?$/;  
			var checkInteger= /^[0-9]+$/;
			var checkCharar= /^[a-zA-Z]*$/;
			var checkCracterSpace= /^[a-zA-Z\s]*$/;
			var checkBillNumber = /^[a-zA-Z0-9/\-]*$/;
			
			var magDateOfEntry = $("#magazineEntryDate").val();
			var magazinePeriod= $("#magazinePeriod").val();
			var magDateOfReceive = $("#magazineReceiveDate").val();
			var magazinetitle= $("#magazineName").val();
			var magazineBillNumber= $("#magazineBillNo").val();
			var magazineCost= $("#magazineCost").val();
			var magazinePublisher = $("#magazinePublisherName").val();
			var magBillDate = $("#magazineBillDate").val();
			
			if(magazinetitle!="")
			{
				if(!(magazinetitle.match(checkCracterSpace))) 
				{ 
					$("#alertDiv").css ("display","block");
					var message= "Please provide only character value in Magazine Title";
					document.getElementById("alertDiv").innerHTML = message;
					return false;
				}
				else
				{
					$("#alertDiv").css ("display","none");
					//return true;
				}
			}
			
			if(magazinePublisher!="")
			{
				if(!(magazinePublisher.match(checkCracterSpace))) 
				{ 
					$("#alertDiv").css ("display","block");
					var message= "Please provide only character value in Magazine Publisher";
					document.getElementById("alertDiv").innerHTML = message;
					return false;
				}
				else
				{
					$("#alertDiv").css ("display","none");
					//return true;
				}
			}
			
			if(magazinePeriod!="")
			{
				if(!(magazinePeriod.match(checkInteger))) 
				{ 
					$("#alertDiv").css ("display","block");
					var message= "Please provide only integer value in Magazine Period";
					document.getElementById("alertDiv").innerHTML = message;
					return false;
				}
				else
				{
					$("#alertDiv").css ("display","none");
					//return true;
				}
			}
			
			if(magazineBillNumber!="")
			{
				if(!(magazineBillNumber.match(checkBillNumber))) 
				{ 
					$("#alertDiv").css ("display","block");
					var message= "Please provide alphabet integer and /\- in Magazine Bill Number";
					document.getElementById("alertDiv").innerHTML = message;
					return false;
				}
				else
				{
					$("#alertDiv").css ("display","none");
					//return true;
				}
			}
			
			if(magazineCost!="")
			{
				if(!(magazineCost.match(decimal))) 
				{ 
					$("#alertDiv").css ("display","block");
					var message= "Please provide correct value in Magazine Cost";
					document.getElementById("alertDiv").innerHTML = message;
					return false;
				}
				else
				{
					$("#alertDiv").css ("display","none");
					//return true;
				}
			}
		   
		   
		}
};	