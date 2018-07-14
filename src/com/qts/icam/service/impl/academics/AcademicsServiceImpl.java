package com.qts.icam.service.impl.academics;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

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
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.google.gson.Gson;
import com.qts.icam.dao.academics.AcademicsDAO;
import com.qts.icam.dao.backoffice.BackOfficeDAO;
import com.qts.icam.dao.bulkdata.DataDAO;
import com.qts.icam.dao.common.CommonDao;
import com.qts.icam.model.academics.Algorithm;
import com.qts.icam.model.academics.AssetConsumption;
import com.qts.icam.model.academics.CoScholasticResult;
import com.qts.icam.model.common.Course;
import com.qts.icam.model.academics.CourseSubjectMapping;
import com.qts.icam.model.academics.CourseType;
import com.qts.icam.model.academics.DescriptiveIndicatorHead;
import com.qts.icam.model.academics.DescriptiveIndicatorSkill;
import com.qts.icam.model.academics.ExamMarks;
import com.qts.icam.model.academics.RoutineTableGridData;
import com.qts.icam.model.academics.StudentCourseSubjectMapping;
import com.qts.icam.model.academics.StudentResult;
import com.qts.icam.model.academics.Subject;
import com.qts.icam.model.academics.SubjectGroup;
import com.qts.icam.model.academics.UserDefinedExams;
import com.qts.icam.model.academics.UserExamMarks;
import com.qts.icam.model.admission.AdmissionForm;
import com.qts.icam.model.backoffice.Exam;
import com.qts.icam.model.backoffice.Term;
import com.qts.icam.model.backoffice.TimeTableGridData;
import com.qts.icam.model.common.TeacherSubjectMapping;
import com.qts.icam.model.common.AcademicYear;
import com.qts.icam.model.common.Asset;
import com.qts.icam.model.common.Department;
import com.qts.icam.model.common.ExamType;
import com.qts.icam.model.common.Standard;
import com.qts.icam.model.common.Student;
import com.qts.icam.model.common.UploadFile;
import com.qts.icam.model.erp.Employee;
import com.qts.icam.model.event.EventAchievement;
import com.qts.icam.model.event.SchoolEvent;
import com.qts.icam.model.grade.GradingSystem;
import com.qts.icam.model.venue.Venue;
import com.qts.icam.service.academics.AcademicsService;
import com.qts.icam.utility.bulkdata.DataUtility;
import com.qts.icam.utility.customexception.CustomException;
import com.qts.icam.utility.uploaddownload.FileUploadDownload;

@Service
public class AcademicsServiceImpl implements AcademicsService {
	
	@Autowired
	AcademicsDAO academicsDAO;
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	DataUtility dataUtility;
		
	@Autowired
	DataDAO dataDAO;
	
	@Autowired
	CommonDao commonDAO;
	
	@Autowired
	FileUploadDownload fileUploadDownload;
	
	List<Asset> assetList = null;
	List<AssetConsumption> assetConsumptionList = null;

	private final static Logger logger = Logger.getLogger("AcademicsServiceImpl.class");
	
	@Override
	public List<Standard> getStandardsWithSection() throws CustomException {
		return academicsDAO.getStandardsWithSection();
	}
	
	@Override
	public List<SchoolEvent> getAllEventAchievements()  {
		return academicsDAO.getAllEventAchievements();
	}
	
	@Override
	public List<SchoolEvent> getSchoolEventList() {
		return academicsDAO.getSchoolEventList();
	}
	
	@Override
	public String insertSchoolEvent(SchoolEvent schoolEvent){
		return academicsDAO.insertSchoolEvent(schoolEvent);
	}
	
	@Override
	public String getEventDescription(String eventName){
		return academicsDAO.getEventDescription(eventName);
	}
	
	@Override
	public String addStandard(Standard standard) throws CustomException {
		return academicsDAO.addStandard(standard);
	}

	@Override
	public String editStandard(Standard standard) throws CustomException {
		return academicsDAO.editStandard(standard);
	}

	@Override
	public List<SubjectGroup> getSubjectGroup() throws CustomException {
		return academicsDAO.getSubjectGroup();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public String addSubjectGroup(SubjectGroup subjectGroup) throws CustomException {
		return academicsDAO.addSubjectGroup(subjectGroup);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public String editSubjectGroup(SubjectGroup subjectGroup) throws CustomException {
		return academicsDAO.editSubjectGroup(subjectGroup);
	}

	

	@Override
	public String addSubject(Subject subject) throws CustomException {
		return academicsDAO.addSubject(subject);
	}

	@Override
	public String editSubject(Subject subject) throws CustomException {
		return academicsDAO.editSubject(subject);
	}

	@Override
	public String getSubjectsForCourse(String course) throws CustomException {
		return academicsDAO.getSubjectsForCourse(course);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String editCourseSubjectMapping(CourseSubjectMapping courseSubjectMapping) throws CustomException {
		return academicsDAO.editCourseSubjectMapping(courseSubjectMapping);
	}


	@Override
	public String getSubjectsAndMarksForStandard(String standard, String exam) throws CustomException {
		String examMarksString = "";
		List<ExamMarks> examMarksList = academicsDAO.getSubjectsAndMarksForStandard(standard, exam);
		if(null != examMarksList && examMarksList.size() != 0){
			for(ExamMarks examMarks : examMarksList){
				examMarksString = examMarksString+
						examMarks.getSubject().getSubjectCode()+"*~*"+
						examMarks.getSubject().getSubjectName()+"*~*"+
						examMarks.getExam().getExamCode()+"*~*"+
						examMarks.getExam().getExamName()+"*~*"+
						examMarks.getTheory()+"*~*"+
						examMarks.getPractical()+"*~*"+
						examMarks.getTotal()+"*~*"+
						examMarks.getPass()+"*~*"+
						examMarks.getTheoryPass()+"*~*"+
						examMarks.getPracticalPass()+"###";
			}
		}
		return examMarksString;
	}

	@Override
	public String editExamMarks(List<ExamMarks> examMarksList) throws CustomException {
		return academicsDAO.editExamMarks(examMarksList);
	}
	
	@Override
	public String editStudentResult(List<StudentResult> studentResultList, String insertUpdate) throws CustomException {
		return academicsDAO.editStudentResult(studentResultList, insertUpdate);
	}

	

//@Override
//	public int insertStandardSubjectMappExcelBulkData(String excelFile, String updatedBy) throws CustomException {
//		int insertStatus = 0;
//		int standardSubjectMappInsertStatus = 0;
//		try{
//			logger.info("In insertStandardSubjectMappExcelBulkData(String excelFile, String updatedBy) method of AcademicsServiceImpl");
//			
//			List<StandardSubjectMapping> standardSubjectMappList = dataUtility.readExcelFileStandardSubjectMapping(excelFile);					
//			if(standardSubjectMappList.size() != 0){
//				standardSubjectMappInsertStatus = dataDAO.batchInsertForStandardSubjectMapp(standardSubjectMappList, updatedBy);
//				logger.info("@@ standardSubjectMappInsertStatus  ::  "+standardSubjectMappInsertStatus);
//			}
//			if(standardSubjectMappInsertStatus != 0){
//				insertStatus = 1;
//			}
//		}catch(Exception e){
//			logger.error("Exception in insertStandardSubjectMappExcelBulkData() to read excel and insert in AcademicsServiceImpl", e);
//			
//		}
//		return insertStatus;
//	}
	
	@Override
	public String insertAssignSection(List<Student> studentList) throws CustomException {
		return academicsDAO.insertAssignSection(studentList);
	}
	
	@Override
	public List<Student> getPendingSectionAssignment() throws CustomException {
		return academicsDAO.getPendingSectionAssignment();
	}
	
	@Override
	public String getStudentsToAssignSection(String standard, String section) throws CustomException {
		String studentString = "";
			try{
				Student student=new Student();
				student.setStandard(standard);
				student.setSection(section);
				List<Student> studentList = academicsDAO.getStudentsToAssignSection(student);
				StringBuilder sb = new StringBuilder();
				if(studentList!=null && studentList.size()!=0){				
				for(Student studentObject : studentList){
					if (sb.length() != 0) {
						sb.append(";");
				    }
					sb.append(studentObject.getUserId());
					sb.append("*");
					sb.append(studentObject.getStudentName());
					sb.append("*");
					sb.append(studentObject.getSecondLanguage());
				}				
			}else{
				logger.info("Section not found by ajax @ getStudentsAgainstStandardAndSection()In CommonServiceImpl");
			}
				studentString = (new Gson().toJson(sb.toString()));
			}catch(Exception e){
				e.printStackTrace();
				logger.error("Exception in getStudentsAgainstStandardAndSection() in CommonServiceImpl ", e);
			}
		return studentString;
	}
	
	@Override
	public String getStudentsAndMarks(StudentResult studentResult, String loggedInUser) throws CustomException {
		String studentsSubjectsAndMarks = "";
		List<StudentResult> studentsSubjectsAndMarksList = academicsDAO.getStudentsAndMarks(studentResult, loggedInUser);
		if(null != studentsSubjectsAndMarksList && studentsSubjectsAndMarksList.size() != 0){
			if(studentsSubjectsAndMarksList.get(0).getStatus().equalsIgnoreCase("INSERT")){
				for(StudentResult studentResultObject : studentsSubjectsAndMarksList){
					studentsSubjectsAndMarks = studentsSubjectsAndMarks+
							studentResultObject.getRollNumber()+"("+studentResultObject.getName()+")*~*"+
							studentResultObject.getTheory()+"*~*"+
							studentResultObject.getPractical()+"*~*"+
							studentResultObject.getTotal()+"*~*"+
							studentResultObject.getPass()+"*~*"+
							studentResultObject.getTheoryPass()+"*~*"+
							studentResultObject.getPracticalPass()+"*~*0*~*0*~*0*~*FAIL"+"###";
				}
				studentsSubjectsAndMarks = studentsSubjectsAndMarks+"INSERT";
			}else if(studentsSubjectsAndMarksList.get(0).getStatus().equalsIgnoreCase("UPDATE")){
				
				for(StudentResult studentResultObject : studentsSubjectsAndMarksList){
					String theoryObtained,practicalObtained,totalObtained;
					if(-1 == studentResultObject.getTheoryObtained())
						theoryObtained = "AB";
					else
						theoryObtained = studentResultObject.getTheoryObtained().toString();
					
					if(-1 == studentResultObject.getPracticalObtained())
						practicalObtained = "AB";
					else
						practicalObtained = studentResultObject.getPracticalObtained().toString();
					
					if(-1 == studentResultObject.getTotalObtained())
						totalObtained = "AB";
					else
						totalObtained = studentResultObject.getTotalObtained().toString();
					
					studentsSubjectsAndMarks = studentsSubjectsAndMarks+
							studentResultObject.getRollNumber()+"("+studentResultObject.getName()+")*~*"+
							studentResultObject.getTheory()+"*~*"+
							studentResultObject.getPractical()+"*~*"+
							studentResultObject.getTotal()+"*~*"+
							studentResultObject.getPass()+"*~*"+
							studentResultObject.getTheoryPass()+"*~*"+
							studentResultObject.getPracticalPass()+"*~*"+
							theoryObtained+"*~*"+
							practicalObtained+"*~*"+
							totalObtained+"*~*"+
							studentResultObject.getPassFail()+"###";
				}
				studentsSubjectsAndMarks = studentsSubjectsAndMarks+"UPDATE";
			}else{
				for(StudentResult studentResultObject : studentsSubjectsAndMarksList){
					String theoryObtained,practicalObtained,totalObtained;
					if(-1 == studentResultObject.getTheoryObtained())
						theoryObtained = "AB";
					else
						theoryObtained = studentResultObject.getTheoryObtained().toString();
					
					if(-1 == studentResultObject.getPracticalObtained())
						practicalObtained = "AB";
					else
						practicalObtained = studentResultObject.getPracticalObtained().toString();
					
					if(-1 == studentResultObject.getTotalObtained())
						totalObtained = "AB";
					else
						totalObtained = studentResultObject.getTotalObtained().toString();
					
					studentsSubjectsAndMarks = studentsSubjectsAndMarks+
							studentResultObject.getRollNumber()+"("+studentResultObject.getName()+")*~*"+
							studentResultObject.getTheory()+"*~*"+
							studentResultObject.getPractical()+"*~*"+
							studentResultObject.getTotal()+"*~*"+
							studentResultObject.getPass()+"*~*"+
							studentResultObject.getTheoryPass()+"*~*"+
							studentResultObject.getPracticalPass()+"*~*"+
							theoryObtained+"*~*"+
							practicalObtained+"*~*"+							
							totalObtained+"*~*"+
							studentResultObject.getPassFail()+"###";
				}
				studentsSubjectsAndMarks = studentsSubjectsAndMarks+"NA";
			}
		}
		logger.info(studentsSubjectsAndMarks);
		
		return studentsSubjectsAndMarks;
	}

	
	@Override
	public List<DescriptiveIndicatorSkill> getDescriptiveIndicatorHeadList()throws CustomException {
		return academicsDAO.getDescriptiveIndicatorHeadList();
	}

	@Override
	public List<CoScholasticResult> getCoScholasticResultList() throws CustomException {
		return academicsDAO.getCoScholasticResultList();
	}

	@Override
	public List<CoScholasticResult> getStudentsForCoScholastic(CoScholasticResult coScholasticResult) throws CustomException {
		return academicsDAO.getStudentsForCoScholastic(coScholasticResult);
	}

	@Override
	public String saveCoScholasticResult(List<CoScholasticResult> studentList)throws CustomException {
		return academicsDAO.saveCoScholasticResult(studentList);
	}

	@Override
	public List<CoScholasticResult> getInsertedCoScholasticStudents(CoScholasticResult coScholasticResult) throws CustomException {
		return academicsDAO.getInsertedCoScholasticStudents(coScholasticResult);
	}

	@Override
	public String getStudentsCoScholasticResult(String roll, String loggedInUser)throws CustomException {
		return academicsDAO.getStudentsCoScholasticResult(roll, loggedInUser);
	}

	@Override
	public String editCoScholasticResult(List<CoScholasticResult> studentList)throws CustomException {
		return academicsDAO.editCoScholasticResult(studentList);
	}

	@Override
	public String getSubjectGroupForStandard(String standard)throws CustomException {
		return academicsDAO.getSubjectGroupForStandard(standard);
	}

	@Override
	public String addUserDefinedExams(UserDefinedExams userDefinedExams)throws CustomException {
		return academicsDAO.addUserDefinedExams(userDefinedExams);
	}

	@Override
	public String editUserDefinedExams(List<UserDefinedExams> userDefinedExamsList) throws CustomException {
		return academicsDAO.editUserDefinedExams(userDefinedExamsList);
	}

	@Override
	public List<UserDefinedExams> getAllUserDefinedExams()throws CustomException {
		return academicsDAO.getAllUserDefinedExams();
	}

	@Override
	public List<UserDefinedExams> getUserDefinedExamsForStandard(String standard)throws CustomException {
		return academicsDAO.getUserDefinedExamsForStandard(standard);
	}

	@Override
	public List<UserExamMarks> getSubjectAndMarksForUserDefinedExams(UserExamMarks userExamMarks) throws CustomException {
		return academicsDAO.getSubjectAndMarksForUserDefinedExams(userExamMarks);
	}

	@Override
	public String editUserExamMarks(List<UserExamMarks> examMarksList)throws CustomException {
		return academicsDAO.editUserExamMarks(examMarksList);
	}

	@Override
	public String getStudentsAndMarksForUserDefinedExams(StudentResult studentResult)throws CustomException {
		String studentsSubjectsAndMarks = "";
		List<StudentResult> studentsSubjectsAndMarksList = academicsDAO.getStudentsAndMarksForUserDefinedExams(studentResult);
		System.out.println("studentsSubjectsAndMarksList ::"+studentsSubjectsAndMarksList);
		if(null != studentsSubjectsAndMarksList && studentsSubjectsAndMarksList.size() != 0){
				
				for(StudentResult studentResultObject : studentsSubjectsAndMarksList){
					String theoryObtained, practicalObtained, totalObtained;
					if(-1 == studentResultObject.getTheoryObtained())
						theoryObtained = "AB";
					else
						theoryObtained = studentResultObject.getTheoryObtained().toString();
					
					if(-1 == studentResultObject.getPracticalObtained())
						practicalObtained = "AB";
					else
						practicalObtained = studentResultObject.getPracticalObtained().toString();
					
					if(-1 == studentResultObject.getTotalObtained())
						totalObtained = "AB";
					else
						totalObtained = studentResultObject.getTotalObtained().toString();
					
					studentsSubjectsAndMarks = studentsSubjectsAndMarks+
							studentResultObject.getRollNumber()+"("+studentResultObject.getName()+")*~*"+
							studentResultObject.getTheory()+"*~*"+
							studentResultObject.getPractical()+"*~*"+
							studentResultObject.getTotal()+"*~*"+
							studentResultObject.getPass()+"*~*"+
							studentResultObject.getTheoryPass()+"*~*"+
							studentResultObject.getPracticalPass()+"*~*"+
							theoryObtained+"*~*"+
							practicalObtained+"*~*"+
							totalObtained+"*~*"+
							studentResultObject.getPassFail()+"###";
				}
		}
		logger.info(studentsSubjectsAndMarks);
		System.out.println("studentsSubjectsAndMarksList STRING ::"+studentsSubjectsAndMarksList);
		return studentsSubjectsAndMarks;
	}


	@Override
	public String editStudentResultForUserExam(List<StudentResult> studentResultList)throws CustomException {
		return academicsDAO.editStudentResultForUserExam(studentResultList);
	}
	
	@Override
	public String uploadQuestionPaper(StudentResult studentResult) throws CustomException {
		String inStatus = "";
		try{
			/*
			 * used for set upload file  path
			 */		
			String dynamicSubFolderPath = studentResult.getAcademicYear()+"/"+studentResult.getCourse()+"/"+studentResult.getExam()+"/"+studentResult.getSubject()+"/";
			/*String filePath = studentResult.getUploadFile().getAttachment().getStorageRootPath()+studentResult.getUploadFile().getAttachment().getFolderName();*/
			/**this line is modified for save files in external repository*/
			String filePath = studentResult.getUploadFile().getAttachment().getStorageRootPath()+"/"+studentResult.getUploadFile().getAttachment().getFolderName();
				   filePath = filePath+dynamicSubFolderPath;
				   
			UploadFile uploadFile = studentResult.getUploadFile();		
			if(null != uploadFile){
					/*
					 * this is used to upload Previous Educational doc
					 */
				if(null != uploadFile.getFileData() && !uploadFile.getFileData().isEmpty()){
					for (CommonsMultipartFile file : uploadFile.getFileData()) {
						if(file.getOriginalFilename() != ""){
							fileUploadDownload.fileUpload(filePath, file);							
						}
					}
				}
			}
		}catch(Exception e){
			inStatus = null;
			e.printStackTrace();
			CustomException.exceptionHandler(e);
		}
		return inStatus;
	}
	
	@Override
	public String downloadExelForCBSEExams(StudentResult studentResult, String folderPath, String fileName, String loggedInUser) {
		String status = "";
		try {
			List<StudentResult> studentsSubjectsAndMarksList=academicsDAO.getStudentsAndMarks(studentResult, loggedInUser);
			if(null!=studentsSubjectsAndMarksList)
				status=createExelForCBSEExams(studentResult, studentsSubjectsAndMarksList, folderPath, fileName);
		} catch (CustomException e) {
			e.printStackTrace();
		}
		return status;
	}

	private String createExelForCBSEExams(StudentResult studentResult, List<StudentResult> studentsSubjectsAndMarksList, String folderPath, String fileName) {
		String fileCompletePath = folderPath+fileName;
		String status = "";
		int sheetCount = 0;
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		//Create a blank sheet
		try  {	
			HSSFSheet sheet = workbook.createSheet(fileName);
			
			sheet.addMergedRegion(new CellRangeAddress(0,0,0,1));	//(rowFrom, rowTo, colFrom, colTo)
			sheet.addMergedRegion(new CellRangeAddress(0,0,2,3));	//(rowFrom, rowTo, colFrom, colTo)
			sheet.addMergedRegion(new CellRangeAddress(0,0,4,5));	//(rowFrom, rowTo, colFrom, colTo)
			sheet.addMergedRegion(new CellRangeAddress(0,0,6,7));	//(rowFrom, rowTo, colFrom, colTo)
			
			sheet.setDefaultColumnWidth(20);
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
	        lockedCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
	        
	        /* cell style for editable cells */
	        CellStyle unlockedCellStyle = workbook.createCellStyle();
	        unlockedCellStyle.setFont(hSSFFont);
	        unlockedCellStyle.setLocked(false);
	        unlockedCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
	        
	        hSSFFont = workbook.createFont();
	        hSSFFont.setFontName(HSSFFont.FONT_ARIAL);
	        hSSFFont.setFontHeightInPoints((short) 14);
	        hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	        hSSFFont.setColor(HSSFColor.BLACK.index);
	        
	        /* cell style for Heading */
	        CellStyle headerCellStyle = workbook.createCellStyle();
	        headerCellStyle.setFont(hSSFFont);
	        headerCellStyle.setLocked(true);
	        headerCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
	        
	        Map<String, List<String>> data = new TreeMap<String, List<String>>();
	        
	        List<String> headerList = new ArrayList<String>();
	        headerList.add("Standard");
	        headerList.add(studentResult.getStandard());
	        headerList.add("Section");
	        headerList.add(studentResult.getSection());
	        headerList.add("Subject");
	        headerList.add(studentResult.getSubject());
	        headerList.add("Exam");
	        headerList.add(studentResult.getExam());
	        data.put("1", headerList);
	        
	        headerList = new ArrayList<String>();
	        data.put("2", headerList);
	        
	        headerList = new ArrayList<String>();
	        headerList.add("Roll Number");
	        headerList.add("Theory Obtained");
	        headerList.add("Theory Total");
	        headerList.add("Theory Pass");
	        if(null != studentsSubjectsAndMarksList && studentsSubjectsAndMarksList.size() != 0){
	        	if(!studentsSubjectsAndMarksList.get(0).getPractical().equals(0)){
			        headerList.add("Practical Obtained");
			        headerList.add("Practical Total");
			        headerList.add("Practical Pass");
	        	}
	        }
	        headerList.add("Total Obtained");
	        headerList.add("Total");
	        headerList.add("Total Pass");
	        headerList.add("Pass/Fail");	        
	        data.put("3", headerList);
	        
	        //*******************************************************************
	        
	        
	        if(null != studentsSubjectsAndMarksList && studentsSubjectsAndMarksList.size() != 0){
				if(studentsSubjectsAndMarksList.get(0).getStatus().equalsIgnoreCase("INSERT")){
					int i = 4;
					for(StudentResult studentResultObject : studentsSubjectsAndMarksList){
						headerList = new ArrayList<String>();
						headerList.add(studentResultObject.getRollNumber());
						headerList.add("0");
						headerList.add(studentResultObject.getTheory()+"");
						headerList.add(studentResultObject.getTheoryPass()+"");
				        if(!studentResultObject.getPractical().equals(0)){
							headerList.add("0");
							headerList.add(studentResultObject.getPractical()+"");
							headerList.add(studentResultObject.getPracticalPass()+"");
				        }
						headerList.add("0");
						headerList.add(studentResultObject.getTotal()+"");
						headerList.add(studentResultObject.getPass()+"");
						headerList.add("FAIL");
						data.put(i+"", headerList);
						i++;
					}
				}else{
					int i = 4;
					for(StudentResult studentResultObject : studentsSubjectsAndMarksList){
						String theoryObtained, practicalObtained, totalObtained;
						if(studentResultObject.getTheoryObtained() < 0){
							theoryObtained = "AB";
						}else{
							theoryObtained = studentResultObject.getTheoryObtained().toString();
						}
						
						if(studentResultObject.getPracticalObtained() < 0){
							practicalObtained = "AB";
						}else{
							practicalObtained = studentResultObject.getPracticalObtained().toString();
						}
						
						if(studentResultObject.getTotalObtained() < 0){
							totalObtained = "AB";
	        			}else{
							totalObtained = studentResultObject.getTotalObtained().toString();
						}
						
						headerList = new ArrayList<String>();
						headerList.add(studentResultObject.getRollNumber());
						headerList.add(theoryObtained);
						headerList.add(studentResultObject.getTheory()+"");
						headerList.add(studentResultObject.getTheoryPass()+"");
						if(!studentResultObject.getPractical().equals(0)){
							headerList.add(practicalObtained);
							headerList.add(studentResultObject.getPractical()+"");
							headerList.add(studentResultObject.getPracticalPass()+"");
						}
						headerList.add(totalObtained);
						headerList.add(studentResultObject.getTotal()+"");
						headerList.add(studentResultObject.getPass()+"");
						headerList.add(studentResultObject.getPassFail());
						data.put(i+"", headerList);
						i++;
					}
				}
			}
	        
	        
	      //Iterate over data and write to sheet
	        //Set<String> keyset = data.keySet();
	        int dataSize = data.size();
	        int rownum = 0;
	        for(int counter = 1; counter<=dataSize; counter++){
	        //for (String key : keyset){
	        	String key = counter+"";
	            Row row = sheet.createRow(rownum++);
	            	            
	            int cellnum = 0;
	            int unlockCount = 0;
	            
	            if(rownum == 1){
	            	int cellCount = 0;
	            	List<String> rowsList  = data.get(key);
	            	if(null != rowsList && rowsList.size() != 0){
	            		String totalContent = "";
			            for (String obj : rowsList) {            	
			            	Cell cell = null;
			            	if(cellCount == 0){
			            		totalContent = totalContent + (String)obj + " : ";
			            	}else if(cellCount == 2){
			            		cell = row.createCell(0);
			            		cell.setCellValue(totalContent);
			                    cell.setCellStyle(headerCellStyle);
			                    totalContent = "";
			                    totalContent = totalContent + (String)obj + " : ";
			            	}else if((cellCount%2) == 0){
			            		cell = row.createCell(cellnum-2);
			            		cell.setCellValue(totalContent);
			                    cell.setCellStyle(headerCellStyle);
			                    totalContent = "";
			                    totalContent = totalContent + (String)obj + " : ";
			            	}else if(cellCount == 7){
			            		totalContent = totalContent + (String)obj;
			            		cell = row.createCell(cellnum-1);
			            		cell.setCellValue(totalContent);
			                    cell.setCellStyle(headerCellStyle);
			            	}else {
			            		totalContent = totalContent + (String)obj;
			            	}
			            	unlockCount++;
			            	cellCount++;
			            	cellnum++;
			            }
		            }
	            }else{
	            	List<String> rowsList  = data.get(key);
	            	if(null != rowsList && rowsList.size() != 0){
			            for (String obj : rowsList) {
			            	Cell cell = row.createCell(cellnum++);
			            	if(obj instanceof String){
			            		cell.setCellValue((String)obj);
			            		if(key.equals("3")){
			            			cell.setCellStyle(lockedCellStyle);
			            		}else if(unlockCount == 0 || unlockCount == 2 || unlockCount == 3 || unlockCount == 5 || unlockCount == 6 || unlockCount == 8 || unlockCount == 9){
			            			cell.setCellStyle(lockedCellStyle);
			            		}else{
			                    	cell.setCellStyle(unlockedCellStyle);
			            		}
			            	}
			            	unlockCount++;
			            }
		            }
	            }
	            
	        }
	        
	        
	      //Write the workbook in file system 
	        File outDir = new File(folderPath); 
			boolean isDirCreated = outDir.mkdirs();
			if (isDirCreated)
				logger.info("In FileUploadDownload class fileUpload() method: upload file folder location created.");
			else
				logger.info("In FileUploadDownload class fileUpload() method: upload file folder location already exist.");
			
			FileOutputStream out = null;
			try{
	            out = new FileOutputStream(new File(fileCompletePath));
	            workbook.write(out);            
			}catch(Exception e){
				logger.error("File write error in createExelForCBSEExams of Academics service ", e);
			}finally{
				out.flush();
		        out.close();
			}           
            status = "Success";
	        
		}catch (Exception e) {
			logger.error("Error in createExelForCBSEExams of Academics service ", e);
    	}		
		return status;
	}

	@Override
	public String downloadExelForUserExam(StudentResult studentResult, String folderPath, String fileName, String loggedInUser) {
		String status = "";
		try {
			List<StudentResult> studentsSubjectsAndMarksList=academicsDAO.getStudentsAndMarksForUserDefinedExams(studentResult);
			if(null!=studentsSubjectsAndMarksList)
				status=createExelForCBSEExams(studentResult, studentsSubjectsAndMarksList, folderPath, fileName);
		} catch (CustomException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public int uploadExelForCBSEExamByExel(String excelFile, String updatedBy) {
		int insertStatus = 0;
		int resultInsertStatus = 0;
		try{
			logger.info("In uploadExelForCBSEExamByExel(String excelFile, String updatedBy) method of Academics service");
			
			List<StudentResult> studentResultList = dataUtility.readExcelFileMarksForCBSEExam(excelFile);
			if(studentResultList.size() != 0){
				resultInsertStatus = dataDAO.batchInsertForStudentResult(studentResultList, updatedBy);				
			}else{
				logger.info("Problem while uploading cbse result.");
				insertStatus = 0;
			}
			if(resultInsertStatus != 0){
				insertStatus = 1;
			}
		}catch(Exception e){
			logger.error("Exception in uploadExelForCBSEExamByExel() to read excel and insert in Academics service", e);
			e.printStackTrace();
		}
		return insertStatus;
	}
	
	
	
	@Override
	public int uploadExelForUserExamByExel(String excelFile, String updatedBy) {
		int insertStatus = 0;
		int resultInsertStatus = 0;
		try{
			logger.info("In uploadExelForUserExamByExel(String excelFile, String updatedBy) method of Academics service");
			
			List<StudentResult> studentResultList = dataUtility.readExcelFileMarksForUserExam(excelFile);
			if(studentResultList.size() != 0){
				resultInsertStatus = dataDAO.batchInsertForStudentResultUserExam(studentResultList, updatedBy);				
			}else{
				logger.info("Problem while uploading cbse result.");
				insertStatus = 0;
			}
			if(resultInsertStatus != 0){
				insertStatus = 1;
			}
		}catch(Exception e){
			logger.error("Exception in uploadExelForUserExamByExel() to read excel and insert in Academics service", e);
			e.printStackTrace();
		}
		return insertStatus;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public int addNewAssetDetails(Asset asset) throws CustomException {
		return academicsDAO.addNewAssetDetails(asset);
	}

	@Override
	public String assetNameValidation(String assetName) throws CustomException {
		return academicsDAO.assetNameValidation(assetName);
	}

	@Override
	public List<Asset> getAssetList() throws CustomException {
		int count = 0;
		List<Asset> assetsList = academicsDAO.getAssetList();
		if(null != assetsList && assetsList.size() != 0){
			assetList = new ArrayList<Asset>();
			for(Asset asst : assetsList){
				if(asst.getDepartment().getDepartmentName().equals("PHYSICS LAB") || asst.getDepartment().getDepartmentName().equals("CHEMISTRY LAB")
					|| asst.getDepartment().getDepartmentName().equals("BIOLOGY LAB") || asst.getDepartment().getDepartmentName().equals("COMPUTER LAB")){
					Asset asset = new Asset();
					asset.setAssetId(asst.getAssetId());
					asset.setAssetName(asst.getAssetName());
					asset.setAssetPrice(asst.getAssetPrice());
					asset.setPurchaseDate(asst.getPurchaseDate());
					asset.setAssetType(asst.getAssetType());
					asset.setAssetSubType(asst.getAssetSubType());
					Department dept = new Department();
					dept.setDepartmentName(asst.getDepartment().getDepartmentName());
					asset.setDepartment(dept);
					assetList.add(asset);
					count++;
				}
			}
		}
		if(!(count > 0)){
			assetList = null;
		}
		System.out.println("count => "+count);
		return assetList;
	}

	@Override
	public PagedListHolder<Asset> getAssetListPaging(HttpServletRequest request) throws CustomException {
		PagedListHolder<Asset> assetPagedListHolder = null;
		try{
			if(null != assetList && assetList.size()!=0){
				assetPagedListHolder = new PagedListHolder<Asset>(assetList);
				int page = ServletRequestUtils.getIntParameter(request, "p", 0);
				assetPagedListHolder.setPage(page);
				assetPagedListHolder.setPageSize(10);
				assetPagedListHolder.setMaxLinkedPages(5);
			}
		}catch(Exception e){
			logger.error("exception in getAssetListPaging() method InventoryServiceImpl ", e);
		}
		return assetPagedListHolder;
	}

	@Override
	public void getSearchAssetList(Map<String, Object> parameters) throws CustomException {
		int count = 0;
		List<Asset> assetsList = academicsDAO.getSearchAssetList(parameters);
		if(null != assetsList && assetsList.size() != 0){
			assetList = new ArrayList<Asset>();
			for(Asset asst : assetsList){
				if(asst.getDepartment().getDepartmentName().equals("PHYSICS LAB") || asst.getDepartment().getDepartmentName().equals("CHEMISTRY LAB")
					|| asst.getDepartment().getDepartmentName().equals("BIOLOGY LAB") || asst.getDepartment().getDepartmentName().equals("COMPUTER LAB")){
					Asset asset = new Asset();
					asset.setAssetId(asst.getAssetId());
					asset.setAssetName(asst.getAssetName());
					asset.setAssetPrice(asst.getAssetPrice());
					asset.setPurchaseDate(asst.getPurchaseDate());
					Department dept = new Department();
					dept.setDepartmentName(asst.getDepartment().getDepartmentName());
					asset.setDepartment(dept);
					assetList.add(asset);
					count++;
				}
			}
		}
		if(!(count > 0)){
			assetList = null;
		}
	}

	@Override
	public Asset getAssetDetails(String assetId) throws CustomException {
		return academicsDAO.getAssetDetails(assetId);
	}

	@Override
	public int updateAssetDetails(Asset asset) throws CustomException {
		return academicsDAO.updateAssetDetails(asset);
	}


	@Override
	public List<Department> getAllDepartment() throws CustomException {
		return academicsDAO.getAllDepartment();
	}
	
	
	@Override
	public String resetStudentResult(StudentResult studentResult) throws CustomException {
		return academicsDAO.resetStudentResult(studentResult);
	}
	
	
	@Override
	public String getExamForStandard(String standard) throws CustomException {
		String exam = "";
		List<Exam> examList = academicsDAO.getExamForStandard(standard);
		if(null != examList && examList.size() != 0){
			for(Exam e : examList){
				exam = exam+e.getExamCode()+"~"+e.getExamName()+"*";
			}
		}
		return exam;
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String submitAssetConsumption(AssetConsumption assetConsumption) throws CustomException {
		return academicsDAO.submitAssetConsumption(assetConsumption);
	}

	@Override
	public void getAssetConsumptionList(AssetConsumption assetConsumption) throws CustomException {
		assetConsumptionList = academicsDAO.getAssetConsumptionList(assetConsumption);
	}
	
	@Override
	public PagedListHolder<AssetConsumption> getAssetConsumptionListPaging(HttpServletRequest request) throws CustomException {
		PagedListHolder<AssetConsumption> assetConsumptionPagedListHolder = null;
		try{
			if(null != assetConsumptionList && assetConsumptionList.size()!=0){
				assetConsumptionPagedListHolder = new PagedListHolder<AssetConsumption>(assetConsumptionList);
				int page = ServletRequestUtils.getIntParameter(request, "p", 0);
				assetConsumptionPagedListHolder.setPage(page);
				assetConsumptionPagedListHolder.setPageSize(2);
				assetConsumptionPagedListHolder.setMaxLinkedPages(5);
			}
		}catch(Exception e){
			logger.error("Exception in getAssetConsumptionListPaging() method AcademicsServiceImpl ", e);
		}
		return assetConsumptionPagedListHolder;
	}

	@Override
	public AssetConsumption getAssetCurrentQuantity(int assetId) throws CustomException {
		return academicsDAO.getAssetCurrentQuantity(assetId);
	}
	

	@Override
	public List<CourseType> getCourseTypeList() throws CustomException {
		return academicsDAO.getCourseTypeList();
	}

	@Override
	public String createCourseType(CourseType courseType) throws CustomException {
		return academicsDAO.createCourseType(courseType);
	}

	@Override
	public String editCourseType(CourseType courseType) throws CustomException {
		return academicsDAO.editCourseType(courseType);
	}

	@Override
	public String createCourse(Course course) throws CustomException {
		return academicsDAO.createCourse(course);
	}

	@Override
	public List<Course> getCourseList() throws CustomException {
		return academicsDAO.getCourseList();
	}

	

	@Override
	public String getStudentsNameAndRollForCourse(String course) throws CustomException {
		String studentNameAndRoll="";
		List<Student> studentList=academicsDAO.getStudentsNameAndRollForCourse(course);
		if(null != studentList && studentList.size()!=0){
			for(Student s:studentList){
				studentNameAndRoll=studentNameAndRoll+s.getRoll()+"*"+s.getStudentName()+"*"+s.getUserId()+"~";
			}
		}
		return studentNameAndRoll;
	}

	@Override
	public String createStudentCourseSubjectMapping(List<StudentCourseSubjectMapping> scsmList) throws CustomException {
		return academicsDAO.createStudentCourseSubjectMapping(scsmList);
	}

	
	@Override
	public String getSubjectsMappedStudents(String course) throws CustomException {
		String studentNameAndRoll="";
		List<Student> studentList=academicsDAO.getSubjectsMappedStudents(course);
		if(null != studentList && studentList.size()!=0){
			for(Student s:studentList){
				studentNameAndRoll=studentNameAndRoll+s.getRoll()+"*"+s.getStudentName()+"*"+s.getUserId()+"~";
			}
		}
		return studentNameAndRoll;
	}
	
	@Override
	public String getSubjectsStudiedByStudentInCourse(StudentCourseSubjectMapping scsm) throws CustomException {
		return academicsDAO.getSubjectsStudiedByStudentInCourse(scsm);
	}

	

	

	@Override
	public String getExamsForCourse(String course) throws CustomException {
		String examString="";
		List<String> examList=academicsDAO.getExamsForCourse(course);
		if(null != examList && examList.size()!=0){
			for(String s:examList){
				examString=examString+s+"~";
			}
		}
		return examString;
	}

	@Override
	public String editCourse(AdmissionForm admissionForm) throws CustomException {
		return academicsDAO.editCourse(admissionForm);
	}
	//Modified by 17062017
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String saveCourse(AdmissionForm admissionForm, String updatedBy) throws CustomException {
		String saveStatusForCourse = "failed";
		try {
			logger.info("saveCourse(AdmissionForm admissionForm) method in AcademicsServiceImpl: ");
			int insertStatusForCourse = academicsDAO.saveCourse(admissionForm, updatedBy);
			if (insertStatusForCourse != 0) {
				saveStatusForCourse = "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in saveCourse() method in AcademicsServiceImpl:", e);
		}
		return saveStatusForCourse;
	}

	@Override
	public List<AdmissionForm> getAllCourseList() throws CustomException {
		logger.info("getCourseList() method in AcademicsServiceImpl: ");
		List<AdmissionForm>  admissionFormList = academicsDAO.getAllCourseList();
		return admissionFormList;
	}

	@Override
	public List<Student> getAllStudentsList() throws CustomException {
		 List<Student> studentList = null;
		studentList = academicsDAO.getAllStudentsList();
		return studentList;
	}
	
	@Override
	public List<Subject> getSubjectFromStandardTeacherSubjectMapping(TeacherSubjectMapping teacherSubjectMapping) {
		// TODO Auto-generated method stub
		return academicsDAO.getSubjectFromStandardTeacherSubjectMapping(teacherSubjectMapping);
	}
	
	@Override
	public List<ExamType> getExamType() {
		return academicsDAO.getExamType();
	}

	@Override
	public List<ExamType> saveExamType(ArrayList<ExamType> examTypeList) {
		return academicsDAO.saveExamType(examTypeList);
	}

	@Override
	public List<Course> getAllCourseForStandard(String string) throws CustomException {
		return commonDAO.getCourseForStandard(string);
	}

	@Override
	public String saveExam(ArrayList<Exam> examList)throws CustomException {
		/*List<Exam> examListDB = academicsDAO.saveExam(examList);
		for (Exam exam : examListDB) {
			String courseStr = "";
			Course courseObj = new Course();
			List<Course> courseList = exam.getCourseList();
			for (Course course : courseList) {
				if (course.getCourseName() != null) {
					courseStr = courseStr + "," + course.getCourseName();
				}
			}
			courseObj.setCourseName(courseStr);
			exam.setCourse(courseObj);
		}
		return examListDB;*/
		return academicsDAO.saveExam(examList);
	}

	@Override
	public List<Exam> getExamDetails() {
		return academicsDAO.getExamDetails();
	}

	@Override
	public List<Exam> savePromotionalExam(Exam exam) {
		return academicsDAO.savePromotionalExam(exam);
	}

	@Override
	public List<Exam> getPromotionalExamList() {
		return academicsDAO.getPromotionalExamList();
	}

	@Override
	public String submitEditedPromotionalExam(Exam ex) {
		return academicsDAO.submitEditedPromotionalExam(ex);
	}

	@Override
	public String getTermsForACourse(String course) {
		List<Term>termList = academicsDAO.getTermsForACourse(course);
		String terms = "";
		if(null!=termList && termList.size()!=0){
			for(Term term:termList){
				terms = terms+term.getTermCode()+"#@#"+term.getTermName()+"*~*";
			}
		}

		return terms;
	}

	@Override
	public String editExam(Exam exam) {
		return academicsDAO.editExam(exam);
	}

	@Override
	public String getExamsForTermAndCourse(Exam exam) {
		List<Exam>examList = academicsDAO.getExamsForTermAndCourse(exam);
		String exams = "";
		if(null!=examList && examList.size()!=0){
			for(Exam e:examList){
				exams = exams+e.getExamCode()+"#@#"+e.getExamName()+"*~*";
			}
		}
		return exams;

	}
/************************Added By Naimisha 27022017**************************/
	@Override
	public String getSubjectsForACourseAndTermAndTeacher(Course courseObj) {
		return academicsDAO.getSubjectsForACourseAndTermAndTeacher(courseObj);
	}

@Override
public String getSubjectsAndMarksForCourseExamTerm(String standard, String exam, String term) {
	String examMarksString = "";
	List<ExamMarks> examMarksList = academicsDAO.getSubjectsAndMarksForCourseExamTerm(standard, exam, term);
	if(null != examMarksList && examMarksList.size() != 0){
		for(ExamMarks examMarks : examMarksList){
			examMarksString = examMarksString+
					examMarks.getSubject().getSubjectCode()+"*~*"+
					examMarks.getSubject().getSubjectName()+"*~*"+
					examMarks.getExam().getExamCode()+"*~*"+
					examMarks.getExam().getExamName()+"*~*"+
					examMarks.getTheory()+"*~*"+
					examMarks.getPractical()+"*~*"+
					examMarks.getTotal()+"*~*"+
					examMarks.getPass()+"*~*"+
					examMarks.getTheoryPass()+"*~*"+
					examMarks.getPracticalPass()+"###";
		}
	}
	return examMarksString;

}

/*********Added By Sourav 01032017***********/


@Override
	public AcademicYear getCurrentAcademicYear() throws CustomException {
		return academicsDAO.getCurrentAcademicYear();
	}

	@Override
	public String createTerm(Term term) throws CustomException {
		return academicsDAO.createTerm(term);
	}

	@Override
	public List<Term> getTermList() throws CustomException {
		return academicsDAO.getTermList();
	}

	/*@Override
	public String getTermNameForCourse(String course) throws CustomException {
		List<Term> termList=academicsDAO.getTermNameForCourse(course);
		String terms = "";
		System.out.println("in AcademicsServiceImpl line on 1152");
		System.out.println("size of list : "+termList.size());
		if(null!=termList && termList.size()!=0){
			System.out.println("in AcademicsServiceImpl line no 1153");
			System.out.println("list size : "+termList.size());
			for(Term term:termList){
				terms = terms+term.getTermCode()+"#@#"+term.getTermName()+"*~*";
				System.out.println(terms);
			}
		}
		return terms;

	}
*/
	@Override
	public String editTermCourseSubjectMapping(CourseSubjectMapping courseSubjectMapping) throws CustomException {
		return academicsDAO.editTermCourseSubjectMapping(courseSubjectMapping);
	}

	@Override
	public List<Subject> getSubjectForCourseAndTerm(Term term) throws CustomException {
		List<Subject> subjectList = academicsDAO.getSubjectForCourseAndTerm(term);
		return subjectList;
	}
	/*******************************03032017GFor Aminities Usage Of Student****************/
	@Override
	public String getStudentsForAllProgrammes() {
		String studentString = "";
		try{
			List<Student> studentList = academicsDAO.getStudentsForAllProgrammes();
			//StringBuilder sb = new StringBuilder();
			if(studentList!=null && studentList.size()!=0){				
			for(Student studentObject : studentList){
				studentString = studentString +";"+ studentObject.getRollNumber() +"*"+ studentObject.getStudentName()+"*"+studentObject.getResource().getGender();
			}			
		}else{
			logger.info("Section not found by ajax @ getStudentsForAllProgrammes()In CommonServiceImpl");
		}
			//studentString = (new Gson().toJson(sb.toString()));
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Exception in getStudentsForAllProgrammes() in CommonServiceImpl ", e);
		}
	return studentString;
	}

	@Override
	public Student getAminitiesUsageByStudent(String rollNumber) {
		return academicsDAO.getAminitiesUsageByStudent(rollNumber);
	}
	
	@Override
	public String getStudentsForAllProgrammesAndBatches(Course course) {
		String studentString = "";
		try{
			List<Student> studentList = academicsDAO.getStudentsForAllProgrammesAndBatches(course);
			//StringBuilder sb = new StringBuilder();
			if(studentList!=null && studentList.size()!=0){				
			for(Student studentObject : studentList){
				studentString = studentString +";"+ studentObject.getRollNumber() +"*"+ studentObject.getStudentName()+"*"+studentObject.getResource().getGender();
			}			
		}else{
			logger.info("Section not found by ajax @ getStudentsForAllProgrammes()In CommonServiceImpl");
		}
			//studentString = (new Gson().toJson(sb.toString()));
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Exception in getStudentsForAllProgrammes() in CommonServiceImpl ", e);
		}
	return studentString;
	}
	/************uploadQuestionPaperBySourav 03032017*************/
/*	@Override
	public String getBatchAgainstCourse(String course)  {
		String section = "";
 		try{
 			List<Section> sectionList = commonDao.getBatchAgainstCourse(course);
 			StringBuilder sb = new StringBuilder();
 			if(sectionList!=null && sectionList.size()!=0){				
				for(Section sectionObject : sectionList){
					if (sb.length() != 0) {
						sb.append(";");
				    }
					sb.append(sectionObject.getSectionCode());
					sb.append("*");
					sb.append(sectionObject.getSectionName());
				}
				
			}else{
				logger.info("Batch not found by ajax @ getSectionAgainstStandard()In CommonServiceImpl");
			}
 			section = (new Gson().toJson(sb.toString()));
 		}catch(Exception e){
 			logger.error("Exception in getSectionAgainstStandard() in CommonServiceImpl ", e);
 		}
 		
		return section;
	}

@Override
	public String getSubjectsForCoursesession(String course)  {
		return hostelDAO.getSubjectsForCoursesession(course);
	}

@Override
	public String getExamsForCoursesession(String course)  {
		String examString="";
		List<String> examList=hostelDAO.getExamsForCoursesession(course);
		if(null != examList && examList.size()!=0){
			for(String s:examList){
				examString=examString+s+"~";
			}
		}
		return examString;
	}*/

	@Override
	public String uploadAssignment(StudentResult studentResult) {

		String inStatus = "";
		try{
			/*
			 * used for set upload file  path
			 */		
			//String dynamicSubFolderPath = studentResult.getAcademicYear()+"/"+studentResult.getCourse()+"/"+studentResult.getExam()+"/"+studentResult.getSubject()+"/";
			String dynamicSubFolderPath = studentResult.getAcademicYear()+"/"+studentResult.getSubject()+"/"+studentResult.getTerm()+"/"+studentResult.getUpdatedBy()+"/"+studentResult.getExam()+"/"+studentResult.getCourse()+"/"+studentResult.getBatch()+"/";
			String filePath = studentResult.getUploadFile().getAttachment().getStorageRootPath()+"/"+studentResult.getUploadFile().getAttachment().getFolderName();
				   filePath = filePath+dynamicSubFolderPath;
				System.out.println("filePath==="+filePath);   
			UploadFile uploadFile = studentResult.getUploadFile();		
			if(null != uploadFile){
					/*
					 * this is used to upload Previous Educational doc
					 */
				if(null != uploadFile.getFileData() && !uploadFile.getFileData().isEmpty()){
					for (CommonsMultipartFile file : uploadFile.getFileData()) {
						if(file.getOriginalFilename() != ""){
							fileUploadDownload.fileUpload(filePath, file);							
						}
					}
				}
			}
		}catch(Exception e){
			inStatus = null;
			e.printStackTrace();
			//CustomException.exceptionHandler(e);
		}
		return inStatus;
	
	}

	@Override
	public String getTeacherAgainstCourse(Course course) {
		Employee employee = academicsDAO.getTeacherAgainstCourse(course);
		String employeeDetails = "";
		if(null!=employee){
			employeeDetails = employee.getEmployeeCode()+"~*~"+employee.getEmployeeName();
		}
		return employeeDetails;
	}

	


/*@Override
	public String getTermsForACourse(String course) {
		List<Term>termList = academicsDAO.getTermsForACourse(course);
		String terms = "";
		if(null!=termList && termList.size()!=0){
			for(Term term:termList){
				terms = terms+term.getTermCode()+"#@#"+term.getTermName()+"*~*";
			}
		}

		return terms;
	}*/
	/************uploadQuestionPaperBySourav 03032017*************/
	/***Work From Home****/
	
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String inactiveStandard(Standard standard) {
		return academicsDAO.inactiveStandard(standard);
	}

	@Override
	public String editTerm(Term term) {
		return academicsDAO.editTerm(term);
	}
/*******Kaustav21032017**************/
	@Override
	public String inactiveExam(Exam exam) {
		return academicsDAO.inactiveExam(exam);
	}
	@Override
	public String inactiveTerm(Term term) {
		return academicsDAO.inactiveTerm(term);
	}
	@Override
	public String inactiveDeleteCourse(Course course) {
		return academicsDAO.inactiveDeleteCourse(course);
	}
	
	/**********Added By Sourav 21032017*********/
	@Override
	public String inactiveProgramType(CourseType courseType) {
		return academicsDAO.inactiveProgramType(courseType);
	}
	
	@Override
	public List<Standard> getStandardsForAssignBatch() throws CustomException {
		List<Standard> programList = academicsDAO.getStandardsForAssignBatch();
		return programList;
	}

	@Override
	public List<Subject> getSubjectsForACourseAndTeacher(Course courseObj) {
		return academicsDAO.getSubjectsForACourseAndTeacher(courseObj);
	}
	
	@Override
	public List<Venue> getVenueListForExam() {
		return academicsDAO.getVenueListForExam();
	}

	@Override
	public List<Algorithm> getAllAlgorithmList() {
		return academicsDAO.getAllAlgorithmList();
	}
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String insertExternalExamination(Exam exam) {
		return academicsDAO.insertExternalExamination(exam);
	}
	
	@Override
	public Venue getVenueDetailsAgainstVenueCode(String venueCode) {
		return academicsDAO.getVenueDetailsAgainstVenueCode(venueCode);
	}
	
	@Override
	public List<Exam> getExternalExamList() {
		return academicsDAO.getExternalExamList();
	}

	//Modified by naimisha 21092017
	@Override
	public Exam getVenueDetailsAgainstExam(String exam) {
		return academicsDAO.getVenueDetailsAgainstExam(exam);
	}

	@Override
	public List<Term> getTermListForACourse(String courseCode) {
		List<Term>termList = academicsDAO.getTermsForACourse(courseCode);
		return termList;
	}

	@Override
	public String saveAdmissionDrive(AdmissionForm admissionForm, String updatedBy) {
		
		return academicsDAO.saveAdmissionDrive(admissionForm,updatedBy);
	}

	@Override
	public List<AdmissionForm> getAllCourseDetails() {
		
		return academicsDAO.getAllCourseDetails();
	}

	@Override
	public AdmissionForm getAllCourseDetailsForEdit(String courseDetailsCode) {
		return academicsDAO.getAllCourseDetailsForEdit(courseDetailsCode);
	}

	@Override
	public String updateCourseDetailsForAdmissionDrive(AdmissionForm admissionForm) {
		return academicsDAO.updateCourseDetailsForAdmissionDrive(admissionForm);
	}

	@Override
	public List<Course> getListOfProgramsToPublish() {
		return academicsDAO.getListOfProgramsToPublish();
	}

	@Override
	public List<Course> listOfPublishedProgramsList() {
		return academicsDAO.listOfPublishedProgramsList();
	}

	@Override
	public Course getProgramDriveDetailsForProgram(String courseCode) {
		return academicsDAO.getProgramDriveDetailsForProgram(courseCode);
	}
	
	/**New CBSE System Start**/
	
	/**
	 * @author anup.roy
	 * */
	
	@Override
	public List<ExamType> getExamTypeNew() {
		return academicsDAO.getExamTypeNew();
	}
	
	/**
	 * @author anup.roy
	 * */
	
	@Override
	public List<Course> getAllCourses() {
		return academicsDAO.getAllCourses();
	}
	
	/**
	 * @author anup.roy
	 * */
	@Override
	public List<Term> getAllTermList(){
		return academicsDAO.getAllTermList();
	}
	
	/**
	 * @author anup.roy
	 * */
	
	@Override
	public String getExamTypesForATerm(String term) {
		return academicsDAO.getExamTypesForATerm(term);
	}
	
	/**
	 * @author anup.roy
	 * this method is for getting all exams new*/
	
	@Override
	public List<Exam> getAllNewExams() {
		return academicsDAO.getAllNewExams();
	}
	
	/**
	 * @author anup.roy
	 * */
	
	@Override
	public String submitExamNew(Exam exam) {
		return academicsDAO.submitExamNew(exam);
	}
	
	@Override
	public String getTermForStandard(String standard) {
		String term = "";
		List<Exam> termList = academicsDAO.getTermForStandard(standard);
		if(null != termList && termList.size() != 0){
			for(Exam e : termList){
				term = term+e.getTerm()+"*";
			}
		}
		return term;
	}

	@Override
	public String getExamForStandardAndTerm(Exam examObj) {
		String exam = "";
		List<Exam> examList = academicsDAO.getExamForStandardAndTerm(examObj);
		if(null != examList && examList.size() != 0){
			for(Exam e : examList){
				exam = exam+e.getExamCode()+"~"+e.getExamName()+"*";
			}
		}
		return exam;
	}

	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public String editIntoExamMarks(List<ExamMarks> examMarksList) {
		return academicsDAO.editIntoExamMarks(examMarksList);
	}
	
	@Override
	public String getSubjectsAndMarksForStandardAndExam(String standard, String exam) {
		String examMarksString = "";
		List<ExamMarks> examMarksList = academicsDAO.getSubjectsAndMarksForStandardAndExam(standard, exam);
		if(null != examMarksList && examMarksList.size() != 0){
			for(ExamMarks examMarks : examMarksList){
				examMarksString = examMarksString+
						examMarks.getSubject().getSubjectCode()+"*~*"+
						examMarks.getSubject().getSubjectName()+"*~*"+
						examMarks.getExam().getExamCode()+"*~*"+
						examMarks.getExam().getExamName()+"*~*"+
						examMarks.getTheory()+"*~*"+
						examMarks.getPractical()+"*~*"+
						examMarks.getTotal()+"*~*"+
						examMarks.getPass()+"*~*"+
						examMarks.getTheoryPass()+"*~*"+
						examMarks.getPracticalPass()+"###";
			}
		}
		return examMarksString;
	}
	
	@Override
	public String getExamsForStandard(String standard) throws CustomException {
		String exam = "";
		List<Exam> examList = academicsDAO.getExamsForStandard(standard);
		if(null != examList && examList.size() != 0){
			for(Exam e : examList){
				exam = exam+e.getExamCode()+"~"+e.getExamName()+"*";
			}
		}
		return exam;
	}
	
	@Override
	public String getMarksForStudents(StudentResult studentResult, String loggedInUser) throws CustomException {
		String studentsSubjectsAndMarks = "";
		List<StudentResult> studentsSubjectsAndMarksList = academicsDAO.getMarksForStudents(studentResult, loggedInUser);
		if(null != studentsSubjectsAndMarksList && studentsSubjectsAndMarksList.size() != 0){
			if(studentsSubjectsAndMarksList.get(0).getStatus().equalsIgnoreCase("INSERT")){
				for(StudentResult studentResultObject : studentsSubjectsAndMarksList){
					studentsSubjectsAndMarks = studentsSubjectsAndMarks+
							studentResultObject.getRollNumber()+"("+studentResultObject.getName()+")*~*"+
							studentResultObject.getTheory()+"*~*"+
							studentResultObject.getPractical()+"*~*"+
							studentResultObject.getTotal()+"*~*"+
							studentResultObject.getPass()+"*~*"+
							studentResultObject.getTheoryPass()+"*~*"+
							studentResultObject.getPracticalPass()+"*~*0*~*0*~*0*~*FAIL"+"###";
				}
				studentsSubjectsAndMarks = studentsSubjectsAndMarks+"INSERT";
			}else if(studentsSubjectsAndMarksList.get(0).getStatus().equalsIgnoreCase("UPDATE")){
				
				for(StudentResult studentResultObject : studentsSubjectsAndMarksList){
					String theoryObtained,practicalObtained,totalObtained;
					//if(-1 == studentResultObject.getTheoryObtained())
						//theoryObtained = "AB";
					//else
						theoryObtained = studentResultObject.getTheoryObtainedChar().toString();
					
					//if(-1 == studentResultObject.getPracticalObtained())
						//practicalObtained = "AB";
					//else
						practicalObtained = studentResultObject.getPracticalObtainedChar().toString();
					
					//if(-1 == studentResultObject.getTotalObtained())
						//totalObtained = "AB";
					//else
						totalObtained = studentResultObject.getTotalObtainedChar().toString();
					
					studentsSubjectsAndMarks = studentsSubjectsAndMarks+
							studentResultObject.getRollNumber()+"("+studentResultObject.getName()+")*~*"+
							studentResultObject.getTheory()+"*~*"+
							studentResultObject.getPractical()+"*~*"+
							studentResultObject.getTotal()+"*~*"+
							studentResultObject.getPass()+"*~*"+
							studentResultObject.getTheoryPass()+"*~*"+
							studentResultObject.getPracticalPass()+"*~*"+
							theoryObtained+"*~*"+
							practicalObtained+"*~*"+
							totalObtained+"*~*"+
							studentResultObject.getPassFail()+"###";
				}
				studentsSubjectsAndMarks = studentsSubjectsAndMarks+"UPDATE";
			}else{
				for(StudentResult studentResultObject : studentsSubjectsAndMarksList){
					String theoryObtained,practicalObtained,totalObtained;
					//if(-1 == studentResultObject.getTheoryObtained())
						//theoryObtained = "AB";
					//else
						theoryObtained = studentResultObject.getTheoryObtained().toString();
					
					//if(-1 == studentResultObject.getPracticalObtained())
					//	practicalObtained = "AB";
					//else
						practicalObtained = studentResultObject.getPracticalObtained().toString();
					
				//	if(-1 == studentResultObject.getTotalObtained())
					//	totalObtained = "AB";
				//	else
						totalObtained = studentResultObject.getTotalObtained().toString();
					
					studentsSubjectsAndMarks = studentsSubjectsAndMarks+
							studentResultObject.getRollNumber()+"("+studentResultObject.getName()+")*~*"+
							studentResultObject.getTheory()+"*~*"+
							studentResultObject.getPractical()+"*~*"+
							studentResultObject.getTotal()+"*~*"+
							studentResultObject.getPass()+"*~*"+
							studentResultObject.getTheoryPass()+"*~*"+
							studentResultObject.getPracticalPass()+"*~*"+
							theoryObtained+"*~*"+
							practicalObtained+"*~*"+							
							totalObtained+"*~*"+
							studentResultObject.getPassFail()+"###";
				}
				studentsSubjectsAndMarks = studentsSubjectsAndMarks+"NA";
			}
		}
		logger.info(studentsSubjectsAndMarks);
		return studentsSubjectsAndMarks;
	}
	
	@Override
	public List<CoScholasticResult> getStudentsForCoScholasticNew(CoScholasticResult coScholasticResult)   {
		return academicsDAO.getStudentsForCoScholasticNew(coScholasticResult);
	}
	

	@Override
	public List<DescriptiveIndicatorSkill> getDescriptiveIndicatorHeadListNew()  {
		return academicsDAO.getDescriptiveIndicatorHeadListNew();
	}
	
	@Override
	public String saveCoScholasticResultNew(List<CoScholasticResult> studentList) {
		return academicsDAO.saveCoScholasticResultNew(studentList);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String editGradingSystemNew(List<GradingSystem> gradingSystemList)   {
		return academicsDAO.editGradingSystemNew(gradingSystemList);
	}
	
	@Override
	public String getGradingSystemForStandardNew(String standard)   {
		String gradingSystemString="";
		List<GradingSystem> gradingSystemList=academicsDAO.getGradingSystemForStandardNew(standard);
		if(null!=gradingSystemList && gradingSystemList.size()!=0){
			for(GradingSystem getGradingSystem:gradingSystemList){
				gradingSystemString=gradingSystemString+getGradingSystem.getType()+"*~*"+
						getGradingSystem.getRange()+"*~*"+
						getGradingSystem.getGrade()+"*~*"+"###";
			}
		}
		return gradingSystemString;
	}
	
	@Override
	public String getCoScolasticResultAgainsRollNumberNew(String roll ,String loggedInUser,  String term )  {
		return academicsDAO.getCoScolasticResultAgainsRollNumberNew(roll,loggedInUser,term);
	}
	
	@Override
	public String editStudentResultNew(List<StudentResult> studentResultList, String insertUpdate)   {
		return academicsDAO.editStudentResultNew(studentResultList, insertUpdate);
	}
	//Modified By Naimisha 09082017
	@Override
	public String getMarksForStudentsNew(StudentResult studentResult, String loggedInUser)   {
		String studentsSubjectsAndMarks = "";
		List<StudentResult> studentsSubjectsAndMarksList = academicsDAO.getMarksForStudentsNew(studentResult, loggedInUser);
		if(null != studentsSubjectsAndMarksList && studentsSubjectsAndMarksList.size() != 0){
			if(studentsSubjectsAndMarksList.get(0).getStatus().equalsIgnoreCase("INSERT")){
				for(StudentResult studentResultObject : studentsSubjectsAndMarksList){
					studentsSubjectsAndMarks = studentsSubjectsAndMarks+
							studentResultObject.getRollNumber()+"("+studentResultObject.getName()+")*~*"+
							studentResultObject.getTheory()+"*~*"+
							studentResultObject.getPractical()+"*~*"+
							studentResultObject.getTotal()+"*~*"+
							studentResultObject.getPass()+"*~*"+
							studentResultObject.getTheoryPass()+"*~*"+
							studentResultObject.getPracticalPass()+"*~*0*~*0*~*0*~*FAIL"+"###";
				}
				studentsSubjectsAndMarks = studentsSubjectsAndMarks+"INSERT";
			}else if(studentsSubjectsAndMarksList.get(0).getStatus().equalsIgnoreCase("UPDATE")){
				
				for(StudentResult studentResultObject : studentsSubjectsAndMarksList){
					String theoryObtained,practicalObtained,totalObtained;
					//if(-1 == studentResultObject.getTheoryObtained())
						//theoryObtained = "AB";
					//else
						theoryObtained = studentResultObject.getTheoryObtainedChar();
					
					//if(-1 == studentResultObject.getPracticalObtained())
						//practicalObtained = "AB";
					//else
						practicalObtained = studentResultObject.getPracticalObtainedChar();
					
					//if(-1 == studentResultObject.getTotalObtained())
						//totalObtained = "AB";
					//else
						totalObtained = studentResultObject.getTotalObtainedChar();
					
					studentsSubjectsAndMarks = studentsSubjectsAndMarks+
							studentResultObject.getRollNumber()+"("+studentResultObject.getName()+")*~*"+
							studentResultObject.getTheory()+"*~*"+
							studentResultObject.getPractical()+"*~*"+
							studentResultObject.getTotal()+"*~*"+
							studentResultObject.getPass()+"*~*"+
							studentResultObject.getTheoryPass()+"*~*"+
							studentResultObject.getPracticalPass()+"*~*"+
							theoryObtained+"*~*"+
							practicalObtained+"*~*"+
							totalObtained+"*~*"+
							studentResultObject.getPassFail()+"###";
				}
				studentsSubjectsAndMarks = studentsSubjectsAndMarks+"UPDATE";
			}else{
				for(StudentResult studentResultObject : studentsSubjectsAndMarksList){
					String theoryObtained,practicalObtained,totalObtained;
					//if(-1 == studentResultObject.getTheoryObtained())
						//theoryObtained = "AB";
					//else
						theoryObtained = studentResultObject.getTheoryObtainedChar();
					
					//if(-1 == studentResultObject.getPracticalObtained())
					//	practicalObtained = "AB";
					//else
						practicalObtained = studentResultObject.getPracticalObtainedChar();
					
				//	if(-1 == studentResultObject.getTotalObtained())
					//	totalObtained = "AB";
				//	else
						totalObtained = studentResultObject.getTotalObtainedChar();
					
					studentsSubjectsAndMarks = studentsSubjectsAndMarks+
							studentResultObject.getRollNumber()+"("+studentResultObject.getName()+")*~*"+
							studentResultObject.getTheory()+"*~*"+
							studentResultObject.getPractical()+"*~*"+
							studentResultObject.getTotal()+"*~*"+
							studentResultObject.getPass()+"*~*"+
							studentResultObject.getTheoryPass()+"*~*"+
							studentResultObject.getPracticalPass()+"*~*"+
							theoryObtained+"*~*"+
							practicalObtained+"*~*"+							
							totalObtained+"*~*"+
							studentResultObject.getPassFail()+"###";
				}
				studentsSubjectsAndMarks = studentsSubjectsAndMarks+"NA";
			}
		}
		logger.info(studentsSubjectsAndMarks);
		return studentsSubjectsAndMarks;
	}	
	/**New CBSE Sytem end**/
	
	/**
	 * @author anup.roy
	 * this is for getting all scholastic type details
	 * 04.08.2017**/
	@Override
	public List<SubjectGroup> getScholasticTypeList() {
		return academicsDAO.getScholasticTypeList();
	}

	/*Added By ranita.sur on 03082017 for getting the strength of Student*/
	@Override
	public String getStrengthOfStudents(String standard,String section) {
		return academicsDAO.getStrengthOfStudents(standard,section);
	}

	@Override
	public List<Standard> getStandardsForSetExamMarks() {
		return academicsDAO.getStandardsForSetExamMarks();
	}

	
	//Added By Naimisha 21092017
	@Override
	public Exam getPrograamDetailsAgainstExam(String exam) {
		return academicsDAO.getPrograamDetailsAgainstExam(exam);
	}

	@Override
	public List<Student> getStudentsListForCourse(String courseCode) throws CustomException {
		// TODO Auto-generated method stub
		return academicsDAO.getStudentsNameAndRollForCourse(courseCode);
	}
	
	
	//Added By Naimisha 1710217
	@Override
	public List<Student> getStudentListForAProgram(String programCode) {
		return academicsDAO.getStudentListForAProgram(programCode);
	}

	//******Added By Naimisha 16042018******//
	
	@Override
	public String createClassTeacher(Standard standard) {
		return academicsDAO.createClassTeacher(standard);
	}

	@Override
	public List<StudentResult> getAllClassTeacherList() {
		return academicsDAO.getAllClassTeacherList();
	}
	
	@Override
	public String getExamNameFromExamTableTimeGrid(String exam) {
		return academicsDAO.getExamNameFromExamTableTimeGrid(exam);
	}
	
	@Override
	public List<Course> getProgramListAndSemeterForExamRoutine(String exam) {
		return academicsDAO.getProgramListAndSemeterForExamRoutine(exam);
	}
	
	@Override
	public List<Subject> getSubjectForCourseAndTermForExamRoutine(Course course) {
		return academicsDAO.getSubjectForCourseAndTermForExamRoutine(course);
	}
	
	@Override
	public String insertIntoExamTableTimeGrid(List<Course> courseList) {
		return academicsDAO.insertIntoExamTableTimeGrid(courseList);
	}
	
	@Override
	public List<RoutineTableGridData> getAllRoutineTableGridDataAgainstExam(String exam) {
		return academicsDAO.getAllRoutineTableGridDataAgainstExam(exam);
	}
	
	@Override
	public TimeTableGridData getRoutineTableGridData(TimeTableGridData timeTableGridData) {
		return academicsDAO.getRoutineTableGridData(timeTableGridData);
	}

	@Override
	public void updateRoutineTableGridData(TimeTableGridData timeTableGridData) {
		academicsDAO.updateRoutineTableGridData(timeTableGridData);
		
	}

	@Override
	public void insertRoutineTableGridData(TimeTableGridData timeTableGridData) {
		academicsDAO.insertRoutineTableGridData(timeTableGridData);		
	}
	
	@Override
	public String submitEventAchievement(List<EventAchievement> eventAcheivementList){
		return academicsDAO.submitEventAchievement(eventAcheivementList);
	}
	
	@Override
	public SchoolEvent selectEventName(String eventCode){
		return academicsDAO.selectEventName(eventCode);
	}
}
