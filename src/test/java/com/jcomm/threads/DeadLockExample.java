package com.jcomm.threads;

public class DeadLockExample {
	
	
	public static void main(String args[]){
	
		Dog d = new Dog();
		Bone b = new Bone();
		b.setDog(d);
		d.setBone(b);


		Dog d1 = new Dog();
		Bone b1 = new Bone();
		b.setDog(d);
		d.setBone(b1);




		
		Thread t1 = new Thread(new Person(d));
		Thread t2 = new Thread(new Person1(d1));
		
		//t2.start();
		t1.start();
	}

}
