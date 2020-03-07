package main.java.bus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Route {

	private int tour_id;
	private char route_direction; 
	private int route_length; 
	private int route_start_loc; 
    private int route_id;
    private String route_name;
    private LocalDateTime route_date;
    private DateTimeFormatter format_route_date = DateTimeFormatter.ofPattern("MM/dd/yy hh:mm");
    private float segment_start_loc; //this might be changed to a routeNode
    private float segment_end_loc;  //this might be changes to a routeNode
    private double segment_length;
    private String duration;
    private HashMap<Integer, Route> routeList = new HashMap<>();

	public Route(int tour_id, char route_direction, int route_length, int route_start_loc) {
		this.tour_id = tour_id;
		this.route_direction = route_direction;
		this.route_length = route_length;
		this.route_start_loc = route_start_loc;
	}
	
	public int getTourID() {
		return tour_id;
	}
	
	public char getRouteDir() {
		return route_direction;
	}
	
	public int getRouteLen() {
		return route_length;
	}
	
	public int getRouteStartLoc() {
		return route_start_loc;
	}
	
	public void setTourID(int tour_id)
	{
		this.tour_id=tour_id;
	}
	
	public void setRouteDir(int route_direction) {
		this.route_direction= (char) route_direction;
	}
	
	public void setRouteLen(int route_length) {
		this.route_length=route_length;
	}
	
	public void setRouteStartLoc(int route_start_loc) {
		this.route_start_loc = route_start_loc;
	}

    public Route() {
        //ctor
    }

    public Route(int route_id, String route_name, float seg_start, float seg_end) {

        this.route_id = route_id;
        this.route_name = route_name;
        this.segment_start_loc = seg_start;
        this.segment_end_loc = seg_end;
        routeList.put(route_id, this);

    }

    public void setRoute_id(int route_id) {

        if (route_id == 0) {
            this.route_id = 1;
        } else {
            this.route_id = route_id;
        }
    }

    public int getRoute_id() {
        return route_id;
    }

    public void setRoute_name(String name) {
        this.route_name = name;
    }

    public String getRoute_name() {
        return route_name;
    }

    public void setRoute_date(int year, int month, int day, int hour, int min) {
        this.route_date = LocalDateTime.of(year, month, day, hour, min);

    }

    public String getRoute_date() throws Exception {

        if (route_date == null) {
            throw new Exception("The date of the route was not set.");

        }
        return route_date.format(format_route_date);
    }

    public void setSegment_start_loc(float segment_start_loc) {
        this.segment_start_loc = segment_start_loc;
    }

    public float getSegment_start_loc() {
        return segment_start_loc;
    }

    public void setSegment_end_loc(float segment_end_loc) {
        this.segment_end_loc = segment_end_loc;
    }

    public float getSegment_end_loc() {
        return segment_end_loc;
    }

    public void setSegment_length(double segment_length) {
        this.segment_length = segment_length;
    }

    public double getSegment_length() {
        return segment_length;
    }


    public void setDirection(String direction) {
        this.duration = direction;
    }

    public String getDirection() {
        return duration;
    }

    public void setRouteList(Route route) {
        this.routeList.put(route_id, route);
    }

    public HashMap<Integer, Route> getRouteList() {
        return routeList;
    }
    
    public boolean booked(int route_id)
    {
    	if(this.route_id==route_id)
    	{
    		return true;
    	}
    	return false;
    }

}
