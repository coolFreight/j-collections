package com.jcomm.threads;

import com.jcomm.models.Dog;

import java.lang.Thread.UncaughtExceptionHandler;

public class PersonSynchronized  {

	public void printPockets(){
			System.out.println("Empty Pockets");
	}
	
	public  synchronized  void printLife(){
		
		while(true){
			
			System.out.println("I am alive");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String []args){
		PersonSynchronized ps = new PersonSynchronized();
		
		PersonDenip pd = new PersonDenip(ps);
		Dog dog = new Dog("shadow");
		pd.setDog(dog);
		
		PersonManip pm = new PersonManip(ps);
		pm.setDog(dog);
		
		
		
		Thread t1 = new Thread(pd);
			t1.setUncaughtExceptionHandler(new UncaughtExceptionHandler(){
			@Override
			public void uncaughtException(Thread t, Throwable e) {			
				System.out.println("Thread t : "+t+" "+e);			
			}
		});
		t1.start();

//		Thread t2 = new Thread(pm);
//		t2.start();
	}
	
}
