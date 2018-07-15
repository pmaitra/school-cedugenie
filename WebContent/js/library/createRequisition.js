$(document).ready(function() {
	var genreData = null;
	var departmentData = null;
	
	var i = 1;
	$("#addRequisitionbButton").click(function(){
		var table = document.getElementById('booktable');
		var rowCount = table.rows.length;
	    var row = table.insertRow(rowCount);
	   
	    var cell0 = row.insertCell(0);
	    var element0 = document.createElement("input");
	    element0.type = "text";
		element0.name="bookName";
		element0.id="bookName"+i;
		element0.setAttribute("class","form-control");
		element0.setAttribute("onfocus" , "getBookName(this);");
	    cell0.appendChild(element0);
	    
		var cell1 = row.insertCell(1);
	    var element1 = document.createElement("input");
	    element1.type = "text";
	    element1.value="";
	    element1.name="bookAuthor";
	    element1.setAttribute("class","form-control");
	    element1.setAttribute("onfocus","getAuthorName(this);");
	    element1.setAttribute("onblur","submitAction(this);");
	    element1.id="bookAuthor"+i;
		cell1.appendChild(element1);
		
		var addAuthorElement = document.createElement('a');
		addAuthorElement.setAttribute("onclick","return addAuthorName(this);");
		addAuthorElement.setAttribute("class","fa fa-plus-square");
		addAuthorElement.setAttribute("href","#");
		cell1.appendChild(addAuthorElement);
		
		
		var cell2 = row.insertCell(2);
	    var element2 = document.createElement("input");
	    element2.type = "text";
	    element2.name="bookEdition";
	    element2.id="bookEdition"+i;
	    element2.setAttribute("onfocus","getEdition(this);");
	    element2.setAttribute("class","form-control");
	    element2.value="";
		cell2.appendChild(element2);
		
		var cell3 = row.insertCell(3);
		var element3 = document.createElement("select");
		element3.name = "genreName";
		element3.id = "genreName"+i;
		var genreId = "genreName"+i;
		element3.setAttribute("class","form-control");
		element3.setAttribute("required","required"); /* added by sourav.bhadra on 05-09-2017 */
		cell3.appendChild(element3);
		var genreOptions = '<select name= \"genreName\" class=\"form-control\" id=\"'+ genreId + '><option value=\"\">Select...</option><option value=\"\">Select...</option>' + genreData + '</select>';
		document.getElementById(genreId).outerHTML = genreOptions;
		
		var cell4 = row.insertCell(4);
		var element4 = document.createElement("select");
		element4.name = "departmentName";
		element4.id = "departmentName"+i;
		var departmentId = "departmentName"+i;
		element4.setAttribute("class","form-control");
		cell4.appendChild(element4);
		var departmentOptions = '<select name= \"departmentName\" class=\"form-control\" id=\"'+ departmentId + '><option value=\"\">Select...</option><option value=\"\">Select...</option>' + departmentData + '</select>';
		document.getElementById(departmentId).outerHTML = departmentOptions;
		
		var cell5 = row.insertCell(5);
	    var element5 = document.createElement("input");
	    element5.type = "text";
	    element5.name="bookPublisher";
	    element5.id="bookPublisher"+i;
	    element5.setAttribute("onfocus","getPublisherName(this);");
	    element5.setAttribute("class","form-control");
	    element5.value="";
		cell5.appendChild(element5);
		
		var cell6 = row.insertCell(6);
	    var element6 = document.createElement("input");
	    element6.type = "text";
	    element6.name="numberOfBooksRequisitioned";
	    element6.id="numberOfBooksRequisitioned"+i;
	    element6.setAttribute("class","form-control");
	    cell6.appendChild(element6);
	    
	    var hiddenElement = document.createElement("input");
	    hiddenElement.type = "hidden";
	    hiddenElement.name="hiddenval";
	    hiddenElement.id="hiddenVal"+i;
		var cell7 = row.insertCell(7);
		cell7.appendChild(hiddenElement);
		
	 	var cancelElement = document.createElement("button");
	 	cancelElement.setAttribute("class", "mb-xs mt-xs mr-xs btn btn-danger");
	 	cancelElement.setAttribute("onclick", "deleteRow1(this);");
	 	cancelElement.innerHTML = "X";
	 	cell7.appendChild(cancelElement);
		
	 	i++;
	});
	
	$.ajax({
		url:"/cedugenie/getGenreListForCreateRequisition.html", 
        success:function(data){
        	genreData = data;
        },
        error:function(data){
        	alert("error : " + data);
        }
    });
	
	$.ajax({
		url:"/cedugenie/getDepartmentListForCreateRequisition.html",
        success:function(data){
        	departmentData = data;
        },
        error:function(data){
        	alert("error : " + data);
        }
    });
});




var p=1;
 function addAuthorName(thisName){
	 var bookAuthor="bookAuthor"+p;
	 var clonedNode = '<input type="text" class="form-control" id="'+bookAuthor+'" onblur="submitAction(this);" onfocus="getAuthorName(this);" name="bookAuthor" value=""/> <a href="#" class="on-default" name="" onclick="deleteThisRow(this, '+bookAuthor+');"><i class="fa fa-minus-square"></i></a>';
	 //clonedNode = clonedNode+'<img src="/sms/images/minus_icon.png" onclick="deleteThisRow(this, '+bookAuthor+');">';
	 //<br class="brClass">
	 p++;
	 $(thisName).parent().append(clonedNode);
 }

 function deleteThisRow(img,txt){
	 $(txt).remove();
	 var authorList = "";
	 $(img).parent().find('input').each(function() {
		 var len = $(img).parent().find('input').length;
			if(len == 1){
				authorList = $(this).val();
			}
			if(len > 1){
			authorList = $(this).val() + "," +authorList ;
			}
		});
	 var hidValId = $(img).parent().next().next().next().next().find('input').attr('id'); 
		$("#"+hidValId).val(authorList);
	 $(".brClass").each(function(){
		$(this).remove(); 
	 });
	 $(img).remove();
 }
function deleteRow(tableID)
{
        var table = document.getElementById(tableID);
        var rowCount = table.rows.length;
		var count=0;
		for(var i=3; i<rowCount; i++)
		{
			var row = table.rows[i];
			var chkbox = row.cells[0].childNodes[0];
			if(true == chkbox.checked)
			{
				count=count+1;
			}
		}

		if(rowCount<=count+2)
		{
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningbox").innerHTML ="Sorry, Atleast one Book with Number Of Copies Required";
			return false;
		}
		
        for(var i=0; i<rowCount; i++)
		{
            var row = table.rows[i];
            var chkbox = row.cells[0].childNodes[0];
            if(null != chkbox && true == chkbox.checked)
			{
                table.deleteRow(i);
                rowCount--;
                i--;
            }
        }
}

function deleteRow1(obj){
	var table = document.getElementById("booktable");
	var rowCount = table.rows.length;	

	if(rowCount>2){
		var p = obj.parentNode.parentNode;
		p.parentNode.removeChild(p);
	}else{
		alert("Atleast One Row Required");
	}
} 


function validateBookRequisition(){
	var regnum = '^[0-9]+$';
	var rname = /^[A-Za-z. ]{2,}$/;
	var bookName = document.getElementsByName("bookName");
	var bookAuthor = document.getElementsByName("bookAuthor");
	var bookEdition = document.getElementsByName("bookEdition");
	var bookPublisher = document.getElementsByName("bookPublisher");
	var numberOfBooksRequisitioned = document.getElementsByName("numberOfBooksRequisitioned");
	
	for(var i = 0; i<bookName.length; i++){
		var bName = bookName[i].value;
		if(bName == ""){
			alert("Please Enter a Book Name for Row "+(i+1)+".");
			return false;
		}
	}
	for(var i = 0; i<bookAuthor.length; i++){
		var bkAuthor = bookAuthor[i].value;
		if(bkAuthor == ""){
			alert("Please Provide all Author names.");
			return false;
		}
		if(bkAuthor != ""){
			if (!bkAuthor.match(rname)) {
				alert("Enter a valid Author name.");
				return false;
			}
		}
	}	
	for(var i = 0; i<bookEdition.length; i++){
		var bEdition = bookEdition[i].value;
		if(bEdition == ""){
			alert("Please Enter Book Edition for Row "+(i+1)+".");
			return false;
		}
	}
	for(var i = 0; i<bookPublisher.length; i++){
		var bPublisher = bookPublisher[i].value;
		if(bPublisher == ""){
			alert("Please Enter Publisher Name for Row "+(i+1)+".");
			return false;
		}
	}
	for(var i = 0; i<numberOfBooksRequisitioned.length; i++){
		var numberOfBooks = numberOfBooksRequisitioned[i].value;
		if(numberOfBooks == ""){
			alert("Please Enter Quantity for Row "+(i+1)+".");
			return false;
		}
		if(numberOfBooks != ""){
			if (!numberOfBooks.match(regnum)) {
				alert("Please Enter Numeric Quantity for Row "+(i+1)+".");
				return false;
			}
		}
	}
} 


var buttonName="";
$(".bookNameClass").each(function(){
	$(this).click(function(){
		buttonName=$(this);
		$.ajax({
			url:'/cedugenie/getBookName.html',
			dataType:'json',
			success:function(data){
				var allnames = data.split("*");
				$(buttonName).autocomplete({
					source:allnames
				});
			}
		});
	});
});

function getBookName(thisBookName){	
	$(thisBookName).autocomplete({
		source: '/sms/getBookName.html'
	});	
};

function getAuthorName(thisBookName){	
	$(thisBookName).autocomplete({
		source: '/sms/getAuthorName.html'
	});		
};



 function getEdition(thisBookName){	
	$(thisBookName).autocomplete({
		source: '/sms/getEdition.html'
	});		
};

function getPublisherName(thisBookName){	
	$(thisBookName).autocomplete({
		source: '/sms/getPublisherName.html'
	});		
}; 

function submitAction(thisName){
	var authorList = "";
	//authorList = $(thisName).val()+","+authorList;
	$(thisName).parent().find('input').each(function() {
		var len = $(thisName).parent().find('input').length;
		if(len == 1){
			authorList = $(this).val();
		}
		if(len > 1){
		authorList = $(this).val() + "~" +authorList ;
		}
	});
	/*var objects =document.getElementsByName("bookAuthor");
	var authorList = "";
	for(var i=0;i<objects.length;i++){
		authorList = authorList+ "," +objects[i].value;
	}	
	authorList = authorList.substring(0,authorList.length);
	if(authorList.length>=1){
		var x = authorList.substring(0,1);
		if(x==','){
			authorList = authorList.substring(1,authorList.length);
		}
	}*/
	var hidValId = $(thisName).parent().next().next().next().next().find('input').attr('id'); 
	$("#"+hidValId).val(authorList);
}