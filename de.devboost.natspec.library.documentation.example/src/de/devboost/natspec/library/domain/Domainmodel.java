package de.devboost.natspec.library.domain;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import de.devboost.natspec.library.ecore.EcoreMetamodellingSupport;

public class Domainmodel {

	EcoreMetamodellingSupport ecoreMetamodellingSupport = new EcoreMetamodellingSupport();

	@SuppressWarnings("unused")
	public void createMetamodelFromDomainmodel() {
		// The code in this method is generated from: /de.devboost.natspec.library.documentation.example/src/de/devboost/natspec/library/domain/Domainmodel.natspec
		// Never change this method or any contents of this file, all local changes will be overwritten.
		// Change _NatSpecTemplate.java instead.
		
		// Glossary for domain Airplane Systems
		org.eclipse.emf.ecore.EPackage ePackage_Airplane_Systems = ecoreMetamodellingSupport.createEPackage(java.util.Arrays.asList(new java.lang.String[] {"Airplane", "Systems"}));
		
		saveModel();
	}

	public org.eclipse.emf.ecore.EPackage getRoot() {
		return ecoreMetamodellingSupport.getRoot();
	}

	public static void main(String[] args) {
		new Domainmodel().createMetamodelFromDomainmodel();
	}

	public void run() {
		new Domainmodel().createMetamodelFromDomainmodel();
	}

	public void saveModel() {
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("ecore", new XMIResourceFactoryImpl());

		// Obtain a new resource set
		ResourceSet resSet = new ResourceSetImpl();

		// Create a resource
		Resource resource = resSet.createResource(URI
				.createURI("./src/de/devboost/natspec/library/domain/domainmodel.ecore"));
		resource.getContents().add(ecoreMetamodellingSupport.getRoot());

		// Now save the content.
		try {
			resource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
