package DataPackage;

public class CelssText {
	private StringBuffer textCels;
	
	public CelssText() {
		textCels = new StringBuffer();
	}
	
	public CelssText(String text) {
		this();
		this.setText(text);
	}
	
	public void setText(String text){
		textCels.append(text);
	}
	
	public void delete(){
		textCels.delete(0, textCels.length());
	}
	
	public void trim(){
		String text = textCels.toString();
		this.delete();
		this.setText(text);
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
