package main.java.bus;

import java.util.List;

/**
 * File:	  main.Graph
 * Author:    Adam Clifton (akclifto@asu.edu)
 * Date:      2019.11.29
 *
 * <p>Description:  This class that will contain the graph of the nodes presented
 * to find the shortest path.
 */
public class GraphShortestPath {

    private final List<RouteNode> vertices;
    private final List<Edge> edges;

    public GraphShortestPath(List<RouteNode> vertices, List<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    public List<RouteNode> getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
