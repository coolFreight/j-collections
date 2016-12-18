package com.jcomm.threads;

public class Bone {
	
	private Dog d; 
	private final int e =1;
	
	public void setDog(Dog d){
		
		
		
		this.d = d;
	}
	
	public synchronized void eatBone(){
		d.printDogName();
		d.printDogName();
		d.printDogName();
		d.printDogName();
		d.printDogName();
	}
	
	public synchronized void printBoneType(){
		System.out.println("Chewy");
	}
}
