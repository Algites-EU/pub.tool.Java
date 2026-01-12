package eu.algites.tool.build.model.artifact.impl;

import eu.algites.tool.build.model.artifact.intf.AIiControlledProductCoreArtifact;

public class AIcControlledProductCoreArtifact extends AIcAbstractControlledCoreArtifact implements AIiControlledProductCoreArtifact {

	public AIcControlledProductCoreArtifact(final String aCoordinateId, final String aGroupId, final String aArtifactId) {
		super(aCoordinateId, aGroupId, aArtifactId);
	}

	public AIcControlledProductCoreArtifact(final String aGroupId, final String aArtifactId) {
		super(aGroupId, aArtifactId);
	}

	public AIcControlledProductCoreArtifact(final String aArtifactCoordinateId) {
		super(aArtifactCoordinateId);
	}

	@Override
	public String toString() {
		return "AIcControlledProductCoreArtifact" + "{ " + super.toString() + "\n} ";
	}

}
