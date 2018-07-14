package com.qts.icam.utility.chartgenerator;

import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.SubCategoryAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.GroupedStackedBarRenderer;
import org.jfree.data.KeyToGroupMap;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

import com.qts.icam.model.academics.StudentResult;
import com.qts.icam.service.impl.inventory.InventoryServiceImpl;

public class ChartGenerator {
	
	public static Logger logger = Logger.getLogger(ChartGenerator.class);

	@SuppressWarnings("deprecation")
	public void generateBarChart(List<StudentResult> studentsSubjectsAndMarksListForChart) {
		 try{
//			 final String fiat = "FIAT";
//		      final String audi = "AUDI";
//		      final String ford = "FORD";
//		      final String speed = "Speed";
//		      final String millage = "Millage";
//		      final String userrating = "User Rating";
//		      final String safety = "safety";

		      final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		      if(studentsSubjectsAndMarksListForChart.get(0).getStatus().equals("FA")){
		    	  for(StudentResult studentResult : studentsSubjectsAndMarksListForChart){
						
						if(studentResult.getMarksType().equals("Pass Marks")){
							dataset.addValue( studentResult.getMarks() , studentResult.getMarksType() , studentResult.getSubject() );
						}
						if(studentResult.getMarksType().equals("Obtain Marks")){
							dataset.addValue( studentResult.getMarks() , studentResult.getMarksType() , studentResult.getSubject() );
						}
						if(studentResult.getMarksType().equals("Total Marks")){
							dataset.addValue( studentResult.getMarks() , studentResult.getMarksType() , studentResult.getSubject() );
						}
						if(studentResult.getMarksType().equals("Class Average Marks")){
							dataset.addValue( studentResult.getMarks() , studentResult.getMarksType() , studentResult.getSubject() );
						}
					}
		    	  
				      JFreeChart barChart = ChartFactory.createBarChart(
				         "RESULT ANALYSIS "+studentsSubjectsAndMarksListForChart.get(0).getExam(), 
				         "Subject", "Marks In Percentage(%)", 
				         dataset,PlotOrientation.VERTICAL, 
				         true, true, false);
				         
				      int width = 700; /* Width of the image */
				      int height = 292; /* Height of the image */ 
				      File BarChart = new File( "C:/icam/conf/image/"+studentsSubjectsAndMarksListForChart.get(0).getExam()+".png" ); 
				      ChartUtilities.saveChartAsJPEG( BarChart , barChart , width , height );
		      }
		      
		      if(studentsSubjectsAndMarksListForChart.get(0).getStatus().equals("SA1")){
		    	  
		    	  File imageChartDir = new File(studentsSubjectsAndMarksListForChart.get(0).getImagePath()); 
		  		// to store output
		  		boolean isDirCreated = imageChartDir.mkdirs();
		  		if (isDirCreated)
		  			logger.info("In Image Chart Dir class fileUpload() method: upload file folder location created.");
		  		else
		  			logger.info("In Image Chart Dir class fileUpload() method: upload file folder location already exist.");
		  		
		    	  
		    	  for(StudentResult studentResult : studentsSubjectsAndMarksListForChart){
						if(studentResult.getExam().equals("FA1")){
							dataset.addValue( studentResult.getMarks() , studentResult.getExam() , studentResult.getSubject() );
						}
						if(studentResult.getExam().equals("FA2")){
							dataset.addValue( studentResult.getMarks() , studentResult.getExam() , studentResult.getSubject() );
						}
						if(studentResult.getExam().equals("SA1")){
							dataset.addValue( studentResult.getMarks() , studentResult.getExam() , studentResult.getSubject() );
						}
						if(studentResult.getExam().equals("Obtained Marks Term-1")){
							dataset.addValue( studentResult.getMarks() , studentResult.getExam() , studentResult.getSubject() );
						}
						if(studentResult.getExam().equals("Class Average Marks Term-1")){
							dataset.addValue( studentResult.getMarks() , studentResult.getExam() , studentResult.getSubject() );
						}
					}
		    	  
				    JFreeChart barChart = ChartFactory.createBarChart(
				         "RESULT ANALYSIS- "+studentsSubjectsAndMarksListForChart.get(0).getStatus(), 
				         "Subject", "Marks In Percentage(%)", 
				         dataset,PlotOrientation.VERTICAL, 
				         true, true, false);
				         
				    CategoryPlot p = barChart.getCategoryPlot(); 
				    p.setOutlineVisible(true);
		            p.setDomainGridlinesVisible(true);

				    CategoryAxis axis = p.getDomainAxis();
				    axis.setLowerMargin(0.1);
				    axis.setUpperMargin(0.1);
				    axis.setCategoryMargin(0.5);
				    axis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(0.39269908169872414D));
			      	BarRenderer barrenderer = (BarRenderer) p.getRenderer();
			      	barrenderer.setDrawBarOutline(false);
			      	barrenderer.setMaximumBarWidth(0.04);
				    barrenderer.setItemMargin(0.05); 
				    barrenderer.setDrawBarOutline(false);
				       
				    NumberAxis rangeAxis = (NumberAxis) p.getRangeAxis();
				    rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
				    BarRenderer r1 = (BarRenderer) p.getRenderer();
				    r1.setDrawBarOutline(false);
				    barChart.getLegend().setFrame(BlockBorder.NONE);
				      
				    int width = 700; /* Width of the image */
				    int height = 292; /* Height of the image */ 
				    File BarChart = new File( studentsSubjectsAndMarksListForChart.get(0).getImagePath()+studentsSubjectsAndMarksListForChart.get(0).getRollNumber()+".png" ); 
				    ChartUtilities.saveChartAsJPEG( BarChart , barChart , width , height );
		      }
			
		      
		      if(studentsSubjectsAndMarksListForChart.get(0).getStatus().equals("SA2")){
		    	  
		    	  File imageChartDir = new File(studentsSubjectsAndMarksListForChart.get(0).getImagePath()); 
		  		// to store output
		  		boolean isDirCreated = imageChartDir.mkdirs();
		  		if (isDirCreated)
		  			logger.info("In Image Chart Dir class fileUpload() method: upload file folder location created.");
		  		else
		  			logger.info("In Image Chart Dir class fileUpload() method: upload file folder location already exist.");
		  		
		  		//SubCategoryAxis domainAxis = new SubCategoryAxis("Subject");
		    	for(StudentResult studentResult : studentsSubjectsAndMarksListForChart){
						
					if(studentResult.getExam().equals("Term-I")){
						dataset.addValue( studentResult.getMarks() , "Obtained Term-I Marks" , studentResult.getSubject() );
					}
					if(studentResult.getExam().equals("Term-II")){
						dataset.addValue( studentResult.getMarks() , "Obtained Term-II Marks"  , studentResult.getSubject() );
					}
				}
		    	  
				    JFreeChart barChart = ChartFactory.createBarChart(
				         "RESULT ANALYSIS ", 
				         "Subjects", "Marks In Percentage(%)", 
				         dataset,PlotOrientation.VERTICAL, 
				         true, true, false);
				      
				    CategoryPlot p = barChart.getCategoryPlot(); 
				    p.setOutlineVisible(true);
		            p.setDomainGridlinesVisible(true);

				    CategoryAxis axis = p.getDomainAxis();
				    axis.setLowerMargin(0.1);
				    axis.setUpperMargin(0.1);
				    axis.setCategoryMargin(0.5);
				    axis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(0.39269908169872414D));
			      	BarRenderer barrenderer = (BarRenderer) p.getRenderer();
			      	barrenderer.setDrawBarOutline(false);
			      	barrenderer.setMaximumBarWidth(0.04);
				    barrenderer.setItemMargin(0.05); 
				    barrenderer.setDrawBarOutline(false);
				       
				    NumberAxis rangeAxis = (NumberAxis) p.getRangeAxis();
				    rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
				    BarRenderer r1 = (BarRenderer) p.getRenderer();
				    r1.setDrawBarOutline(false);
				    barChart.getLegend().setFrame(BlockBorder.NONE);			      
				         
				    int width = 700; /* Width of the image */
				    int height = 292; /* Height of the image */ 
				    File BarChart = new File( studentsSubjectsAndMarksListForChart.get(0).getImagePath()+studentsSubjectsAndMarksListForChart.get(0).getRollNumber()+".png" ); 
				    ChartUtilities.saveChartAsJPEG( BarChart , barChart , width , height );
		      }
			
		 }catch(IOException e){
			 logger.error(e);
		 }catch (Exception e) {
			logger.error(e);
		 }
	}
	
	
}
