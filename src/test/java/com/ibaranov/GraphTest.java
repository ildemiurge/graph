package com.ibaranov;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GraphTest {

    @Test
    public void testDirectedGraph() {
        Graph directedGraph = new GraphImpl(true);

        Vertex vertex1 = new Vertex(1);
        Vertex vertex2 = new Vertex(2);
        Vertex vertex3 = new Vertex(3);
        Vertex vertex4 = new Vertex(4);
        Vertex vertex5 = new Vertex(5);

        directedGraph.addVertex(vertex1);
        directedGraph.addVertex(vertex2);
        directedGraph.addVertex(vertex3);
        directedGraph.addVertex(vertex4);
        directedGraph.addVertex(vertex5);

        directedGraph.addEdge(new Edge(vertex1, vertex3));
        directedGraph.addEdge(new Edge(vertex3, vertex2));
        directedGraph.addEdge(new Edge(vertex3, vertex4));
        directedGraph.addEdge(new Edge(vertex4, vertex5));
        directedGraph.addEdge(new Edge(vertex4, vertex1));

        System.out.println("Result directedGraph: " + directedGraph);

        System.out.println("TEST#1");
        Assert.assertEquals("Path from the same vertex should be empty",
                Collections.EMPTY_LIST, directedGraph.getPath(vertex1, vertex1));

        System.out.println("TEST#2");
        Assert.assertEquals("Path with single edge",
                new ArrayList<>(Arrays.asList(new Edge(vertex3, vertex2))),
                directedGraph.getPath(vertex3, vertex2));

        System.out.println("TEST#3");
        Assert.assertEquals("Path with 3 edges",
                new ArrayList<>(Arrays.asList(new Edge(vertex1, vertex3), new Edge(vertex3, vertex4), new Edge(vertex4, vertex5))),
                directedGraph.getPath(vertex1, vertex5));

        System.out.println("TEST#4");
        Assert.assertEquals("Path with 2 edges",
                new ArrayList<>(Arrays.asList(new Edge(vertex1, vertex3), new Edge(vertex3, vertex4))),
                directedGraph.getPath(vertex1, vertex4));

        System.out.println("TEST#5");
        Assert.assertEquals("Unreachable path should be empty",
                Collections.EMPTY_LIST, directedGraph.getPath(vertex2, vertex5));
    }

    @Test
    public void testUndirectedGraph() {
        Graph undirectedGraph = new GraphImpl(false);

        Vertex vertex1 = new Vertex(1);
        Vertex vertex2 = new Vertex(2);
        Vertex vertex3 = new Vertex(3);
        Vertex vertex4 = new Vertex(4);
        Vertex vertex5 = new Vertex(5);

        undirectedGraph.addVertex(vertex1);
        undirectedGraph.addVertex(vertex2);
        undirectedGraph.addVertex(vertex3);
        undirectedGraph.addVertex(vertex4);
        undirectedGraph.addVertex(vertex5);

        undirectedGraph.addEdge(new Edge(vertex1, vertex4));
        undirectedGraph.addEdge(new Edge(vertex1, vertex3));
        undirectedGraph.addEdge(new Edge(vertex2, vertex3));
        undirectedGraph.addEdge(new Edge(vertex3, vertex5));
        undirectedGraph.addEdge(new Edge(vertex4, vertex5));

        System.out.println("Result undirectedGraph: " + undirectedGraph);

        System.out.println("TEST#1");
        Assert.assertEquals("Path from the same vertex should be empty",
                Collections.EMPTY_LIST, undirectedGraph.getPath(vertex1, vertex1));

        System.out.println("TEST#2");
        Assert.assertEquals("Path with single edge",
                new ArrayList<>(Arrays.asList(new Edge(vertex3, vertex2))),
                undirectedGraph.getPath(vertex3, vertex2));

        System.out.println("TEST#3");
        Assert.assertEquals("Path with 3 edges",
                new ArrayList<>(Arrays.asList(new Edge(vertex2, vertex3), new Edge(vertex3, vertex1), new Edge(vertex1, vertex4))),
                undirectedGraph.getPath(vertex2, vertex4));

        System.out.println("TEST#4");
        Assert.assertEquals("Path with 2 edges",
                new ArrayList<>(Arrays.asList(new Edge(vertex1, vertex4), new Edge(vertex4, vertex5))),
                undirectedGraph.getPath(vertex1, vertex5));
    }
}
