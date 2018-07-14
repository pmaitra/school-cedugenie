$(document).ready(function(){
	$("#submitForm").click(function(){
		var bookCode = $("#bookCode").val();
		var bookMediumName = $("#bookMediumName").val();
		var bookIsbn = $("#bookIsbn").val();
		var bookLanguageName = $("#bookLanguageName").val();
		
		if(bookCode == ""){
			alert("Please enter a code.");
			return false;
		}
		if(bookMediumName == ""){
			alert("Please select book medium type.");
			return false;
		}
		if(bookIsbn == ""){
			alert("Please enter ISBN number.");
			return false;
		}
		if(bookLanguageName == ""){
			alert("Please select language.");
			return false;
		}		
	});
});