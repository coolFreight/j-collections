package com.jcomm.threads;

public class Person  implements Runnable{
	
	private Dog d;
	
	public Person(Dog d){		
		this.d = d;	
	}
	
	
	
	public void run(){
		
		d.printDogNameHoldLock();
	}
	
	

}
