package com.alMundo.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alMundo.Call;
import com.alMundo.Dispatcher;
import com.alMundo.enums.ReasonStatusEnum;
import com.alMundo.helper.Configuration;

import junit.framework.TestCase;

public class DispatcherTest extends TestCase {

	List<Call> listCall;
	
	private static final Logger LOG=  LoggerFactory.getLogger(DispatcherTest.class);

	@Before
	public void beforeEachTest() throws Exception {
		this.listCall = new ArrayList<Call>();
	}


	@Before
	public void before() {
	    MockitoAnnotations.initMocks(this);
	} 
	@Test
	public void test_OneSinglethread() throws Exception {
		
		Configuration config =  new Configuration();
		Dispatcher dispatcher = new Dispatcher(config);
		
		Call call = new Call();
		call.setId(1);
		call.setReason(ReasonStatusEnum.SALE);
		dispatcher.dispatchCall(call);
		
		
		
	    int threads = 10;
	    ExecutorService service =   Executors.newFixedThreadPool(threads);
	    Collection<Future<Integer>> futures = new ArrayList<>(threads);
	    f
	    or (int t = 0; t < threads; ++t) {
	      final String title = String.format("Book #%d", t);
	      futures.add(service.submit(() -> books.add(title)));
	    }
	    Set<Integer> ids = new HashSet<>();
	    for (Future<Integer> f : futures) {
	      ids.add(f.get());
	    }
	    assertThat(ids.size(), equalTo(threads));
		
		
		
		
		
		
		
		
		
		
		
		//assertEquals("Call 1 atendida", true, call.getStatus() ==CallStatusEnum.PROCESS);
	}

}
