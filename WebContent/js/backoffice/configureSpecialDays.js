function showdiv(){
	document.getElementById("one").style.display = "block";	
	 var innerHeight2=document.body.scrollHeight;
	 var frame=window.parent.document.getElementById("frame");	    	
	 frame.style.height = innerHeight2+100+ 'px';
	document.getElementById("two").style.display = "none";	
	
}
function showdivone(){
	document.getElementById("two").style.display = "block";	
	var innerHeight2=document.body.scrollHeight;
	 var frame=window.parent.document.getElementById("frame");	    	
	 frame.style.height = innerHeight2+100+ 'px';
	document.getElementById("one").style.display = "none";	
}

var y=1;
function validate(){
	var date1 = document.getElementById('strdate').value; 
	var date2 = document.getElementById('enddate').value; 
	var date10 =document.getElementById('inputDate').value;
	if(date1 == "" || date2 == ""){
		document.getElementById("warningbox").style.visibility = "visible";
	  	document.getElementById("warningmsg").innerHTML= "Enter start and end date both.";
	}
	var tworking = document.getElementById('tworking');
	date1 = date1.split("/");
	date2 = date2.split("/");
	date10 = date10.split("/");
	var sDate = new Date(date1[1]+"/"+date1[0]+"/"+date1[2]);
	var eDate = new Date(date2[1]+"/"+date2[0]+"/"+date2[2]);
	var sDate1 = new Date(date10[1]+"/"+date10[0]+"/"+date10[2]);
	var curr_date13= sDate1.getDate();
    var curr_month13 = sDate1.getMonth() + 1; //Months are zero based
    var curr_year13 = sDate1.getFullYear();
    var datespecific13=null;
    if(curr_month13>9||curr_date13>9){
    	datespecific13 = curr_date13 + "/" + curr_month13 + "/" + curr_year13;
  	}
    else
    	datespecific13 = "0" + curr_date13 + "/" + "0" + curr_month13 + "/" + curr_year13;
    document.getElementById('datesone').value = datespecific13;
	document.getElementById('dates').value = datespecific1;
	var curr_date1 = sDate.getDate();
    var curr_month1 = sDate.getMonth() + 1; //Months are zero based
    var curr_year1 = sDate.getFullYear();
    var datespecific1=null;
    if(curr_month1>9||curr_date1>9){
    	datespecific1 = curr_date1 + "/" + curr_month1 + "/" + curr_year1;
  	}
    else
    	datespecific1 = "0" + curr_date1 + "/" + "0" + curr_month1 + "/" + curr_year1;
		document.getElementById('dates').value = datespecific1;
	
	
	 if(eDate<sDate){
		document.getElementById("warningbox").style.visibility = "visible";
		document.getElementById("warningmsg").innerHTML= "Select valid start and end date.";
		document.getElementById('tworking').value ="";
	} 
	 
	 if(eDate>sDate){
		 document.getElementById("warningbox").style.visibility = "collapse";
		 document.getElementById("warningmsg").innerHTML= "";
			
		} 
	
	 //dates between two dates...
	 var dayMilliseconds1 = 1000 * 60 * 60 * 24;
	////////////////////////////////////////////////////////////
	sDate = new Date(date1[1]+"/"+date1[0]+"/"+date1[2]);
	eDate = new Date(date2[1]+"/"+date2[0]+"/"+date2[2]);
	var daysApart = Math.abs(Math.round((sDate-eDate)/86400000))+1;
	for(var f = 1;f<daysApart;f++){ 
		sDate = new Date(+sDate + dayMilliseconds1); 
		var curr_date = sDate.getDate();
	    var curr_month = sDate.getMonth() + 1; //Months are zero based
	    var curr_year = sDate.getFullYear();
	    var datespecific=null;
	    if(curr_month>9||curr_date>9){
	    	datespecific = curr_date + "/" + curr_month + "/" + curr_year;
	  	}
	    else
	    	datespecific = "0" + curr_date + "/" + "0" + curr_month + "/" + curr_year;
	 var table = document.getElementById("data"); 
	    var rowCount = table.rows.length;
	    var row = table.insertRow(rowCount);
	    
	    var dates="dates";
	    var indates= dates + y; 
	
		var cell2 = row.insertCell(0);		
	    var element2 = document.createElement("input");
	    element2.setAttribute('readonly','readonly');
	    element2.type = "hidden";
	    element2.name="dates";
	    element2.value=datespecific;
		cell2.appendChild(element2);
	    
	    var serialNo11=document.getElementsByName("dates");
	    var id;
	    for(var i=0;i<serialNo11.length;i++)
		{ id="dates"+(i+1);
	    	serialNo11[i].setAttribute("id",id);
		}
	
	    y++;
 } 
	 }


$(document).ready(function() {
	
//	$('#strdate').Zebra_DatePicker();
//	$('#enddate').Zebra_DatePicker();
//	$('#inputDate').Zebra_DatePicker();
//	$('#cwone').Zebra_DatePicker();
//	$('#inputDate1').Zebra_DatePicker();
//	
//	$('#strdate').Zebra_DatePicker({
// 	  format: 'd/m/Y'
//	});
//	$('#enddate').Zebra_DatePicker({
//	  format: 'd/m/Y'
//	});
//	$('#inputDate').Zebra_DatePicker({
//	 	  format: 'd/m/Y'
//		});
//	$('#cwone').Zebra_DatePicker({
//	  format: 'd/m/Y'
//	});
//	$('#inputDate1').Zebra_DatePicker({
//		  format: 'd/m/Y'
//		});
//	
	$("#courseId").change(function(){
		$.ajax({
	        url: '/icam/getTermForCourse.html',
	        data: "course=" + ($(this).val()),
	        dataType: 'json',
	        success: function(data) {
	        	if(data == null){
	        		document.getElementById("warningbox").style.visibility = 'visible';
					document.getElementById("warningmsg").innerHTML="There is no term For this course.";
	        		var streamid=document.getElementById("sterm");
	        		for(var i=streamid.length;i>0;i--)
	        		{
	        			streamid.remove(i);
	        		} 
	        		
	        	}
	        	
	        	if(data != null){
	        		document.getElementById("warningbox").style.visibility = 'collapse';
					document.getElementById("warningmsg").innerHTML="";
	        		
	        		var arrCourse = data.split("#");
	        		var courseid=document.getElementById("sterm");
	        		for(var i=courseid.length;i>0;i--)
	        		{
	        			courseid.remove(i);
	        		} 
	        		for(var courselist=0;courselist<=arrCourse.length;courselist++)
	    			{
	        			var individualArrCourse = arrCourse[courselist].split("^");
	        			courseid.add(new Option(individualArrCourse[1], individualArrCourse[0],null));
	        			
	    			}
	        		courseid.remove(courselist);
	        	}
	        } 
	}); 
	});
	
	
	$("#sterm").change(function(){
		$.ajax({
	    	url: '/icam/getWorkingDays.html',
	    	dataType: 'json',
	    	data: "sterm=" + ($(this).val()),
	    	success: function(data) {
	    	 if(data != "null"){
	    		var arr = data.split(",");
	    		var daysapart =arr[2];
				$("#tworkingone").val(daysapart);
	    		}   
	    	}
	    
	    	}); 
	});
	

});


$(document).ready(function() {
	$('#submitbutton').click(function(){
		var onlyTerm = $("#sterm").val();
		var onlyCourse = $("#courseId").val();
		var days= $("#tworking").val();
		var compen = $("#inputDate1").val();
		if(onlyCourse=="")
		 {
			 $("#warningbox").css('visibility','visible');
		 	 $("#warningmsg").text("Select Course");
		 return false;
		 }	
		 if(onlyTerm=="")
			{
			 $("#warningbox").css('visibility','visible');
		 	 $("#warningmsg").text("Select Term name");
			 var daysapart = "";
			 $("#tworkingone").val(daysapart);
			 return false;
			} 
		 
		 	var isChecked = $("input[name=singled]:checked").val();
		 	if(isChecked == null){
		 		$("#warningbox").css('visibility','visible');
			 	$("#warningmsg").text("Select atleast one radio button");
				return false;
		 	}
			
		 	if(days == ""){
		 		$("#warningbox").css('visibility','visible');
			 	$("#warningmsg").text("Click on Calculate button");
				return false;
		 	}
		 	if(!($("#reason").val()) && !($("#reasonone").val()) ){
		 		$("#warningbox").css('visibility','visible');
			 	$("#warningmsg").text("Enter the Reason");
				return false;
		 	}
		 	
		 	
		 	
		 	else 
		 		
		 			return true;
	});
	});