package com.jcomm.warmups;

import com.jcomm.algorithms.Backtrack;
import com.jcomm.algorithms.RefObj;

public class Subsets extends Backtrack<Integer> {

	final int TRUE = 1;
	final int FALSE = 0;


	@Override
	public boolean isSolution(int[] a, int k, Integer input) {
		return (k == input.intValue());
	}

	@Override
	public void processSolution(int[] a, int k, Integer input) {

		System.out.print("{");
		for (int i = 1; i <= k; i++) {
			if (a[i] == TRUE)
				System.out.print(" " + i);
		}
		System.out.println("}");
	}



	@Override
	public void constructCandidates(int[] a, int k, Integer input, int[] c,
			RefObj ncandidates) {

		c[0] = TRUE;
		;
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

	public void generateSubsets(int n) {
		int[] a = new int[1000];
		backtrack(a, 0, n);
	}

	public static void main(String args[]) {
		Subsets s = new Subsets();
		// s.maxCandidates = 2;

		int array[] = { 23, 2, 45 };
		int result[] = new int[3];
		boolean bits[] = new boolean[3];
		s.permutations(array, bits, result, 2);

		// long start = System.nanoTime();
		// s.generateSubsets(20);
		// long end = System.nanoTime();
		// System.out.println("Took: " + ((end - start) / 1000000) + "ms");
		//

		// long start = System.nanoTime();
		// s.subsets(0, new boolean[20]);
		// long end = System.nanoTime();
		// System.out.println("Took: " + ((end - start) / 1000000) + "ms");

	}

	public void permutations(int A[], boolean bits[], int result[], int index) {
		if (index < 0) {
			for (int i : result) {
				System.out.print(i + ",");
			}
			System.out.println();

			return;
		}

		for (int x = 0; x < A.length; x++) {
			if (bits[x] == false) {
				bits[x] = true;
				result[index] = A[x];
				permutations(A, bits, result, index - 1);
				bits[x] = false;
			}
		}
	}

	public void subsets(int[] array, int idx, boolean bits[]) {

		if (idx < 0) {

			System.out.print("{");
			for (int x = 0; x < array.length; x++) {

				if (bits[x] == true) {
					System.out.print(array[x] + ", ");
				}
			}
			System.out.print("}");
			System.out.println();
			return;
		}
		bits[idx] = true;
		subsets(array, idx - 1, bits);
		bits[idx] = false;
		subsets(array, idx - 1, bits);

	}

	public void subsets(int n, boolean a[]) {

		if (n == a.length) {
			System.out.print("{");
			for (int i = 1; i < a.length; i++) {
				if (a[i]) {
					System.out.print(i + " ");
				}
			}
			System.out.print("}");
			System.out.println();
		} else {

			if (n > 0)
				a[n] = true;
			subsets(n + 1, a);
			if (0 == n)
				return;
			if (n > 0)
				a[n] = false;
			subsets(n + 1, a);
		}

	}

}
