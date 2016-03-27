package MyXML;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLRead {
	String name;
	ArrayList<String> cells = new ArrayList<>();
	
	public XMLRead(String name) {
		this.name = name;
	}
	
	public ArrayList<String> Read() throws SAXException, IOException, ParserConfigurationException{
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document doc = documentBuilder.parse(name);
		Node optins = doc.getElementsByTagName("options").item(0);
		NodeList loptins = optins.getChildNodes();
		for(int i = 0; i < loptins.getLength(); i++){
			Node node = loptins.item(i);
			cells.add(node.getChildNodes().item(0).getNodeValue());
		}
		return new ArrayList<>(cells);
	}
	
}
