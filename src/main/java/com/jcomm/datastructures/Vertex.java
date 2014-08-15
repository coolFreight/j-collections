package com.jcomm.datastructures;

class Vertex {
	
	private String tag;
	private char label;
	private boolean visited;
	private int weight;

	@Deprecated
	public Vertex(char label, int weight) {
		
		this.tag = label +"";
		this.label = label;
		this.weight = weight;

	}
	
	public Vertex(String tag, int weight) {
		this.tag = tag;
		this.weight = weight;
	}
	
	public Vertex(int tag, int weight) {
		this.tag = tag +"";
		this.weight = weight;
	}
	
	public int getWeight(){
		return this.weight;
	}

	@Deprecated
	public char getLabel() {
		return this.label;
	}

	public String getTag() {
		return tag;
	}

	public void setVisited(boolean val) {

		this.visited = val;
	}

	public boolean wasVisited() {
		return this.visited;

	}

	@Override
	public String toString() {
		return this.tag;
	}

}
