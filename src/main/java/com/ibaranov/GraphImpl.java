package com.ibaranov;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

public class GraphImpl implements Graph {

    private List<Collection<Vertex>> graph = new ArrayList<>();
    private boolean isDirected;

    public GraphImpl(boolean isDirected) {
        this.isDirected = isDirected;
    }

    public boolean isDirected() {
        return isDirected;
    }

    public void addVertex(Vertex vertex) {
        Collection<Vertex> newVertices = new LinkedHashSet<>();
        newVertices.add(vertex);
        graph.add(newVertices);
    }

    public boolean addEdge(Edge edge) {

        if (isDirected) {
            return addDirectedEdge(edge);
        } else {
            // add undirected edge twice as directed from vertex A to B and from B to A
            return addDirectedEdge(edge) &&
                    addDirectedEdge(new Edge(edge.getVertex2(), edge.getVertex1()));
        }
    }

    private boolean addDirectedEdge(Edge edge) {
        int maxVertexId = graph.size();

        if (edge.getVertex1().getId() > maxVertexId || edge.getVertex2().getId() > maxVertexId) {
            System.out.println("Edge cannot be added, because it has vertex which is not yet added. Edge: " + edge);
            return false;
        } else {
            Collection<Vertex> vertices = graph.get(edge.getVertex1().getId() - 1);
            if (vertices.contains(edge.getVertex2())) {
                System.out.println(edge.getVertex1() + " already has edge to vertex " + edge.getVertex2());
                return false;
            } else {
                vertices.add(edge.getVertex2());
                System.out.println(edge.getVertex2() + " has been added");
                return true;
            }
        }
    }

    public List<Edge> getPath(Vertex vertexFrom, Vertex vertexTo) {
        List<Edge> listOfEdges = new ArrayList<>();

        Deque<Vertex> path = new ArrayDeque<>();

        if (getPath(vertexFrom, vertexTo, path) && path.size() > 1) {
            System.out.println("Result path: " + path);

            Iterator<Vertex> it = path.iterator();
            Vertex prevVertex = it.next();
            while (it.hasNext()) {
                Vertex currentVertex = it.next();
                listOfEdges.add(new Edge(prevVertex, currentVertex));
                prevVertex = currentVertex;
            }
            System.out.println("Result listOfEdges: " + listOfEdges);
        } else {
            System.out.println("Path not found");
        }

        return listOfEdges;
    }

    private boolean getPath(Vertex vertexFrom, Vertex vertexTo, Deque<Vertex> currentPath) {

        Collection<Vertex> verticesToCheck = graph.get(vertexFrom.getId() - 1);

        if (!vertexFrom.equals(vertexTo) && !currentPath.contains(vertexFrom)) { // avoid loops
            currentPath.addLast(vertexFrom);

            if (verticesToCheck.size() > 1) { // otherwise there is no path from this vertex
                if (verticesToCheck.contains(vertexTo)) {
                    currentPath.addLast(vertexTo);
                    return true;
                } else {
                    // check path from each vertex recursively
                    for (Vertex vertex : verticesToCheck) {
                        if (!vertex.equals(vertexFrom)) { // avoid loops through the same vertex
                            if (getPath(vertex, vertexTo, currentPath)) {
                                return true;
                            } else {
                                currentPath.removeLast();
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return "GraphImpl{" +
                "graph=" + graph +
                ", isDirected=" + isDirected +
                '}';
    }
}
