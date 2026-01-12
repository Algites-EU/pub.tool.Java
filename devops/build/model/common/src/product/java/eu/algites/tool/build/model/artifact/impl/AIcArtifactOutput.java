package eu.algites.tool.build.model.artifact.impl;

import eu.algites.tool.build.model.artifact.intf.AIcArtifactOutputLocalKey;
import eu.algites.tool.build.model.artifact.intf.AIiAbstractArtifact;
import eu.algites.tool.build.model.artifact.intf.AIiArtifactOutput;
import eu.algites.tool.build.model.artifact.intf.AIiArtifactOutputRuntimeReference;
import eu.algites.tool.build.model.artifact.intf.AIiArtifactRuntimeReference;

import java.util.Objects;

/**
 * Projection that also points to the projected artifact (useful for exclusions).
 *
 * @author linhart1
 */
public class AIcArtifactOutput<A extends AIiAbstractArtifact>
		implements AIiArtifactOutput<A> {

	private AIiArtifactRuntimeReference<A> linkedArtifact;
	private AIiArtifactOutputRuntimeReference<A> linkedArtifactOutput;
	private AIcArtifactOutputLocalKey outputLocalKey;

	public AIcArtifactOutput() {
	}

	public AIcArtifactOutput(AIiArtifactRuntimeReference<A> linkedArtifact, final AIcArtifactOutputLocalKey aOutputLocalKey) {
		this.linkedArtifact = linkedArtifact;
		outputLocalKey = aOutputLocalKey;
	}

	@Override
	public AIiArtifactRuntimeReference<A> getLinkedArtifact() {
		return linkedArtifact;
	}

	public void setLinkedArtifact(AIiArtifactRuntimeReference<A> aLinkedArtifact) {
		this.linkedArtifact = aLinkedArtifact;
	}

	/**
	 * @return the linkedArtifactOutput
	 */
	@Override
	public AIiArtifactOutputRuntimeReference<A> getLinkedArtifactOutput() {
		return linkedArtifactOutput;
	}

	/**
	 * @param aLinkedArtifactOutput the linkedArtifactOutput
	 */
	public void setLinkedArtifactOutput(final AIiArtifactOutputRuntimeReference<A> aLinkedArtifactOutput) {
		linkedArtifactOutput = aLinkedArtifactOutput;
	}

	@Override
	public AIcArtifactOutputLocalKey getOutputLocalKey() {
		return outputLocalKey;
	}

	@Override
	public final boolean equals(final Object aO) {
		if (!(aO instanceof AIcArtifactOutput<?> locthat))
			return false;

		return Objects.equals(linkedArtifact, locthat.linkedArtifact) && Objects.equals(
				linkedArtifactOutput,
				locthat.linkedArtifactOutput) && Objects.equals(outputLocalKey, locthat.outputLocalKey);
	}

	@Override
	public int hashCode() {
		int result = Objects.hashCode(linkedArtifact);
		result = 31 * result + Objects.hashCode(linkedArtifactOutput);
		result = 31 * result + Objects.hashCode(outputLocalKey);
		return result;
	}

	@Override
	public String toString() {
		return "AIcArtifactOutput{" +
				"linkedArtifact=" + linkedArtifact +
				", linkedArtifactOutput=" + linkedArtifactOutput +
				", outputLocalKey=" + outputLocalKey +
				'}';
	}
}
