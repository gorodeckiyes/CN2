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
	private String name;
	private ArrayList<String> cells = new ArrayList<>();
	private boolean createRaport = false;
	private DocumentBuilderFactory documentBuilderFactory = null;
	private DocumentBuilder documentBuilder = null;
	private Document doc = null;
	private int readerCountCells = 0;
	
	public int getReaderCountCells() {
		if(readerCountCells == 0){
			Node elementReaderCountCells = doc.getElementsByTagName("readerCountCells").item(0).getChildNodes().item(0);
			readerCountCells = Integer.parseInt(elementReaderCountCells.getNodeValue());
		}
		return readerCountCells;
	}

	public XMLRead(String name) {
		this.name = name;
	}
	
	public void initialize() throws SAXException, IOException, ParserConfigurationException{
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		doc = documentBuilder.parse(name);
	}
	
	public ArrayList<String> getReadCells() throws Exception{
		if(doc != null){
			if(cells.size() == 0){
				Node optins = doc.getElementsByTagName("readerCells").item(0);
				NodeList loptins = optins.getChildNodes();
				for(int i = 0; i < loptins.getLength(); i++){
					Node node = loptins.item(i);
					cells.add(node.getChildNodes().item(0).getNodeValue());
				}
			return new ArrayList<>(cells);
			} else {
				return new ArrayList<>(cells);
			}
		} else {
			throw new Exception("not initialize");
		}
	}
	
	public boolean getCreateReport() throws Exception{
		if(doc != null && createRaport == false){
			Node elementCreateRaport = doc.getElementsByTagName("createReport").item(0).getChildNodes().item(0);
			if("true".equals(elementCreateRaport.getNodeValue())){
				createRaport = true;
			}
		} else {
			throw new Exception("Not initialize");
		}
		return createRaport;
	}
}
