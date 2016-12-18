package com.jcomm.warmups;

public class Base {

	private int i;



	 public class InnerClass{
		int a = 5;

		public void print(){
			System.out.println(i);
			printa();
		}

	}


	public void printa(){
		System.out.println();

	}

	public static void main(String a[]){


		Base b = new Base();
		Base.InnerClass ic = b.new InnerClass();


	}


}
