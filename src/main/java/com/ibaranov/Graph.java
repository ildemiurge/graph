package com.ibaranov;

import java.util.List;

public interface Graph {

    /**
     * Checks if graph is directed
     *
     * @return true if graph is directed
     */
    boolean isDirected();

    /**
     * Adds new vertex to the graph
     *
     * @param vertex new vertex
     */
    void addVertex(Vertex vertex);

    /**
     * Adds new edge to the graph
     *
     * @param edge new edge
     * @return true if edge is added, otherwise false
     */
    boolean addEdge(Edge edge);

    /**
     * Returns list of edges connecting to vertices
     *
     * @param vertex1 vertex from
     * @param vertex2 vertex to
     * @return list of edges
     */
    List<Edge> getPath(Vertex vertex1, Vertex vertex2);
}
