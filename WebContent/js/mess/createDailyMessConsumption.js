	var i = 1;
	function addNewCommodity(){
		var table = document.getElementById("messDailyConsumptionDiv");
		var rowCount = table.rows.length;
		var row = table.insertRow(rowCount);
		
		var cell0 = row.insertCell(0);
		var element0 = document.createElement("input");
		element0.name = "lpNo";
		element0.id = "lpNo"+i;
		element0.setAttribute("class","form-control");
		element0.setAttribute("required","required");
		cell0.appendChild(element0);
		
		var cell1 = row.insertCell(1);
		var element1 = document.createElement("select");
		element1.name = "commodityType";
		element1.id = "commodityType"+i;
		element1.setAttribute("class","form-control");
		element1.setAttribute("required","required");
		element1.setAttribute("onchange","getCommodities(this);");
		
		var option = document.createElement("option");
	    option.text = "Select..";
	    element1.appendChild(option);
	    
		var option = document.createElement("option");
	    option.value = "dailyration";
	    option.text = "Daily Ration Items";
	    element1.appendChild(option);
	    
	    var option = document.createElement("option");
	    option.value = "nondailyration";
	    option.text = "Other Items";
	    element1.appendChild(option);
		
		cell1.appendChild(element1);
		
		
		var cell2 = row.insertCell(2);
		var element2 = document.createElement("select");
		element2.name = "commodityName";
		element2.id = "commodityName"+i;
		element2.setAttribute("class","form-control");
		element2.setAttribute("required","required");
		element2.setAttribute("onchange","getCommodityStock(this);");
		
		var option = document.createElement("option");
	    option.text = "Select..";
	    element2.appendChild(option);
	    
		cell2.appendChild(element2);
		
		var cell3 = row.insertCell(3);
		var element3 = document.createElement("input");
		element3.name = "commodityAU";
		element3.id = "commodityAU"+i;
		element3.setAttribute("class", "form-control");
		element3.setAttribute("readonly","readonly");
		cell3.appendChild(element3);
		
		var cell4 = row.insertCell(4);
		var element4 = document.createElement("input");
		element4.name = "oldStockInForIssue";
		element4.id = "oldStockInForIssue"+i;
		element4.setAttribute("class","form-control");
		element4.setAttribute("value","0.0");
		element4.setAttribute("readonly","readonly");
		cell4.appendChild(element4);
		
		/*var cell3 = row.insertCell(3);
		var element3 = document.createElement("input");
		element3.name = "newReceivedQuantity";
		element3.id = "newReceivedQuantity"+i;
		element3.setAttribute("class","form-control");
		element3.setAttribute("value","0.0");
		element3.setAttribute("readonly","readonly");
		cell3.appendChild(element3);
		
		var cell4 = row.insertCell(4);
		var element4 = document.createElement("input");
		element4.name = "totalStockForIssue";
		element4.id = "totalStockForIssue"+i;
		element4.setAttribute("class","form-control");
		element4.setAttribute("value","0.0");
		element4.setAttribute("readonly","readonly");
		cell4.appendChild(element4);*/
		
		var cell5 = row.insertCell(5);
		var element5 = document.createElement("input");
		element5.name = "rate";
		element5.id = "rate"+i;
		element5.setAttribute("class","form-control");
		cell5.appendChild(element5);
		
		var cell6 = row.insertCell(6);
		var element6 = document.createElement("input");
		element6.name = "issuingQuantity";
		element6.id = "issuingQuantity"+i;
		element6.setAttribute("class","form-control");
		element6.setAttribute("value","0.0");
		element6.setAttribute("onfocus","");
		element6.setAttribute("onblur","calculateAmount(this);");
		cell6.appendChild(element6);
		
		var cell7 = row.insertCell(7);
		var element7 = document.createElement("input");
		element7.name = "amount";
		element7.id = "amount"+i;
		element7.setAttribute("class","form-control");
		element7.setAttribute("readonly","readonly");
		cell7.appendChild(element7);
		
		var cell8 = row.insertCell(8);
		var cancelElement = document.createElement("button");
	 	cancelElement.setAttribute("class", "mb-xs mt-xs mr-xs btn btn-danger");
	 	cancelElement.setAttribute("onclick", "deleteThisRow(this);");
	 	cancelElement.innerHTML = "X";
	 	cell8.appendChild(cancelElement);
		
		i++;
	}
	/*var detailsOfCommodity;
	function getUnitStockRate(commodityObj){
		var commodityId = commodityObj.id;
		var index = commodityId.slice(9);
		var commodity = commodityObj.value;
		$.ajax({
	        url: '/icam/getCommodityDetailsForIndentSheet.html',
	        data: "commodity="+(commodity),
	        dataType: 'text',
	        success: function(data) {
				if(data != ""){
					detailsOfCommodity = data.split("*");
					($("#commodityUnit"+index).val(detailsOfCommodity[0]));
					($("#commodityStock"+index).val(detailsOfCommodity[1]));
					($("#commodityRate"+index).val(detailsOfCommodity[2]));
				}
	        }
		});
	}*/
	
	function deleteThisRow(obj){	
		var parent = obj.parentNode.parentNode;
		parent.parentNode.removeChild(parent);
	}
	
	/** added by sourav.bhadra on 05-10-2017 **//*
	function getDailyRationVendorDetails(vendor){
		var vendorCode = vendor.value;
		document.getElementById("vendorCode").value = vendorCode;
		
		$.ajax({
	        url: '/icam/getDailyRationVendorDetails.html',
	        data: "vendorCode="+(vendorCode),
	        dataType: 'text',
	        success: function(data) {
				if(data != ""){
					var vendorDetails = data.split(";");
					document.getElementById("vendorAddress").value = vendorDetails[0];
					document.getElementById("vendorContactNumber").value = vendorDetails[1];
				}
	        }
		});
	}*/
	
	/* added by souarv.bhadra on 10-10-2017 */
	$(document).ready(function(){
		var today = new Date();
		var dd = today.getDate();
		var mm = today.getMonth()+1; //January is 0!

		var yyyy = today.getFullYear();
		if(dd<10){
		    dd='0'+dd;
		} 
		if(mm<10){
		    mm='0'+mm;
		} 
		var today = dd+'/'+mm+'/'+yyyy;
		document.getElementById("dateOfIssue").value = today;
	});
	
	function getCommodities(ctype){
		var itemId = ctype.id;
		var id = itemId.substring(13);
		var commodityType = ctype.value;
		$.ajax({
	        url: '/icam/getCommoditiesForDailyMessConsumption.html',
	        data: "commodityType="+(commodityType),
	        dataType: 'text',
	        success: function(data) {
				if(data != ""){
					var dataArr = data.split(";");
					var option = "<option>Select...</option>";
					
					for(var i=0; i<dataArr.length-1; i++){
						option += "<option value='"+dataArr[i]+"'>"+dataArr[i]+"</option>";
					}
					document.getElementById("commodityName"+id).innerHTML = option;
				}
	        }
		});
	}
	
	function getCommodityStock(commodity){
		var itemId = commodity.id;
		var id = itemId.substring(13);
		var com = commodity.value;
		var commodityType = document.getElementById("commodityType"+id).value;
		var issueDate = document.getElementById("dateOfIssue").value;
		
		$.ajax({
	        url: '/icam/getCommoditiesMessStockForDailyMessConsumption.html',
	        data: "commodityType="+(commodityType)+"&commodity="+(com)+"&issueDate="+(issueDate),
	        dataType: 'text',
	        success: function(data) {
				if(data != ""){
					var arr = data.split("#");
					document.getElementById("commodityAU"+id).value = arr[0];
					document.getElementById("oldStockInForIssue"+id).value = arr[1];
					document.getElementById("rate"+id).value = arr[2];
				}
	        }
		});
	}
	
	function calculateAmount(issue){
		var issued = issue.value;
		var itemId = issue.id;
		var id = itemId.substring(15);
		var rate = document.getElementById("rate"+id).value;
		
		if(issued == '' || isNaN(issued)){
			issued = 0.0;
			document.getElementById("issuingQuantity"+id).value = '0.0';
		}
		
		var amount = parseFloat(parseFloat(rate)*parseFloat(issued));
		document.getElementById("amount"+id).value = amount;
	}
	/*function validateIndentSheet(){
		var demandadQuantity = document.getElementsByClassName("textfield1");
		if(demandadQuantity.length == 0){
			alert("No quantity.");
			return false;
		}
		var intRegx=numeric=/^[0-9]{1,}$/;
		for(var i=0; i<demandadQuantity.length;i++){
			var demand=demandadQuantity[i].value;
			demand = demand.replace(/\s{1,}/g,'');
			demandadQuantity[i].value = demand;
			
			if(!demand.match(intRegx)){
				alert("Invalid quantity");
				return false;
			}else{
				demand = parseFloat(demand);
				if(demand<=0.0){
					alert("Please enter a quantity greater than zero.")
					return false;
				}
			}
		}
		return true;
	}*/
	
	