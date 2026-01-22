package eu.algites.tool.devops.build.model.dependency;

import java.util.List;

/**
 * <p>
 * Title: {@link AIiArtifactDependencyScopeBindingsContainer}
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
public interface AIiArtifactDependencyScopeBindingsContainer {
	/**
	 * Gets the dependency scope
	 *
	 * @return the dependency scope
	 */
	List<AIiArtifactDependencyScopeBinding> getBindings();
}
