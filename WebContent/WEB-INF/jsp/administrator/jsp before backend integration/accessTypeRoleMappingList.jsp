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
</head>
<body>
<c:if test="${successMessage ne null}">
		<div class="successbox" id="successboxmsgbox" style="visibility: visible;">
			<span>${successMessage}</span>	
		</div>
	</c:if>
	
	<c:if test="${errorMessage ne null}">
		<div class="errorbox" id="errormsgbox" style="visibility: visible;">
			<span>${errorMessage}</span>	
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
                                    <table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
                                        <thead>
                                            <tr>
                                                <th>Access Type Name</th>
                                                <th>Access Type Description</th>
                                                <th>Actions</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        	<c:forEach var="accessType"  items="${pagedListHolder.pageList}">
                                            <tr>
                                                <td><input type="text" class="form-control" name="firstname" placeholder="" value="${accessType.accessTypeName}" disabled></td>
                                                <td><input type="text" class="form-control" name="accessTypeDesc" id="accessTypeDesc" placeholder="" value="${accessType.accessTypeDesc}" disabled></td>
                                                <td>
                                                    <a href="#" class="on-default edit-row"><i class="fa fa-pencil"></i></a>
                                                    <a href="#" class="hidden on-editing save-row"><i class="fa fa-save"></i></a>
                                                </td>
                                            </tr>
                                            </c:forEach>

                                        </tbody>
                                    </table>
                                </div> 
                                </c:otherwise>
                                </c:choose>
                            </section>
                            </form>
						</div>
					</div>	
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>