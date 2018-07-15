/* added by sourav.bhadra on 19-04-2018 */
function transactionWorkingAreaEdit(index){
	var transactionCode = document.getElementById("transactionCode"+index).value;
	var selectedLedger = document.getElementById('ledgerCode'+index).value;
	$.ajax({
	    url: '/cedugenie/transactionWorkingAreaEdit.html',
	    data: "twaCode=" +transactionCode,
	    dataType: 'json',
	    success: function(data) {
	    	if(data != null && data != ""){
	    		var array = data.split("#");
	    		var ledgerArr = array[0].split(";");
	    		select = document.getElementById('selectledgerCode'+index);
	    		var i;
    			for (i = 1;i<ledgerArr.length;i++){
	    			var arr = ledgerArr[i].split("*");
	    		    var opt = document.createElement('option');
	    		    opt.value = arr[0];
	    		    opt.text  = arr[1];
	    		    if(selectedLedger == arr[0]){
	    		    	opt.setAttribute("selected","selected");
	    		    }
	    		    select.appendChild(opt);
	    		
	    		document.getElementById('selectledgerCode'+index).style.display = "block";
	    		document.getElementById('ledgerCode'+index).style.display = "none";
	    		document.getElementById('edit'+index).style.display = "none";
	    		document.getElementById('save'+index).style.display = "block";
	    		document.getElementById('cancel'+index).style.display = "block";
    		}
	    		
	    	}else{	    		
	    	
	    	}
    	}  
	});			
}

function saveData(rowId){
	document.getElementById("saveId").value=rowId;
	document.updateTransactionWorkingArea.submit();
};

/* added by sourav.bhadra on 19-04-2018 */
function cancelEdit(index){
	document.getElementById('selectledgerCode'+index).style.display = "none";
	document.getElementById('ledgerCode'+index).style.display = "block";
	document.getElementById('edit'+index).style.display = "block";
	document.getElementById('save'+index).style.display = "none";
	document.getElementById('cancel'+index).style.display = "none";
}