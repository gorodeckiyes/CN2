package Thread;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import DataPackage.WordDocument;

public class thredStart implements Runnable {
	private boolean createReport;
	private ArrayList<String> readerCells;
	private File fileName;
	private WordDocument cnDoc;
	private WordDocument cnNewDoc;
	
	public thredStart(String fileName, boolean createReport, ArrayList<String> readerCells) {
		this.createReport = createReport;
		this.readerCells = readerCells;
		this.fileName = new File(fileName);
	}
	
	@Override
	public void run() {
		
		this.ini
	}
	
	/**
	 * Initialize file
	 */
	private void myInit(){
		cnDoc = (WordDocument) new XWPFDocument(new FileInputStream(fileName));
	}

}
