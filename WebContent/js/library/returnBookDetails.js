var bookCode="";
var rating="";
var comment="";
function submitPopupValues() {
	bookCode1=document.getElementById('bookCode1').value;
	comment1=document.getElementById('comment').value;
	rating1=document.getElementById('rating').value;
//	if(bookCode1!="" && rating1!="" && comment1==""){
	if(comment1==""){
	//	 $("#warningbox").css('visibility','visible');
	//	 $("#warningmsg").text('please Enter comment.');
		 alert("Please give a comment.");
		 return false;
	}
//	if(bookCode1!="" && rating1=="" && comment1!=""){
	if(rating1==""){
	//	 $("#warningbox").css('visibility','visible');
	//	 $("#warningmsg").text('please Select Rating.');
		 alert("Please select a rating.");
		 return false;
	}
	if(bookCode=="" && rating=="" && comment==""){
		bookCode=bookCode1;
		rating=rating1;
		comment=comment1;
	}else{
		bookCode = bookCode+"`~`"+bookCode1;
		rating = rating+"`~`"+rating1;
		comment = comment+"`~`"+comment1;
	}
 document.getElementById("strComment").setAttribute("value",comment);
 document.getElementById("strRating").setAttribute("value",rating);
 document.getElementById("strBookCode").setAttribute("value",bookCode); 
 document.getElementById("ratingBox").style.display="none";
 return false;
}
function hidePopup() {
//	 $("#warningmsg").text('');
//	 $("#warningbox").css('visibility','collapse');
	document.getElementById("ratingBox").style.display="none";
	//document.getElementById("login-box").style.display="none";
	return false;
}


window.onload=function(){
	document.getElementById("ratingBox").style.display="none";	
};

//
//function ShowAll(cb){
//	if(cb.checked){
//		var checkbox=getElementsByClassName("listShowHide");
//		for(var i=0;i<checkbox.length;i++){
//			checkbox[i].checked=true;
//			ShowHideField(checkbox[i].value, 'rbdet', checkbox[i]);
//		}
//	}
//	else{
//		var checkbox=getElementsByClassName("listShowHide");
//		for(var i=0;i<checkbox.length;i++){
//			checkbox[i].checked=false;
//			ShowHideField(checkbox[i].value, 'rbdet', checkbox[i]);
//		}
//	}
//}

function showHide(x)
{
	if(document.getElementById("bookCode1").value==x.name){
		
	}
	else{
		document.getElementById("comment").value="";
		document.getElementById("rating").value="NULL";
	}
	document.getElementById("ratingBox").style.display="block";
	//document.getElementById("login-box").style.display="block";
	document.getElementById("bookCode1").value=x.name;
	
}
 var retVal = null;
$(document).ready(function(){
	$("#submitButton").click(function(){
		var itemCodeRadio = $("input:checked" ).length;
		if(itemCodeRadio == 0){
//			$("#warningbox").css('visibility','visible');
//			$("#warningmsg").text('Atleast check one available check box.');
			alert("Atlease check one check box.");
			return false;
			retVal = false;
		}
		
		if(itemCodeRadio != 0){
//			$("#warningbox").css('visibility','collapse');
//			$("#warningmsg").text('');
			return true;
			retVal = true;
		}
		return retVal;
	});
});