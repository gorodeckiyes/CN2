package HTML;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class SaveToHTML {
	private ArrayList<String> htmlBody;
	private String fileName;
	
	public SaveToHTML(String fileName){
		this.fileName = fileName;
	}
	
	public void init(){
		htmlBody = new ArrayList<>();
		htmlBody.add("<!DOCTYPE html>");
		htmlBody.add("<html>");
		htmlBody.add("<head>");
		htmlBody.add("<meta charset=\"utf-8\">");
		htmlBody.add("</head>");
		htmlBody.add("<body>");
	}
	
	/**
	 * Create table
	 */
	public void newTable(){
		htmlBody.add("<table>");
	}
	
	public void newTable(String param){
		htmlBody.add("<table "+param+">");
	}
	
	/**
	 * End Table
	 */
	public void endTable(){
		htmlBody.add("</table>");
	}
	
	/**
	 * Create row
	 * @param param - options to rows
	 */
	public void newRow(String param){
		htmlBody.add("<tr "+param+">");
	}
	
	public void newRow(){
		htmlBody.add("<tr>");
	}
	/**
	 * End row
	 */
	public void endRow(){
		htmlBody.add("</tr>");
	}
	
	/**
	 * Add new cell and add to options
	 * @param param options
	 * @param text
	 */
	public void newCell(String text, String param){
		htmlBody.add("<td "+param+">"+text+"</td>");
	}
	
	/**
	 * add new cell
	 * @param text
	 */
	public void newCell(String text){
		htmlBody.add("<td>"+text+"</td>");
	}
	
	/**
	 * add new paragraph
	 * @param text
	 */
	public void addP(String text){
		htmlBody.add("<p>"+text+"</p>");
	}
	
	/**
	 * add new paragraph and options cell
	 * @param text
	 * @param param options
	 */
	public void addP(String text, String param){
		htmlBody.add("<p "+param+">"+text+"</p>");
	}
	
	/**
	 * add new DIV
	 * @param param options
	 * @param text
	 */
	public void addDiv(String text, String param){
		htmlBody.add("<div "+param+">"+text+"</div>");
	}
	
	public void addDiv(String text){
		htmlBody.add("<div>"+text+"</div>");
	}
	/**
	 * add teg close file
	 */
	public void endFile(){
		htmlBody.add("</body></html>");
	}	
	
	/**
	 * get line html body
	 * @param index
	 * @return
	 */
	public String getLine(int index){
		return htmlBody.get(index);
	}
	
	public int size(){
		return htmlBody.size();
	}
	
	public void saveToFile() throws UnsupportedEncodingException, FileNotFoundException, IOException{
		if(htmlBody.size() > 0){
			StringBuffer body = new StringBuffer();
			htmlBody.forEach(e -> {
				body.append(e);
			});
			try(OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(fileName), "utf-8")){
				out.write(body.toString());
			}
		}
	}
	
	public void addBR(){
		htmlBody.add("<br />");
	}
}
