package com.qts.icam.dao.impl.grade;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qts.icam.dao.grade.GradeDao;
import com.qts.icam.model.common.Standard;
import com.qts.icam.model.grade.GradingSystem;

@Repository
public class GradeDaoImpl implements GradeDao{
	private final static Logger logger = Logger.getLogger(GradeDaoImpl.class);
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Override
	public List<Standard> getAllProgrammes() {
		SqlSession session =sqlSessionFactory.openSession();
		List<Standard> standardList = null;
		try {
			standardList = session.selectList("selectStandardList");
			//System.out.println("standardList::"+standardList);
		} catch (Exception e) {
			logger.error("Exception in getStandards() in CommonDaoImpl(): ", e);
		} finally {
			session.close();
		}
		return standardList;
	}
	
	@Override
	public String editGradingSystem(List<GradingSystem> gradingSystemList){
		String updateStatus = "success";
		SqlSession session = sqlSessionFactory.openSession();
		try{
			if(null!=gradingSystemList && gradingSystemList.size()!=0){				
				String existingGradingSystem = getGradingSystemForCourse(gradingSystemList.get(0).getStandard());
				if(null!=existingGradingSystem && existingGradingSystem.length()!=0)
					session.update("updateGradingSystem", gradingSystemList);
				else
					session.update("insertGradingSystem", gradingSystemList);
			}
		}catch(Exception e) {
			updateStatus="fail";
			logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return updateStatus;
	}
	
	@Override
	public String getGradingSystemForCourse(String course){
		String gradingSystem="";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			List<GradingSystem> gradingSystemList=session.selectList("selectGradingSystemListForCourse", course);
			if(null!=gradingSystemList && gradingSystemList.size()!=0){
				for(GradingSystem gs:gradingSystemList){
					gradingSystem=gradingSystem+gs.getRange()+"*"+gs.getGrade()+"*"+gs.getGradePoint()+"~";
				}
			}
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return gradingSystem;
	}

	
	
}