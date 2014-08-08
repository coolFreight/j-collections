package com.jcomm.datastructures;

import java.util.List;

public class ListGraph implements Graph {

	private List<Vertex> edges [] = new java.util.LinkedList[1000];
	private Vertex vertices[] = new Vertex[1000];
	
	
	
	public ListGraph(){
		
		for(int i = 0; i < vertices.length; i++)
			vertices[i] = new Vertex(convertInt(i), 1);
		
		for(int i = 0; i < edges.length; i++)
			edges[i] = new java.util.LinkedList<>();
	}
	public static void main(String args[]){
		
		
		ListGraph lg = new ListGraph();
		lg.createEdge('A', 'D');
		lg.createEdge('A', 'B');
		lg.createEdge('B', 'C');
		lg.createEdge('B', 'D');
		lg.createEdge('C', 'E');
		lg.createEdge('D', 'C');
		lg.createEdge('D', 'E');
		lg.createEdge('E', 'B');
		lg.printGraph();
	}
	
	public void createEdge(char start, char source){

		List<Vertex> startEdges = getEdges(start);
		
		
		Vertex vSource = getVertex(source);
		startEdges.add(vSource);
	}
	
	private Vertex getVertex(char label){
		
		return vertices[convertLabel(label)];
	}
	
	private List<Vertex> getEdges(char label){
		
		return edges[convertLabel(label)];
	}
	
	
	private int convertLabel(char label){
		return label - 65;
	}
	
	private char convertInt(int num){
		return (char)('A'+num);
	}
	
	public void printGraph(){
	
		for(int x = 0; x <vertices.length; x++){	
			List<Vertex> vertexEdges = edges[x];
			
			System.out.print(vertices[x]);
			for(Vertex v : vertexEdges){			
				System.out.print(" -> "+v);		
			}	
			
			System.out.println();	
		}
	
	}
	@Override
	public void addEdge(int start, int source, int weight) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void bfs(char label) {
		
		
	}
	
	
}
