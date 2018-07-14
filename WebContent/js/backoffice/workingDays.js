 $(document).ready(function() {
var totalData = "";
$("#bt").click(function(){
	var date1 = document.getElementById('strdate').value; 
	var date2 = document.getElementById('enddate').value; 
	var publicHolidayCount = document.getElementById('countHoliPerTerm').value; 
	publicHolidayCount = parseInt(publicHolidayCount);
//	var hh = document.getElementById('workingDaysFromDB');
	date1 = date1.split("/");
	date2 = date2.split("/");
	var sDate = new Date(date1[1]+"/"+date1[0]+"/"+date1[2]);
	var eDate = new Date(date2[1]+"/"+date2[0]+"/"+date2[2]);
	var daysApart = Math.abs(Math.round((sDate-eDate)/86400000))+1;
	var weekendDays = 0;
	var dayMilliseconds = 1000 * 60 * 60 * 24;
	
	$('.checkbox-custom').each(function(){
				if ($(this).is(':checked')){	
					var checkedVal = $(this).val();
					alert(checkedVal);
					
					while (sDate <= eDate) {
					    var day = sDate.getDay();
					    if (day == checkedVal) {
					        weekendDays++;
					    }  
					     sDate = new Date(+sDate + dayMilliseconds); 
					}
					sDate = new Date(date1[1]+"/"+date1[0]+"/"+date1[2]);	    	
					var checkedDay = $(this).attr('name');
					totalData = checkedDay +"/"+ totalData;
					
				}			
		});
	
	
	$("#checkHoliday").val(totalData);
	sDate = new Date(date1[1]+"/"+date1[0]+"/"+date1[2]);
	
	var pb=document.getElementsByName('inputDateA');
	var len=pb.length;
	for(var k=0;k<len;k++){
		
	var x = document.getElementsByName('inputDateA')[k].value;
	
	} 
	
	if(x != ""){
		/*daysApart = daysApart - len - weekendDays;*/
		daysApart = daysApart - len - weekendDays - publicHolidayCount;
		weekendDays = weekendDays + len;
	}
	if(x == ""){
		len--;
		/*daysApart = daysApart-len-weekendDays;*/
		daysApart = daysApart - len - weekendDays - publicHolidayCount;
		weekendDays = weekendDays + len +publicHolidayCount;
	}
	
	document.getElementById("tworking").value = daysApart; 
	document.getElementById('tholi').value = weekendDays; 
});
});