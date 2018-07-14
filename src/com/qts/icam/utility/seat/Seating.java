package com.qts.icam.utility.seat;

import java.util.List;

public abstract class Seating {

	abstract String[][] applySeatingAlgorithim(int rowValue, int columnValue,String examName, int noOfStudents, List<String>finalStudentList,int counter);
	
	
	private String[][] arr;
	
	//template method
	public final String[][] execute(int rowValue,int columnValue, String examName, int noOfStudents, List<String>finalStudentList,int counter){
		
		arr =	applySeatingAlgorithim(rowValue,columnValue, examName, noOfStudents,finalStudentList,counter);
		return arr;
		
	}
}
