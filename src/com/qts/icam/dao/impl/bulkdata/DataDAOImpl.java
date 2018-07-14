package com.qts.icam.dao.impl.bulkdata;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Map.Entry;
import java.util.Date;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qts.icam.dao.bulkdata.DataDAO;
import com.qts.icam.dao.impl.administrator.AdministratorDAOImpl;
import com.qts.icam.model.academics.StudentResult;
import com.qts.icam.model.academics.Subject;
import com.qts.icam.model.administrator.Functionality;
import com.qts.icam.model.administrator.Role;
import com.qts.icam.model.backoffice.ResourceAttendance;
import com.qts.icam.model.backoffice.StudentSubjectMapping;
import com.qts.icam.model.common.Address;
import com.qts.icam.model.common.Asset;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.ResourceType;
import com.qts.icam.model.common.Student;
import com.qts.icam.model.common.WorkShift;
import com.qts.icam.model.erp.Employee;
import com.qts.icam.model.erp.EmployeeDependent;
import com.qts.icam.model.erp.Organization;
import com.qts.icam.model.erp.Publication;
import com.qts.icam.model.erp.Qualification;
import com.qts.icam.model.inventory.Commodity;
import com.qts.icam.model.library.Author;
import com.qts.icam.model.library.Book;
import com.qts.icam.model.library.BookId;
import com.qts.icam.model.library.BookLanguage;
import com.qts.icam.model.library.BookMedium;
import com.qts.icam.model.library.BookPublisher;
import com.qts.icam.utility.Utility;
import com.qts.icam.utility.customexception.CustomException;
import com.qts.icam.utility.encryptdecrypt.EncryptDecrypt;





@Repository
public class DataDAOImpl implements DataDAO, DataDaoImplSql {
	
	private final static Logger logger = Logger.getLogger(AdministratorDAOImpl.class);
	private SqlSession session = null;
	

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	Utility utility;
	
	EncryptDecrypt encryptDecrypt = new EncryptDecrypt();
	
	@Override
	public int insertFunctionalityRoleMappingToDB(List<Role> roleList,String moduleCode,String updatedBy)throws CustomException {
		PreparedStatement ps=null;
		Connection dbConnection = null;		
		int insertStatus=1;
		try{
			dbConnection=dataSource.getConnection();			
			for(Role role : roleList){
				for(Functionality functionality:role.getFunctionalityList()){
				functionality.setUpdatedBy(updatedBy);
				ps = dbConnection.prepareStatement(insertForFunctionalityRoleMapping);
				logger.info(moduleCode+"\t"+role.getRoleName()+"\t"+functionality.getFunctionalityName()+"\t"+functionality.isView()+"\t"+functionality.isInsert()+"\t"+functionality.isUpdate());
				ps.setString(1, functionality.getUpdatedBy());
				ps.setBoolean(2, functionality.isView());
				ps.setBoolean(3, functionality.isInsert());
				ps.setBoolean(4, functionality.isUpdate());
				ps.setString(5, functionality.getFunctionalityName());
				ps.setString(6, moduleCode);				
				ps.setString(7, role.getRoleName());				
				ps.execute();
			}
		}
		}catch(Exception e){
			insertStatus=0;
			CustomException.exceptionHandler(e);
		}finally{
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			} 
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
		}
		return insertStatus;
	}

	
	@Override
	public int batchInsertForBookAuthor(List<String> bookAuthorList, String updatedBy) throws CustomException {
		Connection dbConnection = null;
		PreparedStatement ps = null;
		logger.info(bookAuthorList);
		int authorInsertStatus = 0;
		try{
			dbConnection = dataSource.getConnection();
			for(String s : bookAuthorList){
				ps = dbConnection.prepareStatement(checkAuthorFullName);
				ps.setString(1, s);
				ResultSet rs = ps.executeQuery();
				if(!rs.next()){					
					Author author = new Author();					
					author.setUpdatedBy(updatedBy);
					author.setAuthorObjectId("AUTHOR-OBJ");//encryptDecrypt.getBase64EncodedID("LibraryDAOImpl"));
					author.setAuthorFullName(s);
					
					ps.close();
					ps = dbConnection.prepareStatement(batchInsertForBookAuthor);
					ps.setString(1, author.getAuthorObjectId());
					ps.setString(2, author.getUpdatedBy());
					ps.setString(3, author.getAuthorFullName());
					
					authorInsertStatus = ps.executeUpdate();					
				}
			}
			logger.info("In DATADAOIMPL "+authorInsertStatus);
		}catch(Exception e){
			authorInsertStatus = 0;
			logger.error(e);
		}finally{
			if (null != ps) {
				try {
					ps.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}		
			if (null != dbConnection) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
		}
		return authorInsertStatus;
	}






@Override
	public int batchInsertForBookPublisher(List<String> publisherList, String updatedBy) throws CustomException {
		Connection dbConnection = null;
		PreparedStatement ps = null;
		logger.info(publisherList);
		int publisherInsertStatus = 0;
		try{
			dbConnection = dataSource.getConnection();			
			for(String s : publisherList){
				ps = dbConnection.prepareStatement(checkBookPublisher);
				ps.setString(1, s);
				ResultSet rs = ps.executeQuery();
				if(!rs.next()){					
					BookPublisher publisher = new BookPublisher();
					publisher.setUpdatedBy(updatedBy);
					publisher.setBookPublisherObjectId("BOOKPUB-OBJ");//encryptDecrypt.getBase64EncodedID("LibraryDAOImpl"));
					publisher.setBookPublisherName(s);
					ps.close();
					ps = dbConnection.prepareStatement(batchInsertForBookPublisher);
					ps.setString(1, publisher.getBookPublisherObjectId());
					ps.setString(2, publisher.getUpdatedBy());
					ps.setString(3, publisher.getBookPublisherName());
					ps.setString(4, publisher.getBookPublisherName());
					
					publisherInsertStatus = ps.executeUpdate();					
				}
			}
			logger.info("In DATADAOIMPL "+publisherInsertStatus);
		}catch(Exception e){
			publisherInsertStatus = 0;
			logger.error(e);
		}finally{
			if (null != ps) {
				try {
					ps.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}	
			if (null != dbConnection) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
		}
		return publisherInsertStatus;
	}






@Override
	public int batchInsertForBookMedium(List<String> bookMediumList, String updatedBy) throws CustomException {
		Connection dbConnection = null;
		PreparedStatement ps = null;
		logger.info(bookMediumList);
		int mediumInsertStatus = 0;
		try{
			dbConnection = dataSource.getConnection();
			for(String s : bookMediumList){
				ps = dbConnection.prepareStatement(checkBookMedium);
				ps.setString(1, s);
				ResultSet rs = ps.executeQuery();
				if(!rs.next()){					
					BookMedium medium = new BookMedium();
					medium.setUpdatedBy(updatedBy);
					medium.setBookMediumObjectId("BOOKMED-OBJ");//encryptDecrypt.getBase64EncodedID("LibraryDAOImpl"));
					medium.setBookMediumName(s);
					ps.close();
					ps = dbConnection.prepareStatement(batchInsertForBookMedium);
					ps.setString(1, medium.getBookMediumObjectId());
					ps.setString(2, medium.getUpdatedBy());
					ps.setString(3, medium.getBookMediumName());
					ps.setString(4, medium.getBookMediumName());
					ps.setString(5, medium.getBookMediumName());
					
					mediumInsertStatus = ps.executeUpdate();
				}
			}
			logger.info("In DATADAOIMPL "+mediumInsertStatus);
		}catch(Exception e){
			mediumInsertStatus = 0;
			logger.error(e);
		}finally{
			if (null != ps) {
				try {
					ps.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}	
			if (null != dbConnection) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
		}
		return mediumInsertStatus;
	}






@Override
	public int batchInsertForBookLanguage(List<String> bookLanguageList, String updatedBy) throws CustomException {
		Connection dbConnection = null;
		PreparedStatement ps = null;
		logger.info(bookLanguageList);
		int languageInsertStatus = 0;
		try{
			dbConnection = dataSource.getConnection();			
			for(String s : bookLanguageList){
				ps = dbConnection.prepareStatement(checkBookLanguage);
				ps.setString(1, s);
				ResultSet rs = ps.executeQuery();
				if(!rs.next()){				
					BookLanguage language = new BookLanguage();
					language.setUpdatedBy(updatedBy);
					language.setBookLanguageObjectId("BKLANG-OBJ");//encryptDecrypt.getBase64EncodedID("LibraryDAOImpl"));
					language.setBookLanguageName(s);
					ps.close();
					ps = dbConnection.prepareStatement(batchInsertForBookLanguage);
					ps.setString(1, language.getBookLanguageObjectId());
					ps.setString(2, language.getUpdatedBy());
					ps.setString(3, language.getBookLanguageName());
					ps.setString(4, language.getBookLanguageName());
					ps.setString(5, language.getBookLanguageName());
					
					languageInsertStatus = ps.executeUpdate();					
				}else{
					languageInsertStatus = 1;	
				}
			}
			logger.info("In DATADAOIMPL "+languageInsertStatus);
		}catch(Exception e){
			languageInsertStatus = 0;
			logger.error(e);
		}finally{
			if (null != ps) {
				try {
					ps.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}	
			if (null != dbConnection) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
		}
		return languageInsertStatus;
	}







//@Override
//	public int batchInsertForBook(List<Book> bookList, String updatedBy) throws CustomException {
//		Connection dbConnection = null;
//		PreparedStatement ps = null;
//		
//		int bookInsertStatus = 0;
//		int individualBookInsertStatus = 0;
//		int bookAuthorInsertStatus = 0;
//		try{
//			dbConnection = dataSource.getConnection();			
//			for(Book b : bookList){
//				ps = dbConnection.prepareStatement(checkBook);
//				ps.setString(1, b.getBookName());
//				ResultSet rs = ps.executeQuery();
//				if(rs.next()){
//					b.setBookCode(rs.getString("book_code"));
//					ps = dbConnection.prepareStatement(updateBook);
//					ps.setInt(1, b.getTotalNumberOfBookCopies());
//					ps.setInt(2, b.getTotalNumberOfBookCopies());
//					ps.setString(3, b.getBookCode());
//					bookInsertStatus = ps.executeUpdate();
//				}else{	
//					b.setBookObjectId("BOOK-OBJ");//encryptDecrypt.getBase64EncodedID("LibraryDAOImpl"));
//					b.setUpdatedBy(updatedBy);
//					ps.close();
//					ps = dbConnection.prepareStatement(batchInsertForBook);
//					ps.setString(1, b.getBookObjectId());
//					ps.setString(2, b.getUpdatedBy());
//					ps.setString(3, b.getBookName());
//					ps.setString(4, b.getBookDesc());
//					if(null != b.getBookType() && !b.getBookType().equals("note") && null != b.getBookPublisher()){
//						ps.setString(5, b.getBookPublisher().getBookPublisherName());	// Not For Note				
//					}else{
//						ps.setString(5, null);	
//					}
//					if(null != b.getBookMedium()){
//						ps.setString(6, b.getBookMedium().getBookMediumName());
//					}else{
//						ps.setString(6, null);	
//					}
//					ps.setString(7, b.getBookIsbn());
//					ps.setString(8, b.getBookEdition());
//					ps.setString(9, b.getBookPlace());
//					if(null != b.getBookType() && b.getBookType().equals("periodicals")){
//						ps.setString(10, b.getBookPeriodicity());	// Only For Periodical		
//					}else{
//						ps.setString(10, null);						
//					}
//					ps.setInt(11, b.getTotalNumberOfBookCopies());
//					if(null != b.getBookLanguage()){
//						ps.setString(12, b.getBookLanguage().getBookLanguageName());
//					}else{
//						ps.setString(12, null);						
//					}
//					ps.setInt(13, b.getTotalNumberOfBookCopies());
//					ps.setString(14, b.getBookType());
//					if(null != b.getBookType() && b.getBookType().equals("book") && null != b.getBookSubject()){
//						ps.setString(15, b.getBookSubject().getSubjectName());	// Only For Book		
//					}else{
//						ps.setString(15, null);						
//					}					
//					bookInsertStatus = ps.executeUpdate();
//					if(bookInsertStatus != 0){
//						ps = dbConnection.prepareStatement(checkBook);
//						ps.setString(1, b.getBookName());
//						ResultSet rs1 = ps.executeQuery();
//						if(rs1.next()){
//							b.setBookCode(rs1.getString("book_code"));
//						}
//					}
//				}				
//				BookId bi = new BookId();
//				bi.setUpdatedBy(updatedBy);
//				bi.setBookCode(b.getBookCode());
//				bi.setBookIdObjectId("BOOK-ID-OBJ");//encryptDecrypt.getBase64EncodedID("LibraryDAOImpl"));
//				bi.setBookId(b.getAccessionNumber());
//				bi.setPrice(b.getPrice());
//				
//				ps = dbConnection.prepareStatement(batchInsertIndividualBook);
//				
//				ps.setString(1, bi.getBookIdObjectId());
//				ps.setString(2, bi.getUpdatedBy());
//				ps.setString(3, bi.getBookCode());
//				ps.setString(4, bi.getBookCode());
//				if(null != bi.getPrice()){
//					ps.setDouble(5, bi.getPrice());
//				}else{
//					ps.setDouble(5, 0.00);
//				}
//				ps.setString(6, bi.getBookId());
//				if(null != b.getBookCategory()){
//					ps.setString(7, b.getBookCategory().getBookCategoryName());
//				}else{
//					ps.setString(7, null);
//				}				
//				individualBookInsertStatus = ps.executeUpdate();
//					
//				if(null != b.getBookAuthorList() && b.getBookAuthorList().size() != 0){
//					for(Author a: b.getBookAuthorList()){
//						a.setUpdatedBy(updatedBy);
//						a.setAuthorObjectId("AUTH-BOOK-OBJ");//encryptDecrypt.getBase64EncodedID("LibraryDAOImpl"));
//						a.setAuthorEmail(b.getBookCode());
//						ps = dbConnection.prepareStatement(checkBookAuthor);
//						ps.setString(1, b.getBookCode());
//						ps.setString(2, a.getAuthorFullName());
//						rs = ps.executeQuery();
//						if(!rs.next()){
//							ps = dbConnection.prepareStatement(batchInsertBookAuthor);
//							ps.setString(1, a.getAuthorObjectId());
//							ps.setString(2, a.getUpdatedBy());
//							ps.setString(3, a.getAuthorEmail());
//							ps.setString(4, a.getAuthorFullName());
//														
//							bookAuthorInsertStatus = ps.executeUpdate();							
//						}
//					}
//				}				
//			}						
//		}catch(Exception e){
//			e.printStackTrace();
//			bookInsertStatus = 0;
//			individualBookInsertStatus = 0;
//			bookAuthorInsertStatus = 0;
//			logger.error(e);
//		}finally{			
//			if (null != ps) {
//				try {
//					ps.close();
//				} catch (SQLException e) {
//					logger.error(e);
//				}
//			}	
//			if (null != dbConnection) {
//				try {
//					dbConnection.close();
//				} catch (SQLException e) {
//					logger.error(e);
//				}
//			}
//		}
//		return bookInsertStatus;
//	}



	
	@Override
	public int batchInsertForEmployeeDetails(List<Employee> employeeDetailsList, String updatedBy) throws CustomException {
		Connection dbConnection = null;
		PreparedStatement ps = null;
		int resourceInsertStatus = 0;
		int employeeDetailsInsertStatus = 0;
		int insertStatus = 0;
		try{
			dbConnection = dataSource.getConnection();

			for(Employee employee : employeeDetailsList){						
				Resource resource = employee.getResource();
				resource.setUpdatedBy(updatedBy);
				resource.setObjectId("RES-OBJ");
				
				ps = dbConnection.prepareStatement(batchInsertResourceDetails);
				ps.setString(1, resource.getObjectId());
				ps.setString(2, resource.getUpdatedBy());
				ps.setString(3, employee.getEmployeeType().getEmployeeTypeName());
				ps.setString(4, resource.getGender());
				ps.setString(5, resource.getUserId());
				ps.setString(6, resource.getFirstName());
				ps.setString(7, resource.getMiddleName());
				ps.setString(8, resource.getLastName());
				ps.setString(9, resource.getDateOfBirth());
				ps.setString(10, resource.getFatherFirstName());
				ps.setString(11, resource.getFatherMiddleName());
				ps.setString(12, resource.getFatherLastName());
				ps.setString(13, employee.getFatherOccupation());
				ps.setString(14, resource.getMotherFirstName());
				ps.setString(15, resource.getMotherMiddleName());
				ps.setString(16, resource.getMotherLastName());
				ps.setString(17, employee.getMotherOccupation());
				ps.setString(18, resource.getEmailId());
				ps.setString(19, resource.getMobile());
				ps.setString(20, resource.getBloodGroup());
				ps.setString(21, resource.getMotherTongue());
				ps.setString(22, resource.getReligion());
				ps.setString(23, resource.getNationality());
				ps.setString(24, resource.getCategory());
				ps.setString(25, resource.getPassportNo());
				ps.setString(26, resource.getVoterCardNo());
				ps.setString(27, resource.getPanCardNo());
				ps.setString(28, resource.getAadharCardNo());
				ps.setString(29, resource.getVotingConstituency());
				ps.setString(30, resource.getParliamentaryConstituency());
				ps.setString(31, resource.getBankName());
				ps.setString(32, resource.getBankBranch());
				ps.setString(33, resource.getAccountNumber());
				ps.setString(34, resource.getAccountType());
				ps.setString(35, resource.getAccountHolderName());
				ps.setString(36, employee.getMedicalAttention());
				
				resourceInsertStatus = ps.executeUpdate();
				if(resourceInsertStatus != 0){
					ps = dbConnection.prepareStatement(batchInsertEmployeeDetails);
					ps.setString(1, resource.getUpdatedBy());
					ps.setString(2, employee.getEmployeeCode());
					ps.setString(3, employee.getDateOfJoining());
					ps.setString(4, employee.getDateOfRetirement());
					ps.setString(5, employee.getJobType().getJobTypeName());
					ps.setString(6, employee.getDesignation().getDesignationName());
					ps.setString(7, employee.getDepartment().getDepartmentName());
					ps.setString(8, employee.getQualificationSummary());
					ps.setString(9, employee.getMaritalStatus());
					ps.setString(10, employee.getSpouseName());
					ps.setString(11, resource.getUserId());
					
					employeeDetailsInsertStatus = ps.executeUpdate();
				}
				
				if(employeeDetailsInsertStatus != 0){					
					 CallableStatement cStmt = dbConnection.prepareCall("{call insert_staff_leave_details(?, ?, ?)}");
					 cStmt.setString(1, employee.getResource().getObjectId());
					 cStmt.setString(2, employee.getResource().getUpdatedBy());
					 cStmt.setString(3, employee.getResource().getUserId());
					 boolean leaveainsertStatus=cStmt.execute();					
					 logger.info("leaveainsertStatus:"+leaveainsertStatus);
				}
				
			}
			
			if(employeeDetailsInsertStatus != 0){
				insertStatus = 1;
			}
			logger.info("In DATADAOIMPL "+insertStatus);
		}catch(Exception e){
			insertStatus = 0;
			logger.error(e);
		}finally{
			if (null != ps) {
				try {
					ps.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}	
			if (null != dbConnection) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
		}
		return insertStatus;
	}

	@Override
	public int batchInsertForCity(List<String> cityList, String updatedBy) throws CustomException {
		Connection dbConnection = null;
		PreparedStatement ps = null;
		int cityInsertStatus = 0;
		try{
			dbConnection = dataSource.getConnection();			
			for(String s : cityList){
				ps = dbConnection.prepareStatement(checkCity);
				ps.setString(1, s);
				ResultSet rs = ps.executeQuery();
				if(!rs.next()){
					Address address = new Address();
					address.setUpdatedBy(updatedBy);
					address.setObjectId("CITY-OBJ");
					address.setAddressName(s);
					ps = dbConnection.prepareStatement(batchInsertForCity);
					ps.setString(1, address.getObjectId());
					ps.setString(2, address.getUpdatedBy());
					ps.setString(3, address.getAddressName());
					ps.setString(4, address.getAddressName());
					
					cityInsertStatus = ps.executeUpdate();
				}
			}
			logger.info("In DATADAOIMPL "+cityInsertStatus);
		}catch(Exception e){
			cityInsertStatus = 0;
			logger.error(e);
		}finally{
			if (null != ps) {
				try {
					ps.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}	
			if (null != dbConnection) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
		}
		return cityInsertStatus;
	}


	@Override
	public int batchInsertForDistrict(List<String> districtList, String updatedBy) throws CustomException {
		Connection dbConnection = null;
		PreparedStatement ps = null;
		int districtInsertStatus = 0;
		try{
			dbConnection = dataSource.getConnection();			
			for(String s : districtList){
				ps = dbConnection.prepareStatement(checkDistrict);
				ps.setString(1, s);
				ResultSet rs = ps.executeQuery();
				if(!rs.next()){
					Address address = new Address();
					address.setUpdatedBy(updatedBy);
					address.setObjectId("DISTRICT-OBJ");
					address.setAddressName(s);
					ps = dbConnection.prepareStatement(batchInsertForDistrict);
					ps.setString(1, address.getObjectId());
					ps.setString(2, address.getUpdatedBy());
					ps.setString(3, address.getAddressName());
					ps.setString(4, address.getAddressName());
					
					districtInsertStatus = ps.executeUpdate();
				}
			}
			logger.info("In DATADAOIMPL "+districtInsertStatus);
		}catch(Exception e){
			districtInsertStatus = 0;
			logger.error(e);
		}finally{
			if (null != ps) {
				try {
					ps.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}	
			if (null != dbConnection) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
		}
		return districtInsertStatus;
	}
	
	
	@Override
	public int batchInsertForEmployeeAddressDetails(List<Address> employeeAddressList, String updatedBy) throws CustomException {
		Connection dbConnection = null;
		PreparedStatement ps = null;
		int presentAddressInsertStatus = 0;
		int permanentAddressInsertStatus = 0;
		int insertStatus = 0;
		logger.info("three");
		try{
			dbConnection = dataSource.getConnection();			
			for(Address address : employeeAddressList){						
				Address addr = new Address();
				addr.setUpdatedBy(updatedBy);
				
				ps = dbConnection.prepareStatement(batchInsertEmployeeAddress);
				ps.setString(1, "PRESENT-ADDR-OBJ");
				ps.setString(2, addr.getUpdatedBy());
				ps.setString(3, "PRESENT-CODE");
				ps.setString(4, address.getPresentAddressLine());
				ps.setString(5, address.getPresentAddressLandmark());
				ps.setString(6, address.getPresentAddressCityVillage());
				ps.setString(7, address.getPresentAddressDistrict());
				ps.setString(8, address.getPresentAddressPinCode());
				ps.setString(9, address.getPresentAddressState());
				ps.setString(10, address.getPresentAddressCountry());
				ps.setString(11, address.getPresentAddressPoliceStation());
				ps.setString(12, address.getPresentAddressPostOffice());
				ps.setString(13, "PRESENT");
				ps.setString(14, address.getPresentAddressRailwayStation());
				ps.setString(15, address.getPresentAddressPhone());
				ps.setString(16, address.getUserId());
				
				presentAddressInsertStatus = ps.executeUpdate();
				if(presentAddressInsertStatus != 0){
					ps = dbConnection.prepareStatement(batchInsertEmployeeAddress);
					ps.setString(1, "PERMANENT-ADDR-OBJ");
					ps.setString(2, addr.getUpdatedBy());
					ps.setString(3, "PERMANENT-CODE");
					ps.setString(4, address.getPermanentAddressLine());
					ps.setString(5, address.getPermanentAddressLandmark());
					ps.setString(6, address.getPermanentAddressCityVillage());
					ps.setString(7, address.getPermanentAddressDistrict());
					ps.setString(8, address.getPermanentAddressPinCode());
					ps.setString(9, address.getPermanentAddressState());
					ps.setString(10, address.getPermanentAddressCountry());
					ps.setString(11, address.getPermanentAddressPoliceStation());
					ps.setString(12, address.getPermanentAddressPostOffice());
					ps.setString(13, "PERMANENT");
					ps.setString(14, address.getPermanentAddressRailwayStation());
					ps.setString(15, address.getPermanentAddressPhone());
					ps.setString(16, address.getUserId());
					
					permanentAddressInsertStatus = ps.executeUpdate();
				}
				if(permanentAddressInsertStatus != 0){
					insertStatus = 1;
				}
			}
			logger.info("In DATADAOIMPL "+insertStatus);
		}catch(Exception e){
			insertStatus = 0;
			logger.error(e);
		}finally{
			if (null != ps) {
				try {
					ps.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}	
			if (null != dbConnection) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
		}
		return insertStatus;
	}


	@Override
	public int batchInsertForEmployeeQualificationDetails(List<Qualification> employeeQualificationList, String updatedBy) throws CustomException {
		Connection dbConnection = null;
		PreparedStatement ps = null;
		int qualificationInsertStatus = 0;
		try{
			dbConnection = dataSource.getConnection();			
			for(Qualification qualification : employeeQualificationList){				
				ps = dbConnection.prepareStatement(batchInsertEmployeeQualification);
				ps.setString(1, "QUALIFI-OBJ");
				ps.setString(2, updatedBy);
				ps.setString(3, qualification.getQualificationName());
				ps.setString(4, qualification.getQualificationName());
				ps.setString(5, qualification.getPassingYear());
				ps.setString(6, qualification.getSchoolCollege());
				ps.setString(7, qualification.getBoardUniversity());
				ps.setString(8, qualification.getUserId());
				ps.setString(9, qualification.getSpecialization());
				ps.setString(10, qualification.getMarks());
				ps.setString(11, qualification.getQualificationType());
				
				qualificationInsertStatus = ps.executeUpdate();
			}
			logger.info("In DATADAOIMPL "+qualificationInsertStatus);
		}catch(Exception e){
			qualificationInsertStatus = 0;
			logger.error(e);
		}finally{
			if (null != ps) {
				try {
					ps.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}	
			if (null != dbConnection) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
		}
		return qualificationInsertStatus;
	}


	@Override
	public int batchInsertForEmployeeOrganizationDetails(List<Organization> employeeOrganizationList, String updatedBy) throws CustomException {
		Connection dbConnection = null;
		PreparedStatement ps = null;
		int employeeOrganizationInsertStatus = 0;
		try{
			dbConnection = dataSource.getConnection();			
			for(Organization organization : employeeOrganizationList){					
				ps = dbConnection.prepareStatement(batchInsertForEmployeeOrganization);
				ps.setString(1, "PREV-ORG-OBJ");
				ps.setString(2, updatedBy);
				ps.setString(3, organization.getOrganizationName());
				ps.setString(4, organization.getOrganizationName());
				ps.setString(5, organization.getOrganizationWebSite());
				ps.setString(6, organization.getOrganizationContactNo());
				ps.setString(7, organization.getFromDate());
				ps.setString(8, organization.getToDate());
				ps.setString(9, organization.getUserId());
				
				employeeOrganizationInsertStatus = ps.executeUpdate();
			}
			logger.info("In DATADAOIMPL "+employeeOrganizationInsertStatus);
		}catch(Exception e){
			employeeOrganizationInsertStatus = 0;
			logger.error(e);
		}finally{
			if (null != ps) {
				try {
					ps.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}	
			if (null != dbConnection) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
		}
		return employeeOrganizationInsertStatus;
	}


	@Override
	public int batchInsertForEmployeePublicationDetails(List<Publication> employeePublicationList, String updatedBy) throws CustomException {
		Connection dbConnection = null;
		PreparedStatement ps = null;
		int employeePublicationInsertStatus = 0;
		try{
			dbConnection = dataSource.getConnection();			
			for(Publication publication : employeePublicationList){					
				ps = dbConnection.prepareStatement(batchInsertForEmployeePublication);
				ps.setString(1, "PUBLICA-OBJ");
				ps.setString(2, updatedBy);
				ps.setString(3, publication.getPublicationName());
				ps.setString(4, publication.getPublicationName());
				ps.setString(5, publication.getCoPublisher());
				ps.setString(6, publication.getDateOfPublication());
				ps.setString(7, publication.getUserId());
				
				employeePublicationInsertStatus = ps.executeUpdate();
			}
			logger.info("In DATADAOIMPL "+employeePublicationInsertStatus);
		}catch(Exception e){
			employeePublicationInsertStatus = 0;
			logger.error(e);
		}finally{
			if (null != ps) {
				try {
					ps.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}	
			if (null != dbConnection) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
		}
		return employeePublicationInsertStatus;
	}


	@Override
	public int batchInsertForEmployeeDependents(List<EmployeeDependent> employeeDependentList, String updatedBy) throws CustomException {
		Connection dbConnection = null;
		PreparedStatement ps = null;
		int employeeDependentInsertStatus = 0;
		try{
			dbConnection = dataSource.getConnection();			
			for(EmployeeDependent employeeDependent : employeeDependentList){					
				ps = dbConnection.prepareStatement(batchInsertForEmployeeDependent);
				ps.setString(1, "EMP-CHILD-OBJ");
				ps.setString(2, updatedBy);
				ps.setString(3, employeeDependent.getChildName());
				ps.setString(4, employeeDependent.getChildGender());
				ps.setString(5, employeeDependent.getChildDOB());
				ps.setString(6, employeeDependent.getUserId());
				
				employeeDependentInsertStatus = ps.executeUpdate();
			}
			logger.info("In DATADAOIMPL "+employeeDependentInsertStatus);
		}catch(Exception e){
			employeeDependentInsertStatus = 0;
			logger.error(e);
		}finally{
			if (null != ps) {
				try {
					ps.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}	
			if (null != dbConnection) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
		}
		return employeeDependentInsertStatus;
	}




@Override
	public int batchInsertForStudentSubjectMapp(List<StudentSubjectMapping> studentSubjectMappList, String updatedBy) throws CustomException {
		Connection dbConnection = null;
		PreparedStatement ps = null;
		int studentSubjectMappInsertStatus = 0;
		int insertStatus = 0;
		try{
			dbConnection = dataSource.getConnection();			
			for(StudentSubjectMapping studentSubjectMapping : studentSubjectMappList){
				
				ps = dbConnection.prepareStatement(batchInsertStudentSubjectMapping);
				ps.setString(1, "Student-Subject-Mapp-OBJ");
				ps.setString(2, updatedBy);
				ps.setInt(3, studentSubjectMapping.getSerialId());
				ps.setString(4, studentSubjectMapping.getSubject());
				
				studentSubjectMappInsertStatus = ps.executeUpdate();				
			}
			if(studentSubjectMappInsertStatus != 0){
				insertStatus = 1;
			}
			logger.info("In DATADAOIMPL "+insertStatus);
		}catch(Exception e){
			insertStatus = 0;
			logger.error(e);
		}finally{
			if (null != ps) {
				try {
					ps.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}	
			if (null != dbConnection) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
		}
		return insertStatus;
	}
	

@Override
public int batchInsertForStudentDetails(List<Student> studentDetailsList, String updatedBy) throws CustomException {
	Connection dbConnection = null;
	PreparedStatement ps = null;
	int studentInsertStatus = 0;
	int rollNumInsertStatus = 0;
	int insertStatus = 0;
	try{
		dbConnection = dataSource.getConnection();			
		for(Student student : studentDetailsList){						
			Resource resource = student.getResource();
			resource.setUpdatedBy(updatedBy);
			resource.setObjectId("STUD-OBJ");
			
			ps = dbConnection.prepareStatement(batchInsertStudentDetails);
			ps.setString(1, resource.getObjectId());
			ps.setString(2, updatedBy);
			ps.setString(3, resource.getGender());
			ps.setString(4, student.getDateOfAdmission());
			ps.setInt(5, student.getRollNumber());		
			ps.setString(6, resource.getFirstName());
			ps.setString(7, resource.getMiddleName());
			ps.setString(8, resource.getLastName());				
			ps.setString(9, resource.getDateOfBirth());				
			ps.setString(10, resource.getFatherFirstName());
			ps.setString(11, resource.getFatherMiddleName());
			ps.setString(12, resource.getFatherLastName());				
			ps.setInt(13, student.getFatherIncome());				
			ps.setString(14, resource.getMotherFirstName());
			ps.setString(15, resource.getMotherMiddleName());
			ps.setString(16, resource.getMotherLastName());				
			ps.setInt(17, student.getMotherIncome());
			ps.setString(18, student.getGuardianFirstName());
			ps.setString(19, student.getGuardianMiddleName());
			ps.setString(20, student.getGuardianLastName());				
			ps.setString(21, resource.getEmailId());
			ps.setString(22, resource.getBloodGroup());
			ps.setString(23, resource.getMotherTongue());
			ps.setString(24, resource.getReligion());
			ps.setString(25, resource.getNationality());
			ps.setString(26, resource.getCategory());
			ps.setString(27, student.getStandard());
			ps.setString(28, student.getSection());
			ps.setString(29, student.getHouse());
			ps.setString(30, student.getStateOfDomicile());
			ps.setString(31, resource.getBankName());
			ps.setString(32, resource.getBankBranch());
			ps.setString(33, resource.getAccountNumber());
			ps.setString(34, student.getScholarship());
			ps.setBoolean(35, resource.getFatherInDefence());
			ps.setString(36, resource.getFatherServiceStatus());
			ps.setString(37, resource.getFatherDefenceCategory());
			ps.setString(38, resource.getFatherRank());
			ps.setString(39, resource.getFatherMobile());
			ps.setString(40, resource.getFatherEmail());
			ps.setString(41, resource.getMotherMobile());
			ps.setString(42, resource.getMotherEmail());				
			ps.setInt(43, student.getStudentIncome());
			ps.setInt(44, student.getFamilyIncome());
			ps.setString(45, student.getGuardianMobile());
			ps.setString(46, student.getGuardianEmail());
			ps.setString(47, student.getPreviousSchoolPhone());
			ps.setString(48, student.getPreviousSchoolWebsite());
			ps.setString(49, student.getPreviousSchoolEmail());
			ps.setString(50, student.getPreviousSchoolAddress());
			ps.setString(51, student.getPreviousSchoolName());
			ps.setInt(52, student.getRollNumber());
			ps.setString(53, resource.getMedicalStatus());
			
			studentInsertStatus = ps.executeUpdate();
			if(studentInsertStatus != 0){
				ps = dbConnection.prepareStatement(batchInsertUsedRollNumber);
				ps.setInt(1, student.getRollNumber());
				
				rollNumInsertStatus = ps.executeUpdate();
			}
		}
		if(rollNumInsertStatus != 0){
			insertStatus = 1;
		}
		logger.info("In DATADAOIMPL "+insertStatus);
	}catch(Exception e){
		insertStatus = 0;
		logger.error(e);
	}finally{
		if (null != ps) {
			try {
				ps.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}	
		if (null != dbConnection) {
			try {
				dbConnection.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
	}
	return insertStatus;
}


@Override
public int batchInsertForStudentAddressDetails(List<Address> studentAddressList, String updatedBy) throws CustomException {
	Connection dbConnection = null;
	PreparedStatement ps = null;
	int presentAddressInsertStatus = 0;
	int permanentAddressInsertStatus = 0;
	int guardianAddressInsertStatus = 0;
	int insertStatus = 0;
	try{
		dbConnection = dataSource.getConnection();			
		for(Address address : studentAddressList){						
			Address addr = new Address();
			addr.setUpdatedBy(updatedBy);
			
			ps = dbConnection.prepareStatement(batchInsertStudentAddress);
			ps.setString(1, "STU-PRESENT-ADDR-OBJ");
			ps.setString(2, addr.getUpdatedBy());
			ps.setString(3, "PRESENT-CODE");
			ps.setString(4, address.getPresentAddressLine());
			ps.setString(5, address.getPresentAddressLandmark());
			ps.setString(6, address.getPresentAddressCityVillage());
			ps.setString(7, address.getPresentAddressDistrict());
			ps.setString(8, address.getPresentAddressPinCode());
			ps.setString(9, address.getPresentAddressState());
			ps.setString(10, address.getPresentAddressCountry());
			ps.setString(11, address.getPresentAddressPoliceStation());
			ps.setString(12, address.getPresentAddressPostOffice());
			ps.setString(13, "PRESENT");
			ps.setString(14, address.getPresentAddressRailwayStation());
			ps.setString(15, address.getPresentAddressPhone());
			ps.setInt(16, address.getRollNumber());
			
			presentAddressInsertStatus = ps.executeUpdate();
			if(presentAddressInsertStatus != 0){
				ps = dbConnection.prepareStatement(batchInsertStudentAddress);
				ps.setString(1, "STU-PERMANENT-ADDR-OBJ");
				ps.setString(2, addr.getUpdatedBy());
				ps.setString(3, "PERMANENT-CODE");
				ps.setString(4, address.getPermanentAddressLine());
				ps.setString(5, address.getPermanentAddressLandmark());
				ps.setString(6, address.getPermanentAddressCityVillage());
				ps.setString(7, address.getPermanentAddressDistrict());
				ps.setString(8, address.getPermanentAddressPinCode());
				ps.setString(9, address.getPermanentAddressState());
				ps.setString(10, address.getPermanentAddressCountry());
				ps.setString(11, address.getPermanentAddressPoliceStation());
				ps.setString(12, address.getPermanentAddressPostOffice());
				ps.setString(13, "PERMANENT");
				ps.setString(14, address.getPermanentAddressRailwayStation());
				ps.setString(15, address.getPermanentAddressPhone());
				ps.setInt(16, address.getRollNumber());
				
				permanentAddressInsertStatus = ps.executeUpdate();
			}
			if(permanentAddressInsertStatus != 0){
				ps = dbConnection.prepareStatement(batchInsertStudentAddress);
				ps.setString(1, "STU-GUARDIAN-ADDR-OBJ");
				ps.setString(2, addr.getUpdatedBy());
				ps.setString(3, "LOCAL-GUARDIAN-CODE");
				ps.setString(4, address.getGuardianAddressLine());
				ps.setString(5, address.getGuardianAddressLandmark());
				ps.setString(6, address.getGuardianAddressCityVillage());
				ps.setString(7, address.getGuardianAddressDistrict());
				ps.setString(8, address.getGuardianAddressPinCode());
				ps.setString(9, address.getGuardianAddressState());
				ps.setString(10, address.getGuardianAddressCountry());
				ps.setString(11, address.getGuardianAddressPoliceStation());
				ps.setString(12, address.getGuardianAddressPostOffice());
				ps.setString(13, "LOCAL GUARDIAN");
				ps.setString(14, address.getGuardianAddressRailwayStation());
				ps.setString(15, address.getGuardianAddressPhone());
				ps.setInt(16, address.getRollNumber());
				
				guardianAddressInsertStatus = ps.executeUpdate();
			}
			if(guardianAddressInsertStatus != 0){
				insertStatus = 1;					
			}
		}
		logger.info("In DATADAOIMPL "+insertStatus);
	}catch(Exception e){
		insertStatus = 0;
		logger.error(e);
	}finally{
		if (null != ps) {
			try {
				ps.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}	
		if (null != dbConnection) {
			try {
				dbConnection.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
	}
	return insertStatus;
}

@Override
public int batchInsertForStudentResult(List<StudentResult> studentResultList, String updatedBy) {
	Connection dbConnection = null;
	PreparedStatement ps = null;
	int resultInsertStatus = 0;
	int insertStatus = 0;
	try{
		//System.out.println("List Size While Inserting::"+studentResultList.size());
		dbConnection = dataSource.getConnection();			
		for(StudentResult studentResult : studentResultList){
			//System.out.println("Loop:"+studentResult.getRollNumber());
			PreparedStatement ps0 = dbConnection.prepareStatement(checkForExistingCBSEMarks);
			ps0.setString(1, studentResult.getRollNumber());
			ps0.setString(2, studentResult.getStandard());
			ps0.setString(3, studentResult.getSection());
			ps0.setString(4, studentResult.getExam());
			ps0.setString(5, studentResult.getSubject());
			
			ResultSet rs = ps0.executeQuery();
			while(rs.next()){			//INSERT
				//System.out.println("checker ::"+rs.getInt("num"));
				if(rs.getInt("num")==0){
					//System.out.println("INSERT");
					ps = dbConnection.prepareStatement(batchInsertCBSEMarks);
					ps.setString(1, "ResultExelObject-OBJ");
					ps.setString(2, updatedBy);
					ps.setString(3, studentResult.getRollNumber());
					ps.setString(4, studentResult.getRollNumber());
					ps.setString(5, studentResult.getStandard());
					ps.setString(6, studentResult.getSection());
					ps.setString(7, studentResult.getExam());
					ps.setString(8, studentResult.getSubject());
					ps.setInt(9, studentResult.getTheory());
					ps.setInt(10, studentResult.getTheoryPass());
					ps.setInt(11, studentResult.getPractical());
					ps.setInt(12, studentResult.getPracticalPass());
					ps.setInt(13, studentResult.getTotal());
					ps.setInt(14, studentResult.getPass());
					if(null!=studentResult.getTheoryObtained())
						ps.setInt(15, studentResult.getTheoryObtained());
					else
						ps.setNull(15, java.sql.Types.INTEGER);
					
					if(null!=studentResult.getPracticalObtained())
						ps.setInt(16, studentResult.getPracticalObtained());
					else
						ps.setNull(16, java.sql.Types.INTEGER);
					
					if(null!=studentResult.getTotalObtained())
						ps.setInt(17, studentResult.getTotalObtained());
					else
						ps.setNull(17, java.sql.Types.INTEGER);
					
					ps.setString(18, studentResult.getPassFail());
					ps.setString(19, studentResult.getExam());
					ps.setDouble(20, studentResult.getWeightageObtained());
					ps.setString(21, studentResult.getExam());
					ps.setString(22, updatedBy);
					
					resultInsertStatus = ps.executeUpdate();
				}else{
					//System.out.println("UPDATE");
					ps = dbConnection.prepareStatement(batchUpdateCBSEMarks);
					ps.setString(1, updatedBy);
					ps.setInt(2, studentResult.getTheory());					
					ps.setInt(3, studentResult.getPractical());
					ps.setInt(4, studentResult.getTotal());
					ps.setInt(5, studentResult.getPass());
					if(null!=studentResult.getTheoryObtained())
						ps.setInt(6, studentResult.getTheoryObtained());
					else
						ps.setNull(6, java.sql.Types.INTEGER);
					
					if(null!=studentResult.getPracticalObtained())
						ps.setInt(7, studentResult.getPracticalObtained());
					else
						ps.setNull(7, java.sql.Types.INTEGER);
					
					if(null!=studentResult.getTotalObtained())
						ps.setInt(8, studentResult.getTotalObtained());
					else
						ps.setNull(8, java.sql.Types.INTEGER);
					
					ps.setString(9, studentResult.getPassFail());
					ps.setInt(10, studentResult.getTheoryPass());
					ps.setInt(11, studentResult.getPracticalPass());
					ps.setString(12, studentResult.getExam());
					ps.setDouble(13, studentResult.getWeightageObtained());
					ps.setString(14, studentResult.getExam());
					ps.setString(15, studentResult.getRollNumber());
					ps.setString(16, studentResult.getStandard());
					ps.setString(17, studentResult.getSection());					
					ps.setString(18, studentResult.getExam());
					ps.setString(19, studentResult.getSubject());						
					
					resultInsertStatus = ps.executeUpdate();
				}
			}			
		}
		if(resultInsertStatus != 0){
			insertStatus = 1;
		}
		logger.info("In DATADAOIMPL "+insertStatus);
	}catch(Exception e){
		insertStatus = 0;
		logger.error(e);
		e.printStackTrace();
	}finally{
		if (null != ps) {
			try {
				ps.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}	
		if (null != dbConnection) {
			try {
				dbConnection.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
	}
	return insertStatus;
}


@Override
public int batchInsertForStudentResultUserExam(List<StudentResult> studentResultList, String updatedBy) {
	Connection dbConnection = null;
	PreparedStatement ps = null;
	int resultInsertStatus = 0;
	int insertStatus = 0;
	try{
		//System.out.println("List Size While Inserting::"+studentResultList.size());
		dbConnection = dataSource.getConnection();			
		for(StudentResult studentResult : studentResultList){
			//System.out.println("Loop:"+studentResult.getRollNumber());
			PreparedStatement ps0 = dbConnection.prepareStatement(checkForExistingUserMarks);
			ps0.setString(1, studentResult.getRollNumber());
			ps0.setString(2, studentResult.getStandard());
			ps0.setString(3, studentResult.getSection());
			ps0.setString(4, studentResult.getExam());
			ps0.setString(5, studentResult.getSubject());
			
			ResultSet rs = ps0.executeQuery();
			while(rs.next()){			//INSERT
				//System.out.println("checker ::"+rs.getInt("num"));
				if(rs.getInt("num")==0){
					//System.out.println("INSERT");
					ps = dbConnection.prepareStatement(batchInsertUserMarks);
					ps.setString(1, "ResultExelObject-OBJ");
					ps.setString(2, updatedBy);
					ps.setString(3, studentResult.getRollNumber());
					ps.setString(4, studentResult.getRollNumber());
					ps.setString(5, studentResult.getStandard());
					ps.setString(6, studentResult.getSection());
					ps.setString(7, studentResult.getExam());
					ps.setString(8, studentResult.getSubject());
					ps.setInt(9, studentResult.getTheory());
					ps.setInt(10, studentResult.getTheoryPass());
					ps.setInt(11, studentResult.getPractical());
					ps.setInt(12, studentResult.getPracticalPass());
					ps.setInt(13, studentResult.getTotal());
					ps.setInt(14, studentResult.getPass());
					if(null!=studentResult.getTheoryObtained())
						ps.setInt(15, studentResult.getTheoryObtained());
					else
						ps.setNull(15, java.sql.Types.INTEGER);
					
					if(null!=studentResult.getPracticalObtained())
						ps.setInt(16, studentResult.getPracticalObtained());
					else
						ps.setNull(16, java.sql.Types.INTEGER);
					
					if(null!=studentResult.getTotalObtained())
						ps.setInt(17, studentResult.getTotalObtained());
					else
						ps.setNull(17, java.sql.Types.INTEGER);
					
					ps.setString(18, studentResult.getPassFail());
					ps.setString(19, updatedBy);
					
					resultInsertStatus = ps.executeUpdate();
				}else{
					//System.out.println("UPDATE");
					ps = dbConnection.prepareStatement(batchUpdateUserMarks);
					ps.setString(1, updatedBy);
					ps.setInt(2, studentResult.getTheory());					
					ps.setInt(3, studentResult.getPractical());
					ps.setInt(4, studentResult.getTotal());
					ps.setInt(5, studentResult.getPass());
					if(null!=studentResult.getTheoryObtained())
						ps.setInt(6, studentResult.getTheoryObtained());
					else
						ps.setNull(6, java.sql.Types.INTEGER);
					
					if(null!=studentResult.getPracticalObtained())
						ps.setInt(7, studentResult.getPracticalObtained());
					else
						ps.setNull(7, java.sql.Types.INTEGER);
					
					if(null!=studentResult.getTotalObtained())
						ps.setInt(8, studentResult.getTotalObtained());
					else
						ps.setNull(8, java.sql.Types.INTEGER);
					
					ps.setString(9, studentResult.getPassFail());
					ps.setInt(10, studentResult.getTheoryPass());
					ps.setInt(11, studentResult.getPracticalPass());
					ps.setString(12, studentResult.getRollNumber());
					ps.setString(13, studentResult.getStandard());
					ps.setString(14, studentResult.getSection());					
					ps.setString(15, studentResult.getExam());
					ps.setString(16, studentResult.getSubject());						
					
					resultInsertStatus = ps.executeUpdate();
				}
			}			
		}
		if(resultInsertStatus != 0){
			insertStatus = 1;
		}
		logger.info("In DATADAOIMPL "+insertStatus);
	}catch(Exception e){
		insertStatus = 0;
		logger.error(e);
		e.printStackTrace();
	}finally{
		if (null != ps) {
			try {
				ps.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}	
		if (null != dbConnection) {
			try {
				dbConnection.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
	}
	return insertStatus;
}


@Override
public int batchInsertForAssetDetails(List<Asset> assetDetailsList, String updatedBy) throws CustomException {
	Connection dbConnection = null;
	PreparedStatement ps = null;
	int assetInsertStatus = 0;
	try{
		dbConnection = dataSource.getConnection();
		logger.info("In DATADAOIMPL   ..............   LIST  SIZE =>  "+assetDetailsList.size());
		for(Asset asset : assetDetailsList){
			ps = dbConnection.prepareStatement(checkAssetName);
			ps.setString(1, asset.getAssetName());
			ResultSet rs = ps.executeQuery();
			if(!rs.next()){	
				asset.setUpdatedBy(updatedBy);
				asset.setObjectId("ASSET-EXCEL-OBJ");
				asset.setAssetProperty("");
				ps.close();
				ps = dbConnection.prepareStatement(batchInsertAssetDetails);
				ps.setString(1, asset.getObjectId());
				ps.setString(2, asset.getUpdatedBy());
				ps.setString(3, asset.getAssetName());
				ps.setString(4, asset.getDepartment().getDepartmentName());
				ps.setString(5, asset.getAssetProperty());
				ps.setDouble(6, asset.getAssetPrice());
				ps.setString(7, asset.getPurchaseDate());
				ps.setString(8, asset.getLedgerNumber());
				ps.setString(9, asset.getPageNumber());
				ps.setInt(10, asset.getLedgerBalance());
				ps.setString(11, asset.getAssetSubType());
				ps.setString(12, asset.getAssetType());
				ps.setString(13, asset.getAssetUnit());
				
				assetInsertStatus = ps.executeUpdate();
			}
		}
	}catch(Exception e){
		e.printStackTrace();
		assetInsertStatus = 0;
		logger.info("*** EXCEPTION ..... In DATADAOIMPL : "+assetInsertStatus);
		logger.error(e);
	}finally{
		if (null != ps) {
			try {
				ps.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}	
		if (null != dbConnection) {
			try {
				dbConnection.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
	}
	return assetInsertStatus;
}


@Override
public int batchInsertResourceAttendance(List<ResourceAttendance> resourceAttendanceList,String updatedBy) {
	Connection dbConnection = null;
	PreparedStatement ps=null;
	PreparedStatement psForUpdate=null;
	PreparedStatement psForShiftDetails =null;
	PreparedStatement psForShiftForResource =null;
	int insertStatus=0;
	try{
		dbConnection=dataSource.getConnection();
		Map<String, Object> shiftDetailsMap = new HashMap<String, Object>();
		ResourceType resourceTypeObj = new ResourceType();
	//	psForShiftDetails = dbConnection.prepareStatement(GetShiftDetails);
		
	//	ResultSet rsForShift = psForShiftDetails.executeQuery();
//		while(rsForShift.next()){
//			String shiftSlot = rsForShift.getString("shift_start_time") + "~" + rsForShift.getString("shift_end_time");
//			shiftDetailsMap.put(rsForShift.getString("shift_code"), shiftSlot);
//		}
		
		
		for(ResourceAttendance resourceAttendance : resourceAttendanceList){
			resourceAttendance.setAttendanceObjectId("STUD");
			resourceAttendance.setAttendanceObjectId(utility.getBase64EncodedID("DataDAOImpl"));
			resourceAttendance.setUpdatedBy(updatedBy);
			resourceAttendance.setResourceId(resourceAttendance.getResourceId());
			resourceTypeObj.setResourceTypeName("TEACHING STAFF");
			resourceAttendance.setResourceType(resourceTypeObj);
		//	resourceAttendance.setYear(resourceAttendance.getYear());
		//	resourceAttendance.setMonth(resourceAttendance.getMonth());
			resourceAttendance.setAttendanceDay(resourceAttendance.getAttendanceDay());
			resourceAttendance.setAttendanceMonth(resourceAttendance.getAttendanceMonth());
			resourceAttendance.setAttendanceYear(resourceAttendance.getAttendanceYear());
		//	resourceAttendance.setSwipeTimeSlot(resourceAttendance.getSwipeTimeSlot());
		//	resourceAttendance.setAttendanceFlag(resourceAttendance.isAttendanceFlag());
		//	resourceAttendance.setWeekDay(resourceAttendance.getWeekDay());
		//	WorkShift workShiftObj = new WorkShift();
		//	String[] shiftTimeSlotArray = resourceAttendance.getSwipeTimeSlot().split(" ");
		//	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		//	SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm a");
			
	    	/*psForUpdate = dbConnection.prepareStatement(UpdateTeacherAttendance);
			
			if(!resourceAttendance.getMonth().equalsIgnoreCase("")){
				psForUpdate.setString(1, resourceAttendance.getMonth().trim());
				psForUpdate.setString(2, resourceAttendance.getYear().trim());
				psForUpdate.setString(3, resourceAttendance.getResourceId().trim());
			}
			ResultSet rs = psForUpdate.executeQuery();*/
			
			/*if(!resourceAttendance.getSwipeTimeSlot().trim().equalsIgnoreCase("")){
			for (Entry<String, Object> entry : shiftDetailsMap.entrySet()) {
				System.out.println("Key : " + entry.getKey() + " Value : "
					+ entry.getValue());
				String[] shiftTimeSplited = ((String) entry.getValue()).split("~");
	        //	System.out.println("shift time from db  "+shiftTimeSplited[0]+";;"+shiftTimeSplited[1]);
				Date shiftStartTime = df1.parse("2012-01-24 "+shiftTimeSplited[0]);
				Date shiftEndTime = df.parse("2012-01-24 "+shiftTimeSplited[1]);
				for(int index = 0;index< shiftTimeSlotArray.length;index++){
				//	System.out.println("first time "+shiftTimeSlotArray[0]);
				//	System.out.println("last time "+shiftTimeSlotArray[shiftTimeSlotArray.length-1]);
					if(shiftTimeSlotArray[0] != ""){
					
						Date firstSwipeIn = df.parse("2012-01-24 "+shiftTimeSlotArray[0]);
						Date lastSwipeOut = df.parse("2012-01-24 "+shiftTimeSlotArray[shiftTimeSlotArray.length-1]);
					//	System.out.println("firstSwipeIn"+firstSwipeIn);
						System.out.println("shiftStartTime"+shiftStartTime);
						System.out.println("shiftEndTime"+shiftEndTime);
						System.out.println("firstSwipeIn"+firstSwipeIn);
						System.out.println("lastSwipeOut"+lastSwipeOut);
						System.out.println("shiftStartTime.getTime()"+shiftStartTime.getTime());
						System.out.println("shiftEndTime.getTime()"+shiftEndTime.getTime());
						System.out.println("firstSwipeIn.getTime()"+firstSwipeIn.getTime());
						System.out.println("lastSwipeOut.getTime()"+lastSwipeOut.getTime());
			    		
			    		
						boolean flagStartStatus = (shiftStartTime.getTime() <= firstSwipeIn.getTime()) && (shiftEndTime.getTime() >= lastSwipeOut.getTime());
						boolean flagEndStatus = (shiftStartTime.getTime() <= lastSwipeOut.getTime()) && (shiftEndTime.getTime() >= lastSwipeOut.getTime());
						
						if(flagStartStatus == true && flagEndStatus == true){
							System.out.println("in time "+entry.getKey());
			    			workShiftObj.setWorkShiftCode(entry.getKey());
			    			resourceAttendance.setWorkShift(workShiftObj);
						}
					}
				}
			}
			//if(!rs.next()){
				System.out.println("not exist teacher in not null");
			ps = dbConnection.prepareStatement(InsertTeacherAttendance);
			
			ps.setString(1, resourceAttendance.getAttendanceObjectId().trim());
			ps.setString(2, resourceAttendance.getUpdatedBy().trim());
			ps.setString(3, resourceAttendance.getAttendanceDay().trim());
			ps.setString(4, resourceAttendance.getResourceType().getResourceTypeName().trim());
			ps.setString(5, resourceAttendance.getResourceId().trim());
			ps.setString(6, resourceAttendance.getMonth().trim());
			ps.setString(7, resourceAttendance.getYear().trim());
			ps.setString(8, resourceAttendance.getWorkShift().getWorkShiftCode().trim());
			ps.setString(9, resourceAttendance.getSwipeTimeSlot().trim());
			ps.setBoolean(10, resourceAttendance.isAttendanceFlag());
			ps.setString(11, resourceAttendance.getWeekDay().trim());

			if(ps.execute())
				insertStatus=1;
			//}			
			if(rs.next()){
				System.out.println("already exist teacher in not null");
			}
		}*/
		//if(resourceAttendance.getSwipeTimeSlot().equalsIgnoreCase("")){
			System.out.println("time slot value is not considered");
			/*psForShiftForResource = dbConnection.prepareStatement(GetAssignedShiftForResource);					
			psForShiftForResource.setString(1, resourceAttendance.getResourceId().trim());					
			ResultSet rsForAssignedShift = psForShiftForResource.executeQuery();*/
	
		//	while(rsForAssignedShift.next()){
				System.out.println("in shift");
			//	String assignedShift = rsForAssignedShift.getString("shift_code");
			
			//	workShiftObj.setWorkShiftCode(assignedShift);
    		//	resourceAttendance.setWorkShift(workShiftObj);
    			resourceAttendance.setSwipeTimeSlot(null);
    		//	if(!rs.next()){
					//System.out.println("not exist teacher in null");
					ps = dbConnection.prepareStatement(InsertTeacherAttendance);
					
					ps.setString(1, resourceAttendance.getAttendanceObjectId().trim());
					ps.setString(2, resourceAttendance.getUpdatedBy().trim());
					ps.setString(3, resourceAttendance.getAttendanceDay().trim());
					ps.setString(4, resourceAttendance.getResourceType().getResourceTypeName().trim());
					ps.setString(5, resourceAttendance.getResourceId().trim());
					ps.setString(6, resourceAttendance.getAttendanceMonth().trim());
					ps.setString(7, resourceAttendance.getAttendanceYear().trim());
				//	ps.setString(6, resourceAttendance.getMonth().trim());
				//	ps.setString(7, resourceAttendance.getYear().trim());
				//	ps.setString(8, resourceAttendance.getWorkShift().getWorkShiftCode().trim());
				//	ps.setString(9, resourceAttendance.getSwipeTimeSlot());
				//	ps.setBoolean(10, resourceAttendance.isAttendanceFlag());
				//	ps.setString(11, resourceAttendance.getWeekDay().trim());
					System.out.println("string::"+ps.toString());
					if(ps.execute()){
						insertStatus = 1;
					}
			//	}			
				/*if(rs.next()){
					System.out.println("already exist teacher in null");
				}*/
		//	}
			
		//}
				
		}
	}
	catch(Exception e){
		e.printStackTrace();
		insertStatus=0;
	}finally{
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	return  insertStatus;
}
	
}
