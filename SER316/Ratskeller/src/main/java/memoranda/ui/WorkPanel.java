package main.java.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import main.java.memoranda.util.Context;
import main.java.memoranda.util.Local;
import java.awt.event.ActionListener;
import java.awt.Font;

/**
 * 
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */

/*$Id: WorkPanel.java,v 1.9 2004/04/05 10:05:44 alexeya Exp $*/
public class WorkPanel extends JPanel {
	BorderLayout borderLayout1 = new BorderLayout();
	JToolBar toolBar = new JToolBar();
	JPanel panel = new JPanel();
	CardLayout cardLayout1 = new CardLayout();

	//public JButton notesB = new JButton();
	public DailyItemsPanel dailyItemsPanel = new DailyItemsPanel(this);
	//public ResourcesPanel filesPanel = new ResourcesPanel();
	public JButton agendaB = new JButton();
	//public JButton tasksB = new JButton();
	public JButton eventsB = new JButton();
	//public JButton filesB = new JButton();
	JButton currentB = null;
	Border border1;
	private final JButton BusSchedulerB = new JButton("Bus Scheduler");
	private final JButton BusSchedule = new JButton("Schedule");
	private final JButton BusRoute = new JButton("Routes");
	private final JButton Search = new JButton("Search");

	public WorkPanel() {
		try {
			jbInit();
		} catch (Exception ex) {
			new ExceptionDialog(ex);
		}
	}

	void jbInit() throws Exception {
		border1 =
			BorderFactory.createCompoundBorder(
				BorderFactory.createBevelBorder(
					BevelBorder.LOWERED,
					Color.white,
					Color.white,
					new Color(124, 124, 124),
					new Color(178, 178, 178)),
				BorderFactory.createEmptyBorder(0, 2, 0, 0));

		this.setLayout(borderLayout1);
		toolBar.setOrientation(JToolBar.VERTICAL);
		toolBar.setBackground(Color.white);

		toolBar.setBorderPainted(false);
		toolBar.setFloatable(false);
		panel.setLayout(cardLayout1);

		agendaB.setBackground(Color.white);
		agendaB.setMaximumSize(new Dimension(60, 80));
		agendaB.setMinimumSize(new Dimension(30, 30));

		agendaB.setFont(new java.awt.Font("Dialog", 1, 10));
		agendaB.setPreferredSize(new Dimension(50, 50));
		agendaB.setBorderPainted(false);
		agendaB.setContentAreaFilled(false);
		agendaB.setFocusPainted(false);
		agendaB.setHorizontalTextPosition(SwingConstants.CENTER);
		agendaB.setText(Local.getString("Agenda"));
		agendaB.setVerticalAlignment(SwingConstants.TOP);
		agendaB.setVerticalTextPosition(SwingConstants.BOTTOM);
		agendaB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agendaB_actionPerformed(e);
			}
		});
		agendaB.setIcon(
			new ImageIcon(
				main.java.memoranda.ui.AppFrame.class.getResource(
					"/ui/icons/agenda.png")));
		agendaB.setOpaque(false);
		agendaB.setMargin(new Insets(0, 0, 0, 0));
		agendaB.setSelected(true);
		//eventsB.setSelected(true);

		/**
		 * 
		tasksB.setSelected(true);
		tasksB.setFont(new java.awt.Font("Dialog", 1, 10));
		tasksB.setMargin(new Insets(0, 0, 0, 0));
		tasksB.setIcon(
			new ImageIcon(
				main.java.memoranda.ui.AppFrame.class.getResource(
					"/ui/icons/tasks.png")));
		tasksB.setVerticalTextPosition(SwingConstants.BOTTOM);
		tasksB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tasksB_actionPerformed(e);
			}
		});
		tasksB.setVerticalAlignment(SwingConstants.TOP);
		tasksB.setText(Local.getString("Tasks"));
		tasksB.setHorizontalTextPosition(SwingConstants.CENTER);
		tasksB.setFocusPainted(false);
		tasksB.setBorderPainted(false);
		tasksB.setContentAreaFilled(false);
		tasksB.setPreferredSize(new Dimension(50, 50));
		tasksB.setMinimumSize(new Dimension(30, 30));
		tasksB.setOpaque(false);
		tasksB.setMaximumSize(new Dimension(60, 80));
		tasksB.setBackground(Color.white);
		notesB.setFont(new java.awt.Font("Dialog", 1, 10));
		notesB.setBackground(Color.white);
		notesB.setBorder(null);
		notesB.setMaximumSize(new Dimension(60, 80));
		notesB.setMinimumSize(new Dimension(30, 30));
		notesB.setOpaque(false);
		notesB.setPreferredSize(new Dimension(60, 50));
		notesB.setBorderPainted(false);
		notesB.setContentAreaFilled(false);
		notesB.setFocusPainted(false);
		notesB.setHorizontalTextPosition(SwingConstants.CENTER);
		notesB.setText(Local.getString("Notes"));
		notesB.setVerticalAlignment(SwingConstants.TOP);
		notesB.setVerticalTextPosition(SwingConstants.BOTTOM);
		notesB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				notesB_actionPerformed(e);
			}
		});
		notesB.setIcon(
			new ImageIcon(
				main.java.memoranda.ui.AppFrame.class.getResource(
					"/ui/icons/notes.png")));
		notesB.setMargin(new Insets(0, 0, 0, 0));
		notesB.setSelected(true);
		this.setPreferredSize(new Dimension(1073, 300));
		filesB.setSelected(true);
		filesB.setMargin(new Insets(0, 0, 0, 0));
		filesB.setIcon(
			new ImageIcon(
				main.java.memoranda.ui.AppFrame.class.getResource(
					"/ui/icons/files.png")));
		filesB.setVerticalTextPosition(SwingConstants.BOTTOM);
		filesB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filesB_actionPerformed(e);
			}
		});
		filesB.setFont(new java.awt.Font("Dialog", 1, 10));
		filesB.setVerticalAlignment(SwingConstants.TOP);
		filesB.setText(Local.getString("Resources"));
		filesB.setHorizontalTextPosition(SwingConstants.CENTER);
		filesB.setFocusPainted(false);
		filesB.setBorderPainted(false);
		filesB.setContentAreaFilled(false);
		filesB.setPreferredSize(new Dimension(50, 50));
		filesB.setMinimumSize(new Dimension(30, 30));
		filesB.setOpaque(false);
		filesB.setMaximumSize(new Dimension(60, 80));
		filesB.setBackground(Color.white);
		*/
		this.add(toolBar, BorderLayout.WEST);
		this.add(panel, BorderLayout.CENTER);
		panel.add(dailyItemsPanel, "DAILYITEMS");
		//panel.add(filesPanel, "FILES");
		toolBar.add(agendaB, null);
		
				eventsB.setBackground(Color.white);
				eventsB.setMaximumSize(new Dimension(60, 80));
				eventsB.setMinimumSize(new Dimension(30, 30));
				
						eventsB.setFont(new java.awt.Font("Dialog", 1, 10));
						eventsB.setPreferredSize(new Dimension(50, 50));
						eventsB.setBorderPainted(false);
						eventsB.setContentAreaFilled(false);
						eventsB.setFocusPainted(false);
						eventsB.setHorizontalTextPosition(SwingConstants.CENTER);
						eventsB.setText(Local.getString("Events"));
						eventsB.setVerticalAlignment(SwingConstants.TOP);
						eventsB.setVerticalTextPosition(SwingConstants.BOTTOM);
						eventsB.addActionListener(new java.awt.event.ActionListener() {
							public void actionPerformed(ActionEvent e) {
								eventsB_actionPerformed(e);
							}
						});
						eventsB.setIcon(
							new ImageIcon(
								main.java.memoranda.ui.AppFrame.class.getResource(
									"/ui/icons/events.png")));
						eventsB.setOpaque(false);
						eventsB.setMargin(new Insets(0, 0, 0, 0));
						toolBar.add(eventsB, null);
		BusSchedulerB.setToolTipText("Bus Scheduler");
		BusSchedulerB.setHorizontalTextPosition(SwingConstants.CENTER);
		BusSchedulerB.setFont(new Font("Dialog", Font.BOLD, 10));
		BusSchedulerB.setMargin(new Insets(0, 0, 0, 0));
		BusSchedulerB.setPreferredSize(new Dimension(50, 50));
		BusSchedulerB.setVerticalTextPosition(SwingConstants.BOTTOM);
		BusSchedulerB.setVerticalAlignment(SwingConstants.TOP);
		BusSchedulerB.setMinimumSize(new Dimension(30, 30));
		BusSchedulerB.setMaximumSize(new Dimension(60, 80));
		BusSchedulerB.setIcon(new ImageIcon(WorkPanel.class.getResource("/ui/icons/icons8-bus-48.png")));

		BusSchedule.setToolTipText("Bus Schedule");
		BusSchedule.setHorizontalTextPosition(SwingConstants.CENTER);
		BusSchedule.setFont(new Font("Dialog", Font.BOLD, 10));
		BusSchedule.setMargin(new Insets(0, 0, 0, 0));
		BusSchedule.setPreferredSize(new Dimension(50, 50));
		BusSchedule.setVerticalTextPosition(SwingConstants.BOTTOM);
		BusSchedule.setVerticalAlignment(SwingConstants.TOP);
		BusSchedule.setMinimumSize(new Dimension(30, 30));
		BusSchedule.setMaximumSize(new Dimension(60, 80));
		BusSchedule.setIcon(new ImageIcon(WorkPanel.class.getResource("/ui/icons/icons8-bus-48.png")));

		BusSchedulerB.setIcon(new ImageIcon(WorkPanel.class.getResource("/ui/icons/icons8-bus-48.png")));

		BusRoute.setToolTipText("Bus Route");
		BusRoute.setHorizontalTextPosition(SwingConstants.CENTER);
		BusRoute.setFont(new Font("Dialog", Font.BOLD, 10));
		BusRoute.setMargin(new Insets(0, 0, 0, 0));
		BusRoute.setPreferredSize(new Dimension(50, 50));
		BusRoute.setVerticalTextPosition(SwingConstants.BOTTOM);
		BusRoute.setVerticalAlignment(SwingConstants.TOP);
		BusRoute.setMinimumSize(new Dimension(30, 30));
		BusRoute.setMaximumSize(new Dimension(60, 80));
		BusRoute.setIcon(new ImageIcon(WorkPanel.class.getResource("/ui/icons/icons8-bus-48.png")));
		
		BusSchedulerB.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        BusFrame busFrame = new BusFrame();
		        busFrame.setVisible(true);
		    }
		});

		BusSchedule.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        BusScheduleFrame busRouteFrame = new BusScheduleFrame();
		        busRouteFrame.setVisible(true);
		    }
		});

		BusRoute.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        BusRouteFrame busRouteFrame = new BusRouteFrame();
		        busRouteFrame.setVisible(true);
		    }
		});
		
		Search.setToolTipText("Bus Route");
		Search.setHorizontalTextPosition(SwingConstants.CENTER);
		Search.setFont(new Font("Dialog", Font.BOLD, 10));
		Search.setMargin(new Insets(0, 0, 0, 0));
		Search.setPreferredSize(new Dimension(50, 50));
		Search.setVerticalTextPosition(SwingConstants.BOTTOM);
		Search.setVerticalAlignment(SwingConstants.TOP);
		Search.setMinimumSize(new Dimension(30, 30));
		Search.setMaximumSize(new Dimension(60, 80));
		Search.setIcon(new ImageIcon(WorkPanel.class.getResource("/ui/icons/search.png")));

		Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchFrame searchFrame = new SearchFrame();
				searchFrame.setVisible(true);
			}
		});

		BusRoute.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        BusRouteFrame busRouteFrame = new BusRouteFrame();
		        busRouteFrame.setVisible(true);
			}
		});

		toolBar.add(BusSchedulerB);
		toolBar.add(BusSchedule);
		toolBar.add(BusRoute);
		toolBar.add(Search);
		toolBar.add(BusRoute);

		//toolBar.add(tasksB, null);
		//toolBar.add(notesB, null);
		//toolBar.add(filesB, null);
		currentB = agendaB;
		// Default blue color
		currentB.setBackground(new Color(215, 225, 250));
		currentB.setOpaque(true);

		toolBar.setBorder(null);
		panel.setBorder(null);
		dailyItemsPanel.setBorder(null);
		//filesPanel.setBorder(null);

		     
		}
	public void selectPanel(String pan) {
		if (pan != null) {
			//if (pan.equals("NOTES"))
				//notesB_actionPerformed(null);
			//else if (pan.equals("TASKS"))
				//tasksB_actionPerformed(null);
			if (pan.equals("EVENTS"))
				eventsB_actionPerformed(null);
			//else if (pan.equals("FILES"))
				//filesB_actionPerformed(null);
		}
	}

	public void agendaB_actionPerformed(ActionEvent e) {
		cardLayout1.show(panel, "DAILYITEMS");
		dailyItemsPanel.selectPanel("AGENDA");
		setCurrentButton(agendaB);
		Context.put("CURRENT_PANEL", "AGENDA");
	}

	/**
	public void notesB_actionPerformed(ActionEvent e) {
		cardLayout1.show(panel, "DAILYITEMS");
		dailyItemsPanel.selectPanel("NOTES");
		setCurrentButton(notesB);
		Context.put("CURRENT_PANEL", "NOTES");
	}
	*/
	
	/**
	public void tasksB_actionPerformed(ActionEvent e) {
		cardLayout1.show(panel, "DAILYITEMS");
		dailyItemsPanel.selectPanel("TASKS");
		setCurrentButton(tasksB);
		Context.put("CURRENT_PANEL", "TASKS");
	}
	*/

	
	public void eventsB_actionPerformed(ActionEvent e) {
		cardLayout1.show(panel, "DAILYITEMS");
		dailyItemsPanel.selectPanel("EVENTS");
		setCurrentButton(eventsB);
		Context.put("CURRENT_PANEL", "EVENTS");
	}

	/**
	public void filesB_actionPerformed(ActionEvent e) {
		cardLayout1.show(panel, "FILES");
		setCurrentButton(filesB);
		Context.put("CURRENT_PANEL", "FILES");
	}
	*/

	void setCurrentButton(JButton cb) {
		currentB.setBackground(Color.white);
		currentB.setOpaque(false);
		currentB = cb;
		// Default color blue
		currentB.setBackground(new Color(215, 225, 250));
		currentB.setOpaque(true);
	}
}
