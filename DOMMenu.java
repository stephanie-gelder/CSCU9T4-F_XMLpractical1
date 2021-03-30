import java.io.*;               // import input-output

import javax.xml.XMLConstants;
import javax.xml.parsers.*;         // import parsers
import javax.xml.transform.dom.DOMSource;
import javax.xml.xpath.*;           // import XPath
import javax.xml.validation.*;      // import validators
import javax.xml.transform.*;       // import DOM source classes

//import com.sun.xml.internal.bind.marshaller.NioEscapeHandler;
import org.w3c.dom.*;               // import DOM
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
  DOM handler to read XML information, to create this, and to print it.

  @author   CSCU9T4, University of Stirling
  @version  11/03/20
*/
public class DOMMenu {

  /** Document builder */
  private static DocumentBuilder builder = null;

  /** XML document */
  private static Document document = null;

  /** XPath expression */
  private static XPath path = null;

  /** XML Schema for validation */
  private static Schema schema = null;

  /*----------------------------- General Methods ----------------------------*/

  /**
    Main program to call DOM parser.

    @param args         command-line arguments
  */
  public static void main(String[] args) throws SAXParseException {
    // load XML file into "document"
    loadDocument(args[0]);

    // validates the XML file
    boolean isValidated = validateDocument(args[1]);

    // print small_menu.xml using DOM methods and XPath queries
    if (isValidated) {
      printNodes();
    }
  }

  /**
    Set global document by reading the given file.

    @param filename     XML file to read
  */
  private static void loadDocument(String filename) {
    try {
      // create a new document builder from the builderFactory
      DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
      builder = builderFactory.newDocumentBuilder();

      // create an XPath expression
      XPathFactory xpathFactory = XPathFactory.newInstance();
      path = xpathFactory.newXPath();

      // parse the document for later searching
      document = builder.parse(new File(filename)); //XML document is parsed into a full tree document

    }
    catch (Exception exception) {
      System.err.println("could not load document " + exception);
    }
  }

  /*-------------------------- DOM and XPath Methods -------------------------*/
  /**
   Validate the document given a schema file
   @param filename XSD file to read
  */
  private static Boolean validateDocument(String filename) throws SAXParseException {
    try {
      String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
      SchemaFactory factory = SchemaFactory.newInstance(language);
      schema = factory.newSchema(new File(filename));
      Validator validator = schema.newValidator();
      validator.validate(new DOMSource(document)); //convert file into its own source before its validated
      return true;
    } catch (SAXParseException | IOException e) { //exception thrown if not validated
      e.printStackTrace();
      return false;
    } catch (SAXException e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
    Print nodes using DOM methods and XPath queries.
  */
  private static void printNodes() {
    Node menuItem_1 = document.getFirstChild();
    Node menuItem_2 = menuItem_1.getFirstChild().getNextSibling();
    System.out.println("First child is: " + menuItem_1.getNodeName());
    System.out.println("  Child is: " + menuItem_2.getNodeName());

    Node menuItem_3 = document.getElementsByTagName("item").item(1);
    System.out.println(menuItem_3.getTextContent());

    NodeList menuItems = document.getElementsByTagName("*");
    for (int i = 0; i < menuItems.getLength(); i++){
      Node item = menuItems.item(i);
      if(item.getNodeType() == Node.ELEMENT_NODE){
        System.out.println(item.getTextContent());
      }
    }
  }

  /**
    Get result of XPath query.

    @param query        XPath query
    @return         result of query
  */
  private static String query(String query) {
    String result = "";
    try {
      result = path.evaluate(query, document);
    }
    catch (Exception exception) {
      System.err.println("could not perform query - " + exception);
    }
    return(result);
  }
}
