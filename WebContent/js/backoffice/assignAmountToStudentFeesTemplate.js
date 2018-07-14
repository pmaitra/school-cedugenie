$(document).ready(function(){
	$('#unMappedCourseName').change(function(){
		$('#assignAmountTableDiv').css("display","block");
	});
	
	$("#templateName").change(function(){  
		/*$.ajax({
			url: '/icam/getUnmappedStandardsToFeesTemplate.html',
			dataType: 'json',
			data: "templateCode="+($(this).val()),
			success: function(dataDb){
				var options = "<option value=''>Select</option>";
				if(null!= dataDb){
					var dataArr = dataDb.split("#");
					for(var i =0; i< dataArr.length -1; i++){
						var arr = dataArr[i].split("*");
						options = options + "<option value='"+arr[0]+"'>"+arr[1]+"</option>";
					}
					document.getElementById("unMappedCourseName").innerHTML = options;
				}
				else{
					alert("No such standard found");
				}
			}
		});*/
		
    	$.ajax({
	        url: '/icam/getTemplateWiseFeesStructure.html',
	        dataType: 'json',
	        data: "templateCode=" + ($(this).val()),
	        success: function(dataDB) {	
	        	if(dataDB != null){
	        		var arr = dataDB.split("~");
        			
					for (var i=0; i<arr.length - 1; i++){   
						var feesHead = arr[i].split(":");
	                    var table = document.getElementById("amountTable");
		        	    var rowCount = table.rows.length;
		        	    var row = table.insertRow(rowCount-1);
		        	    
		        	    var cell1 = row.insertCell(0);
		        	    var element1 = document.createTextNode(feesHead[1]);
		        	    cell1.appendChild(element1);
		        	    
		        	    var element2 = document.createElement("input");
		        	    element2.type = "hidden";
		        	    element2.value = feesHead[1];
		        	    element2.name = "feesHead";//feesHead[1];
		        	    element2.innerHTML = feesHead[1];
		        	    cell1.appendChild(element2);

	        	    	var cnt = 0;
		        	    var scNames = document.getElementsByName("socialCategoryName");
		        	    countScRows = document.getElementById("socialCategorySize").value;
		        	    for(var j = 1 ; j <= countScRows ; j++){
		        	    	var cell = row.insertCell(j);		
		        	        var element = document.createElement("input");
		        	        element.type = "text";		        	        	
		        	        element.value = "0.00";
		        	        element.setAttribute("style","text-align: right;");
		        	        element.setAttribute("onfocus","removeZero(this);");
		        	        var totBx = scNames[cnt].value+"Total";
		        	        element.setAttribute("onblur","setZero(this,'"+totBx+"');");
		        	        element.name=feesHead[1]+"##"+scNames[cnt].value;
		        	        cnt++;
		        	        element.className ="textField form-control";
		        	        cell.appendChild(element);
		        	    }
					}
				}
	        }
		});
    });
});

function removeZero(tBox){
	if(tBox.value=="0.00"){
		tBox.value="";
	}
}

function setZero(tBox,totBx){
	if(tBox.value==""){
		tBox.value="0.00";
	}		
	if(isNaN(tBox.value)){
		alert("Please enter a valid value(Numeric)");
		tBox.value="0.00";
	}else{
		var p=parseFloat(tBox.value);
		if(p<0.0){
			alert("Please enter a no greater than zero");
			tBox.value="0.00";
		}
	}
	calculateTotal(totBx);
}

function calculateTotal(totBx){
	document.getElementById(totBx).value="0.00";
	var tb = document.getElementsByClassName("textField");
	for(var i=0;i<tb.length;i++){
		var tName=tb[i].name.split("##");
		tName=tName[1];
		var checker=totBx.replace("Total","");
		if(tName==checker){
			var tot=parseFloat(document.getElementById(totBx).value);
			tot=tot+parseFloat(tb[i].value);
			document.getElementById(totBx).value=tot;
		}
	}	
}