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
	public void testReaderCells() {
		ArrayList<String> optins = null;
		XMLRead reader = new XMLRead("./options.xml");
		try {
			reader.initialize();
		} catch (SAXException | IOException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		optins = reader.getReadCells();
		int sizeOptins = optins.size();
		assertEquals(sizeOptins, 4);
		
	}
}
