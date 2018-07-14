package com.qts.icam.dao.impl.report;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.qts.icam.dao.report.ReportDAO;
import com.qts.icam.model.academics.CoScholasticResult;
import com.qts.icam.model.academics.StudentResult;
import com.qts.icam.model.academics.SubjectGroup;
import com.qts.icam.model.academics.UserDefinedExams;
import com.qts.icam.model.backoffice.Exam;
import com.qts.icam.model.common.AcademicYear;
import com.qts.icam.model.common.Candidate;
import com.qts.icam.model.common.ChartQueryData;
import com.qts.icam.model.common.ChartValuesModel;
import com.qts.icam.model.common.Condemnation;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.Section;
import com.qts.icam.model.common.Standard;
import com.qts.icam.model.common.Student;
import com.qts.icam.model.erp.Employee;
import com.qts.icam.model.inventory.Commodity;
import com.qts.icam.model.report.Report;
import com.qts.icam.utility.date.DateUtility;
import com.qts.icam.utility.encryptdecrypt.EncryptDecrypt;

@Repository
public class ReportDAOImpl implements ReportDAO {

private final static Logger logger = Logger.getLogger(ReportDAOImpl.class);
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Autowired
	DateUtility dateUtility;
	
	@Autowired
	EncryptDecrypt encryptDecrypt;


	public List<Student> getStudentsSubjectsAndMarksReportForCurrentAcademicYear(StudentResult studentResult) {
		SqlSession session = sqlSessionFactory.openSession();
		List<Student> studentList = null;
		try{
			if(null != studentResult && null != studentResult.getExam()){
				if(studentResult.getExam().equals("FA1") || studentResult.getExam().equals("FA2") || studentResult.getExam().equals("FA3") || studentResult.getExam().equals("FA4")){
					//studentResultList = session.selectList("selectStudentsSubjectsAndMarksReportForCurrentAcademicYearFA",studentResult);
				}
				if(studentResult.getExam().equals("Term_1")||studentResult.getExam().equals("M1")||studentResult.getExam().equals("M2") || studentResult.getExam().equals("PC")){
					int insertStatus = session.insert("insertResultsIntoTempTableForReportForXI",studentResult);
					studentList = new ArrayList<Student>();
					for(String studentRoll : studentResult.getStringList()){
						logger.info("### Student :: "+studentRoll);
						Student stu = session.selectOne("selectStudentsSubjectsAndMarksReportForCurrentAcademicYearXI", studentRoll);
						studentList.add(stu);
					}
					session.commit();			
				}								
				if(studentResult.getExam().equals("SA1")){
					logger.info("SA1 : REQUESTED");
					//studentList = session.selectList("selectStudentsSubjectsAndMarksReportForCurrentAcademicYearSA1",studentResult);
					int insertStatus = session.insert("insertResultsIntoTempTableForReportForSA1", studentResult);
					studentList = session.selectList("selectStudentsSubjectsAndMarksReportForCurrentAcademicYearSA1");			
					session.commit();
				}
				if(studentResult.getExam().equals("SA2")){
					logger.info("SA2: REQUESTED");				
					int insertStatus = session.insert("insertResultsIntoTempTableForReport", studentResult);
					studentList = session.selectList("selectStudentsSubjectsAndMarksReportForCurrentAcademicYearSA2");
					session.commit();
				}
			}
			//System.out.println("Vinod 3 :: "+studentList.size());
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		} finally {
			session.close();
		}
		return studentList;
	}


@Override
public List<StudentResult> getStudentsSubjectsAndMarksReportChartForCurrentAcademicYear(StudentResult studentResult) {
	SqlSession session =sqlSessionFactory.openSession();
	List<StudentResult> studentResultListForChart=null;
	try{
		if(studentResult.getExam().equals("FA1") || studentResult.getExam().equals("FA2") || studentResult.getExam().equals("FA3") || studentResult.getExam().equals("FA4")){
			studentResultListForChart = session.selectList("selectStudentsSubjectsAndMarksChartReportForCurrentAcademicYearFA",studentResult);
		}
		if(studentResult.getExam().equals("SA1")){
			logger.info(" aaaaaaaaaaaaaaaaaaaaaa "+studentResult.getStandard());
			studentResultListForChart = session.selectList("selectStudentsSubjectsAndMarksChartReportForCurrentAcademicYearSA1",studentResult);
			session.commit();
		}
		if(studentResult.getExam().equals("SA2")){
			studentResultListForChart = session.selectList("selectStudentsSubjectsAndMarksChartReportForCurrentAcademicYearSA2",studentResult);
			session.commit();
		}
	}catch (Exception e) {
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		logger.error(e);
	} finally {
		session.close();
	}
	return studentResultListForChart;
}

@Override
public void deleteTempTableReportData() {
	SqlSession session =sqlSessionFactory.openSession();
	try{
		session.delete("deleteTempTableReportSA1Data");
		session.delete("deleteTempTableReportSA2Data");
		session.delete("deleteTempTableChartSa1Data");
		session.delete("deleteTempTableChartSa2Data");
		session.commit();
	}catch (Exception e) {
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		logger.error(e);
	} finally {
		session.close();
	}
}

	@Override
	public List<Standard> getStudentNominalRoll(Student student) {
		SqlSession session =sqlSessionFactory.openSession();
		List<Standard> standardList=null;
		try{
			standardList = session.selectList("selectStudentNominalRoll",student);
		}catch (Exception e) {
			logger.error(e);
		} finally {
			session.close();
		}
		return standardList;
	}


	@Override
	public List<Employee> getStaffDetailsList(Employee employee) {
		SqlSession session =sqlSessionFactory.openSession();
		List<Employee>employeeList=null;
		try{
			logger.info(employee.getResource().getResourceType().getResourceTypeCode());
			employeeList = session.selectList("selectStaffDetailsList",employee);
			//System.out.println("employeeList size=="+employeeList.size());
		}catch (Exception e) {
			logger.error(e);
		} finally {
			session.close();
		}
		return employeeList;
	}



	
	@Override
	public List<CoScholasticResult> getCoScholasticResultList(Student student) {
		SqlSession session =sqlSessionFactory.openSession();
		List<CoScholasticResult> coScholasticResultList=null;
		try{			
			coScholasticResultList = session.selectList("selectCoScholasticResultListForReport",student);			
		}catch (Exception e) {
			logger.error(e);
		} finally {
			session.close();
		}
		return coScholasticResultList;
	}


	@Override
	public Condemnation getCondemnationReport(Condemnation condemnation) {
		SqlSession session =sqlSessionFactory.openSession();
		Condemnation condemnationReport=null;
		try{			
			condemnationReport = session.selectOne("selectCondemnationReport",condemnation);			
		}catch (Exception e) {
			logger.error(e);
		} finally {
			session.close();
		}
		return condemnationReport;
	}

	
	@Override
	public List<Student> getConsolidatedReportForExamForCurrentAcademicYear(StudentResult studentResult) {
		SqlSession session = sqlSessionFactory.openSession();
		List<Student> studentList = null;
		try{	
			//call for class & sub mapping
			if(null != studentResult && null != studentResult.getExam() && studentResult.getExam().equals("SA1")){
				int insertDtatus = session.insert("insertIntoTempConsolidatedReportCurrentAcademicYearSA1",studentResult);	
				if(insertDtatus != 0){
					studentList = session.selectList("selectConsolidatedReportForExamForCurrentAcademicYearSA1");
					//System.out.println("******************* IN SA1 **********************");
					if(null != studentResult.getStatus() && studentResult.getStatus().equalsIgnoreCase("grade")){
						if(null != studentList && studentList.size()!=0){
							for(Student student : studentList){
								if(null != student.getStudentResultList() && student.getStudentResultList().size()!=0){
									for(StudentResult sr : student.getStudentResultList()){
										sr.setStandard(studentResult.getStandard());
										String grade = null;
										
										if(sr.getFa1() == -1){
											sr.setStrFa1("AB");
										}else{
											sr.setTheoryObtained(sr.getFa1().intValue());
											grade = session.selectOne("selectGradeForGradePoint",sr);
											sr.setStrFa1(grade);
										}
										
										
										if(sr.getFa2() == -1){
											sr.setStrFa2("AB");
										}else{
//											sr.setFa2(0.0);
											sr.setTheoryObtained(sr.getFa2().intValue());
											grade = session.selectOne("selectGradeForGradePoint",sr);	
											sr.setStrFa2(grade);
										}
										
										if(sr.getSa1() == -1){
											sr.setStrSa1("AB");
										}else{
//											sr.setSa1(0.0);
											sr.setTheoryObtained(sr.getSa1().intValue());
											grade = session.selectOne("selectGradeForGradePoint",sr);
											sr.setStrSa1(grade);
										}
										
										if(sr.getGradepoint() == -1){
											sr.setStrGradepoint("AB");
										}else{
//											sr.setGradepoint(0.0);
											sr.setTheoryObtained(sr.getGradepoint().intValue());
											grade = session.selectOne("selectGradeForGradePoint",sr);
											sr.setStrGradepoint(grade);
										}
										
										if(sr.getTotalTerm1() == null){										
											sr.setStrTotalTerm1("AB");
										}else{
//											sr.setTotalTerm1(0.0);
											sr.setTheoryObtained(sr.getTotalTerm1().intValue());
											grade = session.selectOne("selectGradeForGradePoint",sr);
											sr.setStrTotalTerm1(grade);
										}
									}
								}
							}
						}
					}else{
						if(null != studentList && studentList.size()!=0){
							for(Student student : studentList){
								if(null != student.getStudentResultList() && student.getStudentResultList().size()!=0){
									for(StudentResult sr : student.getStudentResultList()){
										sr.setStandard(studentResult.getStandard());
										String grade = null;
										
										if(sr.getFa1() == null){
											sr.setStrFa1(null);
										}else if(sr.getFa1() == -1){
											sr.setStrFa1("AB");
										}else{
											sr.setTheoryObtained(sr.getFa1().intValue());
											grade = session.selectOne("selectGradeForGradePoint",sr);
											sr.setStrFa1(grade);
										}
										
										
										if(sr.getFa2() == null){
											sr.setStrFa2(null);
										}else if(sr.getFa2() == -1){
											sr.setStrFa2("AB");
										}else{
//											sr.setFa2(0.0);
											sr.setTheoryObtained(sr.getFa2().intValue());
											grade = session.selectOne("selectGradeForGradePoint",sr);	
											sr.setStrFa2(grade);
										}
										
										if(sr.getSa1() == null){
											sr.setStrSa1(null);
										}else if(sr.getSa1() == -1){
											sr.setStrSa1("AB");
										}else{
//											sr.setSa1(0.0);
											sr.setTheoryObtained(sr.getSa1().intValue());
											grade = session.selectOne("selectGradeForGradePoint",sr);
											sr.setStrSa1(grade);
										}
										
										if(sr.getGradepoint() == -1){
											sr.setStrGradepoint("AB");
										}else{
//											sr.setGradepoint(0.0);
											sr.setTheoryObtained(sr.getGradepoint().intValue());
											grade = session.selectOne("selectGradeForGradePoint",sr);
											sr.setStrGradepoint(grade);
										}
										
										if(sr.getTotalTerm1() == null){									
											sr.setStrTotalTerm1("AB");
										}else{
//											sr.setTotalTerm1(0.0);
											sr.setTheoryObtained(sr.getTotalTerm1().intValue());
											grade = session.selectOne("selectGradeForGradePoint",sr);
											sr.setStrTotalTerm1(grade);
										}
										
										if(sr.getGrade().equals("-1")){
											sr.setGrade("AB");
										}
									}
								}
							}
						}
					
					}
					
				}else{					
					//System.out.println("Not Inserted");
				}
				session.delete("deleteTempTableConsolidatedDataSA1");
				session.commit();
			}
			if(null != studentResult && null != studentResult.getExam() && studentResult.getExam().equals("SA2")){
				int insertDtatus = session.insert("insertIntoTempConsolidatedReportCurrentAcademicYearSA2",studentResult);	
				if(insertDtatus != 0){
					studentList = session.selectList("selectConsolidatedReportForExamForCurrentAcademicYearSA2");
				
					if(null != studentResult.getStatus() && studentResult.getStatus().equals("grade")){
						if(null != studentList && studentList.size()!=0){
							for(Student student : studentList){
								if(null != student.getStudentResultList() && student.getStudentResultList().size()!=0){
									if(student.getCgpa()==null)
										student.setCgpa(0.0);
									StudentResult sr1 = new StudentResult();
									sr1.setTheoryObtained(student.getCgpa().intValue());
									sr1.setStandard(studentResult.getStandard());
									String grade1 = session.selectOne("selectGradeForGradePoint",sr1);
									student.setStrCgpa(grade1);
									for(StudentResult sr : student.getStudentResultList()){
										sr.setStandard(studentResult.getStandard());
										String grade = null;
										
										if(sr.getT1() == -1){
											sr.setStrT1("AB");
										}else{
//											if(sr.getT1()==null)
//											sr.setT1(0.0);
											sr.setTheoryObtained(sr.getT1().intValue());
											grade = session.selectOne("selectGradeForGradePoint",sr);
											sr.setStrT1(grade);
										}
										
										if(sr.getFa3() == -1){
											sr.setStrFa3("AB");
										}else{
//											if(sr.getFa3()==null)
//											sr.setFa3(0.0);
											sr.setTheoryObtained(sr.getFa3().intValue());
											grade = session.selectOne("selectGradeForGradePoint",sr);	
											sr.setStrFa3(grade);
										}
										
										if(sr.getFa4() == -1){
											sr.setStrFa4("AB");
										}else{
//											if(sr.getFa4()==null)
//											sr.setFa4(0.0);
											sr.setTheoryObtained(sr.getFa4().intValue());
											grade = session.selectOne("selectGradeForGradePoint",sr);
											sr.setStrFa4(grade);
										}
										
										if(sr.getSa2() == -1){
											sr.setStrSa2("AB");
										}else{
//											if(sr.getSa2()==null)
//											sr.setSa2(0.0);
											sr.setTheoryObtained(sr.getSa2().intValue());
											grade = session.selectOne("selectGradeForGradePoint",sr);
											sr.setStrSa2(grade);
										}
										
										if(sr.getTotalTerm1() == null){										
											sr.setStrTotalTerm1("AB");
										}else{
//											sr.setTotalTerm1(0.0);
											sr.setTheoryObtained(sr.getTotalTerm1().intValue());
											grade = session.selectOne("selectGradeForGradePoint",sr);
											sr.setStrTotalTerm1(grade);
										}
									}
								}
							}
						}
					}else{
						if(null != studentList && studentList.size()!=0){
							for(Student student : studentList){
								if(null != student.getStudentResultList() && student.getStudentResultList().size()!=0){
									if(student.getCgpa()==null)
										student.setCgpa(0.0);
									StudentResult sr1 = new StudentResult();
									sr1.setTheoryObtained(student.getCgpa().intValue());
									sr1.setStandard(studentResult.getStandard());
									String grade1 = session.selectOne("selectGradeForGradePoint",sr1);
									student.setStrCgpa(grade1);
									for(StudentResult sr : student.getStudentResultList()){
										sr.setStandard(studentResult.getStandard());
										String grade = null;
										
										if(sr.getT1() == null){
											sr.setStrT1(null);
										}else if(sr.getT1() == -1){
											sr.setStrT1("AB");
										}else{
//											if(sr.getT1()==null)
//											sr.setT1(0.0);
											sr.setTheoryObtained(sr.getT1().intValue());
											grade = session.selectOne("selectGradeForGradePoint",sr);
											sr.setStrT1(grade);
										}
										
										if(sr.getFa3() == null){
											sr.setStrFa3(null);
										}else if(sr.getFa3() == -1){
											sr.setStrFa3("AB");
										}else{
//											if(sr.getFa3()==null)
//											sr.setFa3(0.0);
											sr.setTheoryObtained(sr.getFa3().intValue());
											grade = session.selectOne("selectGradeForGradePoint",sr);	
											sr.setStrFa3(grade);
										}
										
										if(sr.getFa4() == null){
											sr.setStrFa4(null);
										}else if(sr.getFa4() == -1){
											sr.setStrFa4("AB");
										}else{
//											if(sr.getFa4()==null)
//											sr.setFa4(0.0);
											sr.setTheoryObtained(sr.getFa4().intValue());
											grade = session.selectOne("selectGradeForGradePoint",sr);
											sr.setStrFa4(grade);
										}
										
										if(sr.getSa2() == null){
											sr.setStrSa2(null);
										}else if(sr.getSa2() == -1){
											sr.setStrSa2("AB");
										}else{
//											if(sr.getSa2()==null)
//											sr.setSa2(0.0);
											sr.setTheoryObtained(sr.getSa2().intValue());
											grade = session.selectOne("selectGradeForGradePoint",sr);
											sr.setStrSa2(grade);
										}
										
										if(sr.getTotalTerm1() == null){										
											sr.setStrTotalTerm1("AB");
										}else{
//											sr.setTotalTerm1(0.0);
											sr.setTheoryObtained(sr.getTotalTerm1().intValue());
											grade = session.selectOne("selectGradeForGradePoint",sr);
											sr.setStrTotalTerm1(grade);
										}
									}
								}
							}
						}											
					}
				}
				session.delete("deleteTempTableConsolidatedDataSA2");
				session.commit();
			}
			if(null != studentResult && null != studentResult.getExam() && studentResult.getExam().equals("FA1")||studentResult.getExam().equals("FA2")||studentResult.getExam().equals("FA3")||studentResult.getExam().equals("FA4")){
				int insertDtatus = session.insert("insertIntoTempConsolidatedReportCurrentAcademicYearfa1_fa2_fa3_fa4",studentResult);	
				if(insertDtatus != 0){
					studentList = session.selectList("selectConsolidatedReportForExamForCurrentAcademicYearfa1_fa2_fa3_fa4");
					
					if(null != studentResult.getStatus() && studentResult.getStatus().equalsIgnoreCase("gradepoint")){
						//System.out.println("In gradepoint.....................");
						if(null != studentList && studentList.size()!=0){
							for(Student student : studentList){
								if(null != student.getStudentResultList() && student.getStudentResultList().size()!=0){
									for(StudentResult sr : student.getStudentResultList()){
										sr.setStandard(studentResult.getStandard());
										String grade = null;
										
										if(sr.getFa1()==null){
											sr.setStrFa1("AB");
										}else{
											sr.setTheoryObtained(sr.getFa1().intValue());
											grade = session.selectOne("selectGradeForGradePoint",sr);
											sr.setStrFa1(grade);
										}									
										
										if(sr.getFa2()==null){
											sr.setStrFa2("AB");
										}else{
//											sr.setFa2(0.0);
											sr.setTheoryObtained(sr.getFa2().intValue());
											grade = session.selectOne("selectGradeForGradePoint",sr);	
											sr.setStrFa2(grade);
										}
										
										if(sr.getSa1()==null){
											sr.setStrSa1("AB");
										}else{
//											sr.setSa1(0.0);
											sr.setTheoryObtained(sr.getSa1().intValue());
											grade = session.selectOne("selectGradeForGradePoint",sr);
											sr.setStrSa1(grade);
										}
										
										if(sr.getGradepoint() == null){
											//System.out.println("In getGradepoint null...................");
											sr.setStrGradepoint(null);
										}else if(sr.getGradepoint() == -1){
											//System.out.println("In getGradepoint(-1).....................");
											sr.setStrGradepoint("AB");
										}else{
//											sr.setGradepoint(0.0);
											sr.setTheoryObtained(sr.getGradepoint().intValue());
											grade = session.selectOne("selectGradeForGradePoint",sr);
											sr.setStrGradepoint(grade);
										}
										
										if(sr.getGrade() == null){
											//System.out.println("In getGrade null...................");
											sr.setGrade(null);
										}else if(sr.getGrade().equals("-1")){
											//System.out.println("In getGrade('-1').....................");
											sr.setGrade("AB");
										}
//										else{
//											sr.setTotalTerm1(0.0);
//											sr.setTheoryObtained(sr.getTotalTerm1().intValue());
//											grade = session.selectOne("selectGradeForGradePoint",sr);
//											sr.setStrTotalTerm1(grade);
//										}
									}
								}
							}
						}				
					}
					
				}
				session.delete("deleteTempTableConsolidatedDatafa1_fa2_fa3_fa4");
				session.commit();
			}
			/*Naimisha*/
			if(null != studentResult && null != studentResult.getExam()){
				if(studentResult.getExam().equalsIgnoreCase("Term_1")||studentResult.getExam().equalsIgnoreCase("M1")||studentResult.getExam().equalsIgnoreCase("M2")||studentResult.getExam().equalsIgnoreCase("PC")){
					int insertDtatus = session.insert("insertConsolidatedReportForXIForGeneralStudent", studentResult);	
					studentList = session.selectList("selectConsolidatedReportForXIForGeneralStudent");
				}
				session.commit();
			}			
		}catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return studentList;
	}
	
	
	@Override
	public List<SubjectGroup> getSubjectGroup(StudentResult studentResult) {
		SqlSession session =sqlSessionFactory.openSession();
		List<SubjectGroup> subjectGroupList=null;
		try{	
			subjectGroupList = session.selectList("selectClassWiseSubjectGroups",studentResult);
		}catch (Exception e) {
			logger.error(e);
		} finally {
			session.close();
		}
		return subjectGroupList;
	}


	@Override
	public List<Student> getStudentAddressDetails(List<Student> studentList) {
		SqlSession session =sqlSessionFactory.openSession();
		List<Student> studentAddressList=new ArrayList<Student>();
		try{
			if(null!=studentList && studentList.size()!=0){
				for(Student student:studentList){
					Student studentAddress= new Student();
					studentAddress = session.selectOne("selectStudentAddressDetails",student);
					if(null!=studentAddress){
						studentAddressList.add(studentAddress);
					}
				}
			}		
		}catch (Exception e) {
			logger.error(e);
		} finally {
			session.close();
		}
		return studentAddressList;
	}
	


	@Override
	public List<Commodity> createDemandVoucherReport(Report report) {
		SqlSession session =sqlSessionFactory.openSession();
		List<Commodity> commodtyList=null;
		try{
			commodtyList = session.selectList("selectDemandVoucherReport",report);
		}catch (Exception e) {
			logger.error(e);
		} finally {
			session.close();
		}
		return commodtyList;
	}



	
	@Override
	public List<Candidate> getgenerateMeritListForAdmisionReport(Report report) {
		SqlSession session =sqlSessionFactory.openSession();
		List<Candidate> candidateList=null;
		try{
			candidateList = session.selectList("selectMeritListForAdmisionReport",report);
		}catch (Exception e) {
			logger.error(e);
		} finally {
			session.close();
		}
		return candidateList;
	}
	

	@Override
	public List<Candidate> generateExamVenueWiseCandidatesReport(Report report) {
		SqlSession session =sqlSessionFactory.openSession();
		List<Candidate> candidateList=null;
		try{
			candidateList = session.selectList("selectExamVenueWiseCandidatesReport",report);
		}catch (Exception e) {
			logger.error(e);
		} finally {
			session.close();
		}
		return candidateList;
	}
	
	
	@Override
	public List<Standard> getSocialCategoryWiseClassStrengthReportData(Report report) {
		SqlSession session =sqlSessionFactory.openSession();
		List<Standard> standardList=new ArrayList<Standard>();
		try{
			session.insert("insertIntoTempStudentStrength");
			if(report.getStandardList()!=null && report.getStandardList().size()!=0){
				for(Standard standard : report.getStandardList()){					
					List<Standard> standardListResult=new ArrayList<Standard>();
					standardListResult = session.selectList("selectSocialCategoryWiseClassStrengthReportData",standard);
					standardList.addAll(standardListResult);
				}				
			}
		}catch (Exception e) {
			logger.error(e);
		} finally {
			session.close();
		}
		return standardList;
	}


	@Override
	public Student showTCReport(String rollNumber) {
		SqlSession session =sqlSessionFactory.openSession();		
		Student student =null;
		try{
			student = session.selectOne("generateTCReport",rollNumber);
		}catch (Exception e) {
			logger.error(e);
		} finally {
			session.close();
		}
		return student;
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public List<SubjectGroup> getSubjectGroupForXI_XIIResult(StudentResult studentResult) {
		SqlSession session = sqlSessionFactory.openSession();
		List<SubjectGroup> subjectGroupList = null;
		try{	
			subjectGroupList = session.selectList("getSubjectGroupForXI_XIIResult",studentResult);
		}catch (Exception e) {
			logger.error(e);
		} finally {
			session.close();
		}
		return subjectGroupList;
	}
	@Override
	public List<Student> getConsolidatedReportForXI_XIIForCurrentYear(StudentResult studentResult) {
		SqlSession session = sqlSessionFactory.openSession();
		List<Student> studentList = null;
		try{
			if(null != studentResult && null != studentResult.getExam()){
				int insertDtatus = session.insert("insertConsolidatedReportForXI_XIIForCurrentYear",studentResult);	
			}
			studentList = session.selectList("selectConsolidatedReportForXI_XIIForCurrentYear");
		}catch (Exception e) {
			logger.error(e);
		} finally {
			session.close();
		}		
		return studentList;
	}


	@Override
	public List<Student> getcreateReportForExamXI_XII(StudentResult studentResult) {
		SqlSession session = sqlSessionFactory.openSession();
		List<Student> studentList = null;
		try{
			if(null != studentResult && null != studentResult.getExam() && null != studentResult.getStringList() && studentResult.getStringList().size() != 0){
				studentList = new ArrayList<Student>();
				
				int insertDtatus = session.insert("insertConsolidatedReportForXI_XIIForCurrentYear", studentResult);	
				
				for(String stu : studentResult.getStringList()){
					Student student = session.selectOne("selectReportForXI_XIIForCurrentYear", stu);
					if(null != student)
						studentList.add(student);
				}
				session.commit();
			}
		}catch (Exception e) {
			logger.error(e);
		} finally {
			session.close();
		}
		return studentList;
	}
	
	
	@Override
	public List<UserDefinedExams> getUserDefinedExamsForStandard(String standard) {
		List<UserDefinedExams> examList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try{
			examList = session.selectList("selectUserDefinedExamsForStandard", standard);
		}catch(Exception e) {
			logger.error(e);
		}finally{
			session.close();
		}
		return examList;
	}
	
	
	@Override
	public List<SubjectGroup> getSubjectGroupForInternalExamination(StudentResult studentResult) {
		List<SubjectGroup> subjectGroupList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try{
			subjectGroupList = session.selectList("selectSubjectGroupForInternalExamination", studentResult);
		}catch(Exception e) {
			logger.error(e);
		}finally{
			session.close();
		}
		return subjectGroupList;
	}
	

	@Override
	public List<Student> getStudentListForInternalExamination(StudentResult studentResult) {
		 
		List<Student> studentList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try{
			studentList = session.selectList("selectStudentListForInternalExam", studentResult);
		}catch(Exception e) {
			logger.error(e);
		}finally{
			session.close();
		}
		return studentList;
	}
	
	
	public List<Student> getStudentListForCentralised(StudentResult studentResult) {
		 
		List<Student> studentList = new ArrayList<Student>();;
		SqlSession session = sqlSessionFactory.openSession();
		try{
			for(String s:studentResult.getStringList()){
				studentResult.setRollNumber(s);				
				Student student = session.selectOne("selectStudentListForCentralised", studentResult);
				studentList.add(student);
			}
		}catch(Exception e) {
			logger.error(e);
		}finally{
			session.close();
		}
		if(studentList.size()==0)
			studentList=null;
		return studentList;
	}
	
	
	public int getTotalStudentCount(StudentResult studentResult) {
		SqlSession session = sqlSessionFactory.openSession();
		int count = 0;
		try{
			count = session.selectOne("selectTotalStudentCount", studentResult);
		}
		catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return count;
	}
	
	
	public List<Student> getRollNoWiseStudentList(StudentResult studentResult) {
		
		List<Student> studentList = new ArrayList<Student>();;
		SqlSession session = sqlSessionFactory.openSession();
		try{
			/*for(String s:studentResult.getStringList()){
				studentResult.setRollNumber(s);			*/	
			 studentList = session.selectList("selectRollNoWiseStudentList", studentResult);
				
			//}
		}catch(Exception e) {
			logger.error(e);
		}finally{
			session.close();
		}
		if(studentList.size()==0)
			studentList=null;
		return studentList;
	}
	
	public List<ChartValuesModel> loadChartData(String sql){
	
		List<ChartValuesModel> chartValuesModelList = new ArrayList<ChartValuesModel>();
		
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
            java.sql.Connection conn = session.getConnection();
            java.sql.Statement statement = conn.createStatement();

            ResultSet resultset = statement.executeQuery(sql);

		    while (resultset.next()) {
		    	ChartValuesModel chartValuesModel = new ChartValuesModel();
		    	
		    	chartValuesModel.setLabel(resultset.getString("status"));
		    	chartValuesModel.setValue(String.valueOf(resultset.getInt("count")));
		    	
		    	chartValuesModelList.add(chartValuesModel);
		    }
		    conn.close();
       } catch (SQLException e1) {
                     e1.printStackTrace();
       }

		
		return chartValuesModelList;
	
	}
	
	/**New CBSE System start**/
	
	
	@Override
	public List<Student> getStudentsAgainstStandardAndSectionForNewReport(Student student) {
		SqlSession session =sqlSessionFactory.openSession();
		List<Student> studentList = null;
		try {
			studentList = session.selectList("getStudentsAgainstStandardAndSectionForNewReport", student);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getStudentsAgainstStandardAndSectionForNewReport() In CommonDaoImpl.java: Exception" ,e);
		} finally {
			session.close();
		}
		return studentList;
	}
	@Override
	public List<CoScholasticResult> getCoScholasticResultListNew(Student student) {
		SqlSession session =sqlSessionFactory.openSession();
		List<CoScholasticResult> coScholasticResultList=null;
		try{			
			coScholasticResultList = session.selectList("selectCoScholasticResultListForReportNew",student);			
		}catch (Exception e) {
			logger.error(e);
		} finally {
			session.close();
		}
		return coScholasticResultList;
	}


	@Override
	public List<Student> getConsolidatedReportForExamForCurrentAcademicYearNew(StudentResult studentResult) {
		SqlSession session = sqlSessionFactory.openSession();
		List<Student> studentList = null;
		try{	
			//call for class & sub mapping
			if(null != studentResult && null != studentResult.getExam()){
				if(studentResult.getExam().equalsIgnoreCase("Term1")){
					int insertDtatus = session.insert("insertConsolidatedReportForTerm1", studentResult);	
					studentList = session.selectList("selectConsolidatedReportForTerm1Student");
				}
				
				if(studentResult.getExam().equalsIgnoreCase("Term2")){
					int insertDtatus = session.insert("insertConsolidatedReportForTerm2", studentResult);	
					studentList = session.selectList("selectConsolidatedReportForTerm2Student");
				}
				
				if(studentResult.getExam().equalsIgnoreCase("AnnualExam1")){
					int insertDtatus = session.insert("insertConsolidatedReportForAnnualExam", studentResult);	
					studentList = session.selectList("selectConsolidatedReportForAnnualExamStudent");
				}
				
				if(studentResult.getExam().equalsIgnoreCase("PT1")||studentResult.getExam().equalsIgnoreCase("PT2")||studentResult.getExam().equalsIgnoreCase("PT3")){
					int insertDtatus = session.insert("insertConsolidatedReportForPeriodicTest", studentResult);	
					studentList = session.selectList("selectConsolidatedReportForForPeridicTestNew");
				}
				
				if(studentResult.getExam().equalsIgnoreCase("T1_PT1")){
					int insertDtatus = session.insert("insertConsolidatedReportForPeriodicTest", studentResult);	
					studentList = session.selectList("selectConsolidatedReportForForPeridicTestNew");
 				}
				session.commit();
			}
			/*Naimisha*/
			if(null != studentResult && null != studentResult.getExam()){
				if(studentResult.getExam().equalsIgnoreCase("Term_1")||studentResult.getExam().equalsIgnoreCase("M1")||studentResult.getExam().equalsIgnoreCase("M2")||studentResult.getExam().equalsIgnoreCase("PC")){
					int insertDtatus = session.insert("insertConsolidatedReportForXIForGeneralStudentNew", studentResult);	
					studentList = session.selectList("selectConsolidatedReportForXIForGeneralStudentNew");
				}
				session.commit();
			}
		}	
		catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return studentList;
	}


	@Override
	public List<SubjectGroup> getSubjectGroupFromStudentMarksV1(StudentResult studentResult) {
		SqlSession session =sqlSessionFactory.openSession();
		List<SubjectGroup> subjectGroupList=null;
		try{
			String standard = studentResult.getStandard();
			String exam = studentResult.getExam();
			if(standard.equalsIgnoreCase("VI")||standard.equalsIgnoreCase("VII")||standard.equalsIgnoreCase("VIII")){
				if(exam.equalsIgnoreCase("Term1")){
					subjectGroupList = session.selectList("selectClassWiseSubjectGroupsFromStudentMarksV1ForTerm1",studentResult);
				}else if (exam.equalsIgnoreCase("Term2")){
					subjectGroupList = session.selectList("selectClassWiseSubjectGroupsFromStudentMarksV1ForTerm2",studentResult);
				}else{
					subjectGroupList = session.selectList("selectClassWiseSubjectGroupsNew",studentResult);
				}
			}else if(standard.equalsIgnoreCase("IX")||standard.equalsIgnoreCase("X")){
				if(exam.equalsIgnoreCase("AnnualExam1")){
					subjectGroupList = session.selectList("selectClassWiseSubjectGroupsFromStudentMarksForAnnualExam",studentResult);
				}else if(exam.equalsIgnoreCase("PT1")||exam.equalsIgnoreCase("PT2")||exam.equalsIgnoreCase("PT3")){
					subjectGroupList = session.selectList("selectClassWiseSubjectGroupsNew",studentResult);
				}
			}else if(standard.equalsIgnoreCase("XI")||standard.equalsIgnoreCase("XII")){
				subjectGroupList = session.selectList("selectClassWiseSubjectGroupsNew",studentResult);
			}
		
		}catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return subjectGroupList;
	}


	@Override
	public List<Student> getStudentListForConsolidateCentralisedNew(StudentResult studentResult) {
		List<Student> studentList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try{
			int insertDtatus = session.insert("insertConsolidatedReportForXICentralise", studentResult);	
			studentList = session.selectList("selectStudentListForConsolidateCentralisedNew", studentResult);
		}catch(Exception e) {
			logger.error(e);
		}finally{
			session.close();
		}
		if(studentList.size()==0)
			studentList=null;
		return studentList;
	}
	
	@Override
	public List<SubjectGroup> getSubjectGroupForInternalExaminationNew(StudentResult studentResult) {
		List<SubjectGroup> subjectGroupList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try{
			subjectGroupList = session.selectList("selectSubjectGroupForInternalExaminationNew", studentResult);
		}catch(Exception e) {
			logger.error(e);
		}finally{
			session.close();
		}
		return subjectGroupList;
	}
	

	@Override
	public List<Student> getStudentListForInternalExaminationNew(StudentResult studentResult) {
		 
		List<Student> studentList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try{
			studentList = session.selectList("selectStudentListForInternalExamNew", studentResult);
		}catch(Exception e) {
			logger.error(e);
		}finally{
			session.close();
		}
		return studentList;
	}
	
	public List<Student> getStudentsSubjectsAndMarksReportForCurrentAcademicYearNew(StudentResult studentResult) {
		SqlSession session = sqlSessionFactory.openSession();
		List<Student> studentList = null;
		try{
			if(null != studentResult && null != studentResult.getExam()){
				if(studentResult.getExam().equals("Term1") ){
					System.out.println("within term1");
					int insertStatus = session.insert("insertResultsIntoTempTableForReportForTerm1", studentResult);
					studentList = session.selectList("selectStudentsSubjectsAndMarksReportForCurrentAcademicYearTerm1");			
					int deleteStatus = session.delete("deleteFromTempTableForReportForTerm1");
					session.commit();	
				}
				
				if(studentResult.getExam().equals("Term2") ){
					System.out.println("within term2");
					int insertStatus = session.insert("insertResultsIntoTempTableForReportForTerm2", studentResult);
					studentList = session.selectList("selectStudentsSubjectsAndMarksReportForCurrentAcademicYearTerm2");			
				
					session.commit();	
				}
				
				if(studentResult.getExam().equals("AnnualExam1") ){
					System.out.println("within AnnualExam1");
					int insertStatus = session.insert("insertResultsIntoTempTableForReportForAnnualExam", studentResult);
					studentList = session.selectList("selectStudentsSubjectsAndMarksReportForCurrentAcademicYearAnnualExam");			
				
					session.commit();	
				}
				if(studentResult.getExam().equalsIgnoreCase("PT1") || studentResult.getExam().equalsIgnoreCase("PT2") || studentResult.getExam().equalsIgnoreCase("PT3") ){
					System.out.println("within PT");
					int insertStatus = session.insert("insertResultsIntoTempTableForReportForPeriodicTest", studentResult);
					studentList = session.selectList("selectStudentsSubjectsAndMarksReportForCurrentAcademicYearPeriodicTest");			
					int deleteStatus = session.delete("deleteFromTempTableForReportForPeriodicTable");
					session.commit();	
				}
				if(studentResult.getExam().equals("Term_1")||studentResult.getExam().equals("M1")||studentResult.getExam().equals("M2") || studentResult.getExam().equals("PC")){
					int insertStatus = session.insert("insertResultsIntoTempTableForReportForXINew",studentResult);
					studentList = new ArrayList<Student>();
					for(String studentRoll : studentResult.getStringList()){
						logger.info("### Student :: "+studentRoll);
						Student stu = session.selectOne("selectStudentsSubjectsAndMarksReportForCurrentAcademicYearXINew", studentRoll);
						studentList.add(stu);
					}
					session.commit();
				}	
				
				if(studentResult.getExam().equals("Centralise")){
					int insertStatus = session.insert("insertConsolidatedReportForXICentralise",studentResult);
					studentList = new ArrayList<Student>();
					for(String studentRoll : studentResult.getStringList()){
						logger.info("### Student :: "+studentRoll);
						Student stu = session.selectOne("selectStudentsSubjectsAndMarksReportForCurrentAcademicYearForCentraliseNew", studentRoll);
						studentList.add(stu);
					}
					session.commit();		
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		} finally {
			session.close();
		}
		return studentList;
	}
	
	@Override
	public List<Student> getStudentResultRawDataForNewCBSEPattern(StudentResult studentResult) {
		List<Student> studentList = new ArrayList<Student>();
		SqlSession session = sqlSessionFactory.openSession();
		try{
			 studentList = session.selectList("selectStudentResultRawDataForNewCBSEPattern", studentResult);
			 
//			 for(Student s:studentList){
//				 System.out.println(s.getRollNumber()+"\n");
//				 for(StudentResult sr:s.getStudentResultList()){
//					 System.out.println("\t"+sr.getExam()+"   "+sr.getSubject()+"   "+sr.getTotalObtained());
//				 }
//			 }
//			 
//			 System.out.println("****************************");
//			 
		}catch(Exception e) {
			logger.error(e);
		}finally{
			session.close();
		}
		if(studentList.size()==0)
			studentList=null;
		return studentList;
	}
	
	@Override
	public List<StudentResult> selectGradeForSubjectTotal(Student student) {
		SqlSession session =sqlSessionFactory.openSession();
		List<StudentResult> gradeList = null;
		
		try{
			gradeList = session.selectList("seleteGradeForSubjectTotal",student);
				session.commit();
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		} finally {
			session.close();
		}
		return gradeList;
	}
	
	//Added by saif 17082017
	@Override
	public List<Student> getStudentResultRawDataForNewCBSEPatternNew(StudentResult studentResult) {
		List<Student> studentList = new ArrayList<Student>();
		SqlSession session = sqlSessionFactory.openSession();
		try{
			 studentList = session.selectList("selectStudentResultRawDataForNewCBSEPatternNew", studentResult);
		}catch(Exception e) {
			logger.error(e);
		}finally{
			session.close();
		}
		if(studentList.size()==0)
			studentList=null;
		return studentList;
	}
	
	/**New CBSE System end**/
	
	//Added By Naimisha 04102017
	
	public List<ChartValuesModel> loadChart1DataForLibrary(String sql){
		
		List<ChartValuesModel> chartValuesModelList = new ArrayList<ChartValuesModel>();
		
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
            java.sql.Connection conn = session.getConnection();
            java.sql.Statement statement = conn.createStatement();

            ResultSet resultset = statement.executeQuery(sql);

		    while (resultset.next()) {
		    	ChartValuesModel chartValuesModel = new ChartValuesModel();
		    	
		    	chartValuesModel.setLabel(resultset.getString("status"));
		    	chartValuesModel.setValue(String.valueOf(resultset.getInt("count")));
		    	
		    	chartValuesModelList.add(chartValuesModel);
		    }
		    conn.close();
       } catch (SQLException e1) {
                     e1.printStackTrace();
       }

		
		return chartValuesModelList;
	
	}
}
