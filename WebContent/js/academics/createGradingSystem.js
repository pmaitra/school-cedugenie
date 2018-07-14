$(document).ready(function(){	
	$("#standard").change(
	function() {
		var textBoxes=getElementsByClassName("textfield1");
		for(var box=0;box<textBoxes.length;box++){
			textBoxes[box].value="";
		}
		if($(this).val()!=''){
			$.ajax({
		        url: '/icam/getGradingSystemForStandard.html',
		        data: "standard=" + ($(this).val()),
		        dataType: 'json',
		        success: function(data) {
		        	if(data!=null && data!=""){
		        		data=data.split("###");
		        		for(var grade=0;grade<data.length-1;grade++){
		        			var innerData=data[grade].split("*~*");
		        			if(innerData[0]=="SCHOLASTIC"){
		        				document.getElementById("Sgrade"+innerData[1]).value=innerData[2];
		        				document.getElementById("Spoint"+innerData[1]).value=innerData[3];
		        			}else{
		        				document.getElementById("Cgrade"+innerData[1]).value=innerData[2];
		        				document.getElementById("Cpoint"+innerData[1]).value=innerData[3];
		        			}
		        		}
		        	}
		        }
		    });
		}
	});
});

function saveGradingSystem(){
	var standard=document.getElementById("standard").value;
	if(standard==""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Select Standard.";
		return false;
	}	
	return true;
}