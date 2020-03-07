package test;
import main.java.bus.JSON_nodeReader;
import org.json.simple.JSONArray;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JSON_nodeReaderTest {

    @Before
    public void setUp() throws Exception {

    }


    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void test_parseFile() {

        JSON_nodeReader reader = new JSON_nodeReader();
        reader.parseFile();
        System.out.println("JSON object contents: ");
        System.out.println(reader.getObjectSize());
        System.out.println("Node array size: " + reader.getNodeArraySize());
        assertEquals(8, reader.getNodeArraySize());

        JSONArray arr = reader.getNodeArray();

        for (int i = 0 ; i < arr.size(); i++) {
            System.out.println("Node: " + arr.get(i));
        }
        assertEquals(arr.get(2).toString()
                , "{\"ID: \":3,\"lon: \":-111.92929077148438,\"lat: \":33.427330017089844}");
    }
}