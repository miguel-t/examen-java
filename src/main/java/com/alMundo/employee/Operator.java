package com.alMundo.employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alMundo.Call;
import com.alMundo.enums.CallStatusEnum;
import com.alMundo.enums.EmployeeStatusEnum;
import com.alMundo.enums.EmployeeTypeEnum;
import com.alMundo.helper.EmployeeHelper;

public class Operator extends Employee  {
	
	private static final Logger LOGGER =  LoggerFactory.getLogger(Operator.class);
	
	public Operator() {
		setStatus(EmployeeStatusEnum.FREE);
	}

	public void callHandler(Call call) {
			LOGGER.debug("callHandler Operator");
			long millis = EmployeeHelper.getRandomNumberInts(5,10)* 1000;
			try {
				Thread.sleep(millis);
			} catch (InterruptedException e) {
				LOGGER.info("Thread Interrup {}",e);
			}
			//Change status
			call.setStatus(CallStatusEnum.PROCESS);
			setStatus(EmployeeStatusEnum.FREE);
			LOGGER.debug("finish callHandler OPERADOR : {} - CALL_ID {}", getName(), call.getId());	
	}

	@Override
	public EmployeeTypeEnum getTypeEmployee() {
		return EmployeeTypeEnum.OPERADOR;
	}
}
