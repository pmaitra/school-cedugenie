$(document).ready(function(){
	$.ajax({
		url:'/icam/getCommodityListForHostelExpense.html',
		dataType:'json',
		success:function(data){
			alert(data);
			var allCommoditynames = data.split("*");
			$("#commodity0").autocomplete({
				source: allCommoditynames,
				select: function (event, ui){
					var commodity0 = ui.item.value;
					$.ajax({
						url: '/icam/getCommodityStock.html',
						dataType: 'json',
						data: "commodityName=" + commodity0,
						success: function(data) {
							alert(data);
							if(data != null && data!=""){
								($("#stock0").val(data));
							}
							else{
								alert("Stock Not Found");
							}
						}
					});
				}
			});
		}
	});	
});
function auto(commodityId,index){
	$.ajax({
		url:'/icam/getCommodityListForHostelExpense.html',
		dataType:'json',
		success:function(data){
			alert(data);
			var allCommoditynames = data.split("*");
			$(commodityId).autocomplete({
				source: allCommoditynames,
				select: function (event, ui){
					var commodityId = ui.item.value;
					$.ajax({
						url: '/icam/getCommodityStock.html',
						dataType: 'json',
						data: "commodityName=" + commodityId,
						success: function(data) {
							alert(data);
							if(data != null && data!=""){
								document.getElementById("stock"+index).value = data;
							}
							else{
								alert("Stock Not Found");
							}
						}
					});
				}
			});
		}
	});
}
var count=1;
$('.addbtn').click(function(){
	
	var table = document.getElementById("hostelExpense");
	var rowCount = table.rows.length;
	var row = table.insertRow(rowCount);          
	var cell, element;
	var commodityData = null;
	
	cell = row.insertCell(0);
	element = document.createElement("input");
	element.type = "text";
	element.name="commodity";
	element.className="form-control commodityNameClass ";
	element.id="commodity"+count;
	cell.appendChild(element);

	cell = row.insertCell(1);
	element = document.createElement("input");
	element.type = "text";
	element.name="stock";
	element.className="form-control";
	element.id="stock"+count;
	//element.value="0.00";
	element.setAttribute("readonly" , "readonly");
	cell.appendChild(element);

	cell = row.insertCell(2);
	element = document.createElement("input");
	element.type = "text";
	element.name="morning";
	element.className="form-control";
	element.id="morning"+count;
	element.setAttribute("onfocus",'this.value=""');
	element.setAttribute("onblur","val(this)");
	element.value="0.00";
	cell.appendChild(element);

	cell = row.insertCell(3);
	element = document.createElement("input");
	element.type = "text";
	element.name="noon";
	element.className="form-control";
	element.id="noon"+count;
	element.setAttribute("onfocus",'this.value=""');
	element.setAttribute("onblur","val(this)");
	element.value="0.00";
	cell.appendChild(element);

	cell = row.insertCell(4);
	element = document.createElement("input");
	element.type = "text";
	element.name="evening";
	element.className="form-control";
	element.id="evening"+count;
	element.setAttribute("onfocus",'this.value=""');
	element.setAttribute("onblur","val(this)");
	element.value="0.00";
	cell.appendChild(element);

	cell = row.insertCell(5);
	element = document.createElement("input");
	element.type = "text";
	element.name="night";
	element.className="form-control";
	element.id="night"+count;
	element.setAttribute("onfocus",'this.value=""');
	element.setAttribute("onblur","val(this)");
	element.value="0.00";
	cell.appendChild(element);
	
	cell = row.insertCell(6);
	element = document.createElement("a");
	element.setAttribute("href","#");
	element.setAttribute("class","fa fa-minus-square");		
	element.setAttribute("onclick", "deleteRow(this);");		
	cell.appendChild(element);
	var commodityId = "#commodity"+count;
	alert("commodityId::"+commodityId);
	count++;
	newIndex = count - 1;
	auto(commodityId,newIndex);
});

function deleteRow(obj){
	alert("in deleterow method");
	var table = document.getElementById("hostelExpense");
	var rowCount = table.rows.length;
	alert("rwoCount:"+rowCount);
	if(rowCount>=3){
		var p = obj.parentNode.parentNode;
		p.parentNode.removeChild(p);
	}
}

/*function getCommodityAutoComplete(idBox){
	$(idBox).autocomplete({
		source: '/icam/getCommodityAutoComplete.html'
	});
};*/

/*function getStock(idBox){	
	var idCount=idBox.id.replace("commodity","");
	var commodityStockId ="stock"+idCount;
	
	$.ajax({
	    url: '/sms/getCommodityStock.html',
	    dataType: 'json',
	    data: "commodityCode=" + ($(idBox).val()),
	    success: function(data) {	    	
	    	if(data != null && data != "")
	    		document.getElementById(commodityStockId).value=data;
	    	else
	    		document.getElementById(commodityStockId).value="0.00";
	    }
	});	
};*/

/*function validateHostelExpense(){
	
	var commodityNames = document.getElementsByName("commodity");
	var commodityStocks = document.getElementsByName("stock");
	
	var mornings = document.getElementsByName("morning");
	var noons = document.getElementsByName("noon");
	var evenings = document.getElementsByName("evening");
	var nights = document.getElementsByName("night");
	
	for(var i=0;i<commodityNames.length;i++){
		var sto=0.00;
		var exp=0.00;
		
		commodityNames[i].value=commodityNames[i].value.replace(/\s{1,}/g,'');
		if(commodityNames[i].value == ""){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Commodity Name Not Provided";	
			return false;
		}
		else{
			document.getElementById("warningbox").style.visibility='collapse';
			
			commodityStocks[i].value=commodityStocks[i].value.replace(/\s{1,}/g,'');
			if(commodityStocks[i].value == ""){
				document.getElementById("warningbox").style.visibility='visible';
				document.getElementById("warningmsg").innerHTML=commodityNames[i].value+" Stock Is Empty";
				return false;
			}else{
				if(isNaN(commodityStocks[i].value)){
					document.getElementById('warningbox').style.visibility="visible";
					document.getElementById('warningmsg').innerHTML=commodityNames[i].value+" Stock Is Not A Number";
					return false;		
				}else{
					var s=parseFloat(commodityStocks[i].value);
					if(s<0.0){
						document.getElementById('warningbox').style.visibility="visible";
						document.getElementById('warningmsg').innerHTML=commodityNames[i].value+" Stock Is Negative";
						return false;	
					}else{
						sto=s;
					}
				}
				
				mornings[i].value=mornings[i].value.replace(/\s{1,}/g,'');
				if(mornings[i].value == ""){
					document.getElementById("warningbox").style.visibility='visible';
					document.getElementById("warningmsg").innerHTML=commodityNames[i].value+"'s Morning Consumption Is Empty";
					return false;
				}else{
					if(isNaN(mornings[i].value)){
						document.getElementById('warningbox').style.visibility="visible";
						document.getElementById('warningmsg').innerHTML=commodityNames[i].value+"'s Morning Consumption Is Not A Number";
						return false;		
					}else{
						var s=parseFloat(mornings[i].value);
						if(s<0.0){
							document.getElementById('warningbox').style.visibility="visible";
							document.getElementById('warningmsg').innerHTML=commodityNames[i].value+"'s Morning Consumption Is Negative";
							return false;	
						}else{
							exp=exp+s;
						}
					}
				}
				
				noons[i].value=noons[i].value.replace(/\s{1,}/g,'');
				if(noons[i].value == ""){
					document.getElementById("warningbox").style.visibility='visible';
					document.getElementById("warningmsg").innerHTML=commodityNames[i].value+"'s Noons Consumption Is Empty";
					return false;
				}else{
					if(isNaN(noons[i].value)){
						document.getElementById('warningbox').style.visibility="visible";
						document.getElementById('warningmsg').innerHTML=commodityNames[i].value+"'s Noons Consumption Is Not A Number";
						return false;		
					}else{
						var s=parseFloat(noons[i].value);
						if(s<0.0){
							document.getElementById('warningbox').style.visibility="visible";
							document.getElementById('warningmsg').innerHTML=commodityNames[i].value+"'s Noons Consumption Is Negative";
							return false;	
						}else{
							exp=exp+s;
						}
					}
				}
				
				
				evenings[i].value=evenings[i].value.replace(/\s{1,}/g,'');
				if(evenings[i].value == ""){
					document.getElementById("warningbox").style.visibility='visible';
					document.getElementById("warningmsg").innerHTML=commodityNames[i].value+"'s Evening Consumption Is Empty";
					return false;
				}else{
					if(isNaN(evenings[i].value)){
						document.getElementById('warningbox').style.visibility="visible";
						document.getElementById('warningmsg').innerHTML=commodityNames[i].value+"'s Evening Consumption Is Not A Number";
						return false;		
					}else{
						var s=parseFloat(evenings[i].value);
						if(s<0.0){
							document.getElementById('warningbox').style.visibility="visible";
							document.getElementById('warningmsg').innerHTML=commodityNames[i].value+"'s Evening Consumption Is Negative";
							return false;	
						}else{
							exp=exp+s;
						}
					}
				}
				
				
				
				nights[i].value=nights[i].value.replace(/\s{1,}/g,'');
				if(nights[i].value == ""){
					document.getElementById("warningbox").style.visibility='visible';
					document.getElementById("warningmsg").innerHTML=commodityNames[i].value+"'s Night Consumption Is Empty";
					return false;
				}else{
					if(isNaN(nights[i].value)){
						document.getElementById('warningbox').style.visibility="visible";
						document.getElementById('warningmsg').innerHTML=commodityNames[i].value+"'s Night Consumption Is Not A Number";
						return false;		
					}else{
						var s=parseFloat(nights[i].value);
						if(s<0.0){
							document.getElementById('warningbox').style.visibility="visible";
							document.getElementById('warningmsg').innerHTML=commodityNames[i].value+"'s Night Consumption Is Negative";
							return false;	
						}else{
							exp=exp+s;
						}
					}
				}
				
				if(sto<exp){
					document.getElementById('warningbox').style.visibility="visible";
					document.getElementById('warningmsg').innerHTML=commodityNames[i].value+"'s Consumption Is Greater Than Stock";
					return false;	
				}
				
			}
		}
	}
	return true;
}*/