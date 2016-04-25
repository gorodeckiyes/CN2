package Thread;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;

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
		if(cnDoc.getTables().size() > 0){
			int tableIndex = 1;
			Iterator<XWPFTable> tableIterator = cnDoc.getTablesIterator();
			while(tableIterator.hasNext()){
				XWPFTable docTable = tableIterator.next();
				html.addDiv("Table №"+Integer.toString(tableIndex));
				this.readerCellsDocument(docTable);
				tableIndex++;
			}
		}
	}
	
	/**
	 * Initialize file
	 */
	private void Init(){
		html = new SaveToHTML(fileName.getPath()+File.separator+"Report_"+fileName.getName()+".html");
		html.init();
		this.htmlHat();
		cnNewDoc = new WordDocument();
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

	private void readerCellsDocument(XWPFTable docTable){
		
	}
	
	private void htmlHat(){
		html.addDiv("Таблица цветов ячеек");
		html.newTable("border=\"1\" width=\"100%\" bordercolor=\"black\"");
		
		html.newRow();
		html.newCell("Такие ячейки будут добавлены","bgcolor = #F9F2E3");
		html.endRow();
		
		html.newRow();
		html.newCell("Ячейки с таким цветом не будут добавлены", "bgcolor = red");
		html.endRow();
		
		html.newRow();
		html.newCell("Ячейки с таким цветом для информации, отсутствуют в документе", "bgcolor=silver");
		html.endRow();
		html.endTable();
		html.addBR();
	}
}
