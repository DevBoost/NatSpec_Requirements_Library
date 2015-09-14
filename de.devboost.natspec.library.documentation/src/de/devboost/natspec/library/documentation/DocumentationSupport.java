package de.devboost.natspec.library.documentation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EObject;

import de.devboost.natspec.annotations.Many;
import de.devboost.natspec.annotations.TextSyntax;

public class DocumentationSupport {

	private final static Pattern FIXME_COMMENT_PATTERN = Pattern.compile("^//\\s*FIXME");
	private final static Pattern TODO_COMMENT_PATTERN = Pattern.compile("^//\\s*TODO");

	private final static DocumentationFactory FACTORY = DocumentationFactory.eINSTANCE;

	private Documentation documentation;

	private boolean withinTable;

	public Documentation getDocumentation() {
		return this.documentation;
	}

	public String flattenList(List<String> name) {
		StringBuilder result = new StringBuilder();
		for (String string : name) {
			result.append(string);
			result.append(" ");
		}
		return result.toString();
	}

	@TextSyntax("Documentation - #1")
	public Documentation initDocumentation(List<String> name) {
		documentation = FACTORY.createDocumentation();
		documentation.setTitle(flattenList(name));
		return documentation;
	}

	@TextSyntax("Reference to #1 with caption #2")
	public Reference addReference(String label, List<String> caption,
			TextContainer container, Documentation documentation) {
		
		Reference reference = FACTORY.createReference();
		reference.setName(flattenList(caption));
		reference.setReferredLabel(label);

		container.getTexts().add(reference);
		return reference;
	}

	@TextSyntax("Section - #1")
	public Section addSection(List<String> name, Documentation d) {
		Section section = FACTORY.createSection();
		section.setName(flattenList(name));
		d.getSections().add(section);
		return section;
	}

	@TextSyntax("Section (#1) - #2")
	public Section addSection(String label, List<String> name, Documentation d) {
		Section section = addSection(name, d);
		section.setLabel(label);
		return section;
	}

	@TextSyntax("Subsection - #1")
	public Subsection addSubsection(List<String> name, Section section) {
		Subsection subsection = FACTORY.createSubsection();
		String subsectionName = flattenList(name);
		subsection.setName(subsectionName);
		section.getFragments().add(subsection);
		return subsection;
	}

	@TextSyntax("Subsection (#1) - #2")
	public Subsection addLabeledSubsection(String label, List<String> name,
			Section section) {
		Subsection result = addSubsection(name, section);
		result.setLabel(label);
		return result;
	}

	@TextSyntax("Subsubsection - #1")
	public Subsubsection addSubsubsection(List<String> name, Subsection s) {
		Subsubsection subsubsection = FACTORY.createSubsubsection();
		String subsubsectionName = flattenList(name);
		subsubsection.setName(subsubsectionName);
		s.getFragments().add(subsubsection);
		return subsubsection;

	}

	@TextSyntax("Subsubsection (#1) - #2")
	public Subsubsection addLabeledSubsubsection(String label, List<String> name, Subsection s) {
		Subsubsection subsubsection = addSubsubsection(name, s);
		subsubsection.setLabel(label);
		return subsubsection;
	}

	@TextSyntax("Insert page break")
	public void addPageBreak(FragmentContainer container) {
		PageBreak fragment = FACTORY.createPageBreak();
		container.getFragments().add(fragment);
	}

	@TextSyntax("#1")
	public Line createPlainContents(List<String> fullSentence,
			TextContainer container) {
		String text = flattenList(fullSentence);
		if (text.startsWith("###")) {
			text = "///" + text.substring(3);
		} else if (text.startsWith("##")) {
			text = "//" + text.substring(2);
		}
		return addLine(container, text);
	}

	private Line addLine(TextContainer container, String text) {
		Line line = FACTORY.createLine();
		line.setText(text);
		if (container instanceof FragmentContainer) {
			FragmentContainer fragmentContainer = (FragmentContainer) container;
			fragmentContainer.getFragments().add(line);
		} else {
			container.getTexts().add(line);
		}
		return line;
	}

	@TextSyntax("|---- #1 ----|")
	public Table createOrEndTable(List<String> tableDescription,
			FragmentContainer container) {
		if (!withinTable) {
			withinTable = true;
			Table table = FACTORY.createTable();
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
		TableHeader tableHeader = FACTORY.createTableHeader();
		table.setTableHeader(tableHeader);
		for (String cellValue : removeSeparators) {
			TableCell cell = FACTORY.createTableCell();
			cell.setContent(cellValue);
			tableHeader.getHeaderCells().add(cell );
		}
	}

	@TextSyntax("| #1 |")
	public void createTableRow(List<String> rowContents, Table table) {
		List<String> removeSeparators = removeSeparators(rowContents, "|");
		TableRow tableRow = FACTORY.createTableRow();
		table.getTableRows().add(tableRow);
		for (String cellValue : removeSeparators) {
			TableCell cell = FACTORY.createTableCell();
			cell.setContent(cellValue);
			tableRow.getRowCells().add(cell);
		}
	}

	@TextSyntax("#todo #1")
	public HtmlCode createTodo(List<String> fullSentence,
			TextContainer container) {
		
		String text = flattenList(fullSentence);
		return DocumentationElementFactory.INSTANCE.createTodo(text, container);
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
	public Paragraph createParagraphWithHeading(List<String> heading, FragmentContainer container) {
		if (container instanceof Listing) {
			Listing listing = (Listing) container;
			EObject parent = listing.eContainer();
			if (parent instanceof FragmentContainer) {
				container = (FragmentContainer) parent;
			}
		}

		Paragraph paragraph = FACTORY.createParagraph();
		container.getFragments().add(paragraph);
		if (!heading.isEmpty()) {
			HtmlCode headingLine = FACTORY.createHtmlCode();
			headingLine.setText("<strong>" + StringUtils.join(heading, " ") + " </strong>");
			paragraph.getTexts().add(headingLine);
		}
		
		return paragraph;
	}

	@TextSyntax("List")
	public de.devboost.natspec.library.documentation.List addList(
			FragmentContainer container) {
		de.devboost.natspec.library.documentation.List list = FACTORY
				.createList();
		container.getFragments().add(list);
		return list;
	}

	@TextSyntax("* #1")
	public ListItem addListItem(List<String> item,
			de.devboost.natspec.library.documentation.List list) {
		ListItem listItem = FACTORY.createListItem();
		list.getItems().add(listItem);
		listItem.setText(flattenList(item));
		return listItem;
	}

	@TextSyntax("\\* #1")
	public ListItem continueListItem(List<String> item, ListItem listItem) {
		listItem.setText(listItem.getText() + flattenList(item));
		return listItem;
	}

	@TextSyntax("Image of #1 at #2")
	public Image image(List<String> name, String externalPath,
			FragmentContainer container) {
		Image image = FACTORY.createImage();
		container.getFragments().add(image);
		image.setName(StringUtils.join(name, " "));
		image.setOriginalSource(externalPath);
		return image;
	}

	@TextSyntax("Image of #1 at #2 width #3 #4")
	public Image image(List<String> name, String externalPath,
			String widthPercent, String stringUnit,
			FragmentContainer container) {
		Image image = image(name, externalPath, container);
		try {
			Width width = FACTORY.createWidth();
			width.setWidth(Integer.parseInt(widthPercent));
			Unit unit = Unit.get(stringUnit);
			width.setUnit(unit);
			image.setWidth(width);
		} catch (NumberFormatException e) {
			// let test fail
		}
		return image;
	}

	private String insertCamelCaseWhitespaces(String ccs) {
		String[] individualWords = ccs.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])");

		StringBuilder result = new StringBuilder();
		for (String word : individualWords) {
			result.append(" ");
			result.append(word);
		}
		
		return result.toString();
	}

	@TextSyntax("Story - #1")
	public void addStory(String path, FragmentContainer c) throws Exception {
		addNatSpecFile(path, c, "Story", true, false);
	}
	
	@TextSyntax("StorySubsection - #1")
	public void addSubsectionStory(@Many String path, Section section) throws Exception {
		addNatSpecFile(path, section, "Story", true, true);
	}

	private void addNatSpecFile(String path, FragmentContainer c,
			String contentKind, boolean showLineNumbers, boolean createSubSection)
			throws FileNotFoundException, IOException {
		File f = new File(path);
		if (f.exists()) {
			String nameWithoutExtension = f.getName().substring(0, f.getName().lastIndexOf('.'));
			String nameWithCamelCaseWhitespaces = insertCamelCaseWhitespaces(nameWithoutExtension);
			
			if (createSubSection && c instanceof Section) {
				Section section = (Section) c;
				Subsection subsection = FACTORY.createSubsection();
				subsection.setName(contentKind + ": " + nameWithCamelCaseWhitespaces);
				section.getFragments().add(subsection);	
			} else {
				HtmlCode code = FACTORY.createHtmlCode();
				c.getFragments().add(code);
				code.setText("<h3 class =\"scenario\">" + contentKind + ": "
						+ nameWithCamelCaseWhitespaces
						+ "</h3>");
			}
			
			List<String> codeFragments = new ArrayList<String>();
			List<String> codeFragmentParts = new ArrayList<String>();
			List<String> lines = readLinesFromFile(f);
			
			for (int idx = 0; idx < lines.size(); ++idx) {
				String currentLine = lines.get(idx);
				String nextLine = null;
				if (idx < lines.size() - 1) {
					nextLine = lines.get(idx + 1);
				}
				
				// We skip FIXMEs and TODOs
				if (isFIXMEComment(currentLine) || isTODOComment(currentLine)) {
					continue;
				}
				
				// We skip empty lines that are at the beginning of a new fragment block
				if (codeFragmentParts.isEmpty() && currentLine.isEmpty()) {
					continue;
				}
				
				// We skip subsequent empty lines or empty lines at the end of the fragment block
				if ((nextLine == null || nextLine.isEmpty() || isComment(nextLine)) && currentLine.isEmpty()) {
					continue;
				}
				
				if (isComment(currentLine)) {
					if (!codeFragmentParts.isEmpty()) {
						String codeFragment = StringUtils.join(codeFragmentParts, ' ');
						codeFragments.add(codeFragment);
						codeFragmentParts.clear();
					}
					codeFragments.add(currentLine);
					continue;
				}
				
				if (showLineNumbers) {
					String codeFragment = "<span class=\"linenumber\">"
							+ (idx + 1)
							+ "</span><span class=\"codeline\">" + currentLine
							+ "&nbsp;</span>\n";
					codeFragmentParts.add(codeFragment);
				} else {
					String codeFragment = "<span class=\"codeline\">" + currentLine
							+ "&nbsp;</span>\n";
					codeFragmentParts.add(codeFragment);
				}
			}

			if (!codeFragmentParts.isEmpty()) {
				String codeFragment = StringUtils.join(codeFragmentParts, ' ');
				codeFragments.add(codeFragment);
			}
			for (String fragment : codeFragments) {
				HtmlCode contents = FACTORY.createHtmlCode();
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

	private List<String> readLinesFromFile(File file) throws IOException {
		List<String> lines = new ArrayList<String>();
		FileInputStream inputStream = new FileInputStream(file);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			lines.add(line);
		}
		bufferedReader.close();
		
		return lines;
	}

	private boolean isComment(String line) {
		return line.trim().startsWith("//");
	}
	
	private boolean isFIXMEComment(String line) {
		return FIXME_COMMENT_PATTERN.matcher(line.trim()).matches();
	}
	
	private boolean isTODOComment(String line) {
		return TODO_COMMENT_PATTERN.matcher(line.trim()).matches();
	}

	@TextSyntax("Rules - #1")
	public void addRules(String path, FragmentContainer c) throws Exception {
		addNatSpecFile(path, c, "Rules", false, false);
	}

	@TextSyntax("Define #1 : #2")
	public void addTerminoligyEntry(List<String> entryName,
			List<String> entryDescription, Documentation documentation) {
		TermEntry termEntry = FACTORY.createTermEntry();
		termEntry.setName(StringUtils.join(entryName, " "));
		termEntry.setDescription(StringUtils.join(entryDescription, " "));
		documentation.getTerminology().add(termEntry);
	}

	@TextSyntax("Author #1")
	public void addAuthor(List<String> authors, FragmentContainer container) {
		HtmlCode html = FACTORY.createHtmlCode();
		html.setText("<div class=\"author_tag\">" + StringUtils.join(authors, " ")
				+ "</div>");
		container.getFragments().add(html);
	}

	@TextSyntax("XML of #1 from resource #2 at #3")
	public void codeFromFile(List<String> nameParts, String path, String className,
			FragmentContainer container) {

		String name = StringUtils.join(nameParts, " ");
		DocumentationElementFactory.INSTANCE.createXML(container, path,
				className, name);
	}

	@TextSyntax("Listing")
	public Listing beginListing(FragmentContainer container) {
		Listing createListing = FACTORY.createListing();
		container.getFragments().add(createListing);
		return createListing;
	}

	@TextSyntax("Code #1")
	public Code code(@Many String text, TextContainer container) {
		Code code = FACTORY.createCode();
		code.setText(text);
		if (container instanceof FragmentContainer) {
			FragmentContainer fragmentContainer = (FragmentContainer) container;
			fragmentContainer.getFragments().add(code);
		} else {
			container.getTexts().add(code);
		}
		return code;
	}

	@TextSyntax("Link to #1")
	public Link link(String uri, FragmentContainer container) {
		Link link = FACTORY.createLink();
		container.getFragments().add(link);
		link.setName(uri);
		link.setUri(uri);
		return link;
	}

	@TextSyntax("Link to #2 with caption #1")
	public Link link(List<String> caption, String uri,
			FragmentContainer container) {
		Link link = link(uri, container);
		link.setName(StringUtils.join(caption, " "));
		return link;
	}
}
