package eu.algites.tool.devops.build.model.artifact;

import eu.algites.tool.devops.build.model.dependency.AIiArtifactDependency;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Dependency that also points to the target artifact.
 *
 * @author linhart1
 */
public class AIcArtifactDependency<A extends AIiAbstractArtifact>
		implements AIiArtifactDependency<A> {

	private AIiArtifactDependencyScope dependencyScope;
	private List<AIiArtifactOutput<? extends AIiAbstractArtifact>> dependencyExclusions;
	private AIiArtifactOutput<A> linkedArtifactOutput;

	public AIcArtifactDependency() {
	}

	@Override
	public AIiArtifactOutput<A> getLinkedArtifactOutput() {
		return linkedArtifactOutput;
	}

	/**
	 * @param aLinkedArtifactOutput the linkedArtifactOutput
	 */
	public void setLinkedArtifactOutput(final AIiArtifactOutput<A> aLinkedArtifactOutput) {
		linkedArtifactOutput = aLinkedArtifactOutput;
	}

	@Override
	public List<AIiArtifactOutput<? extends AIiAbstractArtifact>> getExclusions() {
		return dependencyExclusions;
	}

	public void setDependencyExclusions(List<AIiArtifactOutput<? extends AIiAbstractArtifact>> dependencyExclusions) {
		this.dependencyExclusions = dependencyExclusions;
	}

	@Override
	public AIiArtifactDependencyScope getDependencyScope() {
		return dependencyScope;
	}

	public void setDependencyScope(AIiArtifactDependencyScope dependencyScope) {
		this.dependencyScope = dependencyScope;
	}

	public void addDependencyExclusion(AIiArtifactOutput<? extends AIiAbstractArtifact> exclusion) {
		if (dependencyExclusions == null)
			dependencyExclusions = new ArrayList<>();
		dependencyExclusions.add(exclusion);
	}

	@Override
	public final boolean equals(final Object aO) {
		if (!(aO instanceof AIcArtifactDependency<?> locthat))
			return false;

		return Objects.equals(dependencyScope, locthat.dependencyScope) && Objects.equals(
				dependencyExclusions,
				locthat.dependencyExclusions) && Objects.equals(linkedArtifactOutput, locthat.linkedArtifactOutput);
	}

	@Override
	public int hashCode() {
		int result = Objects.hashCode(dependencyScope);
		result = 31 * result + Objects.hashCode(dependencyExclusions);
		result = 31 * result + Objects.hashCode(linkedArtifactOutput);
		return result;
	}

	@Override
	public String toString() {
		return "AIcArtifactDependency{" +
				"dependencyScope=" + dependencyScope +
				", dependencyExclusions=" + dependencyExclusions +
				", linkedArtifactOutput=" + linkedArtifactOutput +
				'}';
	}
}
