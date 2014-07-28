package com.jcomm.trees;

public class Vertex {
	
	private final char label;
	
	
	public Vertex(char label){
		
		this.label = label;
	}
	
	
	@Override
	public String toString(){
		
		return label+"";
	}

}
