package eu.algites.tool.build.model.artifact.impl;

import eu.algites.tool.build.model.artifact.intf.AIcArtifactOutputLocalKey;
import eu.algites.tool.build.model.artifact.intf.AIiAbstractArtifact;

import java.util.HashSet;
import java.util.Set;

import jakarta.annotation.Nonnull;

/**
 * Simple mutable implementation of {@link AIiAbstractArtifact}.
 * <p>
 * Note: This is primarily intended as a backing class for YAML/JSON loading. Keep it boring and predictable (POJO with getters/setters).
 * </p>
 *
 * @author linhart1
 */
public abstract class AIcAbstractArtifact extends AIcArtifactCoordinate implements AIiAbstractArtifact {

	private final Set<AIcArtifactOutputLocalKey> definedOutputs = new HashSet<>();

	public AIcAbstractArtifact(String aCoordinateId, String aGroupId, String aArtifactId) {
		super(aCoordinateId, aGroupId, aArtifactId);
	}

	public AIcAbstractArtifact(final String aGroupId, final String aArtifactId) {
		super(aGroupId, aArtifactId);
	}

	public AIcAbstractArtifact(final String aArtifactCoordinateId) {
		super(aArtifactCoordinateId);
	}

	protected @Nonnull String getStructureHumanReadableName() {
		return "Artifact";
	}

	@Override
	public Set<AIcArtifactOutputLocalKey> getDefinedOutputs() {
		return definedOutputs;
	}

	@Override
	public String toString() {
		return "AIcAbstractArtifact" + "{ " + super.toString() + "\n} ";
	}

}
