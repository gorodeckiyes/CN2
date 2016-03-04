package Start;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import GUI.UserInterface;
import Thread.thredStart;

public class Start {
	private UserInterface ui = null;
	
	/**
	 * Initialize class ui
	 */
	public void init(){
		this.showFileDialog();
		Thread tStart = new Thread(new thredStart());
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
