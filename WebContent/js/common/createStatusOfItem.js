/**
 * @author anup.roy
 */
function validateStatusCreation(){
	var alphaNumeric=/^[A-Za-z0-9 ]{1,50}$/;
	
	var obj = document.getElementById("socialCategoryName");
	var socialCategoryName=obj.value;
	socialCategoryName = socialCategoryName.replace(/\s{1,}/g,' ');
	socialCategoryName = socialCategoryName.trim();
	socialCategoryName = socialCategoryName.toUpperCase();
	obj.value=socialCategoryName;
	
	/*if (socialCategoryName == "") {
		document.getElementById("javascriptmsg").style.display = 'block';			
		document.getElementById("javascriptmsg").innerHTML = "Please Enter Job Type";		
		return false;
	}*/
	if (socialCategoryName != '') {
		if (!socialCategoryName.match(alphaNumeric)) {
			document.getElementById("javascriptmsg").style.display = 'block';			
			document.getElementById("javascriptmsg").innerHTML = "Invalid Category Name.";	
			return false;
		}
	}
	var oldCategoryNames = document.getElementsByName('oldCetegoryNamesForDuplicateCheck');
	for(var i=0; i<oldCategoryNames.length;i++){
		if(oldCategoryNames[i].value == socialCategoryName){			
			document.getElementById("javascriptmsg").style.display = 'block';
			document.getElementById("javascriptmsg").innerHTML = "Social Category Name Already Exists!";
			return false;
		}
	}
	return true;
}
	
function validateEditSocialCategory(rowId){
	/*var alphaNumeric=/^[A-Za-z0-9 ]{1,50}$/;
	var lettersOnly=/^([a-zA-Z]+\s)*[a-zA-Z]+$/;
	var obj = document.getElementById(rowId);
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	val=val.toUpperCase();
	obj.value=val;
	if (val == "") {
		document.getElementById("javascriptmsg").style.display = 'block';			
		document.getElementById("javascriptmsg").innerHTML = "Please Enter A Category Name.";
		return false;
	}
	else{
		document.getElementById("javascriptmsg").style.display = 'block';			
		document.getElementById("javascriptmsg").innerHTML = "";
	}
	if(!val.match(lettersOnly)){
		document.getElementById("javascriptmsg").style.display = 'block';			
		document.getElementById("javascriptmsg").innerHTML = "Invalid Category Name.";
		return false;
	}
	else{		
		document.getElementById("javascriptmsg").style.display = 'block';			
		document.getElementById("javascriptmsg").innerHTML = "";
	}
		*/
	
	var categoryName=document.getElementById("categoryName").value;
	var alphaNumeric=/^[a-zA-Z \s]+$/;
	//var olddesignationTypeName=document.getElementsByName("oldDesgnationTypeNamesforDuplicateChecking");
	var newCategoryName = document.getElementById("getCategoryType").value;
	newCategoryName=newCategoryName.trim();
	newCategoryName=newCategoryName.toUpperCase();
	var categoryList = document.getElementsByName("oldCetegoryNamesForDuplicateCheck");		
	for(var i=0; i<categoryList.length; i++){
		var oldVal=categoryList[i].value;
		if(oldVal==newCategoryName){
			document.getElementById("warningmsg1").style.display = 'block';			
			document.getElementById("warningmsg1").innerHTML = " Duplicate Social Category Name!";
			return false;
		}
	}
	if(categoryName ==""|| categoryName==null ){
		document.getElementById("warningmsg1").style.display = 'block';			
		document.getElementById("warningmsg1").innerHTML = "Enter Social category Name"; 
		return false;
	}else if (alphaNumeric.test(categoryName)==false)
    {
    	document.getElementById("warningmsg1").style.display = 'block';			
		document.getElementById("warningmsg1").innerHTML = "Social Category name can contain alphabets and spaces between words !!";
		return false;
    }else
		{
		return true;
		}
}