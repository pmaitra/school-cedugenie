function checkAllocationStatus(){
	var venueCode = $("#venueCode").val();
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	var status = 'allocated';
	$.ajax({
		url: '/icam/getAlloctedVenue.html',
		dataType: 'json',
		data: { venueCode:  venueCode, startDate: startDate ,endDate: endDate,startTime:startTime,endTime:endTime},
		success: function(data) {
			if(data != 'NOTALLOCATED'){
				
			}else{
				status = "notallocated";
				//document.getElementById('submit').removeAttribute("disabled");
			}
		}
	});
	alert("status==="+status);
	if(status=='allocated'){
		return false;
	}else
			return true;
	
}