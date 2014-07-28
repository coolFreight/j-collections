package com.jcomm.trees;

import com.jcomm.datastructures.CollectionsHelper;

public class Graph {

	private final int size;
	private final int row;
	private final int column;
	private final int [][]graph;
	private final Vertex vertexList[];
	private int numOfVerts = 0;
	
	public Graph(int size){
		this.vertexList = new Vertex[size];
		this.size = size;
		this.row = size;
		this.column = size;
		graph = new int[row][column];
		
		for(int r = 0; r< row; r++){
			for(int c = 0; c < column; c++)
				graph[r][c] = 0;					
		}	
	}
	
	public void addVertex(char lab){
		
		Vertex v = new Vertex(lab);
		vertexList[numOfVerts++] = new Vertex(lab);
		
		System.out.println("Created vertex : "+v);
	}
	
	public void addEdge(int start, int end){
		graph[start][end] = 1;
		graph[end][start] =1;
	}
	
	public void addEdge(char start, char end){
		
		addEdge(start-65, end-65);
	}
	
	
	public void printGraph(){
		
		System.out.print("  ");
		CollectionsHelper.printArray(vertexList);
		System.out.println();
		
		for(int r = 0; r < row; r++){
			System.out.print(vertexList[r]+" ");
			for(int c =0; c <column; c++)
				System.out.print(graph[r][c]);		
			System.out.println();
		}
	}
	
	/**
	 * Will create n amount of vertices
	 * 
	 * @param n  Number of vertices to be created for this graph
	 */
	public void createVertices(int n){
		
		if(n>26)
			throw new IllegalArgumentException("No more than 26 vertices can be created");
		
		for(int i = 0; i<n; i++)
			addVertex((char)(i+65));
	}
	
	public void displayVertex(int v)
	{
		System.out.println(vertexList[v]);
	}
	
	public static void main(String args[]){
		
		Graph g = new Graph(5);	
		g.createVertices(5);
		
		g.addEdge('A', 'B');
		g.addEdge('B', 'C');
		g.addEdge('A', 'D');
		g.addEdge('D', 'E');
		
		
		g.printGraph();
	
	}
	
}
