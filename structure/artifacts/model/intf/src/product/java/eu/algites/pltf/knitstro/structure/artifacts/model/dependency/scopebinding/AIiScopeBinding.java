package eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding;

import eu.algites.pltf.knitstro.structure.artifacts.model.artifact.AIiArtifactCoordinate;
import eu.algites.pltf.knitstro.structure.artifacts.model.common.outputtype.AIiArtifactOutputTypeData;
import eu.algites.pltf.knitstro.structure.artifacts.model.common.outputtype.AInArtifactBuiltinOutputType;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.export.AIiExportModeData;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.localusage.AIiLocalUsageModeData;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.lock.AInBuiltinLockKind;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.purpose.AIiPurposeData;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.sourceset.AIiSourceSetData;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.version.AIiUncontrolledVersionConstraintData;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title: {@link AIiScopeBinding}
 * </p>
 * <p>
 * Description: Defines the rule for the dependency binding
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
public interface AIiScopeBinding {

	/**
	 * Gets the used templates
	 * @return the used templates, The order of the items in the linked hash map defines
	 *    how the templates have to be applied before the other properties
	 *    from this object have to be applied to the artifact.
	 */
	LinkedHashMap<String, AIiDependencyScopeBindingTemplateData> getUsedTemplates();

	/**
	 * Defines the purposes of the artifact.
	 * @return the purposes which have to be used for this binding to be applied to.
	 *    The key is the uid of the dependency purpose.
	 *    if this purpose returns a not null value, then the purpose is considered
	 *    to overwrite the values coming from the templates.
	 */
	Map<String, AIiPurposeData> getPurposes();

	/**
	 * Defines the source sets where the purposes have to be applied to.
	 * @return the source sets which have to be used for this binding to be applied to.
	 *    The key is the uid of the dependency source set.
	 *    if this source set returns a not null value, then the source set is considered
	 *    to overwrite the values coming from the templates.
	 */
	Map<String, AIiSourceSetData> getSourceSets();

	/**
	 * Gets the local inclusion mode of the artifact. This attribute denotes how the artifact will be used
	 * locally in the given purposes, specified by the {@link #getPurposes()} property.
	 * @return Local inclusion mode of the artifact.
	 */
	AIiLocalUsageModeData getLocalInclusionMode();

	/**
	 * Gets the export mode of the artifact dependency binding.
	 * @return the export mode of the artifact dependency binding.
	 */
	AIiExportModeData getExportMode();

	/**
	 * Specifies version of the artifact dependency necessarx for the uncontrolled artifacts
	 * @return the version of the artifact dependency
	 */
	AIiUncontrolledVersionConstraintData getUncontrolledVersionConstraintData();

	/**
	 * Gets the output type of the artifact.
	 * @return the output type of the artifact. Global default
	 *    if unspecified is given by {@link AInArtifactBuiltinOutputType#getDefaultDependencyPackagingClass()}
	 */
	AIiArtifactOutputTypeData getOutputType();

	/**
	 * Gets the dependency exclusions. Here is used only the functionality supported by Gradle and Maven,
	 * since no tool supports more fine-tuned exclusions
	 *
	 * @return the dependency exclusions
	 */
	List<AIiArtifactCoordinate> getExclusions();

	/**
	 * Defines the weight of the binding.
	 * @return the weight of the binding. If not null, then it overwrites the value coming from the templates.
	 */
	Integer getWeight();

	/**
	 * Gets the lock which is applied to this binding
	 * @return the lock which is applied to this binding
	 */
	AInBuiltinLockKind getLock();
}
