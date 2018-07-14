$("#recipients").change(function() {
	
	var recepient = $("#recipients").val();
	//alert(recepient);
	if(recepient === 'standard'){
			$("#standardtableBody").empty();
			$.ajax({
			url: '/cedugenie/getSectionAndStandardList.html',
			dataType: 'json',
			success: function(data){ 
				//alert(data);
				var dataArray = data.split("@#@");
			//	alert(dataArray.length);
				var table = document.getElementById("standardtableBody");
				
				for(var i=0;i<dataArray.length-1;i++){
					var dataArr = dataArray[i].split("##");
					
					//alert("dataArr[1]="+dataArr[1]);
					var standardArr = dataArr[0].split("*");
					var sectionArr = dataArr[1].split("*#*")
					//alert(standardArr);
					//alert(sectionArr);
					
					var row = table.insertRow(i);  
					
					var cell = row.insertCell(0);
					element = document.createElement("input");
	                element.type = "checkbox";
					element.id = "standard" + i;
					element.name = "standard";
					element.value = standardArr[i];
					cell.appendChild(element);
					
					var element1 = document.createTextNode(standardArr[i]);
					cell.appendChild(element1);
					
					var cell = row.insertCell(1);
					for(var j=0;j<sectionArr.length-1;j++){
						var sectionvalue = sectionArr[j].split("@"); 
						element = document.createElement("input");
		                element.type = "checkbox";
						element.id = "section" + standardArr[i];
						element.name = "section"+ standardArr[i];
						element.value = sectionvalue[0];
						cell.appendChild(element);
						
						var element1 = document.createTextNode(sectionvalue[1]);
						cell.appendChild(element1);
					}
					
					
				}
				 
			}
		});
			
		document.getElementById("standardDiv").style.display = "block";
		document.getElementById("rollnumberDiv").style.display = "none";

	}if(recepient === 'roll'){
		document.getElementById("rollnumberDiv").style.display = "block";
		document.getElementById("standardDiv").style.display = "none";
	}
	if(recepient === 'all'){
		document.getElementById("standardDiv").style.display = "none";
		document.getElementById("rollnumberDiv").style.display = "none";
	}
});

