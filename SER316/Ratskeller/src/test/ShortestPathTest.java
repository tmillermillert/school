package test;

import main.java.bus.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ShortestPathTest {

    private List<RouteNode> nodes;
    private List<Edge> edges;

    @org.junit.Test
    public void execute() {

        nodes = new ArrayList<>();
        edges = new ArrayList<>();

        for (int i = 0; i < 11; i++) {
            RouteNode location = new RouteNode("Node " + i, "NodeID " + i);
            nodes.add(location);
        }

        addEdgePath("Edge 0", 0, 1, 10.00);
        addEdgePath("Edge 1", 0, 2, 110.00);
        addEdgePath("Edge 2", 0, 4, 176.00);
        addEdgePath("Edge 3", 2, 6, 188.00);
        addEdgePath("Edge 4", 2, 7, 205.00);
        addEdgePath("Edge 5", 3, 7, 250.00);
        addEdgePath("Edge 6", 3, 8, 84.00);
        addEdgePath("Edge 7", 5, 8, 167.00);
        addEdgePath("Edge 8", 7, 8, 402.00);
        addEdgePath("Edge 9", 7, 9, 40.00);
        addEdgePath("Edge 10", 0, 9, 600.00);

        GraphShortestPath graph = new GraphShortestPath(nodes, edges);
        ShortestPath path = new ShortestPath(graph);
        path.execute(nodes.get(0));

        LinkedList<RouteNode> lane = path.getPath(nodes.get(10));

        assertNotNull(lane);
        assertTrue(lane.size() > 0);

        for (RouteNode node : lane) {
            System.out.println(node);
        }

    }


    private void addEdgePath(String laneID, int sourceLocationNo,
                             int destLocationNo, double dur) {
        Edge lane = new Edge(laneID, nodes.get(sourceLocationNo), nodes.get(destLocationNo), dur);
        edges.add(lane);
    }




}