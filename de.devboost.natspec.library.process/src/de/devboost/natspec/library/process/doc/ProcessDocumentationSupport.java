package de.devboost.natspec.library.process.doc;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;

import de.devboost.natspec.annotations.TextSyntax;
import de.devboost.natspec.library.documentation.DocumentationFactory;
import de.devboost.natspec.library.documentation.DocumentationGenerator;
import de.devboost.natspec.library.documentation.Fragment;
import de.devboost.natspec.library.documentation.Line;
import de.devboost.natspec.library.documentation.Paragraph;
import de.devboost.natspec.library.documentation.Table;
import de.devboost.natspec.library.documentation.TableHeader;
import de.devboost.natspec.library.documentation.TableRow;
import de.devboost.natspec.library.documentation.TextFragmentContainer;
import de.devboost.natspec.process.processes.BusinessProcess;
import de.devboost.natspec.process.processes.Input;
import de.devboost.natspec.process.processes.Output;
import de.devboost.natspec.process.processes.Step;

public class ProcessDocumentationSupport {

	@TextSyntax("Include Process Documentation Table for Process #1")
	public Fragment includeFunctionListInDocumentation(List<String> listName,
			TextFragmentContainer container) throws IOException {
		String fragment = new DocumentationGenerator()
				.getDocumentationFragmentContents(StringUtils.join(listName,
						" "));
		Line line = DocumentationFactory.eINSTANCE.createLine();
		line.setText(fragment);
		container.getFragments().add(line);
		return line;

	}

	@TextSyntax("Create Process Documentation Table")
	public void saveFunctionListForDocumentation(BusinessProcess process)
			throws Exception {
		DocumentationGenerator generator = new DocumentationGenerator();
		DocumentationFactory factory = DocumentationFactory.eINSTANCE;
		Paragraph paragraph = factory.createParagraph();
		Table table = factory.createTable();
		paragraph.getFragments().add(table);

		TableHeader tableHeader = factory.createTableHeader();
		table.setTableHeader(tableHeader);
		tableHeader.getHeaderCells().add("<b>Process:</b>");
		tableHeader.getHeaderCells().add(
				process.getName() + " (Stakeholder: "
						+ StringUtils.join(process.getRoles(), " ") + ")");

		EList<Step> steps = process.getSteps();
		for (Step step : steps) {
			TableRow stepRow = factory.createTableRow();
			table.getTableRows().add(stepRow);
			stepRow.getRowCells().add("<b>Step</b>");
			stepRow.getRowCells().add(step.getName());

			stepRow = factory.createTableRow();
			table.getTableRows().add(stepRow);
			stepRow.getRowCells().add("&nbsp;&nbsp;&nbsp;&nbsp;Input");
			LinkedList<String> inputsString = new LinkedList<String>();
			EList<Input> inputs = step.getInputs();
			for (Input input : inputs) {
				String inputName = input.getName();
				if (input.isExternal()) {
					inputName = "<b>external</b> " + inputName;
				}
				if (input.isOptional()) {
					inputName = "[" + inputName + "]";
				}
				inputsString.add(inputName);
			}
			stepRow.getRowCells().add(StringUtils.join(inputsString, "</br>"));

			stepRow = factory.createTableRow();
			table.getTableRows().add(stepRow);
			stepRow.getRowCells().add("&nbsp;&nbsp;&nbsp;&nbsp;Rationale");
			stepRow.getRowCells().add(step.getRationale());

			stepRow = factory.createTableRow();
			table.getTableRows().add(stepRow);
			stepRow.getRowCells().add("&nbsp;&nbsp;&nbsp;&nbsp;Output");
			LinkedList<String> outputsString = new LinkedList<String>();
			EList<Output> outputs = step.getOutputs();
			for (Output output : outputs) {
				String outputName = output.getName();
				outputsString.add(outputName);
			}
			stepRow.getRowCells().add(StringUtils.join(outputsString, "</br>"));

		}
		generator.saveFragmentToFile(paragraph, process.getName());
	}
}
