package main.java.memoranda.ui;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.Vector;
import java.util.concurrent.ConcurrentSkipListMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.TableView.TableRow;

import main.java.bus.BusSchedulerNeo4j;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ButtonGroup;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import org.neo4j.graphdb.Result;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class BusFrame extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private final ButtonGroup buttonGroup_1 = new ButtonGroup();
    private DefaultTableModel tm = new DefaultTableModel(10,10);
    private JButton btnExecute;
    private JRadioButton rdbtnBus;
    private JRadioButton rdbtnSegment;
    private JRadioButton rdbtnDrives;
    private JRadioButton rdbtnTakes;
    private JRadioButton rdbtnPath;
    private JRadioButton rdbtnBusStop;
    private JRadioButton rdbtnDriver;
    private JRadioButton rdbtnMadeOf;
    private JRadioButton rdbtnBetween;
    private JRadioButton rdbtnOn;
    private JRadioButton rdbtnAdd;
    private JRadioButton rdbtnUpdate;
    private JRadioButton rdbtnLoad;
    private JRadioButton rdbtnDelete;


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BusFrame frame = new BusFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public BusFrame() {
        setTitle("Bus Scheduler - CRUD Operations");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 556, 615);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        table = new JTable();

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);


        table.setModel(tm);
        table.setBounds(30, 40, 200, 300);
        table.repaint();


        /////////////////////////////////////////////////////

        rdbtnAdd = new JRadioButton("Add");
        buttonGroup_1.add(rdbtnAdd);
        rdbtnBus = new JRadioButton("Bus");
        buttonGroup.add(rdbtnBus);
        rdbtnDelete = new JRadioButton("Delete");
        buttonGroup_1.add(rdbtnDelete);
        rdbtnSegment = new JRadioButton("Segment");

        buttonGroup.add(rdbtnSegment);
        rdbtnBusStop = new JRadioButton("Bus Stop");
        buttonGroup.add(rdbtnBusStop);
        rdbtnDrives = new JRadioButton("Drives");
        buttonGroup.add(rdbtnDrives);
        rdbtnDriver = new JRadioButton("Driver");
        buttonGroup.add(rdbtnDriver);
        rdbtnTakes = new JRadioButton("Takes");
        buttonGroup.add(rdbtnTakes);
        rdbtnMadeOf = new JRadioButton("Made Of");
        buttonGroup.add(rdbtnMadeOf);
        rdbtnBetween = new JRadioButton("Between");
        buttonGroup.add(rdbtnBetween);
        rdbtnOn = new JRadioButton("On");
        buttonGroup.add(rdbtnOn);
        rdbtnPath = new JRadioButton("Path");
        buttonGroup.add(rdbtnPath);
        rdbtnUpdate = new JRadioButton("Update");

        buttonGroup_1.add(rdbtnUpdate);
        rdbtnLoad = new JRadioButton("Load");
        buttonGroup_1.add(rdbtnLoad);


        btnExecute = new JButton("Execute");


        JLabel lblTable = new JLabel("Table:");

        JLabel lblOperation = new JLabel("Operation:");


        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                .addGroup(gl_contentPane.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                .addComponent(lblTable)
                                                .addComponent(lblOperation)
                                                .addGroup(gl_contentPane.createSequentialGroup()
                                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                                .addComponent(rdbtnBus)
                                                                .addComponent(rdbtnBetween)
                                                                .addComponent(rdbtnPath)
                                                                .addComponent(rdbtnAdd))
                                                        .addGap(62)
                                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                                .addComponent(rdbtnOn)
                                                                .addGroup(gl_contentPane.createSequentialGroup()
                                                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                                                .addGroup(gl_contentPane.createSequentialGroup()
                                                                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                                                                .addComponent(rdbtnBusStop)
                                                                                                .addComponent(rdbtnSegment))
                                                                                        .addGap(88)
                                                                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                                                                                .addComponent(rdbtnDrives)
                                                                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                                                                        .addComponent(rdbtnLoad)
                                                                                                        .addComponent(rdbtnDriver))))
                                                                                .addComponent(rdbtnUpdate))
                                                                        .addGap(59)
                                                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                                                .addComponent(rdbtnTakes)
                                                                                .addComponent(rdbtnDelete)
                                                                                .addComponent(rdbtnMadeOf)))))
                                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)))
                                .addComponent(btnExecute))
                        .addContainerGap())
                );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                        .addGap(15)
                        .addComponent(lblTable)
                        .addGap(18)
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                .addComponent(rdbtnBus)
                                .addComponent(rdbtnDrives)
                                .addComponent(rdbtnSegment)
                                .addComponent(rdbtnTakes))
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                .addComponent(rdbtnPath)
                                .addComponent(rdbtnBusStop)
                                .addComponent(rdbtnMadeOf)
                                .addComponent(rdbtnDriver))
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                .addComponent(rdbtnBetween)
                                .addComponent(rdbtnOn))
                        .addGap(18)
                        .addComponent(lblOperation)
                        .addGap(18)
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                .addComponent(rdbtnAdd)
                                .addComponent(rdbtnUpdate)
                                .addComponent(rdbtnLoad)
                                .addComponent(rdbtnDelete))
                        .addGap(18)
                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(btnExecute)
                        .addGap(15))
                );


        contentPane.setLayout(gl_contentPane);


        //////////////////////////////////////////////////
        ////////////Events
        /////////////////////
        
        //show segment if segment and update or delete or load selected
        rdbtnSegment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (rdbtnLoad.isSelected() == true && rdbtnSegment.isSelected() == true) {
                    displaySegmentInTable();
                }
                if (rdbtnUpdate.isSelected() == true && rdbtnSegment.isSelected() == true) {
                    displaySegmentInTable();
                }
            }
        });

        //show bus in table if load and bus are selected
        rdbtnLoad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (rdbtnLoad.isSelected() == true && rdbtnBus.isSelected() == true) {
                    displayBusInTable();
                }
                else if (rdbtnLoad.isSelected() == true && rdbtnSegment.isSelected() == true) {
                    displaySegmentInTable();
                }
            }
        });

        //show bus in table if update and bus are selected
        rdbtnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (rdbtnUpdate.isSelected() == true && rdbtnBus.isSelected() == true) {
                    displayBusInTable();
                }
                else if (rdbtnUpdate.isSelected() == true && rdbtnSegment.isSelected() == true) {
                    displaySegmentInTable();
                }
            }
        });



        //show bus in table if update/load and bus are selected
        rdbtnBus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (rdbtnUpdate.isSelected() == true && rdbtnBus.isSelected() == true) {
                    displayBusInTable();
                }
                if (rdbtnLoad.isSelected() == true && rdbtnBus.isSelected() == true) {
                    displayBusInTable();
                }
            }

        });

        // execute
        btnExecute.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (rdbtnUpdate.isSelected() == true && rdbtnBus.isSelected() == true) {
                    int bus_id = 0;
                    int capacity = -1;
                    boolean is_operational = false;
                    boolean passed;
                    for(int row = 0; row < tm.getRowCount(); row++) {
                        passed = true;
                        try {
                            bus_id = Integer.parseInt(table.getValueAt(row, 0).toString());//((Long) table.getValueAt(row, 0)).intValue();
                            capacity = Integer.parseInt(table.getValueAt(row, 1).toString());
                            is_operational =  Boolean.valueOf(table.getValueAt(row, 2).toString());
                        }
                        catch(Exception e1) {
                            e1.printStackTrace();
                            passed = false;

                        }
                        if (passed == true) {
                            BusSchedulerNeo4j.update_bus((int) bus_id, (int) capacity, is_operational);
                        }
  

                    }
                }
                //US46 - Task 66 - Implement methods to pull data on individual buses
                else if (rdbtnLoad.isSelected() == true && rdbtnBus.isSelected() == true) {
                    BusSchedulerNeo4j.load_bus_cvs();
                    displayBusInTable();
                }
                else if (rdbtnLoad.isSelected() == true && rdbtnSegment.isSelected() == true) {
                    BusSchedulerNeo4j.load_segment_cvs();
                    displaySegmentInTable();
                }
            }
        });




    }
    void displayBusInTable() {
        int count =0;
        Map<Integer, Map<String, Object>> result = BusSchedulerNeo4j.match_buses();
        //System.out.printf("Enter\n");
        /*
            if (result == null) {
                System.out.printf("Failed\n");
            }
         */
        tm = new DefaultTableModel(); 
        table.setModel(tm);


        for( Map<String, Object> row : result.values())
        {
            count++;
            Vector<Object> data =new Vector<Object>();
            SortedMap<String,Object> sortedMap = new ConcurrentSkipListMap<String, Object>(row);
            for(Entry<String,Object> column : sortedMap.entrySet()) {
                if (count == 1) {
                    tm.addColumn(column.getKey());
                }
                data.add(column.getValue());
            }
            tm.addRow(data);
        }
        //System.out.printf("%d%n", count);
        table.repaint();

    }
    void displaySegmentInTable() {
        int count =0;
        Map<Integer, Map<String, Object>> result = BusSchedulerNeo4j.match_segments();
        //System.out.printf("Enter\n");
        /*
            if (result == null) {
                System.out.printf("Failed\n");
            }
         */
        tm = new DefaultTableModel(); 
        table.setModel(tm);


        for( Map<String, Object> row : result.values())
        {
            count++;
            Vector<Object> data =new Vector<Object>();
            SortedMap<String,Object> sortedMap = new ConcurrentSkipListMap<String, Object>(row);
            for(Entry<String,Object> column : sortedMap.entrySet()) {
                if (count == 1) {
                    tm.addColumn(column.getKey());
                }
                data.add(column.getValue());
            }
            tm.addRow(data);
        }
        //System.out.printf("%d%n", count);
        table.repaint();

    }
}
