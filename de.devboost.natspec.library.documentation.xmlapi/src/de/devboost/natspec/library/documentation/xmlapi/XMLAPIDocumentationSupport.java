package de.devboost.natspec.library.documentation.xmlapi;

import java.util.ArrayList;
import java.util.List;

import de.devboost.natspec.annotations.TextSyntax;
import de.devboost.natspec.library.documentation.DocumentationSupport;
import de.devboost.natspec.library.documentation.TextFragmentContainer;

public class XMLAPIDocumentationSupport {

	public static final String API_LOCATION = "{API_LOCATION}";
	
	private DocumentationSupport documentationSupport;
	
	public XMLAPIDocumentationSupport(DocumentationSupport documentationSupport) {
		this.documentationSupport = documentationSupport;
	}

	@TextSyntax("Insert documentation placeholder: API_LOCATION")
	public void insertAPILocation(TextFragmentContainer container) {
		insertPlaceholder(container, API_LOCATION);
	}

	public void insertPlaceholder(TextFragmentContainer container, String placeholder) {
		List<String> content = new ArrayList<String>();
		content.add(placeholder);
		documentationSupport.createPlainContents(content, container);
	}
}
