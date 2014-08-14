package com.jcomm.datastructures;

import java.util.List;
import java.util.function.Function;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class ListGraph implements Graph {

	private List<Vertex> edges [] = new java.util.LinkedList[1000];
	private Vertex vertices[] = new Vertex[1000];
	private Queue<Vertex> pQueue = new java.util.LinkedList<>();
	private Stack<Vertex> stackVertices = new Stack<>();
	
	
	public ListGraph(){
		
		for(int i = 0; i < vertices.length; i++)
			vertices[i] = new Vertex(convertInt(i), 1);
		
		for(int i = 0; i < edges.length; i++)
			edges[i] = new java.util.LinkedList<>();
	}
	public static void main(String args[]){
		ListGraph lg = new ListGraph();
		lg.addEdge('A', 'D', 1, true);
		lg.addEdge('A', 'B', 1, true);
		lg.addEdge('B', 'C', 1, true);
		lg.addEdge('B', 'D', 1, true);
		lg.addEdge('C', 'E', 1, true);
		lg.addEdge('D', 'C', 1, true);
		lg.addEdge('D', 'E', 1, true);
		lg.addEdge('E', 'B', 1, true);
		//lg.printGraph();
		lg.bfs('A', (Vertex v) -> { System.out.println(v) ; return v;}); 
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
	public void addEdge(char start, char source, int weight, boolean directed) {
		List<Vertex> startEdges = getEdges(start);
		Vertex vSource = getVertex(source);
		startEdges.add(vSource);
	}
	
	@Override
	public void bfs(char label, Function<Vertex, Vertex> action) {
		
		Vertex v = getVertex(label);
		if(v.wasVisited()==false){
			v.setVisited(true);
			action.apply(v);
			pQueue.add(v);
		}
		
		
		List<Vertex> listOfAdjacent = getEdges(label);
		for(Vertex neighbor : listOfAdjacent){
			
			if(neighbor.wasVisited()==false){
				neighbor.setVisited(true);
				action.apply(neighbor);
				pQueue.add(neighbor);
			}
		}
		
		if(pQueue.isEmpty())
			return;
		
		pQueue.remove();
		if(pQueue.isEmpty())
			return;
		bfs(pQueue.peek().getLabel(), action);			
	}
	
	public void iterativeBfs(char label, Function<Vertex, Vertex> action){
		Vertex v = getVertex(label);
		if(v.wasVisited()==false){
			v.setVisited(true);
			action.apply(v);
			pQueue.add(v);
		}
		
		//while()
	}
	
	@Override
	public void dfs (char label, Function<Vertex, Vertex> action){
		
		Vertex v = getVertex(label);		
		if(v.wasVisited()==false){
			v.setVisited(true);
			action.apply(v);
			stackVertices.add(v);
		}
		
		List<Vertex> listOfAdjacent = getEdges(label);
		for(Vertex t : listOfAdjacent){
			
			if(t.wasVisited()==false){			
				t.setVisited(true);
				action.apply(t);
				stackVertices.add(t);
				dfs (t.getLabel(), action);
			}
		}
		
		if(stackVertices.empty())
			return;
		
		stackVertices.pop();
		
	}
	@Override
	public void addEdge(int start, int source, int weight, boolean directed) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
}
