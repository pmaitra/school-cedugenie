package com.qts.icam.service.impl.grade;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qts.icam.dao.grade.GradeDao;
import com.qts.icam.model.common.Standard;
import com.qts.icam.model.grade.GradingSystem;
import com.qts.icam.service.grade.GradeService;

@Service
public class GradeServiceImpl implements GradeService{

	@Autowired
	GradeDao gradeDao;
	
	@Override
	public List<Standard> getAllProgrammes() {
		return gradeDao.getAllProgrammes();
	}
	
	@Override
	public String editGradingSystem(List<GradingSystem> gradingSystemList){
		return gradeDao.editGradingSystem(gradingSystemList);
	}
	
	@Override
	public String getGradingSystemForCourse(String course){
		return gradeDao.getGradingSystemForCourse(course);
	}

	
	
}