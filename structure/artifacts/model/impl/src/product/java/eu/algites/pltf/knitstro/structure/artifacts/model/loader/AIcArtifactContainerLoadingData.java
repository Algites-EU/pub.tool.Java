package eu.algites.pltf.knitstro.structure.artifacts.model.loader;

import eu.algites.pltf.knitstro.structure.artifacts.model.common.dto.AIcArtifactOutputTypeCoordinateDefDTO;
import eu.algites.tool.devops.build.model.artifact.AIiAbstractArtifact;
import eu.algites.tool.devops.build.model.artifact.AIiAbstractDefinedArtifact;

import java.util.Objects;

/**
 * <p>
 * Title: {@link AIcArtifactContainerLoadingData}
 * </p>
 * <p>
 * Description: TODO: Add description
 * </p>
 * <p>
 * Copyright: Copyright (c) 2026 Artur Linhart, Algites
 * </p>
 * <p>
 * Company: Algites
 * </p>
 *
 * @author linhart1
 * @date 10.01.26 14:50
 */
public class AIcArtifactContainerLoadingData<A extends AIiAbstractArtifact> {
	private A artifact;
	private AIcArtifactOutputTypeCoordinateDefDTO artifactDefDTO;

	AIcArtifactContainerLoadingData(
			final A aArtifact, final AIcArtifactOutputTypeCoordinateDefDTO aArtifactDefDTO) {
		artifact = aArtifact;
		artifactDefDTO = aArtifactDefDTO;
	}

	/**
	 * @return the artifacts
	 */
	public A getArtifact() {
		return artifact;
	}

	/**
	 * @return the artifactDefDTO
	 * @param <ADTO> type of the Artifact DTO
	 */
	@SuppressWarnings("unchecked")
	public <ADTO extends AIcArtifactOutputTypeCoordinateDefDTO> ADTO getArtifactDefDTO() {
		return (ADTO) artifactDefDTO;
	}

	/**
	 * @return the primaryArtifactRegistration
	 */
	public boolean isPrimaryArtifactRegistration() {
		return artifact instanceof AIiAbstractDefinedArtifact;
	}

	/**
	 * @param aArtifact the artifact
	 */
	public void setArtifact(final A aArtifact) {
		artifact = aArtifact;
	}

	/**
	 * @param aArtifactDefDTO the artifactDefDTO
	 */
	public void setArtifactDefDTO(final AIcArtifactOutputTypeCoordinateDefDTO aArtifactDefDTO) {
		artifactDefDTO = aArtifactDefDTO;
	}

	@Override
	public final boolean equals(final Object aO) {
		if (!(aO instanceof AIcArtifactContainerLoadingData<?> locthat))
			return false;

		return Objects.equals(artifact, locthat.artifact)
				&& Objects.equals(artifactDefDTO, locthat.artifactDefDTO);
	}

	@Override
	public int hashCode() {
		int result = Objects.hashCode(artifact);
		result = 31 * result + Objects.hashCode(artifactDefDTO);
		return result;
	}

	@Override
	public String toString() {
		return "AIcArtifactVersionModelLoadingContainer{" +
				"artifact=" + artifact +
				", artifactDefDTO=" + artifactDefDTO +
				'}';
	}
}
