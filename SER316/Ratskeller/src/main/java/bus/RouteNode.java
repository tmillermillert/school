package main.java.bus;

import java.util.ArrayList;
import java.util.List;

public class RouteNode {
    /**
     * File:	    main.RouteNode.java
     * Author:    Adam Clifton (akclifto@asu.edu) and Brayden Giffen(BGiffen@asu.edu)
     * Date:      2019.11.14
     * <p>
     * Description:  This class file will store route node information.  It is comprised of longitude and
     * latitude coordinates, that will be mapped for each individual node.  The structure will be used to
     * populate a JSON file and provide node information for each bus stop and route.
     */
    private String name;
    private String node_id;
    private float lon;
    private float lat;
    private List<RouteNode> node_list = new ArrayList<>();

    public RouteNode(){
        //ctor
    }

    public RouteNode(String name, String node_id) {
        this.name = name;
        this.node_id = node_id;
    }

    public RouteNode(String node_id, float lat, float lon) {

        this.node_id = node_id;
        this.lat = lat;
        this.lon = lon;
        try {
            add_to_node_list();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RouteNode getNode() {
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

    public String getNode_id() {
        return this.node_id;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLat() {
        return this.lat;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public float getLon() {
        return this.lon;
    }

    public List<RouteNode> getNode_list() {
        return node_list;
    }

    /**
     * Method: add nodes to list
     * Inputs: NA
     * Returns: void
     * Description: Method to add node to the list of nodes if list size is zero or list does
     * not already contain node.  Else,Exception is thrown.
     */
    public void add_to_node_list() {

        try {
            if (node_list.size() == 0) {
                node_list.add(this);
            } else {
                if (!node_list.contains(this.node_id)) {
                    node_list.add(this);
                } else {
                    throw new Exception("List already contains node with ID: " + this.node_id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int hashCode() {
        final int prime = 17;
        int result = 1;
        result = prime * result + ((node_id == null) ? 0 : node_id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object object) {

        if (this == object) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if (getClass() != object.getClass()) {
            return false;
        }
        RouteNode other = (RouteNode) object;
        if (node_id == null) {
            return other.node_id == null;
        } else return node_id.equals(other.node_id);
    }

    @Override
    public String toString() {
        return name;
    }
}
