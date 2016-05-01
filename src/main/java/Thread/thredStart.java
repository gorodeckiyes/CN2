package Thread;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import DataPackage.CelssText;
import DataPackage.WordDocument;
import HTML.SaveToHTML;
import java.util.List;

public class thredStart implements Runnable {
	private boolean createReport;
	private ArrayList<String> readerCells;
	private File fileName;
	private WordDocument cnDoc;
	private WordDocument cnNewDoc;
	private SaveToHTML html;
	private int readerCountCells;

	public thredStart(String fileName, boolean createReport, ArrayList<String> readerCells, int readerCountCells) {
		this.createReport = createReport;
		this.readerCells = readerCells;
		this.fileName = new File(fileName);
		this.readerCountCells = readerCountCells;
	}

	@Override
	public void run() {
		this.Init();
		List<String> cellsStringList = new ArrayList<>();
		if (cnDoc.getTables().size() > 0) {
			int tableIndex = 1;
			Iterator<XWPFTable> tableIterator = cnDoc.getTablesIterator();
			while (tableIterator.hasNext()) {
				XWPFTable docTable = tableIterator.next();
				XWPFTable newTable = null;
				html.addBR();
				html.addDiv("<b>Table #" + Integer.toString(tableIndex)+"</b>");
				html.newTable("border=\"1\" width=\"100%\"");
				List<XWPFTableRow> docTableRows = docTable.getRows();
				int rowIndex = 1;
				for(XWPFTableRow row : docTableRows){
					List<XWPFTableCell> docTableCells = row.getTableCells();
					html.newRow();
					html.newCell(Integer.toString(rowIndex), "bgcolor=silver width=10px");
					cellsStringList.clear();
					for(XWPFTableCell cell : docTableCells){
						cellsStringList.add(new CelssText(cell.getTextRecursively()).toString());
					}
					if(isAddCells(cellsStringList)){
						if(newTable == null){
							newTable = cnNewDoc.createTable(1,readerCountCells);
						}
						XWPFTableRow rowNewTable = this.getRow(newTable, rowIndex); 
						int indexCell = 0;
						for(String textCell : cellsStringList){
							if(readerCells.indexOf(Integer.toString(indexCell)) != -1){
								html.newCell(textCell, "bgcolor = limegreen");
								rowNewTable.getCell(indexCell).setText(textCell);
							} else {
								html.newCell(textCell, "bgcolor = red");
							}
							indexCell++;
						}
					} else {
						for(String text: cellsStringList)
							html.newCell(text, "bgcolor = red");
					}
					html.endRow();
					rowIndex++;
				}
				tableIndex++;
				html.endTable();
			}
		}
		this.finall();
	}
	
	private XWPFTableRow getRow(XWPFTable table, int index){
		if(index == 1){
			return table.getRow(0);
		} else {
			return table.createRow();
		}
	}
	
	/**
	 * Check cell
	 * @param textCells list cells
	 * @return boolean
	 */
	private boolean isAddCells(List<String> textCells){
		boolean result = true;
		if(textCells.size() >= readerCountCells){
			for(int i = 0; i < readerCells.size(); i++){
				int indexCells = Integer.parseInt(readerCells.get(i));
				if(textCells.get(indexCells) == null)
					result = false;
				if(textCells.get(indexCells).length() < 3){
					result = false;
				}
			}
		}else{
			result = false;
		}
		return result;
	}

	private void finall(){
		html.endFile();
		try {
			html.saveToFile();
			cnNewDoc.saveToFile(fileName.getParent()+File.separator+"new_"+fileName.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (Desktop.isDesktopSupported()) {
			Desktop d = Desktop.getDesktop();
			try {
				d.open(new File(html.getFileName()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.exit(0);
	}
	
	/**
	 * Initialize file
	 */
	private void Init() {
		html = new SaveToHTML(fileName.getParent() + File.separator + "Report_" + fileName.getName() + ".html");
		html.init();
		this.htmlHat();
		cnNewDoc = new WordDocument();
		try {
			cnDoc = new WordDocument(new FileInputStream(fileName));
		} catch (IOException e) {
			html.addP("Not file open file:" + fileName.getPath() + File.separator + fileName.getName());
			try {
				if (createReport)
					html.saveToFile();
				System.exit(0);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		cnNewDoc = new WordDocument();
	}

	private void htmlHat() {
		html.addDiv("Таблица цветов ячеек");
		html.newTable("border=\"1\" width=\"100%\" bordercolor=\"black\"");

		html.newRow();
		html.newCell("Такие ячейки будут добавлены", "bgcolor = limegreen");
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
