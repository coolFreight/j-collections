package com.jcomm.datastructures;

import java.util.List;
import java.util.function.Function;

import org.junit.Assert;
import org.junit.Test;

public class GraphTest {

	@Test
	public void testShortestPathAlgo() {

		MatrixGraph g = new MatrixGraph(5);

		g.addDirectedEdge('A', 'B', 10);
		g.addDirectedEdge('A', 'D', 80);
		g.addDirectedEdge('B', 'D', 20);
		g.addDirectedEdge('D', 'E', 70);
		g.addDirectedEdge('D', 'C', 20);
		g.addDirectedEdge('C', 'E', 40);
		g.addDirectedEdge('E', 'B', 50);
							
		List<Path> paths = g.findShortestPath('A');
		for (Path p : paths) {

			char end = p.getEndNode().getLabel();

			switch (end) {
				case 'B':
					Assert.assertEquals(10, p.getCost());
					break;
				case 'D':
					Assert.assertEquals(30, p.getCost());
					break;

			}
		}
	}
	
	@Test
	public void testMatrixBfs(){
		MatrixGraph g = new MatrixGraph(9);
		g.addDirectedEdge('A', 'B', 1);
		g.addDirectedEdge('A', 'C', 1);
		g.addDirectedEdge('A', 'D', 1);
		g.addDirectedEdge('A', 'E', 1);
		g.addDirectedEdge('B', 'F', 1);
		g.addDirectedEdge('F', 'H', 1);
		g.addDirectedEdge('D', 'G', 1);
		g.addDirectedEdge('G', 'I', 1);
		
		g.bfs('A', new Function<Vertex, Vertex>(){
			@Override
			public Vertex apply(Vertex t) {
				System.out.println("Vertex : "+t);
				return t;
			}		
		});
	}
	
	
	@Test
	public void testListBfs(){
		ListGraph g = new ListGraph();
		g.addEdge('A', 'B', 1, true);
		g.addEdge('A', 'C', 1, true);
		g.addEdge('A', 'D', 1, true);
		g.addEdge('A', 'E', 1, true);
		g.addEdge('B', 'F', 1, true);
		g.addEdge('F', 'H', 1, true);
		g.addEdge('D', 'G', 1, true);
		g.addEdge('G', 'I', 1, true);
		
		g.bfs('A', new Function<Vertex, Vertex>(){
			@Override
			public Vertex apply(Vertex t) {
				System.out.println("Vertex : "+t);
				return t;
			}		
		});
	}
	
	
	@Test
	public void testListDfs(){
		ListGraph g = new ListGraph();
		g.addEdge('A', 'B', 1, true);
		g.addEdge('A', 'C', 1, true);
		g.addEdge('A', 'D', 1, true);
		g.addEdge('A', 'E', 1, true);
		g.addEdge('B', 'F', 1, true);
		g.addEdge('F', 'H', 1, true);
		g.addEdge('D', 'G', 1, true);
		g.addEdge('G', 'I', 1, true);
		
		g.dfs('A', new Function<Vertex, Vertex>(){
			@Override
			public Vertex apply(Vertex t) {
				System.out.println("Vertex : "+t);
				return t;
			}		
		});
	}	
	
}
