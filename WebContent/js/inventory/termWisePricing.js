
$(document).ready(function(){
	$("#financialYear").change(function() {
		//alert("hii");
		//var financialYear = $("#financialYear").val();
		//alert("financialYear=="+financialYear);
		$.ajax({
			url:' /icam/commodityListMappedForAFinancialYear.html',
			data:'financialYear='+($(this).val()),
			dataType: 'json',
			success: function(data)
			{
			//	alert(data);
				if(data != ""){
		    		data=data.split("*");
		    		for(var i=0;i<data.length-1;i++){		    		
		    			//var innerData=data[i].split("##");
		    			$('#commodityName')
		    	         .append($("<option></option>")
		    	                    .attr("value",data[i])
		    	                    .text(data[i])); 
		    		}
	    		}
				
			}
		});
	});
	$("#commodityName").change(function() {
		
		var financialYear = $("#financialYear").val();
		var table = document.getElementById("vendorPricingTable");
		deleteRow(table);
		$.ajax({
			url:' /icam/vendorListMappedForAFinancialYearAndCommodity.html',
			data:'commodityName='+($(this).val())+'&financialYear='+financialYear,
			dataType: 'json',
			success: function(dataDB)
			{
				var dataVal = dataDB.split("@");
				//alert("dataVal=="+dataVal[0]);
				
				var dataArr = dataVal[1].split("#");
				
				if(dataVal[0] == 'radio'){
					for(var i=0;i<dataArr.length-1;i++){
		        		var data = dataArr[i].split("*");
		                var rowCount = table.rows.length;
		                var row = table.insertRow(rowCount);
		                
		                var cell,element;
		                
		                cell = row.insertCell(0);		
		                element = document.createElement("input");
		                element.type = "radio";
		                element.name="vendorCode";
		                element.value=data[0];
		                cell.appendChild(element);
		                
		                cell = row.insertCell(1);		
		                element = document.createTextNode(data[1]);
		                cell.appendChild(element);
		                
		                cell = row.insertCell(2);		
		                element = document.createTextNode(data[3]);
		                cell.appendChild(element);
		                
		                cell = row.insertCell(2);		
		                element = document.createTextNode(data[2]);
		                cell.appendChild(element);
					}
				}else{
					for(var i=0;i<dataArr.length-1;i++){
		        		var data = dataArr[i].split("*");
		                var rowCount = table.rows.length;
		                var row = table.insertRow(rowCount);
		                
		                var cell,element;
		                
		                cell = row.insertCell(0);		
		                element = document.createTextNode("selected");
		                cell.appendChild(element);
		                
		                cell = row.insertCell(1);		
		                element = document.createTextNode(data[1]);
		                cell.appendChild(element);
		                
		                cell = row.insertCell(2);		
		                element = document.createTextNode(data[2]);
		                cell.appendChild(element);
		                
		                cell = row.insertCell(3);		
		                element = document.createTextNode(data[3]);
		                cell.appendChild(element);
					}
				}
				
				
				
			}
		});
	});
});


function deleteRow(table){
    var rowCount = table.rows.length;
    //alert("rowCount=="+rowCount);
    for(var i=rowCount; i>0; i--){
   		table.deleteRow(i-1);
    }

}