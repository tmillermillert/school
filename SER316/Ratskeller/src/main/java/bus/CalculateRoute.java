package main.java.bus;

import java.text.DecimalFormat;

/**
 * File:	  main.CalculateRoute.java
 * Author:    Adam Clifton (akclifto@asu.edu)
 * Date:      2019.11.25
 *3
 * <p>Description:  This file will calculate the length and duration of given routes.
 * It will find the distance between two nodes, then approximate duration
 * assuming the bus is travelling at 30mph average speed.
 */
public class CalculateRoute {

    private double distance;
    private DecimalFormat df = new DecimalFormat("#.##");

    public CalculateRoute() {
        //ctor
    }


    /**Method: Method used to calculate distance between two given nodes
     * containing latitude and longitude.
     * Inputs: node1_x : float, node1_y : float, node2_x : float, node2_y : float
     * Returns: double
     * Description: Method will calculate distance between two nodes as follows:
     *
     *     <p>Distance between two nodes: (delta(x), delta(y))
     *        -> D^2 = X^2 + Y^2, where D = distance, X and Y are latitude and longitude
     */
    @SuppressWarnings("checkstyle:ParameterName")
    public double calculateDistance(float node1_x, float node1_y, float node2_x, float node2_y) {

        double delta_x = node2_x - node1_x;
        double delta_y = node2_y - node1_y;
        double distSquared = (Math.pow(delta_x, 2)) + (Math.pow(delta_y, 2));

        distSquared = Math.sqrt(distSquared);
        distance = mileConversion(distSquared);
        return Double.parseDouble(df.format(distance));

    }


    /**Method: Method used to calculate duration of a route then convert to a String
     * to approximate time.
     * containing latitude and longitude.
     * Inputs: distance : double, speed : double
     * Returns: String
     * Description: Method will calculate duration of a route as follows:
     *
     *     <p>Duration: Time = Distance/Speed, where Speed = 30mph
     */
    public double calculateDuration(double distance, double speed) {

        return distance / speed;


    }

    /**Method: Method used to calculate duration of a route then convert to a String
     * to approximate time.
     * containing latitude and longitude.
     * Inputs: distance : double, speed : double
     * Returns: String
     * Description: Method will convert the calculated route duration to hours and minutes.
     */
    public String ConvertTimeValue(double duration) {

        int hours = (int) (duration / 1);
        double mins = (duration % 1) * (60);

        return hours + "H " + Math.round(mins) + "M";
    }


    /**Method: Convert coordinate units to miles.
     * Inputs: conversion : double
     * Returns: double
     * Description: Method will calculate mile conversion as follows:
     *
     *     <p>Conversion: 1 coordinate point = 69.09 miles
     */
    private double mileConversion(double conversion) {

        try {
            double miles = 69.09;
            return conversion * miles;
        } catch (NumberFormatException e) {
            System.out.println("Number could not be converted.");
            e.printStackTrace();
        }
        return -1;
    }

}
