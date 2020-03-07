package test;
import main.java.bus.JSON_nodeCreator;
import main.java.bus.RouteNode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class JSON_nodeCreatorTest {

    private RouteNode node;
    private List<RouteNode> list = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test_set_routeNodes() {

        JSON_nodeCreator obj = new JSON_nodeCreator(1);
        obj.set_routeNodes_auto();
        System.out.println(obj.toString());

        JSON_nodeCreator exp = new JSON_nodeCreator(2);
        exp.create_routeNode("1", 33.423251f, -111.930402f);
        exp.create_routeNode("2", 33.424331f, -111.929120f);
        exp.create_routeNode("3", 33.427330f, -111.929291f);
        exp.create_routeNode("4", 33.429479f, -111.931931f);
        exp.create_routeNode("5", 33.430733f, -111.936426f);
        exp.create_routeNode("6", 33.429435f, -111.940101f);
        exp.create_routeNode("7", 33.425621f, -111.940112f);
        exp.create_routeNode("8", 33.425549f, -111.936088f);
        obj.getJSON_nodeList().trimToSize();
        exp.getJSON_nodeList().trimToSize();

        for (int i = 0 ; i < obj.getJSON_nodeList().size(); i++){
            assertEquals(obj.getJSON_nodeList().get(i).toString(), exp.getJSON_nodeList().get(i).toString());
        }
        obj.writeJSONFile();
    }

}
