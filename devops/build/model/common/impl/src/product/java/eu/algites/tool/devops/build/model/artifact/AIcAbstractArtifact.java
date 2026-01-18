package eu.algites.tool.devops.build.model.artifact;

import eu.algites.lib.common.object.stringoutput.AInStringOutputMode;
import eu.algites.lib.common.object.stringoutput.AIsStringOutputUtils;
import eu.algites.tool.devops.build.model.common.AIiArtifactOutputType;
import jakarta.annotation.Nonnull;

import java.util.HashSet;
import java.util.Set;

/**
 * Simple mutable implementation of {@link AIiAbstractArtifact}.
 * <p>
 * Note: This is primarily intended as a backing class for YAML/JSON loading. Keep it boring and predictable (POJO with getters/setters).
 * </p>
 *
 * @author linhart1
 */
public abstract class AIcAbstractArtifact extends AIcArtifactCoordinate implements AIiAbstractArtifact {

	/**
	 * Constructor.
	 * @param aCoordinateId coordinate Id of the artifact
	 * @param aGroupId group Id of the artifact
	 * @param aArtifactId artifact Id of the artifact
	 */
	public AIcAbstractArtifact(
			@Nonnull String aCoordinateId,
			@Nonnull String aGroupId,
			@Nonnull String aArtifactId) {
		super(aCoordinateId, aGroupId, aArtifactId);
	}

	/**
	 * Constructor.
	 * @param aGroupId group Id of the artifact
	 * @param aArtifactId artifact Id of the artifact
	 */
	public AIcAbstractArtifact(
			@Nonnull final String aGroupId,
			@Nonnull final String aArtifactId) {
		super(aGroupId, aArtifactId);
	}

	/**
	 * Constructor.
	 * @param aCoordinateId coordinate Id of the artifact
	 */
	public AIcAbstractArtifact(@Nonnull final String aCoordinateId) {
		super(aCoordinateId);
	}

	protected @Nonnull String getStructureHumanReadableName() {
		return "Artifact";
	}

	@Override
	public String toString() {
		if (AIsStringOutputUtils.isUsedStringOutputMode(
				AInStringOutputMode.USER))
			return "{ " + super.toString() + " } ";
		else
			return "AIcAbstractArtifact" + "{ " + super.toString() + "\n} ";
	}
}
