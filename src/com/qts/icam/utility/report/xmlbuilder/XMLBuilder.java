package com.qts.icam.utility.report.xmlbuilder;

import java.io.File;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.qts.icam.dao.report.ReportDAO;
import com.qts.icam.model.academics.CoScholasticResult;
import com.qts.icam.model.academics.StudentResult;
import com.qts.icam.model.academics.Subject;
import com.qts.icam.model.academics.SubjectGroup;
import com.qts.icam.model.backoffice.Exam;
import com.qts.icam.model.backoffice.StudentSubjectMapping;
import com.qts.icam.model.common.Candidate;
import com.qts.icam.model.common.CondemnationDetails;
import com.qts.icam.model.common.Section;
import com.qts.icam.model.common.SocialCategory;
import com.qts.icam.model.common.Standard;
import com.qts.icam.model.common.Student;
import com.qts.icam.model.erp.Employee;
import com.qts.icam.model.report.Report;

public class XMLBuilder {
	public static Logger logger = Logger.getLogger(XMLBuilder.class);
	
	@Autowired
	ReportDAO reportDAO;
	
	public void createXMLFileForExamResult(Report reportData) {

		try{
			File outDir = new File(reportData.getXmlFilePath()); 
			boolean isDirCreated = outDir.mkdirs();
			if (isDirCreated)
				logger.info("In FileUploadDownload class fileUpload() method: upload file folder location created.");
			else
				logger.info("In FileUploadDownload class fileUpload() method: upload file folder location already exist.");
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("root");
			doc.appendChild(rootElement);
		
			for(Student student : reportData.getStudentList()){
					
				Element studentList = doc.createElement("student");
				rootElement.appendChild(studentList);
				
				Element schoolname = doc.createElement("schoolname");
				schoolname.appendChild(doc.createTextNode((reportData.getSchoolDetails().getSchoolDetailsName() != null ? reportData.getSchoolDetails().getSchoolDetailsName() : "" )));
				studentList.appendChild(schoolname);
				
				Element academicYear = doc.createElement("academicYear");
				academicYear.appendChild(doc.createTextNode((String) (reportData.getAcademicYear().getAcademicYearName()!= null ? reportData.getAcademicYear().getAcademicYearName() : "" )));
				studentList.appendChild(academicYear);
				
				Element report = doc.createElement("report");
				report.appendChild(doc.createTextNode("MARKS STATEMENT"));
				studentList.appendChild(report);
			
				Element termdate = doc.createElement("termdate");
				termdate.appendChild(doc.createTextNode((reportData.getSchoolDetails().getExamName()!=null?reportData.getSchoolDetails().getExamName():"--------")));
				studentList.appendChild(termdate);
				
				Element roll = doc.createElement("roll");
				roll.appendChild(doc.createTextNode((student.getRollNumber().toString()!=null?student.getRollNumber().toString():"---------")));
				studentList.appendChild(roll);
				
				// nickname elements
				Element studentname = doc.createElement("studentname");
				studentname.appendChild(doc.createTextNode((student.getStudentName()!=null?student.getStudentName():"---------------")));
				studentList.appendChild(studentname);
			
				
				Element house = doc.createElement("house");
				house.appendChild(doc.createTextNode((student.getHouse()!=null?student.getHouse():"------")));
				studentList.appendChild(house);
			
				Element standard = doc.createElement("standard");
				standard.appendChild(doc.createTextNode((student.getStandard()!=null?student.getStandard():"------")));
				studentList.appendChild(standard);
			
				Element section = doc.createElement("section");
				section.appendChild(doc.createTextNode((student.getSection()!=null?student.getSection():"--------")));
				studentList.appendChild(section);
				if(student.getResource()!=null){
					Element fathername = doc.createElement("fathername");
					fathername.appendChild(doc.createTextNode((student.getResource().getFatherFirstName()!=null?student.getResource().getFatherFirstName():"--------")));
					studentList.appendChild(fathername);
					
					Element mothername = doc.createElement("mothername");
					mothername.appendChild(doc.createTextNode((student.getResource().getMotherFirstName()!=null?student.getResource().getMotherFirstName():"--------")));
					studentList.appendChild(mothername);
					
					Element dob = doc.createElement("dob");
					dob.appendChild(doc.createTextNode((student.getResource().getDateOfBirth()!=null?student.getResource().getDateOfBirth():"--------")));
					studentList.appendChild(dob);
				}
				
				
				
				
				for(StudentResult studentResult : student.getStudentResultList()){
					Element subject = doc.createElement("subject");
					studentList.appendChild(subject);
					
					Element subjectname = doc.createElement("subjectname");
					subjectname.appendChild(doc.createTextNode((studentResult.getSubject()!=null?studentResult.getSubject():"")));
					subject.appendChild(subjectname);
					for(Exam exam:studentResult.getExamList() ){
						if(exam.getStatus().equals("FA")|| exam.getStatus().equals("SA1")){
							if(exam.getExamName().equals("FA1")){
								Element weightage = doc.createElement("FA1weightage");
								weightage.appendChild(doc.createTextNode((exam.getGrade()!=null ?exam.getGrade():"")));
								subject.appendChild(weightage);
								
								Element weightageObtained = doc.createElement("FA1weightageObtained");
								weightageObtained.appendChild(doc.createTextNode((exam.getGradePoint()!=null?exam.getGradePoint():"")));
								subject.appendChild(weightageObtained);
							}
							if(exam.getExamName().equals("FA2")){
								Element weightage = doc.createElement("FA2weightage");
								weightage.appendChild(doc.createTextNode((exam.getGrade()!=null?exam.getGrade():"")));
								subject.appendChild(weightage);
								
								Element weightageObtained = doc.createElement("FA2weightageObtained");
								weightageObtained.appendChild(doc.createTextNode((exam.getGradePoint()!=null?exam.getGradePoint():"")));
								subject.appendChild(weightageObtained);
							}
							if(exam.getExamName().equals("SA1")){
								Element weightage = doc.createElement("SA1weightage");
								weightage.appendChild(doc.createTextNode((exam.getGrade()!=null?exam.getGrade():"")));
								subject.appendChild(weightage);
								
								Element weightageObtained = doc.createElement("SA1weightageObtained");
								weightageObtained.appendChild(doc.createTextNode((exam.getGradePoint()!=null?exam.getGradePoint():"")));
								subject.appendChild(weightageObtained);
							}
							if(exam.getExamName().equals("FA3")){
								Element weightage = doc.createElement("FA3weightage");
								weightage.appendChild(doc.createTextNode((exam.getGrade()!=null?exam.getGrade():"")));
								subject.appendChild(weightage);
								
								Element weightageObtained = doc.createElement("FA3weightageObtained");
								weightageObtained.appendChild(doc.createTextNode((exam.getGradePoint()!=null?exam.getGradePoint():"")));
								subject.appendChild(weightageObtained);
							}
							if(exam.getExamName().equals("FA4")){
								Element weightage = doc.createElement("FA4weightage");
								weightage.appendChild(doc.createTextNode((exam.getGrade()!=null?exam.getGrade():"")));
								subject.appendChild(weightage);
								
								Element weightageObtained = doc.createElement("FA4weightageObtained");
								weightageObtained.appendChild(doc.createTextNode((exam.getGradePoint()!=null?exam.getGradePoint():"")));
								subject.appendChild(weightageObtained);
							}
						}
						
						if(exam.getStatus().equals("SA2")){
							if(exam.getExamName().equals("FA1")){
								Element weightage = doc.createElement("FA1weightage");
								weightage.appendChild(doc.createTextNode((exam.getGrade()!=null?exam.getGrade():"-")));
								subject.appendChild(weightage);
								
								Element FA1exam_weightage = doc.createElement("FA1exam_weightage");
								FA1exam_weightage.appendChild(doc.createTextNode((exam.getWeightageObtained().toString()!=null?exam.getWeightageObtained().toString():"-")));
								subject.appendChild(FA1exam_weightage);
							}
							
							if(exam.getExamName().equals("FA2")){
								Element weightage = doc.createElement("FA2weightage");
								weightage.appendChild(doc.createTextNode((exam.getGrade()!=null?exam.getGrade():"-")));
								subject.appendChild(weightage);
								
								Element FA2exam_weightage = doc.createElement("FA2exam_weightage");
								FA2exam_weightage.appendChild(doc.createTextNode((exam.getWeightageObtained().toString()!=null?exam.getWeightageObtained().toString():"-")));
								subject.appendChild(FA2exam_weightage);
							}
							if(exam.getExamName().equals("SA1")){
								Element weightage = doc.createElement("SA1weightage");
								weightage.appendChild(doc.createTextNode((exam.getGrade()!=null?exam.getGrade():"-")));
								subject.appendChild(weightage);
								
								Element SA1exam_weightage = doc.createElement("SA1exam_weightage");
								SA1exam_weightage.appendChild(doc.createTextNode((exam.getWeightageObtained().toString()!=null?exam.getWeightageObtained().toString():"-")));
								subject.appendChild(SA1exam_weightage);
							}
							if(exam.getExamName().equals("term_1")){
								Element weightage = doc.createElement("Term1weightage");
								weightage.appendChild(doc.createTextNode((exam.getGrade()!=null?exam.getGrade():"-")));
								subject.appendChild(weightage);
								
								Element Term1exam_weightage = doc.createElement("Term1exam_weightage");
								Term1exam_weightage.appendChild(doc.createTextNode((exam.getWeightageObtained().toString()!=null?exam.getWeightageObtained().toString():"-")));
								subject.appendChild(Term1exam_weightage);
							}
							if(exam.getExamName().equals("FA3")){
								Element weightage = doc.createElement("FA3weightage");
								weightage.appendChild(doc.createTextNode((exam.getGrade()!=null?exam.getGrade():"-")));
								subject.appendChild(weightage);
								
								Element FA3exam_weightage = doc.createElement("FA3exam_weightage");
								FA3exam_weightage.appendChild(doc.createTextNode((exam.getWeightageObtained().toString()!=null?exam.getWeightageObtained().toString():"-")));
								subject.appendChild(FA3exam_weightage);
							}
							if(exam.getExamName().equals("FA4")){
								Element weightage = doc.createElement("FA4weightage");
								weightage.appendChild(doc.createTextNode((exam.getGrade()!=null?exam.getGrade():"-")));
								subject.appendChild(weightage);
								
								Element FA4exam_weightage = doc.createElement("FA4exam_weightage");
								FA4exam_weightage.appendChild(doc.createTextNode((exam.getWeightageObtained().toString()!=null?exam.getWeightageObtained().toString():"-")));
								subject.appendChild(FA4exam_weightage);
							}
							if(exam.getExamName().equals("SA2")){
								Element weightage = doc.createElement("SA2weightage");
								weightage.appendChild(doc.createTextNode((exam.getGrade()!=null?exam.getGrade():"-")));
								subject.appendChild(weightage);
								
								Element SA2exam_weightage = doc.createElement("SA2exam_weightage");
								SA2exam_weightage.appendChild(doc.createTextNode((exam.getWeightageObtained().toString()!=null?exam.getWeightageObtained().toString():"-")));
								subject.appendChild(SA2exam_weightage);
							}
							if(exam.getExamName().equals("term_2")){
								Element weightage = doc.createElement("Term2weightage");
								weightage.appendChild(doc.createTextNode((exam.getGrade()!=null?exam.getGrade():"-")));
								subject.appendChild(weightage);
								
								Element Term2exam_weightage = doc.createElement("Term2exam_weightage");
								Term2exam_weightage.appendChild(doc.createTextNode((exam.getWeightageObtained().toString()!=null?exam.getWeightageObtained().toString():"-")));
								subject.appendChild(Term2exam_weightage);
							}
							if(exam.getExamName().equals("total")){
								Element weightage = doc.createElement("Totalweightage");
								weightage.appendChild(doc.createTextNode((exam.getGrade()!=null?exam.getGrade():"-")));
								subject.appendChild(weightage);
								
								Element Totalexam_weightage = doc.createElement("Totalexam_weightage");
								Totalexam_weightage.appendChild(doc.createTextNode((exam.getWeightageObtained().toString()!=null?exam.getWeightageObtained().toString():"-")));
								subject.appendChild(Totalexam_weightage);
							}
							if(exam.getExamName().equals("fa1-4")){
								Element weightage = doc.createElement("FA1_4weightage");
								weightage.appendChild(doc.createTextNode((exam.getGrade()!=null?exam.getGrade():"-")));
								subject.appendChild(weightage);
						
							}
							if(exam.getExamName().equals("sa1-2")){
								Element weightage = doc.createElement("SA1-2weightage");
								weightage.appendChild(doc.createTextNode((exam.getGrade()!=null?exam.getGrade():"-")));
								subject.appendChild(weightage);
						
							}
							if(exam.getExamName().equals("overall")){
								Element weightage = doc.createElement("Overallweightage");
								weightage.appendChild(doc.createTextNode((exam.getGrade()!=null?exam.getGrade():"-")));
								subject.appendChild(weightage);
						
							}
							if(exam.getExamName().equals("grade_point")){
								Element weightage = doc.createElement("GradePointweightage");
								weightage.appendChild(doc.createTextNode((exam.getGrade()!=null?exam.getGrade():"-")));
								subject.appendChild(weightage);
							}
							
						}
					}
					
				}
				if(student.getStatus()!=null && student.getStatus().equals("SA2")){
//					System.out.println("@@....... "+student.getStatus());
					if(student.getCoScholasticResultList()!=null && student.getCoScholasticResultList().size()!=0){
//						System.out.println("##....... "+student.getCoScholasticResultList().size());
						for(CoScholasticResult csr : student.getCoScholasticResultList()){
//							System.out.println("$$$$....... "+csr.getHead());
							
							if((csr.getHead()!=null && csr.getHead().trim().equalsIgnoreCase("ATTENDANCE"))){
								Element attendance = doc.createElement("attendance");
								attendance.appendChild(doc.createTextNode((csr.getGrade()!= null ? csr.getGrade() : "-" )));
								studentList.appendChild(attendance);
								
								Element indicator_attendance = doc.createElement("indicator_attendance");
								indicator_attendance.appendChild(doc.createTextNode((csr.getIndicator()!= null ? csr.getIndicator() : "" )));
								studentList.appendChild(indicator_attendance);
							}
							
							if((csr.getHead()!=null && csr.getHead().trim().equalsIgnoreCase("WORK EDUCATION"))){
								Element workeducation = doc.createElement("workeducation");
								workeducation.appendChild(doc.createTextNode((csr.getGrade()!= null ? csr.getGrade() : "-" )));
								studentList.appendChild(workeducation);
								
								Element indicator_workeducation = doc.createElement("indicator_workeducation");
								indicator_workeducation.appendChild(doc.createTextNode((csr.getIndicator()!= null ? csr.getIndicator() : "" )));
								studentList.appendChild(indicator_workeducation);
							}
							
							if(csr.getHead()!=null && csr.getHead().trim().equalsIgnoreCase("ART EDUCATION")){
								Element arteducation = doc.createElement("arteducation");
								arteducation.appendChild(doc.createTextNode((csr.getGrade()!= null ? csr.getGrade() : "-" )));
								studentList.appendChild(arteducation);
								
								Element indicator_arteducation = doc.createElement("indicator_arteducation");
								indicator_arteducation.appendChild(doc.createTextNode((csr.getIndicator()!= null ? csr.getIndicator() : "" )));
								studentList.appendChild(indicator_arteducation);
							}
							
							if(csr.getHead()!=null && csr.getHead().trim().equalsIgnoreCase("THINKING SKILL")){
								Element thinkingskill = doc.createElement("thinkingskill");
								thinkingskill.appendChild(doc.createTextNode((csr.getGrade()!= null ? csr.getGrade() : "-" )));
								studentList.appendChild(thinkingskill);
								
								Element indicator_thinkingskill = doc.createElement("indicator_thinkingskill");
								indicator_thinkingskill.appendChild(doc.createTextNode((csr.getIndicator()!= null ? csr.getIndicator() : "" )));
								studentList.appendChild(indicator_thinkingskill);
							}
							
							if(csr.getHead()!=null && csr.getHead().trim().equalsIgnoreCase("SOCIAL SKILLS")){
								Element socialskills = doc.createElement("socialskills");
								socialskills.appendChild(doc.createTextNode((csr.getGrade()!= null ? csr.getGrade() : "-" )));
								studentList.appendChild(socialskills);
								
								Element indicator_socialskills = doc.createElement("indicator_socialskills");
								indicator_socialskills.appendChild(doc.createTextNode((csr.getIndicator()!= null ? csr.getIndicator() : "" )));
								studentList.appendChild(indicator_socialskills);
							}
							
							if(csr.getHead()!=null && csr.getHead().trim().equalsIgnoreCase("EMOTIONAL SKILLS")){
								Element emotionskills = doc.createElement("emotionskills");
								emotionskills.appendChild(doc.createTextNode((csr.getGrade()!= null ? csr.getGrade() : "-" )));
								studentList.appendChild(emotionskills);
								
								Element indicator_emotionskills = doc.createElement("indicator_emotionskills");
								indicator_emotionskills.appendChild(doc.createTextNode((csr.getIndicator()!= null ? csr.getIndicator() : "" )));
								studentList.appendChild(indicator_emotionskills);
							}
							
							if(csr.getHead()!=null && csr.getHead().trim().equalsIgnoreCase("TEACHERS")){
								Element attitudetowarsteachers = doc.createElement("attitudetowarsteachers");
								attitudetowarsteachers.appendChild(doc.createTextNode((csr.getGrade()!= null ? csr.getGrade() : "-" )));
								studentList.appendChild(attitudetowarsteachers);
								
								Element indicator_attitudetowarsteachers = doc.createElement("indicator_attitudetowarsteachers");
								indicator_attitudetowarsteachers.appendChild(doc.createTextNode((csr.getIndicator()!= null ? csr.getIndicator() : "" )));
								studentList.appendChild(indicator_attitudetowarsteachers);
							}
							
							if(csr.getHead()!=null && csr.getHead().trim().equalsIgnoreCase("SCHOOL MATES")){
								Element attitudetowarsschoolmates = doc.createElement("attitudetowarsschoolmates");
								attitudetowarsschoolmates.appendChild(doc.createTextNode((csr.getGrade()!= null ? csr.getGrade() : "-" )));
								studentList.appendChild(attitudetowarsschoolmates);
								
								Element indicator_attitudetowarsschoolmates = doc.createElement("indicator_attitudetowarsschoolmates");
								indicator_attitudetowarsschoolmates.appendChild(doc.createTextNode((csr.getIndicator()!= null ? csr.getIndicator() : "" )));
								studentList.appendChild(indicator_attitudetowarsschoolmates);
							}
							
							if(csr.getHead()!=null && csr.getHead().trim().equalsIgnoreCase("SCHOOL PROGRAMME")){
								Element attitudetowarsschoolprograme = doc.createElement("attitudetowarsschoolprograme");
								attitudetowarsschoolprograme.appendChild(doc.createTextNode((csr.getGrade()!= null ? csr.getGrade() : "-" )));
								studentList.appendChild(attitudetowarsschoolprograme);
								
								Element indicator_attitudetowarsschoolprograme = doc.createElement("indicator_attitudetowarsschoolprograme");
								indicator_attitudetowarsschoolprograme.appendChild(doc.createTextNode((csr.getIndicator()!= null ? csr.getIndicator() : "" )));
								studentList.appendChild(indicator_attitudetowarsschoolprograme);
							}
							
							if(csr.getHead()!=null && csr.getHead().trim().trim().equalsIgnoreCase("ENVIRONMENT")){
								Element attitudetowarsenvironment = doc.createElement("attitudetowarsenvironment");
								attitudetowarsenvironment.appendChild(doc.createTextNode((csr.getGrade()!= null ? csr.getGrade() : "-" )));
								studentList.appendChild(attitudetowarsenvironment);
								
								Element indicator_attitudetowarsenvironment = doc.createElement("indicator_attitudetowarsenvironment");
								indicator_attitudetowarsenvironment.appendChild(doc.createTextNode((csr.getIndicator()!= null ? csr.getIndicator() : "" )));
								studentList.appendChild(indicator_attitudetowarsenvironment);
								
							}
							
							if(csr.getHead()!=null && csr.getHead().trim().equalsIgnoreCase("VALUE SYSTEM")){
								Element valuesystem = doc.createElement("valuesystem");
								valuesystem.appendChild(doc.createTextNode((csr.getGrade()!= null ? csr.getGrade() : "-" )));
								studentList.appendChild(valuesystem);
								
								Element indicator_valuesystem = doc.createElement("indicator_valuesystem");
								indicator_valuesystem.appendChild(doc.createTextNode((csr.getIndicator()!= null ? csr.getIndicator() : "" )));
								studentList.appendChild(indicator_valuesystem);
							}
							
							if(csr.getHead()!=null && csr.getHead().trim().equalsIgnoreCase("SPORT/INDIGENIOUS SPORT")){
								Element healthyphysicaleducationsportindigenioussport = doc.createElement("healthyphysicaleducationsportindigenioussport");
								healthyphysicaleducationsportindigenioussport.appendChild(doc.createTextNode((csr.getGrade()!= null ? csr.getGrade() : "-" )));
								studentList.appendChild(healthyphysicaleducationsportindigenioussport);
								
								Element indicator_healthyphysicaleducationsportindigenioussport = doc.createElement("indicator_healthyphysicaleducationsportindigenioussport");
								indicator_healthyphysicaleducationsportindigenioussport.appendChild(doc.createTextNode((csr.getIndicator()!= null ? csr.getIndicator() : "" )));
								studentList.appendChild(indicator_healthyphysicaleducationsportindigenioussport);
							}
							
							if(csr.getHead()!=null && csr.getHead().trim().equalsIgnoreCase("SCOUTING AND GUIDING")){
								Element healthyphysicaleducationscoutingguiding = doc.createElement("healthyphysicaleducationscoutingguiding");
								healthyphysicaleducationscoutingguiding.appendChild(doc.createTextNode((csr.getGrade()!= null ? csr.getGrade() : "-" )));
								studentList.appendChild(healthyphysicaleducationscoutingguiding);
								
								Element indicator_healthyphysicaleducationscoutingguiding = doc.createElement("indicator_healthyphysicaleducationscoutingguiding");
								indicator_healthyphysicaleducationscoutingguiding.appendChild(doc.createTextNode((csr.getIndicator()!= null ? csr.getIndicator() : "" )));
								studentList.appendChild(indicator_healthyphysicaleducationscoutingguiding);
							}
							
							if(csr.getHead()!=null && csr.getHead().trim().equalsIgnoreCase("YOGA")){
								Element yoga = doc.createElement("yoga");
								yoga.appendChild(doc.createTextNode((csr.getGrade()!= null ? csr.getGrade() : "-" )));
								studentList.appendChild(yoga);
								
								Element indicator_yoga = doc.createElement("indicator_yoga");
								indicator_yoga.appendChild(doc.createTextNode((csr.getIndicator()!= null ? csr.getIndicator() : "" )));
								studentList.appendChild(indicator_yoga);
							}
							
							if(csr.getHead()!=null && csr.getHead().trim().equalsIgnoreCase("FIRST AID")){
								Element firstaid = doc.createElement("firstaid");
								firstaid.appendChild(doc.createTextNode((csr.getGrade()!= null ? csr.getGrade() : "-" )));
								studentList.appendChild(firstaid);
								
								Element indicator_firstaid = doc.createElement("indicator_firstaid");
								indicator_firstaid.appendChild(doc.createTextNode((csr.getIndicator()!= null ? csr.getIndicator() : "" )));
								studentList.appendChild(indicator_firstaid);
							}
							
							if(csr.getHead()!=null && csr.getHead().trim().equalsIgnoreCase("GARDENING/SHRAMDAAN")){
								Element gardeningorahramdan = doc.createElement("gardeningorahramdan");
								gardeningorahramdan.appendChild(doc.createTextNode((csr.getGrade()!= null ? csr.getGrade() : "-" )));
								studentList.appendChild(gardeningorahramdan);
								
								Element indicator_gardeningorahramdan = doc.createElement("indicator_gardeningorahramdan");
								indicator_gardeningorahramdan.appendChild(doc.createTextNode((csr.getIndicator()!= null ? csr.getIndicator() : "" )));
								studentList.appendChild(indicator_gardeningorahramdan);
							}
							
							if(csr.getHead()!=null && csr.getHead().trim().equalsIgnoreCase("GYMNASTICS")){
								Element gymnastics = doc.createElement("gymnastics");
								gymnastics.appendChild(doc.createTextNode((csr.getGrade()!= null ? csr.getGrade() : "-" )));
								studentList.appendChild(gymnastics);
								
								Element indicator_gymnastics = doc.createElement("indicator_gymnastics");
								indicator_gymnastics.appendChild(doc.createTextNode((csr.getIndicator()!= null ? csr.getIndicator() : "" )));
								studentList.appendChild(indicator_gymnastics);
							}
							
							if(csr.getHead()!=null && csr.getHead().trim().equalsIgnoreCase("SWIMMING")){
								Element swimming = doc.createElement("swimming");
								swimming.appendChild(doc.createTextNode((csr.getGrade()!= null ? csr.getGrade() : "-" )));
								studentList.appendChild(swimming);
								
								Element indicator_swimming = doc.createElement("indicator_swimming");
								indicator_swimming.appendChild(doc.createTextNode((csr.getIndicator()!= null ? csr.getIndicator() : "" )));
								studentList.appendChild(indicator_swimming);
							}
							
							if(csr.getHead()!=null && csr.getHead().trim().equalsIgnoreCase("LITERARY AND CREATIVE SKILL")){
								Element literaryandcreativeskill = doc.createElement("literaryandcreativeskill");
								literaryandcreativeskill.appendChild(doc.createTextNode((csr.getGrade()!= null ? csr.getGrade() : "-" )));
								studentList.appendChild(literaryandcreativeskill);
								
								Element indicator_literaryandcreativeskill = doc.createElement("indicator_literaryandcreativeskill");
								indicator_literaryandcreativeskill.appendChild(doc.createTextNode((csr.getIndicator()!= null ? csr.getIndicator() : "" )));
								studentList.appendChild(indicator_literaryandcreativeskill);
							}
							
							if(csr.getHead()!=null && csr.getHead().trim().equalsIgnoreCase("SCIENTIFIC SKILL")){
								Element scientificskill = doc.createElement("scientificskill");
								scientificskill.appendChild(doc.createTextNode((csr.getGrade()!= null ? csr.getGrade() : "-" )));
								studentList.appendChild(scientificskill);
								
								Element indicator_scientificskill = doc.createElement("indicator_scientificskill");
								indicator_scientificskill.appendChild(doc.createTextNode((csr.getIndicator()!= null ? csr.getIndicator() : "" )));
								studentList.appendChild(indicator_scientificskill);
							}
							
							if(csr.getHead()!=null && csr.getHead().trim().equalsIgnoreCase("ORGANIZATIONAL AND LEDGERSHIP SKILLS(CLUBS)")){
								Element organizationalandledgership = doc.createElement("organizationalandledgership");
								organizationalandledgership.appendChild(doc.createTextNode((csr.getGrade()!= null ? csr.getGrade() : "-" )));
								studentList.appendChild(organizationalandledgership);
								
								Element indicator_organizationalandledgership = doc.createElement("indicator_organizationalandledgership");
								indicator_organizationalandledgership.appendChild(doc.createTextNode((csr.getIndicator()!= null ? csr.getIndicator() : "" )));
								studentList.appendChild(indicator_organizationalandledgership);
							}
							
							if(csr.getHead()!=null && csr.getHead().trim().equalsIgnoreCase("COMMUNICATION TECHNOLOGY(ICT)")){
								Element communicationtechnology = doc.createElement("communicationtechnology");
								communicationtechnology.appendChild(doc.createTextNode((csr.getGrade()!= null ? csr.getGrade() : "-" )));
								studentList.appendChild(communicationtechnology);
								
								Element indicator_communicationtechnology = doc.createElement("indicator_communicationtechnology");
								indicator_communicationtechnology.appendChild(doc.createTextNode((csr.getIndicator()!= null ? csr.getIndicator() : "" )));
								studentList.appendChild(indicator_communicationtechnology);
							}
							
							if(csr.getHead()!=null && csr.getHead().trim().equalsIgnoreCase("AESTHETIC AND PERFORMING ART")){
								Element aestheticandperformingart = doc.createElement("aestheticandperformingart");
								aestheticandperformingart.appendChild(doc.createTextNode((csr.getGrade()!= null ? csr.getGrade() : "-" )));
								studentList.appendChild(aestheticandperformingart);
								
								Element indicator_aestheticandperformingart = doc.createElement("indicator_aestheticandperformingart");
								indicator_aestheticandperformingart.appendChild(doc.createTextNode((csr.getIndicator()!= null ? csr.getIndicator() : "" )));
								studentList.appendChild(indicator_aestheticandperformingart);
							}
							
							if(csr.getHead()!=null && csr.getHead().trim().equalsIgnoreCase("CLUB(ECO/INTIGRITY)")){
								Element clubecointigrity = doc.createElement("clubecointigrity");
								clubecointigrity.appendChild(doc.createTextNode((csr.getGrade()!= null ? csr.getGrade() : "-" )));
								studentList.appendChild(clubecointigrity);
								
								Element indicator_clubecointigrity = doc.createElement("indicator_clubecointigrity");
								indicator_clubecointigrity.appendChild(doc.createTextNode((csr.getIndicator()!= null ? csr.getIndicator() : "" )));
								studentList.appendChild(indicator_clubecointigrity);
							}
							
							
							
							
							
							if((csr.getHead()!=null && csr.getHead().trim().equalsIgnoreCase("VISUAL AND PERFORMING ARTS"))){
								Element visualandperformingarts = doc.createElement("visualandperformingarts");
								visualandperformingarts.appendChild(doc.createTextNode((csr.getGrade()!= null ? csr.getGrade() : "-" )));
								studentList.appendChild(visualandperformingarts);
								
								Element indicator_visualandperformingarts = doc.createElement("indicator_visualandperformingarts");
								indicator_visualandperformingarts.appendChild(doc.createTextNode((csr.getIndicator()!= null ? csr.getIndicator() : "" )));
								studentList.appendChild(indicator_visualandperformingarts);
								
							}
						
						
						}
					}
				}
				
			
			}
		
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);		
			StreamResult result = new StreamResult(new File(reportData.getXmlFilePath()+reportData.getXmlFileName()));
			
			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);			
			transformer.transform(source, result);
		
		} catch (ParserConfigurationException pce) {
			logger.error("",pce);
		} catch (TransformerException tfe) {
			logger.error(tfe);
		}
	}
	
	
	public void createXMLFileForNominalRoll(Report reportData, Student studentDataToBeShowned) {
		
		try{
			File outDir = new File(reportData.getXmlFilePath()); 
			boolean isDirCreated = outDir.mkdirs();
			if (isDirCreated)
				logger.info("In FileUploadDownload class fileUpload() method: upload file folder location created.");
			else
				logger.info("In FileUploadDownload class fileUpload() method: upload file folder location already exist.");
			
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	
			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("root");
			doc.appendChild(rootElement);

			for(Standard standard : reportData.getStandardList()){
				
				Element standardList = doc.createElement("standard");
				rootElement.appendChild(standardList);
				
				Element schoolname = doc.createElement("schoolname");
				schoolname.appendChild(doc.createTextNode(reportData.getSchoolDetails().getSchoolDetailsName()));
				standardList.appendChild(schoolname);
				
				Element report = doc.createElement("report");
				report.appendChild(doc.createTextNode("Nominal Roll List - ("+reportData.getAcademicYear().getAcademicYearCode()+")"));
				standardList.appendChild(report);
	
				Element standardName = doc.createElement("standardName");
				standardName.appendChild(doc.createTextNode(standard.getStandardName()));
				standardList.appendChild(standardName);
			
				for(Section section : standard.getSectionList()){
					Element sectionList = doc.createElement("section");	
					standardList.appendChild(sectionList);
					
					Element sectionName = doc.createElement("sectionName");
					sectionName.appendChild(doc.createTextNode(section.getSectionName()));
					sectionList.appendChild(sectionName);
					
					for(Student student : section.getStudentList()){
						Element studentList = doc.createElement("student");
						sectionList.appendChild(studentList);					
						
						if(studentDataToBeShowned.getRollNumber()!=null){
							Element rollNumber = doc.createElement("rollNumber");
							rollNumber.appendChild(doc.createTextNode(((student.getRollNumber().toString()== null ||student.getRollNumber().toString().trim().isEmpty()) ? "" : student.getRollNumber().toString())));
							studentList.appendChild(rollNumber);
						}
						
						if(studentDataToBeShowned.getStudentName()!=null){
							Element studentName = doc.createElement("studentName");
							studentName.appendChild(doc.createTextNode(((student.getStudentName()==null || student.getStudentName().toString().trim().isEmpty()) ? "" : student.getStudentName())));
							studentList.appendChild(studentName);
						}
						
						if(studentDataToBeShowned.getHouse()!=null){
							Element house = doc.createElement("house");
							house.appendChild(doc.createTextNode(((student.getHouse()==null || student.getHouse().toString().trim().isEmpty()) ? "" : student.getHouse())));
							studentList.appendChild(house);
						}
						
	// Commented because of formatting problem
	//					if(studentDataToBeShowned.getResource().getCategory()!=null){
	//						Element category = doc.createElement("category");
	//						category.appendChild(doc.createTextNode((student.getResource()==null || student.getResource().getCategory()==null || student.getResource().getCategory().toString().trim().isEmpty()) ? "" : student.getResource().getCategory()));
	//						studentList.appendChild(category);
	//					}
	//					
	//					if(studentDataToBeShowned.getStateOfDomicile()!=null){
	//						Element stateOfDomicile = doc.createElement("stateOfDomicile");
	//						stateOfDomicile.appendChild(doc.createTextNode((( null==student.getStateOfDomicile() || student.getStateOfDomicile().toString().trim().isEmpty()  ) ? "" : student.getStateOfDomicile())));
	//						studentList.appendChild(stateOfDomicile);
	//					}
	//					
	//					if(studentDataToBeShowned.getDateOfAdmission()!=null){
	//						Element dateOfAdmission = doc.createElement("dateOfAdmission");
	//						dateOfAdmission.appendChild(doc.createTextNode((( null==student.getDateOfAdmission() || student.getDateOfAdmission().toString().trim().isEmpty()) ? "" : student.getDateOfAdmission())));
	//						studentList.appendChild(dateOfAdmission);
	//					}
	//					
	//					if(studentDataToBeShowned.getResource().getDateOfBirth()!=null){
	//						Element dateOfBirth = doc.createElement("dateOfBirth");
	//						dateOfBirth.appendChild(doc.createTextNode(((student.getResource()==null || null==student.getResource().getDateOfBirth() || student.getResource().getDateOfBirth().toString().trim().isEmpty()) ? "" : student.getResource().getDateOfBirth())));
	//						studentList.appendChild(dateOfBirth);
	//					}
	//					
	//					if(studentDataToBeShowned.getSecondLanguage()!=null){
	//						Element secondLanguage = doc.createElement("secondLanguage");
	//						secondLanguage.appendChild(doc.createTextNode(((null==student.getSecondLanguage()|| student.getSecondLanguage().toString().trim().isEmpty()) ? "" : student.getSecondLanguage())));
	//						studentList.appendChild(secondLanguage);
	//					}
	//					
	//					if(studentDataToBeShowned.getResource().getFatherFirstName()!=null){
	//						Element fatherFirstName = doc.createElement("fatherFirstName");
	//						fatherFirstName.appendChild(doc.createTextNode(((student.getResource()==null || null==student.getResource().getFatherFirstName() || student.getResource().getFatherFirstName().toString().trim().isEmpty()) ? "" : student.getResource().getFatherFirstName())));
	//						studentList.appendChild(fatherFirstName);
	//					}
	//					
	//					if(studentDataToBeShowned.getResource().getMotherFirstName()!=null){
	//						Element motherFirstName = doc.createElement("motherFirstName");
	//						motherFirstName.appendChild(doc.createTextNode(((student.getResource()==null || null==student.getResource().getMotherFirstName() || student.getResource().getMotherFirstName().toString().trim().isEmpty()) ? "" : student.getResource().getMotherFirstName())));
	//						studentList.appendChild(motherFirstName);
	//					}
	//					
	//					if(studentDataToBeShowned.getResource().getMobile()!=null){
	//						Element fatherMobile = doc.createElement("fatherMobile");
	//						fatherMobile.appendChild(doc.createTextNode(((student.getResource()==null || null==student.getResource().getFatherMobile() || student.getResource().getFatherMobile().toString().trim().isEmpty()) ? "" : student.getResource().getFatherMobile())));
	//						studentList.appendChild(fatherMobile);
	//					}
						
					}
				}
			}
		
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);	
			StreamResult result = new StreamResult(new File(reportData.getXmlFilePath()+reportData.getXmlFileName()));

		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);
		} catch (ParserConfigurationException pce) {
			logger.error(pce);
	  	} catch (TransformerException tfe) {
	  		logger.error(tfe);
	  	}
	}



public void createXMLFileForStaffDetailsList(Report report, Employee employeeFromScreen) {
	try{
		File outDir = new File(report.getXmlFilePath()); 
		boolean isDirCreated = outDir.mkdirs();
		if (isDirCreated)
			logger.info("In FileUploadDownload class fileUpload() method: upload file folder location created.");
		else
			logger.info("In FileUploadDownload class fileUpload() method: upload file folder location already exist.");		
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		
			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("root");
			doc.appendChild(rootElement);
		
			
			Element schoolname = doc.createElement("schoolname");
			schoolname.appendChild(doc.createTextNode(report.getSchoolDetails().getSchoolDetailsName()));
			rootElement.appendChild(schoolname);
			
			Element reporText = doc.createElement("report");
			reporText.appendChild(doc.createTextNode("Employee Details List"));
			rootElement.appendChild(reporText);
			
			for(Employee employee : report.getEmployeeList()){
					
				Element employeeList = doc.createElement("employee");
				rootElement.appendChild(employeeList);
				
				
		
						
				if(employeeFromScreen.getEmployeeCode()!=null){
					Element employeeCode = doc.createElement("employeeCode");
					employeeCode.appendChild(doc.createTextNode(((employee.getEmployeeCode()== null ||employee.getEmployeeCode().trim().isEmpty()) ? "" : employee.getEmployeeCode())));
					employeeList.appendChild(employeeCode);
				}
				
				
				
				if(employeeFromScreen.getResource().getUserId()!=null){
					Element userID = doc.createElement("userID");
					userID.appendChild(doc.createTextNode((employee.getResource()==null || employee.getResource().getUserId()==null || employee.getResource().getUserId().trim().isEmpty()) ? "" : employee.getResource().getUserId()));
					employeeList.appendChild(userID);
				}
				
				if(employeeFromScreen.getResource().getName()!=null){
					Element name = doc.createElement("name");
					name.appendChild(doc.createTextNode((employee.getResource()==null || employee.getResource().getName()==null || employee.getResource().getName().trim().isEmpty()) ? "" :employee.getResource().getName()));
					employeeList.appendChild(name);
				}
				
				if(employeeFromScreen.getDesignation().getDesignationCode()!=null){
					Element designation = doc.createElement("designation");
					designation.appendChild(doc.createTextNode((employee.getDesignation()==null || employee.getDesignation().getDesignationCode()==null || employee.getDesignation().getDesignationCode().trim().isEmpty()) ? "" :employee.getDesignation().getDesignationCode()));
					employeeList.appendChild(designation);
				}
				
				
				if(employeeFromScreen.getResource().getGender()!=null){
					Element gender = doc.createElement("gender");
					gender.appendChild(doc.createTextNode((employee.getResource()==null || employee.getResource().getGender()==null || employee.getResource().getGender().trim().isEmpty()) ? "" :employee.getResource().getGender()));
					employeeList.appendChild(gender);
				}
				
				if(employeeFromScreen.getResource().getCategory()!=null){
					Element category = doc.createElement("category");
					category.appendChild(doc.createTextNode((employee.getResource()==null || employee.getResource().getCategory()==null || employee.getResource().getCategory().trim().isEmpty()) ? "" :employee.getResource().getCategory()));
					employeeList.appendChild(category);
				}
				
				if(employeeFromScreen.getResource().getDateOfBirth()!=null){
					Element dateOfBirth = doc.createElement("dateOfBirth");
					dateOfBirth.appendChild(doc.createTextNode((employee.getResource()==null || employee.getResource().getDateOfBirth()==null || employee.getResource().getDateOfBirth().trim().isEmpty()) ? "" :employee.getResource().getDateOfBirth()));
					employeeList.appendChild(dateOfBirth);
				}
				
				
				
				if(employeeFromScreen.getDateOfJoining()!=null){
					Element dateOfJoining = doc.createElement("dateOfJoining");
					dateOfJoining.appendChild(doc.createTextNode((( null==employee.getDateOfJoining() || employee.getDateOfJoining().toString().trim().isEmpty()  ) ? "" : employee.getDateOfJoining())));
					employeeList.appendChild(dateOfJoining);
				}
				
				if(employeeFromScreen.getQualificationSummary()!=null){
					Element qualificationSummary = doc.createElement("qualificationSummary");
					qualificationSummary.appendChild(doc.createTextNode((( null==employee.getQualificationSummary() || employee.getQualificationSummary().toString().trim().isEmpty()  ) ? "" : employee.getQualificationSummary())));
					employeeList.appendChild(qualificationSummary);
				}
				
				if(employeeFromScreen.getResource().getReligion()!=null){
					Element religion = doc.createElement("religion");
					religion.appendChild(doc.createTextNode((employee.getResource()==null || employee.getResource().getReligion()==null || employee.getResource().getReligion().trim().isEmpty()) ? "" :employee.getResource().getReligion()));
					employeeList.appendChild(religion);
				}
				
				if(employeeFromScreen.getDateOfRetirement()!=null){
					Element dateOfRetirement = doc.createElement("dateOfRetirement");
					dateOfRetirement.appendChild(doc.createTextNode((( null==employee.getDateOfRetirement() || employee.getDateOfRetirement().toString().trim().isEmpty()  ) ? "" : employee.getDateOfRetirement())));
					employeeList.appendChild(dateOfRetirement);
				}
				
				
				if(employeeFromScreen.getResource().getMobile()!=null){
					Element contactDetails = doc.createElement("contactDetails");
					contactDetails.appendChild(doc.createTextNode((employee.getResource()==null || employee.getResource().getMobile()==null || employee.getResource().getMobile().trim().isEmpty()) ? "" :employee.getResource().getMobile()));
					employeeList.appendChild(contactDetails);
				}
				
			}
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
		
			StreamResult result = new StreamResult(new File(report.getXmlFilePath()+report.getXmlFileName()));
		
			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);
		
			transformer.transform(source, result);
		
		
		  } catch (ParserConfigurationException pce) {
			  logger.error(pce);
		  } catch (TransformerException tfe) {
			  logger.error(tfe);
		  }
	
}


























public void createXMLFileForAdmitCard(Report report) {
	try{
		File outDir = new File(report.getXmlFilePath()); 
		boolean isDirCreated = outDir.mkdirs();
		if (isDirCreated)
			logger.info("In XMLBuilder class fileUpload() method: upload file folder location created.");
		else
			logger.info("In XMLBuilder class fileUpload() method: upload file folder location already exist.");


		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("root");
		doc.appendChild(rootElement);

		for(Candidate candidate : report.getCandidateList()){
	
			Element candidateList = doc.createElement("candidate");
			rootElement.appendChild(candidateList);

			Element schoolname = doc.createElement("schoolname");
			schoolname.appendChild(doc.createTextNode(report.getSchoolDetails().getSchoolDetailsName()));
			candidateList.appendChild(schoolname);

			Element admissionyear = doc.createElement("admissionyear");
			admissionyear.appendChild(doc.createTextNode(report.getAcademicYear().getAcademicYearName()));
			candidateList.appendChild(admissionyear);

			Element roll = doc.createElement("roll");
			roll.appendChild(doc.createTextNode(candidate.getAdmissionFormId()));
			candidateList.appendChild(roll);

			// nickname elements
			Element candidatename = doc.createElement("candidatename");
			candidatename.appendChild(doc.createTextNode(candidate.getCandidateName()));
			candidateList.appendChild(candidatename);


			Element dateOfBirth = doc.createElement("dateOfBirth");
			dateOfBirth.appendChild(doc.createTextNode(candidate.getResource().getDateOfBirth()));
			candidateList.appendChild(dateOfBirth);

			Element fathername = doc.createElement("fathername");
			fathername.appendChild(doc.createTextNode(candidate.getResource().getFatherFirstName()));
			candidateList.appendChild(fathername);

			Element address = doc.createElement("address");
			String correspondenceAddress =(candidate.getAddress().getPresentAddressLine()!=null?candidate.getAddress().getPresentAddressLine()+", ":"");
			correspondenceAddress = correspondenceAddress+"\n"+(candidate.getAddress().getPresentAddressCityVillage()!=null?candidate.getAddress().getPresentAddressCityVillage()+", ":"");
			correspondenceAddress = correspondenceAddress+""+(candidate.getAddress().getPresentAddressPostOffice()!=null?candidate.getAddress().getPresentAddressPostOffice()+", ":"");//;
			correspondenceAddress = correspondenceAddress+"\n"+(candidate.getAddress().getPresentAddressPoliceStation()!=null?candidate.getAddress().getPresentAddressPoliceStation()+", ":"");
			correspondenceAddress = correspondenceAddress+"\n"+(candidate.getAddress().getPresentAddressState()!=null?candidate.getAddress().getPresentAddressState()+", ":"");
			correspondenceAddress = correspondenceAddress+"\n"+(candidate.getAddress().getPresentAddressCountry()!=null?candidate.getAddress().getPresentAddressCountry()+"-":"");
			correspondenceAddress = correspondenceAddress+"\n"+(candidate.getAddress().getPresentAddressPinCode()!=null?candidate.getAddress().getPresentAddressPinCode():"");
			address.appendChild(doc.createTextNode(correspondenceAddress));
			candidateList.appendChild(address);

			Element elePermanentAddress = doc.createElement("permanentAddress");

			String permanentAddress = (candidate.getAddress().getPermanentAddressLine()!=null) ? candidate.getAddress().getPermanentAddressLine()+", " : "";
			permanentAddress = permanentAddress+"\n"+ (candidate.getAddress().getPermanentAddressCityVillage() != null  ? candidate.getAddress().getPermanentAddressCityVillage()+", " : "");
			permanentAddress = permanentAddress+"\n"+(candidate.getAddress().getPermanentAddressPostOffice() !=null ? candidate.getAddress().getPermanentAddressPostOffice()+", " : ""); 
			permanentAddress = permanentAddress+"\n"+(candidate.getAddress().getPermanentAddressPoliceStation() !=null  ? candidate.getAddress().getPermanentAddressPoliceStation()+", " : "");
			permanentAddress = permanentAddress+"\n"+(candidate.getAddress().getPermanentAddressState() !=null  ? candidate.getAddress().getPermanentAddressState()+", " : ""); 
			permanentAddress = permanentAddress+"\n"+(candidate.getAddress().getPermanentAddressCountry() !=null  ? candidate.getAddress().getPermanentAddressCountry()+"-" : ""); 
			permanentAddress = permanentAddress+"\n"+(candidate.getAddress().getPermanentAddressPinCode() !=null  ? candidate.getAddress().getPermanentAddressPinCode() : ""); 
			elePermanentAddress.appendChild(doc.createTextNode(permanentAddress));
			candidateList.appendChild(elePermanentAddress);

			Element centre = doc.createElement("centre");
			String centreDetails = report.getVenue().getVenueName();
			centreDetails = centreDetails+", "+report.getVenue().getAddress().getPresentAddressLine();

			centreDetails = centreDetails+",\n"+report.getVenue().getAddress().getPresentAddressCityVillage();
			centreDetails = centreDetails+","+report.getVenue().getAddress().getPresentAddressPostOffice();
			centreDetails = centreDetails+",\n"+report.getVenue().getAddress().getPresentAddressPoliceStation();
			centreDetails = centreDetails+",\n"+report.getVenue().getAddress().getPresentAddressState();
			centreDetails = centreDetails+","+report.getVenue().getAddress().getPresentAddressCountry();
			centreDetails = centreDetails+"-"+report.getVenue().getAddress().getPresentAddressPinCode();
			centre.appendChild(doc.createTextNode(centreDetails));
			candidateList.appendChild(centre);
			
			Element examdatetime = doc.createElement("examdatetime");
			examdatetime.appendChild(doc.createTextNode((examdatetime==null) ? "-----------" :report.getStartDate()));
			candidateList.appendChild(examdatetime);
		}

		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);

		StreamResult result = new StreamResult(new File(report.getXmlFilePath()+report.getXmlFileName()));

		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);
	
		transformer.transform(source, result);
	

	} catch (ParserConfigurationException pce) {
		logger.error(pce);
	} catch (TransformerException tfe) {
		logger.error(tfe);
	}
}




public void createXMLFileForCondemnationReport(Report report) {
	try{
		File outDir = new File(report.getXmlFilePath()); 
		boolean isDirCreated = outDir.mkdirs();
		if (isDirCreated)
			logger.info("In FileUploadDownload class fileUpload() method: upload file folder location created.");
		else
			logger.info("In FileUploadDownload class fileUpload() method: upload file folder location already exist.");		
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		
			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("root");
			doc.appendChild(rootElement);
		
			
			Element schoolname = doc.createElement("schoolname");
			schoolname.appendChild(doc.createTextNode(report.getSchoolDetails().getSchoolDetailsName()));
			rootElement.appendChild(schoolname);
			
			Element reporText = doc.createElement("report");
			reporText.appendChild(doc.createTextNode("Condemnation Report For The Session "+report.getCondemnation().getYear()));
			rootElement.appendChild(reporText);
			
			if((report.getCondemnation().getDepartment()!= null ||report.getCondemnation().getDepartment().getDepartmentName().trim().isEmpty())){
				Element department = doc.createElement("department");
				department.appendChild(doc.createTextNode(report.getCondemnation().getDepartment().getDepartmentName()));
				rootElement.appendChild(department);
			}
			
			
			if(report.getCondemnation().getCondemnationDetailsList()!=null && report.getCondemnation().getCondemnationDetailsList().size()!=0){
				for(CondemnationDetails condemnationDetails : report.getCondemnation().getCondemnationDetailsList()){
					
					Element condemnationDetailsList = doc.createElement("condemnationDetails");
					rootElement.appendChild(condemnationDetailsList);
					
					Element itemName = doc.createElement("itemName");
					itemName.appendChild(doc.createTextNode((condemnationDetails.getItemName()==null ||condemnationDetails.getItemName().trim().isEmpty()) ? "" :condemnationDetails.getItemName()));
					condemnationDetailsList.appendChild(itemName);
							
					Element unit = doc.createElement("unit");
					unit.appendChild(doc.createTextNode((condemnationDetails.getUnit()==null ||condemnationDetails.getUnit().trim().isEmpty()) ? "" :condemnationDetails.getUnit()));
					condemnationDetailsList.appendChild(unit);
					
					Element qtyproducedforcb = doc.createElement("qtyproducedforcb");
					qtyproducedforcb.appendChild(doc.createTextNode(Double.toString(condemnationDetails.getQtyProducedForCB())));
					condemnationDetailsList.appendChild(qtyproducedforcb);
					
					Element qtysentencedtoser = doc.createElement("qtysentencedtoser");
					qtysentencedtoser.appendChild(doc.createTextNode(Double.toString(condemnationDetails.getQtySentencedToSer())));
					condemnationDetailsList.appendChild(qtysentencedtoser);
					
					Element qtysentencedtorep = doc.createElement("qtysentencedtorep");
					qtysentencedtorep.appendChild(doc.createTextNode(Double.toString(condemnationDetails.getQtySentencedToRep())));
					condemnationDetailsList.appendChild(qtysentencedtorep);
					
					Element qtysentencedtouns = doc.createElement("qtysentencedtouns");
					qtysentencedtouns.appendChild(doc.createTextNode(Double.toString(condemnationDetails.getQtySentencedToUNS())));
					condemnationDetailsList.appendChild(qtysentencedtouns);
					
					Element dateofpurchase = doc.createElement("dateofpurchase");
					dateofpurchase.appendChild(doc.createTextNode((condemnationDetails.getDateOfPurchase()==null ||condemnationDetails.getDateOfPurchase().trim().isEmpty()) ? "" :condemnationDetails.getDateOfPurchase()));
					condemnationDetailsList.appendChild(dateofpurchase);
					
					Element rate = doc.createElement("rate");
					rate.appendChild(doc.createTextNode(Double.toString(condemnationDetails.getRate())));
					condemnationDetailsList.appendChild(rate);
					
					Element amount = doc.createElement("amount");
					amount.appendChild(doc.createTextNode(Double.toString(condemnationDetails.getAmount())));
					condemnationDetailsList.appendChild(amount);
					
					Element remarks = doc.createElement("remarks");
					remarks.appendChild(doc.createTextNode((condemnationDetails.getRemarks()==null ||condemnationDetails.getRemarks().trim().isEmpty()) ? "" :condemnationDetails.getRemarks()));
					condemnationDetailsList.appendChild(remarks);
				}	
			}
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
		
			StreamResult result = new StreamResult(new File(report.getXmlFilePath()+report.getXmlFileName()));
		
			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);
		
			transformer.transform(source, result);
		
		
		  } catch (ParserConfigurationException pce) {
			  logger.error(pce);
		  } catch (TransformerException tfe) {
			  logger.error(tfe);
		  }
}
		

public void createXMLFileForConsolidateExamResult(Report reportData) {
	try{
		File outDir = new File(reportData.getXmlFilePath()); 
		boolean isDirCreated = outDir.mkdirs();
		if (isDirCreated)
			logger.info("In XMLBuilder class fileUpload() method: upload file folder location created.");
		else
			logger.info("In XMLBuilder class fileUpload() method: upload file folder location already exist.");
		
		
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	
		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("root");
		doc.appendChild(rootElement);
		
		
		Element schoolname = doc.createElement("schoolname");
		schoolname.appendChild(doc.createTextNode((reportData.getSchoolDetails().getSchoolDetailsName() != null ? reportData.getSchoolDetails().getSchoolDetailsName() : "" )));
		rootElement.appendChild(schoolname);
		
		if(reportData.getSchoolDetails().getExamName().equals("FA1")||reportData.getSchoolDetails().getExamName().equals("FA2")||reportData.getSchoolDetails().getExamName().equals("FA3")||reportData.getSchoolDetails().getExamName().equals("FA4")){
			Element report = doc.createElement("report");
			report.appendChild(doc.createTextNode("CONSOLIDATED RESULT SHEET : "+reportData.getAcademicYear().getAcademicYearName() +" ("+(reportData.getSchoolDetails().getExamName())+")"));
			rootElement.appendChild(report);

		}
		if(reportData.getSchoolDetails().getExamName().equals("SA1")||reportData.getSchoolDetails().getExamName().equals("SA2")){
			Element report = doc.createElement("report");
			report.appendChild(doc.createTextNode("CONSOLIDATED RESULT SHEET : "+reportData.getAcademicYear().getAcademicYearName() +(reportData.getSchoolDetails().getExamName().equals("SA1")?"(TERM-I)":"(TERM-II)")));
			rootElement.appendChild(report);
		}
		
	
		Element termdate = doc.createElement("termdate");
		termdate.appendChild(doc.createTextNode((reportData.getSchoolDetails().getExamName()!=null?reportData.getSchoolDetails().getExamName():"--------")));
		rootElement.appendChild(termdate);
		
		Element standard = doc.createElement("standard");
		standard.appendChild(doc.createTextNode((reportData.getStudent().getStandard() !=null?reportData.getStudent().getStandard():"------")));
		rootElement.appendChild(standard);
	
		Element section = doc.createElement("section");
		section.appendChild(doc.createTextNode((reportData.getStudent().getSection()!=null?reportData.getStudent().getSection():"--------")));
		rootElement.appendChild(section);
	
		for(SubjectGroup subjectGroup : reportData.getSubjectGroupList()){
			Element subjectGroupList = doc.createElement("subjectgroup");
			rootElement.appendChild(subjectGroupList);
			
			Element subjectgroupname = doc.createElement("subjectgroupname");
			subjectgroupname.appendChild(doc.createTextNode((subjectGroup.getSubjectGroupName()!=null?subjectGroup.getSubjectGroupName():"----")));
			subjectGroupList.appendChild(subjectgroupname);
			}
		for(Student student : reportData.getStudentList()){
				
			Element studentList = doc.createElement("student");
			rootElement.appendChild(studentList);
	
			
			Element roll = doc.createElement("roll");
			roll.appendChild(doc.createTextNode((student.getRollNumber().toString()!=null?student.getRollNumber().toString():"---------")));
			studentList.appendChild(roll);
			
			// nickname elements
			Element studentname = doc.createElement("studentname");
			studentname.appendChild(doc.createTextNode((student.getStudentName()!=null?student.getStudentName():"---------------")));
			studentList.appendChild(studentname);
	
			if(reportData.getSchoolDetails().getExamName().equals("FA1")||reportData.getSchoolDetails().getExamName().equals("FA2")||reportData.getSchoolDetails().getExamName().equals("FA3")||reportData.getSchoolDetails().getExamName().equals("FA4")){
				String nodeValue = "";
				for(StudentResult studentResult : student.getStudentResultList()){
					Element subject = doc.createElement("subject");
					studentList.appendChild(subject);
					
					Element subjectname = doc.createElement("subjectname");
					subjectname.appendChild(doc.createTextNode((studentResult.getSubject()!=null?studentResult.getSubject():"-")));
					subject.appendChild(subjectname);
					
					Element fa1 = doc.createElement("grade");
					nodeValue = "";
					if(studentResult.getGrade() == null){
						nodeValue = "-";
					}else if(studentResult.getGrade().equals("-1")){
						nodeValue = "AB";
					}else{
						nodeValue = studentResult.getGrade().toString();
					}
					
//					fa1.appendChild(doc.createTextNode((studentResult.getGrade()!=null?studentResult.getGrade().toString():"-")));
					fa1.appendChild(doc.createTextNode(nodeValue));
					subject.appendChild(fa1);
					
					
					Element fa2 = doc.createElement("gradePoint");
					nodeValue = "";
					if(studentResult.getGradepoint() == null){
						nodeValue = "-";
					}else if(studentResult.getGradepoint() < 0){
						nodeValue = "AB";
					}else{
						nodeValue = String.valueOf(studentResult.getGradepoint().intValue());
					}
					
					fa2.appendChild(doc.createTextNode(nodeValue));
					subject.appendChild(fa2);
					
				}
			}
			if(reportData.getSchoolDetails().getExamName().equals("SA1")){
				String nodeValue = "";
				for(StudentResult studentResult : student.getStudentResultList()){
					Element subject = doc.createElement("subject");
					studentList.appendChild(subject);
					
					Element subjectname = doc.createElement("subjectname");
					subjectname.appendChild(doc.createTextNode((studentResult.getSubject()!=null?studentResult.getSubject():"-")));
					subject.appendChild(subjectname);
					
					
					Element fa1 = doc.createElement("FA1gradePoint");
					if(reportData.getStatus()!=null && reportData.getStatus().equals("grade")){
						if(studentResult.getStrFa1() == null){System.out.println("FA1 Grade is NULL");}
						nodeValue = "";
						if(studentResult.getStrFa1() == null){
							nodeValue = "-";
						}else if(studentResult.getStrFa1().equals("AB")){
							nodeValue = "AB";
						}else{
							nodeValue = studentResult.getStrFa1().toString();
						}
//						fa1.appendChild(doc.createTextNode((studentResult.getStrFa1()!=null?studentResult.getStrFa1().toString():"-")));
						fa1.appendChild(doc.createTextNode(nodeValue));
					}else{
						if(studentResult.getFa1() == null){System.out.println("FA1 is NULL");}
						nodeValue = "";
						if(studentResult.getFa1() == null){
							nodeValue = "-";
						}else if(studentResult.getFa1() == -1){
							nodeValue = "AB";
						}else{
							nodeValue = studentResult.getFa1().toString();
						}
//						fa1.appendChild(doc.createTextNode((studentResult.getFa1()!=null?studentResult.getFa1().toString():"-")));
						fa1.appendChild(doc.createTextNode(nodeValue));
					}
					subject.appendChild(fa1);
					
					
					Element fa2 = doc.createElement("FA2gradePoint");
					if(reportData.getStatus()!=null && reportData.getStatus().equals("grade")){
						if(studentResult.getStrFa2() == null){System.out.println("FA2 Grade is NULL");}
						nodeValue = "";
						if(studentResult.getStrFa2() == null){
							nodeValue = "-";
						}else if(studentResult.getStrFa2().equals("AB")){
							nodeValue = "AB";
						}else{
							nodeValue = studentResult.getStrFa2().toString();
						}
//						fa2.appendChild(doc.createTextNode((studentResult.getStrFa2()!=null?studentResult.getStrFa2().toString():"-")));
						fa2.appendChild(doc.createTextNode(nodeValue));
					}else{
						if(studentResult.getFa2() == null){System.out.println("FA2 is NULL");}
						nodeValue = "";
						if(studentResult.getFa2() == null){
							nodeValue = "-";
						}else if(studentResult.getFa2() == -1){
							nodeValue = "AB";
						}else{
							nodeValue = studentResult.getFa2().toString();
						}
						fa2.appendChild(doc.createTextNode(nodeValue));
					}
					subject.appendChild(fa2);
					
					Element sa1 = doc.createElement("SA1gradePoint");
					if(reportData.getStatus()!=null && reportData.getStatus().equals("grade")){
						if(studentResult.getStrSa1() == null){System.out.println("SA1 Grade is NULL");}
						nodeValue = "";
						if(studentResult.getStrSa1() == null){
							nodeValue = "-";
						}else if(studentResult.getStrSa1().equals("AB")){
							nodeValue = "AB";
						}else{
							nodeValue = studentResult.getStrSa1().toString();
						}
						sa1.appendChild(doc.createTextNode(nodeValue));
					}else{
						if(studentResult.getSa1() == null){System.out.println("SA1 is NULL");}
						nodeValue = "";
						if(studentResult.getSa1() == null){
							nodeValue = "-";
						}else if(studentResult.getSa1() == -1){
							nodeValue = "AB";
						}else{
							nodeValue = studentResult.getSa1().toString();
						}
						sa1.appendChild(doc.createTextNode(nodeValue));
					}
					subject.appendChild(sa1);
					
					
					Element total = doc.createElement("totalTerm1gradePoint");
					if(reportData.getStatus()!=null && reportData.getStatus().equals("grade")){
						total.appendChild(doc.createTextNode((studentResult.getStrGradepoint()!=null?studentResult.getStrGradepoint().toString():"-")));
					}else{
						total.appendChild(doc.createTextNode((studentResult.getGradepoint()!=null?studentResult.getGradepoint().toString():"-")));
					}
					subject.appendChild(total);
					
					Element grade = doc.createElement("term1grade");
					grade.appendChild(doc.createTextNode((studentResult.getGrade()!=null?studentResult.getGrade().toString():"-")));
					subject.appendChild(grade);
					
					Element gradepoint = doc.createElement("term1gradepoint");
					gradepoint.appendChild(doc.createTextNode((studentResult.getGradepoint()!=null?String.valueOf(studentResult.getGradepoint().intValue()):"-")));
					subject.appendChild(gradepoint);
				}
			}
			if(reportData.getSchoolDetails().getExamName().equals("SA2")){
				String nodeValue = "";
				for(StudentResult studentResult : student.getStudentResultList()){
					Element subject = doc.createElement("subject");
					studentList.appendChild(subject);
					
					Element subjectname = doc.createElement("subjectname");
					subjectname.appendChild(doc.createTextNode((studentResult.getSubject()!=null?studentResult.getSubject():"-")));
					subject.appendChild(subjectname);
					
					Element t1 = doc.createElement("T1gradePoint");
					if(reportData.getStatus()!=null && reportData.getStatus().equals("grade")){	
						nodeValue = "";
						if(studentResult.getStrT1() == null){
							nodeValue = "-";
						}else if(studentResult.getStrT1().equals("AB")){
							nodeValue = "AB";
						}else{
							nodeValue = studentResult.getStrT1().toString();
						}						
//						t1.appendChild(doc.createTextNode((studentResult.getStrT1()!=null?studentResult.getStrT1().toString():"-")));
						t1.appendChild(doc.createTextNode(nodeValue));
					}else{
						nodeValue = "";
						if(studentResult.getT1() == null){
							nodeValue = "-";
						}else if(studentResult.getT1() == -1){
							nodeValue = "AB";
						}else{
							nodeValue = studentResult.getT1().toString();
						}
//						t1.appendChild(doc.createTextNode((studentResult.getT1()!=null?studentResult.getT1().toString():"-")));
						t1.appendChild(doc.createTextNode(nodeValue));
					}
					subject.appendChild(t1);
					
					Element fa3 = doc.createElement("FA3gradePoint");
					if(reportData.getStatus()!=null && reportData.getStatus().equals("grade")){
						nodeValue = "";
						if(studentResult.getStrFa3() == null){
							nodeValue = "-";
						}else if(studentResult.getStrFa3().equals("AB")){
							nodeValue = "AB";
						}else{
							nodeValue = studentResult.getStrFa3().toString();
						}
//						fa3.appendChild(doc.createTextNode((studentResult.getStrFa3()!=null?studentResult.getStrFa3().toString():"-")));
						fa3.appendChild(doc.createTextNode(nodeValue));
					}else{
						nodeValue = "";
						if(studentResult.getFa3() == null){
							nodeValue = "-";
						}else if(studentResult.getFa3() == -1){
							nodeValue = "AB";
						}else{
							nodeValue = studentResult.getFa3().toString();
						}
//						fa3.appendChild(doc.createTextNode((studentResult.getFa3()!=null?studentResult.getFa3().toString():"-")));
						fa3.appendChild(doc.createTextNode(nodeValue));
					}
					subject.appendChild(fa3);
					
					Element fa4 = doc.createElement("FA4gradePoint");
					if(reportData.getStatus()!=null && reportData.getStatus().equals("grade")){
						nodeValue = "";
						if(studentResult.getStrFa4() == null){
							nodeValue = "-";
						}else if(studentResult.getStrFa4().equals("AB")){
							nodeValue = "AB";
						}else{
							nodeValue = studentResult.getStrFa4().toString();
						}
//						fa4.appendChild(doc.createTextNode((studentResult.getStrFa4()!=null?studentResult.getStrFa4().toString():"-")));
						fa4.appendChild(doc.createTextNode(nodeValue));
					}else{
						nodeValue = "";
						if(studentResult.getFa4() == null){
							nodeValue = "-";
						}else if(studentResult.getFa4() == -1){
							nodeValue = "AB";
						}else{
							nodeValue = studentResult.getFa4().toString();
						}
//						fa4.appendChild(doc.createTextNode((studentResult.getFa4()!=null?studentResult.getFa4().toString():"-")));
						fa4.appendChild(doc.createTextNode(nodeValue));
					}
					subject.appendChild(fa4);
					
					Element sa2 = doc.createElement("SA2gradePoint");
					if(reportData.getStatus()!=null && reportData.getStatus().equals("grade")){
						nodeValue = "";
						if(studentResult.getStrSa2() == null){
							nodeValue = "-";
						}else if(studentResult.getStrSa2().equals("AB")){
							nodeValue = "AB";
						}else{
							nodeValue = studentResult.getStrSa2().toString();
						}
//						sa2.appendChild(doc.createTextNode((studentResult.getStrSa2()!=null?studentResult.getStrSa2().toString():"-")));
						sa2.appendChild(doc.createTextNode(nodeValue));
					}else{
						nodeValue = "";
						if(studentResult.getSa2() == null){
							nodeValue = "-";
						}else if(studentResult.getSa2() == -1){
							nodeValue = "AB";
						}else{
							nodeValue = studentResult.getSa2().toString();
						}
//						sa2.appendChild(doc.createTextNode((studentResult.getSa2()!=null?studentResult.getSa2().toString():"-")));
						sa2.appendChild(doc.createTextNode(nodeValue));
					}
					subject.appendChild(sa2);
					
					Element total = doc.createElement("totalTerm1gradePoint");
					if(reportData.getStatus()!=null && reportData.getStatus().equals("grade")){
						total.appendChild(doc.createTextNode((studentResult.getStrTotalTerm1()!=null?studentResult.getStrTotalTerm1().toString():"-")));
					}else{
						total.appendChild(doc.createTextNode((studentResult.getTotalTerm1()!=null?studentResult.getTotalTerm1().toString():"-")));
					}
					subject.appendChild(total);
					
					Element grade = doc.createElement("term1grade");
					grade.appendChild(doc.createTextNode((studentResult.getGrade()!=null?studentResult.getGrade().toString():"-")));
					subject.appendChild(grade);
					
					Element gradepoint = doc.createElement("term1gradepoint");
					gradepoint.appendChild(doc.createTextNode((studentResult.getGradepoint()!=null?String.valueOf(studentResult.getGradepoint().intValue()):"-")));
					subject.appendChild(gradepoint);
					
					
				}
				Element cgpa = doc.createElement("cgpa");
				if(reportData.getStatus()!=null && reportData.getStatus().equals("grade")){
					cgpa.appendChild(doc.createTextNode((student.getStrCgpa()!=null? student.getStrCgpa().toString():"-")));
				}else{
					cgpa.appendChild(doc.createTextNode((student.getCgpa()!=null?new DecimalFormat("###.##").format(student.getCgpa()).toString():"-")));
				}
				studentList.appendChild(cgpa);
				
				
			}
			
			
			
		}
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		//System.out.println(source+"----------------------------"+doc);

	//	System.out.println("################################################## "+reportData.getXmlFilePath()+reportData.getXmlFileName());
		StreamResult result = new StreamResult(new File(reportData.getXmlFilePath()+reportData.getXmlFileName()));

		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);

		transformer.transform(source, result);

		//System.out.println("File saved!");

	  } catch (ParserConfigurationException pce) {
		  logger.error(pce);
	  } catch (TransformerException tfe) {
		  logger.error(tfe);
	  } catch (Exception e) {
		  logger.error(e);
	  }

}

public void createXMLFileForStudentAddressDetails(Report report) {
	try{
		File outDir = new File(report.getXmlFilePath()); 
		boolean isDirCreated = outDir.mkdirs();
		if (isDirCreated)
			logger.info("In FileUploadDownload class fileUpload() method: upload file folder location created.");
		else
			logger.info("In FileUploadDownload class fileUpload() method: upload file folder location already exist.");		
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		
			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("root");
			doc.appendChild(rootElement);
			
			Element studentList = doc.createElement("studentList");
			rootElement.appendChild(studentList);
			
			if(report.getStudentList()!=null && report.getStudentList().size()!=0){
				for(Student student : report.getStudentList()){
					
					Element studentAddressDetails = doc.createElement("student");
					studentList.appendChild(studentAddressDetails);
					
					Element studentName = doc.createElement("studentName");
					studentName.appendChild(doc.createTextNode((student.getStudentName()==null ||student.getStudentName().trim().isEmpty()) ? "" :student.getStudentName()));
					studentAddressDetails.appendChild(studentName);
					
					if(student.getResource()!=null && student.getResource().getFatherFirstName()!=null && !student.getResource().getFatherFirstName().trim().isEmpty()){
						Element fatherName = doc.createElement("fatherName");
						fatherName.appendChild(doc.createTextNode((student.getResource()==null ||student.getResource().getFatherFirstName()==null ||student.getResource().getFatherFirstName().trim().isEmpty()) ? "" :student.getResource().getFatherFirstName()));
						studentAddressDetails.appendChild(fatherName);
					}
					
					
					if(student.getResource()!=null && student.getResource().getMotherFirstName()!=null && !student.getResource().getMotherFirstName().trim().isEmpty()){
						Element motherName = doc.createElement("motherName");
						motherName.appendChild(doc.createTextNode((student.getResource()==null ||student.getResource().getMotherFirstName()==null ||student.getResource().getMotherFirstName().trim().isEmpty()) ? "" :student.getResource().getMotherFirstName()));
						studentAddressDetails.appendChild(motherName);
					}
					
					
					if(student.getGuardianFirstName()!=null && !student.getGuardianFirstName().trim().isEmpty()){
						Element guardianName = doc.createElement("guardianName");
						guardianName.appendChild(doc.createTextNode((student.getGuardianFirstName()==null ||student.getGuardianFirstName().trim().isEmpty()) ? "" :student.getGuardianFirstName()));
						studentAddressDetails.appendChild(guardianName);
					}
					
					
					if(student.getAddress()!=null && student.getAddress().getPermanentAddressLine()!=null ){
						Element addressLine = doc.createElement("addressLine");
						addressLine.appendChild(doc.createTextNode((student.getAddress()==null ||student.getAddress().getPermanentAddressLine()==null ||student.getAddress().getPermanentAddressLine().trim().isEmpty()) ? "" :student.getAddress().getPermanentAddressLine()));
						studentAddressDetails.appendChild(addressLine);
					}
					
					
					if(student.getAddress()!=null && student.getAddress().getPermanentAddressLandmark()!=null ){
						Element landmark = doc.createElement("landmark");
						landmark.appendChild(doc.createTextNode((student.getAddress()==null ||student.getAddress().getPermanentAddressLandmark()==null ||student.getAddress().getPermanentAddressLandmark().trim().isEmpty()) ? "" :student.getAddress().getPermanentAddressLandmark()));
						studentAddressDetails.appendChild(landmark);
					}
					
					
					if(student.getAddress()!=null && student.getAddress().getPermanentAddressCityVillage()!=null){
						Element cityVillage = doc.createElement("cityVillage");
						cityVillage.appendChild(doc.createTextNode((student.getAddress()==null ||student.getAddress().getPermanentAddressCityVillage()==null ||student.getAddress().getPermanentAddressCityVillage().trim().isEmpty()) ? "" :student.getAddress().getPermanentAddressCityVillage()));
						studentAddressDetails.appendChild(cityVillage);
					}
					
					
					if(student.getAddress()!=null && student.getAddress().getPermanentAddressPostOffice()!=null){
						Element postOffice = doc.createElement("postOffice");
						postOffice.appendChild(doc.createTextNode((student.getAddress()==null ||student.getAddress().getPermanentAddressPostOffice()==null ||student.getAddress().getPermanentAddressPostOffice().trim().isEmpty()) ? "" :student.getAddress().getPermanentAddressPostOffice()));
						studentAddressDetails.appendChild(postOffice);
					}
					
					
					if(student.getAddress()!=null && student.getAddress().getPermanentAddressPoliceStation()!=null){
						Element policeStation = doc.createElement("policeStation");
						policeStation.appendChild(doc.createTextNode((student.getAddress()==null ||student.getAddress().getPermanentAddressPoliceStation()==null ||student.getAddress().getPermanentAddressPoliceStation().trim().isEmpty()) ? "" :student.getAddress().getPermanentAddressPoliceStation()));
						studentAddressDetails.appendChild(policeStation);
					}
					
					
					if(student.getAddress()!=null && student.getAddress().getPermanentAddressDistrict()!=null){
						Element district = doc.createElement("district");
						district.appendChild(doc.createTextNode((student.getAddress()==null ||student.getAddress().getPermanentAddressDistrict()==null ||student.getAddress().getPermanentAddressDistrict().trim().isEmpty()) ? "" :student.getAddress().getPermanentAddressDistrict()));
						studentAddressDetails.appendChild(district);
					}
					
					
					if(student.getAddress()!=null && student.getAddress().getPermanentAddressPinCode()!=null){
						Element pinCode = doc.createElement("pinCode");
						pinCode.appendChild(doc.createTextNode((student.getAddress()==null ||student.getAddress().getPermanentAddressPinCode()==null ||student.getAddress().getPermanentAddressPinCode().trim().isEmpty()) ? "" :student.getAddress().getPermanentAddressPinCode()));
						studentAddressDetails.appendChild(pinCode);
					}
					
					
					if(student.getAddress()!=null && student.getAddress().getPermanentAddressState()!=null){
						Element state = doc.createElement("state");
						state.appendChild(doc.createTextNode((student.getAddress()==null ||student.getAddress().getPermanentAddressState()==null ||student.getAddress().getPermanentAddressState().trim().isEmpty()) ? "" :student.getAddress().getPermanentAddressState()));
						studentAddressDetails.appendChild(state);
					}
					
					
					if(student.getAddress()!=null && student.getAddress().getPermanentAddressCountry()!=null){
						Element country = doc.createElement("country");
						country.appendChild(doc.createTextNode((student.getAddress()==null ||student.getAddress().getPermanentAddressCountry()==null ||student.getAddress().getPermanentAddressCountry().trim().isEmpty()) ? "" :student.getAddress().getPermanentAddressCountry()));
						studentAddressDetails.appendChild(country);
					}
					
					
					if(student.getAddress()!=null && student.getAddress().getPermanentAddressPhone()!=null ){
						Element phone = doc.createElement("phone");
						phone.appendChild(doc.createTextNode((student.getAddress()==null ||student.getAddress().getPermanentAddressPhone()==null ||student.getAddress().getPermanentAddressPhone().trim().isEmpty()) ? "" :student.getAddress().getPermanentAddressPhone()));
						studentAddressDetails.appendChild(phone);
					}
					
					
					if(student.getResource()!=null && student.getResource().getFatherMobile()!=null){
						Element mobile = doc.createElement("mobile");
						mobile.appendChild(doc.createTextNode((student.getResource()==null ||student.getResource().getFatherMobile()==null ||student.getResource().getFatherMobile().trim().isEmpty()) ? "" :student.getResource().getFatherMobile()));
						studentAddressDetails.appendChild(mobile);
					}
					
					
					if(student.getResource()!=null && student.getResource().getMotherMobile()!=null){
						Element mobile1 = doc.createElement("mobile1");
						mobile1.appendChild(doc.createTextNode((student.getResource()==null ||student.getResource().getMotherMobile()==null ||student.getResource().getMotherMobile().trim().isEmpty()) ? "" :student.getResource().getMotherMobile()));
						studentAddressDetails.appendChild(mobile1);
					}
					
					
					if(student.getGuardianMobile()!=null){
						Element mobile2 = doc.createElement("mobile2");
						mobile2.appendChild(doc.createTextNode((student.getGuardianMobile()==null ||student.getGuardianMobile().trim().isEmpty()) ? "" :student.getGuardianMobile()));
						studentAddressDetails.appendChild(mobile2);
					}
					
					
					
					Element schoolname = doc.createElement("schoolname");
					schoolname.appendChild(doc.createTextNode((report.getSchoolDetails()==null ||report.getSchoolDetails().getSchoolDetailsName()==null ||report.getSchoolDetails().getSchoolDetailsName().trim().isEmpty()) ? "" :report.getSchoolDetails().getSchoolDetailsName()));
					studentAddressDetails.appendChild(schoolname);
					
					Element affiliatedBoard = doc.createElement("affiliatedBoard");
					affiliatedBoard.appendChild(doc.createTextNode((report.getSchoolDetails()==null ||report.getSchoolDetails().getBoardUniversity()==null ||report.getSchoolDetails().getBoardUniversity().trim().isEmpty()) ? "" :report.getSchoolDetails().getBoardUniversity()));
					studentAddressDetails.appendChild(affiliatedBoard);
					
					Element affiliationCode = doc.createElement("affiliationCode");
					affiliationCode.appendChild(doc.createTextNode((report.getSchoolDetails()==null ||report.getSchoolDetails().getBoardUniversityCode()==null ||report.getSchoolDetails().getBoardUniversityCode().trim().isEmpty()) ? "" :report.getSchoolDetails().getBoardUniversityCode()));
					studentAddressDetails.appendChild(affiliationCode);
					
					Element schoolAddress = doc.createElement("schoolAddress");
					schoolAddress.appendChild(doc.createTextNode((report.getSchoolDetails()==null ||report.getSchoolDetails().getSchoolAddress()==null ||report.getSchoolDetails().getSchoolAddress().trim().isEmpty()) ? "" :report.getSchoolDetails().getSchoolAddress()));
					studentAddressDetails.appendChild(schoolAddress);
					
					Element schoolPhone = doc.createElement("schoolPhone");
					schoolPhone.appendChild(doc.createTextNode((report.getSchoolDetails()==null ||report.getSchoolDetails().getPhone()==null ||report.getSchoolDetails().getPhone().trim().isEmpty()) ? "" :report.getSchoolDetails().getPhone()));
					studentAddressDetails.appendChild(schoolPhone);
					
					Element schoolMobile = doc.createElement("schoolMobile");
					schoolMobile.appendChild(doc.createTextNode((report.getSchoolDetails()==null ||report.getSchoolDetails().getMobile()==null ||report.getSchoolDetails().getMobile().trim().isEmpty()) ? "" :report.getSchoolDetails().getMobile()));
					studentAddressDetails.appendChild(schoolMobile);
					
					Element schoolEmail = doc.createElement("schoolEmail");
					schoolEmail.appendChild(doc.createTextNode((report.getSchoolDetails()==null ||report.getSchoolDetails().getEmail()==null ||report.getSchoolDetails().getEmail().trim().isEmpty()) ? "" :report.getSchoolDetails().getEmail()));
					studentAddressDetails.appendChild(schoolEmail);
					
				}	
			}
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
		
			StreamResult result = new StreamResult(new File(report.getXmlFilePath()+report.getXmlFileName()));
		
			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);
		
			transformer.transform(source, result);
		
		
		  } catch (ParserConfigurationException pce) {
			  logger.error(pce);
		  } catch (TransformerException tfe) {
			  logger.error(tfe);
		  }
}







		public void createXMLFileForGenerateAdmisionMeritListReport(Report report) {
			try{
				File outDir = new File(report.getXmlFilePath()); 
				boolean isDirCreated = outDir.mkdirs();
				if (isDirCreated)
					logger.info("In XmlBuilder class createXMLFileForLPGCylinderReport() method: excel file folder location created.");
				else
					logger.info("In XmlBuilder class createXMLFileForLPGCylinderReport() method: excel file folder location already exist.");		
					DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				
					// root elements
					Document doc = docBuilder.newDocument();
					Element rootElement = doc.createElement("root");
					doc.appendChild(rootElement);
					
					Element schoolname = doc.createElement("schoolname");
					schoolname.appendChild(doc.createTextNode((report.getSchoolDetails().getSchoolDetailsName() != null ? report.getSchoolDetails().getSchoolDetailsName() : "" )));
					rootElement.appendChild(schoolname);
					
					Element reportFor = doc.createElement("listheader");
					reportFor.appendChild(doc.createTextNode("Admission Merit List"));
					rootElement.appendChild(reportFor);

					Element classname = doc.createElement("classname");
					classname.appendChild(doc.createTextNode((report.getCandidate().getStandard().getStandardName()!=null?report.getCandidate().getStandard().getStandardName():"-----")));
					rootElement.appendChild(classname);
					
					Element meritlisttype = doc.createElement("meritlisttype");
					meritlisttype.appendChild(doc.createTextNode((report.getCandidate().getMeritListType().getMeritListTypeName()!=null?report.getCandidate().getMeritListType().getMeritListTypeName():"-----")));
					rootElement.appendChild(meritlisttype);
					
					Element selectiontype = doc.createElement("selectiontype");
					selectiontype.appendChild(doc.createTextNode((report.getCandidate().getStatus()!=null?report.getCandidate().getStatus():"-----")));
					rootElement.appendChild(selectiontype);
					if(report.getCandidateList()!=null && report.getCandidateList().size()!=0){
						for(Candidate candidate : report.getCandidateList()){
							
							Element candidateList = doc.createElement("candidate");
							rootElement.appendChild(candidateList);
							
							Element formId = doc.createElement("formId");
							formId.appendChild(doc.createTextNode(candidate.getAdmissionFormId()!=null ? candidate.getAdmissionFormId() : "-----"));
							candidateList.appendChild(formId);
							
							Element candidatename = doc.createElement("candidatename");
							candidatename.appendChild(doc.createTextNode(candidate.getResource().getName()!=null ? candidate.getResource().getName() : "-----"));
							candidateList.appendChild(candidatename);
							
							Element category = doc.createElement("category");
							category.appendChild(doc.createTextNode(candidate.getResource().getCategory()!=null ? candidate.getResource().getCategory() : "-----"));
							candidateList.appendChild(category);
							
							Element stateofdomicile = doc.createElement("stateofdomicile");
							stateofdomicile.appendChild(doc.createTextNode(candidate.getAddress().getPresentAddressState()!=null ? candidate.getAddress().getPresentAddressState() : "-----"));
							candidateList.appendChild(stateofdomicile);
							
							Element dateofbirth = doc.createElement("dateofbirth");
							dateofbirth.appendChild(doc.createTextNode(candidate.getResource().getDateOfBirth()!=null ? candidate.getResource().getDateOfBirth() : "-----"));
							candidateList.appendChild(dateofbirth);
							
							Element status = doc.createElement("status");
							status.appendChild(doc.createTextNode(report.getCandidate().getStatus()!=null ? report.getCandidate().getStatus() : "-----"));
							candidateList.appendChild(status);
						}	
					}
					// write the content into xml file
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					DOMSource source = new DOMSource(doc);
					StreamResult result = new StreamResult(new File(report.getXmlFilePath()+report.getXmlFileName()));
					transformer.transform(source, result);
				  } catch (ParserConfigurationException pce) {
					  logger.error(pce);
				  } catch (TransformerException tfe) {
					  logger.error(tfe);
				  }catch(Exception e){
					  logger.error(e);
			}

		}
		
		
		
		
		
		
		public void createXMLFileForGenerateExamVenueWiseCandidatesReport(Report report) {
			try{
				File outDir = new File(report.getXmlFilePath()); 
				boolean isDirCreated = outDir.mkdirs();
				if (isDirCreated)
					logger.info("In XmlBuilder class createXMLFileForLPGCylinderReport() method: excel file folder location created.");
				else
					logger.info("In XmlBuilder class createXMLFileForLPGCylinderReport() method: excel file folder location already exist.");		
					DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				
					// root elements
					Document doc = docBuilder.newDocument();
					Element rootElement = doc.createElement("root");
					doc.appendChild(rootElement);
					
					Element schoolname = doc.createElement("schoolname");
					schoolname.appendChild(doc.createTextNode((report.getSchoolDetails().getSchoolDetailsName() != null ? report.getSchoolDetails().getSchoolDetailsName() : "" )));
					rootElement.appendChild(schoolname);
					
					Element reportFor = doc.createElement("listheader");
					reportFor.appendChild(doc.createTextNode("Candidate List"));
					rootElement.appendChild(reportFor);

					Element venuename = doc.createElement("venuename");
					venuename.appendChild(doc.createTextNode((report.getVenue().getVenueName()!=null?report.getVenue().getVenueName():"-----")));
					rootElement.appendChild(venuename);
					
					Element noofcandidates = doc.createElement("noofcandidates");
					noofcandidates.appendChild(doc.createTextNode((String.valueOf(report.getCandidateList().size()))));
					rootElement.appendChild(noofcandidates);
					
					Element venueaddress = doc.createElement("venueaddress");
					String centreDetails = report.getVenue().getVenueName();
					centreDetails = centreDetails+", \n"+report.getVenue().getAddress().getPresentAddressLine();
					centreDetails = centreDetails+",\n"+report.getVenue().getAddress().getPresentAddressCityVillage();
					centreDetails = centreDetails+","+report.getVenue().getAddress().getPresentAddressPostOffice();
					centreDetails = centreDetails+",\n"+report.getVenue().getAddress().getPresentAddressPoliceStation();
					centreDetails = centreDetails+",\n"+report.getVenue().getAddress().getPresentAddressState();
					centreDetails = centreDetails+","+report.getVenue().getAddress().getPresentAddressCountry();
					centreDetails = centreDetails+"-"+report.getVenue().getAddress().getPresentAddressPinCode();
					venueaddress.appendChild(doc.createTextNode(centreDetails));
					rootElement.appendChild(venueaddress);
					
					if(report.getCandidateList()!=null && report.getCandidateList().size()!=0){
						for(Candidate candidate : report.getCandidateList()){
							
							Element candidateList = doc.createElement("candidate");
							rootElement.appendChild(candidateList);
							
							Element className = doc.createElement("className");
							className.appendChild(doc.createTextNode(candidate.getStandard().getStandardName()!=null ? candidate.getStandard().getStandardName() : "-----"));
							candidateList.appendChild(className);
							
							Element formId = doc.createElement("formId");
							formId.appendChild(doc.createTextNode(candidate.getAdmissionFormId()!=null ? candidate.getAdmissionFormId() : "-----"));
							candidateList.appendChild(formId);
							
							Element candidatename = doc.createElement("candidatename");
							candidatename.appendChild(doc.createTextNode(candidate.getResource().getName()!=null ? candidate.getResource().getName() : "-----"));
							candidateList.appendChild(candidatename);
							
							Element category = doc.createElement("category");
							category.appendChild(doc.createTextNode(candidate.getResource().getCategory()!=null ? candidate.getResource().getCategory() : "-----"));
							candidateList.appendChild(category);
							
							Element stateofdomicile = doc.createElement("stateofdomicile");
							stateofdomicile.appendChild(doc.createTextNode(candidate.getAddress().getPresentAddressState()!=null ? candidate.getAddress().getPresentAddressState() : "-----"));
							candidateList.appendChild(stateofdomicile);
							
							Element dateofbirth = doc.createElement("dateofbirth");
							dateofbirth.appendChild(doc.createTextNode(candidate.getResource().getDateOfBirth()!=null ? candidate.getResource().getDateOfBirth() : "-----"));
							candidateList.appendChild(dateofbirth);
							
						}	
					}
					// write the content into xml file
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					DOMSource source = new DOMSource(doc);
					StreamResult result = new StreamResult(new File(report.getXmlFilePath()+report.getXmlFileName()));
					transformer.transform(source, result);
				  } catch (ParserConfigurationException pce) {
					  logger.error(pce);
				  } catch (TransformerException tfe) {
					  logger.error(tfe);
				  }catch(Exception e){
					  logger.error(e);
			}

		}
		
		
		
		
		public void createXMLFileForGenerateSocialCategoryWiseClassStrengthReport(Report report) {
			try{
				File outDir = new File(report.getXmlFilePath()); 
				boolean isDirCreated = outDir.mkdirs();
				if (isDirCreated)
					logger.info("In XmlBuilder class createXMLFileForLPGCylinderReport() method: excel file folder location created.");
				else
					logger.info("In XmlBuilder class createXMLFileForLPGCylinderReport() method: excel file folder location already exist.");		
					DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				
					// root elements
					Document doc = docBuilder.newDocument();
					Element rootElement = doc.createElement("root");
					doc.appendChild(rootElement);
					
					Element schoolname = doc.createElement("schoolname");
					schoolname.appendChild(doc.createTextNode((report.getSchoolDetails().getSchoolDetailsName() != null ? report.getSchoolDetails().getSchoolDetailsName() : "" )));
					rootElement.appendChild(schoolname);
					
					Element reportFor = doc.createElement("listheader");
					reportFor.appendChild(doc.createTextNode("School Strength Report"));
					rootElement.appendChild(reportFor);
					
					Element year = doc.createElement("year");
					year.appendChild(doc.createTextNode(report.getAcademicYear().getAcademicYearName()));
					rootElement.appendChild(year);
					
					SortedMap<String,Integer> categoryTotalStrengthMap = new TreeMap<>();
					if(report.getStandardList().get(0).getSocialCategoriesList()!=null && report.getStandardList().get(0).getSocialCategoriesList().size()!=0){
						for(SocialCategory sc: report.getStandardList().get(0).getSocialCategoriesList()){
							categoryTotalStrengthMap.put(sc.getSocialCategoryName(), 0);
						}
					}
					if(report.getStandardList()!=null && report.getStandardList().size()!=0){
						for(Standard std: report.getStandardList()){
							if(std.getSocialCategoriesList()!=null && std.getSocialCategoriesList().size()!=0){
								for(SocialCategory sc: std.getSocialCategoriesList()){
									if(categoryTotalStrengthMap.containsKey(sc.getSocialCategoryName())){
										categoryTotalStrengthMap.put(sc.getSocialCategoryName(),categoryTotalStrengthMap.get(sc.getSocialCategoryName())+(int)sc.getAmount());
									}
								}
							}
						}
					}
					for(Map.Entry<String, Integer> socialCategoryMap: categoryTotalStrengthMap.entrySet()){
						Element catagorygroupList = doc.createElement("catagorygroup");
						rootElement.appendChild(catagorygroupList);
						
						Element catname = doc.createElement("catname");
						catname.appendChild(doc.createTextNode(socialCategoryMap.getKey()!=null ? socialCategoryMap.getKey() : "-----"));
						catagorygroupList.appendChild(catname);
						
						Element totcatstrength = doc.createElement("totcatstrength");
						totcatstrength.appendChild(doc.createTextNode(socialCategoryMap.getValue()!=null ? socialCategoryMap.getValue().toString() : "-----"));
						catagorygroupList.appendChild(totcatstrength);
					}
					
					
					if(report.getStandardList()!=null && report.getStandardList().size()!=0){
						for(Standard std: report.getStandardList()){
							
							Element dataList = doc.createElement("data");
							rootElement.appendChild(dataList);
							
							Element className = doc.createElement("class");
							className.appendChild(doc.createTextNode(std.getStandardName()!=null ? std.getStandardName() : "-----"));
							dataList.appendChild(className);
							
							Element section = doc.createElement("section");
							section.appendChild(doc.createTextNode(std.getSection()!=null ? std.getSection() : "-----"));
							dataList.appendChild(section);
							
							if(std.getSocialCategoriesList()!=null && std.getSocialCategoriesList().size()!=0){
								int total=0;
								for(SocialCategory sc: std.getSocialCategoriesList()){
									
									Element catagoryList = doc.createElement("catagory");
									dataList.appendChild(catagoryList);
									
									Element catagoryname = doc.createElement("catagoryname");
									catagoryname.appendChild(doc.createTextNode(sc.getSocialCategoryName()!=null ? sc.getSocialCategoryName() : "-----"));
									catagoryList.appendChild(catagoryname);
									
									Element strength = doc.createElement("strength");
									strength.appendChild(doc.createTextNode(String.valueOf((int)sc.getAmount())));
									catagoryList.appendChild(strength);
									total=total+(int)sc.getAmount();
								}
								Element totalValue = doc.createElement("total");
								totalValue.appendChild(doc.createTextNode(String.valueOf(total)));
								dataList.appendChild(totalValue);
							}
						}
					}
				
					// write the content into xml file
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					DOMSource source = new DOMSource(doc);
					StreamResult result = new StreamResult(new File(report.getXmlFilePath()+report.getXmlFileName()));
					transformer.transform(source, result);
				  } catch (ParserConfigurationException pce) {
					  logger.error(pce);
				  } catch (TransformerException tfe) {
					  logger.error(tfe);
				  }catch(Exception e){
					  logger.error(e);
			}
		}

		public void createXMLFileForTCReport(Report report) {
			try{
				File outDir = new File(report.getXmlFilePath()); 
				boolean isDirCreated = outDir.mkdirs();
				if (isDirCreated)
					logger.info("In XmlBuilder class createXMLFileForLPGCylinderReport() method: excel file folder location created.");
				else
					logger.info("In XmlBuilder class createXMLFileForLPGCylinderReport() method: excel file folder location already exist.");		
					DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				
					// root elements
					Document doc = docBuilder.newDocument();
					Element rootElement = doc.createElement("root");
					doc.appendChild(rootElement);
					
					Element schoolname = doc.createElement("schoolname");
					schoolname.appendChild(doc.createTextNode("SAINIK SCHOOL PURULIA"));
					rootElement.appendChild(schoolname);
					
					Element address1 = doc.createElement("address1");
					address1.appendChild(doc.createTextNode("P.O. : SAINIK SCHOOL, DISTRICT : PURULIA"));
					rootElement.appendChild(address1);
					
					Element address2 = doc.createElement("address2");
					address2.appendChild(doc.createTextNode("WEST BENGAL - 723104"));
					rootElement.appendChild(address2);
					
					Element phone = doc.createElement("phone");
					phone.appendChild(doc.createTextNode("Phone : 03252-202004 / 202003 (FAX)"));
					rootElement.appendChild(phone);
					
					Element email = doc.createElement("email");
					email.appendChild(doc.createTextNode("e-mail : sainikpurulia@rediffmail.com"));
					rootElement.appendChild(email);
					
					Element website = doc.createElement("website");
					website.appendChild(doc.createTextNode("Website : www.sainikschoolpurulia.com"));
					rootElement.appendChild(website);
					
					Element affliation = doc.createElement("affliation");
					affliation.appendChild(doc.createTextNode("(Affliated to CBSE, Delhi & Member of IPSC)"));
					rootElement.appendChild(affliation);
					
					Element listheader = doc.createElement("listheader");
					listheader.appendChild(doc.createTextNode("TRANSFER CERTIFICATE"));
					rootElement.appendChild(listheader);
					
					Element oldaffilistion = doc.createElement("oldaffilistion");
					oldaffilistion.appendChild(doc.createTextNode("2430012"));
					rootElement.appendChild(oldaffilistion);
					
					Element newaffilistion = doc.createElement("newaffilistion");
					newaffilistion.appendChild(doc.createTextNode("2480001"));
					rootElement.appendChild(newaffilistion);
					
					Element slno = doc.createElement("slno");
					slno.appendChild(doc.createTextNode(""));
					rootElement.appendChild(slno);
					
					Element schoolno = doc.createElement("schoolno");
					schoolno.appendChild(doc.createTextNode("08436"));
					rootElement.appendChild(schoolno);
					
					Element bookno = doc.createElement("bookno");
					bookno.appendChild(doc.createTextNode(""));
					rootElement.appendChild(bookno);
					
					Element admissionno = doc.createElement("admissionno");
					admissionno.appendChild(doc.createTextNode(("")));
					rootElement.appendChild(admissionno);
					
					if(report.getStudent()!=null){					
						
						if(report.getStudent().getStudentName()!=null && !report.getStudent().getStudentName().trim().isEmpty()){
							Element studentName = doc.createElement("studentName");
							studentName.appendChild(doc.createTextNode((report.getStudent().getStudentName())));
							rootElement.appendChild(studentName);
						}
						if(report.getStudent().getRollNumber()!=null){
							Element rollNumber = doc.createElement("rollNumber");
							rollNumber.appendChild(doc.createTextNode(Integer.toString((report.getStudent().getRollNumber()))));
							rootElement.appendChild(rollNumber);
						}
						if(report.getStudent().getDateOfAdmission()!=null && !report.getStudent().getDateOfAdmission().trim().isEmpty()){
							Element doa = doc.createElement("doa");
							doa.appendChild(doc.createTextNode((report.getStudent().getDateOfAdmission())));
							rootElement.appendChild(doa);
						}
						
						if(report.getStudent().getStandard()!=null && !report.getStudent().getStandard().trim().isEmpty()){
							Element standard = doc.createElement("standard");
							standard.appendChild(doc.createTextNode((report.getStudent().getStandard())));
							rootElement.appendChild(standard);
						}
						
						if(report.getStudent().getStudentTC()!=null){	
							
							if(report.getStudent().getStudentTC().getReason()!=null && !report.getStudent().getStudentTC().getReason().trim().isEmpty()){
								Element reason = doc.createElement("reason");
								reason.appendChild(doc.createTextNode((report.getStudent().getStudentTC().getReason())));
								rootElement.appendChild(reason);
							}
							
							if(report.getStudent().getStudentTC().getDescription()!=null && !report.getStudent().getStudentTC().getDescription().trim().isEmpty()){
								Element description = doc.createElement("description");
								description.appendChild(doc.createTextNode((report.getStudent().getStudentTC().getDescription())));
								rootElement.appendChild(description);
							}
						}
						
						
						if(report.getStudent().getResource()!=null){	
							
							if(report.getStudent().getResource().getMotherFirstName()!=null && !report.getStudent().getResource().getMotherFirstName().trim().isEmpty()){
								Element mothersName = doc.createElement("mothersName");
								mothersName.appendChild(doc.createTextNode((report.getStudent().getResource().getMotherFirstName())));
								rootElement.appendChild(mothersName);
							}
							
							if(report.getStudent().getResource().getFatherFirstName()!=null && !report.getStudent().getResource().getFatherFirstName().trim().isEmpty()){
								Element fathersName = doc.createElement("fathersName");
								fathersName.appendChild(doc.createTextNode((report.getStudent().getResource().getFatherFirstName())));
								rootElement.appendChild(fathersName);								
							}
							
							if(report.getStudent().getResource().getDateOfBirth()!=null && !report.getStudent().getResource().getDateOfBirth().trim().isEmpty()){
								Element dob = doc.createElement("dob");
								dob.appendChild(doc.createTextNode((report.getStudent().getResource().getDateOfBirth())));
								rootElement.appendChild(dob);								
							}
							
							if(report.getStudent().getResource().getNationality()!=null && !report.getStudent().getResource().getNationality().trim().isEmpty()){
								Element nationality = doc.createElement("nationality");
								nationality.appendChild(doc.createTextNode((report.getStudent().getResource().getNationality())));
								rootElement.appendChild(nationality);
							}
							
							if(report.getStudent().getResource().getCategory()!=null && !report.getStudent().getResource().getCategory().trim().isEmpty()
									&& report.getStudent().getResource().getCategory().equalsIgnoreCase("SC") && report.getStudent().getResource().getCategory().equalsIgnoreCase("ST") && report.getStudent().getResource().getCategory().equalsIgnoreCase("OBC")){
								Element category = doc.createElement("category");
								category.appendChild(doc.createTextNode((report.getStudent().getResource().getNationality())));
								rootElement.appendChild(category);
							}
						}						
						}	
					// write the content into xml file
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					DOMSource source = new DOMSource(doc);
					StreamResult result = new StreamResult(new File(report.getXmlFilePath()+report.getXmlFileName()));
					transformer.transform(source, result);
				  } catch (ParserConfigurationException pce) {
					  logger.error(pce);
				  } catch (TransformerException tfe) {
					  logger.error(tfe);
				  }catch(Exception e){
					  logger.error(e);
			}			
		}
		
		
		public void createXMLFileForConsolidateExamResultXI_XII(Report reportData) {
			try{
				File outDir = new File(reportData.getXmlFilePath()); 
				boolean isDirCreated = outDir.mkdirs();
				if (isDirCreated)
					logger.info("In XMLBuilder class fileUpload() method: upload file folder location created.");
				else
					logger.info("In XMLBuilder class fileUpload() method: upload file folder location already exist.");
				
				
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
				// root elements
				Document doc = docBuilder.newDocument();
				Element rootElement = doc.createElement("root");
				doc.appendChild(rootElement);
				
				
				Element schoolname = doc.createElement("schoolname");
				schoolname.appendChild(doc.createTextNode((reportData.getSchoolDetails().getSchoolDetailsName() != null ? reportData.getSchoolDetails().getSchoolDetailsName() : "" )));
				rootElement.appendChild(schoolname);
				
				Element report = doc.createElement("report");
				report.appendChild(doc.createTextNode("MARKS STATEMENT : "));
				rootElement.appendChild(report);
				
				String examname = reportData.getSchoolDetails().getExamName();				
				String examName = "";
				
				if(examname.equalsIgnoreCase("M1")){					
					examName = "Monthly-1";
				}else if(examname.equalsIgnoreCase("M2")){					
					examName = "Monthly-2";
				}else if(examname.equalsIgnoreCase("PC")){					
					examName = "Pre Centralised";
				}else{					
					examName = "Term-1";
				}
				
				
				Element exam = doc.createElement("exam");
				report.appendChild(doc.createTextNode(reportData.getAcademicYear().getAcademicYearName() +" ("+(examName)+")"));
				rootElement.appendChild(report);
			
				Element standard = doc.createElement("standard");
				standard.appendChild(doc.createTextNode((reportData.getStudent().getStandard() !=null?reportData.getStudent().getStandard():"------")));
				rootElement.appendChild(standard);
			
				Element section = doc.createElement("section");
				section.appendChild(doc.createTextNode((reportData.getStudent().getSection()!=null?reportData.getStudent().getSection():"--------")));
				rootElement.appendChild(section);
			
				for(SubjectGroup subjectGroup : reportData.getSubjectGroupList()){
					Element subjectGroupList = doc.createElement("subjectgroup");
					rootElement.appendChild(subjectGroupList);
					
					Element subjectgroupname = doc.createElement("subjectgroupname");
					subjectgroupname.appendChild(doc.createTextNode((subjectGroup.getSubjectGroupName()!=null?subjectGroup.getSubjectGroupName():"----")));
					subjectGroupList.appendChild(subjectgroupname);
				}
				for(Student student : reportData.getStudentList()){
						
					Element studentList = doc.createElement("student");
					rootElement.appendChild(studentList);
			
					
					Element roll = doc.createElement("roll");
					roll.appendChild(doc.createTextNode((student.getRollNumber().toString()!=null?student.getRollNumber().toString():"---------")));
					studentList.appendChild(roll);
					
					// nickname elements
					Element studentname = doc.createElement("name");
					studentname.appendChild(doc.createTextNode((student.getStudentName()!=null?student.getStudentName():"---------------")));
					studentList.appendChild(studentname);
			

					for(StudentResult studentResult : student.getStudentResultList()){
						Element subject = doc.createElement("subject");
						studentList.appendChild(subject);
						
						Element subjectname = doc.createElement("subjectname");
						subjectname.appendChild(doc.createTextNode((studentResult.getSubject()!=null?studentResult.getSubject():"-")));
						subject.appendChild(subjectname);
						
						Element fa1 = doc.createElement("marks");
						fa1.appendChild(doc.createTextNode((studentResult.getTotalObtained()!=null?studentResult.getTotalObtained().toString():"-")));
						subject.appendChild(fa1);
						
					}
					
					Element total = doc.createElement("total");
					total.appendChild(doc.createTextNode((student.getStudentTotal()!=null?student.getStudentTotal().toString():"-")));
					studentList.appendChild(total);
					
					Element percent = doc.createElement("percent");
//					all_total.appendChild(doc.createTextNode(new Double(new DecimalFormat("##.0").format(total)).toString()));
					percent.appendChild(doc.createTextNode((student.getStudentPercent()!=null?((new DecimalFormat("##.0").format(student.getStudentPercent())).toString()):"-")));
					studentList.appendChild(percent);
					
					Element rank = doc.createElement("rank");
					rank.appendChild(doc.createTextNode((student.getStudentPassFail()!=null?student.getStudentPassFail():"-")));
					studentList.appendChild(rank);
					
				}
				// write the content into xml file
				
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);				
				StreamResult result = new StreamResult(new File(reportData.getXmlFilePath()+reportData.getXmlFileName()));
				transformer.transform(source, result);
				
			  } catch (ParserConfigurationException pce) {
				  logger.error(pce);
			  } catch (TransformerException tfe) {
				  logger.error(tfe);
			  } catch (Exception e) {
				  logger.error(e);
			  }
		}
		
		
		public void createXMLFileForExamResultXI_XII(Report reportData) {

			try{
				File outDir = new File(reportData.getXmlFilePath()); 
				boolean isDirCreated = outDir.mkdirs();
				if (isDirCreated)
					logger.info("In FileUploadDownload class fileUpload() method: upload file folder location created.");
				else
					logger.info("In FileUploadDownload class fileUpload() method: upload file folder location already exist.");
				
				
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	
				// root elements
				Document doc = docBuilder.newDocument();
				Element rootElement = doc.createElement("root");
				doc.appendChild(rootElement);
	
				for(Student student : reportData.getStudentList()){					
					String exam = reportData.getSchoolDetails().getExamName();					
					String examName = "";
					
					if(exam.equalsIgnoreCase("M1")){						
						examName = "Monthly-1";
					}else if(exam.equalsIgnoreCase("M2")){						
						examName = "Monthly-2";
					}else if (exam.equalsIgnoreCase("PC")){						
						examName = "Pre Centralised";
					}else{						
						examName = "Term-1";
					}
					
					
					Element studentList = doc.createElement("student");
					rootElement.appendChild(studentList);
					
					Element schoolname = doc.createElement("schoolname");
					schoolname.appendChild(doc.createTextNode((reportData.getSchoolDetails().getSchoolDetailsName() != null ? reportData.getSchoolDetails().getSchoolDetailsName() : "" )));
					studentList.appendChild(schoolname);
					
					Element report = doc.createElement("report");
					report.appendChild(doc.createTextNode("MARKS STATEMENT : (" +examName+" : "+reportData.getAcademicYear().getAcademicYearName()+ ")") );
					studentList.appendChild(report);
					
					// nickname elements
					Element studentname = doc.createElement("studentname");
					studentname.appendChild(doc.createTextNode((student.getStudentName()!=null?student.getStudentName():"---------------")));
					studentList.appendChild(studentname);
	
					Element roll = doc.createElement("roll");
					roll.appendChild(doc.createTextNode((student.getRollNumber().toString()!=null?student.getRollNumber().toString():"---------")));
					studentList.appendChild(roll);
					
					Element house = doc.createElement("house");
					house.appendChild(doc.createTextNode((student.getHouse()!=null?student.getHouse():"------")));
					studentList.appendChild(house);
	
					Element standard = doc.createElement("standard");
					standard.appendChild(doc.createTextNode((student.getStandard()!=null?student.getStandard():"------")));
					studentList.appendChild(standard);
	
					Element section = doc.createElement("section");
					section.appendChild(doc.createTextNode((student.getSection()!=null?student.getSection():"--------")));
					studentList.appendChild(section);				
					
					
					for(StudentResult studentResult : student.getStudentResultList()){
						Element subject = doc.createElement("subject");
						studentList.appendChild(subject);
						
						Element subjectname = doc.createElement("subjectname");
						subjectname.appendChild(doc.createTextNode((studentResult.getSubject()!=null?studentResult.getSubject():"")));
						subject.appendChild(subjectname);
						
						Element weightage = doc.createElement("maxmarks");
						weightage.appendChild(doc.createTextNode( (studentResult.getTotal()!=null ?studentResult.getTotal().toString():"")));
						subject.appendChild(weightage);
						
						String nodeValue = "";
						Element weightageObtained = doc.createElement("marksobtained");				
						if(studentResult.getTotalObtained() == null){
							nodeValue = "";
						}else if(studentResult.getTotalObtained() < 0){
							nodeValue = "AB";
						}else{
							nodeValue = String.valueOf(studentResult.getTotalObtained());
						}					
						weightageObtained.appendChild(doc.createTextNode(nodeValue));
						subject.appendChild(weightageObtained);					
					}
					
					Element position = doc.createElement("position");
					position.appendChild(doc.createTextNode((student.getStudentPassFail()!=null?student.getStudentPassFail():"----")));
					studentList.appendChild(position);
				}
	
				// write the content into xml file
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);	
				StreamResult result = new StreamResult(new File(reportData.getXmlFilePath()+reportData.getXmlFileName()));
				transformer.transform(source, result);
				
				// Output to console for testing
				// StreamResult result = new StreamResult(System.out);

			} catch (ParserConfigurationException pce) {
				logger.error("",pce);
			} catch (TransformerException tfe) {
				logger.error(tfe);
			}
		}
		
		
		public void createXMLFileForStudentSubjectMappingList(Report report, Student studentObject) {
			try{
				File outDir = new File(report.getXmlFilePath()); 
				boolean isDirCreated = outDir.mkdirs();
				if (isDirCreated)
					logger.info("In createXMLFileForStudentSubjectMappingList() method: upload file folder location created.");
				else
					logger.info("In createXMLFileForStudentSubjectMappingList() method: upload file folder location already exist.");		
					DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
					int i = 1;
					Document doc = docBuilder.newDocument();
					Element rootElement = doc.createElement("root");
					doc.appendChild(rootElement);
					
					Element schoolname = doc.createElement("schoolname");
					schoolname.appendChild(doc.createTextNode(report.getSchoolDetails().getSchoolDetailsName()));
					rootElement.appendChild(schoolname);
					
					Element standard = doc.createElement("standard");
					standard.appendChild(doc.createTextNode(studentObject.getStandard()));
					rootElement.appendChild(standard);
					
					Element section = doc.createElement("section");
					section.appendChild(doc.createTextNode(studentObject.getSection()));
					rootElement.appendChild(section);
					
					for(StudentSubjectMapping studentSubjectMapping : report.getStudentSubjectMappingList()){					
						Element student = doc.createElement("student");
						rootElement.appendChild(student);
						Element studentname = doc.createElement("studentname");
						studentname.appendChild(doc.createTextNode(studentSubjectMapping.getStatus()));
						student.appendChild(studentname);
						for(Subject sub : studentSubjectMapping.getSubjects()){					
							if(null != sub.getSubjectName()){
								Element subjectName = doc.createElement("subjectName"+i);
								subjectName.appendChild(doc.createTextNode(sub.getSubjectName()));
								student.appendChild(subjectName);
							}
							i++;
						}
						i = 1;
					}
					
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					DOMSource source = new DOMSource(doc);
				
					StreamResult result = new StreamResult(new File(report.getXmlFilePath()+report.getXmlFileName()));
				
					// Output to console for testing
					// StreamResult result = new StreamResult(System.out);				
					transformer.transform(source, result);			
			
			}catch (ParserConfigurationException pce) {
				logger.error(pce);
			}catch (TransformerException tfe) {
				logger.error(tfe);
			}
		}
		
		
		public void createXMLFileForInternalExamResultXI(Report report) {			
			try
			{
				File outDir = new File(report.getXmlFilePath()); 
				boolean isDirCreated = outDir.mkdirs();
				if (isDirCreated)
					logger.info("In createXMLFileForStudentSubjectMappingList() method: upload file folder location created.");
				else
					logger.info("In createXMLFileForStudentSubjectMappingList() method: upload file folder location already exist.");
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				
				Document doc = docBuilder.newDocument();
				Element rootElement = doc.createElement("root");
				doc.appendChild(rootElement);
				
				
				Element schoolname = doc.createElement("schoolname");
				schoolname.appendChild(doc.createTextNode((report.getSchoolDetails().getSchoolDetailsName() != null ? report.getSchoolDetails().getSchoolDetailsName() : "" )));
				rootElement.appendChild(schoolname);
				
				Element reportname = doc.createElement("reportname");
				reportname.appendChild(doc.createTextNode("CONSOLIDATE RESULTSHEET FOR INTERNAL EXAMINATION : "+report.getAcademicYear().getAcademicYearName()+ ")") );
				rootElement.appendChild(reportname);
				
				Element standard = doc.createElement("standard");
				standard.appendChild(doc.createTextNode((report.getStudent().getStandard() !=null?report.getStudent().getStandard():"------")));
				rootElement.appendChild(standard);
			
				Element section = doc.createElement("section");
				section.appendChild(doc.createTextNode((report.getStudent().getSection()!=null?report.getStudent().getSection():"--------")));
				rootElement.appendChild(section);
				
				for(SubjectGroup subjectGroup : report.getSubjectGroupList()){
					Element subjectGroupList = doc.createElement("subjectgroup");
					rootElement.appendChild(subjectGroupList);
					
					Element subjectgroupname = doc.createElement("subjectgroupname");
					subjectgroupname.appendChild(doc.createTextNode((subjectGroup.getSubjectGroupName()!=null?subjectGroup.getSubjectGroupName():"----")));
					subjectGroupList.appendChild(subjectgroupname);
				}
				for(String exam : report.getExamList()){
					Element examList = doc.createElement("examgroup");
					rootElement.appendChild(examList);
					
					Element examgroupname = doc.createElement("examgroupname");
					examgroupname.appendChild(doc.createTextNode((exam!=null?exam:"----")));
					examList.appendChild(examgroupname);
				}
				
				for(Student student:report.getStudentList()){
					/*if(student.getRollNumber().intValue()==4395){
						for(StudentResult sr:student.getStudentResultList())
						{
							System.out.println(sr.getExam()+"\t\t"+sr.getSubject()+"\t\t"+sr.getTotalObtained());
						}
					}*/
					Element studentList = doc.createElement("student");
					rootElement.appendChild(studentList);
					
					Element roll = doc.createElement("roll");
					roll.appendChild(doc.createTextNode((student.getRollNumber().toString()!=null?student.getRollNumber().toString():"---------")));
					studentList.appendChild(roll);
					
					// nickname elements
					Element studentname = doc.createElement("studentname");
					studentname.appendChild(doc.createTextNode((student.getStudentName()!=null?student.getStudentName():"---------------")));
					studentList.appendChild(studentname);
					
					int i = report.getExamList().size();
					int j = 0;
					int total_marks_obtained = 0;
					double total = 0.0;
					double percent_total = 0.0;
					for(StudentResult sr : student.getStudentResultList()){						
						j++;
						Element examList = doc.createElement("exammarks");
						studentList.appendChild(examList);						
						
						Element examName = doc.createElement("exammark");
						if(null != sr.getTotalObtained()){
							if(sr.getTotalObtained()==-1){
								String total_obtained = "AB";
								examName.appendChild(doc.createTextNode(total_obtained));
								examList.appendChild(examName);
								
							}else{
								String total_obtained = sr.getTotalObtained().toString();
								examName.appendChild(doc.createTextNode(total_obtained));
								examList.appendChild(examName);
							}
						}else{
							examName.appendChild(doc.createTextNode("--"));
							examList.appendChild(examName);
						}
						
						if(null != sr.getTotalObtained()){
							if(sr.getTotalObtained()==-1){
								
								total_marks_obtained = total_marks_obtained + 0;
							}else{
								total_marks_obtained = total_marks_obtained+sr.getTotalObtained().intValue();
							}
							
						}							
						
						if(j == i){
							Element examList0 = doc.createElement("exammarks");
							studentList.appendChild(examList0);
							
							percent_total = (((double)total_marks_obtained*10)/100);
							Element examName0 = doc.createElement("exammark");
							//Element total_obtained= doc.createElement("total_obtained");
							examName0.appendChild(doc.createTextNode((new Integer(total_marks_obtained).toString())));
							examList0.appendChild(examName0);
							
							Element examList1 = doc.createElement("exammarks");
							studentList.appendChild(examList1);
							
							Element examName1 = doc.createElement("exammark");
							//Element percentage_total= doc.createElement("percent_total");
							examName1.appendChild(doc.createTextNode(new Double(percent_total).toString()));
							examList1.appendChild(examName1);
							
							j = 0;
							total_marks_obtained = 0;
							total=total+percent_total;
						}
					}
					
					Double total1 = new Double(total);
					
					Element all_total = doc.createElement("total");
					all_total.appendChild(doc.createTextNode(new Double(new DecimalFormat("##.0").format(total)).toString()));
					studentList.appendChild(all_total);
					total = 0;					
				}
				
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);				
				StreamResult result = new StreamResult(new File(report.getXmlFilePath()+report.getXmlFileName()));
				transformer.transform(source, result);
			}catch(Exception e) {
				e.printStackTrace();
				logger.error(e);
			}
			
		}
		
		
public void createXMLFileForCentraliseExamForXI_XII(Report report) {
			
			try
			{
				File outDir = new File(report.getXmlFilePath()); 
				boolean isDirCreated = outDir.mkdirs();
				if (isDirCreated)
					logger.info("In createXMLFileForStudentSubjectMappingList() method: upload file folder location created.");
				else
					logger.info("In createXMLFileForStudentSubjectMappingList() method: upload file folder location already exist.");
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				
				Document doc = docBuilder.newDocument();
				Element rootElement = doc.createElement("root");
				doc.appendChild(rootElement);
				
				
				/*Element schoolname = doc.createElement("schoolname");
				schoolname.appendChild(doc.createTextNode((report.getSchoolDetails().getSchoolDetailsName() != null ? report.getSchoolDetails().getSchoolDetailsName() : "" )));
				rootElement.appendChild(schoolname);
				
				Element reportname = doc.createElement("reportname");
				reportname.appendChild(doc.createTextNode("Annual Exam : "+report.getAcademicYear().getAcademicYearName()+ ")") );
				rootElement.appendChild(reportname);*/
				
			/*	Element standard = doc.createElement("standard");
				standard.appendChild(doc.createTextNode((report.getStudent().getStandard() !=null?report.getStudent().getStandard():"------")));
				rootElement.appendChild(standard);
			
				Element section = doc.createElement("section");
				section.appendChild(doc.createTextNode((report.getStudent().getSection()!=null?report.getStudent().getSection():"--------")));
				rootElement.appendChild(section);*/
					
					
				for(Student student : report.getStudentList()){
					
					Element studentList = doc.createElement("student");
					rootElement.appendChild(studentList);
					
					Element schoolname = doc.createElement("schoolname");
					schoolname.appendChild(doc.createTextNode((report.getSchoolDetails().getSchoolDetailsName() != null ? report.getSchoolDetails().getSchoolDetailsName() : "" )));
					studentList.appendChild(schoolname);
					
					Element academicyear = doc.createElement("academicyear");
					academicyear.appendChild(doc.createTextNode(( report.getAcademicYear().getAcademicYearName())));
					studentList.appendChild(academicyear);
					
					// nickname elements
					Element studentname = doc.createElement("studentname");
					studentname.appendChild(doc.createTextNode((student.getStudentName()!=null?student.getStudentName():"---------------")));
					studentList.appendChild(studentname);

					Element roll = doc.createElement("roll");
					roll.appendChild(doc.createTextNode((student.getRollNumber().toString()!=null?student.getRollNumber().toString():"---------")));
					studentList.appendChild(roll);
					
					Element house = doc.createElement("house");
					house.appendChild(doc.createTextNode((student.getHouse()!=null?student.getHouse():"------")));
					studentList.appendChild(house);

					Element standard = doc.createElement("standard");
					standard.appendChild(doc.createTextNode((student.getStandard()!=null?student.getStandard():"------")));
					studentList.appendChild(standard);

					Element section = doc.createElement("section");
					section.appendChild(doc.createTextNode((student.getSection()!=null?student.getSection():"--------")));
					studentList.appendChild(section);
					
					String totalCount = ""+report.getReportId();
					
					Element strength = doc.createElement("strength");
					strength.appendChild(doc.createTextNode((totalCount!=null?totalCount:"--------")));
					studentList.appendChild(strength);
					//int grandTotal=0;
					for(String subjectName : student.getStringListForStudent())	{
						int total = 0;
						int flag = 0;
						int flag1 = 0;
						int flag2 = 0;
						Element subject = doc.createElement("subject");
						studentList.appendChild(subject);
						
						Element subjectname = doc.createElement("subjectname");
						subjectname.appendChild(doc.createTextNode((subjectName != null ? subjectName : "")));
						subject.appendChild(subjectname);
						
						for(StudentResult studentResult : student.getStudentResultList()){
							if(studentResult.getSubject().equalsIgnoreCase(subjectName)){								
								Element examName = doc.createElement("examname");
								examName.appendChild(doc.createTextNode( (studentResult.getExam() != null ? studentResult.getExam().toString() : "")));
								subject.appendChild(examName);
								
								//if(studentResult.getTheoryObtained() == -1 && studentResult.getPracticalObtained() == -1 )
								if(studentResult.getExam().equalsIgnoreCase("Centralise")){									
									if(studentResult.getPractical() == 0 || null == studentResult.getPractical()){										
										String theoryMarks = null;
										Integer theory = studentResult.getTheoryObtained();
										if(theory == -1){
											 theoryMarks = "AB";
										}else{
											 theoryMarks = ""+theory;
										}
										
										Element theoryObtained=doc.createElement("theorymarks");
										theoryObtained.appendChild(doc.createTextNode( (theoryMarks!=null ?theoryMarks:"")));
										subject.appendChild(theoryObtained);
										
										Element practicalObtained=doc.createElement("practicalmarks");
										//practicalObtained.appendChild(doc.createTextNode( (studentResult.getPracticalObtained()!=null ?studentResult.getPracticalObtained().toString():"")));
										subject.appendChild(practicalObtained);
										// Integer internal_new = studentResult.getTotalObtained();
										//if(theory==-1 && internal_new ==-1)
										if(theory == -1 ){
											flag1 = -1;
										}else{
											total = total + studentResult.getTotalObtained();
										}
									}else{										
										String theoryMarks = null;
										Integer theory = studentResult.getTheoryObtained();
										if(theory == -1){
											 theoryMarks = "AB";
										}else{
											 theoryMarks = ""+theory;
										}
										
										Element theoryObtained = doc.createElement("theorymarks");
										theoryObtained.appendChild(doc.createTextNode((theoryMarks != null ? theoryMarks : "")));
										subject.appendChild(theoryObtained);
										
										String practicalMarks = null;
										Integer practical = studentResult.getPracticalObtained();
										if(practical == -1){
											 practicalMarks = "AB";
										}else{
											 practicalMarks = ""+practical;
										}
										 
										Element practicalObtained = doc.createElement("practicalmarks");
										practicalObtained.appendChild(doc.createTextNode( (practicalMarks!=null ?practicalMarks:"")));
										subject.appendChild(practicalObtained);
										
										if(theory == -1 && practical == -1){
											flag = -1;
										}else{										
											total = total + studentResult.getTotalObtained();
										}
									}
								}else{
								
									String internalMarks = null;
//									Integer practical = studentResult.getPracticalObtained();
//									Integer theory = studentResult.getTheoryObtained();
									Integer internal = studentResult.getTotalObtained();
									if(internal == -1){
										 internalMarks = "AB";
									}else{
										 internalMarks = ""+internal;
									}
																	
									Element totalObtained = doc.createElement("totalmarksinternal");
									totalObtained.appendChild(doc.createTextNode((internalMarks != null ? internalMarks : "")));
									subject.appendChild(totalObtained);
									
									if(internal == -1){
										flag2 = -1;
									}else{
										total = total + studentResult.getTotalObtained();
									}
								}									
							}
						}
					
						String totalNumber = null;
						if(flag == -1 && flag1 == -1 && flag2 == -1){
							totalNumber = "AB";
						}else{
							totalNumber = "" + total;
						}
						
						Element totalObtained = doc.createElement("total");
						totalObtained.appendChild(doc.createTextNode((totalNumber != null ? totalNumber : "")));
						subject.appendChild(totalObtained);
						
						flag = 0;
					}
					HashMap<Integer,Integer> reportMap = report.getReportMap();
					int roll_no = student.getRollNumber();
					String positionOfStudent = null;
					if(reportMap.containsKey(roll_no)){
						positionOfStudent = ""+reportMap.get(roll_no);
					}
					Element position = doc.createElement("position");
					position.appendChild(doc.createTextNode((positionOfStudent != null ? positionOfStudent : "")));
					studentList.appendChild(position);
					
				//	int subjectCount = student.getStringListForStudent().size();
				//	double percentage = grandTotal/subjectCount;
					
					/*Element position = doc.createElement("position");
					position.appendChild(doc.createTextNode((student.getStudentPassFail()!=null?student.getStudentPassFail():"----")));
					studentList.appendChild(position);*/
				}				
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);				
				StreamResult result = new StreamResult(new File(report.getXmlFilePath()+report.getXmlFileName()));
				transformer.transform(source, result);			
			}catch(Exception e) {
				e.printStackTrace();
				logger.error(e);
			}		
		}
		
		
	/***
	 * Naimisha..for NISM demo purpose
	 * **/	
		

public void createXMLFileForCertificate(Report report) {
	try{
		File outDir = new File(report.getXmlFilePath()); 
		boolean isDirCreated = outDir.mkdirs();
		if (isDirCreated)
			logger.info("In FileUploadDownload class fileUpload() method: upload file folder location created.");
		else
			logger.info("In FileUploadDownload class fileUpload() method: upload file folder location already exist.");
		
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		
		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("root");
		doc.appendChild(rootElement);
	
			for(Student student : report.getStudentList()){
					
				Element studentList = doc.createElement("student");
				rootElement.appendChild(studentList);
				
				Element schoolname = doc.createElement("schoolname");
				schoolname.appendChild(doc.createTextNode((report.getSchoolDetails().getSchoolDetailsName() != null ? report.getSchoolDetails().getSchoolDetailsName() : "" )));
				studentList.appendChild(schoolname);
				
				Element academicYear = doc.createElement("academicYear");
				academicYear.appendChild(doc.createTextNode((String) (report.getAcademicYear().getAcademicYearName()!= null ? report.getAcademicYear().getAcademicYearName() : "" )));
				studentList.appendChild(academicYear);
				
				Element roll = doc.createElement("roll");
				roll.appendChild(doc.createTextNode((student.getRollNumber().toString()!=null?student.getRollNumber().toString():"---------")));
				studentList.appendChild(roll);
				
				// nickname elements
				Element studentname = doc.createElement("studentname");
				studentname.appendChild(doc.createTextNode((student.getStudentName()!=null?student.getStudentName():"---------------")));
				studentList.appendChild(studentname);
			
				
				Element house = doc.createElement("house");
				house.appendChild(doc.createTextNode((student.getHouse()!=null?student.getHouse():"------")));
				studentList.appendChild(house);
			
				Element standard = doc.createElement("standard");
				standard.appendChild(doc.createTextNode((student.getStandard()!=null?student.getStandard():"------")));
				studentList.appendChild(standard);
			
				Element section = doc.createElement("section");
				section.appendChild(doc.createTextNode((student.getSection()!=null?student.getSection():"--------")));
				studentList.appendChild(section);
				
			//	StudentResult  setudentResult = new
				
				Element exam = doc.createElement("exam");
				exam.appendChild(doc.createTextNode((student.getStudentResultList().get(0).getExam()!=null?student.getStudentResultList().get(0).getExam():"--------")));
				studentList.appendChild(exam);
			}
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);		
			StreamResult result = new StreamResult(new File(report.getXmlFilePath()+report.getXmlFileName()));
			
			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);			
			transformer.transform(source, result);
		}catch (ParserConfigurationException pce) {
			logger.error("",pce);
		} catch (TransformerException tfe) {
			logger.error(tfe);
		}
	}


	/**
	 * new method by naimisha
	 * changes taken on 11042017
	 * for NOC
	 * **/

	public void createXMLFileForNOC(Report report) {
	try{
		File outDir = new File(report.getXmlFilePath()); 
		boolean isDirCreated = outDir.mkdirs();
		if (isDirCreated)
			logger.info("In FileUploadDownload class fileUpload() method: upload file folder location created.");
		else
			logger.info("In FileUploadDownload class fileUpload() method: upload file folder location already exist.");
		
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		
		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("root");
		doc.appendChild(rootElement);
	
			//for(Student student : report.getStudentList()){
					
				Element studentList = doc.createElement("student");
				rootElement.appendChild(studentList);
				
				/*Element schoolname = doc.createElement("schoolname");
				schoolname.appendChild(doc.createTextNode((report.getSchoolDetails().getSchoolDetailsName() != null ? report.getSchoolDetails().getSchoolDetailsName() : "" )));
				studentList.appendChild(schoolname);*/
				
				Element academicYear = doc.createElement("academicYear");
				academicYear.appendChild(doc.createTextNode((String) (report.getAcademicYear().getAcademicYearName()!= null ? report.getAcademicYear().getAcademicYearName() : "" )));
				studentList.appendChild(academicYear);
				
				Element roll = doc.createElement("roll");
				roll.appendChild(doc.createTextNode((report.getReportCode().toString()!=null?report.getReportCode().toString():"---------")));
				studentList.appendChild(roll);
				
				Element studentname = doc.createElement("studentname");
				studentname.appendChild(doc.createTextNode((report.getUpdatedBy()!=null?report.getUpdatedBy():"---------------")));
				studentList.appendChild(studentname);
			
				
			//}
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);		
			StreamResult result = new StreamResult(new File(report.getXmlFilePath()+report.getXmlFileName()));
			
			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);			
			transformer.transform(source, result);
		}catch (ParserConfigurationException pce) {
			pce.printStackTrace();
			logger.error("",pce);
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
			logger.error(tfe);
		}
	}
	
	/**new method by sourav.bhadra 20042017**/
	
	public void createXMLFileForGatePass(Report report) {
		try{
			File outDir = new File(report.getXmlFilePath()); 
			boolean isDirCreated = outDir.mkdirs();
			if (isDirCreated)
				logger.info("In FileUploadDownload class fileUpload() method: upload file folder location created.");
			else
				logger.info("In FileUploadDownload class fileUpload() method: upload file folder location already exist.");
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("root");
			doc.appendChild(rootElement);
		
			Element studentList = doc.createElement("student");
			rootElement.appendChild(studentList);
			
			Element academicYear = doc.createElement("academicYear");
			academicYear.appendChild(doc.createTextNode((String) (report.getAcademicYear().getAcademicYearName()!= null ? report.getAcademicYear().getAcademicYearName() : "" )));
			studentList.appendChild(academicYear);
				
			Element roll = doc.createElement("roll");
			roll.appendChild(doc.createTextNode((report.getReportCode().toString()!=null?report.getReportCode().toString():"---------")));
			studentList.appendChild(roll);
			
			Element studentname = doc.createElement("studentname");
			studentname.appendChild(doc.createTextNode((report.getUpdatedBy()!=null?report.getUpdatedBy():"---------------")));
			studentList.appendChild(studentname);
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);		
			StreamResult result = new StreamResult(new File(report.getXmlFilePath()+report.getXmlFileName()));
			
			transformer.transform(source, result);
		}catch (ParserConfigurationException pce) {
			pce.printStackTrace();
			logger.error("",pce);
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
			logger.error(tfe);
		}

	}


	public void createXMLFileForStudentPromotion(Report report) {
		try{
			File outDir = new File(report.getXmlFilePath()); 
			boolean isDirCreated = outDir.mkdirs();
			if (isDirCreated)
				logger.info("In FileUploadDownload class fileUpload() method: upload file folder location created.");
			else
				logger.info("In FileUploadDownload class fileUpload() method: upload file folder location already exist.");
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("root");
			doc.appendChild(rootElement);
			
			/*Element student = doc.createElement("student");
			rootElement.appendChild(student);
			*/
			Element program = doc.createElement("program");
			program.appendChild(doc.createTextNode( (report.getStudent().getCourseId()!= null ? (report.getStudent().getCourseId()) : "" )));
			rootElement.appendChild(program);
			
			Element term = doc.createElement("term");
			term.appendChild(doc.createTextNode( (report.getStudent().getStatus()!= null ? (report.getStudent().getStatus()) : "" )));
			rootElement.appendChild(term);
			
			Element roll = doc.createElement("roll");
			roll.appendChild(doc.createTextNode( (report.getStudent().getRoll()!= null ? (report.getStudent().getRoll()) : "" )));
			rootElement.appendChild(roll);
			
			Element section = doc.createElement("section");
			section.appendChild(doc.createTextNode( (report.getStudent().getSection()!= null ? (report.getStudent().getSection()) : "" )));
			rootElement.appendChild(section);
			Student stu = report.getStudent();
			
			for(StudentResult studentResult : stu.getStudentResultList()){		
				Element subject = doc.createElement("subject");
				rootElement.appendChild(subject);
					Element subjectName = doc.createElement("subjectName");
					subjectName.appendChild(doc.createTextNode( (studentResult.getSubject() != null ? studentResult.getSubject().toString() : "")));
					subject.appendChild(subjectName);
					
					Element theoryObtained = doc.createElement("theoryObtained");
					theoryObtained.appendChild(doc.createTextNode( (studentResult.getTheoryObtained() != null ? studentResult.getTheoryObtained().toString() : "")));
					subject.appendChild(theoryObtained);
					
					Element practicalObtained = doc.createElement("practicalObtained");
					practicalObtained.appendChild(doc.createTextNode( (studentResult.getPracticalObtained() != null ? studentResult.getPracticalObtained().toString() : "")));
					subject.appendChild(practicalObtained);
			}	
					
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);		
			StreamResult result = new StreamResult(new File(report.getXmlFilePath()+report.getXmlFileName()));
			transformer.transform(source, result);
			
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	/**New CBSE System start**/
	
	public void createXMLFileForExamResultNew(Report reportData) {
		try{
			File outDir = new File(reportData.getXmlFilePath()); 
			boolean isDirCreated = outDir.mkdirs();
			if (isDirCreated)
				logger.info("In FileUploadDownload class fileUpload() method: upload file folder location created.");
			else
				logger.info("In FileUploadDownload class fileUpload() method: upload file folder location already exist.");
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("root");
			doc.appendChild(rootElement);
		
			for(Student student : reportData.getStudentList()){
				String final_weitage=null;
				int total_weitage = 0;	
					
				Element studentList = doc.createElement("student");
				rootElement.appendChild(studentList);
				
				Element schoolname = doc.createElement("schoolname");
				schoolname.appendChild(doc.createTextNode((reportData.getSchoolDetails().getSchoolDetailsName() != null ? reportData.getSchoolDetails().getSchoolDetailsName() : "" )));
				studentList.appendChild(schoolname);
				
				Element academicYear = doc.createElement("academicYear");
				academicYear.appendChild(doc.createTextNode((String) (reportData.getAcademicYear().getAcademicYearName()!= null ? reportData.getAcademicYear().getAcademicYearName() : "" )));
				studentList.appendChild(academicYear);
				
				Element report = doc.createElement("report");
				report.appendChild(doc.createTextNode("MARKS STATEMENT"));
				studentList.appendChild(report);
			
				Element termdate = doc.createElement("termdate");
				termdate.appendChild(doc.createTextNode((reportData.getSchoolDetails().getExamName()!=null?reportData.getSchoolDetails().getExamName():"--------")));
				studentList.appendChild(termdate);
				
				Element roll = doc.createElement("roll");
				roll.appendChild(doc.createTextNode((student.getRollNumber().toString()!=null?student.getRollNumber().toString():"---------")));
				studentList.appendChild(roll);
				
				// nickname elements
				Element studentname = doc.createElement("studentname");
				studentname.appendChild(doc.createTextNode((student.getStudentName()!=null?student.getStudentName():"---------------")));
				studentList.appendChild(studentname);
			
				
				Element house = doc.createElement("house");
				house.appendChild(doc.createTextNode((student.getHouse()!=null?student.getHouse():"------")));
				studentList.appendChild(house);
			
				Element standard = doc.createElement("standard");
				standard.appendChild(doc.createTextNode((student.getStandard()!=null?student.getStandard():"------")));
				studentList.appendChild(standard);
			
				Element section = doc.createElement("section");
				section.appendChild(doc.createTextNode((student.getSection()!=null?student.getSection():"--------")));
				studentList.appendChild(section);
				
				Element bloodgroup = doc.createElement("bloodgroup");
				bloodgroup.appendChild(doc.createTextNode((student.getBloodGroup()!=null?student.getBloodGroup():"NA")));
				studentList.appendChild(bloodgroup);
				
				if(student.getResource()!=null){
					Element fathername = doc.createElement("fathername");
					fathername.appendChild(doc.createTextNode((student.getResource().getFatherFirstName()!=null?student.getResource().getFatherFirstName():"--------")));
					studentList.appendChild(fathername);
					
					Element mothername = doc.createElement("mothername");
					mothername.appendChild(doc.createTextNode((student.getResource().getMotherFirstName()!=null?student.getResource().getMotherFirstName():"--------")));
					studentList.appendChild(mothername);
					
					Element dob = doc.createElement("dob");
					dob.appendChild(doc.createTextNode((student.getResource().getDateOfBirth()!=null?student.getResource().getDateOfBirth():"--------")));
					studentList.appendChild(dob);
					}
				if(reportData.getSchoolDetails().getExamName().equalsIgnoreCase("Term1")){
						for(StudentResult studentResult : student.getStudentResultList()){
							Element subject = doc.createElement("subject");
							studentList.appendChild(subject);
							Double subjecTotal = 0.00 ;
							int subjectTotalInt = 0;
							
							Element subjectname = doc.createElement("subjectname");
							subjectname.appendChild(doc.createTextNode((studentResult.getSubject()!=null?studentResult.getSubject():"")));
							subject.appendChild(subjectname);
							//if(reportData.getSchoolDetails().getExamName().equalsIgnoreCase("Term1")){
								for(Exam examObj:studentResult.getExamList() ){
									if(examObj.getExamName().equalsIgnoreCase("PerioDic Test")){
										Element examMarks = doc.createElement("PTexammarks");
										examMarks.appendChild(doc.createTextNode((examObj.getGrade()!=null?examObj.getGrade():"")));
										subject.appendChild(examMarks);
									}else if(examObj.getExamName().equalsIgnoreCase("Note Book")){
										Element examMarks = doc.createElement("NBexammarks");
										examMarks.appendChild(doc.createTextNode((examObj.getGrade()!=null?examObj.getGrade():"")));
										subject.appendChild(examMarks);
									}else if(examObj.getExamName().equalsIgnoreCase("Sub Enrichment")){
										Element examMarks = doc.createElement("SEexammarks");
										examMarks.appendChild(doc.createTextNode((examObj.getGrade()!=null?examObj.getGrade():"")));
										subject.appendChild(examMarks);
									}else if(examObj.getExamName().equalsIgnoreCase("Half Yearly Exam")){
										Element examMarks = doc.createElement("HYexammarks");
										examMarks.appendChild(doc.createTextNode((examObj.getGrade()!=null?examObj.getGrade():"")));
										subject.appendChild(examMarks);
									}
								
									
									if(null != examObj.getGrade()){
										if(examObj.getGrade().equalsIgnoreCase("UM")||examObj.getGrade().equalsIgnoreCase("AB")){
											subjecTotal = subjecTotal + 0;
										}else{
											subjecTotal = subjecTotal + Double.parseDouble(examObj.getGrade());
										}
										
									}
									
								}
								 String subjectString = String.format( "%.2f", subjecTotal ) ;
								subjectTotalInt =  (int) Math.round(subjecTotal);
								String subjectTotalChar = subjectTotalInt +"";
								Element total = doc.createElement("total");
								total.appendChild(doc.createTextNode((subjectTotalChar!=null?subjectTotalChar:"")));
								subject.appendChild(total);
								
								String standardValue = student.getStandard();
								String grade = getGradeForSubjectTotal(subjectTotalInt,standardValue);
								
							
								Element gradeElement = doc.createElement("grade");
								gradeElement.appendChild(doc.createTextNode((grade!=null?grade:"")));
								subject.appendChild(gradeElement);
								
								
								/*for(Exam examObj:studentResult.getExamList() ){
									
									
									Element examMarks = doc.createElement("exammarks"); //For Term 2
									examMarks.appendChild(doc.createTextNode(("")));
									subject.appendChild(examMarks);
									
									
								}*/
								
								/*String subjectTotalCharecter = "";
								Element totalTerm2 = doc.createElement("exammarks");
								totalTerm2.appendChild(doc.createTextNode((subjectTotalCharecter!=null?subjectTotalCharecter:"")));
								subject.appendChild(totalTerm2);
								
								
								
							
								Element gradeTerm2 = doc.createElement("exammarks");
								gradeTerm2.appendChild(doc.createTextNode(("")));
								subject.appendChild(gradeTerm2);*/
							//}
							
							System.out.println("subjecTotal===="+subjecTotal);
							
							
							
						}
				}else if(reportData.getSchoolDetails().getExamName().equalsIgnoreCase("Term2")){
					System.out.println("within term2");
					for(StudentResult studentResult : student.getStudentResultList()){
						Element subject = doc.createElement("subject");
						studentList.appendChild(subject);
						
						
						Element subjectname = doc.createElement("subjectname");
						subjectname.appendChild(doc.createTextNode((studentResult.getSubject()!=null?studentResult.getSubject():"")));
						subject.appendChild(subjectname);
						//if(reportData.getSchoolDetails().getExamName().equalsIgnoreCase("Term1")){
						for(Subject subjectObj : studentResult.getSubjectList()){
							Double subjecTotal = 0.00 ;
							int subjectTotalInt = 0;
							for(Exam examObj:subjectObj.getExamList() ){
								
								Element examMarks = doc.createElement("exammarks");
								examMarks.appendChild(doc.createTextNode((examObj.getGrade()!=null?examObj.getGrade():"")));
								subject.appendChild(examMarks);
								
								/*if(null != examObj.getGrade()){
									subjecTotal = subjecTotal + Double.parseDouble(examObj.getGrade());
								}*/
								if(null != examObj.getGrade()){
									if(examObj.getGrade().equalsIgnoreCase("UM")||examObj.getGrade().equalsIgnoreCase("AB")){
										subjecTotal = subjecTotal + 0;
									}else{
										subjecTotal = subjecTotal + Double.parseDouble(examObj.getGrade());
									}
									
								}
								
							}
							String subjectTotalChar = subjecTotal.toString();
							subjectTotalInt =  (int) Math.round(subjecTotal);
							String subjectTotalString = subjectTotalInt +"";
							Element total = doc.createElement("exammarks");
							total.appendChild(doc.createTextNode((subjectTotalString!=null?subjectTotalString:"")));
							subject.appendChild(total);
							
							String standardValue = student.getStandard();
							String grade = getGradeForSubjectTotal(subjectTotalInt,standardValue);
							
						
							Element gradeElement = doc.createElement("exammarks");
							gradeElement.appendChild(doc.createTextNode((grade!=null?grade:"")));
							subject.appendChild(gradeElement);
							
						
						 System.out.println("subjecTotal===="+subjecTotal);
						
						}
						
						
					}
				}else if(reportData.getSchoolDetails().getExamName().equalsIgnoreCase("AnnualExam1")){
					for(StudentResult studentResult : student.getStudentResultList()){
						Element subject = doc.createElement("subject");
						studentList.appendChild(subject);
						Double subjecTotal = 0.00 ;
						int subjectTotalInt = 0;
						Element subjectname = doc.createElement("subjectname");
						subjectname.appendChild(doc.createTextNode((studentResult.getSubject()!=null?studentResult.getSubject():"")));
						subject.appendChild(subjectname);
						//if(reportData.getSchoolDetails().getExamName().equalsIgnoreCase("Term1")){
							for(Exam examObj:studentResult.getExamList() ){
								
								
								Element examMarks = doc.createElement("exammarks");
								examMarks.appendChild(doc.createTextNode((examObj.getGrade()!=null?examObj.getGrade():"")));
								subject.appendChild(examMarks);
								
								/*if(null != examObj.getGrade()){
									subjecTotal = subjecTotal + Double.parseDouble(examObj.getGrade());
								}*/
								if(null != examObj.getGrade()){
									if(examObj.getGrade().equalsIgnoreCase("UM")||examObj.getGrade().equalsIgnoreCase("AB")){
										subjecTotal = subjecTotal + 0;
									}else{
										subjecTotal = subjecTotal + Double.parseDouble(examObj.getGrade());
									}
									
								}
								
							}
							
							//String subjectTotalChar = subjecTotal.toString();
							
							String subjectTotalChar = subjecTotal.toString();
							subjectTotalInt =  (int) Math.round(subjecTotal);
							String subjectTotalString = subjectTotalInt +"";
							
							
							Element total = doc.createElement("exammarks");
							total.appendChild(doc.createTextNode((subjectTotalChar!=null?subjectTotalChar:"")));
							subject.appendChild(total);
							
							String standardValue = student.getStandard();
							String grade = getGradeForSubjectTotal(subjectTotalInt,standardValue);
							
						
							Element gradeElement = doc.createElement("exammarks");
							gradeElement.appendChild(doc.createTextNode((grade!=null?grade:"")));
							subject.appendChild(gradeElement);
							
							
							
						//}
						
						System.out.println("subjecTotal===="+subjecTotal);
						
						
						
					}
			}else if(reportData.getSchoolDetails().getExamName().equalsIgnoreCase("PT1") || reportData.getSchoolDetails().getExamName().equalsIgnoreCase("PT2") ||reportData.getSchoolDetails().getExamName().equalsIgnoreCase("PT3")){
				Double allTotal = 0.00 ;
				for(StudentResult studentResult : student.getStudentResultList()){
					Element subject = doc.createElement("subject");
					studentList.appendChild(subject);
					Double subjecTotal = 0.00 ;
					
					Element subjectname = doc.createElement("subjectname");
					subjectname.appendChild(doc.createTextNode((studentResult.getSubject()!=null?studentResult.getSubject():"")));
					subject.appendChild(subjectname);
					for(Exam examObj:studentResult.getExamList() ){
						
						Element maxmarks = doc.createElement("maxmarks");
						maxmarks.appendChild(doc.createTextNode((examObj.getExamName()!=null?examObj.getExamName():"")));
						subject.appendChild(maxmarks);
						
						Element examMarks = doc.createElement("exammarks");
						examMarks.appendChild(doc.createTextNode((examObj.getGrade()!=null?examObj.getGrade():"")));
						subject.appendChild(examMarks);
						
						if(null != examObj.getGrade()){
							if(examObj.getGrade().equalsIgnoreCase("UM")||examObj.getGrade().equalsIgnoreCase("AB") || examObj.getGrade().equalsIgnoreCase("NA")){
								subjecTotal = subjecTotal + 0.0;
								allTotal = allTotal + 0.0;
							}else{
								subjecTotal = subjecTotal + Double.parseDouble(examObj.getGrade());
								allTotal = allTotal + Double.parseDouble(examObj.getGrade());
							}
							
						}
					}
					String subjectString = String.format( "%.2f", subjecTotal ) ;
					
					/*Element total = doc.createElement("total");
					total.appendChild(doc.createTextNode((subjectString!=null?subjectString:"")));
					subject.appendChild(total);*/
				}
				
				String allTotalString = String.format( "%.2f", allTotal ) ;
				
				Element total = doc.createElement("total");
				total.appendChild(doc.createTextNode((allTotalString!=null?allTotalString:"")));
				studentList.appendChild(total);
			}
						if(student.getCoScholasticResultList()!=null && student.getCoScholasticResultList().size()!=0){
		//					System.out.println("##....... "+student.getCoScholasticResultList().size());
							if(reportData.getSchoolDetails().getExamName().equalsIgnoreCase("Term1")){
								for(CoScholasticResult csr : student.getCoScholasticResultList()){
									
									//Element coscholasticmarks = doc.createElement("coscholasticmarks");
									//studentList.appendChild(coscholasticmarks);
									
									if(csr.getHead()!=null && csr.getHead().trim().equalsIgnoreCase("PHYSICAL EDUCATION")){	
										
										Element coscholasticphysicaleducationTerm1 = doc.createElement("coscholastic");
										coscholasticphysicaleducationTerm1.appendChild(doc.createTextNode(( "PHYSICAL EDUCATION" )));
										studentList.appendChild(coscholasticphysicaleducationTerm1);
										
										Element physicaleducationTerm1 = doc.createElement("physicalEducation");
										physicaleducationTerm1.appendChild(doc.createTextNode((csr.getGrade()!= null ? csr.getGrade() : "-" )));
										studentList.appendChild(physicaleducationTerm1);
										

										/*Element coscholasticphysicaleducationTerm2 = doc.createElement("coscholastic");
										coscholasticphysicaleducationTerm2.appendChild(doc.createTextNode(( "PHYSICAL EDUCATION" )));
										studentList.appendChild(coscholasticphysicaleducationTerm2);
										
										Element physicaleducationTerm2 = doc.createElement("coscholastic");
										physicaleducationTerm2.appendChild(doc.createTextNode(("" )));
										studentList.appendChild(physicaleducationTerm2);*/
										
										
									}
									if(csr.getHead()!=null && csr.getHead().trim().equalsIgnoreCase("DISCIPLINE")){	
										
										Element coscholasticdisciplineTerm1 = doc.createElement("coscholastic");
										coscholasticdisciplineTerm1.appendChild(doc.createTextNode(( "DISCIPLINE" )));
										studentList.appendChild(coscholasticdisciplineTerm1);
										
										Element disciplineTerm1 = doc.createElement("discipline");
										disciplineTerm1.appendChild(doc.createTextNode((csr.getGrade()!= null ? csr.getGrade() : "-" )));
										studentList.appendChild(disciplineTerm1);
										
										/*Element coscholasticdisciplineTerm2 = doc.createElement("coscholastic");
										coscholasticdisciplineTerm2.appendChild(doc.createTextNode(( "DISCIPLINE" )));
										studentList.appendChild(coscholasticdisciplineTerm2);
										
										Element disciplineTerm2 = doc.createElement("coscholastic");
										coscholasticdisciplineTerm2.appendChild(doc.createTextNode(("" )));
										studentList.appendChild(coscholasticdisciplineTerm2);*/
										
										
									}
									if(csr.getHead()!=null && csr.getHead().trim().equalsIgnoreCase("HEALTH EDUCATION")){	
										
										Element coscholastichealtheducationTerm1 = doc.createElement("coscholastic");
										coscholastichealtheducationTerm1.appendChild(doc.createTextNode(( "HEALTH EDUCATION" )));
										studentList.appendChild(coscholastichealtheducationTerm1);
										
										Element healtheducationterm1 = doc.createElement("healtheducation");
										healtheducationterm1.appendChild(doc.createTextNode((csr.getGrade()!= null ? csr.getGrade() : "-" )));
										studentList.appendChild(healtheducationterm1);
										
									/*	Element coscholastichealtheducationTerm2 = doc.createElement("coscholastic");
										coscholastichealtheducationTerm2.appendChild(doc.createTextNode(( "HEALTH EDUCATION" )));
										studentList.appendChild(coscholastichealtheducationTerm2);
										
										Element healtheducationterm2 = doc.createElement("coscholastic");
										healtheducationterm2.appendChild(doc.createTextNode(("")));
										studentList.appendChild(healtheducationterm2);*/
										
										
									}
									if(csr.getHead()!=null && csr.getHead().trim().equalsIgnoreCase("WORK EDUCATION")){	
										
										Element coscholasticworkeducationTerm1 = doc.createElement("coscholastic");
										coscholasticworkeducationTerm1.appendChild(doc.createTextNode(( "WORK EDUCATION" )));
										studentList.appendChild(coscholasticworkeducationTerm1);
										
										Element workeducationterm1 = doc.createElement("workeducation");
										workeducationterm1.appendChild(doc.createTextNode((csr.getGrade()!= null ? csr.getGrade() : "-" )));
										studentList.appendChild(workeducationterm1);
										
										
										/*Element coscholasticworkeducationTerm2 = doc.createElement("coscholastic");
										coscholasticworkeducationTerm2.appendChild(doc.createTextNode(( "WORK EDUCATION" )));
										studentList.appendChild(coscholasticworkeducationTerm2);
										
										Element workeducationterm2 = doc.createElement("coscholastic");
										workeducationterm2.appendChild(doc.createTextNode(("")));
										studentList.appendChild(workeducationterm2);*/
										
									}
								}
							}
							if(reportData.getSchoolDetails().getExamName().equalsIgnoreCase("AnnualExam1")){
								for(CoScholasticResult csr : student.getCoScholasticResultList()){
									if(csr.getHead()!=null && csr.getHead().trim().equalsIgnoreCase("PHYSICAL EDUCATION")){	
										
										Element coscholasticphysicaleducationTerm1 = doc.createElement("coscholastic");
										coscholasticphysicaleducationTerm1.appendChild(doc.createTextNode(( "PHYSICAL EDUCATION" )));
										studentList.appendChild(coscholasticphysicaleducationTerm1);
										
										Element physicaleducationTerm1 = doc.createElement("coscholastic");
										physicaleducationTerm1.appendChild(doc.createTextNode((csr.getGrade()!= null ? csr.getGrade() : "-" )));
										studentList.appendChild(physicaleducationTerm1);
										
										
									}
									if(csr.getHead()!=null && csr.getHead().trim().equalsIgnoreCase("DISCIPLINE")){	
										
										Element coscholasticdisciplineTerm1 = doc.createElement("coscholastic");
										coscholasticdisciplineTerm1.appendChild(doc.createTextNode(( "DISCIPLINE" )));
										studentList.appendChild(coscholasticdisciplineTerm1);
										
										Element disciplineTerm1 = doc.createElement("coscholastic");
										disciplineTerm1.appendChild(doc.createTextNode((csr.getGrade()!= null ? csr.getGrade() : "-" )));
										studentList.appendChild(disciplineTerm1);
										
										
										
									}
									if(csr.getHead()!=null && csr.getHead().trim().equalsIgnoreCase("HEALTH EDUCATION")){	
										
										Element coscholastichealtheducationTerm1 = doc.createElement("coscholastic");
										coscholastichealtheducationTerm1.appendChild(doc.createTextNode(( "HEALTH EDUCATION" )));
										studentList.appendChild(coscholastichealtheducationTerm1);
										
										Element healtheducationterm1 = doc.createElement("coscholastic");
										healtheducationterm1.appendChild(doc.createTextNode((csr.getGrade()!= null ? csr.getGrade() : "-" )));
										studentList.appendChild(healtheducationterm1);
										
										
									}
									if(csr.getHead()!=null && csr.getHead().trim().equalsIgnoreCase("WORK EDUCATION")){	
										
										Element coscholasticworkeducationTerm1 = doc.createElement("coscholastic");
										coscholasticworkeducationTerm1.appendChild(doc.createTextNode(( "WORK EDUCATION" )));
										studentList.appendChild(coscholasticworkeducationTerm1);
										
										Element workeducationterm1 = doc.createElement("coscholastic");
										workeducationterm1.appendChild(doc.createTextNode((csr.getGrade()!= null ? csr.getGrade() : "-" )));
										studentList.appendChild(workeducationterm1);
										
										
									}
								}
							}
							
						}
				}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);		
			StreamResult result = new StreamResult(new File(reportData.getXmlFilePath()+reportData.getXmlFileName()));
			
			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);			
			transformer.transform(source, result);
			}catch(Exception e) {
				e.printStackTrace();
				logger.error(e);
			}
		}


	/**
	 * naimisha 
	 * 03.08.2017
	 * new CBSE System**/
	private String getGradeForSubjectTotal(int subjectTotalInt , String standardValue) {
	 	
		Student student = new Student();
		student.setStandard(standardValue);
		student.setStudentTotal(subjectTotalInt);
		String grade = null;
		List<StudentResult>gradeList = reportDAO.selectGradeForSubjectTotal(student);
		for(StudentResult gradeValue : gradeList){
			String[] gradeArr = gradeValue.getStatus().split("-");
			int minGradeRange = Integer.parseInt(gradeArr[0]);
			int maxRange = Integer.parseInt(gradeArr[1]);
			if(subjectTotalInt >= minGradeRange && subjectTotalInt <= maxRange){
				grade = gradeValue.getGrade();
			}
	 	}
		return grade;
	}
	
	public void createXMLFileForConsolidateExamResultNew(Report reportData) {
		try{
			File outDir = new File(reportData.getXmlFilePath()); 
			boolean isDirCreated = outDir.mkdirs();
			if (isDirCreated)
				logger.info("In XMLBuilder class fileUpload() method: upload file folder location created.");
			else
				logger.info("In XMLBuilder class fileUpload() method: upload file folder location already exist.");
			
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		
			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("root");
			doc.appendChild(rootElement);
			
			
			Element schoolname = doc.createElement("schoolname");
			schoolname.appendChild(doc.createTextNode((reportData.getSchoolDetails().getSchoolDetailsName() != null ? reportData.getSchoolDetails().getSchoolDetailsName() : "" )));
			rootElement.appendChild(schoolname);
			
			Element report = doc.createElement("report");
			report.appendChild(doc.createTextNode("MARKS STATEMENT : "));
			rootElement.appendChild(report);
			
			String examname = reportData.getSchoolDetails().getExamName();				
			String examName = "";
			
			if(examname.equalsIgnoreCase("Term1")){					
				examName = "Term 1";
			}else if(examname.equalsIgnoreCase("Term2")){					
				examName = "Term 2";
			}else if(examname.equalsIgnoreCase("AnnualExam1")){					
				examName = "Annual Exam";
			}else if(examname.equalsIgnoreCase("PT1")){
				examName = "Periodic Test 1";
			}else if(examname.equalsIgnoreCase("PT2")){
				examName = "Periodic Test 2";
			}else if(examname.equalsIgnoreCase("PT3")){
				examName = "Periodic Test 3";
			}else if(examname.equalsIgnoreCase("T1_PT1")){
			examName = "Periodic Test";
			}
			
			Element exam = doc.createElement("exam");
			report.appendChild(doc.createTextNode(reportData.getAcademicYear().getAcademicYearName() +" ("+(examName)+")"));
			rootElement.appendChild(report);
		
			Element standard = doc.createElement("standard");
			standard.appendChild(doc.createTextNode((reportData.getStudent().getStandard() !=null?reportData.getStudent().getStandard():"------")));
			rootElement.appendChild(standard);
		
			Element section = doc.createElement("section");
			section.appendChild(doc.createTextNode((reportData.getStudent().getSection()!=null?reportData.getStudent().getSection():"--------")));
			rootElement.appendChild(section);
		
			for(SubjectGroup subjectGroup : reportData.getSubjectGroupList()){
				Element subjectGroupList = doc.createElement("subjectgroup");
				rootElement.appendChild(subjectGroupList);
				
				Element subjectgroupname = doc.createElement("subjectgroupname");
				subjectgroupname.appendChild(doc.createTextNode((subjectGroup.getSubjectGroupName()!=null?subjectGroup.getSubjectGroupName():"----")));
				subjectGroupList.appendChild(subjectgroupname);
			}
			if(examname.equalsIgnoreCase("Term1")){
				for(Student student : reportData.getStudentList()){
					
					Element studentList = doc.createElement("student");
					rootElement.appendChild(studentList);
			
					
					Element roll = doc.createElement("roll");
					roll.appendChild(doc.createTextNode((student.getRollNumber().toString()!=null?student.getRollNumber().toString():"---------")));
					studentList.appendChild(roll);
					
					// nickname elements
					Element studentname = doc.createElement("name");
					studentname.appendChild(doc.createTextNode((student.getStudentName()!=null?student.getStudentName():"---------------")));
					studentList.appendChild(studentname);
			

					for(StudentResult studentResult : student.getStudentResultList()){
						Element subject = doc.createElement("subject");
						studentList.appendChild(subject);
						
						Element subjectname = doc.createElement("subjectname");
						subjectname.appendChild(doc.createTextNode((studentResult.getSubject()!=null?studentResult.getSubject():"-")));
						subject.appendChild(subjectname);
						
						Element periodicTest = doc.createElement("periodictest");
						periodicTest.appendChild(doc.createTextNode((studentResult.getPeriodicTest()!=null?studentResult.getPeriodicTest().toString():"-")));
						subject.appendChild(periodicTest);
						
						Element noteBook = doc.createElement("notebook");
						noteBook.appendChild(doc.createTextNode((studentResult.getNoteBook()!=null?studentResult.getNoteBook().toString():"-")));
						subject.appendChild(noteBook);
						
						Element subEnrichment = doc.createElement("subenrichment");
						subEnrichment.appendChild(doc.createTextNode((studentResult.getSubEnrichment()!=null?studentResult.getSubEnrichment().toString():"-")));
						subject.appendChild(subEnrichment);
						
						Element halfYearly = doc.createElement("halfyearly");
						halfYearly.appendChild(doc.createTextNode((studentResult.getHalfYearly()!=null?studentResult.getHalfYearly().toString():"-")));
						subject.appendChild(halfYearly);
						
						Element subjectTotal = doc.createElement("subjecttotal");
						subjectTotal.appendChild(doc.createTextNode((studentResult.getTotalObtainedChar()!=null?studentResult.getTotalObtainedChar().toString():"-")));
						subject.appendChild(subjectTotal);
						
						Element grade = doc.createElement("grade");
						grade.appendChild(doc.createTextNode((studentResult.getGrade()!=null?studentResult.getGrade().toString():"-")));
						subject.appendChild(grade);
						
					}
					
					Element total = doc.createElement("total");
					total.appendChild(doc.createTextNode((student.getStudentPercent()!=null?((new DecimalFormat("##.0").format(student.getStudentPercent())).toString()):"-")));
					studentList.appendChild(total);
					
			
					
					Element rank = doc.createElement("rank");
					rank.appendChild(doc.createTextNode((student.getStudentPassFail()!=null?student.getStudentPassFail():"-")));
					studentList.appendChild(rank);
					
				}
			}else if(examname.equalsIgnoreCase("PT1")||examname.equalsIgnoreCase("PT2")||examname.equalsIgnoreCase("PT3")){
				for(Student student : reportData.getStudentList()){
					
					Element studentList = doc.createElement("student");
					rootElement.appendChild(studentList);
			
					
					Element roll = doc.createElement("roll");
					roll.appendChild(doc.createTextNode((student.getRollNumber().toString()!=null?student.getRollNumber().toString():"---------")));
					studentList.appendChild(roll);
					
					// nickname elements
					Element studentname = doc.createElement("name");
					studentname.appendChild(doc.createTextNode((student.getStudentName()!=null?student.getStudentName():"---------------")));
					studentList.appendChild(studentname);
			

					for(StudentResult studentResult : student.getStudentResultList()){
						Element subject = doc.createElement("subject");
						studentList.appendChild(subject);
						
						Element subjectname = doc.createElement("subjectname");
						subjectname.appendChild(doc.createTextNode((studentResult.getSubject()!=null?studentResult.getSubject():"-")));
						subject.appendChild(subjectname);
						
						Element totalMarks = doc.createElement("totalmarks");
						totalMarks.appendChild(doc.createTextNode((studentResult.getTotalObtainedChar()!=null?studentResult.getTotalObtainedChar():"-")));
						subject.appendChild(totalMarks);
						
						Element totalObtained = doc.createElement("totalobtained");
						totalObtained.appendChild(doc.createTextNode((studentResult.getTheoryObtainedChar()!=null?studentResult.getTheoryObtainedChar():"-")));
						subject.appendChild(totalObtained);
						
						
						
					}
					
					Element total = doc.createElement("total");
					total.appendChild(doc.createTextNode((student.getStudentTotalChar()!=null?student.getStudentTotalChar().toString():"-")));
					studentList.appendChild(total);
					
					Element percentage = doc.createElement("percentage");
					percentage.appendChild(doc.createTextNode((student.getStudentPercentChar()!=null?student.getStudentPercentChar():"-")));
					studentList.appendChild(percentage); //Modified By Naimisha 09082017
					
					Element rank = doc.createElement("rank");
					rank.appendChild(doc.createTextNode((student.getStudentPassFail()!=null?student.getStudentPassFail():"-")));
					studentList.appendChild(rank);
					
				}
			}else if(examname.equalsIgnoreCase("T1_PT1")){
				for(Student student : reportData.getStudentList()){
				
					Element studentList = doc.createElement("student");
					rootElement.appendChild(studentList);
			
					
					Element roll = doc.createElement("roll");
					roll.appendChild(doc.createTextNode((student.getRollNumber().toString()!=null?student.getRollNumber().toString():"---------")));
					studentList.appendChild(roll);
					
					// nickname elements
					Element studentname = doc.createElement("name");
					studentname.appendChild(doc.createTextNode((student.getStudentName()!=null?student.getStudentName():"---------------")));
					studentList.appendChild(studentname);
			

					for(StudentResult studentResult : student.getStudentResultList()){
						Element subject = doc.createElement("subject");
						studentList.appendChild(subject);
						
						Element subjectname = doc.createElement("subjectname");
						subjectname.appendChild(doc.createTextNode((studentResult.getSubject()!=null?studentResult.getSubject():"-")));
						subject.appendChild(subjectname);
						
						/*Element totalMarks = doc.createElement("totalmarks");
						totalMarks.appendChild(doc.createTextNode((studentResult.getTotalObtainedChar()!=null?studentResult.getTotalObtainedChar():"-")));
						subject.appendChild(totalMarks);
						*/
						Element totalObtained = doc.createElement("marks");
						totalObtained.appendChild(doc.createTextNode((studentResult.getTheoryObtainedChar()!=null?studentResult.getTheoryObtainedChar():"-")));
						subject.appendChild(totalObtained);
						
						
						
					}
					
					Element total = doc.createElement("total");
					total.appendChild(doc.createTextNode((student.getStudentTotalChar()!=null?student.getStudentTotalChar().toString():"-")));
					studentList.appendChild(total);
					
					Element percent = doc.createElement("percent");
	//				all_total.appendChild(doc.createTextNode(new Double(new DecimalFormat("##.0").format(total)).toString()));
					percent.appendChild(doc.createTextNode((student.getStudentPercentChar()!=null?student.getStudentPercentChar():"-")));
					studentList.appendChild(percent);
					
					Element rank = doc.createElement("rank");
					rank.appendChild(doc.createTextNode((student.getStudentPassFail()!=null?student.getStudentPassFail():"-")));
					studentList.appendChild(rank);   //Added by naimisha 09082017
					
				}
			}
			// write the content into xml file
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);				
			StreamResult result = new StreamResult(new File(reportData.getXmlFilePath()+reportData.getXmlFileName()));
			transformer.transform(source, result);
			
		} catch (ParserConfigurationException pce) {
			  logger.error(pce);
			 
		  } catch (TransformerException tfe) {
			  logger.error(tfe);
		  } catch (Exception e) {
			  e.printStackTrace();
			  logger.error(e);
		  }
		
	}


	public void createXMLFileForConsolidateExamResultXI_XIINew(Report reportData) {

		try{
			File outDir = new File(reportData.getXmlFilePath()); 
			boolean isDirCreated = outDir.mkdirs();
			if (isDirCreated)
				logger.info("In XMLBuilder class fileUpload() method: upload file folder location created.");
			else
				logger.info("In XMLBuilder class fileUpload() method: upload file folder location already exist.");
			
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		
			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("root");
			doc.appendChild(rootElement);
			
			
			Element schoolname = doc.createElement("schoolname");
			schoolname.appendChild(doc.createTextNode((reportData.getSchoolDetails().getSchoolDetailsName() != null ? reportData.getSchoolDetails().getSchoolDetailsName() : "" )));
			rootElement.appendChild(schoolname);
			
			Element report = doc.createElement("report");
			report.appendChild(doc.createTextNode("MARKS STATEMENT : "));
			rootElement.appendChild(report);
			
			String examname = reportData.getSchoolDetails().getExamName();				
			String examName = "";
			
			if(examname.equalsIgnoreCase("M1")){					
				examName = "Monthly-1";
			}else if(examname.equalsIgnoreCase("M2")){					
				examName = "Monthly-2";
			}else if(examname.equalsIgnoreCase("PC")){					
				examName = "Pre Centralised";
			}else{					
				examName = "Term-1";
			}
			
			
			Element exam = doc.createElement("exam");
			report.appendChild(doc.createTextNode(reportData.getAcademicYear().getAcademicYearName() +" ("+(examName)+")"));
			rootElement.appendChild(report);
		
			Element standard = doc.createElement("standard");
			standard.appendChild(doc.createTextNode((reportData.getStudent().getStandard() !=null?reportData.getStudent().getStandard():"------")));
			rootElement.appendChild(standard);
		
			Element section = doc.createElement("section");
			section.appendChild(doc.createTextNode((reportData.getStudent().getSection()!=null?reportData.getStudent().getSection():"--------")));
			rootElement.appendChild(section);
		
			for(SubjectGroup subjectGroup : reportData.getSubjectGroupList()){
				Element subjectGroupList = doc.createElement("subjectgroup");
				rootElement.appendChild(subjectGroupList);
				
				Element subjectgroupname = doc.createElement("subjectgroupname");
				subjectgroupname.appendChild(doc.createTextNode((subjectGroup.getSubjectGroupName()!=null?subjectGroup.getSubjectGroupName():"----")));
				subjectGroupList.appendChild(subjectgroupname);
			}
			for(Student student : reportData.getStudentList()){
					
				Element studentList = doc.createElement("student");
				rootElement.appendChild(studentList);
		
				
				Element roll = doc.createElement("roll");
				roll.appendChild(doc.createTextNode((student.getRollNumber().toString()!=null?student.getRollNumber().toString():"---------")));
				studentList.appendChild(roll);
				
				// nickname elements
				Element studentname = doc.createElement("name");
				studentname.appendChild(doc.createTextNode((student.getStudentName()!=null?student.getStudentName():"---------------")));
				studentList.appendChild(studentname);
		

				for(StudentResult studentResult : student.getStudentResultList()){
					Element subject = doc.createElement("subject");
					studentList.appendChild(subject);
					
					Element subjectname = doc.createElement("subjectname");
					subjectname.appendChild(doc.createTextNode((studentResult.getSubject()!=null?studentResult.getSubject():"-")));
					subject.appendChild(subjectname);
					
					Element fa1 = doc.createElement("marks");
					fa1.appendChild(doc.createTextNode((studentResult.getTotalObtainedChar()!=null?studentResult.getTotalObtainedChar():"-")));
					subject.appendChild(fa1);
					
				}
				
				Element total = doc.createElement("total");
				total.appendChild(doc.createTextNode((student.getStudentTotal()!=null?student.getStudentTotal().toString():"-")));
				studentList.appendChild(total);
				
				Element percent = doc.createElement("percent");
//				all_total.appendChild(doc.createTextNode(new Double(new DecimalFormat("##.0").format(total)).toString()));
				percent.appendChild(doc.createTextNode((student.getStudentPercent()!=null?((new DecimalFormat("##.0").format(student.getStudentPercent())).toString()):"-")));
				studentList.appendChild(percent);
				
				Element rank = doc.createElement("rank");
				rank.appendChild(doc.createTextNode((student.getStudentPassFail()!=null?student.getStudentPassFail():"-")));
				studentList.appendChild(rank);
				
			}
			// write the content into xml file
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);				
			StreamResult result = new StreamResult(new File(reportData.getXmlFilePath()+reportData.getXmlFileName()));
			transformer.transform(source, result);
			
		  } catch (ParserConfigurationException pce) {
			  logger.error(pce);
		  } catch (TransformerException tfe) {
			  logger.error(tfe);
		  } catch (Exception e) {
			  logger.error(e);
		  }

		
	}	


	public void createXMLFileForConsolidateCentraliseExamForXI_XIINew(Report reportData) {

		try{
			File outDir = new File(reportData.getXmlFilePath()); 
			boolean isDirCreated = outDir.mkdirs();
			if (isDirCreated)
				logger.info("In XMLBuilder class fileUpload() method: upload file folder location created.");
			else
				logger.info("In XMLBuilder class fileUpload() method: upload file folder location already exist.");
			
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		
			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("root");
			doc.appendChild(rootElement);
			
			
			Element schoolname = doc.createElement("schoolname");
			schoolname.appendChild(doc.createTextNode((reportData.getSchoolDetails().getSchoolDetailsName() != null ? reportData.getSchoolDetails().getSchoolDetailsName() : "" )));
			rootElement.appendChild(schoolname);
			
			Element report = doc.createElement("report");
			report.appendChild(doc.createTextNode("MARKS STATEMENT : "));
			rootElement.appendChild(report);
			
			/*String examname = reportData.getSchoolDetails().getExamName();				
			String examName = "";
			
			if(examname.equalsIgnoreCase("M1")){					
				examName = "Monthly-1";
			}else if(examname.equalsIgnoreCase("M2")){					
				examName = "Monthly-2";
			}else if(examname.equalsIgnoreCase("PC")){					
				examName = "Pre Centralised";
			}else{					
				examName = "Term-1";
			}*/
			
			
			Element exam = doc.createElement("exam");
			report.appendChild(doc.createTextNode(reportData.getAcademicYear().getAcademicYearName() +" ("+( reportData.getSchoolDetails().getExamName())+")"));
			rootElement.appendChild(report);
		
			Element standard = doc.createElement("standard");
			standard.appendChild(doc.createTextNode((reportData.getStudent().getStandard() !=null?reportData.getStudent().getStandard():"------")));
			rootElement.appendChild(standard);
		
			Element section = doc.createElement("section");
			section.appendChild(doc.createTextNode((reportData.getStudent().getSection()!=null?reportData.getStudent().getSection():"--------")));
			rootElement.appendChild(section);
		
			for(SubjectGroup subjectGroup : reportData.getSubjectGroupList()){
				Element subjectGroupList = doc.createElement("subjectgroup");
				rootElement.appendChild(subjectGroupList);
				if(subjectGroup.getSubjectGroupName().equalsIgnoreCase("BENGALI")||subjectGroup.getSubjectGroupName().equalsIgnoreCase("ENGLISH")||subjectGroup.getSubjectGroupName().equalsIgnoreCase("MATHEMATICS")||subjectGroup.getSubjectGroupName().equalsIgnoreCase("HINDI")){
					Element id = doc.createElement("id");
					id.appendChild(doc.createTextNode((2+"")));
					subjectGroupList.appendChild(id);
				}else{
					Element id = doc.createElement("id");
					id.appendChild(doc.createTextNode((3+"")));
					subjectGroupList.appendChild(id);
				}
				Element subjectgroupname = doc.createElement("subjectgroupname");
				subjectgroupname.appendChild(doc.createTextNode((subjectGroup.getSubjectGroupName()!=null?subjectGroup.getSubjectGroupName():"----")));
				subjectGroupList.appendChild(subjectgroupname);
			}
			for(Student student : reportData.getStudentList()){
					
				Element studentList = doc.createElement("student");
				rootElement.appendChild(studentList);
		
				
				Element roll = doc.createElement("roll");
				roll.appendChild(doc.createTextNode((student.getRollNumber().toString()!=null?student.getRollNumber().toString():"---------")));
				studentList.appendChild(roll);
				
				// nickname elements
				Element studentname = doc.createElement("name");
				studentname.appendChild(doc.createTextNode((student.getStudentName()!=null?student.getStudentName():"---------------")));
				studentList.appendChild(studentname);
		
				Element exammarks;
				for(StudentResult studentResult : student.getStudentResultList()){
					if(null == studentResult.getPractical() || studentResult.getPractical() == 0){
						//String theoryMarks = null;
																
						
						
						exammarks = doc.createElement("exammarks");										
						Element theoryTotal = doc.createElement("marks");
						theoryTotal.appendChild(doc.createTextNode( (studentResult.getTheoryObtainedChar()!=null ?studentResult.getTheoryObtainedChar():"")));
						exammarks.appendChild(theoryTotal);
						studentList.appendChild(exammarks);
						
						exammarks = doc.createElement("exammarks");										
						Element total = doc.createElement("marks");
						total.appendChild(doc.createTextNode( (studentResult.getTotalObtainedChar()!=null ?studentResult.getTotalObtainedChar():"")));
						exammarks.appendChild(total);
						studentList.appendChild(exammarks);
						
						
					}else{										
						
						exammarks = doc.createElement("exammarks");										
						Element theoryTotal = doc.createElement("marks");
						theoryTotal.appendChild(doc.createTextNode( (studentResult.getTheoryObtainedChar()!=null ?studentResult.getTheoryObtainedChar():"")));
						exammarks.appendChild(theoryTotal);
						studentList.appendChild(exammarks);
						
						exammarks = doc.createElement("exammarks");										
						Element practical = doc.createElement("marks");
						practical.appendChild(doc.createTextNode( (studentResult.getPracticalObtainedChar()!=null ?studentResult.getPracticalObtainedChar():"")));
						exammarks.appendChild(practical);
						studentList.appendChild(exammarks);
						
						exammarks = doc.createElement("exammarks");										
						Element total = doc.createElement("marks");
						total.appendChild(doc.createTextNode( (studentResult.getTotalObtainedChar()!=null ?studentResult.getTotalObtainedChar():"")));
						exammarks.appendChild(total);
						studentList.appendChild(exammarks);
						
					}
					
				}
				
				Element total = doc.createElement("total");
				total.appendChild(doc.createTextNode((student.getStudentTotalChar()!=null?student.getStudentTotalChar():"-")));
				studentList.appendChild(total);
				
				Element percent = doc.createElement("percent");
//				all_total.appendChild(doc.createTextNode(new Double(new DecimalFormat("##.0").format(total)).toString()));
				percent.appendChild(doc.createTextNode((student.getStudentPercentChar()!=null?((new DecimalFormat("##.0").format(student.getStudentPercentChar()))):"-")));
				studentList.appendChild(percent);
				
				Element rank = doc.createElement("rank");
				rank.appendChild(doc.createTextNode((student.getStudentPassFail()!=null?student.getStudentPassFail():"-")));
				studentList.appendChild(rank);
				
			}
			// write the content into xml file
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);				
			StreamResult result = new StreamResult(new File(reportData.getXmlFilePath()+reportData.getXmlFileName()));
			transformer.transform(source, result);
			
		  } catch (ParserConfigurationException pce) {
			  logger.error(pce);
		  } catch (TransformerException tfe) {
			  logger.error(tfe);
		  } catch (Exception e) {
			  logger.error(e);
		  }

		
	}


	public void createXMLFileForExamResultXI_XIINew(Report reportData) {


		try{
			File outDir = new File(reportData.getXmlFilePath()); 
			boolean isDirCreated = outDir.mkdirs();
			if (isDirCreated)
				logger.info("In FileUploadDownload class fileUpload() method: upload file folder location created.");
			else
				logger.info("In FileUploadDownload class fileUpload() method: upload file folder location already exist.");
			
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("root");
			doc.appendChild(rootElement);

			for(Student student : reportData.getStudentList()){					
				String exam = reportData.getSchoolDetails().getExamName();					
				String examName = "";
				
				if(exam.equalsIgnoreCase("M1")){						
					examName = "Monthly-1";
				}else if(exam.equalsIgnoreCase("M2")){						
					examName = "Monthly-2";
				}else if (exam.equalsIgnoreCase("PC")){						
					examName = "Pre Centralised";
				}else{						
					examName = "Term-1";
				}
				
				
				Element studentList = doc.createElement("student");
				rootElement.appendChild(studentList);
				
				Element schoolname = doc.createElement("schoolname");
				schoolname.appendChild(doc.createTextNode((reportData.getSchoolDetails().getSchoolDetailsName() != null ? reportData.getSchoolDetails().getSchoolDetailsName() : "" )));
				studentList.appendChild(schoolname);
				
				Element report = doc.createElement("report");
				report.appendChild(doc.createTextNode("MARKS STATEMENT : (" +examName+" : "+reportData.getAcademicYear().getAcademicYearName()+ ")") );
				studentList.appendChild(report);
				
				// nickname elements
				Element studentname = doc.createElement("studentname");
				studentname.appendChild(doc.createTextNode((student.getStudentName()!=null?student.getStudentName():"---------------")));
				studentList.appendChild(studentname);

				Element roll = doc.createElement("roll");
				roll.appendChild(doc.createTextNode((student.getRollNumber().toString()!=null?student.getRollNumber().toString():"---------")));
				studentList.appendChild(roll);
				
				Element house = doc.createElement("house");
				house.appendChild(doc.createTextNode((student.getHouse()!=null?student.getHouse():"------")));
				studentList.appendChild(house);

				Element standard = doc.createElement("standard");
				standard.appendChild(doc.createTextNode((student.getStandard()!=null?student.getStandard():"------")));
				studentList.appendChild(standard);

				Element section = doc.createElement("section");
				section.appendChild(doc.createTextNode((student.getSection()!=null?student.getSection():"--------")));
				studentList.appendChild(section);				
				
				
				for(StudentResult studentResult : student.getStudentResultList()){
					Element subject = doc.createElement("subject");
					studentList.appendChild(subject);
					
					Element subjectname = doc.createElement("subjectname");
					subjectname.appendChild(doc.createTextNode((studentResult.getSubject()!=null?studentResult.getSubject():"")));
					subject.appendChild(subjectname);
					
					Element weightage = doc.createElement("maxmarks");
					weightage.appendChild(doc.createTextNode( (studentResult.getTotal()!=null ?studentResult.getTotal().toString():"")));
					subject.appendChild(weightage);
					
					String nodeValue = "";
					Element weightageObtained = doc.createElement("marksobtained");				
					/*if(studentResult.getTotalObtained() == null){
						nodeValue = "";
					}else if(studentResult.getTotalObtained() < 0){
						nodeValue = "AB";
					}else{
						nodeValue = String.valueOf(studentResult.getTotalObtained());
					}		*/			
					weightageObtained.appendChild(doc.createTextNode((studentResult.getTotalObtainedChar()!=null ?studentResult.getTotalObtainedChar():"")));
					subject.appendChild(weightageObtained);					
				}
				Element total = doc.createElement("total");
				total.appendChild(doc.createTextNode((student.getStudentTotal()!=null?student.getStudentTotal().toString():"----")));
				studentList.appendChild(total);
				
				Element position = doc.createElement("position");
				position.appendChild(doc.createTextNode((student.getStudentPassFail()!=null?student.getStudentPassFail():"----")));
				studentList.appendChild(position);
			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);	
			StreamResult result = new StreamResult(new File(reportData.getXmlFilePath()+reportData.getXmlFileName()));
			transformer.transform(source, result);
			
			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

		} catch (ParserConfigurationException pce) {
			logger.error("",pce);
		} catch (TransformerException tfe) {
			logger.error(tfe);
		}

		
	}


		public void createXMLFileForCentraliseExamForXI_XIINew(Report report) {
				try
				{
					File outDir = new File(report.getXmlFilePath()); 
					boolean isDirCreated = outDir.mkdirs();
					DecimalFormat dformat = new DecimalFormat("##.##");
					if (isDirCreated)
						logger.info("In createXMLFileForCentraliseExamForXI_XIINew() method: upload file folder location created.");
					else
						logger.info("In createXMLFileForCentraliseExamForXI_XIINew() method: upload file folder location already exist.");
					DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
					
					Document doc = docBuilder.newDocument();
					Element rootElement = doc.createElement("root");
					doc.appendChild(rootElement);
					
					
				
					for(Student student : report.getStudentList()){
						
						Element studentList = doc.createElement("student");
						rootElement.appendChild(studentList);
						
						Element schoolname = doc.createElement("schoolname");
						schoolname.appendChild(doc.createTextNode((report.getSchoolDetails().getSchoolDetailsName() != null ? report.getSchoolDetails().getSchoolDetailsName() : "" )));
						studentList.appendChild(schoolname);
						
						Element academicyear = doc.createElement("academicyear");
						academicyear.appendChild(doc.createTextNode(( report.getAcademicYear().getAcademicYearName())));
						studentList.appendChild(academicyear);
						
						// nickname elements
						Element studentname = doc.createElement("studentname");
						studentname.appendChild(doc.createTextNode((student.getStudentName()!=null?student.getStudentName():"---------------")));
						studentList.appendChild(studentname);
			
						Element roll = doc.createElement("roll");
						roll.appendChild(doc.createTextNode((student.getRollNumber().toString()!=null?student.getRollNumber().toString():"---------")));
						studentList.appendChild(roll);
						
						Element house = doc.createElement("house");
						house.appendChild(doc.createTextNode((student.getHouse()!=null?student.getHouse():"------")));
						studentList.appendChild(house);
			
						Element standard = doc.createElement("standard");
						standard.appendChild(doc.createTextNode((student.getStandard()!=null?student.getStandard():"------")));
						studentList.appendChild(standard);
			
						Element section = doc.createElement("section");
						section.appendChild(doc.createTextNode((student.getSection()!=null?student.getSection():"--------")));
						studentList.appendChild(section);
						
						String totalCount = ""+report.getReportId();
						
						Element strength = doc.createElement("strength");
						strength.appendChild(doc.createTextNode((totalCount!=null?totalCount:"--------")));
						studentList.appendChild(strength);
					
					
						for(StudentResult studentResult : student.getStudentResultList()){
							Element subject = doc.createElement("subject");
							studentList.appendChild(subject);
							
							Element subjectname = doc.createElement("subjectname");
							subjectname.appendChild(doc.createTextNode((studentResult.getSubject()!=null?studentResult.getSubject():"")));
							subject.appendChild(subjectname);
							
							Element examname = doc.createElement("examname");
							examname.appendChild(doc.createTextNode("Centralise"));
							subject.appendChild(examname);
							
							Element theorymarks = doc.createElement("theorymarks");
							theorymarks.appendChild(doc.createTextNode( (studentResult.getTheoryObtainedChar()!=null ?studentResult.getTheoryObtainedChar():"")));
							subject.appendChild(theorymarks);
							
							Element practicalObtained=doc.createElement("practicalmarks");
							if(studentResult.getSubject().equalsIgnoreCase("MATHEMATICS")||studentResult.getSubject().equalsIgnoreCase("ENGLISH")||studentResult.getSubject().equalsIgnoreCase("BENGALI"))
							{
								
								//practicalObtained.appendChild(doc.createTextNode( (studentResult.getPracticalObtained()!=null ?studentResult.getPracticalObtained().toString():"")));
								subject.appendChild(practicalObtained);
								
							}else{
								practicalObtained.appendChild(doc.createTextNode((studentResult.getPracticalObtainedChar()!=null ?studentResult.getPracticalObtainedChar():"")));
								subject.appendChild(practicalObtained);
							}
							
						
							Element total = doc.createElement("total");								
							total.appendChild((doc.createTextNode((studentResult.getTotalObtainedChar()!=null ?studentResult.getTotalObtainedChar():""))));
							subject.appendChild(total);					
						}
						
						Element grandTotal = doc.createElement("grandTotal");								
						grandTotal.appendChild((doc.createTextNode((student.getStudentTotalChar()!=null ?student.getStudentTotalChar():""))));
						studentList.appendChild(grandTotal);	
						
						Element percentage = doc.createElement("percentage");								
						percentage.appendChild((doc.createTextNode((student.getStudentPercentChar()!=null ?student.getStudentPercentChar():""))));
						studentList.appendChild(percentage);	
						
						Element position = doc.createElement("position");
						position.appendChild(doc.createTextNode((student.getStudentPassFail()!=null?student.getStudentPassFail():"----")));
						studentList.appendChild(position);
			
					}				
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					DOMSource source = new DOMSource(doc);				
					StreamResult result = new StreamResult(new File(report.getXmlFilePath()+report.getXmlFileName()));
					transformer.transform(source, result);			
				}catch(Exception e) {
					e.printStackTrace();
					logger.error(e);
				}
				
		}
	
	/**New CBSE System end**/
}
	