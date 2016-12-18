package com.jcomm.threads;

import java.util.concurrent.Callable;

public class PersonCallNip implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		int num = 1_000_000_000;
		int secondNum = 10000;	
		while(num > 0){
			
			
			num--;
			while(secondNum > 0){
				
				
				secondNum--;
			}
		}
		
		return 1;
	}

}
