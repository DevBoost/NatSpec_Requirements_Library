package de.devboost.natspec.library.components.doc;

public class StyleBuilder {

	private StringBuffer style = new StringBuffer();
	
	public StyleBuilder addStyle(String key, String value) {
		style.append(key + "=" + value + ";");
		return this;
	}
	
	public StyleBuilder addStyle(String key, Object value) {
		style.append(key + "=" + value + ";");
		return this;
	}
	
	public String getStyle() {
		return style.toString();
	}

	public String toString() {
		return getStyle();
	}
}
