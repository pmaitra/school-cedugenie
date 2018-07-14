package com.qts.icam.dao.grade;

import java.util.List;

import com.qts.icam.model.common.Standard;
import com.qts.icam.model.grade.GradingSystem;

public interface GradeDao{
	
	public List<Standard> getAllProgrammes();
	
	public String editGradingSystem(List<GradingSystem> gradingSystemList);

	public String getGradingSystemForCourse(String course);

	
	
}