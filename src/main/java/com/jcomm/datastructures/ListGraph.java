package com.jcomm.datastructures;

import java.util.List;
import java.util.function.Function;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class ListGraph implements Graph {

	private List<Vertex> edges [] = new java.util.LinkedList[27];
	private Vertex vertices[] = new Vertex[1000];
	private Queue<Vertex> pQueue = new java.util.LinkedList<>();
	private Stack<Vertex> stackVertices = new Stack<>();
	private Vertex parentVertices[] = new Vertex[1000];
	
	
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
		
		//vertices[]
		
		
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	
	
	public LinkedList<Vertex> findPath(char start, char end){
		this.bfs(start, (Vertex v) -> { System.out.println(v); return v;} );
		return findPath(start, end, new LinkedList<Vertex>(), parentVertices);
	}
	
	//A B C D E F G
	//0 1 2 3 4 5 6
	//    A     C F
	// shortest path from A to G
	
	// A -> A
	// A -> C
	// A -> F
	// A -> G 
	private LinkedList<Vertex> findPath(char start, char end,  LinkedList<Vertex> paths, Vertex[] parents){
		
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
