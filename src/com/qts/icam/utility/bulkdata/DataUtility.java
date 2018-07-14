package com.qts.icam.utility.bulkdata;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.qts.icam.model.academics.StudentResult;
import com.qts.icam.model.academics.Subject;
import com.qts.icam.model.administrator.Functionality;
import com.qts.icam.model.administrator.Role;
import com.qts.icam.model.backoffice.ResourceAttendance;
import com.qts.icam.model.backoffice.StudentSubjectMapping;
import com.qts.icam.model.common.Address;
import com.qts.icam.model.common.Asset;
import com.qts.icam.model.common.Department;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.Student;
import com.qts.icam.model.erp.Designation;
import com.qts.icam.model.erp.Employee;
import com.qts.icam.model.erp.EmployeeDependent;
import com.qts.icam.model.erp.EmployeeType;
import com.qts.icam.model.erp.JobType;
import com.qts.icam.model.erp.Organization;
import com.qts.icam.model.erp.Publication;
import com.qts.icam.model.erp.Qualification;
import com.qts.icam.model.inventory.Commodity;
import com.qts.icam.model.library.Author;
import com.qts.icam.model.library.Book;
import com.qts.icam.model.library.BookCategory;
import com.qts.icam.model.library.BookLanguage;
import com.qts.icam.model.library.BookMedium;
import com.qts.icam.model.library.BookPublisher;


public class DataUtility {
	
	public static Logger logger = Logger.getLogger(DataUtility.class);

	public List<Role> updateFunctionalityRoleMappingFromExcel(String string, String functionalityRoleMappingExcel) {
		
		String Path = string+functionalityRoleMappingExcel;			
		List<Role> rolelist = new ArrayList<Role>();
		try {
			FileInputStream file = new FileInputStream(new File(Path));
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			int sheetNumber = workbook.getNumberOfSheets();
			for(int i=0;i<sheetNumber;i++){
				HSSFSheet sheet = workbook.getSheetAt(i);
				Role role = new Role();
				List<Functionality> functionalityList = new ArrayList<Functionality>();
				role.setRoleName(sheet.getSheetName());				
				Iterator<Row> rowIterator = sheet.iterator();				
				int flag = 0;
				while (rowIterator.hasNext())  {					  					    
					Row row = rowIterator.next();
		                //For each row, iterate through all the columns
		            Iterator<Cell> cellIterator = row.cellIterator();
		            Functionality functionality = new Functionality();;
		            while (cellIterator.hasNext()){		                	 
		                Cell cell = cellIterator.next();
		                    //Check the cell type and format accordingly
		                switch (cell.getCellType()){
			            	case Cell.CELL_TYPE_STRING:
			                    if(cell.getStringCellValue().equalsIgnoreCase("Functionality Name")|| cell.getStringCellValue().equalsIgnoreCase("VIEW") || cell.getStringCellValue().equalsIgnoreCase("INSERT") || cell.getStringCellValue().equalsIgnoreCase("UPDATE") ||cell.getStringCellValue()==null){
			                    	break;
			                    }
			                    else{
			                    	if(cell.getStringCellValue()!= null){			                            		
			                        	functionality.setFunctionalityName(cell.getStringCellValue());
				                    }
			                    }
			                    break;
			                case Cell.CELL_TYPE_BOOLEAN:
				                if(flag == 0){
				                	functionality.setView(cell.getBooleanCellValue());				                    			
				                    flag++;
				                }
				                else if(flag == 1){
				                	functionality.setInsert(cell.getBooleanCellValue());
				                	flag++;
				                }
				                else if(flag == 2){
				                	functionality.setUpdate(cell.getBooleanCellValue());
				                	flag = 0;
				                }
				                break;
			                case Cell.CELL_TYPE_FORMULA:
				                if(flag == 0){
				                	functionality.setView(cell.getBooleanCellValue());				                    			
				                	flag++;
				                }
				                else if(flag == 1){
				                	functionality.setInsert(cell.getBooleanCellValue());
				                	flag++;
				                }
				                else if(flag == 2){
				                	functionality.setUpdate(cell.getBooleanCellValue());
				                	flag = 0;
				                }
				                break;				                        
		                }
	                }
	                if(null != functionality.getFunctionalityName()){
	                	functionalityList.add(functionality);
	                }			                
		        }
				role.setFunctionalityList(functionalityList);				  	
				rolelist.add(role);
			    file.close();		            
//			       for(Role r:rolelist)   {
//			    	   for(Functionality f:r.getFunctionalityList()){
//			    		   logger.info(f.getFunctionalityName()+"\t"+f.isView()+"\t"+f.isInsert()+"\t"+f.isUpdate());
//			    	   }
//			       } 
			}
		}
        catch (Exception e) {
            logger.error(e);	            
        }
		return rolelist;
	}
	
	
	public List<String> readExcelFileForBookAuthorList(String excelFile) {
		List<String> authorList = null;
		HashSet<String> hs = new HashSet<String>();
		try{
			FileInputStream file = new FileInputStream(new File(excelFile));
		     
			//Get the workbook instance for XLS file
			HSSFWorkbook workbook = new HSSFWorkbook(file);			
	
			//Get first sheet from the workbook
			HSSFSheet bookDetailsSheet = workbook.getSheetAt(0);
			for (int i = 1; i <= bookDetailsSheet.getLastRowNum(); i++) {
				HSSFRow bookDetailsSheetRow = bookDetailsSheet.getRow(i);
				
				if(null != bookDetailsSheetRow.getCell(11)){
					if(bookDetailsSheetRow.getCell(11).getStringCellValue().length() != 0){
						bookDetailsSheetRow.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
						hs.add(bookDetailsSheetRow.getCell(11).getStringCellValue());
					}
				}
				if(null != bookDetailsSheetRow.getCell(12)){
					if(bookDetailsSheetRow.getCell(12).getStringCellValue().length() != 0){
						bookDetailsSheetRow.getCell(12).setCellType(Cell.CELL_TYPE_STRING);
						hs.add(bookDetailsSheetRow.getCell(12).getStringCellValue());
					}						
				}
				if(null != bookDetailsSheetRow.getCell(13)){
					if(bookDetailsSheetRow.getCell(13).getStringCellValue().length() != 0){
						bookDetailsSheetRow.getCell(13).setCellType(Cell.CELL_TYPE_STRING);
						hs.add(bookDetailsSheetRow.getCell(13).getStringCellValue());
					}						
				}
				if(null != bookDetailsSheetRow.getCell(14)){
					if(bookDetailsSheetRow.getCell(14).getStringCellValue().length() != 0){
						bookDetailsSheetRow.getCell(14).setCellType(Cell.CELL_TYPE_STRING);
						hs.add(bookDetailsSheetRow.getCell(14).getStringCellValue());
					}						
				}				
			}				
			if(hs.size() >= 1){
				authorList = new ArrayList<String>();
				authorList.addAll(hs);
				return authorList;
			}else{
				return null;
			}
		}catch(Exception e) {
			logger.error(e);
		}
		return null;
	}



	public List<String> readExcelFileForPublisherList(String excelFile) {
		List<String> publisherList = null;
		HashSet<String> hs = new HashSet<String>();
		try{
			FileInputStream file = new FileInputStream(new File(excelFile));
			
			//Get the workbook instance for XLS file
			HSSFWorkbook workbook = new HSSFWorkbook(file);			
	
			//Get first sheet from the workbook
			HSSFSheet bookDetailsSheet = workbook.getSheetAt(0);
			for (int i = 1; i <= bookDetailsSheet.getLastRowNum(); i++) {
				HSSFRow bookDetailsSheetRow = bookDetailsSheet.getRow(i);
				
				if(null != bookDetailsSheetRow.getCell(2)){
					if(bookDetailsSheetRow.getCell(2).getStringCellValue().length() != 0){
						bookDetailsSheetRow.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
						hs.add(bookDetailsSheetRow.getCell(2).getStringCellValue());
					}						
				}
			}				
			if(hs.size() >= 1){
				publisherList = new ArrayList<String>();
				publisherList.addAll(hs);
				return publisherList;
			}else{
				return null;
			}
		}catch(Exception e) {
			logger.error(e);
		}
		return null;
	}



	public List<String> readExcelFileForMediumList(String excelFile) {
		List<String> mediumList = null;
		HashSet<String> hs = new HashSet<String>();
		try{
			FileInputStream file = new FileInputStream(new File(excelFile));
		     
			//Get the workbook instance for XLS file
			HSSFWorkbook workbook = new HSSFWorkbook(file);			
	
			//Get first sheet from the workbook
			HSSFSheet bookDetailsSheet = workbook.getSheetAt(0);
			for (int i = 1; i <= bookDetailsSheet.getLastRowNum(); i++) {
				HSSFRow bookDetailsSheetRow = bookDetailsSheet.getRow(i);
				
				if(null != bookDetailsSheetRow.getCell(3)){
					if(bookDetailsSheetRow.getCell(3).getStringCellValue().length() != 0){
						bookDetailsSheetRow.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
						hs.add(bookDetailsSheetRow.getCell(3).getStringCellValue());
					}						
				}
			}				
			if(hs.size() >= 1){
				mediumList = new ArrayList<String>();
				mediumList.addAll(hs);
				return mediumList;
			}else{
				return null;
			}
		}catch(Exception e) {
			logger.error(e);
		}
		return null;
	}



	public List<String> readExcelFileForLanguageList(String excelFile) {
		List<String> languageList = null;
		HashSet<String> hs = new HashSet<String>();
		try{
			FileInputStream file = new FileInputStream(new File(excelFile));
		     
			//Get the workbook instance for XLS file
			HSSFWorkbook workbook = new HSSFWorkbook(file);			
	
			//Get first sheet from the workbook
			HSSFSheet bookDetailsSheet = workbook.getSheetAt(0);
			for (int i = 1; i <= bookDetailsSheet.getLastRowNum(); i++) {
				HSSFRow bookDetailsSheetRow = bookDetailsSheet.getRow(i);
				
				if(null != bookDetailsSheetRow.getCell(8)){
					if(bookDetailsSheetRow.getCell(8).getStringCellValue().length() != 0){
						bookDetailsSheetRow.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
						hs.add(bookDetailsSheetRow.getCell(8).getStringCellValue().toUpperCase());
					}					
				}
			}				
			if(hs.size() >= 1){
				languageList = new ArrayList<String>();
				languageList.addAll(hs);
				return languageList;
			}else{
				return null;
			}
		}catch(Exception e) {
			logger.error(e);
		}
		return null;
	}






//	public List<Book> readExcelFileForBookDetails(String excelFile) {
//		List<Book> bookDetailsList = new ArrayList<Book>();
//		try{
//			FileInputStream file = new FileInputStream(new File(excelFile));
//     
//			//Get the workbook instance for XLS file
//			HSSFWorkbook workbook = new HSSFWorkbook(file);			
// 
//			//Get first sheet from the workbook
//			HSSFSheet bookDetailsSheet = workbook.getSheetAt(0);
//			for (int i = 1; i <= bookDetailsSheet.getLastRowNum(); i++) {
//				HSSFRow bookDetailsSheetRow = bookDetailsSheet.getRow(i); 
//				Book bookObj = new Book();
//				
//				if(null != bookDetailsSheetRow.getCell(0)){
//					bookDetailsSheetRow.getCell(0).setCellType(Cell.CELL_TYPE_STRING);							
//					bookObj.setBookName(bookDetailsSheetRow.getCell(0).getStringCellValue());
//					bookObj.setBookDesc(bookDetailsSheetRow.getCell(0).getStringCellValue());
//				}
//				
//				if(null != bookDetailsSheetRow.getCell(1)){
//					bookDetailsSheetRow.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
//					bookObj.setAccessionNumber(bookDetailsSheetRow.getCell(1).getStringCellValue());
//				}						
//				
//				if(null != bookDetailsSheetRow.getCell(2)){
//					bookDetailsSheetRow.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
//					BookPublisher bookPub = new BookPublisher();
//					bookPub.setBookPublisherName(bookDetailsSheetRow.getCell(2).getStringCellValue());
//					bookObj.setBookPublisher(bookPub);
//				}
//				if(null != bookDetailsSheetRow.getCell(3)){
//					bookDetailsSheetRow.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
//					BookMedium bookMed = new BookMedium();
//					bookMed.setBookMediumName(bookDetailsSheetRow.getCell(3).getStringCellValue());
//					bookObj.setBookMedium(bookMed);
//				}
//				if(null != bookDetailsSheetRow.getCell(4)){
//					bookDetailsSheetRow.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
//					bookObj.setBookIsbn(bookDetailsSheetRow.getCell(4).getStringCellValue());
//				}
//				if(null != bookDetailsSheetRow.getCell(5)){
//					bookDetailsSheetRow.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
//					bookObj.setBookEdition(bookDetailsSheetRow.getCell(5).getStringCellValue());
//				}
//				if(null != bookDetailsSheetRow.getCell(6)){
//					bookDetailsSheetRow.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
//					bookObj.setBookPlace(bookDetailsSheetRow.getCell(6).getStringCellValue());
//				}
//				if(null != bookDetailsSheetRow.getCell(7)){
//					bookDetailsSheetRow.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
//					bookObj.setBookPeriodicity(bookDetailsSheetRow.getCell(7).getStringCellValue());
//				}
//				bookObj.setTotalNumberOfBookCopies(1);				
//				if(null != bookDetailsSheetRow.getCell(8)){
//					bookDetailsSheetRow.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
//					BookLanguage bookLang = new BookLanguage();
//					bookLang.setBookLanguageName(bookDetailsSheetRow.getCell(8).getStringCellValue().toUpperCase());
//					bookObj.setBookLanguage(bookLang);
//				}
//				if(null != bookDetailsSheetRow.getCell(9)){
//					bookDetailsSheetRow.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
//					bookObj.setBookType(bookDetailsSheetRow.getCell(9).getStringCellValue());
//				}
//				if(null != bookDetailsSheetRow.getCell(10)){
//					bookDetailsSheetRow.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
//					try{
//						bookObj.setPrice(Double.parseDouble(bookDetailsSheetRow.getCell(10).getStringCellValue()));
//					}catch(NumberFormatException nfe){
//						logger.error(nfe);
//					}
//				}
//				HashSet<String> hs = new HashSet<String>();
//				if(null != bookDetailsSheetRow.getCell(11)){
//					bookDetailsSheetRow.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
//					hs.add(bookDetailsSheetRow.getCell(11).getStringCellValue());
//				}
//				if(null != bookDetailsSheetRow.getCell(12)){
//					bookDetailsSheetRow.getCell(12).setCellType(Cell.CELL_TYPE_STRING);
//					hs.add(bookDetailsSheetRow.getCell(12).getStringCellValue());
//				}
//				if(null != bookDetailsSheetRow.getCell(13)){
//					bookDetailsSheetRow.getCell(13).setCellType(Cell.CELL_TYPE_STRING);
//					hs.add(bookDetailsSheetRow.getCell(13).getStringCellValue());
//				}
//				if(null != bookDetailsSheetRow.getCell(14)){
//					bookDetailsSheetRow.getCell(14).setCellType(Cell.CELL_TYPE_STRING);
//					hs.add(bookDetailsSheetRow.getCell(14).getStringCellValue());
//				}
//				if(null != bookDetailsSheetRow.getCell(15)){
//					if(bookDetailsSheetRow.getCell(15).getStringCellValue().length() != 0){
//						bookDetailsSheetRow.getCell(15).setCellType(Cell.CELL_TYPE_STRING);
//						Subject subject = new Subject();
//						subject.setSubjectName(bookDetailsSheetRow.getCell(15).getStringCellValue().toUpperCase());
//						bookObj.setBookSubject(subject);
//					}					
//				}
//				if(null != bookDetailsSheetRow.getCell(16)){
//					if(bookDetailsSheetRow.getCell(16).getStringCellValue().length() != 0){
//						bookDetailsSheetRow.getCell(16).setCellType(Cell.CELL_TYPE_STRING);
//						BookCategory bookCategory = new BookCategory();
//						bookCategory.setBookCategoryName(bookDetailsSheetRow.getCell(16).getStringCellValue());
//						bookObj.setBookCategory(bookCategory);
//					}
//				}
//				List<String> authorList = null;
//				if(hs.size() >= 1){
//					authorList = new ArrayList<String>();
//					authorList.addAll(hs);
//				}
//				List<Author> bookAuthorList = null;
//				if(null != authorList){
//					bookAuthorList = new ArrayList<Author>();
//					for(String str: authorList){
//						Author author = new Author();
//						author.setAuthorFullName(str);
//						bookAuthorList.add(author);
//					}
//				}
//				if(null != bookAuthorList && bookAuthorList.size() != 0){
//					bookObj.setBookAuthorList(bookAuthorList);
//				}
//				
//				int count = 0;
//				if(null != bookDetailsList && bookDetailsList.size() != 0){
//					for(Book book : bookDetailsList){
//						if(null != book && null != book.getAccessionNumber() && null != bookObj && null != bookObj.getAccessionNumber()){
//							if(book.getAccessionNumber().equalsIgnoreCase(bookObj.getAccessionNumber())){
//								count = 0;
//							}else{
//								count = 1;
//							}
//						}
//					}
//				}else{
//					count = 1;
//				}
//				
//				if(count == 1){
//					if(bookObj != null){
//						bookDetailsList.add(bookObj);
//					}
//				}
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//			logger.error(e);
//		}
//		return bookDetailsList;
//	}


	
	/// Changing this now
	
	public List<Employee> readExcelFileForStaffDetails(String excelFile) {
		List<Employee> employeeDetailsList = new ArrayList<Employee>();
		try{
			FileInputStream file = new FileInputStream(new File(excelFile));    
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			HSSFSheet staffDetailsSheet = workbook.getSheetAt(0);
			logger.info("@@ staffDetailsSheet :: "+staffDetailsSheet.getLastRowNum());
			
			for (int i = 1; i <= staffDetailsSheet.getLastRowNum(); i++) {
				HSSFRow staffDetailsSheetRow = staffDetailsSheet.getRow(i); 
				if(null!=staffDetailsSheetRow){
					Employee employee = new Employee();
					Resource resource = new Resource();
					
					if(null != staffDetailsSheetRow.getCell(0)){
						if(staffDetailsSheetRow.getCell(0).getStringCellValue().length() != 0){
							staffDetailsSheetRow.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
							employee.setEmployeeCode(staffDetailsSheetRow.getCell(0).getStringCellValue().toUpperCase());
						}
					}
					if(null != staffDetailsSheetRow.getCell(1)){
						if(staffDetailsSheetRow.getCell(1).getStringCellValue().length() != 0){
							staffDetailsSheetRow.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
							resource.setUserId(staffDetailsSheetRow.getCell(1).getStringCellValue().toUpperCase());
						}
					}
					if(null != staffDetailsSheetRow.getCell(2)){
						if(staffDetailsSheetRow.getCell(2).getStringCellValue().length() != 0){
							staffDetailsSheetRow.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
							resource.setFirstName(staffDetailsSheetRow.getCell(2).getStringCellValue().toUpperCase());
						}
					}				
					if(null != staffDetailsSheetRow.getCell(3)){
						if(staffDetailsSheetRow.getCell(3).getStringCellValue().length() != 0){
							staffDetailsSheetRow.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
							resource.setMiddleName(staffDetailsSheetRow.getCell(3).getStringCellValue().toUpperCase());
						}
					}				
					if(null != staffDetailsSheetRow.getCell(4)){
						if(staffDetailsSheetRow.getCell(4).getStringCellValue().length() != 0){
							staffDetailsSheetRow.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
							resource.setLastName(staffDetailsSheetRow.getCell(4).getStringCellValue().toUpperCase());
						}
					}
					if(null != staffDetailsSheetRow.getCell(5)){
						if(staffDetailsSheetRow.getCell(5).getStringCellValue().length() != 0){
							staffDetailsSheetRow.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
							employee.setDateOfJoining(staffDetailsSheetRow.getCell(5).getStringCellValue());
						}
					}
					if(null != staffDetailsSheetRow.getCell(6)){
						if(staffDetailsSheetRow.getCell(6).getStringCellValue().length() != 0){
							staffDetailsSheetRow.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
							EmployeeType empType = new EmployeeType();
							empType.setEmployeeTypeName(staffDetailsSheetRow.getCell(6).getStringCellValue());
							employee.setEmployeeType(empType);
						}
					}
					if(null != staffDetailsSheetRow.getCell(7)){
						if(staffDetailsSheetRow.getCell(7).getStringCellValue().length() != 0){
							staffDetailsSheetRow.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
							JobType jType = new JobType();
							jType.setJobTypeName(staffDetailsSheetRow.getCell(7).getStringCellValue());
							employee.setJobType(jType);
						}
					}
					if(null != staffDetailsSheetRow.getCell(8)){
						if(staffDetailsSheetRow.getCell(8).getStringCellValue().length() != 0){
							staffDetailsSheetRow.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
							Designation desig = new Designation();
							desig.setDesignationName(staffDetailsSheetRow.getCell(8).getStringCellValue());
							employee.setDesignation(desig);
						}
					}
					if(null != staffDetailsSheetRow.getCell(9)){
						if(staffDetailsSheetRow.getCell(9).getStringCellValue().length() != 0){
							staffDetailsSheetRow.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
							Department dept = new Department();
							dept.setDepartmentName(staffDetailsSheetRow.getCell(9).getStringCellValue());
							employee.setDepartment(dept);
						}
					}
	//				if(null != staffDetailsSheetRow.getCell(9) && null != staffDetailsSheetRow.getCell(10) && null != staffDetailsSheetRow.getCell(11)){
	//					if(staffDetailsSheetRow.getCell(9).getStringCellValue().length() != 0 && staffDetailsSheetRow.getCell(10).getStringCellValue().length() != 0 && staffDetailsSheetRow.getCell(11).getStringCellValue().length() != 0){
	//						staffDetailsSheetRow.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
	//						staffDetailsSheetRow.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
	//						staffDetailsSheetRow.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
	////						StandardPayScales stanPayScale = new StandardPayScales();
	////						stanPayScale.setPayBand(staffDetailsSheetRow.getCell(9).getStringCellValue());
	////						stanPayScale.setGradePay(Integer.parseInt(staffDetailsSheetRow.getCell(10).getStringCellValue()));
	////						stanPayScale.setCorrespondingPayBands(staffDetailsSheetRow.getCell(11).getStringCellValue());
	////						employee.setStandardPayScales(stanPayScale);
	//					}
	//				}
					if(null != staffDetailsSheetRow.getCell(13)){
						if(staffDetailsSheetRow.getCell(13).getStringCellValue().length() != 0){
							staffDetailsSheetRow.getCell(13).setCellType(Cell.CELL_TYPE_STRING);
							employee.setBasicPay(Integer.parseInt(staffDetailsSheetRow.getCell(13).getStringCellValue()));
						}
					}
					if(null != staffDetailsSheetRow.getCell(14)){
						if(staffDetailsSheetRow.getCell(14).getStringCellValue().length() != 0){
							staffDetailsSheetRow.getCell(14).setCellType(Cell.CELL_TYPE_STRING);
							resource.setGender(staffDetailsSheetRow.getCell(14).getStringCellValue());
						}
					}
					if(null != staffDetailsSheetRow.getCell(15)){
						if(staffDetailsSheetRow.getCell(15).getStringCellValue().length() != 0){
							staffDetailsSheetRow.getCell(15).setCellType(Cell.CELL_TYPE_STRING);
							resource.setDateOfBirth(staffDetailsSheetRow.getCell(15).getStringCellValue());
						}
					}
					if(null != staffDetailsSheetRow.getCell(16)){
						if(staffDetailsSheetRow.getCell(16).getStringCellValue().length() != 0){
							staffDetailsSheetRow.getCell(16).setCellType(Cell.CELL_TYPE_STRING);
							employee.setDateOfRetirement(staffDetailsSheetRow.getCell(16).getStringCellValue());
						}
					}
					if(null != staffDetailsSheetRow.getCell(17)){
						if(staffDetailsSheetRow.getCell(17).getStringCellValue().length() != 0){
							staffDetailsSheetRow.getCell(17).setCellType(Cell.CELL_TYPE_STRING);
							resource.setBloodGroup(staffDetailsSheetRow.getCell(17).getStringCellValue());
						}
					}				
					if(null != staffDetailsSheetRow.getCell(18)){
						if(staffDetailsSheetRow.getCell(18).getStringCellValue().length() != 0){
							staffDetailsSheetRow.getCell(18).setCellType(Cell.CELL_TYPE_STRING);
							resource.setMotherTongue(staffDetailsSheetRow.getCell(18).getStringCellValue().toUpperCase());
						}
					}				
					if(null != staffDetailsSheetRow.getCell(19)){
						if(staffDetailsSheetRow.getCell(19).getStringCellValue().length() != 0){
							staffDetailsSheetRow.getCell(19).setCellType(Cell.CELL_TYPE_STRING);
							resource.setCategory(staffDetailsSheetRow.getCell(19).getStringCellValue());
						}
					}				
					if(null != staffDetailsSheetRow.getCell(20)){
						if(staffDetailsSheetRow.getCell(20).getStringCellValue().length() != 0){
							staffDetailsSheetRow.getCell(20).setCellType(Cell.CELL_TYPE_STRING);
							resource.setReligion(staffDetailsSheetRow.getCell(20).getStringCellValue().toUpperCase());
						}
					}				
					if(null != staffDetailsSheetRow.getCell(21)){
						if(staffDetailsSheetRow.getCell(21).getStringCellValue().length() != 0){
							staffDetailsSheetRow.getCell(21).setCellType(Cell.CELL_TYPE_STRING);
							resource.setNationality(staffDetailsSheetRow.getCell(21).getStringCellValue().toUpperCase());
						}
					}				
					if(null != staffDetailsSheetRow.getCell(22)){
						if(staffDetailsSheetRow.getCell(22).getStringCellValue().length() != 0){
							staffDetailsSheetRow.getCell(22).setCellType(Cell.CELL_TYPE_STRING);
							resource.setFatherFirstName(staffDetailsSheetRow.getCell(22).getStringCellValue().toUpperCase());
						}
					}				
					if(null != staffDetailsSheetRow.getCell(23)){
						if(staffDetailsSheetRow.getCell(23).getStringCellValue().length() != 0){
							staffDetailsSheetRow.getCell(23).setCellType(Cell.CELL_TYPE_STRING);
							resource.setFatherMiddleName(staffDetailsSheetRow.getCell(23).getStringCellValue().toUpperCase());
						}
					}				
					if(null != staffDetailsSheetRow.getCell(24)){
						if(staffDetailsSheetRow.getCell(24).getStringCellValue().length() != 0){
							staffDetailsSheetRow.getCell(24).setCellType(Cell.CELL_TYPE_STRING);
							resource.setFatherLastName(staffDetailsSheetRow.getCell(24).getStringCellValue().toUpperCase());
						}
					}
					if(null != staffDetailsSheetRow.getCell(25)){
						if(staffDetailsSheetRow.getCell(25).getStringCellValue().length() != 0){
							staffDetailsSheetRow.getCell(25).setCellType(Cell.CELL_TYPE_STRING);
							employee.setFatherOccupation(staffDetailsSheetRow.getCell(25).getStringCellValue().toUpperCase());
						}
					}
					if(null != staffDetailsSheetRow.getCell(26)){
						if(staffDetailsSheetRow.getCell(26).getStringCellValue().length() != 0){
							staffDetailsSheetRow.getCell(26).setCellType(Cell.CELL_TYPE_STRING);
							resource.setMotherFirstName(staffDetailsSheetRow.getCell(26).getStringCellValue().toUpperCase());
						}
					}				
					if(null != staffDetailsSheetRow.getCell(27)){
						if(staffDetailsSheetRow.getCell(27).getStringCellValue().length() != 0){
							staffDetailsSheetRow.getCell(27).setCellType(Cell.CELL_TYPE_STRING);
							resource.setMotherMiddleName(staffDetailsSheetRow.getCell(27).getStringCellValue().toUpperCase());
						}
					}				
					if(null != staffDetailsSheetRow.getCell(28)){
						if(staffDetailsSheetRow.getCell(28).getStringCellValue().length() != 0){
							staffDetailsSheetRow.getCell(28).setCellType(Cell.CELL_TYPE_STRING);
							resource.setMotherLastName(staffDetailsSheetRow.getCell(28).getStringCellValue().toUpperCase());
						}
					}				
					if(null != staffDetailsSheetRow.getCell(29)){
						if(staffDetailsSheetRow.getCell(29).getStringCellValue().length() != 0){
							staffDetailsSheetRow.getCell(29).setCellType(Cell.CELL_TYPE_STRING);
							employee.setMotherOccupation(staffDetailsSheetRow.getCell(29).getStringCellValue().toUpperCase());
						}
					}				
					if(null != staffDetailsSheetRow.getCell(30)){
						if(staffDetailsSheetRow.getCell(30).getStringCellValue().length() != 0){
							staffDetailsSheetRow.getCell(30).setCellType(Cell.CELL_TYPE_STRING);
							employee.setMedicalAttention(staffDetailsSheetRow.getCell(30).getStringCellValue());
						}
					}					
					if(null != staffDetailsSheetRow.getCell(31)){
						if(staffDetailsSheetRow.getCell(31).getStringCellValue().length() != 0){
							staffDetailsSheetRow.getCell(31).setCellType(Cell.CELL_TYPE_STRING);		
							employee.setMaritalStatus(staffDetailsSheetRow.getCell(31).getStringCellValue());
						}
					}			
					if(null != staffDetailsSheetRow.getCell(32)){
						if(staffDetailsSheetRow.getCell(32).getStringCellValue().length() != 0){
							staffDetailsSheetRow.getCell(32).setCellType(Cell.CELL_TYPE_STRING);		
							employee.setSpouseName(staffDetailsSheetRow.getCell(32).getStringCellValue().toUpperCase());
						}
					}				
					if(null != staffDetailsSheetRow.getCell(33)){
						if(staffDetailsSheetRow.getCell(33).getStringCellValue().length() != 0){
							staffDetailsSheetRow.getCell(33).setCellType(Cell.CELL_TYPE_STRING);
							resource.setMobile(staffDetailsSheetRow.getCell(33).getStringCellValue());
						}
					}				
					if(null != staffDetailsSheetRow.getCell(34)){
						if(staffDetailsSheetRow.getCell(34).getStringCellValue().length() != 0){
							staffDetailsSheetRow.getCell(34).setCellType(Cell.CELL_TYPE_STRING);
							resource.setEmailId(staffDetailsSheetRow.getCell(34).getStringCellValue());
						}
					}
					if(null != staffDetailsSheetRow.getCell(35)){
						if(staffDetailsSheetRow.getCell(35).getStringCellValue().length() != 0){
							staffDetailsSheetRow.getCell(35).setCellType(Cell.CELL_TYPE_STRING);
							resource.setPassportNo(staffDetailsSheetRow.getCell(35).getStringCellValue());
						}
					}
					if(null != staffDetailsSheetRow.getCell(36)){
						if(staffDetailsSheetRow.getCell(36).getStringCellValue().length() != 0){
							staffDetailsSheetRow.getCell(36).setCellType(Cell.CELL_TYPE_STRING);
							resource.setPanCardNo(staffDetailsSheetRow.getCell(36).getStringCellValue());
						}
					}
					if(null != staffDetailsSheetRow.getCell(37)){
						if(staffDetailsSheetRow.getCell(37).getStringCellValue().length() != 0){
							staffDetailsSheetRow.getCell(37).setCellType(Cell.CELL_TYPE_STRING);
							resource.setAadharCardNo(staffDetailsSheetRow.getCell(35).getStringCellValue());
						}
					}
					if(null != staffDetailsSheetRow.getCell(38)){
						if(staffDetailsSheetRow.getCell(38).getStringCellValue().length() != 0){
							staffDetailsSheetRow.getCell(38).setCellType(Cell.CELL_TYPE_STRING);
							resource.setVoterCardNo(staffDetailsSheetRow.getCell(38).getStringCellValue());
						}
					}
					if(null != staffDetailsSheetRow.getCell(39)){
						if(staffDetailsSheetRow.getCell(39).getStringCellValue().length() != 0){
							staffDetailsSheetRow.getCell(39).setCellType(Cell.CELL_TYPE_STRING);
							resource.setVotingConstituency(staffDetailsSheetRow.getCell(39).getStringCellValue());
						}
					}
					if(null != staffDetailsSheetRow.getCell(40)){
						if(staffDetailsSheetRow.getCell(40).getStringCellValue().length() != 0){
							staffDetailsSheetRow.getCell(40).setCellType(Cell.CELL_TYPE_STRING);
							resource.setParliamentaryConstituency(staffDetailsSheetRow.getCell(40).getStringCellValue());
						}
					}
					if(null != staffDetailsSheetRow.getCell(41)){
						if(staffDetailsSheetRow.getCell(41).getStringCellValue().length() != 0){
							staffDetailsSheetRow.getCell(41).setCellType(Cell.CELL_TYPE_STRING);
							resource.setBankName(staffDetailsSheetRow.getCell(41).getStringCellValue().toUpperCase());
						}
					}
					if(null != staffDetailsSheetRow.getCell(42)){
						if(staffDetailsSheetRow.getCell(42).getStringCellValue().length() != 0){
							staffDetailsSheetRow.getCell(42).setCellType(Cell.CELL_TYPE_STRING);
							resource.setBankBranch(staffDetailsSheetRow.getCell(42).getStringCellValue().toUpperCase());
						}
					}
					if(null != staffDetailsSheetRow.getCell(43)){
						if(staffDetailsSheetRow.getCell(43).getStringCellValue().length() != 0){
							staffDetailsSheetRow.getCell(43).setCellType(Cell.CELL_TYPE_STRING);
							resource.setAccountType(staffDetailsSheetRow.getCell(43).getStringCellValue().toUpperCase());
						}
					}
					if(null != staffDetailsSheetRow.getCell(44)){
						if(staffDetailsSheetRow.getCell(44).getStringCellValue().length() != 0){
							staffDetailsSheetRow.getCell(44).setCellType(Cell.CELL_TYPE_STRING);
							resource.setAccountNumber(staffDetailsSheetRow.getCell(44).getStringCellValue().toUpperCase());
						}
					}
					if(null != staffDetailsSheetRow.getCell(45)){
						if(staffDetailsSheetRow.getCell(45).getStringCellValue().length() != 0){
							staffDetailsSheetRow.getCell(45).setCellType(Cell.CELL_TYPE_STRING);
							resource.setAccountHolderName(staffDetailsSheetRow.getCell(45).getStringCellValue().toUpperCase());
						}
					}
					if(null != staffDetailsSheetRow.getCell(46)){
						if(staffDetailsSheetRow.getCell(46).getStringCellValue().length() != 0){
							staffDetailsSheetRow.getCell(46).setCellType(Cell.CELL_TYPE_STRING);
							employee.setQualificationSummary(staffDetailsSheetRow.getCell(46).getStringCellValue());
						}
					}
	
					employee.setResource(resource);
					employeeDetailsList.add(employee);
				}
			}
		}catch(Exception e){
			logger.error(e);
		}
		return 	employeeDetailsList;
	}
	
	
	public List<String> readExcelFileForCityList(String excelFile) {
		List<String> cityList = null;
		HashSet<String> hs = new HashSet<String>();
		try{
			FileInputStream file = new FileInputStream(new File(excelFile));
		     
			//Get the workbook instance for XLS file
			HSSFWorkbook workbook = new HSSFWorkbook(file);			
	
			//Get first sheet from the workbook
			HSSFSheet cityNameSheet = workbook.getSheetAt(1);
			for (int i = 2; i <= cityNameSheet.getLastRowNum(); i++) {
				HSSFRow cityNameSheetRow = cityNameSheet.getRow(i);
				
				if(null != cityNameSheetRow.getCell(4)){
					if(cityNameSheetRow.getCell(4).getStringCellValue().length() != 0){
						cityNameSheetRow.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
						hs.add(cityNameSheetRow.getCell(4).getStringCellValue().toUpperCase());
					}						
				}
				if(null != cityNameSheetRow.getCell(15)){
					if(cityNameSheetRow.getCell(15).getStringCellValue().length() != 0){
						cityNameSheetRow.getCell(15).setCellType(Cell.CELL_TYPE_STRING);
						hs.add(cityNameSheetRow.getCell(15).getStringCellValue().toUpperCase());
					}						
				}
			}				
			if(hs.size() >= 1){
				cityList = new ArrayList<String>();
				cityList.addAll(hs);
				return cityList;
			}else{
				return null;
			}
		}catch(Exception e) {
			logger.error(e);
		}
		return null;
	}
	
	
	public List<String> readExcelFileForDistrictList(String excelFile) {
		List<String> districtList = null;
		HashSet<String> hs = new HashSet<String>();
		try{
			FileInputStream file = new FileInputStream(new File(excelFile));
		     
			//Get the workbook instance for XLS file
			HSSFWorkbook workbook = new HSSFWorkbook(file);			
	
			//Get first sheet from the workbook
			HSSFSheet districtNameSheet = workbook.getSheetAt(1);
			for (int i = 2; i <= districtNameSheet.getLastRowNum(); i++) {
				HSSFRow districtNameSheetRow = districtNameSheet.getRow(i);
				
				if(null != districtNameSheetRow.getCell(8)){
					if(districtNameSheetRow.getCell(8).getStringCellValue().length() != 0){
						districtNameSheetRow.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
						hs.add(districtNameSheetRow.getCell(8).getStringCellValue().toUpperCase());
					}						
				}
				if(null != districtNameSheetRow.getCell(19)){
					if(districtNameSheetRow.getCell(19).getStringCellValue().length() != 0){
						districtNameSheetRow.getCell(19).setCellType(Cell.CELL_TYPE_STRING);
						hs.add(districtNameSheetRow.getCell(19).getStringCellValue().toUpperCase());
					}						
				}
			}				
			if(hs.size() >= 1){
				districtList = new ArrayList<String>();
				districtList.addAll(hs);
				return districtList;
			}else{
				return null;
			}
		}catch(Exception e) {
			logger.error(e);
		}
		return null;
	}
	
	
	public List<Address> readExcelFileForStaffAddressDetails(String excelFile) {
		List<Address> addressDetailsList = new ArrayList<Address>();
		try{
			FileInputStream file = new FileInputStream(new File(excelFile));
     
			HSSFWorkbook workbook = new HSSFWorkbook(file);	 
			HSSFSheet resourceAddressDetailsSheet = workbook.getSheetAt(1);
			
			if(resourceAddressDetailsSheet != null){
				for (int i = 2; i <= resourceAddressDetailsSheet.getLastRowNum(); i++) {
					HSSFRow resourceAddressDetailsSheetRow = resourceAddressDetailsSheet.getRow(i); 
					Address address = new Address();					
					
					if(null != resourceAddressDetailsSheetRow.getCell(0)){
						if(resourceAddressDetailsSheetRow.getCell(0).getStringCellValue().length() != 0){
							resourceAddressDetailsSheetRow.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
							address.setUserId(resourceAddressDetailsSheetRow.getCell(0).getStringCellValue().toUpperCase());
						}
					}					
					if(null != resourceAddressDetailsSheetRow.getCell(1)){
						if(resourceAddressDetailsSheetRow.getCell(1).getStringCellValue().length() != 0){
							resourceAddressDetailsSheetRow.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
							address.setPresentAddressLine(resourceAddressDetailsSheetRow.getCell(1).getStringCellValue());	
						}				
					}
					if(null != resourceAddressDetailsSheetRow.getCell(2)){
						if(resourceAddressDetailsSheetRow.getCell(2).getStringCellValue().length() != 0){
							resourceAddressDetailsSheetRow.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
							address.setPresentAddressRailwayStation(resourceAddressDetailsSheetRow.getCell(2).getStringCellValue());
						}
					}
					if(null != resourceAddressDetailsSheetRow.getCell(3)){
						if(resourceAddressDetailsSheetRow.getCell(3).getStringCellValue().length() != 0){
							resourceAddressDetailsSheetRow.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
							address.setPresentAddressLandmark(resourceAddressDetailsSheetRow.getCell(3).getStringCellValue());
						}
					}
					if(null != resourceAddressDetailsSheetRow.getCell(4)){
						if(resourceAddressDetailsSheetRow.getCell(4).getStringCellValue().length() != 0){
							resourceAddressDetailsSheetRow.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
							address.setPresentAddressCityVillage(resourceAddressDetailsSheetRow.getCell(4).getStringCellValue().toUpperCase());
						}
					}
					if(null != resourceAddressDetailsSheetRow.getCell(5)){
						if(resourceAddressDetailsSheetRow.getCell(5).getStringCellValue().length() != 0){
							resourceAddressDetailsSheetRow.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
							address.setPresentAddressPostOffice(resourceAddressDetailsSheetRow.getCell(5).getStringCellValue());
						}
					}
					if(null != resourceAddressDetailsSheetRow.getCell(6)){
						if(resourceAddressDetailsSheetRow.getCell(6).getStringCellValue().length() != 0){
							resourceAddressDetailsSheetRow.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
							address.setPresentAddressPoliceStation(resourceAddressDetailsSheetRow.getCell(6).getStringCellValue());	
						}				
					}
					if(null != resourceAddressDetailsSheetRow.getCell(7)){
						if(resourceAddressDetailsSheetRow.getCell(7).getStringCellValue().length() != 0){
							resourceAddressDetailsSheetRow.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
							address.setPresentAddressPinCode(resourceAddressDetailsSheetRow.getCell(7).getStringCellValue());
						}
					}
					if(null != resourceAddressDetailsSheetRow.getCell(8)){
						if(resourceAddressDetailsSheetRow.getCell(8).getStringCellValue().length() != 0){
							resourceAddressDetailsSheetRow.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
							address.setPresentAddressDistrict(resourceAddressDetailsSheetRow.getCell(8).getStringCellValue().toUpperCase());
						}
					}
					if(null != resourceAddressDetailsSheetRow.getCell(9)){
						if(resourceAddressDetailsSheetRow.getCell(9).getStringCellValue().length() != 0){
							resourceAddressDetailsSheetRow.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
							address.setPresentAddressCountry(resourceAddressDetailsSheetRow.getCell(9).getStringCellValue().toUpperCase());
						}
					}
					if(null != resourceAddressDetailsSheetRow.getCell(10)){
						if(resourceAddressDetailsSheetRow.getCell(10).getStringCellValue().length() != 0){
							resourceAddressDetailsSheetRow.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
							address.setPresentAddressState(resourceAddressDetailsSheetRow.getCell(10).getStringCellValue().toUpperCase());	
						}				
					}					
					if(null != resourceAddressDetailsSheetRow.getCell(11)){
						if(resourceAddressDetailsSheetRow.getCell(11).getStringCellValue().length() != 0){
							resourceAddressDetailsSheetRow.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
							address.setPresentAddressPhone(resourceAddressDetailsSheetRow.getCell(11).getStringCellValue());
						}
					}
					if(null != resourceAddressDetailsSheetRow.getCell(12)){
						if(resourceAddressDetailsSheetRow.getCell(12).getStringCellValue().length() != 0){
							resourceAddressDetailsSheetRow.getCell(12).setCellType(Cell.CELL_TYPE_STRING);
							address.setPermanentAddressLine(resourceAddressDetailsSheetRow.getCell(12).getStringCellValue());
						}
					}
					if(null != resourceAddressDetailsSheetRow.getCell(13)){
						if(resourceAddressDetailsSheetRow.getCell(13).getStringCellValue().length() != 0){
							resourceAddressDetailsSheetRow.getCell(13).setCellType(Cell.CELL_TYPE_STRING);
							address.setPermanentAddressRailwayStation(resourceAddressDetailsSheetRow.getCell(13).getStringCellValue());
						}
					}				
					if(null != resourceAddressDetailsSheetRow.getCell(14)){
						if(resourceAddressDetailsSheetRow.getCell(14).getStringCellValue().length() != 0){
							resourceAddressDetailsSheetRow.getCell(14).setCellType(Cell.CELL_TYPE_STRING);
							address.setPermanentAddressLandmark(resourceAddressDetailsSheetRow.getCell(14).getStringCellValue());
						}
					}
					if(null != resourceAddressDetailsSheetRow.getCell(15)){
						if(resourceAddressDetailsSheetRow.getCell(15).getStringCellValue().length() != 0){
							resourceAddressDetailsSheetRow.getCell(15).setCellType(Cell.CELL_TYPE_STRING);
							address.setPermanentAddressCityVillage(resourceAddressDetailsSheetRow.getCell(15).getStringCellValue().toUpperCase());
						}
					}
					if(null != resourceAddressDetailsSheetRow.getCell(16)){
						if(resourceAddressDetailsSheetRow.getCell(16).getStringCellValue().length() != 0){
							resourceAddressDetailsSheetRow.getCell(16).setCellType(Cell.CELL_TYPE_STRING);
							address.setPermanentAddressPostOffice(resourceAddressDetailsSheetRow.getCell(16).getStringCellValue());
						}			
					}
					if(null != resourceAddressDetailsSheetRow.getCell(17)){
						if(resourceAddressDetailsSheetRow.getCell(17).getStringCellValue().length() != 0){
							resourceAddressDetailsSheetRow.getCell(17).setCellType(Cell.CELL_TYPE_STRING);
							address.setPermanentAddressPoliceStation(resourceAddressDetailsSheetRow.getCell(17).getStringCellValue());
						}
					}
					if(null != resourceAddressDetailsSheetRow.getCell(18)){
						if(resourceAddressDetailsSheetRow.getCell(18).getStringCellValue().length() != 0){
							resourceAddressDetailsSheetRow.getCell(18).setCellType(Cell.CELL_TYPE_STRING);
							address.setPermanentAddressPinCode(resourceAddressDetailsSheetRow.getCell(18).getStringCellValue());
						}
					}
					if(null != resourceAddressDetailsSheetRow.getCell(19)){	
						if(resourceAddressDetailsSheetRow.getCell(19).getStringCellValue().length() != 0){	
							resourceAddressDetailsSheetRow.getCell(19).setCellType(Cell.CELL_TYPE_STRING);
							address.setPermanentAddressDistrict(resourceAddressDetailsSheetRow.getCell(19).getStringCellValue().toUpperCase());
						}
					}
					if(null != resourceAddressDetailsSheetRow.getCell(20)){
						if(resourceAddressDetailsSheetRow.getCell(20).getStringCellValue().length() != 0){
							resourceAddressDetailsSheetRow.getCell(20).setCellType(Cell.CELL_TYPE_STRING);
							address.setPermanentAddressCountry(resourceAddressDetailsSheetRow.getCell(20).getStringCellValue().toUpperCase());	
						}
					}
					if(null != resourceAddressDetailsSheetRow.getCell(21)){
						if(resourceAddressDetailsSheetRow.getCell(21).getStringCellValue().length() != 0){
							resourceAddressDetailsSheetRow.getCell(21).setCellType(Cell.CELL_TYPE_STRING);
							address.setPermanentAddressState(resourceAddressDetailsSheetRow.getCell(21).getStringCellValue().toUpperCase());
						}	
					}
					if(null != resourceAddressDetailsSheetRow.getCell(22)){
						if(resourceAddressDetailsSheetRow.getCell(22).getStringCellValue().length() != 0){
							resourceAddressDetailsSheetRow.getCell(22).setCellType(Cell.CELL_TYPE_STRING);
							address.setPermanentAddressPhone(resourceAddressDetailsSheetRow.getCell(22).getStringCellValue());
						}
					}
					addressDetailsList.add(address);
				} 
			}
	
		}catch(Exception e){
			logger.error(e);
		}
		return addressDetailsList;
	}
		
	
	public List<Qualification> readExcelFileForStaffQualificationDetails(String excelFile) {
		List<Qualification> qualificationDetailsList = new ArrayList<Qualification>();
		try{
			FileInputStream file = new FileInputStream(new File(excelFile));
     
			//Get the workbook instance for XLS file
			HSSFWorkbook workbook = new HSSFWorkbook(file);			
 			HSSFSheet resourceQualificationDetailsSheet = workbook.getSheetAt(2);
 			if(resourceQualificationDetailsSheet != null){
 				for (int i = 1; i <= resourceQualificationDetailsSheet.getLastRowNum(); i++) {
 					HSSFRow resourceQualificationDetailsSheetRow = resourceQualificationDetailsSheet.getRow(i); 
 					Qualification qualification = new Qualification();					
 					
 					if(null != resourceQualificationDetailsSheetRow.getCell(0)){
 						if(resourceQualificationDetailsSheetRow.getCell(0).getStringCellValue().length() != 0){
 							resourceQualificationDetailsSheetRow.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
 							qualification.setUserId(resourceQualificationDetailsSheetRow.getCell(0).getStringCellValue().toUpperCase());
 						}
 					}
 					if(null != resourceQualificationDetailsSheetRow.getCell(1)){
 						if(resourceQualificationDetailsSheetRow.getCell(1).getStringCellValue().length() != 0){
 							resourceQualificationDetailsSheetRow.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
 							qualification.setQualificationName(resourceQualificationDetailsSheetRow.getCell(1).getStringCellValue().toUpperCase());	
 						}			
 					}
 					if(null != resourceQualificationDetailsSheetRow.getCell(2)){
 						if(resourceQualificationDetailsSheetRow.getCell(2).getStringCellValue().length() != 0){
 							resourceQualificationDetailsSheetRow.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
 							qualification.setSpecialization(resourceQualificationDetailsSheetRow.getCell(2).getStringCellValue().toUpperCase());
 						}
 					}
 					if(null != resourceQualificationDetailsSheetRow.getCell(3)){
 						if(resourceQualificationDetailsSheetRow.getCell(3).getStringCellValue().length() != 0){
 							resourceQualificationDetailsSheetRow.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
 							qualification.setSchoolCollege(resourceQualificationDetailsSheetRow.getCell(3).getStringCellValue().toUpperCase());
 						}
 					}					
 					if(null != resourceQualificationDetailsSheetRow.getCell(4)){
 						if(resourceQualificationDetailsSheetRow.getCell(4).getStringCellValue().length() != 0){
 							resourceQualificationDetailsSheetRow.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
 							qualification.setBoardUniversity(resourceQualificationDetailsSheetRow.getCell(4).getStringCellValue().toUpperCase());
 						}
 					}
 					if(null != resourceQualificationDetailsSheetRow.getCell(5)){
 						if(resourceQualificationDetailsSheetRow.getCell(5).getStringCellValue().length() != 0){
 							resourceQualificationDetailsSheetRow.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
 							qualification.setMarks(resourceQualificationDetailsSheetRow.getCell(5).getStringCellValue());
 						}
 					}					
 					if(null != resourceQualificationDetailsSheetRow.getCell(6)){
 						if(resourceQualificationDetailsSheetRow.getCell(6).getStringCellValue().length() != 0){
 							resourceQualificationDetailsSheetRow.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
 							qualification.setPassingYear(resourceQualificationDetailsSheetRow.getCell(6).getStringCellValue());	
 						}
 					}					
 					if(null != resourceQualificationDetailsSheetRow.getCell(7)){
 						if(resourceQualificationDetailsSheetRow.getCell(7).getStringCellValue().length() != 0){
 							resourceQualificationDetailsSheetRow.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
 							qualification.setQualificationType(resourceQualificationDetailsSheetRow.getCell(7).getStringCellValue());
 						}
 					}
 					qualificationDetailsList.add(qualification);
 				}	
 			}
 				     
		}catch(Exception e){
			logger.error(e);
		}
		return qualificationDetailsList;
	}
	
	
	public List<Organization> readExcelFileForStaffPreviousOrganizationDetails(String excelFile) {
		List<Organization> organizationDetailsList = new ArrayList<Organization>();
		try{
			FileInputStream file = new FileInputStream(new File(excelFile));
     
			//Get the workbook instance for XLS file
			HSSFWorkbook workbook = new HSSFWorkbook(file);		
			HSSFSheet resourceOrganizationDetailsSheet = workbook.getSheetAt(3);
			if(resourceOrganizationDetailsSheet != null){
				for (int i = 1; i <= resourceOrganizationDetailsSheet.getLastRowNum(); i++) {
					HSSFRow resourceOrganizationDetailsSheetRow = resourceOrganizationDetailsSheet.getRow(i); 
					Organization org = new Organization();				
						
					if(null != resourceOrganizationDetailsSheetRow.getCell(0)){
						if(resourceOrganizationDetailsSheetRow.getCell(0).getStringCellValue().length() != 0){
							resourceOrganizationDetailsSheetRow.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
							org.setUserId(resourceOrganizationDetailsSheetRow.getCell(0).getStringCellValue().toUpperCase());
						}
					}
					if(null != resourceOrganizationDetailsSheetRow.getCell(1)){
						if(resourceOrganizationDetailsSheetRow.getCell(1).getStringCellValue().length() != 0){
							resourceOrganizationDetailsSheetRow.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
							org.setOrganizationName(resourceOrganizationDetailsSheetRow.getCell(1).getStringCellValue().toUpperCase());
						}			
					}
					if(null != resourceOrganizationDetailsSheetRow.getCell(2)){
						if(resourceOrganizationDetailsSheetRow.getCell(2).getStringCellValue().length() != 0){
							resourceOrganizationDetailsSheetRow.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
							org.setFromDate(resourceOrganizationDetailsSheetRow.getCell(2).getStringCellValue());
						}
					}
					if(null != resourceOrganizationDetailsSheetRow.getCell(3)){
						if(resourceOrganizationDetailsSheetRow.getCell(3).getStringCellValue().length() != 0){
							resourceOrganizationDetailsSheetRow.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
							org.setToDate(resourceOrganizationDetailsSheetRow.getCell(3).getStringCellValue());
						}
					}
					if(null != resourceOrganizationDetailsSheetRow.getCell(4)){
						if(resourceOrganizationDetailsSheetRow.getCell(4).getStringCellValue().length() != 0){
							resourceOrganizationDetailsSheetRow.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
							org.setOrganizationContactNo(resourceOrganizationDetailsSheetRow.getCell(4).getStringCellValue());
						}
					}
					if(null != resourceOrganizationDetailsSheetRow.getCell(5)){
						if(resourceOrganizationDetailsSheetRow.getCell(5).getStringCellValue().length() != 0){
							resourceOrganizationDetailsSheetRow.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
							org.setOrganizationWebSite(resourceOrganizationDetailsSheetRow.getCell(5).getStringCellValue());
						}														
					}
					organizationDetailsList.add(org);
				}	
			}
				     
		}catch(Exception e){
			logger.error(e);
		}
		return organizationDetailsList;
	}
	
	
	public List<Publication> readExcelFileForStaffPublicationDetails(String excelFile) {
		List<Publication> publicationDetailsList = new ArrayList<Publication>();
		try{
			FileInputStream file = new FileInputStream(new File(excelFile));
     
			//Get the workbook instance for XLS file
			HSSFWorkbook workbook = new HSSFWorkbook(file);		
			HSSFSheet resourcePublicationDetailsSheet = workbook.getSheetAt(4);
			if(resourcePublicationDetailsSheet != null){
				for (int i = 1; i <= resourcePublicationDetailsSheet.getLastRowNum(); i++) {
					HSSFRow resourcePublicationDetailsSheetRow = resourcePublicationDetailsSheet.getRow(i); 
					Publication pub = new Publication();		
						
					if(null != resourcePublicationDetailsSheetRow.getCell(0)){
						if(resourcePublicationDetailsSheetRow.getCell(0).getStringCellValue().length() != 0){
							resourcePublicationDetailsSheetRow.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
							pub.setUserId(resourcePublicationDetailsSheetRow.getCell(0).getStringCellValue().toUpperCase());	
						}
					}
					if(null != resourcePublicationDetailsSheetRow.getCell(1)){
						if(resourcePublicationDetailsSheetRow.getCell(1).getStringCellValue().length() != 0){
							resourcePublicationDetailsSheetRow.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
							pub.setPublicationName(resourcePublicationDetailsSheetRow.getCell(1).getStringCellValue().toUpperCase());	
						}					
					}
					if(null != resourcePublicationDetailsSheetRow.getCell(2)){
						if(resourcePublicationDetailsSheetRow.getCell(2).getStringCellValue().length() != 0){
							resourcePublicationDetailsSheetRow.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
							pub.setDateOfPublication(resourcePublicationDetailsSheetRow.getCell(2).getStringCellValue());	
						}
					}
					if(null != resourcePublicationDetailsSheetRow.getCell(3)){
						if(resourcePublicationDetailsSheetRow.getCell(3).getStringCellValue().length() != 0){
							resourcePublicationDetailsSheetRow.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
							pub.setCoPublisher(resourcePublicationDetailsSheetRow.getCell(3).getStringCellValue().toUpperCase());	
						}
					}
					if(null != resourcePublicationDetailsSheetRow.getCell(4)){
						if(resourcePublicationDetailsSheetRow.getCell(4).getStringCellValue().length() != 0){
							resourcePublicationDetailsSheetRow.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
							pub.setPublicationDesc(resourcePublicationDetailsSheetRow.getCell(4).getStringCellValue());	
						}
					}
					publicationDetailsList.add(pub);
				}	
			}				     
		}catch(Exception e){
			logger.error(e);
		}
		return publicationDetailsList;
	}
	
	
	public List<EmployeeDependent> readExcelFileForEmployeeDependentDetails(String excelFile) {
		List<EmployeeDependent> employeeDependentList = new ArrayList<EmployeeDependent>();
		try{
			FileInputStream file = new FileInputStream(new File(excelFile));
     
			//Get the workbook instance for XLS file
			HSSFWorkbook workbook = new HSSFWorkbook(file);		
			HSSFSheet resourceDependantDetailsSheet = workbook.getSheetAt(5);
			if(resourceDependantDetailsSheet != null){
				for (int i = 1; i <= resourceDependantDetailsSheet.getLastRowNum(); i++) {
					HSSFRow resourceDependantDetailsSheetRow = resourceDependantDetailsSheet.getRow(i); 
					EmployeeDependent employDepentdant = new EmployeeDependent();		
						
					if(null != resourceDependantDetailsSheetRow.getCell(0)){
						if(resourceDependantDetailsSheetRow.getCell(0).getStringCellValue().length() != 0){
							resourceDependantDetailsSheetRow.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
							employDepentdant.setUserId(resourceDependantDetailsSheetRow.getCell(0).getStringCellValue().toUpperCase());
						}
					}
					if(null != resourceDependantDetailsSheetRow.getCell(1)){
						if(resourceDependantDetailsSheetRow.getCell(1).getStringCellValue().length() != 0){
							resourceDependantDetailsSheetRow.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
							employDepentdant.setChildName(resourceDependantDetailsSheetRow.getCell(1).getStringCellValue().toUpperCase());	
						}				
					}
					if(null != resourceDependantDetailsSheetRow.getCell(2)){
						if(resourceDependantDetailsSheetRow.getCell(2).getStringCellValue().length() != 0){
							resourceDependantDetailsSheetRow.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
							employDepentdant.setChildGender(resourceDependantDetailsSheetRow.getCell(2).getStringCellValue());
						}
					}
					if(null != resourceDependantDetailsSheetRow.getCell(3)){
						if(resourceDependantDetailsSheetRow.getCell(3).getStringCellValue().length() != 0){
							resourceDependantDetailsSheetRow.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
							employDepentdant.setChildDOB(resourceDependantDetailsSheetRow.getCell(3).getStringCellValue());
						}
					}
					employeeDependentList.add(employDepentdant);
				}
			}
					     
		}catch(Exception e){
			logger.error(e);
		}
		return employeeDependentList;
	}
	
	



public List<String> readExcelFileForStudentCityList(String excelFile) {
	List<String> cityList = null;
	HashSet<String> hs = new HashSet<String>();
	try{
		FileInputStream file = new FileInputStream(new File(excelFile));
	     
		//Get the workbook instance for XLS file
		HSSFWorkbook workbook = new HSSFWorkbook(file);			

		//Get first sheet from the workbook
		HSSFSheet cityNameSheet = workbook.getSheetAt(1);
		for (int i = 2; i <= cityNameSheet.getLastRowNum(); i++) {
			HSSFRow cityNameSheetRow = cityNameSheet.getRow(i);
			
			if(null != cityNameSheetRow.getCell(3)){
				if(cityNameSheetRow.getCell(3).getStringCellValue().length() != 0){
					cityNameSheetRow.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
					hs.add(cityNameSheetRow.getCell(3).getStringCellValue().toUpperCase());
				}						
			}
			if(null != cityNameSheetRow.getCell(14)){
				if(cityNameSheetRow.getCell(14).getStringCellValue().length() != 0){
					cityNameSheetRow.getCell(14).setCellType(Cell.CELL_TYPE_STRING);
					hs.add(cityNameSheetRow.getCell(14).getStringCellValue().toUpperCase());
				}						
			}
		}				
		if(hs.size() >= 1){
			cityList = new ArrayList<String>();
			cityList.addAll(hs);
			return cityList;
		}else{
			return null;
		}
	}catch(Exception e) {
		logger.error(e);
	}
	return null;
}


public List<String> readExcelFileForStudentDistrictList(String excelFile) {
	List<String> districtList = null;
	HashSet<String> hs = new HashSet<String>();
	try{
		FileInputStream file = new FileInputStream(new File(excelFile));
	     
		//Get the workbook instance for XLS file
		HSSFWorkbook workbook = new HSSFWorkbook(file);			

		//Get first sheet from the workbook
		HSSFSheet districtNameSheet = workbook.getSheetAt(1);
		for (int i = 2; i <= districtNameSheet.getLastRowNum(); i++) {
			HSSFRow districtNameSheetRow = districtNameSheet.getRow(i);
			
			if(null != districtNameSheetRow.getCell(7)){
				if(districtNameSheetRow.getCell(7).getStringCellValue().length() != 0){
					districtNameSheetRow.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
					hs.add(districtNameSheetRow.getCell(7).getStringCellValue().toUpperCase());
				}						
			}
			if(null != districtNameSheetRow.getCell(18)){
				if(districtNameSheetRow.getCell(18).getStringCellValue().length() != 0){
					districtNameSheetRow.getCell(18).setCellType(Cell.CELL_TYPE_STRING);
					hs.add(districtNameSheetRow.getCell(18).getStringCellValue().toUpperCase());
				}						
			}
		}				
		if(hs.size() >= 1){
			districtList = new ArrayList<String>();
			districtList.addAll(hs);
			return districtList;
		}else{
			return null;
		}
	}catch(Exception e) {
		logger.error(e);
	}
	return null;
}



public List<Address> readExcelFileForStudentAddressDetails(String excelFile) {
	List<Address> addressDetailsList = new ArrayList<Address>();
	try{
		FileInputStream file = new FileInputStream(new File(excelFile));
 
		HSSFWorkbook workbook = new HSSFWorkbook(file);	 
		HSSFSheet resourceAddressDetailsSheet = workbook.getSheetAt(1);
		
		for (int i = 2; i <= resourceAddressDetailsSheet.getLastRowNum(); i++) {
			HSSFRow resourceAddressDetailsSheetRow = resourceAddressDetailsSheet.getRow(i); 
			Address address = new Address();					
			
			if(null != resourceAddressDetailsSheetRow.getCell(0)){
				if(resourceAddressDetailsSheetRow.getCell(0).getStringCellValue().length() != 0){
					resourceAddressDetailsSheetRow.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
					address.setRollNumber(Integer.parseInt(resourceAddressDetailsSheetRow.getCell(0).getStringCellValue()));
				}
			}					
			if(null != resourceAddressDetailsSheetRow.getCell(1)){
				if(resourceAddressDetailsSheetRow.getCell(1).getStringCellValue().length() != 0){
					resourceAddressDetailsSheetRow.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
					address.setPresentAddressLine(resourceAddressDetailsSheetRow.getCell(1).getStringCellValue());	
				}				
			}
			if(null != resourceAddressDetailsSheetRow.getCell(2)){
				if(resourceAddressDetailsSheetRow.getCell(2).getStringCellValue().length() != 0){
					resourceAddressDetailsSheetRow.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
					address.setPresentAddressLandmark(resourceAddressDetailsSheetRow.getCell(2).getStringCellValue());
				}
			}
			if(null != resourceAddressDetailsSheetRow.getCell(3)){
				if(resourceAddressDetailsSheetRow.getCell(3).getStringCellValue().length() != 0){
					resourceAddressDetailsSheetRow.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
					address.setPresentAddressCityVillage(resourceAddressDetailsSheetRow.getCell(3).getStringCellValue().toUpperCase());
				}
			}
			if(null != resourceAddressDetailsSheetRow.getCell(4)){
				if(resourceAddressDetailsSheetRow.getCell(4).getStringCellValue().length() != 0){
					resourceAddressDetailsSheetRow.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
					address.setPresentAddressPostOffice(resourceAddressDetailsSheetRow.getCell(4).getStringCellValue());
				}
			}
			if(null != resourceAddressDetailsSheetRow.getCell(5)){
				if(resourceAddressDetailsSheetRow.getCell(5).getStringCellValue().length() != 0){
					resourceAddressDetailsSheetRow.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
					address.setPresentAddressPoliceStation(resourceAddressDetailsSheetRow.getCell(5).getStringCellValue());	
				}				
			}
			if(null != resourceAddressDetailsSheetRow.getCell(6)){
				if(resourceAddressDetailsSheetRow.getCell(6).getStringCellValue().length() != 0){
					resourceAddressDetailsSheetRow.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
					address.setPresentAddressPinCode(resourceAddressDetailsSheetRow.getCell(6).getStringCellValue());
				}
			}
			if(null != resourceAddressDetailsSheetRow.getCell(7)){
				if(resourceAddressDetailsSheetRow.getCell(7).getStringCellValue().length() != 0){
					resourceAddressDetailsSheetRow.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
					address.setPresentAddressDistrict(resourceAddressDetailsSheetRow.getCell(7).getStringCellValue().toUpperCase());
				}
			}
			if(null != resourceAddressDetailsSheetRow.getCell(8)){
				if(resourceAddressDetailsSheetRow.getCell(8).getStringCellValue().length() != 0){
					resourceAddressDetailsSheetRow.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
					address.setPresentAddressState(resourceAddressDetailsSheetRow.getCell(8).getStringCellValue().toUpperCase());	
				}				
			}
			if(null != resourceAddressDetailsSheetRow.getCell(9)){
				if(resourceAddressDetailsSheetRow.getCell(9).getStringCellValue().length() != 0){
					resourceAddressDetailsSheetRow.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
					address.setPresentAddressCountry(resourceAddressDetailsSheetRow.getCell(9).getStringCellValue().toUpperCase());
				}
			}	
			if(null != resourceAddressDetailsSheetRow.getCell(10)){
				if(resourceAddressDetailsSheetRow.getCell(10).getStringCellValue().length() != 0){
					resourceAddressDetailsSheetRow.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
					address.setPresentAddressPhone(resourceAddressDetailsSheetRow.getCell(10).getStringCellValue());
				}
			}				
			if(null != resourceAddressDetailsSheetRow.getCell(11)){
				if(resourceAddressDetailsSheetRow.getCell(11).getStringCellValue().length() != 0){
					resourceAddressDetailsSheetRow.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
					address.setPresentAddressRailwayStation(resourceAddressDetailsSheetRow.getCell(11).getStringCellValue());
				}
			}				
			if(null != resourceAddressDetailsSheetRow.getCell(12)){
				if(resourceAddressDetailsSheetRow.getCell(12).getStringCellValue().length() != 0){
					resourceAddressDetailsSheetRow.getCell(12).setCellType(Cell.CELL_TYPE_STRING);
					address.setPermanentAddressLine(resourceAddressDetailsSheetRow.getCell(12).getStringCellValue());
				}
			}				
			if(null != resourceAddressDetailsSheetRow.getCell(13)){
				if(resourceAddressDetailsSheetRow.getCell(13).getStringCellValue().length() != 0){
					resourceAddressDetailsSheetRow.getCell(13).setCellType(Cell.CELL_TYPE_STRING);
					address.setPermanentAddressLandmark(resourceAddressDetailsSheetRow.getCell(13).getStringCellValue());
				}
			}
			if(null != resourceAddressDetailsSheetRow.getCell(14)){
				if(resourceAddressDetailsSheetRow.getCell(14).getStringCellValue().length() != 0){
					resourceAddressDetailsSheetRow.getCell(14).setCellType(Cell.CELL_TYPE_STRING);
					address.setPermanentAddressCityVillage(resourceAddressDetailsSheetRow.getCell(14).getStringCellValue().toUpperCase());
				}
			}
			if(null != resourceAddressDetailsSheetRow.getCell(15)){
				if(resourceAddressDetailsSheetRow.getCell(15).getStringCellValue().length() != 0){
					resourceAddressDetailsSheetRow.getCell(15).setCellType(Cell.CELL_TYPE_STRING);
					address.setPermanentAddressPostOffice(resourceAddressDetailsSheetRow.getCell(15).getStringCellValue());
				}			
			}
			if(null != resourceAddressDetailsSheetRow.getCell(16)){
				if(resourceAddressDetailsSheetRow.getCell(16).getStringCellValue().length() != 0){
					resourceAddressDetailsSheetRow.getCell(16).setCellType(Cell.CELL_TYPE_STRING);
					address.setPermanentAddressPoliceStation(resourceAddressDetailsSheetRow.getCell(16).getStringCellValue());
				}
			}
			if(null != resourceAddressDetailsSheetRow.getCell(17)){
				if(resourceAddressDetailsSheetRow.getCell(17).getStringCellValue().length() != 0){
					resourceAddressDetailsSheetRow.getCell(17).setCellType(Cell.CELL_TYPE_STRING);
					address.setPermanentAddressPinCode(resourceAddressDetailsSheetRow.getCell(17).getStringCellValue());
				}
			}
			if(null != resourceAddressDetailsSheetRow.getCell(18)){	
				if(resourceAddressDetailsSheetRow.getCell(18).getStringCellValue().length() != 0){	
					resourceAddressDetailsSheetRow.getCell(18).setCellType(Cell.CELL_TYPE_STRING);
					address.setPermanentAddressDistrict(resourceAddressDetailsSheetRow.getCell(18).getStringCellValue().toUpperCase());
				}
			}
			if(null != resourceAddressDetailsSheetRow.getCell(19)){
				if(resourceAddressDetailsSheetRow.getCell(19).getStringCellValue().length() != 0){
					resourceAddressDetailsSheetRow.getCell(19).setCellType(Cell.CELL_TYPE_STRING);
					address.setPermanentAddressState(resourceAddressDetailsSheetRow.getCell(19).getStringCellValue().toUpperCase());
				}	
			}
			if(null != resourceAddressDetailsSheetRow.getCell(20)){
				if(resourceAddressDetailsSheetRow.getCell(20).getStringCellValue().length() != 0){
					resourceAddressDetailsSheetRow.getCell(20).setCellType(Cell.CELL_TYPE_STRING);
					address.setPermanentAddressCountry(resourceAddressDetailsSheetRow.getCell(20).getStringCellValue().toUpperCase());	
				}
			}
			if(null != resourceAddressDetailsSheetRow.getCell(21)){
				if(resourceAddressDetailsSheetRow.getCell(21).getStringCellValue().length() != 0){
					resourceAddressDetailsSheetRow.getCell(21).setCellType(Cell.CELL_TYPE_STRING);
					address.setPermanentAddressPhone(resourceAddressDetailsSheetRow.getCell(21).getStringCellValue());
				}
			}
			if(null != resourceAddressDetailsSheetRow.getCell(22)){
				if(resourceAddressDetailsSheetRow.getCell(22).getStringCellValue().length() != 0){
					resourceAddressDetailsSheetRow.getCell(22).setCellType(Cell.CELL_TYPE_STRING);
					address.setPermanentAddressRailwayStation(resourceAddressDetailsSheetRow.getCell(22).getStringCellValue());
				}
			}				
			if(null != resourceAddressDetailsSheetRow.getCell(23)){
				if(resourceAddressDetailsSheetRow.getCell(23).getStringCellValue().length() != 0){
					resourceAddressDetailsSheetRow.getCell(23).setCellType(Cell.CELL_TYPE_STRING);
					address.setGuardianAddressLine(resourceAddressDetailsSheetRow.getCell(23).getStringCellValue());
				}
			}				
			if(null != resourceAddressDetailsSheetRow.getCell(24)){
				if(resourceAddressDetailsSheetRow.getCell(24).getStringCellValue().length() != 0){
					resourceAddressDetailsSheetRow.getCell(24).setCellType(Cell.CELL_TYPE_STRING);
					address.setGuardianAddressLandmark(resourceAddressDetailsSheetRow.getCell(24).getStringCellValue());
				}
			}
			if(null != resourceAddressDetailsSheetRow.getCell(25)){
				if(resourceAddressDetailsSheetRow.getCell(25).getStringCellValue().length() != 0){
					resourceAddressDetailsSheetRow.getCell(25).setCellType(Cell.CELL_TYPE_STRING);
					address.setGuardianAddressCityVillage(resourceAddressDetailsSheetRow.getCell(25).getStringCellValue().toUpperCase());
				}
			}
			if(null != resourceAddressDetailsSheetRow.getCell(26)){
				if(resourceAddressDetailsSheetRow.getCell(26).getStringCellValue().length() != 0){
					resourceAddressDetailsSheetRow.getCell(26).setCellType(Cell.CELL_TYPE_STRING);
					address.setGuardianAddressPostOffice(resourceAddressDetailsSheetRow.getCell(26).getStringCellValue());
				}			
			}
			if(null != resourceAddressDetailsSheetRow.getCell(27)){
				if(resourceAddressDetailsSheetRow.getCell(27).getStringCellValue().length() != 0){
					resourceAddressDetailsSheetRow.getCell(27).setCellType(Cell.CELL_TYPE_STRING);
					address.setGuardianAddressPoliceStation(resourceAddressDetailsSheetRow.getCell(27).getStringCellValue());
				}
			}
			if(null != resourceAddressDetailsSheetRow.getCell(28)){
				if(resourceAddressDetailsSheetRow.getCell(28).getStringCellValue().length() != 0){
					resourceAddressDetailsSheetRow.getCell(28).setCellType(Cell.CELL_TYPE_STRING);
					address.setGuardianAddressPinCode(resourceAddressDetailsSheetRow.getCell(28).getStringCellValue());
				}
			}
			if(null != resourceAddressDetailsSheetRow.getCell(29)){	
				if(resourceAddressDetailsSheetRow.getCell(29).getStringCellValue().length() != 0){	
					resourceAddressDetailsSheetRow.getCell(29).setCellType(Cell.CELL_TYPE_STRING);
					address.setGuardianAddressDistrict(resourceAddressDetailsSheetRow.getCell(29).getStringCellValue().toUpperCase());
				}
			}
			if(null != resourceAddressDetailsSheetRow.getCell(30)){
				if(resourceAddressDetailsSheetRow.getCell(30).getStringCellValue().length() != 0){
					resourceAddressDetailsSheetRow.getCell(30).setCellType(Cell.CELL_TYPE_STRING);
					address.setGuardianAddressState(resourceAddressDetailsSheetRow.getCell(30).getStringCellValue().toUpperCase());
				}	
			}
			if(null != resourceAddressDetailsSheetRow.getCell(31)){
				if(resourceAddressDetailsSheetRow.getCell(31).getStringCellValue().length() != 0){
					resourceAddressDetailsSheetRow.getCell(31).setCellType(Cell.CELL_TYPE_STRING);
					address.setGuardianAddressCountry(resourceAddressDetailsSheetRow.getCell(31).getStringCellValue().toUpperCase());	
				}
			}
			if(null != resourceAddressDetailsSheetRow.getCell(32)){
				if(resourceAddressDetailsSheetRow.getCell(32).getStringCellValue().length() != 0){
					resourceAddressDetailsSheetRow.getCell(32).setCellType(Cell.CELL_TYPE_STRING);
					address.setGuardianAddressPhone(resourceAddressDetailsSheetRow.getCell(32).getStringCellValue());
				}
			}
			if(null != resourceAddressDetailsSheetRow.getCell(33)){
				if(resourceAddressDetailsSheetRow.getCell(33).getStringCellValue().length() != 0){
					resourceAddressDetailsSheetRow.getCell(33).setCellType(Cell.CELL_TYPE_STRING);
					address.setGuardianAddressRailwayStation(resourceAddressDetailsSheetRow.getCell(33).getStringCellValue());
				}
			}
			addressDetailsList.add(address);
		} 
	}catch(Exception e){
		logger.error(e);
	}
	return addressDetailsList;
}

public List<Student> readExcelFileForStudentDetails(String excelFile) {
	List<Student> studentDetailsList = new ArrayList<Student>();
	try{
		FileInputStream file = new FileInputStream(new File(excelFile));    
		HSSFWorkbook workbook = new HSSFWorkbook(file);
		HSSFSheet studentDetailsSheet = workbook.getSheetAt(0);
		logger.info("@@ studentDetailsSheet :: "+studentDetailsSheet.getLastRowNum());
		
		for (int i = 1; i <= studentDetailsSheet.getLastRowNum(); i++) {
			HSSFRow studentDetailsSheetRow = studentDetailsSheet.getRow(i); 
			Student student = new Student();
			Resource resource = new Resource();
			
			if(null != studentDetailsSheetRow.getCell(0)){
				
				if(studentDetailsSheetRow.getCell(0).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
					student.setRollNumber(Integer.parseInt(studentDetailsSheetRow.getCell(0).getStringCellValue().toUpperCase()));
				}
			}
			if(null != studentDetailsSheetRow.getCell(1)){
				if(studentDetailsSheetRow.getCell(1).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
					student.setStandard(studentDetailsSheetRow.getCell(1).getStringCellValue().toUpperCase());
				}
			}
			if(null != studentDetailsSheetRow.getCell(2)){
				if(studentDetailsSheetRow.getCell(2).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
					student.setSection(studentDetailsSheetRow.getCell(2).getStringCellValue().toUpperCase());
				}
			}
			if(null != studentDetailsSheetRow.getCell(3)){
				if(studentDetailsSheetRow.getCell(3).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
					resource.setFirstName(studentDetailsSheetRow.getCell(3).getStringCellValue().toUpperCase());
				}
			}				
			if(null != studentDetailsSheetRow.getCell(4)){
				if(studentDetailsSheetRow.getCell(4).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
					resource.setMiddleName(studentDetailsSheetRow.getCell(4).getStringCellValue().toUpperCase());
				}
			}				
			if(null != studentDetailsSheetRow.getCell(5)){
				if(studentDetailsSheetRow.getCell(5).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
					resource.setLastName(studentDetailsSheetRow.getCell(5).getStringCellValue().toUpperCase());
				}
			}
			if(null != studentDetailsSheetRow.getCell(6)){
				if(studentDetailsSheetRow.getCell(6).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
					student.setHouse(studentDetailsSheetRow.getCell(6).getStringCellValue());						
				}
			}
			if(null != studentDetailsSheetRow.getCell(7)){
				if(studentDetailsSheetRow.getCell(7).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
					student.setDateOfAdmission(studentDetailsSheetRow.getCell(7).getStringCellValue());
				}
			}
			if(null != studentDetailsSheetRow.getCell(8)){
				if(studentDetailsSheetRow.getCell(8).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
					student.setStateOfDomicile(studentDetailsSheetRow.getCell(8).getStringCellValue());
				}
			}
			if(null != studentDetailsSheetRow.getCell(9)){
				if(studentDetailsSheetRow.getCell(9).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
					resource.setGender(studentDetailsSheetRow.getCell(9).getStringCellValue());
				}
			}
			if(null != studentDetailsSheetRow.getCell(10)){
				if(studentDetailsSheetRow.getCell(10).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
					resource.setDateOfBirth(studentDetailsSheetRow.getCell(10).getStringCellValue());
				}
			}				
			if(null != studentDetailsSheetRow.getCell(11)){
				if(studentDetailsSheetRow.getCell(11).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
					resource.setBloodGroup(studentDetailsSheetRow.getCell(11).getStringCellValue());
				}
			}				
			if(null != studentDetailsSheetRow.getCell(12)){
				if(studentDetailsSheetRow.getCell(12).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(12).setCellType(Cell.CELL_TYPE_STRING);
					resource.setMotherTongue(studentDetailsSheetRow.getCell(12).getStringCellValue().toUpperCase());
				}
			}				
			if(null != studentDetailsSheetRow.getCell(13)){
				if(studentDetailsSheetRow.getCell(13).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(13).setCellType(Cell.CELL_TYPE_STRING);
					resource.setCategory(studentDetailsSheetRow.getCell(13).getStringCellValue());
				}
			}				
			if(null != studentDetailsSheetRow.getCell(14)){
				if(studentDetailsSheetRow.getCell(14).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(14).setCellType(Cell.CELL_TYPE_STRING);
					resource.setReligion(studentDetailsSheetRow.getCell(14).getStringCellValue().toUpperCase());
				}
			}				
			if(null != studentDetailsSheetRow.getCell(15)){
				if(studentDetailsSheetRow.getCell(15).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(15).setCellType(Cell.CELL_TYPE_STRING);
					resource.setNationality(studentDetailsSheetRow.getCell(15).getStringCellValue().toUpperCase());
				}
			}				
			if(null != studentDetailsSheetRow.getCell(16)){
				if(studentDetailsSheetRow.getCell(16).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(16).setCellType(Cell.CELL_TYPE_STRING);
					resource.setFatherFirstName(studentDetailsSheetRow.getCell(16).getStringCellValue().toUpperCase());
				}
			}				
			if(null != studentDetailsSheetRow.getCell(17)){
				if(studentDetailsSheetRow.getCell(17).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(17).setCellType(Cell.CELL_TYPE_STRING);
					resource.setFatherMiddleName(studentDetailsSheetRow.getCell(17).getStringCellValue().toUpperCase());
				}
			}				
			if(null != studentDetailsSheetRow.getCell(18)){
				if(studentDetailsSheetRow.getCell(18).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(18).setCellType(Cell.CELL_TYPE_STRING);
					resource.setFatherLastName(studentDetailsSheetRow.getCell(18).getStringCellValue().toUpperCase());
				}
			}
			if(null != studentDetailsSheetRow.getCell(19)){
				if(studentDetailsSheetRow.getCell(19).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(19).setCellType(Cell.CELL_TYPE_STRING);
					student.setFatherIncome(Integer.parseInt(studentDetailsSheetRow.getCell(19).getStringCellValue()));
				}
			}
			if(null != studentDetailsSheetRow.getCell(20)){
				if(studentDetailsSheetRow.getCell(20).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(20).setCellType(Cell.CELL_TYPE_STRING);
					resource.setFatherMobile(studentDetailsSheetRow.getCell(20).getStringCellValue().toUpperCase());
				}
			}
			if(null != studentDetailsSheetRow.getCell(21)){
				if(studentDetailsSheetRow.getCell(21).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(21).setCellType(Cell.CELL_TYPE_STRING);
					resource.setFatherEmail(studentDetailsSheetRow.getCell(21).getStringCellValue().toUpperCase());
				}
			}
			if(null != studentDetailsSheetRow.getCell(22)){
				if(studentDetailsSheetRow.getCell(22).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(22).setCellType(Cell.CELL_TYPE_STRING);
					resource.setMotherFirstName(studentDetailsSheetRow.getCell(22).getStringCellValue().toUpperCase());
				}
			}				
			if(null != studentDetailsSheetRow.getCell(23)){
				if(studentDetailsSheetRow.getCell(23).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(23).setCellType(Cell.CELL_TYPE_STRING);
					resource.setMotherMiddleName(studentDetailsSheetRow.getCell(23).getStringCellValue().toUpperCase());
				}
			}				
			if(null != studentDetailsSheetRow.getCell(24)){
				if(studentDetailsSheetRow.getCell(24).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(24).setCellType(Cell.CELL_TYPE_STRING);
					resource.setMotherLastName(studentDetailsSheetRow.getCell(24).getStringCellValue().toUpperCase());
				}
			}
			if(null != studentDetailsSheetRow.getCell(25)){
				if(studentDetailsSheetRow.getCell(25).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(25).setCellType(Cell.CELL_TYPE_STRING);
					student.setMotherIncome(Integer.parseInt(studentDetailsSheetRow.getCell(25).getStringCellValue()));
				}
			}
			if(null != studentDetailsSheetRow.getCell(26)){
				if(studentDetailsSheetRow.getCell(26).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(26).setCellType(Cell.CELL_TYPE_STRING);
					resource.setMotherMobile(studentDetailsSheetRow.getCell(26).getStringCellValue().toUpperCase());
				}
			}
			if(null != studentDetailsSheetRow.getCell(27)){
				if(studentDetailsSheetRow.getCell(27).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(27).setCellType(Cell.CELL_TYPE_STRING);
					resource.setMotherEmail(studentDetailsSheetRow.getCell(27).getStringCellValue().toUpperCase());
				}
			}
			if(null != studentDetailsSheetRow.getCell(28)){
				if(studentDetailsSheetRow.getCell(28).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(28).setCellType(Cell.CELL_TYPE_STRING);
					resource.setBankName(studentDetailsSheetRow.getCell(28).getStringCellValue().toUpperCase());
				}
			}
			if(null != studentDetailsSheetRow.getCell(29)){
				if(studentDetailsSheetRow.getCell(29).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(29).setCellType(Cell.CELL_TYPE_STRING);
					resource.setBankBranch(studentDetailsSheetRow.getCell(29).getStringCellValue().toUpperCase());
				}
			}
			if(null != studentDetailsSheetRow.getCell(30)){
				if(studentDetailsSheetRow.getCell(30).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(30).setCellType(Cell.CELL_TYPE_STRING);
					resource.setAccountNumber(studentDetailsSheetRow.getCell(30).getStringCellValue().toUpperCase());
				}
			}				
			if(null != studentDetailsSheetRow.getCell(31)){
				if(studentDetailsSheetRow.getCell(31).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(31).setCellType(Cell.CELL_TYPE_STRING);
					resource.setMedicalStatus(studentDetailsSheetRow.getCell(31).getStringCellValue());
				}
			}			
			if(null != studentDetailsSheetRow.getCell(32)){
				if(studentDetailsSheetRow.getCell(32).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(32).setCellType(Cell.CELL_TYPE_STRING);
					student.setScholarship(studentDetailsSheetRow.getCell(32).getStringCellValue());
				}
			}
			if(null != studentDetailsSheetRow.getCell(33)){
				studentDetailsSheetRow.getCell(33).setCellType(Cell.CELL_TYPE_BOOLEAN);
				resource.setFatherInDefence(studentDetailsSheetRow.getCell(33).getBooleanCellValue());
			}
			if(null != studentDetailsSheetRow.getCell(34)){
				if(studentDetailsSheetRow.getCell(34).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(34).setCellType(Cell.CELL_TYPE_STRING);
					resource.setFatherServiceStatus(studentDetailsSheetRow.getCell(34).getStringCellValue());
				}
			}
			if(null != studentDetailsSheetRow.getCell(35)){
				if(studentDetailsSheetRow.getCell(35).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(35).setCellType(Cell.CELL_TYPE_STRING);
					resource.setFatherDefenceCategory(studentDetailsSheetRow.getCell(35).getStringCellValue());
				}
			}
			if(null != studentDetailsSheetRow.getCell(36)){
				if(studentDetailsSheetRow.getCell(36).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(36).setCellType(Cell.CELL_TYPE_STRING);
					resource.setFatherRank(studentDetailsSheetRow.getCell(36).getStringCellValue());
				}
			}
			if(null != studentDetailsSheetRow.getCell(37)){
				if(studentDetailsSheetRow.getCell(37).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(37).setCellType(Cell.CELL_TYPE_STRING);
					student.setStudentIncome(Integer.parseInt(studentDetailsSheetRow.getCell(37).getStringCellValue()));
				}
			}
			if(null != studentDetailsSheetRow.getCell(38)){
				if(studentDetailsSheetRow.getCell(38).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(38).setCellType(Cell.CELL_TYPE_STRING);
					student.setFamilyIncome(Integer.parseInt(studentDetailsSheetRow.getCell(38).getStringCellValue()));
				}
			}
			if(null != studentDetailsSheetRow.getCell(39)){
				if(studentDetailsSheetRow.getCell(39).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(39).setCellType(Cell.CELL_TYPE_STRING);
					student.setGuardianFirstName(studentDetailsSheetRow.getCell(39).getStringCellValue());
				}
			}
			if(null != studentDetailsSheetRow.getCell(40)){
				if(studentDetailsSheetRow.getCell(40).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(40).setCellType(Cell.CELL_TYPE_STRING);
					student.setGuardianMiddleName(studentDetailsSheetRow.getCell(40).getStringCellValue());
				}
			}
			if(null != studentDetailsSheetRow.getCell(41)){
				if(studentDetailsSheetRow.getCell(41).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(41).setCellType(Cell.CELL_TYPE_STRING);
					student.setGuardianLastName(studentDetailsSheetRow.getCell(41).getStringCellValue());
				}
			}
			if(null != studentDetailsSheetRow.getCell(42)){
				if(studentDetailsSheetRow.getCell(42).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(42).setCellType(Cell.CELL_TYPE_STRING);
					student.setGuardianMobile(studentDetailsSheetRow.getCell(42).getStringCellValue().toUpperCase());
				}
			}
			if(null != studentDetailsSheetRow.getCell(43)){
				if(studentDetailsSheetRow.getCell(43).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(43).setCellType(Cell.CELL_TYPE_STRING);
					student.setGuardianEmail(studentDetailsSheetRow.getCell(43).getStringCellValue().toUpperCase());
				}
			}
			if(null != studentDetailsSheetRow.getCell(44)){
				if(studentDetailsSheetRow.getCell(44).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(44).setCellType(Cell.CELL_TYPE_STRING);
					student.setPreviousSchoolName(studentDetailsSheetRow.getCell(44).getStringCellValue());
				}
			}
			if(null != studentDetailsSheetRow.getCell(45)){
				if(studentDetailsSheetRow.getCell(45).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(45).setCellType(Cell.CELL_TYPE_STRING);
					student.setPreviousSchoolAddress(studentDetailsSheetRow.getCell(45).getStringCellValue());
				}
			}
			if(null != studentDetailsSheetRow.getCell(46)){
				if(studentDetailsSheetRow.getCell(46).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(46).setCellType(Cell.CELL_TYPE_STRING);
					student.setPreviousSchoolPhone(studentDetailsSheetRow.getCell(46).getStringCellValue());
				}
			}
			if(null != studentDetailsSheetRow.getCell(47)){
				if(studentDetailsSheetRow.getCell(47).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(47).setCellType(Cell.CELL_TYPE_STRING);
					student.setPreviousSchoolEmail(studentDetailsSheetRow.getCell(47).getStringCellValue());
				}
			}
			if(null != studentDetailsSheetRow.getCell(48)){
				if(studentDetailsSheetRow.getCell(48).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(48).setCellType(Cell.CELL_TYPE_STRING);
					student.setPreviousSchoolWebsite(studentDetailsSheetRow.getCell(48).getStringCellValue());
				}
			}
			if(null != studentDetailsSheetRow.getCell(49)){
				if(studentDetailsSheetRow.getCell(49).getStringCellValue().length() != 0){
					studentDetailsSheetRow.getCell(49).setCellType(Cell.CELL_TYPE_STRING);
					resource.setEmailId(studentDetailsSheetRow.getCell(49).getStringCellValue());
				}
			}
			
			student.setResource(resource);
			studentDetailsList.add(student);
		}		     
	}catch(Exception e){
		logger.error(e);
	}
	return 	studentDetailsList;
}


	public List<StudentSubjectMapping> readExcelFileStudentSubjectMapping(String excelFile) {
		List<StudentSubjectMapping> studentSubjectMappingList = new ArrayList<StudentSubjectMapping>();
		try{
			FileInputStream file = new FileInputStream(new File(excelFile));
	 
			//Get the workbook instance for XLS file
			HSSFWorkbook workbook = new HSSFWorkbook(file);			
			HSSFSheet studentSubjectMappingSheet = workbook.getSheetAt(0);			
			HSSFRow studentSubjectMappingSheetRow = studentSubjectMappingSheet.getRow(0);
		
			for (int i = 1; i <= studentSubjectMappingSheet.getLastRowNum(); i++) {				
				HSSFRow studentSubjectMappingSheetExceptFirstRow = studentSubjectMappingSheet.getRow(i); 
				
				for(int j = 1; j<studentSubjectMappingSheetRow.getLastCellNum(); j++){		
					StudentSubjectMapping studentSubjectMapping = new StudentSubjectMapping();
						
					if(null != studentSubjectMappingSheetExceptFirstRow.getCell(0)){
							studentSubjectMappingSheetExceptFirstRow.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
							studentSubjectMapping.setSerialId(Integer.parseInt(studentSubjectMappingSheetExceptFirstRow.getCell(0).getStringCellValue()));
					}					
					if(null != studentSubjectMappingSheetExceptFirstRow.getCell(j)){
						studentSubjectMappingSheetExceptFirstRow.getCell(j).setCellType(Cell.CELL_TYPE_BOOLEAN);
						if(studentSubjectMappingSheetExceptFirstRow.getCell(j).getBooleanCellValue()){
							studentSubjectMapping.setSubject(studentSubjectMappingSheetRow.getCell(j).getStringCellValue().toUpperCase());
						}
					}
					if(studentSubjectMappingSheetExceptFirstRow.getCell(j).getBooleanCellValue()){
						studentSubjectMappingList.add(studentSubjectMapping);
					}else{
						studentSubjectMapping = null;
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return studentSubjectMappingList;
	}

	
	public List<StudentResult> readExcelFileMarksForCBSEExam(String excelFile) {
		List<StudentResult> studentResultList = new ArrayList<StudentResult>();
		try{
			FileInputStream file = new FileInputStream(new File(excelFile));
	 
			//Get the workbook instance for XLS file
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			int sheetCount = workbook.getNumberOfSheets();
			System.out.println("Sheet Count"+sheetCount);
			for (int j = 0; j < sheetCount; j++) {
				HSSFSheet resultSheet = workbook.getSheetAt(j);
				HSSFRow firstRow = resultSheet.getRow(0);
				
				StudentResult studentResult = new StudentResult();
				if(null != firstRow.getCell(0)){
					if(firstRow.getCell(0).getStringCellValue().length() != 0){
						firstRow.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
						String standard[] = firstRow.getCell(0).getStringCellValue().split(" : ");
						studentResult.setStandard(standard[1]);
					}
				}
				if(null != firstRow.getCell(2)){
					if(firstRow.getCell(2).getStringCellValue().length() != 0){
						firstRow.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
						String section[] = firstRow.getCell(2).getStringCellValue().split(" : ");
						studentResult.setSection(section[1]);
					}
				}
				if(null != firstRow.getCell(4)){
					if(firstRow.getCell(4).getStringCellValue().length() != 0){
						firstRow.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
						String subject[] = firstRow.getCell(4).getStringCellValue().split(" : ");
						studentResult.setSubject(subject[1]);
					}
				}
				if(null != firstRow.getCell(6)){
					if(firstRow.getCell(6).getStringCellValue().length() != 0){
						firstRow.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
						String exam[] = firstRow.getCell(6).getStringCellValue().split(" : ");
						studentResult.setExam(exam[1]);
					}
				}				
				
				int lastCell = resultSheet.getRow(2).getLastCellNum();
				for (int i = 3; i <= resultSheet.getLastRowNum(); i++) {
					HSSFRow resultRow = resultSheet.getRow(i); 
					StudentResult studResult = new StudentResult();
					studResult.setStandard(studentResult.getStandard());
					studResult.setSection(studentResult.getSection());
					studResult.setSubject(studentResult.getSubject());
					studResult.setExam(studentResult.getExam());
					int totalObt = 0;
					double weightage = 0.0;
						
					if(null != resultRow.getCell(0)){
						if(resultRow.getCell(0).getStringCellValue().length() != 0){
							resultRow.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
							studResult.setRollNumber(resultRow.getCell(0).getStringCellValue());
						}
					}
					
					if(null != resultRow.getCell(1)){
						resultRow.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
						if(resultRow.getCell(1).getStringCellValue().length() != 0){
							if(!resultRow.getCell(1).getStringCellValue().equalsIgnoreCase("AB")){
								studResult.setTheoryObtained(Integer.parseInt(resultRow.getCell(1).getStringCellValue()));
							}else{
								studResult.setTheoryObtained(-1);
							}
						}else{
							studResult.setTheoryObtained(-1);
						}
					}
					
					if(null != resultRow.getCell(2)){
						if(resultRow.getCell(2).getStringCellValue().length() != 0){
							resultRow.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
							studResult.setTheory(Integer.parseInt(resultRow.getCell(2).getStringCellValue()));
						}else{
							studResult.setTheory(0);
						}
					}else{
						studResult.setTheory(0);
					}
					
					if(null != resultRow.getCell(3)){
						if(resultRow.getCell(3).getStringCellValue().length() != 0){
							resultRow.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
							studResult.setTheoryPass(Integer.parseInt(resultRow.getCell(3).getStringCellValue()));
						}else{
							studResult.setTheoryPass(0);
						}
					}else{
						studResult.setTheoryPass(0);
					}
					
					if(lastCell == 11){
						if(null != resultRow.getCell(4)){
							resultRow.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
							if(resultRow.getCell(4).getStringCellValue().length() != 0){						
								if(!resultRow.getCell(4).getStringCellValue().equalsIgnoreCase("AB")){
									studResult.setPracticalObtained(Integer.parseInt(resultRow.getCell(4).getStringCellValue()));	
								}else{
									studResult.setPracticalObtained(-1);
								}
							}else{
								studResult.setPracticalObtained(-1);
							}
						}
						
						if(null != resultRow.getCell(5)){
							if(resultRow.getCell(5).getStringCellValue().length() != 0){
								resultRow.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
								studResult.setPractical(Integer.parseInt(resultRow.getCell(5).getStringCellValue()));
							}else{
								studResult.setPractical(0);
							}
						}else{
							studResult.setPractical(0);
						}
						
						if(null != resultRow.getCell(6)){
							if(resultRow.getCell(6).getStringCellValue().length() != 0){
								resultRow.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
								studResult.setPracticalPass(Integer.parseInt(resultRow.getCell(6).getStringCellValue()));
							}else{
								studResult.setPracticalPass(0);
							}
						}else{
							studResult.setPracticalPass(0);
						}

						if(null != resultRow.getCell(7)){
							resultRow.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
							if(resultRow.getCell(7).getStringCellValue().length() != 0){
								if(!resultRow.getCell(7).getStringCellValue().equalsIgnoreCase("AB")){
									studResult.setTotalObtained(Integer.parseInt(resultRow.getCell(7).getStringCellValue()));
									totalObt = studResult.getTotalObtained();
								}else{
									studResult.setTotalObtained(-1);
								}
							}else{
								studResult.setTotalObtained(-1);
							}
						}

						if(null != resultRow.getCell(8)){
							if(resultRow.getCell(8).getStringCellValue().length() != 0){
								resultRow.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
								studResult.setTotal(Integer.parseInt(resultRow.getCell(8).getStringCellValue()));
							}else{
								studResult.setTotal(0);
							}
						}else{
							studResult.setTotal(0);
						}
						
						if(null != resultRow.getCell(9)){
							if(resultRow.getCell(9).getStringCellValue().length() != 0){
								resultRow.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
								studResult.setPass(Integer.parseInt(resultRow.getCell(9).getStringCellValue()));
							}else{
								studResult.setPass(0);
							}
						}else{
							studResult.setPass(0);
						}
						
						if(null != resultRow.getCell(10)){
							if(resultRow.getCell(10).getStringCellValue().length() != 0){
								resultRow.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
								studResult.setPassFail(resultRow.getCell(10).getStringCellValue().toUpperCase());
							}
						}
												
					}else if(lastCell == 8){
						if(null != resultRow.getCell(4)){
							resultRow.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
							if(resultRow.getCell(4).getStringCellValue().length() != 0){
								if(!resultRow.getCell(4).getStringCellValue().equalsIgnoreCase("AB")){
									studResult.setTotalObtained(Integer.parseInt(resultRow.getCell(4).getStringCellValue()));
									totalObt = studResult.getTotalObtained();
								}else{
									studResult.setTotalObtained(-1);
								}
							}else{
								studResult.setTotalObtained(-1);
							}
						}

						if(null != resultRow.getCell(5)){
							if(resultRow.getCell(5).getStringCellValue().length() != 0){
								resultRow.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
								studResult.setTotal(Integer.parseInt(resultRow.getCell(5).getStringCellValue()));
							}else{
								studResult.setTotal(0);
							}
						}else{
							studResult.setTotal(0);
						}
						
						if(null != resultRow.getCell(6)){
							if(resultRow.getCell(6).getStringCellValue().length() != 0){
								resultRow.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
								studResult.setPass(Integer.parseInt(resultRow.getCell(6).getStringCellValue()));
							}else{
								studResult.setPass(0);
							}
						}else{
							studResult.setPass(0);
						}
						
						if(null != resultRow.getCell(7)){
							if(resultRow.getCell(7).getStringCellValue().length() != 0){
								resultRow.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
								studResult.setPassFail(resultRow.getCell(7).getStringCellValue().toUpperCase());
							}
						}						
						studResult.setPractical(0);
						studResult.setPracticalPass(0);
						studResult.setPracticalObtained(0);
					}
					if(null != studResult.getTotalObtained() && studResult.getTotalObtained()!=0 && studResult.getTotal()!=0){
						weightage = totalObt/studResult.getTotal();
					}
					studResult.setWeightageObtained(weightage);
					
					studentResultList.add(studResult);
				}
			}
			
		}catch(Exception e){
			logger.error("Error while updating exel for CBSE marks", e);			
			studentResultList=null;
			e.printStackTrace();
		}
		System.out.println("List Size ::"+studentResultList.size());
		return studentResultList;
	}
	
	
	public List<StudentResult> readExcelFileMarksForUserExam(String excelFile) {
		List<StudentResult> studentResultList = new ArrayList<StudentResult>();
		try{
			FileInputStream file = new FileInputStream(new File(excelFile));
	 
			//Get the workbook instance for XLS file
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			int sheetCount = workbook.getNumberOfSheets();
			System.out.println("Sheet Count"+sheetCount);
			for (int j = 0; j < sheetCount; j++) {
				HSSFSheet resultSheet = workbook.getSheetAt(j);
				HSSFRow firstRow = resultSheet.getRow(0);
				
				StudentResult studentResult = new StudentResult();
				if(null != firstRow.getCell(1)){
					if(firstRow.getCell(1).getStringCellValue().length() != 0){
						firstRow.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
						studentResult.setStandard(firstRow.getCell(1).getStringCellValue());
					}
				}
				if(null != firstRow.getCell(4)){
					if(firstRow.getCell(4).getStringCellValue().length() != 0){
						firstRow.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
						studentResult.setSection(firstRow.getCell(4).getStringCellValue());
					}
				}
				if(null != firstRow.getCell(7)){
					if(firstRow.getCell(7).getStringCellValue().length() != 0){
						firstRow.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
						studentResult.setSubject(firstRow.getCell(7).getStringCellValue());
					}
				}
				if(null != firstRow.getCell(10)){
					if(firstRow.getCell(10).getStringCellValue().length() != 0){
						firstRow.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
						studentResult.setExam(firstRow.getCell(10).getStringCellValue());
					}
				}
				
				System.out.println("Last Row Number ::"+resultSheet.getLastRowNum());
				for (int i = 3; i <= resultSheet.getLastRowNum(); i++) {
					HSSFRow resultRow = resultSheet.getRow(i); 
					StudentResult sr = new StudentResult();
					sr.setStandard(studentResult.getStandard());
					sr.setSection(studentResult.getSection());
					sr.setSubject(studentResult.getSubject());
					sr.setExam(studentResult.getExam());
						
					if(null != resultRow.getCell(0)){
						if(resultRow.getCell(0).getStringCellValue().length() != 0){
							resultRow.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
							sr.setRollNumber(resultRow.getCell(0).getStringCellValue());
						}
					}
					
					if(null != resultRow.getCell(1)){
						resultRow.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
						if(resultRow.getCell(1).getStringCellValue().length() != 0){
							if(!resultRow.getCell(1).getStringCellValue().equalsIgnoreCase("AB")){
								sr.setTheoryObtained(Integer.parseInt(resultRow.getCell(1).getStringCellValue()));
							}else{
								sr.setTheoryObtained(null);
							}
						}
					}
					
					if(null != resultRow.getCell(2)){
						if(resultRow.getCell(2).getStringCellValue().length() != 0){
							resultRow.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
							sr.setTheory(Integer.parseInt(resultRow.getCell(2).getStringCellValue()));
						}else
							sr.setTheory(0);
					}else
						sr.setTheory(0);
					
					if(null != resultRow.getCell(3)){
						if(resultRow.getCell(3).getStringCellValue().length() != 0){
							resultRow.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
							sr.setTheoryPass(Integer.parseInt(resultRow.getCell(3).getStringCellValue()));
						}else
							sr.setTheoryPass(0);							
					}else
						sr.setTheoryPass(0);
					
					if(null != resultRow.getCell(4)){
						resultRow.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
						if(resultRow.getCell(4).getStringCellValue().length() != 0){						
							if(!resultRow.getCell(4).getStringCellValue().equalsIgnoreCase("AB")){
								sr.setPracticalObtained(Integer.parseInt(resultRow.getCell(4).getStringCellValue()));	
							}else{
								sr.setPracticalObtained(null);
							}
						}else{
							sr.setPracticalObtained(null);
						}
					}
					
					if(null != resultRow.getCell(5)){
						if(resultRow.getCell(5).getStringCellValue().length() != 0){
							resultRow.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
							sr.setPractical(Integer.parseInt(resultRow.getCell(5).getStringCellValue()));
						}else
							sr.setPractical(0);							
					}else
						sr.setPractical(0);
					
					if(null != resultRow.getCell(6)){
						if(resultRow.getCell(6).getStringCellValue().length() != 0){
							resultRow.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
							sr.setPracticalPass(Integer.parseInt(resultRow.getCell(6).getStringCellValue()));
						}else
							sr.setPracticalPass(0);							
					}else
						sr.setPracticalPass(0);

					if(null != resultRow.getCell(7)){
						resultRow.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
						if(resultRow.getCell(7).getStringCellValue().length() != 0){
							if(!resultRow.getCell(7).getStringCellValue().equalsIgnoreCase("AB")){
								sr.setTotalObtained(Integer.parseInt(resultRow.getCell(7).getStringCellValue()));
							}else{
								sr.setTotalObtained(null);
							}
						}else{
							sr.setTotalObtained(null);
						}
					}

					if(null != resultRow.getCell(8)){
						if(resultRow.getCell(8).getStringCellValue().length() != 0){
							resultRow.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
							sr.setTotal(Integer.parseInt(resultRow.getCell(8).getStringCellValue()));
						}else
							sr.setTotal(0);							
					}else
						sr.setTotal(0);
					
					if(null != resultRow.getCell(9)){
						if(resultRow.getCell(9).getStringCellValue().length() != 0){
							resultRow.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
							sr.setPass(Integer.parseInt(resultRow.getCell(9).getStringCellValue()));
						}else
							sr.setPass(0);							
					}else
						sr.setPass(0);
					
					if(null != resultRow.getCell(10)){
						if(resultRow.getCell(10).getStringCellValue().length() != 0){
							resultRow.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
							sr.setPassFail(resultRow.getCell(10).getStringCellValue());
						}
					}
					
					studentResultList.add(sr);
				}
			}
			
		}catch(Exception e){
			logger.error("Error while updating exel for User marks", e);			
			studentResultList=null;
			e.printStackTrace();
		}
		System.out.println("List Size ::"+studentResultList.size());
		return studentResultList;
	}
	
	
	public List<Asset> readExcelFileForAssetDetails(String excelFile) {
		List<Asset> assetList = new ArrayList<Asset>();
		try{
			FileInputStream file = new FileInputStream(new File(excelFile));
     
			//Get the workbook instance for XLS file
			HSSFWorkbook workbook = new HSSFWorkbook(file);		
			HSSFSheet assetDetailsSheet = workbook.getSheetAt(0);
			
			for (int i = 1; i <= assetDetailsSheet.getLastRowNum(); i++) {
				HSSFRow assetDetailsSheetRow = assetDetailsSheet.getRow(i); 
				Asset asset = new Asset();
				
				if(null != assetDetailsSheetRow.getCell(0)){
					if(assetDetailsSheetRow.getCell(0).getStringCellValue().length() != 0){
						assetDetailsSheetRow.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
						asset.setAssetType(assetDetailsSheetRow.getCell(0).getStringCellValue().toUpperCase());
					}
				}
				if(null != assetDetailsSheetRow.getCell(1)){
					if(assetDetailsSheetRow.getCell(1).getStringCellValue().length() != 0){
						assetDetailsSheetRow.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
						asset.setAssetSubType(assetDetailsSheetRow.getCell(1).getStringCellValue().toUpperCase());
					}
				}
				if(null != assetDetailsSheetRow.getCell(2)){
					if(assetDetailsSheetRow.getCell(2).getStringCellValue().length() != 0){
						assetDetailsSheetRow.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
						asset.setAssetName(assetDetailsSheetRow.getCell(2).getStringCellValue().toUpperCase());
					}
				}
				if(null != assetDetailsSheetRow.getCell(3)){
					if(assetDetailsSheetRow.getCell(3).getStringCellValue().length() != 0){
						assetDetailsSheetRow.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
						Department department = new Department();
						department.setDepartmentName(assetDetailsSheetRow.getCell(3).getStringCellValue().toUpperCase());
						asset.setDepartment(department);	
					}				
				}
				if(null != assetDetailsSheetRow.getCell(4)){
					if(assetDetailsSheetRow.getCell(4).getStringCellValue().length() != 0){
						assetDetailsSheetRow.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
						asset.setAssetPrice(Double.parseDouble(assetDetailsSheetRow.getCell(4).getStringCellValue()));
					}
				}
				if(null != assetDetailsSheetRow.getCell(5)){
					if(assetDetailsSheetRow.getCell(5).getStringCellValue().length() != 0){
						assetDetailsSheetRow.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
						asset.setPurchaseDate(assetDetailsSheetRow.getCell(5).getStringCellValue());
					}
				}
				if(null != assetDetailsSheetRow.getCell(6)){
					if(assetDetailsSheetRow.getCell(6).getStringCellValue().length() != 0){
						assetDetailsSheetRow.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
						asset.setLedgerBalance(Integer.parseInt(assetDetailsSheetRow.getCell(6).getStringCellValue()));
					}
				}
				if(null != assetDetailsSheetRow.getCell(7)){
					if(assetDetailsSheetRow.getCell(7).getStringCellValue().length() != 0){
						assetDetailsSheetRow.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
						asset.setLedgerNumber(assetDetailsSheetRow.getCell(7).getStringCellValue());
					}
				}
				if(null != assetDetailsSheetRow.getCell(8)){
					if(assetDetailsSheetRow.getCell(8).getStringCellValue().length() != 0){
						assetDetailsSheetRow.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
						asset.setPageNumber(assetDetailsSheetRow.getCell(8).getStringCellValue());
					}
				}
				if(null != assetDetailsSheetRow.getCell(9)){
					if(assetDetailsSheetRow.getCell(9).getStringCellValue().length() != 0){
						assetDetailsSheetRow.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
						asset.setAssetUnit(assetDetailsSheetRow.getCell(9).getStringCellValue());
					}
				}
				assetList.add(asset);
			}		     
		}catch(Exception e){
			logger.error(e);
		}
		return assetList;
	}
	
	public List<ResourceAttendance> readExcelForResourceAttendance(String actualFolderPathForUpload) {
		List<ResourceAttendance> teacherAttendanceList = new ArrayList<ResourceAttendance>();
		try{
			FileInputStream file = new FileInputStream(new File(actualFolderPathForUpload));
     
			//Get the workbook instance for XLS file
			HSSFWorkbook workbook = new HSSFWorkbook(file);		
			
			//Get first sheet from the workbook
			HSSFSheet TeacherAttendanceSheet = workbook.getSheetAt(0);
			for (int i = 1; i <= TeacherAttendanceSheet.getLastRowNum(); i++) {
				HSSFRow TeacherAttendanceSheetRow = TeacherAttendanceSheet.getRow(i); 
				ResourceAttendance resourceAttendance = new ResourceAttendance();
				if(TeacherAttendanceSheetRow.getCell(0) != null){
					TeacherAttendanceSheetRow.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
					resourceAttendance.setResourceId(TeacherAttendanceSheetRow.getCell(0).getStringCellValue());	
				}
				if(TeacherAttendanceSheetRow.getCell(1) != null){
					TeacherAttendanceSheetRow.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
					resourceAttendance.setAttendanceDay(TeacherAttendanceSheetRow.getCell(1).getStringCellValue().trim());
					/*if(!TeacherAttendanceSheetRow.getCell(1).getStringCellValue().trim().equalsIgnoreCase("")){
						String[] splitedDate = TeacherAttendanceSheetRow.getCell(1).getStringCellValue().trim().split("/");
						resourceAttendance.setYear(splitedDate[2]);
						resourceAttendance.setMonth(splitedDate[1]);
						resourceAttendance.setAttendanceDay(splitedDate[0]);
					}*/
				}
				if(TeacherAttendanceSheetRow.getCell(1) != null){
					TeacherAttendanceSheetRow.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
					String date = TeacherAttendanceSheetRow.getCell(1).getStringCellValue();
					String[] splitedDate = date.split("/");
					resourceAttendance.setAttendanceMonth(splitedDate[1]);
					resourceAttendance.setAttendanceYear(splitedDate[2]);
				}
				/*if(TeacherAttendanceSheetRow.getCell(4) != null){
					TeacherAttendanceSheetRow.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
					resourceAttendance.setWeekDay(TeacherAttendanceSheetRow.getCell(4).getStringCellValue());
				}*/
				/*if(TeacherAttendanceSheetRow.getCell(5) != null){
					TeacherAttendanceSheetRow.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
					resourceAttendance.setSwipeTimeSlot(TeacherAttendanceSheetRow.getCell(5).getStringCellValue());
					
					if(!TeacherAttendanceSheetRow.getCell(5).getStringCellValue().trim().equalsIgnoreCase("")){
						resourceAttendance.setAttendanceFlag(true);
					}
					if(TeacherAttendanceSheetRow.getCell(5).getStringCellValue().trim().equalsIgnoreCase("")){
						resourceAttendance.setAttendanceFlag(false);
					}
				}*/
			
				teacherAttendanceList.add(resourceAttendance);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			logger.error("In  readExcelForResourceAttendance(String actualFolderPathForUpload)  method of DataUtility",e);
		}
		return teacherAttendanceList;
	}
	
	
}
