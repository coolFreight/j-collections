package com.jcomm.threads;

import com.jcomm.models.Dog;

public class PersonManip implements Runnable {

	private PersonNonSynchronized pns;
	private boolean nonsyn = false;
	
	private PersonSynchronized ps = new PersonSynchronized();
	private boolean syn = false; 
	private Dog dog;
	
	public void setDog(Dog dog){
		this.dog = dog;
	}
	
	public PersonManip(PersonSynchronized ps){
		this.ps = ps;
		syn = true;
	}
	
	public PersonManip(PersonNonSynchronized pns){
		this.pns = pns;
		nonsyn = true;
	}
	
	@Override
	public void run() {
		if(nonsyn)
			loopNonSyncronizer();
		else
			loopSyncronizer();

	}
	
	public void loopSyncronizer(){
		
		while(true){
			ps.printLife();
			pns.printLife();
		}
		
	}
	
	public void loopNonSyncronizer(){
		
		while(true){
			this.dog.printDog();
			ps.printPockets();
		}
		
	}
	
	

}
