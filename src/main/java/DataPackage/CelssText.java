package DataPackage;

public class CelssText {
	private StringBuffer textCels;
	
	public CelssText() {
		textCels = new StringBuffer();
	}
	
	public CelssText(String text) {
		this();
		this.addText(text);
	}
	
	/**
	 * Add text
	 * @param text
	 */
	public void addText(String text){
		textCels.append(text);
	}
	
	/**
	 * Set text
	 * @param text
	 */
	public void setText(String text){
		this.delete();
		this.addText(text);
	}
	
	/**
	 * Get text
	 * @return
	 */
	public String getText(){
		return textCels.toString();
	}
	
	/**
	 * Delete text this object
	 */
	public void delete(){
		textCels.delete(0, textCels.toString().length());
	}
	
	/**
	 * trim text this object
	 */
	public void trim(){
		String text = textCels.toString();
		this.delete();
		this.addText(text.trim());
	}
	
	/**
	 * replace text as replacetext
	 * @param regex - search text
	 * @param replaceText - replace text
	 */
	public void replaceText(String regex, String replaceText){
		String text = textCels.toString();
		while (text.indexOf(regex) > -1){
			text = text.replaceAll(regex, replaceText);
		}
	}
	
	@Override
	public String toString() {
		trim();
		this.replaceText("  ", " ");
		return textCels.toString();
	}
}
