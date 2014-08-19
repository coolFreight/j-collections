package com.jcomm.datastructures;

public class Vertex implements Comparable<Vertex> {
	
	private int tag;
	private String label;
	private boolean visited;

	
	public Vertex(String label){
		this.label = label;
	}
	public Vertex(int tag, String label) {
		this.label = label;
		this.tag = tag;
	}
	
	public Vertex(int tag) {
		this.tag = tag;
	}
	

	public String getLabel() {
		return this.label;
	}

	public int getTag() {
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
		return label;
	}
	
	@Override
	public boolean equals(Object o){
		
		return this.label.equals(((Vertex)o).getLabel());	
	}
	
	@Override
	public int hashCode(){
		return label.hashCode();
		
	}

	@Override
	public int compareTo(Vertex o) {	
		return this.label.compareTo(o.getLabel());
	}

}
