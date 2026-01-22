package eu.algites.tool.devops.build.model.dependency;

import eu.algites.lib.common.object.enums.AIiEnumItem;

import java.util.Set;

/**
 * <p>
 * Title: {@link AIiArtifactDependencyScopeSourceSet}
 * </p>
 * <p>
 * Description: Defines the sources set
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 18.01.26 15:28
 */
public interface AIiArtifactDependencyScopeSourceSet extends AIiEnumItem {

	Set<AIiArtifactDependencyScopeSourceCategory> sourceCategories();

}
