package main.java.bus;

import org.json.JSONObject;
import org.json.simple.JSONArray;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSON_nodeCreator {

    /**
     * File:   JSONBusCreator.java
     * Author: Adam Clifton (akclifto@asu.edu)
     * Date:   2019.11.10
     *
     * <p>Description:  This file will take data inputs to create JSON files.
     * This will be used in conjunction with Bus data storage.
     */
    private int id;
    private RouteNode nodeInfo;
    private List<RouteNode> nodeInfoList = new ArrayList<>();
    private JSONArray JSON_nodeList = new JSONArray();


    public JSON_nodeCreator(int id) {
        this.id = id;
    }

    public void set_id(int id) {
        this.id = id;
    }

    public int get_id() {
        return id;
    }

    public JSONArray getJSON_nodeList() {
        return JSON_nodeList;
    }

    public void create_routeNode(String id, float lat, float lon) {
        nodeInfo = new RouteNode(id, lat, lon);
        nodeInfoList.add(nodeInfo);
        addNode();
    }

    /**
     * Method: Method to automatically set predefined nodes for routes and add to JSON list before writing.
     * Inputs: NA
     * Returns: void
     * Description: This method will auto-build route nodes and add to list using predefined values.
     */
    public void set_routeNodes_auto() {

        nodeInfo = new RouteNode("1", 33.423251f, -111.930402f);
        nodeInfoList.add(nodeInfo);
        addNode();
        nodeInfo = new RouteNode("2", 33.424331f, -111.929120f);
        nodeInfoList.add(nodeInfo);
        addNode();
        nodeInfo = new RouteNode("3", 33.427330f, -111.929291f);
        nodeInfoList.add(nodeInfo);
        addNode();
        nodeInfo = new RouteNode("4", 33.429479f, -111.931931f);
        nodeInfoList.add(nodeInfo);
        addNode();
        nodeInfo = new RouteNode("5", 33.430733f, -111.936426f);
        nodeInfoList.add(nodeInfo);
        addNode();
        nodeInfo = new RouteNode("6", 33.429435f, -111.940101f);
        nodeInfoList.add(nodeInfo);
        addNode();
        nodeInfo = new RouteNode("7", 33.425621f, -111.940112f);
        nodeInfoList.add(nodeInfo);
        addNode();
        nodeInfo = new RouteNode("8", 33.425549f, -111.936088f);
        nodeInfoList.add(nodeInfo);
        addNode();
    }

    /**
     * Method:addBus()
     * Inputs: NA
     * Returns: NA
     * Description: Add a new node as a JSON object and store its attributes, adds to JSON node list.
     */
    public void addNode() {

        JSONObject nodes = new JSONObject();
        nodes.put("ID: ", nodeInfo.getNode_id());
        nodes.put("lat: ", nodeInfo.getLat());
        nodes.put("lon: ", nodeInfo.getLon());
        JSON_nodeList.add(nodes);

    }

    /**
     * Method:writeJSONFile()
     * Inputs: NA
     * Returns: NA
     * Description: Writes JSON file to directory
     *
     * @throws IOException if error occurs.
     */
    public void writeJSONFile() {
        try (FileWriter file = new FileWriter("RouteNodes.json")) {
            file.write(JSON_nodeList.toJSONString());
            file.flush();
        } catch (IOException e) {
            System.out.println("There was a problem writing the JSON file to path.");
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < nodeInfoList.size(); i++) {
            res.append("Node ID: " + nodeInfoList.get(i).getNode_id() +
                " lat: " + nodeInfoList.get(i).getLat() + " lon: " + nodeInfoList.get(i).getLon() + "\n");
        }
        return res.toString();
    }


}
