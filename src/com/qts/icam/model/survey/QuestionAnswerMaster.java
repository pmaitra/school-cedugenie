package com.qts.icam.model.survey;

public class QuestionAnswerMaster {

	private QuestionMaster questionMaster;
	private AnswerMaster answerMaster;
	private String updatedBy;
	
	public QuestionMaster getQuestionMaster() {
		return questionMaster;
	}
	public void setQuestionMaster(QuestionMaster questionMaster) {
		this.questionMaster = questionMaster;
	}
	public AnswerMaster getAnswerMaster() {
		return answerMaster;
	}
	public void setAnswerMaster(AnswerMaster answerMaster) {
		this.answerMaster = answerMaster;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
}
