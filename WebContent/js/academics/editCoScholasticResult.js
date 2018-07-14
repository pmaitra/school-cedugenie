function showHideTR(sel){
	if(sel.value=="OTHERS"){
		document.getElementById(sel.id.split("-")[0]+"-Others").style.visibility="visible";
	}else{
		document.getElementById(sel.id.split("-")[0]+"-Others").style.visibility="collapse";
	}
}


window.onload=function(){
	var x=getElementsByClassName("midsec1");
	var maxWidth=0;
	for(var i=0;i<x.length;i++)
		if(x[i].offsetWidth>maxWidth)
			maxWidth=x[i].offsetWidth;
			
	for(var i=0;i<x.length;i++)
		x[i].width=maxWidth;
};
$(document).ready(function(){
	 $("#rollSelect").change(function(){
		var sel=document.getElementById("rollSelect");
		showName(sel);
		
		$.ajax({
	        url: '/cedugenie/getStudentsCoScholasticResult.html',
	        dataType: 'json',
	        data: "roll=" + sel.options[sel.selectedIndex].text + "&loggedInUser="+ ($("#loggedInUser").val()),
	        success: function(dataDB) {
	        	if(null!=dataDB && dataDB!=""){
	        		dataDB=dataDB.split("~");
	        		if(dataDB[dataDB.length-1]!="UPDATE"){
	        			document.getElementById("warningbox0").style.visibility="visible";
	        			document.getElementById("submitbuttonDiv").style.visibility="collapse";
	        		}else{
	        			document.getElementById("warningbox0").style.visibility="collapse";
	        			document.getElementById("submitbuttonDiv").style.visibility="visible";
	        		}
	        		for(var i=0;i<dataDB.length-1;i++){
	        			var inner=dataDB[i].split("`");
	        			var indicator=document.getElementById(inner[1]+"-Indicator");
	        			var checker=0;
	        			if(inner[2]!=""){	        				
	        				if(inner[1]=="ATTENDANCE")
	        				{
	        					indicator.value=inner[2];
	        					var grade=document.getElementById(inner[1]+"-Grade");
	        					grade.value=inner[3];
	        					checker=1;
	        					//continue;
	        				}
	        				else if(inner[1]=="HEIGHT" || inner[1]=="WEIGHT")
	        				{
	        					var grade=document.getElementById(inner[1]+"-Grade");
	        					grade.value=inner[3];
	        					checker=1;
	        					//continue;
	        				}
	        				else if(inner[1]!="ATTENDANCE" && inner[1]!="HEIGHT" && inner[1]!="WEIGHT"){	
			        			for (var j = 0; j < indicator.length; j++){
			        			    if (indicator.options[j].value == inner[2]){
			        			      checker=1;
			        			      indicator.value=inner[2];
			        			      //continue;
			        			    }
			        			}	        			
			        			if(checker==0){
			        				indicator.value="OTHERS";
			        				document.getElementById(inner[1]+"-Others").style.visibility="visible";
			        				document.getElementById(inner[1]+"-Text").value=inner[2];
			        			}
			        			document.getElementById(inner[1]+"-Grade").value=inner[3];
			        			if(null!=inner[4] && inner[4]!="" && inner[4]!="null")
			        				document.getElementById(inner[1]+"-GradePoint").value=inner[4];			        			
	        				}
	        				checker=0;
	        			}
	        			
	        		}
	        	}else{
	        		var x=getElementsByClassName("defaultselect");
	        		for(var i=0;i<x.length;i++){
	        			var id=x[i].id.split("-")[0];
	        			document.getElementById(id+"-Others").style.visibility="collapse";
	        			document.getElementById(id+"-Text").value="";
	        			document.getElementById(id+"-Grade").value="";
	        			document.getElementById(id+"-GradePoint").value="";
	        			x[i].value="";
	        			document.getElementById("submitbuttonDiv").style.visibility="collapse";
	        		}
	        	}
	       }
		});
		
	 });
});
function showName(sel){
	document.getElementById("rollNumber").value=sel.options[sel.selectedIndex].text;
	document.getElementById("name").value=sel.value;
	document.getElementById("nameSpan").innerHTML=sel.value;
}