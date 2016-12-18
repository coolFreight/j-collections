package com.jcomm.threads;

import com.jcomm.datastructures.JBlockingQueue;
import junit.framework.Assert;

import org.junit.Test;

public class JBlockingQueueTest {
	
	private JBlockingQueue<Integer> q = new JBlockingQueue<>(1);
	
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
		IntRemover ir = new IntRemover(q);
		Thread t2 = new Thread(ir);
		t2.start();
		Thread.sleep(1000);
		Assert.assertEquals(Thread.State.TERMINATED, t1.getState());
	}
	
	@Test
	public void testRemove() throws InterruptedException{
		testAddAwait();
		IntRemover ir = new IntRemover(q);
		Thread t1 = new Thread(ir);
		t1.start();
		
	}
	
	
	
	 private static class IntAdder implements Runnable{

		private JBlockingQueue<Integer> q;
		
		public IntAdder(JBlockingQueue<Integer> q){
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

			private JBlockingQueue<Integer> q;
			
			public IntRemover(JBlockingQueue<Integer> q){
				this.q = q;
			}
			
			@Override
			public void run() {	
				System.out.println("Remover got : "+q.removeElement());
			}
			
		}

}



