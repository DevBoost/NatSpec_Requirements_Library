package de.devboost.natspec.library.documentation;

import de.devboost.natspec.library.components.doc.ComponentsDocumentationSupport;
import de.devboost.natspec.library.features.doc.FunctionDocumentationSupport;
import de.devboost.natspec.library.process.doc.ProcessDocumentationSupport;


public class _NatSpecTemplate {

	private DocumentationSupport documentationSupport = new DocumentationSupport(
			this.getClass());

	protected FunctionDocumentationSupport functionDocumentationSupport = new FunctionDocumentationSupport();
	protected ComponentsDocumentationSupport componentsDocumentationSupport = new ComponentsDocumentationSupport();
	protected ProcessDocumentationSupport processDocumentationSupport = new ProcessDocumentationSupport();

	public static void main(String[] args) throws Exception {
		new _NatSpecTemplate().saveDocumentation();
	}

	public void saveDocumentation() throws Exception {
		/* @MethodBody */
		
		Documentation documentation = documentationSupport.getDocumentation();
		DocumentationGenerator generator = new DocumentationGenerator();
		generator.saveDocumentationToFile(documentation);
	
	}
}
