package com.jcomm.datastructures;

import java.util.function.Function;

public interface Graph {

	
	void printGraph();
	void createVertex(String label);
	void addEdge(String start, String end, int weight, boolean directed);
	void bfs(String label, Function<Vertex, Vertex> action);
	void dfs (String label, Function<Vertex, Vertex> action);
	void init();
	JList<String> getEdges(String label);
}	
