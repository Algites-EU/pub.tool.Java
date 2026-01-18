package eu.algites.tool.devops.build.model.artifact;

import eu.algites.lib.common.object.stringoutput.AInStringOutputMode;
import eu.algites.lib.common.object.stringoutput.AIsStringOutputUtils;
import eu.algites.tool.devops.build.model.common.AIcContainedArtifactLocalKey;
import eu.algites.tool.devops.build.model.common.AIiArtifactOutputType;
import eu.algites.tool.devops.build.model.common.version.AIiVersionContext;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import jakarta.annotation.Nonnull;

public class AIcControlledArtifact extends AIcAbstractArtifact implements AIiControlledArtifact {

	private String name;
	private String description;

	private final Set<AIiArtifactOutputType> definedOutputs = new HashSet<>();
	private AIiVersionContext versionContext;
	private Path artifactConfigurationFile;

	private Map<AIcContainedArtifactLocalKey, AIiControlledArtifact> containedArtifacts = new HashMap<>();

	private AIiAbstractArtifact parent;
	private List<AIiArtifactDependency<? extends AIiAbstractArtifact>> dependencies;

	public AIcControlledArtifact(final String aCoordinateId, final String aGroupId, final String aArtifactId) {
		super(aCoordinateId, aGroupId, aArtifactId);
	}

	public AIcControlledArtifact(final String aGroupId, final String aArtifactId) {
		super(aGroupId, aArtifactId);
	}

	public AIcControlledArtifact(final String aArtifactCoordinateId) {
		super(aArtifactCoordinateId);
	}

	protected @Nonnull String getStructureHumanReadableName() {
		return "Controlled Artifact";
	}

	/**
	 * @return the name
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * @param aName the name
	 */
	public void setName(final String aName) {
		name = aName;
	}

	/**
	 * @return the description
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/**
	 * @param aDescription the description
	 */
	public void setDescription(final String aDescription) {
		description = aDescription;
	}

	@Override
	public Set<AIiArtifactOutputType> getDefinedOutputs() {
		return definedOutputs;
	}

	@Override
	public AIiVersionContext getVersionContext() {
		return versionContext;
	}

	public void setVersionContext(AIiVersionContext versionContext) {
		this.versionContext = versionContext;
	}

	@Override
	public Path getArtifactConfigurationFile() {
		return artifactConfigurationFile;
	}

	/**
	 * @param aArtifactConfigurationFile the artifactConfigurationFile
	 */
	public void setArtifactConfigurationFile(final Path aArtifactConfigurationFile) {
		artifactConfigurationFile = aArtifactConfigurationFile;
	}

	@Override
	public Map<AIcContainedArtifactLocalKey, AIiControlledArtifact> getContainedArtifacts() {
		return containedArtifacts;
	}

	/**
	 * Sets the contained artifacts.
	 * @param aContainedArtifacts the containedArtifacts to set
	 */
	public void setContainedArtifacts(Map<AIcContainedArtifactLocalKey, AIiControlledArtifact> aContainedArtifacts) {
		this.containedArtifacts = aContainedArtifacts;
	}

	@Override
	public AIiAbstractArtifact getParent() {
		return parent;
	}

	public void setParent(AIiAbstractArtifact aParent) {
		this.parent = aParent;
	}

	@Override
	public List<AIiArtifactDependency<? extends AIiAbstractArtifact>> getDependencies() {
		return dependencies;
	}

	public void setDependencies(List<AIiArtifactDependency<? extends AIiAbstractArtifact>> aDependencies) {
		this.dependencies = aDependencies;
	}

	@Override
	public final boolean equals(final Object aO) {
		if (!(aO instanceof AIcControlledArtifact locthat))
			return false;
		if (!super.equals(aO))
			return false;

		return Objects.equals(name, locthat.name) && Objects.equals(description, locthat.description)
				&& definedOutputs.equals(locthat.definedOutputs) && Objects.equals(versionContext, locthat.versionContext)
				&& Objects.equals(artifactConfigurationFile, locthat.artifactConfigurationFile) && Objects.equals(
				containedArtifacts,
				locthat.containedArtifacts) && Objects.equals(parent, locthat.parent) && Objects.equals(
				dependencies,
				locthat.dependencies);
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + Objects.hashCode(name);
		result = 31 * result + Objects.hashCode(description);
		result = 31 * result + definedOutputs.hashCode();
		result = 31 * result + Objects.hashCode(versionContext);
		result = 31 * result + Objects.hashCode(artifactConfigurationFile);
		result = 31 * result + Objects.hashCode(containedArtifacts);
		result = 31 * result + Objects.hashCode(parent);
		result = 31 * result + Objects.hashCode(dependencies);
		return result;
	}

	@Override
	public String toString() {
		if (AIsStringOutputUtils.isUsedStringOutputMode(
				AInStringOutputMode.USER))
			return "{" + super.toString() + ", " +
					"name='" + name + '\'' +
					", description='" + description + '\'' +
					", definedOutputs=" + definedOutputs +
					", versionContext=" + versionContext +
					", artifactConfigurationFile=" + artifactConfigurationFile +
					", containedArtifacts=" + containedArtifacts +
					", parent=" + parent +
					", dependencies=" + dependencies +
					"} ";
		else
			return "AIcControlledArtifact{" + super.toString() + "\n" +
					"            name='" + name + '\'' +
					",             description='" + description + '\'' +
					",             definedOutputs=" + definedOutputs +
					",             versionContext=" + versionContext +
					",             artifactConfigurationFile=" + artifactConfigurationFile +
					",             containedArtifacts=" + containedArtifacts +
					",             parent=" + parent +
					",             dependencies=" + dependencies +
					"} ";
	}
}
