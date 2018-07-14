<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >
  <xsl:output method="html" indent="yes"/>
   <xsl:template match="/">
  <html>
		<head> 
		 	<script language="Javascript">
					nTotalQuestions=<xsl:value-of select="count(/survey/surveytypes/surveytype/questions/question)"/> 
				//alert("nTotalQuestions===="+nTotalQuestions);
				document.getELementById(nTotalQuestionsValue).value = nTotalQuestions;
 			 </script>
		     <script src="/icam/js/common/survey.js"></script>
		     
		</head>
		<h2 align="center"> <xsl:value-of select="/survey/surveytypes/surveytype/surveyname"/> 
							<xsl:element name="input">
								<xsl:attribute name="type">hidden</xsl:attribute>
								<xsl:attribute name="name"><xsl:text>SURVEYID</xsl:text></xsl:attribute>
								<xsl:attribute name="id"><xsl:text>SURVEYID</xsl:text></xsl:attribute>
								<xsl:attribute name="value"><xsl:value-of select="/survey/surveytypes/surveytype/surveyid"/></xsl:attribute>
							</xsl:element></h2>

  
  <body>
  <form  ID="Form1">
   <!-- <xsl:attribute name="id"><xsl:text>nTotalQuestionsValue</xsl:text></xsl:attribute> -->
		
		<table>
			<div style="align:left;width:100%;margin-left:5%">
			
				<xsl:for-each select="/survey/surveytypes/surveytype/questions/*">
					<xsl:variable name="qidvar" select="qid"/>	
						
						<xsl:element name="div" >
							<xsl:attribute name="id"><xsl:text>DIV</xsl:text><xsl:value-of select="$qidvar"/></xsl:attribute>
							<xsl:attribute name="name"><xsl:text>DIV</xsl:text><xsl:value-of select="$qidvar"/></xsl:attribute>
							
							<xsl:element name="div" >
								<xsl:attribute name="id"><xsl:text>ERROR1</xsl:text></xsl:attribute>
							</xsl:element>
							
							<table width="90%" style="margin-left:5%">
								<tr>

									<td width="50%"  valign="top"><FONT face="Arial, Helvetica, sans-serif" size="2"><xsl:value-of select="questiondesc"/></FONT></td>
									<!-- Text box, text -->
									<xsl:if test="typeid = 1 ">
										<td> 
											<xsl:element name="input">
												<xsl:attribute name="type">hidden</xsl:attribute>
												<xsl:attribute name="name"><xsl:text>CONTROLHIDDEN</xsl:text></xsl:attribute>
												<xsl:attribute name="id"><xsl:text>CONTROLHIDDEN</xsl:text><xsl:value-of select="id"/></xsl:attribute>
												<xsl:attribute name="value"><xsl:value-of select="$qidvar"/></xsl:attribute>
											</xsl:element>
											<xsl:element name="input">
												<xsl:attribute name="type">hidden</xsl:attribute>
												<xsl:attribute name="name"><xsl:text>CONTROLTYPE</xsl:text></xsl:attribute>
												<xsl:attribute name="id"><xsl:text>CONTROLTYPE</xsl:text><xsl:value-of select="id"/></xsl:attribute>
												<xsl:attribute name="value"><xsl:value-of select="typeid"/></xsl:attribute>
											</xsl:element>
											<xsl:element name="input">
												<xsl:attribute name="type">text</xsl:attribute>
												<xsl:attribute name="name"><xsl:text>CONTROL</xsl:text><xsl:value-of select="$qidvar"/></xsl:attribute>
												<xsl:attribute name="id"><xsl:text>CONTROL</xsl:text><xsl:value-of select="$qidvar"/></xsl:attribute>
												<xsl:attribute name="value"></xsl:attribute>
											</xsl:element>
										</td>
									</xsl:if>
									<!-- Text box, number -->
									<xsl:if test="typeid = 2 ">
										<td > 
											<xsl:element name="input">
												<xsl:attribute name="type">hidden</xsl:attribute>
												<xsl:attribute name="name"><xsl:text>CONTROLHIDDEN</xsl:text></xsl:attribute>
												<xsl:attribute name="id"><xsl:text>CONTROLHIDDEN</xsl:text><xsl:value-of select="id"/></xsl:attribute>
												<xsl:attribute name="value"><xsl:value-of select="$qidvar"/></xsl:attribute>
											</xsl:element>
											<xsl:element name="input">
												<xsl:attribute name="type">hidden</xsl:attribute>
												<xsl:attribute name="name"><xsl:text>CONTROLTYPE</xsl:text></xsl:attribute>
												<xsl:attribute name="id"><xsl:text>CONTROLTYPE</xsl:text><xsl:value-of select="id"/></xsl:attribute>
												<xsl:attribute name="value"><xsl:value-of select="typeid"/></xsl:attribute>
											</xsl:element>
											<xsl:element name="input">
												<xsl:attribute name="type">text</xsl:attribute>
												<xsl:attribute name="name"><xsl:text>CONTROL</xsl:text><xsl:value-of select="$qidvar"/></xsl:attribute>
												<xsl:attribute name="id"><xsl:text>CONTROL</xsl:text><xsl:value-of select="$qidvar"/></xsl:attribute>
												<xsl:attribute name="value"></xsl:attribute>
											</xsl:element>
										</td>
									</xsl:if>
									<!-- Text box, multiline -->
									<xsl:if test="typeid = 3">
										<td> 
											<xsl:element name="input">
												<xsl:attribute name="type">hidden</xsl:attribute>
												<xsl:attribute name="name"><xsl:text>CONTROLHIDDEN</xsl:text></xsl:attribute>
												<xsl:attribute name="id"><xsl:text>CONTROLHIDDEN</xsl:text><xsl:value-of select="id"/></xsl:attribute>
												<xsl:attribute name="value"><xsl:value-of select="$qidvar"/></xsl:attribute>
											</xsl:element>
											<xsl:element name="input">
												<xsl:attribute name="type">hidden</xsl:attribute>
												<xsl:attribute name="name"><xsl:text>CONTROLTYPE</xsl:text></xsl:attribute>
												<xsl:attribute name="id"><xsl:text>CONTROLTYPE</xsl:text><xsl:value-of select="id"/></xsl:attribute>
												<xsl:attribute name="value"><xsl:value-of select="typeid"/></xsl:attribute>
											</xsl:element>
											<xsl:element name="textarea">
												<xsl:attribute name="name"><xsl:text>CONTROL</xsl:text><xsl:value-of select="$qidvar"/></xsl:attribute>
													<xsl:attribute name="id"><xsl:text>CONTROL</xsl:text><xsl:value-of select="$qidvar"/></xsl:attribute>
													<xsl:attribute name="rows">4</xsl:attribute>
													<xsl:attribute name="cols">34</xsl:attribute>
													<xsl:attribute name="wrap">SOFT</xsl:attribute>
													<xsl:value-of select="''" disable-output-escaping="yes"/>
												</xsl:element>
										</td>
									</xsl:if>
									<!-- Checkbox -->
									<xsl:if test="typeid = 4">
										<td>
												<xsl:element name="input">
													<xsl:attribute name="type">hidden</xsl:attribute>
													<xsl:attribute name="name"><xsl:text>CONTROLHIDDEN</xsl:text></xsl:attribute>
													<xsl:attribute name="id"><xsl:text>CONTROLHIDDEN</xsl:text><xsl:value-of select="id"/></xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="$qidvar"/></xsl:attribute>
												</xsl:element>
												<xsl:element name="input">
													<xsl:attribute name="type">hidden</xsl:attribute>
													<xsl:attribute name="name"><xsl:text>CONTROLTYPE</xsl:text></xsl:attribute>
													<xsl:attribute name="id"><xsl:text>CONTROLTYPE</xsl:text><xsl:value-of select="id"/></xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="typeid"/></xsl:attribute>
												</xsl:element>
												<xsl:for-each select="./choices/*">
													<xsl:variable name="choicevar" select="choicedesc"/>
														<xsl:element name="input">
															<xsl:attribute name="type">checkbox</xsl:attribute>
															<xsl:attribute name="name"><xsl:text>CONTROL</xsl:text><xsl:value-of select="$qidvar"/></xsl:attribute>
															<xsl:attribute name="id"><xsl:text>CONTROL</xsl:text><xsl:value-of select="$qidvar"/></xsl:attribute>
															<xsl:attribute name="value"><xsl:value-of select="$choicevar"/></xsl:attribute>
														</xsl:element>
														<FONT face="Arial, Helvetica, sans-serif" size="2"> <xsl:value-of select="$choicevar"/></FONT>
														<br/>
												</xsl:for-each>	
										</td>
									</xsl:if>									
									<!-- Radio -->
									<xsl:if test="typeid = 6">
										<td> 
												<xsl:element name="input">
													<xsl:attribute name="type">hidden</xsl:attribute>
													<xsl:attribute name="name"><xsl:text>CONTROLHIDDEN</xsl:text></xsl:attribute>
													<xsl:attribute name="id"><xsl:text>CONTROLHIDDEN</xsl:text><xsl:value-of select="id"/></xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="$qidvar"/></xsl:attribute>
												</xsl:element>
												<xsl:element name="input">
													<xsl:attribute name="type">hidden</xsl:attribute>
													<xsl:attribute name="name"><xsl:text>CONTROLTYPE</xsl:text></xsl:attribute>
													<xsl:attribute name="id"><xsl:text>CONTROLTYPE</xsl:text><xsl:value-of select="id"/></xsl:attribute>
													<xsl:attribute name="value"><xsl:value-of select="typeid"/></xsl:attribute>
												</xsl:element>
												<xsl:for-each select="./choices/*">
													<xsl:variable name="choicevar" select="choicedesc"/>
														<xsl:element name="input">
															<xsl:attribute name="type">radio</xsl:attribute>
															<xsl:attribute name="name"><xsl:text>CONTROL</xsl:text><xsl:value-of select="$qidvar"/></xsl:attribute>
															<xsl:attribute name="id"><xsl:text>CONTROL</xsl:text><xsl:value-of select="$qidvar"/></xsl:attribute>
															<xsl:attribute name="value"><xsl:value-of select="$choicevar"/></xsl:attribute>
														</xsl:element>
														<FONT face="Arial, Helvetica, sans-serif" size="2"> <xsl:value-of select="$choicevar"/></FONT>
														<br/>
												</xsl:for-each>	
										</td>
									</xsl:if>
										
																		
								</tr>
							</table>
							
						</xsl:element>
				</xsl:for-each>
			</div>
		</table>
			<table>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr>
					<td></td>
					<td width="100%" align="center"> 
							<INPUT id="Submit" type="button" value="Submit" name="Submit" onclick="submit_click()"/>
					</td>
				</tr>
			</table>
	</form>


  </body>
  </html>
  </xsl:template>
</xsl:stylesheet>
