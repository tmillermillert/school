package ser322;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;

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
 
 Write a method named query2 that uses JDBC to execute a SELECT query to List a Dept Name together
  with the Names of Customers who have purchased a Product made by that Department and the amount 
  the Customer spent on the purchase. Use a PreparedStatement with a parameter for the DeptNo and
  filter the query results to only return rows tied to that Dept.
	Pretty-print the results to the console (One line per row in readable aligned columns)
	This would be invoked on the command line as 
           java ser322.JdbcLab <url> <user> <pwd> <driver> query2 <DeptNo>
 */
public class JdbcLab
{
	public ResultSet rs;
	public Statement stmt;
	public PreparedStatement pstmt;
	public ResultSet rs2;
	public Statement stmt2;
	public PreparedStatement pstmt2;
	public ResultSet rs3;
	public Connection conn;
	public static void main(String[] args)
	{
		if (args.length < 5)
		{
			System.out.println("USAGE: java ser322.JdbcLab <url> <user> <passwd> <driver> query1");
			System.out.println("java ser322.JdbcLab <url> <user> <pwd> <driver> query2 <DeptNo>");
			System.out.println("java ser322.JdbcLab <url> <user> <pwd> <driver> dml1 <customer id> <product id> <name> <quantity>");
			System.exit(0);
		}
		JdbcLab jdbcLab = new JdbcLab();

		String _url = args[0];
		try {
			// Step 1: Load the JDBC driver
			Class.forName(args[3]);

			// Step 2: make a connection
			jdbcLab.conn = DriverManager.getConnection(_url, args[1], args[2]);

			// Step 3: Create a statement
			jdbcLab.stmt = jdbcLab.conn.createStatement();
			jdbcLab.conn.setAutoCommit(false);

			//SELECT query to List all Emp IDs, Names, together with their Dept names
			// Step 4: Make a query
			if(args[4].compareTo("query1") == 0){
				jdbcLab.query1();
			}
			else if(args[4].compareTo("query2") == 0){
				jdbcLab.query2(args[5]);
			}
			else if(args[4].compareTo("dml1") == 0){
				jdbcLab.dml1(args[5],args[6],args[7],args[8]);
			}
			else if(args[4].compareTo("export") == 0){
				jdbcLab.export(args[5]);
			}

			
		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}
		finally {  // ALWAYS clean up your DB resources
			try {
				if (jdbcLab.rs != null)
					jdbcLab.rs.close();
				if (jdbcLab.rs2 != null)
					jdbcLab.rs2.close();
				if (jdbcLab.rs3 != null)
					jdbcLab.rs3.close();
				if (jdbcLab.stmt != null)
					jdbcLab.stmt.close();
				if (jdbcLab.stmt2 != null)
					jdbcLab.stmt2.close();
				if (jdbcLab.pstmt != null)
					jdbcLab.pstmt.close();
				if (jdbcLab.pstmt2 != null)
					jdbcLab.pstmt2.close();
				if (jdbcLab.conn != null)
					jdbcLab.conn.close();
			}
			catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	//USAGE: java ser322.JdbcLab <url> <user> <passwd> <driver> query1
	public void query1() throws Exception{
		this.rs = this.stmt.executeQuery("SELECT EMPNO, ENAME, DNAME FROM EMP, DEPT WHERE EMP.DEPTNO = DEPT.DEPTNO");
		// Step 5: Display the results
		System.out.print("EMPNO\t\t");
		System.out.print("ENAME\t\t");
		System.out.println("DNAME\t\t");
			
		while (this.rs.next()) {
			System.out.print(this.rs.getInt(1) + "\t\t");
			System.out.print(this.rs.getString(2) + "\t\t");
			System.out.println(this.rs.getString(3) + "\t\t");
		}
	}

	//java ser322.JdbcLab <url> <user> <pwd> <driver> query2 <DeptNo>
	public void query2(String dept) throws Exception{
		this.pstmt = this.conn.prepareStatement("SELECT DNAME, NAME, CAST(PRICE * CAST(QUANTITY as decimal(10,2)) as decimal(10,2))" +
			" FROM CUSTOMER, DEPT, PRODUCT" +
			" WHERE CUSTOMER.PID = PRODUCT.PRODID AND PRODUCT.MADE_BY = DEPT.DEPTNO AND DEPT.DEPTNO=?");
		this.pstmt.setString(1, dept);
		this.rs = this.pstmt.executeQuery();
		// Step 5: Display the results
		System.out.print("DNAME\t\t");
		System.out.print("NAME\t\t");
		System.out.println("PRICE * QUANTITY\t\t");
			
		while (this.rs.next()) {
			System.out.print(this.rs.getString(1) + "\t\t");
			System.out.print(this.rs.getString(2) + "\t\t");
			System.out.println(this.rs.getFloat(3) + "\t\t");
		}
	}
	//java ser322.JdbcLab <url> <user> <pwd> <driver> dml1 <customer id> <product id> <name> <quantity>
	public void dml1(String customer_id, String product_id, String name, String quantity) throws Exception{
		
		this.pstmt = this.conn.prepareStatement("INSERT INTO CUSTOMER VALUES (?,?,?,?)");
		this.pstmt.setInt(1, Integer.parseInt(customer_id));
		this.pstmt.setInt(2, Integer.parseInt(product_id));
		this.pstmt.setString(3, name);
		this.pstmt.setInt(4, Integer.parseInt(quantity));
		this.pstmt.executeUpdate();
		System.out.println("Success");
		conn.commit();

	}
	//java ser322.JdbcLab <url> <user> <pwd> <driver> export <filename>
	public void export(String filename){
      	try {
        	DocumentBuilderFactory dbFactory =
         	DocumentBuilderFactory.newInstance();
         	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         	Document doc = dBuilder.newDocument();
         
         	// root element
         	Element rootElement = doc.createElement("ASSIGNMENT5");
         	doc.appendChild(rootElement);
			
         	// Create Department elements
         	this.rs = this.stmt.executeQuery("SELECT DEPTNO, DNAME, LOC FROM DEPT");
         	
         	while (this.rs.next()) {
         		Element dept = doc.createElement("DEPT");
         		rootElement.appendChild(dept);
         		Element deptNO = doc.createElement("DEPTNO");
         		dept.appendChild(deptNO);
         		Element dName = doc.createElement("DNAME");
         		dept.appendChild(dName);
         		Element loc = doc.createElement("LOC");
         		dept.appendChild(loc);
				deptNO.appendChild(doc.createTextNode(Integer.toString(this.rs.getInt(1))));
				dName.appendChild(doc.createTextNode(this.rs.getString(2)));
				loc.appendChild(doc.createTextNode(this.rs.getString(3)));

				// Create Employee elements
				this.pstmt = this.conn.prepareStatement("SELECT EMPNO, ENAME, JOB, MGR, SAL, COMM, EMP.DEPTNO" +
				" FROM EMP, DEPT" +
				" WHERE EMP.DEPTNO = DEPT.DEPTNO AND EMP.DEPTNO = ?");
				this.pstmt.setInt(1, this.rs.getInt(1));
				this.rs2 = this.pstmt.executeQuery();
				while (this.rs2.next()){
					Element emp = doc.createElement("EMP");
					dept.appendChild(emp);
					Element empNO = doc.createElement("EMPNO");
					emp.appendChild(empNO);
					Element eName = doc.createElement("ENAME");
					emp.appendChild(eName);
					Element job = doc.createElement("JOB");
					emp.appendChild(job);
					Element mgr = doc.createElement("MGR");
					emp.appendChild(mgr);
					Element sal = doc.createElement("SAL");
					emp.appendChild(sal);
					Element comm = doc.createElement("COMM");
					emp.appendChild(comm);
					Element eDeptNO = doc.createElement("DEPTNO");
					emp.appendChild(eDeptNO);
					empNO.appendChild(doc.createTextNode(Integer.toString(this.rs2.getInt(1))));
					eName.appendChild(doc.createTextNode(this.rs2.getString(2)));
					job.appendChild(doc.createTextNode(this.rs2.getString(3)));
					mgr.appendChild(doc.createTextNode(Integer.toString(this.rs2.getInt(4))));
					sal.appendChild(doc.createTextNode(Integer.toString(this.rs2.getInt(5))));
					comm.appendChild(doc.createTextNode(this.rs2.getString(6)));
					eDeptNO.appendChild(doc.createTextNode(Integer.toString(this.rs2.getInt(7))));
				}

				// Create Product elements
				this.pstmt = this.conn.prepareStatement("SELECT PRODID, PRICE, MADE_BY, DESCRIP" +
				" FROM PRODUCT, DEPT" +
				" WHERE PRODUCT.MADE_BY = DEPT.DEPTNO AND PRODUCT.MADE_BY = ?");
				this.pstmt.setInt(1, this.rs.getInt(1));
				this.rs2 = this.pstmt.executeQuery();
				while (this.rs2.next()){
					Element product = doc.createElement("PRODUCT");
					dept.appendChild(product);
					Element prodID = doc.createElement("PRODID");
					product.appendChild(prodID);
					Element price = doc.createElement("PRICE");
					product.appendChild(price);
					Element made_by = doc.createElement("MADE_BY");
					product.appendChild(made_by);
					Element descrip = doc.createElement("DESCRIP");
					product.appendChild(descrip);
					
					prodID.appendChild(doc.createTextNode(Integer.toString(this.rs2.getInt(1))));
					price.appendChild(doc.createTextNode(Integer.toString(this.rs2.getInt(2))));
					made_by.appendChild(doc.createTextNode(Integer.toString(this.rs2.getInt(3))));
					descrip.appendChild(doc.createTextNode(this.rs2.getString(4)));

					// Create customer elements
					this.pstmt2 = this.conn.prepareStatement("SELECT CUSTID, PID, NAME, QUANTITY" + 
					" FROM CUSTOMER, PRODUCT" + 
					" WHERE PRODUCT.PRODID = CUSTOMER.PID AND CUSTOMER.PID = ?");
					this.pstmt2.setInt(1, this.rs2.getInt(1));
					this.rs3 = this.pstmt2.executeQuery();
					while (this.rs3.next()){
						Element customer = doc.createElement("CUSTOMER");
						product.appendChild(customer);
						Element custID = doc.createElement("CUSTID");
						customer.appendChild(custID);
						Element pID = doc.createElement("PID");
						customer.appendChild(pID);
						Element name = doc.createElement("NAME");
						customer.appendChild(name);
						Element quantity = doc.createElement("QUANTITY");
						customer.appendChild(quantity);
					
						custID.appendChild(doc.createTextNode(Integer.toString(this.rs3.getInt(1))));
						pID.appendChild(doc.createTextNode(Integer.toString(this.rs3.getInt(2))));
						name.appendChild(doc.createTextNode(this.rs3.getString(3)));
						quantity.appendChild(doc.createTextNode(Integer.toString(this.rs3.getInt(4))));
					}
					
				}
	
			}

         	// write the content into xml file
         	TransformerFactory transformerFactory = TransformerFactory.newInstance();
         	Transformer transformer = transformerFactory.newTransformer();
         	DOMSource source = new DOMSource(doc);
         	StreamResult result = new StreamResult(new File("./" + filename));
         	transformer.transform(source, result);
         
         	// Output to console for testing
         	StreamResult consoleResult = new StreamResult(System.out);
         	transformer.transform(source, consoleResult);
      	} catch (Exception e) {
        	e.printStackTrace();
      	}
	}
}