package SaveToHTML;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

import HTML.SaveToHTML;

public class TestSaveToHTML {
	private final int LINE_TEST = 6; 

	@Test
	public void testNewTable(){
		SaveToHTML html = new SaveToHTML("./TestSaveToHTML.html");
		html.init();
		html.newTable();
		assertTrue("<table>".equals(html.getLine(LINE_TEST)));
	}
	
	@Test
	public void testNewTableToParam(){
		SaveToHTML html = new SaveToHTML("./TestSaveToHTML.html");
		html.init();
		html.newTable("style width:100%");
		assertTrue("<table style width:100%>".equals(html.getLine(LINE_TEST)));
	}
	
	@Test
	public void testNewRowToOptions(){
		SaveToHTML html = new SaveToHTML("./TestSaveToHTML.html");
		html.init();
		html.newRow("style:width:10%");
		assertTrue("<tr style:width:10%>".equals(html.getLine(LINE_TEST)));
	}
	
	@Test
	public void testNewRow(){
		SaveToHTML html = new SaveToHTML("./TestSaveToHTML.html");
		html.init();
		html.newRow();
		assertTrue("<tr>".equals(html.getLine(LINE_TEST)));
	}
	
	@Test
	public void testEndTable(){
		SaveToHTML html = new SaveToHTML("./TestSaveToHTML.html");
		html.init();
		html.endTable();
		assertTrue(html.getLine(LINE_TEST),"</table>".equals(html.getLine(LINE_TEST)));
	}

	@Test
	public void testEndRow(){
		SaveToHTML html = new SaveToHTML("./TestSaveToHTML.html");
		html.init();
		html.endRow();
		assertTrue("</tr>".equals(html.getLine(LINE_TEST)));
	}
	
	@Test
	public void testNewCell(){
		SaveToHTML html = new SaveToHTML("./TestSaveToHTML.html");
		html.init();
		html.newCell("111");
		assertTrue("<td>111</td>".equals(html.getLine(LINE_TEST)));
	}

	@Test
	public void testNewCellToOptions(){
		SaveToHTML html = new SaveToHTML("./TestSaveToHTML.html");
		html.init();
		html.newCell("111","style width:100%");
		assertTrue("<td style width:100%>111</td>".equals(html.getLine(LINE_TEST)));
	}
	
	@Test
	public void testAddP(){
		SaveToHTML html = new SaveToHTML("./TestSaveToHTML.html");
		html.init();
		html.addP("111");
		assertTrue(html.getLine(LINE_TEST),"<p>111</p>".equals(html.getLine(LINE_TEST)));
	}
	
	@Test
	public void testAddPToOptions(){
		SaveToHTML html = new SaveToHTML("./TestSaveToHTML.html");
		html.init();
		html.addP("111","Style width:100%");
		assertTrue(html.getLine(LINE_TEST),"<p Style width:100%>111</p>".equals(html.getLine(LINE_TEST)));
	}
	
	@Test
	public void testAddDivToOptions(){
		SaveToHTML html = new SaveToHTML("./TestSaveToHTML.html");
		html.init();
		html.addDiv("111","Style width:100%");
		assertTrue(html.getLine(LINE_TEST),"<div Style width:100%>111</div>".equals(html.getLine(LINE_TEST)));
	}
	
	@Test
	public void testAddDiv(){
		SaveToHTML html = new SaveToHTML("./TestSaveToHTML.html");
		html.init();
		html.addDiv("111");
		assertTrue(html.getLine(LINE_TEST),"<div>111</div>".equals(html.getLine(LINE_TEST)));
	}
	
	
	
	@Test
	public void testSaveToFile() throws UnsupportedEncodingException, FileNotFoundException, IOException{
		SaveToHTML html = new SaveToHTML("./TestSaveToHTML.html");
		html.init();
		html.addDiv("test create and save file");
		html.addP("Test to paragraph");
		html.newTable("border=\"1\" width=\"100%\" bordercolor=\"black\"");
		html.newRow();
		html.newCell("1");
		html.newCell("2");
		html.newCell("3");
		html.newCell("4");
		html.endRow();
		html.newRow();
		html.newCell("1");
		html.newCell("2");
		html.newCell("3");
		html.newCell("4");
		html.endRow();
		html.endTable();
		html.endFile();
		html.saveToFile();
		assertTrue(new File("./TestSaveToHTML.html").exists());
	}
}