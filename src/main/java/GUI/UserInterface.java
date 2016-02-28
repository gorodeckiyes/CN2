package GUI;

import java.awt.FileDialog;
import java.io.File;

import javax.swing.JFrame;

public class UserInterface extends JFrame {

	/**
	 * Class for user interface
	 */
	private static final long serialVersionUID = -4301164820427803206L;
	private String fileName;
	public void init(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(50,50);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.showFileDialog();
	}
	
	private void showFileDialog(){
		FileDialog fd = new FileDialog(this, "Select file", FileDialog.LOAD);
		fd.setFile("*.docx");
		fd.setVisible(true);
		fileName = fd.getDirectory()+File.separator+fd.getFile();
	}
	
	public String getFileName(){
		return fileName;
	}
}
