package com.alMundo;

public class Call {
	
	private Integer id;
	
	private CallStatusEnum status;
	
	private ReasonStatusEnum reason;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public CallStatusEnum getStatus() {
		return status;
	}
	
	public void setStatus(CallStatusEnum status) {
		this.status = status;
	}

	public ReasonStatusEnum getReason() {
		return reason;
	}

	public void setReason(ReasonStatusEnum reason) {
		this.reason = reason;
	}
}
