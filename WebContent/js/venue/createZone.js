function makeEditable(rowId){
		rowId=rowId.replace("edit","");
		//document.getElementById("standardName"+rowId).removeAttribute("readonly");
		document.getElementById("zoneName"+rowId).removeAttribute("readonly");
		document.getElementById("zoneDesc"+rowId).removeAttribute("readonly");
	};
	function saveData(rowId){
		rowId=rowId.replace("save","");
		alert("In saveData :: "+rowId);
		/*var status = editZone(rowId);
		if(status == true){*/
			document.getElementById("saveId").value = rowId;
			document.editZone.submit();
		//}
	};
