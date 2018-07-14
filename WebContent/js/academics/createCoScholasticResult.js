function showHideTR(sel){
	if(sel.value=="OTHERS"){
		document.getElementById(sel.id.split("-")[0]+"-Others").style.visibility="visible";
	}else{
		document.getElementById(sel.id.split("-")[0]+"-Others").style.visibility="collapse";
	}
}


window.onload=function(){
	var x=getElementsByClassName("midsec1");
	var maxWidth=0;
	for(var i=0;i<x.length;i++)
		if(x[i].offsetWidth>maxWidth)
			maxWidth=x[i].offsetWidth;
			
	for(var i=0;i<x.length;i++)
		x[i].width=maxWidth;
};