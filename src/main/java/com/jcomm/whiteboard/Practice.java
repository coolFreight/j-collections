package com.jcomm.whiteboard;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by jova on 4/21/15.
 */
public class Practice {
	
	
	public static void MaximumSubArray(){
		
		int arr [] = {100, 113, 110, 85, 105, 102, 86, 63, 81, 101, 94, 106, 101, 79, 94, 90, 97};
		
		
		for(int x = 0; x<arr.length; x++){
			
			for(int i =0; i<arr.length; ){
				
				
			}
			
		}
		
	}

	
	
	public static void isNested(String arg[]) {

		char[] p = { '(', '(', '(', ')', '(', ')', ')', '(', ')' };
		Stack<Character> openP = new Stack<>();
		boolean malformed;

		int count = 0;

		for (char c : p) {

			if (c == '(') {
				count++;
				openP.push(c);
			} else if (c == ')') {
				count--;
				if (openP.isEmpty()) {
					System.out.println("the " + count + " char is malformed");
					malformed = true;
				}
				openP.pop();
			}
		}

		if (!openP.isEmpty()) {
			System.out.println("the " + count + " char is malformed");
			malformed = true;
		}
		malformed = false;
		System.out.println("Is malformed " + malformed);
	}

	public static void main(String arg[]) {
		Practice p = new Practice();
		int[] arr = { 1, 4, 2, 1 };
		p.Kareem(arr);

	}

	public static void ana(int depth, char[] words, boolean[] cans, char[] orig) {

		if (depth == words.length) {

			for (int x = 0; x < words.length; x++) {

				System.out.print(words[x]);
			}

			System.out.println();

		} else {

			int idx[] = new int[orig.length];
			int candids = 0;
			for (int x = 0; x < cans.length; x++) {
				if (cans[x] == false) {
					idx[candids] = x;
					candids++;
				}
			}

			for (int x = 0; x < candids; x++) {
				words[depth] = orig[idx[x]];
				cans[idx[x]] = true;
				ana(depth + 1, words, cans, orig);
				cans[idx[x]] = false;
			}
		}

	}

	public void reverseLinkedList() {

		PracticeNode pn1 = new PracticeNode();

		PracticeNode pn2 = new PracticeNode();

		PracticeNode pn3 = new PracticeNode();

		PracticeNode pn4 = new PracticeNode();

		pn1.setX(4);

		pn2.setX(8);

		pn3.setX(1);

		pn4.setX(898);

	}

	public void Kareem(int[] arr) {

		Arrays.sort(arr);

		for (int j = arr.length - 1; j >= 0; j--) {
			if (arr[j] < arr.length && arr.length - j > arr[j] && (j != arr.length - 1 && arr[j] != arr[j + 1])) {
				System.out.println(arr[j]);
				return;
			}
		}

	}

	private class PracticeNode {

		private Integer x = new Integer(0);
		private PracticeNode next = null;

		public Integer getX() {
			return x;
		}

		public void setX(Integer x) {
			this.x = x;
		}

	}
}
