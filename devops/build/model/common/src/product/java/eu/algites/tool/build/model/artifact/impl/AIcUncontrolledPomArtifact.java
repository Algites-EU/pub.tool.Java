package eu.algites.tool.build.model.artifact.impl;

/**
 * Mutable POJO implementation of {@link AIiUncontrolledPomArtifact}.
 * <p>
 * This is primarily intended as a backing class for YAML/JSON loading. The artifact kind is fixed to
 * {@link AInArtifactKind#UNCONTROLLED_POM}.
 * </p>
 * @author linhart1
 */
public class AIcUncontrolledPomArtifact extends AIcAbstractUncontrolledArtifact implements AIiUncontrolledPomArtifact {

	public AIcUncontrolledPomArtifact(final String aCoordinateId, final String aGroupId, final String aArtifactId) {
		super(aCoordinateId, aGroupId, aArtifactId);
	}

	public AIcUncontrolledPomArtifact(final String aGroupId, final String aArtifactId) {
		super(aGroupId, aArtifactId);
	}

	public AIcUncontrolledPomArtifact(final String aArtifactCoordinateId) {
		super(aArtifactCoordinateId);
	}

	@Override
	public String toString() {
		return "AIcUncontrolledBomArtifact" + "{ " + super.toString() + "\n} ";
	}
}
