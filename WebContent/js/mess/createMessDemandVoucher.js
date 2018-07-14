	/*var commoditySelectBoxdata;
	
	$(document).ready(function(){
		commoditySelectBoxdata = null;
		$.ajax({
			url:"/icam/getCommoditiesForMess.html",
			success:function(data){
				commoditySelectBoxdata = data;
	        },
	        error:function(data){
	        	alert("error : " + data);
	        }
		});		
	});*/
	
	var i = 1;
	var pattern = "^\\s*(?=.*[1-9])\\d*(?:\\.\\d{1,2})?\\s*$"; /* modified by sourav.bhadra on 26-02-2018 */
	function addNewCommodity(){
		document.getElementById("warningDiv").style.display = 'none';
		var commodityCodes = document.getElementsByName("commodityCode");
		var commodityCode = null;
		for(var k=0;k<commodityCodes.length;k++){
			commodityCode = commodityCode + "*" +commodityCodes[k].value;
		}
		var commodityOptionCode = commodityCode.split("*");
		
		var table = document.getElementById("createDemandVoucherTable");
		var rowCount = table.rows.length;
		var row = table.insertRow(rowCount);
		
		var cell0 = row.insertCell(0);
		var element0 = document.createElement("select");
		element0.name = "commodity";
		element0.id = "commodity"+i;
		var commodityId = "commodity"+i;
		element0.setAttribute("class","form-control");
		element0.setAttribute( "required","required");
		element0.setAttribute( "onchange","getUnitStockRate(this);");
		element0.add(new Option("Select", ""));
		for(var j = 1; j<commodityOptionCode.length;j++){
			element0.add(new Option(commodityOptionCode[j], commodityOptionCode[j]));
		}
		cell0.appendChild(element0);
		//$(element0).addClass("form-control selectClass");
		//element0.setClassName = "form-control selectClass";
		//element0.onchange = "getUnitStockRate(this);";
		/*var commodityOptions = '<select name= \"commodity\" onchange=\"getUnitStockRate(this);\" class=\"form-control\" id=\"'+ commodityId + '><option value=\"\">Select...</option><option value=\"\">Select...</option>' + commoditySelectBoxdata + '</select>';
		document.getElementById(commodityId).outerHTML = commodityOptions;*/
		//element0.setAttribute("onchange","getUnitStockRate(this);");
		
		
		var cell1 = row.insertCell(1);
		var element1 = document.createElement("input");
		element1.name = "commodityUnit";
		element1.id = "commodityUnit"+i;
		element1.setAttribute("class","form-control");
		element1.setAttribute("readonly","readonly");
		cell1.appendChild(element1);
		
		var cell2 = row.insertCell(2);
		var element2 = document.createElement("input");
		element2.name = "commodityStock";
		element2.id = "commodityStock"+i;
		element2.setAttribute("class","form-control textfield2");
		element2.setAttribute("readonly","readonly");
		cell2.appendChild(element2);
		
		var cell3 = row.insertCell(3);
		var element3 = document.createElement("input");
		element3.name = "commodityDemandedQuantity";
		element3.id = "commodityDemandedQuantity"+i;
		element3.setAttribute("class","form-control textfield1");
		element3.setAttribute( "required","required");
		element3.setAttribute( "onblur","checkWithStock(this)");
		element3.setAttribute("pattern", pattern);
		cell3.appendChild(element3);
		
		/*var cell4 = row.insertCell(4);
		var element4 = document.createElement("input");
		element4.name = "commodityRate";
		element4.id = "commodityRate"+i;
		element4.setAttribute("class","form-control");
		element4.setAttribute("readonly","readonly");
		cell4.appendChild(element4);*/
		
		var cell5 = row.insertCell(4);
		var cancelElement = document.createElement("button");
	 	cancelElement.setAttribute("class", "mb-xs mt-xs mr-xs btn btn-danger");
	 	cancelElement.setAttribute("onclick", "deleteThisRow(this);");
	 	cancelElement.innerHTML = "X";
	 	cell5.appendChild(cancelElement);
		
		i++;
	}
	var detailsOfCommodity;
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
					/*($("#commodityRate"+index).val(detailsOfCommodity[2]));*/
				}
	        }
		});
	}
	
	function deleteThisRow(obj){
		document.getElementById("warningDiv").style.display = 'none';
		var parent = obj.parentNode.parentNode;
		parent.parentNode.removeChild(parent);
	}
	/**
	 * @author anup.roy
	 * for checking with stock*/
	function checkWithStock(node){
		var nodeid = node.id;
		var index = nodeid.substring(25);
		var stockvalue = $("#commodityStock"+index).val();
		var stock = parseFloat(stockvalue); 
		var demandvalue = $("#commodityDemandedQuantity"+index).val();
		var demand = parseFloat(demandvalue);
		//var intDemandValue = parseInt(demandvalue);
		/*if(intDemandValue == 0){
			document.getElementById("commodityDemandedQuantity"+index).value = '';
			document.getElementById("warningDiv").style.display = 'block';
			document.getElementById("warningmsg").innerHTML="Demanded quantity must be greater than zero."
		}*/
		if(demand>stock){
			document.getElementById("commodityDemandedQuantity"+index).value = '';
			document.getElementById("warningDiv").style.display = 'block';
			document.getElementById("warningmsg").innerHTML="Demanded quantity can't be greater than Stock."
		}else{
			document.getElementById("warningDiv").style.display = 'none';
		}
	}
	
	/* modified by sourav.bhadra on 12-10-2017 
	function validateIndentSheet(){
		document.getElementById("warningDiv").style.display = 'none';
		 added by sourav.bhadra on 12-10-2017 
		var status = true;
		$("select[name='commodity']").each(function(){
			var commodity = $(this).val();
			if(null == commodity || commodity == ''){
				documemnt.getElementById("warningDiv").style = 'block';
				document.getElementById("warningmsg").innerHTML="Please Select a Commodity."
				status = false;
			}
		});
		
		var demandadQuantity = document.getElementsByClassName("textfield1");
		let commodityInStock = document.getElementsByClassName("textfield2");
		//var intRegx=numeric=/^[+]?\d+(\.\d+)?$/;	/* modified by sourav.bhadra on 12-10-2017 
		for(var i=0; i<demandadQuantity.length;i++){
			var demand = demandadQuantity[i].value;
			let stock = commodityInStock[i].value;
			
			if(demand>0){
				if(demand > stock){
					documemnt.getElementById("warningDiv").style = 'block';
					document.getElementById("warningmsg").innerHTML="Demanded quantity can't be greater than stock in hand."
					status = false;
				}
			}else{
				demand = '';
				status = false;
			}
		}
		return status;
	}*/