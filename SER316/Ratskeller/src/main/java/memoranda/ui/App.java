package main.java.memoranda.ui;
import main.java.bus.*;

import main.java.memoranda.EventsScheduler;
import main.java.memoranda.util.Configuration;
import main.java.memoranda.util.LoadableProperties;

//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.xpath.XPath;
//import javax.xml.xpath.XPathConstants;
//import javax.xml.xpath.XPathExpressionException;
//import javax.xml.xpath.XPathFactory;
import java.awt.*;
//import java.io.File;
//import java.io.IOException;
import java.util.Calendar;

/**
 * 
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */

/*$Id: App.java,v 1.28 2007/03/20 06:21:46 alexeya Exp $*/
public class App {
	// boolean packFrame = false;

	static main.java.memoranda.ui.AppFrame frame = null;
	//Help menu website functionality
	public static final String GUIDE_URL = "https://github.com/amehlhase316/Ratskeller/blob/master/Online_User's_Guide.md";
	public static final String BUGS_TRACKER_URL = "https://github.com/amehlhase316/Ratskeller/blob/master/Report_a_Bug.md";
	public static final String WEBSITE_URL = "https://github.com/amehlhase316/Ratskeller/blob/master/Bus_Scheduler_Website.md";

	private JFrame splash = null;

	/*========================================================================*/ 
	/* Note: Please DO NOT edit the version/build info manually!
       The actual values are substituted by the Ant build script using 
       'version' property and datestamp.*/

	private static final LoadableProperties properties = initProperties();
	public static final String VERSION_INFO = (String) properties.get("VERSION");
	public static final String BUILD_INFO = (String) properties.get("BUILD");

	
	/*========================================================================*/
	
	/**
	* Sets App.BUILD_INFO and App.VERSION_INFO to the values in 
	* build.xml 
	*/
	/*
	public static void set_version_and_build_info() {
		File inputFile = null;
		try{
		
			inputFile = new File("build.xml");

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;
		
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
		
			//System.out.printf("doc=%s\n",doc.getDocumentURI());
		
			doc.getDocumentElement().normalize();
			//System.out.printf("doc=%s\n",doc.getDocumentURI());
			XPath xPath = XPathFactory.newInstance().newXPath();
			//System.out.printf("xPath=%s\n",xPath);
			//System.out.println("\nCurrent Element :");
			String expression = "//PROJECT/PROPERTY[@name='build']";
			Element node = (Element) xPath.evaluate(expression, doc, XPathConstants.NODE);
			//App.BUILD_INFO = node.getAttribute("value");
			//System.out.printf("build=%s\n", App.BUILD_INFO);
			
		
			expression = "//PROJECT/PROPERTY[@name='version']";
			node = (Element) xPath.evaluate(expression, doc, XPathConstants.NODE);
			//App.VERSION_INFO = node.getAttribute("value");
			//System.out.printf("version=%s\n", App.VERSION_INFO);
			
		
		
		} 
		catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		catch (SAXException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		catch (XPathExpressionException e) {
			e.printStackTrace();
		}
	}
    */
	public static main.java.memoranda.ui.AppFrame getFrame() {
		return frame;
	}

	public void show() {
		if (frame.isVisible()) {
			frame.toFront();
			frame.requestFocus();
		} else
			init();
	}

	public App(boolean fullmode) {
		super();
		//start neo4j database
		BusSchedulerNeo4j.start_neo4jDB();

		//set color for Event bus scheduling dropdown highlight.
		UIManager.put("ComboBox.background", new ColorUIResource(Color.white));
		UIManager.put("ComboBox.selectionBackground", new ColorUIResource(156, 234, 240));

		//doesn't work in certain jdks
		//App.set_version_and_build_info();
		if (fullmode)
			fullmode = !Configuration.get("START_MINIMIZED").equals("yes");
		/* DEBUG */
		if (!fullmode)
			System.out.println("Now in Minimized mode");//second change
		if (!Configuration.get("SHOW_SPLASH").equals("no"))
			showSplash();

		System.out.println(VERSION_INFO);
		System.out.println(Configuration.get("LOOK_AND_FEEL"));
		try {
			if (Configuration.get("LOOK_AND_FEEL").equals("system"))
				UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
			else if (Configuration.get("LOOK_AND_FEEL").equals("default"))
				UIManager.setLookAndFeel(
					UIManager.getCrossPlatformLookAndFeelClassName());					
			else if (
				Configuration.get("LOOK_AND_FEEL").toString().length() > 0)
				UIManager.setLookAndFeel(
					Configuration.get("LOOK_AND_FEEL").toString());

		} catch (Exception e) {		    
			new main.java.memoranda.ui.ExceptionDialog(e, "Error when initializing a pluggable look-and-feel. Default LF will be used.", "Make sure that specified look-and-feel library classes are on the CLASSPATH.");
		}
		if (Configuration.get("FIRST_DAY_OF_WEEK").equals("")) {
			String fdow;
			if (Calendar.getInstance().getFirstDayOfWeek() == 2)
				fdow = "mon";
			else
				fdow = "sun";
			Configuration.put("FIRST_DAY_OF_WEEK", fdow);
			Configuration.saveConfig();
			/* DEBUG */
			System.out.println("[DEBUG] first day of week is set to " + fdow);
		}

		EventsScheduler.init();
		frame = new main.java.memoranda.ui.AppFrame();
		if (fullmode) {
			init();
		}
		if (!Configuration.get("SHOW_SPLASH").equals("no"))
			splash.dispose();

		
	}

	void init() {
		/*
		 * if (packFrame) { frame.pack(); } else { frame.validate(); }
		 * 
		 * Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		 * 
		 * Dimension frameSize = frame.getSize(); if (frameSize.height >
		 * screenSize.height) { frameSize.height = screenSize.height; } if
		 * (frameSize.width > screenSize.width) { frameSize.width =
		 * screenSize.width; }
		 * 
		 * 
		 * Make the window fullscreen - On Request of users This seems not to
		 * work on sun's version 1.4.1_01 Works great with 1.4.2 !!! So update
		 * your J2RE or J2SDK.
		 */
		/* Used to maximize the screen if the JVM Version if 1.4 or higher */
		/* --------------------------------------------------------------- */
		double JVMVer =
			Double
				.valueOf(System.getProperty("java.version").substring(0, 3))
				.doubleValue();

		frame.pack();
		if (JVMVer >= 1.4) {
			frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		} else {
			frame.setExtendedState(Frame.NORMAL);
		}
		/* --------------------------------------------------------------- */
		/* Added By Jeremy Whitlock (jcscoobyrs) 07-Nov-2003 at 15:54:24 */

		// Not needed ???
		frame.setVisible(true);
		frame.toFront();
		frame.requestFocus();

	}

	public static void closeWindow() {
		if (frame == null)
			return;
		frame.dispose();
	}

	/**
	 * Method showSplash.
	 */
	// HERE IS THE SPLASH SCREEN ////////////////////////////////////////////
	private void showSplash() {
		String text = "Version "+App.VERSION_INFO + " (Build " + App.BUILD_INFO + ")";
		splash = new JFrame();
		ImageIcon spl =
			new ImageIcon(App.class.getResource("/ui/bus_splash.png"));
		JLabel l = new JLabel();
		JLabel l2 = new JLabel();
		JPanel panel = new JPanel();
		panel.setLayout(null);
	    panel.add(l2);
	    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		l.setSize(400, 300);
		l.setIcon(spl);
		l2.setLayout(null);
		l2.setFont(new java.awt.Font("Dialog", 0, 11));
	    l2.setText(text);
	    l2.setBounds(10,10,350,600);
		splash.getContentPane().add(l);
	    splash.getContentPane();
		splash.setSize(400, 325);
		splash.add(panel);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		splash.setLocation(
			(screenSize.width - 400) / 2,
			(screenSize.height - 300) / 2);
		splash.setUndecorated(true);
		splash.setVisible(true);
	}
	
	public static LoadableProperties initProperties() {

		LoadableProperties properties = new LoadableProperties();
		try {
			properties.load(App.class.getResourceAsStream("/util/bus.properties"));
		} catch (Exception e) {
			System.out.println("[DEBUG] Could not open resources/util/memoranda.properties.");
			properties.put("VERSION", "@VERSION@");
			properties.put("BUILD", "@BUILD@");
		}
		return properties;
	}
}
