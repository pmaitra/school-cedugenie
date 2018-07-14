package com.qts.icam.utility.seat;

import java.util.Calendar;
import java.util.List;

public class RightToLeftSeating extends Seating{

	private String[][] rightToLeftArr;
	
	@Override
	String[][] applySeatingAlgorithim(int rowValue,int columnValue, String examName, int noOfStudents,List<String>finalStudentList,int counter) {
		rightToLeftArr = new String[rowValue][columnValue];
		
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		String yearInString = String.valueOf(year);
		for(int rowcount = 0; rowcount < rowValue; rowcount++){
			for(int columncount = columnValue-1; columncount >= 0; columncount--){
				
				if(counter<finalStudentList.size()){
					rightToLeftArr[rowcount][columncount]= finalStudentList.get(counter);//examName+"-"+yearInString+"-"+counter;  
					counter ++;
				}else{
					rightToLeftArr[rowcount][columncount]= " ";  
				}
			}
			
		}
		return rightToLeftArr;
	}
	
	
}
