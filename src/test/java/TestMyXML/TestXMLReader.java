package TestMyXML;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import MyXML.XMLRead;

public class TestXMLReader {

	@Test
	public void testReader() {
		ArrayList<String> optins = null;
		XMLRead reader = new XMLRead("./options.xml");
		try {
			optins = reader.Read();
		} catch (SAXException | IOException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int sizeOptins = optins.size();
		optins.forEach(e -> {System.out.println(e);});
		assertEquals(sizeOptins, 6);
		
	}
}
