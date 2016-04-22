package Thread;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import DataPackage.WordDocument;
import HTML.SaveToHTML;

public class thredStart implements Runnable {
	private boolean createReport;
	private ArrayList<String> readerCells;
	private File fileName;
	private WordDocument cnDoc;
	private WordDocument cnNewDoc;
	private SaveToHTML html;
	
	public thredStart(String fileName, boolean createReport, ArrayList<String> readerCells) {
		this.createReport = createReport;
		this.readerCells = readerCells;
		this.fileName = new File(fileName);
	}
	
	@Override
	public void run() {
		this.Init();
		
	}
	
	/**
	 * Initialize file
	 */
	private void Init(){
		html = new SaveToHTML(fileName.getPath()+File.separator+"Report_"+fileName.getName()+".html");
		html.init();
		try{
			cnDoc = (WordDocument) new XWPFDocument(new FileInputStream(fileName));
		}catch(IOException e){
			html.addP("Not file open file:"+fileName.getPath()+File.separator+fileName.getName());
			try {
				if(createReport)
					html.saveToFile();
				System.exit(0);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		cnNewDoc = (WordDocument) new XWPFDocument();
	}

	private void readerCellsDocument(){
		
	}
}
