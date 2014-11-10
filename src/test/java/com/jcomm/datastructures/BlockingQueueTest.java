package com.jcomm.datastructures;

import junit.framework.Assert;

import org.junit.Test;

public class BlockingQueueTest {
	
	private BlockingQueue<Integer> q = new BlockingQueue<>(1);
	
	@Test
	public void testAdd(){
		q.addElement(Integer.valueOf(5));
		Assert.assertEquals(1, q.size());
		Assert.assertEquals(Integer.valueOf(5), q.removeElement());
	}
	
	
	@Test
	public void testAddAwait() throws InterruptedException{	
		
		IntAdder ia = new IntAdder(q);
		Thread t1 = new Thread(ia);
		t1.start();	
		Thread.sleep(1000);
		Assert.assertEquals(Thread.State.WAITING, t1.getState());
	}
	
	
	
	 private static class IntAdder implements Runnable{

		private BlockingQueue<Integer> q;
		
		public IntAdder(BlockingQueue<Integer> q){
			this.q = q;
		}
		
		@Override
		public void run() {
			q.addElement(Integer.valueOf(8));	
			System.out.println("Adding 2 "+q.size()+" size ");
			q.addElement(Integer.valueOf(20));	
			System.out.println("Adding 2 "+q.size()+" size ");		
			System.out.println("Thread about to die");
		}
		
	}
	 
	 private static class IntRemover implements Runnable{

			private BlockingQueue<Integer> q;
			
			public IntRemover(BlockingQueue<Integer> q){
				this.q = q;
			}
			
			@Override
			public void run() {
				q.addElement(Integer.valueOf(8));		
				System.out.println("Remover got : "+q.removeElement());
			}
			
		}

}



