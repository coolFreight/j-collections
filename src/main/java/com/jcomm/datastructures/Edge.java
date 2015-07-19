package com.jcomm.datastructures;

/**
 * Created by jova on 7/16/15.
 */
public class Edge {

    private final Vertex startVertex;

    public Vertex getStartVertex() {
        return startVertex;
    }

    public Vertex getEndVertex() {
        return endVertex;
    }

    public int getWeight() {
        return weight;
    }

    private final Vertex endVertex;
    private final  int weight;

    public Edge(Vertex start, Vertex end, int weight){
        this.startVertex = start;
        this.endVertex = end;
        this.weight = weight;
    }


}
