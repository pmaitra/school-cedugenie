package com.qts.icam.controller.login;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.qts.icam.model.common.ChartData;
import com.qts.icam.model.common.ChartValuesModel;
import com.qts.icam.model.common.RepositoryStructure;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.SessionObject;
import com.qts.icam.service.administrator.AdministratorService;
import com.qts.icam.service.login.LoginService;
import com.qts.icam.service.report.ReportService;


@Controller 
@SessionAttributes("sessionObject")
public class ChartController {
	
	@Autowired
	ReportService reportService = null;
	
	@Autowired
	LoginService loginService = null;
	
	@Autowired
	AdministratorService administratorService = null;
	
	@Value("${report.query}")
	private String reportQueryPath;

	@RequestMapping(value="/chart1Data", method = RequestMethod.GET)
    protected @ResponseBody String loadChart1Data(@ModelAttribute("sessionObject") SessionObject sessionObject){
 
		
		StringBuffer sb = new StringBuffer();
		
		//return "column2d||Revenue for the past year||<chart caption=\"Harry\'s SuperMart\" subcaption=\"Monthly revenue for last year\" xaxisname=\"Month\" yaxisname=\"Amount\" numberprefix=\"$\" palettecolors=\"#008ee4\" bgalpha=\"0\" borderalpha=\"20\" canvasborderalpha=\"0\" useplotgradientcolor=\"0\" plotborderalpha=\"10\" placevaluesinside=\"1\" rotatevalues=\"1\" valuefontcolor=\"#ffffff\" captionpadding=\"20\" showaxislines=\"1\" axislinealpha=\"25\" divlinealpha=\"1\"><set label=\"Jan\" value=\"420000\" /><set label=\"Feb\" value=\"810000\" /><set label=\"Mar\" value=\"720000\" /><set label=\"Apr\" value=\"550000\" /><set label=\"May\" value=\"910000\" /><set label=\"Jun\" value=\"510000\" /><set label=\"Jul\" value=\"680000\" /><set label=\"Aug\" value=\"620000\" /><set label=\"Sep\" value=\"610000\" /><set label=\"Oct\" value=\"490000\" /><set label=\"Nov\" value=\"900000\" /><set label=\"Dec\" value=\"730000\" /></chart>";
		
		//return "pie3d||Visitors by age group||<chart caption=\"Split of Visitors by Age Group\" subcaption=\"Last year\" palettecolors=\"#0075c2,#1aaf5d,#f2c500,#f45b00,#8e0000\" bgcolor=\"#ffffff\" showborder=\"0\" use3dlighting=\"0\" showshadow=\"0\" enablesmartlabels=\"0\" startingangle=\"0\" showpercentvalues=\"1\" showpercentintooltip=\"0\" decimals=\"1\" captionfontsize=\"14\" subcaptionfontsize=\"14\" subcaptionfontbold=\"0\" tooltipcolor=\"#ffffff\" tooltipborderthickness=\"0\" tooltipbgcolor=\"#000000\" tooltipbgalpha=\"80\" tooltipborderradius=\"2\" tooltippadding=\"5\" showhovereffect=\"1\" showlegend=\"1\" legendbgcolor=\"#ffffff\" legendborderalpha=\"0\" legendshadow=\"0\" legenditemfontsize=\"10\" legenditemfontcolor=\"#666666\" usedataplotcolorforlabels=\"1\"><set label=\"Teenage\" value=\"1250400\" /><set label=\"Adult\" value=\"1463300\" /><set label=\"Mid-age\" value=\"1050700\" /><set label=\"Senior\" value=\"491000\" /></chart>";
		
		String userId = sessionObject.getUserId();
		Resource resource = loginService.getAccessTypeAndRoleList(userId);
		String roleName = LoginController.ROLE_NAME_TO_DISPLAY_ITS_CHARTS;
		String moduleName = LoginController.MODULE_NAME_TO_DISPLAY_ITS_CHARTS;
		/**
		 * new code added for reading queries.xml file from external repository*/
		RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
		String repository = repositoryStructure.getRepositoryPathName();
		String queryPath = repository+"/"+reportQueryPath;
		/***/
		//ChartData chartData =  reportService.loadChart1Data(roleName, moduleName, reportQueryPath);
		ChartData chartData =  reportService.loadChart1Data(roleName, moduleName, queryPath);
		
		sb.append(chartData.getChartType() + "||");
		sb.append(chartData.getChartLabel() + "||");
		sb.append("<chart caption = '" + chartData.getCaption() + "' ");
		sb.append("subcaption = '" + chartData.getSubCaption() + "' ");
		if((chartData.getChartType() == "pie2d") || (chartData.getChartType() == "pie3d")){
			
		}else{
			sb.append("xaxisname = '" + chartData.getXaxisname()  + "' ");
			sb.append("yaxisname = '" + chartData.getYaxisname()  + "' ");
			sb.append("numberprefix = '" + chartData.getNumberprefix()  + "' ");
		}
		sb.append("theme = '" + chartData.getTheme() + "'> ");
		
		List<ChartValuesModel> chartValuesModelList = chartData.getChartValuesModelList();
		for(ChartValuesModel chartValuesModel : chartValuesModelList){
			sb.append("<set label = '" + chartValuesModel.getLabel()  + "' value = '" + chartValuesModel.getValue() + "'/>");
		}
		
		sb.append("</chart>");
		
		//System.out.println(sb.toString());
        
		return sb.toString();
    }
	
	@RequestMapping(value="/chart2Data", method = RequestMethod.GET)
    protected @ResponseBody String loadChart2Data(@ModelAttribute("sessionObject") SessionObject sessionObject){
 
		
		StringBuffer sb = new StringBuffer();
		
		//return "column2d||Revenue for the past year||<chart caption=\"Harry\'s SuperMart\" subcaption=\"Monthly revenue for last year\" xaxisname=\"Month\" yaxisname=\"Amount\" numberprefix=\"$\" palettecolors=\"#008ee4\" bgalpha=\"0\" borderalpha=\"20\" canvasborderalpha=\"0\" useplotgradientcolor=\"0\" plotborderalpha=\"10\" placevaluesinside=\"1\" rotatevalues=\"1\" valuefontcolor=\"#ffffff\" captionpadding=\"20\" showaxislines=\"1\" axislinealpha=\"25\" divlinealpha=\"1\"><set label=\"Jan\" value=\"420000\" /><set label=\"Feb\" value=\"810000\" /><set label=\"Mar\" value=\"720000\" /><set label=\"Apr\" value=\"550000\" /><set label=\"May\" value=\"910000\" /><set label=\"Jun\" value=\"510000\" /><set label=\"Jul\" value=\"680000\" /><set label=\"Aug\" value=\"620000\" /><set label=\"Sep\" value=\"610000\" /><set label=\"Oct\" value=\"490000\" /><set label=\"Nov\" value=\"900000\" /><set label=\"Dec\" value=\"730000\" /></chart>";
		
		//return "pie3d||Visitors by age group||<chart caption=\"Split of Visitors by Age Group\" subcaption=\"Last year\" palettecolors=\"#0075c2,#1aaf5d,#f2c500,#f45b00,#8e0000\" bgcolor=\"#ffffff\" showborder=\"0\" use3dlighting=\"0\" showshadow=\"0\" enablesmartlabels=\"0\" startingangle=\"0\" showpercentvalues=\"1\" showpercentintooltip=\"0\" decimals=\"1\" captionfontsize=\"14\" subcaptionfontsize=\"14\" subcaptionfontbold=\"0\" tooltipcolor=\"#ffffff\" tooltipborderthickness=\"0\" tooltipbgcolor=\"#000000\" tooltipbgalpha=\"80\" tooltipborderradius=\"2\" tooltippadding=\"5\" showhovereffect=\"1\" showlegend=\"1\" legendbgcolor=\"#ffffff\" legendborderalpha=\"0\" legendshadow=\"0\" legenditemfontsize=\"10\" legenditemfontcolor=\"#666666\" usedataplotcolorforlabels=\"1\"><set label=\"Teenage\" value=\"1250400\" /><set label=\"Adult\" value=\"1463300\" /><set label=\"Mid-age\" value=\"1050700\" /><set label=\"Senior\" value=\"491000\" /></chart>";
		
		String userId = sessionObject.getUserId();
		Resource resource = loginService.getAccessTypeAndRoleList(userId);
		String roleName = LoginController.ROLE_NAME_TO_DISPLAY_ITS_CHARTS;
		String moduleName = LoginController.MODULE_NAME_TO_DISPLAY_ITS_CHARTS;
		/**
		 * new code added for reading queries.xml file from external repository*/
		RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
		String repository = repositoryStructure.getRepositoryPathName();
		String queryPath = repository+"/"+reportQueryPath;
		/***/
		//ChartData chartData =  reportService.loadChart1Data(roleName, moduleName, reportQueryPath);
		ChartData chartData =  reportService.loadChart2Data(roleName, moduleName, queryPath);
		
		sb.append(chartData.getChartType() + "||");
		sb.append(chartData.getChartLabel() + "||");
		sb.append("<chart caption = '" + chartData.getCaption() + "' ");
		sb.append("subcaption = '" + chartData.getSubCaption() + "' ");
		if((chartData.getChartType() == "pie2d") || (chartData.getChartType() == "pie3d")){
			
		}else{
			sb.append("xaxisname = '" + chartData.getXaxisname()  + "' ");
			sb.append("yaxisname = '" + chartData.getYaxisname()  + "' ");
			sb.append("numberprefix = '" + chartData.getNumberprefix()  + "' ");
		}
		sb.append("theme = '" + chartData.getTheme() + "'> ");
		
		List<ChartValuesModel> chartValuesModelList = chartData.getChartValuesModelList();
		for(ChartValuesModel chartValuesModel : chartValuesModelList){
			sb.append("<set label = '" + chartValuesModel.getLabel()  + "' value = '" + chartValuesModel.getValue() + "'/>");
		}
		
		sb.append("</chart>");
		
	//	System.out.println(sb.toString());
        
		return sb.toString();
    }
}
