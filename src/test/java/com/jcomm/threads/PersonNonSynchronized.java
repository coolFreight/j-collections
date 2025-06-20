package com.jcomm.threads;

import com.jcomm.models.Dog;

public class PersonNonSynchronized {
	
	public PersonNonSynchronized(Dog d){
		this.d = d;
	}
	private final Dog d;
	public synchronized void printPockets(){
			System.out.println("Empty Pockets");
			d.printDog();
	}
	
	public static void main(String []args){
		
		Dog d = new Dog();
		PersonNonSynchronized pns = new PersonNonSynchronized(d);
		
		PersonDenip pd = new PersonDenip(pns);
		PersonManip pm = new PersonManip(pns);
		
		Thread t1 = new Thread(pd);
		Thread t2 = new Thread(pm);
		
		t1.start();
		t2.start();
	}

	public void printLife(){
		
		while(true){
			
			System.out.println("I am alive");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
}
