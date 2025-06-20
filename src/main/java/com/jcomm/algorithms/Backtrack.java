package com.jcomm.algorithms;


public abstract class Backtrack<T> {

	protected boolean finished = false;
	protected int maxCandidates  = 0;
	
	
	public final void backtrack(int[] a, int k, T input){
		
		int [] possibleCandidates = new int[maxCandidates];
		RefObj numCandidates = new RefObj();

		if(isSolution(a, k, input)) //when k is equal to an input you have a solution or subset `
			processSolution(a,k,input);
		 else{
			k = k+1;
			constructCandidates(a,k,input, possibleCandidates, numCandidates );
			for(int i=0; i<numCandidates.getVal(); i++){		
				a[k] = possibleCandidates[i];
				make_move(a,k,input);
				backtrack(a,k,input);
				unmake_move(a,k,input);
				if(finished) return;
			}
		}	
	}
	
	public abstract boolean isSolution(int[] a, int k, T input);
	public abstract void processSolution(int[] a, int k, T input);
	public abstract void constructCandidates(int[] a, int k, T input, int []possibleCandidates, RefObj ncandidates);
	public abstract void make_move(int[] a, int k, T input);
	public abstract void unmake_move(int[] a, int k, T input);

}
