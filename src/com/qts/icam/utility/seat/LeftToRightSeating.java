package com.qts.icam.utility.seat;

import java.util.Calendar;
import java.util.List;

public class LeftToRightSeating extends Seating{

	private String[][] leftToRightArr;
	
	@Override
	String[][] applySeatingAlgorithim(int rowValue,int columnValue,String examName, int noOfStudents,List<String>finalStudentList,int counter) {
		leftToRightArr = new String[rowValue][columnValue];
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		String yearInString = String.valueOf(year);
	//	int counter = 1;
		for(int rowcount = 0; rowcount < rowValue; rowcount++){
			for(int columncount = 0; columncount < columnValue; columncount++){
				if(counter<finalStudentList.size()){
					leftToRightArr[rowcount][columncount]= finalStudentList.get(counter);//examName+"-"+yearInString+"-"+counter;  
					counter ++;
				}else{
					leftToRightArr[rowcount][columncount]= "";  
				}
				
			}
			
		}
		return leftToRightArr;
	}
	
	
}
