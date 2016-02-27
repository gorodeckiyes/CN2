package DataPackage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class WordDocument extends XWPFDocument{
	
	/**
	 * Save result to file
	 * @param nameFile
	 * @throws IOException
	 */
	public void saveToFile(String nameFile) throws IOException{
		FileOutputStream saveFile = new FileOutputStream(new File(nameFile));
		super.write(saveFile);
		saveFile.close();
	}
}
