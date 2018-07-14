package com.qts.icam.service.survey;

import java.util.List;

import com.qts.icam.model.common.Student;
import com.qts.icam.model.survey.Answer;
import com.qts.icam.model.survey.Question;
import com.qts.icam.model.survey.QuestionAnswerMaster;
import com.qts.icam.model.survey.QuestionMaster;

public interface SurveyService {

	public String insertQuestionAnswerMaster(QuestionAnswerMaster questionAnswerMaster);

	public String insertFeedbackResult(Question question);

	public String addConfigureSurveyQuestion(Question question);

	public List<Question> getQuestionListNotMappedWithAnswer();

	public String addQuestionAnswer(List<Answer> answerList);

	public List<Question> getQuestionListMappedWithAnswer();

	public String addSurveyQuestionAnswer(List<Question> questionList);

	public List<QuestionMaster> fetchQuestionAnswerForXMLWrite();

	public List<Question> fetchSurveyForAProgramme(String programName, String userId);

	public Student getStudentInfoAgainstUserId(Student student);

	public Question checkSurveyConfigurationStatus(Question question);

	public Question checkQuestionStatus(Question question);



}
