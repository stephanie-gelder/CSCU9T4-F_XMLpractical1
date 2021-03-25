import org.xml.sax.*;				// import SAX classes
import org.xml.sax.helpers.*;		// import SAX helper classes

//extends default handler and overrides main methods
//modify script so that when it encounters menu item it prints the menu name and info
//could add some class variables to keep track of the element yur in or the last one you were in
/**
  SAX event handler to output in basic format.

  @author 	CSCU9T4 Demo, University of Stirling
  @version  11/03/20
*/
public class MenuHandler extends DefaultHandler { //DefaultHandler is a default class from XMLSAX
  /**
    Callback when parser finds character data.

    @param ch			character data
    @param start		character start index
    @param length		character count
    @throws			    SAX exception
  */
  public void characters(char ch[], int start, int length) {
    String characters = new String(ch, start, length).trim();
    System.out.println(length);
    if (!characters.isEmpty())
      System.out.println("  characters '" + characters + "'");
  }

  /**
    Callback when parser finds the end of a document.

    @throws			SAX exception
  */
  public void endDocument() throws SAXException {
    System.out.println("endDocument callback");
  }

  /**
    Callback when parser finds the end of an element.

    @param namespaceURI		namespace URI
    @param localName		local namespace identifier
    @param qName		qualified name for namespace
    @throws			SAX exception
  */
  public void endElement(String namespaceURI, String localName, String qName)
   throws SAXException {
    System.out.println("endElement callback for '" + qName + "' '" + localName +"'");
  }

  /**
    Callback when parser starts to read a document.

    @throws			SAX exception
  */
  public void startDocument() throws SAXException {
    System.out.println("startDocument callback");
  }

  /**
    Callback when parser starts to read an element.

    @param namespaceURI		namespace URI
    @param localName		local namespace identifier
    @param qName		qualified name for namespace
    @param attributes		elements attributes
    @throws			SAX exception
  */
  public void startElement(String namespaceURI, String localName,
                            String qName, Attributes attributes) throws SAXException {
    System.out.println("startElement callback for '"  + qName + "'");
    for (int i = 0; i < attributes.getLength(); i++)
      System.out.println(
	"  attribute '" + attributes.getQName(i) +
	"' is '" + attributes.getValue(i) + "'");
  }

}
