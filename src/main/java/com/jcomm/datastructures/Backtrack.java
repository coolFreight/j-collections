package com.jcomm.datastructures;

public abstract class Backtrack<T> {

	protected boolean finished = false;
	protected int maxCandidates  = 0;
	
	
	public final void backtrack(int a[], int k, T input){
		
		int []c = new int[maxCandidates];
		RefObj numCandidates = new RefObj();
		int i = 0;
		
		if(isSolution(a, k, input))
			processSolution(a,k,input);
		 else{
			k = k+1;
			constructCandidates(a,k,input, c, numCandidates );
			for(i=0; i<numCandidates.getVal(); i++){		
				a[k] = c[i];
				make_move(a,k,input);
				backtrack(a,k,input);
				unmake_move(a,k,input);
				if(finished) return;
			}
		}	
	}
	
	public abstract boolean isSolution(int a[], int k, T input);
	public abstract void processSolution(int a[], int k, T input);
	public abstract void constructCandidates(int a[], int k, T input, int []c, RefObj ncandidates);
	public abstract void make_move(int a[], int k, T input);
	public abstract void unmake_move(int a[], int k, T input);

}
