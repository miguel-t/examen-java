package com.alMundo;

import com.alMundo.enums.CallStatusEnum;
import com.alMundo.enums.ReasonStatusEnum;

public class CallMain {

	public static void main(String[] args) {
		/** Se resuelve Extras/Plus
		 *	Ver application proterties
		 * 11 llamadas -10 thread -1 empleado de cada tipo
		 */
		 Dispatcher dispatcher = new Dispatcher();
		//Call
		 Call call;
		 for (int i = 0; i < 11; i++) {
			 call = new Call(i,CallStatusEnum.PENDING,ReasonStatusEnum.SERVICE);
			 dispatcher.dispatchCall(call);
		}
	}
}
