package de.devboost.natspec.library.process;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;

import com.mxgraph.view.mxGraph;

import de.devboost.natspec.annotations.TextSyntax;
import de.devboost.natspec.process.processes.BusinessProcess;
import de.devboost.natspec.process.processes.Input;
import de.devboost.natspec.process.processes.Output;
import de.devboost.natspec.process.processes.ProcessesFactory;
import de.devboost.natspec.process.processes.Step;

public class ProcessModellingSupport {

	private mxGraph graph;
	private Object processRoot;
	private ProcessesFactory processFactory = ProcessesFactory.eINSTANCE;

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
	public BusinessProcess createProcess(List<String> processname) {
		Object parent = graph.getDefaultParent();
		Object vertex = graph.insertVertex(parent, null,
				StringUtils.join(processname, " "), 240, 150, 80, 30);
		BusinessProcess process = processFactory.createBusinessProcess();
		process.setVertex(vertex);
		process.setName(StringUtils.join(processname, " "));
		graph.insertEdge(parent, null, "", processRoot, vertex);
		return process;
	}

	@TextSyntax("Executed by: #1")
	public void createRoles(List<String> roles, BusinessProcess process) {
		process.getRoles().add(StringUtils.join(roles, " "));
	}

	@TextSyntax("Step #1")
	public Step createStep(List<String> stepname,
			BusinessProcess businessProcess) {
		return createStepInModel(stepname, businessProcess);
	}

	private Step createStepInModel(List<String> stepname,
			BusinessProcess businessProcess) {
		Object parent = graph.getDefaultParent();
		Object stepVertex = graph.insertVertex(parent, null,
				StringUtils.join(stepname, " "), 240, 150, 80, 30);
		graph.insertEdge(parent, null, "", businessProcess.getVertex(),
				stepVertex);
		Step step = processFactory.createStep();
		businessProcess.getSteps().add(step);
		step.setName(StringUtils.join(stepname, " "));
		step.setVertex(stepVertex);
		return step;
	}

	@TextSyntax("Automated Step #1")
	public Step createAutomatedStep(List<String> stepname,
			BusinessProcess businessProcess) {
		List<String> stepnames = new LinkedList<String>();
		stepnames.addAll(stepname);
		stepnames.add("[AUTOMATED]");
		return createStep(stepnames, businessProcess);
	}

	@TextSyntax("External Input: #1 from: #2")
	public void createStepInputFrom(List<String> inputName,
			List<String> fromName, Step step) {
		Input input = createInputInModel(inputName, step, fromName);
		input.setExternal(true);
	}

	@TextSyntax("Optional External Input: #1 from: #2")
	public void createOptinoalStepInputFrom(List<String> inputName,
			List<String> fromName, Step step) {
		Input input = createInputInModel(inputName, step, fromName);
		input.setExternal(true);
		input.setOptional(true);
	}

	@TextSyntax("Input: #1")
	public Input createStepInput(List<String> inputName, Step step) {
		return createInputInModel(inputName, step,
				Collections.<String> emptyList());
	}

	private Input createInputInModel(List<String> inputName, Step step,
			List<String> from) {
		Input input = processFactory.createInput();
		step.getInputs().add(input);
		input.setName(StringUtils.join(inputName, " "));
		return input;
	}

	@TextSyntax("Rationale: #1")
	public void addRationale(List<String> rationale, Step step) {
		step.setRationale(StringUtils.join(rationale, " "));
	}

	@TextSyntax("Output: #1")
	public void createStepOutput(List<String> output, Step step) {
		Output stepOutput = processFactory.createOutput();
		step.getOutputs().add(stepOutput);
		stepOutput.setName(StringUtils.join(output, " "));
	}

	@TextSyntax("Follows: #1")
	public void addFollowsRelation(List<String> preceedingStep,
			Step contextStep, BusinessProcess process) {
		String prevStepName = StringUtils.join(preceedingStep, " ");
		EList<Step> steps = process.getSteps();
		Step prevStep = null;
		for (Step step : steps) {
			if (step.getName().equals(prevStepName)) {
				prevStep = step;
			}
		}
		if (prevStep != null) {
			contextStep.getPreviousSteps().add(prevStep);
		}
	}
}
