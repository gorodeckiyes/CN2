package Thread;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import DataPackage.CelssText;
import DataPackage.WordDocument;
import HTML.SaveToHTML;

public class thredStart implements Runnable {
	private boolean createReport;
	private ArrayList<String> readerCells;
	private File fileName;
	private WordDocument cnDoc;
	private XWPFDocument cnNewDoc;
	private SaveToHTML html;
	private int numberRowTable;
	private int readerCountCells;
	private int readerCell;

	public thredStart(String fileName, boolean createReport, ArrayList<String> readerCells, int readerCountCells) {
		this.createReport = createReport;
		this.readerCells = readerCells;
		this.fileName = new File(fileName);
		this.readerCountCells = readerCountCells;
	}

	@Override
	public void run() {
		this.Init();
		if (cnDoc.getTables().size() > 0) {
			int tableIndex = 1;
			Iterator<XWPFTable> tableIterator = cnDoc.getTablesIterator();
			while (tableIterator.hasNext()) {
				XWPFTable docTable = tableIterator.next();
				XWPFTable newTable = cnNewDoc.createTable();
				html.addDiv("Table #" + Integer.toString(tableIndex));
				html.newTable("border=\"1\" width=\"100%\" bordercolor=\"black\"");
				this.readerRowsTable(docTable, newTable);
				html.endTable();
				tableIndex++;
			}
		}
		html.endFile();
		try {
			html.saveToFile();
		} catch (IOException e) {
			e.printStackTrace();
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

	private void readerRowsTable(XWPFTable docTable, XWPFTable newTable) {
		numberRowTable = 1;
		docTable.getRows().forEach(row -> {
			if (createReport)
				html.newRow();
			XWPFTableRow newTableRow = newTable.createRow();
			this.readerCellsTable(numberRowTable, row, newTableRow);
			newTable.addRow(newTableRow);
			if (createReport)
				html.endRow();
		});
	}

	private void readerCellsTable(int rowNumber, XWPFTableRow row, XWPFTableRow newRow) {
		if (createReport)
			html.newCell(Integer.toString(rowNumber), "bgcolor=silver width=10px");
		readerCell = 0;
		row.getTableCells().forEach(cell -> {
			if (cell.getTextRecursively() != null) {
				CelssText text = new CelssText(cell.getTextRecursively());
				if (readerCells.indexOf(Integer.toString(readerCell)) != -1) {
					XWPFTableCell newCell = newRow.createCell();
					newCell.setText(text.toString());
					if (createReport)
						html.newCell(text.toString(), "bgcolor = #F9F2E3");
				} else {
					html.newCell(text.toString(), "bgcolor = red");
				}
			} else {
				html.newCell("", "bgcolor = red  width=10px");
			}
			readerCell++;
		});
	}

	private void htmlHat() {
		html.addDiv("Таблица цветов ячеек");
		html.newTable("border=\"1\" width=\"100%\" bordercolor=\"black\"");

		html.newRow();
		html.newCell("Такие ячейки будут добавлены", "bgcolor = #F9F2E3");
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
