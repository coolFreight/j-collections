package com.jcomm.datastructures;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Disabled
public class GraphTest {

	ListGraph listGraph = new ListGraph();
	Vertex a = new Vertex("A");
	Vertex b = new Vertex("B");
	Vertex c = new Vertex("C");
	Vertex d = new Vertex("D");
	Vertex e = new Vertex("E");
	Vertex f = new Vertex("F");
	Vertex g = new Vertex("G");
	Vertex h = new Vertex("H");	
	Vertex i = new Vertex("I");

	
	
	@BeforeEach
	public void setUp(){
		listGraph = new ListGraph();
	}
	
	
	private void createMatrixVertices(MatrixGraph matrixGraph){
		
		matrixGraph.createVertex("A");
		matrixGraph.createVertex("B");
		matrixGraph.createVertex("C");
		matrixGraph.createVertex("D");
		matrixGraph.createVertex("E");
		matrixGraph.createVertex("F");
		matrixGraph.createVertex("G");
		matrixGraph.createVertex("H");
		matrixGraph.createVertex("I");
	}
	
	private void createListVertices(ListGraph graph){

		graph.createVertex("A");
		graph.createVertex("B");
		graph.createVertex("F");

	}


	
	@Test
	public void testShortestPathAlgo() {

		MatrixGraph g = new MatrixGraph(25);
		createMatrixVertices(g);
		g.addEdge("A", "B", 10, true);
		g.addEdge("A", "D", 80, true);
		g.addEdge("B", "D", 20, true);
		g.addEdge("D", "E", 70, true);
		g.addEdge("D", "C", 20, true);
		g.addEdge("C", "E", 40, true);
		g.addEdge("E", "B", 50, true);
							
		List<Path> paths = g.findShortestPath("A");
		for (Path p : paths) {

			String end = p.getEndNode().getLabel();

			switch (end) {
				case "B":
					assertEquals(10, p.getCost());
					break;
				case "D":
					assertEquals(30, p.getCost());
					break;

			}
		}
	}
	
	@Test
	public void testMatrixBfs(){
		MatrixGraph g = new MatrixGraph(9);
		createMatrixVertices(g);
		g.addEdge("A", "B", 1, false);
		g.addEdge("A", "C", 1, false);
		g.addEdge("A", "D", 1, false);
		g.addEdge("A", "E", 1, false);
		g.addEdge("B", "F", 1, false);
		g.addEdge("F", "H", 1, false);
		g.addEdge("D", "G", 1, false);
		g.addEdge("G", "I", 1, false);
		
		g.bfs("A", new Function<Vertex, Vertex>(){
			@Override
			public Vertex apply(Vertex t) {
				System.out.println("Vertex : "+t);
				return t;
			}		
		});
	}
	
	
	@Test
	public void testListBfs(){
	
		listGraph = new ListGraph();
		createListVertices(listGraph);
		listGraph.addEdge("A","B", 1, true);
		listGraph.addEdge("A", "C", 1, true);
		listGraph.addEdge("A", "D", 1, true);
		listGraph.addEdge("A", "E", 1, true);
		listGraph.addEdge("B", "F", 1, true);
		listGraph.addEdge("F", "H", 1, true);
		listGraph.addEdge("D", "G", 1, true);
		listGraph.addEdge("G","I", 1, true);
		
//		g.bfs("A", new Function<Vertex, Vertex>(){
//			@Override
//			public Vertex apply(Vertex t) {
//				System.out.println("Vertex : "+t);
//				return t;
//			}
//		});
	}



	@Test
	public void testFindCycle(){

		createListVertices(listGraph);
		listGraph.addEdge("A", "B", 1, true);
		listGraph.addEdge("B", "F", 1, true);
		listGraph.addEdge("F", "A", 1, true);


		listGraph.dfsRecursive("A", null);
	}



	@Test
	public void testListDfs(){
		
		createListVertices(listGraph);
		listGraph.addEdge("A", "B", 1, true);
		listGraph.addEdge("A", "C", 1, true);
		listGraph.addEdge("A", "D", 1, true);
		listGraph.addEdge("A", "E", 1, true);
		listGraph.addEdge("B", "F", 1, true);
		listGraph.addEdge("F", "H", 1, true);
		listGraph.addEdge("D", "G", 1, true);
		listGraph.addEdge("E", "I", 1, true);
		
		listGraph.dfsIter("A", new Function<Vertex, Vertex>(){
			@Override
			public Vertex apply(Vertex t) {
				System.out.println("Vertex : "+t);
				return t;
			}		
		});
	}	
	
	
	@Test
	public void testFindPath(){
		createListVertices(listGraph);
		listGraph.addEdge("A", "B", 1, true);
		listGraph.addEdge("A", "C", 1, true);
		listGraph.addEdge("A", "D", 1, true);
		listGraph.addEdge("A", "G", 1, true);
		listGraph.addEdge("D", "G", 1, true);
		listGraph.addEdge("D", "E", 1, true);
		listGraph.addEdge("E", "G", 1, true);
		//graph.addEdge(g, g, 1, true);

		//JLinkedList<Vertex> v = g.findPath("D", "Z");
		
		
		//CollectionsHelper.printCollection(v);
//    	Assert.assertEquals("D",v.getValueAtIndex(0).getLabel());
//		Assert.assertEquals("G", v.getValueAtIndex(1).getLabel());
//    	Assert.assertEquals("Z", v.getValueAtIndex(2).getLabel());
 		//Assert.assertEquals("Z", v.getValueAtIndex(3).getLabel());
	}
	
	
	@Test
	public void testConnectTwoComponents(){
		//Assert.fail();
	}
	
}
