package com.qts.icam.dao.impl.survey;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.qts.icam.dao.impl.backoffice.BackOfficeDAOImpl;
import com.qts.icam.dao.survey.SurveyDAO;
import com.qts.icam.model.common.Standard;
import com.qts.icam.model.common.Student;
import com.qts.icam.model.survey.Answer;
import com.qts.icam.model.survey.AnswerMaster;
import com.qts.icam.model.survey.Question;
import com.qts.icam.model.survey.QuestionAnswerMaster;
import com.qts.icam.model.survey.QuestionMaster;
import com.qts.icam.utility.customexception.CustomException;
import com.qts.icam.utility.encryptdecrypt.EncryptDecrypt;

@Repository
public class SurveyDAOImpl implements SurveyDAO{

private final static Logger logger = Logger.getLogger(BackOfficeDAOImpl.class);
	
	EncryptDecrypt encryptDecrypt = new EncryptDecrypt();
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	
	
	@Override
	public String insertQuestionAnswerMaster(QuestionAnswerMaster questionAnswerMaster) {
		String insertStatus="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			//questionAnswerMaster.setObjectId(encryptDecrypt.getBase64EncodedID("SurveyDAOImpl"));
			QuestionMaster questionMaster = questionAnswerMaster.getQuestionMaster();
			//questionMaster.set
			//List<Question>questionList = questionMaster.getQuestionList();
			String SurveyId = questionMaster.getQuestionList().get(0).getSurveyId();
			System.out.println("SurveyId====="+SurveyId);
			List<Question>questionList =  session.selectList("selectSurveyForQuestionMaster", SurveyId);
			System.out.println("questionList size==="+questionList.size());
			if(questionList.size() ==0){
				questionMaster.setObjectId(encryptDecrypt.getBase64EncodedID("SurveyDAOImpl"));
				session.insert("insertQuestionMaster", questionMaster);
				
				
				
				AnswerMaster answerMaster = questionAnswerMaster.getAnswerMaster();
				answerMaster.setObjectId(encryptDecrypt.getBase64EncodedID("SurveyDAOImpl"));
				session.insert("insertAnswerMaster", answerMaster);
			}else{
				insertStatus = "exists";
			}
			
			
			
		}catch(Exception e) {
			insertStatus="fail";
			logger.error(e);
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}finally{
			session.close();
		}
		return insertStatus;
	}



	@Override
	public String insertFeedbackResult(Question question) {
		String insertStatus="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			question.setObjectId(encryptDecrypt.getBase64EncodedID("SurveyDAOImpl"));
			
			session.insert("insertFeedBAckData", question);
				
			
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
	public String addConfigureSurveyQuestion(Question question) {
		String insertStatus="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			question.setObjectId(encryptDecrypt.getBase64EncodedID("SurveyDAOImpl"));
			
			session.insert("insertConfigureSurveyQuestion", question);
				
			
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
	public List<Question> getQuestionListNotMappedWithAnswer() {
		List<Question> Question = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			Question=session.selectList("selectQuestionListNotMappedWithAnswer");
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return Question;
	}


	/**
	 * naimisha 25052017**/
	
	@Override
	public String addQuestionAnswer(List<Answer> answerList) {
		String insertStatus="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			List<Answer>newAnswerList = new ArrayList<Answer>();
			for(Answer answer : answerList){
				answer.setObjectId(encryptDecrypt.getBase64EncodedID("SurveyDAOImpl"));
				newAnswerList.add(answer);
			}
			
			AnswerMaster answerMaster = new AnswerMaster();
			answerMaster.setAnswerList(newAnswerList);
			
			Question question = new Question();
			question.setQuestion(answerList.get(0).getQuestionId());
			question.setObjectId(encryptDecrypt.getBase64EncodedID("SurveyDAOImpl"));
			
			Question questionObj = session.selectOne("checkSuestionStatus",question);
			if(null == questionObj){
				session.insert("insertConfigureSurveyQuestion", question);
				
				session.insert("insertAnswerMaster", answerMaster);
			}else{
				insertStatus="duplicate";
			}

			
		}catch(Exception e) {
			insertStatus="fail";
			logger.error(e);
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}finally{
			session.close();
		}
		return insertStatus;
	}



	@Override
	public List<Question> getQuestionListMappedWithAnswer() {
		List<Question> QuestionList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			QuestionList = session.selectList("selectQuestionListMappedWithAnswer");
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return QuestionList;
	}



	@Override
	public String addSurveyQuestionAnswer(List<Question> questionList) {
		String insertStatus="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			List<Question>newQuestionList = new ArrayList<Question>();
			int maxId = session.selectOne("selectMaxSurveyQuestionAnswerId");
			String surveyId = "S_"+maxId;
			System.out.println("maxId===="+maxId);
			System.out.println("surveyId===="+surveyId);
			for(Question question : questionList){
				question.setObjectId(encryptDecrypt.getBase64EncodedID("SurveyDAOImpl"));
				question.setSurveyId(surveyId);
				newQuestionList.add(question);
			}
			
			QuestionMaster questionMaster = new QuestionMaster();
			questionMaster.setQuestionList(newQuestionList);
			
			session.insert("insertSurveyQuestionAnswer", questionMaster);
			
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
	public List<QuestionMaster> fetchQuestionAnswerForXMLWrite() {
		List<QuestionMaster> questionList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			questionList = session.selectList("fetchQuestionAnswerForXMLWrite");
			System.out.println("questionList======="+questionList.size());
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return questionList;
	}



	@Override
	public List<Question> fetchSurveyForAProgramme(String programName,String userId) {
		List<Question> questionList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			Question questionObj = new Question();
			questionObj.setCourse(programName);
			questionObj.setUpdatedBy(userId);
			questionList = session.selectList("fetchSurveyForAProgramme",questionObj);
			
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return questionList;
	}



	@Override
	public Student getStudentInfoAgainstUserId(Student student) {
		Student studentObj = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
		/*	Question questionObj = new Question();
			questionObj.setCourse(programName);
			questionObj.setUpdatedBy(userId);
			question = session.selectOne("fetchSurveyForAProgramme",questionObj);
			*/
			studentObj = session.selectOne("fetchStudentInfoAgainstUserId",student);
			
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return studentObj;
	}



	@Override
	public Question checkSurveyConfigurationStatus(Question question) {
		Question questionObj = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			questionObj = session.selectOne("checkSurveyConfigurationStatus",question);
			
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return questionObj;
	}



	@Override
	public Question checkQuestionStatus(Question question) {
		Question questionObj = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			questionObj = session.selectOne("checkSuestionStatus",question);
			
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return questionObj;
	}



	

}
