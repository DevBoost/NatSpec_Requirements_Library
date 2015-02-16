package de.devboost.natspec.library.components;

import de.devboost.natspec.library.components.doc.ComponentsDocumentationSupport;


public class exampleComponents {

	ComponentModellingSupport componentModellingSupport;
	ComponentsDocumentationSupport componentsDocumentationSupport;

	public exampleComponents() {

		componentModellingSupport = new ComponentModellingSupport();
		componentsDocumentationSupport = new ComponentsDocumentationSupport();
	}

	public void createModel() throws Exception {
		// The code in this method is generated from: /de.devboost.natspec.library.documentation.example/src/de/devboost/natspec/library/components/exampleComponents.natspec
		// Never change this method or any contents of this file, all local changes will be overwritten.
		// Change _NatSpecTemplate.java instead.
		
		// Container Example Components
		de.devboost.natspec.library.components.components.ComponentContainer componentContainer_Example_Components = componentModellingSupport.createContainerComponent(java.util.Arrays.asList(new java.lang.String[] {"Example", "Components"}));
		// Component ExampleComponent One
		de.devboost.natspec.library.components.components.Component component_ExampleComponent_One = componentModellingSupport.createSubComponent(java.util.Arrays.asList(new java.lang.String[] {"ExampleComponent", "One"}), componentContainer_Example_Components);
		// Description: First exemplary component
		componentModellingSupport.createComponentDescription(java.util.Arrays.asList(new java.lang.String[] {"First", "exemplary", "component"}), component_ExampleComponent_One);
		// Uses ExampleComponent Two
		componentModellingSupport.createUseRelation(java.util.Arrays.asList(new java.lang.String[] {"ExampleComponent", "Two"}), component_ExampleComponent_One, componentContainer_Example_Components);
		// Component ExampleComponent Two
		de.devboost.natspec.library.components.components.Component component_ExampleComponent_Two = componentModellingSupport.createSubComponent(java.util.Arrays.asList(new java.lang.String[] {"ExampleComponent", "Two"}), componentContainer_Example_Components);
		// Description: Second exemplary component
		componentModellingSupport.createComponentDescription(java.util.Arrays.asList(new java.lang.String[] {"Second", "exemplary", "component"}), component_ExampleComponent_Two);
		// Uses eTe IDE Core Components
		componentModellingSupport.createUseRelation(java.util.Arrays.asList(new java.lang.String[] {"eTe", "IDE", "Core", "Components"}), component_ExampleComponent_Two, componentContainer_Example_Components);
		// Create Component Documentation
		componentsDocumentationSupport.saveFunctionListForDocumentation(componentContainer_Example_Components);
		

	}

	public static void main(String[] args) throws Exception {
		new exampleComponents().createModel();
	}

	public void saveModel() {
		// does nothing yet
	}
}
