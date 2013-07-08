package de.devboost.natspec.library.components.doc;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.SwingConstants;

import org.eclipse.emf.common.util.EList;

import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.util.mxMorphing;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.view.mxEdgeStyle;
import com.mxgraph.view.mxGraph;

import de.devboost.natspec.library.components.components.Actor;
import de.devboost.natspec.library.components.components.Component;
import de.devboost.natspec.library.components.components.ComponentContainer;
import de.devboost.natspec.library.components.components.ComponentUser;

public class ComponentDiagramGenerator extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1040057978683964535L;

	private Object componentRoot;
	private mxGraph graph;
	private mxGraphComponent graphComponent;

	private Map<String, mxCell> vertexMap = new HashMap<>();

	private StyleBuilder containerStyle;
	private StyleBuilder componentStyle;
	private StyleBuilder actorStyle;

	private Object containerVertex;

	private StyleBuilder usesEdgeStyle;

	public ComponentDiagramGenerator() {
		super("_NatSpecTemplate");
		graph = new mxGraph();
		graphComponent = new mxGraphComponent(graph);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1200, 1000);
		this.setVisible(true);
		graph.setGridEnabled(true);
		componentRoot = graph.getDefaultParent();
	}

	public void createComponentDiagram(ComponentContainer container) {

		graph.getModel().beginUpdate();
		
		try {
			EList<Actor> externalActors = container.getExternalActors();
			for (Actor actor : externalActors) {
				String name = actor.getName();
				StyleBuilder actorStyle = getActorStyle();
				createGraphVertex(componentRoot, name, actorStyle);
			}
			String componentName = container.getName();
			StyleBuilder containerStyle = getContainerStyle();
			containerVertex = createGraphVertex(componentRoot,
					componentName, containerStyle );
			EList<Component> subComponents = container.getSubComponents();
			
			for (Component component : subComponents) {
				String vertextName = component.getName();
				Object componentVertex = createGraphVertex(containerVertex,
						vertextName, getComponentStyle());
				EList<ComponentUser> usedBy = component.getUsedBy();
				for (ComponentUser componentUser : usedBy) {
					Object userVertex = vertexMap.get(componentUser.getName());
					String id = componentUser.getName() + "->" + componentName;
					createGraphEdge(componentVertex, userVertex, id, getUsesEdgeStyle());
				}
			}
			layoutGraph();
		} finally {
			graph.getModel().endUpdate();
		}

		getContentPane().add(graphComponent);

	}

	

	private void createGraphEdge(Object componentVertex, Object userVertex, String id, StyleBuilder styleBuilder) {
		graph.insertEdge(containerVertex, id, "<uses>", userVertex,
				componentVertex, styleBuilder.getStyle());
	}

	private StyleBuilder getUsesEdgeStyle() {
		if (usesEdgeStyle == null) {
			usesEdgeStyle = new StyleBuilder();
			usesEdgeStyle.addStyle(mxConstants.STYLE_DASHED, true);
			//usesEdgeStyle.addStyle(mxConstants.STSTYLE_NOEDGESTYLE, true);
		}
		return usesEdgeStyle;
	}
	
	private StyleBuilder getComponentStyle() {
		if (componentStyle == null) {
			componentStyle = new StyleBuilder();
			componentStyle.addStyle(mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_TOP);
			componentStyle.addStyle(mxConstants.STYLE_SPACING, 10);
			componentStyle.addStyle(mxConstants.STYLE_FILLCOLOR, "lightblue");
			
		}
		return componentStyle;
	}

	private StyleBuilder getContainerStyle() {
		if (containerStyle == null) {
			containerStyle = new StyleBuilder();
			containerStyle.addStyle(mxConstants.STYLE_VERTICAL_LABEL_POSITION, mxConstants.ALIGN_TOP);
			containerStyle.addStyle(mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_BOTTOM);
			containerStyle.addStyle(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_LEFT);
			containerStyle.addStyle(mxConstants.STYLE_FILLCOLOR, "yellow");
			containerStyle.addStyle(mxConstants.STYLE_SPACING, 0);
			containerStyle.addStyle(mxConstants.STYLE_STROKECOLOR, "grey");
			containerStyle.addStyle(mxConstants.STYLE_AUTOSIZE, 1);
		}
		return containerStyle;
	}

	private StyleBuilder getActorStyle() {
		if (actorStyle == null) {
			actorStyle = new StyleBuilder();
			actorStyle.addStyle(mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_BOTTOM);
			
		}
		return actorStyle;
	}

	private Object createGraphVertex(Object containerVertex, String vertextName, StyleBuilder styleBuilder) {
		mxCell newVertex = (mxCell) graph.insertVertex(containerVertex, null,
				vertextName, 240, 150, 150, 50, "", false);
		newVertex.setStyle(styleBuilder.getStyle());
		vertexMap.put(vertextName, newVertex);
		return newVertex;
	}

	public void layoutGraph() {

		if (graph != null) {

			
			final mxHierarchicalLayout layout = new mxHierarchicalLayout(graph, SwingConstants.WEST);
			layout.setResizeParent(false);
			layout.setParentBorder(20);
			layout.setInterHierarchySpacing(30);
			layout.setInterRankCellSpacing(100);
//			layout.setIntraCellSpacing(10);
		//	layout.setFineTuning(true);
	//		layout.setUseBoundingBox(true);
			graph.getModel().beginUpdate();
			try {
				layoutCellsAndChildren(layout, containerVertex);
				//layout.execute(graph);
				//layout.execute(graph.getDefaultParent());

			} finally {
				mxMorphing morph = new mxMorphing(graphComponent, 20, 1.2, 20);

				morph.addListener(mxEvent.DONE, new mxIEventListener() {

					public void invoke(Object sender, mxEventObject evt) {
						graph.getModel().endUpdate();
					}

				});

				morph.startAnimation();
			}
			resizeCellAndChildren(graph.getDefaultParent());
		}

	}

	private void resizeCellAndChildren(Object cell) {
		Object[] cells = graph.getChildVertices(cell);
		for (int i = 0; i < cells.length; i++) {
			resizeCellAndChildren(cells[i]);
			graph.updateGroupBounds(new Object[] { cell },
					30, false);
			
		}
	}

	private void layoutCellsAndChildren(final mxIGraphLayout layout, Object cell) {
		Object[] childVertices = graph.getChildVertices(cell);
		if (childVertices != null && childVertices.length > 0) {
			layout.execute(cell);
			for (int i = 0; i < childVertices.length; i++) {
				Object child = childVertices[i];
				layoutCellsAndChildren(layout, child);
			}
			
		}
	}

}
