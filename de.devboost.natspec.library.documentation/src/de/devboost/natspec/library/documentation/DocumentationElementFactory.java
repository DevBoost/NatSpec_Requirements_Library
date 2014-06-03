package de.devboost.natspec.library.documentation;

import java.util.List;

/**
 * This factory can be used to create complex documentation elements that
 * consist of multiple basic documentation element that are provided by the
 * {@link DocumentationFactory}.
 */
public class DocumentationElementFactory {
	
	/**
	 * This class is a singleton and this is the only instance.
	 */
	public final static DocumentationElementFactory INSTANCE = new DocumentationElementFactory();
	
	private final static DocumentationFactory DOCUMENTATION_FACTORY = DocumentationFactory.eINSTANCE;
	
	private DocumentationElementFactory() {
		super();
	}

	public HtmlCode createTodo(String text, TextContainer container) {
		
		HtmlCode htmlCode = DOCUMENTATION_FACTORY.createHtmlCode();
		htmlCode.setText("<span class=\"todo\">#TODO " + text + "</span></br>");
		
		container.getTexts().add(htmlCode);
		
		return htmlCode;
	}

	/**
	 * Creates a new line and adds it to the given container.
	 * 
	 * @param container the container to add the new line to
	 * @param text the text the line must contain
	 * @return the created line
	 */
	public Line createLine(TextContainer container, String text) {
		Line line = DOCUMENTATION_FACTORY.createLine();
		line.setText(text);
		container.getTexts().add(line);
		return line;
	}

	/**
	 * Creates a new paragraph and adds the given text as line to the paragraph.
	 * 
	 * @param text the text the paragraph must contain
	 * @return the created paragraph
	 */
	public Paragraph createParagraph(String text) {
		Paragraph paragraph = DOCUMENTATION_FACTORY.createParagraph();
		createLine(paragraph, text);
		return paragraph;
	}

	/**
	 * Creates a new list item with the given text and adds it to the specified
	 * list.
	 * 
	 * @param list the list to extend with the new item
	 * @param text the text of the new item
	 * @return the newly create list item
	 */
	public ListItem createListItem(
			de.devboost.natspec.library.documentation.List list, String text) {
		
		ListItem item = DOCUMENTATION_FACTORY.createListItem();
		item.setText(text);

		List<ListItem> items = list.getItems();
		items.add(item);
		return item;
	}

	public XML createXML(FragmentContainer container, String path,
			String className, String name) {
		
		XML xml = DOCUMENTATION_FACTORY.createXML();
		xml.setName(name);
		xml.setResource(path);
		xml.setContextClassName(className);
		
		container.getFragments().add(xml);
		
		return xml;
	}
}
