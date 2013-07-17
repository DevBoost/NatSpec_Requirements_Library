package de.devboost.natspec.library.features;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.mxgraph.view.mxGraph;

import de.devboost.natspec.annotations.TextSyntax;
import de.devboost.natspec.library.function.Component;
import de.devboost.natspec.library.function.Function;
import de.devboost.natspec.library.function.FunctionFactory;
import de.devboost.natspec.library.function.FunctionGroup;
import de.devboost.natspec.library.function.FunctionList;

public class FunctionsSupport {

	private mxGraph graph;
	private FunctionList functionsRoot;
	private FunctionFactory factory;

	public FunctionsSupport(mxGraph graph) {
		this.graph = graph;
		this.factory = FunctionFactory.eINSTANCE;
	}

	@TextSyntax("Function List for #1")
	public FunctionList functionListForPEPProcess(List<String> listname) {
		Object parent = graph.getDefaultParent();
		String listnameString = StringUtils.join(listname, " ");
		Object vertex = graph.insertVertex(parent, null,
				listname, 240, 150, 80, 30);
		
		functionsRoot= factory.createFunctionList();
		functionsRoot.setVertex(vertex);
		functionsRoot.setDescription(listnameString);
		return functionsRoot;
	}

	@TextSyntax("Component: #1")
	public Component createComponent(List<String> componentName,
			FunctionList group) {
		Object parent = graph.getDefaultParent();
		String componentNameString = StringUtils.join(componentName,
				" ");
		Object vertex = graph.insertVertex(parent, null, componentNameString,
				240, 150, 80, 30);
		Component component = factory.createComponent();
		component.setDescription(componentNameString);
		component.setVertex(vertex);
		
		group.getComponents().add(component);
		graph.insertEdge(parent, null, "", group.getVertex(),
				component.getVertex());
		return component;
	}

	@TextSyntax("Function Group: #1 - #2")
	public FunctionGroup createFunctionGroup(List<String> functionDescription, String abbrev,
			Component parentComponent) {
		Object parent = graph.getDefaultParent();
		String functionDescriptionString = StringUtils.join(functionDescription, " ");
		Object vertex = graph.insertVertex(parent, null,
				functionDescriptionString, 240, 150,
				80, 30);
		FunctionGroup functionGroup = factory.createFunctionGroup();
		functionGroup.setVertex(vertex);
		functionGroup.setDescription(functionDescriptionString);
		functionGroup.setAbbrev(abbrev);
		
		parentComponent.getFunctionGroups().add(functionGroup);
		graph.insertEdge(parent, null, "", parentComponent.getVertex(),
				functionGroup.getVertex());
		return functionGroup;
	}

	@TextSyntax("Function: #1")
	public Function createFunction(List<String> functionDescription,
			FunctionGroup group) {
		Object parent = graph.getDefaultParent();
		String functionDescriptionString = StringUtils.join(functionDescription, " ");
		Object vertex = graph.insertVertex(parent, null,
				functionDescriptionString, 240, 150,
				80, 30);
		Function function = factory.createFunction();
		function.setVertex(vertex);
		function.setDescription(functionDescriptionString);
		group.getFunctions().add(function);
		graph.insertEdge(parent, null, "", group.getVertex(),
				function.getVertex());
		return function;
	}

}
