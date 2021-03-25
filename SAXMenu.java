import java.io.*;
import javax.xml.parsers.*;

import org.xml.sax.*;

/**
  SAX event handler to:
  
  1) Show basic event handling while reading an XML file
  2) output in a csv file

  @author 	CSCU9T4 Demo, University of Stirling
  @version  11/03/20
*/
public class SAXMenu {

  /**
    Main program to call SAX parser.

    @param args			command-line arguments
    First argument is the name of the xml file to process
  */
 
  public static void main(String[] args) {
    parse(args[0]); //calls the parse method with the first argument (command line arg)
  }

  /**
    Callback when parser finds character data.

    @param filename		XML file to read
  */
  private static void parse(String filename) {
    try {
      System.out.println("-------------------");
      System.out.println("parsing " + filename);
      System.out.println();
      // get an instance of SAXParserFactory and get an XMLReader from it
      SAXParserFactory factory = SAXParserFactory.newInstance(); //define a new SAx parser factory
      XMLReader reader = factory.newSAXParser().getXMLReader(); //get the XML reader for that factory
          //knows which callback methods to reach when it runs into an event

      // turn off XML validation
      //reader.setFeature("http://xml.org/sax/features/validation",false);

      // register the relevant handler with the parser, choosing one of:
      MenuHandler handler = new MenuHandler(); //define a handler
      //CountHandler handler = new CountHandler();
    
      reader.setContentHandler(handler); //set content handler as our handler
        //tells the reader that the defined handler method will have any callback methods for the event
      reader.setErrorHandler(handler); //set error handler as our handler

      // parse the given file
      InputSource inputSource = new InputSource(filename); //get input source - file in command line
      reader.parse(inputSource);
      System.out.println("-------------------");
      System.out.println();
    }
    catch (Exception exception) {
      System.err.println("could not parse file - " + exception);
    }
  }

}
