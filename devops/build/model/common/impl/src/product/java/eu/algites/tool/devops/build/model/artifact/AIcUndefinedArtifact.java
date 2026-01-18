package eu.algites.tool.devops.build.model.artifact;

import eu.algites.lib.common.object.stringoutput.AInStringOutputMode;
import eu.algites.lib.common.object.stringoutput.AIsStringOutputUtils;
import eu.algites.tool.devops.build.model.common.AIiArtifactOutputType;
import jakarta.annotation.Nonnull;

/**
 * Undefined Artifact implementation.
 * @author linhart1
 */
public class AIcUndefinedArtifact extends AIcAbstractVersionedMultiOutputArtifact implements AIiUndefinedArtifact {

	public AIcUndefinedArtifact(
			final String aCoordinateId,
			final String aGroupId,
			final String aArtifactId,
			final AIiArtifactOutputType aOutput) {
		super(aCoordinateId, aGroupId, aArtifactId, aOutput);
	}

	public AIcUndefinedArtifact(
			final String aGroupId,
			final String aArtifactId,
			final AIiArtifactOutputType aOutput) {
		super(aGroupId, aArtifactId, aOutput);
	}

	public AIcUndefinedArtifact(final String aCoordinateId, final AIiArtifactOutputType aOutput) {
		super(aCoordinateId, aOutput);
	}

	protected @Nonnull String getStructureHumanReadableName() {
		return "Undefined Artifact";
	}

	@Override
	public String toString() {
		if (AIsStringOutputUtils.isUsedStringOutputMode(
				AInStringOutputMode.USER))
			return "{ " + super.toString() + " } ";
		else
			return "AIcUndefinedArtifact" + "{ " + super.toString() + "\n} ";
	}
}
