package com.alMundo.employee;

import com.alMundo.Call;
import com.alMundo.enums.EmployeeStatusEnum;
import com.alMundo.enums.EmployeeTypeEnum;

public abstract class Employee {

	private Integer id;
	
	private String name ;
	
	private EmployeeStatusEnum status;
	

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
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
	
	public boolean isFree() {
		return this.status == EmployeeStatusEnum.FREE;
	}
	
	public void setStatus(EmployeeStatusEnum status) {
		this.status = status;
	}

	public abstract EmployeeTypeEnum getTypeEmployee();
	
	public abstract void callHandler(Call call);
}
