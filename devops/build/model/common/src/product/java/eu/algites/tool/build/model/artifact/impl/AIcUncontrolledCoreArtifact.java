package eu.algites.tool.build.model.artifact.impl;

import eu.algites.tool.build.model.artifact.intf.AIiUncontrolledCoreArtifact;

/**
 * Mutable POJO implementation of {@link AIiUncontrolledCoreArtifact}.
 * <p>
 * This is primarily intended as a backing class for YAML/JSON loading. The artifact kind is fixed to
 * {@link AInArtifactKind#UNCONTROLLED_CORE}.
 * </p>
 * @author linhart1
 */
public class AIcUncontrolledCoreArtifact extends AIcAbstractUncontrolledArtifact implements AIiUncontrolledCoreArtifact {

	public AIcUncontrolledCoreArtifact(final String aCoordinateId, final String aGroupId, final String aArtifactId) {
		super(aCoordinateId, aGroupId, aArtifactId);
	}

	public AIcUncontrolledCoreArtifact(final String aGroupId, final String aArtifactId) {
		super(aGroupId, aArtifactId);
	}

	public AIcUncontrolledCoreArtifact(final String aArtifactCoordinateId) {
		super(aArtifactCoordinateId);
	}

	@Override
	public String toString() {
		return "AIcUncontrolledCoreArtifact" + "{ " + super.toString() + "\n} ";
	}
}
