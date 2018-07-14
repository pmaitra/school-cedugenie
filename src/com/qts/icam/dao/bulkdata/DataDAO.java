package com.qts.icam.dao.bulkdata;

import java.util.List;


import com.qts.icam.model.academics.StudentResult;
import com.qts.icam.model.administrator.Role;
import com.qts.icam.model.backoffice.ResourceAttendance;
import com.qts.icam.model.backoffice.StudentSubjectMapping;
import com.qts.icam.model.common.Address;
import com.qts.icam.model.common.Asset;
import com.qts.icam.model.common.Student;
import com.qts.icam.model.erp.Employee;
import com.qts.icam.model.erp.EmployeeDependent;
import com.qts.icam.model.erp.Organization;
import com.qts.icam.model.erp.Publication;
import com.qts.icam.model.erp.Qualification;
import com.qts.icam.model.inventory.Commodity;
import com.qts.icam.model.library.Book;
import com.qts.icam.utility.customexception.CustomException;


public interface DataDAO {	

	int insertFunctionalityRoleMappingToDB(List<Role> roleList,String moduleCode, String updatedBy) throws CustomException;
	
	int batchInsertForBookAuthor(List<String> bookAuthorList, String updatedBy) throws CustomException;
	
	int batchInsertForBookPublisher(List<String> publisherList, String updatedBy) throws CustomException;
	
	int batchInsertForBookMedium(List<String> bookMediumList, String updatedBy) throws CustomException;
	
	int batchInsertForBookLanguage(List<String> bookLanguageList, String updatedBy) throws CustomException;
	
//	int batchInsertForBook(List<Book> bookList, String updatedBy) throws CustomException;


	int batchInsertForEmployeeDetails(List<Employee> employeeDetailsList, String updatedBy) throws CustomException;

	int batchInsertForEmployeeAddressDetails(List<Address> employeeAddressList, String updatedBy) throws CustomException;

	int batchInsertForEmployeeQualificationDetails(List<Qualification> employeeQualificationList, String updatedBy) throws CustomException;

	int batchInsertForEmployeeOrganizationDetails(List<Organization> employeeOrganizationList, String updatedBy) throws CustomException;

	int batchInsertForEmployeePublicationDetails(List<Publication> employeePublicationList, String updatedBy) throws CustomException;

	int batchInsertForEmployeeDependents(List<EmployeeDependent> employeeDependentList, String updatedBy) throws CustomException;

	int batchInsertForCity(List<String> cityList, String updatedBy) throws CustomException;

	int batchInsertForDistrict(List<String> districtList, String updatedBy) throws CustomException;

	int batchInsertForStudentSubjectMapp(List<StudentSubjectMapping> studentSubjectMappList, String updatedBy) throws CustomException;
	
	int batchInsertForStudentDetails(List<Student> studentDetailsList, String updatedBy) throws CustomException;

	int batchInsertForStudentAddressDetails(List<Address> studentAddressList, String updatedBy) throws CustomException;

	int batchInsertForStudentResultUserExam(List<StudentResult> studentResultList, String updatedBy);

	int batchInsertForStudentResult(List<StudentResult> studentResultList, String updatedBy);
	
	int batchInsertForAssetDetails(List<Asset> assetDetailsList, String updatedBy) throws CustomException;
	
	int batchInsertResourceAttendance(List<ResourceAttendance> resourceAttendanceList, String updatedBy);
	
}
