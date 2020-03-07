package test;
import main.java.bus.RouteNode;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RouteNodeTest {

    private RouteNode nodeManual;
    private List<RouteNode> nodeList = new ArrayList<>();
    private List<RouteNode> nodeListExpected = new ArrayList<>();


    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Before
    public void setUp() throws Exception {
        set_ManualInputs();
        set_CtorTest();
    }


    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void testManualInputs(){

        nodeManual.getNode().getNode_id();
        nodeManual.getLon();
        nodeManual.getLat();
        System.out.println("Test list size: " + nodeList.size());
        for (RouteNode entry : nodeList){
            System.out.println("Node ID: " + entry.getNode_id());
            entry.getNode_list();
        }
        assertEquals(3, nodeManual.getNode_id());
        assertEquals(112.5465f, nodeManual.getLat(), 0.0);
        assertEquals(131.4465f, nodeManual.getLon(), 0.0);
        assertEquals(1, nodeManual.getNode_list().size());
        assertEquals(3, nodeManual.getNode_list().get(0).getNode_id());
    }

    @Test
    public void  testCtor(){
        System.out.println("Size of test list: " + nodeList.size());
        for (RouteNode entry : nodeList){
            System.out.println("Node ID: " + entry.getNode_id() + " lon: " + entry.getLon() +
                    " lat: " + entry.getLat());
        }
        System.out.println();
        System.out.println("Size of expected list: " + nodeListExpected.size());
        for (RouteNode entry : nodeListExpected){
            System.out.println("Node ID: " + entry.getNode_id() + " lon: " + entry.getLon() +
                    " lat: " + entry.getLat());
        }
        assertTrue(nodeList.equals(nodeListExpected));

    }

    /*set up methods */
    public void set_ManualInputs(){

        nodeManual = new RouteNode();
        nodeManual.setNode_id("3");
        nodeManual.setLon(131.4465f);
        nodeManual.setLat(112.5465f);
        nodeList.add(nodeManual);
        nodeManual.add_to_node_list();
    }

    public void set_CtorTest(){

        RouteNode node1 = new RouteNode("1", 121.25f, 32.12f);
        RouteNode node2 = new RouteNode("4", 115.2f, 48.52f);
        RouteNode node3 = new RouteNode("13", 131.105f, 110.002f);
        nodeList.add(node1);
        nodeList.add(node2);
        nodeList.add(node3);
        node1.add_to_node_list();
        node2.add_to_node_list();
        node3.add_to_node_list();
        //expectation
        nodeListExpected.addAll(nodeList);
//        nodeListExpected.add(new RouteNode(3, 112.5465f, 131.4465f));
//        nodeListExpected.add(new RouteNode(1, 121.25f, 32.12f));
//        nodeListExpected.add(new RouteNode(4, 115.2f, 48.52f));
//        nodeListExpected.add(new RouteNode(13, 131.105f, 110.002f));
    }
}