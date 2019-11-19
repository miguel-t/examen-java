package com.alMundo.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.alMundo.employee.Director;
import com.alMundo.employee.Employee;
import com.alMundo.employee.Operator;
import com.alMundo.employee.Supervisor;
import com.alMundo.enums.EmployeeStatusEnum;
import com.alMundo.enums.EmployeeTypeEnum;

public class EmployeeHelper {

	List<Employee> employees ;
	
	public  EmployeeHelper(Configuration config){
		 employees =  new ArrayList<Employee>();
		
		//Operadores 
		int size =  config.getOperador();
		Operator operator;
		for (int i = 0; i < size; i++) {
			operator = new Operator();
			operator.setName(new StringBuilder("Operador ").append(String.valueOf(i)).toString());
			operator.setId(i);
			employees.add(operator);
		}
		
		//Directores
		size = config.getDirector();
		Director director;
		for (int i = 0; i < size; i++) {
			director = new Director();
			director.setName(new StringBuilder("Director ").append(String.valueOf(i)).toString());
			director.setId(i);
			employees.add(director);
		}
		//Supervisores
		size = config.getSupervisor();
		Supervisor supervisor;
		for (int i = 0; i < size; i++) {
			supervisor = new Supervisor();
			supervisor.setName(new StringBuilder("Supervisor ").append(String.valueOf(i)).toString());
			supervisor.setId(i);
			employees.add(supervisor);
		}
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	
	/**
	 * Orden de atencion : 
	 * 1. Operador
	 * 2.Supervisor
	 * 3.Director
	 * @param employees
	 * @return
	 */
	public synchronized Employee getNextEmploye() {
		
		
		// Find any Operator
		Optional<Employee>  resultOptional = employees.stream()
													  .filter(empl ->  empl.isFree() && empl.getTypeEmployee() == EmployeeTypeEnum.OPERADOR)
													  .findAny();
		Employee employeeFind;
		if (resultOptional.isPresent()){
			employeeFind = resultOptional.get();
			employeeFind.setStatus(EmployeeStatusEnum.BUSY);
			return employeeFind;
		}
		// Find any Supervisor
		resultOptional = employees.stream()
								  .filter(empl ->  empl.isFree() && empl.getTypeEmployee() == EmployeeTypeEnum.SUPERVISOR)
								  .findAny();
		if (resultOptional.isPresent()){
			employeeFind = resultOptional.get();
			employeeFind.setStatus(EmployeeStatusEnum.BUSY);
		
			return employeeFind;
		}
		// Find any Director
		resultOptional = employees.stream()
								  .filter(empl ->  empl.isFree() && empl.getTypeEmployee() == EmployeeTypeEnum.DIRECTOR)
								  .findAny();
		if (resultOptional.isPresent()){
			employeeFind = resultOptional.get();
			employeeFind.setStatus(EmployeeStatusEnum.BUSY);
		   return employeeFind;
		}
		//Nadie disponible
		return null;
	}

	public static int getRandomNumberInts(int min, int max){
		Random random = new Random();
		return random.ints(min,(max+1)).findFirst().getAsInt();

	}

}
