package de.devboost.natspec.library.documentation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EObject;

import de.devboost.natspec.annotations.TextSyntax;

public class DocumentationSupport {

	private boolean withinTable;
	private Documentation documentation;
	private DocumentationFactory factory;

	public DocumentationSupport(Class<?> testClass) {
		factory = DocumentationFactory.eINSTANCE;
	}

	public Documentation getDocumentation() {
		return this.documentation;
	}

	public String flattenList(List<String> name) {
		String result = "";
		for (String string : name) {
			result += string + " ";
		}
		return result;
	}

	@TextSyntax("Documentation - #1")
	public Documentation initDocumentation(List<String> name) {
		documentation = factory.createDocumentation();
		documentation.setTitle(flattenList(name));
		return documentation;
	}

	@TextSyntax("Section - #1")
	public Section addSection(List<String> name, Documentation d) {
		Section section = factory.createSection();
		section.setName(flattenList(name));
		d.getSections().add(section);
		return section;
	}

	@TextSyntax("Subsection - #1")
	public Subsection addSubsection(List<String> name, Section section) {
		Subsection subsection = factory.createSubsection();
		String subsectionName = flattenList(name);
		subsection.setName(subsectionName);
		section.getFragments().add(subsection);
		return subsection;
	}

	@TextSyntax("Subsubsection - #1")
	public Subsubsection addSubsubsection(List<String> name, Subsection s) {
		Subsubsection subsubsection = factory.createSubsubsection();
		String subsubsectionName = flattenList(name);
		subsubsection.setName(subsubsectionName);
		s.getFragments().add(subsubsection);
		return subsubsection;

	}

	@TextSyntax("#1")
	public Line createPlainContents(List<String> fullSentence,
			TextFragmentContainer container) {
		String text = flattenList(fullSentence);
		return addLine(container, text);
	}

	private Line addLine(TextFragmentContainer container, String text) {
		Line line = factory.createLine();
		line.setText(text);
		container.getFragments().add(line);
		return line;
	}

	@TextSyntax("|---- #1 ----|")
	public Table createOrEndTable(List<String> tableDescription,
			TextFragmentContainer container) {
		if (!withinTable) {
			withinTable = true;
			Table table = factory.createTable();
			container.getFragments().add(table);
			return table;
		} else {
			withinTable = false;
			return null;
		}
	}

	@TextSyntax("|- #1 -|")
	public void createTableHeader(List<String> headerContents, Table table) {
		List<String> removeSeparators = removeSeparators(headerContents, "-|-");
		TableHeader tableHeader = factory.createTableHeader();
		table.setTableHeader(tableHeader);
		for (String cell : removeSeparators) {
			tableHeader.getHeaderCells().add(cell);
		}
	}

	@TextSyntax("| #1 |")
	public void createTableRow(List<String> rowContents, Table table) {
		List<String> removeSeparators = removeSeparators(rowContents, "|");
		TableRow tableRow = factory.createTableRow();
		table.getTableRows().add(tableRow);
		for (String cell : removeSeparators) {
			tableRow.getRowCells().add(cell);
		}
	}

	@TextSyntax("#todo #1")
	public Line createTodo(List<String> fullSentence,
			TextFragmentContainer container) {
		Line line = factory.createLine();
		line.setText("<span class=\"todo\">#TODO " + 
				flattenList(fullSentence) + "</span></br>");
		container.getFragments().add(line);
		return line;
	}

	private List<String> removeSeparators(List<String> rowContents,
			String separator) {
		List<String> filtered = new LinkedList<String>();
		String compound = "";
		for (String content : rowContents) {
			if (content.equals(separator)) {
				filtered.add(compound.trim());
				compound = "";
				continue;
			}
			compound += " " + content;
		}
		if (!compound.isEmpty()) {
			filtered.add(compound);
		}
		return filtered;
	}

	@TextSyntax("Paragraph #1")
	public Paragraph createParagraphWithHeading(List<String> heading,
			TextFragmentContainer container) {
		Paragraph paragraph = factory.createParagraph();
		container = locateProperContainer(container);
		container.getFragments().add(paragraph);
		if (!heading.isEmpty()) {
			Line headingLine = factory.createLine();
			headingLine.setText("<strong>" + StringUtils.join(heading, " ")
					+ " </strong>");
			paragraph.getFragments().add(headingLine);
		}
		return paragraph;
	}

	private TextFragmentContainer locateProperContainer(
			TextFragmentContainer container) {
		while (container instanceof ListItem) {
			EObject parentContainer = container.eContainer();
			while (parentContainer != null
					&& !(parentContainer instanceof TextFragmentContainer)) {
				parentContainer = parentContainer.eContainer();
			}
			if (parentContainer instanceof TextFragmentContainer) {
				container = (TextFragmentContainer) parentContainer;
			} else {
				break;
			}
		}
		return container;
	}

	@TextSyntax("List")
	public de.devboost.natspec.library.documentation.List addList(
			TextFragmentContainer container) {
		de.devboost.natspec.library.documentation.List list = factory
				.createList();
		container.getFragments().add(list);
		return list;
	}

	@TextSyntax("* #1")
	public ListItem addListItem(List<String> item,
			de.devboost.natspec.library.documentation.List list) {
		ListItem listItem = factory.createListItem();
		list.getItems().add(listItem);
		listItem.setText(flattenList(item));
		return listItem;
	}

	@TextSyntax("\\* #1")
	public ListItem continueListItem(List<String> item,
			ListItem listItem) {
		listItem.setText(listItem.getText() + flattenList(item));
		return listItem;
	}

	@TextSyntax("Image of #1 at #2")
	public Image image(List<String> name, String externalPath,
			TextFragmentContainer container) {
		Image image = factory.createImage();
		container.getFragments().add(image);
		image.setName(StringUtils.join(name, " "));
		image.setOriginalSource(externalPath);
		return image;
	}

	@TextSyntax("Image of #1 at #2 width #3 %")
	public Image image(List<String> name, String externalPath,
			String widthPercent, TextFragmentContainer container) {
		Image image = image(name, externalPath, container);
		try {
			image.setWidth(Integer.parseInt(widthPercent));
		} catch (NumberFormatException e) {
		}
		return image;
	}

	private String insertCamelCaseWhitespaces(String ccs) {
		String result = "";
		String[] individualWords = ccs
				.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])");

		for (String word : individualWords) {
			result += " " + word;
		}
		return result;
	}

	@TextSyntax("Story - #1")
	public void addStory(String path, TextFragmentContainer c) throws Exception {
		addNatSpecFile(path, c, "Story", true);
	}

	private void addNatSpecFile(String path, TextFragmentContainer c,
			String contentKind, boolean showLineNumbers)
			throws FileNotFoundException, IOException {
		File f = new File(path);
		if (f.exists()) {
			Line l = factory.createLine();
			c.getFragments().add(l);
			String nameWithoutExtension = f.getName().substring(0,
					f.getName().lastIndexOf('.'));
			l.setText("<h3 class =\"scenario\">" + contentKind + ": "
					+ insertCamelCaseWhitespaces(nameWithoutExtension)
					+ "</h3>");
			FileInputStream inputStream = new FileInputStream(f);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					inputStream));

			String line;
			int lineNumber = 1;
			// TODO handle comments as plain documentation
			List<String> codeFragments = new LinkedList<String>();
			String codeFragment = "";
			while ((line = br.readLine()) != null) {
				if (isComment(line)) {
					if (!codeFragment.isEmpty()) {
						codeFragments.add(codeFragment);
						codeFragment = "";
					}
					codeFragments.add(line);
				} else {

					if (showLineNumbers) {
						codeFragment += "<span class=\"linenumber\">"
								+ lineNumber
								+ "</span><span class=\"codeline\">" + line
								+ "&nbsp;</span>\n";
						lineNumber++;
					} else {
						codeFragment += "<span class=\"codeline\">" + line
								+ "&nbsp;</span>\n";

					}
				}
			}

			br.close();

			if (!codeFragment.isEmpty()) {
				codeFragments.add(codeFragment);
			}
			for (String fragment : codeFragments) {
				Line contents = factory.createLine();
				c.getFragments().add(contents);
				if (isComment(fragment)) {
					contents.setText(fragment.substring(2));
				} else {
					contents.setText("<div class=\"code\"><code class=\"natspec_code\">\n"
							+ fragment + "\n</code></div>\n");
				}
			}

		} else {
			System.out.println("Can't find " + contentKind + " at: "
					+ f.getAbsolutePath());
		}
	}

	private boolean isComment(String line) {
		return line.trim().startsWith("//");
	}

	@TextSyntax("Rules - #1")
	public void addRules(String path, TextFragmentContainer c) throws Exception {
		addNatSpecFile(path, c, "Rules", false);
	}

	@TextSyntax("Define #1 : #2")
	public void addTerminoligyEntry(List<String> entryName,
			List<String> entryDescription, Documentation documentation) {
		TermEntry termEntry = factory.createTermEntry();
		termEntry.setName(StringUtils.join(entryName, " "));
		termEntry.setDescription(StringUtils.join(entryDescription, " "));
		documentation.getTerminology().add(termEntry);
	}

	@TextSyntax("Author #1")
	public void addAuthor(List<String> authors, TextFragmentContainer container) {
		addLine(container,
				"<div class=\"author_tag\">" + StringUtils.join(authors, " ")
						+ "</div>");
	}

	@TextSyntax("XML of #1 from resource #2 at #3")
	public void codeFromFile(List<String> name, String path, String className,
			TextFragmentContainer container) {
		
		XML xml = factory.createXML();
		container.getFragments().add(xml);
		xml.setName(StringUtils.join(name, " "));
		xml.setResource(path);
		xml.setContextClassName(className);
	}
}
