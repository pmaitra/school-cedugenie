<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Policy Configuration</title>
<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
<header class="page-header">
	<h2>Policy Configuration</h2>
</header>
<div class="content-padding">
	<c:choose>
		<c:when test="${resourceTypeList eq null}">	
			<div class="errorbox" id="errorbox" style="visibility:visible;">
				<span id="errormsg">No Resource Type Found</span>
			</div>					
		</c:when>
	<c:otherwise>
 		<div class="row">
			<div class="col-md-12">
	  			<form:form name="libraryPolicy" method="POST" action="saveLibraryPolicy.html">
					<section class="panel">
						<header class="panel-heading">
							<div class="panel-actions">
								<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
							</div>
							<h2 class="panel-title">Library Policy</h2>
						</header>
						<div style="display: block;" class="panel-body">
                        	<div class="row">
                            	<div class="col-md-3">
                                	<div class="form-group">
                                    	<label class="control-label">Resource Type <span aria-required="true" class="required">*</span></label>
                                       	<select class="form-control" id="resourceTypeSelect" name="resourceTypeSelect" required>
                                           	<option value="">Select...</option>
                                       		<c:forEach var="resourceType" items="${resourceTypeList}">
												<option value="${resourceType.resourceTypeName}">${resourceType.resourceTypeName}</option>
											</c:forEach>
                                       	</select>
                               		</div>
                           		</div>
                                <br/>
								<div class=warningbox id="warningbox" >
									<span id="waringmsg"></span>	
								</div>
                               	<div class="col-md-9" id="policyDetails" style="display: none">
                           			<u style="text-transform: uppercase;"><span id="titleToShow"></span></u><br>
                                  	<p>
                                    	Max No. Of requisition that can be raised <input type="text" class="form-control" name="maxNoOfBookReq" id="maxNoOfBookReq" value = "" style="width:100px; display: inline-block;"><br>
                                    	Max No. Of books per requisition <input type="text" class="form-control" name="maxNoOfBooksPerReq" id="maxNoOfBooksPerReq" value = "" style="width:100px; display: inline-block;"><br>
                                      	Max borrowing period for books (Depends On Book Rating)
                                  	</p>
                                  	<table id="showRating" class="table table-bordered table-striped mb-none" style="width:150px;"></table>
                                  	<p>
                                      	Max No. Of continuous requisition for same books by same person <input type="text" class="form-control" name="sameBookAcrossReq" id="sameBookAcrossReq" value = "" style="width:100px; display: inline-block;"><br>
                                      	Max No. Of allocated book for a person <input type="text" class="form-control" name="maxNoOfBookAllocated" id="maxNoOfBookAllocated" value = "" style="width:100px; display: inline-block;"><br>
                                  	</p>
                                  	<h3>Fine Policy</h3>
                                  	<p>
                                  		1. Library Fine Will Be Charged @ <input type="text" class="form-control" name="finePerDay" id="finePerDay" value = "" style="width:100px; display: inline-block;"> Per Day Per Book, With A Max Of <input type="text" class="form-control"  name="maxFine" id="maxFine" value = "" style="width:100px; display: inline-block;"> After the Fine Has Reached Its Max Limit.<br>
                                        2. The Borrower Will Have To Pay A Penalty Of <input type="text" class="form-control" name="overDueFine" id="overDueFine"  value = ""style="width:100px; display: inline-block;"> And May Be Barred From Borrowing Privileges<br>
                                        3. The Decision Of Library Authority is Final In This Regard.<br>
                                        4. In Case Of Damage/Lost Books The Borrower Will Have To Pay A Penalty Of <input type="text" class="form-control" name="processingFee" id="processingFee" value= "" style="width:100px; display: inline-block;">  Along With The Damage/LostCharge Which Will Be Decided By The Librarian.
                                    </p>
                              	</div>
                          	</div>
						</div>
						<footer style="display: block;" class="panel-footer">
							<button class="btn btn-primary" type= "submit" id="submitLibraryPolicy" name="submitLibraryPolicy">Submit </button>
							<button type="reset" class="btn btn-default" name="reset" >Reset</button>
						</footer>
					</section>
              	</form:form>
			</div>
		</div>	
   	</c:otherwise>
    </c:choose>
</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript">
var retval = null;
$(document).ready(function(){
	$("#submitLibraryPolicy").click(function(){
		$(".textfield1").each(function(){
			var valNumeric = parseInt($(this).val());
			if(isNaN(valNumeric)){
				document.getElementById("warningbox").style.display='block';
				document.getElementById("waringmsg").innerHTML="Please Enter the input in neumerical format !";
				retval = false;
				return false;
			}
			else{
				document.getElementById("warningbox").style.display='none';
				document.getElementById("waringmsg").innerHTML="";
				retval = true;
				return true;
			}
		});
		return retval;
	});
	$("#resourceTypeSelect").change(function(){	
		var resourceTypeSelected = $(this).val();
		if(resourceTypeSelected != ""){
			document.getElementById("warningbox").style.display='none';
			document.getElementById("waringmsg").innerHTML="";
			$.ajax({
		        url: '/cedugenie/getlibraryToShow.html',
		        data: "resourceTypeSelect=" + ($(this).val()),
		        dataType: 'json',
		        success: function(data) {        		
		        
		    		document.getElementById("titleToShow").innerHTML=resourceTypeSelected+" BOOK POLICY";
	        		document.getElementById("policyDetails").style.display='block';
	        		var table = document.getElementById("showRating");
	        		var splitedData = data.split("^");
	        		var libraryPolicySplited = splitedData[0].split("#");
	        		var libraryRatingSplited = splitedData[1].split("$");
	        			
        			$("#maxNoOfBookReq").val(libraryPolicySplited[0]);
        			$("#maxNoOfBooksPerReq").val(libraryPolicySplited[1]);
        			$("#sameBookAcrossReq").val(libraryPolicySplited[2]);
        			$("#finePerDay").val(libraryPolicySplited[3]);
        			$("#maxFine").val(libraryPolicySplited[4]);
        			$("#overDueFine").val(libraryPolicySplited[5]);
        			$("#processingFee").val(libraryPolicySplited[6]);
					$("#maxNoOfBookAllocated").val(libraryPolicySplited[7]);
        			for(var i = table.rows.length-1 ; i >= 0; i--){
        				table.deleteRow(i);
        			}
        			
	        		for(var j=0;j<libraryRatingSplited.length;j++){
	        			var splitedRating = libraryRatingSplited[j].split("%%");
	        	        var row = table.insertRow(0);
	        	        var id1="rating"+j;        	       
	        	    	var id2="maxLendingPeriod"+j;
	        	        var rowText="<tr><td>"+
	        	        "<input type='text' name='rating' id='"+id1+"' class='textfield1' value='"+splitedRating[0]+"'/></td>" +
	        	        "<td><input type='text' name='maxLendingPeriod' id='"+id2+"' class='textfield1' value='"+splitedRating[1]+"'/></td></tr>" ;		        	       
	        	        row.innerHTML=rowText;
	        		}
	        	}
			});
		}
		if(resourceTypeSelected == ""){
			document.getElementById("policyDetails").style.display='none';
			document.getElementById("titleToShow").style.display='none';
		}
	});
});

</script>
</body>
</html>