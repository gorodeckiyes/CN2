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
	
	public void addText(String text){
		textCels.append(text);
	}
	
	public String getText(){
		return textCels.toString();
	}
	
	public void delete(){
		textCels.delete(0, textCels.toString().length());
	}
	
	public void trim(){
		String text = textCels.toString();
		this.delete();
		this.addText(text.trim());
	}
	
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
