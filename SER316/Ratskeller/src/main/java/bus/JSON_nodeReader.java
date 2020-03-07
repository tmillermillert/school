package main.java.bus;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * File:	    JSONFileReader.java
 * Author :  Adam Clifton (akclifto@asu.edu)
 * Date:      2019.11.10
 * Description:  The class will read and parse JSON files to be compatible with Java.
 */
public class JSON_nodeReader {

    private Object obj;
    private JSONArray nodeArray;

    public JSON_nodeReader() {
        //ctor
    }

    /**
     * Method: parseFile()
     * Inputs: NA
     * Returns: NA
     * Description: Parses JSON file for compatibility.
     */
    public void parseFile() {

        try {

            JSONParser parser = new JSONParser();
            obj = parser.parse(new FileReader("RouteNodes.json"));
            nodeArray = new JSONArray();
            nodeArray = (JSONArray) obj;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JSONArray getNodeArray() {
        return nodeArray;
    }

    public int getNodeArraySize() {
        return nodeArray.size();
    }

    public String getObjectSize() {
        return obj.toString();
    }
}
