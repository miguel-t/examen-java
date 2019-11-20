package com.alMundo;

import com.alMundo.enums.CallStatusEnum;
import com.alMundo.enums.ReasonStatusEnum;

public class Call {
	
	private Integer id;
	private CallStatusEnum status;
	private ReasonStatusEnum reason;
	
	public Call() {
		this.status = CallStatusEnum.PENDING;
	}

	public Call(Integer id, CallStatusEnum status, ReasonStatusEnum reason) {
		this.id = id;
		this.status = status;
		this.reason = reason;
	}

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
