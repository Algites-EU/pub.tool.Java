package eu.algites.tool.build.model.artifact.impl;

import eu.algites.tool.build.model.artifact.intf.AIiAbstractArtifact;
import eu.algites.tool.build.model.artifact.intf.AIiAbstractControlledParentRwContainerArtifact;
import eu.algites.tool.build.model.artifact.intf.AIiArtifactDependency;
import eu.algites.tool.build.model.artifact.intf.AIiControlledDependencyDefinitionPolicyArtifact;
import eu.algites.tool.build.model.artifact.intf.AIiAbstractVersionedArtifact;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AIcControlledDependencyDefinitionPolicyArtifact
		extends AIcAbstractControlledArtifact
		implements
		AIiControlledDependencyDefinitionPolicyArtifact,
		  AIiAbstractControlledParentRwContainerArtifact<AIiAbstractArtifact> {

	private AIiAbstractArtifact parent;

	private List<AIiArtifactDependency<? extends AIiAbstractArtifact>> directDependencies;
	private List<AIiArtifactDependency<? extends AIiAbstractVersionedArtifact>> managedPolicyBackgroundDependencies;
	private boolean policyBackgroundBomAutomaticallyGenerated;

	public AIcControlledDependencyDefinitionPolicyArtifact(final String aCoordinateId, final String aGroupId, final String aArtifactId) {
		super(aCoordinateId, aGroupId, aArtifactId);
	}

	public AIcControlledDependencyDefinitionPolicyArtifact(final String aGroupId, final String aArtifactId) {
		super(aGroupId, aArtifactId);
	}

	public AIcControlledDependencyDefinitionPolicyArtifact(final String aArtifactCoordinateId) {
		super(aArtifactCoordinateId);
	}

	@Override
	public boolean isPolicyBackgroundBomAutomaticallyGenerated() {
		return policyBackgroundBomAutomaticallyGenerated;
	}

	/**
	 * @param aPolicyBackgroundBomAutomaticallyGenerated the policyBackgroundBomAutomaticallyGenerated
	 */
	public void setPolicyBackgroundBomAutomaticallyGenerated(final boolean aPolicyBackgroundBomAutomaticallyGenerated) {
		policyBackgroundBomAutomaticallyGenerated = aPolicyBackgroundBomAutomaticallyGenerated;
	}

	@Override
	public AIiAbstractArtifact getParent() {
		return parent;
	}

	public void setParent(AIiAbstractArtifact parent) {
		this.parent = parent;
	}

	@Override
	public List<AIiArtifactDependency<? extends AIiAbstractArtifact>> getDirectDependencies() {
		return directDependencies;
	}

	public void setDirectDependencies(List<AIiArtifactDependency<? extends AIiAbstractArtifact>> directDependencies) {
		this.directDependencies = directDependencies;
	}

	public void addDirectDependency(AIiArtifactDependency<? extends AIiAbstractArtifact> dep) {
		if (directDependencies == null)
			directDependencies = new ArrayList<>();
		directDependencies.add(dep);
	}

	@Override
	public List<AIiArtifactDependency<? extends AIiAbstractArtifact>> getManagedPolicyBackgroundDependencies() {
		return managedPolicyBackgroundDependencies;
	}

	public void setManagedPolicyBackgroundDependencies(
			List<AIiArtifactDependency<? extends AIiAbstractVersionedArtifact>> managedPolicyBackgroundDependencies) {
		this.managedPolicyBackgroundDependencies = managedPolicyBackgroundDependencies;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + Objects.hashCode(policyDefinitionUid);
		result = 31 * result + Objects.hashCode(policyDefinitionVersion);
		result = 31 * result + Objects.hashCode(parent);
		result = 31 * result + Objects.hashCode(directDependencies);
		result = 31 * result + Objects.hashCode(managedPolicyBackgroundDependencies);
		return result;
	}

	@Override
	public boolean equals(final Object aO) {
		if (!(aO instanceof AIcControlledDependencyDefinitionPolicyArtifact locthat))
			return false;
		if (!super.equals(aO))
			return false;

		return Objects.equals(policyDefinitionUid, locthat.policyDefinitionUid) && Objects.equals(
				policyDefinitionVersion,
				locthat.policyDefinitionVersion) && Objects.equals(parent, locthat.parent) && Objects.equals(
				directDependencies,
				locthat.directDependencies) && Objects.equals(
				managedPolicyBackgroundDependencies,
				locthat.managedPolicyBackgroundDependencies);
	}

	@Override
	public String toString() {
		return "AIcControlledPolicyArtifact{" + super.toString() + "\n" +
				"policyDefinitionUid='" + policyDefinitionUid + '\'' +
				", policyDefinitionVersion='" + policyDefinitionVersion + '\'' +
				", parent=" + parent +
				", directDependencies=" + directDependencies +
				", managedPolicyBackgroundDependencies=" + managedPolicyBackgroundDependencies +
				"} ";
	}
}
