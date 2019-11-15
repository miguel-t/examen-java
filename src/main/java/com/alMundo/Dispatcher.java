package com.alMundo;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import javax.security.auth.login.Configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Dispatcher {

	private static final Logger logger =  LoggerFactory.getLogger(Dispatcher.class);
	
	private ExecutorService executor;
	private BlockingQueue pendingCalls;
		
	
}
