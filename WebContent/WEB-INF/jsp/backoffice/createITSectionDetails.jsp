<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 <%@ include file="/file/sessionDataForChildPages.txt"%>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Student Details Form</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }.mb-md{
       	   display: none;
       }
</style>
</head>
<body>
			<c:choose>
				<c:when test="${itSectionList == null}">
					<div class="errorbox" id="errorbox" style="visibility:visible;">
						<span id="errormsg">No IT Section Found</span>	
		 			</div>
				</c:when>	
				<c:otherwise>


 					<div class="row">
						<div class="col-md-6 col-md-offset-3">
						  <form:form name="createITSectionRebates" action="createITSectionRebates.html" method="POST">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>
										<h2 class="panel-title">Create/Edit Rebates Under IT Sections</h2>										
									</header>
									<div style="display: block;" class="panel-body">
										<div class="form-group">
											<label class="col-sm-5 control-label">IT Section</label>
											<div class="col-sm-7">
												<select class="form-control" name="itSectionCode" id="itSectionCode" required>
                                                    <option value="">Select...</option>
                                                    <c:forEach var= "itSec" items="${itSectionList}">									
														<option value="${itSec.itSectionCode}">${itSec.itSectionName}</option>					
													</c:forEach>
                                                </select>
											</div>
										</div>
									</div>
                                    <div id="itSecDetailInputDiv" style="display:none;" class="panel-body">
                                        <table class="table table-bordered table-striped mb-none" id="itSecDetail">
                                            <thead>
                                                <tr>
                                                    <th colspan="4" style="background:#eee; text-align:center;"></th>
                                                </tr>
                                                <tr>
                                                    <th>Rebate Name</th>
                                                    <th>Delete</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td><input type="text" id="itSectionDetailsName0" name="itSectionDetailsName" class="form-control" required></td>
                                                    <td><a href="#" class="on-default" name="deleteButton" onclick="return deleteRow(this);"><i class="fa fa-minus-square"></i></a></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                        <button type = "button" class="btn btn-primary pull-right" onclick="addrows();">Add </button>    
									</div>
									<footer style="display: block;" class="panel-footer" id = "footerUpperDiv">
										<button class="btn btn-primary" type = "submit" id = "submit" value="SUBMIT">Submit </button>
									</footer>
								</section>
                            </form:form>
                            <form:form name="editITSectionRebates" action="editITSectionRebates.html" method="POST">
								<section class="panel">
                                    <div id="itSecDetailEditDiv" style="display:none;" class="panel-body">
                                        <table class="table table-bordered table-striped mb-none" id="itSecDetailEdit">
                                            
                                        </table>
									</div>
									<footer style="display: none;" class="panel-footer" id = "footerEditDiv">
										<button type="button" class="btn btn-success" id="editButton"  value="edit" onclick="return removeDisabled();">Edit</button>
                                        <button type="submit" class="btn btn-primary" value="SUBMIT" id="submitButton" disabled="disabled">Submit</button>
									</footer>
									<c:if test="${submitResponse ne null}">	
										<div class="successbox" id="successbox" style="display:">
											${submitResponse}
										</div>
									</c:if>
									
									<div class="infomsgbox" id="infomsgbox" >
										<span id="infomsg"></span>	
									</div>		
								</section>
                            </form:form>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
					


<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/backoffice/createITSectionDetails.js"></script>
</body>
</html>