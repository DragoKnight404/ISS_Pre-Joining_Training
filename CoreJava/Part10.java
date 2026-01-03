/**
 * Part 10: XML Processing (DOM Parser)
 * Concepts Covered:
 * 1. XML Structure: Tags, Attributes, Elements.
 * 2. DocumentBuilder: The standard Java tool to load XML.
 * 3. Normalization: Cleaning up the structure before reading.
 * 4. Node Traversal: Looping through tags.
 */

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.File;
import java.io.FileWriter;


public class Part10 {

    public static void main(String[] args) {
        String fileName = "employees.xml";

        // STEP 1: Create a dummy XML file for us to read
        createSampleXML(fileName);

        // STEP 2: Read and Parse the XML
        try {
            // A. Prepare the Loader (Boilerplate code)
            File xmlFile = new File(fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            // B. Parse the file into memory (The 'Document' object)
            Document doc = dBuilder.parse(xmlFile);

            // C. Normalize: Removes empty text nodes/newlines that confuse parsing
            doc.getDocumentElement().normalize();

            // D. Get the Root Element
            System.out.println("Root Element: " + doc.getDocumentElement().getNodeName()); // Prints "Company"

            // E. Get all "Employee" tags
            NodeList nList = doc.getElementsByTagName("Employee");
            System.out.println("----------------------------");

            // F. Loop through the list of employees
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                // Check if this node is actually an Element (and not just whitespace)
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    // READING AN ATTRIBUTE (Data inside the tag definition)
                    // Example: <Employee id="101">
                    String id = eElement.getAttribute("id");

                    // READING TEXT CONTENT (Data between tags)
                    // Example: <Name>Alice</Name>
                    String name = eElement.getElementsByTagName("Name").item(0).getTextContent();
                    String role = eElement.getElementsByTagName("Role").item(0).getTextContent();

                    System.out.println("Employee ID : " + id);
                    System.out.println("Name        : " + name);
                    System.out.println("Role        : " + role);
                    System.out.println("----------------------------");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Helper method to write a file
    public static void createSampleXML(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("<?xml version=\"1.0\"?>\n");
            writer.write("<Company>\n");
            writer.write("   <Employee id=\"101\">\n");
            writer.write("      <Name>Alice Green</Name>\n");
            writer.write("      <Role>Manager</Role>\n");
            writer.write("   </Employee>\n");
            writer.write("   <Employee id=\"102\">\n");
            writer.write("      <Name>Bob Brown</Name>\n");
            writer.write("      <Role>Developer</Role>\n");
            writer.write("   </Employee>\n");
            writer.write("</Company>");
            System.out.println(">> XML File created: " + filename);
        } catch (Exception e) {
            System.out.println("Error writing XML: " + e.getMessage());
        }
    }
}