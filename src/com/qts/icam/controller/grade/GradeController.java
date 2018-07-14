package com.qts.icam.controller.grade;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.google.gson.Gson;
import com.qts.icam.model.grade.GradingSystem;
import com.qts.icam.model.academics.Subject;
import com.qts.icam.model.common.SessionObject;
import com.qts.icam.model.common.Standard;
import com.qts.icam.service.common.CommonService;
import com.qts.icam.service.grade.GradeService;

@Controller
@SessionAttributes("sessionObject")
public class GradeController{
	public static Logger logger = Logger.getLogger(GradeController.class);
	
	@Autowired
	CommonService commonService;
	
	@Autowired
	GradeService gradeService;
	
	/**@author anup.roy
	 * this method is for getting you the view page of grading system **/
	
	@RequestMapping(value = "/createGradingSystem", method = RequestMethod.GET)
	public String createGradingSystem(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = "grade/createGradingSystem";
		try {
			List<Standard> standardList = gradeService.getAllProgrammes();
			//System.out.println("standard list in controller::"+standardList);
			model.addAttribute("standardList", standardList);
		} catch (Exception e){
			e.printStackTrace();
			logger.error("Exception in method createGradingSystem-GET of GradeController", e);
		}
		return strView;
	}
	
	/**@author anup.roy
	 * this method is to get all course name against a standard**/
	
	@RequestMapping(value = "/getCourseForStandard", method = RequestMethod.GET)
	public @ResponseBody
	String getCourseForStandard(@RequestParam("standard") String standard) {
		logger.info("Inside Method getSubjectsForStandard-GET of AcademicsController");
		String course = "";
		try {
			//System.out.println("standard:"+standard);
			course = commonService.getCourseForStandard(standard);
			//System.out.println("course::"+course);
			//List<Subject> subjectList = commonService.getSubject();
			/*course = course + "#*#";
			for(Subject subject : subjectList){
				course = course + "###"+ subject.getSubjectCode();
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in method getSubjectsForStandard-GET of AcademicsController", e);
		}
		return (new Gson().toJson(course));
	}
	
	@RequestMapping(value = "/getGradingSystemForCourse", method = RequestMethod.GET)
	public @ResponseBody
	String getGradingSystemForCourse(@RequestParam("course") String course) {
		String gs = "";
		try {
			//System.out.println("course:"+course);
			gs = gradeService.getGradingSystemForCourse(course);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in method getGradingSystemForCourse-GET of AcademicsController", e);
		}
		return (new Gson().toJson(gs));
	}
	
	@RequestMapping(value = "/editGradingSystem", method = RequestMethod.POST)
	public String editGradingSystem(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam(value="standard") String standard,
			@RequestParam(value="course") String course,
			@RequestParam(value="range") String []range,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method editGradingSystem-POST of AcademicsController");
			List<GradingSystem> gradingSystemList=new ArrayList<GradingSystem>();
			if(null!=range && range.length!=0){
				for(int i=0;i<range.length;i++){
					GradingSystem gradingSystem=new GradingSystem();
					gradingSystem.setStandard(standard);
					gradingSystem.setCourse(course);
					gradingSystem.setObjectId("Obj-id");
					gradingSystem.setRange(range[i]);
					if(null!=request.getParameter("grade"+range[i]) && request.getParameter("grade"+range[i]).length()!=0)
						gradingSystem.setGrade(request.getParameter("grade"+range[i]));
					if(null!=request.getParameter("point"+range[i]) && request.getParameter("point"+range[i]).length()!=0)
						gradingSystem.setGradePoint(request.getParameter("point"+range[i]));
					gradingSystem.setUpdatedBy(sessionObject.getUserId());
					
					gradingSystemList.add(gradingSystem);
				}
			}
			
			if(gradingSystemList.size()!=0){
				String updateStatus = gradeService.editGradingSystem(gradingSystemList);
				model.addAttribute("insertUpdateStatus", updateStatus);
			}
		} catch (Exception e){
			e.printStackTrace();
			logger.error("Exception in method editGradingSystem-POST of AcademicsController", e);
		}
		return createGradingSystem(request, response, model);
	}
}