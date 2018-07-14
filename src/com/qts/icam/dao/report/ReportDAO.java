package com.qts.icam.dao.report;

import java.util.List;
import java.util.Map;

import com.qts.icam.model.academics.CoScholasticResult;
import com.qts.icam.model.academics.StudentResult;
import com.qts.icam.model.academics.SubjectGroup;
import com.qts.icam.model.academics.UserDefinedExams;
import com.qts.icam.model.backoffice.Exam;
import com.qts.icam.model.common.AcademicYear;
import com.qts.icam.model.common.Candidate;
import com.qts.icam.model.common.ChartValuesModel;
import com.qts.icam.model.common.Condemnation;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.Section;
import com.qts.icam.model.common.Standard;
import com.qts.icam.model.common.Student;
import com.qts.icam.model.erp.Employee;
import com.qts.icam.model.inventory.Commodity;
import com.qts.icam.model.report.Report;

public interface ReportDAO {

	List<Student> getStudentsSubjectsAndMarksReportForCurrentAcademicYear(StudentResult studentResult);
	List<StudentResult> getStudentsSubjectsAndMarksReportChartForCurrentAcademicYear(
			StudentResult studentResult);
	List<Standard> getStudentNominalRoll(Student student);
	List<Employee> getStaffDetailsList(Employee employee);
	List<CoScholasticResult> getCoScholasticResultList(Student student);
	void deleteTempTableReportData();
	Condemnation getCondemnationReport(Condemnation condemnation);
	List<SubjectGroup> getSubjectGroup(StudentResult studentResult);
	List<Student> getConsolidatedReportForExamForCurrentAcademicYear(StudentResult studentResult);
	List<Student> getStudentAddressDetails(List<Student> studentList);
	List<Commodity> createDemandVoucherReport(Report report);
	public List<Candidate> getgenerateMeritListForAdmisionReport(Report report);
	public List<Candidate> generateExamVenueWiseCandidatesReport(Report report);
	public List<Standard> getSocialCategoryWiseClassStrengthReportData(Report report);
	Student showTCReport(String rollNumber);
	List<SubjectGroup> getSubjectGroupForXI_XIIResult(StudentResult studentResult);
	List<Student> getConsolidatedReportForXI_XIIForCurrentYear(StudentResult studentResult);
	List<Student> getcreateReportForExamXI_XII(StudentResult studentResult);
	
	public List<UserDefinedExams> getUserDefinedExamsForStandard(String standard);
	public List<SubjectGroup> getSubjectGroupForInternalExamination(StudentResult studentResult);
	public List<Student> getStudentListForInternalExamination(StudentResult studentResult);
	
	public List<Student> getStudentListForCentralised(StudentResult studentResult);
	public int getTotalStudentCount(StudentResult studentResult);
	public List<Student> getRollNoWiseStudentList(StudentResult studentResult);
	
	public List<ChartValuesModel> loadChartData(String sql);
	
	/**New CBSE System start**/
	
	public List<Student> getStudentsAgainstStandardAndSectionForNewReport(Student student);
	
	public List<CoScholasticResult> getCoScholasticResultListNew(Student student);
	
	public List<Student> getConsolidatedReportForExamForCurrentAcademicYearNew(StudentResult studentResult);
	
	public List<SubjectGroup> getSubjectGroupFromStudentMarksV1(StudentResult studentResult);
	
	public List<Student> getStudentListForConsolidateCentralisedNew(StudentResult studentResult);
	
	public List<SubjectGroup> getSubjectGroupForInternalExaminationNew(StudentResult studentResult);
	
	public List<Student> getStudentListForInternalExaminationNew(StudentResult studentResult);
	
	public List<Student> getStudentsSubjectsAndMarksReportForCurrentAcademicYearNew(StudentResult studentResult);
	
	public List<Student> getStudentResultRawDataForNewCBSEPattern(StudentResult studentResult);
	
	public List<StudentResult> selectGradeForSubjectTotal(Student student);
	
	public List<Student> getStudentResultRawDataForNewCBSEPatternNew(StudentResult studentResult);

	/**New CBSE System end**/

	//Added By Naimisha 04102017
	public List<ChartValuesModel> loadChart1DataForLibrary(String sql);
	//public List<ChartValuesModel> loadChart3DataForLibrary(String sql);

}
