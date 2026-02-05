package eu.algites.pltf.knitstro.structure.artifacts.model.artifact;

import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.AIiArtifactDependency;
import eu.algites.pltf.knitstro.structure.artifacts.model.dependency.scopebinding.AIiScopeBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Dependency that also points to the target artifact.
 *
 * @author linhart1
 */
public class AIcArtifactDependency<A extends AIiAbstractArtifact, AREF extends AIiArtifactRuntimeReference<A>
		implements AIiArtifactDependency<A, AREF> {

	private final AREF linkedArtifact;
	private final List<AIiScopeBinding> bindings = new ArrayList<>();

	public AIcArtifactDependency(final AREF aLinkedArtifact) {
		linkedArtifact = aLinkedArtifact;
	}

	@Override
	public List<AIiScopeBinding> getBindings() {
		return bindings;
	}

	@Override
	public AREF getLinkedArtifact() {
		return linkedArtifact;
	}

}
