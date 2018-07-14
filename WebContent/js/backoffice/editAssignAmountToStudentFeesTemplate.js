function makepageEditbale(){
	var i;
	$("#reset").css('display','block');
	$("#submit").css('display','block');
	$("#submit").css('float','left');
	$("#editButton").css('display','none');
	var input = document.getElementsByTagName("input");	
	for(i=0;i<input.length;i++){
		input[i].removeAttribute("disabled");
	}
}
	
window.onload=function(){
	
	var tb=document.getElementsByClassName("textfield2");
	for(var i=0;i<tb.length;i++){
		calculateTotal(tb[i].id);
	}
};

function removeZero(tBox){
	if(tBox.value=="0.00"){
		tBox.value="";
	}
}

function setZero(tBox,totBx){
	if(tBox.value==""){
		tBox.value="0.00";
	}		
	if(isNaN(tBox.value)){
		alert("Please enter a valid value(Numeric)");
		tBox.value="0.00";
	}else{
		var p=parseFloat(tBox.value);
		if(p<0.0){
			alert("Please enter a no greater than zero");
			tBox.value="0.00";
		}
	}
	calculateTotal(totBx);
}

function calculateTotal(totBx){
	document.getElementById(totBx).value="0.00";
	var tb = document.getElementsByClassName("textfield1");
	for(var i=0;i<tb.length;i++){
		var tName=tb[i].name.split("##");
		tName=tName[1];
		var checker=totBx.replace("Total","");
		if(tName==checker){
			var tot=parseFloat(document.getElementById(totBx).value);
			tot=tot+parseFloat(tb[i].value);
			document.getElementById(totBx).value=tot;
		}
	}	
}