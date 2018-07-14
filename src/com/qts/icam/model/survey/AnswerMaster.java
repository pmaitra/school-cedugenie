package com.qts.icam.model.survey;

import java.util.List;

public class AnswerMaster {

	private List<Answer> answerList;
	private String objectId;

	public List<Answer> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<Answer> answerList) {
		this.answerList = answerList;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
}
