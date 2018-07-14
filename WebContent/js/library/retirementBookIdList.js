window.onload = function(){
	var checkbox = getElementsByClassName("listShowHide");
	for(var i=0;i<checkbox.length;i++){
		ShowHideField(checkbox[i].value, 'rbidl', checkbox[i]);
	}
};

$(document).ready(function(){
	$("#Retire").click(function(){
});

	
	
	
});


function getLoggedData(){
	var bookCode= document.getElementById("bookCode").value;
	 $.ajax({
		    url: '/sms/getRetiredBookLogDetails.html',
		    dataType: 'json',
		    data: "bookCode="+bookCode,
		    success: function(data) {
		    	if(data!=''){
		    		createTable(data);
			    }
		    }
	 });
}



function validateCheck(){
//	document.getElementById("warningmsg").innerHTML = "";
	var countCheckd=0;
	$(".bookId").each(function(){	
		if ($(this).is(':checked')){			
			countCheckd=1;
		}
	});
	if(countCheckd  == 0){
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningmsg").innerHTML = "Please check One Book";
		alert("Please Select An Option.");
		return false;
	}
	
	var countWrongInput=0;
	ran = /^\d+.?\d*$/;
	$(".textfield").each(function(){
	var price=$(this).val().replace(/\s{1,}/g,'');
		if(price!=''){
			 if (!price.match(ran)) {
				 countWrongInput=1;
			}
		}
	});
	if(countWrongInput  != 0){
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningmsg").innerHTML = "Please Enter Numeric Positive Value";
		alert("Please enter a numeric value.");
		return false;
	}
return true;
}