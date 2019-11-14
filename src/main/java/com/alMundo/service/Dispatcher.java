package com.alMundo.service;

import java.util.List;
import java.util.concurrent.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alMundo.model.Call;

public class Dispatcher {

	private static final Logger LOG = LoggerFactory.getLogger(Dispatcher.class);
	private BlockingQueue pendingCalls;
//	private EmpleadosPool empleados;
	private ExecutorService executor;
	private List<Call> calls;
	
//	public void setEmpleados(EmpleadosPool empleados) {
//			this.empleados = empleados;
//	}

	public List<Call> getCalls() {
		return calls;
	}

	public void setCalls(List<Call> calls) {
		this.calls = calls;
	}

//	public Dispatcher( EmpleadosPool empleados){
	
	 //this.setEmpleados(empleados);
	public Dispatcher() { 
		this.executor = Executors.newFixedThreadPool(2,new ThreadFactory() {
				public Thread newThread(Runnable r) {
					Thread t = Executors.defaultThreadFactory().newThread(r);
					t.setDaemon(false);
					return t;
				}
			});
			
			this.pendingCalls = new LinkedBlockingDeque();
		}

		public void dispatchCalls(List<Call> calls){
			this.setLlamadasRecibidas(calls);
			calls.forEach(call -> this.handleSingleCall(call));
		}

		private void handleSingleCall(Call call){
			Empleado emplDisponible = empleados.getAvailableEmployee();
			if (emplDisponible == null){
				llamada.newAttemp();
				LOG.info("Llamada " + llamada.getName() +" imposible de atender, no hay empleados disponibles");
				this.pendingCalls.add(llamada);
				return;
			}
			this.dispatchCall(emplDisponible, llamada);
		}

		public void dispatchCall(Empleado empl, Llamada llamada){
			Runnable callThread = () -> {
				empl.atenderLlamada(llamada);
				this.notifyFreeEmployee();
			};
			this.executor.submit(callThread);
		}

		/**
		 * Desencola una llamada de la lista de llamadas pendientes para mandar a procesar;
		 * Si no hay llamadas pendientes, manda a terminar el proceso
		 */
		public void notifyFreeEmployee(){
			if (this.pendingCalls.isEmpty()){
				try {
					LOG.info("Shuting down executor...");
					this.executor.shutdown();
					executor.awaitTermination(5, TimeUnit.SECONDS);
				} catch (Exception e) {
					LOG.info("tasks interrupted");
				} finally {
					if (!executor.isTerminated()) {
						LOG.info("cancel non-finished tasks");
					}
					executor.shutdownNow();
					LOG.info("shutdown finished");
				}

			}
			Call call = (Call)this.pendingCalls.remove();
			this.handleSingleCall(call);
		}

		public BlockingQueue getPendingCalls() {
			return pendingCalls;
		}

		public void setPendingCalls(BlockingQueue pendingCalls) {
			this.pendingCalls = pendingCalls;
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
