function showDiv(classDiv){
		document.getElementById(classDiv).style.display="block";
}

function hideDiv(classDiv){
	document.getElementById(classDiv).style.display="none";
}

function addText(chkbx,w){
	var ww=document.getElementById(w).value;	
	if(chkbx.checked){
		if(ww=="" || ww=='null')
			ww=ww+chkbx.value;
		else
			ww=ww+','+chkbx.value;
		
		document.getElementById(w).value=ww;
	}
	else{
		var wInLower=ww.toUpperCase();
		var chkbxInLower=chkbx.value.toUpperCase();			
		var i=wInLower.search(chkbxInLower);
		//i=-1 Not Possible since the value will always be present in textbox as check box is checked			
		if(i==0){
			wInLower = wInLower.replace(chkbxInLower,"");
			document.getElementById(w).value=wInLower;			
			var j=wInLower.search(',');
			if(j==0){
			wInLower = wInLower.replace(',',"");
			document.getElementById(w).value=wInLower;
			}
		}
		if(i>0){
			chkbxInLower=","+chkbxInLower;
			wInLower = wInLower.replace(chkbxInLower,"");
			document.getElementById(w).value=wInLower;
		}
	}
};

$(document).ready(function() {
	$("#eventViewer").change(function(){
	 var eventViewer = $("#eventViewer option:selected").text();
	 if(eventViewer == "Roll Based"){
		 $("#roleNames").css('visibility','visible');
	 	 $("#labelText").css('visibility','visible');
	 }
	 if(eventViewer != "Roll Based"){
		 $("#roleNames").css('visibility','collapse');
		 $("#roleNames").val("");
		 $("#labelText").css('visibility','collapse');
	 }
	});
	
	$("#buttonEventGenerator").click(function(){
    	var textDiv = document.getElementById("eventGenerator").value;
    	if(textDiv != ""){
    		$("#infomsgboxForEvent").css('visibility','collapse');
    		$("#infomsgboxForEvent").text('');
			var div = document.createElement("div");
			div.setAttribute("class", "external-event");
			div.appendChild(document.createTextNode(textDiv));
			document.getElementById("external-events1").appendChild(div);
			$('.external-event').each(function() {
				var eventObject = {
				title: $.trim($(this).text())
				};
				var eventtitle = eventObject.title;
				$(this).data('eventObject', eventObject);
				$(this).draggable({
					zIndex: 999,
					revert: true,
					revertDuration: 0
				});
			});
	    	return false;
    	}
    	if(textDiv == ""){
    		$("#infomsgboxForEvent").css('visibility','visible');
    		$("#infomsgboxForEvent").text('Enter some text to create event');
    	}
	});
	
	$("#external-events div.external-event").each(function() {
		// create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
		// it doesn't need to have a start or end
		var eventObject = {
			title: $.trim($(this).text()) // use the element's text as the event title
		};
		// store the Event Object in the DOM element so we can get to it later
		$(this).data('eventObject', eventObject);
		// make the event draggable using jQuery UI
		$(this).draggable({
			zIndex: 999,
			revert: true,      // will cause the event to go back to its
			revertDuration: 0  //  original position after the drag
		});
	}); 
});