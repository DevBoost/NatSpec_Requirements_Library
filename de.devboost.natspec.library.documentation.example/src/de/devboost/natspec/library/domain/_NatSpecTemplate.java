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

public class _NatSpecTemplate {

	EcoreMetamodellingSupport ecoreMetamodellingSupport = new EcoreMetamodellingSupport();

	@SuppressWarnings("unused")
	public void createMetamodelFrom_NatSpecTemplate() {
		/* @MethodBody */
		saveModel();
	}

	public org.eclipse.emf.ecore.EPackage getRoot() {
		return ecoreMetamodellingSupport.getRoot();
	}

	public static void main(String[] args) {
		new _NatSpecTemplate().createMetamodelFrom_NatSpecTemplate();
	}

	public void run() {
		new _NatSpecTemplate().createMetamodelFrom_NatSpecTemplate();
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
