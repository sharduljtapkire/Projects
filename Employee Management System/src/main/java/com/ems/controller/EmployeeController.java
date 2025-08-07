package com.ems.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.exception.ResourceNotFoundException;
import com.ems.model.Employee;
import com.ems.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:3000/")
public class EmployeeController {

	
	@Autowired
	private EmployeeRepository empRepo;
	
	
	//get All Employee 
	@GetMapping("/employees")
	public List<Employee> getAllEmployee(){
		return empRepo.findAll();
	}
	
	//Add Employee
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee emp) {
		return empRepo.save(emp);
		
	}
	
	//getEmployee By ID
	@GetMapping("/employees/{empId}")
	public ResponseEntity<Employee> getEmpById(@PathVariable Long empId) {
		
		Employee emp = empRepo.findById(empId)
				.orElseThrow(()->new ResourceNotFoundException("Employee Not Exist with id :"+empId));
		return ResponseEntity.ok(emp);
	}
	
	//update employee 
	@PutMapping("/employees/{empId}")
	public ResponseEntity<Employee> updateEmp(@PathVariable Long empId,@RequestBody Employee empDetails){
		Employee emp = empRepo.findById(empId)
				.orElseThrow(()->new ResourceNotFoundException("Employee Not Exist with id:"+empId));
		
		emp.setFirstName(empDetails.getFirstName());
		emp.setLastName(empDetails.getLastName());
		emp.setEmailId(empDetails.getEmailId());
		emp.setDesignation(empDetails.getDesignation());
		emp.setDepartment(empDetails.getDepartment()); 
		
		Employee updatedEmp =  empRepo.save(emp);
		return ResponseEntity.ok(updatedEmp);		
	}
	
	//Delete Employee
	@DeleteMapping("/employees/{empId}")
	public ResponseEntity< Map<String,Boolean>> deleteEmployee(@PathVariable Long empId){
		Employee emp = empRepo.findById(empId)
				.orElseThrow(()->new ResourceNotFoundException("Employee Not Exist with id:"+empId));
		
		empRepo.delete(emp);
		Map<String,Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
