package com.jcomm.datastructures;

import java.util.function.Function;

public interface Graph {

	
	void printGraph();
	void addEdge(int start, int source, int weight, boolean directed);
	void addEdge(char start, char source, int weight, boolean directed);
	void bfs(char label, Function<Vertex, Vertex> action);
	void dfs(char label, Function<Vertex, Vertex> action);
	void init();
}
