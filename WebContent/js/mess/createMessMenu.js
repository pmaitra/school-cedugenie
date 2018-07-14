$(document).ready(function() {
    $("#startDate").datepicker({
    	minDate: 0,
    	showOtherMonths: true,
        selectOtherMonths: true,
        dateFormat: "dd/mm/yy",
        onSelect: function(selectedDate) {
            var date = $(this).datepicker("getDate");
            date.setDate(date.getDate() + 7);
            getNextdate = ('0' + date.getDate()).slice(-2) + '/'
            + ('0' + (date.getMonth()+1)).slice(-2) + '/'
            + date.getFullYear();
            $('#endDate').val(getNextdate);
        }
    });
});
function makeMessMenuDetailsEditable(){		
	document.getElementById("messMenuRoutineName").removeAttribute("readonly");
	document.getElementById("startDate").removeAttribute("readonly");
	document.getElementById("submit").style.display = "block";
	document.getElementById("reset").style.display = "block";
	document.getElementById("edit").style.display = "none";
	var input = document.getElementsByTagName("textarea");
	for(var i=0;i<input.length;i++){
		input[i].removeAttribute("readonly");
	}
}
/*function validateMessMenuDetails(){
	var status = true;
	var alphaNumeric = /^[A-Za-z0-9 ]{1,200}$/;
	obj = document.getElementById("messMenuName");
	val = obj.value;
	val = val.replace(/\s{1,}/g,' ');
	val = val.trim();
	obj.value = val;
	if(!val.match(alphaNumeric)){
		document.getElementById("warningbox").style.display='block';
		document.getElementById("warningmsg").innerHTML="Invalid Name";
		status = false;
	}

	obj=document.getElementById("messMenuStartDate");
	val=obj.value;
	if(val==""){
		document.getElementById("warningbox").style.display='block';
		document.getElementById("warningmsg").innerHTML="Select Start Date";
		status = false;
	}
	
	var textAreas = document.getElementsByTagName("textarea");	
	for ( var i = 0; i < textAreas.length; i++) {
		if(textAreas[i].value == ""){
			document.getElementById("warningbox").style.display='block';
			document.getElementById("warningmsg").innerHTML="Assign food To All Boxes";
			status = false;
		}
	}
	return status;	
	}*/