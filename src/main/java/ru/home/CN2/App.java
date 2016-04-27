package ru.home.CN2;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import MyXML.XMLRead;
import MyXML.XMLSave;
import Start.Start;

/**
 * Hello world!
 *
 */
public class App 
{
	private static String FILE_NAME = "./options.xml";
    public static void main( String[] args )
    {
       File file = new File(FILE_NAME);
       
       if(!file.exists()){
    	   try {
			new XMLSave(FILE_NAME).save();
		} catch (ParserConfigurationException | TransformerException e) {
			e.printStackTrace();
		}   
       }
       XMLRead xml = new XMLRead(FILE_NAME);
       try {
		xml.initialize();
       } catch (SAXException | IOException | ParserConfigurationException e) {
    	   e.printStackTrace();
       }
       Start start = null;
       try {
		 start = new Start(xml.getReaderCountCells(), xml.getCreateReport(), xml.getReadCells());
       } catch (Exception e) {
		e.printStackTrace();
       }
       start.init();
    }
}
