package com.jcomm.datastructures;

public class Vertex {
	
	private char label;
	private boolean visited;
	private int weight;

	public Vertex(char label, int weight) {

		this.label = label;
		this.weight = weight;

	}
	
	public int getWeight(){
		return this.weight;
	}

	public char getLabel() {

		return this.label;

	}

	public void setVisited(boolean val) {

		this.visited = val;
	}

	public boolean wasVisited() {
		return this.visited;

	}

	@Override
	public String toString() {
		return this.label + "";
	}

}
