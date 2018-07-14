package com.qts.icam.service.grade;

import java.util.List;

import com.qts.icam.model.common.Standard;
import com.qts.icam.model.grade.GradingSystem;
import com.qts.icam.utility.customexception.CustomException;

public interface GradeService{
	
	public List<Standard> getAllProgrammes();
	
	public String editGradingSystem(List<GradingSystem> gradingSystemList) throws CustomException;

	public String getGradingSystemForCourse(String course) throws CustomException;

	
	
}