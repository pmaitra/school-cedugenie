package com.qts.icam.utility.seat;

import java.util.Calendar;
import java.util.List;

public class ZigzagSeating extends Seating{

	private String[][] zigzagArr;

	
	@Override
	String[][] applySeatingAlgorithim(int rowValue,int columnValue, String examName, int noOfStudents,  List<String>finalStudentList,int counter){
		zigzagArr = new String[rowValue][columnValue];
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		String yearInString = String.valueOf(year);
		for(int rowcount = 0; rowcount < rowValue; rowcount++){
			if(rowcount % 2 == 0){
				for(int columncount = 0; columncount < columnValue; columncount++){
					
					
					if(counter<finalStudentList.size()){
						//zigzagArr[rowcount][columncount]= examName+"-"+yearInString+"-"+counter;  
						zigzagArr[rowcount][columncount]= finalStudentList.get(counter);  
						counter ++;
					}else{
						zigzagArr[rowcount][columncount]= "";  
					}
				}
			}else{
				for(int columncount = columnValue-1; columncount >= 0; columncount--){
					
					if(counter<finalStudentList.size()){
						//zigzagArr[rowcount][columncount]= examName+"-"+yearInString+"-"+counter; 
						
						zigzagArr[rowcount][columncount]=  finalStudentList.get(counter);  
						counter ++;
					}else{
						zigzagArr[rowcount][columncount]= "";  
					}
				}
			}
		}
		System.out.println("zigzagArr=========="+zigzagArr);
		return zigzagArr;
	}

	
	
	
}
