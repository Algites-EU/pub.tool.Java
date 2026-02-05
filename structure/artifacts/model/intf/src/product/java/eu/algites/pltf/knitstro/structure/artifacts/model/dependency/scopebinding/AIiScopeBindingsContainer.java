package eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding;

import java.util.List;

/**
 * <p>
 * Title: {@link AIiScopeBindingsContainer}
 * </p>
 * <p>
 * Description: Contains multiple bindings
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 18.01.26 18:01
 */
public interface AIiScopeBindingsContainer {
	/**
	 * Gets the dependency scope bindings. During the evaluation
	 * wins the last from them, the order matters.
	 *
	 * @return the dependency scope bindings used for the given exposure policy
	 */
	List<AIiScopeBinding> getBindings();
}
