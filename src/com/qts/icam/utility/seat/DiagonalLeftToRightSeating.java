package com.qts.icam.utility.seat;

import java.util.Calendar;
import java.util.List;

public class DiagonalLeftToRightSeating extends Seating{

	private String[][] diagonalArr;
	
	@Override
	String[][] applySeatingAlgorithim(int rowValue,int columnValue,String examName, int noOfStudents,List<String>finalStudentList,int counter) {
		diagonalArr = new String[rowValue][columnValue];
		//int rowCount = 5;
		//int columnCount = 10;
		
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		String yearInString = String.valueOf(year);
		for (int rid = 0; rid < rowValue; rid++) {
			for (int row = rid, col = 0; row >= 0 && col < columnValue; row--, col++){
				if(counter<finalStudentList.size()){
					diagonalArr[row][col]= finalStudentList.get(counter);///examName+"-"+yearInString+"-"+counter;  
					counter ++;
				}else{
					diagonalArr[row][col]= "";  
				}
			}
		}
		for (int cid = 1; cid < columnValue; cid++) {
			   for (int row = rowValue-1, col = cid; row >= 0 && col < columnValue; row--, col++) {
				  /* diagonalArr[row][col]= counter; 
				   counter++;*/
				   if(counter<finalStudentList.size()){
						diagonalArr[row][col]= finalStudentList.get(counter); //examName+"-"+yearInString+"-"+counter;  
						counter ++;
					}else{
						diagonalArr[row][col]= "";  
					}
			   }
		}
		return diagonalArr;
	}
	/*@Override
	void displaySeatingArrangement() {
		for(int rowcount = 0; rowcount < 5; rowcount++){
			for(int columncount = 0; columncount < 10; columncount++){
				System.out.print(arr[rowcount][columncount] + "   ");  
			}
			System.out.println("");
			System.out.println("");
		}
	}*/
	
}	
	

