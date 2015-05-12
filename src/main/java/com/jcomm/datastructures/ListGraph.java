package com.jcomm.datastructures;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.function.Function;

public class ListGraph implements Graph {

	private Map<Vertex, JList<Vertex>> mapOfEdges = new HashMap<>();
	private Map<String, Vertex> mapOfVertices = new HashMap<>();
	private Map<String, Integer> mapOfVerticeIndex = new HashMap<>();
	private JList<Vertex> edges[] = new JLinkedList[1000];
	private Vertex vertices[] = new Vertex[1000];
	private int verticeCount = 0;
	private Queue<Vertex> pQueue = new java.util.LinkedList<>();
	private Stack<Vertex> stackVertices = new Stack<>();
	private JLinkedList<Vertex> parentVertices[] = new JLinkedList[1000];

	public ListGraph() {
		for (int i = 0; i < edges.length; i++)
			edges[i] = new JLinkedList<>();
		for (int i = 0; i < parentVertices.length; i++)
			parentVertices[i] = new JLinkedList<>();
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
	public JList<String> getEdges(String label) {
		int index = getVertexIndex(label);
		
		JLinkedList<String> list = new JLinkedList<>();
		for(Vertex v : edges[index]){
			list.add(v.getLabel());
		}

		return list;
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

	public void iterativeBfs(char label, Function<Vertex, Vertex> action) {
		Vertex v = getVertex(label);
		if (v.wasVisited() == false) {
			v.setVisited(true);
			action.apply(v);
			pQueue.add(v);
		}
	}

	public void dfsIterative(String label, Function<Vertex, Vertex> action) {
		boolean found = true;
		Vertex v = getVertex(label);
		while(v!=null) {

			if(!v.wasVisited() && found){
				v.setVisited(true);
				action.apply(v);
				stackVertices.add(v);
				found = false;
			}

			JList<Vertex> listOfAdjacent = getEdges(mapOfVerticeIndex.get(v.getLabel()));
			for (Vertex next : listOfAdjacent) {

				if (next != null && next.wasVisited() == false) {
					v = next;
					found = true;
					break;

				}
			}

			if(!found){
				stackVertices.pop();
				if(stackVertices.isEmpty())
					return;
				v = stackVertices.peek();
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
		mapOfEdges.put(vertex, new JLinkedList<>());
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}


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

	@Override
	public void addEdge(String start, String end, int weight, boolean directed) {
		JList<Vertex> startEdges = getEdges(mapOfVerticeIndex.get(start));
		startEdges.add(getVertex(end));

		if (!directed) {
			JList<Vertex> endEdges = getEdges(mapOfVerticeIndex.get(end));
			endEdges.add(getVertex(start));
		}
	}

	@Override
	public void createVertex(String label) {

		Vertex vertex = new Vertex(label);
		vertices[verticeCount] = vertex;

		this.mapOfVerticeIndex.put(label, verticeCount);
		edges[verticeCount] = new JLinkedList<Vertex>();
		verticeCount++;
		mapOfEdges.put(vertex, new JLinkedList<>());

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
