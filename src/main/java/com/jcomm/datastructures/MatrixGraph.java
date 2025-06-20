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
import java.util.function.Function;

/**
 *
 * @author jova
 */
public class MatrixGraph implements Graph {

	private final int[][] graph;
	private final int ROWS;
	private final int COLS;
	private final Queue<Vertex> queueOfNodes = new java.util.LinkedList<>();
	private final Stack<Vertex> visitedNodes = new Stack<>();
	private final PriorityQueue<Path> pQueue = new PriorityQueue<Path>();
	private final Vertex[] vertices;
	private int vertexIndexCount = 0;
	private final Map<String, Integer> vertexIndexes = new HashMap<>();

	public MatrixGraph(int size) {

		vertices = new Vertex[size];
		this.ROWS = size;
		this.COLS = size;
		this.graph = new int[this.ROWS][this.COLS];
	}

	public void createVertex(String label) {
		this.vertexIndexes.put(label, this.vertexIndexCount);
		vertices[this.vertexIndexCount] = new Vertex(label);
		vertexIndexCount++;
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
	public void dfs(String label) {

		Vertex v = getVertex(label);
		v.setVisited(true);
		visitedNodes.push(v);
		System.out.println("Visited vertex " + v);
		int row = getVertexIndex(label);
		for (int i = 0; i < this.COLS; i++) {
			if (graph[row][i] == 1) {
				Vertex t = getVertex(i);
				if (!t.wasVisited())
					dfs(t.getLabel());
			}
		}
	}

	public void mst(String label) {

		Vertex v = getVertex(label);
		v.setVisited(true);
		int row = getVertexIndex(label);
		for (int i = 0; i < this.COLS; i++) {
			if (graph[row][i] == 1) {
				Vertex t = getVertex(i);
				if (!t.wasVisited()) {
					System.out.println(v + " to " + t);
					mst(t.getLabel());
				}
			}
		}
	}

	public List<Path> mwst() {
		return mwst("A", new java.util.ArrayList<Path>());
	}

	private List<Path> mwst(String label, List<Path> mininumSpanTree) {
		Vertex v = getVertex(label);
		v.setVisited(true);
		findCheapsestNeighbor(v, mininumSpanTree);
		return mininumSpanTree;
	}

	public List<Path> findShortestPath(String start) {

		List<Path> listOfPaths = new java.util.ArrayList<Path>();
		return findShortestPaths("A", listOfPaths, null);
	}

	private List<Path> findShortestPaths(String start, List<Path> listOfPaths,
			Path currentPath) {

		Vertex vStart = getVertex(start);
		vStart.setVisited(true);
		int row = getVertexIndex(start);

		for (int col = 0; col < COLS; col++) {
			Vertex tempVertex = getVertex(col);
			if (graph[row][col] > 0 && !tempVertex.wasVisited()) {
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

		return findShortestPaths(newPath.getEndNode().getLabel(), listOfPaths,
				newPath);

	}

	private void shortestPathQueueInsert(Path newPath) {
		Path temp = null;
		for (Path p : pQueue) {
			if (p.getEndNode().getLabel() == newPath.getEndNode().getLabel())
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

		int r = getVertexIndex(v.getLabel());
		Vertex start = getVertex(r);

		// getting all adjacent neighbors
		for (int c = 0; c < COLS; c++) {
			Vertex end;
			if (graph[r][c] > 0) {
				end = getVertex(c);
				if (end.wasVisited())
					continue;
				Path p = new Path(start, end, graph[r][c]);
				pQueue.add(p);
			}
		}

		if (pQueue.isEmpty())
			return;

		Path p = pQueue.remove();
		p.getEndNode().setVisited(true);
		pQueue.removeIf(t -> t.getEndNode().getLabel() == p.getEndNode()
				.getLabel());

		mininumSpanTree.add(p);
		findCheapsestNeighbor(p.getEndNode(), mininumSpanTree);
	}

	public int getVertexIndex(String label) {
		return this.vertexIndexes.get(label);
	}

	private Vertex getVertex(String label) {
		int index = this.vertexIndexes.get(label);
		return vertices[index];
	}

	private Vertex getVertex(int index) {
		return vertices[index];
	}

	public void recursiveBFS(String label) {

		Vertex v = getVertex(label);
		if (!v.wasVisited()) {
			v.setVisited(true);
			this.queueOfNodes.add(v);
			System.out.println("Visited vertex : " + v);
		}
		int r = getVertexIndex(label);
		for (int i = 0; i < this.COLS; i++) {

			if (graph[r][i] == 1) {

				Vertex t = this.vertices[i];
				if (!t.wasVisited()) {
					t.setVisited(true);
					this.queueOfNodes.add(t);
					System.out.println("Visited vertex : " + t);
				}
			}
		}
		this.queueOfNodes.poll();
		if (queueOfNodes.isEmpty())
			return;
		this.recursiveBFS(this.queueOfNodes.peek().getLabel());
	}

	public void bfs(String label, Function<Vertex, Vertex> action) {

		int index = getVertexIndex(label);

		if (!vertices[index].wasVisited()) {
			vertices[index].setVisited(true);

			action.apply(vertices[index]);
		}

		for (int cols = 0; cols < COLS; cols++) {

			if (graph[index][cols] == 1 && !vertices[cols].wasVisited()) {
				vertices[cols].setVisited(true);
				queueOfNodes.add(vertices[cols]);
				action.apply(vertices[cols]);
				// System.out.println("Enqueue: " + vertices[cols].getLabel());
			}// end of if

		}// end of for loop

		Vertex temp = queueOfNodes.poll();

		if (temp != null) {
			// System.out.println("Dequeue: " + temp.getLabel());
			bfs(temp.getLabel(), action);
		}
	}

	public void findConnected(String label) {
		int index = getVertexIndex(label);
		if (!vertices[index].wasVisited()) {
			vertices[index].setVisited(true);
			visitedNodes.push(vertices[index]);
			System.out.println("push " + label);
		}
		// traverse columns
		for (int col = 0; col < COLS; col++) {
			if (graph[index][col] == 1 && !vertices[col].wasVisited()) {
				findConnected(getVertex(col).getLabel());
			}
		}

		if (!visitedNodes.isEmpty()) {
			System.out.println("pop " + visitedNodes.pop().getLabel());
		}
		if (!visitedNodes.isEmpty()) {
			findConnected(visitedNodes.peek().getLabel());
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
                    break;
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
							&& vertices[i].getLabel() == noSuccessorVertex
									.getLabel()) {
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

	public void dfs(char label, Function<Vertex, Vertex> action) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dfs(String label, Function<Vertex, Vertex> action) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addEdge(String start, String end, int weight, boolean directed) {
		int startIdx = getVertexIndex(start);
		int endIdx = getVertexIndex(end);

		graph[startIdx][endIdx] = weight;
		if (!directed)
			graph[endIdx][startIdx] = weight;

	}

	@Override
	public JList<Edge> getEdges(String label) {
		// TODO Auto-generated method stub
		return null;
	}

}
