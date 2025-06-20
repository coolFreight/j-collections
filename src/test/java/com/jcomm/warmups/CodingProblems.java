package com.jcomm.warmups;

public abstract class CodingProblems {

	public static void main(String[] args) {

		// int [] values = {5,-1,-2};
		//
		// int n = values.length;
		//
		// int[] count = new int[n+1];
		// for (int val: values)
		// if (val >= n)
		// count[n]++;
		// else if (val > 0) // ignore negative values
		// count[val]++;
		// int am = 0;
		// for (int i = n; i > 0; i--) {
		// am += count[i]; // amount of numbers >= i
		// if (am >= i){
		// System.out.println(i);
		// return;
		// }
		// }
		// System.out.println(0);

		System.out.println(CodingProblems.removeDuplicates("AAA BBB AAA CCCC"));
	}

	public static char findNonFirstRepeating(String str) {

		int[] count = new int[256];

		for (int x = 0; x < str.length(); x++) {
			int key = str.charAt(x);
			count[key]++;
		}

		for (int y = 0; y < str.length(); y++) {

			int key = str.charAt(y);
			if (count[key] == 1)
				return str.charAt(y);

		}
		return 0;
	}

	public static String removeDuplicates(String str) {

		boolean[] word = new boolean[256];
		StringBuffer sb = new StringBuffer();

		for (char c : str.toCharArray()) {

			int key = c;
			if (!word[key]) {
				sb.append(c);
				word[key] = true;
			}
		}

		return sb.toString();

	}

}
