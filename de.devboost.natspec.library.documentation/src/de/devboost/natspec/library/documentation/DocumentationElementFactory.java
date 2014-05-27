package de.devboost.natspec.library.documentation;

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
}
