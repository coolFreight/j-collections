/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcomm.datastructures;

import java.util.Queue;

/**
 *
 * @author jova
 */
public class Graph {

    private final int graph[][];
    private final int ROWS;
    private final int COLS;
    private java.util.Stack<Vertex> visitedNodes = new java.util.Stack<Vertex>();
    private Queue<Vertex> queueOfNodes = new java.util.LinkedList<>();
    private Vertex[] vertices;

    public Graph(int size) {

        vertices = new Vertex[size];
        this.ROWS = size;
        this.COLS = size;
        this.graph = new int[this.ROWS][this.COLS];
        int x = 0;
        while (x < size) {
            vertices[x] = new Vertex(((char) (x + 65)));
            x++;
        }
    }

    public static void main(String args[]) {
        Graph graph = new Graph(9);
        graph.addEdge('A', 'B');
        graph.addEdge('B', 'F');
        graph.addEdge('F', 'H');
        graph.addEdge('A', 'C');
        graph.addEdge('A', 'D');
        graph.addEdge('D', 'G');
        graph.addEdge('G', 'I');
        graph.addEdge('A', 'E');
        graph.printGraph();

        graph.bfs('A');
    }

    public void addEdge(char start, char end) {
        addEdge(start - 65, end - 65);
    }

    private void addEdge(int start, int end) {
        graph[start][end] = 1;
        graph[end][start] = 1;
    }

    public void printGraph() {
        System.out.print(" ");
        for (int x = 0; x < COLS; x++) {
            System.out.print((char) (x + 65));
        }
        System.out.println();

        for (int row = 0; row < ROWS; row++) {
            System.out.print((char) (row + 65));
            for (int col = 0; col < COLS; col++) {
                System.out.print(graph[row][col]);
            }
            System.out.println();
        }
    }

    public void bfs(char label) {

        int index = label - 65;


        if (vertices[index].wasVisited() == false) {
            vertices[index].setVisited(true);

            System.out.println(label);
        }


        for (int cols = 0; cols < COLS; cols++) {

            if (graph[index][cols] == 1 && vertices[cols].wasVisited() == false) {
                vertices[cols].setVisited(true);
                queueOfNodes.add(vertices[cols]);
                System.out.println("Enqueue: " + vertices[cols].getLabel());
            }//end of if

        }//end of for loop

        Vertex temp = queueOfNodes.poll();

        if (temp != null) {
            System.out.println("Dequeue: " + temp.getLabel());
            bfs(temp.getLabel());
        }
    }

    public void findConnected(char label) {
        int index = label - 65;
        if (vertices[index].wasVisited() == false) {
            vertices[index].setVisited(true);
            visitedNodes.push(vertices[index]);
            System.out.println("push " + label);
        }
        //traverse columns
        for (int col = 0; col < COLS; col++) {
            if (graph[index][col] == 1 && vertices[col].wasVisited() == false) {
                findConnected((char) (col + 65));
            }
        }

        if (visitedNodes.isEmpty() == false) {
            System.out.println("pop " + visitedNodes.pop().getLabel());
        }
        if (visitedNodes.isEmpty() == false) {
            findConnected(visitedNodes.peek().getLabel());
        }
    }

    private class Vertex {

        private char label;
        private boolean visited;

        public Vertex(char label) {

            this.label = label;

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
    }
}
