package com.qts.icam.service.impl.report;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.SAXException;

import com.google.gson.Gson;
import com.qts.icam.dao.backoffice.BackOfficeDAO;
import com.qts.icam.dao.inventory.InventoryDao;
import com.qts.icam.dao.report.ReportDAO;
import com.qts.icam.model.academics.StudentResult;
import com.qts.icam.model.academics.SubjectGroup;
import com.qts.icam.model.academics.UserDefinedExams;
import com.qts.icam.model.admission.AdmissionForm;
import com.qts.icam.model.backoffice.Exam;
import com.qts.icam.model.backoffice.StudentSubjectMapping;
import com.qts.icam.model.common.AcademicYear;
import com.qts.icam.model.common.Candidate;
import com.qts.icam.model.common.ChartData;
import com.qts.icam.model.common.ChartQueryData;
import com.qts.icam.model.common.ChartValuesModel;
import com.qts.icam.model.common.Condemnation;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.SchoolDetails;
import com.qts.icam.model.common.Section;
import com.qts.icam.model.common.Standard;
import com.qts.icam.model.common.Student;
import com.qts.icam.model.erp.Employee;
import com.qts.icam.model.inventory.Commodity;
import com.qts.icam.model.report.Report;
import com.qts.icam.service.common.CommonService;
import com.qts.icam.service.login.LoginService;
import com.qts.icam.service.report.ReportService;
import com.qts.icam.utility.bulkdata.DataUtility;
import com.qts.icam.utility.chartgenerator.ChartGenerator;
import com.qts.icam.utility.chartmanager.ChartQueryManager;
import com.qts.icam.utility.customexception.CustomException;
import com.qts.icam.utility.pdfbuilder.PdfBuilder;
import com.qts.icam.utility.report.xmlbuilder.XMLBuilder;
import com.qts.icam.utility.uploaddownload.FileUploadDownload;






@Service
public class ReportServiceImpl implements ReportService{
	
	public static Logger logger = Logger.getLogger(ReportServiceImpl.class);
	
	@Autowired
	DataUtility dataUtility;
		
	@Autowired
	ReportDAO reportDAO;
	
	@Autowired
	BackOfficeDAO backofficeDAO;
	
	@Autowired
	InventoryDao inventoryDAO;
	
	@Autowired
	XMLBuilder xMLBuilder;
	
	@Autowired
	PdfBuilder pdfBuilder;
	
	@Autowired
	ChartGenerator chartGenerator;
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	CommonService commonService;
	
	@Autowired
	FileUploadDownload fileUploadDownload;
	
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public synchronized  void createReportForExam(StudentResult studentResult,HttpServletResponse response) {
		try {
			if(studentResult.getExam().equalsIgnoreCase("Centralise")){
				List<Student> studentsSubjectsAndMarksList = reportDAO.getStudentListForCentralised(studentResult);	
				int totalStudentCount = reportDAO.getTotalStudentCount(studentResult);
				List<Student> rollNoWiseStudentList = reportDAO.getRollNoWiseStudentList(studentResult);
				HashMap<Integer,Integer> rollWiseStudentMap = new HashMap<Integer,Integer>();
				for(Student student:rollNoWiseStudentList )
				{
					int position = student.getSerialId();
					int roll = student.getRollNumber();
					rollWiseStudentMap.put(roll, position);
				}
				if(null != studentsSubjectsAndMarksList && studentsSubjectsAndMarksList.size() != 0){
					Report report = new Report();
					SchoolDetails schoolDetails = loginService.getSchoolDetails();
					schoolDetails.setExamName(studentResult.getExam());
					report.setSchoolDetails(schoolDetails);
					report.setReportId(totalStudentCount);
					Student studentDetails = null;
					for(Student student : studentsSubjectsAndMarksList){
						studentDetails = commonService.getStudentDetails(student.getRollNumber().toString());
						if(null != studentDetails){
							student.setStudentName(studentDetails.getStudentName());
							student.setStandard(studentDetails.getStandard());
							student.setSection(studentDetails.getSection());
							student.setHouse(studentDetails.getHouse());
							student.setResource(studentDetails.getResource());
						}
					}
		
					AcademicYear academicYear = commonService.getCurrentAcademicYear();
					report.setAcademicYear(academicYear);
					report.setStudent(studentDetails);
					report.setReportMap(rollWiseStudentMap);
					/*List<SubjectGroup> subjectGroupList = reportDAO.getSubjectGroupForInternalExamination(studentResult);*/
					
					
					
					/*for(Student s:studentsSubjectsAndMarksList){
						System.out.println(s.getRollNumber());
							for(StudentResult sr:s.getStudentResultList()){
								System.out.print(sr.getSubject()+"   "+sr.getExam()+"   "+sr.getTotal()+"   "+sr.getPractical()+"   "+sr.getTheoryObtained()+"   "+sr.getPracticalObtained()+"   "+sr.getTotalObtained()+"\n");
							}
						System.out.println("\n\n");
					}
					System.out.println("**************************************");*/
					
					
					List<Student> finalStudentsList=new ArrayList<Student>();
					
					for(Student s:studentsSubjectsAndMarksList){
					
						Student std=new Student();
						std.setRollNumber(s.getRollNumber());
						std.setStudentName(s.getStudentName());
						std.setHouse(s.getHouse());
						std.setSection(s.getSection());
						std.setStandard(s.getStandard());
						
						List<String> subjectList = new ArrayList<String>();
						for(StudentResult sResult:s.getStudentResultList()){
							
							String subName = sResult.getSubject();
							if(!subjectList.contains(subName))
							{
								subjectList.add(subName);
							}
						}
						std.setStringListForStudent(subjectList);
						
						int tenPer=0;
						int tot=0;
						int count=0;
						String sub="";
						
						int centTheo=0;
						int centPrac=0;
						int centTotObt=0;
						int centTot=0;
						int pracTot=0;
						
						List<StudentResult> srList=new ArrayList<StudentResult>();
						
						for(StudentResult sr:s.getStudentResultList()){
						
							if(sub.equalsIgnoreCase("")){
								sub=sr.getSubject();
								
								if(sr.getExam().equalsIgnoreCase("Centralise")){
									centTheo=sr.getTheoryObtained();
									centPrac=sr.getPracticalObtained();
									centTotObt=sr.getTotalObtained();
									centTot=sr.getTotal();
									pracTot=pracTot+sr.getPractical();
								}else{
									if(sr.getTotalObtained()==-1)
									{
										count=count+1;
									}
									tot=tot+sr.getTotalObtained();
								}
							}else if(sub.equalsIgnoreCase(sr.getSubject())){
								if(sr.getExam().equalsIgnoreCase("Centralise")){
									centTheo=sr.getTheoryObtained();
									centPrac=sr.getPracticalObtained();
									centTotObt=sr.getTotalObtained();
									centTot=sr.getTotal();
									pracTot=pracTot+sr.getPractical();
								}else{
									if(sr.getTotalObtained()==-1)
									{
										count=count+1;
									}
									tot=tot+sr.getTotalObtained();
								}
							}else{
								if(count==4)
								{
									tenPer=-1;
								}else{
									tenPer= Math.round(((tot+count)*10)/100);
								}
								
								StudentResult sr2=new StudentResult();
								sr2.setExam("Internal");
								sr2.setTotalObtained(tenPer);
								sr2.setTotal(30);
								sr2.setSubject(sub);
								srList.add(sr2);
								
								/*std.setStudentResultList(srList);
								finalStudentsList.add(std);*/
								
								
								StudentResult sr1=new StudentResult();
								sr1.setExam("Centralise");
								sr1.setTheoryObtained(centTheo);
								sr1.setPracticalObtained(centPrac);
								sr1.setTotalObtained(centTotObt);
								sr1.setTotal(centTot);
								sr1.setPractical(pracTot);
								sr1.setSubject(sub);
								srList.add(sr1);
								
								
								System.out.println(sub+"\t\t"+tot+"\t\t"+tenPer);
								
								sub=sr.getSubject();
								tenPer=0;
								tot=0;
								count=0;
								centTheo=0;
								centPrac=0;
								centTotObt=0;
								centTot=0;
								pracTot=0;
								
								if(sr.getExam().equalsIgnoreCase("Centralise")){
									centTheo=sr.getTheoryObtained();
									centPrac=sr.getPracticalObtained();
									centTotObt=sr.getTotalObtained();
									pracTot=pracTot+sr.getPractical();
									centTot=sr.getTotal();
								}else{
									tot=sr.getTotalObtained();
								}
							}
						}
						
						
						/*tenPer=(tot*10)/100;*/
						if(count==4)
						{
							tenPer =-1;
						}else{
							tenPer = Math.round(((tot+count)*10)/100);
						}
						
						
						StudentResult sr2=new StudentResult();
						sr2.setExam("Internal");
						sr2.setTotalObtained(tenPer);
						sr2.setTotal(30);
						sr2.setSubject(sub);
						srList.add(sr2);
						/*std.setStudentResultList(srList);
						finalStudentsList.add(std);*/
						
						
						StudentResult sr1=new StudentResult();
						sr1.setExam("Centralise");
						sr1.setTheoryObtained(centTheo);
						sr1.setPracticalObtained(centPrac);
						sr1.setTotalObtained(centTotObt);
						sr1.setTotal(centTot);
						sr1.setPractical(pracTot);
						sr1.setSubject(sub);
						srList.add(sr1);
						
						
						
						std.setStudentResultList(srList);
						finalStudentsList.add(std);
					}
					
						for(Student s:finalStudentsList){
						
						System.out.println(s.getRollNumber());
							for(StudentResult sr:s.getStudentResultList()){
								System.out.print(sr.getSubject()+"   "+sr.getExam()+"   "+sr.getTotal()+"   "+sr.getPractical()+"   "+sr.getTheoryObtained()+"   "+sr.getPracticalObtained()+"   "+sr.getTotalObtained()+"\n");
							}
						System.out.println("\n\n");
					}
					
					report.setStudentList(finalStudentsList);
					
					List<String> rollList= studentResult.getStringList();
					logger.info("1 : "+studentResult);
					String baseDir = studentResult.getReportConfigPath()+"result/"+studentDetails.getStandard()+"/"+studentResult.getExam()+"/";	;
					String pdfFilePath = studentResult.getPdfPath()+academicYear.getAcademicYearName()+"/"+studentDetails.getStandard()+"/"+studentResult.getExam()+"/";
					String fileName="";
					if(null != rollList && rollList.size()!=0 ){
						if( rollList.size() == 1){
							fileName = "Term_1_"+studentDetails.getRollNumber()+"_"+studentDetails.getStandard()+"_"+studentResult.getExam();
						}if( rollList.size() > 1){
							fileName = "Term_1_Students_"+studentDetails.getStandard()+"_"+studentResult.getExam();
						}

						String xmlFilePath = baseDir+"xml/";
						String xmlFileName = "result"+".xml";
						report.setXmlFilePath(xmlFilePath);
						report.setXmlFileName(xmlFileName);
						xMLBuilder.createXMLFileForCentraliseExamForXI_XII(report);
						
						
						String xsltFilePath = baseDir+"xslt/";
						String xsltFileName = "result.xsl";
						report.setXsltFilePath(xsltFilePath);
						report.setXsltFileName(xsltFileName);
						
						String pdfFileName = fileName+".pdf";
						report.setPdfFilePath(pdfFilePath);
						report.setPdfFileName(pdfFileName);
						
						try{
							pdfBuilder.createPDF(report);
						}catch(IOException e){
							logger.error(e);
						}
						FileUtils.cleanDirectory(new File(studentResult.getImagePath()));
						if ((new File(report.getPdfFilePath()+report.getPdfFileName())).exists()) {
							if (Desktop.isDesktopSupported()) {
								//Desktop.getDesktop().open(new File(report.getPdfFilePath()+report.getPdfFileName()));
								fileUploadDownload.downloadFile(response, report.getPdfFilePath(), report.getPdfFileName());
							}
						}
					}
					
					
					
					
				
					
				}
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			else{
				List<Student> studentsSubjectsAndMarksList = reportDAO.getStudentsSubjectsAndMarksReportForCurrentAcademicYear(studentResult);
				logger.info(studentsSubjectsAndMarksList);
				if(null != studentsSubjectsAndMarksList && studentsSubjectsAndMarksList.size() != 0){
					Report report = new Report();
					SchoolDetails schoolDetails = loginService.getSchoolDetails();
					schoolDetails.setExamName(studentResult.getExam());
					report.setSchoolDetails(schoolDetails);
					Student studentDetails = null;
					for(Student student : studentsSubjectsAndMarksList){
						if(studentResult.getExam().equals("SA2")){
							student.setStudentDesc(studentResult.getAcademicYear());
							student.setStandard(studentResult.getStandard());
							student.setStatus(studentResult.getExam());
							student.setCoScholasticResultList(reportDAO.getCoScholasticResultList(student));
						}
						studentDetails = commonService.getStudentDetails(student.getRollNumber().toString());
						if(null != studentDetails){
							student.setStudentName(studentDetails.getStudentName());
							student.setStandard(studentDetails.getStandard());
							student.setSection(studentDetails.getSection());
							student.setHouse(studentDetails.getHouse());
							student.setResource(studentDetails.getResource());
						}
					}
					AcademicYear academicYear = commonService.getCurrentAcademicYear();
					report.setAcademicYear(academicYear);
					report.setStudent(studentDetails);
					report.setStudentList(studentsSubjectsAndMarksList);
					List<String> rollList= studentResult.getStringList();
					logger.info("1 : "+studentResult);
					String baseDir;
					String pdfFilePath;
					/*if(studentDetails.getStandard().equalsIgnoreCase("XI"))
					{
						String exam = studentResult.getExam().replace(" ", "_");
						baseDir = studentResult.getReportConfigPath()+"result/"+studentDetails.getStandard()+"/"+exam+"/";
						pdfFilePath = studentResult.getPdfPath()+academicYear.getAcademicYearName()+"/"+studentDetails.getStandard()+"/"+exam+"/";
					}
					else*/
					{
						baseDir = studentResult.getReportConfigPath()+"result/"+studentDetails.getStandard()+"/"+studentResult.getExam()+"/";	
						pdfFilePath = studentResult.getPdfPath()+academicYear.getAcademicYearName()+"/"+studentDetails.getStandard()+"/"+studentResult.getExam()+"/";
					}
					//String baseDir = studentResult.getReportConfigPath()+"result/"+studentDetails.getStandard()+"/"+studentResult.getExam()+"/";
					String fileName="";
					if(null != rollList && rollList.size()!=0 ){
						if( rollList.size() == 1){
							fileName = "Term_1_"+studentDetails.getRollNumber()+"_"+studentDetails.getStandard()+"_"+studentResult.getExam();
						}if( rollList.size() > 1){
							fileName = "Term_1_Students_"+studentDetails.getStandard()+"_"+studentResult.getExam();
						}

						String xmlFilePath = baseDir+"xml/";
						String xmlFileName = "result"+".xml";
						report.setXmlFilePath(xmlFilePath);
						report.setXmlFileName(xmlFileName);
						
						if(studentDetails.getStandard().equalsIgnoreCase("XI")||studentDetails.getStandard().equalsIgnoreCase("XII")){
							/*if(studentResult.getExam().equalsIgnoreCase("Centralise"))
							{
								xMLBuilder.createXMLFileForCentraliseExamForXI_XII(report);
							}
							else*/ 
							{
								xMLBuilder.createXMLFileForExamResultXI_XII(report);
							}
							
						} else{
							xMLBuilder.createXMLFileForExamResult(report);
						}
						String xsltFilePath = baseDir+"xslt/";
						String xsltFileName = "result.xsl";
						report.setXsltFilePath(xsltFilePath);
						report.setXsltFileName(xsltFileName);
						
						String pdfFileName = fileName+".pdf";
						report.setPdfFilePath(pdfFilePath);
						report.setPdfFileName(pdfFileName);
						if(!(studentResult.getStandard().equalsIgnoreCase("XI") || studentResult.getStandard().equalsIgnoreCase("XII"))){
							for(String roll : rollList){
								studentResult.setRollNumber(roll);
								List<StudentResult> studentsSubjectsAndMarksListForChart = reportDAO.getStudentsSubjectsAndMarksReportChartForCurrentAcademicYear(studentResult);
								logger.info("studentsSubjectsAndMarksListForChart ::  "+studentsSubjectsAndMarksListForChart);
								if(null != studentsSubjectsAndMarksListForChart && studentsSubjectsAndMarksListForChart.size() != 0)
									studentsSubjectsAndMarksListForChart.get(0).setImagePath(studentResult.getImagePath());
									studentsSubjectsAndMarksListForChart.get(0).setRollNumber(roll);
									chartGenerator.generateBarChart(studentsSubjectsAndMarksListForChart);
							}
						}
						try{
							pdfBuilder.createPDF(report);
						}catch(IOException e){
							logger.error(e);
						}
						FileUtils.cleanDirectory(new File(studentResult.getImagePath()));
						if ((new File(report.getPdfFilePath()+report.getPdfFileName())).exists()) {
							if (Desktop.isDesktopSupported()) {
								//Desktop.getDesktop().open(new File(report.getPdfFilePath()+report.getPdfFileName()));
								fileUploadDownload.downloadFile(response, report.getPdfFilePath(), report.getPdfFileName());
							}
						}
					}
				}
			}
			
			reportDAO.deleteTempTableReportData();
		}catch (IOException e) {
			logger.error(e);
		}catch (CustomException e) {
			logger.error(e);
		}catch (Exception e) {
			logger.error(e);
		}
	}
	
		
	@Override
	public String getStudentReport(Standard standard, Section section, AcademicYear academicYear, Exam exam,
									String[] roll, String reportResultConfigFilePath, String reportResultPdfFilPath,
									String reportExamResultChatImagePath, HttpServletResponse response) {			
		boolean currentYear = false;
		String status = null;
		AcademicYear currentAcademicYear;
		try {
			currentAcademicYear = commonService.getCurrentAcademicYear();
			if(null != currentAcademicYear){
				if(currentAcademicYear.getAcademicYearCode().equalsIgnoreCase(academicYear.getAcademicYearCode())){
					currentYear = true;// For Current Year Report
				}else{
					currentYear = false;// ForPrevious Year Report
				}
				
				if(null != roll && roll.length != 0){	
					//for(int count=0;count<roll.length;count++){
					List<String> studentsRollList = new ArrayList<String>(Arrays.asList(roll));
					logger.info("ROLL SET:::  "+studentsRollList);
					StudentResult studentResult=new StudentResult();
					studentResult.setStringList(studentsRollList);
					studentResult.setExam(exam.getExamName());
					studentResult.setStandard(standard.getStandardCode());
					studentResult.setSection(section.getSectionCode());
					studentResult.setAcademicYear(academicYear.getAcademicYearCode());
					studentResult.setReportConfigPath(reportResultConfigFilePath);	
					studentResult.setPdfPath(reportResultPdfFilPath);
					studentResult.setImagePath(reportExamResultChatImagePath);
					logger.info(studentResult.getStringList()+"$$$$$$"+studentResult.getExam()+"$$$$$$"+studentResult.getStandard()+"$$$$$$"+studentResult.getSection()+"$$$$$$"+studentResult.getAcademicYear());
					if(currentYear){
						createReportForExam(studentResult,response);
						status = "success";					
					}else{
						// for previous years
					}
				
				}
			}
		} catch (CustomException e) {
			logger.error(e);
		}
		return status;
	}


		@Override
		public String getStudentNominalRoll(Student studentDataToBeShowned,String excel,
											String reportStudentNominalRollConfigFilePath,
											String reportStudentNominalRollPdfFilPath,
											String nominalRollExcel, HttpServletResponse response) {			
			String status = null;	
			try {
				if(null != studentDataToBeShowned){					
					List<Standard> standardList = reportDAO.getStudentNominalRoll(studentDataToBeShowned);
					AcademicYear currentAcademicYear = commonService.getCurrentAcademicYear();
					String academicYear="--";
					if(null != currentAcademicYear)
						academicYear = currentAcademicYear.getAcademicYearCode();
					logger.info(studentDataToBeShowned.getResource().getUpdatedBy());
					if(null != standardList && standardList.size() != 0){
						if(null != excel){
							status = createExcelForNominalRole(academicYear,standardList,studentDataToBeShowned, reportStudentNominalRollPdfFilPath, nominalRollExcel, response);								
						}else{
							createReportForStudentNominalRoll(academicYear,standardList,reportStudentNominalRollConfigFilePath,reportStudentNominalRollPdfFilPath,studentDataToBeShowned, response);
							status = "success";
						}							
					}						
				}
			} catch (CustomException e) {					
				logger.error(e);
			}				
			return status;
		}
		
		
		private String createExcelForNominalRole(String academicYear, List<Standard> standardList,
				Student studentDataToBeShowned, String reportStudentNominalRollPdfFilPath, 
				String nominalRollExcel, HttpServletResponse response) {
			reportStudentNominalRollPdfFilPath = reportStudentNominalRollPdfFilPath+nominalRollExcel;
			String status = "";			
			int sheetCount = 0;
			HSSFWorkbook workbook = new HSSFWorkbook();         
			//Create a blank sheet				
			try  {	
				HSSFSheet sheet = workbook.createSheet(standardList.get(0).getStandardName());	 
				sheet.autoSizeColumn(5);
				sheet.protectSheet("secretPassword");		        
				        			        
				HSSFFont hSSFFont = workbook.createFont();
				hSSFFont.setFontName(HSSFFont.FONT_ARIAL);
				hSSFFont.setFontHeightInPoints((short) 12);
				hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				hSSFFont.setColor(HSSFColor.BLACK.index);
				        
				/* cell style for locking */
				CellStyle lockedCellStyle = workbook.createCellStyle();
				lockedCellStyle.setFont(hSSFFont);
				lockedCellStyle.setLocked(true);
				/* cell style for editable cells */
				CellStyle unlockedCellStyle = workbook.createCellStyle();
				unlockedCellStyle.setLocked(false);	
				
				
				
				/* cell style for locking */
				CellStyle centerAlignCell = workbook.createCellStyle();
				centerAlignCell.setFont(hSSFFont);
				centerAlignCell.setLocked(true);
				centerAlignCell.setAlignment(CellStyle.ALIGN_CENTER);
			
			
				//This data needs to be written (Object[])
				Map<Integer, List<String>> data = new TreeMap<Integer, List<String>>();	
				int counter = 0;
				List<String> headerList = new ArrayList<String>();
				if(studentDataToBeShowned.getRollNumber() != null){
					headerList.add("Roll Number");
					counter++;
				}
			
				if(studentDataToBeShowned.getStudentName() != null){
					headerList.add("Name");
					counter++;
				}
			
				if(studentDataToBeShowned.getHouse() != null){
					headerList.add("House");
					counter++;
				}
			
				if(studentDataToBeShowned.getResource() != null){
					if(studentDataToBeShowned.getResource().getCategory() != null){
						headerList.add("Category");
						counter++;
					}			        		
				}
			
				if(studentDataToBeShowned.getResource() != null){
					if(studentDataToBeShowned.getResource().getDateOfBirth() != null){
						headerList.add("Date Of Birth");
						counter++;
					}		        		
				}	
			
				if(studentDataToBeShowned.getStateOfDomicile() != null){
					headerList.add("State Of Domicile");
					counter++;
				}
			
				if(studentDataToBeShowned.getResource() != null){
					if(studentDataToBeShowned.getResource().getFatherFirstName() != null){
						headerList.add("Father Name");
						counter++;
					}
				}
			
				if(studentDataToBeShowned.getResource() != null){
					if(studentDataToBeShowned.getResource().getMotherFirstName() != null){
						headerList.add("Mother Name");
						counter++;
					}
				}		
			
				if(studentDataToBeShowned.getSecondLanguage() != null){
					headerList.add("Second Language");
					counter++;
				}
			
				if(studentDataToBeShowned.getDateOfAdmission() != null){
					headerList.add("Date Of Admission");
					counter++;
				}
			
				if(studentDataToBeShowned.getResource() != null){
					if(studentDataToBeShowned.getResource().getMobile() != null){
						headerList.add("Contact");
						counter++;
					}
				}
			
			
				List<String> schoolDetailsList = new ArrayList<String>();
				sheet.addMergedRegion(new CellRangeAddress(0,0,0,counter-1));	//(rowFrom, rowTo, colFrom, colTo)
				schoolDetailsList.add("SAINIK SCHOOL PURULIA");
				data.put(1, schoolDetailsList);
				
				sheet.addMergedRegion(new CellRangeAddress(1,1,0,counter-1));	//(rowFrom, rowTo, colFrom, colTo)
				schoolDetailsList = new ArrayList<String>();
				schoolDetailsList.add("Nominal Roll List - ("+academicYear+") :: "+standardList.get(0).getStandardName()+" ("+standardList.get(0).getSectionList().get(0).getSectionName()+")");
				data.put(2, schoolDetailsList);
				data.put(3, headerList);
			
				int count = 4;
				for(Standard  standard: standardList){			        	
					for(Section section : standard.getSectionList()){
						for(Student student : section.getStudentList()){
							List<String> rowList = new ArrayList<String>();
			 
							if(studentDataToBeShowned.getRollNumber() != null){
								 rowList.add(student.getRollNumber().toString());
							}
			 
							if(studentDataToBeShowned.getStudentName() != null){
								 rowList.add(student.getStudentName());
							}
							 
							if(studentDataToBeShowned.getHouse() != null){
								 rowList.add(student.getHouse());
							}
							
							if(studentDataToBeShowned.getResource() != null){
								 if(studentDataToBeShowned.getResource().getCategory() != null){
									 rowList.add(student.getResource().getCategory());
								 }			        		
							}
			
							if(studentDataToBeShowned.getResource() != null){
								 if(studentDataToBeShowned.getResource().getDateOfBirth() != null){
									 rowList.add(student.getResource().getDateOfBirth());
								 }		        		
							}	
							 
							if(studentDataToBeShowned.getStateOfDomicile() != null){
								 rowList.add(student.getStateOfDomicile());
							}
			 
							if(studentDataToBeShowned.getResource() != null){
								 if(studentDataToBeShowned.getResource().getFatherFirstName() != null){
									 rowList.add(student.getResource().getFatherFirstName());
								 }
							}
							 
							if(studentDataToBeShowned.getResource() != null){
								 if(studentDataToBeShowned.getResource().getMotherFirstName() != null){
									 rowList.add(student.getResource().getMotherFirstName());
								 }
							}		
			 
							if(studentDataToBeShowned.getSecondLanguage() != null){
								 rowList.add(student.getSecondLanguage());
							}
							 
							if(studentDataToBeShowned.getDateOfAdmission() != null){
								 rowList.add(student.getDateOfAdmission());
							}
			
							if(studentDataToBeShowned.getResource() != null){
								 if(studentDataToBeShowned.getResource().getMobile() != null){
									 rowList.add(student.getResource().getMobile());
								 }
							}			        	
							//System.out.println(rowList.size());
							data.put(count, rowList);					        	
							count++;
						}
					}			
				}	
			
				//Iterate over data and write to sheet
				Set<Integer> keyset = data.keySet();
				int rownum = 0;
				for (Integer key : keyset){
					Row row = sheet.createRow(rownum++);
					List<String> rowsList  = data.get(key);
					int cellnum = 0;
					for (String obj : rowsList) {
						Cell cell = row.createCell(cellnum++);
						if(obj instanceof String){
							cell.setCellValue((String)obj);
							cell.setCellStyle(lockedCellStyle);
						}			                
					}
				}
				HSSFRow rows = workbook.getSheetAt(sheetCount).getRow(2);
				for(int colNum = 0; colNum<rows.getLastCellNum();colNum++){   
					workbook.getSheetAt(sheetCount).autoSizeColumn(colNum);
				}
				workbook.getSheetAt(sheetCount).getRow(0).getCell(0).setCellStyle(centerAlignCell);
				workbook.getSheetAt(sheetCount).getRow(1).getCell(0).setCellStyle(centerAlignCell);
				
				sheetCount++;
				//Write the workbook in file system  
				FileOutputStream out = new FileOutputStream(new File(reportStudentNominalRollPdfFilPath));
				workbook.write(out);
				out.flush();
				out.close();
				status = "Success";			
				fileUploadDownload.downloadFile(response, reportStudentNominalRollPdfFilPath, nominalRollExcel);			
			}
			catch (Exception e) {
				logger.error(e);
			}
			return status;
		}
		
		
		private synchronized void createReportForStudentNominalRoll(
				String academicYear, List<Standard> standardList,String reportStudentNominalRollConfigFilePath,
				String reportStudentNominalRollPdfFilPath,Student studentDataToBeShowned,
				HttpServletResponse response) throws CustomException {
			try {				
				if(null!=standardList && standardList.size()!=0){
					Report report = new Report();
					SchoolDetails schoolDetails = loginService.getSchoolDetails();					
					report.setSchoolDetails(schoolDetails);					
					report.setStandardList(standardList);	
					AcademicYear ay=new AcademicYear();
					ay.setAcademicYearCode(academicYear);
					report.setAcademicYear(ay);
					String baseDir = reportStudentNominalRollConfigFilePath+"Nominal_Roll"+"/";
					String fileName="Nominal Roll List";
					
					String xmlFilePath = baseDir+"xml/";
					String xmlFileName = "nominalRoll"+".xml";
					report.setXmlFilePath(xmlFilePath);
					report.setXmlFileName(xmlFileName);
					xMLBuilder.createXMLFileForNominalRoll(report,studentDataToBeShowned);

					
					String xsltFilePath = baseDir+"xslt/";
					String xsltFileName = "nominalRoll.xsl";
					report.setXsltFilePath(xsltFilePath);
					report.setXsltFileName(xsltFileName);
					
					String pdfFilePath = reportStudentNominalRollPdfFilPath+"Nominal Roll"+"/";
					String pdfFileName = fileName+".pdf";
					report.setPdfFilePath(pdfFilePath);
					report.setPdfFileName(pdfFileName);
					
					try{
					pdfBuilder.createPDF(report);
					}catch (IOException e) {
						logger.error(e);
					}	
					
						if ((new File(report.getPdfFilePath()+report.getPdfFileName())).exists()) {
							if (Desktop.isDesktopSupported()) {
//								Desktop.getDesktop().open(new File(report.getPdfFilePath()+report.getPdfFileName()));
								fileUploadDownload.downloadFile(response, report.getPdfFilePath(), report.getPdfFileName());
							}
						}
						
					}				
			}catch (Exception e) {
				logger.error(e);
			}			
		}


		@Override
		public String getStaffDetailsList(Employee employee, String excel, String reportStaffDetailsConfigFilePath, 
				String reportStaffDetailsPdfFilPath, String staffExcel,HttpServletResponse response) {
			String status=null;	
			try {
				if(null!=employee){					
					List<Employee> employeeList=reportDAO.getStaffDetailsList(employee);					
					if(null!=employeeList && employeeList.size()!=0){						
						if(null != excel){
							status=createStaffExcel(employeeList, employee, reportStaffDetailsPdfFilPath, staffExcel, response);								
						}else{
							createReportForStaffDetailsList(employee,employeeList,reportStaffDetailsConfigFilePath,reportStaffDetailsPdfFilPath, response);
							status="success";
						}					
					}					
				}
			} catch (Exception e) {					
					logger.error(e);
				}
				
				return status;
		}


		private String createStaffExcel(List<Employee> employeeList, Employee employee, String reportStaffDetailsPdfFilPath, 
										String staffExcel, HttpServletResponse response) {
			reportStaffDetailsPdfFilPath=reportStaffDetailsPdfFilPath+staffExcel;
			String status = "";			
			int sheetCount=0;
			HSSFWorkbook workbook = new HSSFWorkbook();         
			//Create a blank sheet				
			try  {	
				HSSFSheet sheet = workbook.createSheet(employee.getResource().getResourceType().getResourceTypeCode());	 
		        sheet.autoSizeColumn(5);
		        sheet.protectSheet("secretPassword");		        
		        			        			        
		        HSSFFont hSSFFont = workbook.createFont();
		        hSSFFont.setFontName(HSSFFont.FONT_ARIAL);
		        hSSFFont.setFontHeightInPoints((short) 12);
		        hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		        hSSFFont.setColor(HSSFColor.BLACK.index);
		        			        
		        /* cell style for locking */
		        CellStyle lockedCellStyle = workbook.createCellStyle();
		        lockedCellStyle.setFont(hSSFFont);
		        lockedCellStyle.setLocked(true);
		        /* cell style for editable cells */
		        CellStyle unlockedCellStyle = workbook.createCellStyle();
		        unlockedCellStyle.setLocked(false);	
		        
		        
		        //This data needs to be written (Object[])
		        Map<String, List<String>> data = new TreeMap<String, List<String>>();	
		     
				 List<String> headerList = new ArrayList<String>();
	        	 if(employee.getResource().getUserId() != null){
	        		 headerList.add("User ID");
	        	 }
	        	 
	        	 if(employee.getEmployeeCode() != null){
	        		 headerList.add("Emp Code");
	        	 }
	        	 
	        	 if(employee.getResource() != null){
	        		 if(employee.getResource().getName() != null){
		        		 headerList.add("Name");
	        		 }	        		 
	        	 }        	 
	        	 
	        	 if(employee.getDesignation()!= null){
	        		 if(employee.getDesignation().getDesignationCode() != null){
	        			 headerList.add("Designation");
	        		 }			        		
	        	 }
	        	 
	        	 if(employee.getResource()!= null){
	        		 if(employee.getResource().getGender() != null){
	        			 headerList.add("Gender");
	        		 }			        		
	        	 }
				
	        	 if(employee.getResource() != null){
	        		 if(employee.getResource().getCategory() != null){
	        			 headerList.add("Category");
	        		 }		        		
	        	 }	
	        	 
	        	 if(employee.getResource() != null){
	        		 if(employee.getResource().getReligion() != null){
		        		 headerList.add("Religion");
		        	 }
	        	 }
	        	 
	        	 if(employee.getResource() != null){
	        		 if(employee.getResource().getMobile() != null){
	        			 headerList.add("Contact");
	        		 }
	        	 }	        	 
	        	  
	        	
		        data.put("1", headerList);			        
		        String count="3";
		        for(Employee employeeObj: employeeList){   	
		        	
		        			 List<String> rowList = new ArrayList<String>();
		        			 
		        			 if(employeeObj.getResource() != null){
		        				 if(employeeObj.getResource().getUserId() != null){
			        				 rowList.add(employeeObj.getResource().getUserId());
					        	 }
				        	 }
				        	 
				        	 if(employeeObj.getEmployeeCode() != null){
				        		 rowList.add(employeeObj.getEmployeeCode());
				        	 }
				        	 
				        	 if(employeeObj.getResource() != null){
				        		 if(employeeObj.getResource().getName() != null){
				        			 rowList.add(employeeObj.getResource().getName());
				        		 }	        		 
				        	 }
				        	
				        	 if(employeeObj.getDesignation()!= null){
				        		 if(employeeObj.getDesignation().getDesignationCode() != null){
				        			 rowList.add(employeeObj.getDesignation().getDesignationCode());
				        		 }			        		
				        	 }
							
				        	 if(employeeObj.getResource() != null){
				        		 if(employeeObj.getResource().getGender() != null){
				        			 rowList.add(employeeObj.getResource().getGender());
				        		 }		        		
				        	 }	
				        	 
				        	 if(employeeObj.getResource() != null){
				        		 if(employeeObj.getResource().getCategory() != null){
				        			 rowList.add(employeeObj.getResource().getCategory());
				        		 }		        		
				        	 }
				        	 
				        	 if(employeeObj.getResource() != null){
				        		 if(employeeObj.getResource().getReligion() != null){
				        			 rowList.add(employeeObj.getResource().getReligion());
					        	 }
				        	 }
				        	 
				        	 if(employeeObj.getResource() != null){
				        		 if(employeeObj.getResource().getMobile() != null){
				        			 rowList.add(employeeObj.getResource().getMobile());
				        		 }
				        	 }	        	 
		        			data.put(count, rowList);
				        	int loopControl=Integer.parseInt(count);
				        	loopControl++;
				        	count=""+loopControl;
		        		}   
		         
		        //Iterate over data and write to sheet
		        Set<String> keyset = data.keySet();
		        int rownum = 0;
		        for (String key : keyset){
		            Row row = sheet.createRow(rownum++);
		            List<String> rowsList  = data.get(key);
		            int cellnum = 0;
		            for (String obj : rowsList) {
		               Cell cell = row.createCell(cellnum++);
		               if(obj instanceof String){
		                    cell.setCellValue((String)obj);
		                    cell.setCellStyle(lockedCellStyle);
		               }			                
		            }
		        }
		        HSSFRow rows = workbook.getSheetAt(sheetCount).getRow(0);
		        for(int colNum = 0; colNum<rows.getLastCellNum();colNum++){   
		        	workbook.getSheetAt(sheetCount).autoSizeColumn(colNum);
		        }
			        sheetCount++;
			        //Write the workbook in file system  
		            FileOutputStream out = new FileOutputStream(new File(reportStaffDetailsPdfFilPath));
		            workbook.write(out);
		            out.flush();
		            out.close();
		            status="Success";
		            
					fileUploadDownload.downloadFile(response, reportStaffDetailsPdfFilPath, staffExcel);

		            
				}
		        catch (Exception e) {
		        	logger.error(e);
	        	}		        
					
		return status;
		}


		private void createReportForStaffDetailsList(Employee employee,
				List<Employee> employeeList,
				String reportStaffDetailsConfigFilePath,
				String reportStaffDetailsPdfFilPath,
				HttpServletResponse response) {
			try {				
				if(null!=employeeList && employeeList.size()!=0){
					Report report = new Report();
					SchoolDetails schoolDetails = loginService.getSchoolDetails();					
					report.setSchoolDetails(schoolDetails);					
					report.setEmployeeList(employeeList);					
					String baseDir = reportStaffDetailsConfigFilePath+"Employee_List"+"/";
					String fileName="Employee_List";
					
					String xmlFilePath = baseDir+"xml/";
					String xmlFileName = "employeeList"+".xml";
					report.setXmlFilePath(xmlFilePath);
					report.setXmlFileName(xmlFileName);
					xMLBuilder.createXMLFileForStaffDetailsList(report,employee);

					
					String xsltFilePath = baseDir+"xslt/";
					String xsltFileName = "employeeList.xsl";
					report.setXsltFilePath(xsltFilePath);
					report.setXsltFileName(xsltFileName);
					
					String pdfFilePath = reportStaffDetailsPdfFilPath+"Employee_List"+"/";
					String pdfFileName = fileName+".pdf";
					report.setPdfFilePath(pdfFilePath);
					report.setPdfFileName(pdfFileName);
					
					try{
					pdfBuilder.createPDF(report);					
					}catch (IOException e) {
						logger.error(e);
					}		
					if ((new File(report.getPdfFilePath()+report.getPdfFileName())).exists()) {
							if (Desktop.isDesktopSupported()) {
//								Desktop.getDesktop().open(new File(report.getPdfFilePath()+report.getPdfFileName()));
								fileUploadDownload.downloadFile(response, report.getPdfFilePath(), report.getPdfFileName());
							}
						}
					}				
			}catch (Exception e) {
				logger.error(e);
			}		
		}


	
		
		
		public synchronized  void createAdmitCardForAdmission(AdmissionForm admitcardGenerateForm, HttpServletResponse response) {
			try {
					Report report = new Report();
					List<AcademicYear> academicYearList= commonService.getCurrentAndNextAcademicYear();
					AcademicYear  academicYear = new AcademicYear();
					if(academicYearList != null &&  academicYearList.size()!=0){
						for(AcademicYear ay : academicYearList){
							if(ay.getYearStatus()!=null && ay.getYearStatus().equals("NEXT")){
								academicYear.setAcademicYearName(new StringTokenizer(ay.getAcademicYearName()).nextToken("-"));
							}
						}
					}
					report.setAcademicYear(academicYear);
					SchoolDetails schoolDetails = loginService.getSchoolDetails();
					report.setSchoolDetails(schoolDetails);
					report.setCandidateList(admitcardGenerateForm.getCandidateList());
					report.setVenue(admitcardGenerateForm.getVenue());
					report.setStartDate(admitcardGenerateForm.getExaminationDate()+"   "+admitcardGenerateForm.getExaminationTime());
					String baseDir = admitcardGenerateForm.getAdmitCardFilePath();
					String fileName="admitcard";
					
					String xmlFilePath = baseDir+"xml/";
					String xmlFileName = fileName+".xml";
					report.setXmlFilePath(xmlFilePath);
					report.setXmlFileName(xmlFileName);
					xMLBuilder.createXMLFileForAdmitCard(report);

					
					String xsltFilePath = baseDir+"xslt/";
					String xsltFileName = fileName+".xsl";
					report.setXsltFilePath(xsltFilePath);
					report.setXsltFileName(xsltFileName);
					
					String pdfFilePath = admitcardGenerateForm.getPdfFilePath()+"admitcard/";
					String pdfFileName = fileName+".pdf";
					report.setPdfFilePath(pdfFilePath);
					report.setPdfFileName(pdfFileName);

					if ((new File(report.getPdfFilePath()+report.getPdfFileName())).exists()) {
						FileUtils.cleanDirectory(new File(pdfFilePath));
					}
					try{
					pdfBuilder.createPDF(report);
					}catch (IOException e) {
						logger.error(e);
					}	
						if ((new File(report.getPdfFilePath()+report.getPdfFileName())).exists()) {
							if (Desktop.isDesktopSupported()) {
//								Desktop.getDesktop().open(new File(report.getPdfFilePath()+report.getPdfFileName()));
								fileUploadDownload.downloadFile(response, report.getPdfFilePath(), report.getPdfFileName());
							}
						}
			}catch (Exception e) {
				logger.error(e);
			}
		}





		private String createStudentMarkSheetExcel(List<SubjectGroup> subjectGroupList, StudentResult studentResult, List<Student> studentsSubjectsAndMarksList, String reportResultPdfFilPath, String studentMarkSheetExcel) {
			
			reportResultPdfFilPath = reportResultPdfFilPath+studentMarkSheetExcel;
			String status = "";	
			
			if(studentResult.getExam().equalsIgnoreCase("SA1")){
				
				int sheetCount = 0;
				HSSFWorkbook workbook = new HSSFWorkbook();         
				//Create a blank sheet				
				try  {	
					HSSFSheet sheet = workbook.createSheet("consolidate_result_"+studentResult.getStandard()+"_"+studentResult.getSection()+"_"+studentResult.getExam());
			        sheet.protectSheet("secretPassword");	
			        
			      //MERGING CODE
			        sheet.addMergedRegion(new CellRangeAddress(0,0,0,16));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(1,1,0,16));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(2,2,0,16));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(3,3,0,16));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(4,4,2,6));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(4,4,7,11));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(4,4,12,16));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(4,4,17,21));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(4,4,22,26));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(4,4,27,31));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(4,4,32,36));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(4,4,37,41));	//(rowFrom, rowTo, colFrom, colTo)
			        
			        sheet.setDefaultColumnWidth(8);
			        
			        HSSFFont hSSFFont = workbook.createFont();
			        hSSFFont.setFontName(HSSFFont.FONT_ARIAL);
			        hSSFFont.setFontHeightInPoints((short) 12);
			        hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			        hSSFFont.setColor(HSSFColor.BLACK.index);
			        			        
			        /* cell style for locking */
			        CellStyle lockedCellStyle = workbook.createCellStyle();
			        lockedCellStyle.setFont(hSSFFont);
			        lockedCellStyle.setLocked(true);
			        lockedCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
			        
			        CellStyle nameCellStyle = workbook.createCellStyle();
			        nameCellStyle.setFont(hSSFFont);
			        nameCellStyle.setLocked(true);
			        nameCellStyle.setAlignment(CellStyle.ALIGN_LEFT);
			        
			        /* cell style for editable cells */
			        CellStyle unlockedCellStyle = workbook.createCellStyle();
			        unlockedCellStyle.setLocked(false);	
			        unlockedCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
			        
			        
			        //This data needs to be written (Object[])
			        Map<Integer, List<String>> data = new TreeMap<Integer, List<String>>();	
			        
			        List<String> schoolDetailsList = new ArrayList<String>();
			        schoolDetailsList.add("SAINIK SCHOOL PURULIA");
			        data.put(1, schoolDetailsList);
			        
			        
			        List<String> resultDetailsList = new ArrayList<String>();
			        resultDetailsList.add("CONSOLIDATED RESULT SHEET : "+studentResult.getAcademicYear()+" (TERM-I)");
			        data.put(2, resultDetailsList);
			        
			        List<String> standardDetailsList = new ArrayList<String>();
			        standardDetailsList.add("STANDARD : "+studentResult.getStandard()+" - "+studentResult.getSection());
			        data.put(3, standardDetailsList);		        
			        data.put(4, new ArrayList<String>());
			        
					List<String> headerList = new ArrayList<String>();				 				 
					headerList.add("Roll No");				 
					headerList.add("Name");				 
					 
					for(SubjectGroup subjectGroup : subjectGroupList){					
						headerList.add(subjectGroup.getSubjectGroupName());
						headerList.add("");
						headerList.add("");
						headerList.add("");
						headerList.add("");
					}	        	
			        data.put(5, headerList);
			        
			        List<String> secondHeaderList = new ArrayList<String>();
			        secondHeaderList.add("");
			        secondHeaderList.add("");
			        for(SubjectGroup subjectGroup : subjectGroupList){				       				       
				        secondHeaderList.add("FA1");
				        secondHeaderList.add("FA2");
				        secondHeaderList.add("SA1");
				        secondHeaderList.add("Tot");
				        secondHeaderList.add("Grd/GP");
			        } 
			        data.put(6, secondHeaderList);		        
			        data.put(7, new ArrayList<String>());
			        Integer count = 8;
			        
			        for(Student student : studentsSubjectsAndMarksList){		        	
			        	List<String> rowList = new ArrayList<String>();		        	
			        	rowList.add(student.getRollNumber().toString());
			        	rowList.add(student.getStudentName());				        	
			        	
			        	for(StudentResult studentResult2 : student.getStudentResultList()){
			        		
			        		if(studentResult2.getFa1() == null){
								rowList.add("");			        			
			        		}else if(studentResult2.getFa1() < 0){
			        			rowList.add("AB");
							}else{
								rowList.add(studentResult2.getFa1().toString());
							}
			        		
			        		if(studentResult2.getFa2() == null){
								rowList.add("");			        			
			        		}else if(studentResult2.getFa2() < 0){
			        			rowList.add("AB");
							}else{
								rowList.add(studentResult2.getFa2().toString());
							}
							
			        		if(studentResult2.getSa1() == null){
								rowList.add("");			        			
			        		}else if(studentResult2.getSa1() < 0){
			        			rowList.add("AB");
							}else{
								rowList.add(studentResult2.getSa1().toString());
							}
							
							if(studentResult2.getGradepoint() != null){
								rowList.add(studentResult2.getGradepoint().toString());
							}else{
								rowList.add("");
							}
							
							if(studentResult2.getGrade() != null && studentResult2.getGradepoint() != null){
								rowList.add(studentResult2.getGrade()+"/"+studentResult2.getGradepoint());
							}else if(studentResult2.getGrade() == null && studentResult2.getGradepoint() != null){
								rowList.add("-/"+studentResult2.getGradepoint());
							}else if(studentResult2.getGrade() != null && studentResult2.getGradepoint() == null){
								rowList.add(studentResult2.getGrade()+"/-");
							}else{
								rowList.add("-/-");
							}
						}   	
		        	    data.put(count, rowList);		
			            int loopControl = count;
			        	loopControl++;
			        	count = loopControl;		 
			        }
			        //Iterate over data and write to sheet
			        Set<Integer> keyset = data.keySet();
			        int rownum = 0;
			        for (Integer key : keyset){
			            Row row = sheet.createRow(rownum++);
			            List<String> rowsList = data.get(key);
			            int cellnum = 0;
			            for (String obj : rowsList) {
			               Cell cell = row.createCell(cellnum++);
			               if(obj instanceof String){
			                    cell.setCellValue((String)obj);
			                    if(cellnum == 2 && key >= 8){
			                    	cell.setCellStyle(nameCellStyle);
			                    }else if(cellnum > 2 && key >= 8){
			                    	cell.setCellStyle(unlockedCellStyle);
			                    }else{
			                    	cell.setCellStyle(lockedCellStyle);
			                    }
			               }			                
			            }
			        }
				   	workbook.getSheetAt(sheetCount).autoSizeColumn(1);
				   	
			        //Write the workbook in file system  
		            FileOutputStream out = new FileOutputStream(new File(reportResultPdfFilPath));
		            workbook.write(out);
		            out.flush();
		            out.close();
		            status = "Success";				         
				} catch (Exception e) {
					e.printStackTrace();
		        	logger.error(e);
	        	}
			}else if(studentResult.getExam().equalsIgnoreCase("SA2")){
				int sheetCount = 0;
				HSSFWorkbook workbook = new HSSFWorkbook();         
				//Create a blank sheet				
				try  {	
					HSSFSheet sheet = workbook.createSheet("consolidate_result_"+studentResult.getStandard()+"_"+studentResult.getSection()+"_"+studentResult.getExam());
			        sheet.protectSheet("secretPassword");	 
			        
			        //MERGING CODE
			        sheet.addMergedRegion(new CellRangeAddress(0,0,0,20));//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(1,1,0,20));//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(2,2,0,20));//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(3,3,0,20));//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(4,4,2,7));//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(4,4,8,13));//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(4,4,14,19));//(rowFrom, rowTo, colFrom, colTo)

			        sheet.setDefaultColumnWidth(10);		        
			        
			        HSSFFont hSSFFont = workbook.createFont();
			        hSSFFont.setFontName(HSSFFont.FONT_ARIAL);
			        hSSFFont.setFontHeightInPoints((short) 12);
			        hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			        hSSFFont.setColor(HSSFColor.BLACK.index);
			        			        
			        /* cell style for locking */
			        CellStyle lockedCellStyle = workbook.createCellStyle();
			        lockedCellStyle.setFont(hSSFFont);
			        lockedCellStyle.setLocked(true);
			        lockedCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
			        
			        CellStyle nameCellStyle = workbook.createCellStyle();
			        nameCellStyle.setFont(hSSFFont);
			        nameCellStyle.setLocked(true);
			        nameCellStyle.setAlignment(CellStyle.ALIGN_LEFT);
			        
			        /* cell style for editable cells */
			        CellStyle unlockedCellStyle = workbook.createCellStyle();
			        unlockedCellStyle.setLocked(false);	
			        unlockedCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
			        
			        //This data needs to be written (Object[])
			        Map<Integer, List<String>> data = new TreeMap<Integer, List<String>>();	
			        
			        List<String> schoolDetailsList = new ArrayList<String>();
			        schoolDetailsList.add("SAINIK SCHOOL PURULIA");
			        data.put(1, schoolDetailsList);
			        
			        
			        List<String> resultDetailsList = new ArrayList<String>();
			        resultDetailsList.add("CONSOLIDATED RESULT SHEET : "+studentResult.getAcademicYear()+" (TERM-II)");
			        data.put(2, resultDetailsList);
			        
			        List<String> standardDetailsList = new ArrayList<String>();
			        standardDetailsList.add("STANDARD : "+studentResult.getStandard()+" - "+studentResult.getSection());
			        data.put(3, standardDetailsList);		        
			        data.put(4, new ArrayList<String>());
			     
					List<String> headerList = new ArrayList<String>();						 
					headerList.add("Roll No");				 
					headerList.add("Name");				 
					 
					for(SubjectGroup subjectGroup : subjectGroupList){					
						 headerList.add(subjectGroup.getSubjectGroupName());
						 headerList.add("");
						 headerList.add("");
						 headerList.add("");
						 headerList.add("");
						 headerList.add("");
					}      
					headerList.add("CGPA");	        	
			        data.put(5, headerList);			        
			        
			        
			        List<String> secondHeaderList = new ArrayList<String>();
			        secondHeaderList.add("");
			        secondHeaderList.add("");
			        for(SubjectGroup subjectGroup : subjectGroupList){				       				       
				        secondHeaderList.add("T1");
				        secondHeaderList.add("F3");
				        secondHeaderList.add("F4");
				        secondHeaderList.add("SA2");
				        secondHeaderList.add("Tot");
				        secondHeaderList.add("Grd/GP");
			        } 
			        secondHeaderList.add("");
			        data.put(6, secondHeaderList);
			        
			        data.put(7, new ArrayList<String>());
			        Integer count = 8;
			        for(Student student : studentsSubjectsAndMarksList){		        	
			        	List<String> rowList = new ArrayList<String>();		        	
			        	rowList.add(student.getRollNumber().toString());
			        	rowList.add(student.getStudentName());	
			        	
			        	
			        	for(StudentResult studentResult2 : student.getStudentResultList()){
			        		
							if(studentResult2.getT1() != null){
								rowList.add(studentResult2.getT1().toString());
							}
							if(studentResult2.getFa3() != null){
								rowList.add(studentResult2.getFa3().toString());
							}
							if(studentResult2.getFa4() != null){
								rowList.add(studentResult2.getFa4().toString());
							}							
							if(studentResult2.getSa2()!= null){
								rowList.add(studentResult2.getSa2().toString());
							}
							if(studentResult2.getTotalTerm1() != null){
								rowList.add(studentResult2.getTotalTerm1().toString());
							}
							if(studentResult2.getGrade() != null){
								rowList.add(studentResult2.getGrade().toString()+"/"+studentResult2.getGradepoint().toString());
							}							
						}
			        	if(student.getCgpa() != null){
			        		rowList.add((student.getCgpa()!=null?new DecimalFormat("###.##").format(student.getCgpa()).toString():"-"));
			        	}
			        	
		        	    data.put(count, rowList);		
			            int loopControl = count;
			        	loopControl++;
			        	count = loopControl;		 
			        }
			        //Iterate over data and write to sheet
			        Set<Integer> keyset = data.keySet();
			        int rownum = 0;
			        for (Integer key : keyset){
			            Row row = sheet.createRow(rownum++);
			            List<String> rowsList = data.get(key);
			            int cellnum = 0;
			            for (String obj : rowsList) {
			               Cell cell = row.createCell(cellnum++);
			               if(obj instanceof String){
			            	   cell.setCellValue((String)obj);
			            	   if(cellnum == 2 && key >= 8){
			                    	cell.setCellStyle(nameCellStyle);
			                   }else if(cellnum > 2 && key >= 8){
			                    	cell.setCellStyle(unlockedCellStyle);
			                   }else{
			                    	cell.setCellStyle(lockedCellStyle);
			                   }
			               }			                
			            }
			        }
				   	workbook.getSheetAt(sheetCount).autoSizeColumn(1);
				   	
			        //Write the workbook in file system  
		            FileOutputStream out = new FileOutputStream(new File(reportResultPdfFilPath));
		            workbook.write(out);
		            out.flush();
		            out.close();
		            status = "Success";				         
				} catch (Exception e) {
					e.printStackTrace();
		        	logger.error(e);
	        	}
			}else if(studentResult.getExam().equalsIgnoreCase("FA1") || studentResult.getExam().equalsIgnoreCase("FA2") 
					|| studentResult.getExam().equalsIgnoreCase("FA3") || studentResult.getExam().equalsIgnoreCase("FA4")){
						
				int sheetCount = 0;
				HSSFWorkbook workbook = new HSSFWorkbook();         
				//Create a blank sheet				
				try  {	
					HSSFSheet sheet = workbook.createSheet("consolidate_result_"+studentResult.getStandard()+"_"+studentResult.getSection()+"_"+studentResult.getExam());	 
			        
			        sheet.protectSheet("secretPassword");		        
			        
			        //MERGING CODE
			        sheet.addMergedRegion(new CellRangeAddress(0,0,0,7));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(1,1,0,7));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(2,2,0,7));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(3,3,0,7));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(4,4,2,3));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(4,4,4,5));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(4,4,6,7));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(4,4,8,9));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(4,4,10,11));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(4,4,12,13));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(4,4,14,15));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(4,4,16,17));	//(rowFrom, rowTo, colFrom, colTo)	
			        
			        sheet.setDefaultColumnWidth(10);
			        
			        HSSFFont hSSFFont = workbook.createFont();
			        hSSFFont.setFontName(HSSFFont.FONT_ARIAL);
			        hSSFFont.setFontHeightInPoints((short) 12);
			        hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			        hSSFFont.setColor(HSSFColor.BLACK.index);
			        			        
			        /* cell style for locking */
			        CellStyle lockedCellStyle = workbook.createCellStyle();
			        lockedCellStyle.setFont(hSSFFont);
			        lockedCellStyle.setLocked(true);
			        lockedCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
			        
			        CellStyle nameCellStyle = workbook.createCellStyle();
			        nameCellStyle.setFont(hSSFFont);
			        nameCellStyle.setLocked(true);
			        nameCellStyle.setAlignment(CellStyle.ALIGN_LEFT);
			        
			        /* cell style for editable cells */
			        CellStyle unlockedCellStyle = workbook.createCellStyle();
			        unlockedCellStyle.setLocked(false);	
			        unlockedCellStyle.setAlignment(CellStyle.ALIGN_CENTER);		        
			        
			        //This data needs to be written (Object[])
			        Map<Integer, List<String>> data = new TreeMap<Integer, List<String>>();	
			        
			        List<String> schoolDetailsList = new ArrayList<String>();
			        schoolDetailsList.add("SAINIK SCHOOL PURULIA");
			        data.put(1, schoolDetailsList);
			        
			        
			        List<String> resultDetailsList = new ArrayList<String>();
			        resultDetailsList.add("CONSOLIDATED RESULT SHEET : "+studentResult.getAcademicYear()+" ("+studentResult.getExam()+")");
			        data.put(2, resultDetailsList);
			        
			        List<String> standardDetailsList = new ArrayList<String>();
			        standardDetailsList.add("STANDARD : "+studentResult.getStandard()+" - "+studentResult.getSection());
			        data.put(3, standardDetailsList);		        
			        data.put(4, new ArrayList<String>());
			     
					List<String> headerList = new ArrayList<String>();					 
					headerList.add("Roll No");				 
					headerList.add("Name");				 
					 
					for(SubjectGroup subjectGroup : subjectGroupList){					
						headerList.add(subjectGroup.getSubjectGroupName());
						headerList.add("");					 
					}       		        	
			        data.put(5, headerList);		        
			        data.put(6, new ArrayList<String>());
			        Integer count = 7;				
			        
			        for(Student student : studentsSubjectsAndMarksList){		        	
			        	List<String> rowList = new ArrayList<String>();		        	
			        	rowList.add(student.getRollNumber().toString());
			        	rowList.add(student.getStudentName());		        	
			        	for(StudentResult studentResult2 : student.getStudentResultList()){	
							if(null == studentResult2.getGradepoint()){
								rowList.add("--");
								rowList.add("0.0");
							}else if(studentResult2.getGradepoint() < 0){
								rowList.add(studentResult2.getGrade());
								rowList.add("AB");								
							}else{
								rowList.add(studentResult2.getGrade());
								rowList.add(studentResult2.getGradepoint().toString());								
							}
						}
			        	data.put(count, rowList);		
			            int loopControl = count;
			        	loopControl++;
			        	count = loopControl;		 
			        }
			        //Iterate over data and write to sheet
			        Set<Integer> keyset = data.keySet();
			        int rownum = 0;
			        for (Integer key : keyset){
			            Row row = sheet.createRow(rownum++);
			            List<String> rowsList = data.get(key);
			            int cellnum = 0;
			            for (String obj : rowsList) {
			               Cell cell = row.createCell(cellnum++);
			               if(obj instanceof String){
			            	   cell.setCellValue((String)obj);
			            	   if(cellnum == 2 && key >= 7){
			                    	cell.setCellStyle(nameCellStyle);
			                   }else if(cellnum > 2 && key >= 7){
			                    	cell.setCellStyle(unlockedCellStyle);
			                   }else{
			                    	cell.setCellStyle(lockedCellStyle);
			                   }
			               }
			            }			            
			        }
				   	workbook.getSheetAt(sheetCount).autoSizeColumn(1);
			        
				   	//Write the workbook in file system  
		            FileOutputStream out = new FileOutputStream(new File(reportResultPdfFilPath));
		            workbook.write(out);
		            out.flush();
		            out.close();
		            status = "Success";				         
				} catch (Exception e) {
		        	e.printStackTrace();
		        	logger.error(e);
	        	}
			}					
			return status;
		}
		
//		@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
//		public synchronized  void createConsolidatedReportForExamForCurrentYear(StudentResult studentResult) {
//			try {
//				
//				List<SubjectGroup> subjectGroupList = reportDAO.getSubjectGroup(studentResult);
//				if(subjectGroupList!=null && subjectGroupList.size()!=0){
//					List<Student> studentsSubjectsAndMarksList=reportDAO.getConsolidatedReportForExamForCurrentAcademicYear(studentResult);
//					
//					
//					
//					if(studentsSubjectsAndMarksList!=null && studentsSubjectsAndMarksList.size()!=0){
//						Report report = new Report();
//						
//						report.setSubjectGroupList(subjectGroupList);
//						
//						SchoolDetails schoolDetails = loginService.getSchoolDetails();
//						schoolDetails.setExamName(studentResult.getExam());
//						report.setSchoolDetails(schoolDetails);
//						
//						
//						
//						AcademicYear academicYear = commonService.getCurrentAcademicYear();
//						report.setStudentList(studentsSubjectsAndMarksList);
//						report.setAcademicYear(academicYear);
//						String baseDir = studentResult.getReportConfigPath()+studentResult.getStandard()+"/"+studentResult.getExam()+"/"+"consolidate_result"+"/";
//						String fileName = "consolidate_result_"+studentResult.getStandard()+"_"+studentResult.getSection()+"_"+studentResult.getExam();
//						
//						
//						Student student = new Student();
//						student.setStandard(studentResult.getStandard());
//						student.setSection(studentResult.getSection());
//						report.setStudent(student);
//						
//						
//						String xmlFilePath = baseDir+"xml/";
//						String xmlFileName = fileName+".xml";
//						report.setXmlFilePath(xmlFilePath);
//						report.setXmlFileName(xmlFileName);
//						xMLBuilder.createXMLFileForConsolidateExamResult(report);
//
//						
//						String xsltFilePath = baseDir+"xslt/";
//						String xsltFileName = "result.xsl";
//						report.setXsltFilePath(xsltFilePath);
//						report.setXsltFileName(xsltFileName);
//						
//						String pdfFilePath = studentResult.getPdfPath()+academicYear.getAcademicYearName()+"/"+studentResult.getStandard()+"/"+studentResult.getExam()+"/"+"consolidate_result"+"/";
//						String pdfFileName = fileName+".pdf";
//						report.setPdfFilePath(pdfFilePath);
//						report.setPdfFileName(pdfFileName);
//						
//						try{
//							pdfBuilder.createPDF(report);
//						}catch(IOException e){
//							logger.error(e);
//						}
//						if ((new File(report.getPdfFilePath()+report.getPdfFileName())).exists()) {
//							if (Desktop.isDesktopSupported()) {
//								Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + new File(report.getPdfFilePath()+report.getPdfFileName()));
//							}
//						}
//
//					}
//				}
//				
//			}catch (Exception e) {
//				logger.error(e);
//			 }
//		}

		@Override
		public String getStudentAddressDetails(List<Student> studentList,
				String reportStudentAddressConfigFilePath, String reportstudentAddressPdfFilPath,HttpServletResponse response) {			
			String status=null;
			try {
				List<Student> studentAddressList=reportDAO.getStudentAddressDetails(studentList);
				if(null!=studentAddressList && studentAddressList.size()!=0){
					createReportForStudentAddressDetails(studentAddressList,reportStudentAddressConfigFilePath,reportstudentAddressPdfFilPath, response);
					status="success";					
					}else{
						
					}
			 }catch (Exception e) {
				logger.error(e);
			 }
			 return status;
		}

		private void createReportForStudentAddressDetails(
				List<Student> studentAddressList,String reportStudentAddressConfigFilePath, String reportstudentAddressPdfFilPath, HttpServletResponse response) {
			Report report = new Report();
			SchoolDetails schoolDetails = loginService.getSchoolDetails();
			report.setSchoolDetails(schoolDetails);	
			report.setStudentList(studentAddressList);			
			String baseDir = reportStudentAddressConfigFilePath+"Student_Address"+"/";
			String fileName="Student_Address";
			
			String xmlFilePath = baseDir+"xml/";
			String xmlFileName = "studentAddress"+".xml";
			
			report.setXmlFilePath(xmlFilePath);
			report.setXmlFileName(xmlFileName);
			xMLBuilder.createXMLFileForStudentAddressDetails(report);

			
			String xsltFilePath = baseDir+"xslt/";
			String xsltFileName = "studentAddress.xsl";
			report.setXsltFilePath(xsltFilePath);
			report.setXsltFileName(xsltFileName);
			
			String pdfFilePath = reportstudentAddressPdfFilPath+"Student_Address"+"/";
			String pdfFileName = fileName+".pdf";
			report.setPdfFilePath(pdfFilePath);
			report.setPdfFileName(pdfFileName);
			
			try{
				pdfBuilder.createPDF(report);
			}catch(IOException e){
				logger.error(e);
			}
			if ((new File(report.getPdfFilePath()+report.getPdfFileName())).exists()) {
				if (Desktop.isDesktopSupported()) {
					try {
//						Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + new File(report.getPdfFilePath()+report.getPdfFileName()));
						fileUploadDownload.downloadFile(response, report.getPdfFilePath(), report.getPdfFileName());
					} catch (Exception e) {						
						logger.error(e);
					}
				}
			}
		}
		
		
	
		
		@Override
		public String generateMeritListForAdmisionReport(Report report,String reportResultConfigFilePath, String reportResultPdfFilPath, HttpServletResponse response) {
			String status=null;
			try{
				List<Candidate> candidateList = reportDAO.getgenerateMeritListForAdmisionReport(report);
				if(candidateList!=null && candidateList.size()!=0){
					if(report.getCandidate().getStatus().equals("REVIEW")){
						report.getCandidate().setStatus("UNDER REVIEW");
					}
					if(report.getCandidate().getStatus().equals("QUEUED")){
						report.getCandidate().setStatus("WAITING");
					}
					SchoolDetails schoolDetails = loginService.getSchoolDetails();
					report.setSchoolDetails(schoolDetails);	
					report.setCandidateList(candidateList);		
					String baseDir = reportResultConfigFilePath+"Admission_Merit_List"+"/";
					String fileName="Admission_Merit_List_Report";
					
					String xmlFilePath = baseDir+"xml/";
					String xmlFileName = fileName+".xml";
					
					report.setXmlFilePath(xmlFilePath);
					report.setXmlFileName(xmlFileName);
					xMLBuilder.createXMLFileForGenerateAdmisionMeritListReport(report);

					
					String xsltFilePath = baseDir+"xslt/";
					String xsltFileName = fileName+".xsl";
					report.setXsltFilePath(xsltFilePath);
					report.setXsltFileName(xsltFileName);
					
					String pdfFilePath = reportResultPdfFilPath+"Admission Merit List"+"/";
					String pdfFileName = fileName+".pdf";
					report.setPdfFilePath(pdfFilePath);
					report.setPdfFileName(pdfFileName);
					
					try{
						pdfBuilder.createPDF(report);
						status="success";
					}catch(IOException e){
						logger.error(e);
					}
					if ((new File(report.getPdfFilePath()+report.getPdfFileName())).exists()) {
						if (Desktop.isDesktopSupported()) {
							try {
//								Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + new File(report.getPdfFilePath()+report.getPdfFileName()));
								fileUploadDownload.downloadFile(response, report.getPdfFilePath(), report.getPdfFileName());
							} catch (Exception e) {						
								logger.error(e);
							}
						}
					}
				}
				
			}catch(Exception e){
				logger.error(e);
			}
			return status;
		}		

@Override
		public String generateExamVenueWiseCandidatesReport(Report report,String reportResultConfigFilePath, String reportResultPdfFilPath, HttpServletResponse response) {
			String status=null;
			try{
				List<Candidate> candidateList = reportDAO.generateExamVenueWiseCandidatesReport(report);
				if(candidateList!=null && candidateList.size()!=0){
					
					SchoolDetails schoolDetails = loginService.getSchoolDetails();
					report.setSchoolDetails(schoolDetails);	
					report.setCandidateList(candidateList);		
					String baseDir = reportResultConfigFilePath+"Admission_Exam_Centre"+"/";
					String fileName="Admission_Exam_Centre_Report";
					
					String xmlFilePath = baseDir+"xml/";
					String xmlFileName = fileName+".xml";
					
					report.setXmlFilePath(xmlFilePath);
					report.setXmlFileName(xmlFileName);
					xMLBuilder.createXMLFileForGenerateExamVenueWiseCandidatesReport(report);

					
					String xsltFilePath = baseDir+"xslt/";
					String xsltFileName = fileName+".xsl";
					report.setXsltFilePath(xsltFilePath);
					report.setXsltFileName(xsltFileName);
					
					String pdfFilePath = reportResultPdfFilPath+"Admission Exam Centre"+"/";
					String pdfFileName = fileName+".pdf";
					report.setPdfFilePath(pdfFilePath);
					report.setPdfFileName(pdfFileName);
					
					try{
						pdfBuilder.createPDF(report);
						status="success";
					}catch(IOException e){
						logger.error(e);
					}
					if ((new File(report.getPdfFilePath()+report.getPdfFileName())).exists()) {
						if (Desktop.isDesktopSupported()) {
							try {
//								Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + new File(report.getPdfFilePath()+report.getPdfFileName()));
								fileUploadDownload.downloadFile(response, report.getPdfFilePath(), report.getPdfFileName());
							} catch (Exception e) {						
								logger.error(e);
							}
						}
					}
				}
			}catch(Exception e){
				logger.error(e);
			}
			return status;
		}			

@Override
		public String generateSocialCategoryWiseClassStrengthReport(Report report, String reportResultConfigFilePath,String reportResultPdfFilPath, HttpServletResponse response) {
			String status=null;
			try{
				List<Standard> standardList = reportDAO.getSocialCategoryWiseClassStrengthReportData(report);
				if(standardList!=null && standardList.size()!=0){
					
					SchoolDetails schoolDetails = loginService.getSchoolDetails();
					AcademicYear academicYear = commonService.getCurrentAcademicYear();
					report.setAcademicYear(academicYear);
					report.setSchoolDetails(schoolDetails);	
					report.setStandardList(standardList);		
					String baseDir = reportResultConfigFilePath+"Social_Category_Wise_Class_Strength"+"/";
					String fileName="Social_Category_Wise_Class_Strength_Report";
					
					String xmlFilePath = baseDir+"xml/";
					String xmlFileName = fileName+".xml";
					
					report.setXmlFilePath(xmlFilePath);
					report.setXmlFileName(xmlFileName);
					xMLBuilder.createXMLFileForGenerateSocialCategoryWiseClassStrengthReport(report);

					
					String xsltFilePath = baseDir+"xslt/";
					String xsltFileName = fileName+".xsl";
					report.setXsltFilePath(xsltFilePath);
					report.setXsltFileName(xsltFileName);
					
					String pdfFilePath = reportResultPdfFilPath+"Social Category Wise Class Strength"+"/";
					String pdfFileName = fileName+".pdf";
					report.setPdfFilePath(pdfFilePath);
					report.setPdfFileName(pdfFileName);
					
					try{
						pdfBuilder.createPDF(report);
						status="success";
					}catch(IOException e){
						logger.error(e);
					}
					if ((new File(report.getPdfFilePath()+report.getPdfFileName())).exists()) {
						if (Desktop.isDesktopSupported()) {
							try {
//								Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + new File(report.getPdfFilePath()+report.getPdfFileName()));
								fileUploadDownload.downloadFile(response, report.getPdfFilePath(), report.getPdfFileName());
							} catch (Exception e) {						
								logger.error(e);
							}
						}
					}
				}
			}catch(Exception e){
				logger.error(e);
			}
			return status;		
		}		


	@Override
	public String getConsolidatedResultReport(Standard standard,Section section, AcademicYear academicYear, Exam exam, String reportResultConfigFilePath, 
												String reportResultPdfFilPath, String excel, String studentMarkSheetExcel, HttpServletResponse response ) {
		boolean currentYear = false;
		String status = null;
		AcademicYear currentAcademicYear;
		try {
			currentAcademicYear = commonService.getCurrentAcademicYear();
			if(null != currentAcademicYear){
				if(currentAcademicYear.getAcademicYearCode().equalsIgnoreCase(academicYear.getAcademicYearCode())){
					currentYear = true;// For Current Year Report
				}else{
					currentYear = false;// ForPrevious Year Report
				}
				StudentResult studentResult = new StudentResult();
				studentResult.setStatus(standard.getStatus());
				studentResult.setExam(exam.getExamName());
				studentResult.setStandard(standard.getStandardCode());
				studentResult.setSection(section.getSectionCode());
				studentResult.setAcademicYear(academicYear.getAcademicYearCode());
				studentResult.setReportConfigPath(reportResultConfigFilePath);	
				studentResult.setPdfPath(reportResultPdfFilPath);
				if(currentYear){
					if(!studentResult.getExam().equalsIgnoreCase("IE"))	{
						createConsolidatedReportForExamForCurrentYear(studentResult, reportResultPdfFilPath, excel, studentMarkSheetExcel, response);
					}else{
						createConsolidateReportForInternalExamination(studentResult, reportResultPdfFilPath, excel, studentMarkSheetExcel, response);
					}
					status = "success";					
				}else{
					// for previous years
				}
			}
		}catch (CustomException e) {
			e.printStackTrace();
			logger.error(e);
		}
		return status;
	}


	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public synchronized  void createConsolidatedReportForExamForCurrentYear(StudentResult studentResult,String reportResultPdfFilPath, String excel, String studentMarkSheetExcel, HttpServletResponse response) {
		try {		
			List<SubjectGroup> subjectGroupList = reportDAO.getSubjectGroup(studentResult);
			if(null != subjectGroupList && subjectGroupList.size() != 0){
				String status = null;
				List<Student> studentsSubjectsAndMarksList = reportDAO.getConsolidatedReportForExamForCurrentAcademicYear(studentResult);		
				
				//Setting subjects which are not available in student result
				if(!(studentResult.getStandard().equalsIgnoreCase("XI") || studentResult.getStandard().equalsIgnoreCase("XII"))){
					if(null != studentsSubjectsAndMarksList && studentsSubjectsAndMarksList.size()!=0){
						for(SubjectGroup sg : subjectGroupList){
							for(Student stu : studentsSubjectsAndMarksList){
								List<StudentResult> srl = stu.getStudentResultList();
								int i = subjectGroupList.indexOf(sg);
								if(!srl.get(i).getSubject().equalsIgnoreCase(sg.getSubjectGroupName())){
									StudentResult sr = new StudentResult();
									sr.setSubject(sg.getSubjectGroupName());
									srl.add(i, sr);
								}
								stu.setStudentResultList(srl);
							}
						}
					}
				}
				/*for(SubjectGroup sg:subjectGroupList){
					System.out.println(sg.getSubjectGroupName());
				}
				System.out.println("*******************");
				for(Student s:studentsSubjectsAndMarksList){
					System.out.println(s.getRollNumber()+"\t\t"+s.getStudentResultList().size());
					for(StudentResult sr:s.getStudentResultList()){
						System.out.println("\t\t"+sr.getSubject()+"\t"+sr.getGrade()+"/"+sr.getGradepoint());
					}
				}*/
				if(null != excel){
					status = createStudentMarkSheetExcel(subjectGroupList, studentResult, studentsSubjectsAndMarksList, reportResultPdfFilPath, studentMarkSheetExcel);								
				}else{
					if(null != studentsSubjectsAndMarksList && studentsSubjectsAndMarksList.size() != 0){
						Report report = new Report();
						report.setStatus(studentResult.getStatus());
						report.setSubjectGroupList(subjectGroupList);
						
						SchoolDetails schoolDetails = loginService.getSchoolDetails();
						schoolDetails.setExamName(studentResult.getExam());
						report.setSchoolDetails(schoolDetails);				
						
						AcademicYear academicYear = commonService.getCurrentAcademicYear();
						report.setStudentList(studentsSubjectsAndMarksList);
						report.setAcademicYear(academicYear);
						//String baseDir;
						//String fileName;
						/*if(studentResult.getStandard().equalsIgnoreCase("XI")){
							String exam=studentResult.getExam().replace(" ", "_");
							baseDir = studentResult.getReportConfigPath()+"result/"+studentResult.getStandard()+"/"+exam+"/"+"consolidate_result"+"/";
							fileName = "consolidate_result_"+studentResult.getStandard()+"_"+studentResult.getSection()+"_"+exam;				
						}else{*/
						String baseDir = studentResult.getReportConfigPath()+"result/"+studentResult.getStandard()+"/"+studentResult.getExam()+"/"+"consolidate_result"+"/";
						String fileName = "consolidate_result_"+studentResult.getStandard()+"_"+studentResult.getSection()+"_"+studentResult.getExam();				
						//}	
											
						Student student = new Student();
						student.setStandard(studentResult.getStandard());
						student.setSection(studentResult.getSection());
						report.setStudent(student);				
						
						String xmlFilePath = baseDir+"xml/";
						String xmlFileName = fileName+".xml";
						report.setXmlFilePath(xmlFilePath);
						report.setXmlFileName(xmlFileName);
						String xsltFilePath;
						if (studentResult.getStandard().equalsIgnoreCase("XI")||studentResult.getStandard().equalsIgnoreCase("XII")){
							xMLBuilder.createXMLFileForConsolidateExamResultXI_XII(report);
							xsltFilePath = baseDir+"xslt/";
						}else{
							xMLBuilder.createXMLFileForConsolidateExamResult(report);
							xsltFilePath = baseDir+"xslt/";
						}
						
						String xsltFileName = "result.xsl";
						report.setXsltFilePath(xsltFilePath);
						report.setXsltFileName(xsltFileName);
						
						String pdfFilePath = studentResult.getPdfPath()+academicYear.getAcademicYearName()+"/"+studentResult.getStandard()+"/"+studentResult.getExam()+"/"+"consolidate_result"+"/";
						String pdfFileName = fileName+".pdf";
						report.setPdfFilePath(pdfFilePath);
						report.setPdfFileName(pdfFileName);
						
						try{
							pdfBuilder.createPDF(report);
						}catch(IOException e){
							logger.error(e);
							e.printStackTrace();
						}
						if ((new File(report.getPdfFilePath()+report.getPdfFileName())).exists()) {
							if (Desktop.isDesktopSupported()) {
		//						Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + new File(report.getPdfFilePath()+report.getPdfFileName()));
								fileUploadDownload.downloadFile(response, report.getPdfFilePath(), report.getPdfFileName());
							}
						}
		
					}
				}				
			}				
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}


	@Override
	public String showTCReport(String rollNumber, String reportResultConfigFilePath, 
								String reportResultPdfFilPath, HttpServletResponse response) {				
		String status = null;	
		try {
			if(null != rollNumber){					
				Student student = reportDAO.showTCReport(rollNumber);					
				if(null != student){
					status = createTCReport(student, reportResultConfigFilePath, reportResultPdfFilPath, response);
				}							
			}
		} catch (Exception e) {
			logger.error(e);
		}			
		return status;	
	}

	private String createTCReport(Student student, String reportResultConfigFilePath, String reportResultPdfFilPath,
			HttpServletResponse response) {
		String status = null;
		Report report = null;
		try{
			if(null != student){
				report = new Report();
				String baseDir = reportResultConfigFilePath+"TC"+"/";
				String fileName = "TC";			
				String xmlFilePath = baseDir+"xml/";
				String xmlFileName = fileName+".xml";
				report.setStudent(student);
				report.setXmlFilePath(xmlFilePath);
				report.setXmlFileName(xmlFileName);
				xMLBuilder.createXMLFileForTCReport(report);
					
				String xsltFilePath = baseDir+"xslt/";
				String xsltFileName = fileName+".xsl";
				report.setXsltFilePath(xsltFilePath);
				report.setXsltFileName(xsltFileName);
				
				String pdfFilePath = reportResultPdfFilPath+"TC"+"/";
				String pdfFileName = fileName+".pdf";
				report.setPdfFilePath(pdfFilePath);
				report.setPdfFileName(pdfFileName);
				
				try{
					pdfBuilder.createPDF(report);
					status="success";
				}catch(IOException e){
					logger.error(e);
				}
				if ((new File(report.getPdfFilePath()+report.getPdfFileName())).exists()) {
					if (Desktop.isDesktopSupported()) {
						try {
	//						Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + new File(report.getPdfFilePath()+report.getPdfFileName()));
							fileUploadDownload.downloadFile(response, report.getPdfFilePath(), report.getPdfFileName());
						} catch (Exception e) {						
							logger.error(e);
						}
					}
				}
			}
		}catch(Exception e){
			logger.error(e);
		}
		return status;		
	}




































		@Override
		public String getConsolidatedResultForXII_XII(Standard standard, Section section, AcademicYear academicYear, Exam exam,
				String reportResultConfigFilePath, String reportResultPdfFilPath, HttpServletResponse response) {
			boolean currentYear = false;
			String status=null;
			AcademicYear currentAcademicYear;
			try {
				currentAcademicYear = commonService.getCurrentAcademicYear();
				if(null!=currentAcademicYear){
					if(currentAcademicYear.getAcademicYearCode().equalsIgnoreCase(academicYear.getAcademicYearCode())){
						currentYear=true;// For Current Year Report
					}else{
						currentYear=false;// ForPrevious Year Report
					}
					StudentResult studentResult=new StudentResult();
					studentResult.setExam(exam.getExamName());
					studentResult.setStandard(standard.getStandardCode());
					studentResult.setSection(section.getSectionCode());
					studentResult.setAcademicYear(academicYear.getAcademicYearCode());
					studentResult.setReportConfigPath(reportResultConfigFilePath);	
					studentResult.setPdfPath(reportResultPdfFilPath);
					if(currentYear){
						createConsolidatedReportForXI_XIIForCurrentYear(studentResult, reportResultPdfFilPath, response);
						status="success";					
					}else{
						// for previous years
					}
				}
			 }catch (CustomException e) {
				logger.error(e);
				//e.printStackTrace();
			 }
			 return status;
		}
		
		@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
		public synchronized  void createConsolidatedReportForXI_XIIForCurrentYear(StudentResult studentResult,String reportResultPdfFilPath, HttpServletResponse response) {
			try {
				String status = null;	
				List<SubjectGroup> subjectGroupList = reportDAO.getSubjectGroupForXI_XIIResult(studentResult);
				if(null != subjectGroupList && subjectGroupList.size() != 0){
					List<Student> studentsSubjectsAndMarksList = reportDAO.getConsolidatedReportForXI_XIIForCurrentYear(studentResult);		
//					for(SubjectGroup sg:subjectGroupList){
//						System.out.println(sg.getSubjectGroupName());
//					}
//					System.out.println("==========================");
//					for(Student s : studentsSubjectsAndMarksList){
//						System.out.println(s.getRollNumber());
//						for(StudentResult sr:s.getStudentResultList()){
//							System.out.println("\t"+sr.getSubject());
//						}
//					}
//					System.out.println("************************");
					
					//Setting subjects which are not available in student result
					if(null != studentsSubjectsAndMarksList && studentsSubjectsAndMarksList.size()!=0){
						for(SubjectGroup sg : subjectGroupList){
							for(Student s : studentsSubjectsAndMarksList){
								List<StudentResult> srl = s.getStudentResultList();
								int i = subjectGroupList.indexOf(sg);
								//System.out.println(sg.getSubjectGroupName());
								if(subjectGroupList.size() > srl.size()){
									if(i>=srl.size()){
										StudentResult sr = new StudentResult();
										sr.setSubject(sg.getSubjectGroupName());
										srl.add(i, sr);
									}else if(!srl.get(i).getSubject().equalsIgnoreCase(sg.getSubjectGroupName())){
										StudentResult sr = new StudentResult();
										sr.setSubject(sg.getSubjectGroupName());
										srl.add(i, sr);
									}
									s.setStudentResultList(srl);
								}								
							}
						}
					}					
					
					if(null != studentsSubjectsAndMarksList && studentsSubjectsAndMarksList.size() != 0){
						Report report = new Report();
						report.setStatus(studentResult.getStatus());
						report.setSubjectGroupList(subjectGroupList);
						
						SchoolDetails schoolDetails = loginService.getSchoolDetails();
						schoolDetails.setExamName(studentResult.getExam());
						report.setSchoolDetails(schoolDetails);				
						
						AcademicYear academicYear = commonService.getCurrentAcademicYear();
						report.setStudentList(studentsSubjectsAndMarksList);
						report.setAcademicYear(academicYear);
						String baseDir = studentResult.getReportConfigPath()+"result/"+studentResult.getStandard()+"/"+studentResult.getExam()+"/"+"consolidate_result"+"/";
						baseDir = baseDir.replace(" ", "_");
						String fileName = "consolidate_result_"+studentResult.getStandard()+"_"+studentResult.getSection()+"_"+studentResult.getExam();				
						fileName = fileName.replace(" ", "_");						
						
						Student student = new Student();
						student.setStandard(studentResult.getStandard());
						student.setSection(studentResult.getSection());
						report.setStudent(student);
						
						String xmlFilePath = baseDir+"xml/";
						String xmlFileName = fileName+".xml";
						report.setXmlFilePath(xmlFilePath);
						report.setXmlFileName(xmlFileName);
						xMLBuilder.createXMLFileForConsolidateExamResultXI_XII(report);
						
						String xsltFilePath = baseDir+"xslt/";
						String xsltFileName = "result.xsl";
						report.setXsltFilePath(xsltFilePath);
						report.setXsltFileName(xsltFileName);
						
						String pdfFilePath = studentResult.getPdfPath()+academicYear.getAcademicYearName()+"/"+studentResult.getStandard()+"/"+studentResult.getExam()+"/"+"consolidate_result"+"/";
						String pdfFileName = fileName+".pdf";
						report.setPdfFilePath(pdfFilePath);
						report.setPdfFileName(pdfFileName);
						
						try{
							pdfBuilder.createPDF(report);
						}catch(IOException e){
							logger.error(e);
						}
						if ((new File(report.getPdfFilePath()+report.getPdfFileName())).exists()) {
							if (Desktop.isDesktopSupported()) {
		//						Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + new File(report.getPdfFilePath()+report.getPdfFileName()));
								fileUploadDownload.downloadFile(response, report.getPdfFilePath(), report.getPdfFileName());
							}
						}
		
					}
				}				
			}catch (Exception e) {
				logger.error(e);
				//e.printStackTrace();
			}
		}
		
		
		@Override
		public String getStudentReportFORXI_XII(Standard standard,
													Section section, AcademicYear academicYear, Exam exam,
													String[] roll, String reportResultConfigFilePath,
													String reportResultPdfFilPath,String reportExamResultChatImagePath,HttpServletResponse response) {			
			boolean currentYear = false;
			String status=null;
			AcademicYear currentAcademicYear;
			try {
				currentAcademicYear = commonService.getCurrentAcademicYear();
				if(null!=currentAcademicYear){
					if(currentAcademicYear.getAcademicYearCode().equalsIgnoreCase(academicYear.getAcademicYearCode())){
						currentYear=true;// For Current Year Report
					}else{
						currentYear=false;// ForPrevious Year Report
					}
					
					if(null!=roll && roll.length!=0){
						List<String> studentsRollList = new ArrayList<String>(Arrays.asList(roll));
						StudentResult studentResult=new StudentResult();
						studentResult.setStringList(studentsRollList);
						studentResult.setExam(exam.getExamName());
						studentResult.setStandard(standard.getStandardCode());
						studentResult.setSection(section.getSectionCode());
						studentResult.setAcademicYear(academicYear.getAcademicYearCode());
						studentResult.setReportConfigPath(reportResultConfigFilePath);	
						studentResult.setPdfPath(reportResultPdfFilPath);
						if(currentYear){
							createReportForExamXI_XII(studentResult,response);
							status="success";					
						}else{
							// for previous years
						}
					}
				}
			} catch (CustomException e) {
				logger.error(e);
				//e.printStackTrace();
			}
			return status;
		}
		
		
		@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
		public synchronized  void createReportForExamXI_XII(StudentResult studentResult,HttpServletResponse response) {
			try {
				List<Student> studentsSubjectsAndMarksList = reportDAO.getcreateReportForExamXI_XII(studentResult);
				if(null != studentsSubjectsAndMarksList && studentsSubjectsAndMarksList.size() != 0){
					Report report = new Report();
					SchoolDetails schoolDetails = loginService.getSchoolDetails();
					schoolDetails.setExamName(studentResult.getExam());
					report.setSchoolDetails(schoolDetails);
					Student studentDetails = null;
					for(Student student : studentsSubjectsAndMarksList){
						studentDetails = commonService.getStudentDetails(student.getRollNumber().toString());
						if(studentDetails != null){
							student.setStudentName(studentDetails.getStudentName());
							student.setStandard(studentDetails.getStandard());
							student.setSection(studentDetails.getSection());
							student.setHouse(studentDetails.getHouse());
							student.setResource(studentDetails.getResource());
						}
					}
					AcademicYear academicYear = commonService.getCurrentAcademicYear();
					report.setAcademicYear(academicYear);
					report.setStudent(studentDetails);
					report.setStudentList(studentsSubjectsAndMarksList);
					List<String> rollList = studentResult.getStringList();
					String baseDir = studentResult.getReportConfigPath()+"result/"+studentDetails.getStandard()+"/"+studentResult.getExam()+"/";
					baseDir = baseDir.replace(" ", "_");
					String fileName="";
					if(null != rollList && rollList.size()!=0 ){
						if( rollList.size() == 1){
							fileName = studentDetails.getRollNumber()+"_"+studentDetails.getStandard()+"_"+studentResult.getExam();
						}if( rollList.size() > 1){
							fileName = studentDetails.getStandard()+"_"+studentResult.getExam();
						}
						fileName = fileName.replace(" ", "_");

						String xmlFilePath = baseDir+"xml/";
						String xmlFileName = "result"+".xml";
						report.setXmlFilePath(xmlFilePath);
						report.setXmlFileName(xmlFileName);
						xMLBuilder.createXMLFileForExamResultXI_XII(report);
						
						String xsltFilePath = baseDir+"xslt/";
						String xsltFileName = "result.xsl";
						report.setXsltFilePath(xsltFilePath);
						report.setXsltFileName(xsltFileName);
					
						String pdfFilePath = studentResult.getPdfPath()+academicYear.getAcademicYearName()+"/"+studentDetails.getStandard()+"/"+studentResult.getExam()+"/";
						String pdfFileName = fileName+".pdf";
						report.setPdfFilePath(pdfFilePath);
						report.setPdfFileName(pdfFileName);
						
						try{
							pdfBuilder.createPDF(report);
						}catch(IOException e){
							logger.error(e);
						}
						if ((new File(report.getPdfFilePath()+report.getPdfFileName())).exists()) {
							if (Desktop.isDesktopSupported()) {
								//Desktop.getDesktop().open(new File(report.getPdfFilePath()+report.getPdfFileName()));
								fileUploadDownload.downloadFile(response, report.getPdfFilePath(), report.getPdfFileName());
							}
						}
					}
				}
				reportDAO.deleteTempTableReportData();
			}catch (CustomException e) {
				logger.error(e);
				//e.printStackTrace();
			}catch (Exception e) {
				logger.error(e);
				//e.printStackTrace();
			}
		}
		
		
		@Override
		public List<UserDefinedExams> getUserExamsForStandard(String standard) {
			return reportDAO.getUserDefinedExamsForStandard(standard);
		}

		@Override
		public String getStudentSubjectMapping(Student studentObject, String reportResultConfigFilePath,
				String reportResultPdfFilPath, HttpServletResponse response) {
			String status = null;	
			try {
				if(null != studentObject){					
					List<StudentSubjectMapping> studentSubjectMappingList = backofficeDAO.listUpdatedStudentSubjectMapping(studentObject);					
					if(null != studentSubjectMappingList && studentSubjectMappingList.size() != 0){						
						createReportForStudentSubjectMappingList(studentObject,studentSubjectMappingList,reportResultConfigFilePath,reportResultPdfFilPath, response);
						status = "success";										
					}					
				}
			} catch (Exception e) {					
				logger.error(e);
			}	
			return status;
		}
		
		
		private void createReportForStudentSubjectMappingList(Student studentObject,List<StudentSubjectMapping> studentSubjectMappingList,
							String reportResultConfigFilePath,String reportResultPdfFilPath, HttpServletResponse response) {
			try {				
				if(null != studentSubjectMappingList && studentSubjectMappingList.size()!=0){
				Report report = new Report();
				SchoolDetails schoolDetails = loginService.getSchoolDetails();					
				report.setSchoolDetails(schoolDetails);					
				report.setStudentSubjectMappingList(studentSubjectMappingList);					
				String baseDir = reportResultConfigFilePath+"Student_Subject_Mapping_List"+"/";
				String fileName = "Student_Subject_Mapping_List";
				
				String xmlFilePath = baseDir+"xml/";
				String xmlFileName = "studentSubjectMappingList"+".xml";
				report.setXmlFilePath(xmlFilePath);
				report.setXmlFileName(xmlFileName);
				xMLBuilder.createXMLFileForStudentSubjectMappingList(report,studentObject);
				
				String xsltFilePath = baseDir+"xslt/";
				String xsltFileName = "studentSubjectMappingList.xsl";
				report.setXsltFilePath(xsltFilePath);
				report.setXsltFileName(xsltFileName);
					
				String pdfFilePath = reportResultPdfFilPath+"Student_Subject_Mapping_List"+"/";
				String pdfFileName = fileName+".pdf";
				report.setPdfFilePath(pdfFilePath);
				report.setPdfFileName(pdfFileName);
				
				try{
				pdfBuilder.createPDF(report);					
				}catch (IOException e) {
					logger.error(e);
				}		
				if ((new File(report.getPdfFilePath()+report.getPdfFileName())).exists()) {
						if (Desktop.isDesktopSupported()) {
//								Desktop.getDesktop().open(new File(report.getPdfFilePath()+report.getPdfFileName()));
							fileUploadDownload.downloadFile(response, report.getPdfFilePath(), report.getPdfFileName());
						}
					}
				}				
			}catch (Exception e) {
				logger.error(e);
			}		
			
		}
		
		
		@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
		public synchronized  void createConsolidateReportForInternalExamination(StudentResult studentResult, String reportResultPdfFilPath, String excel, 
																				String studentMarkSheetExcel, HttpServletResponse response) {
			try{
				List<SubjectGroup> subjectGroupList = reportDAO.getSubjectGroupForInternalExamination(studentResult);
				List<Student> studentsSubjectsAndMarksList = reportDAO.getStudentListForInternalExamination(studentResult);				
				
				List<String> examList = new ArrayList<String>();
				List<String> allExamList = new ArrayList<String>();
				allExamList.add("M1");
				allExamList.add("M2");
				allExamList.add("PC");
				allExamList.add("Term_1");
				for(StudentResult sr : studentsSubjectsAndMarksList.get(0).getStudentResultList()){
					if(!examList.contains(sr.getExam()))
					examList.add(sr.getExam());
				}
				
				for(Student s : studentsSubjectsAndMarksList){
					List<StudentResult> srList = s.getStudentResultList();
					List<StudentResult> srListFinal = new ArrayList<StudentResult>();
					int resultCount = -1;
					int listCount=-1;
					for(SubjectGroup sg : subjectGroupList){
						for(String ex : allExamList){
							resultCount++;
							listCount++;
							if(srList.size() > resultCount){
								StudentResult sr = srList.get(resultCount);
								if(!sr.getExam().equalsIgnoreCase(ex)){
									resultCount--;
									StudentResult srNew = new StudentResult();
									srNew.setExam(ex);
									srNew.setSubject(sg.getSubjectGroupName());
									srListFinal.add(listCount, srNew);
									
								}else{
								if(sr.getSubject().equalsIgnoreCase(sg.getSubjectGroupName()) && sr.getExam().equalsIgnoreCase(ex)){
									srListFinal.add(listCount, sr);
								}else{
									StudentResult srNew = new StudentResult();
									srNew.setExam(ex);
									srNew.setSubject(sg.getSubjectGroupName());
									srListFinal.add(listCount, srNew);
								}
								}
							}else{
								StudentResult srNew = new StudentResult();
								srNew.setExam(ex);
								srNew.setSubject(sg.getSubjectGroupName());
								srListFinal.add(listCount, srNew);
							}
						}
					}
					s.setStudentResultList(srListFinal);
					
					
				}
				
				
				if(null != studentsSubjectsAndMarksList && studentsSubjectsAndMarksList.size() != 0){
					Report report = new Report();
					report.setStatus(studentResult.getStatus());
					report.setSubjectGroupList(subjectGroupList);
					
					SchoolDetails schoolDetails = loginService.getSchoolDetails();
					schoolDetails.setExamName(studentResult.getExam());
					report.setSchoolDetails(schoolDetails);				
					
					AcademicYear academicYear = commonService.getCurrentAcademicYear();
					report.setStudentList(studentsSubjectsAndMarksList);
					report.setSubjectGroupList(subjectGroupList);
					report.setExamList(allExamList);
					report.setAcademicYear(academicYear);
					String baseDir = studentResult.getReportConfigPath()+"result/"+studentResult.getStandard()+"/"+"/"+"internal_exam_result"+"/";
					String fileName = "internal_exam_result_"+studentResult.getStandard()+"_"+studentResult.getSection();				
					
					Student student = new Student();
					student.setStandard(studentResult.getStandard());
					student.setSection(studentResult.getSection());
					report.setStudent(student);
					
					String xmlFilePath = baseDir+"xml/";
					String xmlFileName = fileName+".xml";
					report.setXmlFilePath(xmlFilePath);
					report.setXmlFileName(xmlFileName);
					xMLBuilder.createXMLFileForInternalExamResultXI(report);
					
					String xsltFilePath = baseDir+"xslt/";
					String xsltFileName = "result.xsl";
					report.setXsltFilePath(xsltFilePath);
					report.setXsltFileName(xsltFileName);
					
					String pdfFilePath = studentResult.getPdfPath()+academicYear.getAcademicYearName()+"/"+studentResult.getStandard()+"/"+"/"+"internal_exam_result"+"/";
					String pdfFileName = fileName+".pdf";
					report.setPdfFilePath(pdfFilePath);
					report.setPdfFileName(pdfFileName);
					
					try{
						pdfBuilder.createPDF(report);
					}catch(IOException e){
						logger.error(e);
					}
					if ((new File(report.getPdfFilePath()+report.getPdfFileName())).exists()) {
						if (Desktop.isDesktopSupported()) {
//							Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + new File(report.getPdfFilePath()+report.getPdfFileName()));
							fileUploadDownload.downloadFile(response, report.getPdfFilePath(), report.getPdfFileName());
						}
					}

				}
				
			} catch(Exception e){
				logger.error(e);
			}
			
		}

		public ChartData loadChart1Data(String role, String module, String reportQueryPath){
			
			ChartQueryManager chartQueryManager = new ChartQueryManager();
			Map<String, List<ChartQueryData>> queriesMap = null;
			try {
				queriesMap = chartQueryManager.loadSqlXMLFile(reportQueryPath);
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			}
			
			List<ChartQueryData> queryDatalist = queriesMap.get(module);
			ChartData chartData = new ChartData();
			
			//for(ChartQueryData queryData : queryDatalist){
			ChartQueryData queryData = queryDatalist.get(0);
				if(queryData.isDefault() == true){
					chartData.setChartLabel(queryData.getChartLabel());
					chartData.setChartType(queryData.getChartType());
					chartData.setCaption(queryData.getCaption());
					chartData.setSubCaption(queryData.getSubCaption());
					chartData.setXaxisname(queryData.getXaxisname());
					chartData.setYaxisname(queryData.getYaxisname());
					chartData.setNumberprefix(queryData.getNumberprefix());
					chartData.setTheme(queryData.getTheme());
					List <ChartValuesModel> chartValuesModelList = reportDAO.loadChartData(queryData.getSql());
					chartData.setChartValuesModelList(chartValuesModelList);
				}
			//}
			return chartData;
			
		}
		
		public ChartData loadChart2Data(String role, String module, String reportQueryPath){
			
			ChartQueryManager chartQueryManager = new ChartQueryManager();
			Map<String, List<ChartQueryData>> queriesMap = null;
			try {
				queriesMap = chartQueryManager.loadSqlXMLFile(reportQueryPath);
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			}
			
			List<ChartQueryData> queryDatalist = queriesMap.get(module);
			ChartData chartData = new ChartData();
			
			//for(ChartQueryData queryData : queryDatalist){
			ChartQueryData queryData = queryDatalist.get(1);
				if(queryData.isDefault() == true){
					chartData.setChartLabel(queryData.getChartLabel());
					chartData.setChartType(queryData.getChartType());
					chartData.setCaption(queryData.getCaption());
					chartData.setSubCaption(queryData.getSubCaption());
					chartData.setXaxisname(queryData.getXaxisname());
					chartData.setYaxisname(queryData.getYaxisname());
					chartData.setNumberprefix(queryData.getNumberprefix());
					chartData.setTheme(queryData.getTheme());
					List <ChartValuesModel> chartValuesModelList = reportDAO.loadChartData(queryData.getSql());
					chartData.setChartValuesModelList(chartValuesModelList);
				}
			//}
			return chartData;
			
		}


		@Override
		public String getStudentCertificate(Standard standard, Section section, AcademicYear academicYear, Exam exam,
				String[] roll, String reportResultConfigFilePath, String reportResultPdfFilPath, String string,
				HttpServletResponse response) {
			boolean currentYear = false;
			String status=null;
			AcademicYear currentAcademicYear;
			try {
				currentAcademicYear = commonService.getCurrentAcademicYear();
				if(null!=currentAcademicYear){
					if(currentAcademicYear.getAcademicYearCode().equalsIgnoreCase(academicYear.getAcademicYearCode())){
						currentYear=true;// For Current Year Report
					}else{
						currentYear=false;// ForPrevious Year Report
					}
					
					if(null!=roll && roll.length!=0){
						List<String> studentsRollList = new ArrayList<String>(Arrays.asList(roll));
						StudentResult studentResult=new StudentResult();
						studentResult.setStringList(studentsRollList);
						studentResult.setExam(exam.getExamName());
						studentResult.setStandard(standard.getStandardCode());
						studentResult.setSection(section.getSectionCode());
						studentResult.setAcademicYear(academicYear.getAcademicYearCode());
						studentResult.setReportConfigPath(reportResultConfigFilePath);	
						studentResult.setPdfPath(reportResultPdfFilPath);
						if(currentYear){
							createCertificate(studentResult,response);
							status="success";					
						}else{
							// for previous years
						}
					}
				}
			} catch (CustomException e) {
				logger.error(e);
				//e.printStackTrace();
			}
			return status;
		}

		@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
		private void createCertificate(StudentResult studentResult, HttpServletResponse response) {
			try {
				List<Student> studentsSubjectsAndMarksList = reportDAO.getcreateReportForExamXI_XII(studentResult);
				if(null != studentsSubjectsAndMarksList && studentsSubjectsAndMarksList.size() != 0){
					Report report = new Report();
					SchoolDetails schoolDetails = loginService.getSchoolDetails();
					schoolDetails.setExamName(studentResult.getExam());
					report.setSchoolDetails(schoolDetails);
					Student studentDetails = null;
					for(Student student : studentsSubjectsAndMarksList){
						studentDetails = commonService.getStudentDetails(student.getRollNumber().toString());
						if(studentDetails != null){
							student.setStudentName(studentDetails.getStudentName());
							student.setStandard(studentDetails.getStandard());
							student.setSection(studentDetails.getSection());
							student.setHouse(studentDetails.getHouse());
							student.setResource(studentDetails.getResource());
						}
					}
					AcademicYear academicYear = commonService.getCurrentAcademicYear();
					report.setAcademicYear(academicYear);
					report.setStudent(studentDetails);
					report.setStudentList(studentsSubjectsAndMarksList);
					List<String> rollList = studentResult.getStringList();
					String baseDir = studentResult.getReportConfigPath()+"result/"+studentDetails.getStandard()+"/"+"Certificate"+"/";
					baseDir = baseDir.replace(" ", "_");
					String fileName="";
					if(null != rollList && rollList.size()!=0 ){
						if( rollList.size() == 1){
							fileName = studentDetails.getRollNumber()+"_"+studentDetails.getStandard()+"_"+studentResult.getExam();
						}if( rollList.size() > 1){
							fileName = studentDetails.getStandard()+"_"+studentResult.getExam();
						}
						fileName = fileName.replace(" ", "_");

						String xmlFilePath = baseDir+"xml/";
						String xmlFileName = "result"+".xml";
						report.setXmlFilePath(xmlFilePath);
						report.setXmlFileName(xmlFileName);
						xMLBuilder.createXMLFileForCertificate(report);
						
						String xsltFilePath = baseDir+"xslt/";
						String xsltFileName = "result.xsl";
						report.setXsltFilePath(xsltFilePath);
						report.setXsltFileName(xsltFileName);
					
						String pdfFilePath = studentResult.getPdfPath()+academicYear.getAcademicYearName()+"/"+studentDetails.getStandard()+"/"+studentResult.getExam()+"/";
						String pdfFileName = fileName+".pdf";
						report.setPdfFilePath(pdfFilePath);
						report.setPdfFileName(pdfFileName);
						
						try{
							pdfBuilder.createPDF(report);
						}catch(IOException e){
							logger.error(e);
						}
						if ((new File(report.getPdfFilePath()+report.getPdfFileName())).exists()) {
							if (Desktop.isDesktopSupported()) {
								//Desktop.getDesktop().open(new File(report.getPdfFilePath()+report.getPdfFileName()));
								fileUploadDownload.downloadFile(response, report.getPdfFilePath(), report.getPdfFileName());
							}
						}
					}
				}
				reportDAO.deleteTempTableReportData();
			}catch (CustomException e) {
				logger.error(e);
				//e.printStackTrace();
			}catch (Exception e) {
				logger.error(e);
				//e.printStackTrace();
			}
			
		}
		
	
	/**New CBSE System start**/
		
		@Override
		public String getStudentsAgainstStandardAndSectionForNewReport(String standard, String section) throws CustomException {
			String studentString = "";
	 		try{
	 			Student student=new Student();
	 			student.setStandard(standard);
	 			student.setSection(section);
	 			List<Student> studentList = reportDAO.getStudentsAgainstStandardAndSectionForNewReport(student);
	 			StringBuilder sb = new StringBuilder();
	 			if(studentList!=null && studentList.size()!=0){				
					for(Student studentObject : studentList){
						if (sb.length() != 0) {
							sb.append(";");
					    }
						sb.append(studentObject.getRollNumber());
						sb.append("*");
						sb.append(studentObject.getStudentName());
					}				
				}else{
					logger.info("Section not found by ajax @ getStudentsAgainstStandardAndSection()In CommonServiceImpl");
				}
	 			studentString = (new Gson().toJson(sb.toString()));
	 		}catch(Exception e){
	 			
	 			logger.error("Exception in getStudentsAgainstStandardAndSection() in CommonServiceImpl ", e);
	 		}
			return studentString;
		}
		
		@Override
		public String getStudentReportNew(Standard standard, Section section, AcademicYear academicYear, Exam exam,
										String[] roll, String reportResultConfigFilePath, String reportResultPdfFilPath,
										String reportExamResultChatImagePath, HttpServletResponse response) {			
			boolean currentYear = false;
			String status = null;
			AcademicYear currentAcademicYear;
			try {
				currentAcademicYear = commonService.getCurrentAcademicYear();
				if(null != currentAcademicYear){
					if(currentAcademicYear.getAcademicYearCode().equalsIgnoreCase(academicYear.getAcademicYearCode())){
						currentYear = true;// For Current Year Report
					}else{
						currentYear = false;// ForPrevious Year Report
					}
					
					if(null != roll && roll.length != 0){	
						//for(int count=0;count<roll.length;count++){
						List<String> studentsRollList = new ArrayList<String>(Arrays.asList(roll));
						logger.info("ROLL SET:::  "+studentsRollList);
						StudentResult studentResult=new StudentResult();
						studentResult.setStringList(studentsRollList);
						studentResult.setExam(exam.getExamName());
						studentResult.setStandard(standard.getStandardCode());
						studentResult.setSection(section.getSectionCode());
						studentResult.setAcademicYear(academicYear.getAcademicYearCode());
						studentResult.setReportConfigPath(reportResultConfigFilePath);	
						studentResult.setPdfPath(reportResultPdfFilPath);
						studentResult.setImagePath(reportExamResultChatImagePath);
						logger.info(studentResult.getStringList()+"$$$$$$"+studentResult.getExam()+"$$$$$$"+studentResult.getStandard()+"$$$$$$"+studentResult.getSection()+"$$$$$$"+studentResult.getAcademicYear());
						if(currentYear){
							createReportForExamNew(studentResult,response);
							status = "success";
						}else{
							// for previous years
						}
					
					}
				}
			} catch (CustomException e) {
				logger.error(e);
			}
			return status;
		}

		@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
		public synchronized  void createReportForExamNew(StudentResult studentResult,HttpServletResponse response) {
			try {
				DecimalFormat dformat = new DecimalFormat("##.##");
				
				if(studentResult.getExam().equalsIgnoreCase("Centralise")){
					List<Student> studentsSubjectsAndMarksList = reportDAO.getStudentsSubjectsAndMarksReportForCurrentAcademicYearNew(studentResult);	
					int totalStudentCount = reportDAO.getTotalStudentCount(studentResult);
					
					
					if(null != studentsSubjectsAndMarksList && studentsSubjectsAndMarksList.size() != 0){
						Report report = new Report();
						SchoolDetails schoolDetails = loginService.getSchoolDetails();
						schoolDetails.setExamName(studentResult.getExam());
						report.setSchoolDetails(schoolDetails);
						report.setReportId(totalStudentCount);
						Student studentDetails = null;
						for(Student student : studentsSubjectsAndMarksList){
							studentDetails = commonService.getStudentDetails(student.getRollNumber().toString());
							if(null != studentDetails){
								student.setStudentName(studentDetails.getStudentName());
								student.setStandard(studentDetails.getStandard());
								student.setSection(studentDetails.getSection());
								student.setHouse(studentDetails.getHouse());
								student.setResource(studentDetails.getResource());
							}
						}
			
						AcademicYear academicYear = commonService.getCurrentAcademicYear();
						report.setAcademicYear(academicYear);
						report.setStudent(studentDetails);
						report.setStatus(studentResult.getStatus());
						report.setStudentList(studentsSubjectsAndMarksList);
						
						
						
						
						for(Student s:studentsSubjectsAndMarksList){
							System.out.println(s.getRollNumber());
								for(StudentResult sr:s.getStudentResultList()){
									System.out.print(sr.getSubject()+"   "+sr.getExam()+"   "+sr.getTotal()+"   "+sr.getPractical()+"   "+sr.getTheoryObtained()+"   "+sr.getPracticalObtained()+"   "+sr.getTotalObtained()+"\n");
								}
							System.out.println("\n\n");
						}
						System.out.println("**************************************");
						
						
						
						List<String> rollList = studentResult.getStringList();
						logger.info("1 : "+studentResult);
						String baseDir = studentResult.getReportConfigPath()+"result/"+studentDetails.getStandard()+"/"+studentResult.getExam()+"/";	;
						String pdfFilePath = studentResult.getPdfPath()+academicYear.getAcademicYearName()+"/"+studentDetails.getStandard()+"/"+studentResult.getExam()+"/";
						String fileName="";
						if(null != rollList && rollList.size()!=0 ){
							if( rollList.size() == 1){
								fileName = "Term_1_"+studentDetails.getRollNumber()+"_"+studentDetails.getStandard()+"_"+studentResult.getExam();
							}if( rollList.size() > 1){
								fileName = "Term_1_Students_"+studentDetails.getStandard()+"_"+studentResult.getExam();
							}

							String xmlFilePath = baseDir+"xml/";
							String xmlFileName = "result"+".xml";
							report.setXmlFilePath(xmlFilePath);
							report.setXmlFileName(xmlFileName);
							xMLBuilder.createXMLFileForCentraliseExamForXI_XIINew(report);
							
							
							String xsltFilePath = baseDir+"xslt/";
							String xsltFileName = "result.xsl";
							report.setXsltFilePath(xsltFilePath);
							report.setXsltFileName(xsltFileName);
							
							String pdfFileName = fileName+".pdf";
							report.setPdfFilePath(pdfFilePath);
							report.setPdfFileName(pdfFileName);
							
							try{
								pdfBuilder.createPDF(report);
							}catch(IOException e){
								logger.error(e);
							}
							FileUtils.cleanDirectory(new File(studentResult.getImagePath()));
							if ((new File(report.getPdfFilePath()+report.getPdfFileName())).exists()) {
								if (Desktop.isDesktopSupported()) {
									//Desktop.getDesktop().open(new File(report.getPdfFilePath()+report.getPdfFileName()));
									fileUploadDownload.downloadFile(response, report.getPdfFilePath(), report.getPdfFileName());
								}
							}
						}					
					}
				}else{
					List<Student> studentsSubjectsAndMarksList = reportDAO.getStudentsSubjectsAndMarksReportForCurrentAcademicYearNew(studentResult);
					logger.info(studentsSubjectsAndMarksList);
					if(null != studentsSubjectsAndMarksList && studentsSubjectsAndMarksList.size() != 0){
						Report report = new Report();
						SchoolDetails schoolDetails = loginService.getSchoolDetails();
						schoolDetails.setExamName(studentResult.getExam());
						report.setSchoolDetails(schoolDetails);
						Student studentDetails = null;
						for(Student student : studentsSubjectsAndMarksList){
							if(studentResult.getExam().equals("Term1")||studentResult.getExam().equals("Term2")||studentResult.getExam().equals("AnnualExam1")){
								student.setStudentDesc(studentResult.getAcademicYear());
								student.setStandard(studentResult.getStandard());
								student.setStatus(studentResult.getExam());
								student.setCoScholasticResultList(reportDAO.getCoScholasticResultListNew(student));
							}
							studentDetails = commonService.getStudentDetails(student.getRollNumber().toString());
							if(null != studentDetails){
								student.setStudentName(studentDetails.getStudentName());
								student.setStandard(studentDetails.getStandard());
								student.setSection(studentDetails.getSection());
								student.setHouse(studentDetails.getHouse());
								student.setResource(studentDetails.getResource());
								student.setBloodGroup(studentDetails.getBloodGroup());
							}
						}
						AcademicYear academicYear = commonService.getCurrentAcademicYear();
						report.setAcademicYear(academicYear);
						report.setStudent(studentDetails);
						report.setStatus(studentResult.getStatus());
						report.setStudentList(studentsSubjectsAndMarksList);
						List<String> rollList= studentResult.getStringList();
						logger.info("1 : "+studentResult);
						String baseDir;
						String pdfFilePath;
						/*if(studentDetails.getStandard().equalsIgnoreCase("XI"))
						{
							String exam = studentResult.getExam().replace(" ", "_");
							baseDir = studentResult.getReportConfigPath()+"result/"+studentDetails.getStandard()+"/"+exam+"/";
							pdfFilePath = studentResult.getPdfPath()+academicYear.getAcademicYearName()+"/"+studentDetails.getStandard()+"/"+exam+"/";
						}
						else*/
						//{
							baseDir = studentResult.getReportConfigPath()+"result/"+studentResult.getStandard()+"/"+studentResult.getExam()+"/";	
							pdfFilePath = studentResult.getPdfPath()+academicYear.getAcademicYearName()+"/"+studentResult.getStandard()+"/"+studentResult.getExam()+"/";
						//}
						//String baseDir = studentResult.getReportConfigPath()+"result/"+studentDetails.getStandard()+"/"+studentResult.getExam()+"/";
						String fileName="";
						if(null != rollList && rollList.size()!=0 ){
							if( rollList.size() == 1){
								fileName = "Term_1_"+studentDetails.getRollNumber()+"_"+studentResult.getStandard()+"_"+studentResult.getExam();
							}if( rollList.size() > 1){
								fileName = "Term_1_Students_"+studentDetails.getStandard()+"_"+studentResult.getExam();
							}

							String xmlFilePath = baseDir+"xml/";
							String xmlFileName = "result"+".xml";
							report.setXmlFilePath(xmlFilePath);
							report.setXmlFileName(xmlFileName);
							
							if(studentDetails.getStandard().equalsIgnoreCase("XI")||studentDetails.getStandard().equalsIgnoreCase("XII")){
								
							
									xMLBuilder.createXMLFileForExamResultXI_XIINew(report);
								
								
							} else{
								xMLBuilder.createXMLFileForExamResultNew(report);
							}
							String xsltFilePath = baseDir+"xslt/";
							String xsltFileName = "result.xsl";
							report.setXsltFilePath(xsltFilePath);
							report.setXsltFileName(xsltFileName);
							
							String pdfFileName = fileName+".pdf";
							report.setPdfFilePath(pdfFilePath);
							report.setPdfFileName(pdfFileName);
							/*if(!(studentResult.getStandard().equalsIgnoreCase("XI") || studentResult.getStandard().equalsIgnoreCase("XII"))){
								for(String roll : rollList){
									studentResult.setRollNumber(roll);
									List<StudentResult> studentsSubjectsAndMarksListForChart = reportDAO.getStudentsSubjectsAndMarksReportChartForCurrentAcademicYear(studentResult);
									logger.info("studentsSubjectsAndMarksListForChart ::  "+studentsSubjectsAndMarksListForChart);
									if(null != studentsSubjectsAndMarksListForChart && studentsSubjectsAndMarksListForChart.size() != 0)
										studentsSubjectsAndMarksListForChart.get(0).setImagePath(studentResult.getImagePath());
										studentsSubjectsAndMarksListForChart.get(0).setRollNumber(roll);
										chartGenerator.generateBarChart(studentsSubjectsAndMarksListForChart);
								}
							}*/
							try{
								pdfBuilder.createPDF(report);
							}catch(IOException e){
								logger.error(e);
							}
							FileUtils.cleanDirectory(new File(studentResult.getImagePath()));
							if ((new File(report.getPdfFilePath()+report.getPdfFileName())).exists()) {
								if (Desktop.isDesktopSupported()) {
									//Desktop.getDesktop().open(new File(report.getPdfFilePath()+report.getPdfFileName()));
									fileUploadDownload.downloadFile(response, report.getPdfFilePath(), report.getPdfFileName());
								}
							}
						}
					}
				}
				
				//reportDAO.deleteTempTableReportData();
			}catch (IOException e) {
				e.printStackTrace();
				logger.error(e);
			}catch (CustomException e) {
				logger.error(e);
				e.printStackTrace();
			}catch (Exception e) {
				logger.error(e);
				e.printStackTrace();
			}
		}


		@Override
		public String getConsolidatedResultReportNew(Standard standard, Section section, AcademicYear academicYear,Exam exam, String reportResultConfigFilePath, String reportResultPdfFilPath, String excel,
				String studentMarkSheetExcel, HttpServletResponse response) {
			boolean currentYear = false;
			String status = null;
			AcademicYear currentAcademicYear;
			try {
				currentAcademicYear = commonService.getCurrentAcademicYear();
				if(null != currentAcademicYear){
					if(currentAcademicYear.getAcademicYearCode().equalsIgnoreCase(academicYear.getAcademicYearCode())){
						currentYear = true;// For Current Year Report
					}else{
						currentYear = false;// ForPrevious Year Report
					}
					StudentResult studentResult = new StudentResult();
					studentResult.setStatus(standard.getStatus());
					studentResult.setExam(exam.getExamName());
					studentResult.setStandard(standard.getStandardCode());
					studentResult.setSection(section.getSectionCode());
					studentResult.setAcademicYear(academicYear.getAcademicYearCode());
					studentResult.setReportConfigPath(reportResultConfigFilePath);	
					studentResult.setPdfPath(reportResultPdfFilPath);
					System.out.println("status::"+studentResult.getStatus()+"\n exam:"+studentResult.getExam()+"\ncurrent:"+currentYear+"\nyear:"+studentResult.getAcademicYear());
					if(currentYear){
						if(!studentResult.getExam().equalsIgnoreCase("IE"))	{
							if(studentResult.getExam().equalsIgnoreCase("Centralise")){							
								createConsolidatedReportForCentraliseExamForCurrentYearNew(studentResult, reportResultPdfFilPath, excel, studentMarkSheetExcel, response);
							}else{
								createConsolidatedReportForExamForCurrentYearNew(studentResult, reportResultPdfFilPath, excel, studentMarkSheetExcel, response);
							}
						}else{
							createConsolidateReportForInternalExaminationNew(studentResult, reportResultPdfFilPath, excel, studentMarkSheetExcel, response);
						}
						status = "success";					
					}else{
						// for previous years
						/*if(!studentResult.getExam().equalsIgnoreCase("IE"))	{
							if(studentResult.getExam().equalsIgnoreCase("Centralise")){							
								createConsolidatedReportForCentraliseExamForCurrentYear(studentResult, reportResultPdfFilPath, excel, studentMarkSheetExcel, response);
							}else{
								createConsolidatedReportForExamForPreviousYear(studentResult, reportResultPdfFilPath, excel, studentMarkSheetExcel, response);
							}
						}else{
							createConsolidateReportForInternalExamination(studentResult, reportResultPdfFilPath, excel, studentMarkSheetExcel, response);
						}
						status = "success";*/

					}
				}
			}catch (CustomException e) {
				e.printStackTrace();
				logger.error(e);
			}
			return status;
		}


	

		@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
		public synchronized  void createConsolidateReportForInternalExaminationNew(StudentResult studentResult, String reportResultPdfFilPath, String excel, 
																				String studentMarkSheetExcel, HttpServletResponse response) {
			try{
				List<SubjectGroup> subjectGroupList = reportDAO.getSubjectGroupForInternalExaminationNew(studentResult);
				List<Student> studentsSubjectsAndMarksList = reportDAO.getStudentListForInternalExaminationNew(studentResult);				
				
				List<String> examList = new ArrayList<String>();
				List<String> allExamList = new ArrayList<String>();
				allExamList.add("M1");
				allExamList.add("M2");
				allExamList.add("PC");
				allExamList.add("Term_1");
				for(StudentResult sr : studentsSubjectsAndMarksList.get(0).getStudentResultList()){
					if(!examList.contains(sr.getExam()))
					examList.add(sr.getExam());
				}
				
				for(Student s : studentsSubjectsAndMarksList){
					List<StudentResult> srList = s.getStudentResultList();
					List<StudentResult> srListFinal = new ArrayList<StudentResult>();
					int resultCount = -1;
					int listCount=-1;
					for(SubjectGroup sg : subjectGroupList){
						for(String ex : allExamList){
							resultCount++;
							listCount++;
							if(srList.size() > resultCount){
								StudentResult sr = srList.get(resultCount);
								if(!sr.getExam().equalsIgnoreCase(ex)){
									resultCount--;
									StudentResult srNew = new StudentResult();
									srNew.setExam(ex);
									srNew.setSubject(sg.getSubjectGroupName());
									srListFinal.add(listCount, srNew);
									
								}else{
								if(sr.getSubject().equalsIgnoreCase(sg.getSubjectGroupName()) && sr.getExam().equalsIgnoreCase(ex)){
									srListFinal.add(listCount, sr);
								}else{
									StudentResult srNew = new StudentResult();
									srNew.setExam(ex);
									srNew.setSubject(sg.getSubjectGroupName());
									srListFinal.add(listCount, srNew);
								}
								}
							}else{
								StudentResult srNew = new StudentResult();
								srNew.setExam(ex);
								srNew.setSubject(sg.getSubjectGroupName());
								srListFinal.add(listCount, srNew);
							}
						}
					}
					s.setStudentResultList(srListFinal);
					
					
				}
				
				
				if(null != studentsSubjectsAndMarksList && studentsSubjectsAndMarksList.size() != 0){
					Report report = new Report();
					report.setStatus(studentResult.getStatus());
					report.setSubjectGroupList(subjectGroupList);
					
					SchoolDetails schoolDetails = loginService.getSchoolDetails();
					schoolDetails.setExamName(studentResult.getExam());
					report.setSchoolDetails(schoolDetails);				
					
					AcademicYear academicYear = commonService.getCurrentAcademicYear();
					report.setStudentList(studentsSubjectsAndMarksList);
					report.setSubjectGroupList(subjectGroupList);
					report.setExamList(allExamList);
					report.setAcademicYear(academicYear);
					String baseDir = studentResult.getReportConfigPath()+"result/"+studentResult.getStandard()+"/"+"/"+"internal_exam_result"+"/";
					String fileName = "internal_exam_result_"+studentResult.getStandard()+"_"+studentResult.getSection();				
					
					Student student = new Student();
					student.setStandard(studentResult.getStandard());
					student.setSection(studentResult.getSection());
					report.setStudent(student);
					
					String xmlFilePath = baseDir+"xml/";
					String xmlFileName = fileName+".xml";
					report.setXmlFilePath(xmlFilePath);
					report.setXmlFileName(xmlFileName);
					xMLBuilder.createXMLFileForInternalExamResultXI(report);
					
					String xsltFilePath = baseDir+"xslt/";
					String xsltFileName = "result.xsl";
					report.setXsltFilePath(xsltFilePath);
					report.setXsltFileName(xsltFileName);
					
					String pdfFilePath = studentResult.getPdfPath()+academicYear.getAcademicYearName()+"/"+studentResult.getStandard()+"/"+"/"+"internal_exam_result"+"/";
					String pdfFileName = fileName+".pdf";
					report.setPdfFilePath(pdfFilePath);
					report.setPdfFileName(pdfFileName);
					
					try{
						pdfBuilder.createPDF(report);
					}catch(IOException e){
						logger.error(e);
					}
					if ((new File(report.getPdfFilePath()+report.getPdfFileName())).exists()) {
						if (Desktop.isDesktopSupported()) {
//							Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + new File(report.getPdfFilePath()+report.getPdfFileName()));
							fileUploadDownload.downloadFile(response, report.getPdfFilePath(), report.getPdfFileName());
						}
					}

				}
				
			} catch(Exception e){
				logger.error(e);
			}
			
		}


		@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
		public synchronized  void createConsolidatedReportForExamForCurrentYearNew(StudentResult studentResult,String reportResultPdfFilPath, String excel, String studentMarkSheetExcel, HttpServletResponse response) {
			try {		
				List<SubjectGroup> subjectGroupList = reportDAO.getSubjectGroupFromStudentMarksV1(studentResult);
				if(null != subjectGroupList && subjectGroupList.size() != 0){
					String status = null;
					List<Student> studentsSubjectsAndMarksList = reportDAO.getConsolidatedReportForExamForCurrentAcademicYearNew(studentResult);		
					
					//Setting subjects which are not available in student result
					if(!(studentResult.getStandard().equalsIgnoreCase("XI") || studentResult.getStandard().equalsIgnoreCase("XII"))){
						if(null != studentsSubjectsAndMarksList && studentsSubjectsAndMarksList.size()!=0){
							//if(subjectGroupList.size() >= studentsSubjectsAndMarksList.size()){
								for(SubjectGroup sg : subjectGroupList){
									for(Student stu : studentsSubjectsAndMarksList){
										if(subjectGroupList.size() >= stu.getStudentResultList().size()){
											List<StudentResult> srl = stu.getStudentResultList();
											int i = subjectGroupList.indexOf(sg);
											if(!srl.get(i).getSubject().equalsIgnoreCase(sg.getSubjectGroupName())){
												//System.out.println("within");
												//System.out.println("srl.get(i).getSubject()=="+srl.get(i).getSubject());
												//System.out.println("sg.getSubjectGroupName()=="+sg.getSubjectGroupName());
												StudentResult sr = new StudentResult();
												sr.setSubject(sg.getSubjectGroupName());
												srl.add(i, sr);
											}
											stu.setStudentResultList(srl);
									}
								}
							}
						}
					}
					/*for(SubjectGroup sg:subjectGroupList){
						System.out.println(sg.getSubjectGroupName());
					}
					System.out.println("*******************");
					for(Student s:studentsSubjectsAndMarksList){
						System.out.println(s.getRollNumber()+"\t\t"+s.getStudentResultList().size());
						for(StudentResult sr:s.getStudentResultList()){
							System.out.println("\t\t"+sr.getSubject()+"\t"+sr.getGrade()+"/"+sr.getGradepoint());
						}
					}*/
					if(null != excel){
						/*List<Student> studentResultRawData = reportDAO.getStudentResultRawDataForNewCBSEPattern(studentResult);
						status = createStudentMarkSheetExcel(subjectGroupList, studentResult, studentsSubjectsAndMarksList, reportResultPdfFilPath, studentMarkSheetExcel, studentResultRawData);*/	
						//Added By Saif 17/08/2017
						List<Student> studentResultRawDataNew = reportDAO.getStudentResultRawDataForNewCBSEPatternNew(studentResult);
						status = createStudentMarkSheetExcelNew(subjectGroupList, studentResult, studentsSubjectsAndMarksList, reportResultPdfFilPath, studentMarkSheetExcel, studentResultRawDataNew);
					}else{
						if(null != studentsSubjectsAndMarksList && studentsSubjectsAndMarksList.size() != 0){
							Report report = new Report();
							report.setStatus(studentResult.getStatus());
							report.setSubjectGroupList(subjectGroupList);
							
							SchoolDetails schoolDetails = loginService.getSchoolDetails();
							schoolDetails.setExamName(studentResult.getExam());
							report.setSchoolDetails(schoolDetails);				
							
							AcademicYear academicYear = commonService.getCurrentAcademicYear();
							report.setStudentList(studentsSubjectsAndMarksList);
							report.setAcademicYear(academicYear);
							//String baseDir;
							//String fileName;
							/*if(studentResult.getStandard().equalsIgnoreCase("XI")){
								String exam=studentResult.getExam().replace(" ", "_");
								baseDir = studentResult.getReportConfigPath()+"result/"+studentResult.getStandard()+"/"+exam+"/"+"consolidate_result"+"/";
								fileName = "consolidate_result_"+studentResult.getStandard()+"_"+studentResult.getSection()+"_"+exam;				
							}else{*/
							String baseDir = studentResult.getReportConfigPath()+"result/"+studentResult.getStandard()+"/"+studentResult.getExam()+"/"+"consolidate_result"+"/";
							String fileName = "consolidate_result_"+studentResult.getStandard()+"_"+studentResult.getSection()+"_"+studentResult.getExam();				
							//}	
												
							Student student = new Student();
							student.setStandard(studentResult.getStandard());
							student.setSection(studentResult.getSection());
							report.setStudent(student);				
							
							String xmlFilePath = baseDir+"xml/";
							String xmlFileName = fileName+".xml";
							report.setXmlFilePath(xmlFilePath);
							report.setXmlFileName(xmlFileName);
							String xsltFilePath;
							if (studentResult.getStandard().equalsIgnoreCase("XI")||studentResult.getStandard().equalsIgnoreCase("XII")){
								xMLBuilder.createXMLFileForConsolidateExamResultXI_XIINew(report);
								xsltFilePath = baseDir+"xslt/";
							}else{
								xMLBuilder.createXMLFileForConsolidateExamResultNew(report);
								xsltFilePath = baseDir+"xslt/";
							}
							
							String xsltFileName = "result.xsl";
							report.setXsltFilePath(xsltFilePath);
							report.setXsltFileName(xsltFileName);
							
							String pdfFilePath = studentResult.getPdfPath()+academicYear.getAcademicYearName()+"/"+studentResult.getStandard()+"/"+studentResult.getExam()+"/"+"consolidate_result"+"/";
							String pdfFileName = fileName+".pdf";
							report.setPdfFilePath(pdfFilePath);
							report.setPdfFileName(pdfFileName);
							
							try{
								pdfBuilder.createPDF(report);
							}catch(IOException e){
								logger.error(e);
								e.printStackTrace();
							}
							if ((new File(report.getPdfFilePath()+report.getPdfFileName())).exists()) {
								if (Desktop.isDesktopSupported()) {
			//						Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + new File(report.getPdfFilePath()+report.getPdfFileName()));
									fileUploadDownload.downloadFile(response, report.getPdfFilePath(), report.getPdfFileName());
								}
							}
			
						}
					}				
				}				
			}catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			}
		}
		
		private void createConsolidatedReportForCentraliseExamForCurrentYearNew(StudentResult studentResult,String reportResultPdfFilPath, String excel, String studentMarkSheetExcel,
				HttpServletResponse response) {

			try{	
				
				List<SubjectGroup> subjectGroupList = reportDAO.getSubjectGroupFromStudentMarksV1(studentResult);
				List<Student> studentsSubjectsAndMarksList = reportDAO.getStudentListForConsolidateCentralisedNew(studentResult);	

				if(null != studentsSubjectsAndMarksList && studentsSubjectsAndMarksList.size() != 0){
					Report report = new Report();
					SchoolDetails schoolDetails = loginService.getSchoolDetails();
					schoolDetails.setExamName(studentResult.getExam());
					report.setSchoolDetails(schoolDetails);
					//report.setReportId(totalStudentCount);
					//Student studentDetails = null;
					/*for(Student student : studentsSubjectsAndMarksList){
						studentDetails = commonService.getStudentDetails(student.getRollNumber().toString());
						if(null != studentDetails){
							student.setStudentName(studentDetails.getStudentName());
							student.setStandard(studentDetails.getStandard());
							student.setSection(studentDetails.getSection());
							student.setHouse(studentDetails.getHouse());
							student.setResource(studentDetails.getResource());
						}
					}*/
					AcademicYear academicYear = commonService.getCurrentAcademicYear();
					report.setAcademicYear(academicYear);
					//report.setStudent(studentDetails);	
					report.setSubjectGroupList(subjectGroupList);
				//	report.setExamList(subjectList);
					
					
					
					//}
					
						
					
					String baseDir = studentResult.getReportConfigPath()+"result/"+studentResult.getStandard()+"/"+studentResult.getExam()+"/"+"consolidate_result"+"/";
					String fileName = "consolidate_result_"+studentResult.getStandard()+"_"+studentResult.getSection()+"_"+studentResult.getExam();				


						String xmlFilePath = baseDir+"xml/";
						String xmlFileName = "result"+".xml";
						report.setXmlFilePath(xmlFilePath);
						report.setXmlFileName(xmlFileName);
						xMLBuilder.createXMLFileForConsolidateCentraliseExamForXI_XIINew(report);
						
						
						String xsltFilePath = baseDir+"xslt/";
						String xsltFileName = "result.xsl";
						report.setXsltFilePath(xsltFilePath);
						report.setXsltFileName(xsltFileName);
						
						String pdfFileName = fileName+".pdf";
						String pdfFilePath = studentResult.getPdfPath()+academicYear.getAcademicYearName()+"/"+studentResult.getStandard()+"/"+studentResult.getExam()+"/"+"consolidate_result"+"/";
						report.setPdfFilePath(pdfFilePath);
						report.setPdfFileName(pdfFileName);
						
						try{
							pdfBuilder.createPDF(report);
						}catch(IOException e){
							logger.error(e);
						}
						
						if ((new File(report.getPdfFilePath()+report.getPdfFileName())).exists()) {
							if (Desktop.isDesktopSupported()) {
								//Desktop.getDesktop().open(new File(report.getPdfFilePath()+report.getPdfFileName()));
								fileUploadDownload.downloadFile(response, report.getPdfFilePath(), report.getPdfFileName());
							}
						}
				}
				}catch (CustomException e) {
					
					logger.error(e);
				}catch (Exception e) {
					
					logger.error(e);
				}
			
			
		}
		
		//Added By Naimisha 17082017
		private String createStudentMarkSheetExcelNew(List<SubjectGroup> subjectGroupList, StudentResult studentResult,
				List<Student> studentsSubjectsAndMarksList, String reportResultPdfFilPath, String studentMarkSheetExcel,
				List<Student> studentResultRawDataNew) {
			
			reportResultPdfFilPath = reportResultPdfFilPath+studentMarkSheetExcel;
			String status = "";	
			
			if(studentResult.getExam().equalsIgnoreCase("T1_PT1")||studentResult.getExam().equalsIgnoreCase("T2_PT1")){
				
				int sheetCount = 0;
				HSSFWorkbook workbook = new HSSFWorkbook();         
				//Create a blank sheet				
				try  {	
					HSSFSheet sheet = workbook.createSheet("consolidate_result_"+studentResult.getStandard()+"_"+studentResult.getSection()+"_"+studentResult.getExam());
			        //sheet.protectSheet("secretPassword");	
			        
			      //MERGING CODE
			        sheet.addMergedRegion(new CellRangeAddress(0,0,0,12));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(1,1,0,12));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(2,2,0,12));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(3,3,0,12));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(4,4,1,1));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(5,5,2,2));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(6,6,3,3));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(7,7,4,4));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(8,8,5,5));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(9,9,6,6));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(10,10,7,7));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(11,11,8,8));	//(rowFrom, rowTo, colFrom, colTo)
			        
			        sheet.setDefaultColumnWidth(8);
			        
			        HSSFFont hSSFFont = workbook.createFont();
			        hSSFFont.setFontName(HSSFFont.FONT_ARIAL);
			        hSSFFont.setFontHeightInPoints((short) 12);
			        hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			        hSSFFont.setColor(HSSFColor.BLACK.index);
			        			        
			        /* cell style for locking */
			        CellStyle lockedCellStyle = workbook.createCellStyle();
			        lockedCellStyle.setFont(hSSFFont);
			       // lockedCellStyle.setLocked(true);
			        lockedCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
			        
			        CellStyle nameCellStyle = workbook.createCellStyle();
			        nameCellStyle.setFont(hSSFFont);
			       // nameCellStyle.setLocked(true);
			        nameCellStyle.setAlignment(CellStyle.ALIGN_LEFT);
			        
			        /* cell style for editable cells */
			        CellStyle unlockedCellStyle = workbook.createCellStyle();
			        //unlockedCellStyle.setLocked(false);	
			        unlockedCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
			        
			        
			        //This data needs to be written (Object[])
			        Map<Integer, List<String>> data = new TreeMap<Integer, List<String>>();	
			        
			        List<String> schoolDetailsList = new ArrayList<String>();
			        schoolDetailsList.add("SAINIK SCHOOL PURULIA");
			        data.put(1, schoolDetailsList);
			        
			        
			        List<String> resultDetailsList = new ArrayList<String>();
			        resultDetailsList.add("CONSOLIDATED RESULT SHEET : "+studentResult.getAcademicYear()+" (Periodic Test)");
			        data.put(2, resultDetailsList);
			        
			        List<String> standardDetailsList = new ArrayList<String>();
			        standardDetailsList.add("STANDARD : "+studentResult.getStandard()+" - "+studentResult.getSection());
			        data.put(3, standardDetailsList);		        
			        data.put(4, new ArrayList<String>());
			        
					List<String> headerList = new ArrayList<String>();				 				 
					headerList.add("Roll No");				 
					headerList.add("Name");				 
					 
					for(SubjectGroup subjectGroup : subjectGroupList){					
						headerList.add(subjectGroup.getSubjectGroupName());
						/*headerList.add("");
						headerList.add("");
						headerList.add("");
						headerList.add("");*/
					}	        	
			        data.put(5, headerList);
			        
			        List<String> secondHeaderList = new ArrayList<String>();
			        secondHeaderList.add("");
			        secondHeaderList.add("");
			       /*for(SubjectGroup subjectGroup : subjectGroupList){				       				       
				        secondHeaderList.add("Total Obtained");
				        secondHeaderList.add("Total");
				        secondHeaderList.add("SA1");
				        secondHeaderList.add("Tot");
				        secondHeaderList.add("Grd/GP");
			        }*/
			        data.put(6, secondHeaderList);		        
			        data.put(7, new ArrayList<String>());
			        Integer count = 8;
			        
			        for(Student student : studentsSubjectsAndMarksList){
			        	int x=0;
			        	for(StudentResult studentResult2 : student.getStudentResultList()){
			        		studentResult2.setSubject(subjectGroupList.get(x).getSubjectGroupName());
			        		x++;
			        		if(x==subjectGroupList.size())
			        			x=0;
			        	}
			        }
			     
			        for(Student student : studentsSubjectsAndMarksList){
			        	List<String> rowList = new ArrayList<String>();		        	
			        	rowList.add(student.getRollNumber().toString());
			        	rowList.add(student.getStudentName());
			        	int total=0;
			        	for(StudentResult studentResult2 : student.getStudentResultList()){
			        		if(studentResult2.getTotalObtainedChar() == null){
								rowList.add("--");
			        		}else{
								int checker=0;
								for(Student s : studentResultRawDataNew){
									if(student.getRollNumber().equals(s.getRollNumber())){
										for(StudentResult sr:s.getStudentResultList()){
											if((sr.getExam().equalsIgnoreCase("T1_PT1")) && (sr.getSubject().equalsIgnoreCase(studentResult2.getSubject()))){
												rowList.add(sr.getTotalObtainedChar());
												checker=1;
											}
										}
									}
								}
								if(checker==0){
									rowList.add("NA");
								}
							}
						}
		        	    data.put(count, rowList);		
			            int loopControl = count;
			        	loopControl++;
			        	count = loopControl;		 
			        }
			        //Iterate over data and write to sheet
			        Set<Integer> keyset = data.keySet();
			        int rownum = 0;
			        for (Integer key : keyset){
			            Row row = sheet.createRow(rownum++);
			            List<String> rowsList = data.get(key);
			            int cellnum = 0;
			            for (String obj : rowsList) {
			               Cell cell = row.createCell(cellnum++);
			               if(obj instanceof String){
			                    cell.setCellValue((String)obj);
			                    if(cellnum == 2 && key >= 8){
			                    	cell.setCellStyle(nameCellStyle);
			                    }else if(cellnum > 2 && key >= 8){
			                    	cell.setCellStyle(unlockedCellStyle);
			                    }else{
			                    	cell.setCellStyle(lockedCellStyle);
			                    }
			               }			                
			            }
			        }
				   	workbook.getSheetAt(sheetCount).autoSizeColumn(1);
					workbook.getSheetAt(sheetCount).autoSizeColumn(2);
					workbook.getSheetAt(sheetCount).autoSizeColumn(3);
					workbook.getSheetAt(sheetCount).autoSizeColumn(4);
					workbook.getSheetAt(sheetCount).autoSizeColumn(5);
					workbook.getSheetAt(sheetCount).autoSizeColumn(6);
					workbook.getSheetAt(sheetCount).autoSizeColumn(7);
					workbook.getSheetAt(sheetCount).autoSizeColumn(8);
					workbook.getSheetAt(sheetCount).autoSizeColumn(9);
			        //Write the workbook in file system  
		            FileOutputStream out = new FileOutputStream(new File(reportResultPdfFilPath));
		            workbook.write(out);
		            out.flush();
		            out.close();
		            status = "Success";				         
				} catch (Exception e) {
					e.printStackTrace();
		        	logger.error(e);
	        	}
			}
			else if(studentResult.getExam().equalsIgnoreCase("PT1")||studentResult.getExam().equalsIgnoreCase("PT2")||studentResult.getExam().equalsIgnoreCase("PT3")){
				
				int sheetCount = 0;
				HSSFWorkbook workbook = new HSSFWorkbook();         
				//Create a blank sheet				
				try  {	
					HSSFSheet sheet = workbook.createSheet("consolidate_result_"+studentResult.getStandard()+"_"+studentResult.getSection()+"_"+studentResult.getExam());
			        //sheet.protectSheet("secretPassword");	
			        
			      //MERGING CODE
			        sheet.addMergedRegion(new CellRangeAddress(0,0,0,12));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(1,1,0,12));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(2,2,0,12));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(3,3,0,12));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(4,4,1,1));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(5,5,2,2));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(6,6,3,3));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(7,7,4,4));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(8,8,5,5));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(9,9,6,6));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(10,10,7,7));	//(rowFrom, rowTo, colFrom, colTo)
			        
			        sheet.setDefaultColumnWidth(8);
			        
			        HSSFFont hSSFFont = workbook.createFont();
			        hSSFFont.setFontName(HSSFFont.FONT_ARIAL);
			        hSSFFont.setFontHeightInPoints((short) 12);
			        hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			        hSSFFont.setColor(HSSFColor.BLACK.index);
			        			        
			        /* cell style for locking */
			        CellStyle lockedCellStyle = workbook.createCellStyle();
			        lockedCellStyle.setFont(hSSFFont);
			       // lockedCellStyle.setLocked(true);
			        lockedCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
			        
			        CellStyle nameCellStyle = workbook.createCellStyle();
			        nameCellStyle.setFont(hSSFFont);
			       // nameCellStyle.setLocked(true);
			        nameCellStyle.setAlignment(CellStyle.ALIGN_LEFT);
			        
			        /* cell style for editable cells */
			        CellStyle unlockedCellStyle = workbook.createCellStyle();
			        //unlockedCellStyle.setLocked(false);	
			        unlockedCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
			        
			        
			        //This data needs to be written (Object[])
			        Map<Integer, List<String>> data = new TreeMap<Integer, List<String>>();	
			        
			        List<String> schoolDetailsList = new ArrayList<String>();
			        schoolDetailsList.add("SAINIK SCHOOL PURULIA");
			        data.put(1, schoolDetailsList);
			        
			        
			        List<String> resultDetailsList = new ArrayList<String>();
			        resultDetailsList.add("CONSOLIDATED RESULT SHEET : "+studentResult.getAcademicYear()+" (Periodic Test 1)");
			        data.put(2, resultDetailsList);
			        
			        List<String> standardDetailsList = new ArrayList<String>();
			        standardDetailsList.add("STANDARD : "+studentResult.getStandard()+" - "+studentResult.getSection());
			        data.put(3, standardDetailsList);		        
			        data.put(4, new ArrayList<String>());
			        
					List<String> headerList = new ArrayList<String>();				 				 
					headerList.add("Roll No");				 
					headerList.add("Name");				 
					 
					for(SubjectGroup subjectGroup : subjectGroupList){					
						headerList.add(subjectGroup.getSubjectGroupName());
						/*headerList.add("");
						headerList.add("");
						headerList.add("");
						headerList.add("");*/
					}	        	
			        data.put(5, headerList);
			        
			        List<String> secondHeaderList = new ArrayList<String>();
			        secondHeaderList.add("");
			        secondHeaderList.add("");
			       /*for(SubjectGroup subjectGroup : subjectGroupList){				       				       
				        secondHeaderList.add("Total Obtained");
				        secondHeaderList.add("Total");
				        secondHeaderList.add("SA1");
				        secondHeaderList.add("Tot");
				        secondHeaderList.add("Grd/GP");
			        }*/
			        data.put(6, secondHeaderList);		        
			        data.put(7, new ArrayList<String>());
			        Integer count = 8;
			        
			        for(Student student : studentsSubjectsAndMarksList){
			        	int x=0;
			        	for(StudentResult studentResult2 : student.getStudentResultList()){
			        		studentResult2.setSubject(subjectGroupList.get(x).getSubjectGroupName());
			        		x++;
			        		if(x==subjectGroupList.size())
			        			x=0;
			        	}
			        }
			     
			        for(Student student : studentsSubjectsAndMarksList){
			        	List<String> rowList = new ArrayList<String>();		        	
			        	rowList.add(student.getRollNumber().toString());
			        	rowList.add(student.getStudentName());
			        	int total=0;
			        	for(StudentResult studentResult2 : student.getStudentResultList()){
			        		if(studentResult2.getTotalObtainedChar() == null){
								rowList.add("--");
			        		}else{
								int checker=0;
								for(Student s : studentResultRawDataNew){
									if(student.getRollNumber().equals(s.getRollNumber())){
										for(StudentResult sr:s.getStudentResultList()){
											if((sr.getExam().equalsIgnoreCase("PT1")) && (sr.getSubject().equalsIgnoreCase(studentResult2.getSubject()))){
												rowList.add(sr.getTotalObtainedChar());
												checker=1;
											}
										}
									}
								}
								if(checker==0){
									rowList.add("NA");
								}
							}
						}
		        	    data.put(count, rowList);		
			            int loopControl = count;
			        	loopControl++;
			        	count = loopControl;		 
			        }
			        //Iterate over data and write to sheet
			        Set<Integer> keyset = data.keySet();
			        int rownum = 0;
			        for (Integer key : keyset){
			            Row row = sheet.createRow(rownum++);
			            List<String> rowsList = data.get(key);
			            int cellnum = 0;
			            for (String obj : rowsList) {
			               Cell cell = row.createCell(cellnum++);
			               if(obj instanceof String){
			                    cell.setCellValue((String)obj);
			                    if(cellnum == 2 && key >= 8){
			                    	cell.setCellStyle(nameCellStyle);
			                    }else if(cellnum > 2 && key >= 8){
			                    	cell.setCellStyle(unlockedCellStyle);
			                    }else{
			                    	cell.setCellStyle(lockedCellStyle);
			                    }
			               }			                
			            }
			        }
				   	workbook.getSheetAt(sheetCount).autoSizeColumn(1);
					workbook.getSheetAt(sheetCount).autoSizeColumn(2);
					workbook.getSheetAt(sheetCount).autoSizeColumn(3);
					workbook.getSheetAt(sheetCount).autoSizeColumn(4);
					workbook.getSheetAt(sheetCount).autoSizeColumn(5);
					workbook.getSheetAt(sheetCount).autoSizeColumn(6);
					workbook.getSheetAt(sheetCount).autoSizeColumn(7);
					workbook.getSheetAt(sheetCount).autoSizeColumn(8);
					workbook.getSheetAt(sheetCount).autoSizeColumn(9);
			        //Write the workbook in file system  
		            FileOutputStream out = new FileOutputStream(new File(reportResultPdfFilPath));
		            workbook.write(out);
		            out.flush();
		            out.close();
		            status = "Success";				         
				} catch (Exception e) {
					e.printStackTrace();
		        	logger.error(e);
	        	}
			}
				else if(studentResult.getExam().equalsIgnoreCase("M1")||studentResult.getExam().equalsIgnoreCase("M2")){
				
				int sheetCount = 0;
				HSSFWorkbook workbook = new HSSFWorkbook();         
				//Create a blank sheet				
				try  {	
					HSSFSheet sheet = workbook.createSheet("consolidate_result_"+studentResult.getStandard()+"_"+studentResult.getSection()+"_"+studentResult.getExam());
			        //sheet.protectSheet("secretPassword");	
			        
			      //MERGING CODE
			        sheet.addMergedRegion(new CellRangeAddress(0,0,0,12));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(1,1,0,12));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(2,2,0,12));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(3,3,0,12));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(4,4,1,1));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(5,5,2,2));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(6,6,3,3));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(7,7,4,4));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(8,8,5,5));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(9,9,6,6));	//(rowFrom, rowTo, colFrom, colTo)
			        sheet.addMergedRegion(new CellRangeAddress(10,10,7,7));	//(rowFrom, rowTo, colFrom, colTo)
			        
			        sheet.setDefaultColumnWidth(8);
			        
			        HSSFFont hSSFFont = workbook.createFont();
			        hSSFFont.setFontName(HSSFFont.FONT_ARIAL);
			        hSSFFont.setFontHeightInPoints((short) 12);
			        hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			        hSSFFont.setColor(HSSFColor.BLACK.index);
			        			        
			        /* cell style for locking */
			        CellStyle lockedCellStyle = workbook.createCellStyle();
			        lockedCellStyle.setFont(hSSFFont);
			       // lockedCellStyle.setLocked(true);
			        lockedCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
			        
			        CellStyle nameCellStyle = workbook.createCellStyle();
			        nameCellStyle.setFont(hSSFFont);
			       // nameCellStyle.setLocked(true);
			        nameCellStyle.setAlignment(CellStyle.ALIGN_LEFT);
			        
			        /* cell style for editable cells */
			        CellStyle unlockedCellStyle = workbook.createCellStyle();
			        //unlockedCellStyle.setLocked(false);	
			        unlockedCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
			        
			        
			        //This data needs to be written (Object[])
			        Map<Integer, List<String>> data = new TreeMap<Integer, List<String>>();	
			        
			        List<String> schoolDetailsList = new ArrayList<String>();
			        schoolDetailsList.add("SAINIK SCHOOL PURULIA");
			        data.put(1, schoolDetailsList);
			        
			        
			        List<String> resultDetailsList = new ArrayList<String>();
			        resultDetailsList.add("CONSOLIDATED RESULT SHEET : "+studentResult.getAcademicYear()+" (Monthly 1)");
			        data.put(2, resultDetailsList);
			        
			        List<String> standardDetailsList = new ArrayList<String>();
			        standardDetailsList.add("STANDARD : "+studentResult.getStandard()+" - "+studentResult.getSection());
			        data.put(3, standardDetailsList);		        
			        data.put(4, new ArrayList<String>());
			        
					List<String> headerList = new ArrayList<String>();				 				 
					headerList.add("Roll No");				 
					headerList.add("Name");				 
					 
					for(SubjectGroup subjectGroup : subjectGroupList){					
						headerList.add(subjectGroup.getSubjectGroupName());
						/*headerList.add("");
						headerList.add("");
						headerList.add("");
						headerList.add("");*/
					}	        	
			        data.put(5, headerList);
			        
			        List<String> secondHeaderList = new ArrayList<String>();
			        secondHeaderList.add("");
			        secondHeaderList.add("");
			       /*for(SubjectGroup subjectGroup : subjectGroupList){				       				       
				        secondHeaderList.add("Total Obtained");
				        secondHeaderList.add("Total");
				        secondHeaderList.add("SA1");
				        secondHeaderList.add("Tot");
				        secondHeaderList.add("Grd/GP");
			        }*/
			        data.put(6, secondHeaderList);		        
			        data.put(7, new ArrayList<String>());
			        Integer count = 8;
			        
			        for(Student student : studentsSubjectsAndMarksList){
			        	int x=0;
			        	for(StudentResult studentResult2 : student.getStudentResultList()){
			        		studentResult2.setSubject(subjectGroupList.get(x).getSubjectGroupName());
			        		x++;
			        		if(x==subjectGroupList.size())
			        			x=0;
			        	}
			        }
			     
			        for(Student student : studentsSubjectsAndMarksList){
			        	List<String> rowList = new ArrayList<String>();		        	
			        	rowList.add(student.getRollNumber().toString());
			        	rowList.add(student.getStudentName());
			        	int total=0;
			        	for(StudentResult studentResult2 : student.getStudentResultList()){
			        		if(studentResult2.getTotalObtainedChar() == null){
								rowList.add("--");
			        		}else{
								int checker=0;
								for(Student s : studentResultRawDataNew){
									if(student.getRollNumber().equals(s.getRollNumber())){
										for(StudentResult sr:s.getStudentResultList()){
											if((sr.getExam().equalsIgnoreCase("M1")) && (sr.getSubject().equalsIgnoreCase(studentResult2.getSubject()))){
												rowList.add(sr.getTotalObtainedChar());
												checker=1;
											}
										}
									}
								}
								if(checker==0){
									rowList.add("NA");
								}
							}
						}
		        	    data.put(count, rowList);		
			            int loopControl = count;
			        	loopControl++;
			        	count = loopControl;		 
			        }
			        //Iterate over data and write to sheet
			        Set<Integer> keyset = data.keySet();
			        int rownum = 0;
			        for (Integer key : keyset){
			            Row row = sheet.createRow(rownum++);
			            List<String> rowsList = data.get(key);
			            int cellnum = 0;
			            for (String obj : rowsList) {
			               Cell cell = row.createCell(cellnum++);
			               if(obj instanceof String){
			                    cell.setCellValue((String)obj);
			                    if(cellnum == 2 && key >= 8){
			                    	cell.setCellStyle(nameCellStyle);
			                    }else if(cellnum > 2 && key >= 8){
			                    	cell.setCellStyle(unlockedCellStyle);
			                    }else{
			                    	cell.setCellStyle(lockedCellStyle);
			                    }
			               }			                
			            }
			        }
				   	workbook.getSheetAt(sheetCount).autoSizeColumn(1);
					workbook.getSheetAt(sheetCount).autoSizeColumn(2);
					workbook.getSheetAt(sheetCount).autoSizeColumn(3);
					workbook.getSheetAt(sheetCount).autoSizeColumn(4);
					workbook.getSheetAt(sheetCount).autoSizeColumn(5);
					workbook.getSheetAt(sheetCount).autoSizeColumn(6);
					workbook.getSheetAt(sheetCount).autoSizeColumn(7);
					workbook.getSheetAt(sheetCount).autoSizeColumn(8);
					workbook.getSheetAt(sheetCount).autoSizeColumn(9);
			        //Write the workbook in file system  
		            FileOutputStream out = new FileOutputStream(new File(reportResultPdfFilPath));
		            workbook.write(out);
		            out.flush();
		            out.close();
		            status = "Success";				         
				} catch (Exception e) {
					e.printStackTrace();
		        	logger.error(e);
	        	}
			}
			return status;
		}
		
	/**New CBSE System end**/	

	//Added By Naimisha 04102017
		
		public ChartData loadChart1DataForLibrary(String role, String module, String reportQueryPath){
			
			ChartQueryManager chartQueryManager = new ChartQueryManager();
			Map<String, List<ChartQueryData>> queriesMap = null;
			try {
				queriesMap = chartQueryManager.loadSqlXMLFile(reportQueryPath);
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			}
			
			List<ChartQueryData> queryDatalist = queriesMap.get(module);
			ChartData chartData = new ChartData();
			
			//for(ChartQueryData queryData : queryDatalist){
			ChartQueryData queryData = queryDatalist.get(2);
			
			System.out.println("queryData====="+queryData.toString());
				if(queryData.isDefault() == true){
					chartData.setChartLabel(queryData.getChartLabel());
					chartData.setChartType(queryData.getChartType());
					chartData.setCaption(queryData.getCaption());
					chartData.setSubCaption(queryData.getSubCaption());
					chartData.setXaxisname(queryData.getXaxisname());
					chartData.setYaxisname(queryData.getYaxisname());
					chartData.setNumberprefix(queryData.getNumberprefix());
					chartData.setTheme(queryData.getTheme());
					List <ChartValuesModel> chartValuesModelList = reportDAO.loadChart1DataForLibrary(queryData.getSql());
					chartData.setChartValuesModelList(chartValuesModelList);
				}
			//}
			return chartData;
			
		}

	public ChartData loadChart2DataForLibrary(String role, String module, String reportQueryPath){
			
			ChartQueryManager chartQueryManager = new ChartQueryManager();
			Map<String, List<ChartQueryData>> queriesMap = null;
			try {
				queriesMap = chartQueryManager.loadSqlXMLFile(reportQueryPath);
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			}
			
			List<ChartQueryData> queryDatalist = queriesMap.get(module);
			ChartData chartData = new ChartData();
			
			//for(ChartQueryData queryData : queryDatalist){
			ChartQueryData queryData = queryDatalist.get(3);
			System.out.println("queryData====="+queryData.toString());
				if(queryData.isDefault() == true){
					chartData.setChartLabel(queryData.getChartLabel());
					chartData.setChartType(queryData.getChartType());
					chartData.setCaption(queryData.getCaption());
					chartData.setSubCaption(queryData.getSubCaption());
					chartData.setXaxisname(queryData.getXaxisname());
					chartData.setYaxisname(queryData.getYaxisname());
					chartData.setNumberprefix(queryData.getNumberprefix());
					chartData.setTheme(queryData.getTheme());
					List <ChartValuesModel> chartValuesModelList = reportDAO.loadChart1DataForLibrary(queryData.getSql());
					chartData.setChartValuesModelList(chartValuesModelList);
				}
			//}
			return chartData;
			
		}
		
		public ChartData loadChart3DataForLibrary(String role, String module, String reportQueryPath){
			
			ChartQueryManager chartQueryManager = new ChartQueryManager();
			Map<String, List<ChartQueryData>> queriesMap = null;
			try {
				queriesMap = chartQueryManager.loadSqlXMLFile(reportQueryPath);
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			}
			
			List<ChartQueryData> queryDatalist = queriesMap.get(module);
			ChartData chartData = new ChartData();
			
			//for(ChartQueryData queryData : queryDatalist){
			ChartQueryData queryData = queryDatalist.get(4);
			System.out.println("queryData====="+queryData.toString());
				if(queryData.isDefault() == true){
					chartData.setChartLabel(queryData.getChartLabel());
					chartData.setChartType(queryData.getChartType());
					chartData.setCaption(queryData.getCaption());
					chartData.setSubCaption(queryData.getSubCaption());
					chartData.setXaxisname(queryData.getXaxisname());
					chartData.setYaxisname(queryData.getYaxisname());
					chartData.setNumberprefix(queryData.getNumberprefix());
					chartData.setTheme(queryData.getTheme());
					List <ChartValuesModel> chartValuesModelList = reportDAO.loadChart1DataForLibrary(queryData.getSql());
					chartData.setChartValuesModelList(chartValuesModelList);
				}
			//}
			return chartData;
			
		}
		
		public ChartData loadChart4DataForLibrary(String role, String module, String reportQueryPath){
			
			ChartQueryManager chartQueryManager = new ChartQueryManager();
			Map<String, List<ChartQueryData>> queriesMap = null;
			try {
				queriesMap = chartQueryManager.loadSqlXMLFile(reportQueryPath);
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			}
			
			List<ChartQueryData> queryDatalist = queriesMap.get(module);
			ChartData chartData = new ChartData();
			
			//for(ChartQueryData queryData : queryDatalist){
			ChartQueryData queryData = queryDatalist.get(5);
			System.out.println("queryData====="+queryData.toString());
				if(queryData.isDefault() == true){
					chartData.setChartLabel(queryData.getChartLabel());
					chartData.setChartType(queryData.getChartType());
					chartData.setCaption(queryData.getCaption());
					chartData.setSubCaption(queryData.getSubCaption());
					chartData.setXaxisname(queryData.getXaxisname());
					chartData.setYaxisname(queryData.getYaxisname());
					chartData.setNumberprefix(queryData.getNumberprefix());
					chartData.setTheme(queryData.getTheme());
					List <ChartValuesModel> chartValuesModelList = reportDAO.loadChart1DataForLibrary(queryData.getSql());
					chartData.setChartValuesModelList(chartValuesModelList);
				}
			//}
			return chartData;
			
		}

}
