package main.java.memoranda.ui;


import main.java.bus.CalculateRoute;
import main.java.bus.RouteNode;

import javax.swing.*;
import java.awt.*;

public class ComboBoxHandler {

    JComboBox<String> comboBox;


    public ComboBoxHandler(JComboBox<String> comboBox) {

        this.comboBox = comboBox;
    }


    /** Initialize ComboBox for Events UI */
    public void init(JComboBox<String> comboBox) {

        comboBox = new JComboBox<>();
        comboBox.setMinimumSize(new Dimension(375, 24));
        comboBox.setPreferredSize(new Dimension(375, 24));
        comboBox.getEditor().getEditorComponent().setBackground(Color.white);

    }

    /** Add predetermined destinations to ComboBox. */
    public void addDestinations(JComboBox<String> comboBox) {

        comboBox.addItem(" ");
        comboBox.addItem(" Destination 001: University/Veteran's Way");  // 33.422019, -111.929083
        comboBox.addItem(" Destination 002: San Pablo Residence Hall");  // 33.423428, -111.931051
        comboBox.addItem(" Destination 003: Veteran's Way/College");     // 33.425483, -111.934808
        comboBox.addItem(" Destination 004: Mill/Harkin's Theatre");     // 33.425347, -111.940090
        comboBox.addItem(" Destination 005: University/Mill Ave.");      // 33.422169, -111.940120
        comboBox.addItem(" Destination 006: University/Old Main");       // 33.421931, -111.934115
    }

    /**Sets route length based on selected item in combobox. */
    public double setRouteLengths(JComboBox<String> comboBox) {

        if (comboBox.getSelectedItem().toString().equals(" Destination 001: University/Veteran's Way")) {
            return getLength(33.422019f, -111.929083f);
        } else if (comboBox.getSelectedItem().toString().equals(" Destination 002: San Pablo Residence Hall")) {

            return getLength(33.423428f, -111.931051f);
        } else if (comboBox.getSelectedItem().toString().equals(" Destination 003: Veteran's Way/College")) {

            return getLength(33.425483f, -111.934808f);
        } else if (comboBox.getSelectedItem().toString().equals(" Destination 004: Mill/Harkin's Theatre")) {

            return getLength(33.425347f, -111.940090f);
        } else if (comboBox.getSelectedItem().toString().equals(" Destination 005: University/Mill Ave.")) {

            return getLength(33.422169f, -111.940120f);
        } else if (comboBox.getSelectedItem().toString().equals(" Destination 006: University/Old Main")) {

            return getLength(33.421931f, -111.934115f);
        } else {
            return 0;
        }

    }

    /**Get length of route from preset starting point */
    public double getLength(float lat, float lon) {

        float presetLat = 33.416839f;
        float presetLon = -111.926156f;
        RouteNode start = new RouteNode("Start", presetLat, presetLon);
        RouteNode end = new RouteNode("End", lat, lon);
        CalculateRoute calc = new CalculateRoute();

        return calc.calculateDistance(start.getLat(), start.getLon(), end.getLat(), end.getLon());
    }

    /**Get the estimated duration of a bus trip using predetermined average speed.  */
    public String duration(double dist) {

        CalculateRoute calc = new CalculateRoute();
        double dur = calc.calculateDuration(dist, 30.0);
        return calc.ConvertTimeValue(dur);
    }
}
