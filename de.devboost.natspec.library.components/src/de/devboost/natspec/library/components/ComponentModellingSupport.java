package de.devboost.natspec.library.components;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mxgraph.view.mxGraph;

import de.devboost.essentials.MapUtils;
import de.devboost.essentials.StringUtils;
import de.devboost.natspec.annotations.TextSyntax;
import de.devboost.natspec.library.components.components.Actor;
import de.devboost.natspec.library.components.components.Component;
import de.devboost.natspec.library.components.components.ComponentContainer;
import de.devboost.natspec.library.components.components.ComponentUser;
import de.devboost.natspec.library.components.components.ComponentsFactory;

public class ComponentModellingSupport {

	
	private Map<String, List<ComponentUser>> usedToUsers = new HashMap<>();

	public ComponentModellingSupport() {
	
	}

	@TextSyntax("Container #1")
	public ComponentContainer createContainerComponent(List<String> name) {
		ComponentContainer container = ComponentsFactory.eINSTANCE
				.createComponentContainer();
		container.setName(new StringUtils().explode(name, " "));
		return container;

	}

	@TextSyntax("Component #1")
	public Component createSubComponent(List<String> name,
			ComponentContainer parent) {
		Component component = ComponentsFactory.eINSTANCE.createComponent();
		String explodedName = new StringUtils().explode(name, " ");
		component.setName(explodedName);
		parent.getSubComponents().add(component);
		List<ComponentUser> users = usedToUsers.get(explodedName);
		if (users != null) {
			for (ComponentUser user : users) {
				user.getUses().add(component);
			}
		}
		return component;
	}

	@TextSyntax("Actor #1")
	public void createActor(List<String> name, ComponentContainer parent) {
		Actor actor = ComponentsFactory.eINSTANCE.createActor();
		actor.setName(new StringUtils().explode(name, " "));
		parent.getExternalActors().add(actor);
	}

	@TextSyntax("Uses #1")
	public void createUseRelation(List<String> usedName, ComponentUser user,
			ComponentContainer parent) {
		String usedComponent = new StringUtils().explode(usedName, " ");
		for (Component c : parent.getSubComponents()) {
			if (usedComponent.equals(c.getName())) {
				user.getUses().add(c);
			}
		}
		new MapUtils().addMapMapping(usedToUsers, usedComponent, user);
	}

	@TextSyntax("Description: #1")
	public void createComponentDescription(List<String> description,
			Component component) {
		component.setDescription(new StringUtils().explode(description, " "));
	}

}
