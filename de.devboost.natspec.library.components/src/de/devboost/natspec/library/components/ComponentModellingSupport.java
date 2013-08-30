package de.devboost.natspec.library.components;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import de.devboost.natspec.annotations.TextSyntax;
import de.devboost.natspec.library.components.components.Actor;
import de.devboost.natspec.library.components.components.Component;
import de.devboost.natspec.library.components.components.ComponentContainer;
import de.devboost.natspec.library.components.components.ComponentUser;
import de.devboost.natspec.library.components.components.ComponentsFactory;

public class ComponentModellingSupport {

	
	private Map<String, List<ComponentUser>> usedToUsers = new HashMap<String, List<ComponentUser>>();

	public ComponentModellingSupport() {
	
	}

	@TextSyntax("Container #1")
	public ComponentContainer createContainerComponent(List<String> name) {
		ComponentContainer container = ComponentsFactory.eINSTANCE
				.createComponentContainer();
		container.setName(StringUtils.join(name, " "));
		return container;

	}

	@TextSyntax("Component #1")
	public Component createSubComponent(List<String> name,
			ComponentContainer parent) {
		Component component = ComponentsFactory.eINSTANCE.createComponent();
		String explodedName = StringUtils.join(name, " ");
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
		actor.setName(StringUtils.join(name, " "));
		parent.getExternalActors().add(actor);
	}

	@TextSyntax("Uses #1")
	public void createUseRelation(List<String> usedName, ComponentUser user,
			ComponentContainer parent) {
		String usedComponent = StringUtils.join(usedName, " ");
		for (Component c : parent.getSubComponents()) {
			if (usedComponent.equals(c.getName())) {
				user.getUses().add(c);
			}
		}
		addMapMapping(usedToUsers, usedComponent, user);
	}

	@TextSyntax("Description: #1")
	public void createComponentDescription(List<String> description,
			Component component) {
		component.setDescription(StringUtils.join(description, " "));
	}

	
	/**
	 * Adds the given value to the list that is reference by the key in the map.
	 * If the key does not yet reference a list a new list is created and the
	 * values is added to this list. This methods is useful for multi-value
	 * maps (i.e., maps where one key references to a list of values).
	 * 
	 * @param map the map to add the value to
	 * @param key the key to use
	 * @param value the value to add
	 */
	public static <K, V> void addMapMapping(Map<K, List<V>> map, K key, V value) {
		List<V> list = map.get(key);
		if (list == null) {
			list = new LinkedList<V>();
			map.put(key, list);
		}
		list.add(value);
	}
}
