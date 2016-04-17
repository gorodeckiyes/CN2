package Start;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import GUI.UserInterface;
import Thread.thredStart;

public class Start {
	private UserInterface ui = null;
	private ArrayList<String> readCells = null;
	private boolean createReport = false;
	
	public Start(boolean createReport, ArrayList<String> readerCells) {
		this.createReport = createReport;
		this.readCells = readerCells;
	}
	
	/**
	 * Initialize class ui
	 */
	public void init(){
		this.showFileDialog();
		Thread tStart = new Thread(new thredStart(ui.getFileName(), createReport,
				new ArrayList<>(readCells)));
		tStart.start();
	}
	
	private void showFileDialog(){
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				
				@Override
				public void run() {
					ui = new UserInterface();
					ui.init();
				}
			});
		} catch (InvocationTargetException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
