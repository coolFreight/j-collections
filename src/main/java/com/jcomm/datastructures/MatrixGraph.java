/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcomm.datastructures;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author jova
 */
public class MatrixGraph implements Graph {

	private final int graph[][];
	private final int ROWS;
	private final int COLS;
	private Queue<Vertex> queueOfNodes = new java.util.LinkedList<>();
	private Stack<Vertex> visitedNodes = new Stack<>();
	private PriorityQueue<Path> pQueue = new PriorityQueue<Path>();
	private Vertex[] vertices;

	public MatrixGraph(int size) {

		vertices = new Vertex[size];
		this.ROWS = size;
		this.COLS = size;
		this.graph = new int[this.ROWS][this.COLS];
		int x = 0;
		while (x < size) {
			vertices[x] = new Vertex(((char) (x + 65)));
			x++;
		}
	}

	public void createVertices(int n) {

		int x = 0;
		while (0 < n) {
			Vertex v = new Vertex(((char) (x + 65)));
			vertices[x] = v;
			System.out.println("Created vertex : " + v);
			x++;
		}
	}

	public static void main(String args[]) {
		MatrixGraph graph = new MatrixGraph(5);
		graph.addDirectedEdge('A', 'B', 50);
		graph.addDirectedEdge('A', 'D', 80);

		graph.addDirectedEdge('B', 'D', 90);
		graph.addDirectedEdge('B', 'C', 60);
		

		graph.addDirectedEdge('C', 'E', 40);
		
		graph.addDirectedEdge('D', 'C', 20);
		graph.addDirectedEdge('D', 'E', 70);

		graph.addDirectedEdge('E', 'B', 50);

		graph.printGraph();

		// graph.bfs('B');
		// graph.dfs('B');
		// graph.mst('A');
		// graph.recursiveBFS('B');
		List<Path> paths = graph.findShortestPath('A');
		CollectionsHelper.printCollection(paths, "\n");

		
	}

	public void addUnDirectedEdge(char start, char end) {
		addEdge(start - 65, end - 65, 1);
	}

	public void addUnDirectedEdge(char start, char end, int weight) {
		addEdge(start - 65, end - 65, weight);
	}
	

	public void addEdge(int start, int end, int weight) {
		graph[start][end] = weight;
		graph[end][start] = weight;
	}

	public void addDirectedEdge(char start, char end) {
		addDirectedEdge(start - 65, end - 65, 1);
	}

	public void addDirectedEdge(char start, char end, int weight) {
		addDirectedEdge(start - 65, end - 65, weight);
	}

	private void addDirectedEdge(int start, int end, int weight) {
		graph[start][end] = weight;

	}

	public void printGraph() {
		System.out.print(" ");
		for (int x = 0; x < COLS; x++) {
			System.out.print((char) (x + 65));
		}
		System.out.println();

		for (int row = 0; row < ROWS; row++) {
			System.out.print((char) (row + 65));
			for (int col = 0; col < COLS; col++) {
				System.out.print(graph[row][col]);
			}
			System.out.println();
		}
	}

	/**
	 * 3 rules
	 * 
	 * DFS makes use of a stack
	 * 
	 * Rule 1 : Visit vertex in a systematic way, mark it visited and put it the
	 * stack
	 * 
	 * Rule 2 : If you cannot follow Rule 1 anymore then pop vertex off stack
	 * 
	 * Rule 3 : If you cannot do Rule 1 or Rule 2 anymore you are done
	 * 
	 * @param label
	 *            of vertex
	 */
	public void dfs(char label) {

		Vertex v = getVertex(label);
		v.visited = true;
		visitedNodes.push(v);
		System.out.println("Visited vertex " + v);
		int row = getVertexRow(label);
		for (int i = 0; i < this.COLS; i++) {
			if (graph[row][i] == 1) {
				Vertex t = getVertex(i);
				if (t.visited == false)
					dfs(t.label);
			}
		}
	}

	public void mst(char label) {

		Vertex v = getVertex(label);
		v.visited = true;
		int row = getVertexRow(label);
		for (int i = 0; i < this.COLS; i++) {
			if (graph[row][i] == 1) {
				Vertex t = getVertex(i);
				if (t.visited == false) {
					System.out.println(v + " to " + t);
					mst(t.label);
				}
			}
		}
	}

	public List<Path> mwst() {
		return mwst('A', new java.util.ArrayList<Path>());
	}

	private List<Path> mwst(char label, List<Path> mininumSpanTree) {

		Vertex v = getVertex(label);
		v.visited = true;

		findCheapsestNeighbor(v, mininumSpanTree);

		return mininumSpanTree;

	}

	public List<Path> findShortestPath(char start) {

		List<Path> listOfPaths = new java.util.ArrayList<Path>();
		return findShortestPaths('A', listOfPaths, null);
	}

	private List<Path> findShortestPaths(char start, List<Path> listOfPaths,
			Path currentPath) {

		Vertex vStart = getVertex(start);
		vStart.visited = true;
		int row = getVertexRow(start);

		for (int col = 0; col < COLS; col++) {
			Vertex tempVertex = getVertex(col);
			if (graph[row][col] > 0 && !tempVertex.visited) {
				int tempCost = graph[row][col];

				Path newPath = null;
				if (currentPath != null) {

					List<Vertex> list = new java.util.ArrayList<>();
					list.addAll(currentPath.getDestinations());
					list.add(tempVertex);
					newPath = new Path(currentPath.getStartNode(), list,
							currentPath.getCost() + tempCost);
					shortestPathQueueInsert(newPath);
				} else {
					newPath = new Path(vStart, tempVertex, tempCost);
					pQueue.add(newPath);
				}
			}
		}

		if (pQueue.isEmpty())
			return listOfPaths;

		Path newPath = pQueue.remove();
		listOfPaths.add(newPath);

		return findShortestPaths(newPath.getEndNode().label, listOfPaths,
				newPath);

	}

	private void shortestPathQueueInsert(Path newPath) {

		Path temp = null;
		for (Path p : pQueue) {
			if (p.getEndNode().label == newPath.getEndNode().label)
				temp = p;
		}

		if (temp == null) {
			// Path not in the queue
			// add new path
			pQueue.add(newPath);
		} else if (temp != null && (temp.getCost() > newPath.getCost())) {
			// replace old path
			pQueue.remove(temp);
			pQueue.add(newPath);

		}

	}

	public void findCheapsestNeighbor(Vertex v, List<Path> mininumSpanTree) {

		int r = getVertexRow(v.label);
		Vertex start = getVertex(r);

		// getting all adjacent neighbors
		for (int c = 0; c < COLS; c++) {
			Vertex end;
			if (graph[r][c] > 0) {
				end = getVertex(c);
				if (end.visited)
					continue;
				Path p = new Path(start, end, graph[r][c]);
				pQueue.add(p);
			}
		}

		if (pQueue.isEmpty())
			return;

		Path p = pQueue.remove();
		p.getEndNode().visited = true;
		pQueue.removeIf(t -> t.getEndNode().label == p.getEndNode().label);

		mininumSpanTree.add(p);
		findCheapsestNeighbor(p.getEndNode(), mininumSpanTree);
	}

	private Vertex getVertex(int col) {
		return vertices[col];
	}

	private int getVertexRow(char label) {
		return label - 65;
	}

	private Vertex getVertex(char label) {
		int index = label - 65;
		return vertices[index];

	}

	public void recursiveBFS(char label) {

		Vertex v = getVertex(label);
		if (v.visited == false) {
			v.visited = true;
			this.queueOfNodes.add(v);
			System.out.println("Visited vertex : " + v);
		}
		int r = getVertexRow(label);
		for (int i = 0; i < this.COLS; i++) {

			if (graph[r][i] == 1) {

				Vertex t = this.vertices[i];
				if (t.visited == false) {
					t.visited = true;
					this.queueOfNodes.add(t);
					System.out.println("Visited vertex : " + t);
				}
			}
		}
		this.queueOfNodes.poll();
		if (queueOfNodes.isEmpty())
			return;
		this.recursiveBFS(this.queueOfNodes.peek().label);
	}

	public void bfs(char label) {

		int index = label - 65;

		if (vertices[index].wasVisited() == false) {
			vertices[index].setVisited(true);

			System.out.println(label);
		}

		for (int cols = 0; cols < COLS; cols++) {

			if (graph[index][cols] == 1 && vertices[cols].wasVisited() == false) {
				vertices[cols].setVisited(true);
				queueOfNodes.add(vertices[cols]);
				System.out.println("Enqueue: " + vertices[cols].getLabel());
			}// end of if

		}// end of for loop

		Vertex temp = queueOfNodes.poll();

		if (temp != null) {
			System.out.println("Dequeue: " + temp.getLabel());
			bfs(temp.getLabel());
		}
	}

	public void findConnected(char label) {
		int index = label - 65;
		if (vertices[index].wasVisited() == false) {
			vertices[index].setVisited(true);
			visitedNodes.push(vertices[index]);
			System.out.println("push " + label);
		}
		// traverse columns
		for (int col = 0; col < COLS; col++) {
			if (graph[index][col] == 1 && vertices[col].wasVisited() == false) {
				findConnected((char) (col + 65));
			}
		}

		if (visitedNodes.isEmpty() == false) {
			System.out.println("pop " + visitedNodes.pop().getLabel());
		}
		if (visitedNodes.isEmpty() == false) {
			findConnected(visitedNodes.peek().getLabel());
		}
	}

	private class Vertex {

		private char label;
		private boolean visited;

		public Vertex(char label) {

			this.label = label;

		}

		public char getLabel() {

			return this.label;

		}

		public void setVisited(boolean val) {

			this.visited = val;
		}

		public boolean wasVisited() {
			return this.visited;

		}

		@Override
		public String toString() {
			return this.label + "";
		}
	}

	public Vertex noSuccessor() {

		Vertex noSuccessorVertex;
		for (int r = 0; r < ROWS; r++) {
			noSuccessorVertex = vertices[r];
			int noSuccessorColumn = r;
			for (int c = 0; c < COLS; c++) {
				if (graph[r][c] > 0) {
					noSuccessorVertex = null;
				}
			}
			if (noSuccessorVertex != null) {
				for (r = 0; r < ROWS; r++)
					graph[r][noSuccessorColumn] = 0;
				return noSuccessorVertex;
			}
		}
		return null;
	}

	public <T> void deleteVertex(int deleteIndex, T[] arr) {
		if (arr == null || arr.length == 1)
			return;

		for (int i = deleteIndex; i < arr.length - 1; i++) {

			arr[i] = arr[i + 1];
		}
	}

	public void topo() {

		java.util.ArrayList<Vertex> s = new java.util.ArrayList<>();
		int numOfVertices = vertices.length;

		while (numOfVertices > 0) {
			int tempNumOfVertices = numOfVertices;
			Vertex noSuccessorVertex = noSuccessor();
			if (noSuccessorVertex != null) {
				for (int i = 0; i < vertices.length; i++) {
					if (vertices[i] != null
							&& vertices[i].label == noSuccessorVertex.label) {
						s.add(0, noSuccessorVertex);
						deleteVertex(i, vertices);
						numOfVertices--;
						break;
					}
				}
			} else {

				if (tempNumOfVertices == numOfVertices
						|| noSuccessorVertex == null) {
					System.out.println("There is a cycle");
					return;
				}
			}
		}
		CollectionsHelper.printCollection(s, "\n");
	}

	private class Path implements Comparable<Path>, Cloneable {

		private Vertex start;
		private List<Vertex> listOfDestinations;

		private int cost;
		private String path;

		public Path(Vertex start, Vertex otherNode, int cost) {
			this.start = start;
			this.listOfDestinations = new java.util.LinkedList<>();
			this.listOfDestinations.add(otherNode);
			this.cost = cost;
			this.path = createPath(start);
		}

		public Path(Vertex start, List<Vertex> vertices, int cost) {
			this.start = start;
			this.listOfDestinations = vertices;
			this.cost = cost;
			this.path = createPath(start);
		}

		public Vertex getStartNode() {
			return start;
		}

		public List<Vertex> getDestinations() {
			return this.listOfDestinations;
		}

		public Vertex getEndNode() {
			return listOfDestinations.get(listOfDestinations.size() - 1);
		}

		public void addDestination(Vertex v, int cost) {
			this.cost += cost;
			this.listOfDestinations.add(v);
		}

		private String createPath(Vertex start) {

			StringBuffer path = new StringBuffer();
			path.append(start);
			for (Vertex v : this.listOfDestinations) {
				path.append(" to " + v);
			}
			path.append(" " + this.cost);
			return path.toString();
		}

		@Override
		public String toString() {
			return path;
		}

		public int getCost() {
			return this.cost;
		}

		@Override
		public int compareTo(Path o) {

			if (this.cost > o.cost) {
				return 1;
			} else if (this.cost < o.cost) {
				return -1;
			}

			return 0;
		}

		@Override
		public boolean equals(Object o) {
			return this.path.equals(((Path) o).path);
		}

	}
}
