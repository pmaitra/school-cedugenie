package com.qts.icam.utility.seat;

import java.util.List;

public class SpiralSeating extends Seating{

	private String[][] spiralArr;
	
	@Override
	String[][] applySeatingAlgorithim(int rowValue,int columnValue,String examName, int noOfStudents,List<String>finalStudentList,int counter) {
		spiralArr = new String[rowValue][columnValue];
		//int counter;
		int countervalue = 1;
	    int starting_row_index = 0;
        int ending_row_index = rowValue;
        int starting_column_index = 0;
        int ending_column_index = columnValue;
        
 
    while (starting_row_index < ending_row_index && starting_column_index < ending_column_index)
    {
        /* Print the first row from the remaining rows */
        for (counter = starting_column_index; counter < ending_column_index; ++counter)
        {
        	spiralArr[starting_row_index][counter] = examName+"-"+countervalue;
        	countervalue++;
        }
        starting_row_index++;
 
        /* Print the last column from the remaining columns */
        for (counter = starting_row_index; counter < ending_row_index; ++counter)
        {
        	spiralArr[counter][ending_column_index-1] = examName+"-"+countervalue;
            countervalue++;
        }
        ending_column_index--;
 
        /* Print the last row from the remaining rows */
        if ( starting_row_index < ending_row_index)
        {
            for (counter = ending_column_index-1; counter >= starting_column_index; --counter)
            {
            	spiralArr[ending_row_index-1][counter] = examName+"-"+countervalue;
               countervalue++;
            }
            ending_row_index--;
        }
 
        /* Print the first column from the remaining columns */
	        if (starting_column_index < ending_column_index)
	        {
	            for (counter = ending_row_index-1; counter >= starting_row_index; --counter)
	            {
	            	spiralArr[counter][starting_column_index] = examName+"-"+countervalue;
	                countervalue++;
	            }
	            starting_column_index++;    
	        }        
    	}
    	return spiralArr;
	}
	
	
}
