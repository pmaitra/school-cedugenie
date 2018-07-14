$("#subDepartment").change(function(){
	document.getElementById("subDeptPercentage").removeAttribute("readonly");
});

function calculateSubDeptAmount(){
	var budgetAmount = document.getElementById("allocatedAmount").value;
	var availableAmt = document.getElementById("unreserveFund").value;
	var percentageValue = document.getElementById("subDeptPercentage").value;
	var calculatedValue = (budgetAmount)*(percentageValue)/100;
	document.getElementById("subDeptAmount").value = calculatedValue;
	var remaining = availableAmt - calculatedValue;
	document.getElementById("availableFund").value = remaining;
}