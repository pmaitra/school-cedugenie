/*$("#vendor").change(function(){
		$("#vendorCode").val($("#vendor").val());
		
		if($("#vendor").val()!=""){
			$.ajax({
		    url: '/cedugenie/vendorCommodityListAccordingToTender.html',
		    	dataType: 'json',
		    	data: "vendorCode=" + ($("#vendor").val())+'&financialYear='+($("#financialYear").val()),		    	
		    	success: function(data) {
		    		//alert("hii");
		    		//alert(data);
		    		if(data != ""){
			    		data=data.split("+-+");
			    		for(var i=0;i<data.length-1;i++){		    		
			    			var innerData=data[i].split("##");
			    			$('#commodityName0')
			    	         .append($("<option></option>")
			    	                    .attr("value",innerData[0])
			    	                    .text(innerData[0])); 
			    		}
		    		}
		    	}
		    });
		}
});*/



$("#departmentCode").change(function(){
	document.getElementById("departmentBudgetTable").style.display="none";
	var dept = $(this).val();
	$.ajax({
	    url: '/cedugenie/getDepartmentBudgetDetails.html',
	    	dataType: 'json',
	    	data: "departmentCode=" + dept,		    	
	    	success: function(data) {
	    		if(null != data){
	    			var budget = data.split("~");
	    			document.getElementById("budgetAmount").value=budget[0];
	    			document.getElementById("totalExpence").value=budget[1];
	    			document.getElementById("balance").value=budget[2];
	    			document.getElementById("departmentBudgetTable").style.display="block";
	    			/* added by sourav.bhadra on 04-08-2017 
	    			 * to store departmental budget */
	    			saveBalance(budget);
	    			/* added by sourav.bhadra on 18-08-2017 */
	    			if(budget[0]=="0.0"){
	    				document.getElementById("budgetProblem").style.display="block";
	    			}else{
	    				document.getElementById("budgetProblem").style.display="none";
	    			}
	    		}
	    	}
	});
});

$("#vendor").change(function(){
	$.ajax({			
    url: '/cedugenie/vendorCommodityListAccordingToTender.html',
    	dataType: 'json',
    	data: "vendorCode=" + ($("#vendor").val())+"&financialYear="+($("#financialYear").val()),		    	
    	success: function(datadB) {
    		
    		//alert("datadB=="+datadB);
	    	if(datadB != ""){
	    		
	    		var datadBArr = datadB.split("@");
	    		var data = datadBArr[0].split("+-+");
	    		dataLength=data.length-1;
	    		
	    		document.getElementById("vendorCode").value = datadBArr[1]
	    		var table=document.getElementById("commodityTable");
	    		var p=0;//(table.rows.length); /* modified by sourav.bhadra on 28-07-2017 */
	    			 
	    	    var rowCount = table.rows.length;
	    	   
	    	       
	    	    var cell,element;
	    	    
	    	    for(var i=0;i<data.length-1;i++)
	    	    	{
	    	    	 var row = table.insertRow(i);
	    	    	
	    	    	var dataArr = data[i].split("##");
	    	    	 cell = row.insertCell(0);
	    	    	 /* element = document.createElement("input");
	 	    	    element.type = "hidden";
	 	    	    element.name="commodityCode";
	 	    	    element.id="commodityCode"+p;
	 	    	    element.setAttribute("class","form-control");
	 	    	    element.setAttribute("readonly","readonly");
	 	    	    cell.appendChild(element);*/
	 	    	    
	 	    	    element = document.createElement("input");
	 	    		element.name="commodityName";
	 				element.setAttribute("class","form-control");
	 				element.id="commodityName"+p;
	 				element.value = dataArr[0];
	 				element.setAttribute("readonly","readonly");
	 				/*element.add(new Option("Select...", ""),null);		    				
	 				for(var i=0;i<dataLength;i++)
	 				{
	 					var item = data[i].split("##");
	 					var Code = item[0];
	 					var Name = item[0];						
	 					element.add(new Option(Name, Code),null);
	 				}*/
	 				//var id="commodityCode"+p;
	 				//var mat="setCommodityCode(this.value,\""+id+"\",\""+p+"\");"; /* modified */
	 				//element.setAttribute("onchange",mat);
	 	            cell.appendChild(element);
	 	    				
	 				cell = row.insertCell(1);
	 	            element = document.createElement("input");
	 	            element.type = "text";
	 	            element.name="purchaseRate";
	 	            element.id="purchaseRate"+p;
	 	            element.value=dataArr[1];
	 	           // element.setAttribute("onfocus","this.value=''");
	 	           // element.setAttribute("onblur","validateRate(this);");//arnab
	 	            element.setAttribute("class","form-control");
	 	           element.setAttribute("readonly","readonly");
	 				cell.appendChild(element);
	 				
	 				/* added by sourav.bhadra on 28-07-2017 
	 				 * to display commodity unit */
	 				cell = row.insertCell(2);
	 	            element = document.createElement("input");
	 	            element.type = "text";
	 	            element.name="commodityUnit";
	 	            element.id="commodityUnit"+p;
	 				element.setAttribute("class","form-control");
	 				element.value = dataArr[2];
	 				element.setAttribute("readonly","readonly");
	 				cell.appendChild(element);
	 				
	 				cell = row.insertCell(3);
	 	            element = document.createElement("input");
	 	            element.type = "text";
	 	            element.name="quantity";
	 	            element.id="quantity"+p;
	 				element.value="0.00";
	 				element.setAttribute("class","form-control");
	 				element.setAttribute("onfocus","this.value=''");
	 				element.setAttribute("onblur","calculateTotal("+p+");"); 
	 				cell.appendChild(element);
	 				
	 				
	 				
	 				cell = row.insertCell(4);
	 	            element = document.createElement("input");
	 	            element.type = "text";
	 	            element.name="total";
	 	            element.value="0.00";
	 	            element.setAttribute("readonly","readonly");
	 	            element.setAttribute("class","form-control");
	 	            element.id="total"+p;
	 				cell.appendChild(element);	
	 				
	 				
	 				/*cell = row.insertCell(5);
	 				var element8= document.createElement('a');	
	 				element8.setAttribute("class","fa fa-minus-square");
	 				element8.setAttribute("onclick", "deleteRow(this);");
	 				element8.setAttribute("href","#");
	 				cell.appendChild(element8);*/
	 				p++;
	    	    	}
	    	   
	    	    document.getElementById("commodityDiv").style.display ="block";
					
	    	}else{
	    		alert("No More Assets Found");
	    	}
	    }
	});
});


function calculateTotal(p){

	//alert("hii");
	var quantity = $("#quantity"+p).val()
	var itemRate = $("#purchaseRate"+p).val();
	
	
	
	//alert("quantity=="+$("#quantity"+p).val());
	
	var total=(quantity*itemRate);
	//total=(total-((discount*total)/100));
	//document.getElementById(total).value=total;
	
	$("#total"+p).val(total);
	
	var totalQtyOrdered=0;
	var totalCommAmount=0;	
	q=document.getElementsByName("quantity");
	t=document.getElementsByName("total");
	for(var i=0;i<q.length;i++){		
		totalQtyOrdered=totalQtyOrdered + parseFloat(q[i].value);
		totalCommAmount=totalCommAmount + parseFloat(t[i].value);
	}
	document.getElementById("totalQtyOrdered").value = totalQtyOrdered;
	document.getElementById("totalCommodityAmount").value = totalCommAmount;
		
}




$("#purchaseId").change(function(){
	
	$.ajax({
	    url: '/cedugenie/getTicketNoAgainstTaskCode.html',
	    	dataType: 'json',
	    	data: "taskCode="+$(this).val(),		    	
	    	success: function(data) {
	    		if(null != data){
	    			//alert("data=="+data);
	    			$("#ticket").val(data);
	    		}
	    	}
	});
});