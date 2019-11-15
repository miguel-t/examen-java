package com.alMundo;

import com.alMundo.service.EmployeeService;

public abstract class Employee implements EmployeeService {

	private String id;
	
	private String name ;
	
	private EmployeeStatusEnum status;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public EmployeeStatusEnum getStatus() {
		return status;
	}
	
	public void setStatus(EmployeeStatusEnum status) {
		this.status = status;
	}

	public abstract String getTypeEmployee();



}
