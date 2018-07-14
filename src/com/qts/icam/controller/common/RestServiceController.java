package com.qts.icam.controller.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qts.icam.model.common.EmployeeRC;
import com.qts.icam.model.common.LeaveRC;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.erp.Employee;
import com.qts.icam.model.erp.Leave;
import com.qts.icam.service.common.CommonService;
import com.qts.icam.service.erp.ERPService;


@Controller
@RequestMapping("/RC")
public class RestServiceController {
	
	@Autowired
	CommonService commonService;
	
	@Autowired
	ERPService erpService;
	
	
	@RequestMapping(value = "/validateUser", method = RequestMethod.POST)
	public @ResponseBody String validateUser(@RequestBody EmployeeRC emp) {
		//System.out.println("validateUser called");
		String message =null;
		//System.out.println("FROM SCREEn:"+emp.getUserId());
			String userName = erpService.serverSideValidationOfUserId(emp.getUserId());
			if(userName != null){
				message="Authorized User";
			}else{
				message="Unauthorized User";
			}
		return message;
	}
	
	
	@RequestMapping("/getLeaveDetailsRC/{userId}")
    public @ResponseBody List<LeaveRC> getLeaveDetailsRC(@PathVariable(value="userId")  String userId) {
		//System.out.println("getLeaveDetailsRC called");
		List<LeaveRC> leaves = new ArrayList<>();
		List<Leave> staffLeaveDetails = commonService.getResourceLeaveDetails(userId);
		for(Leave l : staffLeaveDetails){
			LeaveRC leave = new LeaveRC();
			leave.setLeaveType(l.getLeaveType());
			leave.setTotalAllocatedLeaves(l.getTotalAvailLeave());
			leave.setUsedLeaves(l.getUsedLeave());
			leave.setRemainingLeaves(l.getRemainingLeave());
			leaves.add(leave);
		}
          return  leaves;	   
    }
	
	
	@RequestMapping("/getResourceDetailsRC/{userId}")
    public @ResponseBody EmployeeRC getResourceDetailsRC(@PathVariable(value="userId")  String userId) {
		//System.out.println("getResourceDetailsRC called");
		Resource resource = new Resource();
		resource.setUserId(userId);
		Employee employee = new Employee();
		employee.setResource(resource);
		Employee employeeDetails = erpService.getEmployeeDetails(employee);	
		EmployeeRC emp = new EmployeeRC();
		emp.setUserId(employeeDetails.getResource().getUserId());
		emp.setDesignation(employeeDetails.getDesignation().getDesignationName());
		emp.setFirstName(employeeDetails.getResource().getFirstName());
		emp.setMiddleName(employeeDetails.getResource().getMiddleName());
		emp.setLastName(employeeDetails.getResource().getLastName());
		emp.setGender(employeeDetails.getResource().getGender());
		emp.setDateOfBirh(employeeDetails.getResource().getDateOfBirth());
		return  emp;	   
    }
	

	
	

}
