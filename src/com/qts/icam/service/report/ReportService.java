package com.qts.icam.service.report;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.qts.icam.model.academics.UserDefinedExams;
import com.qts.icam.model.admission.AdmissionForm;
import com.qts.icam.model.backoffice.Exam;
import com.qts.icam.model.common.AcademicYear;
import com.qts.icam.model.common.ChartData;
import com.qts.icam.model.common.Condemnation;
import com.qts.icam.model.common.Section;
import com.qts.icam.model.common.Standard;
import com.qts.icam.model.common.Student;
import com.qts.icam.model.erp.Employee;
import com.qts.icam.model.report.Report;
import com.qts.icam.utility.customexception.CustomException;



public interface ReportService {

	//void createReportForExam(List<StudentResult> studentResultList);

	

	String getStudentReport(Standard standard, Section section,AcademicYear academiYear,
										 Exam exam, String[] roll,String reportResultConfigFilePath,
										 String reportResultPdfFilPath,String reportExamResultChatImagePath, HttpServletResponse response);

	

	

	void createAdmitCardForAdmission(AdmissionForm admitcardGenerateForm, HttpServletResponse response);


	String getStudentAddressDetails(List<Student> studentList,
			String reportResultConfigFilePath, String reportResultPdfFilPath, HttpServletResponse response);

	public String generateMeritListForAdmisionReport(Report report,String reportResultConfigFilePath, String reportResultPdfFilPath, HttpServletResponse response);

	public String generateExamVenueWiseCandidatesReport(Report report,String reportResultConfigFilePath, String reportResultPdfFilPath, HttpServletResponse response);
	
	public String generateSocialCategoryWiseClassStrengthReport(Report report,String reportResultConfigFilePath, String reportResultPdfFilPath, HttpServletResponse response);

	

	String getConsolidatedResultReport(Standard standard, Section section,AcademicYear academicYear, Exam exam,
			String reportResultConfigFilePath, String reportResultPdfFilPath,String excel, String studentMarkSheetExcel,HttpServletResponse response);

	String getStudentNominalRoll(Student student, String reportResultConfigFilePath, String reportResultPdfFilPath, String excel, String nominalRollExcel, HttpServletResponse response);

	String getStaffDetailsList(Employee employee, String reportResultConfigFilePath, String reportResultPdfFilPath, String excel, String staffExcel, HttpServletResponse response);





	String showTCReport(String rollNumber, String reportResultConfigFilePath,
			String reportResultPdfFilPath, HttpServletResponse response);





	String getConsolidatedResultForXII_XII(Standard standard, Section section, AcademicYear academicYear, Exam exam,
			String reportResultConfigFilePath, String reportResultPdfFilPath, HttpServletResponse response);



	String getStudentReportFORXI_XII(Standard standard, Section section,
			AcademicYear academicYear, Exam exam, String[] roll,
			String reportResultConfigFilePath, String reportResultPdfFilPath,
			String string, HttpServletResponse response);
	
	public List<UserDefinedExams> getUserExamsForStandard(String standard);





	public String getStudentSubjectMapping(Student studentObject, String reportResultConfigFilePath, String reportResultPdfFilPath, HttpServletResponse response);
	
	
	public ChartData loadChart1Data(String role, String module, String reportQueryPath);
	
	public ChartData loadChart2Data(String role, String module, String reportQueryPath);





	public String getStudentCertificate(Standard standard, Section section, AcademicYear academicYear, Exam exam,
			String[] roll, String reportResultConfigFilePath, String reportResultPdfFilPath, String string,
			HttpServletResponse response);

	/**New CBSE System start**/
	
	public String getStudentsAgainstStandardAndSectionForNewReport(String standard, String section)throws CustomException;

	public String getStudentReportNew(Standard standard, Section section, AcademicYear academicYear, Exam exam, String[] roll,
			String reportResultConfigFilePath, String reportResultPdfFilPath, String string,
			HttpServletResponse response);

	public String getConsolidatedResultReportNew(Standard standard, Section section, AcademicYear academicYear, Exam exam,
			String reportResultConfigFilePath, String reportResultPdfFilPath, String excel,
			String studentMarkSheetExcel, HttpServletResponse response);
	
	/**New CBSE System end**/
	//Added By Naimisha 04102017

	public ChartData loadChart1DataForLibrary(String roleName, String moduleName, String queryPath);

	public ChartData loadChart3DataForLibrary(String roleName, String moduleName, String queryPath);

	public	ChartData loadChart2DataForLibrary(String roleName, String moduleName, String queryPath);

	public ChartData loadChart4DataForLibrary(String roleName, String moduleName, String queryPath);

}
