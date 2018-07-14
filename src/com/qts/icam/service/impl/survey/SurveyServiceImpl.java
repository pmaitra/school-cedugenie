package com.qts.icam.service.impl.survey;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.qts.icam.dao.impl.survey.SurveyDAOImpl;
import com.qts.icam.dao.survey.SurveyDAO;
import com.qts.icam.model.common.Student;
import com.qts.icam.model.survey.Answer;
import com.qts.icam.model.survey.Question;
import com.qts.icam.model.survey.QuestionAnswerMaster;
import com.qts.icam.model.survey.QuestionMaster;
import com.qts.icam.service.survey.SurveyService;

@Service
public class SurveyServiceImpl implements SurveyService {

	public static Logger logger = Logger.getLogger(SurveyDAOImpl.class);
	
	@Autowired
	SurveyDAO surveyDAO ;
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String insertQuestionAnswerMaster(QuestionAnswerMaster questionAnswerMaster) {
		return surveyDAO.insertQuestionAnswerMaster(questionAnswerMaster);
	}

	@Override
	public String insertFeedbackResult(Question question) {
		return surveyDAO.insertFeedbackResult(question);
	}

	@Override
	public String addConfigureSurveyQuestion(Question question) {
		return surveyDAO.addConfigureSurveyQuestion(question);
	}

	@Override
	public List<Question> getQuestionListNotMappedWithAnswer() {
		return surveyDAO.getQuestionListNotMappedWithAnswer();
	}

	@Override
	public String addQuestionAnswer(List<Answer> answerList) {
		return surveyDAO.addQuestionAnswer(answerList);
	}

	@Override
	public List<Question> getQuestionListMappedWithAnswer() {
		return surveyDAO.getQuestionListMappedWithAnswer();
	}

	@Override
	public String addSurveyQuestionAnswer(List<Question> questionList) {
		return surveyDAO.addSurveyQuestionAnswer(questionList);
	}

	@Override
	public List<QuestionMaster> fetchQuestionAnswerForXMLWrite() {
		return surveyDAO.fetchQuestionAnswerForXMLWrite();
	}

	@Override
	public List<Question> fetchSurveyForAProgramme(String programName,String userId) {
		return surveyDAO.fetchSurveyForAProgramme(programName, userId);
	}

	@Override
	public Student getStudentInfoAgainstUserId(Student student) {
		return surveyDAO.getStudentInfoAgainstUserId(student);
	}

	@Override
	public Question checkSurveyConfigurationStatus(Question question) {
		return surveyDAO.checkSurveyConfigurationStatus(question);
	}

	@Override
	public Question checkQuestionStatus(Question question) {
		return surveyDAO.checkQuestionStatus(question);
	}

	

}
