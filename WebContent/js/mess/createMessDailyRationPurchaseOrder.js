
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
					($("#commodityRate"+index).val(detailsOfCommodity[2]));
				}
	        }
		});
	}
	
	function deleteThisRow(obj){	
		var parent = obj.parentNode.parentNode;
		parent.parentNode.removeChild(parent);
	}
	
	/** added by sourav.bhadra on 05-10-2017 **/
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
		
		/** added by sourav.bhadra on 26-10-2017 **/
		/*$.ajax({
	        url: '/icam/getDailyRationCommoditiesPriceDetailsForAVendor.html',
	        data: "vendorCode="+(vendorCode),
	        dataType: 'text',
	        success: function(data) { 
				if(data != ""){
					var list = data.split(";");
					var commodities = list[0].split("*");
					var prices = list[1].split("#");
					
					$("input[name='commodity']").each(function(){
						var c = this.value;
						var fid = this.id;
						var rid = fid.substring(9);
						
						for(var j=0; j<commodities.length-1; j++){
							if(c == commodities[j]){
								document.getElementById("commodityRate"+rid).value = prices[j];
								document.getElementById("commodityRate"+rid).setAttribute("readonly","readonly");
							}
						}
					});
				}
	        }
		});*/
		
	}
	
/** added by sourav.bhadra on 06-03-2018 **/
function calculateAmount(index){
	var commodityRate = document.getElementById("commodityRate"+index).value;
	var demamdedQuantity = document.getElementById("commodityDemandedQuantity"+index).value;
	if(null == commodityRate || commodityRate == "" || isNaN(commodityRate)){
		commodityRate = 0.0;
		document.getElementById("commodityRate"+index).value = "0.0";
	}
	
	var amount = parseFloat(demamdedQuantity) * parseFloat(commodityRate);
	document.getElementById("amount"+index).value = amount;
	calculateTotalAmount();
}

function calculateTotalAmount(){
	var totalAmount = 0.0;
	$("input[name='amount']").each(function(){
		var amount = this.value;
		if(isNaN(amount)){
			amount = 0.0;
		}
		totalAmount = parseFloat(totalAmount) + parseFloat(amount);
	});
	document.getElementById("totalAmount").value = totalAmount;
}