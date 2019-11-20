package com.alMundo.test;

import java.awt.DisplayMode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.statements.RunAfters;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.Times;
import org.mockito.verification.VerificationAfterDelay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alMundo.Call;
import com.alMundo.Dispatcher;
import com.alMundo.enums.CallStatusEnum;
import com.alMundo.enums.ReasonStatusEnum;
import com.alMundo.helper.Configuration;

import junit.framework.TestCase;

public class DispatcherTest extends TestCase {

	private static final Logger LOG=  LoggerFactory.getLogger(DispatcherTest.class);
	
	private List<Call> listCall;
	
	//Class under test
	Dispatcher dispatcher ;
	
	@Mock
	private ExecutorService executorServiceMock;
	
	@Before
	public void setUp() {
	    MockitoAnnotations.initMocks(this);
	 } 
	
	@Captor
	private ArgumentCaptor<Runnable> runnableArgumentCaptor;

	@Test
	public void test_OneSinglethread() throws Exception {
		Configuration config =  new Configuration(1,1,1,1);
		
		//Call
		Call call = new Call();
		call.setId(1);
		call.setReason(ReasonStatusEnum.SALE);
		
		dispatcher = new Dispatcher(config, this.executorServiceMock);
		dispatcher.dispatchCall(call);
		
	    // Let's call the callback. ArgumentCaptor.capture() works like a matcher.
		Mockito.verify(executorServiceMock, new Times(1)).submit(runnableArgumentCaptor.capture());
		
		//Despues de ejecutar la tarea
	
		//Moc
//		verify(executorService, new Times(1)).su(
//			        dummyCallbackArgumentCaptor.capture());

		//assertEquals("Call 1 atendida", true, call.getStatus() ==CallStatusEnum.PROCESS);
	}
	/**
	 * 10 llamadas , 5 operadores, 3 director y 1 supervisor
	 * @throws Exception
	 */
	@Test
	public void test_tenCall() throws Exception {
		Configuration config =  new Configuration(5,3,2,10);
		
		dispatcher = new Dispatcher(config, this.executorServiceMock);
		//Call
		Call call;
		
		listCall = new ArrayList<Call>();
		for (int i = 0; i < 10 ; i++) {
			dispatcher.dispatchCall(new Call(i,CallStatusEnum.PENDING,ReasonStatusEnum.SERVICE));
		
		}
	    // Let's call the callback. ArgumentCaptor.capture() works like a matcher.
		Mockito.verify(executorServiceMock, new Times(10)).submit(runnableArgumentCaptor.capture());
		
		//assertEquals("Call 1 atendida", true, call.getStatus() ==CallStatusEnum.PROCESS);
	}
	
}
