package main.java.bus;

/**
 * File:	  main.Edge
 * Author:    Adam Clifton (akclifto@asu.edu)
 * Date:      2019.11.29
 *
 * <p>Description:  This class that will contain the edges between each route node used
 * to find the shortest path.
 */
public class Edge {

    private final String id;
    private final RouteNode source;
    private final RouteNode destination;
    private final double duration;

    public Edge(String id, RouteNode source, RouteNode destination, double duration) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public RouteNode getDestination() {
        return destination;
    }

    public RouteNode getSource() {
        return source;
    }

    public double getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return source + " " + destination;
    }
}
