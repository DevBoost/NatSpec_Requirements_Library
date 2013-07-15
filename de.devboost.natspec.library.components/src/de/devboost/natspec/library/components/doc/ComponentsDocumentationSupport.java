package de.devboost.natspec.library.components.doc;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.EList;

import de.devboost.essentials.StringUtils;
import de.devboost.natspec.annotations.TextSyntax;
import de.devboost.natspec.library.components.components.Component;
import de.devboost.natspec.library.components.components.ComponentContainer;
import de.devboost.natspec.library.components.components.ComponentUser;
import de.devboost.natspec.library.documentation.DocumentationFactory;
import de.devboost.natspec.library.documentation.DocumentationGenerator;
import de.devboost.natspec.library.documentation.Fragment;
import de.devboost.natspec.library.documentation.Line;
import de.devboost.natspec.library.documentation.Table;
import de.devboost.natspec.library.documentation.TableHeader;
import de.devboost.natspec.library.documentation.TableRow;
import de.devboost.natspec.library.documentation.TextFragmentContainer;

public class ComponentsDocumentationSupport {

	@TextSyntax("Include Component Documentation Table from #1")
	public Fragment includeFunctionListInDocumentation(List<String> listName,
			TextFragmentContainer container) throws IOException {
		String fragment = new DocumentationGenerator()
				.getDocumentationFragmentContents(new StringUtils().explode(
						listName, " "));
		Line line = DocumentationFactory.eINSTANCE.createLine();
		line.setText(fragment);
		container.getFragments().add(line);
		return line;

	}

	@TextSyntax("Create Component Documentation")
	public void saveFunctionListForDocumentation(ComponentContainer container)
			throws Exception {
		createComponentDiagram(container);
		DocumentationGenerator generator = new DocumentationGenerator();
		DocumentationFactory factory = DocumentationFactory.eINSTANCE;
		Table table = factory.createTable();
		TableHeader tableHeader = factory.createTableHeader();
		table.setTableHeader(tableHeader);
		tableHeader.getHeaderCells().add(container.getName());
		tableHeader.getHeaderCells().add("Used by");
		tableHeader.getHeaderCells().add("Description");

		EList<Component> subComponents = container.getSubComponents();
		for (Component component : subComponents) {
			TableRow row = factory.createTableRow();
			table.getTableRows().add(row);
			row.getRowCells().add(component.getName());
			List<String> nameList = new LinkedList<>();
			EList<ComponentUser> usedBy = component.getUsedBy();
			for (ComponentUser componentUser : usedBy) {
				nameList.add(componentUser.getName());
			}
			String usersNames = new StringUtils().explode(nameList, ";</br> ") + " ";
			row.getRowCells().add(usersNames);
			row.getRowCells().add(component.getDescription());
		}

		generator.saveFragmentToFile(table, container.getName());
	}
	
	public void createComponentDiagram(ComponentContainer container) {
		new ComponentDiagramGenerator().createComponentDiagram(container);
	}
}
