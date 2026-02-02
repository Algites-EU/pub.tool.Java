package eu.algites.pltf.knitstro.structure.artifacts.model.loader;

import static eu.algites.tool.devops.build.model.artifact.AInArtifactClass.UNDEFINED;
import static eu.algites.tool.devops.build.model.utils.AIsArtifactModelUtils.UNSPECIFIED_VERSION_PLACEHOLDER;
import static eu.algites.lib.common.object.stringoutput.AIsStringOutputUtils.useSystemStringOutput;
import static eu.algites.lib.common.object.stringoutput.AIsStringOutputUtils.useStringOutputMode;

import eu.algites.tool.devops.build.model.common.AIcArtifactOutputLocalKey;
import eu.algites.tool.devops.build.model.artifact.AIiAbstractArtifact;
import eu.algites.tool.devops.build.model.artifact.AIiControlledArtifact;
import eu.algites.tool.devops.build.model.artifact.AIiAbstractDefinedArtifact;
import eu.algites.tool.devops.build.model.artifact.AIiArtifactCoordinate;
import eu.algites.tool.devops.build.model.artifact.AIiUncontrolledArtifact;
import eu.algites.tool.devops.build.model.artifact.AIiUndefinedArtifact;
import eu.algites.tool.devops.build.model.utils.AIsArtifactModelUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * <p>
 * Title: {@link AIcArtifactModelLoadContainer}
 * </p>
 * <p>
 * Description: Container for the artifacts and their versions.
 *    This object stores for the given coordinated Id all found version references
 *    of the given artifact with the given coordinatedId
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 10.01.26 14:50
 */
public class AIcArtifactModelLoadContainer<A extends AIiAbstractArtifact> implements AIiArtifactCoordinate {

	private final String coordinateId;
	private final String groupId;
	private final String artifactId;

	private final Map<String, AIcArtifactContainerLoadingData<A>>
			internalArtifactVersions = new HashMap<>();
	private final Map<String, AIcArtifactContainerLoadingData<A>>
			publicArtifactVersions = Collections.unmodifiableMap(internalArtifactVersions);

	AIcArtifactModelLoadContainer(final AIiArtifactCoordinate aArtifactCoordinate) {
		coordinateId = aArtifactCoordinate.getCoordinateId();
		groupId = aArtifactCoordinate.getGroupId();
		artifactId = aArtifactCoordinate.getArtifactId();
		AIsArtifactModelUtils.validateCoordinateConsistency(groupId, artifactId, coordinateId, "Artifact coordinate in ModelLoadingContainer");
	}

	/**
	 * @return the coordinateId
	 */
	@Override
	public String getCoordinateId() {
		return coordinateId;
	}

	/**
	 * @return the groupId
	 */
	@Override
	public String getGroupId() {
		return groupId;
	}

	/**
	 * @return the artifactId
	 */
	@Override
	public String getArtifactId() {
		return artifactId;
	}

	/**
	 * @return the internalArtifactVersions
	 */
	protected Map<String, AIcArtifactContainerLoadingData<A>> getInternalArtifactVersions() {
		return internalArtifactVersions;
	}

	/**
	 * Gets the Mapping of the artifacts to their versions.
	 * As a key is the version string.
	 * The version string can be either {@link AIsArtifactModelUtils#UNSPECIFIED_VERSION_PLACEHOLDER}
	 * in the case the artifact is controlled artifact, which does not have a version specified,
	 * but takes its version from the version context, or it can be already unidentified
	 * artifact coordinate with output, where the version is not known yet and should be during the loading determined
	 * from some other (dependency definition policy) artifacts, which have not been loaded yet.<br/>
	 * <strong>WARNING: This map is unmodifiable. The new items have to be inserted through
	 * the call of the method </strong>
	 * @return the artifactVersions known to the given artifact coordinates.
	 */
	public Map<String, AIcArtifactContainerLoadingData<A>> getArtifactVersions() {
		return publicArtifactVersions;
	}

	/**
	 * Returns the container for the unspecified version of the artifact.
	 * @return container for the unspecified version of the artifact
	 */
	public AIcArtifactContainerLoadingData<A> getUnspecifiedVersionContainer() {
		return internalArtifactVersions.get(UNSPECIFIED_VERSION_PLACEHOLDER);
	}

	private static void checkDefinedArtifactContainsAllOutputsOfUndefinedArtifact(
			final AIiAbstractDefinedArtifact aExistingArtifact, final AIiUndefinedArtifact aUndefinedArtifactToCheck,
			final Supplier<String> aHumanReadableOperationContextInfoSupplier) throws IllegalArgumentException {
		if (!aExistingArtifact.getDefinedOutputs().containsAll(aUndefinedArtifactToCheck.getDefinedOutputs())) {
			useStringOutputMode();
			try {
				Set<AIcArtifactOutputLocalKey> locMissingOutputDefinitions
						= aUndefinedArtifactToCheck.getDefinedOutputs().stream()
						.filter(c -> !aExistingArtifact.getDefinedOutputs().contains(c))
						.collect(Collectors.toSet());
				String locControlledArtifactConfigurationFileName
						= aExistingArtifact instanceof AIiControlledArtifact
						? ((AIiControlledArtifact) aExistingArtifact).getArtifactConfigurationFile().toString() : null;
				throw new IllegalArgumentException("Cannot reference the artifact output if not defined. "
						+ (locControlledArtifactConfigurationFileName == null ? "Uncontrolled" : "Controlled")
						+ " artifact with coordinatedId '" + aUndefinedArtifactToCheck.getCoordinateId()
						+ (locControlledArtifactConfigurationFileName == null ? "" : "' defined in file '" + locControlledArtifactConfigurationFileName)
						+ "' contains no definition of the outputs " + locMissingOutputDefinitions
						+ (locControlledArtifactConfigurationFileName == null ? "' for Version '" + aUndefinedArtifactToCheck.getNormalizedArtifactVersion() + "'": "'")
						+ "\nin following context: " + aHumanReadableOperationContextInfoSupplier.get());
			} finally {
				useSystemStringOutput();
			}
		}
	}

	/**
	 * Appends to the internal Map the given Artifact. The version is taken from the artifact itself. If the same version of the artifact
	 * already exists and is undefined, then the outputs of the registration are appended. If the defined version exists, then
	 * it is checked it defines all outputs forced by the undefined artifact, and if not, then the user error is thrown.
	 *
	 * @param aArtifactContainer undefined artifact to be added.
	 * @param aHumanReadableOperationContextInfoSupplier supplier of the message about the context, put into the thrown exception if the error happens.
	 * @throws IllegalArgumentException If some user or development error occurs.
	 */
	public void putUndefinedArtifact(final AIcArtifactContainerLoadingData<AIiUndefinedArtifact> aArtifactContainer,
			Supplier<String> aHumanReadableOperationContextInfoSupplier) throws IllegalArgumentException {
		AIcArtifactContainerLoadingData<? extends AIiAbstractArtifact> locExistingArtifactContainer
				= getInternalArtifactVersions().get(aArtifactContainer.getArtifact().getNormalizedArtifactVersion());
		if (locExistingArtifactContainer == null)
			getInternalArtifactVersions().put(aArtifactContainer.getArtifact().getNormalizedArtifactVersion(),
					(AIcArtifactContainerLoadingData<A>) aArtifactContainer);
		else {
			if (locExistingArtifactContainer.getArtifact().getArtifactClass() == UNDEFINED) {
				locExistingArtifactContainer.getArtifact().getDefinedOutputs().addAll(aArtifactContainer.getArtifact().getDefinedOutputs());
			} else {
				checkDefinedArtifactContainsAllOutputsOfUndefinedArtifact(
						(AIiAbstractDefinedArtifact) locExistingArtifactContainer.getArtifact(),
						aArtifactContainer.getArtifact(),
						aHumanReadableOperationContextInfoSupplier);
			}
		}
	}

	/**
	 * Appends to the internal Map the given Artifact. The version is taken from the artifact itself. If the same version of the artifact
	 * already exists and is defined, the error is thrown - and if undefined, then first, there is checked the currently passed-defined artifact
	 * contains all defined outputs of the existing undefined artifact. If not, then an exception is thrown; otherwise the new-defined
	 * artifact replaces the old undefined in the map for the given version.
	 *
	 * @param aArtifactContainer uncontrolled artifact to be added.
	 * @param aHumanReadableOperationContextInfoSupplier supplier of the message about the context, put into the thrown exception if the error happens.
	 * @throws IllegalArgumentException If some user or development error occurs.
	 */
	@SuppressWarnings("unchecked")
	public void putUncontrolledArtifact(final AIcArtifactContainerLoadingData<AIiUncontrolledArtifact> aArtifactContainer,
			Supplier<String> aHumanReadableOperationContextInfoSupplier) {
		AIcArtifactContainerLoadingData<A> locExistingArtifactContainer
				= getInternalArtifactVersions().get(aArtifactContainer.getArtifact().getNormalizedArtifactVersion());
		if (locExistingArtifactContainer == null)
			getInternalArtifactVersions().put(aArtifactContainer.getArtifact().getNormalizedArtifactVersion(),
					(AIcArtifactContainerLoadingData<A>) aArtifactContainer);
		else
			switch (locExistingArtifactContainer.getArtifact().getArtifactClass()) {
			case UNDEFINED:
				/* Full definition of the artifact - validate the outputs of the definition
				 * are supporting the already registered outputs and then put the uncontrolled artifact as
				 * a final definition of the artifact: */
				checkDefinedArtifactContainsAllOutputsOfUndefinedArtifact(
						aArtifactContainer.getArtifact(), (AIiUndefinedArtifact) locExistingArtifactContainer.getArtifact(),
						aHumanReadableOperationContextInfoSupplier);
				getInternalArtifactVersions().put(aArtifactContainer.getArtifact().getNormalizedArtifactVersion(),
						(AIcArtifactContainerLoadingData<A>) aArtifactContainer);
				break;
			case CONTROLLED:
					throw new IllegalArgumentException("\\\\ Development error: The definition already exists as a controlled artifact and cannot be overwritten by the uncontrolled artifact. "
							+ "Reference to artifact with coordinatedId '" + aArtifactContainer.getArtifact().getCoordinateId()
							+ "defined in the file '" + ((AIcArtifactContainerLoadingData<AIiControlledArtifact>) locExistingArtifactContainer).getArtifact().getArtifactConfigurationFile()
							+ "' contains the existing controlled Artifact\n" + locExistingArtifactContainer + "\n while is being inserted new uncontrolled artifact new Artifact\n" + aArtifactContainer
							+ "\nin following context: " + aHumanReadableOperationContextInfoSupplier.get());
			case UNCONTROLLED:
				/* Extend the existing outputs about the outputs defined within the passed uncontrolled artifact - join both definitions: */
				locExistingArtifactContainer.getArtifact().getDefinedOutputs().addAll(aArtifactContainer.getArtifact().getDefinedOutputs());
			}
	}

	/**
	 * Appends to the internal Map the given Artifact. The version is taken from the artifact itself. If the same version of the artifact
	 * already exists and is defined, the error is thrown - and if undefined, then first, there is checked the currently passed-defined artifact
	 * contains all defined outputs of the existing undefined artifact. If not, then an exception is thrown; otherwise the new-defined
	 * artifact replaces the old undefined in the map for the given version.
	 *
	 * @param aArtifactContainer controlled artifact to be added.
	 * @param aHumanReadableOperationContextInfoSupplier supplier of the message about the context, put into the thrown exception if the error happens.
	 * @throws IllegalArgumentException If some user or development error occurs.
	 */
	public void putControlledArtifact(final AIcArtifactContainerLoadingData<AIiControlledArtifact> aArtifactContainer,
			Supplier<String> aHumanReadableOperationContextInfoSupplier) {
		AIcArtifactContainerLoadingData<A> locExistingArtifactContainer = getInternalArtifactVersions().get(UNSPECIFIED_VERSION_PLACEHOLDER);
		if (locExistingArtifactContainer == null)
			getInternalArtifactVersions().put(UNSPECIFIED_VERSION_PLACEHOLDER, (AIcArtifactContainerLoadingData<A>) aArtifactContainer);
		else
			switch (locExistingArtifactContainer.getArtifact().getArtifactClass()) {
			case UNDEFINED:
				/* Full definition of the artifact is being added - validate the outputs of the definition
				 * are supporting the already registered outputs and then put the uncontrolled artifact as
				 * a final definition of the artifact: */
				checkDefinedArtifactContainsAllOutputsOfUndefinedArtifact(
						aArtifactContainer.getArtifact(), (AIiUndefinedArtifact) locExistingArtifactContainer.getArtifact(), aHumanReadableOperationContextInfoSupplier);
				getInternalArtifactVersions().put(UNSPECIFIED_VERSION_PLACEHOLDER, (AIcArtifactContainerLoadingData<A>) aArtifactContainer);
				break;
			case CONTROLLED:
				if (Objects.equals(((AIiControlledArtifact)locExistingArtifactContainer.getArtifact()).getArtifactConfigurationFile(),
						aArtifactContainer.getArtifact().getArtifactConfigurationFile()))
					throw new IllegalArgumentException("\\\\ Development error: Repeated defionition of the same controlled artifact with coordinatedId '"
							+ aArtifactContainer.getArtifact().getCoordinateId()
							+ "' - the existing Artifact container\n" + locExistingArtifactContainer
							+ "\n while is being inserted new artifact new Artifact\n" + aArtifactContainer
							+ "\nin following context: " + aHumanReadableOperationContextInfoSupplier.get());
				throw new IllegalArgumentException("Duplicate artifact definition. The artifact with coordinatedId '"
						+ aArtifactContainer.getArtifact().getCoordinateId()
						+ "' is defined in the file '" + ((AIiControlledArtifact)locExistingArtifactContainer.getArtifact()).getArtifactConfigurationFile()
						+ "' as well in the file '" + aArtifactContainer.getArtifact().getArtifactConfigurationFile()
						+ "' \nin following context: " + aHumanReadableOperationContextInfoSupplier.get());
			case UNCONTROLLED:
				throw new IllegalArgumentException("\\\\ Development error: Cannot overwrite the already existing uncontrolled artifact definition "
						+ "for controlled artifacts. Reference to artifact with coordinatedId '" + aArtifactContainer.getArtifact().getCoordinateId()
						+ "' contains the existing Artifact container\n" + locExistingArtifactContainer
						+ "\n while is being inserted new artifact new Artifact\n" + aArtifactContainer
						+ "\nin following context: " + aHumanReadableOperationContextInfoSupplier.get());
			}
	}
}

