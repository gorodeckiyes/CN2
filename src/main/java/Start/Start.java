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
	private int readerCountCells;

	public Start(int readerCountCells, boolean createReport, ArrayList<String> readerCells) {
		this.createReport = createReport;
		this.readCells = readerCells;
		this.readerCountCells = readerCountCells;
	}

	/**
	 * Initialize class ui
	 */
	public void init() {
		this.showFileDialog();
		Thread tStart = new Thread(
				new thredStart(ui.getFileName(), createReport, new ArrayList<>(readCells), readerCountCells));
		tStart.setDaemon(true);
		tStart.start();
	}

	private void showFileDialog() {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {

				@Override
				public void run() {
					ui = new UserInterface();
					ui.init();
				}
			});
		} catch (InvocationTargetException | InterruptedException e) {
//			e.printStackTrace();
			System.exit(0);
		}
	}
}
