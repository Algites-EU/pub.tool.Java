package eu.algites.tool.build.model.artifact.impl;

import eu.algites.tool.build.model.artifact.intf.AIiAbstractArtifact;
import eu.algites.tool.build.model.artifact.intf.AIiAbstractControlledParentRwContainerArtifact;
import eu.algites.tool.build.model.artifact.intf.AIiArtifactDependency;
import eu.algites.tool.build.model.artifact.intf.AIiControlledDependencyDefinitionPolicyArtifact;
import eu.algites.tool.build.model.artifact.intf.AIiControlledProductInterfaceBomArtifact;
import eu.algites.tool.build.model.artifact.intf.AIiControlledProductVariantBomArtifact;

import java.util.List;
import java.util.Objects;

public class AIcControlledProductVariantBomArtifact extends AIcAbstractControlledArtifact
		implements
		  AIiControlledProductVariantBomArtifact,
		  AIiAbstractControlledParentRwContainerArtifact<AIiControlledDependencyDefinitionPolicyArtifact> {

	private AIiControlledDependencyDefinitionPolicyArtifact parent;
	private AIiControlledProductInterfaceBomArtifact productInterfaceBom;
	private List<AIiArtifactDependency<? extends AIiAbstractArtifact>> managedProductVariantDependencies;

	public AIcControlledProductVariantBomArtifact(final String aCoordinateId, final String aGroupId, final String aArtifactId) {
		super(aCoordinateId, aGroupId, aArtifactId);
	}

	public AIcControlledProductVariantBomArtifact(final String aGroupId, final String aArtifactId) {
		super(aGroupId, aArtifactId);
	}

	public AIcControlledProductVariantBomArtifact(final String aArtifactCoordinateId) {
		super(aArtifactCoordinateId);
	}

	@Override
	public AIiControlledDependencyDefinitionPolicyArtifact getParent() {
		return parent;
	}

	public void setParent(AIiControlledDependencyDefinitionPolicyArtifact parent) {
		this.parent = parent;
	}

	@Override
	public AIiControlledProductInterfaceBomArtifact getProductInterfaceBom() {
		return productInterfaceBom;
	}

	public void setProductInterfaceBom(AIiControlledProductInterfaceBomArtifact productInterfaceBom) {
		this.productInterfaceBom = productInterfaceBom;
	}

	@Override
	public List<AIiArtifactDependency<? extends AIiAbstractArtifact>> getManagedProductVariantDependencies() {
		return managedProductVariantDependencies;
	}

	public void setManagedProductVariantDependencies(
			List<AIiArtifactDependency<? extends AIiAbstractArtifact>> managedProductVariantDependencies) {
		this.managedProductVariantDependencies = managedProductVariantDependencies;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + Objects.hashCode(parent);
		result = 31 * result + Objects.hashCode(productInterfaceBom);
		result = 31 * result + Objects.hashCode(managedProductVariantDependencies);
		return result;
	}

	@Override
	public boolean equals(final Object aO) {
		if (!(aO instanceof AIcControlledProductVariantBomArtifact locthat))
			return false;
		if (!super.equals(aO))
			return false;

		return Objects.equals(parent, locthat.parent) && Objects.equals(productInterfaceBom, locthat.productInterfaceBom)
				&& Objects.equals(managedProductVariantDependencies, locthat.managedProductVariantDependencies);
	}

	@Override
	public String toString() {
		return "AIcControlledProductVariantBomArtifact{" + super.toString() + "\n" +
				"parent=" + parent +
				", productInterfaceBom=" + productInterfaceBom +
				", managedProductVariantDependencies=" + managedProductVariantDependencies +
				'}';
	}
}
