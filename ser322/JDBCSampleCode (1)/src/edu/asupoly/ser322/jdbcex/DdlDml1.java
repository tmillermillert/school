package edu.asupoly.ser322.jdbcex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;
import java.util.List;
import java.util.ArrayList;

public class DdlDml1 {
    public static void main(String[] args) {
	ResultSet rs = null;
	PreparedStatement ps = null;
	Statement stmt = null;
	Connection conn = null;

	if (args.length != 4) {
	    System.out.println("USAGE: java edu.asupoly.ser322.jdbcex.DdlDml1 <url> <user> <passwd> <driver>");
	    System.exit(0);
	}

	try {
	    String _url = args[0];

	    // Step 1: Load the JDBC driver
	    Class.forName(args[3]);

	    // Step 2: make a connection
	    conn = DriverManager.getConnection(_url, args[1], args[2]);
	    // if autocommit is true then a transaction will be executed
	    // on each DDL or DML statement immediately, usually you want
	    // to set to false to batch within a single transaction.
	    conn.setAutoCommit(false);

	    // Step 2.1 - get the DB MetaData
	    DatabaseMetaData dbmd = (DatabaseMetaData) conn.getMetaData();
	    
	    // get the table named tab1 if it exists
	    rs = dbmd.getTables(null, null, "tab1", null);
	    if (rs.next()) {
		System.out.println("Table tab1 found, removing...");
		// the table exists, better remove it
		stmt = conn.createStatement();
		stmt.executeUpdate("DROP TABLE tab1");
		stmt.close();
	    }
	    if (rs != null) rs.close();
	    
	    // Step 3: Create a statement
	    stmt = conn.createStatement();

	    // Step 4: Make a query
	    stmt.executeUpdate("CREATE TABLE tab1 (id integer NOT NULL, name varchar(300)," +
			       " PRIMARY KEY(id))");
	    stmt.close();

	    // Let's write a tuple or two
	    ps = conn.prepareStatement("INSERT INTO tab1 VALUES (?, ?)");
	    ps.setInt(1, 1);
	    ps.setString(2, "Joe");
	    if (ps.executeUpdate() > 0) {
		System.out.println("Inserted first tuple OK");
	    }
	    ps.clearParameters();
	    // now we can start again
	    ps.setInt(1, 2);
	    ps.setString(2, "Sue");

	    if (ps.executeUpdate() > 0) {
		System.out.println("Inserted second tuple OK");
	    }
	    ps.close();

	    // Have to do this to write changes to a DB
	    conn.commit();

	    // the connection is still open, let's query our new table
	    stmt = conn.createStatement();
	    rs = stmt.executeQuery("Select * from tab1");
	    // Let's marshall the data into an object this time
	    List<Tab1Object> tab1Objects = new ArrayList<Tab1Object>();
	    while (rs.next()) {
		tab1Objects.add(new Tab1Object(rs.getInt(1), rs.getString(2)));
	    }
	    rs.close();
	    stmt.close();
	    // Now let's output what we got
	    for (Tab1Object t1obj : tab1Objects) {
		System.out.println(t1obj);
	    }
	}
	catch (Exception se) {
	    se.printStackTrace();
	}
	finally {
	    try {
		if (stmt != null) 
		    stmt.close();
		if (conn != null) {
		    // check if the connection is open, and if so do a rollback
		    // to avoid a transaction context sitting open on the server
		    conn.rollback();
		    conn.close();
		}
	    }
	    catch (SQLException se2) {
		se2.printStackTrace();
		System.out.println("Not all DB resources freed!");
	    }
	}
    }
}

class Tab1Object {
    private int     id;
    private String  name;
    Tab1Object(int id, String name) {
	this.id = id;
	this.name = name;
    }
    public String toString() {
	return "Tab1Object: id = " + id + ", name is " + name;
    }
}
