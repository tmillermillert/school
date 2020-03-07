import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

/*
This program connects to the database at the given URL and makes the specified query.
It takes as parameters the username and password.

query1 uses JDBC to execute a SELECT query to List all Emp IDs, Names, together with their Dept names
	Use a regular JDBC Statement object for this.
	Pretty-print the results to the console (One line per row in readable aligned columns)

	This would be invoked on the command line as 
           java ser322.JdbcLab <url> <user> <pwd> <driver> query1
    e.g.
    java -cp mysql-connector-java-5.1.45-bin.jar:. "jdbc:mysql://localhost/company?autoReconnect=true&useSSL=false" root mydbpassword com.mysql.jdbc.Driver
 */
public class JdbcLab
{
	public static void main(String[] args)
	{
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;

		if (args.length != 5)
		{
			System.out.println("USAGE: java ser322.JdbcLab <url> <user> <passwd> <driver> query1");
			System.exit(0);
		}
		String _url = args[0];
		try {
			// Step 1: Load the JDBC driver
			Class.forName(args[3]);

			// Step 2: make a connection
			conn = DriverManager.getConnection(_url, args[1], args[2]);

			// Step 3: Create a statement
			stmt = conn.createStatement();

			//SELECT query to List all Emp IDs, Names, together with their Dept names
			// Step 4: Make a query
			if(args[4].compareTo("query1") == 0){
				rs = stmt.executeQuery("SELECT EMPNO, ENAME, DNAME FROM EMP, DEPT WHERE EMP.DEPTNO = DEPT.DEPTNO");
			}
			// Step 5: Display the results
			System.out.print("EMPNO\t\t");
			System.out.print("ENAME\t\t");
			System.out.println("DNAME\t\t");
			
			while (rs.next()) {
				System.out.print(rs.getInt(1) + "\t\t");
				System.out.print(rs.getString(2) + "\t\t");
				System.out.println(rs.getString(3) + "\t\t");
			}
			
		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}
		finally {  // ALWAYS clean up your DB resources
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			}
			catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
}