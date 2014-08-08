package com.jcomm.datastructures;

public interface Graph {

	
	void printGraph();
	void addEdge(int start, int source, int weight);
	void bfs(char label);
}
