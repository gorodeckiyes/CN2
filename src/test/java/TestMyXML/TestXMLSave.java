package TestMyXML;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import MyXML.XMLSave;

public class TestXMLSave {

	@Test
	public void testSave() {
		String FILENAME = "./options.xml";
		try{
			XMLSave xmls = new XMLSave(FILENAME);
			xmls.save();
				assertTrue(new File(FILENAME).exists());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
