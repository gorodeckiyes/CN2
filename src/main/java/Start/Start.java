package Start;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import GUI.UserInterface;

public class Start {
	UserInterface ui = null;
	
	/**
	 * Initialize class ui
	 */
	public void init(){
		
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				
				@Override
				public void run() {
					ui = new UserInterface();
				}
			});
		} catch (InvocationTargetException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
