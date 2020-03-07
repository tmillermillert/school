package ser322;

import java.io.FileReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.sax.SAXSource;

import org.xml.sax.InputSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import java.io.File;

public class JdbcLab2 {
    public static void main(String args[]) throws Exception {

        if (args.length != 1)
        {
            System.out.println("USAGE: java ser322.JdbcLab2 DEPTNO");
            System.exit(0);
        }

	   // Create XPathFactory object
	   XPathFactory xpathFactory = XPathFactory.newInstance();
	   // Create XPath object
	   XPath xpath = xpathFactory.newXPath();

	   // You need an InputSource for each expression you evaluate
        
        InputSource source = new InputSource(new FileReader(new File("ASSIGNMENT5.xml")));

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringElementContentWhitespace(true);
        factory.setValidating(false);

        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(source);

        Element root = doc.getDocumentElement();
        root.normalize();


        //////////////////////// XPath examples ///////////////////////
        /*
        Construct a separate Java program to display the results of an XPath expression on an exported XML file that returns all Product descriptions of Products from a given Dept
        This would be invoked on the command line as
        java ser322.JdbcLab2 <DeptNo>
        */

        NodeList productDescriptions = (NodeList)xpath.evaluate("//DEPT[DEPTNO = " + args[0] + "]/PRODUCT/DESCRIP", root, XPathConstants.NODESET);
        // XPath returning a collection of nodes
        System.out.println("Product Description for Department " + args[0]);
        for (int i=0; i < productDescriptions.getLength(); i++) {
            Node descriptionNode = (Node) productDescriptions.item(i);
            Node descriptionText = (Node)xpath.evaluate("text()", descriptionNode, XPathConstants.NODE);
            System.out.println(descriptionText.getNodeValue());
        }
    }
}
