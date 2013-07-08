package de.devboost.natspec.library.ecore;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;

import de.devboost.natspec.annotations.TextSyntax;
import de.devboost.natspec.annotations.TextSyntaxes;

public class EcoreMetamodellingSupport {

	EcoreFactory factory = EcoreFactory.eINSTANCE;
	Map<String, EClass> classes = new HashMap<String, EClass>();
	private EPackage root;

	public EcoreMetamodellingSupport() {
		EPackage ePackage = factory.createEPackage();
		ePackage.setName("core");
		root = ePackage;
	}

	@TextSyntax("Glossary for domain #1")
	public EPackage createEPackage(List<String> name) {
		EPackage ePackage = factory.createEPackage();
		ePackage.setName(explode(name, ""));
		root.getESubpackages().add(ePackage);
		return ePackage;
	}

	@TextSyntax("Term #2 - is #3")
	public EClass createEClass(EPackage pkg, String name,
			List<String> description) {
		EClass eClass = classes.get(name);
		if (eClass == null) {
			eClass = factory.createEClass();
			eClass.setName(name);
			pkg.getEClassifiers().add(eClass);
			classes.put(name, eClass);
		} else {
			pkg.getEClassifiers().add(eClass);
		}
		addDocumentation(eClass, description);
		return eClass;
	}

	@TextSyntaxes({ @TextSyntax("- has a numeric #2 to describe #3"),
			@TextSyntax("- has a number of #2 to describe #3") })
	public EAttribute addAttribute(EClass eclass, String attributeName,
			List<String> description) {
		return createAttribute(eclass, attributeName,
				EcorePackage.Literals.EINT, description);
	}

	@TextSyntax("- has a flag #2 to describe #3")
	public EAttribute addBoolAttribute(EClass eclass, String attributeName,
			List<String> description) {
		return createAttribute(eclass, attributeName,
				EcorePackage.Literals.EBOOLEAN, description);
	}

	private EAttribute createAttribute(EClass eclass, String attributeName,
			EClassifier dataType, List<String> description) {
		EAttribute attribute = factory.createEAttribute();
		attribute.setName(attributeName);
		attribute.setEType(dataType);
		eclass.getEStructuralFeatures().add(attribute);
		addDocumentation(attribute, description);
		return attribute;
	}

	@TextSyntaxes({ @TextSyntax("- has a textual #2 to describe #3") })
	public EAttribute addTextAttribute(EClass eclass, String attributeName,
			List<String> description) {
		return createAttribute(eclass, attributeName,
				EcorePackage.Literals.ESTRING, description);
	}

	@TextSyntax("- has a unique textual #2 to describe #3")
	public EAttribute HasAUniqueTextualNameToDescribeItsUnique(EClass eclass, String attributeName,
			List<String> description) {
		EAttribute createAttribute = createAttribute(eclass, attributeName,
				EcorePackage.Literals.ESTRING, description);
		createAttribute.setUnique(true);
		return createAttribute;
	}
	
	
	@TextSyntax("- relates to a #1 #2 to #3")
	public EReference addNamedEReference(String referenceName, String referenceTypeName,
			List<String> description, EClass eclass,  EPackage current) {
		return createReference(eclass, referenceName, referenceTypeName, description, current);
	}
	
	@TextSyntax("- relates to a #2 to #3")
	public EReference addEReference(EClass eclass, String referenceTypeName,
			List<String> description, EPackage current) {
		return createReference(eclass, referenceTypeName, referenceTypeName, description, current);
	}

	private EReference createReference(EClass eclass, String referenceName,
			String typeName, List<String> description,
			EPackage current) {
		return createReference(eclass, referenceName, typeName, description, current, false);
	}

	private EReference createReference(EClass eclass, String referenceName, String referenceTypeName,
			List<String> description, EPackage current, boolean isContainment) {
		EReference ereference = factory.createEReference();
		ereference.setName(toFirstLowerCase(referenceName));
		ereference.setContainment(isContainment);
		EClass referenceType = classes.get(referenceTypeName);
		if (referenceType == null) {
			referenceType = createEClass(current, referenceTypeName,
					Collections.<String> emptyList());
		}
		ereference.setEType(referenceType);
		eclass.getEStructuralFeatures().add(ereference);
		addDocumentation(ereference, description);
		return ereference;
	}


	@TextSyntax("defines a specialization of term #2")
	public void setSupertype(EClass subtype, String supertypeName,
			EPackage current) {
		EClass supertype = classes.get(supertypeName);
		if (supertype == null) {
			supertype = createEClass(current, supertypeName, null);
		}
		subtype.getESuperTypes().add(supertype);
	}

	@TextSyntax("- relates to a list of #1 to #2")
	public EReference relatedToAListOfReference(String referenceTypeName,
			List<String> description, EClass eclass, EPackage containingPackage) {
		EReference ereference = createReference(referenceTypeName,
				referenceTypeName, description, eclass, containingPackage);
		return ereference;
	}

	@TextSyntax("- relates to a list of #1 #2 to #3")
	public EReference relatedToAListOfReference(String referenceName,
			String referenceTypeName, List<String> description, EClass eclass,
			EPackage current) {
		EReference ereference = createReference(referenceName,
				referenceTypeName, description, eclass, current);
		return ereference;
	}

	private EReference createReference(String referenceName,
			String referenceTypeName, List<String> description, EClass eclass,
			EPackage current) {
		EReference ereference = factory.createEReference();
		ereference.setName(toFirstLowerCase(referenceName));

		EClass referenceType = classes.get(referenceTypeName);
		if (referenceType == null) {
			referenceType = createEClass(current, referenceTypeName,
					Collections.<String> emptyList());
		}
		ereference.setEType(referenceType);
		ereference.setUpperBound(-1);
		eclass.getEStructuralFeatures().add(ereference);
		addDocumentation(ereference, description);
		return ereference;
	}

	public EPackage getRoot() {
		return root;
	}

	private String toFirstLowerCase(String referenceTypeName) {
		return referenceTypeName.substring(0, 1).toLowerCase()
				+ referenceTypeName.substring(1);
	}

	private void addDocumentation(EModelElement element,
			List<String> description) {
		EAnnotation annotation = factory.createEAnnotation();
		annotation.setSource("http://www.eclipse.org/emf/2002/GenModel");
		annotation.getDetails().put("documentation", explode(description, " "));
		element.getEAnnotations().add(annotation);
	}

	private String explode(List<String> description, String separator) {
		String out = "";
		for (String string : description) {
			out += string;
			if (description.indexOf(string ) < description.size()) {
				out += separator;
			}
		}
		return out.trim();
	}

	private List<String> removeListSeparator(List<String> values,
			String separator) {
		List<String> cleanedList = new LinkedList<String>();
		for (String value : values) {
			if (value.startsWith(separator)) {
				value = value.substring(1);
			}
			if (value.endsWith(separator)) {
				value = value.substring(0, value.length() - 1);
			}
			cleanedList.add(value);

		}
		return cleanedList;
	}

	@TextSyntax("- has a #1 of #2 or #3 to #4")
	public void createEnum(String enumType, List<String> values, String value,
			List<String> description, EClass containingClass,
			EPackage containingPackage) {
		EEnum eenum = factory.createEEnum();
		eenum.setName(enumType);
		containingPackage.getEClassifiers().add(eenum);

		List<String> allValues = new LinkedList<String>();
		allValues.addAll(removeListSeparator(values, ","));
		allValues.add(value);
		int counter = 0;
		for (String enumValue : allValues) {
			EEnumLiteral eEnumLiteral = factory.createEEnumLiteral();
			eenum.getELiterals().add(eEnumLiteral);
			eEnumLiteral.setValue(counter);
			eEnumLiteral.setLiteral(enumValue);
			counter++;
			eEnumLiteral.setName(enumValue.toUpperCase());
		}

		createAttribute(containingClass, toFirstLowerCase(enumType), eenum,
				description);
	}

	@TextSyntax("- contains a list of #1 to #2")
	public EReference createContainmentRefererenceList(String typeName, List<String> documentation, EClass containingClass, EPackage currentPackage) {
		EReference createReference = createReference(containingClass, toFirstLowerCase(typeName), typeName, documentation, currentPackage, true);
		createReference.setUpperBound(-1);
		return createReference;
	
	}

	@TextSyntax("- contains a list of #1 #2 to #3")
	public EReference createContainmentRefererenceList(String referenceName, String typeName, List<String> documentation, EClass containingClass, EPackage currentPackage) {
		EReference createReference = createReference(containingClass, referenceName, typeName, documentation, currentPackage, true);
		createReference.setUpperBound(-1);
		return createReference;
	
	}
	
	@TextSyntax("- contains a #1 to #2")
	public EReference createContainmentRefernce(String typeName, List<String> documentation, EClass containingClass, EPackage currentPackage) {
		return createReference(containingClass, toFirstLowerCase(typeName), typeName, documentation, currentPackage, true);
	}


	@TextSyntax("- contains a #1 #2 to #3")
	public EReference createContainmentRefernce(String referenceName, String typeName, List<String> documentation, EClass containingClass, EPackage currentPackage) {
		return createReference(containingClass, referenceName, typeName, documentation, currentPackage, true);
	}

	@TextSyntax("is abstract")
	public void isAbstract(EClass currentClass) {
		currentClass.setAbstract(true);
	}

	
	

}
