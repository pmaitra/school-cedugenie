package com.qts.icam.dao.impl.academics;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.itextpdf.text.log.SysoLogger;
import com.qts.icam.dao.academics.AcademicsDAO;
import com.qts.icam.dao.backoffice.BackOfficeDAO;
import com.qts.icam.dao.impl.backoffice.BackOfficeDAOImpl;
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
import com.qts.icam.model.administrator.Approver;
import com.qts.icam.model.admission.AdmissionForm;
import com.qts.icam.model.backoffice.Exam;
import com.qts.icam.model.backoffice.Fees;
import com.qts.icam.model.backoffice.FeesTemplate;
import com.qts.icam.model.backoffice.Term;
import com.qts.icam.model.backoffice.TimeTableGridData;
import com.qts.icam.model.common.AcademicYear;
import com.qts.icam.model.common.Asset;
import com.qts.icam.model.common.Department;
import com.qts.icam.model.common.ExamType;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.Scholarship;
import com.qts.icam.model.common.Section;
import com.qts.icam.model.common.SocialCategory;
import com.qts.icam.model.common.Standard;
import com.qts.icam.model.common.Student;
import com.qts.icam.model.common.TeacherSubjectMapping;
import com.qts.icam.model.erp.Employee;
import com.qts.icam.model.event.EventAchievement;
import com.qts.icam.model.event.SchoolEvent;
import com.qts.icam.model.grade.GradingSystem;
import com.qts.icam.model.ticket.ServiceType;
import com.qts.icam.model.ticket.Ticket;
import com.qts.icam.model.venue.Venue;
import com.qts.icam.utility.Utility;
import com.qts.icam.utility.customexception.CustomException;
import com.qts.icam.utility.encryptdecrypt.EncryptDecrypt;

@Repository
public class AcademicsDAOImpl implements AcademicsDAO {
	private final static Logger logger = Logger.getLogger(BackOfficeDAOImpl.class);
	
	EncryptDecrypt encryptDecrypt = new EncryptDecrypt();
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	
	@Override
	public List<Standard> getStandardsWithSection() throws CustomException {
		List<Standard> standardsWithSectionList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			standardsWithSectionList=session.selectList("selectStandardsWithSection");
		}catch(Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return standardsWithSectionList;
	}

	@Override
	public List<SchoolEvent> getAllEventAchievements() {
		List<SchoolEvent> eventsWithAchievementList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			eventsWithAchievementList=session.selectList("getAllEventAchievements");
			System.out.println("Fine in DAOIMPL");
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return eventsWithAchievementList;
	}
	
	@Override
	public String addStandard(Standard standard) throws CustomException {
		String insertStatus="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			standard.setObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			session.insert("insertStandard", standard);
		}catch(Exception e) {
			insertStatus="fail";
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return insertStatus;
	}

	/**
	 * modified by saurav.bhadra
	 * changes taken on 12042017
	 * **/
	@Override
	public String editStandard(Standard standard) throws CustomException {
		String updateStatus="success";
		SqlSession session =sqlSessionFactory.openSession();
		List<Section> allSections = null;
		List<Section> sectionsList = new ArrayList<Section>();
		try{
			allSections = session.selectList("selectListForStandard", standard);
			String sections = "";
			for(Section scDb : allSections){
				sections += scDb.getSectionCode()+",";
			}
			String[] arrSections = sections.split(",");
			Set<String> setSection = new HashSet<String>(Arrays.asList(arrSections));
			for(Section sc : standard.getSectionList()){
				String sectionCode = sc.getSectionCode();
				String updatedby = sc.getUpdatedBy();
				if(!setSection.contains(sectionCode)){
					Section singleSection = new Section();
					singleSection.setUpdatedBy(updatedby);
					singleSection.setSectionCode(sectionCode);
					singleSection.setSectionName(sectionCode);
					singleSection.setDesc(sectionCode);
					sectionsList.add(singleSection);
				}
			}
			standard.setSectionList(sectionsList);
			session.update("updateStandard", standard);
		}catch(Exception e) {
			updateStatus="fail";
			logger.error(e);
			e.printStackTrace();
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}		
		return updateStatus;
	}

	//****************************************************************Standard Ends
		
	@Override
	public List<SubjectGroup> getSubjectGroup() throws CustomException {
		List<SubjectGroup> subjectGroupList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			subjectGroupList=session.selectList("selectSubjectGroupList");
		}catch(Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return subjectGroupList;
	}

	@Override
	public String addSubjectGroup(SubjectGroup subjectGroup) throws CustomException {
		String insertStatus="success";
		SqlSession session =sqlSessionFactory.openSession();
		int insrtStatus2 = 0;
		try{
			subjectGroup.setObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			insrtStatus2 = session.insert("insertSubjectGroup", subjectGroup); //fetch table name against task list then insert the table name along with rec_id in the new table
			
			
			
			
			if(insrtStatus2 != 0){
				List<String>tableNameList = null;
				String taskDetailsCode = subjectGroup.getTaskNo();
				
				//fetch table names for a task
				tableNameList = session.selectList("selectTableListForATask", taskDetailsCode);
				
				if(null != tableNameList && tableNameList.size() != 0){
					for(String tableName : tableNameList){
						Ticket ticket = new Ticket();
						ticket.setTicketObjectId(subjectGroup.getObjectId());
						ticket.setUpdatedBy(subjectGroup.getUpdatedBy());
						ticket.setTicketCode(subjectGroup.getTicketNo());
						ticket.setTicketDesc(subjectGroup.getTaskNo());
						ticket.setTableName(tableName);
						ticket.setStatus("INSERT");
						
						//get last inserted record
						
						String lastInsertedRecId = session.selectOne("selectLastInsertedRecId",ticket);
						
						
						ticket.setTicketRecId(lastInsertedRecId);
						int insertStatus3 = session.insert("insertIntoTicketTaskTablenameMapping",ticket);
					}
					
				}
			}
		}catch(Exception e) {
			insertStatus="fail";
			logger.error(e);
			e.printStackTrace();
			CustomException.exceptionHandler(e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}finally{
			session.close();
		}
		return insertStatus;
	}

	@Override
	public String editSubjectGroup(SubjectGroup subjectGroup) throws CustomException {
		String updateStatus="success";
		SqlSession session =sqlSessionFactory.openSession();
		int updateStatus2 = 0;
		try{
			updateStatus2 = session.update("updateSubjectGroup", subjectGroup);//fetch table name against task list then insert the table name along with rec_id in the new table
			
					if(updateStatus2 != 0){
						List<String>tableNameList = null;
						String taskDetailsCode = subjectGroup.getTaskNo();
						
						//fetch table names for a task
						tableNameList = session.selectList("selectTableListForATask", taskDetailsCode);
						
						if(null != tableNameList && tableNameList.size() != 0){
							for(String tableName : tableNameList){
								Ticket ticket = new Ticket();
								ticket.setTicketObjectId(encryptDecrypt.getBase64EncodedID("AcademicsDAOImpl"));
								ticket.setUpdatedBy(subjectGroup.getUpdatedBy());
								ticket.setTicketCode(subjectGroup.getTicketNo());
								ticket.setTicketDesc(subjectGroup.getTaskNo());
								ticket.setTableName(tableName);
								ticket.setStatus("UPDATE");
								int insertStatus3 = session.insert("insertIntoTicketTaskTablenameMapping",ticket);
							}
							
						}
					}
		
		}catch(Exception e) {
			updateStatus="fail";
			logger.error(e);
			e.printStackTrace();
			CustomException.exceptionHandler(e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}finally{
			session.close();
		}		
		return updateStatus;
	}

	//****************************************************************Subject Group Ends
	
	


	@Override
	public String addSubject(Subject subject) throws CustomException {
		String insertStatus="Insert Successful";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			subject.setObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			session.insert("insertSubject", subject);
		}catch(Exception e) {
			insertStatus="Update Failed";
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return insertStatus;
	}

	@Override
	public String editSubject(Subject subject) throws CustomException {
		String updateStatus="Update Successful";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			session.update("updateSubject", subject);
		}catch(Exception e) {
			updateStatus="Update Failed";
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return updateStatus;
	}

	//****************************************************************Subject Ends
	
	@Override
	public String getSubjectsForCourse(String course) throws CustomException {
		String subjects="";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			List<Subject> subjectList=session.selectList("selectSubjectsForCourse", course);
			if(null!=subjectList && subjectList.size()!=0){
				for(Subject subject:subjectList){
					subjects=subjects+subject.getSubjectCode()+"#@#"+subject.getSubjectName()+"*~*";
				}
			}
		}catch(Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return subjects;
	}

	@Override
	public String editCourseSubjectMapping(CourseSubjectMapping courseSubjectMapping) throws CustomException {
		String status="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			courseSubjectMapping.setObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			if(null!=courseSubjectMapping.getOldSubjectList() && courseSubjectMapping.getOldSubjectList().size()!=0){
				session.update("inactiveCourseSubjectMapping", courseSubjectMapping);
			}
			if(null!=courseSubjectMapping.getNewSubjectList() && courseSubjectMapping.getNewSubjectList().size()!=0){
				for(String newSubject:courseSubjectMapping.getNewSubjectList()){
					courseSubjectMapping.setSubject(newSubject);
					CourseSubjectMapping courseSubjectMappingCheckParam=new CourseSubjectMapping();
					courseSubjectMappingCheckParam.setSubject(newSubject);
					courseSubjectMappingCheckParam.setCourseCode(courseSubjectMapping.getCourseCode());
					CourseSubjectMapping checker=session.selectOne("selectInactiveSubjectForCourse",courseSubjectMappingCheckParam);
					if(null!=checker && null!=checker.getSubject()){
						session.update("updateCourseSubjectMapping", courseSubjectMapping);
					}else{
						session.insert("insertCourseSubjectMapping", courseSubjectMapping);
					}
				}
			}
			/*new code starting Added By Sourav 02032017*/
			int totalCredit = 0;
			if(null != courseSubjectMapping.getSubjectList()){
				for(String subjects : courseSubjectMapping.getSubjectList()){
					Subject sub = session.selectOne("selectSubjectCreditsForCourse", subjects);
					totalCredit += sub.getCredit();
				}
			}
			
				//System.out.println("in editCourseSubjectMapping of AcaDAOImpl....line no 252....");
				//System.out.println("Total Credit : "+totalCredit);
		//	if(totalCredit != 0){
				courseSubjectMapping.setCourseCredit(totalCredit);
				session.update("updateTotalCredit", courseSubjectMapping);
				//System.out.println("Course credit updated in course table...257");
			//}
			//System.out.println("in editCourseSubjectMapping of AcaDAOImpl...line no 259....");
			/*new code ending*/
			
		}catch(Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			status="fail";
			logger.error(e);
			e.printStackTrace();
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return status;
	}




	@Override
	public List<ExamMarks> getSubjectsAndMarksForStandard(String standard, String exam) throws CustomException {
		List<ExamMarks> examMarksListFinal = null;
		SqlSession session = sqlSessionFactory.openSession();
		try{
			List<String> subjectGroupList = session.selectList("selectSubjectGroupForStandard", standard);
			
			if(null != subjectGroupList && subjectGroupList.size() != 0){
				examMarksListFinal = new ArrayList<ExamMarks>();
				for(String s : subjectGroupList){
					ExamMarks em = new ExamMarks();
					em.setStatus(s);
					em.setDesc(exam);
					em.setStandard(standard);
					em = session.selectOne("selectMarksForExamCourseAndTermSubject", em);
					if(null != em){
						examMarksListFinal.add(em);
					}else{
						em = new ExamMarks();
						Subject sub = new Subject();
						sub.setSubjectCode(s);
						sub.setSubjectName(s);
						em.setSubject(sub);
						Exam e = new Exam();
						e.setExamCode(exam);
						e.setExamName(exam);
						em.setExam(e);
						em.setTheory(0);
						em.setTheoryPass(0);
						em.setPractical(0);
						em.setPracticalPass(0);
						em.setTotal(0);
						em.setPass(0);
						examMarksListFinal.add(em);
					}
				}
			}
		}catch(Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return examMarksListFinal;
	}

	@Override
	public String editExamMarks(List<ExamMarks> examMarksList) throws CustomException {
		String updateStatus="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			if(null!=examMarksList && examMarksList.size()!=0)
				session.update("updateExamMarks", examMarksList);
		}catch(Exception e) {
			updateStatus="fail";
			logger.error(e);
			CustomException.exceptionHandler(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return updateStatus;
	}

	@Override
	public String editStudentResult(List<StudentResult> studentResultList, String insertUpdate) throws CustomException {
		String updateStatus="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			if(null!=studentResultList && studentResultList.size()!=0){
				if(insertUpdate.equalsIgnoreCase("INSERT"))
					session.insert("insertStudentResult", studentResultList);
				else if(insertUpdate.equalsIgnoreCase("UPDATE"))
					session.update("updateStudentResult", studentResultList);
			}
		}catch(Exception e) {
			updateStatus="fail";
			logger.error(e);
			CustomException.exceptionHandler(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return updateStatus;
	}
	
	@Override
	public List<StudentResult> getStudentsAndMarks(StudentResult studentResult, String loggedInUser) throws CustomException {
		List<StudentResult> studentsSubjectsAndMarksList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try{
			studentsSubjectsAndMarksList = session.selectList("selectUploadedStudentsAndMarks", studentResult);
			if(null != studentsSubjectsAndMarksList && studentsSubjectsAndMarksList.size() != 0){
				List<String> academicAdmin = session.selectList("getAllResourceForRole", "ACADEMICS ADMINISTRATOR");
				List<String> academicUser = session.selectList("getAllResourceForAccessType", "Admin User");
				if(null == academicAdmin || academicAdmin.size() == 0){
					academicAdmin = new ArrayList<String>();
				}
				academicAdmin.addAll(academicUser);
				if(null == academicAdmin || academicAdmin.size() == 0){
					academicAdmin = new ArrayList<String>();
				}
				academicAdmin.add("superadmin");
				
				if(academicAdmin.contains(loggedInUser) || studentsSubjectsAndMarksList.get(0).getUpdatedBy().equalsIgnoreCase(loggedInUser))
					studentsSubjectsAndMarksList.get(0).setStatus("UPDATE");
				else
					studentsSubjectsAndMarksList.get(0).setStatus("NA");
			}else{
				List<String> studentRollList = session.selectList("selectAllRollForResult", studentResult);		// List<Integer> changed to List<String>	
				StudentResult selectedStudentReslt = session.selectOne("selectMarksForSubject", studentResult);
				
				if(null != selectedStudentReslt && null != studentRollList && studentRollList.size() != 0){
					studentsSubjectsAndMarksList = new ArrayList<StudentResult>();
					for(String rollNumberWithName : studentRollList){
						String rollAndName[] = rollNumberWithName.split("~");		// Roll number and names split.
						StudentResult studResult = new StudentResult();
						studResult.setRollNumber(rollAndName[0]);
						studResult.setName(rollAndName[1]);
						studResult.setTheory(selectedStudentReslt.getTheory());
						studResult.setTheoryPass(selectedStudentReslt.getTheoryPass());
						studResult.setPractical(selectedStudentReslt.getPractical());
						studResult.setPracticalPass(selectedStudentReslt.getPracticalPass());
						studResult.setTotal(selectedStudentReslt.getTotal());
						studResult.setPass(selectedStudentReslt.getPass());
						
						studentsSubjectsAndMarksList.add(studResult);
					}
					studentsSubjectsAndMarksList.get(0).setStatus("INSERT");
				}						
			}
		}catch(Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return studentsSubjectsAndMarksList;
	}


	@Override
	public String insertAssignSection(List<Student> studentList)throws CustomException {
		String updateStatus="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			if(null!=studentList && studentList.size()!=0){
				session.update("insertAssignSection", studentList);
			}
		}catch(Exception e) {
			updateStatus="fail";
			logger.error(e);
			CustomException.exceptionHandler(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return updateStatus;
	}

	@Override
	public List<Student> getPendingSectionAssignment() throws CustomException {
		List<Student> pendingList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			pendingList=session.selectList("selectPendingSectionAssignment");
		}catch(Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return pendingList;
	}

	@Override
	public List<Student> getStudentsToAssignSection(Student student)throws CustomException {
		SqlSession session =sqlSessionFactory.openSession();
		List<Student> studentList = null;
		try {
			studentList = session.selectList("selectStudentsToAssignSection", student);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getStudentsAgainstStandardAndSection() In CommonDaoImpl.java: Exception" ,e);
		} finally {
			session.close();
		}
		return studentList;
	}

	@Override
	public List<DescriptiveIndicatorSkill> getDescriptiveIndicatorHeadList()throws CustomException {
		List<DescriptiveIndicatorSkill> descriptiveIndicatorHeadList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try{
			descriptiveIndicatorHeadList = session.selectList("selectDescriptiveIndicatorSkillList");
		}catch(Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return descriptiveIndicatorHeadList;
	}

	@Override
	public List<CoScholasticResult> getCoScholasticResultList() throws CustomException {
		List<CoScholasticResult> resultStatusList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try{
			resultStatusList = session.selectList("selectCoScholasticResultList");
		}catch(Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return resultStatusList;
	}

	@Override
	public List<CoScholasticResult> getStudentsForCoScholastic(CoScholasticResult coScholasticResult) throws CustomException {
		List<CoScholasticResult> studentList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try{
			studentList = session.selectList("selectStudentsForCoScholastic", coScholasticResult);
		}catch(Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return studentList;
	}

	@Override
	public String saveCoScholasticResult(List<CoScholasticResult> studentList)throws CustomException {
		String insertStatus = "Insert Successful";
		SqlSession session = sqlSessionFactory.openSession();
		try{
			if(null != studentList && studentList.size() != 0){
				session.update("insertCoScholasticResult", studentList);
			}
		}catch(Exception e) {
			insertStatus = "Insert Failed";
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return insertStatus;
	}

	@Override
	public List<CoScholasticResult> getInsertedCoScholasticStudents(CoScholasticResult coScholasticResult) throws CustomException {
		List<CoScholasticResult> studentList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try{
			studentList = session.selectList("selectInsertedCoScholasticStudents", coScholasticResult);
		}catch(Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return studentList;
	}

	@Override
	public String getStudentsCoScholasticResult(String roll, String loggedInUser)throws CustomException {
		String result="";
		SqlSession session = sqlSessionFactory.openSession();
		try{
			List<CoScholasticResult> resultList = session.selectList("selectStudentsCoScholasticResult", roll);
			if(null != resultList && resultList.size() != 0){
				List<String> academicAdmin = session.selectList("getAllResourceForRole", "ACADEMICS ADMINISTRATOR");				
				List<String> academicUser = session.selectList("getAllResourceForAccessType", "Admin User");
				if(null == academicAdmin || academicAdmin.size() == 0){
					academicAdmin = new ArrayList<String>();
				}
				academicAdmin.addAll(academicUser);
				academicAdmin.add("superadmin");
				
				/*if(null == academicAdmin || academicAdmin.size()==0){
					academicAdmin = new ArrayList<String>();
				}*/
				for(CoScholasticResult cr:resultList){
					result = result+cr.getSkill()+"`"+cr.getHead()+"`"+cr.getIndicator()+"`"+cr.getGrade()+"`"+cr.getGradePoint()+"~";
				}
				if(academicAdmin.contains(loggedInUser) || resultList.get(0).getUpdatedBy().equalsIgnoreCase(loggedInUser))
					result = result+"UPDATE";
				else
					result = result+"NA";
			}
		}catch(Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return result;
	}

	@Override
	public String editCoScholasticResult(List<CoScholasticResult> studentList)throws CustomException {
		String updateStatus = "Update Successful";
		SqlSession session = sqlSessionFactory.openSession();
		try{
			if(null != studentList && studentList.size()!=0){
				session.update("updateCoScholasticResult", studentList);
			}
		}catch(Exception e) {
			updateStatus="Update Failed";
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return updateStatus;
	}

	/**
	 * modified by anup.roy
	 * this method is for getting subjects for standard*/
	
	@Override
	public String getSubjectGroupForStandard(String standard)throws CustomException {
		
		String subjectGroup = "";
		SqlSession session = sqlSessionFactory.openSession();
		try{
			/*List<String> subjectGroupList = session.selectList("selectSubjectGroupForStandard", standard);*/
			List<String> subjectGroupList = session.selectList("selectSubjectGroupForAStandard", standard);
			if(null != subjectGroupList && subjectGroupList.size() != 0){
				for(String s : subjectGroupList){
					subjectGroup=subjectGroup+s+"*~*";
				}
			}
		}catch(Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return subjectGroup;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public String addUserDefinedExams(UserDefinedExams userDefinedExams)throws CustomException {
		String insertStatus="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			userDefinedExams.setObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			session.update("insertUserDefinedExams", userDefinedExams);
		}catch(Exception e) {
			insertStatus="fail";
			logger.error(e);
			CustomException.exceptionHandler(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return insertStatus;
	}

	@Override
	public String editUserDefinedExams(List<UserDefinedExams> userDefinedExamsList) throws CustomException {
		String insertStatus="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			if(null!=userDefinedExamsList && userDefinedExamsList.size()!=0){
				UserDefinedExams userDefinedExam = userDefinedExamsList.get(0);
				session.update("updateUserDefinedExams", userDefinedExam);
			}
		}catch(Exception e) {
			e.printStackTrace();
			insertStatus="fail";
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return insertStatus;
	}

	@Override
	public List<UserDefinedExams> getAllUserDefinedExams()throws CustomException {
		List<UserDefinedExams> examList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			examList=session.selectList("selectAllUserDefinedExams");
		}catch(Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return examList;
	}

	@Override
	public List<UserDefinedExams> getUserDefinedExamsForStandard(String standard)throws CustomException {
		List<UserDefinedExams> examList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			examList=session.selectList("selectUserDefinedExamsForStandard", standard);
		}catch(Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return examList;
	}

	@Override
	public List<UserExamMarks> getSubjectAndMarksForUserDefinedExams(UserExamMarks userExamMarks) throws CustomException {
		List<UserExamMarks> examMarksListFinal = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			examMarksListFinal = session.selectList("selectMarksForUserExamAndCourse", userExamMarks);
			
		}catch(Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return examMarksListFinal;
	}

	@Override
	public String editUserExamMarks(List<UserExamMarks> examMarksList)throws CustomException {
		String updateStatus="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			if(null!=examMarksList && examMarksList.size()!=0)
				session.update("updateUserExamMarks", examMarksList);
		}catch(Exception e) {
			updateStatus="fail";
			logger.error(e);
			e.printStackTrace();
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return updateStatus;
	}

	@Override
	public List<StudentResult> getStudentsAndMarksForUserDefinedExams(StudentResult studentResult)throws CustomException {
		List<StudentResult> studentsSubjectsAndMarksList = null;
		SqlSession session = sqlSessionFactory.openSession();
	
		try{
			
			studentsSubjectsAndMarksList = session.selectList("selectUploadedStudentsAndMarksForUserDefinedExams", studentResult);
			if(null == studentsSubjectsAndMarksList || studentsSubjectsAndMarksList.size() == 0){
				System.out.println(studentResult.getCourse()+"\n"+studentResult.getSubject()+"\n"+studentResult.getSection());
				List<String> studentRollList = session.selectList("selectAllRollForResult", studentResult);				
				UserExamMarks userExamMark = session.selectOne("selectMarksForUserExamSubjectAndCourse", studentResult);
				
				if(null != userExamMark && null != studentRollList && studentRollList.size() != 0){
					studentsSubjectsAndMarksList = new ArrayList<StudentResult>();
					for(String rollNumberWithName : studentRollList){
						String rollAndName[] = rollNumberWithName.split("~");		// Roll Number and name split.
						
						StudentResult studResult = new StudentResult();
						studResult.setRollNumber(rollAndName[0]);
						studResult.setName(rollAndName[1]);						
						studResult.setTheory(userExamMark.getTheory());
						studResult.setTheoryPass(userExamMark.getTheoryPass());
						studResult.setPractical(userExamMark.getPractical());
						studResult.setPracticalPass(userExamMark.getPracticalPass());
						studResult.setTotal(userExamMark.getTotal());
						studResult.setPass(userExamMark.getPass());
						studResult.setPassFail("FAIL");
						studResult.setTheoryObtained(0);
						studResult.setPracticalObtained(0);
						studResult.setTotalObtained(0);
						
						studentsSubjectsAndMarksList.add(studResult);
					}
				}						
			
			}
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		//System.out.println(studentsSubjectsAndMarksList.size());
		return studentsSubjectsAndMarksList;
	}

	@Override
	public String editStudentResultForUserExam(List<StudentResult> studentResultList)throws CustomException {
		String updateStatus="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			if(null!=studentResultList && studentResultList.size()!=0){
			List<StudentResult> studentsSubjectsAndMarksList = session.selectList("selectUploadedStudentsAndMarksForUserDefinedExams", studentResultList.get(0));
			if(null == studentsSubjectsAndMarksList || studentsSubjectsAndMarksList.size() == 0){
				session.insert("insertStudentResultUserExam", studentResultList);
			}else{
				session.update("updateStudentResultUserExam", studentResultList);
			}
			}
		}catch(Exception e) {
			e.printStackTrace();
			updateStatus="fail";
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return updateStatus;
	}
	
	
	@Override
	public int addNewAssetDetails(Asset asset) throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		int insertStatus=0;		
		try {		
			if(null != asset){
				asset.setObjectId(encryptDecrypt.encrypt("AcademicsDAOImpl"));										
				insertStatus = session.insert("insertNewAssetDetails",asset);	
			}						
			if(insertStatus!=0){
				session.commit();
			}				
		}catch (Exception e) {	
			logger.error("Exception in addNewAssetDetails(Asset asset) of AcademicsDAOImpl ", e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		} finally {
			session.close();
		}
		return insertStatus;
	}

	@Override
	public String assetNameValidation(String assetName) throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		String asset = null;
		try {
			logger.info("Executing assetNameValidation(String assetName) from AcademicsDAOImpl");
			asset = session.selectOne("serverSideValidationOfAssetName", assetName);
		} catch (Exception e) {
			logger.error("Exception occured in assetNameValidation(String assetName) from AcademicsDAOImpl : ", e);
		} finally {
			session.close();
		}
		return asset;
	}

	@Override
	public List<Asset> getAssetList() throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		List<Asset> assetList = null;
		try {			
			assetList = session.selectList("selectAllAssets");	
		}catch (Exception e) {
			logger.error("Exception In getAssetList() AcademicsDAOImpl : " ,e);
		} finally {
			session.close();
		}
		return assetList;
	}

	@Override
	public List<Asset> getSearchAssetList(Map<String, Object> parameters) throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		List<Asset> assetList = null;
		try {
			assetList = session.selectList("selectAllAssets",parameters);
		}catch (Exception e) {
			logger.error("Exception in getSearchAssetList() of AcademicsDAOImpl ", e);
			
		} finally {
			session.close();
		}
		return assetList;
	}

	@Override
	public Asset getAssetDetails(String assetId) throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		Asset asset = null;
		try {
			asset = session.selectOne("selectAssetDetails", Integer.parseInt(assetId));
		}catch (Exception e) {
			logger.error("Exception In getAssetDetails(String assetId) AcademicsDAOImpl : " ,e);
		} finally {
			session.close();
		}
		return asset;
	}

	@Override
	public int updateAssetDetails(Asset asset) throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		int updateStatus = 0;
		try {
			updateStatus = session.update("updateAssetDetails", asset);
		}catch (Exception e) {
			logger.error("Exception In updateAssetDetails(Asset asset) AcademicsDAOImpl : " ,e);
		} finally {
			session.close();
		}
		return updateStatus;
	}


	@Override
	public List<Department> getAllDepartment() throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		List<Department> departmentList = null;
		try {			
			departmentList = session.selectList("selectAllDepartment");	
		}catch (Exception e) {
			logger.error("Exception In getAllDepartment() AcademicsDAOImpl : " ,e);
		} finally {
			session.close();
		}
		return departmentList;
	}
	
	
	@Override
	public String resetStudentResult(StudentResult studentResult) throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		String status = "fail";
		try {
			//System.out.println(studentResult.getSection()+"\n"+studentResult.getExam()+"\n"+studentResult.getCourse()+"\n"+studentResult.getSubject());
			session.update("resetStudentResult", studentResult);
			status = "success";
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception In getAllDepartment() AcademicsDAOImpl : " ,e);
			status = "fail";
		} finally {
			session.close();
		}
		return status;
	}
	
	
	@Override
	public List<Exam> getExamForStandard(String standard) throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		List<Exam> examList = null;
		try {			
			examList = session.selectList("selectExamForStandard", standard);
		}catch (Exception e) {
			logger.error("Exception In getExamForStandard(String standard) AcademicsDAOImpl : " ,e);
		} finally {
			session.close();
		}
		return examList;
	}

	@Override
	public String submitAssetConsumption(AssetConsumption assetConsumption) throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		int insertStatus = 0;
		int finalStatus = 0;
		String status = "fail";
		try {		
			if(null != assetConsumption){
				assetConsumption.setObjectId(encryptDecrypt.encrypt("AcademicsDAOImpl"));										
				insertStatus = session.insert("insertAssetConsumption",assetConsumption);	
			}						
			if(insertStatus != 0){
				finalStatus = session.insert("updateAssetAfterConsumption",assetConsumption);	
				session.commit();
			}
			if(finalStatus != 0){
				status = "Successfully Inserted.";
				session.commit();
			}				
		}catch (Exception e) {	
			logger.error("Exception in submitAssetConsumption(AssetConsumption assetConsumption) of AcademicsDAOImpl ", e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		} finally {
			session.close();
		}
		return status;
	}

	@Override
	public List<AssetConsumption> getAssetConsumptionList(AssetConsumption assetConsumption) throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		List<AssetConsumption> assetConsumptionList = null;
		try {
			if(null != assetConsumption.getEndDate() && assetConsumption.getEndDate().trim().length() == 0 && assetConsumption.getEndDate().equals("")){
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				assetConsumption.setEndDate(dateFormat.format(cal.getTime()));
				//System.out.println("To Date :: ..."+assetConsumption.getEndDate()+"...");				
			}
			assetConsumptionList = session.selectList("selectAssetConsumptionList", assetConsumption);
		}catch (Exception e) {
			logger.error("Exception In getAssetConsumptionList(AssetConsumption assetConsumption) AcademicsDAOImpl : " ,e);
		} finally {
			session.close();
		}
		return assetConsumptionList;
	}

	@Override
	public AssetConsumption getAssetCurrentQuantity(int assetId) throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		AssetConsumption assetConsumption = null;
		try {			
			assetConsumption = session.selectOne("selectAssetTotalQuantity", assetId);
		}catch (Exception e) {
			logger.error("Exception In getAssetCurrentQuantity(int assetId) of AcademicsDAOImpl : " ,e);
		} finally {
			session.close();
		}
		return assetConsumption;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public List<CourseType> getCourseTypeList() throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		List<CourseType> courseTypeList = null;
		try {			
			courseTypeList = session.selectList("getCourseTypeList");
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception In getCourseTypeList() of AcademicsDAOImpl : " ,e);
		} finally {
			session.close();
		}
		return courseTypeList;
	}

	@Override
	public String createCourseType(CourseType courseType) throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		String status = "fail";
		try {
			courseType.setObjectId(encryptDecrypt.encrypt("AcademicsDAOImpl"));										
			int insertStatus = session.insert("createCourseType",courseType);
			if(insertStatus>0){
				status="success";
			}
		}catch (Exception e) {
			e.printStackTrace();
			status = "fail";
			logger.error("Exception in createCourseType(CourseType courseType) of AcademicsDAOImpl ", e);
		} finally {
			session.close();
		}
		return status;
	}

	@Override
	public String editCourseType(CourseType courseType) throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		String status = "success";
		try {
			int updateStatus = session.update("editCourseType",courseType);
			if(updateStatus>0){
				status = "success";
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in createCourseType(CourseType courseType) of AcademicsDAOImpl ", e);
			status = "fail";
		} finally {
			session.close();
		}
		return status;
	}

	@Override
	public String createCourse(Course course) throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		String status=course.getCourseCode()+" Insertion Failed";
		try {
			course.setObjectId(encryptDecrypt.encrypt("AcademicsDAOImpl"));										
			int insertStatus = session.insert("createCourse",course);
			if(insertStatus>0){
				status=course.getCourseCode()+" Inserted Successfully";
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in createCourse(CourseType courseType) of AcademicsDAOImpl ", e);
			status=course.getCourseCode()+" Insertion Failed";
		} finally {
			session.close();
		}
		return status;
	}

	@Override
	public List<Course> getCourseList() throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		List<Course> courseList = null;
		try {			
			courseList = session.selectList("getCourseList");
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception In getCourseTypeList() of AcademicsDAOImpl : " ,e);
		} finally {
			session.close();
		}
		return courseList;
	}

	

	@Override
	public List<Student> getStudentsNameAndRollForCourse(String course) throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		List<Student> studentList = null;
		try {			
			studentList = session.selectList("getStudentsNameAndRollForCourse", course);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception In getCourseTypeList() of AcademicsDAOImpl : " ,e);
		} finally {
			session.close();
		}
		return studentList;
	}

	@Override
	public String createStudentCourseSubjectMapping(List<StudentCourseSubjectMapping> scsmList) throws CustomException {
		String updateStatus="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			if(null!=scsmList && scsmList.size()!=0){
				
				//System.out.println("list==="+scsmList.get(0).getSubjectList());
				session.insert("createStudentCourseSubjectMapping", scsmList);
				
			}
		}catch(Exception e) {
			updateStatus="fail";
			logger.error(e);
			e.printStackTrace();
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return updateStatus;
	}
	
	
	
	
	@Override
	public List<Student> getSubjectsMappedStudents(String course) throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		List<Student> studentList = null;
		try {			
			studentList = session.selectList("getSubjectsMappedStudents", course);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception In getCourseTypeList() of AcademicsDAOImpl : " ,e);
		} finally {
			session.close();
		}
		return studentList;
	}
	
	
	@Override
	public String getSubjectsStudiedByStudentInCourse(StudentCourseSubjectMapping scsm) throws CustomException {
		String subjects="";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			List<Subject> subjectList=session.selectList("getSubjectsStudiedByStudentInCourse", scsm);
			if(null!=subjectList && subjectList.size()!=0){
				for(Subject subject:subjectList){
					subjects=subjects+subject.getSubjectGroup()+"#@#"+subject.getSubjectCode()+"*~*";
				}
			}
		}catch(Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return subjects;
	}

	

	

	@Override
	public List<String> getExamsForCourse(String course) throws CustomException {
		List<String> examList=null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			examList=session.selectList("getExamsForCourse", course);			
		}catch(Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return examList;
	}

	@Override
	public String editCourse(AdmissionForm admissionForm) throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		String status = "success";
		try {
			int updateStatus = session.update("editCourse",admissionForm);
			if(updateStatus>0){
				status = "success";
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in createCourseType(CourseType courseType) of AcademicsDAOImpl ", e);
			status ="failed";
		} finally {
			session.close();
		}
		return status;
	}

	
	/***
	 * modified by naimisha.ghosh
	 * changes taken on 17062017**/
	
	@Override
	public int saveCourse(AdmissionForm admissionForm, String updatedBy) {
		int saveStatusForCourse = 0;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("saveCourse(AdmissionForm admissionForm) method in AcademicsDaoImpl");
			Utility util = new Utility();
			String existingCourseStatus = session.selectOne("checkingExistingCourseStatus", admissionForm);
			if (existingCourseStatus == null || existingCourseStatus.trim().length() == 0) {
				admissionForm.setUpdatedBy(updatedBy);
				Resource resource = new Resource();
				resource.setKlass(admissionForm.getCourseClass());
				resource.setAdmissionYear(admissionForm.getAdmissionFormYear());
			//	resource.setStream(admissionForm.getStreamName());
				
				admissionForm.setAdmissionFormObjectId(util.getBase64EncodedID("AcademicsDAOImpl"));
				admissionForm.setAdmissionFormCode("Course-CODE-CLS" + "-");
				admissionForm.setAdmissionFormDesc(admissionForm.getCourseName());
			//	System.out.println("Course start date = "+admissionForm.getCourseStartDate());
				//String startTime12hrFormat= admissionForm.getInterviewStartTime();
			//	String endTime12hrFormat= admissionForm.getInterviewEndTime();
			//	SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm:ss");
			 //   SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
			   // Date startTime = parseFormat.parse(startTime12hrFormat);
			   // Date endTime = parseFormat.parse(endTime12hrFormat);
			  //  String startTime24hrformat = displayFormat.format(startTime);
			   // String endTime24hrformat = displayFormat.format(endTime);
			   // admissionForm.setInterviewStartTime(startTime24hrformat);
			  //  admissionForm.setInterviewEndTime(endTime24hrformat);
				//admissionForm.setInterviewStartTime(admissionForm.getInterviewDate()+" "+admissionForm.getInterviewStartTime());
			//	admissionForm.setInterviewEndTime(admissionForm.getInterviewDate()+" "+admissionForm.getInterviewEndTime());
				//System.out.println("start::"+admissionForm.getInterviewStartTime()+"\nEnd:"+admissionForm.getInterviewEndTime());
				saveStatusForCourse = session.insert("insertIntoCourse",admissionForm);
				if(saveStatusForCourse != 0){
					saveStatusForCourse = session.update("updateSectionForCapacity", admissionForm);
				}
				/*if (admissionForm.getAttachment() != null) {
					Attachment achmnt = admissionForm.getAttachment();
					if (achmnt.getAttachmentName().trim().length() != 0) {
						achmnt.setAttachmentObjectId(util.getBase64EncodedID("AcademicsDaoImpl"));
						achmnt.setFolderObjectId(util.getBase64EncodedID("AcademicsDaoImpl"));
						achmnt.setUpdatedBy(updatedBy);
						achmnt.setStorageObjectId(util.getBase64EncodedID("AcademicsDaoImpl"));
						int docInsertStatus = session.insert("insertStaffUploadedDocumentDetails", achmnt);
						logger.info("In saveCourse(AdmissionForm admissionForm) method in Attachment Block In AcademicsDaoImpl::  Upload status  "	+ docInsertStatus);
					}
				}*/
				session.commit();
			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("Exception in saveCourse(AdmissionForm admissionForm) method in AcademicsDaoImpl",e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return saveStatusForCourse;
	}

	@Override
	public List<AdmissionForm> getAllCourseList() {
		List<AdmissionForm> courseList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("getCourseList() method in AcademicsDaoImpl");			
			courseList = session.selectList("selectCourseList");
		} catch (Exception e) {
			logger.error("Exception in getCourseList() method in AcademicsDaoImpl", e);
		} finally {
			session.close();
		}
		return courseList;
	}
	
	@Override
	public List<Student> getAllStudentsList() throws CustomException {
		SqlSession session =sqlSessionFactory.openSession();
		List<Student> studentList = null;
		try{
			studentList = session.selectList("selectAllStudentsList");
			//System.out.println("studentlist size::"+studentList.size());
		}catch(Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return studentList;
	}

	@Override
	public List<Subject> getSubjectFromStandardTeacherSubjectMapping(TeacherSubjectMapping teacherSubjectMapping) {
		SqlSession session =sqlSessionFactory.openSession();
		List<Subject> subjectList = null;
		try{
			subjectList = session.selectList("selectSubjectsListFromStandardTeacherSubjectMapping",teacherSubjectMapping);
			//System.out.println("subjectList size::"+subjectList.size());
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(e);
		}finally{
			session.close();
		}
		return subjectList;
	}
	
	/**done by naimisha
	 * changes taken on 11Jan 2017**/

	@Override
	public List<ExamType> getExamType() {
		List<ExamType> examTypeList = new ArrayList<ExamType>();
		SqlSession session = sqlSessionFactory.openSession();
		try {			
			examTypeList = session.selectList("getExamType");
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return examTypeList;
	}

	@Override
	public List<ExamType> saveExamType(ArrayList<ExamType> examTypeList) {
		List<ExamType> examTypeListDB = new ArrayList<ExamType>();
		SqlSession session = sqlSessionFactory.openSession();
		Utility util = new Utility();
		try {			
			for (ExamType et : examTypeList) {
				et.setExamTypeObjectId(util.getBase64EncodedID("AcademicsDaoImpl"));
				session.update("updateExamTypeName", et);
				session.commit();
				examTypeListDB = session.selectList("getExamType");
			}
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return examTypeListDB;
	}

	@Override
	public String saveExam(ArrayList<Exam> examList) throws CustomException {
		List<Exam> examListDB = new ArrayList<Exam>();
		SqlSession session = sqlSessionFactory.openSession();
		Utility util = new Utility();
		String insertStatus = "success";
		try {
				for (Exam exam : examList) {
				List<Course> courseList = exam.getCourseList();
				//System.out.println("courseList size=="+courseList.size());
				for (Course course : courseList) {
					exam.setObjectId(util.getBase64EncodedID("AcademicsDaoImpl"));
					//System.out.println("course=="+course);
					exam.setCourse(course);
					int i = session.insert("insertIntoExam", exam);
					session.commit();
				}
			}
			//examListDB = session.selectList("getExam");
				
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			insertStatus = "fail";
		}
		return insertStatus;
	}
	
	/**
	 * @author anup.roy
	 * this method is for get details of created exams from db
	 * 09.08.2017
	 * */
	
	@Override
	public List<Exam> getExamDetails() {
		List<Exam> examListDB = new ArrayList<Exam>();
		SqlSession session = sqlSessionFactory.openSession();
		try {			
			examListDB = session.selectList("getExam");
			/*List<ExamType> examTypeListDB = session.selectList("getExamType");
			
			if(examListDB != null && examListDB.size() !=0 ){
				for (Exam ex : examListDB) {
					ex.setExamTypeList(examTypeListDB);
				}
			}*/
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return examListDB;
	}

	@Override
	public List<Exam> savePromotionalExam(Exam exam) {
		List<Exam> examListDB = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {			
			//for (Exam ex : examList) {
				String examDB = session.selectOne("checkForPromotionalExam", exam);
				if (examDB == null) {
					session.update("updateForPromotionalExam", exam);
					session.commit();
				}
			//}
			examListDB = session.selectList("listPromotionalExam");
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return examListDB;
	}

	@Override
	public List<Exam> getPromotionalExamList() {
		List<Exam> examListDB = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {			
			examListDB = session.selectList("listPromotionalExam");
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return examListDB;
	}

	@Override
	public String submitEditedPromotionalExam(Exam ex) {
		int status = 0;
		String updateStatus = "success";
		SqlSession session = sqlSessionFactory.openSession();
		Utility util = new Utility();
		try {
				status = session.update("updatePromotionalExam", ex);
				session.commit();
			session.close();
			if(status == 0){
				updateStatus = "fail";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updateStatus;
	}

	@Override
	public List<Term> getTermsForACourse(String course) {
		List<Term> termListDB = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {			
			termListDB = session.selectList("listTermsAgainstACourse",course);
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return termListDB;
	}

	/**
	 * @author anup.roy
	 * this method is for save data via edit*/
	
	@Override
	public String editExam(Exam exam) {
		String updateStatus = "success";
		SqlSession session = sqlSessionFactory.openSession();
		//Utility util = new Utility();
		try {
			session.update("editExam", exam);
			session.commit();
			session.close();			
		} catch (Exception e) {
			updateStatus = "fail";
			e.printStackTrace();
		}
		return updateStatus;
	}

	@Override
	public List<Exam> getExamsForTermAndCourse(Exam exam) {
		List<Exam> examListDB = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {			
			examListDB = session.selectList("lisExamsAgainstACourseAndTerm",exam);
			//System.out.println("size==="+examListDB.size());
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return examListDB;
	}

	
	/*************Added By Naimisha 27022017**************/
	@Override
	public String getSubjectsForACourseAndTermAndTeacher(Course courseObj) {
		String subjects="";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			//System.out.println("coursecode=="+courseObj.getCourseCode());
			//System.out.println("termCode====="+courseObj.getCourseName());
			//System.out.println("section_code====="+courseObj.getCourseDesc());
			//System.out.println("updated by==="+courseObj.getUpdatedBy());
			List<Subject> subjectList = session.selectList("selectSubjectForCourseAndTermAndTeacher", courseObj);
			//System.out.println("subjectList====="+subjectList.size());
			if(null!=subjectList && subjectList.size()!=0){
				for(Subject subject:subjectList){
					subjects=subjects+subject.getSubjectCode()+"#@#"+subject.getSubjectName()+"*~*";
				}
			}
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
			//CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return subjects;
	}

	@Override
	public List<ExamMarks> getSubjectsAndMarksForCourseExamTerm(String standard, String exam, String term) {
		List<ExamMarks> examMarksListFinal = null;
		SqlSession session = sqlSessionFactory.openSession();
		try{
			Course course = new Course();
			course.setCourseCode(standard);
			course.setCourseName(term);
			List<Subject> subjectGroupList = session.selectList("selectSubjectForCourseAndTerm", course);
			//System.out.println("subjectGroupList size==="+subjectGroupList.size());
			if(null != subjectGroupList && subjectGroupList.size() != 0){
				examMarksListFinal = new ArrayList<ExamMarks>();
				for(Subject s : subjectGroupList){
					ExamMarks em = new ExamMarks();
					em.setStatus(s.getSubjectCode());
					em.setDesc(exam);
					em.setStandard(standard);
					em.setObjectId(term);
					//System.out.println("subject=="+em.getStatus());
					//System.out.println("exam===="+em.getDesc());
					//System.out.println("Course====="+em.getStandard());
					//System.out.println("term==="+em.getObjectId());
					em = session.selectOne("selectMarksForExamCourseAndTermSubject", em);
					if(null != em){
						examMarksListFinal.add(em);
					}else{
						em = new ExamMarks();
						Subject sub = new Subject();
						sub.setSubjectCode(s.getSubjectCode());
						sub.setSubjectName(s.getSubjectName());
						em.setSubject(sub);
						Exam e = new Exam();
						e.setExamCode(exam);
						e.setExamName(exam);
						em.setExam(e);
						em.setTheory(0);
						em.setTheoryPass(0);
						em.setPractical(0);
						em.setPracticalPass(0);
						em.setTotal(0);
						em.setPass(0);
						examMarksListFinal.add(em);
					}
				}
			}
			
			//System.out.println("examMarksListFinal size==="+examMarksListFinal.size());
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(e);
			//CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return examMarksListFinal;
	}
	
	/*************Added By Sourav 01032017**********/
	
	@Override
	public AcademicYear getCurrentAcademicYear() {
		logger.info("In getCurrentAcademicYear() method of AcademicsDaoImpl");
		AcademicYear academicYear = null;
		SqlSession session =sqlSessionFactory.openSession();
		try {
			academicYear = session.selectOne("selectCurrentSession");
			//System.out.println("in academicsDAOImpl line no 1444");
			//System.out.println(academicYear);
		} catch (Exception e) {
			logger.error("In getCurrentAcademicYear() method of AcademicsDaoImpl" + e);
		} finally {
			session.close();
		}
		return academicYear;
	}

	@Override
	public String createTerm(Term term) {
		SqlSession session = sqlSessionFactory.openSession();
		String status = "fail";
		try {
			term.setTermObjectId(encryptDecrypt.encrypt("AcademicsDAOImpl"));
			List<Term>termList = session.selectList("selectTermAgainstTermCode",term);/*work from home*/
			//System.out.println("termList size = "+termList.size());
			if(null != termList && termList.size() != 0){
				status = "duplicate";
				
			}else{
				int insertStatus = session.insert("createTerm",term);
				if(insertStatus > 0){
					status="success";
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in createTerm(Term Term) of AcademicsDAOImpl ", e);
		} finally {
			session.close();
		}
		return status;
	}

	@Override
	public List<Term> getTermList() throws CustomException {
		List<Term> termList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {			
			termList = session.selectList("listTerm");
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return termList;
	}

	/*@Override
	public List<Term> getTermNameForCourse(String course) throws CustomException {
		List<Term> termListDB = null;
		SqlSession session = sqlSessionFactory.openSession();
		System.out.println("in AcademicsDAOImpl line no 1491");
		try {			
			termListDB = session.selectList("getTermNameForCourse",course);
			System.out.println("in AcademicsDAOImpl line no 1494");
			System.out.println("list size : "+termListDB.size());
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return termListDB;

	}*/

	@Override
	public String editTermCourseSubjectMapping(CourseSubjectMapping termCourseSubjectMapping) throws CustomException {
		String status="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			termCourseSubjectMapping.setObjectId(encryptDecrypt.getBase64EncodedID("AcademicsDAOImpl"));
			if(null != termCourseSubjectMapping.getOldSubjectList() && termCourseSubjectMapping.getOldSubjectList().size()!= 0){
				session.update("inactiveTermCourseSubjectMapping", termCourseSubjectMapping);
			}
			//System.out.println("AcademicsDAOImpl line no 1513");
			if(null != termCourseSubjectMapping.getNewSubjectList() && termCourseSubjectMapping.getNewSubjectList().size()!=0){
				for(String newSubject:termCourseSubjectMapping.getNewSubjectList()){
					termCourseSubjectMapping.setSubject(newSubject);
					
					CourseSubjectMapping courseSubjectMappingCheckParam = new CourseSubjectMapping();
					courseSubjectMappingCheckParam.setSubject(newSubject);
					courseSubjectMappingCheckParam.setCourseCode(termCourseSubjectMapping.getCourseCode());
					courseSubjectMappingCheckParam.setTerm(termCourseSubjectMapping.getTerm());
					
					CourseSubjectMapping checker=session.selectOne("selectInactiveSubjectForCourseAndTerm",courseSubjectMappingCheckParam);
					
					if(null!=checker && null!=checker.getSubject()){
						session.update("updateTermCourseSubjectMapping", termCourseSubjectMapping);
					}else{
						session.insert("insertTermCourseSubjectMapping", termCourseSubjectMapping);
						//System.out.println("AcademicsDAOImpl line no 1529");
					}
				}
			}
			
		}catch(Exception e) {
//			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			status="fail";
			logger.error(e);
			e.printStackTrace();
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return status;
	}

	@Override
	public List<Subject> getSubjectForCourseAndTerm(Term term) throws CustomException {
		List<Subject> subjectList = null;
		SqlSession session = sqlSessionFactory.openSession();
		//System.out.println("in getSubjectForCourseAndTerm of AcademicsDAOImpl line no 1550");
		try {			
			subjectList = session.selectList("getSubjectForCourseAndTerm",term);
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return subjectList;
	}
	/*******************************03032017GFor Aminities Usage Of Student****************/
	@Override
	public List<Student> getStudentsForAllProgrammes() {
		SqlSession session =sqlSessionFactory.openSession();
		List<Student> studentList = null;
		try {
			studentList = session.selectList("selectStudentsForAllProgrammes");
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getStudentsAgainstStandardAndSection() In CommonDaoImpl.java: Exception" ,e);
		} finally {
			session.close();
		}
		return studentList;
	}

	@Override
	public Student getAminitiesUsageByStudent(String rollNumber) {
		SqlSession session =sqlSessionFactory.openSession();
		Student student = null;
		try {
			student = session.selectOne("selectAminitiesUsageByStudent",rollNumber);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getStudentsAgainstStandardAndSection() In CommonDaoImpl.java: Exception" ,e);
		} finally {
			session.close();
		}
		return student;
	}

	@Override
	public List<Student> getStudentsForAllProgrammesAndBatches(Course course) {
		SqlSession session =sqlSessionFactory.openSession();
		List<Student> studentList = null;
		try {
			//studentList = session.selectList("selectStudentsForAllProgrammes");
			if(course.getCourseName().equalsIgnoreCase("All")){
				studentList = session.selectList("selectStudentsForProgrammesAndAllBatches",course);
			}else{
				studentList = session.selectList("selectStudentsForProgrammesAndBatches",course);
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getStudentsForAllProgrammesAndBatches(course) In CommonDaoImpl.java: Exception" ,e);
		} finally {
			session.close();
		}
		return studentList;
	}
	
	
	
	@Override
	public Employee getTeacherAgainstCourse(Course course) {
		SqlSession session =sqlSessionFactory.openSession();
		Employee employee = null;
		try {
			employee = session.selectOne("getTeacherAgainstCourse",course);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getStudentsAgainstStandardAndSection() In CommonDaoImpl.java: Exception" ,e);
		} finally {
			session.close();
		}
		return employee;
	}

	
	/******Work From Home*********/
	@Override
	public String inactiveStandard(Standard standard) {
		String status="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			standard.setObjectId(encryptDecrypt.getBase64EncodedID("AcademicsDAOImpl"));
			String standardValueForSubjectMapping = session.selectOne("checkStandardSubjectMappingStatus", standard);
			String standardForFees = session.selectOne("checkStandardForFeesTemplateAmount", standard);
			String standardForAdmissionDrive = session.selectOne("checkStandardFromAdmissionDrive", standard);
			if(null != standardValueForSubjectMapping){
				status = "mapped";
			}else if(null != standardForFees){
				status = "mapped";
			}else if(null != standardForAdmissionDrive){
				status = "mapped";
			}else{
				System.out.println("within inactive");
				String standardValue = session.selectOne("checkStandardDetailsForAStandard",standard);
				if(null != standardValue){
					session.update("inactiveStandardDetails", standard);
				}
				
				session.update("inactiveStandard", standard);	
			}
			
		}catch(Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			status="fail";
			logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return status;
	}

	@Override
	public String editTerm(Term term) {
		SqlSession session = sqlSessionFactory.openSession();
		String status = "fail";
		try {
			term.setTermObjectId(encryptDecrypt.encrypt("AcademicsDAOImpl"));
			List<Term>termList = session.selectList("selectTermAgainstTermCode",term);/*work from home*/
			//System.out.println("termList size = "+termList.size());
			if(null != termList && termList.size() != 0){
				status = "duplicate";
				
			}else{
				int insertStatus = session.insert("editTerm",term);
				if(insertStatus > 0){
					status="success";
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in editTerm(Term Term) of AcademicsDAOImpl ", e);
		} finally {
			session.close();
		}
		return status;
	}
	
/*********koustav 21032017*********/	
@Override
	public String inactiveExam(Exam exam) {
		String status="success";
		SqlSession session =sqlSessionFactory.openSession();
		//System.out.println("DAO 1870");
		//System.out.println(exam);
		try{    
			
				session.update("inactiveExam", exam);	
		}catch(Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			status="fail";
			logger.error(e);
			e.printStackTrace();
		}finally{
			//System.out.println("in dao 1881");
			//System.out.println(status);
			session.close();
		}
		
		return status;
	}
	
	@Override
	public String inactiveTerm(Term term) {
		String status="success";
		SqlSession session =sqlSessionFactory.openSession();
		//System.out.println("DAO 1848");
		//System.out.println(term);
		try{
			
				session.update("inactiveTerm", term);	
		}catch(Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			status="fail";
			logger.error(e);
			e.printStackTrace();
		}finally{
			//System.out.println("in dao 1959");
			//System.out.println(status);
			session.close();
		}
		
		return status;
	}
	
	/**
	 * modified by kaustav.sen
	 * changes taken on 18042017
	 * **/	
	
	@Override
	public String inactiveDeleteCourse(Course course) {
		String status="success";
		SqlSession session =sqlSessionFactory.openSession();
		//System.out.println("DAO 1894");
		//System.out.println(course.getSerialId());
		try{    
			
				session.update("inactiveDeleteCourse", course);	
		}catch(Exception e) {
			
			status="fail";
			logger.error(e);
			e.printStackTrace();
		}finally{
			//System.out.println("in dao 1904");
			//System.out.println(status);
			session.close();
		}
		
		return status;
	}
	/*********Sourav21032017*********/
	@Override
	public String inactiveProgramType(CourseType courseType) {
		String status="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			courseType.setObjectId(encryptDecrypt.getBase64EncodedID("AcademicsDAOImpl"));
			session.update("inactiveProgramType", courseType);	
		}catch(Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			status="fail";
			logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return status;
	}
	
	@Override
	public List<Standard> getStandardsForAssignBatch(){
		SqlSession session =sqlSessionFactory.openSession();
		List<Standard> programList = null;
		try {
			programList = session.selectList("selectStandardListForAssignBatch");
			//System.out.println("standardList::"+programList);
		} catch (Exception e) {
			logger.error("Exception in getStandards() in CommonDaoImpl(): ", e);
		} finally {
			session.close();
		}
		return programList;
	}

	@Override
	public List<Subject> getSubjectsForACourseAndTeacher(Course courseObj) {
		SqlSession session =sqlSessionFactory.openSession();
		List<Subject> subjectList = null;
		try {
			subjectList = session.selectList("selectSubjectsForACourseAndTeacher",courseObj);
			//System.out.println("standardList::"+programList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in getSubjectsForACourseAndTeacher() in AcademicsDaoImpl(): ", e);
		} finally {
			session.close();
		}
		return subjectList;
	}
	
	@Override
	public List<Venue> getVenueListForExam() {
		SqlSession session =sqlSessionFactory.openSession();
		List<Venue> venueList = null;
		try {
			venueList = session.selectList("getVenueListForExam");
			//System.out.println("standardList::"+programList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in getSubjectsForACourseAndTeacher() in AcademicsDaoImpl(): ", e);
		} finally {
			session.close();
		}
		return venueList;
	}

	@Override
	public List<Algorithm> getAllAlgorithmList() {
		SqlSession session =sqlSessionFactory.openSession();
		List<Algorithm> algorithmList = null;
		try {
			algorithmList = session.selectList("getAllAlgorithmList");
			//System.out.println("standardList::"+programList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in getSubjectsForACourseAndTeacher() in AcademicsDaoImpl(): ", e);
		} finally {
			session.close();
		}
		return algorithmList;
	}

	@Override
	public String insertExternalExamination(Exam exam) {
		SqlSession session = sqlSessionFactory.openSession();
		String status = "success";
		try {
			exam.setObjectId(encryptDecrypt.encrypt("AcademicsDAOImpl"));
		
			int insertStatus = session.insert("createExternalExam",exam);
			if(insertStatus != 0){
				List<Course>courseList = exam.getCourseList();
				for(Course course : courseList){
					course.setObjectId(exam.getObjectId());
					int insertStatus2 = session.insert("insertIntoExamProgramMapping",course);
				}
				List<Venue>venueList = exam.getVenueList();
				for(Venue venue : venueList){
					venue.setObjectId(exam.getObjectId());
					int insertStatus3 =  session.insert("insertIntoExamVenueMappingForSeatingArrangements",venue);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			status = "fail";
			logger.error("Exception in insertExternalExamination(Exam exam) of AcademicsDAOImpl ", e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		} finally {
			session.close();
		}
		return status;
	}
	
	@Override
	public Venue getVenueDetailsAgainstVenueCode(String venueCode) {
		SqlSession session =sqlSessionFactory.openSession();
		Venue venue = null;
		try {
			 venue = session.selectOne("getVenueDetailsAgainstVenueCode",venueCode);
			//System.out.println("standardList::"+programList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in getSubjectsForACourseAndTeacher() in AcademicsDaoImpl(): ", e);
		} finally {
			session.close();
		}
		return venue;
	}
	
	@Override
	public List<Exam> getExternalExamList() {
		SqlSession session =sqlSessionFactory.openSession();
		List<Exam> examList = null;
		try {
			examList = session.selectList("getExternalExamList");
			//System.out.println("standardList::"+programList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in getExternalExamList() in AcademicsDaoImpl(): ", e);
		} finally {
			session.close();
		}
		return examList;
	}

	
	//Modified by naimisha 21092017
	@Override
	public Exam getVenueDetailsAgainstExam(String exam) {
		SqlSession session =sqlSessionFactory.openSession();
		Exam examObj = new Exam();
		try {
			System.out.println("exam==="+exam);
			examObj = session.selectOne("getVenueDetailsAgainstExam",exam);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in getVenueDetailsAgainstExam() in AcademicsDaoImpl(): ", e);
		} finally {
			session.close();
		}
		return examObj;
	}

	@Override
	public String saveAdmissionDrive(AdmissionForm admissionForm, String updatedBy) {
	String saveStatusForCourse = "success";
	SqlSession session = sqlSessionFactory.openSession();
	try {
		logger.info("setUpAdmissionDrive(AdmissionForm admissionForm) method in AcademicsDaoImpl");
		Utility util = new Utility();
		
			admissionForm.setUpdatedBy(updatedBy);
			
			
			admissionForm.setAdmissionFormObjectId(util.getBase64EncodedID("AcademicsDAOImpl"));
			System.out.println("course_code===="+admissionForm.getCourseCode());
			//AdmissionForm  admissionFormObj =  session.selectOne("generateAdmissionDriveCodeForAProgram",admissionForm);
			//
			 //
			session.selectOne("generateAdmissionDriveCodeForAProgram",admissionForm);
			System.out.println("Drive name===="+admissionForm.getAdmissionDriveName());
			//admissionForm.setAdmissionDriveName(admissionDriveName);
			 session.insert("setUpAdmissionDrive",admissionForm);
			
			
			session.commit();
		
	} catch (Exception e) {

		logger.error("Exception in setUpAdmissionDrive(AdmissionForm admissionForm) method in AcademicsDaoImpl",e);
		e.printStackTrace();
		saveStatusForCourse = "fail";
	} finally {
		session.close();
	}
	return saveStatusForCourse;
	}

	@Override
	public List<AdmissionForm> getAllCourseDetails() {
		SqlSession session =sqlSessionFactory.openSession();
		List<AdmissionForm> courseDetailsList = null;
		try {
			courseDetailsList = session.selectList("selectAdmissionDriveList");
			//System.out.println("standardList::"+programList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in getAllCourseDetails() in AcademicsDaoImpl(): ", e);
		} finally {
			session.close();
		}
		return courseDetailsList;
	}

	@Override
	public AdmissionForm getAllCourseDetailsForEdit(String courseDetailsCode) {
		SqlSession session =sqlSessionFactory.openSession();
		AdmissionForm courseDetails = null;
		try {
			courseDetails = session.selectOne("getAllCourseDetailsForEdit",courseDetailsCode);
			//System.out.println("standardList::"+programList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in courseDetailsCode() in AcademicsDaoImpl(): ", e);
		} finally {
			session.close();
		}
		return courseDetails;
	}

	@Override
	public String updateCourseDetailsForAdmissionDrive(AdmissionForm admissionForm) {
		String saveStatusForCourse = "success";
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("updateCourseDetailsForAdmissionDrive(AdmissionForm admissionForm) method in AcademicsDaoImpl");
			Utility util = new Utility();
		
			admissionForm.setAdmissionFormObjectId(util.getBase64EncodedID("AcademicsDAOImpl"));
		
			session.insert("updateCourseDetailsForAdmissionDrive",admissionForm);
				
				
			session.commit();
			
		} catch (Exception e) {

			logger.error("Exception in updateCourseDetailsForAdmissionDrive(AdmissionForm admissionForm) method in AcademicsDaoImpl",e);
			e.printStackTrace();
			saveStatusForCourse = "fail";
		} finally {
			session.close();
		}
		return saveStatusForCourse;
	}

	@Override
	public List<Course> getListOfProgramsToPublish() {
		SqlSession session =sqlSessionFactory.openSession();
		List<Course> courseList = null;
		try {
			courseList = session.selectList("getListOfProgramsToPublish");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in getListOfProgramsToPublish() in AcademicsDaoImpl(): ", e);
		} finally {
			session.close();
		}
		return courseList;
	}

	@Override
	public List<Course> listOfPublishedProgramsList() {
		SqlSession session =sqlSessionFactory.openSession();
		List<Course> courseList = null;
		try {
			courseList = session.selectList("listOfPublishedProgramsList");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in listOfPublishedProgramsList() in AcademicsDaoImpl(): ", e);
		} finally {
			session.close();
		}
		return courseList;
	}

	@Override
	public Course getProgramDriveDetailsForProgram(String courseCode) {
		SqlSession session =sqlSessionFactory.openSession();
		Course programDriveDetails = null;
		try {
			programDriveDetails = session.selectOne("programDriveDetailsForAProgram",courseCode);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in getProgramDriveDetailsForProgram() in AcademicsDaoImpl(): ", e);
		} finally {
			session.close();
		}
		return programDriveDetails;
	}
	
	/**New CBSE System start**/
	
	
	/***
	 * @author anup.roy
	 * this method is for getting all exam types
	 * */
	@Override
	public List<ExamType> getExamTypeNew() {
		SqlSession session = sqlSessionFactory.openSession();
		List<ExamType> examTypeList = null;
		try{
			examTypeList = session.selectList("getExamTypeNew");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return examTypeList;
	}
	
	/***
	 * @author anup.roy
	 * this method is for getting all courses for mapping with exam type and term
	 * */
	@Override
	public List<Course> getAllCourses() {
		SqlSession session = sqlSessionFactory.openSession();
		List<Course> courseList = null;
		try{
			courseList = session.selectList("getAllCourses");
		}catch (Exception e) {
			logger.info("Error in getAllCourses in AcademicsDaoImpl.java",e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return courseList;
	}
	
	/**
	 * @author anup.roy
	 * this method is for get all terms for map standard and exam types*/
	
	@Override
	public List<Term> getAllTermList() {
		SqlSession session = sqlSessionFactory.openSession();
		List<Term> termList = null;
		try{
			logger.info("In getAllTermList of AcademicsDaoImpl.java");
			termList = session.selectList("getAllTermsList");
		}catch (Exception e) {
			logger.error("Error in getAllTermList of AcademicsDaoImpl.java",e);
			e.printStackTrace();
		}
		return termList;
	}
	
	/**
	 * @author anup.roy
	 * this method is for getting all exam types w.r.t term
	 * */
	
	@Override
	public String getExamTypesForATerm(String term) {
		String examTypes = "";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			logger.info("In getExamTypesForATerm(term) of AcademicsDaoImpl.java");
			System.out.println("Term:"+term);
			List<ExamType> examTypeList = session.selectList("selectExamTypesForTerm", term);
			if(null != examTypeList && examTypeList.size()!=0){
				for(ExamType examType : examTypeList){
					examTypes = examTypes + examType.getExamTypeCode()+"#"+examType.getExamTypeName()+"*";
				}
			}
		}catch(Exception e) {
			logger.error("Error in getExamTypesForATerm(term) of AcademicsDaoImpl.java",e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return examTypes;
	}
	
	/**
	 * @author anup.roy
	 * this method is for view all exams*/
	
	@Override
	public List<Exam> getAllNewExams() {
		SqlSession session = sqlSessionFactory.openSession();
		List<Exam> examList = null;
		try{
			logger.info("In getAllNewExams method in AcademicsDaoImpl.java");
			examList = session.selectList("getAllNewExams");
		}catch (Exception e) {
			logger.error("Error in getAllNewExams method in AcademicsDaoImpl.java",e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return examList;
	}
	
	/**
	 * @author anup.roy
	 * this method is for submit the exam names*/
	
	@Override
	public String submitExamNew(Exam exam) {
		SqlSession session = sqlSessionFactory.openSession();
		String updateStatus = "fail";
		int status = 0;
		try{
			logger.info("In submitExamNew method of AcademicsDaoImpl.java");
			exam.setObjectId(encryptDecrypt.getBase64EncodedID("AcademicsDaoImpl"));
			status = session.insert("submitNewExam", exam);
			if(status != 0){
				updateStatus = "success";
			}
		}catch (Exception e) {
			logger.error("Error in submitExamNew method of AcademicsDaoImpl.java",e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return updateStatus;
	}
	
	@Override
	public List<Exam> getTermForStandard(String standard) {
		SqlSession session = sqlSessionFactory.openSession();
		List<Exam> termList = null;
		try {			
			termList = session.selectList("selectTermForStandard", standard);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception In getExamForStandard(String standard) AcademicsDAOImpl : " ,e);
		} finally {
			session.close();
		}
		return termList;
	}

	@Override
	public List<Exam> getExamForStandardAndTerm(Exam examObj) {
		SqlSession session = sqlSessionFactory.openSession();
		List<Exam> examList = null;
		try {			
			examList = session.selectList("selectExamForStandardAndTerm", examObj);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception In getExamForStandard(String standard) AcademicsDAOImpl : " ,e);
		} finally {
			session.close();
		}
		return examList;
	}

	/**
	 * modified by anup.roy
	 * this method submits the exam marks in new system*/
	
	@Override
	public String editIntoExamMarks(List<ExamMarks> examMarksList) {
		String updateStatus="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			if(null!=examMarksList && examMarksList.size()!=0)
				session.update("updateIntoExamMarks", examMarksList);
		}catch(Exception e) {
			updateStatus="fail";
			logger.error(e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return updateStatus;
	}
	
	

	@Override
	public List<ExamMarks> getSubjectsAndMarksForStandardAndExam(String standard, String exam)  {
		List<ExamMarks> examMarksListFinal = null;
		SqlSession session = sqlSessionFactory.openSession();
		try{
			List<String> subjectGroupList = session.selectList("selectSubjectGroupForAStandard", standard);
			
			if(null != subjectGroupList && subjectGroupList.size() != 0){
				examMarksListFinal = new ArrayList<ExamMarks>();
				for(String s : subjectGroupList){
					ExamMarks em = new ExamMarks();
					em.setStatus(s);
					em.setDesc(exam);
					em.setStandard(standard);
					em = session.selectOne("selectMarksForExamStandardAndSubjectGroupAndExam", em);
					if(null != em){
						examMarksListFinal.add(em);
					}else{
						em = new ExamMarks();
						Subject sub = new Subject();
						sub.setSubjectCode(s);
						sub.setSubjectName(s);
						em.setSubject(sub);
						Exam e = new Exam();
						e.setExamCode(exam);
						e.setExamName(exam);
						em.setExam(e);
						em.setTheory(0);
						em.setTheoryPass(0);
						em.setPractical(0);
						em.setPracticalPass(0);
						em.setTotal(0);
						em.setPass(0);
						examMarksListFinal.add(em);
					}
				}
			}
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return examMarksListFinal;
	}
	
	@Override
	public List<Exam> getExamsForStandard(String standard) throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		List<Exam> examList = null;
		try {			
			examList = session.selectList("selectExamsForStandard", standard);
		}catch (Exception e) {
			logger.error("Exception In getExamForStandard(String standard) AcademicsDAOImpl : " ,e);
		} finally {
			session.close();
		}
		return examList;
	}

	@Override
	public List<StudentResult> getMarksForStudents(StudentResult studentResult, String loggedInUser) throws CustomException {
		List<StudentResult> studentsSubjectsAndMarksList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try{
			studentsSubjectsAndMarksList = session.selectList("selectUploadedMarksForStudents", studentResult);
			if(null != studentsSubjectsAndMarksList && studentsSubjectsAndMarksList.size() != 0){
				List<String> academicAdmin = session.selectList("getAllResourceForRole", "ACADEMICS ADMINISTRATOR");
				List<String> academicUser = session.selectList("getAllResourceForAccessType", "Admin User");
				if(null == academicAdmin || academicAdmin.size() == 0){
					academicAdmin = new ArrayList<String>();
				}
				academicAdmin.addAll(academicUser);
				if(null == academicAdmin || academicAdmin.size() == 0){
					academicAdmin = new ArrayList<String>();
				}
				academicAdmin.add("superadmin");
				
				if(academicAdmin.contains(loggedInUser) || studentsSubjectsAndMarksList.get(0).getUpdatedBy().equalsIgnoreCase(loggedInUser))
					studentsSubjectsAndMarksList.get(0).setStatus("UPDATE");
				else
					studentsSubjectsAndMarksList.get(0).setStatus("NA");
			}else{
				List<String> studentRollList = session.selectList("selectAllRollForResult", studentResult);		// List<Integer> changed to List<String>	
				StudentResult selectedStudentReslt = session.selectOne("selectMarksForAllSubject", studentResult);
				
				if(null != selectedStudentReslt && null != studentRollList && studentRollList.size() != 0){
					studentsSubjectsAndMarksList = new ArrayList<StudentResult>();
					for(String rollNumberWithName : studentRollList){
						String rollAndName[] = rollNumberWithName.split("~");		// Roll number and names split.
						StudentResult studResult = new StudentResult();
						studResult.setRollNumber(rollAndName[0]);
						studResult.setName(rollAndName[1]);
						studResult.setTheory(selectedStudentReslt.getTheory());
						studResult.setTheoryPass(selectedStudentReslt.getTheoryPass());
						studResult.setPractical(selectedStudentReslt.getPractical());
						studResult.setPracticalPass(selectedStudentReslt.getPracticalPass());
						studResult.setTotal(selectedStudentReslt.getTotal());
						studResult.setPass(selectedStudentReslt.getPass());
						
						studentsSubjectsAndMarksList.add(studResult);
					}
					studentsSubjectsAndMarksList.get(0).setStatus("INSERT");
				}						
			}
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return studentsSubjectsAndMarksList;
	}

	@Override
	public List<CoScholasticResult> getStudentsForCoScholasticNew(CoScholasticResult coScholasticResult)   {
		List<CoScholasticResult> studentList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try{
			studentList = session.selectList("selectStudentsForCoScholasticNew", coScholasticResult);
		}catch(Exception e) {
			logger.error(e);
			
		}finally{
			session.close();
		}
		return studentList;
	}

	@Override
	public List<DescriptiveIndicatorSkill> getDescriptiveIndicatorHeadListNew()  {
		List<DescriptiveIndicatorSkill> descriptiveIndicatorHeadList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try{
			descriptiveIndicatorHeadList = session.selectList("selectDescriptiveIndicatorSkillListForNewPattern");
		}catch(Exception e) {
			logger.error(e);
		}finally{
			session.close();
		}
		return descriptiveIndicatorHeadList;
	}

	@Override
	public String saveCoScholasticResultNew(List<CoScholasticResult> studentList) {
		String insertStatus = "Insert Successful";
		SqlSession session = sqlSessionFactory.openSession();
		try{
			String roll = studentList.get(0).getRollNumber();
			String term = studentList.get(0).getStatus();
			Student student = new Student();
			student.setStandard(term);
			student.setSection(roll);
			List<CoScholasticResult> resultList = session.selectList("selectStudentsCoScholasticResultNew", student);
			if(null != resultList && resultList.size() !=0){
				if(null != studentList && studentList.size() != 0){
					session.update("deleteCoScholasticResultForNew", roll);
					session.update("insertCoScholasticResultForNew", studentList);
				}
			}else{
				if(null != studentList && studentList.size() != 0){
					session.update("insertCoScholasticResultForNew", studentList);
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			insertStatus = "Insert Failed";
			logger.error(e);
		}finally{
			session.close();
		}
		return insertStatus;
	}

	@Override
	public String editGradingSystemNew(List<GradingSystem> gradingSystemList) {
		String updateStatus="Update Successful";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			if(null!=gradingSystemList && gradingSystemList.size()!=0){
				for(GradingSystem gradingSystem:gradingSystemList){
					gradingSystem.setObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
				}
				List<GradingSystem> existingGradingSystemList = getGradingSystemForStandardNew(gradingSystemList.get(0).getStandard());
				if(null!=existingGradingSystemList && existingGradingSystemList.size()!=0)
					session.update("updateGradingSystemNew", gradingSystemList);
				else
					session.update("insertGradingSystemNew", gradingSystemList);
			}			
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			updateStatus="Update Failed";
			logger.error(e);
			
		}finally{
			session.close();
		}
		return updateStatus;
	}
	
	@Override
	public List<GradingSystem> getGradingSystemForStandardNew(String standard)   {
		List<GradingSystem> gradingSystemList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			gradingSystemList=session.selectList("selectGradingSystemListForStandardNew", standard);
		}catch(Exception e) {
			logger.error(e);
			
		}finally{
			session.close();
		}
		return gradingSystemList;
	}
	
	@Override
	public String getCoScolasticResultAgainsRollNumberNew(String roll, String loggedInUser, String term)  {
		String result = "NE";
		SqlSession session = sqlSessionFactory.openSession();
		try{
			Student student = new Student();
			student.setStandard(term);
			student.setSection(roll);
			List<CoScholasticResult> resultList = session.selectList("selectStudentsCoScholasticResultNew", student);
			if(null != resultList && resultList.size() != 0){
				List<String> academicAdmin = session.selectList("getAllResourceForRole", "ACADEMICS ADMINISTRATOR");				
				List<String> academicUser = session.selectList("getAllResourceForAccessType", "Admin User");
				if(null == academicAdmin || academicAdmin.size() == 0){
					academicAdmin = new ArrayList<String>();
				}
				academicAdmin.addAll(academicUser);
				academicAdmin.add("superadmin");
				
				/*if(null == academicAdmin || academicAdmin.size()==0){
					academicAdmin = new ArrayList<String>();
				}*/
				for(CoScholasticResult cr:resultList){
					result = result+"~"+cr.getHead()+"*"+cr.getGrade();
				}
				if(academicAdmin.contains(loggedInUser) || resultList.get(0).getUpdatedBy().equalsIgnoreCase(loggedInUser))
					result = result+"~"+"UPDATE";
				else
					result = result+"~"+"NA";
			}
		}catch(Exception e) {
			logger.error(e);
		
		}finally{
			session.close();
		}
		return result;
	}
	
	@Override
	public String editStudentResultNew(List<StudentResult> studentResultList, String insertUpdate)   {
		String updateStatus="Update Successful";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			if(null!=studentResultList && studentResultList.size()!=0){
				/*if(insertUpdate.equalsIgnoreCase("INSERT"))
					session.insert("insertStudentResult", studentResultList);
				else if(insertUpdate.equalsIgnoreCase("UPDATE"))
					session.update("updateStudentResult", studentResultList);*/
				
				List<StudentResult> studentMarksList  = session.selectList("selectUploadedMarksForStudentsNew", studentResultList.get(0));
				
				if(null==studentMarksList || studentMarksList.size()==0)					
					session.insert("insertIntoStudentResultNew", studentResultList);
				else //if(insertUpdate.equalsIgnoreCase("UPDATE"))
					session.update("updateIntoStudentResultNew", studentResultList);
			}
		}catch(Exception e) {
			updateStatus="Update Failed";
			logger.error(e);
			
			e.printStackTrace();
		}finally{
			session.close();
		}
		return updateStatus;
	}
	
	@Override
	public List<StudentResult> getMarksForStudentsNew(StudentResult studentResult, String loggedInUser)   {
		List<StudentResult> studentsSubjectsAndMarksList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try{
			studentsSubjectsAndMarksList = session.selectList("selectUploadedMarksForStudentsNew", studentResult);
			if(null != studentsSubjectsAndMarksList && studentsSubjectsAndMarksList.size() != 0){
				List<String> academicAdmin = session.selectList("getAllResourceForRole", "ACADEMICS ADMINISTRATOR");
				List<String> academicUser = session.selectList("getAllResourceForAccessType", "Admin User");
				if(null == academicAdmin || academicAdmin.size() == 0){
					academicAdmin = new ArrayList<String>();
				}
				academicAdmin.addAll(academicUser);
				if(null == academicAdmin || academicAdmin.size() == 0){
					academicAdmin = new ArrayList<String>();
				}
				academicAdmin.add("superadmin");
				
				if(academicAdmin.contains(loggedInUser) || studentsSubjectsAndMarksList.get(0).getUpdatedBy().equalsIgnoreCase(loggedInUser))
					studentsSubjectsAndMarksList.get(0).setStatus("UPDATE");
				else
					studentsSubjectsAndMarksList.get(0).setStatus("NA");
			}else{
				List<String> studentRollList = session.selectList("selectAllRollForResult", studentResult);		// List<Integer> changed to List<String>	
				StudentResult selectedStudentReslt = session.selectOne("selectMarksForAllSubjectNew", studentResult);
				
				if(null != selectedStudentReslt && null != studentRollList && studentRollList.size() != 0){
					studentsSubjectsAndMarksList = new ArrayList<StudentResult>();
					for(String rollNumberWithName : studentRollList){
						String rollAndName[] = rollNumberWithName.split("~");		// Roll number and names split.
						StudentResult studResult = new StudentResult();
						studResult.setRollNumber(rollAndName[0]);
						studResult.setName(rollAndName[1]);
						studResult.setTheory(selectedStudentReslt.getTheory());
						studResult.setTheoryPass(selectedStudentReslt.getTheoryPass());
						studResult.setPractical(selectedStudentReslt.getPractical());
						studResult.setPracticalPass(selectedStudentReslt.getPracticalPass());
						studResult.setTotal(selectedStudentReslt.getTotal());
						studResult.setPass(selectedStudentReslt.getPass());
						
						studentsSubjectsAndMarksList.add(studResult);
					}
					studentsSubjectsAndMarksList.get(0).setStatus("INSERT");
				}						
			}
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
			
		}finally{
			session.close();
		}
		return studentsSubjectsAndMarksList;
	}

	/**New CBSE System end**/


	/**
	 * @author anup.roy
	 * this method is for get list of scholastic types list*/
	@Override
	public List<SubjectGroup> getScholasticTypeList() {
		SqlSession session = sqlSessionFactory.openSession();
		List<SubjectGroup> scholasticTypeList = null;
		try{
			scholasticTypeList = session.selectList("getScholasticTypeList");
		}catch (Exception e) {
			logger.info("In getScholasticTypeList() of AcademicsDaoImpl"+e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return scholasticTypeList;
	}

	/*Added By ranita.sur on 03082017 for getting the strength of Student*/
	@Override
	public String getStrengthOfStudents(String standard,String section) {
		logger.info("In getStrengthOfStudents() method of AcademicsDaoImpl");
		SqlSession session =sqlSessionFactory.openSession();
		String strengthDetails = ""; 
		try {
			Map<String, String> map = new HashMap<String, String>();
	        map.put("standard", standard);
	        map.put("section", section);
	        
	        
			int strength = session.selectOne("selectStrengthOfStudent", map);
			strengthDetails+=Integer.toString(strength);
			} catch (Exception e) {
			e.printStackTrace();
			logger.error("In getStrengthOfStudents() method of AcademicsDaoImpl", e);
		} finally {
			session.close();
		}
		return strengthDetails;
	}

	/***
	 * @author anup.roy
	 * this method is for getting all standards for set up exam marks 
	 * */
	
	@Override
	public List<Standard> getStandardsForSetExamMarks() {
		SqlSession session = sqlSessionFactory.openSession();
		List<Standard> standardList = null;
		try{
			logger.info("In getStandardsForSetExamMarks method in AcademicsDaoImpl.java");
			standardList = session.selectList("getStandardsForSetExamMarks");
		}catch (Exception e) {
			logger.error("Error in getStandardsForSetExamMarks method in AcademicsDaoImpl.java",e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return standardList;
	}

	
	public Exam getPrograamDetailsAgainstExam(String exam) {
		SqlSession session =sqlSessionFactory.openSession();
		Exam examObj = new Exam();
		try {
			System.out.println("exam==="+exam);
			examObj = session.selectOne("getPrograamDetailsAgainstExam",exam);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in getPrograamDetailsAgainstExam() in AcademicsDaoImpl(): ", e);
		} finally {
			session.close();
		}
		return examObj;
	}
	
	
	//Added by naimisha 17102017
	@Override
	public List<Student> getStudentListForAProgram(String programCode){
		SqlSession session = sqlSessionFactory.openSession();
		List<Student> studentList = null;
		try {		
			System.out.println("programCode=="+programCode);
			studentList = session.selectList("getStudentListForAProgram", programCode);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception In getStudentListForAProgram() of AcademicsDAOImpl : " ,e);
		} finally {
			session.close();
		}
		return studentList;
	}

	@Override
	public String createClassTeacher(Standard standard) {
		String insertStatus="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			standard.setObjectId(encryptDecrypt.getBase64EncodedID("AcademicsDAOImpl"));
			int insertValu = session.insert("insertClassTeacher", standard);
		}catch(Exception e) {
			insertStatus="fail";
			logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return insertStatus;
	}

	@Override
	public List<StudentResult> getAllClassTeacherList() {
		SqlSession session = sqlSessionFactory.openSession();
		List<StudentResult> classTeacherList = null;
		try {		
			classTeacherList = session.selectList("getAllClassTeacherList");
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception In getAllClassTeacherList() of AcademicsDAOImpl : " ,e);
		} finally {
			session.close();
		}
		return classTeacherList;
	}
	
	@Override
	public String getExamNameFromExamTableTimeGrid(String exam) {
		String examName = "";
		SqlSession session = sqlSessionFactory.openSession();
		try {			
			examName = session.selectOne("getExamNameFromExamTableTimeGrid",exam);
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return examName;
	}
	
	@Override
	public List<Course> getProgramListAndSemeterForExamRoutine(String exam) {
		List<Course> courseList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {			
			courseList = session.selectList("getProgramListAndSemeterForExamRoutine",exam);
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courseList;
	}
	
	@Override
	public List<Subject> getSubjectForCourseAndTermForExamRoutine(Course course) {
		List<Subject> subjectList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {			
			subjectList = session.selectList("getSubjectForCourseAndTermForExamRoutine",course);
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return subjectList;
	}
	
	@Override
	public String insertIntoExamTableTimeGrid(List<Course> courseList) {
		String insertStatus="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			////standard.setObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			//session.insert("insertStandard", standard);
			for(int i= 0;i<courseList.size() ; i++){
				Course courseObject = courseList.get(i);
				Subject subject = new Subject();
				subject.setSubjectCode(i+"_0");
				subject.setSubjectName(courseObject.getCourseName());
				subject.setDesc(courseList.get(0).getSubjectList().get(0).getDesc());
				int insertStatusValue = session.insert("insertIntoExamTableTimeGrid", subject);
				List<Subject> subjectList =  courseObject.getSubjectList();
				for(int j = 0;j<subjectList.size();j++){
					Subject subjectObj =  subjectList.get(j);
					int index = j+1;
					subjectObj.setSubjectCode(i+"_"+index);
					int insertValue = session.insert("insertIntoExamTableTimeGrid", subjectObj);
				}
			}
		}catch(Exception e) {
			insertStatus="fail";
			logger.error(e);
			e.printStackTrace();
			//CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return insertStatus;
	}
	
	@Override
	public List<RoutineTableGridData> getAllRoutineTableGridDataAgainstExam(String exam) {
		SqlSession session = sqlSessionFactory.openSession();
		List<RoutineTableGridData> gridDataList = null;
		
		try {
			logger.info("getAllRoutineTableGridDataAgainstExam() method in AcademicsDaoImpl");			
			gridDataList = session.selectList("getAllRoutineTableGridDataAgainstExam",exam);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getAllRoutineTableGridDataAgainstExam() method in AcademicsDaoImpl", e);
		} finally {
			session.close();
		}
		
		return gridDataList;
	}
	
	@Override
	public TimeTableGridData getRoutineTableGridData(TimeTableGridData timeTableGridData) {
		SqlSession session = sqlSessionFactory.openSession();
		TimeTableGridData timeTableGridDataDB = null;
		
		try {
			logger.info("getRoutineTableGridData() method in BackOfficeDaoImpl");		
			timeTableGridDataDB = session.selectOne("getRoutineTableGridData", timeTableGridData);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getRoutineTableGridData() method in BackOfficeDaoImpl", e);
		}
		
		return timeTableGridDataDB;
	}

	@Override
	public void updateRoutineTableGridData(TimeTableGridData timeTableGridData) {
	SqlSession session = sqlSessionFactory.openSession();
		
		try {
			logger.info("updateRoutineTableGridData() method in BackOfficeDaoImpl");		
			session.update("updateRoutineTableGridData" , timeTableGridData);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("updateRoutineTableGridData() method in BackOfficeDaoImpl", e);
		}
		
		
	}

	@Override
	public void insertRoutineTableGridData(TimeTableGridData timeTableGridData) {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			logger.info("insertRoutineTableGridData() method in BackOfficeDaoImpl");		
			session.insert("insertRoutineTableGridData" , timeTableGridData);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("insertRoutineTableGridData() method in BackOfficeDaoImpl", e);
		}
		
	}
	
	@Override
	public List<SchoolEvent> getSchoolEventList(){
		List<SchoolEvent> schoolEventList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			System.out.println("In getSchoolEventList() of AcademicsDAOImpl");
			schoolEventList=session.selectList("schoolEventList");
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return schoolEventList;

	}
	
	@Override
	public String insertSchoolEvent(SchoolEvent schoolEvent){
		String insertStatus="success";
		String oldEventName = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			System.out.println("In insertSchoolEvent() of AcademicsDAOImpl");
			schoolEvent.setSchoolEventObjectId(encryptDecrypt.getBase64EncodedID("AcademicsDAOImpl"));
			
			/*Validate if the Event already exists*/
			String result = session.selectOne("validateEventName", schoolEvent.getEventName());
			System.out.println("RESULT from DB:"+result);
			if(Integer.parseInt(result) == 0){
				int insert = session.insert("insertSchoolEvent",schoolEvent);
				session.commit();
			}else{
				insertStatus="fail";
			}
		}catch(Exception e) {
			//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			insertStatus="fail";
			logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return insertStatus;
	
	}
	
	@Override
	public String getEventDescription(String eventName){
		String eventDescription = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			System.out.println("In getEventDescription() of AcademicsDAOImpl:"+eventName);
			eventDescription=session.selectOne("selectEventDescription",Integer.valueOf(eventName));
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		System.out.println("EventDesc "+eventDescription);
		return eventDescription;

	}
	
	@Override
	public String submitEventAchievement(List<EventAchievement> eventAcheivementList){
		String insertStatus="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			System.out.println("In submitEventAchievement() of AcademicsDAOImpl");
			int insert = session.insert("insertEventAchievement",eventAcheivementList);
			session.commit();
		}catch(Exception e) {
			//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			insertStatus="fail";
			logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return insertStatus;
	}

	@Override
	public SchoolEvent selectEventName(String eventCode){
		SchoolEvent schoolEvent = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			System.out.println("In getEventName() of AcademicsDAOImpl:"+eventCode);
			schoolEvent=session.selectOne("selectEventName",Integer.valueOf(eventCode));
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return schoolEvent;
	}
}
