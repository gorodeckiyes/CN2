package MyXML;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLSave {
	private String fileName;
	private String countCountN = "5";

	public XMLSave(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Save options
	 * 
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 */
	public void save() throws ParserConfigurationException, TransformerException {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document doc = documentBuilder.newDocument();
		Element options = doc.createElement("options");
		doc.appendChild(options);

		Element colCount = doc.createElement("colCount");
		colCount.appendChild(doc.createTextNode(countCountN));
		options.appendChild(colCount);
		for (int i = 0; i <= Integer.parseInt(countCountN); i++) {
			Element cell = doc.createElement("cells" + Integer.toString(i));
			cell.appendChild(doc.createTextNode(Integer.toString(i)));
			options.appendChild(cell);
		}
		TransformerFactory transformerFectory = TransformerFactory.newInstance();
		Transformer transformer = transformerFectory.newTransformer();
		StreamResult streamResult = new StreamResult(new File(fileName));
		DOMSource domSource = new DOMSource(doc);
		transformer.transform(domSource, streamResult);
	}
}
