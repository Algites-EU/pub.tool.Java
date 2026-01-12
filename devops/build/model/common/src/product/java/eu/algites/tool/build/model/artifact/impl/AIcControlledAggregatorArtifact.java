package eu.algites.tool.build.model.artifact.impl;

import eu.algites.tool.build.model.artifact.intf.AIiControlledAggregatorArtifact;
import eu.algites.tool.build.model.artifact.intf.AIiAbstractControlledArtifact;
import eu.algites.tool.build.model.common.intf.AIcContainedArtifactLocalKey;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AIcControlledAggregatorArtifact extends AIcAbstractControlledArtifact implements AIiControlledAggregatorArtifact {

	private Map<Path, AIiAbstractControlledArtifact> containedArtifacts = new HashMap<>();

	public AIcControlledAggregatorArtifact(final String aCoordinateId, final String aGroupId, final String aArtifactId) {
		super(aCoordinateId, aGroupId, aArtifactId);
	}

	public AIcControlledAggregatorArtifact(final String aGroupId, final String aArtifactId) {
		super(aGroupId, aArtifactId);
	}

	public AIcControlledAggregatorArtifact(final String aArtifactCoordinateId) {
		super(aArtifactCoordinateId);
	}

	@Override
	public Map<AIcContainedArtifactLocalKey, AIiAbstractControlledArtifact> getContainedArtifacts() {
		return containedArtifacts;
	}

	public void setContainedArtifacts(Map<Path, AIiAbstractControlledArtifact> containedArtifacts) {
		this.containedArtifacts = containedArtifacts;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + Objects.hashCode(containedArtifacts);
		return result;
	}

	@Override
	public boolean equals(final Object aO) {
		if (!(aO instanceof AIcControlledAggregatorArtifact locthat))
			return false;
		if (!super.equals(aO))
			return false;

		return Objects.equals(containedArtifacts, locthat.containedArtifacts);
	}

	public String toString() {
		return "AIcControlledAggregatorArtifact{" + super.toString() + "\n" +
				"containedArtifacts=" + containedArtifacts +
				"} ";
	}
}
