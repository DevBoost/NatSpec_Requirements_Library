package de.devboost.natspec.library.features.doc;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;

import de.devboost.natspec.annotations.TextSyntax;
import de.devboost.natspec.library.documentation.DocumentationFactory;
import de.devboost.natspec.library.documentation.DocumentationGenerator;
import de.devboost.natspec.library.documentation.Fragment;
import de.devboost.natspec.library.documentation.FragmentContainer;
import de.devboost.natspec.library.documentation.Line;
import de.devboost.natspec.library.documentation.ListItem;
import de.devboost.natspec.library.documentation.Paragraph;
import de.devboost.natspec.library.documentation.TextContainer;
import de.devboost.natspec.library.function.Component;
import de.devboost.natspec.library.function.Function;
import de.devboost.natspec.library.function.FunctionGroup;
import de.devboost.natspec.library.function.FunctionList;

public class FunctionDocumentationSupport {

	@TextSyntax("Include Function List Documentation for List #1")
	public Fragment includeFunctionListInDocumentation(List<String> listName,
			TextContainer container) throws IOException {
		String fragment = new DocumentationGenerator()
				.getDocumentationFragmentContents(StringUtils.join(
						listName, " "));
		Line line = DocumentationFactory.eINSTANCE.createLine();
		line.setText(fragment);
		container.getTexts().add(line);
		return line;

	}

	@TextSyntax("Create Function List Documentation")
	public void saveFunctionListForDocumentation(FunctionList list)
			throws Exception {
		DocumentationGenerator generator = new DocumentationGenerator();
		DocumentationFactory factory = DocumentationFactory.eINSTANCE;
		Paragraph paragraph = factory.createParagraph();
		EList<Component> components = list.getComponents();
		for (Component component : components) {
			Line componentHeader = factory.createLine();
			componentHeader.setText("<h5>Functions for Compontent: "
					+ component.getDescription() + "</h5>");
			paragraph.getTexts().add(componentHeader);

			EList<FunctionGroup> functionGroups = component.getFunctionGroups();
			for (FunctionGroup functionGroup : functionGroups) {
				Line groupItem = factory.createLine();
				paragraph.getTexts().add(groupItem);
				
				groupItem.setText("<strong>" + functionGroup.getDescription() + " (" + functionGroup.getAbbrev()+")"
						+ "</strong></br>");
				EList<Function> functions = functionGroup.getFunctions();
				de.devboost.natspec.library.documentation.List features = factory
						.createList();
				((FragmentContainer) paragraph.eContainer()).getFragments().add(features);
				
				int functionCounter = 0;
				for (Function function : functions) {
					functionCounter++;
					ListItem featureItem = factory.createListItem();
					String storyRefString = StringUtils.join(function.getStoryRefs(), ", ");
					featureItem.setText("//" + functionGroup.getAbbrev() + " "+ functionCounter*10 + "// -  " + function.getDescription()
							+ " ("+storyRefString + ") </br>");
					features.getItems().add(featureItem);
				}
			}
		}
		generator.saveFragmentToFile(paragraph, list.getDescription());
	}
}
