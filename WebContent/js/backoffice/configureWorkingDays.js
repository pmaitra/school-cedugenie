function showdivone(){
	var count = 0;
	/* modified by sourav.bhadra on 21-09-2017 */
	$("input[name='inputDateA']").each(function(){
		var data = $(this).val();
		if(data != ''){
			count++;
		}
		
	});
	document.getElementById("tholi").value=count;
	document.getElementById("showdivone").style.display="block";
	document.getElementById("submitFooter").style.display="block";
}
 function validateDate(){
	 	var date1 = document.getElementById('strdate').value; 
		var date2 = document.getElementById('enddate').value; 
		date1 = date1.split("/");
		date2 = date2.split("/");
		var sDate = new Date(date1[1]+"/"+date1[0]+"/"+date1[2]);
		var eDate = new Date(date2[1]+"/"+date2[0]+"/"+date2[2]);
		var pubholi = document.getElementsByName('inputDateA'); 
		 for(var i=0;i<pubholi.length;i++){
			 var holivalue=pubholi[i].value;
			 holivalue = holivalue.split("-");
			 var holivalueDate = new Date(holivalue[1]+"/"+holivalue[0]+"/"+holivalue[2]);
			 if(holivalueDate < sDate || holivalueDate > eDate){
				 alert("select date within start date and end date");
			 }
			 else{
//				 document.getElementById("warningbox").style.visibility='collapse';
//				 document.getElementById("warningmsg").innerHTML="";
			 }
			 
		 }
 }
 
 
 $(document).ready(function() {
	 $('#submitbutton').click(function(){
			var onlyClass = $("#academicYear").val();
			var onlyYear = $("#month").val();
			var rowCount = $('#showdiv tr').length;
			if(rowCount<=1){
				alert("No Record to submit");
				return false;
			}
			if(onlyClass==""){
				//$("#warningbox").css('visibility','visible');
				//$("#warningmsg").text("Please Select Course");
				alert("Please select course");
				 return false;
			} 
			if(onlyYear==""){
				//$("#warningbox").css('visibility','visible');
				//$("#warningmsg").text("Select Academic Stream");
				alert("Select Academic Year");
				return false;
			 }
			var checkDays = "";
			$('input[type="checkbox"]:checked').each(function(){
				checkDays += $(this).val()+";";
			});
			document.getElementById("checkHoliday").value=checkDays;
		});
	
	 $("#year").change(function(){
		var selectedYear = $(this).val();
		var year = document.getElementById("academicYear").value;
		var yearArr = year.split("-");
		var option = "<option value='0'>Select..</option>";
		if(selectedYear==yearArr[0]){
			option += "<option value='04'>April</option>"+
                    "<option value='05'>May</option>"+
                    "<option value='06'>June</option>"+
                    "<option value='07'>July</option>"+
                    "<option value='08'>August</option>"+
                    "<option value='09'>September</option>"+
                    "<option value='10'>October</option>"+
                    "<option value='11'>November</option>"+
                    "<option value='12'>December</option>";
			document.getElementById("month").innerHTML=option;
		}else if(selectedYear==yearArr[1]){
			option += "<option value='01'>January</option>"+
		            "<option value='02'>February</option>"+
		            "<option value='03'>March</option>";
			document.getElementById("month").innerHTML=option;
		}
	});
	
	 $("#month").change(function(){
		$('#showdiv').css('visibility','visible');
		var innerHeight2=document.body.scrollHeight;
    	var frame=window.parent.document.getElementById("frame");	    	
    	frame.style.height = innerHeight2+100+ 'px';
	});
});
 