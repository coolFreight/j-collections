package com.jcomm.models;

public class Dog extends Mammal {
	
	private Bone b;
	
	
	
	public void setBone(Bone b){
		this.b = b;
	}
	
	public Dog(){
		super(null);
	b = new Bone();
	}
	
	public synchronized void printDog() {	
			b.eatBone();	
	}
	
//	//public synchronized void doDog(){
//		b.printBoneType();
//	}
//
	public void printDogNameHoldLock(){
		System.out.println("Spot is holding lock "+Thread.currentThread().getName());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		System.out.println("printDogNameHoldLock() : giving up lock "+Thread.currentThread().getName());
	}
	
	public synchronized void printDogName(){
	
		System.out.println("Spot");
		
	}

	public String toString(){

		return b.toString() + " Spot";
	}


	public String soul(){

		System.out.println("Dog soul "+toString());
		return toString();
	}

	
	
	public static void main(String args[]) throws InterruptedException{
		
		Dog d = new Dog();
//		Person p1 = new Person (d);
//		Thread t1 = new Thread(p1, "p1");
//		t1.start();
//
//		Person1 p2 = new Person1(d);
//		Thread t2 = new Thread(p2, "p2");
//		t2.start();

		d.soul();

		//Thread.sleep(1000);
		//System.out.println("debug statement "+t2.getState());
	}
}
