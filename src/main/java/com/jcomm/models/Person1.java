package com.jcomm.models;

import com.jcomm.models.Dog;

public class Person1  implements Runnable{
	
	private Dog d;
	
	public Person1(Dog d){	
		this.d = d;
	}
	
	public void run(){		
		d.printDogNameHoldLock();
	}
	

}
