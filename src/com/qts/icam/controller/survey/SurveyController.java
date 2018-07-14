package com.qts.icam.controller.survey;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.qts.icam.model.admission.AdmissionForm;
import com.qts.icam.model.common.Course;
import com.qts.icam.model.common.RepositoryStructure;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.SessionObject;
import com.qts.icam.model.common.Student;
import com.qts.icam.model.survey.Answer;
import com.qts.icam.model.survey.AnswerMaster;
import com.qts.icam.model.survey.Question;
import com.qts.icam.model.survey.QuestionAnswerMaster;
import com.qts.icam.model.survey.QuestionMaster;
import com.qts.icam.service.academics.AcademicsService;
import com.qts.icam.service.administrator.AdministratorService;
import com.qts.icam.service.common.CommonService;
import com.qts.icam.service.survey.SurveyService;





@Controller
@SessionAttributes("sessionObject")
public class SurveyController {
	
	@Autowired
	AdministratorService administratorService;
	
	@Autowired
	SurveyService surveyService;
	
	@Autowired
	AcademicsService academicsService;
	
	@Autowired
	CommonService commonService;
	
	@Value("${survey.path}")
	private String surveyPath;
	
	public static Logger logger = Logger.getLogger(SurveyController.class);
	
/*	@RequestMapping(value = "/configureSurvey", method = RequestMethod.GET)
	public ModelAndView configureSurvey(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		
		QuestionAnswerMaster questionAnswerMaster = new QuestionAnswerMaster();
		try {
			RepositoryStructure repository = administratorService.getRespositoryStructure();
			String repositoryName = repository.getRepositoryPathName();
			String path = repositoryName+"/" + surveyPath+"/"+"S_1.xml";
			System.out.println("path====="+path);
			logger.info("In configureSurvey() method of SurveyController");
			File xmlFile = new File(path);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);

			doc.getDocumentElement().normalize();
			
			QuestionMaster questionMaster = new QuestionMaster();
			AnswerMaster answerMaster = new AnswerMaster();

			//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			//System.out.println("----------------------------");
			
			NodeList surveyName = doc.getElementsByTagName("surveyname");
			NodeList surveyId = doc.getElementsByTagName("surveyid");
			
			//System.out.println(surveyName.item(0).getTextContent() + "  ----------------------------  " + surveyId.item(0).getTextContent());
			
			NodeList questionLst = doc.getElementsByTagName("question");
			
			List<Question> questionList = new ArrayList<Question>();
			List<Answer> answerList = new ArrayList<Answer>();
			
			for (int questioncounter = 0; questioncounter < questionLst.getLength(); questioncounter++) {

				Node questionNode = questionLst.item(questioncounter);

				//System.out.println("\nCurrent Element :" + questionNode.getNodeName());
				
				if (questionNode.getNodeType() == Node.ELEMENT_NODE) {
					
					Element questionElement = (Element) questionNode;
					
					if(null == questionElement.getElementsByTagName("choices").item(0)){
						
						Question question = new Question();
						Answer answer = new Answer();
						
						question.setSurveyId(surveyId.item(0).getTextContent());
						answer.setSurveyId(surveyId.item(0).getTextContent());
						question.setSurveyName(surveyName.item(0).getTextContent());
						
						question.setQuestionId("Q" + questionElement.getElementsByTagName("qid").item(0).getTextContent());
						answer.setQuestionId("Q" + questionElement.getElementsByTagName("qid").item(0).getTextContent());
						answer.setAnswerId("A" + questionElement.getElementsByTagName("qid").item(0).getTextContent());
						question.setQuestion(questionElement.getElementsByTagName("questiondesc").item(0).getTextContent());
						question.setMustAnswer(questionElement.getElementsByTagName("required").item(0).getTextContent());
						answer.setWeightage(questionElement.getElementsByTagName("weightage").item(0).getTextContent());
						answer.setInputType(questionElement.getElementsByTagName("inputtype").item(0).getTextContent());
						answer.setInitialValue("");
						
						System.out.println("User Id ===="+sessionObject.getUserId());
						question.setUpdatedBy(sessionObject.getUserId()); //Added By Naimisha 25042017
						answer.setUpdatedBy(sessionObject.getUserId());
						
						questionList.add(question);
						answerList.add(answer);
						
					}else{
						
						NodeList choicesList = questionElement.getElementsByTagName("choices");
						
						for (int choicescounter = 0; choicescounter < choicesList.getLength(); choicescounter++) {
							
							Node choicesNode = choicesList.item(choicescounter);
							Element choicesElement = (Element) choicesNode;
							
							NodeList choiceList = choicesElement.getElementsByTagName("choice");
							System.out.println("choiceList" + choiceList.getLength());
							for (int choicecounter = 0; choicecounter < choiceList.getLength(); choicecounter++) {
								
								Node choiceNode = choiceList.item(choicecounter);
								Element choiceElement = (Element) choiceNode;
								
								if(choicecounter ==0){
									
									Question question = new Question();
									Answer answer = new Answer();
									
									question.setSurveyId(surveyId.item(0).getTextContent());
									answer.setSurveyId(surveyId.item(0).getTextContent());
									question.setSurveyName(surveyName.item(0).getTextContent());
									
									question.setQuestionId("Q" + questionElement.getElementsByTagName("qid").item(0).getTextContent());
									answer.setQuestionId("Q" + questionElement.getElementsByTagName("qid").item(0).getTextContent());
									answer.setAnswerId("A" + questionElement.getElementsByTagName("qid").item(0).getTextContent());
									question.setQuestion(questionElement.getElementsByTagName("questiondesc").item(0).getTextContent());
									question.setMustAnswer(questionElement.getElementsByTagName("required").item(0).getTextContent());
									answer.setWeightage(choiceElement.getElementsByTagName("weightage").item(0).getTextContent());
									answer.setInputType(questionElement.getElementsByTagName("inputtype").item(0).getTextContent());
									answer.setInitialValue(choiceElement.getElementsByTagName("choicedesc").item(0).getTextContent());
									
									System.out.println("User Id ===="+sessionObject.getUserId());
									question.setUpdatedBy(sessionObject.getUserId()); //Added By Naimisha 25042017
									
									questionList.add(question);
									answerList.add(answer);
									
								}else{
									
									Answer answer = new Answer();
									
									answer.setSurveyId(surveyId.item(0).getTextContent());
									answer.setQuestionId("Q" + questionElement.getElementsByTagName("qid").item(0).getTextContent());
									answer.setAnswerId("A" + questionElement.getElementsByTagName("qid").item(0).getTextContent());
									answer.setWeightage(choiceElement.getElementsByTagName("weightage").item(0).getTextContent());
									answer.setInputType(questionElement.getElementsByTagName("inputtype").item(0).getTextContent());
									answer.setInitialValue(choiceElement.getElementsByTagName("choicedesc").item(0).getTextContent());
									
									answer.setUpdatedBy(sessionObject.getUserId()); //Added By Naimisha 25042017
									
									answerList.add(answer);
									
								}
							}
									
						}
						
					}
				}
				
			}
			
			questionMaster.setQuestionList(questionList);
			answerMaster.setAnswerList(answerList);
			
			questionAnswerMaster.setQuestionMaster(questionMaster);
			questionAnswerMaster.setAnswerMaster(answerMaster);
			
			questionAnswerMaster.setUpdatedBy(sessionObject.getUserId());
			String status = surveyService.insertQuestionAnswerMaster(questionAnswerMaster);
			System.out.println("Status======"+status);
			model.addAttribute("status", status);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("configureSurvey() In SurveyController.java: Exception"+ e);
		}
		return new ModelAndView("survey/configurationSurvey");
	}*/

	@RequestMapping(value = "/takeSurvey", method = RequestMethod.GET)
	public ModelAndView takeSurvey(HttpServletRequest request,
			HttpServletResponse response, 
			@ModelAttribute("sessionObject") SessionObject sessionObject,
			@ModelAttribute("surveyId") String surveyId) {
		
		ModelAndView model = null;
		ModelMap modelObj = new ModelMap();
		try {
			logger.info("In takeSurvey() method of SurveyController");
			
			RepositoryStructure repository = administratorService.getRespositoryStructure();
			String repositoryName = repository.getRepositoryPathName();
			if(!(surveyId.equalsIgnoreCase("NA"))){
				String path = repositoryName+"/" + surveyPath+surveyId+".xml";
				System.out.println("path====="+path);
				logger.info("In configureSurvey() method of SurveyController");
				//File xmlFile = new File(path);
				Source source = new StreamSource(new File(path));
				 model = new ModelAndView("SurveyForm");
				
		        
		 
		        // adds the XML source file to the model so the XsltView can detect
		        
		        model.addObject("xmlSource", source);
		       
			}
			
	         
	        
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("configureSurvey() In SurveyController.java: Exception"+ e);
		}
		//return new ModelAndView("survey/takeSurvey");
		if(surveyId.equalsIgnoreCase("NA")){
			String msg = "No Survey Available For This Program.";
			System.out.println("msg===="+msg);
			//model.addAttribute("msg",msg );
			return new ModelAndView("survey/takeSurvey");
		}else{
			return model;
		}
		
	}
	
	@RequestMapping(value = "/giveSurvey", method = RequestMethod.GET)
	public ModelAndView giveSurvey(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject,
			@ModelAttribute("courseCode") String courseCode) {
		
		//ModelAndView model = null;
	//	ModelMap modelObj = new ModelMap();
		try {
			logger.info("In giveSurvey() method of SurveyController");
			
			List<Question> surveyList = surveyService.fetchSurveyForAProgramme(courseCode,sessionObject.getUserId());
			model.addAttribute("surveyList", surveyList);
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("giveSurvey() In SurveyController.java: Exception"+ e);
		}
		return new ModelAndView("survey/takeSurvey");
		
		
	}
	@RequestMapping(value = "/submitSurvey", method = RequestMethod.GET)
	public String submitSurvey(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject,
			@RequestParam("questionsANDAnswers") String questionAnswer) {
		
		 
		try {
			logger.info("In takeSurvey() method of SurveyController");
			//System.out.println("within Controller");
			Question question = new Question();
			//System.out.println("questionAnswer==="+questionAnswer);
			Student student = new Student();
			student.setUserId(sessionObject.getUserId());
			student.setCourseId(sessionObject.getCourseCode());
			//System.out.println("Course===="+sessionObject.getCourseCode());
			Student studentObj = surveyService.getStudentInfoAgainstUserId(student);
			List<Answer>answerList = new ArrayList<Answer>();
			String[] questionAnswerArray =  questionAnswer.split("\\;");
			question.setSurveyId(questionAnswerArray[0]);
			for(int i=1; i<questionAnswerArray.length;i++){
				
				String[] questionAndAnswer = questionAnswerArray[i].split("\\*");
				String answerString = questionAndAnswer[1];
				if(answerString.contains("@")){
					String[] answerArr = answerString.split("\\@"); 
					for(int j= 1;j<answerArr.length;j++){
						Answer answer = new Answer();
						answer.setQuestionId(questionAndAnswer[0]);
						answer.setAnswerId(answerArr[j]);
						answer.setUpdatedBy(sessionObject.getUserId());
						answer.setRollNumber(studentObj.getRoll());
						answer.setName(studentObj.getStudentName());
						answerList.add(answer);
					}
					
				}else{
					Answer answer = new Answer();
					answer.setQuestionId(questionAndAnswer[0]);
					answer.setAnswerId(questionAndAnswer[1]);
					answer.setUpdatedBy(sessionObject.getUserId());
					answer.setRollNumber(studentObj.getRoll());
					answer.setName(studentObj.getStudentName());
					answerList.add(answer);
				}
				
			}
			
			question.setAnswerList(answerList);
			
			String status = surveyService.insertFeedbackResult(question);
		//	System.out.println("status======"+status);
			
	        
	        model.addAttribute("status", status);
	        String msg = null;
	        if(status.equalsIgnoreCase("success")){
	        	msg = "Survey SuccessFully Submitted";
	        }else{
	        	msg = "Failed To Submit Survey";
	        }
	        model.addAttribute("msg", msg);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("configureSurvey() In SurveyController.java: Exception"+ e);
		}
		//return new ModelAndView("survey/takeSurvey");
		return "survey/takeSurvey";
	}
	
	/******Naimisha 25052017*****/
	
	@RequestMapping(value = "/configureQuestionAnswer", method = RequestMethod.GET)
	public ModelAndView configureQuestionAnswer(HttpServletRequest request,
			HttpServletResponse response, 
			@ModelAttribute("sessionObject") SessionObject sessionObject,ModelMap model) {
		
		String strView = null;
		try {
			logger.info("In SurveyController configureQuestionAnswer() method");
			List<Question> questionList = surveyService.getQuestionListMappedWithAnswer();
			model.addAttribute("questionList", questionList);
			
			//if(resourceTypeList != null){
			//	model.addAttribute("resourceTypeList", resourceTypeList);
				strView = "survey/mapAnswerWithQuestion";
			//}
		} catch (NullPointerException e) {
			logger.error("Error in SurveyController configureQuestionAnswer() method for NullPointerException: ",e);
		} catch (Exception e) {
			logger.error("Error in SurveyController configureQuestionAnswer() method for Exception: ",e);
		}
		return new ModelAndView(strView);
	}
	
	/**
	 * @author naimisha 
	 * changes 25052017
	 * **/
	
	@RequestMapping(value = "/addQuestionAnswer", method = RequestMethod.POST)
	public ModelAndView addQuestionAnswer(HttpServletRequest request,
			HttpServletResponse response, 
			@ModelAttribute("sessionObject") SessionObject sessionObject,ModelMap model,
			Answer answer,
			@RequestParam(value = "answer" , required = false) String[] answerValue,
			@RequestParam(value = "weightage" , required = false) String[] weightage) {
		
		
		List<Answer>answerList = new ArrayList<Answer>();
		try {
			logger.info("In SurveyController addQuestionAnswer() method");
			Question question = new Question();
			question.setQuestion(answer.getQuestionId());
			Question questionObj = surveyService.checkQuestionStatus(question);
			String status = null;
			String msg = null;
			if(null == questionObj){
					if(null != answerValue & answerValue.length !=0){
						for (int count = 0; count < answerValue.length;count++) {
							if (answerValue[count] != null && weightage[count] != null) {
								Answer answerObj = new Answer();
								answerObj.setAnswerId(answerValue[count]);
								answerObj.setWeightage(weightage[count]);
								answerObj.setUpdatedBy(sessionObject.getUserId());
								answerObj.setQuestionId(answer.getQuestionId());
								answerObj.setInputType(answer.getInputType());
								//answerObj.setName(answer.getQuestionId());
								answerList.add(answerObj);
							}
						}
					}else{
						answer.setUpdatedBy(sessionObject.getUserId());
						answerList.add(answer);
					}
				
					status = surveyService.addQuestionAnswer(answerList);
				
					
					msg = null;
					if(status.equalsIgnoreCase("success")){
						msg = "Question Answer Configured SuccessFully";
					}else{
						msg = "Failed To Configure Question And Answer";
					}
					
				}else{
					status = "duplicate";
					msg = "Question Already Exists";
				}
			model.addAttribute("insertUpdateStatus", status);
			model.addAttribute("msg", msg);
			
			} catch (NullPointerException e) {
			logger.error("Error in SurveyController addQuestionAnswer() method for NullPointerException: ",e);
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in SurveyController addQuestionAnswer() method for Exception: ",e);
		}
		return configureQuestionAnswer( request, response,  sessionObject, model);
	}
	
	
	
	
	@RequestMapping(value = "/configureSurveyQuestion", method = RequestMethod.GET)
	public ModelAndView configureSurveyQuestion(HttpServletRequest request,
			HttpServletResponse response, 
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		
		String strView = null;
		try {
			logger.info("In BackOfficeController programPolicy() method: calling programPolicy() in BackOfficeService.java");
			
				strView = "survey/configureQuestion";
			//}
		} catch (NullPointerException e) {
			logger.error("Error in BackOfficeController programPolicy() method for NullPointerException: ",e);
		} catch (Exception e) {
			logger.error("Error in BackOfficeController programPolicy() method for Exception: ",e);
		}
		return new ModelAndView(strView);
	}
	
	@RequestMapping(value = "/addConfigureSurveyQuestion", method = RequestMethod.POST)
	public ModelAndView addConfigureSurveyQuestion(HttpServletRequest request,
			HttpServletResponse response, 
			@ModelAttribute("sessionObject") SessionObject sessionObject,
			Question question,ModelMap model) {
		
		
		try {
			logger.info("In BackOfficeController programPolicy() method: calling programPolicy() in BackOfficeService.java");
			//List<ResourceType> resourceTypeList = commonService.getAllResourceType();
			question.setUpdatedBy(sessionObject.getUserId());
			question.setQuestion(question.getQuestion().trim());
			Question questionObj = surveyService.checkQuestionStatus(question);
			String status = null;
			String msg = null;
			if(null == questionObj){
				status = surveyService.addConfigureSurveyQuestion(question);
				if(status.equalsIgnoreCase("success")){
					msg = "Question Configured SuccessFully";
				}else{
					msg = "Failed To Configure Question";
				}
				
			}else{
				status = "duplicate";
				msg = "Question Already Exists";
			}
			 
			
			model.addAttribute("insertUpdateStatus", status);
			model.addAttribute("msg", msg);
			
		} catch (NullPointerException e) {
			logger.error("Error in BackOfficeController programPolicy() method for NullPointerException: ",e);
		} catch (Exception e) {
			logger.error("Error in BackOfficeController programPolicy() method for Exception: ",e);
		}
		return configureSurveyQuestion( request,response, sessionObject);
	}
	
	@RequestMapping(value = "/configureSurveyQuestionAnswer", method = RequestMethod.GET)
	public ModelAndView configureSurveyQuestionAnswer(HttpServletRequest request,
			HttpServletResponse response, 
			@ModelAttribute("sessionObject") SessionObject sessionObject,ModelMap model) {
		
		String strView = null;
		try {
			logger.info("In SurveyController configureQuestionAnswer() method");
			List<Question> questionList = surveyService.getQuestionListMappedWithAnswer();
			model.addAttribute("questionList", questionList);
			List<AdmissionForm>courseList = academicsService.getAllCourseList();
			model.addAttribute("courseList", courseList);
			//if(resourceTypeList != null){
			//	model.addAttribute("resourceTypeList", resourceTypeList);
				strView = "survey/mapSurveyWithQuestionAnswer";
			//}
		} catch (NullPointerException e) {
			logger.error("Error in SurveyController configureQuestionAnswer() method for NullPointerException: ",e);
		} catch (Exception e) {
			logger.error("Error in SurveyController configureQuestionAnswer() method for Exception: ",e);
		}
		return new ModelAndView(strView);
	}
	
	@RequestMapping(value = "/addSurveyQuestionAnswer", method = RequestMethod.POST)
	public ModelAndView addSurveyQuestionAnswer(HttpServletRequest request,
			HttpServletResponse response, Question question,
			@ModelAttribute("sessionObject") SessionObject sessionObject,ModelMap model,
			@RequestParam(value = "questionId") String[] questionId) {
		
		String msg = null;
		String status = null;
		List<Question>questionList = new ArrayList<Question>();
		try {
			logger.info("In SurveyController addSurveyQuestionAnswer() method");
			question.setSurveyName(question.getSurveyName().trim());
			Question questionObject = surveyService.checkSurveyConfigurationStatus(question);
			if(null == questionObject){
				if(null != questionId & questionId.length !=0){
					for (int count = 0; count < questionId.length;count++) {
						if (questionId[count] != null ) {
							Question questionObj = new Question();
							questionObj.setQuestionId(questionId[count]);
							//String mustAnswer = request.getParameter("mustAnswer"+count);
							//questionObj.setMustAnswer(mustAnswer);
							System.out.println("question===="+questionId[count]);
							//System.out.println("answer==="+mustAnswer);
							questionObj.setSurveyName(question.getSurveyName());
							questionObj.setUpdatedBy(sessionObject.getUserId());
							questionObj.setCourse(question.getCourse());
							questionList.add(questionObj);
						}
					}
				}
				status = surveyService.addSurveyQuestionAnswer(questionList);
				List<QuestionMaster>questionAnswerList = surveyService.fetchQuestionAnswerForXMLWrite();
				
				QuestionMaster questionMaster = questionAnswerList.get(0);
				
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

				// root elements
				Document doc = docBuilder.newDocument();
				Element rootElement = doc.createElement("survey");
				doc.appendChild(rootElement);
				
				Element surveyTypesElement = doc.createElement("surveytypes");
				rootElement.appendChild(surveyTypesElement);
				
				Element surveyTypeElement = doc.createElement("surveytype");
				surveyTypesElement.appendChild(surveyTypeElement);
				
				Element surveyNameElement = doc.createElement("surveyname");
				surveyNameElement.appendChild(doc.createTextNode(questionMaster.getSurveyName()));
				surveyTypeElement.appendChild(surveyNameElement);
				
				Element surveyIdElement = doc.createElement("surveyid");
				surveyIdElement.appendChild(doc.createTextNode(questionMaster.getSurveyId()));
				surveyTypeElement.appendChild(surveyIdElement);
				
				Element questionsElement = doc.createElement("questions");
				surveyTypeElement.appendChild(questionsElement);
				
				List<Question> questionLst = questionMaster.getQuestionList();
				int count = 1;
				for(Question questn : questionLst){
					
					List<Answer> answerList = questn.getAnswerList();
					Answer answer = answerList.get(0);
					int sizeOfAnswerList = answerList.size();
									
					Element questionElement = doc.createElement("question");
					Attr qIdAttr = doc.createAttribute("qid");
					qIdAttr.setValue(questn.getQuestionId());
					questionElement.setAttributeNode(qIdAttr);
					questionsElement.appendChild(questionElement);
					
					Element idElement = doc.createElement("id");
					idElement.appendChild(doc.createTextNode((String.valueOf(count))));
					//questionElement.setAttributeNode(idElement);
					questionElement.appendChild(idElement);
					
					Element qidElement = doc.createElement("qid");
					qidElement.appendChild(doc.createTextNode(questn.getQuestionId()));
					questionElement.appendChild(qidElement);
					
					Element questionDescElement = doc.createElement("questiondesc");
					questionDescElement.appendChild(doc.createTextNode(questn.getQuestion()));
					questionElement.appendChild(questionDescElement);
					
					String inputType = answer.getInputType();
					String typeId = getTypeIdOfInput(inputType);
					
					Element typeIdElement = doc.createElement("typeid");
					typeIdElement.appendChild(doc.createTextNode(typeId));
					questionElement.appendChild(typeIdElement);
					
					Element requiredElement = doc.createElement("required");
					requiredElement.appendChild(doc.createTextNode("Y"));
					questionElement.appendChild(requiredElement);
					
					Element inputTypeElement = doc.createElement("inputtype");
					inputTypeElement.appendChild(doc.createTextNode(inputType));
					questionElement.appendChild(inputTypeElement);
					
					if(sizeOfAnswerList == 1){
						Element weightageElement = doc.createElement("weightage");
						weightageElement.appendChild(doc.createTextNode("0"));
						questionElement.appendChild(weightageElement);
					}else{
						Element choicesElement = doc.createElement("choices");
						questionElement.appendChild(choicesElement);
						
						int counter = 1;
						for(Answer answr : answerList){
							
							System.out.println("Initial Value===="+answr.getInitialValue());
							System.out.println("weitage===="+answr.getWeightage());
							Element choiceElement = doc.createElement("choice");
							Attr cIdAttr = doc.createAttribute("cid");
							cIdAttr.setValue(String.valueOf(counter));
							choiceElement.setAttributeNode(cIdAttr);
							choicesElement.appendChild(choiceElement);
							
							Element cIdElement = doc.createElement("cid");
							cIdElement.appendChild(doc.createTextNode(String.valueOf(counter)));
							choiceElement.appendChild(cIdElement);
							
							Element choiceDescElement = doc.createElement("choicedesc");
							choiceDescElement.appendChild(doc.createTextNode(answr.getInitialValue()));
							choiceElement.appendChild(choiceDescElement);
							
							Element weightageElement = doc.createElement("weightage");
							weightageElement.appendChild(doc.createTextNode(answr.getWeightage()));
							choiceElement.appendChild(weightageElement);
							
							counter++;
						}
						System.out.println("*********************");
						
					}
					
					count++;
					
				}
				
			
				
				RepositoryStructure repository = administratorService.getRespositoryStructure();
				String repositoryName = repository.getRepositoryPathName();
				String serveyId = questionAnswerList.get(0).getSurveyId();
				//String path = repositoryName+"/" + surveyPath+question.getCourse()+"/";
				String path = repositoryName+"/" + surveyPath;
				System.out.println("path===="+path);
				//String xmlFilePath = baseDir+"xml/";
				String xmlFileName = serveyId+".xml";
				String xmlFilePath = path+"/"+xmlFileName;
				//StreamResult result = new StreamResult(new File(path+xmlFileName));
				
				/*TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				transformer.setParameter(OutputKeys.INDENT, "yes");
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(path+xmlFileName));*/
				//transformer.transform(source, result);
				
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				//System.out.println(source+"----------------------------"+doc);

			//	System.out.println("################################################## "+reportData.getXmlFilePath()+reportData.getXmlFileName());
				StreamResult result = new StreamResult(new File(path+xmlFileName));

				// Output to console for testing
				// StreamResult result = new StreamResult(System.out);

				transformer.transform(source, result);
			}else{
				status = "exists";
				msg = "This survey is already Configured";
			}
			

			
			model.addAttribute("insertUpdateStatus", status);
			
			if(status.equalsIgnoreCase("success")){
				msg = "Survey Configured SuccessFully";
			}else if(status.equalsIgnoreCase("fail")){
				msg = "Failed To Configure Survey";
			}
			model.addAttribute("msg", msg);
		} catch (NullPointerException e) {
			logger.error("Error in SurveyController addSurveyQuestionAnswer() method for NullPointerException: ",e);
			e.printStackTrace();
		} catch (ParserConfigurationException pce) {
			logger.error("Error in SurveyController addSurveyQuestionAnswer() method for ParserConfigurationException: ",pce);
			pce.printStackTrace();
		  } catch (TransformerException tfe) {
			  logger.error("Error in SurveyController addSurveyQuestionAnswer() method for TransformerException: ",tfe);
			tfe.printStackTrace();
		  }catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in SurveyController addSurveyQuestionAnswer() method for Exception: ",e);
		}
		return configureSurveyQuestionAnswer( request, response,  sessionObject, model);
	}
	
	private String getTypeIdOfInput(String inputType){
		String strId = "";
		if(inputType.equalsIgnoreCase("Text")){
			strId = "1";
		}else if(inputType.equalsIgnoreCase("TextArea")){
			strId = "3";
		}else if(inputType.equalsIgnoreCase("Radio")){
			strId = "6";
		}else if(inputType.equalsIgnoreCase("Checkbox")){
			strId = "4";
		}
		return strId;
	}

}