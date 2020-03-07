package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import main.java.memoranda.ui.App;

import static org.junit.Assert.*;

public class buildandversion {
    private App classUnderTest;

    @Before
    public void setUp() throws Exception {
    	
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testBuildNumber() {
    	double build_number = -1;
    	try {
    		//App.set_version_and_build_info();
    		build_number = Double.parseDouble(App.BUILD_INFO);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		assertTrue(false);
    	}
    	System.out.printf("Build Number = %s\n", App.BUILD_INFO);
        assertTrue(build_number != -1);
    }

    @Test
    public void testVersionNumber() {
    	double version_number = -1;
    	try {
    		//App.set_version_and_build_info();
    		version_number = Double.parseDouble(App.VERSION_INFO);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		assertTrue(false);
    	}
    	System.out.printf("Version Number = %s\n", App.VERSION_INFO);
        assertTrue(version_number != -1);
    }
    

}
