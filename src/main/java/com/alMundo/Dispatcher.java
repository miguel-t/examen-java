package com.alMundo;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alMundo.employee.Employee;
import com.alMundo.helper.Configuration;
import com.alMundo.helper.EmployeeHelper;

public class Dispatcher {

	private static final Logger LOG =  LoggerFactory.getLogger(Dispatcher.class);
	
	private EmployeeHelper employeeHelper;
	private ExecutorService executor;
	private BlockingQueue<Call> pendingCalls;
	private List<Call>	 calls;
	
	/**
	 * Constructor para poder usar Mockito
	 * @param config
	 * @param executorService
	 */
	public Dispatcher(Configuration config,ExecutorService executorService){
		this.employeeHelper = new EmployeeHelper(config);
		this.pendingCalls = new LinkedBlockingDeque<Call>();
		this.executor = executorService;
	}
	
	public Dispatcher(){
		Configuration config =  new Configuration();
		this.employeeHelper = new EmployeeHelper(config);
		this.pendingCalls = new LinkedBlockingDeque<Call>();
		this.executor =  Executors.newFixedThreadPool(config.getThread());
	}
	
	
	public void dispatchCall(Call call){
		Employee employee = this.employeeHelper.getNextEmploye();
		if (employee == null){
			this.pendingCalls.add(call);
			LOG.info("No hay empleados disponibles");
			return;
		}
		//Atendemos la llamada
		Runnable callThread = () -> {employee.callHandler(call);this.dispatchPending();};
		this.executor.submit(callThread);
	}

	public void dispatchPending(){
		if (this.pendingCalls.isEmpty()){
			try {
				this.executor.shutdown();
				//Peor caso que el ultimo thread que esta ejecutando 
				//no termine su tarea
				executor.awaitTermination(10, TimeUnit.SECONDS);
			} catch (Exception e) {
				LOG.info("tasks interrupted");
			} finally {
				if (!executor.isTerminated()) {
					LOG.info("cancel non-finished tasks");
				}
				executor.shutdownNow();
			}
		}
		Call call = (Call)this.pendingCalls.remove();
		this.dispatchCall(call);
	}
	/**
	 * Para ejecutar una lista de llamadas
	 * @param call
	 */
//	public void dispatchCalls(List<Call> calls){
//		this.setCalls(calls);
//		
//		for (Call call : calls) {
//			this.dispatchCall(call);
//		}
//	
//	}

	public List<Call> getCalls() {
		return calls;
	}
	
	public void setCalls(List<Call> calls) {
		this.calls = calls;
	}

	public EmployeeHelper getEmployeeHelper() {
		return employeeHelper;
	}

	public void setEmployeeHelper(EmployeeHelper employeeHelper) {
		this.employeeHelper = employeeHelper;
	}

}
