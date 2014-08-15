package com.jcomm.datastructures;

import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.function.Function;

public class ListGraph implements Graph {

	private List<Vertex> edges [] = new java.util.LinkedList[1000];
	private Vertex vertices[] = new Vertex[1000];
	private Queue<Vertex> pQueue = new java.util.LinkedList<>();
	private Stack<Vertex> stackVertices = new Stack<>();
	private Vertex parentVertices[] = new Vertex[1000];
		
	public ListGraph(){
		for(int i = 0; i < vertices.length; i++)
			vertices[i] = new Vertex(i, 1);
		
		for(int i = 0; i < edges.length; i++)
			edges[i] = new java.util.LinkedList<>();
	}
	
	public void createEdge(char start, char source){

		List<Vertex> startEdges = getEdges(start);
		Vertex vSource = getVertex(source);
		startEdges.add(vSource);
	
	}
	
	@Deprecated
	private Vertex getVertex(char label){
		return vertices[convertLabel(label)];
	}
	
	private Vertex getVertex(int index){
		return vertices[index];
	}
	
	private List<Vertex> getEdges(char label){
		
		return edges[convertLabel(label)];
	}
	
	private List<Vertex> getEdges(int vertex){
		
		return edges[vertex];
	}
	
	/**
	 * This will convert the label into the key array index 
	 * where the vertex is stored
	 * 
	 *  @param label the vertex character label
	 *  
	 *  return the index value where the Vertex is stored
	 */
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
			parentVertices[0] = new Vertex('.', -1);
		}
		
		
		List<Vertex> listOfAdjacent = getEdges(label);
		for(Vertex neighbor : listOfAdjacent){
			
			if(neighbor.wasVisited()==false){
				
				parentVertices[convertLabel(neighbor.getLabel())] = v;
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
		
		List<Vertex> startEdges = getEdges(start);
		Vertex vSource = getVertex(source);
		startEdges.add(vSource);
		if(!directed){
			List<Vertex> endEdges = getEdges(source);
			Vertex vStart = getVertex(start);
			endEdges.add(vStart);
		}
		
		
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	
	
	public JLinkedList<Vertex> findPath(char start, char end){
		this.bfs(start, (Vertex v) -> {  return v;} );
		return findPath(start, end, new JLinkedList<Vertex>(), parentVertices);
	}
	
	private JLinkedList<Vertex> findPath(char start, char end,  JLinkedList<Vertex> paths, Vertex[] parents){
		
		if(start == end || start == '.'){
			paths.add(getVertex(end));
			return paths;
		}else{
			
			int endIndex = convertLabel(end);
			//recurseCall
			findPath(start, parents[endIndex].getLabel(), paths, parents);
			paths.add(this.getVertex(end));
		}
		
		return paths;
		
	}

}
