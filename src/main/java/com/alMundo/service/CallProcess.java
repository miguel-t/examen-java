package com.alMundo.service;

import java.util.UUID;

public class CallProcess implements Runnable{

	
	private UUID uuid;
	
	private Integer interval;
	
	private Boolean finish = Boolean.FALSE;

	private Boolean stopped = Boolean.TRUE;

	//Aca puede ir el DAO para recuperar las entidades
	
	//Aca puede ir un servicio para ejecutar 
	
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	public CallProcess(UUID uuid, Integer interval, Boolean finish, Boolean stopped) {
		this.uuid = UUID.randomUUID();
		this.interval = interval;
		this.finish = finish;
		this.stopped = stopped;
	}




}
