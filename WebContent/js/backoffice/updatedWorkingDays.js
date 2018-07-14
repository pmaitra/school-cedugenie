function showupdateddiff(){
	var radiovalue=null;
	var hh = null;
	hh = document.getElementById('tworking');
	 var inputs = document.getElementsByName("singled");
     for (var l = 0; l < inputs.length; l++) {
       if (inputs[l].checked) {
    	   radiovalue=inputs[l].value;
    	   }
     }
	var date1 = document.getElementById('strdate').value; 
	var date2 = document.getElementById('enddate').value; 
	var date3 = document.getElementById('inputDate').value;
	date1 = date1.split("/");
	date2 = date2.split("/");
	var sDate = new Date(date1[1]+"/"+date1[0]+"/"+date1[2]);
	var eDate = new Date(date2[1]+"/"+date2[0]+"/"+date2[2]);
	/*sDate.create().format('{Weekday} {d} {Month}, {yyyy}'); */
	var daysApart = Math.abs(Math.round((sDate-eDate)/86400000))+1;
	var weekendDays1 = 0;
	var dayMilliseconds1 = 1000 * 60 * 60 * 24;
	while (sDate <= eDate) {
	    var day1 = sDate.getDay();
	    if (day1 == 6 || day1 == 0) {
	        weekendDays1++;
	    }  
	     sDate = new Date(+sDate + dayMilliseconds1); 
	}
	 
	var daysApart1 = 0;
	 /*  document.getElementById('diffDays').lastChild.data = daysApart; */
	var pb=document.getElementsByName('inputDateA');
	var len=pb.length;
	var date4 = document.getElementById('tworkingone').value; 
	for(var k=0;k<len;k++){
		
	var x = document.getElementsByName('inputDateA')[k].value;
	} 
	var daysequal = 0;
	daysequal = daysApart - weekendDays1;
	//For multiple days
	if(radiovalue == "multi"){
	if(x != ""){
		
		if(len > daysequal){
			document.getElementById("warningbox").style.visibility = "visible";
		  	document.getElementById("warningmsg").innerHTML= "Compensatory holidays exceed declared holidays.";
			len--;
			daysApart1 = date4 - daysApart + len + weekendDays1;
		}
		daysApart1 = date4 - daysApart + len + weekendDays1;
		document.getElementById("warningbox").style.visibility = "collapse";
	  	document.getElementById("warningmsg").innerHTML= "";
	}
	if(x == ""){
		if(len > daysequal){
			document.getElementById("warningbox").style.visibility = "visible";
		  	document.getElementById("warningmsg").innerHTML= "Compensatory holidays exceed declared holidays";
		}
		len--;
		daysApart1 = date4-daysApart + len + weekendDays1;
		
	}
	hh.value = daysApart1;
	}
	//For single Day
	if(radiovalue == "single"){
		hh.value="";
		if(date3 != ""){
			daysApart1 = date4 -1;
			var date5 = document.getElementById('cwone').value;
			if(date5 != ""){
				daysApart1 = daysApart1 + 1;
			}
			if(date5 == ""){
				daysApart1 = date4 -1;
			}
			
			hh.value = daysApart1; 
		}
	}
	var innerHeight2=document.body.scrollHeight;
	var frame=window.parent.document.getElementById("frame");	    	
	frame.style.height = innerHeight2+25+ 'px';
	} 





