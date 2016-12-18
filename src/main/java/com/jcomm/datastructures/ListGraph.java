package com.jcomm.datastructures;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ListGraph implements Graph {

	private Map<String, JList<Edge>> mapOfEdges = new HashMap<>();
	private Map<String, Vertex> mapOfVertices = new HashMap<>();
	private Map<String, Integer> mapOfVerticeIndex = new HashMap<>();
	private JList<Vertex> edges[] = new JLinkedList[1000];
	private Vertex vertices[] = new Vertex[1000];
	private int verticeCount = 0;
	private Queue<Vertex> pQueue = new java.util.LinkedList<>();
	private Stack<Vertex> stackVertices = new Stack<>();
	private JLinkedList<Vertex> parentVertices[] = new JLinkedList[1000];
	private int entry[] = new int[1000];
	private int exit[] = new int[1000];
	private int parent[] = new int[1000];
	private STATE state[] = new STATE[1000];
	private Vertex parentVertex [] = new Vertex[1000];
	private int time = 0;


	private enum STATE{
		UNDISCOVERED,DISCOVERED,PROCESSSED
	}

	public ListGraph() {
		for (int i = 0; i < edges.length; i++)
			edges[i] = new JLinkedList<>();
		for (int i = 0; i < parentVertices.length; i++) {
			parentVertices[i] = new JLinkedList<>();
			state[i] = STATE.UNDISCOVERED;
			entry[i] = -1;
			exit[i] = -1;
			parent[i] = -1;
		}
	}

	@Deprecated
	private Vertex getVertex(char label) {
		return vertices[convertLabel(label)];
	}

	private Vertex getVertex(int index) {
		return vertices[index];
	}

	private Vertex getVertex(String label) {
		int index = getVertexIndex(label);
		return vertices[index];
	}

	private JList<Vertex> getEdges(int index) {
		return edges[index];
	}
	
	@Override
	public JList<Edge> getEdges(String label) {

		return mapOfEdges.get(label);
	}

	/**
	 * This will convert the label into the key array index where the vertex is
	 * stored
	 * 
	 * @param label
	 *            the vertex character label
	 * 
	 *            return the index value where the Vertex is stored
	 */
	private int convertLabel(char label) {
		return label - 65;
	}

	private char convertInt(int num) {
		return (char) ('A' + num);
	}

	public void printGraph() {

		for (int x = 0; x < vertices.length; x++) {
			JList<Vertex> vertexEdges = edges[x];
			System.out.print(vertices[x]);
			for (Vertex v : vertexEdges) {
				System.out.print(" -> " + v);
			}

			System.out.println();
		}

	}

	@Override
	public void bfs(String label, Function<Vertex, Vertex> action) {
		Vertex v = getVertex(label);
		if (v.wasVisited() == false) {
			v.setVisited(true);
			action.apply(v);
			pQueue.add(v);
		}
		
		JList<Vertex> listOfAdjacent = getEdges(mapOfVerticeIndex.get(v.getLabel()));
		for (Vertex neighbor : listOfAdjacent) {

			if (neighbor.wasVisited() == false) {
				neighbor.setVisited(true);
				action.apply(neighbor);
				pQueue.add(neighbor);
			}
		}

		if (pQueue.isEmpty())
			return;

		pQueue.remove();
		if (pQueue.isEmpty())
			return;
		bfs(pQueue.peek().getLabel(), action);
	}

	public void iterativeBfs(String label, Function<Vertex, Vertex> action) {
		Vertex v = getVertex(label);
		v.setVisited(true);
		Queue<Vertex> pQueue = new java.util.LinkedList<>();

		while(v!=null){

			JList<Vertex> listOfAdjacent = getEdges(mapOfVerticeIndex.get(v.getLabel()));
			for(Vertex n : listOfAdjacent){
				if(!n.wasVisited()){
					pQueue.add(n);
					action.apply(n);
				}
			}

			v = pQueue.remove();
		}
	}

	public void dfs(Function<Vertex, Vertex> action) {

		for (Vertex next : vertices) {

			if (next!= null && next.wasVisited() == false) {
				dfs(next.getLabel(), action);

			}
		}
		
		for (Vertex next : vertices)
			next.setVisited(false);	
	}

	public void dfsRecursive(String label, BiFunction<Vertex, Vertex, Boolean> processEdge){
		Vertex u = getVertex(label);
		int vertexIndex = mapOfVerticeIndex.get(label);
		state[vertexIndex] = STATE.DISCOVERED;
		entry[vertexIndex] = ++time;

		for(Vertex v : getEdges(vertexIndex)){
			int vIndex =  mapOfVerticeIndex.get(v.getLabel());
			if(state[vIndex] == STATE.UNDISCOVERED){
				parentVertex[vIndex] = u;
				parent[vIndex] = vertexIndex;
				processEdge(vertexIndex, vIndex);
				dfsRecursive(v.getLabel(), processEdge);
			}else if(state[vIndex] != STATE.PROCESSSED){
				processEdge(vertexIndex, vIndex);
			}

		}

		state[vertexIndex] = STATE.PROCESSSED;
		exit[vertexIndex] = time;
		time++;

	}

	private void processEdge(int x, int y){
		if(state[y]==STATE.DISCOVERED && parent[x] != y){
			System.out.println("Found cycle");
		}
	}

	private void processEdge(Vertex u, Vertex v){
		int uIndex =  mapOfVerticeIndex.get(u.getLabel());
		int vIndex =  mapOfVerticeIndex.get(v.getLabel());

		if(state[vIndex]==STATE.DISCOVERED && parent[uIndex] != vIndex){
			System.out.println("Found cycle");
		}
	}

	public void dfsIter(String label, Function<Vertex, Vertex> action) {


		boolean foundUnVisted = false;
		Vertex v = getVertex(label);
		while(v != null) {
			foundUnVisted = false;
			if(!v.wasVisited()) {
				action.apply(v);
				v.setVisited(true);
				stackVertices.push(v);
			}

			JList<Vertex> listOfAdjacent = getEdges(mapOfVerticeIndex.get(v.getLabel()));
			for (Vertex t : listOfAdjacent) {

				if (!t.wasVisited()) {
					foundUnVisted = true;
					v = t;
					break;
				}


			}
			if (!foundUnVisted) {
				v = stackVertices.pop();

			}
		}
	}

	public void dfs(String label, Function<Vertex, Vertex> action) {

		Vertex v = getVertex(label);
		if (v.wasVisited() == false) {
			v.setVisited(true);
			action.apply(v);
			stackVertices.add(v);
		}

		JList<Vertex> listOfAdjacent = getEdges(mapOfVerticeIndex.get(v.getLabel()));
		for (Vertex t : listOfAdjacent) {
			if (t.wasVisited() == false) {
				t.setVisited(true);
				int vertexIndex = mapOfVerticeIndex.get(t.getLabel());
				parentVertices[vertexIndex].add(v);
				action.apply(t);
				stackVertices.add(t);
				dfs(t.getLabel(), action);
			}
		}

		if (stackVertices.empty())
			return;

		stackVertices.pop();
	}

	public void addVertex(Vertex vertex) {
		vertices[verticeCount] = vertex;
		verticeCount++;

		mapOfVertices.put(vertex.getLabel(), vertex);
		mapOfEdges.put(vertex.getLabel(), new JLinkedList<>());
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

//	public JLinkedList<Vertex> findPath(Vertex start, Vertex end) {
//		// this.bfs(start.getLabel(), (Vertex v) -> { return v;} );
//		//return findPath(start, end, new JLinkedList<Vertex>(), parentVertices);
//	}

	private JLinkedList<Vertex> findPath(Vertex start, Vertex end,
			JLinkedList<Vertex> paths, Vertex[] parents) {

		if (start.equals(end) || start.equals(".")) {
			paths.add(end);
			return paths;
		} else {

			// int endIndex = convertLabel(end);
			// //recurseCall
			// findPath(start, parents[endIndex].getLabel(), paths, parents);
			// paths.add(this.getVertex(end));
		}

		return paths;

	}

	/**
	 * Edges are maintained internally with array of linked list vertices that is connected to.
	 *
	 * @param start
	 * @param end
	 * @param weight
	 * @param directed
	 */
	@Override
	public void addEdge(String start, String end, int weight, boolean directed) {
		Vertex s = vertices[mapOfVerticeIndex.get(start)];
		Vertex e = vertices[mapOfVerticeIndex.get(end)];
		Edge edge = new Edge(s, e, weight);

		JList<Vertex> startEdges = getEdges(mapOfVerticeIndex.get(start));
		startEdges.add(getVertex(end));

		JList<Edge> edges = mapOfEdges.get(s.getLabel());
		edges.add(edge);

		if (directed) {
			edges.add(new Edge(e, s, weight));
		}
	}

	@Override
	public void createVertex(String label) {

		Vertex vertex = new Vertex(label);
		vertices[verticeCount] = vertex;

		this.mapOfVerticeIndex.put(label, verticeCount);
		edges[verticeCount] = new JLinkedList<Vertex>();
		verticeCount++;
		mapOfEdges.put(vertex.getLabel(), new JLinkedList<>());

	}

	private Integer getVertexIndex(String label) {
		if(mapOfVerticeIndex.containsKey(label))
			return this.mapOfVerticeIndex.get(label);
		
		throw new IllegalArgumentException(label+" vertex does not exist");
	}

	/***
	 * This will return a list of the descendants from the parent.
	 * 
	 * @param label vertex to start at
	 * @param depth how many level of descendants should be returned.
	 * @return list of descendants
	 */
	public JList<String> getDescedants(String label, int depth){
				
		Vertex v = getVertex(label);
		v.setVisited(true);
		
		JList<Vertex> list = getEdges(mapOfVerticeIndex.get(label));
		JList<String> descedancts = new JLinkedList<>();
		
		int countDepth = 0;
		for(Vertex t : list){
			descedancts.add(t.getLabel());
			if(countDepth == depth)
				break;
		}
		return descedancts;
	}


}
