<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ include file="/file/sessionDataForChildPages.txt"%>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Student Details Form</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       } .mb-md{
       	   display: none;
       }
</style>
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/radio.js"></script>
<script type="text/javascript">
function makeEditable(rowId){
	alert("rowId=="+rowId);
	rowId=rowId.replace("edit","");
	document.getElementById("accessTypeName"+rowId).removeAttribute("readonly");
	document.getElementById("accessTypeDesc"+rowId).removeAttribute("readonly");
	
	
};
function saveData(rowId){
	rowId=rowId.replace("save","");
	alert("In saveData :: "+rowId);
	window.location="test.html?saveId="+rowId;
};
</script>
</head>
<body>

					<c:if test="${successMessage ne null}">
						<div class="alert alert-success" id="successboxmsgbox" >
							<strong>${successMessage}</strong>	
						</div>
					</c:if>
			
					<c:if test="${errorMessage ne null}">
							<div class="alert alert-danger" id="errormsgbox" >
								<strong>${errorMessage}</strong>	
							</div>
					</c:if>
	 <div class="row">
						<div class="col-md-8 col-md-offset-2">
                            <form method="POST" action="createNewAccessType.html" name="accessTypeRoleMappingList" id="accessTypeRoleMappingList">
                                <section class="panel">
                                <header class="panel-heading">
                                    <div class="panel-actions">
                                        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                                    </div>

                                    <h2 class="panel-title">Existing Access Type(s)</h2>
                                </header>
                                <c:choose>
									<c:when test="${pagedListHolder eq null}">
										<div class="errorbox" id="errorbox" style="visibility: visible;">
											<span id="errormsg">No Role Access Type Mapping List Found</span>	
										</div>						
									</c:when>
								<c:otherwise>
                                <div class="panel-body">
                                    <table class="table table-bordered table-striped mb-none" id="datatable-editable">
                                        <thead>
                                            <tr>
                                                <th>Access Type Name</th>
                                                <th>Access Type Description</th>
                                                <th>Actions</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        	<c:forEach var="accessType"  items="${pagedListHolder}"  varStatus = "i">
                                            <tr>
                                                <td><input type="text" class="form-control" name="accessTypeName${i.index}" id = "accessTypeName${i.index}" placeholder="" value="${accessType.accessTypeName}" readonly/></td>
                                                <td><input type="text" class="form-control" name="accessTypeDesc${i.index}" id="accessTypeDesc${i.index}" placeholder="" value="${accessType.accessTypeDesc}" readonly/>
                                                <input type="hidden" name="accessTypeRadio${i.index}" value="${accessType.accessTypeCode}"/></td>
                                                <td>
<%--                                                     <a href="createNewAccessType.html" class="on-default edit-row" id="edit${i.index}" name ="edit${i.index}" onclick="return validateradio('accessTypeRadio')"><i class="fa fa-pencil"></i></a> --%>
<!--                                                     <a href="#" class="hidden on-editing save-row" id="save"><i class="fa fa-save"></i></a> -->
                                                		 <a href="editAccessType.html?accessTypeRadio=${accessType.accessTypeCode}" class="mb-xs mt-xs mr-xs modal-basic btn btn-info">Details</a>
                                                </td>
                                            </tr>
                                            </c:forEach>

                                        </tbody>
                                    </table>
                                </div> 
                                </c:otherwise>
                                </c:choose>
                                <footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary">Create </button>
										<button type="reset" class="btn btn-default">Reset</button>
								</footer>
                            </section>
                            </form>
						</div>
					</div>	
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>