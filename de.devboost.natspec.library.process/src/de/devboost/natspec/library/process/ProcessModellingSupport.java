package de.devboost.natspec.library.process;

import java.util.List;

import com.mxgraph.view.mxGraph;

import de.devboost.essentials.StringUtils;
import de.devboost.natspec.annotations.TextSyntax;

public class ProcessModellingSupport {

	private mxGraph graph;
	private Object processRoot;

	public ProcessModellingSupport(mxGraph graph) {
		this.graph = graph;
		Object defaultParent = graph.getDefaultParent();
		processRoot = graph.insertVertex(defaultParent, null, "PEP_Process",
				20, 20, 80, 30);

	}

	@TextSyntax("#1")
	public void defaultMatch(List<String> allWords) {
		System.out.println(allWords);
	}

	@TextSyntax("Process #1")
	public BPMNProcess createProcess(List<String> processname) {
		Object parent = graph.getDefaultParent();
		Object vertex = graph.insertVertex(parent, null,
				new StringUtils().explode(processname, " "), 240, 150, 80, 30);
		BPMNProcess process = new BPMNProcess(vertex);
		graph.insertEdge(parent, null, "", processRoot, vertex);
		return process;
	}

	@TextSyntax("Executed by: #1")
	public void createRoles(List<String> roles) {
		// TODO throw new UnsupportedOperationException("Not implemented yet.");
	}

	@TextSyntax("Step #1")
	public Object createStep(List<String> stepname, BPMNProcess parentVertex) {
		Object parent = graph.getDefaultParent();
		Object stepVertex = graph.insertVertex(parent, null,
				new StringUtils().explode(stepname, " "), 240, 150, 80, 30);
		graph.insertEdge(parent, null, "", parentVertex.getMxVertex(),
				stepVertex);
		return stepVertex;
	}

	@TextSyntax("Automated Step #1")
	public void createAutomatedStep(List<String> stepname) {
		// TODO throw new UnsupportedOperationException("Not implemented yet.");
	}

	@TextSyntax("External Input: #1 from: #2")
	public void createStepInputFrom(List<String> inputName,
			List<String> fromName) {
		// TODO throw new UnsupportedOperationException("Not implemented yet.");
	}

	@TextSyntax("Optional External Input: #1 from: #2")
	public void createOptinoalStepInputFrom(List<String> inputName,
			List<String> fromName) {
		// TODO throw new UnsupportedOperationException("Not implemented yet.");
	}

	@TextSyntax("Input: #1")
	public void createStepInput(List<String> inputName) {
		// TODO throw new UnsupportedOperationException("Not implemented yet.");
	}

	@TextSyntax("Rationale: #1")
	public void addRationale(List<String> rationale) {
		// TODO throw new UnsupportedOperationException("Not implemented yet.");
	}

	@TextSyntax("Output: #1")
	public void cereatStepOutput(List<String> output) {
		// TODO throw new UnsupportedOperationException("Not implemented yet.");
	}

	@TextSyntax("Follows: #1")
	public void addFollowsRelation(List<String> preceedingStep) {
		// TODO throw new UnsupportedOperationException("Not implemented yet.");
	}
}
