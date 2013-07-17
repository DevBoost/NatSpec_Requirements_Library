package de.devboost.natspec.library.process;

import javax.swing.JFrame;

import com.mxgraph.layout.mxCompactTreeLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.util.mxMorphing;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.view.mxGraph;

import de.devboost.natspec.library.process.doc.ProcessDocumentationSupport;

public class ExampleProcesses extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8863857814633852153L;

	protected ProcessModellingSupport processModellingSupport;
	protected ProcessDocumentationSupport processDocumentationSupport;

	private mxGraph graph;
	private mxGraphComponent graphComponent;

	public ExampleProcesses() {
		super("ExampleProcesses");
		graph = new mxGraph();
		graphComponent = new mxGraphComponent(graph);

		processModellingSupport = new ProcessModellingSupport(graph);
		processDocumentationSupport = new ProcessDocumentationSupport();
	}

	@SuppressWarnings("unused")
	public void createModel() throws Exception {

		Object parent = graph.getDefaultParent();
		graph.getModel().beginUpdate();

		try {
			// The code in this method is generated from: /de.devboost.natspec.library.documentation.example/src/de/devboost/natspec/library/process/ExampleProcesses.natspec
			// Never change this method or any contents of this file, all local changes will be overwritten.
			
			// Related to PEP Glossar
			processModellingSupport.defaultMatch(java.util.Arrays.asList(new String[] {"Related", "to", "PEP", "Glossar"}));
			// Process Example Process
			de.devboost.natspec.process.processes.BusinessProcess businessProcess_Example_Process = processModellingSupport.createProcess(java.util.Arrays.asList(new java.lang.String[] {"Example", "Process"}));
			// Executed by: Example Stakeholder
			processModellingSupport.createRoles(java.util.Arrays.asList(new java.lang.String[] {"Example", "Stakeholder"}), businessProcess_Example_Process);
			// Step (1) First Step
			de.devboost.natspec.process.processes.Step step__1__First_Step = processModellingSupport.createStep(java.util.Arrays.asList(new java.lang.String[] {"(1)", "First", "Step"}), businessProcess_Example_Process);
			// External Input: the steps external From: Context
			processModellingSupport.createStepInputFrom(java.util.Arrays.asList(new java.lang.String[] {"the", "steps", "external"}), java.util.Arrays.asList(new java.lang.String[] {"Context"}), step__1__First_Step);
			// Rationale: The step's rationale
			processModellingSupport.addRationale(java.util.Arrays.asList(new java.lang.String[] {"The", "step's", "rationale"}), step__1__First_Step);
			// Output: the steps output
			processModellingSupport.createStepOutput(java.util.Arrays.asList(new java.lang.String[] {"the", "steps", "output"}), step__1__First_Step);
			// Step (2) Second Step
			de.devboost.natspec.process.processes.Step step__2__Second_Step = processModellingSupport.createStep(java.util.Arrays.asList(new java.lang.String[] {"(2)", "Second", "Step"}), businessProcess_Example_Process);
			// Follows: First Step
			processModellingSupport.addFollowsRelation(java.util.Arrays.asList(new java.lang.String[] {"First", "Step"}), step__2__Second_Step, businessProcess_Example_Process);
			// External Input: the steps external From: Context
			processModellingSupport.createStepInputFrom(java.util.Arrays.asList(new java.lang.String[] {"the", "steps", "external"}), java.util.Arrays.asList(new java.lang.String[] {"Context"}), step__2__Second_Step);
			// Rationale: The step's rationale
			processModellingSupport.addRationale(java.util.Arrays.asList(new java.lang.String[] {"The", "step's", "rationale"}), step__2__Second_Step);
			// Output: the steps output
			processModellingSupport.createStepOutput(java.util.Arrays.asList(new java.lang.String[] {"the", "steps", "output"}), step__2__Second_Step);
			// Create Process Documentation Table
			processDocumentationSupport.saveFunctionListForDocumentation(businessProcess_Example_Process);
			// 
			processModellingSupport.defaultMatch(java.util.Arrays.asList(new String[] {""}));
			
			layoutGraph();
		} finally {
			graph.getModel().endUpdate();
		}

		getContentPane().add(graphComponent);
	}

	public static void main(String[] args) throws Exception {
		ExampleProcesses frame = new ExampleProcesses();
		frame.createModel();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1400, 800);
		frame.setVisible(true);
	}

	public void layoutGraph() {

		if (graph != null) {
			Object[] cells = graph.getChildVertices(graph.getDefaultParent());
			mxIGraphModel model = graph.getModel();

			model.beginUpdate();
			try {
				for (int i = 0; i < cells.length; i++) {
					graph.updateCellSize(cells[i]);
				}
			} finally {
				model.endUpdate();
			}
		}

		final mxIGraphLayout layout = new mxCompactTreeLayout(graph, true);

		Object cell = graph.getSelectionCell();

		if (cell == null || graph.getModel().getChildCount(cell) == 0) {
			cell = graph.getDefaultParent();
		}

		graph.getModel().beginUpdate();
		try {
			layout.execute(cell);
		} finally {
			mxMorphing morph = new mxMorphing(graphComponent, 20, 1.2, 20);

			morph.addListener(mxEvent.DONE, new mxIEventListener() {

				public void invoke(Object sender, mxEventObject evt) {
					graph.getModel().endUpdate();
				}

			});

			morph.startAnimation();
		}

	}

	public void saveModel() {
		// does nothing yet
	}
}
