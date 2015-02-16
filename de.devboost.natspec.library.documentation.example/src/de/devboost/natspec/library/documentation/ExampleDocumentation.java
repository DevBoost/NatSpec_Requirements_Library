package de.devboost.natspec.library.documentation;

import de.devboost.natspec.library.components.doc.ComponentsDocumentationSupport;
import de.devboost.natspec.library.features.doc.FunctionDocumentationSupport;
import de.devboost.natspec.library.process.doc.ProcessDocumentationSupport;


public class ExampleDocumentation {

	private DocumentationSupport documentationSupport = new DocumentationSupport(
			this.getClass());

	protected FunctionDocumentationSupport functionDocumentationSupport = new FunctionDocumentationSupport();
	protected ComponentsDocumentationSupport componentsDocumentationSupport = new ComponentsDocumentationSupport();
	protected ProcessDocumentationSupport processDocumentationSupport = new ProcessDocumentationSupport();

	public static void main(String[] args) throws Exception {
		new ExampleDocumentation().saveDocumentation();
	}

	public void saveDocumentation() throws Exception {
		// The code in this method is generated from: /de.devboost.natspec.library.documentation.example/src/de/devboost/natspec/library/documentation/ExampleDocumentation.natspec
		// Never change this method or any contents of this file, all local changes will be overwritten.
		// Change _NatSpecTemplate.java instead.
		
		// Documentation - Arch42 Documentation an Example System
		de.devboost.natspec.library.documentation.Documentation documentation_Arch42_Documentation_an_Example_System = documentationSupport.initDocumentation(java.util.Arrays.asList(new java.lang.String[] {"Arch42", "Documentation", "an", "Example", "System"}));
		// Section - Meta Data
		de.devboost.natspec.library.documentation.Section section_Meta_Data = documentationSupport.addSection(java.util.Arrays.asList(new java.lang.String[] {"Meta", "Data"}), documentation_Arch42_Documentation_an_Example_System);
		// Subsection - Authors
		de.devboost.natspec.library.documentation.Subsection subsection_Authors = documentationSupport.addSubsection(java.util.Arrays.asList(new java.lang.String[] {"Authors"}), section_Meta_Data);
		// |---- --------------------------------------------------------------------------------- ----|
		de.devboost.natspec.library.documentation.Table table__________________________________________________________________________________ = documentationSupport.createOrEndTable(java.util.Arrays.asList(new java.lang.String[] {"---------------------------------------------------------------------------------"}), subsection_Authors);
		// |- Author -|-  Affiliation -|- E-Mail  -|
		documentationSupport.createTableHeader(java.util.Arrays.asList(new java.lang.String[] {"Author", "-|-", "", "Affiliation", "-|-", "E-Mail", ""}), table__________________________________________________________________________________);
		// | Who | Example Company | firstname.lastname@company.com |
		documentationSupport.createTableRow(java.util.Arrays.asList(new java.lang.String[] {"Who", "|", "Example", "Company", "|", "firstname.lastname@company.com"}), table__________________________________________________________________________________);
		// |---- --------------------------------------------------------------------------------- ----|
		de.devboost.natspec.library.documentation.Table table__________________________________________________________________________________0 = documentationSupport.createOrEndTable(java.util.Arrays.asList(new java.lang.String[] {"---------------------------------------------------------------------------------"}), subsection_Authors);
		// Subsection - Revisions
		de.devboost.natspec.library.documentation.Subsection subsection_Revisions = documentationSupport.addSubsection(java.util.Arrays.asList(new java.lang.String[] {"Revisions"}), section_Meta_Data);
		// |---- ---------------------------------------------------------------------- ----|
		de.devboost.natspec.library.documentation.Table table_______________________________________________________________________ = documentationSupport.createOrEndTable(java.util.Arrays.asList(new java.lang.String[] {"----------------------------------------------------------------------"}), subsection_Revisions);
		// |- Date -|- Revision -|- Author -|- Changes  -|
		documentationSupport.createTableHeader(java.util.Arrays.asList(new java.lang.String[] {"Date", "-|-", "Revision", "-|-", "Author", "-|-", "Changes", ""}), table_______________________________________________________________________);
		// | 01.03.2013 | 0.1 | Who | first revision  |
		documentationSupport.createTableRow(java.util.Arrays.asList(new java.lang.String[] {"01.03.2013", "|", "0.1", "|", "Who", "|", "first", "revision", ""}), table_______________________________________________________________________);
		// | 10.03.2013 | 1.0 | Who | final revision |
		documentationSupport.createTableRow(java.util.Arrays.asList(new java.lang.String[] {"10.03.2013", "|", "1.0", "|", "Who", "|", "final", "revision"}), table_______________________________________________________________________);
		// |---- ---------------------------------------------------------------------- ----|
		de.devboost.natspec.library.documentation.Table table_______________________________________________________________________0 = documentationSupport.createOrEndTable(java.util.Arrays.asList(new java.lang.String[] {"----------------------------------------------------------------------"}), subsection_Revisions);
		// Section - Introduction and Goals
		de.devboost.natspec.library.documentation.Section section_Introduction_and_Goals = documentationSupport.addSection(java.util.Arrays.asList(new java.lang.String[] {"Introduction", "and", "Goals"}), documentation_Arch42_Documentation_an_Example_System);
		// Paragraph
		de.devboost.natspec.library.documentation.Paragraph paragraph_ = documentationSupport.createParagraphWithHeading(java.util.Arrays.asList(new java.lang.String[] {}), section_Introduction_and_Goals);
		// Short introduction on what system do we design here, why, for whom.
		de.devboost.natspec.library.documentation.Line line_Short_introduction_on_what_system_do_we_design_here__why__for_whom_ = documentationSupport.createPlainContents(java.util.Arrays.asList(new String[] {"Short", "introduction", "on", "what", "system", "do", "we", "design", "here,", "why,", "for", "whom."}), paragraph_);
		// Subsection - Objectives
		de.devboost.natspec.library.documentation.Subsection subsection_Objectives = documentationSupport.addSubsection(java.util.Arrays.asList(new java.lang.String[] {"Objectives"}), section_Introduction_and_Goals);
		// Overview of the core objectives for the system.
		de.devboost.natspec.library.documentation.Line line_Overview_of_the_core_objectives_for_the_system_ = documentationSupport.createPlainContents(java.util.Arrays.asList(new String[] {"Overview", "of", "the", "core", "objectives", "for", "the", "system."}), subsection_Objectives);
		// Subsection - Stakeholders
		de.devboost.natspec.library.documentation.Subsection subsection_Stakeholders = documentationSupport.addSubsection(java.util.Arrays.asList(new java.lang.String[] {"Stakeholders"}), section_Introduction_and_Goals);
		// Overview of potential users of the system (actors) and maybe other stakeholders with an impact on system design
		de.devboost.natspec.library.documentation.Line line_Overview_of_potential_users_of_the_system__actors__and_maybe_other_stakeholders_with_an_impact_on_system_design = documentationSupport.createPlainContents(java.util.Arrays.asList(new String[] {"Overview", "of", "potential", "users", "of", "the", "system", "(actors)", "and", "maybe", "other", "stakeholders", "with", "an", "impact", "on", "system", "design"}), subsection_Stakeholders);
		// Section - Constraints
		de.devboost.natspec.library.documentation.Section section_Constraints = documentationSupport.addSection(java.util.Arrays.asList(new java.lang.String[] {"Constraints"}), documentation_Arch42_Documentation_an_Example_System);
		// Subsection - Architecture Constraints
		de.devboost.natspec.library.documentation.Subsection subsection_Architecture_Constraints = documentationSupport.addSubsection(java.util.Arrays.asList(new java.lang.String[] {"Architecture", "Constraints"}), section_Constraints);
		// Core Constraints on System Architecture that directly result form application context
		de.devboost.natspec.library.documentation.Line line_Core_Constraints_on_System_Architecture_that_directly_result_form_application_context = documentationSupport.createPlainContents(java.util.Arrays.asList(new String[] {"Core", "Constraints", "on", "System", "Architecture", "that", "directly", "result", "form", "application", "context"}), subsection_Architecture_Constraints);
		// e.g., web-based architecture due to distributed users, components for mobile access, etc.
		de.devboost.natspec.library.documentation.Line line_e_g___web_based_architecture_due_to_distributed_users__components_for_mobile_access__etc_ = documentationSupport.createPlainContents(java.util.Arrays.asList(new String[] {"e.g.,", "web-based", "architecture", "due", "to", "distributed", "users,", "components", "for", "mobile", "access,", "etc."}), subsection_Architecture_Constraints);
		// Subsection - Technical Constraints
		de.devboost.natspec.library.documentation.Subsection subsection_Technical_Constraints = documentationSupport.addSubsection(java.util.Arrays.asList(new java.lang.String[] {"Technical", "Constraints"}), section_Constraints);
		// Technical constraints derived from the system context
		de.devboost.natspec.library.documentation.Line line_Technical_constraints_derived_from_the_system_context = documentationSupport.createPlainContents(java.util.Arrays.asList(new String[] {"Technical", "constraints", "derived", "from", "the", "system", "context"}), subsection_Technical_Constraints);
		// e.g. JEE system due to customer demand, hardware, software, frameworks,
		de.devboost.natspec.library.documentation.Line line_e_g__JEE_system_due_to_customer_demand__hardware__software__frameworks_ = documentationSupport.createPlainContents(java.util.Arrays.asList(new String[] {"e.g.", "JEE", "system", "due", "to", "customer", "demand,", "hardware,", "software,", "frameworks,"}), subsection_Technical_Constraints);
		// Subsection - Organizational Constraints
		de.devboost.natspec.library.documentation.Subsection subsection_Organizational_Constraints = documentationSupport.addSubsection(java.util.Arrays.asList(new java.lang.String[] {"Organizational", "Constraints"}), section_Constraints);
		// Constraints that result from organizational constraints in the project context
		de.devboost.natspec.library.documentation.Line line_Constraints_that_result_from_organizational_constraints_in_the_project_context = documentationSupport.createPlainContents(java.util.Arrays.asList(new String[] {"Constraints", "that", "result", "from", "organizational", "constraints", "in", "the", "project", "context"}), subsection_Organizational_Constraints);
		// e.g., acccess rights due to responsibilities or review processes, available resources, legislative issues
		de.devboost.natspec.library.documentation.Line line_e_g___acccess_rights_due_to_responsibilities_or_review_processes__available_resources__legislative_issues = documentationSupport.createPlainContents(java.util.Arrays.asList(new String[] {"e.g.,", "acccess", "rights", "due", "to", "responsibilities", "or", "review", "processes,", "available", "resources,", "legislative", "issues"}), subsection_Organizational_Constraints);
		// Subsection - Conventions
		de.devboost.natspec.library.documentation.Subsection subsection_Conventions = documentationSupport.addSubsection(java.util.Arrays.asList(new java.lang.String[] {"Conventions"}), section_Constraints);
		// conventions on programming, documentation, versioning, naming, building etc.
		de.devboost.natspec.library.documentation.Line line_conventions_on_programming__documentation__versioning__naming__building_etc_ = documentationSupport.createPlainContents(java.util.Arrays.asList(new String[] {"conventions", "on", "programming,", "documentation,", "versioning,", "naming,", "building", "etc."}), subsection_Conventions);
		// Section - System Scope and Context
		de.devboost.natspec.library.documentation.Section section_System_Scope_and_Context = documentationSupport.addSection(java.util.Arrays.asList(new java.lang.String[] {"System", "Scope", "and", "Context"}), documentation_Arch42_Documentation_an_Example_System);
		// Subsection - Technical Context
		de.devboost.natspec.library.documentation.Subsection subsection_Technical_Context = documentationSupport.addSubsection(java.util.Arrays.asList(new java.lang.String[] {"Technical", "Context"}), section_System_Scope_and_Context);
		// Systems in the context that are to be aligned
		de.devboost.natspec.library.documentation.Line line_Systems_in_the_context_that_are_to_be_aligned = documentationSupport.createPlainContents(java.util.Arrays.asList(new String[] {"Systems", "in", "the", "context", "that", "are", "to", "be", "aligned"}), subsection_Technical_Context);
		// Subsection - External Interfaces
		de.devboost.natspec.library.documentation.Subsection subsection_External_Interfaces = documentationSupport.addSubsection(java.util.Arrays.asList(new java.lang.String[] {"External", "Interfaces"}), section_System_Scope_and_Context);
		// Interfaces for these systems
		de.devboost.natspec.library.documentation.Line line_Interfaces_for_these_systems = documentationSupport.createPlainContents(java.util.Arrays.asList(new String[] {"Interfaces", "for", "these", "systems"}), subsection_External_Interfaces);
		// Section - Solution Strategy
		de.devboost.natspec.library.documentation.Section section_Solution_Strategy = documentationSupport.addSection(java.util.Arrays.asList(new java.lang.String[] {"Solution", "Strategy"}), documentation_Arch42_Documentation_an_Example_System);
		// Outline of basic approach to realise the system
		de.devboost.natspec.library.documentation.Line line_Outline_of_basic_approach_to_realise_the_system = documentationSupport.createPlainContents(java.util.Arrays.asList(new String[] {"Outline", "of", "basic", "approach", "to", "realise", "the", "system"}), section_Solution_Strategy);
		// Section - Components View
		de.devboost.natspec.library.documentation.Section section_Components_View = documentationSupport.addSection(java.util.Arrays.asList(new java.lang.String[] {"Components", "View"}), documentation_Arch42_Documentation_an_Example_System);
		// System components
		de.devboost.natspec.library.documentation.Line line_System_components = documentationSupport.createPlainContents(java.util.Arrays.asList(new String[] {"System", "components"}), section_Components_View);
		// Subsection - Components Overview
		de.devboost.natspec.library.documentation.Subsection subsection_Components_Overview = documentationSupport.addSubsection(java.util.Arrays.asList(new java.lang.String[] {"Components", "Overview"}), section_Components_View);
		// From the uppermost level
		de.devboost.natspec.library.documentation.Line line_From_the_uppermost_level = documentationSupport.createPlainContents(java.util.Arrays.asList(new String[] {"From", "the", "uppermost", "level"}), subsection_Components_Overview);
		// Subsection - Component One Details View
		de.devboost.natspec.library.documentation.Subsection subsection_Component_One_Details_View = documentationSupport.addSubsection(java.util.Arrays.asList(new java.lang.String[] {"Component", "One", "Details", "View"}), section_Components_View);
		// Zoom into next level
		de.devboost.natspec.library.documentation.Line line_Zoom_into_next_level = documentationSupport.createPlainContents(java.util.Arrays.asList(new String[] {"Zoom", "into", "next", "level"}), subsection_Component_One_Details_View);
		// Subsection - Component Two Details View
		de.devboost.natspec.library.documentation.Subsection subsection_Component_Two_Details_View = documentationSupport.addSubsection(java.util.Arrays.asList(new java.lang.String[] {"Component", "Two", "Details", "View"}), section_Components_View);
		// Zoom into next level
		de.devboost.natspec.library.documentation.Line line_Zoom_into_next_level0 = documentationSupport.createPlainContents(java.util.Arrays.asList(new String[] {"Zoom", "into", "next", "level"}), subsection_Component_Two_Details_View);
		// Subsection - Refine hierarchy as needed...
		de.devboost.natspec.library.documentation.Subsection subsection_Refine_hierarchy_as_needed___ = documentationSupport.addSubsection(java.util.Arrays.asList(new java.lang.String[] {"Refine", "hierarchy", "as", "needed..."}), section_Components_View);
		// Section - Domain Models
		de.devboost.natspec.library.documentation.Section section_Domain_Models = documentationSupport.addSection(java.util.Arrays.asList(new java.lang.String[] {"Domain", "Models"}), documentation_Arch42_Documentation_an_Example_System);
		// Business objects form domain perspective, maybe also technical view
		de.devboost.natspec.library.documentation.Line line_Business_objects_form_domain_perspective__maybe_also_technical_view = documentationSupport.createPlainContents(java.util.Arrays.asList(new String[] {"Business", "objects", "form", "domain", "perspective,", "maybe", "also", "technical", "view"}), section_Domain_Models);
		// Section - Runtime View
		de.devboost.natspec.library.documentation.Section section_Runtime_View = documentationSupport.addSection(java.util.Arrays.asList(new java.lang.String[] {"Runtime", "View"}), documentation_Arch42_Documentation_an_Example_System);
		// What functions is the system supposed to satisfy
		de.devboost.natspec.library.documentation.Line line_What_functions_is_the_system_supposed_to_satisfy = documentationSupport.createPlainContents(java.util.Arrays.asList(new String[] {"What", "functions", "is", "the", "system", "supposed", "to", "satisfy"}), section_Runtime_View);
		// Subsection - User Interface
		de.devboost.natspec.library.documentation.Subsection subsection_User_Interface = documentationSupport.addSubsection(java.util.Arrays.asList(new java.lang.String[] {"User", "Interface"}), section_Runtime_View);
		// Requirements on concrete user interface, if available
		de.devboost.natspec.library.documentation.Line line_Requirements_on_concrete_user_interface__if_available = documentationSupport.createPlainContents(java.util.Arrays.asList(new String[] {"Requirements", "on", "concrete", "user", "interface,", "if", "available"}), subsection_User_Interface);
		// Subsection - Use Cases & Scenarios
		de.devboost.natspec.library.documentation.Subsection subsection_Use_Cases___Scenarios = documentationSupport.addSubsection(java.util.Arrays.asList(new java.lang.String[] {"Use", "Cases", "&", "Scenarios"}), section_Runtime_View);
		// Use cases for actors, their steps.
		de.devboost.natspec.library.documentation.Line line_Use_cases_for_actors__their_steps_ = documentationSupport.createPlainContents(java.util.Arrays.asList(new String[] {"Use", "cases", "for", "actors,", "their", "steps."}), subsection_Use_Cases___Scenarios);
		// Scenarios define concrete path through use case
		de.devboost.natspec.library.documentation.Line line_Scenarios_define_concrete_path_through_use_case = documentationSupport.createPlainContents(java.util.Arrays.asList(new String[] {"Scenarios", "define", "concrete", "path", "through", "use", "case"}), subsection_Use_Cases___Scenarios);
		// Section - Business Rules
		de.devboost.natspec.library.documentation.Section section_Business_Rules = documentationSupport.addSection(java.util.Arrays.asList(new java.lang.String[] {"Business", "Rules"}), documentation_Arch42_Documentation_an_Example_System);
		// Subsection - Decision Rules
		de.devboost.natspec.library.documentation.Subsection subsection_Decision_Rules = documentationSupport.addSubsection(java.util.Arrays.asList(new java.lang.String[] {"Decision", "Rules"}), section_Business_Rules);
		// Rules that are to be encoded when the system supports automated decision processes
		de.devboost.natspec.library.documentation.Line line_Rules_that_are_to_be_encoded_when_the_system_supports_automated_decision_processes = documentationSupport.createPlainContents(java.util.Arrays.asList(new String[] {"Rules", "that", "are", "to", "be", "encoded", "when", "the", "system", "supports", "automated", "decision", "processes"}), subsection_Decision_Rules);
		// Subsection - Validation Rules
		de.devboost.natspec.library.documentation.Subsection subsection_Validation_Rules = documentationSupport.addSubsection(java.util.Arrays.asList(new java.lang.String[] {"Validation", "Rules"}), section_Business_Rules);
		// Rules that operate on domain models and perform some kind of validation
		de.devboost.natspec.library.documentation.Line line_Rules_that_operate_on_domain_models_and_perform_some_kind_of_validation = documentationSupport.createPlainContents(java.util.Arrays.asList(new String[] {"Rules", "that", "operate", "on", "domain", "models", "and", "perform", "some", "kind", "of", "validation"}), subsection_Validation_Rules);
		// Subsection - Error Handling Rules
		de.devboost.natspec.library.documentation.Subsection subsection_Error_Handling_Rules = documentationSupport.addSubsection(java.util.Arrays.asList(new java.lang.String[] {"Error", "Handling", "Rules"}), section_Business_Rules);
		// Rules that define system behaviour in case of errors
		de.devboost.natspec.library.documentation.Line line_Rules_that_define_system_behaviour_in_case_of_errors = documentationSupport.createPlainContents(java.util.Arrays.asList(new String[] {"Rules", "that", "define", "system", "behaviour", "in", "case", "of", "errors"}), subsection_Error_Handling_Rules);
		// Section - Cross-Cutting Requirements
		de.devboost.natspec.library.documentation.Section section_Cross_Cutting_Requirements = documentationSupport.addSection(java.util.Arrays.asList(new java.lang.String[] {"Cross-Cutting", "Requirements"}), documentation_Arch42_Documentation_an_Example_System);
		// Quality requirements that crosscut with scenarios etc.
		de.devboost.natspec.library.documentation.Line line_Quality_requirements_that_crosscut_with_scenarios_etc_ = documentationSupport.createPlainContents(java.util.Arrays.asList(new String[] {"Quality", "requirements", "that", "crosscut", "with", "scenarios", "etc."}), section_Cross_Cutting_Requirements);
		// Subsection - Usability
		de.devboost.natspec.library.documentation.Subsection subsection_Usability = documentationSupport.addSubsection(java.util.Arrays.asList(new java.lang.String[] {"Usability"}), section_Cross_Cutting_Requirements);
		// Subsection - Accessibility
		de.devboost.natspec.library.documentation.Subsection subsection_Accessibility = documentationSupport.addSubsection(java.util.Arrays.asList(new java.lang.String[] {"Accessibility"}), section_Cross_Cutting_Requirements);
		// Subsection - Reliability
		de.devboost.natspec.library.documentation.Subsection subsection_Reliability = documentationSupport.addSubsection(java.util.Arrays.asList(new java.lang.String[] {"Reliability"}), section_Cross_Cutting_Requirements);
		// Subsection - Responsibility
		de.devboost.natspec.library.documentation.Subsection subsection_Responsibility = documentationSupport.addSubsection(java.util.Arrays.asList(new java.lang.String[] {"Responsibility"}), section_Cross_Cutting_Requirements);
		// Subsection - Security
		de.devboost.natspec.library.documentation.Subsection subsection_Security = documentationSupport.addSubsection(java.util.Arrays.asList(new java.lang.String[] {"Security"}), section_Cross_Cutting_Requirements);
		// Subsection - Persistence
		de.devboost.natspec.library.documentation.Subsection subsection_Persistence = documentationSupport.addSubsection(java.util.Arrays.asList(new java.lang.String[] {"Persistence"}), section_Cross_Cutting_Requirements);
		// Section - Risks
		de.devboost.natspec.library.documentation.Section section_Risks = documentationSupport.addSection(java.util.Arrays.asList(new java.lang.String[] {"Risks"}), documentation_Arch42_Documentation_an_Example_System);
		// Section - Glossary
		de.devboost.natspec.library.documentation.Section section_Glossary = documentationSupport.addSection(java.util.Arrays.asList(new java.lang.String[] {"Glossary"}), documentation_Arch42_Documentation_an_Example_System);
		// Define Term : Definition
		documentationSupport.addTerminoligyEntry(java.util.Arrays.asList(new java.lang.String[] {"Term"}), java.util.Arrays.asList(new java.lang.String[] {"Definition"}), documentation_Arch42_Documentation_an_Example_System);
		
		
		Documentation documentation = documentationSupport.getDocumentation();
		DocumentationGenerator generator = new DocumentationGenerator();
		generator.saveDocumentationToFile(documentation);
	
	}
}
