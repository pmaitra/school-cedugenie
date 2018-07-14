var sResultFileSize = '';

function fileSelected() {
    // get selected file element
    var oFile = document.getElementById('image_upload').files[0];    
    // filter for image files
    var rFilter = /^(image\/bmp|image\/gif|image\/jpeg|image\/png|image\/tiff)$/i;
    if (! rFilter.test(oFile.type)) {
        document.getElementById('error').innerHTML="Please select Valid Image File";
        return false;
    }
    // get preview element
    var oImage = document.getElementById('preview');

    // prepare HTML5 FileReader
    var oReader = new FileReader();
        oReader.onload = function(e){

        // e.target.result contains the DataURL which we will use as a source of the image
        oImage.src = e.target.result;

        oImage.onload = function () { // binding onload event

        	var x = document.getElementById('image_upload'); // get the file input element in your form
        	var f = x.files.item(0); // get only the first file from the list of files
        	var filesize = f.size;
        	alert("the size of the image is : "+filesize+" bytes");
        	//1048576 byte = 1 MB.
        	if(filesize > 1048576){
        		alert("File size is greater then required");
        		document.getElementById('error').innerHTML="File size is greater then required";
        		return false;
        	}        	

        	var i = new Image();
        	var reader = new FileReader();
        	reader.readAsDataURL(f);
        	i.src=reader.result;        	    
        };
    };

    // read selected file as DataURL
    oReader.readAsDataURL(oFile);
    return true;
}

function docSelected() {
var docFile = document.getElementById('fileData0').files[0];  
 //filter for Document files
var reg = /^(([a-zA-Z]:)|(\\{2}\w+)\$?)(\\(\w[\w].*))+(.doc|.docx|.DOC|.DOCX)$/;	 
var rFilter = /^(application\/pdf|application\/PDF|text\/plain|TEXT\/plain)$/i;

if (! (rFilter.test(docFile.type)|| reg.test(docFile))) {
    document.getElementById('error').innerHTML="Please select Valid Document File";
    return false;
}

}