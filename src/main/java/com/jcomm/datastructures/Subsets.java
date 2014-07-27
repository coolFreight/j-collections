package com.jcomm.datastructures;

public class Subsets extends Backtrack<Integer> {

	final int TRUE = 1;
	final int FALSE = 0;
	
	@Override
	public boolean isSolution(int[] a, int k, Integer input) {
		 return (k==input.intValue());
	}

	@Override
	public void processSolution(int[] a, int k, Integer input) {
		
		System.out.print("{");
		for(int i=1; i<=k; i++){
			if(a[i] == TRUE)
				System.out.print(" "+i);
		}
		System.out.println("}");
	}

	@Override
	public void constructCandidates(int[] a, int k, Integer input, int[] c,
			RefObj ncandidates) {

		c[0] = TRUE;;
		c[1] = FALSE;
		ncandidates.setVal(2);
	}

	@Override
	public void make_move(int[] a, int k, Integer input) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unmake_move(int[] a, int k, Integer input) {
		// TODO Auto-generated method stub

	}
	
	public void generateSubsets(int n){
		int [] a = new int[1000];
		backtrack(a, 0, n);
	}
	
	public static void main(String args[]){
		Subsets s = new Subsets();
//		s.maxCandidates = 2;
//		s.generateSubsets(3);
		s.subsets(0, new boolean[4]);
		
	}
	
	
	public void subsets(int n, boolean a[]){
		
		if(n == a.length ){
			System.out.print("{");
			for(int i = 1; i<a.length; i++){
				if(a[i]){
					System.out.print(i+" ");
				}
			}
			System.out.print("}");
			System.out.println();
		}
		else{
			
			if(n>0)
				a[n] = true;
			subsets(n+1, a);
			if(0==n)
				return;
			if(n>0)
				a[n]= false;
			subsets(n+1, a);	
		}
			
	}

}
