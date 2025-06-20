package com.jcomm.datastructures;

import java.util.List;

public class Path implements Comparable<Path>, Cloneable {

	private final Vertex start;
	private final List<Vertex> listOfDestinations;

	private int cost;
	private final String path;

	public Path(Vertex start, Vertex otherNode, int cost) {
		this.start = start;
		this.listOfDestinations = new java.util.LinkedList<>();
		this.listOfDestinations.add(otherNode);
		this.cost = cost;
		this.path = createPath(start);
	}

	public Path(Vertex start, List<Vertex> vertices, int cost) {
		this.start = start;
		this.listOfDestinations = vertices;
		this.cost = cost;
		this.path = createPath(start);
	}

	public Vertex getStartNode() {
		return start;
	}

	public List<Vertex> getDestinations() {
		return this.listOfDestinations;
	}

	public Vertex getEndNode() {
		return listOfDestinations.get(listOfDestinations.size() - 1);
	}

	public void addDestination(Vertex v, int cost) {
		this.cost += cost;
		this.listOfDestinations.add(v);
	}

	private String createPath(Vertex start) {

		StringBuffer path = new StringBuffer();
		path.append(start);
		for (Vertex v : this.listOfDestinations) {
			path.append(" to " + v);
		}
		path.append(" " + this.cost);
		return path.toString();
	}

	@Override
	public String toString() {
		return path;
	}

	public int getCost() {
		return this.cost;
	}

	@Override
	public int compareTo(Path o) {

		if (this.cost > o.cost) {
			return 1;
		} else if (this.cost < o.cost) {
			return -1;
		}

		return 0;
	}

	@Override
	public boolean equals(Object o) {
		return this.path.equals(((Path) o).path);
	}

}