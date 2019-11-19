package com.alMundo.test;

import java.util.concurrent.ExecutorService;

import org.junit.Before;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class TestBase {

	@Before
	public void before() {
	    MockitoAnnotations.initMocks(this);
	} 

	protected void implementAsDirectExecutor(ExecutorService executor) {
	    doAnswer(new Answer<Object>() {
	        public Object answer(InvocationOnMock invocation)
	            throws Exception {
	            Object[] args = invocation.getArguments();
	            Runnable runnable = (Runnable)args[0];
	            runnable.run();
	            return null;
	        }
	    }).when(executor).submit(any(Runnable.class));
	}

	private Object doAnswer(Answer<Object> answer) {
		// TODO Auto-generated method stub
		return null;
	} 

}
