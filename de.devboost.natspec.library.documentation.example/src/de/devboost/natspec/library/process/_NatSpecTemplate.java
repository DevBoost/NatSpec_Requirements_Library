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

public class _NatSpecTemplate extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8863857814633852153L;

	protected ProcessModellingSupport processModellingSupport;
	protected ProcessDocumentationSupport processDocumentationSupport;

	private mxGraph graph;
	private mxGraphComponent graphComponent;

	public _NatSpecTemplate() {
		super("_NatSpecTemplate");
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
			/* @MethodBody */
			layoutGraph();
		} finally {
			graph.getModel().endUpdate();
		}

		getContentPane().add(graphComponent);
	}

	public static void main(String[] args) throws Exception {
		_NatSpecTemplate frame = new _NatSpecTemplate();
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
