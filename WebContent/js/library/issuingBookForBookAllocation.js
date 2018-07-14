
window.onload = function(){
	var checkbox = getElementsByClassName("listShowHide");
	for(var i=0;i<checkbox.length;i++){
		ShowHideField(checkbox[i].value, 'rdet', checkbox[i]);
	}
	$('.returnDate').Zebra_DatePicker();		
	$('.returnDate').Zebra_DatePicker({
 	  format: 'd/m/Y',
 	  direction:true
	});	
	
};





function checkThreshold(val){    
	 var avlCopies =$(val).parent().next().find('input').val();
	 //alert(avlCopies);
	 //var threshold =$(val).parent().next().next().find('input').val();
	 //alert(threshold);
	 if(avlCopies<1){
		 val.value="NULL";
//		 $("#infomsgbox").css('visibility','visible');
//		 $("#infomsg").text('Available copies is under threshold value, So you can not allocate this Book.');
		 alert("No copies available for this book.");
	 }
} 
 var retVal = null;
$(document).ready(function(){
	$("#submitButton").click(function(){
//		document.getElementById("warningmsg").innerHTML = "";
//		document.getElementById("warningbox").style.visibility='collapse';
		
		var selectVal = document.getElementById("allocatedBookId").value.replace(/\s{1,}/g,'').length;
		if(selectVal == 0){
//			$("#warningbox").css('visibility','visible');
//			$("#warningmsg").text('Select the available book id.');
			alert("Please select the available book id.");
			return false;
		}
		
		var countWrongDateInput=0;
		$("#allocatedBookId").each(function(){
			var bookId=$(this).val().replace(/\s{1,}/g,'');
			if(bookId!=''){
				if(document.getElementById("returnDate"+countWrongDateInput).value.replace(/\s{1,}/g,'').length==0){
					countWrongDateInput=countWrongDateInput+1;
				}
			}
		});
		if(countWrongDateInput  != 0){
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML = "Please Select Expected Return Date";
			alert("Please select expected return date.");
			return false;
		}
		
	});
});