package com.jcomm.datastructures;

public class Permutations extends Backtrack<Integer> {

	@Override
	public boolean isSolution(int[] a, int k, Integer input) {
		return (k == input.intValue());
	}

	@Override
	public void processSolution(int[] a, int k, Integer input) {
		int i = 0;
		for(i=1; i<=k; i++)
			System.out.print(" "+a[i]);
		
		System.out.println();
	}

	@Override
	public void constructCandidates(int[] a, int k, Integer input, int[] c,
			RefObj ncandidates) {
		int i = 0;
		int nMax = input.intValue()+1;
		boolean [] in_perm = new boolean[nMax];
		
		for(i =1; i<nMax; i++)
			in_perm[i] = false;
		
		for (i=0; i<k; i++)
			in_perm[ a[i]] = true;
		
		ncandidates.setVal(0);
		for(i =1; i<=input.intValue(); i++){
			if(in_perm[i] == false){
				c[ncandidates.getVal()] = i;
				ncandidates.setVal( ncandidates.getVal()+1);
				
			}
		}
			
		
	}

	@Override
	public void make_move(int[] a, int k, Integer input) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unmake_move(int[] a, int k, Integer input) {
		// TODO Auto-generated method stub
		
	}
	
	public void generate_permutations(int n){
		
		int a[] = new int[n+1];
		backtrack(a,0,n);
	}
	
	public static void main(String ars[]){
		Permutations p = new Permutations();
		p.maxCandidates = 4;
		p.generate_permutations(3);
	}

}
