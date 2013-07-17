package de.devboost.natspec.library.components;

import de.devboost.natspec.library.components.doc.ComponentsDocumentationSupport;


public class _NatSpecTemplate {

	ComponentModellingSupport componentModellingSupport;
	ComponentsDocumentationSupport componentsDocumentationSupport;

	public _NatSpecTemplate() {

		componentModellingSupport = new ComponentModellingSupport();
		componentsDocumentationSupport = new ComponentsDocumentationSupport();
	}

	public void createModel() throws Exception {
		/* @MethodBody */

	}

	public static void main(String[] args) throws Exception {
		new _NatSpecTemplate().createModel();
	}

	public void saveModel() {
		// does nothing yet
	}
}
