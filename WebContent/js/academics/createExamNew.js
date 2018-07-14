$("#termName").change(function(){
	$.ajax({
	    url: '/icam/getExamTypesForATerm.html',
	    dataType: 'json',
	    data:"term=" + $(this).val(),
	    success: function(data) {
	    	var options='<option value="">Select...</option>';
	    	if(data!=null && data != ""){
    			var examTypeArr = data.split("*");
    			for(var i=0; i<examTypeArr.length - 1; i++){
    				var examType = examTypeArr[i].split("#");
    				options = options + '<option value="'+examType[0]+'">'+examType[1]+'</option>';
    			}
	    	}
	    	document.getElementById("examType").innerHTML=options;
	    }
	});
});