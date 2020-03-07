package main.java.bus;

import java.util.*;

/**
 * File:	  ShortestPath.java
 * Author:    Adam Clifton (akclifto@asu.edu)
 * Date:      2019.11.29
 *
 * <p>Description:  This class file will node RouteNodes given in the system and calculate
 * the shortest path from the source node to the destination node.  This is done using
 * Dijkstra's Algorithm to find the shortest path.
 */
public class ShortestPath {

    private final List<RouteNode> nodeList;
    private final List<Edge> edgeList;
    private Set<RouteNode> visitedNodes;
    private Set<RouteNode> unvisitedNodes;
    private Map<RouteNode, RouteNode> predecessors;
    private Map<RouteNode, Double> distance;

    public ShortestPath(GraphShortestPath graph) {
        this.nodeList = new ArrayList<>(graph.getVertices());
        this.edgeList = new ArrayList<>(graph.getEdges());
    }


    public void execute(RouteNode source) {

        visitedNodes = new HashSet<>();
        unvisitedNodes = new HashSet<>();
        distance = new HashMap<>();
        predecessors = new HashMap<>();

        distance.put(source, 0d);
        unvisitedNodes.add(source);

        while (unvisitedNodes.size() > 0) {
            RouteNode node = getMinimum(unvisitedNodes);
            visitedNodes.add(node);
            unvisitedNodes.remove(node);
            findMinDistance(node);
        }
    }

    private double getDistance(RouteNode node, RouteNode target) {

        CalculateRoute calc = new CalculateRoute();
        //TODO
        for (Edge edge : edgeList) {
            if (edge.getSource().equals(node)
                    && edge.getDestination().equals(target)) {
                return edge.getDuration();
            }
        }
        return -1;
    }

    private List<RouteNode> getNeighbors(RouteNode node) {

        List<RouteNode> neighbors = new ArrayList<>();

        for (Edge edge : edgeList) {
            if (edge.getSource().equals(node) && !isVisited(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

    private RouteNode getMinimum(Set<RouteNode> vertices) {

        RouteNode min = null;
        for (RouteNode vertex : vertices) {
            if (min == null) {
                min = vertex;
            } else if (getShortestDistance(vertex) < getShortestDistance(min)) {
                min = vertex;
            }
        }
        return min;
    }


    private double getShortestDistance(RouteNode destination) {

        Double dist = distance.get(destination);
        if (dist == null) {
            return (int) Double.MAX_VALUE;
        } else {
            return dist;
        }

    }

    public LinkedList<RouteNode> getPath(RouteNode target) {

        LinkedList<RouteNode> path = new LinkedList<>();
        RouteNode step = target;

        if (predecessors.get(step) == null) {
            step = predecessors.get(step);
            path.add(step);
        }

        Collections.reverse(path);
        return path;
    }

    private void findMinDistance(RouteNode node) {

        List<RouteNode> adjNodes = getNeighbors(node);

        for (RouteNode target : adjNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                unvisitedNodes.add(target);
            }
        }
    }


    /**
     * Method: Check if a node has been visited.
     * Inputs: vertex : RouteNode
     * Returns: void
     * Description: Method to check if a node on the graph has been visited yet.
     */
    private boolean isVisited(RouteNode vertex) {
        return visitedNodes.contains(vertex);
    }


}
