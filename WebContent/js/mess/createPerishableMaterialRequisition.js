
	var i = 1;
	function addNewCommodity(){
		var table = document.getElementById("messDailyRationPo");
		var rowCount = table.rows.length;
		var row = table.insertRow(rowCount);
		
		
		var commUnits = "";
		$("input[name='comUnit']").each(function(){ 
			var unit = $(this).val();
			commUnits += unit +";";
		});
		var unitsArr = commUnits.split(";");
		
		var cell0 = row.insertCell(0);
		var element0 = document.createElement("input");
		element0.name = "commodity";
		element0.id = "commodity"+i;
		element0.setAttribute("class","form-control commodityNameClass");
		element0.setAttribute("onkeydown","makeAutocomplete(this)");
		element0.setAttribute("required","required");
		cell0.appendChild(element0);
		
		var cell1 = row.insertCell(1);
		var element1 = document.createElement("select");
		element1.name = "commodityUnit";
		element1.id = "commodityUnit"+i;
		element1.setAttribute("class","form-control");
		
		var option = document.createElement("option");
	    option.text = "Select..";
	    element1.appendChild(option);
	    
	    for(var j=0; j<unitsArr.length-1; j++){
	    	var option = document.createElement("option");
		    option.text = unitsArr[j];
		    option.value = unitsArr[j];
		    element1.appendChild(option);
	    }
		
		cell1.appendChild(element1);
		
		
		var cell2 = row.insertCell(2);
		var element2 = document.createElement("input");
		element2.name = "commodityDemandedQuantity";
		element2.id = "commodityDemandedQuantity"+i;
		element2.setAttribute("class","form-control");
		element2.setAttribute("value","0.0");
		element2.setAttribute("onfocus","");
		cell2.appendChild(element2);
		
		var cell3 = row.insertCell(3);
		var cancelElement = document.createElement("button");
	 	cancelElement.setAttribute("class", "mb-xs mt-xs mr-xs btn btn-danger");
	 	cancelElement.setAttribute("onclick", "deleteThisRow(this);");
	 	cancelElement.innerHTML = "X";
	 	cell3.appendChild(cancelElement);
		
		i++;
	}
	
	function deleteThisRow(obj){	
		var parent = obj.parentNode.parentNode;
		parent.parentNode.removeChild(parent);
	}
	
	function makeAutocomplete(x){
		var allnames;
		var buttonName="";

		var field = x.id;
		var i = field.substring(9);

		buttonName=$(x);
		$.ajax({
			url:'/cedugenie/getNameOfCommodities.html',
			dataType:'json',
			success:function(data){
				allnames = data.split(";");
				$(buttonName).autocomplete({
					source: allnames,
					select: function (event, ui){
						var commodityName = ui.item.value;
						$.ajax({
							url: '/cedugenie/getCommodityUnitForPerishableMaterialRequisition.html',
							dataType: 'json',
							data: "commodityName=" + commodityName,
							success: function(data) {	
								if(data != null && data!=""){
									var selOp = document.getElementById("commodityUnit"+i);
									setSelectedValue(selOp, data);
									document.getElementById("commodityUnit"+i).setAttribute("readonly","readonly");
								}
								else{
									alert("Not Found");
								}
							}			        
						});
					}
				});
			}
		});
	}
	
	function setSelectedValue(selectObj, valueToSet) {
	    var sid = selectObj.id;
		$('#'+sid+' option[value='+valueToSet+']').attr('selected','selected');
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