package com.jcomm.datastructures;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class JHashMapTest {

	@Test
	public void testJHashMap(){
		
		JHashMap<MapTest, MapTest> map = new JHashMap<>();
		
		
		MapTest tester = new MapTest();
		tester.setHashCode(10);
		tester.setName("jovaughn");
		
		MapTest tester2 = new MapTest();
		tester2.setHashCode(10);
		
		map.put(tester, tester);
		map.put(tester2, tester2);
		
		assertEquals(tester, tester);
		assertEquals(tester2, tester2);
		assertNotSame(tester, tester2);
		assertEquals("jovaughn", map.get(tester).toString());
		assertEquals("default", map.get(tester2).toString());
	}
	
	@Test
	public void testFullJHashMap(){
		
		JHashMap<MapTest, MapTest> map = new JHashMap<>(2);
		
		
		MapTest tester = new MapTest();
		tester.setHashCode(10);
		tester.setName("jovaughn");
		
		MapTest tester2 = new MapTest();
		tester2.setHashCode(10);
		
		MapTest tester3 = new MapTest();
		tester3.setHashCode(10);
		
		
		map.put(tester, tester);
		map.put(tester2, tester2);
		map.put(tester3, tester3);
		
		assertEquals(tester, tester);
		assertEquals(tester2, tester2);
		assertNotSame(tester, tester2);
		assertEquals("jovaughn", map.get(tester).toString());
		assertEquals("default", map.get(tester2).toString());
	}
	
	
	/***
	 * Class is for testing hashmap purpose only 
	 * 
	 * @author Jovaughn
	 *
	 */
	private class MapTest{
		
		private int hashCode = 0;
		private String name = "default";
		
		public void setHashCode(int hashCode){
			this.hashCode = hashCode;
		}
		
		
		@Override
		public int hashCode(){
			
			return hashCode;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}
		
		@Override
		public boolean equals(Object o){
			
			return this.name.equals(((MapTest)o).name);
		}
		
		@Override
		public String toString(){
			return name;
		}
		
	}
	
}



