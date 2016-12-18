package com.jcomm.threads;

public class PersonDenip implements Runnable {

	private PersonNonSynchronized pns;
	private boolean nonsyn = false;
	private Dog dog;
	
	private PersonSynchronized ps = new PersonSynchronized();
	private boolean syn = false; 
	
	
	public void setDog(Dog dog){
		this.dog = dog;
	}
	public PersonDenip(PersonSynchronized ps){
		this.ps = ps;
		syn = true;
	}
	
	public PersonDenip(PersonNonSynchronized pns){
		this.pns = pns;
		nonsyn = true;
	}
	
	@Override
	public void run() {
		int [] arr = new int[3];
		
		int e = arr[9];
		
		if(nonsyn)
			loopNonSyncronizer();
		else
			loopSyncronizer();

	}
	
	public void loopSyncronizer(){
		
		while(true){
			ps.printPockets();
			this.dog.printDog();
		}
		
	}
	
	public void loopNonSyncronizer(){
		
		while(true){
			pns.printPockets();
			this.dog.printDog();
		}
		
	}
	

}
