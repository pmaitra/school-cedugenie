<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Add Asset" />
<meta name="keywords" content="Add Asset" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Generate QR Code</title>

<link rel="stylesheet" href="/sms/css/backoffice/generateQRCode.css" />
<link href="/sms/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/sms/css/common/jquery-ui.css" />
<link href="/sms/fonts/font-awesome.css" rel="stylesheet" />
</head>
<body>
<div class="ttlarea">	
	<h1>
	<img src="/sms/images/titleicon01.png" alt="" />&nbsp;&nbsp;Generate QR Code
	</h1>
</div>
		<a href="qrcodeForTeacher.html"><h3 align="center">TEACHER</a>
		<a href="qrcodeForStudent.html"><h3 align="center">STUDENT</h3></a>
		<a href="qrcodeForBook.html"><h3 align="center">BOOK</h3></a>
		<a href="createResult.html?link=hallPass"><h3 align="center">HALL PASS</h3></a>

</body>
</html>